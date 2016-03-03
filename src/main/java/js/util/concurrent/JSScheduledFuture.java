package js.util.concurrent;

import org.stjs.javascript.TimeoutHandler;

public class JSScheduledFuture<T> implements ScheduledFuture<T> {

    private TimeoutHandler handler;

    public JSScheduledFuture(TimeoutHandler handler) {
        this.handler = handler;
    }

    @Override
    public T get(Object... arguments) {
        throw new RuntimeException("TODO Future<T>.get");
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new RuntimeException("TODO Future<T>.cancel");
    }

    @Override
    public boolean isCancelled() {
        throw new RuntimeException("TODO Future<T>.isCancelled");
    }

    @Override
    public boolean isDone() {
        throw new RuntimeException("TODO Future<T>.isDone");
    }

}
