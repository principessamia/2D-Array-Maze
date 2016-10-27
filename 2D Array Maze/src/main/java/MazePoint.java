public class MazePoint {

	public enum PointType {
		WALL, SPACE, START, FINISH
	}

	private int row;
	private int column;
	private PointType pointType;

	public MazePoint(int row, int column, PointType pointType) {
		this.row = row;
		this.column = column;
		this.pointType = pointType;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public PointType getPointType() {
		return pointType;
	}

	public boolean isWall() {
		return pointType == PointType.WALL;
	}

	@Override
	public String toString() {
		return "row=" + row + ", column=" + column + ", pointType=" + pointType
				+ "";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MazePoint other = (MazePoint) obj;
		if (column != other.column)
			return false;
		if (pointType != other.pointType)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result
				+ ((pointType == null) ? 0 : pointType.hashCode());
		result = prime * result + row;
		return result;
	}
}
