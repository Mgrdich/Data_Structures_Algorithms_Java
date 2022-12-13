package Problems;

import data_structure.SinglyLinkedListNode;

public class SingleLinkedListRemovalEnd {
    public static void main(String[] args) {
        SinglyLinkedListNode<Integer> head = new SinglyLinkedListNode<>(1, null);
        SinglyLinkedListNode<Integer> f = new SinglyLinkedListNode<>(2, null);
        SinglyLinkedListNode<Integer> s = new SinglyLinkedListNode<>(3, null);
        SinglyLinkedListNode<Integer> t = new SinglyLinkedListNode<>(4, null);
        head.setNext(f);
        f.setNext(s);
        s.setNext(t);


        SinglyLinkedListNode<Integer> curr = head;
        while (curr != null) {
            System.out.print(curr.getElement() + " ");
            curr = curr.getNext();
        }


        System.out.println();

        SinglyLinkedListNode<Integer> currZ = removalNthFromEnd(head, 3);
        while (currZ != null) {
            System.out.print(currZ.getElement() + " ");
            currZ = currZ.getNext();
        }
    }


    public static SinglyLinkedListNode<Integer> removalNthFromEnd(SinglyLinkedListNode<Integer> head, int n) {
        int size = calculateSize(head);
        int at = size - n - 1;
        SinglyLinkedListNode<Integer> currentHead = head;


        if (at >= 0) {
            int i = 0;
            SinglyLinkedListNode<Integer> beforeCursor = currentHead;
            while (at != i) {
                i++;
                beforeCursor = beforeCursor.getNext();
            }


            SinglyLinkedListNode<Integer> next = beforeCursor.getNext();

            if (next != null) {
                beforeCursor.setNext(next.getNext());
                next.setNext(null);
            } else {
                beforeCursor.setNext(null);
            }

        } else {
            currentHead = head.getNext();
            head.setNext(null);
        }


        return currentHead;
    }

    public static int calculateSize(SinglyLinkedListNode<Integer> head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.getNext();
        }
        return size;
    }
}
