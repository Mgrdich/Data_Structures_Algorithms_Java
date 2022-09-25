import data_structure.CircularlyLinkedList;
import data_structure.SinglyLinkedList;

public class Test {
    public static void main(String[] args) {
        CircularlyLinkedList<Integer> list = new CircularlyLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(4);
        list.print();

        list.rotate();
        list.print();

        list.removeFirst();
        list.print();
    }
}
