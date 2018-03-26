package bishop;

import org.junit.Before;
import org.junit.Test;
import pieces.Bishop;
import pieces.Type;

import static org.junit.Assert.*;

public class BishopTest {
    private Bishop bishop;

    @Before
    public void setUp() {
        bishop = new Bishop(4, 3, null);
    }

    @Test
    public void getType() {
        assertEquals(Type.BISHOP, bishop.getType());
    }

    @Test
    public void isValidMove() {
        // check diagonal moves only
        assertTrue(bishop.isValidMove(5, 4));
        assertTrue(bishop.isValidMove(3, 2));
        assertTrue(bishop.isValidMove(1, 0));
        assertTrue(bishop.isValidMove(6, 5));
        assertTrue(bishop.isValidMove(6, 1));
        assertTrue(bishop.isValidMove(1, 0));
        assertTrue(bishop.isValidMove(7, 0));
        assertTrue(bishop.isValidMove(10, 9));
        assertTrue(bishop.isValidMove(100, 99));

    }

    @Test
    public void testBishopInvalidMoves() {
        assertFalse(bishop.isValidMove(5, 3));
        assertFalse(bishop.isValidMove(3, 3));
        assertFalse(bishop.isValidMove(10, 3));
        assertFalse(bishop.isValidMove(4, 6));
        assertFalse(bishop.isValidMove(4, 1));
        assertFalse(bishop.isValidMove(5, 5));
        assertFalse(bishop.isValidMove(3, 3));
    }

    @Test
    public void drawPath() {
    }
}