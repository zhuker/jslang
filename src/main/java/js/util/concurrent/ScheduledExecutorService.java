package js.util.concurrent;
import js.util.concurrent.TimeUnit;

import js.lang.Runnable;
import js.util.concurrent.ScheduledFuture;

public interface ScheduledExecutorService extends js.util.concurrent.ExecutorService {

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long initialDelay,long period,TimeUnit unit);

}
