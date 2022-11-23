package data_structure;

import adt.Entry;

import java.util.Iterator;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
    private final ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    private int findIndex(K key) {
        int n = table.size();
        for (int i = 0; i < n; i++) {
            if (table.get(i).getKey().equals(key)) return i;
        }

        return -1;
    }


    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);
        if (index == -1) return null;

        return table.get(index).getValue();
    }

    @Override
    public V put(K key, V value) {
        int index = findIndex(key);
        if (index == -1) {
            table.add(new MapEntry<>(key, value));
            return null;
        }

        return table.get(index).setValue(value);
    }

    @Override
    public V remove(K key) {
        int index = findIndex(key);
        if (index == -1) {
            return null;
        }

        int n = table.size();

        V temp = table.get(n - 1).getValue();

        // semi-swap for algorithmic efficiency
        if (index != n - 1)
            table.set(index, table.get(n - 1));

        table.remove();

        return temp;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntriesIterable();
    }

    private class EntriesIterator implements Iterator<Entry<K, V>> {

        private final Iterator<MapEntry<K, V>> it = table.iterator();

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Entry<K, V> next() {
            return it.next();
        }
    }

    private class EntriesIterable implements Iterable<Entry<K,V>>{
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntriesIterator();
        }
    }
}
