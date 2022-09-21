package sorting;

/**
 * The insertion sort proceed by considering one element at a time
 * and putting it in the right order with respect those that are before it <br/>
 * <p>
 * 1. First element is trivially sorted <br/>
 * 2. Second element : if it is smaller than the first one swap <br/>
 * 3. Third element: swap it leftward until it is in the correct position
 */
public class InsertionSort {

    public static void sort(Integer[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int curr = arr[i];
            int j = i;

            while (j > 0 && arr[j - 1] > curr) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = curr;
        }
    }
}
