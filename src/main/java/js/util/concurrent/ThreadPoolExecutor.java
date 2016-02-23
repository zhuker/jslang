package js.util.concurrent;

import js.lang.Runnable;

public class ThreadPoolExecutor implements ExecutorService {

    public ThreadPoolExecutor(int nThreads, int nThreads2, long l, TimeUnit milliseconds,
            PriorityBlockingQueue<Runnable> priorityBlockingQueue) {
        // TODO Auto-generated constructor stub
        throw new RuntimeException("TODO");
    }

    @Override
    public void execute(Runnable command) {
        throw new RuntimeException("TODO Executor.execute");
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        throw new RuntimeException("TODO ExecutorService.submit");
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        throw new RuntimeException("TODO ExecutorService.submit");
    }

    @Override
    public void shutdown() {
        throw new RuntimeException("TODO ExecutorService.shutdown");
    }

}
