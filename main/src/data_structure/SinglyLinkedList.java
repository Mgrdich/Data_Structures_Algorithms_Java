package data_structure;

public class SinglyLinkedList<E> {

    private SinglyLinkedListNode<E> head;
    private SinglyLinkedListNode<E> tail;
    private int size = 0;

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E first() {
        if (isEmpty()) return null;
        return head.getElement();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }

    public void addFirst(E e) {
        head = new SinglyLinkedListNode<>(e, head);
        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addLast(E e) {
        SinglyLinkedListNode<E> newNode = new SinglyLinkedListNode<>(e, null);
        if (isEmpty()) {
            head = newNode;

        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        E returnElement = head.getElement();

        head = head.getNext();
        size--;

        if (isEmpty()) tail = null;

        return returnElement;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        SinglyLinkedListNode<E> curr = head;

        while (curr != null) {
            stringBuilder.append(curr.getElement());
            curr = curr.getNext();
            if (curr != null) {
                stringBuilder.append(" => ");
            }
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
