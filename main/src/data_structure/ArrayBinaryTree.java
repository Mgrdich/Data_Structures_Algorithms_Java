package data_structure;

import adt.Position;

import java.util.Iterator;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
    private class ArrayBinaryTreeNode implements Position<E> {
        private int index;
        private E element;

        public ArrayBinaryTreeNode() {

        }

        public ArrayBinaryTreeNode(E element, int index) {
            this.element = element;
            this.index = index;
        }

        private int getLeftIndex() {
            return 2 * index + 1;
        }

        private int getRightIndex() {
            return 2 * index + 2;
        }

        private int getParentIndex() {
            return (index - 1) / 2;
        }

        @Override
        public E getElement() throws IllegalStateException {
            return element;
        }

        public ArrayBinaryTreeNode getLeft() {
            int index = getLeftIndex();
            if (index > size())
                return null;
            return data[index];
        }

        public ArrayBinaryTreeNode getRight() {
            int index = getRightIndex();
            if (index > size())
                return null;
            return data[index];
        }

        public ArrayBinaryTreeNode getParent() {
            int index = getParentIndex();
            if (index < 0)
                return null;
            return data[index];
        }

        public void setElement(E element) {
            this.element = element;
        }

        private int setIndex(int index) {
            return this.index = index;
        }
    }

    private ArrayBinaryTreeNode[] data;
    private int size = 0;

    public static final int CAPACITY = 16;


    public ArrayBinaryTree() {
        this(CAPACITY);
    }

    @SuppressWarnings("all")
    public ArrayBinaryTree(int capacity) {
        data = (ArrayBinaryTreeNode[]) new Object[capacity];
    }

    private ArrayBinaryTreeNode validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof ArrayBinaryTreeNode node))
            throw new IllegalArgumentException("not valid position type!");
        if (node == parent(node))
            throw new IllegalArgumentException("p is no longer in the tree!");
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
        ArrayBinaryTreeNode node = validate(position);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> position) throws IllegalArgumentException {
        ArrayBinaryTreeNode node = validate(position);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> position) throws IllegalArgumentException {
        ArrayBinaryTreeNode node = validate(position);
        return node.getRight();
    }


    public Position<E> addRoot(E element) throws IllegalStateException, IllegalArgumentException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        ArrayBinaryTreeNode root = new ArrayBinaryTreeNode(element, 0);
        size = 1;
        return root;
    }

    public Position<E> addLeft(Position<E> position, E element) throws IllegalStateException, IllegalArgumentException {
        ArrayBinaryTreeNode parent = validate(position);

        if (parent.getLeft() != null) throw new IllegalStateException("Node already has a left child");

        ArrayBinaryTreeNode child = new ArrayBinaryTreeNode(element, parent.getLeftIndex());
        size++;
        return child;
    }

    public Position<E> addRight(Position<E> position, E element) throws IllegalStateException {
        ArrayBinaryTreeNode parent = validate(position);

        if (parent.getRight() != null) throw new IllegalStateException("Node already has a right child");

        ArrayBinaryTreeNode child = new ArrayBinaryTreeNode(element, parent.getRightIndex());
        size++;
        return child;
    }

    public E set(Position<E> position, E element) throws IllegalArgumentException {
        ArrayBinaryTreeNode node = validate(position);
        E temp = node.getElement();
        node.setElement(element);
        return temp;
    }

    public E remove(Position<E> position) throws IllegalArgumentException {
        ArrayBinaryTreeNode node = validate(position);

        if (numChildren(node) == 2) throw new IllegalArgumentException("Node has two children");

        ArrayBinaryTreeNode child = node.getLeft() != null ? node.getLeft() : node.getRight();

        return position.getElement();
    }

    @Override
    public Iterable<Position<E>> positions() {
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
