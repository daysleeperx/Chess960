package game;

import board.Board;
import move.Move;
import pieces.Color;
import pieces.Piece;
import player.Human;
import player.Player;
import stockfish.StockFish;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    private Map<Color, Player> players = new HashMap<>();
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
    private GameStatus currentGameStatus;

    /**
     * Colors for console printing.
     */
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public void createGame() {
        // set up board
        board = new Board();

        // set up players
        players.put(Color.WHITE, new Human(Color.WHITE, this));
        players.put(Color.BLACK, new StockFish(Color.BLACK, this));

        board.setUpPieces(players.get(Color.WHITE), players.get(Color.BLACK));
        sideToMove = Color.WHITE;
        setCurrentGameStatus(GameStatus.OPEN);
    }

    public Board getBoard() {
        return board;
    }

    public GameStatus getCurrentGameStatus() {
        return currentGameStatus;
    }

    private void setCurrentGameStatus(GameStatus currentGameStatus) {
        this.currentGameStatus = currentGameStatus;
    }

    public Color getSideToMove() {
        return sideToMove;
    }

    /**
     * Check if game over: checkmate, stalemate or draw.
     * TODO: check for stalemate and draws
     *
     * @param sideToMove Color
     * @return {@code true}, otherwise {@code false}
     */
    public boolean isGameOver(Color sideToMove) {
        return board.getPossibleMoves(sideToMove).size() == 0;
    }

    /**
     * Set game status and print winner.
     *
     * @param sideToMove Color
     */
    private void announceWinner(Color sideToMove) {
        System.out.println("GAME OVER");

        switch (sideToMove) {
            case WHITE:
                setCurrentGameStatus(GameStatus.BLACK_WON);
                break;

            case BLACK:
                setCurrentGameStatus(GameStatus.WHITE_WON);
                break;
        }
        System.out.println(currentGameStatus);
    }

    /**
     * Main Game loop.
     */
    public void game() throws IOException {
//        StockFish stockFish = new StockFish();
//        stockFish.startEngine(); // Start Stockfish
        createGame();
        ((StockFish) players.get(Color.BLACK)).startEngine();
        //((StockFish) players.get(Color.WHITE)).startEngine();


        while (true) {// Main Game loop
            Player currentPlayer = players.get(sideToMove);
            // check for win
            if (isGameOver(sideToMove)) {
                board.printGame();
                announceWinner(sideToMove);
                break;
            }
            System.out.println(sideToMove.toString() + " to move");
            System.out.println(board.getPossibleMoves(sideToMove));
            board.printGame();
            // move
            String move = currentPlayer.move(board, sideToMove);
            System.out.println(ANSI_BLUE + "Current move " + move + ANSI_RESET);
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
            if (piece != null && piece.getColor() == sideToMove && board.getPossibleMoves(sideToMove).contains(move)) {
                board.movePiece(piece, targetX, targetY);
            } else {
                System.out.println(ANSI_BLUE + "\033[1mInvalid move!\033[0m" + ANSI_RESET);
                continue;
            }
            sideToMove = (sideToMove == Color.WHITE) ? Color.BLACK : Color.WHITE;
        }
    }

    public static void main(String[] args) throws IOException {
        Game g = new Game();
        g.game();
    }


}
