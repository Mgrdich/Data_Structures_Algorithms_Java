package Problems;

import java.util.Arrays;

public class StringMatching {
    public static void main(String[] args) {
        char[] alphabet = {'a', 'b'};
        String pattern = "aabaabaaab";
        String text = "aaabaabaaabaabaaabab";

        int[][] transitionFunction = computeTransitionFunction(pattern, alphabet);
        System.out.println(Arrays.deepToString(transitionFunction).replace("], ", "]\n"));

        finiteAutomationMatching(text, transitionFunction, pattern.length(), alphabet);

        System.out.println();
    }

    public static int[][] computeTransitionFunction(String pattern, char[] alphabet) {
        int m = pattern.length();
        int[][] delta = new int[m + 1][alphabet.length];

        for (int q = 0; q <= m; q++) {
            for (int i = 0; i < alphabet.length; i++) {
                char a = alphabet[i];
                int k = Math.min(q + 2, m + 1);
                do {
                    k--;
                } while (k > 0 && !pattern.substring(0, q).concat(String.valueOf(a)).endsWith(pattern.substring(0, k)));
                delta[q][i] = k;
            }
        }

        return delta;
    }

    public static void finiteAutomationMatching(String text, int[][] delta, int m, char[] alphabet) {
        int n = text.length();
        int q = 0;

        for (int i = 0; i < n; i++) {
            char a = text.charAt(i);
            q = delta[q][getIndex(a, alphabet)];

            if (q == m) {
                System.out.println("Pattern occurs at shift " + (i - m + 1));
            }
        }
    }

    private static int getIndex(char c, char[] alphabet) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }
}
