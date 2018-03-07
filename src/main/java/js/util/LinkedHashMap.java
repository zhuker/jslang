package js.util;

import org.stjs.javascript.Array;
import org.stjs.javascript.JSCollections;

public class LinkedHashMap<K, V> implements Map<K, V> {
    private org.stjs.javascript.Map<String, V> map;
    private Array<String> keys;

    public LinkedHashMap() {
        map = JSCollections.$map();
        keys = JSCollections.$array();
    }

    @Override
    public Set<js.util.Map.Entry<K, V>> entrySet() {
        throw new RuntimeException("TODO");
    }

    @Override
    public V get(K k) {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    @Override
    public V put(K k, V v) {
        String key = "" + k;
        V prev = map.$get(key);
        if (prev == null) {
            keys.push(key);
        }
        map.$put(key, v);
        return prev;
    }

    @Override
    public boolean containsKey(K k) {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    @Override
    public void putAll(Map<K, V> map) {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");

    }

    @Override
    public V remove(K k) {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");

    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        throw new RuntimeException("TODO");
    }

    @Override
    public boolean isEmpty() {
        throw new RuntimeException("TODO Map<K,V>.isEmpty");
    }

}
