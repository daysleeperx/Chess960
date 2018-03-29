package queen;

import org.junit.Before;
import org.junit.Test;
import pieces.Queen;
import pieces.Type;

import static org.junit.Assert.*;

public class QueenTest {
    private Queen queen;

    @Before
    public void setUp() throws Exception {
        queen = new Queen(5, 5, null);
    }

    @Test
    public void getType() {
        assertEquals(Type.QUEEN, queen.getType());
    }

    @Test
    public void isValidMove() {
        // vertical
        assertTrue(queen.isValidMove(5, 8));
        assertTrue(queen.isValidMove(5, 1));
        assertTrue(queen.isValidMove(5, 4));
        assertTrue(queen.isValidMove(5, 7));
        assertTrue(queen.isValidMove(5, 3));
        // horizontal
        assertTrue(queen.isValidMove(1, 5));
        assertTrue(queen.isValidMove(3, 5));
        assertTrue(queen.isValidMove(6, 5));
        assertTrue(queen.isValidMove(8, 5));
        assertTrue(queen.isValidMove(99, 5));
        // diagonal
        assertTrue(queen.isValidMove(6, 6));
        assertTrue(queen.isValidMove(8, 8));
        assertTrue(queen.isValidMove(4, 4));
        assertTrue(queen.isValidMove(1, 1));
        assertTrue(queen.isValidMove(99, 99));

    }

    @Test
    public void testQueenInvalidMoves() {
        // knight type of moves
        queen = new Queen(3, 3, null);
        assertFalse(queen.isValidMove(4, 1));
        assertFalse(queen.isValidMove(5, 2));
        assertFalse(queen.isValidMove(5, 4));
        assertFalse(queen.isValidMove(4, 5));

    }

    @Test
    public void testLongAndWeirdMoves() {
        queen = new Queen(3, 3, null);
        assertFalse(queen.isValidMove(6, 4));
        assertFalse(queen.isValidMove(1, 4));
        assertFalse(queen.isValidMove(8, 4));
        assertFalse(queen.isValidMove(6, 5));
        assertFalse(queen.isValidMove(5, 7));
        assertFalse(queen.isValidMove(1, 8));
    }

    @Test
    public void drawPath() {
    }
}