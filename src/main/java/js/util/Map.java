package js.util;


public interface Map<K, V> {

	interface Entry<K,V> {
        K getKey();
        V getValue();
	}

	Set<Entry<K, V>> entrySet();
	
	V get(K k);

	int size();

    V put(K key, V value);

	boolean containsKey(K k);

	Collection<V> values();

	void putAll(Map<K, V> map);

	void clear();

	void remove(K k);

	Set<K> keySet();

}
