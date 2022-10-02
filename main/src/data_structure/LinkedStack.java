package data_structure;

public class LinkedStack<E> implements Stack<E> {
    private final DoublyLinkedList<E> linkedList = new DoublyLinkedList<>();

    @Override
    public void push(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E pop() {
        return linkedList.removeLast();
    }

    @Override
    public E top() {
        return linkedList.last();
    }

    @Override
    public int size() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    public void print() {
        linkedList.print();
    }
}
