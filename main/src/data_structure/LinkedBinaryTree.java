package data_structure;

import adt.Position;

import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    private LinkedBinaryTreeNode<E> root;
    private int size = 0;

    protected LinkedBinaryTreeNode<E> createNode(E element, LinkedBinaryTreeNode<E> parent, LinkedBinaryTreeNode<E> left, LinkedBinaryTreeNode<E> right) {
        return new LinkedBinaryTreeNode<>(element, parent, left, right);
    }

    protected LinkedBinaryTreeNode<E> validate(Position<E> position) throws IllegalArgumentException {
        if(!(position instanceof LinkedBinaryTreeNode<E>))
            throw new IllegalArgumentException("Not a valid position type");

        LinkedBinaryTreeNode<E> node = (LinkedBinaryTreeNode<E>) position;
        if(node.getParent() == node)
            throw new IllegalArgumentException("position is no longer valid in the tree");

        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> left(Position<E> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);
        return node.getRight();
    }

    @Override
    public Position<E> parent(Position<E> position) throws IllegalArgumentException {
        LinkedBinaryTreeNode<E> node = validate(position);
        return node.getParent();
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
