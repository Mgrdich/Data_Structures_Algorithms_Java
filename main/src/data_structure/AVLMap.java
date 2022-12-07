package data_structure;

import adt.Entry;
import adt.List;
import adt.Position;

import java.util.Comparator;

public class AVLMap<K, V> extends AbstractSortedMap<K, V> {

    AVLTree<K, V> tree = new AVLTree<>();

    public AVLMap() {
        super();
        tree.addRoot(null);
    }

    public AVLMap(Comparator<K> comparator) {
        super(comparator);
        tree.addRoot(null);
    }

    private void expandExternal(Position<Entry<K, V>> position, Entry<K, V> entry) {
        tree.set(position, entry);
        tree.addRight(position, null);
        tree.addLeft(position, null);
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

    private Position<Entry<K, V>> root() {
        return tree.root();
    }

    private Position<Entry<K, V>> left(Position<Entry<K, V>> position) {
        return tree.left(position);
    }

    private Position<Entry<K, V>> parent(Position<Entry<K, V>> position) {
        return tree.parent(position);
    }

    private Entry<K, V> remove(Position<Entry<K, V>> position) {
        return tree.remove(position);
    }

    private Entry<K, V> set(Position<Entry<K, V>> position, Entry<K, V> entry) {
        return tree.set(position, entry);
    }

    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> position, K key) {
        if (isExternal(position)) {
            return position;
        }

        int compare = compare(key, position.getElement());

        if (compare == 0) return position;

        if (compare < 0) return treeSearch(left(position), key);

        return treeSearch(right(position), key);
    }

    private Position<Entry<K, V>> treeMin(Position<Entry<K, V>> position) {
        while (isInternal(position)) {
            position = left(position);
        }

        return parent(position); // because we need a number here not a sentinel
    }

    private Position<Entry<K, V>> treeMax(Position<Entry<K, V>> position) {
        while (isInternal(position)) {
            position = right(position);
        }

        return parent(position); // because we need a number here not a sentinel
    }

    @Override
    public int size() {
        return (tree.size() - 1) / 2;
    }

    @Override
    public V get(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        if (isExternal(position)) return null;

        return position.getElement().getValue();
    }

    @Override
    public V put(K key, V value) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        Entry<K, V> newEntry = new MapEntry<>(key, value);
        if (isExternal(position)) {
            expandExternal(position, newEntry);
            // rebalance(position);
            return null;
        }

        V old = position.getElement().getValue();
        set(position, newEntry);
        return old;
    }

    @Override
    public V remove(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        if (isExternal(position)) {
            return null;
        }

        V old = position.getElement().getValue();

        if (isInternal(right(position)) && isInternal(left(position))) {
            Position<Entry<K, V>> replacementPosition = treeMax(left(position));
            set(position, replacementPosition.getElement());
            position = replacementPosition;
        }

        Position<Entry<K, V>> leaf = isExternal(left(position)) ? left(position) : right(position);

        Position<Entry<K, V>> sibling = tree.sibling(position);
        remove(leaf);
        remove(position);
        if (!tree.isRoot(sibling)) {
             reBalance(parent(sibling));
        }
        return old;

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

    private Entry<K, V> lowerFlowerFinder(Position<Entry<K, V>> position) {
        while (!tree.isRoot(position)) {
            if (position == right(parent(position)))
                return parent(position).getElement();
            else
                position = parent(position);
        }

        return null;
    }

    private Entry<K, V> higherCeilingFinder(Position<Entry<K, V>> position) {
        while (!tree.isRoot(position)) {
            if (position == left(parent(position)))
                return parent(position).getElement();
            else
                position = parent(position);
        }

        return null;
    }

    @Override
    public Entry<K, V> firstEntry() {
        if (isEmpty()) return null;
        return treeMin(root()).getElement();
    }

    @Override
    public Entry<K, V> lastEntry() {
        if (isEmpty()) return null;
        return treeMax(root()).getElement();
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position)) return position.getElement();
        return higherCeilingFinder(position);
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);

        if (isInternal(position)) return position.getElement();

        return lowerFlowerFinder(position);
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        if (isInternal(right(position)) && isInternal(left(position))) {
            return treeMax(left(position)).getElement();
        }
        return lowerFlowerFinder(position);
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = treeSearch(root(), key);
        if (isInternal(right(position)) && isInternal(left(position))) {
            return treeMin(right(position)).getElement();
        }

        return higherCeilingFinder(position);
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

    private void recomputeHeight(Position<Entry<K, V>> position) {
        int height = 1 + Math.max(tree.getHeight(left(position)), tree.getHeight(right(position)));
        tree.setHeight(position, height);
    }

    private boolean isBalanced(Position<Entry<K, V>> position) {
        int leftHeight = tree.getHeight(left(position));
        int rightHeight = tree.getHeight(right(position));
        return Math.abs(rightHeight - leftHeight) <= 1;
    }


    private Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> position) {
        Position<Entry<K, V>> leftPosition = left(position);
        Position<Entry<K, V>> rightPosition = right(position);

        int leftHeight = tree.getHeight(leftPosition);
        int rightHeight = tree.getHeight(rightPosition);

        if (leftHeight > rightHeight) {
            return leftPosition;
        }

        if (rightHeight > leftHeight) {
            return rightPosition;
        }

        // preference
        if (tree.isRoot(position))
            return leftPosition;

        if (position == left(parent(position))) {
            return leftPosition;
        }

        return rightPosition;
    }


    private void reBalance(Position<Entry<K, V>> position) {
        int oldHeight;
        int newHeight;

        do {
            oldHeight = tree.getHeight(position);
            if(!isBalanced(position)){

                // perform tri-node restructuring, setting p to resulting root
                // perform tri-node restructuring, setting p to resulting root

                position = tree.restructure(tallerChild(tallerChild(position)));
                recomputeHeight(left(position));
                recomputeHeight(right(position));
            }
            recomputeHeight(position);
            newHeight = tree.getHeight(position);
            position = parent(position);

        } while (oldHeight != newHeight && position != null);
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
