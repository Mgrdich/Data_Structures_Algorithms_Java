package data_structure;

import adt.Entry;
import adt.Position;
import adt.PositionalList;

import java.util.Comparator;

public class UnsortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    private final PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    public UnsortedPriorityQueue() {
        super();
    }

    public UnsortedPriorityQueue(Comparator<K> comparator) {
        super(comparator);
    }

    private Position<Entry<K, V>> findMin() {
        Position<Entry<K, V>> minPointer = list.first();
        for (Position<Entry<K, V>> walk : list.positions()) {
            if (compare(walk.getElement(), minPointer.getElement()) < 0) {
                minPointer = walk;
            }
        }
        return minPointer;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Entry<K, V> min() {
        if (isEmpty()) return null;
        return findMin().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if (isEmpty()) return null;
        return list.remove(findMin());
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        return new PQEntry<>(key, value);
    }
}
