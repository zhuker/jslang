package js.util;

import java.util.Iterator;

import js.util.Map.Entry;

public class HashSet<E> implements Set<E> {
    HashMap<E, Boolean> map;

    public HashSet(Object... arguments) {
        map = new HashMap<>();
        if (arguments.length == 0) {
        } else if (arguments.length == 1 && arguments[0] instanceof Iterable) {
            Iterable<E> it = (Iterable<E>) arguments[0];
            for (Iterator<E> iterator = it.iterator(); iterator.hasNext();) {
                E type = (E) iterator.next();
                add(type);
            }
        } else {
            throw new RuntimeException("TODO new HashSet " + arguments.length);
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey((E) o);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new RuntimeException("TODO Collection<E>.toArray");
    }

    @Override
    public Object add(Object... arguments) {
        Boolean old = map.put((E) arguments[0], true);
        return old != true;
    }

    @Override
    public Boolean remove(Object... arguments) {
        Boolean old = map.remove((E) arguments[0]);
        return old != true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.containsAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object k : c) {
            changed |= map.remove((E) k);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.retainAll");
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<Entry<E, Boolean>> iterator = this.map.entrySet().iterator();
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public E next() {
                return iterator.next().getKey();
            }
        };
    }

    @Override
    public boolean addAll(Collection<E> other) {
        throw new RuntimeException("TODO Collection<E>.addAll");
    }
}
