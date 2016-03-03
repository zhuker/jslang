package js.util;

class ListItr<E> extends Itr<E> implements ListIterator<E> {
    ListItr(List<E> list, int index) {
        super(list);
        cursor = index;
    }

    public boolean hasPrevious() {
        return cursor != 0;
    }

    public int nextIndex() {
        return cursor;
    }

    public int previousIndex() {
        return cursor - 1;
    }

    public E previous() {
        int i = cursor - 1;
        if (i < 0)
            throw new js.lang.NoSuchElementException();
        cursor = i;
        return list.get(lastRet = i);
    }

    public void set(E e) {
        if (lastRet < 0)
            throw new js.lang.IllegalStateException();

        list.set(lastRet, e);
    }

    public void add(E e) {
        int i = cursor;
        list.add(i, e);
        cursor = i + 1;
        lastRet = -1;
    }
}