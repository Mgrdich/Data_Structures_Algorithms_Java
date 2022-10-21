package sorting;

import adt.Position;
import adt.PositionalList;

/**
 * The insertion sort proceed by considering one element at a time
 * and putting it in the right order with respect those that are before it <br/>
 * <p>
 * 1. First element is trivially sorted <br/>
 * 2. Second element : if it is smaller than the first one swap <br/>
 * 3. Third element: swap it leftward until it is in the correct position
 * <p>
 * worst case:  O(n^2)
 * best case:  when the array is sorted O(n)
 */
public class InsertionSort {

    public static void sort(Integer[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int curr = arr[i];
            int j = i;

            while (j > 0 && arr[j - 1] > curr) {
                // more of a shifting than swapping much more efficient
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = curr;
        }
    }

    public static void sort(PositionalList<Integer> arr) {

        Position<Integer> upperCursor = arr.after(arr.first());
        while (upperCursor != null) {
            Position<Integer> embeddedCursor = upperCursor;
            boolean swapped = false;

            while (embeddedCursor != arr.first() && arr.before(embeddedCursor).getElement() > upperCursor.getElement()) {
                embeddedCursor = arr.before(embeddedCursor);
                swapped = true;
            }

            if (swapped) {
                arr.addBefore(embeddedCursor, upperCursor.getElement());
                Position<Integer> deleted = upperCursor;
                upperCursor = arr.after(upperCursor);
                arr.remove(deleted);
            } else {
                upperCursor = arr.after(upperCursor);
            }

        }
    }
}
