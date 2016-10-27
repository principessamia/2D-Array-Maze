import java.util.HashMap;
import java.util.Map;

public class Maze {

	private MazePoint[][] mazePoints;
	private MazePoint startPoint;
	private MazePoint finishPoint;
	private int numberOfWalls;
	private int numberOfEmptySpaces;

	public Maze() {
	}

	public Maze(MazePoint[][] mazePoints) {
		this.mazePoints = mazePoints;
	}

	private boolean isValidCoordinate(int row, int column) {

		if (row >= 0 && row < getHeight() && column >= 0 && column < getWidth()) {
			return true;
		} else {
			return false;
		}
	}

	public PointType getPointType(int row, int column) {

		PointType pointType = null;

		if (isValidCoordinate(row, column)) {
			pointType = mazePoints[row][column].getPointType();
		}
		return pointType;
	}

	/**
	 * Based on a given location, retrieves 
	 * any neighbouring points to N,S,E,W if they exist
	 * 
	 * @param currentLocation
	 * @return valid neighbours
	 */
	public Map<Direction, MazePoint> getNeighbours(MazePoint currentLocation) {

		Map<Direction, MazePoint> neighbours = new HashMap<Direction, MazePoint>();

		int row = currentLocation.getRow();
		int column = currentLocation.getColumn();

		if (isValidCoordinate(row - 1, column)) {
			neighbours.put(Direction.NORTH, mazePoints[row - 1][column]);
		}
		if (isValidCoordinate(row + 1, column)) {
			neighbours.put(Direction.SOUTH, mazePoints[row + 1][column]);
		}
		if (isValidCoordinate(row, column - 1)) {
			neighbours.put(Direction.WEST, mazePoints[row][column - 1]);
		}
		if (isValidCoordinate(row, column + 1)) {
			neighbours.put(Direction.EAST, mazePoints[row][column + 1]);
		}
		return neighbours;
	}

	public int getHeight() {
		return getMazePoints().length;
	}

	public int getWidth() {
		return getMazePoints()[0].length;
	}

	public int getSize() {
		return getHeight() * getWidth();
	}

	public int getNumberOfWalls() {
		return numberOfWalls;
	}

	public void setNumberOfWalls() {
		numberOfWalls++;
	}

	public int getNumberOfEmptySpaces() {
		return numberOfEmptySpaces;
	}

	public void setNumberOfEmptySpaces() {
		numberOfEmptySpaces++;
	}

	public void setMazePoints(MazePoint[][] mazePoints) {
		this.mazePoints = mazePoints;
	}

	public MazePoint[][] getMazePoints() {
		return this.mazePoints;
	}

	public void setStartPoint(MazePoint startPoint) {
		this.startPoint = startPoint;
	}

	public MazePoint getStartPoint() {
		return startPoint;
	}

	public MazePoint getFinishPoint() {
		return finishPoint;
	}

	public void setFinishPoint(MazePoint finishPoint) {
		this.finishPoint = finishPoint;
	}
}
