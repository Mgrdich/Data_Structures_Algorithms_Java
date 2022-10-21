package data_structure;

import adt.Position;
import adt.PositionalList;

public class LinkedPositionalList<E> implements PositionalList<E> {

    private static class Node<E> implements Position<E> {
        private Node<E> next;
        private Node<E> prev;
        private E element;

        public Node(E element, Node<E> prev, Node<E> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }

        public Node<E> getNext() {
            return next;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public E getElement() throws IllegalStateException {
            if(next == null) throw new IllegalStateException("Position no longer valid");
            return element;
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
