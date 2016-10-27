import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import PointType;

public class ExplorerTests {

	private Maze maze;
	private Explorer explorer;

	@Before
	public void setUp() throws MazeException {
		maze = MazeFactory.generateMaze("ValidMaze.txt");
		explorer = new Explorer(maze);
	}

	@Test
	public void numberOfEmptySpaces() {
		int numberOfEmptySpaces = explorer.getNumberOfEmptySpaces();
		Assert.assertTrue(numberOfEmptySpaces > 0);
		int empty = maze.getSize() - maze.getNumberOfWalls() - 2;
		Assert.assertEquals(empty, numberOfEmptySpaces);
	}

	@Test
	public void numberOfWalls() {
		int numberOfWalls = explorer.getNumberOfWalls();
		Assert.assertTrue(numberOfWalls > 0);
		int walls = maze.getSize() - maze.getNumberOfEmptySpaces() - 2;
		Assert.assertEquals(walls, numberOfWalls);
	}

	@Test
	public void declareWhatExistsAtCoordinate() throws MazeException {
		PointType pointType = explorer.declareWhatExistsAtCoordinate(0, 0);
		Assert.assertEquals(PointType.WALL, pointType);
		pointType = explorer.declareWhatExistsAtCoordinate(12, 5);
		Assert.assertEquals(PointType.SPACE, pointType);
	}

	@Test
	public void dropIntoStartPoint() {
		explorer.dropIntoStartPoint();
		Assert.assertTrue(explorer.getDirection() == Direction.NORTH);
		Assert.assertTrue(explorer.getCurrentLocation().getPointType() == PointType.START);
	}

	@Test
	public void moveForward() {
		explorer.setCurrentLocation(new MazePoint(9,6, PointType.SPACE));
		explorer.setDirection(Direction.NORTH);
		explorer.moveForward();
		Assert.assertEquals(6, explorer.getCurrentLocation().getRow());
		Assert.assertEquals(6, explorer.getCurrentLocation().getColumn());

		explorer.setCurrentLocation(new MazePoint(1,13, PointType.SPACE));
		explorer.setDirection(Direction.WEST);
		explorer.moveForward();
		Assert.assertEquals(1, explorer.getCurrentLocation().getRow());
		Assert.assertEquals(1, explorer.getCurrentLocation().getRow());
	}

	@Test
	public void turnLeft() {
		explorer.dropIntoStartPoint();
		explorer.turnLeft();
		Assert.assertTrue(explorer.getDirection() == Direction.WEST);
		explorer.turnLeft();
		Assert.assertTrue(explorer.getDirection() == Direction.SOUTH);
		explorer.turnLeft();
		Assert.assertTrue(explorer.getDirection() == Direction.EAST);
		explorer.turnLeft();
		Assert.assertTrue(explorer.getDirection() == Direction.NORTH);
	}

	@Test
	public void turnRight() {
		explorer.dropIntoStartPoint();
		explorer.turnRight();
		Assert.assertTrue(explorer.getDirection() == Direction.EAST);
		explorer.turnRight();
		Assert.assertTrue(explorer.getDirection() == Direction.SOUTH);
		explorer.turnRight();
		Assert.assertTrue(explorer.getDirection() == Direction.WEST);
		explorer.turnRight();
		Assert.assertTrue(explorer.getDirection() == Direction.NORTH);
	}

	@Test
	public void declareWhatLiesAhead() {
		explorer.setCurrentLocation(maze.getMazePoints()[1][13]);
		explorer.setDirection(Direction.EAST);
		MazePoint ahead = explorer.declareWhatLiesAhead();
		Assert.assertTrue(ahead.isWall());
	}

	@Test
	public void declareAllMovementOptions() {
		explorer.setCurrentLocation(maze.getMazePoints()[9][13]);
		Map<Direction, MazePoint> allAvailableMovements = explorer
				.declareAllAvailableMovements();
		Set<Direction> keySet = allAvailableMovements.keySet();
		Assert.assertTrue(keySet.contains(Direction.SOUTH));
		Assert.assertTrue(keySet.contains(Direction.NORTH));
		Assert.assertFalse(keySet.contains(Direction.WEST));
		Assert.assertFalse(keySet.contains(Direction.EAST));
	}

	@Test
	public void reportRecordOfJourney() {
		explorer.dropIntoStartPoint();
		explorer.turnRight();
		explorer.moveForward();
		StringBuilder builder = new StringBuilder();
		builder.append("row=3, column=3, pointType=START").append("\n")
			   .append("row=3, column=4, pointType=SPACE").append("\n")
			   .append("row=3, column=5, pointType=SPACE").append("\n")
			   .append("row=3, column=6, pointType=SPACE").append("\n")
			   .append("row=3, column=7, pointType=SPACE").append("\n")
			   .append("row=3, column=8, pointType=SPACE").append("\n")
			   .append("row=3, column=9, pointType=SPACE").append("\n")
			   .append("row=3, column=10, pointType=SPACE").append("\n")
		       .append("row=3, column=11, pointType=SPACE").append("\n");
		Assert.assertEquals(builder.toString(), explorer.reportRecordOfJourney());
	}

}
