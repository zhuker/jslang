package js.lang;

public class Runtime {

    private Runtime() {
    }

    private final static Runtime instance = new Runtime();

    public static Runtime getRuntime() {
        return instance;
    }

    public Process exec(String string) {
        throw new RuntimeException("TODO Runtime.exec");
    }

    public int availableProcessors() {
        return 1;
    }

}
