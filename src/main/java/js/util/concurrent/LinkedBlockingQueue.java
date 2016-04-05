package js.util.concurrent;

import js.util.LinkedList;

public class LinkedBlockingQueue<E> extends LinkedList<E> implements BlockingQueue<E> {

    @Override
    public E take() {
        throw new RuntimeException("TODO BlockingQueue<E>.take");
    }

    @Override
    public void put(E ret) {
        throw new RuntimeException("TODO BlockingQueue<E>.put");
    }

}
