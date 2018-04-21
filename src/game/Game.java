package game;

import board.Board;
import move.Move;
import pieces.Color;
import pieces.Piece;
import player.Human;
import stockfish.StockFish;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static utils.Parser.parseInput;
import static utils.FenParser.parseToFen;

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
    private Color sideToMove;
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
        sideToMove = Color.WHITE;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Main Game loop.
     */
    public void game() throws IOException {
        StockFish stockFish = new StockFish();
        stockFish.startEngine(); // Start Stockfish
        createGame();
        while (true) { // Main Game loop
            System.out.println(sideToMove.toString() + " to move");
            board.printGame();
            Scanner sc = new Scanner(System.in);
            int[] move = (sideToMove == Color.WHITE) ? parseInput(sc.nextLine()) : parseInput(stockFish.getBestMove(parseToFen(board.getBoardArray()), 5000));
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
                // TODO: do not switch sides if invalid move
            }
            sideToMove = (sideToMove == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
    }

    public static void main(String[] args) throws IOException {
        Game g = new Game();
        g.game();
    }


}
