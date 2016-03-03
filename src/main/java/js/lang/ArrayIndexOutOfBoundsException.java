package js.lang;

public class ArrayIndexOutOfBoundsException extends js.lang.IndexOutOfBoundsException {

    public ArrayIndexOutOfBoundsException(Object... arguments) {
        super(""+arguments);
    }

}
