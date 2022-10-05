package data_structure;

import adt.Deque;

public class LinkedDeque<E> implements Deque<E> {
    private final DoublyLinkedList<E> doublyLinkedList = new DoublyLinkedList<>();

    @Override
    public void addFirst(E element) {
        doublyLinkedList.addFirst(element);
    }

    @Override
    public void addLast(E element) {
        doublyLinkedList.addLast(element);
    }

    @Override
    public E removeFirst() {
        return doublyLinkedList.removeFirst();
    }

    @Override
    public E removeLast() {
        return doublyLinkedList.removeLast();
    }

    @Override
    public E first() {
        return doublyLinkedList.first();
    }

    @Override
    public E last() {
        return doublyLinkedList.last();
    }

    @Override
    public int size() {
        return doublyLinkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return doublyLinkedList.isEmpty();
    }
}
