package js.util.concurrent;

import js.util.Queue;

public interface BlockingQueue<E> extends Queue<E> {

	E poll();

	E take();

	void put(E ret);

}
