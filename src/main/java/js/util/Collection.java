package js.util;

import java.util.Iterator;

public interface Collection<E> extends Iterable<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    <T> T[] toArray(T[] a);

    Object add(Object... arguments);

    Object remove(Object... arguments);

    boolean containsAll(Collection<?> c);

    boolean addAll(Object... arguments);

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);

    void clear();

}
