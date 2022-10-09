import Problems.ReverseADoublyLinkedList;
import Problems.ReverseQueue;
import Problems.ReverseStack;
import adt.*;
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


//        CircularlyDoublyLinkedList<Integer> db = new CircularlyDoublyLinkedList<>();
//
//        db.addFirst(1);
//        db.addLast(2);
//        db.addLast(3);
//        db.addLast(4);
//
//        db.print();
//        db.rotate();
//        db.print();
//        System.out.println(db.first());
//        System.out.println(db.last());
//
//        db.removeFirst();
//        db.print();
//        db.removeLast();
//        db.print();

//        DoublyLinkedList<Integer> db = new DoublyLinkedList<>();
//
//        db.addFirst(1);
//        db.addLast(2);
//        db.addLast(3);
//        db.addLast(4);
//        db.addLast(5);
//        db.addLast(6);
//
//        db.print();
//
//        ReverseADoublyLinkedList.reverse(db);
//
//        db.print();


//        DoubleLinkedListNode<Integer> header = new DoubleLinkedListNode<>(null, null, null);
//
//        DoubleLinkedListNode<Integer> node2 = new DoubleLinkedListNode<>(6, header, null);
//        header.setNext(node2);
//
//        DoubleLinkedListNode<Integer> node3 = new DoubleLinkedListNode<>(8, node2, null);
//        node2.setNext(node3);
//
//        DoubleLinkedListNode<Integer> node4 = new DoubleLinkedListNode<>(10, node3, null);
//        node3.setNext(node4);
//
//        DoubleLinkedListNode<Integer> node5 = new DoubleLinkedListNode<>(12, node4, null);
//        node4.setNext(node5);
//
//        DoubleLinkedListNode<Integer> node6 = new DoubleLinkedListNode<>(14, node5, null);
//        node5.setNext(node6);
//
//        DoubleLinkedListNode<Integer> trailer = new DoubleLinkedListNode<>(null, node6, null);
//        node6.setNext(trailer);
//
//        ReverseADoublyLinkedList.printNodes(header);
//
////        ReverseADoublyLinkedList.swapNodes(node2, node3);
//        System.out.println("");
//
//
//
//        ReverseADoublyLinkedList.reverse(header, trailer);
//        ReverseADoublyLinkedList.printNodes(header);


//        PrintableQueue<Integer> e = new LinkedQueue<>();
//
//        e.enqueue(1);
//        e.enqueue(2);
//        e.enqueue(3);
//        e.enqueue(4);
//
//        e.print();
//        PrintableQueue<Integer> qq = ReverseQueue.getReversed(e);
//        qq.print();

//        PrintableStack<Integer> st = new LinkedStack<>();
//        st.push(1);
//        st.push(2);
//        st.push(3);
//        st.push(4);
//
//        st.print();
////        ReverseStack.reverse(st);
//
//        ReverseStack.reverseMemory(st);
//
//        st.print();


        ArrayList<Integer> ar = new ArrayList<>();

        ar.add(0,1);
        ar.add(1,2);
        ar.add(2,2);
        ar.push(4);

        ar.print();
    }
}
