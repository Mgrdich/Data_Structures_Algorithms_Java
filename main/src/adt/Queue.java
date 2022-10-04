package adt;

public interface Queue<E> {
    int size();

    void enqueue(E element);

    E dequeue();

    E first();

    boolean isEmpty();
}
