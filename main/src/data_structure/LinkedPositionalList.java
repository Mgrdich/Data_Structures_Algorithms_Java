package data_structure;

import adt.Position;
import adt.PositionalList;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
            if (next == null) throw new IllegalStateException("Position no longer valid");
            return element;
        }
    }

    private final Node<E> header;
    private final Node<E> trailer;
    private int size = 0;

    public LinkedPositionalList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }

    /**
     * It casts and validate a particular position and cast it to node
     */
    private Node<E> validate(Position<E> position) throws IllegalArgumentException {
        if (!(position instanceof Node<E> node)) throw new IllegalArgumentException("An invalid Argument");

        if (node.getNext() == null) throw new IllegalArgumentException("is no longer valid position");

        return node;
    }

    /**
     * It casts node to Position
     */
    private Position<E> position(Node<E> node) {
        if (node == header || node == trailer) return null;

        return node;
    }

    private Node<E> addBetween(E element, Node<E> prev, Node<E> next) {
        Node<E> newNode = new Node<>(element, prev, next);
        prev.setNext(newNode);
        next.setPrev(newNode);
        size++;
        return newNode;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Position<E> first() {
        return position(header.getNext());
    }

    @Override
    public Position<E> last() {
        return position(trailer.getPrev());
    }

    @Override
    public Position<E> before(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return position(node.getPrev());
    }

    @Override
    public Position<E> after(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return position(node.getNext());
    }

    @Override
    public Position<E> addFirst(E element) {
        return addBetween(element, header, header.getNext());
    }

    @Override
    public Position<E> addLast(E element) {
        return addBetween(element, trailer.getPrev(), trailer);
    }

    @Override
    public Position<E> addBefore(Position<E> position, E element) throws IllegalArgumentException{
        Node<E> node = validate(position);
        return addBetween(element, node.getPrev(), node);
    }

    @Override
    public Position<E> addAfter(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return addBetween(element, node, node.getNext());
    }

    @Override
    public E set(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> node = validate(position);
        E answer = node.getElement();
        node.setElement(element);
        return answer;
    }

    @Override
    public E remove(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        Node<E> prev = node.getPrev();
        Node<E> next = node.getNext();

        prev.setNext(next);
        next.setPrev(prev);
        size--;

        E answer = node.getElement();
        node.setNext(null);
        node.setPrev(null);
        node.setElement(null);

        return answer;
    }

    private class PositionIterator implements Iterator<Position<E>> {
        private Position<E> cursor = first();
        private Position<E> recent = null;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public Position<E> next() throws NoSuchElementException {
            if (cursor == null) throw new NoSuchElementException("nothing left");
            recent = cursor;
            cursor = after(cursor);
            return recent;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (recent == null) throw new IllegalStateException("nothing to remove");
            LinkedPositionalList.this.remove(recent);
            recent = null;
        }
    }

    private class PositionIterable implements Iterable<Position<E>> {
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }

    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> positionIterator = new PositionIterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
}
