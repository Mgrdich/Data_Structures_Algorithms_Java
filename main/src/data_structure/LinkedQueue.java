package data_structure;

import adt.Queue;

public class LinkedQueue<E> implements Queue<E> {
    SinglyLinkedList<E> linkedList = new SinglyLinkedList<>();

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public void enqueue(E element) {
        linkedList.addLast(element);
    }

    @Override
    public E dequeue() {
        return linkedList.removeFirst();
    }

    @Override
    public E first() {
        return linkedList.first();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /**
     * This print prints from the top accessible element to bottom
     * cause that is the access structure
     */
    public void print() {
        linkedList.print();
    }
}
