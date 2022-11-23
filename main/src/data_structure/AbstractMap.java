package data_structure;

import adt.Entry;
import adt.Map;

import java.util.Iterator;

public abstract class AbstractMap<K, V> implements Map<K, V> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    protected static class MapEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public MapEntry(K key, V value) {
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

        public void setKey(K key) {
            this.key = key;
        }

        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }
    }

    private class KeyIterator implements Iterator<K> {

        private final Iterator<Entry<K, V>> iterator = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            return iterator.next().getKey();
        }
    }

    private class ElementIterator implements Iterator<V> {

        private final Iterator<Entry<K, V>> iterator = entrySet().iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public V next() {
            return iterator.next().getValue();
        }
    }

    private class KeyIterable implements Iterable<K> {

        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    private class ElementIterable implements Iterable<V> {

        @Override
        public Iterator<V> iterator() {
            return new ElementIterator();
        }
    }

    @Override
    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    @Override
    public Iterable<V> values() {
        return new ElementIterable();
    }
}
