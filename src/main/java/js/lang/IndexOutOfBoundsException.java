package js.lang;

public class IndexOutOfBoundsException extends RuntimeException {

    public IndexOutOfBoundsException(Object... arguments) {
        super("" + arguments[0]);
    }

}
