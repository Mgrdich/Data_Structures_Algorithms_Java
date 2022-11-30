package data_structure;

import adt.Entry;
import adt.Position;
import util.DefaultComparator;

import java.util.Comparator;

public class BSTWithNode<K, V> extends AbstractBinaryTree<Entry<K, V>> {

    protected static class BSTEntry<K, V> implements Entry<K, V> {
        private K key;
        private V value;

        public BSTEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }

        @Override
        public String toString() {
            return "{" + key + "->" + value + "}";
        }
    }

    private LinkedBinaryTreeNode<Entry<K, V>> root;
    private int size = 0;

    private final Comparator<K> comparator;

    public BSTWithNode(Comparator<K> comparator) {
        this.comparator = comparator;
        root = new LinkedBinaryTreeNode<>(null, null, null, null);
    }

    public BSTWithNode() {
        this.comparator = new DefaultComparator<>();
        root = new LinkedBinaryTreeNode<>(null, null, null, null);
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

    protected LinkedBinaryTreeNode<Entry<K, V>> validate(Position<Entry<K, V>> position) throws IllegalArgumentException {
        if (!(position instanceof LinkedBinaryTreeNode<Entry<K, V>> node))
            throw new IllegalArgumentException("Not a valid position type");

        if (node.getParent() == node)
            throw new IllegalArgumentException("position is no longer valid in the tree");

        return node;
    }

    protected LinkedBinaryTreeNode<Entry<K, V>> createNode(Entry<K, V> element, LinkedBinaryTreeNode<Entry<K, V>> parent, LinkedBinaryTreeNode<Entry<K, V>> left, LinkedBinaryTreeNode<Entry<K, V>> right) {
        return new LinkedBinaryTreeNode<>(element, parent, left, right);
    }

    private void addLeft(Position<Entry<K, V>> position, Entry<K, V> element) throws IllegalStateException {
        LinkedBinaryTreeNode<Entry<K, V>> parent = validate(position);

        if (parent.getLeft() != null) throw new IllegalStateException("Node already has a left child");

        LinkedBinaryTreeNode<Entry<K, V>> child = createNode(element, parent, null, null);
        parent.setLeft(child);
        size++;
    }

    private void addRight(Position<Entry<K, V>> position, Entry<K, V> element) throws IllegalStateException {
        LinkedBinaryTreeNode<Entry<K, V>> parent = validate(position);

        if (parent.getRight() != null) throw new IllegalStateException("Node already has a right child");

        LinkedBinaryTreeNode<Entry<K, V>> child = createNode(element, parent, null, null);
        parent.setRight(child);
        size++;
    }

    private void set(Position<Entry<K, V>> position, Entry<K, V> element) throws IllegalStateException {
        LinkedBinaryTreeNode<Entry<K, V>> node = validate(position);
        Entry<K, V> temp = node.getElement();
        node.setElement(element);
    }

    private void remove(Position<Entry<K, V>> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<Entry<K, V>> node = validate(position);

        if (numChildren(node) == 2) throw new IllegalArgumentException("Node has two children");

        LinkedBinaryTreeNode<Entry<K, V>> child = node.getLeft() != null ? node.getLeft() : node.getRight();

        if (child != null)
            child.setParent(node.getParent());

        if (node == root) {
            root = child;
        } else {
            LinkedBinaryTreeNode<Entry<K, V>> parentNode = node.getParent();

            // determine which child is the deleted node and setting it.
            if (node == parentNode.getLeft())
                parentNode.setLeft(child);
            else
                parentNode.setRight(child);
        }

        size--;
        node.setElement(null);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(node); // invalidate
    }

    private void expand(Position<Entry<K, V>> position, Entry<K, V> newEntry) throws IllegalArgumentException {
        LinkedBinaryTreeNode<Entry<K, V>> node = validate(position);
        if (isInternal(node)) throw new IllegalArgumentException("Cannot expand non external nodes");

        set(position, newEntry);
        addLeft(position, null);
        addRight(position, null);
    }

    private V value(Position<Entry<K, V>> position) {
        if (isExternal(position)) return null;

        return position.getElement().getValue();
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

    private Position<Entry<K, V>> find(Position<Entry<K, V>> position, K key) {
        if (isExternal(position)) {
            return position;
        }

        int compare = compare(key, position.getElement());

        if (compare == 0) return position;

        if (compare < 0) return find(left(position), key);

        return find(right(position), key);
    }

    public V insert(K key, V value) {
        checkKey(key);
        Entry<K, V> newEntry = new BSTEntry<>(key, value);
        Position<Entry<K, V>> position = find(root(), key);

        if (isExternal(position)) {
            expand(position, newEntry);
            return null;
        }

        V oldValue = value(position);
        set(position, newEntry);

        return oldValue;
    }

    public V remove(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = find(root(), key);

        if (isExternal(position)) { // not found
            return null;
        }

        V oldValue = value(position);

        Position<Entry<K, V>> leftPosition = left(position);
        Position<Entry<K, V>> rightPosition = right(position);


        if (isInternal(leftPosition) && isInternal(rightPosition)) {
            Position<Entry<K, V>> replacement = inOrderBefore(position);
            set(position, replacement.getElement());
            position = replacement; // since replacement is has always external node by inOrderBefore definition
        }

        // it has at-least one leaf
        Position<Entry<K, V>> leaf = isExternal(leftPosition) ? leftPosition : rightPosition;

        //order here is important
        remove(leaf);
        remove(position);

        return oldValue;
    }

    public V shrink(K key) {
        checkKey(key);
        Position<Entry<K, V>> position = find(root(), key);

        if (left(position) != null || right(position) != null) {
            throw new IllegalArgumentException("cannot shrink this component");
        }

        LinkedBinaryTreeNode<Entry<K, V>> node = validate(position);
        V temp = position.getElement().getValue();
        node.setElement(null); // set itself as null
        remove(node.getLeft()); // remove its sentinels
        remove(node.getRight()); // remove its sentinels
        return temp;
    }

    @Override
    public Position<Entry<K, V>> root() {
        return root;
    }

    @Override
    public int size() {
        return (size - 1) / 2;
    }

    @Override
    public Position<Entry<K, V>> left(Position<Entry<K, V>> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<Entry<K, V>> node = validate(position);
        return node.getLeft();
    }

    @Override
    public Position<Entry<K, V>> right(Position<Entry<K, V>> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<Entry<K, V>> node = validate(position);
        return node.getRight();
    }

    @Override
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<Entry<K, V>> node = validate(position);
        return node.getParent();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("( ");
        for (Position<Entry<K, V>> p : inOrder()) {
            if (isInternal(p)) {
                str.append(p.getElement()).append(" ");
            }
        }
        str.append(")");
        return str.toString();
    }
}
