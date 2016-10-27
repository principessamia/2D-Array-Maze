import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public class Explorer {

	private final Maze maze;
	private Direction direction;
	private Deque<MazePoint> path;

	public Explorer(Maze maze) {
		this.maze = maze;
		this.path = new LinkedList<>();
	}

	public int getNumberOfEmptySpaces() {
		return maze.getNumberOfEmptySpaces();
	}

	public int getNumberOfWalls() {
		return maze.getNumberOfWalls();
	}

	public void dropIntoStartPoint() {
		this.path = new LinkedList<>();
		setCurrentLocation(getMaze().getStartPoint());
		setDirection(Direction.NORTH);
	}

	public PointType declareWhatExistsAtCoordinate(int row, int column) {
		return maze.getPointType(row, column);
	}

	/**
	 * Dependent on the direction that the explorer is facing, moves
	 * in a straight line until a wall is reached
	 */
	public void moveForward() {
		MazePoint currentLocation = getCurrentLocation();
		int row = currentLocation.getRow();
		int column = currentLocation.getColumn();

		MazePoint[][] mazePoints = getMaze().getMazePoints();
		switch (getDirection()) {
		case NORTH:
			while (row > 0 && row < mazePoints.length) {
				int nextRow = --row;
				MazePoint mazePoint = mazePoints[nextRow][column];
				if (mazePoint.isWall()) {
					break;
				} else {
					setCurrentLocation(mazePoint);
				}
			}
			break;

		case EAST:
			while (column >= 0 && column < mazePoints[row].length - 1) {
				int nextColumn = ++column;
				MazePoint mazePoint = mazePoints[row][nextColumn];
				if (mazePoint.isWall()) {
					break;
				} else {
					setCurrentLocation(mazePoint);
				}
			}
			break;

		case SOUTH:
			while (row >= 0 && row < mazePoints.length - 1) {
				int nextRow = ++row;
				MazePoint mazePoint = mazePoints[nextRow][column];
				if (mazePoint.isWall()) {
					break;
				} else {
					setCurrentLocation(mazePoint);
				}
			}
			break;

		case WEST:
			while (column > 0 && column < mazePoints[row].length) {
				int nextColumn = --column;
				MazePoint mazePoint = mazePoints[row][nextColumn];
				if (mazePoint.isWall()) {
					break;
				} else {
					setCurrentLocation(mazePoint);
				}
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Changes the relative direction the explorer is facing to the left
	 */
	public void turnLeft() {
		int directionLength = Direction.values().length;
		int newDirectionIndex = getDirection().getIndex() + directionLength - 1;
		setDirection(Direction.getByIndex(newDirectionIndex % directionLength));
	}

	/**
	 * Changes the relative direction the explorer is facing to the right
	 */
	public void turnRight() {
		int newDirectionIndex = getDirection().getIndex() + 1;
		int directionLength = Direction.values().length;
		setDirection(Direction.getByIndex(newDirectionIndex % directionLength));
	}

	/**
	 * Declares what is directly in front of the explorer, depending on 
	 * the direction which is faced
	 * 
	 * @return a MazePoint that is directly ahead
	 */
	public MazePoint declareWhatLiesAhead() {
		Map<Direction, MazePoint> neighbours = maze
				.getNeighbours(getCurrentLocation());
		return neighbours.get(getDirection());
	}

	/**
	 * Finds empty space immediately surrounding the explorer
	 * 
	 * @return A map of Direction to MazePoint of any movement options
	 */
	public Map<Direction, MazePoint> declareAllAvailableMovements() {

		Map<Direction, MazePoint> neighbours = maze.getNeighbours(getCurrentLocation());
		Map<Direction, MazePoint> available = neighbours
				.entrySet()
				.stream()
				.filter(neighbour -> !neighbour.getValue().isWall())
				.collect(
						Collectors.toMap(neighbour -> neighbour.getKey(),
								         neighbour -> neighbour.getValue()));

		return available;
	}

	/**
	 * Reports the path of the explorer from the start of the journey
	 * 
	 * @return A String representation of the path the
	 * 		   explorer has taken up to the current location    
	 */
	public String reportRecordOfJourney() {
		StringBuilder builder = new StringBuilder();
		for (Iterator<MazePoint> iterator = path.iterator(); iterator.hasNext();) {
			builder.append(iterator.next()).append("\n");
		}
		return builder.toString();
	}

	public MazePoint getCurrentLocation() {
		return path.peekLast();
	}

	public void setCurrentLocation(MazePoint currentLocation) {
		path.add(currentLocation);
	}

	public Maze getMaze() {
		return maze;
	}

	public Deque<MazePoint> getPath() {
		return path;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

}
