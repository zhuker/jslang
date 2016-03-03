package js.util.concurrent;

public class Executors {

	public static ExecutorService newFixedThreadPool(int availableProcessors, ThreadFactory threadFactory) {
		throw new RuntimeException("TODO");
	}

	public static ThreadFactory defaultThreadFactory() {
		throw new RuntimeException("TODO");
	}

	public static ScheduledExecutorService newSingleThreadScheduledExecutor() {
        return new JSScheduledExecutorService();
	}

	public static ScheduledExecutorService newScheduledThreadPool(int i, ThreadFactory daemonThreadFactory) {
	    return new JSScheduledExecutorService();
	}

}
