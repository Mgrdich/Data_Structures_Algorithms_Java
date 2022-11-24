package data_structure;

import adt.Entry;

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
        return snapshot(0, null);
    }

    private Iterable<Entry<K, V>> snapshot(int index, K key) {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        int j = index;
        while (j < table.size() && (key == null || compare(key, table.get(j)) > 0)) {
            buffer.add(table.get(j++));
        }
        return buffer;
    }

    private Entry<K, V> safeEntry(int j) {
        if (j < 0 || j >= table.size()) return null;
        return table.get(j);
    }

    @Override
    public Entry<K, V> firstEntry() {
        return safeEntry(0);
    }

    @Override
    public Entry<K, V> lastEntry() {
        return safeEntry(table.size() - 1);
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return safeEntry(findIndex(key));
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        int j = findIndex(key);

        if (j == size() || !key.equals(table.get(j).getKey())) j--;

        return safeEntry(j);
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return safeEntry(findIndex(key) - 1);
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        int j = findIndex(key);

        if (j == size() || !key.equals(table.get(j).getKey())) j++;

        return safeEntry(j);
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K key, K anotherKey) {
        return snapshot(findIndex(key), anotherKey);
    }
}
