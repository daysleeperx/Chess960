package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Parses user input into chess notation.
 */

public final class Parser {
    private static Map<Character, Integer> sq = new HashMap<>();
    private static Map<Integer, Character> algebraic = new HashMap<>();

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

    /**
     * Parse move to algebraic notation.
     *
     * @param piece Piece object
     * @param targetX int
     * @param targetY int
     * @return String
     */
    public static String parseToAlgebraic(int x, int y, int targetX, int targetY) {
        algebraic.put(0, 'a');
        algebraic.put(1, 'b');
        algebraic.put(2, 'c');
        algebraic.put(3, 'd');
        algebraic.put(4, 'e');
        algebraic.put(5, 'f');
        algebraic.put(6, 'g');
        algebraic.put(7, 'h');

        String out = String.valueOf(algebraic.get(x)) +
                (y + 1) +
                algebraic.get(targetX) +
                (targetY + 1);

        return out;
    }

    /**
     *
     * @param x
     * @param y
     * @param targetX
     * @param targetY
     * @return
     */
    public static String parseToAlgebraicGui(int x, int y, int targetX, int targetY) {
        algebraic.put(0, 'a');
        algebraic.put(1, 'b');
        algebraic.put(2, 'c');
        algebraic.put(3, 'd');
        algebraic.put(4, 'e');
        algebraic.put(5, 'f');
        algebraic.put(6, 'g');
        algebraic.put(7, 'h');

        String first1 = String.valueOf(algebraic.get(x));
        String second = String.valueOf(y - (y - (7 - y)) + 1);
        String third = String.valueOf(algebraic.get(targetX));
        String fourth = String.valueOf(targetY - (targetY - (7 - targetY)) + 1);
        return first1 + second + third + fourth;
    }

    public static int[] parseAlgebraicToGui(String input) {
        int[] out = new int[4];
        char[] inputArray = input.toCharArray();

        sq.put('a', 0);
        sq.put('b', 1);
        sq.put('c', 2);
        sq.put('d', 3);
        sq.put('e', 4);
        sq.put('f', 5);
        sq.put('g', 6);
        sq.put('h', 7);

        out[0] = sq.get(inputArray[0]);
        int y0 = inputArray[1] - '0';
        out[1] = y0 - (y0 - (8 - y0));
        out[2] = sq.get(inputArray[2]);
        int y1 = inputArray[3] - '0';
        out[3] = y1 - (y1 - (8 - y1));
        return out;
    }



    public static void main(String[] args) {
//        Piece pawn = new Pawn(4, 1, null);
//        System.out.println(parseToAlgebraicGui(4, 6, 4, 4));
//        System.out.println(Arrays.toString(parseInput("e2e4")));
        System.out.println(Arrays.toString(parseAlgebraicToGui("b8c6")));
    }
}
