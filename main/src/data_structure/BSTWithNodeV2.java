package data_structure;

import adt.Entry;
import adt.Position;
import util.DefaultComparator;

import java.util.Comparator;

public class BSTWithNodeV2<K, V> extends AbstractBinaryTree<Entry<K, V>> {

    protected static class BSTEntry<K, V> implements Entry<K, V>, Position<Entry<K, V>> {
        private K key;
        private V value;

        private BSTEntry<K, V> right;
        private BSTEntry<K, V> left;
        private BSTEntry<K, V> parent;

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

        public BSTEntry<K, V> getLeft() {
            return left;
        }

        public BSTEntry<K, V> getRight() {
            return right;
        }

        public BSTEntry<K, V> getParent() {
            return parent;
        }


        public void setLeft(BSTEntry<K, V> left) {
            this.left = left;
        }

        public void setRight(BSTEntry<K, V> right) {
            this.right = right;
        }

        public void setParent(BSTEntry<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return "{" + key + "->" + value + "}";
        }

        @Override
        public Entry<K, V> getElement() throws IllegalStateException {
            return this;
        }

        public void setElement(Entry<K, V> node) throws IllegalStateException {
            if (node != null) {
                key = node.getKey();
                value = node.getValue();
                return;
            }
            key = null;
            value = null;
        }
    }

    private BSTEntry<K, V> root;
    private int size = 0;

    private final Comparator<K> comparator;

    public BSTWithNodeV2(Comparator<K> comparator) {
        this.comparator = comparator;
        root = new BSTEntry<>(null, null);
    }

    public BSTWithNodeV2() {
        this.comparator = new DefaultComparator<>();
        root = new BSTEntry<>(null, null);
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

    protected BSTEntry<K, V> validate(Position<Entry<K, V>> position) throws IllegalArgumentException {
        if (!(position instanceof BSTEntry<K, V> node))
            throw new IllegalArgumentException("Not a valid position type");

        if (node.getParent() == node) throw new IllegalArgumentException("position is no longer valid in the tree");

        return node;
    }

    protected BSTEntry<K, V> createNode(Entry<K, V> element, BSTEntry<K, V> parent, BSTEntry<K, V> left, BSTEntry<K, V> right) {

        BSTEntry<K, V> newNode;
        if (element != null) {
            newNode = new BSTEntry<>(element.getKey(), element.getValue());
        } else {
            newNode = new BSTEntry<>(null, null);
        }

        newNode.setLeft(left);
        newNode.setRight(right);
        newNode.setParent(parent);

        return newNode;
    }

    private void addLeft(Position<Entry<K, V>> position, Entry<K, V> element) throws IllegalStateException {
        BSTEntry<K, V> parent = validate(position);

        if (parent.getLeft() != null) throw new IllegalStateException("Node already has a left child");

        BSTEntry<K, V> child = createNode(element, parent, null, null);
        parent.setLeft(child);
        size++;
    }

    private void addRight(Position<Entry<K, V>> position, Entry<K, V> element) throws IllegalStateException {
        BSTEntry<K, V> parent = validate(position);

        if (parent.getRight() != null) throw new IllegalStateException("Node already has a right child");

        BSTEntry<K, V> child = createNode(element, parent, null, null);
        parent.setRight(child);
        size++;
    }

    private void set(Position<Entry<K, V>> position, Entry<K, V> element) throws IllegalStateException {
        BSTEntry<K, V> node = validate(position);
        Entry<K, V> temp = node.getElement();
        node.setElement(element);
    }

    private void remove(Position<Entry<K, V>> position) throws IllegalArgumentException {
        BSTEntry<K, V> node = validate(position);

        if (numChildren(node) == 2) throw new IllegalArgumentException("Node has two children");

        BSTEntry<K, V> child = node.getLeft() != null ? node.getLeft() : node.getRight();

        if (child != null) child.setParent(node.getParent());

        if (node == root) {
            root = child;
        } else {
            BSTEntry<K, V> parentNode = node.getParent();

            // determine which child is the deleted node and setting it.
            if (node == parentNode.getLeft()) parentNode.setLeft(child);
            else parentNode.setRight(child);
        }

        size--;
        node.setElement(null);
        node.setRight(null);
        node.setLeft(null);
        node.setParent(node); // invalidate
    }

    private void expand(Position<Entry<K, V>> position, Entry<K, V> newEntry) throws IllegalArgumentException {
        BSTEntry<K, V> node = validate(position);
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

        BSTEntry<K, V> node = validate(position);
        V temp = position.getElement().getValue();
        node.setElement(null); // set itself as null
        remove(node.getLeft()); // remove its sentinels
        remove(node.getRight()); // remove its sentinels
        return temp;
    }

    public boolean isAVL() {
        if (isEmpty()) return true;

        return isBalanced(root);
    }

    public boolean isBalanced(BSTEntry<K, V> root) {
        return balancedHeight(root) != -1;
    }

    private int balancedHeight(BSTEntry<K, V> root) {
        if (root == null) {
            return 0;
        }

        int leftSubTreeHeight = balancedHeight(root.getLeft());
        if (leftSubTreeHeight == -1) return -1;

        int rightSubTreeHeight = balancedHeight(root.getRight());
        if (rightSubTreeHeight == -1) return -1;

        // checking the difference of left and right subtree for current node
        if (Math.abs(leftSubTreeHeight - rightSubTreeHeight) > 1) {
            return -1;
        }

        // if it is balanced then return the height
        return (Math.max(leftSubTreeHeight, rightSubTreeHeight) + 1);
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
        BSTEntry<K, V> node = validate(position);
        return node.getLeft();
    }

    @Override
    public Position<Entry<K, V>> right(Position<Entry<K, V>> position) throws IllegalArgumentException {
        BSTEntry<K, V> node = validate(position);
        return node.getRight();
    }

    @Override
    public Position<Entry<K, V>> parent(Position<Entry<K, V>> position) throws IllegalArgumentException {
        BSTEntry<K, V> node = validate(position);
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
