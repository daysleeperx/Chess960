package game;

import board.Board;
import org.junit.Before;
import org.junit.Test;
import pieces.Color;

import static org.junit.Assert.*;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void createGame() {
        game.createGame();
        assertTrue(game.getCurrentGameStatus() == Game.GameStatus.OPEN);
        assertTrue(game.getSideToMove() == Color.WHITE);
    }

    @Test
    public void isGameOver() {
        game.createGame();
        Board board = game.getBoard();
        board.setNewPiecePosition(board.getSquare(1, 7).getPiece(), 6, 1);
        board.printGame();

    }
}