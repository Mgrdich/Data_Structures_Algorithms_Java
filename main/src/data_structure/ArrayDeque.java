package data_structure;

import adt.Deque;

public class ArrayDeque<E> implements Deque<E> {
    public static int CAPACITY = 1000;
    private int first = -1;
    private int last = -1;
    private int size = 0;

    private final E[] deque;

    public ArrayDeque() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayDeque(int capacity) {
        deque = (E[]) new Object[capacity];
    }

    @Override
    public void addFirst(E element) throws IllegalStateException {
        if (deque.length == size()) throw new IllegalStateException("Out of memory");

        if (isEmpty()) {
            first = 0;
            last = 0;
        } else {
            first = indexCalculator(first - 1);
        }

        deque[first] = element;
        size++;
    }

    @Override
    public void addLast(E element) throws IllegalStateException {
        if (deque.length == size()) throw new IllegalStateException("Out of memory");

        if (isEmpty()) {
            first = 0;
            last = 0;
        } else {
            last = indexCalculator(last + 1);
        }

        deque[last] = element;
        size++;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) return null;
        size--;

        E temp = deque[first];
        deque[first] = null; // garbage collection

        if (isEmpty()) {
            first = -1;
            last = -1;
        } else {
            first = indexCalculator(first + 1);
        }


        return temp;
    }

    @Override
    public E removeLast() {
        if (isEmpty()) return null;

        size--;

        E temp = deque[last];
        deque[last] = null; // garbage collection

        if (isEmpty()) {
            first = -1;
            last = -1;
        } else {
            last = indexCalculator(last - 1);
        }

        return temp;
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

    private int indexCalculator(int num) {
        return num <= -1 ? deque.length - 1 : num % deque.length;
    }
}
