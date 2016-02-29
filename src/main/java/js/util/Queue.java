package js.util;

import js.util.Collection;

public interface Queue<E> extends Collection<E> {
    boolean offer(E e);

    E remove(Object... arguments);

    E poll();

    E element();

    E peek();
}
