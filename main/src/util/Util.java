package util;

public class Util {
    public static <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * a helper method does print all the elements in the array
     * Algorithmic complexity is O(n) since it iterates over the array once
     * n is the size of the array
     *
     * @param arr and array that you want to print
     * */
    public static <T> void arrayPrint(T[] arr) {
        for (T t : arr) {
            System.out.print(t + " ");
        }
    }

    public static int getMax(Integer[] arr) {
        int max = arr[0];

        for (int j : arr) {
            if (j > max) {
                max = j;
            }
        }

        return max;
    }

    public static int getMin(Integer[] arr) {
        int min = arr[0];

        for (int j : arr) {
            if (j < min) {
                min = j;
            }
        }

        return min;
    }
}
