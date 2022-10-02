package data_structure;

public class LinkedStack<E> implements Stack<E> {
    private final DoublyLinkedList<E> linkedList = new DoublyLinkedList<>();

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
