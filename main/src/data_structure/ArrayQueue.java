package data_structure;

import adt.Queue;

public class ArrayQueue<E> implements Queue<E> {

    final static public int CAPACITY = 1000;
    private int size = 0;
    private int first = 0;
    private final E[] queue;


    public ArrayQueue() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        queue = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(E element) throws IllegalStateException {
        if (queue.length == size()) throw new IllegalStateException("Queue s full");

        // this will bring it to first position if it is pushed
        int availableIndex = (first + size) % queue.length;

        queue[availableIndex] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        E toBeDeleted = queue[first];
        queue[first] = null;

        first = (first + 1) % queue.length;
        size--;
        return toBeDeleted;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;

        return queue[first];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        int i = first;
        int count = 0;
        while (count < size) {
            stringBuilder.append(queue[i]);
            if (count != size - 1) {
                stringBuilder.append(", ");
            }
            i = (i + 1) % queue.length;
            count++;
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
