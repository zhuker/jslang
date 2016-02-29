package js.util.concurrent;

public interface Future<V> {

	V get(Object... arguments);
    boolean cancel(boolean mayInterruptIfRunning);
    boolean isCancelled();
    boolean isDone();

}
