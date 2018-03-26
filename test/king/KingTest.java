package king;

import org.junit.Before;
import org.junit.Test;
import pieces.King;
import pieces.Type;

import static org.junit.Assert.*;

public class KingTest {
    private King king;

    @Before
    public void setUp() {
        king = new King(4, 4, null);
    }

    @Test
    public void getType() {
        assertEquals(Type.KING, king.getType());
    }

    @Test
    public void isValidMoveBasic() {
        // in all possible directions
        assertTrue(king.isValidMove(4, 5));
        assertTrue(king.isValidMove(4, 3));
        assertTrue(king.isValidMove(5, 3));
        assertTrue(king.isValidMove(5, 5));
        assertTrue(king.isValidMove(3, 5));
        assertTrue(king.isValidMove(3, 3));
        assertTrue(king.isValidMove(5, 4));
        assertTrue(king.isValidMove(3, 5));
    }

    @Test
    public void testKingLongMoves() {
        // test long moves
        assertFalse(king.isValidMove(6, 4));
        assertFalse(king.isValidMove(7, 6));
        assertFalse(king.isValidMove(2, 4));
        assertFalse(king.isValidMove(5, 6));
        assertFalse(king.isValidMove(1, 4));
        assertFalse(king.isValidMove(7, 7));
        assertFalse(king.isValidMove(999, 999));
    }

    @Test
    public void drawPath() {
    }
}