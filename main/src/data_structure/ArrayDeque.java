package data_structure;

import adt.Deque;

public class ArrayDeque<E> implements Deque<E> {
    public static int CAPACITY = 1000;
    private int first = 0;

    private int last = 0;
    private int size = 0;

    private final E[] deque;

    public ArrayDeque() {
        this(CAPACITY);
    }

    public ArrayDeque(int capacity) {
        deque = (E[]) new Object[capacity];
    }

    @Override
    public void addFirst(E element) throws IllegalStateException {
        if (size == size()) throw new IllegalStateException("Out of memory");

        int availableIndex = (first) % deque.length;
        deque[availableIndex] = element;
        last = (first + 1) % deque.length;
        size++;
    }

    @Override
    public void addLast(E element) throws IllegalStateException {
        if (size == size()) throw new IllegalStateException("Out of memory");

        int availableIndex = (last) % deque.length;
        deque[availableIndex] = element;
        last = (last + 1) % deque.length;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;

        E element = deque[first];
        deque[first] = null; // garbage collection

        first = (first + 1) % deque.length;
        size--;

        return element;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;

        E element = deque[last];
        deque[last] = null; // garbage collection

        last = last - 1;
        last = last == -1 ? deque.length - 1 : last % deque.length;
        size--;

        return element;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;

        return deque[first];
    }

    @Override
    public E last() {
        if (isEmpty()) return null;

        return deque[last];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
