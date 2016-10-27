import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MazeTests {

	private Maze maze;

	@Before
	public void setUp() throws MazeException {
		maze = MazeFactory.generateMaze("ValidMaze.txt");
	}

	@Test
	public void getNeighbours() {
		Map<Direction, MazePoint> neighbours = maze.getNeighbours(maze.getMazePoints()[1][0]);
		Assert.assertNotNull(neighbours.get(Direction.EAST));
		Assert.assertNotNull(neighbours.get(Direction.SOUTH));
		Assert.assertNotNull(neighbours.get(Direction.NORTH));
		Assert.assertNull(neighbours.get(Direction.WEST));
	}

	@Test
	public void getPointTypeValidCoordinates() {
		PointType pointType = maze.getPointType(3, 3);
		Assert.assertEquals(MazePoint.PointType.START, pointType);
		pointType = maze.getPointType(10, 7);
		Assert.assertEquals(MazePoint.PointType.WALL, pointType);
	}

	@Test
	public void getPointTypeInvalidCoordinates() {
		PointType pointType = maze.getPointType(-15, -15);
		Assert.assertNull(pointType);
		pointType = maze.getPointType(100, 100);
		Assert.assertNull(pointType);
	}

	@Test
	public void mazeDimensions() {
		Assert.assertEquals(15, maze.getHeight());
		Assert.assertEquals(15, maze.getWidth());
		Assert.assertEquals(225, maze.getSize());
	}
}
