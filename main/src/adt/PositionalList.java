package adt;

public interface PositionalList<E> {
    int size();
    boolean isEmpty();

    Position<E> first();
    Position<E> last();

    Position<E> before(Position<E> position) throws IllegalArgumentException;
    Position<E> after(Position<E> position) throws IllegalArgumentException;

    Position<E> addFirst(E element);
    Position<E> addLast(E element);

    Position<E> addBefore(Position<E> position, E element);
    Position<E> addAfter(Position<E> position, E element);

    E set(Position<E> position, E element) throws IllegalArgumentException;
    E remove(Position<E> position) throws IllegalArgumentException;
}
