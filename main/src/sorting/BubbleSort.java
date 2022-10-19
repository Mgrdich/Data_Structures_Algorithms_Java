package sorting;

import util.Util;

/**
 * Bubble Sort will keep passing through the array until it is sorted
 * <p>
 * This with each iteration we will sort the max element to the end of our array
 * First pass largest element in array is in last place
 * Second pass second-largest element in the array is the second to last place
 * <p>
 * Worst case O(n^2)<br/>
 * Best case O(n) when sorted
 */
public class BubbleSort {
    public static void sort(Integer[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    Util.swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                // sorted
                break;
            }
        }
    }


    public static void sort() {

    }
}
