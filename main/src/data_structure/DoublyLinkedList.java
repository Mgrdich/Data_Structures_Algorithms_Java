package data_structure;

public class DoublyLinkedList<E> {

    private final DoubleLinkedListNode<E> header;
    private final DoubleLinkedListNode<E> trailer;
    private int size = 0;

    public DoublyLinkedList() {
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

    public void print() {
        DoubleLinkedListNode<E> curr = header.getNext();

        while (curr != trailer) {
            System.out.print(curr.getElement()+ " ");
            curr = curr.getNext();
        }
        System.out.println();
    }
}
