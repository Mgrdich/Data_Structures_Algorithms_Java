package Problems;

import adt.Queue;

public class ReverseQueue {
    public static <E> void reverse(Queue<E> queue) {
        if (queue.size() == 1) {
            return;
        }

        E temp = queue.dequeue();
        reverse(queue);
        queue.enqueue(temp);
    }
}
