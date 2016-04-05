package js.util.concurrent;

import js.util.HashMap;

public class ConcurrentHashMap<K, V> extends HashMap<K, V> implements ConcurrentMap<K, V> {

    @Override
    public V putIfAbsent(K key, V value) {
        if (!containsKey(key)) {
            return put(key, value);
        }
        return null;

    }

}
