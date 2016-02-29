package js.lang;

public class AssertionError extends RuntimeException {
    public AssertionError(Object... arguments) {
        super(""+arguments);
    }
}
