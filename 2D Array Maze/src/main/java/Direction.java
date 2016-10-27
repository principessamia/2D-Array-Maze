import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Direction {

	NORTH(0), EAST(1), SOUTH(2), WEST(3);

	private static final Map<Integer, Direction> lookup = new HashMap<Integer, Direction>();
	private int index;

	static {
		for (Direction direction : EnumSet.allOf(Direction.class))
			lookup.put(direction.getIndex(), direction);
	}

	public static Direction getByIndex(int index) {
		return lookup.get(index);
	}

	private Direction(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

}
