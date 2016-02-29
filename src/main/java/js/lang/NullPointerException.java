package js.lang;

public class NullPointerException extends RuntimeException {

    public NullPointerException(Object... arguments) {
        super("" + arguments);
    }

}
