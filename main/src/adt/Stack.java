package adt;

public interface Stack<E> {
    void push(E e);

    E pop();

    E top();

    int size();

    boolean isEmpty();
}