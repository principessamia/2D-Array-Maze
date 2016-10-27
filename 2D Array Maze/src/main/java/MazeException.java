public class MazeException extends Exception {

	static final long serialVersionUID = 1L;

	public MazeException() {
	}

	public MazeException(String message) {
		super(message);
	}

	public MazeException(Throwable cause) {
		super(cause);
	}
}
