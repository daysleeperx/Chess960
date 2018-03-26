package game;

import pieces.Piece;

import java.util.Scanner;

import static utils.Parser.parseInput;

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
        while (true) {
            b.printGame();
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            if (in.equals("quit")) break;
            int[] m = parseInput(in);
            int col = m[0];
            int row = m[1];
            int col2 = m[2];
            int row2 = m[3];
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
