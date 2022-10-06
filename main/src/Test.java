import adt.Deque;
import data_structure.*;

public class Test {
    public static void main(String[] args) {
//        Deque<Integer> dq = new ArrayDeque<>();
//        Deque<Integer> dq1 = new LinkedDeque<>();

//        dq.addFirst(1);
//        dq.addLast(2);
//        dq.addFirst(0);
//
//        dq1.addFirst(1);
//        dq1.addLast(2);
//        dq1.addFirst(0);
//
//        System.out.println(dq.first());
//        System.out.println(dq1.first());
//        System.out.println("--");
//        System.out.println(dq.last());
//        System.out.println(dq1.last());
//        System.out.println("--");
//
//        System.out.println(dq.removeFirst());
//        System.out.println(dq1.removeFirst());
//        System.out.println("--");
//
//        System.out.println(dq.first());
//        System.out.println(dq1.first());
//
//        System.out.println("--");
//        System.out.println(dq.last());
//        System.out.println(dq1.last());
//        System.out.println("--");
//
//        System.out.println("--");
//        System.out.println(dq.removeLast());
//        System.out.println(dq1.removeLast());
//        System.out.println("--");
//        System.out.println(dq.removeLast());
//        System.out.println(dq1.removeLast());
//        System.out.println("--");
//        System.out.println(dq.removeLast());
//        System.out.println(dq1.removeLast());
//        System.out.println("--");
//
//        dq.addFirst(1);
//        dq1.addFirst(1);
//        System.out.println(dq.first());
//        System.out.println(dq1.first());
//        dq.addLast(2);
//        dq1.addLast(2);
//        dq.addLast(2);
//        dq1.addLast(2);
//        System.out.println("--");
//        System.out.println(dq.last());
//        System.out.println(dq1.last());


//        dq.addLast(5);
//        dq1.addLast(5);
//
//        dq.addFirst(3);
//        dq1.addFirst(3);
//
//        dq.addFirst(7);
//        dq1.addFirst(7);
//
//        System.out.println("--");
//        System.out.println(dq.first() + " " + dq1.first());
//
//        System.out.println("--");
//        System.out.println(dq.removeLast() + " " + dq1.removeLast());
//
//        System.out.println("--");
//        System.out.println(dq.size() + " " + dq1.size());
//
//        System.out.println("--");
//        System.out.println(dq.removeLast() + " " + dq1.removeLast());
//
//        System.out.println("--");
//        System.out.println(dq.removeFirst() + " " + dq1.removeFirst());
//
//        System.out.println("--");
//        dq.addFirst(6);
//        dq1.addFirst(6);
//        System.out.println(dq.last() + " " + dq1.last());
//
//        System.out.println("--");
//        dq.addFirst(8);
//        dq1.addFirst(8);
//        System.out.println(dq.isEmpty() + " " + dq1.isEmpty());
//
//        System.out.println("--");
//        System.out.println(dq.last() + " " + dq1.last());


        CircularlyDoublyLinkedList<Integer> db = new CircularlyDoublyLinkedList<>();

        db.addFirst(1);
        db.addLast(2);
        db.addLast(3);
        db.addLast(4);

        db.print();
        db.rotate();
        db.print();
        System.out.println(db.first());
        System.out.println(db.last());

        db.removeFirst();
        db.print();
        db.removeLast();
        db.print();
    }
}
