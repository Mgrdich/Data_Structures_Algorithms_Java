package sorting;

import data_structure.ArrayList;

public class BucketSort {
    public static void sort(int[] arr) {

    }
    @SuppressWarnings("unchecked")
    public static void sort(ArrayList<Integer> arr) {

        ArrayList<Integer>[] collection = new ArrayList[1000];

        for (Integer j : arr) { // O(n)
            int index = Math.abs(j);

            if (collection[index] == null) {
                collection[index] = new ArrayList<>();// O(capacity)
            }
            push(collection[index], j); //O(1)
        }

        int j = 0;
        for (ArrayList<Integer> currList : collection) { //O(1000)
            if (currList == null) continue;

            while (currList.size() != 0) { // worst case O(n)
                arr.set(j, pop(currList)); // O(1)
                j++;
            }
        }

        //whole O(n) + O(d*n) where d is the bucket quantity here O(n) + O(1000*n) hence O(n)
    }

    /**
     * Helper methods that adds element to the end of the array list
     * Algorithmic complexity time is O(1) in most cases and O(n) during resize
     * but on average it will end-up O(1) constant time
     * Space-complexity: O(1) since it does not create new array on average just during resize just uses the same
     * array underneath
     */
    private static <E> void push(ArrayList<E> list, E element) {
        list.add(list.size(), element);
    }

    /**
     * Helper methods that removes element to the array list
     * Time-complexity: for the inner method is O(n) but since we are removing last element it will be O(1)
     * Space-complexity: O(1) since it does not create new array just the same array underneath
     */
    private static <E> E pop(ArrayList<E> list) {
        return list.remove(list.size() - 1);
    }
}
