package js.lang;

public class UnsupportedOperationException extends RuntimeException {

	public UnsupportedOperationException(Object... arguments) {
		super(""+arguments);
	}

}
