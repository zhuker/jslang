package js.util.concurrent;
import js.lang.Runnable;
public interface ThreadFactory {

	Thread newThread(Runnable r);

}
