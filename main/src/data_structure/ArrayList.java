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

    @Override
    public void add(E element) {
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

    @Override
    public E remove() throws IndexOutOfBoundsException {
        return remove(size() - 1);
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("(");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(list[i]);
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<E> {
        private int j = 0;
        private boolean removable = false;

        @Override
        public boolean hasNext() {
            return j < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true;
            return list[j++];
        }

        public void remove() throws IllegalStateException {
            if (!removable) throw new IllegalStateException("nothing to remove");
            ArrayList.this.remove(j - 1);
            j--;
            removable = false;
        }
    }
}
