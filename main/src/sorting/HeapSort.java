package sorting;

import util.Util;

public class HeapSort {
    public static void main(String[] args) {
        Integer[] arr = {3, 7, 2, 1, 4};
        Integer[] arr2 = {3, 7, 2, 1, 4};
        Integer[] arr3 = {16, 4, 7, 1, 12, 19};

        sort(arr);
        Util.arrayPrint(arr);
    }

    public static void sort(Integer[] arr) {
        bottomUpHeap(arr);
        int heapEndIndex = arr.length - 1;
        while (heapEndIndex > 0) {
            Util.swap(arr, 0, heapEndIndex);
            downHeapForMaxHeap(arr, 0, heapEndIndex);
            heapEndIndex--;
        }
    }

    public static void makeTopDownHeap(Integer[] arr) {
        int cursor = 1;
        while (cursor != arr.length) {
            upHeapForMaxHeap(arr, cursor);
            cursor++;
        }
    }

    public static void bottomUpHeap(Integer[] arr) {
        int parentIndex = parent(arr.length - 1);
        for (int i = parentIndex; i >= 0; i--) {
            downHeapForMaxHeap(arr, i, arr.length);
        }
    }

    private static void downHeapForMaxHeap(Integer[] arr, int j, int size) {
        while (hasLeft(j, size)) {
            int leftIndex = left(j);
            int largestIndex = leftIndex;
            if (hasRight(j, size)) {
                int rightIndex = right(j);
                if (arr[leftIndex] < arr[rightIndex]) {
                    largestIndex = rightIndex;
                }
            }

            if (arr[largestIndex] <= arr[j]) break;

            Util.swap(arr, largestIndex, j);
            j = largestIndex;
        }
    }

    private static void upHeapForMaxHeap(Integer[] arr, int j) {
        while (j > 0) {
            int parentIndex = parent(j);
            if (arr[j] <= arr[parentIndex]) break;

            Util.swap(arr, j, parentIndex);
            j = parentIndex;
        }
    }

    private static boolean hasLeft(int i, int size) {
        return left(i) < size;
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    private static boolean hasRight(int i, int size) {
        return right(i) < size;
    }

    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }
}
