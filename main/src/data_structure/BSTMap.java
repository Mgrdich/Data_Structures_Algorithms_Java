package data_structure;

import adt.Entry;
import adt.List;
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

    private boolean isInternal(Position<Entry<K, V>> position) {
        return tree.isInternal(position);
    }

    private Position<Entry<K, V>> right(Position<Entry<K, V>> position) {
        return tree.right(position);
    }

    private Position<Entry<K, V>> left(Position<Entry<K, V>> position) {
        return tree.left(position);
    }

    private Position<Entry<K, V>> parent(Position<Entry<K, V>> position) {
        return tree.parent(position);
    }

    private Position<Entry<K, V>> root() {
        return tree.root();
    }

    private Entry<K, V> safeEntry(Position<Entry<K, V>> position) {
        if (isExternal(position)) return null;

        return position.getElement();
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

        if (compare == 0) return position;

        if (compare < 0) return treeSearch(tree.left(position), key);

        return treeSearch(tree.right(position), key);
    }

    private Position<Entry<K, V>> min(Position<Entry<K, V>> position) {
        while (isInternal(position)) {
            position = left(position);
        }

        return parent(position); // because we need a number here not a sentinel
    }

    private Position<Entry<K, V>> max(Position<Entry<K, V>> position) {
        while (isInternal(position)) {
            position = right(position);
        }

        return parent(position); // because we need a number here not a sentinel
    }

    private Position<Entry<K, V>> inOrderBefore(Position<Entry<K, V>> position) {
        Position<Entry<K, V>> currentPosition = left(position);

        return max(currentPosition);
    }

    private Position<Entry<K, V>> inOrderAfter(Position<Entry<K, V>> position) {
        Position<Entry<K, V>> currentPosition = right(position);

        return min(currentPosition);
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

        V oldValue = value(position);
        tree.set(position, newEntry);

        return oldValue;
    }

    @Override
    public V remove(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isExternal(position)) { // not found
            return null;
        }

        V oldValue = value(position);

        Position<Entry<K, V>> leftPosition = left(position);
        Position<Entry<K, V>> rightPosition = right(position);


        if (isInternal(leftPosition) && isInternal(rightPosition)) {
            Position<Entry<K, V>> replacement = inOrderBefore(position);
            tree.set(position, replacement.getElement());
            position = replacement; // since replacement is has always external node by inOrderBefore definition
        }

        // it has at-least one leaf
        Position<Entry<K, V>> leaf = isExternal(leftPosition) ? leftPosition : rightPosition;

        //order here is important
        tree.remove(leaf);
        tree.remove(position);

        return oldValue;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> buffer = new ArrayList<>();
        for (Position<Entry<K, V>> position : tree.inOrder()) {
            if (isInternal(position))
                buffer.add(position.getElement());
        }
        return buffer;
    }


    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty()) return null;
        return safeEntry(min(root()));
    }

    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) return null;

        return safeEntry(max(root()));
    }

    private Position<Entry<K, V>> lowerFlowerFinder(Position<Entry<K, V>> position) {
        while (!tree.isRoot(position)) {
            if (position == right(parent(position)))
                return parent(position);
            else
                position = parent(position);
        }

        return null;
    }

    private Position<Entry<K, V>> higherCeilingFinder(Position<Entry<K, V>> position) {
        while (!tree.isRoot(position)) {
            if (position == left(parent(position)))
                return parent(position);
            else
                position = parent(position);
        }

        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position)) return safeEntry(position); // found


        return safeEntry(higherCeilingFinder(position)); // find it again
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position)) return safeEntry(position); // found

        return safeEntry(lowerFlowerFinder(position)); // find it again
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position) && isInternal(left(position)))
            return safeEntry(inOrderBefore(position));

        return safeEntry(lowerFlowerFinder(position));
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position) && isInternal(right(position)))
            return safeEntry(inOrderAfter(position));

        return safeEntry(higherCeilingFinder(position));
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K key, K anotherKey) {
        checkKey(key);
        checkKey(anotherKey);
        List<Entry<K, V>> list = new ArrayList<>();
        if (compare(key, anotherKey) < 0) {
            subMapRecursive(key, anotherKey, root(), list);
        }
        return list;
    }

    private void subMapRecursive(K fromKey, K toKey, Position<Entry<K, V>> p, List<Entry<K, V>> list) {
        if (isExternal(p)) return;

        if (compare(p.getElement(), fromKey) < 0) {
            subMapRecursive(fromKey, toKey, right(p), list);
            return;
        }

        subMapRecursive(fromKey, toKey, left(p), list);
        if (compare(p.getElement(), toKey) < 0) { // in range
            list.add(p.getElement());
            subMapRecursive(fromKey, toKey, right(p), list); // right subtree as well
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("( ");
        for (Position<Entry<K, V>> p : tree.inOrder()) {
            if (isInternal(p)) {
                str.append(p.getElement()).append(" ");
            }
        }
        str.append(")");
        return str.toString();
    }
}
