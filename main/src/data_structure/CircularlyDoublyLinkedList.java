package data_structure;

public class CircularlyDoublyLinkedList<E> {
    private final DoubleLinkedListNode<E> sentinel;
    private int size = 0;

    public CircularlyDoublyLinkedList() {
        sentinel = new DoubleLinkedListNode<>(null, null, null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void rotate() {

        DoubleLinkedListNode<E> nextNode = sentinel.getNext();
        DoubleLinkedListNode<E> prevNode = sentinel.getPrev();
        DoubleLinkedListNode<E> doubleNextNode = nextNode.getNext();

        nextNode.setPrev(prevNode);
        prevNode.setNext(nextNode);

        nextNode.setNext(sentinel);
        sentinel.setPrev(nextNode);

        doubleNextNode.setPrev(sentinel);
        sentinel.setNext(doubleNextNode);
    }

    public E first() {
        if (isEmpty()) return null;

        return sentinel.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;

        return sentinel.getPrev().getElement();
    }

    public void addFirst(E element) {
        addBetween(element, sentinel, sentinel.getNext());
    }

    public void addLast(E element) {
        addBetween(element, sentinel.getPrev(), sentinel);
    }

    public E removeFirst() {
        if (isEmpty()) return null;

        return remove(sentinel.getNext());
    }

    public E removeLast() {
        if (isEmpty()) return null;

        return remove(sentinel.getPrev());
    }

    private void addBetween(E element, DoubleLinkedListNode<E> predecessor, DoubleLinkedListNode<E> successor) {
        DoubleLinkedListNode<E> newNode = new DoubleLinkedListNode<>(element, predecessor, successor);

        predecessor.setNext(newNode);
        successor.setPrev(newNode);
        size++;
    }

    private E remove(DoubleLinkedListNode<E> toBeDeletedNode) {
        DoubleLinkedListNode<E> next = toBeDeletedNode.getNext();
        DoubleLinkedListNode<E> prev = toBeDeletedNode.getPrev();

        prev.setNext(next);
        next.setPrev(prev);
        size--;

        return toBeDeletedNode.getElement();
    }

    public void print() {
        DoubleLinkedListNode<E> curr = sentinel.getNext();

        while (curr != sentinel) {
            System.out.print(curr.getElement() + " ");
            curr = curr.getNext();
        }
        System.out.println();
    }
}
