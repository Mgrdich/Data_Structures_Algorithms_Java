package data_structure;

import adt.Entry;
import adt.Position;

import java.util.Comparator;

public class BSTMap<K, V> extends AbstractSortedMap<K, V> {
    protected LinkedBinaryTree<Entry<K, V>> tree = new LinkedBinaryTree<>();

    public BSTMap() {
        super();
        tree.addRoot(null);
    }

    public BSTMap(Comparator<K> comparator) {
        super(comparator);
        tree.addRoot(null);
    }

    private void expandExternal(Position<Entry<K, V>> position, Entry<K, V> entry) {
        tree.set(position, entry);
        tree.addLeft(position, null);
        tree.addRight(position, null);
    }

    private boolean isExternal(Position<Entry<K, V>> position) {
        return tree.isExternal(position);
    }

    private Position<Entry<K, V>> root() {
        return tree.root();
    }

    private V value(Position<Entry<K, V>> position) {
        if (isExternal(position)) return null;

        return position.getElement().getValue();
    }

    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> position, K key) {
        if (tree.isExternal(position)) {
            return position;
        }

        int compare = compare(key, position.getElement());

        if (compare == 0)
            return position;

        if (compare < 0)
            return treeSearch(tree.left(position), key);

        return treeSearch(tree.right(position), key);
    }


    @Override
    public int size() {
        return (tree.size() - 1) / 2;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        return value(position);
    }

    @Override
    public V put(K key, V value) {
        checkKey(key);
        Entry<K, V> newEntry = new MapEntry<>(key, value);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isExternal(position)) {
            expandExternal(position, newEntry);
            return null;
        }

        V oldValue = position.getElement().getValue();
        tree.set(position, newEntry);

        return oldValue;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return null;
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
