package king;

import org.junit.Before;
import org.junit.Test;
import pieces.King;
import pieces.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
    public void drawPathBasic() {
        // all possible directions
        List<int[]> testPath = new ArrayList<>();
        testPath.add(new int[]{4, 5});
        assertEquals(1, king.generatePath(4, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(4, 5).get(i)));

        testPath.clear();
        testPath.add(new int[]{4, 3});
        assertEquals(1, king.generatePath(4, 3).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(4, 3).get(i)));

        testPath.clear();
        testPath.add(new int[]{5, 3});
        assertEquals(1, king.generatePath(5, 3).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(5, 3).get(i)));

        testPath.clear();
        testPath.add(new int[]{5, 5});
        assertEquals(1, king.generatePath(5, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(5, 5).get(i)));

        testPath.clear();
        testPath.add(new int[]{3, 5});
        assertEquals(1, king.generatePath(3, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(3, 5).get(i)));

        testPath.clear();
        testPath.add(new int[]{3, 3});
        assertEquals(1, king.generatePath(3, 3).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(3, 3).get(i)));

        testPath.clear();
        testPath.add(new int[]{5, 4});
        assertEquals(1, king.generatePath(5, 4).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(5, 4).get(i)));

        testPath.clear();
        testPath.add(new int[]{3, 5});
        assertEquals(1, king.generatePath(3, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), king.generatePath(3, 5).get(i)));
    }
}