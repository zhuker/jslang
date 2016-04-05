package js.util;

import static org.stjs.javascript.Global.console;
import static org.stjs.javascript.JSCollections.$map;
import static org.stjs.javascript.JSObjectAdapter.$get;

import org.stjs.javascript.Global;
import org.stjs.javascript.JSFunctionAdapter;
import org.stjs.javascript.JSGlobal;
import org.stjs.javascript.JSObjectAdapter;
import org.stjs.javascript.functions.Callback0;
import org.stjs.javascript.functions.Function0;
import org.stjs.javascript.functions.Function1;

import js.lang.IllegalArgumentException;

public class HashMap<K, V> implements Map<K, V> {

    private org.stjs.javascript.Map<String, V> _map;

    public HashMap() {
        this._map = $map();
    }

    @Override
    public Set<js.util.Map.Entry<K, V>> entrySet() {
        throw new RuntimeException("TODO Map<K,V>.entrySet");
    }

    @Override
    public V get(K k) {
        return _map.$get(makeKey(k));
    }

    @Override
    public int size() {
        throw new RuntimeException("TODO Map<K,V>.size");
    }

    @Override
    public V put(K k, V v) {
        String key = makeKey(k);
        V old = _map.$get(key);
        _map.$put(key, v);
        return old;
    }

    private String makeKey(K k) {
        if (k != null) {
            if ("string" == JSGlobal.typeof(k) || "number" == JSGlobal.typeof(k)) {
                return "" + k;
            }
            Function0<String> _toString = (Function0<String>) $get(k, "toString");
            if (_toString != null) {
                try {
                    String str = JSFunctionAdapter.call(_toString, k);
                    return str;
                } catch (Exception e) {
//                    console.error("error", e);
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


    @Override
    public boolean containsKey(K k) {
        V v = _map.$get(makeKey(k));
        return v != null;
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("TODO Map<K,V>.values");
    }

    @Override
    public void putAll(Map<K, V> map) {
        throw new RuntimeException("TODO Map<K,V>.putAll");
    }

    @Override
    public void clear() {
        this._map = $map();
    }

    @Override
    public void remove(K k) {
        throw new RuntimeException("TODO Map<K,V>.remove");
    }

    @Override
    public Set<K> keySet() {
        throw new RuntimeException("TODO Map<K,V>.keySet");
    }
}
