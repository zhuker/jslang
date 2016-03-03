package js.util.concurrent;

import static org.stjs.javascript.Global.console;

import org.stjs.javascript.Global;
import org.stjs.javascript.TimeoutHandler;

import js.lang.Runnable;

public class JSScheduledExecutorService implements ScheduledExecutorService {

    @Override
    public <T> Future<T> submit(Object object) {
        throw new RuntimeException("TODO ExecutorService.submit");
    }

    @Override
    public void shutdown() {
        throw new RuntimeException("TODO ExecutorService.shutdown");
    }

    @Override
    public void execute(Runnable command) {
        throw new RuntimeException("TODO Executor.execute");
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long initialDelay, long period, TimeUnit unit) {
        if (initialDelay == 0) {
            TimeoutHandler th = Global.setInterval(() -> {
                runnable.run();
            }, (int) unit.toMillis(period));
            return new JSScheduledFuture(th);
        }
        throw new RuntimeException("TODO scheduleAtFixedRate initialDelay > 0");
    }

}
