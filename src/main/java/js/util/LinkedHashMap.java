package js.util;

import static org.stjs.javascript.JSCollections.$array;
import static org.stjs.javascript.JSCollections.$map;

import org.stjs.javascript.Array;
import org.stjs.javascript.JSCollections;

public class LinkedHashMap<K, V> extends HashMap<K, V> {
    Array<String> orderedKeys;

    public LinkedHashMap() {
        super();
        this.orderedKeys = JSCollections.$array();
    }

    @Override
    public Set<js.util.Map.Entry<K, V>> entrySet() {
        return new EntrySet<>(this, orderedKeys);
    }

    @Override
    public V put(K k, V v) {
        String key = makeKey(k);
        _keys.$put(key, k);

        V old = _map.$get(key);
        if (old == null) {
            orderedKeys.push(key);
        }
        _map.$put(key, v);
        return old;
    }

    @Override
    public void clear() {
        super.clear();
        this._map = $map();
        this._keys = $map();
        this.orderedKeys = $array();
    }

    @Override
    public int size() {
        return orderedKeys.$length();
    }

    @Override
    public V remove(K k) {
        String makeKey = makeKey(k);
        V old = super.remove(k);
        int indexOf = orderedKeys.indexOf(makeKey);
        if (indexOf >= 0) {
            orderedKeys.splice(indexOf, 1);
        }
        return old;
    }

}
