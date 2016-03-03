package js.util;

import js.util.ListIterator;

public interface List<T> extends Collection<T> {

    T get(int idx);

    T set(int index, T element);

    int size();

    void clear();

    T remove(Object... arguments);

    ListIterator<T> listIterator();

    int indexOf(T t);

}
