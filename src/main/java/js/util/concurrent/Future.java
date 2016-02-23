package js.util.concurrent;

public interface Future<V> {

	V get();
    boolean cancel(boolean mayInterruptIfRunning);

}
