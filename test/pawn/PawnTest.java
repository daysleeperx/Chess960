package pawn;

import game.Game;
import org.junit.Before;
import org.junit.Test;
import pieces.Color;
import pieces.Pawn;
import pieces.Type;
import player.Human;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class PawnTest {
    private Pawn whitePawn;
    private Pawn blackPawn;
    private Game game;

    @Before
    public void setUp() {
        game = new Game();
        game.createGame();

        whitePawn = (Pawn) game.getBoard().getSquare(3, 1).getPiece();
        blackPawn = (Pawn) game.getBoard().getSquare(3, 6).getPiece();
    }

    @Test
    public void isHasMoved() {
        assertFalse(whitePawn.isHasMoved());
        assertFalse(blackPawn.isHasMoved());
    }

    @Test
    public void getType() {
        assertEquals(Type.PAWN, whitePawn.getType());
        assertEquals(Type.PAWN, blackPawn.getType());
    }

    @Test
    public void isValidMoveBasic() {
        // check first moves by white and black pawns
        assertTrue(whitePawn.isValidMove(3, 3));
        whitePawn.setHasMoved(true);
        assertTrue(whitePawn.isHasMoved());
        assertTrue(blackPawn.isValidMove(3, 4));
        blackPawn.setHasMoved(true);
        assertTrue(blackPawn.isHasMoved());
        assertTrue(whitePawn.isValidMove(3, 2));
        assertTrue(blackPawn.isValidMove(3, 5));

    }

    @Test
    public void testPawnsInvalidSecondMove() {
        whitePawn.setHasMoved(true);
        blackPawn.setHasMoved(true);
        assertFalse(whitePawn.isValidMove(3, 3));
        assertFalse(blackPawn.isValidMove(3, 4));
    }

    @Test
    public void testPawnsWeirdMoves() {
        assertFalse(whitePawn.isValidMove(4, 5));
        //assertFalse(whitePawn.isValidMove(5, 99));
        //assertFalse(whitePawn.isValidMove(999, 5));
        assertFalse(whitePawn.isValidMove(2, 0));
        assertFalse(whitePawn.isValidMove(1, 0));

        assertFalse(blackPawn.isValidMove(3, 7));
        //assertFalse(blackPawn.isValidMove(5, 99));
        assertFalse(blackPawn.isValidMove(3, 2));
        //assertFalse(blackPawn.isValidMove(999, 5));
    }

    @Test
    public void drawPathBasic() {
        whitePawn.setX(4);
        List<int[]> testPath = new LinkedList<>();
        // from e2 to e4
        testPath.add(new int[] {4, 2});
        testPath.add(new int[] {4, 3});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), whitePawn.generatePath(4, 3).get(i)));
        // from e2 to e3
        testPath.clear();
        testPath.add(new int[] {4, 2});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), whitePawn.generatePath(4, 2).get(i)));
        // from e7 to e5
        testPath.clear();
        blackPawn.setX(4);
        testPath.add(new int[] {4, 5});
        testPath.add(new int[] {4, 4});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), blackPawn.generatePath(4, 4).get(i)));
        // from e7 to e6
        testPath.clear();
        testPath.add(new int[] {4, 5});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), blackPawn.generatePath(4, 5).get(i)));
    }
}