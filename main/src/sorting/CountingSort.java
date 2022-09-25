package sorting;

import util.Util;

public class CountingSort {
    public static void sort(Integer[] arr) {
        int max = Util.getMax(arr);

        int[] newArray = new int[max + 1];

        for (int j : arr) {
            newArray[j]++;
        }

        for (int i = 0; i < newArray.length; i++) {
            int count = newArray[i];
            int pushIndex = 0;
            int j = 0;
            while (j < count) {
                arr[pushIndex] = i;
                j++;
                pushIndex++;
            }
        }
    }
}
