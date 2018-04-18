package game;

import board.Board;
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
        while (true) { // Main Game loop
            b.printGame();
            Scanner sc = new Scanner(System.in);
            String in = sc.nextLine();
            if (in.equals("quit")) break;
            int[] move = parseInput(in);
            if (move.length == 0) {
                System.out.println("Invalid input");
                continue;
            }
            int col = move[0];
            int row = move[1];
            int targetX = move[2];
            int targetY = move[3];
            Piece piece = b.boardArray[row][col].getPiece();
            b.movePiece(piece, targetX, targetY);
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.game();
    }


}
