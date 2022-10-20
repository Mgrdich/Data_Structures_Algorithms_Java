package adt;

public interface Position<E> {
    E getElement() throws IllegalStateException;
}
