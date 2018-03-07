package js.util.concurrent;
import js.lang.Runnable;

import js.util.concurrent.Future;

public interface RunnableFuture<V> extends Runnable, Future<V> {
    @Override
    void run();
}
