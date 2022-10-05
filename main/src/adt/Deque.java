package adt;

public interface Deque<E> {
    void addFirst(E element);

    void addLast(E element);

    E removeFirst();

    E removeLast();

    E first();

    E last();

    int size();

    boolean isEmpty();
}
