package data_structure;

import adt.Map;
import adt.Set;

import java.util.Iterator;

public class UnsortedSet<K> implements Set<K> {
    Map<K, Boolean> map = new UnsortedTableMap<>();

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
        return map.get(key);
    }

    @Override
    public void add(K key) {
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
