package adt;

public interface List<E> extends Iterable<E> {
    boolean isEmpty();

    int size();

    E get(int i) throws IndexOutOfBoundsException;

    E set(int i, E element) throws IndexOutOfBoundsException;

    void add(int i, E element) throws IndexOutOfBoundsException;

    E remove(int i) throws IndexOutOfBoundsException;
}
