package data_structure;

public class CircularlyLinkedList<E> {

    private SinglyLinkedListNode<E> tail;
    private int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void rotate() {
        if (!isEmpty()) {
            tail = tail.getNext();
        }
    }

    public void addFirst(E element) {
        SinglyLinkedListNode<E> newNode = new SinglyLinkedListNode<>(element, null);
        if (isEmpty()) {
            tail = newNode;
        } else {
            newNode.setNext(tail.getNext());
        }
        tail.setNext(newNode);
        size++;
    }

    public void addLast(E element) {
        addFirst(element);
        rotate();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        SinglyLinkedListNode<E> head = tail.getNext();
        if (head == tail) {
            tail = null;
        } else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getElement();
    }

    public void print() {
        SinglyLinkedListNode<E> curr = tail.getNext();

        while (curr != tail){
            System.out.print(curr.getElement()+ " ");
            curr = curr.getNext();
        }
        System.out.println(tail.getElement());
        System.out.println();
    }
}
