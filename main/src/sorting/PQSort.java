package sorting;

import adt.PositionalList;
import adt.PriorityQueue;

public class PQSort {
    public static <E> void sort(PositionalList<E> positionalList, PriorityQueue<E, ?> priorityQueue) {
        int n = positionalList.size();
        for (int i = 0; i < n; i++) {
            E element = positionalList.remove(positionalList.first());
            priorityQueue.insert(element, null);
        }

        for (int i = 0; i < n; i++) {
            E element = priorityQueue.removeMin().getKey();
            positionalList.addLast(element);
        }
    }
}
