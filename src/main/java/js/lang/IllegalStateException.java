package js.lang;

public class IllegalStateException extends RuntimeException {
	public IllegalStateException(Object... arguments) {
		super(""+arguments);
	}
}
