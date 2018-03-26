package knight;

import org.junit.Before;
import org.junit.Test;
import pieces.Knight;
import pieces.Type;

import static org.junit.Assert.*;

public class KnightTest {
    private Knight k;

    @Before
    public void createKnights() {
        k = new Knight(3, 3, null);
    }

    @Test
    public void getType() {
        assertEquals(Type.KNIGHT, k.getType());
    }

    @Test
    public void isValidMoveBasic() {
        // d5 to e7
        assertTrue(k.isValidMove(4, 1));
        // d5 to f6
        assertTrue(k.isValidMove(5, 2));
        // d5 to f4
        assertTrue(k.isValidMove(5, 4));
        // d5 to e3
        assertTrue(k.isValidMove(4, 5));
        // d5 to c3
        assertTrue(k.isValidMove(2, 5));
        // d5 to b4
        assertTrue(k.isValidMove(1, 4));
        // d5 to b6
        assertTrue(k.isValidMove(1, 2));
        // d5 to c7
        assertTrue(k.isValidMove(2, 1));
    }

    @Test
    public void invalidMoves() {
        // d5 to e6
        assertFalse(k.isValidMove(4, 2));
        // d5 to d6
        assertFalse(k.isValidMove(3, 2));
        // d5 to c6
        assertFalse(k.isValidMove(2, 2));
        // d5 to c5
        assertFalse(k.isValidMove(2, 3));
        // d5 to e5
        assertFalse(k.isValidMove(4, 3));
        // d5 to d4
        assertFalse(k.isValidMove(3, 4));
    }

    @Test
    public void drawPath() {
    }

}