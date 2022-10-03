package data_structure;

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

    public void print() {
        linkedList.print();
    }
}
