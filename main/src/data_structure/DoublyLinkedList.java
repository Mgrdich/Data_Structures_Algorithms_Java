package data_structure;

public class DoublyLinkedList<E> {

    private DoubleLinkedListNode<E> header;
    private DoubleLinkedListNode<E> trailer;
    private int size = 0;

    public DoublyLinkedList() {
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
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
    }

    public void addLast(E element) {
    }

    public void removeFirst(E element) {
    }

    public void removeLast(E element) {
    }

    private void addBetween(DoubleLinkedListNode<E> firstNode, DoubleLinkedListNode<E> secondNode) {

    }

    public void print() {
    }
}
