package Problems;

import adt.Stack;


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
        if(stack.size() == 0) {
            stack.push(element);
            return;
        }

        E tmp = stack.pop();
        insertAtTheBottom(stack, element);
        stack.push(tmp);
    }
}
