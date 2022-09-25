package sorting;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * It uses recursion, and it divides to conquer the sorting
 * its running time is O(nlog(n)) for all the cases
 *
 * During the execution it creates a binary tree
 * */
public class MergeSort {
    public static void sort(Integer[] arr) {
        int n = arr.length;

        if (n < 2) return;

        // divide
        int mid = n / 2;

        Integer[] arr1 = Arrays.copyOfRange(arr, 0, mid);
        Integer[] arr2 = Arrays.copyOfRange(arr, mid, n);

        // conquer
        sort(arr1);
        sort(arr2);

        merge(arr1, arr2, arr);
    }

    private static void merge(Integer[] arr1, Integer[] arr2, Integer[] arr) {
        if (arr1.length + arr2.length != arr.length) {
            throw new InvalidParameterException("the parameters length are not equivalent");
        }

        int j = 0;
        int i = 0;

        while (i + j < arr.length) {
            // the equality does what is supposed to do where with this equality opposite is to put elements from j
            if (j == arr2.length || (i < arr1.length && arr1[i] < arr2[j])) {
                arr[i + j] = arr1[i++];
            } else {
                arr[i + j] = arr2[j++];
            }
        }
    }
}
