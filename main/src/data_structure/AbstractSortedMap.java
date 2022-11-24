package data_structure;

import adt.Entry;
import adt.SortedMap;
import util.DefaultComparator;

import java.util.Comparator;

public abstract class AbstractSortedMap<K, V> extends AbstractMap<K, V> implements SortedMap<K, V> {
    private final Comparator<K> comparator;

    protected AbstractSortedMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    protected AbstractSortedMap() {
        this(new DefaultComparator<>());
    }

    protected int compare(Entry<K, V> entry, Entry<K, V> anotherEntry) {
        return comparator.compare(entry.getKey(), anotherEntry.getKey());
    }

    protected int compare(Entry<K, V> entry, K key) {
        return comparator.compare(entry.getKey(), key);
    }

    protected int compare(K key, Entry<K, V> entry) {
        return comparator.compare(key, entry.getKey());
    }

    protected int compare(K key, K anotherKey) {
        return comparator.compare(key, anotherKey);
    }

    @SuppressWarnings("all")
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comparator.compare(key, key) == 0);
        } catch (ClassCastException err) {
            throw new IllegalArgumentException("Incompatible Key");
        }
    }
}
