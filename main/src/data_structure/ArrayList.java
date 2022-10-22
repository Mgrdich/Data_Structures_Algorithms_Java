package data_structure;

import adt.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 16;
    private E[] list;
    private int size = 0;

    public ArrayList() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        list = (E[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int i) throws IndexOutOfBoundsException {
        sizeChecker(i, size());

        return list[i];
    }

    @Override
    public E set(int i, E element) throws IndexOutOfBoundsException {
        sizeChecker(i, size());

        E temp = list[i];
        list[i] = element;

        return temp;
    }

    @Override
    public void add(int i, E element) throws IndexOutOfBoundsException {
        sizeChecker(i, size() + 1);

        if (size == list.length) resize(2 * size);

        if (size - i >= 0) System.arraycopy(list, i, list, i + 1, size - i);
        list[i] = element;
        size++;
    }

    public void push(E element) {
        add(size(), element);
    }

    @Override
    public E remove(int i) throws IndexOutOfBoundsException {
        sizeChecker(i, size());

        E temp = list[i];

        System.arraycopy(list, i + 1, list, i, i + 1 - i);
        size--;
        return temp;
    }


    private void sizeChecker(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) throw new IndexOutOfBoundsException("Out of bounds");
    }

    @SuppressWarnings("unchecked")
    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity];
        if (size >= 0) System.arraycopy(list, 0, temp, 0, size);
        list = temp;
    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(list[i] + " ");
        }
    }

    private class ArrayIterator implements Iterator<E> {
        private int j = 0;

        @Override
        public boolean hasNext() {
            return j < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            boolean removable = true;
            return list[j++];
        }
    }
}
