package js.lang;

public class StackTraceElement {
    final static StackTraceElement Fake = new StackTraceElement();

    @FixMe
    public String getFileName() {
        return "UnknownFile";
    }

    @FixMe
    public String getClassName() {
        return "UnknownClass";
    }

    @FixMe
    public String getMethodName() {
        return "unknownMethod";
    }

    @FixMe
    public int getLineNumber() {
        return 42;
    }

}
