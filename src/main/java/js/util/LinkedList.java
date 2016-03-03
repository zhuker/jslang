package js.util;

import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSGlobal.typeof;

import java.util.Iterator;

import js.util.ListIterator;
import java.util.function.Consumer;

import org.stjs.javascript.Array;
import org.stjs.javascript.Global;

public class LinkedList<T> implements List<T> {

    private final Array<T> _array;

    public LinkedList(Object... arguments) {
        if (arguments.length == 0) {
            this._array = new Array<T>();
        } else {
            throw new RuntimeException("TODO new LinkedList " + arguments.length);
        }
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return _array.$get(index);
    }

    @Override
    public <X> X[] toArray(X[] a) {
        int _size = _array.$length();
        if (a.length < _size) {
            // Make a new array of a's runtime type, but my contents:
            Object copy = (Array<X>) _array.slice(0);
            return (X[]) copy;
        }
        js.lang.System.arraycopy(_array, 0, a, 0, _size);
        if (a.length > _size)
            a[_size] = null;
        return a;
    }

    @Override
    public void clear() {
        this._array.splice(0, _array.$length());
    }

    @Override
    public T remove(Object... arguments) {
        if (arguments.length == 1 && "number" == typeof(arguments[0])) {
            int idx = (int) arguments[0];
            rangeCheck(idx);
            T oldValue = _array.$get(idx);
            _array.splice(idx, 1);
            return oldValue;
        } else {
            throw new RuntimeException("TODO Collection<T>.remove");
        }
    }

    private void rangeCheck(int index) {
        if (index >= _array.$length())
            throw new js.lang.IndexOutOfBoundsException("" + index);
    }

    @Override
    public boolean isEmpty() {
        return _array.$length() == 0;
    }

    @Override
    public Object add(Object... arguments) {
        if (arguments.length == 1) {
            _array.push((T) arguments[0]);
        } else if (arguments.length == 2) {
            int idx = (int) arguments[0];
            T item = (T) arguments[1];
            _array.splice(idx, 0, item);
        } else {
            // TODO Auto-generated method stub
            console.error("arguments", arguments);
            throw new RuntimeException("TODO LinkedList.add");
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    public T getLast() {
        throw new RuntimeException("TODO");
    }

    public T peekFirst() {
        throw new RuntimeException("TODO");
    }

    public T removeFirst() {
        throw new RuntimeException("TODO");
    }

    @Override
    public int indexOf(T t) {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    public void push(T e) {
        throw new RuntimeException("TODO");
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr<T>(this);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");

    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr<>(this, 0);
    }

    @Override
    public int size() {
        return _array.$length();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.containsAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<T>.retainAll");
    }

    @Override
    public boolean addAll(Object... arguments) {
        throw new RuntimeException("TODO Collection<T>.addAll");
    }

    @Override
    public T set(int index, T element) {
        rangeCheck(index);

        T oldValue = _array.$get(index);
        _array.$set(index, element);
        return oldValue;
    }

}
