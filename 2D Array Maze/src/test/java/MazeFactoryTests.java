import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MazeFactoryTests {

	@Test
	public void readMazeFile() throws MazeException {
		List<String> lines = MazeFactory.readMazeFile("ValidMaze.txt");
		Assert.assertNotNull(lines);
		Assert.assertEquals(15, lines.size());
	}

	@Test(expected = MazeException.class)
	public void readNonExistentMazeFile() throws MazeException {
		MazeFactory.readMazeFile("MazeFileDoesNotExist.txt");
	}

	@Test
	public void validateValidMaze() throws MazeException {
		List<String> lines = MazeFactory.readMazeFile("ValidMaze.txt");
		MazeFactory.validateMaze(lines);
	}

	@Test(expected = MazeException.class)
	public void validateInvalidMaze() throws MazeException {
		List<String> lines = MazeFactory.readMazeFile("InvalidFinishMaze.txt");
		MazeFactory.validateMaze(lines);
	}

	@Test
	public void constructValidMaze() throws MazeException {
		List<String> lines = MazeFactory.readMazeFile("ValidMaze.txt");
		Maze maze = MazeFactory.constructMaze(lines);
		Assert.assertTrue(maze.getSize() > 0);
		Assert.assertNotNull(maze.getFinishPoint());
		Assert.assertNotNull(maze.getStartPoint());
	}

	@Test(expected = MazeException.class)
	public void constructInvalidMaze() throws MazeException {
		List<String> lines = MazeFactory.readMazeFile("InvalidCharMaze.txt");
		MazeFactory.constructMaze(lines);
	}

}
