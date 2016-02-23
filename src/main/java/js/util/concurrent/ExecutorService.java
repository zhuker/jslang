package js.util.concurrent;
import js.lang.Runnable;
import js.util.concurrent.Callable;
import js.util.concurrent.Future;

public interface ExecutorService extends Executor {

	Future<?> submit(Runnable runnable);

	<T> Future<T> submit(Callable<T> task);

	void shutdown();

}
