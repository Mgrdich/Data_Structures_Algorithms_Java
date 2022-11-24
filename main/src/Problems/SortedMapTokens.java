package Problems;

import adt.Entry;
import adt.PriorityQueue;
import data_structure.HeapPriorityQueue;
import data_structure.SortedPriorityQueue;
import data_structure.UnsortedPriorityQueue;

import java.util.HashMap;
import java.util.StringTokenizer;

public class SortedMapTokens {
    public static void main(String[] args) {
        String[] input = {"Lab002", "mgos", "boghos", "kskww%%%00000"};
        printTokens(input, new SortedPriorityQueue<>());
        System.out.println();
        printTokens(input, new UnsortedPriorityQueue<>());
        System.out.println();
        printTokens(input, new HeapPriorityQueue<>());
    }


    public static void printTokens(String[] arr, PriorityQueue<Integer, String> priorityQueue) {
        HashMap<Integer, String> map = new HashMap<>();

        for (String curr : arr) {
            int length = countLatinLetters(curr);

            if (map.containsKey(length)) continue;

            map.put(length, curr);
            priorityQueue.insert(length, curr);
        }

        while (!priorityQueue.isEmpty()) {
            Entry<Integer, String> word = priorityQueue.removeMin();
            System.out.println(word.getValue() + " ( " + word.getKey() + " letters )");
        }
    }

    public static void printTokens(String str, PriorityQueue<Integer, String> priorityQueue) {
        HashMap<Integer, String> map = new HashMap<>();

        StringTokenizer st = new StringTokenizer(str, " 0123456789");

        // depends on the tokens length
        while (st.hasMoreElements()) {
            String curr = st.nextToken();
            int length = curr.length();

            if (map.containsKey(length)) continue;

            map.put(length, curr);
            priorityQueue.insert(length, curr);
        }

        while (!priorityQueue.isEmpty()) {
            Entry<Integer, String> word = priorityQueue.removeMin();
            System.out.println("Word: " + word.getValue() + " letter:" + word.getKey());
        }
    }


    private static int countLatinLetters(String str) {
        int i = 0;
        int n = str.length();
        for (int j = 0; j < n; j++) {
            char c = str.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) i++;
        }
        return i;
    }
}
