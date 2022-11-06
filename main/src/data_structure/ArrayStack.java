package data_structure;

import adt.Stack;

public class ArrayStack<E> implements Stack<E> {
    final static public int CAPACITY = 1000;
    private final E[] stack;
    int pointer = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        stack = (E[]) new Object[capacity];
    }

    @Override
    public void push(E e) {
        pointer++;
        if (pointer == stack.length) {
            throw new IndexOutOfBoundsException("Memory out of bound");
        }

        stack[pointer] = e;
    }

    @Override
    public E pop() {
        if (isEmpty()) return null;
        E temp = stack[pointer];

        stack[pointer] = null;
        pointer--;
        return temp;
    }

    @Override
    public E top() {
        if (isEmpty()) return null;

        return stack[pointer];
    }

    @Override
    public int size() {
        return pointer + 1;
    }

    @Override
    public boolean isEmpty() {
        return pointer == -1;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = pointer; i >= 0; i--) {
            stringBuilder.append(stack[i]);
            if (i != 0) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
