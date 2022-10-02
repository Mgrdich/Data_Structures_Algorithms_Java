import data_structure.ArrayStack;
import data_structure.DoublyLinkedList;
import data_structure.LinkedStack;
import data_structure.Stack;

public class Test {
    public static void main(String[] args) {
        ArrayStack<Integer> st = new ArrayStack<>();
        LinkedStack<Integer> stl = new LinkedStack<>();

        st.push(2);
        stl.push(2);
        st.push(3);
        stl.push(3);
        st.push(4);
        stl.push(4);

        st.print();
        stl.print();
        System.out.println(st.top());
        System.out.println(stl.top());

        System.out.println(st.pop());
        System.out.println(stl.pop());
    }
}
