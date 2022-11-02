package data_structure;

import adt.Position;

public class LinkedBinaryTreeNode<E> implements Position<E> {
    private E element;
    private LinkedBinaryTreeNode<E> right;
    private LinkedBinaryTreeNode<E> left;
    private LinkedBinaryTreeNode<E> parent;

    public LinkedBinaryTreeNode(E element, LinkedBinaryTreeNode<E> parent, LinkedBinaryTreeNode<E> left, LinkedBinaryTreeNode<E> right) {
        this.element = element;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getElement() throws IllegalStateException {
        return null;
    }

    public LinkedBinaryTreeNode<E> getLeft() {
        return left;
    }

    public LinkedBinaryTreeNode<E> getRight() {
        return right;
    }

    public LinkedBinaryTreeNode<E> getParent() {
        return parent;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setLeft(LinkedBinaryTreeNode<E> left) {
        this.left = left;
    }

    public void setRight(LinkedBinaryTreeNode<E> right) {
        this.right = right;
    }

    public void setParent(LinkedBinaryTreeNode<E> parent) {
        this.parent = parent;
    }
}
