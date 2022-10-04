import data_structure.*;

public class Test {
    public static void main(String[] args) {
        ArrayQueue<Integer> st = new ArrayQueue<>();
        LinkedQueue<Integer> stl = new LinkedQueue<>();

        st.enqueue(2);
        stl.enqueue(2);
        st.enqueue(3);
        stl.enqueue(3);
        st.enqueue(4);
        stl.enqueue(4);

        st.print();
        stl.print();

        System.out.println(st.first());
        System.out.println(stl.first());

        System.out.println(st.dequeue());
        System.out.println(stl.dequeue());

        System.out.println(st.first());
        System.out.println(stl.first());

        System.out.println(st.dequeue());
        System.out.println(stl.dequeue());



    }
}
