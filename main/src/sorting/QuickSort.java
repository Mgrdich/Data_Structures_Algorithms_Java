package sorting;

import java.util.Arrays;

/**
 * Quick sort is a kind of recursive algorithm that rely on divide and conquer method
 * But much of the work is done in the divide rather than to conquer part
 * <p>
 * Pick a Pivot then divide into three sets
 * L elements less than x
 * E elements equal to x
 * G elements greater than x
 * <p>
 * Recursively sort L and G then Merge
 * <p>
 * in this class I will demonstrate memory optimized approach in place quick sort
 * and non optimized version just for demonstration’s sake but the public api will
 * only reveal the memory optimized one
 * <p>
 * Worst case running is where the pivot is the largest element then we will have a very unequal tree
 **/
public class QuickSort {
    public static void nonMemoryOptimizedSort(Integer[] arr) {
        int n = arr.length;

        if (n < 2) return;

        int pivot = arr[n - 1];


        // divide
        int setLLastIndex = 0;
        int setGFirstIndex = n;

        Integer[] temp = new Integer[n];
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < pivot)
                temp[setLLastIndex++] = arr[i];
            else if (pivot < arr[i])
                temp[--setGFirstIndex] = arr[i];
        }

        Integer[] L = Arrays.copyOfRange(temp, 0, setLLastIndex);

        Integer[] E = new Integer[setGFirstIndex - setLLastIndex];
        Arrays.fill(E, pivot);

        Integer[] G = Arrays.copyOfRange(temp, setGFirstIndex, n);

        // conquer
        nonMemoryOptimizedSort(L);
        nonMemoryOptimizedSort(G);

        // concatenate
        System.arraycopy(L, 0, arr, 0, setLLastIndex);
        System.arraycopy(E, 0, arr, setLLastIndex, setGFirstIndex - setLLastIndex);
        System.arraycopy(G, 0, arr, setGFirstIndex, n - setGFirstIndex);
    }

}
