package js.lang;

public class Thread {

    private Runnable r;

    public Thread(js.lang.Runnable r) {
        this.r = r;
    }

    public void setDaemon(boolean b) {
        throw new RuntimeException("TODO Thread.setDaemon");
    }

    public void setName(String name) {
        throw new RuntimeException("TODO Thread.setName");
    }

    private final static Thread CurrentThread = new Thread(null);

    public static Thread currentThread() {
        return CurrentThread;
    }

    private static final StackTraceElement[] FakeStackTrace = new StackTraceElement[] { StackTraceElement.Fake,
            StackTraceElement.Fake, StackTraceElement.Fake, StackTraceElement.Fake };

    public js.lang.StackTraceElement[] getStackTrace() {
        return FakeStackTrace;
    }

}
