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

import static utils.FenParser.parseToFen;
import static utils.Parser.parseInput;

/**
 * Represent Game.
 */
public class Game {
    /**
     * Game status.
     */
    public enum GameStatus {
        WHITE_WON,
        BLACK_WON,
        CHECK_MATE,
        CHECK,
        DRAW,
        OPEN
    }
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

    /**
     * Current game status.
     */
    public GameStatus currentGameStatus;

    public void createGame() {
        // set up board
        board = new Board();

        // set up players
        players[0] = new Human(Color.WHITE, this);
        players[1] = new Human(Color.BLACK, this);

        board.setUpPieces(players[0], players[1]);
        sideToMove = Color.WHITE;
        setCurrentGameStatus(GameStatus.OPEN);
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getCurrentGameStatus() {
        return currentGameStatus;
    }

    public void setCurrentGameStatus(GameStatus currentGameStatus) {
        this.currentGameStatus = currentGameStatus;
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
            String move = (sideToMove == Color.WHITE) ? sc.nextLine()
                    : stockFish.getBestMove(parseToFen(board), 5000);
            System.out.println("Current move " + move);
            int[] moveArray = parseInput(move);
            if (moveArray.length == 0) {
                System.out.println("Invalid input");
                continue;
            }
            int col = moveArray[0];
            int row = moveArray[1];
            int targetX = moveArray[2];
            int targetY = moveArray[3];
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
