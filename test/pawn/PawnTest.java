package pawn;

import org.junit.Before;
import org.junit.Test;
import pieces.Color;
import pieces.Pawn;
import pieces.Type;

import static org.junit.Assert.*;

public class PawnTest {
    private Pawn whitePawn;
    private Pawn blackPawn;
    @Before
    public void setUp() {
        whitePawn = new Pawn(3, 1, Color.WHITE);
        blackPawn = new Pawn(3, 6, Color.BLACK);

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
        assertFalse(whitePawn.isValidMove(5, 99));
        assertFalse(whitePawn.isValidMove(999, 5));
        assertFalse(whitePawn.isValidMove(2, 0));
        assertFalse(whitePawn.isValidMove(1, 0));

        assertFalse(blackPawn.isValidMove(3, 7));
        assertFalse(blackPawn.isValidMove(5, 99));
        assertFalse(blackPawn.isValidMove(3, 2));
        assertFalse(blackPawn.isValidMove(999, 5));
    }

    @Test
    public void drawPath() {
    }
}