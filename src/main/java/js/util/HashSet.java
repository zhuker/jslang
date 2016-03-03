package js.util;

import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSCollections.$map;
import static org.stjs.javascript.JSObjectAdapter.$get;

import java.util.Iterator;

import java.util.function.Consumer;

import org.stjs.javascript.Global;
import org.stjs.javascript.JSCollections;
import org.stjs.javascript.JSFunctionAdapter;
import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.JSObjectAdapter;
import org.stjs.javascript.Map;
import org.stjs.javascript.functions.Function0;

import js.lang.IllegalArgumentException;
import js.lang.VGObjectAdapter;
import js.node.NodeJS;

public class HashSet<E> implements Set<E> {
    private Map<String, E> _map;
    private int _size = 0;

    public HashSet(Object... arguments) {
        this._map = $map();
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
        throw new RuntimeException("TODO Collection<E>.size");
    }

    @Override
    public boolean isEmpty() {
        return _size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new RuntimeException("TODO Collection<E>.contains");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new RuntimeException("TODO Collection<E>.toArray");
    }

    @Override
    public Object add(Object... arguments) {
        E item = (E) arguments[0];
        String key = makeKey(item);
        boolean contains = _map.$get(key) != null;
        _map.$put(key, item);
        this._size = VGObjectAdapter.keys(Object.class, _map).$length();
        return !contains;
    }

    @Override
    public Boolean remove(Object... arguments) {
        throw new RuntimeException("TODO Collection<E>.remove");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.containsAll");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.removeAll");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new RuntimeException("TODO Collection<E>.retainAll");
    }

    @Override
    public void clear() {
        this._map = $map();
    }

    @Override
    public Iterator<E> iterator() {
        throw new RuntimeException("TODO Set<E>.iterator");
    }

    @Override
    public boolean addAll(Object... arguments) {
        throw new RuntimeException("TODO Collection<E>.addAll");
    }

    private String makeKey(E k) {
        if (k != null) {
            if ("string" == JSGlobal.typeof(k) || "number" == JSGlobal.typeof(k)) {
                return "" + k;
            }
            Function0<String> _toString = (Function0<String>) $get(k, "toString");
            if (_toString != null) {
                try {
                    String str = _toString.$invoke();
                    return str;
                } catch (Exception e) {
                    console.error("error", e);
                }
            }
            if ("function" == JSGlobal.typeof(k)) {
                Object name = $get(k, "name");
                if (name != null && name != "") {
                    return (String) name;
                }
            }
        }
        console.error("key", k);
        throw new IllegalArgumentException("supported key types: string, number, Function.name, anything.toString()");
    }
}
