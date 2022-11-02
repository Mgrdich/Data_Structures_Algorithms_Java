package data_structure;

import adt.Position;

import java.util.Iterator;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E>{
    @Override
    public Position<E> left(Position<E> node) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Position<E> right(Position<E> node) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Position<E> root() {
        return null;
    }

    @Override
    public Position<E> parent(Position<E> child) throws IllegalArgumentException {
        return null;
    }

    @Override
    public int size() {
        return 0;
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
