package js.util.concurrent;

public class FutureTask<V> implements RunnableFuture<V> {

    public FutureTask(Callable<V> callable) {
        // TODO Auto-generated constructor stub
        throw new RuntimeException("TODO FutureTask");
    }

    @Override
    public V get(Object... arguments) {
        throw new RuntimeException("TODO Future<V>.get");
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new RuntimeException("TODO Future<V>.cancel");
    }

    @Override
    public boolean isCancelled() {
        throw new RuntimeException("TODO Future<V>.isCancelled");
    }

    @Override
    public boolean isDone() {
        throw new RuntimeException("TODO Future<V>.isDone");
    }

    @Override
    public void run() {
        throw new RuntimeException("TODO RunnableFuture<V>.run");
    }

}
