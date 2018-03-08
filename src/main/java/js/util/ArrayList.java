package js.util;

import static org.stjs.javascript.JSFunctionAdapter.call;
import static org.stjs.javascript.JSGlobal.typeof;

import java.util.Iterator;
import java.util.function.Consumer;

import org.stjs.javascript.Array;
import org.stjs.javascript.Global;
import org.stjs.javascript.JSFunctionAdapter;
import org.stjs.javascript.functions.Callback1;

public class ArrayList<T> implements List<T> {

    private Array<T> _array;

    public ArrayList(Object... arguments) {
        if (arguments.length == 0) {
            _array = new Array<>();
        } else if (arguments.length == 1 && "number" == typeof(arguments[0])) {
            _array = new Array<>((int) arguments[0]);
        } else {
            Collection other = (Collection) arguments[0];
            _array = (Array<T>) (Object) other.toArray(new Object[other.size()]);
        }
    }

    static <T> ArrayList<T> fromArray(Array<T> arr) {
        ArrayList<T> list = new ArrayList<>();
        list._array = arr;
        return list;
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
            throw new js.lang.IndexOutOfBoundsException("rangeCheck " + index);
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
    public boolean isEmpty() {
        return _array.$length() == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new RuntimeException("TODO Collection<T>.contains");
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr<T>(this);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int _size = _array.$length();
        if (a.length < _size) {
            // Make a new array of a's runtime type, but my contents:
            Object copy = (Array<T>) _array.slice(0);
            return (T[]) copy;
        }
        js.lang.System.arraycopy(_array, 0, a, 0, _size);
        if (a.length > _size)
            a[_size] = null;
        return a;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void addAt(int index, T element) {
        _array.splice(index, 0, element);
    }

    @Override
    public Object add(Object... arguments) {
        if (arguments.length == 1) {
            _array.push((T) arguments[0]);
            return true;
        }
        int index = (int) arguments[0] | 0;
        T val = (T) arguments[1];
        this.addAt(index, val);
        return true;
    }

    @Override
    public T get(int index) {
        rangeCheck(index);
        return _array.$get(index);
    }

    @Override
    public int size() {
        return _array.$length();
    }

    @Override
    public void clear() {
        this._array.splice(0, _array.$length());
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr<>(this, 0);
    }

    @Override
    public int indexOf(T t) {
        throw new RuntimeException("TODO List<T>.indexOf");
    }

    @Override
    public T set(int index, T element) {
        rangeCheck(index);

        T oldValue = _array.$get(index);
        _array.$set(index, element);
        return oldValue;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        _array.$forEach((Callback1<T>) action);
        //        int len = size();
        //        for (int i = 0; i < len; i++) {
        //            call(action, action, _array.$get(i));
        //        }
    }

    @Override
    public boolean addAll(Collection<T> other) {
        for (T t : other) {
            _array.push(t);
        }
        return other.size() != 0;
    }

    @Override
    public boolean addAllAt(int idx, Collection<T> other) {
        throw new RuntimeException("TODO Collection<T>.addAllAt");
    }

}
