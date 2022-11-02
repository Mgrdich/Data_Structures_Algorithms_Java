package adt;

public interface Tree<E> extends Iterable<E> {
    Position<E> root();

    Position<E> parent(Position<E> child) throws IllegalArgumentException;

    Iterable<Position<E>> children(Position<E> parent) throws IllegalArgumentException;

    int numChildren(Position<E> parent) throws IllegalArgumentException;

    int size();

    boolean isExternal(Position<E> node) throws IllegalArgumentException;

    boolean isInternal(Position<E> node) throws IllegalArgumentException;

    boolean isRoot(Position<E> node) throws IllegalArgumentException;

    boolean isEmpty();

    Iterable<Position<E>> positions();
}
