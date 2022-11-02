package adt;

public interface BinaryTree<E> extends Tree<E> {
    Position<E> left(Position<E> node) throws IllegalArgumentException;

    Position<E> right(Position<E> node) throws IllegalArgumentException;

    Position<E> sibling(Position<E> node) throws IllegalArgumentException;
}
