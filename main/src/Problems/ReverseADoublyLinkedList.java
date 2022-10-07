package Problems;

import data_structure.DoubleLinkedListNode;
import data_structure.DoublyLinkedList;

public class ReverseADoublyLinkedList {

    /**
     * Normal way of reversing a LinkedList without any node interference
     */
    public static <E> void reverse(DoublyLinkedList<E> linkedList) {
        // this should work with recursion
        if (linkedList.size() == 1) {
            return;
        }

        E temp = linkedList.removeLast();
        reverse(linkedList);
        linkedList.addFirst(temp);
    }


    /**
     * Reverse a Linked List using Nodes version
     */
    public static <E> void reverse(DoubleLinkedListNode<E> header, DoubleLinkedListNode<E> trailer) {
        DoubleLinkedListNode<E> element1 = header.getNext();

        DoubleLinkedListNode<E> element2 = trailer.getPrev();

        int size = getCount(header, trailer);

        int count = 0;

        while (size / 2 != count) {
            DoubleLinkedListNode<E> temp1 = element1.getNext();
            DoubleLinkedListNode<E> temp2 = element2.getPrev();

            swapNodes(element1, element2);

            element1 = temp1;
            element2 = temp2;

            count++;
        }
    }

    private static <E> int getCount(DoubleLinkedListNode<E> header, DoubleLinkedListNode<E> trailer) {
        int count = 0;
        DoubleLinkedListNode<E> curr = header.getNext();

        while (curr != trailer) {
            count++;
            curr = curr.getNext();
        }

        return count;
    }

    public static <E> void printNodes(DoubleLinkedListNode<E> header) {
        DoubleLinkedListNode<E> curr = header.getNext();

        while (curr.getElement() != null) {
            System.out.print(curr.getElement() + " ");
            curr = curr.getNext();
        }
    }

    private static <E> void swapNodes(DoubleLinkedListNode<E> node1, DoubleLinkedListNode<E> node2) {

        DoubleLinkedListNode<E> node1Prev = node1.getPrev();
        DoubleLinkedListNode<E> node2Prev = node2.getPrev();

        DoubleLinkedListNode<E> node1Next = node1.getNext();
        DoubleLinkedListNode<E> node2Next = node2.getNext();

        // neighbors
        if (node1Next == node2) {
            node1Prev.setNext(node2);
            node2.setPrev(node1Prev);

            node2.setNext(node1);
            node1.setNext(node2Next);


            node2Next.setPrev(node1);
            node1.setPrev(node2);

            return;
        }

        node1Prev.setNext(node2);
        node1Next.setPrev(node2);

        node2.setNext(node1Next);
        node2.setPrev(node1Prev);

        node2Prev.setNext(node1);
        node2Next.setPrev(node1);

        node1.setNext(node2Next);
        node1.setPrev(node2Prev);
    }
}
