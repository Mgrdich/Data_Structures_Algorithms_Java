package data_structure;

import adt.List;

public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 16;
    private final E[] list;
    private int size = 0;

    public ArrayList() {
        this(CAPACITY);
    }

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

        for (int j = size - 1; j >= i; j--) {
            list[j + 1] = list[j];
        }
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

        for (int j = i; j >= i; j--) {
            list[j] = list[j + 1];
        }
        size--;
        return temp;
    }


    private void sizeChecker(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n) throw new IndexOutOfBoundsException("Out of bounds");
    }

    protected void resize(int capacity) {

    }

    public void print() {
        for (int i = 0; i < size(); i++) {
            System.out.print(list[i] + " ");
        }
    }
}
