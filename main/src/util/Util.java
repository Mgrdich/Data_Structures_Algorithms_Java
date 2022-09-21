package util;

public class Util {
    public static <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static <T> void arrayPrint(T[] arr) {
        for (T t : arr) {
            System.out.print(t + " ");
        }
    }
}
