package game;

import pieces.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represent Game.
 */
public class Game {
    /**
     * Main Game loop.
     */
    public void game() {
        Board b = new Board();
        b.setUpPieces();
        Map<Character, Integer> sq = new HashMap<>();
        sq.put('a', 0);
        sq.put('b', 1);
        sq.put('c', 2);
        sq.put('d', 3);
        sq.put('e', 4);
        sq.put('f', 5);
        sq.put('g', 6);
        sq.put('h', 7);
        while (true) {
            b.printGame();
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            if (in.equals("quit")) break;
            char[] inputArray = in.toCharArray();
            int col = sq.get(inputArray[0]);
            int row = inputArray[1] - '0' - 1;
            int row2 = inputArray[3] - '0' - 1;
            int col2 = sq.get(inputArray[2]);
            Piece current = b.boardArray[row][col].getPiece();
            b.boardArray[row2][col2].setPiece(current);
            b.boardArray[row][col].setPiece(null);


        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.game();
    }


}
