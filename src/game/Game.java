package game;

import board.Board;
import move.Move;
import pieces.Color;
import pieces.Piece;
import player.Human;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static utils.Parser.parseInput;

/**
 * Represent Game.
 */
public class Game {
    /**
     * Board associated with the Game.
     */
    private Board board;
    /**
     * Players associated with the Game.
     */
    private Human[] players = new Human[2];
    /**
     * Side to move.
     */
    private int sideToMove;
    /**
     * List of moves.
     */
    private List<Move> moves = new LinkedList<>();


    public void createGame() {
        // set up board
        board = new Board();

        // set up players
        players[0] = new Human(Color.WHITE, this);
        players[1] = new Human(Color.BLACK, this);

        board.setUpPieces(players[0], players[1]);
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Main Game loop.
     */
    public void game() {
        createGame();
        while (true) { // Main Game loop
            board.printGame();
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
            Piece piece = board.boardArray[row][col].getPiece();
            if (piece != null) {
                board.movePiece(piece, targetX, targetY);
            }
        }
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.game();
    }


}
