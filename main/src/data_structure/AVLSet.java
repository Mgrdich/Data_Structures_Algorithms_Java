package data_structure;

import adt.Map;
import adt.Set;
import util.DefaultComparator;

import java.util.Comparator;
import java.util.Iterator;

public class AVLSet<K extends Comparable<K>>  implements Set<K> {
    Map<K, Boolean> map = new AVLMap<>();

    private final Comparator<K> comparator;


    public AVLSet() {
        this.comparator = new DefaultComparator<>();
    }

    public AVLSet(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("all")
    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return (comparator.compare(key, key) == 0);
        } catch (ClassCastException err) {
            throw new IllegalArgumentException("Incompatible Key");
        }
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(K key) {
        checkKey(key);
        return map.get(key);
    }

    @Override
    public void add(K key) {
        checkKey(key);
        map.put(key, true);
    }

    @Override
    public K remove(K key) {
        map.remove(key);
        return key;
    }

    @Override
    public Iterator<K> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (K element : this) {
            stringBuilder.append(element).append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
