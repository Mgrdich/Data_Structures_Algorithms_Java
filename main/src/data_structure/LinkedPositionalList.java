package data_structure;

import adt.Position;
import adt.PositionalList;

public class LinkedPositionalList<E> implements PositionalList<E> {

    private static class Node<E> implements Position<E> {

        @Override
        public E getElement() throws IllegalStateException {
            return null;
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Position<E> first() {
        return null;
    }

    @Override
    public Position<E> last() {
        return null;
    }

    @Override
    public Position<E> before(Position<E> position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Position<E> after(Position<E> position) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Position<E> addFirst(E element) {
        return null;
    }

    @Override
    public Position<E> addLast(E element) {
        return null;
    }

    @Override
    public Position<E> addBefore(Position<E> position, E element) {
        return null;
    }

    @Override
    public Position<E> addAfter(Position<E> position, E element) {
        return null;
    }

    @Override
    public E set(Position<E> position, E element) throws IllegalArgumentException {
        return null;
    }

    @Override
    public E remove(Position<E> position) throws IllegalArgumentException {
        return null;
    }
}
