package js.util.concurrent;
import js.lang.Runnable;
import js.lang.Thread;
public interface ThreadFactory {

	Thread newThread(Runnable r);

}
