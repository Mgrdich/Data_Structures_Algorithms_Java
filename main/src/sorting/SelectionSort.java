package sorting;

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
}
