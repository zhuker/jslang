package js.util.concurrent;
import js.lang.Runnable;

public interface Executor {
	void execute(Runnable command);
}
