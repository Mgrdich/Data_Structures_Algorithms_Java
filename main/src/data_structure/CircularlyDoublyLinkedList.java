package data_structure;

public class CircularlyDoublyLinkedList<E> {
    private DoubleLinkedListNode<E> header;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void rotate() {}
}
