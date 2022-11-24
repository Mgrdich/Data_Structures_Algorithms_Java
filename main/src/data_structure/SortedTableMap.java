package data_structure;

import adt.Entry;

import java.util.Iterator;

public class SortedTableMap<K, V> extends AbstractSortedMap<K, V> {
    ArrayList<MapEntry<K, V>> table = new ArrayList<>();

    private int findIndex(K key, int low, int high) {
        if (low > high) return high + 1;
        int mid = (low + high) / 2;
        int comp = compare(key, table.get(mid));

        if (comp == 0) return mid;

        if (comp < 0) return findIndex(key, low, mid - 1);

        return findIndex(key, mid + 1, high);
    }

    private int findIndex(K key) {
        int j = findIndex(key, 0, table.size() - 1);
        if (j == size() || compare(key, table.get(j)) != 0) return -1;
        return j;
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public V get(K key) {
        int j = findIndex(key);
        if (j == -1) return null;

        return table.get(j).getValue();
    }

    @Override
    public V put(K key, V value) {
        int j = findIndex(key);

        if (j == -1) {
            table.add(j, new MapEntry<>(key, value));
            return null;
        }

        return table.get(j).setValue(value);
    }

    @Override
    public V remove(K key) {
        int j = findIndex(key);
        if (j == -1) return null;
        return table.remove(j).getValue();
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

    private class EntriesIterable implements Iterable<Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntriesIterator();
        }
    }

    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> lastEntry() {
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return null;
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K key, K anotherKey) {
        return null;
    }
}
