package data_structure;

import adt.Position;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
    private static class ArrayBinaryTreeNode<E> implements Position<E> {
        private int index;
        private E element;

        public ArrayBinaryTreeNode() {

        }

        public ArrayBinaryTreeNode(E element, int index) {
            this.element = element;
            this.index = index;
        }

        private int getLeftIndex() {
            return (2 * index) + 1;
        }

        private int getRightIndex() {
            return (2 * index) + 2;
        }

        private int getParentIndex() {
            return (index - 1) / 2;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        private int setIndex(int index) {
            return this.index = index;
        }
    }

    private ArrayBinaryTreeNode<E>[] data;
    private int size = 0;

    public static final int CAPACITY = 100; //it increases frequently


    public ArrayBinaryTree() {
        this(CAPACITY);
    }

    @SuppressWarnings("all")
    public ArrayBinaryTree(int capacity) {
        data = new ArrayBinaryTreeNode[capacity];
    }

    private ArrayBinaryTreeNode<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof ArrayBinaryTreeNode<E> node)) throw new IllegalArgumentException("not valid position type!");
        if (node.getIndex() == -1) throw new IllegalArgumentException("p is no longer in the tree!");
        return node;
    }

    @SuppressWarnings("all")
    protected void resize(int capacity) {
        ArrayBinaryTreeNode<E>[] temp = new ArrayBinaryTreeNode[capacity];
        for (int i = 0; i < data.length; i++)
            temp[i] = data[i];
        data = temp; // start using the new array
    }

    private ArrayBinaryTreeNode<E> getLeft(ArrayBinaryTreeNode<E> node) {
        int index = node.getLeftIndex();
        if (index >= data.length) return null;
        return data[index];
    }

    private ArrayBinaryTreeNode<E> getRight(ArrayBinaryTreeNode<E> node) {
        int index = node.getRightIndex();
        if (index >= data.length) return null;
        return data[index];
    }

    private ArrayBinaryTreeNode<E> getParent(ArrayBinaryTreeNode<E> node) {
        int index = node.getParentIndex();
        if (index < 0) return null;
        return data[index];
    }

    private ArrayBinaryTreeNode<E> addElement(ArrayBinaryTreeNode<E> node) {
        data[node.getIndex()] = node;
        size++;
        return node;
    }

    @Override
    public Position<E> root() {
        if (isEmpty()) return null;

        return data[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> parent(Position<E> position) throws IllegalArgumentException {
        ArrayBinaryTreeNode<E> node = validate(position);
        return getParent(node);
    }

    @Override
    public Position<E> left(Position<E> position) throws IllegalArgumentException {
        ArrayBinaryTreeNode<E> node = validate(position);
        return getLeft(node);
    }

    @Override
    public Position<E> right(Position<E> position) throws IllegalArgumentException {
        ArrayBinaryTreeNode<E> node = validate(position);
        return getRight(node);
    }


    public Position<E> addRoot(E element) throws IllegalStateException, IllegalArgumentException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        ArrayBinaryTreeNode<E> root = new ArrayBinaryTreeNode<>(element, 0);

        return addElement(root);
    }

    public Position<E> addLeft(Position<E> position, E element) throws IllegalStateException, IllegalArgumentException {
        ArrayBinaryTreeNode<E> parent = validate(position);

        if (left(parent) != null) throw new IllegalStateException("Node already has a left child");

        if (parent.getLeftIndex() >= data.length) resize(parent.getLeftIndex() + 1);

        ArrayBinaryTreeNode<E> child = new ArrayBinaryTreeNode<>(element, parent.getLeftIndex());

        return addElement(child);
    }

    public Position<E> addRight(Position<E> position, E element) throws IllegalStateException {
        ArrayBinaryTreeNode<E> parent = validate(position);

        if (right(parent) != null) throw new IllegalStateException("Node already has a right child");

        if (parent.getRightIndex() >= data.length) resize(parent.getRightIndex() + 1);

        ArrayBinaryTreeNode<E> child = new ArrayBinaryTreeNode<>(element, parent.getRightIndex());

        return addElement(child);
    }

    public E set(Position<E> position, E element) throws IllegalArgumentException {
        ArrayBinaryTreeNode<E> node = validate(position);
        E temp = node.getElement();
        node.setElement(element);
        return temp;
    }

    public E remove(Position<E> position) throws IllegalArgumentException {
        ArrayBinaryTreeNode<E> node = validate(position);

        if (numChildren(node) == 2) throw new IllegalArgumentException("Node has two children");

        ArrayBinaryTreeNode<E> child = getLeft(node) != null ? getLeft(node) : getRight(node);

        return position.getElement();
    }
}
