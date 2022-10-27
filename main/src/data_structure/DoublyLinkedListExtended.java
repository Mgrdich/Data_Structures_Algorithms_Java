package data_structure;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedListExtended<E> implements Iterable<E> {
    private static class DoubleLinkedListNode<E> {
        private DoubleLinkedListNode<E> next;
        private DoubleLinkedListNode<E> prev;
        private E element;

        public DoubleLinkedListNode(E e, DoubleLinkedListNode<E> p, DoubleLinkedListNode<E> n) {
            prev = p;
            next = n;
            element = e;
        }


        public E getElement() {
            return element;
        }

        public DoubleLinkedListNode<E> getNext() {
            return next;
        }

        public DoubleLinkedListNode<E> getPrev() {
            return prev;
        }

        public void setNext(DoubleLinkedListNode<E> next) {
            this.next = next;
        }

        public void setPrev(DoubleLinkedListNode<E> prev) {
            this.prev = prev;
        }

        public void setElement(E element) {
            this.element = element;
        }
    }

    private final DoubleLinkedListNode<E> header;
    private final DoubleLinkedListNode<E> trailer;
    private int size = 0;

    public DoublyLinkedListExtended() {
        header = new DoubleLinkedListNode<>(null, null, null);
        trailer = new DoubleLinkedListNode<>(null, header, null);
        header.setNext(trailer);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E first() {
        if (isEmpty()) return null;

        return header.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;

        return trailer.getPrev().getElement();
    }

    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    public E removeFirst() {
        if (isEmpty()) return null;

        return remove(header.getNext());
    }

    public E removeLast() {
        if (isEmpty()) return null;

        return remove(trailer.getPrev());
    }

    private void addBetween(E element, DoubleLinkedListNode<E> predecessor, DoubleLinkedListNode<E> successor) {
        DoubleLinkedListNode<E> newNode = new DoubleLinkedListNode<>(element, predecessor, successor);

        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    private E remove(DoubleLinkedListNode<E> toBeDeletedNode) {
        DoubleLinkedListNode<E> prevNode = toBeDeletedNode.getPrev();
        DoubleLinkedListNode<E> nextNode = toBeDeletedNode.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        size--;

        return toBeDeletedNode.getElement();
    }

    public String toString() {
        StringBuilder str = new StringBuilder("(");
        DoubleLinkedListNode<E> curr = header.getNext();

        while (curr != trailer) {
            str.append(curr.getElement());
            if (curr != trailer.getPrev()) {
                str.append(", ");
            }
            curr = curr.getNext();
        }
        str.append(")");

        return str.toString();
    }

    private class DoublyLinkedListIterator implements ListIterator<E> {
        DoubleLinkedListNode<E> curr = header;
        boolean removable = false;
        boolean added = false;
        int j = -1;

        @Override
        public boolean hasNext() {
            DoubleLinkedListNode<E> next = curr.getNext();
            return next != null && next != trailer; // null for the initial case
        }

        @Override
        public E next() throws NoSuchElementException {
            DoubleLinkedListNode<E> next = curr.getNext();
            if (next == trailer) throw new NoSuchElementException("no such element");
            curr = next;
            removable = true;
            added = false;
            j++;
            return next.getElement();
        }

        @Override
        public boolean hasPrevious() {
            DoubleLinkedListNode<E> prev = curr.getPrev();
            return prev != null && prev != header; // null for the initial case
        }

        @Override
        public E previous() throws NoSuchElementException {
            DoubleLinkedListNode<E> prev = curr.getPrev();
            if (prev == header) throw new NoSuchElementException("no such element");
            j--;

            if (added) {
                added = false;
                return curr.getElement();
            }

            curr = prev;
            removable = true;

            return prev.getElement();
        }

        @Override
        public int nextIndex() {
            return j + 1;
        }

        @Override
        public int previousIndex() {
            if (j == -1) return -1;

            return j - 1;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("nothing to remove");
            removable = false;
            DoubleLinkedListNode<E> prev = curr.getPrev();
            DoublyLinkedListExtended.this.remove(curr);
            curr = prev;
            j--;
        }

        @Override
        public void set(E e) throws IllegalStateException {
            if (curr == header) throw new IllegalStateException("Nothing to set");
            curr.setElement(e);
        }

        @Override
        public void add(E e) {
            DoubleLinkedListNode<E> prev = curr;
            DoubleLinkedListNode<E> next = curr.getNext();
            curr = new DoubleLinkedListNode<>(e, prev, next); // so that pointer will be specified on it
            prev.setNext(curr);
            next.setPrev(curr);
            added = true; // flag to keep the right position
            j++;
        }
    }

    public ListIterator<E> iterator() {
        return new DoublyLinkedListIterator();
    }
}
