package Problems;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringMatching {
    public static void main(String[] args) {
        String pattern = "aabaabaaab";
        String text = "aaabaabaaabaabaaabab";

        Map<Character, Integer> alphabet = createAlphabet(new char[]{'a', 'b'});

        System.out.println("text: "+ text);
        System.out.println("pattern: "+ pattern);
        int[][] transitionFunction = computeTransitionFunction(pattern, alphabet);
        System.out.println(Arrays.deepToString(transitionFunction).replace("], ", "]\n"));

        finiteAutomatonMatching(text, transitionFunction, pattern.length(), alphabet);

        System.out.println();
    }

    public static int[][] computeTransitionFunction(String pattern, Map<Character, Integer> alphabet) {
        int m = pattern.length();
        int[][] delta = new int[m + 1][alphabet.size()];

        for (int q = 0; q <= m; q++) {
            for (Map.Entry<Character, Integer> entry : alphabet.entrySet()) {
                char a = entry.getKey();
                int k = Math.min(q + 2, m + 1);
                do {
                    k--;
                } while (k > 0 && !pattern.substring(0, q).concat(String.valueOf(a)).endsWith(pattern.substring(0, k)));
                delta[q][entry.getValue()] = k;
            }
        }

        return delta;
    }

    public static void finiteAutomatonMatching(String text, int[][] delta, int m, Map<Character, Integer> alphabet) {
        int n = text.length();
        int q = 0;

        for (int i = 0; i < n; i++) {
            char a = text.charAt(i);
            q = delta[q][alphabet.get(a)];

            if (q == m) {
                System.out.println("Pattern occurs at shift " + (i - m + 1));
            }
        }
    }

    private static Map<Character, Integer> createAlphabet(char[] alphabet) {
        Map<Character, Integer> alphabetMapped = new HashMap<>(alphabet.length);

        for (int i = 0; i < alphabet.length; i++) {
            alphabetMapped.put(alphabet[i], i);
        }
        return alphabetMapped;
    }
}
