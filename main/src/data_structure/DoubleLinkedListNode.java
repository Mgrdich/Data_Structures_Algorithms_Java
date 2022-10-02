package data_structure;

public class DoubleLinkedListNode<E> {
    private DoubleLinkedListNode<E> next;
    private DoubleLinkedListNode<E> prev;
    private E element;

    public E getElement() {
        return element;
    }

    public DoubleLinkedListNode<E> getNext() {
        return next;
    }

    public DoubleLinkedListNode<E> getPrev() {
        return prev;
    }

    public void setNext(DoubleLinkedListNode<E> next) {
        this.next = next;
    }

    public void setPrev(DoubleLinkedListNode<E> prev) {
        this.prev = prev;
    }
}
