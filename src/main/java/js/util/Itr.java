package js.util;

import java.util.Iterator;

/**
 * An optimized version of AbstractList.Itr
 */
class Itr<E> implements Iterator<E> {
    int cursor; // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    protected List<E> list;

    public Itr(List<E> list) {
        this.list = list;
    }

    public boolean hasNext() {
        return cursor != list.size();
    }

    @SuppressWarnings("unchecked")
    public E next() {
        int i = cursor;
        if (i >= list.size())
            throw new js.lang.NoSuchElementException();
        cursor = i + 1;
        return (E) list.get(lastRet = i);
    }

    public void remove() {
        if (lastRet < 0)
            throw new js.lang.IllegalStateException();

        list.remove(lastRet);
        cursor = lastRet;
        lastRet = -1;
    }

}