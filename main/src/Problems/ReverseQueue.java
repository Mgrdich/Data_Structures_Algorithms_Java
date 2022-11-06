package Problems;

import adt.Queue;
import data_structure.LinkedQueue;

public class ReverseQueue {
    public static <E> void reverse(Queue<E> queue) {
        if (queue.size() == 1) {
            return;
        }

        E temp = queue.dequeue();
        reverse(queue);
        queue.enqueue(temp);
    }


    public static <E> Queue<E> getReversed(Queue<E> queue) {
        Queue<E> interQueue = new LinkedQueue<>();
        Queue<E> finalQueue = new LinkedQueue<>();

        while (queue.size() > 0) {

            int count = queue.size() - 1;

            for (int i = 0; i < count; i++) {
                interQueue.enqueue(queue.dequeue());
            }

            finalQueue.enqueue(queue.dequeue());

            Queue<E> temp = queue;
            queue = interQueue;
            interQueue = temp;
        }

        return finalQueue;
    }
}
