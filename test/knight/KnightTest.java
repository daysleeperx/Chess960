package knight;

import org.junit.Before;
import org.junit.Test;
import pieces.Knight;
import pieces.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
    public void drawPathBasic() {
        // d5 to e7
        List<int[]> testPath = new ArrayList<>();
        testPath.add(new int[]{4, 1});
        assertEquals(1, k.generatePath(4, 1).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(4, 1).get(i)));
        // d5 to f6
        testPath.clear();
        testPath.add(new int[]{5, 2});
        assertEquals(1, k.generatePath(5, 2).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(5, 2).get(i)));
        // d5 to f4
        testPath.clear();
        testPath.add(new int[]{5, 4});
        assertEquals(1, k.generatePath(5, 4).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(5, 4).get(i)));
        // d5 to e3
        testPath.clear();
        testPath.add(new int[]{4, 5});
        assertEquals(1, k.generatePath(4, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(4, 5).get(i)));
        // d5 to c3
        testPath.clear();
        testPath.add(new int[]{2, 5});
        assertEquals(1, k.generatePath(2, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(2, 5).get(i)));
        // d5 to b4
        testPath.clear();
        testPath.add(new int[]{1, 4});
        assertEquals(1, k.generatePath(1, 4).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(1, 4).get(i)));
        // d5 to b6
        testPath.clear();
        testPath.add(new int[]{1, 2});
        assertEquals(1, k.generatePath(1, 2).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(1, 2).get(i)));
        // d5 to c7
        testPath.clear();
        testPath.add(new int[]{2, 1});
        assertEquals(1, k.generatePath(2, 1).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), k.generatePath(2, 1).get(i)));
    }

}