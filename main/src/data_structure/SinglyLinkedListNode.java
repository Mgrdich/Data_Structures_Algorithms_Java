package data_structure;

/**
 * this class is public because it can be used in exercises
 */
public class SinglyLinkedListNode<E> {
    private final E element;
    private SinglyLinkedListNode<E> next;

    public SinglyLinkedListNode(E e, SinglyLinkedListNode<E> n) {
        element = e;
        next = n;
    }

    public E getElement() {
        return element;
    }

    public SinglyLinkedListNode<E> getNext() {
        return next;
    }

    public void setNext(SinglyLinkedListNode<E> nextNode) {
        next = nextNode;
    }
}
