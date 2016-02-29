package js.util.concurrent;
import js.lang.Runnable;
import js.util.concurrent.Callable;
import js.util.concurrent.Future;

public interface ExecutorService extends Executor {

	<T> Future<T> submit(Object object);

	void shutdown();

}
