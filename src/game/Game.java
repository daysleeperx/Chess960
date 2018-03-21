package game;

import pieces.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static game.Parser.parseInput;

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
            Piece current = b.boardArray[m[1]][m[0]].getPiece();
            b.boardArray[m[3]][m[2]].setPiece(current);
            b.boardArray[m[1]][m[0]].setPiece(null);
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.game();
    }


}
