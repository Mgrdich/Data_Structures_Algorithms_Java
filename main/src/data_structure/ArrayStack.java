package data_structure;

import adt.Stack;

public class ArrayStack<E> implements Stack<E> {
    final static public int CAPACITY = 1000;
    private final E[] stack;
    int pointer = -1;

    public ArrayStack() {
        this(CAPACITY);
    }

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


    /**
     * This print prints from the top accessible element to bottom
     * cause that is the access structure
     * */
    public void print() {
        for (int i = pointer; i >= 0; i--) {
            System.out.print(stack[i]+ " ");
        }
        System.out.println();
    }
}
