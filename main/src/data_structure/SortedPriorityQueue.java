package data_structure;

import adt.Entry;
import adt.Position;
import adt.PositionalList;

import java.util.Comparator;

public class SortedPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    private final PositionalList<Entry<K, V>> list = new LinkedPositionalList<>();

    public SortedPriorityQueue() {
        super();
    }

    public SortedPriorityQueue(Comparator<K> comparator) {
        super(comparator);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public Entry<K, V> min() {
        if (isEmpty()) return null;
        return list.first().getElement();
    }

    @Override
    public Entry<K, V> removeMin() {
        if (isEmpty()) return null;
        return list.remove(list.first());
    }

    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);
        PQEntry<K, V> pqEntry = new PQEntry<>(key, value);
        Position<Entry<K, V>> walk = list.first();

        while (walk != null && compare(walk.getElement(), pqEntry) < 0) {
            walk = list.before(walk);
        }

        if (walk == null) list.addFirst(pqEntry);
        else list.addAfter(walk, pqEntry);

        return pqEntry;
    }
}
