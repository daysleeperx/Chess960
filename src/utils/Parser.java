package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses user input into chess notation.
 */

public final class Parser {
    private static Map<Character, Integer> sq = new HashMap<>();

    /**
     * Class constructor.
     */
    private Parser() {

    }

    public static int[] parseInput(String input) {
        sq.put('a', 0);
        sq.put('b', 1);
        sq.put('c', 2);
        sq.put('d', 3);
        sq.put('e', 4);
        sq.put('f', 5);
        sq.put('g', 6);
        sq.put('h', 7);
        int[] out = new int[4];
        char[] inputArray = input.toCharArray();
        // if input is invalid return empty array
        if (input.length() != 4 || !sq.containsKey(input.charAt(0))
            || !sq.containsKey(input.charAt(2)) || input.charAt(1) - '0' > 8
                || input.charAt(3) - '0' > 8) return new int[0];
        out[0] = sq.get(inputArray[0]);
        out[1] = inputArray[1] - '0' - 1;
        out[2] = sq.get(inputArray[2]);
        out[3] = inputArray[3] - '0' - 1;
        return out;
    }
}
