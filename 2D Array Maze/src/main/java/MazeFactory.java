import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class MazeFactory {

	/**
	 * Reads in a given maze file, performs various 
	 * validation and constructs a representation of the maze
	 * 
	 * @param mazeFileName
	 * @return a Maze
	 * @throws MazeException
	 */
	public static Maze generateMaze(String mazeFileName) throws MazeException {
		List<String> mazeLines = readMazeFile(mazeFileName);
		validateMaze(mazeLines);
		return constructMaze(mazeLines);
	}

	/**
	 * Constructs a Maze from the String representations 
	 * of each line, flagging up any invalid characters
	 * 
	 * @param mazeLines
	 * @return a Maze
	 * @throws MazeException
	 */
	protected static Maze constructMaze(List<String> mazeLines)
			throws MazeException {

		MazePoint[][] mazePoints = new MazePoint[mazeLines.size()][mazeLines.get(0).length()];
		Maze maze = new Maze();

		int row = 0;
		for (String line : mazeLines) {

			for (int column = 0; column < line.length(); column++) {

				MazePoint.PointType type = null;

				switch (line.charAt(column)) {
				case 'X':
					maze.setNumberOfWalls();
					type = MazePoint.PointType.WALL;
					break;
				case ' ':
					maze.setNumberOfEmptySpaces();
					type = MazePoint.PointType.SPACE;
					break;
				case 'S':
					type = MazePoint.PointType.START;
					maze.setStartPoint(new MazePoint(row, column, type));
					break;
				case 'F':
					type = MazePoint.PointType.FINISH;
					maze.setFinishPoint(new MazePoint(row, column, type));
					break;
				default:
					throw new MazeException("Invalid char found in maze "
							+ line.charAt(column));
				}

				mazePoints[row][column] = new MazePoint(row, column, type);
			}
			row++;
		}

		maze.setMazePoints(mazePoints);
		return maze;
	}

	/**
	 * Checks the maze to ensure only 1 start point 
	 * and only 1 finish point exist
	 * 
	 * @param a list of lines
	 * @throws MazeException
	 */
	protected static void validateMaze(List<String> lines) throws MazeException {

		long numberOfStartPoints = lines.stream()
				.filter(line -> line.contains("S")).count();
		long numberOfFinishPoints = lines.stream()
				.filter(line -> line.contains("F")).count();

		if (numberOfStartPoints != 1 || numberOfFinishPoints != 1) {
			throw new MazeException("Invalid number of start or finish points");
		}
	}

	/**
	 * Reads in a given maze file from the 
	 * classpath and constructs a list of Strings 
	 * 
	 * @param mazeFileName
	 * @return a list of lines in the file
	 * @throws MazeException
	 */
	protected static List<String> readMazeFile(String mazeFileName)
			throws MazeException {

		URI uri = null;
		try {
			URL systemResource = ClassLoader.getSystemResource(mazeFileName);
			if (systemResource != null) {
				uri = systemResource.toURI();
			} else {
				throw new MazeException("Unable to load file from classpath: "
						+ mazeFileName);
			}
		} catch (URISyntaxException e) {
			throw new MazeException(e);
		}

		List<String> lines = new ArrayList<>();
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(uri))) {
			lines = bufferedReader.lines().collect(Collectors.toList());
		} catch (IOException e) {
			throw new MazeException(e);
		}

		return lines;
	}

};
