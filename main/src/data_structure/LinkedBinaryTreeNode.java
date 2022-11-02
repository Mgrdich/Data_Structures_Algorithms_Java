package data_structure;

import adt.Position;

public class LinkedBinaryTreeNode<E> implements Position<E> {
    private E element;
    private LinkedBinaryTree<E> right;
    private LinkedBinaryTree<E> left;
    private LinkedBinaryTree<E> parent;

    public LinkedBinaryTreeNode(E element, LinkedBinaryTree<E> parent, LinkedBinaryTree<E> left, LinkedBinaryTree<E> right) {
        this.element = element;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    @Override
    public E getElement() throws IllegalStateException {
        return null;
    }

    public LinkedBinaryTree<E> getLeft() {
        return left;
    }

    public LinkedBinaryTree<E> getRight() {
        return right;
    }

    public LinkedBinaryTree<E> getParent() {
        return parent;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setLeft(LinkedBinaryTree<E> left) {
        this.left = left;
    }

    public void setRight(LinkedBinaryTree<E> right) {
        this.right = right;
    }

    public void setParent(LinkedBinaryTree<E> parent) {
        this.parent = parent;
    }
}
