import Problems.HanoiTower;
import Problems.ReverseADoublyLinkedList;
import Problems.ReverseQueue;
import Problems.ReverseStack;
import adt.*;
import data_structure.*;
import sorting.BubbleSort;
import sorting.InsertionSort;
import sorting.SelectionSort;
import util.Util;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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


//        ArrayList<Integer> ar = new ArrayList<>();
//
//        ar.add(0,1);
//        ar.add(1,2);
//        ar.add(2,2);
//        ar.push(4);
//
//        ar.print();
//
//        PositionalList<Integer> arr = new LinkedPositionalList<>();
//        Position<Integer> p = arr.addLast(8);
//        System.out.println(arr.first() == p);
//        Position<Integer> q = arr.addAfter(p, 5);
//        System.out.println(arr.before(q) == p);
//        Position<Integer> r = arr.addBefore(q, 3);
//        System.out.println(r.getElement());
//        System.out.println(arr.after(p) == r);
//        System.out.println(arr.before(p) == null);
//        Position<Integer> s = arr.addFirst(9);
//        System.out.println(arr.remove(arr.last()));
//
//        System.out.println(arr.set(p, 7));
//        Util.printPositionalList(arr);
//        System.out.println();
//        arr.remove(q);
//        Util.printPositionalList(arr);


//        PositionalList<Integer> arr = new LinkedPositionalList<>();
//
//
//        arr.addFirst(4);
//        arr.addFirst(2);
//        arr.addFirst(5);
//        arr.addFirst(11);
//        arr.addFirst(22);
//        arr.addFirst(1);
//        arr.addFirst(32);
//        Util.printPositionalList(arr);
//
//
//        InsertionSort.sort(arr);
//        System.out.println();
//        Util.printPositionalList(arr);
//        System.out.println();
//
//
//
//        PositionalList<Integer> arr1 = new LinkedPositionalList<>();
//        arr1.addLast(1);
//        arr1.addLast(2);
//        arr1.addLast(3);
//        Util.printPositionalList(arr1);
//        System.out.println();
//        InsertionSort.sort(arr1);
//        Util.printPositionalList(arr1);


//        DoublyLinkedListExtended<Integer> list = new DoublyLinkedListExtended<>();
//        list.addLast(1);
//        list.addLast(2);
//        list.addLast(3);
//        list.addLast(4);
//        list.addLast(5);
//        System.out.println(list);
//
//        ListIterator<Integer> it = list.iterator();
//
//        System.out.println("index=" + it.previousIndex());
//        System.out.println(it.hasPrevious());
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println("index=" + it.nextIndex());
//        System.out.println("index=" + it.previousIndex());
//        it.set(66);
//        System.out.println(list); // shows 66 override 2
//        System.out.println(it.previous());
//        it.add(67);
//        System.out.println(list);
//        System.out.println(it.previous()); // the added element
//        it.set(69);
//        System.out.println(list);
//        System.out.println(it.previous());
//        System.out.println(it.hasPrevious());
////
//        System.out.println(it.next());
//        System.out.println(it.next());
//        it.remove();
//        System.out.println(list);
//
//        try {
//            it.remove();
//        } catch (IllegalStateException err) {
//            System.out.println("neither next nor previous is called");
//        }
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.next());
//        System.out.println(it.next());
//
//        System.out.println(it.hasNext());
//        System.out.println("next index=" + it.nextIndex());
//
//        try {
//            it.next();
//        } catch (NoSuchElementException err) {
//            System.out.println("no such elements");
//        }
//
//        System.out.println(it.previous());
//        it.set(666);
//        System.out.println(list);
//        it.remove();
//        System.out.println(list);
//        it.add(6666);
//        System.out.println(list);
//
//        System.out.println(list);
//        ListIterator<Integer> it2 = list.iterator();
//        while (it2.hasNext()) {
//            System.out.print(it2.next() + " ");
//        }
//
//        System.out.println();
//        while (it2.hasPrevious()) {
//            System.out.print(it2.previous() + " ");
//        }

//        HanoiTower.show(4,"0","1","2");
//        System.out.println();
//        System.out.println(HanoiTower.printNthMove(4,"0","1","2",4));


//        StackPriorityQueue<Integer, Integer> pqueue = new StackPriorityQueue<>();
//
//        pqueue.insert(0, 0);
//        pqueue.insert(2, 2);
//        Entry<Integer,Integer> ss1 = pqueue.insert(-1, -1);
//        System.out.println(ss1.getKey());
//        pqueue.insert(3, 3);
//        Entry<Integer,Integer> ss = pqueue.insert(1, 1);
//        System.out.println(ss.getKey());
//
//        System.out.println(pqueue.size());
//        System.out.println(pqueue.min().getKey());
//        System.out.println(pqueue.removeMin().getKey());
//        System.out.println(pqueue.removeMin().getKey());
//        System.out.println(pqueue.removeMin().getKey());
//        System.out.println(pqueue.removeMin().getKey());
//        System.out.println(pqueue.min().getKey());
//        System.out.println(pqueue.removeMin().getKey());
//        System.out.println(pqueue.isEmpty());


//        Queue<Integer> q = new LinkedQueue<>();
//        q.enqueue(1);
//        q.enqueue(2);
//        q.enqueue(3);
//        q.enqueue(4);
//        q.enqueue(5);
//        q.dequeue();
//        q.enqueue(6);
//        q.dequeue();
//        q.enqueue(7);
//
//        System.out.println(q);

//        int[] arr = {-1,0,3,5,9,12};
//        int[] arr1 = {2};
//        System.out.println(Util.binarySearch(arr1,1));


//

        BSTMap<Integer, Integer> map = new BSTMap<>();

        map.put(6, 6);
        map.put(9, 9);
        map.put(2, 2);
        map.put(1, 1);
        map.put(8, 8);
        map.put(5, 5);
        System.out.println(map);
        map.remove(5);
    }
}
