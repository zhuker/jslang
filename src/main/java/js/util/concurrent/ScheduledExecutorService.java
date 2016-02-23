package js.util.concurrent;
import js.lang.Runnable;
import js.util.concurrent.ScheduledFuture;

public interface ScheduledExecutorService extends js.util.concurrent.ExecutorService {

	ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, int i, int j, TimeUnit milliseconds);

}
