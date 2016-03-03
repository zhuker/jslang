package js.util;

import java.util.Iterator;

import org.stjs.javascript.Array;

class JSArrayIterator<T> implements Iterator<T> {
    private Array<T> arr;
    int cursor;

    public JSArrayIterator(Array<T> arr) {
        this.arr = arr;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return cursor < arr.$length();
    }

    @Override
    public T next() {
        return arr.$get(cursor++);
    }
}