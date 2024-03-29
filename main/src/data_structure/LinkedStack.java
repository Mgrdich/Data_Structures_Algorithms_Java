package data_structure;

import adt.Stack;

public class LinkedStack<E> implements Stack<E> {
    private final SinglyLinkedList<E> linkedList = new SinglyLinkedList<>();

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E top() {
        return linkedList.first();
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
