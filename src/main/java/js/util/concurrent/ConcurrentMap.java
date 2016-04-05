package js.util.concurrent;

import js.util.Map;

public interface ConcurrentMap<K, V> extends Map<K, V> {
    V putIfAbsent(K key, V value);

}
