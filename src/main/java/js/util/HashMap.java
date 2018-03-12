package js.util;

import static org.stjs.javascript.JSCollections.$map;
import static org.stjs.javascript.JSGlobal.JSON;

import java.util.Iterator;

import org.stjs.javascript.Array;
import org.stjs.javascript.JSGlobal;

import js.lang.VGObjectAdapter;

public class HashMap<K, V> implements Map<K, V> {
    final static class EntrySet<K, V> implements Set<js.util.Map.Entry<K, V>> {
        private final HashMap<K, V> self;
        private final Array<String> keys;

        EntrySet(HashMap<K, V> self, Array<String> keys) {
            this.self = self;
            this.keys = keys;
        }

        @Override
        public int size() {
            return keys.$length();
        }

        @Override
        public boolean isEmpty() {
            return keys.$length() == 0;
        }

        @Override
        public boolean contains(Object o) {
            return keys.indexOf(String.valueOf(o)) >= 0;
        }

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator<>(self, keys);
        }

        @Override
        public <T> T[] toArray(T[] a) {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.toArray");
        }

        @Override
        public Object add(Object... arguments) {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.add");
        }

        @Override
        public Object remove(Object... arguments) {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.remove");
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.containsAll");
        }

        @Override
        public boolean addAll(Collection<Entry<K, V>> other) {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.addAll");
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.removeAll");
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.retainAll");
        }

        @Override
        public void clear() {
            throw new RuntimeException("TODO Map.EntrySet<Entry<K,V>>.clear");
        }
    }

    private final static class MapEntry<K, V> implements Entry<K, V> {
        private final K key;
        private final V value;

        MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    final static class EntryIterator<K, V> implements Iterator<Entry<K, V>> {
        private final int len;
        private final Array<String> keys;
        private final HashMap<K, V> self;
        int i = 0;

        EntryIterator(HashMap<K, V> self, Array<String> keys) {
            this.self = self;
            this.len = keys.$length();
            this.keys = keys;
        }

        @Override
        public boolean hasNext() {
            return i < len;
        }

        @Override
        public Entry<K, V> next() {
            String strkey = keys.$get(i++);
            K k = self._keys.$get(strkey);
            return new MapEntry<>(k, self._map.$get(strkey));
        }
    }

    org.stjs.javascript.Map<String, V> _map;
    org.stjs.javascript.Map<String, K> _keys;

    public HashMap() {
        this._map = $map();
        this._keys = $map();
    }

    @Override
    public Set<js.util.Map.Entry<K, V>> entrySet() {
        return new EntrySet<>(this, VGObjectAdapter.keys(Object.class, _map));
    }

    @Override
    public V get(K k) {
        return _map.$get(makeKey(k));
    }

    @Override
    public int size() {
        return VGObjectAdapter.keys(Object.class, _map).$length();
    }

    @Override
    public V put(K k, V v) {
        String key = makeKey(k);
        _keys.$put(key, k);
        V old = _map.$get(key);
        _map.$put(key, v);
        return old;
    }

    protected String makeKey(K k) {
        if (k != null) {
            if ("string" == JSGlobal.typeof(k) || "number" == JSGlobal.typeof(k)) {
                return "" + k;
            }
            return JSON.stringify(k);
        }
        return "null";
    }

    @Override
    public boolean containsKey(K k) {
        V v = _map.$get(makeKey(k));
        return v != null;
    }

    @Override
    public Collection<V> values() {
        List<V> vals = new ArrayList<>();
        for (String key : _map) {
            vals.add(_map.$get(key));
        }
        return vals;
    }

    @Override
    public void putAll(Map<K, V> map) {
        for (Entry<K, V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        this._map = $map();
        this._keys = $map();
    }

    @Override
    public V remove(K k) {
        String key = makeKey(k);
        V old = _map.$get(key);
        _map.$delete(key);
        _keys.$delete(key);
        return old;
    }

    @Override
    public Set<K> keySet() {
        HashMap<K, V> self = this;
        return new Set<K>() {

            @Override
            public int size() {
                return self.size();
            }

            @Override
            public boolean isEmpty() {
                return self.isEmpty();
            }

            @Override
            public boolean contains(Object o) {
                return self.containsKey((K) o);
            }

            @Override
            public Iterator<K> iterator() {
                Iterator<Entry<K, V>> iterator = self.entrySet().iterator();
                return new Iterator<K>() {
                    @Override
                    public boolean hasNext() {
                        return iterator.hasNext();
                    }

                    @Override
                    public K next() {
                        return iterator.next().getKey();
                    }
                };
            }

            @Override
            public <T> T[] toArray(T[] a) {
                throw new RuntimeException("TODO keySet<K>.toArray");
            }

            @Override
            public Object add(Object... arguments) {
                throw new RuntimeException("TODO keySet<K>.add");
            }

            @Override
            public Object remove(Object... arguments) {
                throw new RuntimeException("TODO keySet<K>.remove");
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                throw new RuntimeException("TODO keySet<K>.containsAll");
            }

            @Override
            public boolean addAll(Collection<K> other) {
                throw new RuntimeException("TODO keySet<K>.addAll");
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                throw new RuntimeException("TODO keySet<K>.removeAll");
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                throw new RuntimeException("TODO keySet<K>.retainAll");
            }

            @Override
            public void clear() {
                self.clear();
            }
        };
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
