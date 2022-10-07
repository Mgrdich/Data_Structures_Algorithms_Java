package Problems;

import adt.Stack;
import data_structure.LinkedStack;


public class ReverseStack {
    public static <E> void reverse(Stack<E> stack) {
        if (stack.size() == 1) {
            return;
        }

        E tmp = stack.pop();
        reverse(stack);
        insertAtTheBottom(stack, tmp);
    }

    public static <E> void insertAtTheBottom(Stack<E> stack, E element) {
        if (stack.size() == 0) {
            stack.push(element);
            return;
        }

        E tmp = stack.pop();
        insertAtTheBottom(stack, element);
        stack.push(tmp);
    }


    private static <E> void transfer(Stack<E> stack1, Stack<E> stack2) {

        while (stack1.size() != 0) {
            stack2.push(stack1.pop());
        }
    }

    public static <E> void reverseMemory(Stack<E> stack) {
        Stack<E> a = new LinkedStack<>();
        Stack<E> b = new LinkedStack<>();

        transfer(stack, a);
        transfer(a, b);
        transfer(b, stack);
    }
}
