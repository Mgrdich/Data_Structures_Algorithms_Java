package sorting;

import adt.Position;
import adt.PositionalList;
import util.Util;

/***
 * Selection sort works in the following way<br></>
 * 1. Find the smallest element , exchange it with the first element in the array <br></>
 * 2. Find the second smallest element , exchange it with the second element in the array <br></>
 * 3. Continue in this way until the whole array is sorted <br></>
 *
 * Algorithmic Complexity and Analysis
 * best and worst case: O(n^2) does not depend on something
 * */
public class SelectionSort {
    public static void sort(Integer[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = min + 1; j < n; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            Util.swap(arr, i, min);
        }
    }


    public static void sort(PositionalList<Integer> arr) {
        int n = arr.size();

        Position<Integer> upperCursor = arr.first();
        while (n != 0) {
            Position<Integer> min = upperCursor;

            Position<Integer> embeddedCursor = arr.after(min);
            while (embeddedCursor != null) {
                if (min.getElement() > embeddedCursor.getElement()) {
                    min = embeddedCursor;
                }
                embeddedCursor = arr.after(embeddedCursor);
            }

            if (min == upperCursor) {
                upperCursor = arr.after(upperCursor);
            } else {
                // insert logic here
                arr.addBefore(upperCursor, min.getElement());
                arr.remove(min);
            }

            n--;
        }

    }
}
