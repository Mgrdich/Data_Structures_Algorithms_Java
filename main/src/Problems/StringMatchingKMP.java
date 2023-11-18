package Problems;

import java.util.Arrays;

public class StringMatchingKMP {
    public static void main(String[] args) {
        String pattern = "aabaabaaab";
        String text = "aaabaabaaabaabaaabab";

        System.out.println("Prefix function Results: " + Arrays.toString(computePrefixFunction(pattern)));
        kmpMatch(text, pattern);
    }

    public static int[] computePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        pi[0] = 0;
        int k = 0;

        for (int i = 1; i < m; i++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(i)) {
                k = pi[k - 1];
            }
            if (pattern.charAt(k) == pattern.charAt(i)) {
                k++;
            }
            pi[i] = k;
        }

        return pi;
    }

    public static void kmpMatch(String text, String pattern) {
        kmpMatchCore(text, pattern, computePrefixFunction(pattern));
    }

    private static void kmpMatchCore(String text, String pattern, int[] pi) {
        int n = text.length();
        int m = pattern.length();
        int current = 0;

        for (int i = 0; i < n; i++) {
            while (current > 0 && pattern.charAt(current) != text.charAt(i)) {
                current = pi[current - 1];
            }
            if (pattern.charAt(current) == text.charAt(i)) {
                current++;
            }
            if (current == m) {
                System.out.println("Pattern occurs with shift " + (i - m + 1));
                current = pi[current - 1];
            }
        }
    }
}
