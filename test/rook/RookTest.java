package rook;

import org.junit.Before;
import org.junit.Test;
import pieces.Rook;
import pieces.Type;

import static org.junit.Assert.*;

public class RookTest {
    private Rook rook;

    @Before
    public void setUp() {
        rook = new Rook(1, 1, null);
    }

    @Test
    public void getType() {
        assertEquals(Type.ROOK, rook.getType());
    }

    @Test
    public void isValidMove() {
        assertTrue(rook.isValidMove(1, 8));
        assertTrue(rook.isValidMove(1, 999));
        assertTrue(rook.isValidMove(100, 1));
        assertTrue(rook.isValidMove(8, 1));
        assertTrue(rook.isValidMove(2, 1));
        assertTrue(rook.isValidMove(3, 1));
        assertTrue(rook.isValidMove(1, 2));
        assertTrue(rook.isValidMove(1, 5));

    }

    @Test
    public void testRookInvalidMoves() {
        rook = new Rook(3, 3, null);
        assertFalse(rook.isValidMove(4, 1));
        assertFalse(rook.isValidMove(5, 2));
        assertFalse(rook.isValidMove(5, 4));
        assertFalse(rook.isValidMove(4, 5));
        // long weird moves
        assertFalse(rook.isValidMove(6, 4));
        assertFalse(rook.isValidMove(1, 4));
        assertFalse(rook.isValidMove(8, 4));
        assertFalse(rook.isValidMove(6, 5));
        assertFalse(rook.isValidMove(5, 7));
        assertFalse(rook.isValidMove(1, 8));
        // diagonal
        assertFalse(rook.isValidMove(4, 4));
        assertFalse(rook.isValidMove(2, 2));
        assertFalse(rook.isValidMove(99, 99));
        assertFalse(rook.isValidMove(8, 8));
        assertFalse(rook.isValidMove(1, 1));

    }

    @Test
    public void drawPath() {
    }
}