package rook;

import org.junit.Before;
import org.junit.Test;
import pieces.Color;
import pieces.Rook;
import pieces.Type;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

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
    public void drawPathBasic() {
        rook = new Rook(0, 0, Color.WHITE);
        List<int[]> testPath = new LinkedList<>();
        // from a1 to a8
        testPath.add(new int[] {0, 1});
        testPath.add(new int[] {0, 2});
        testPath.add(new int[] {0, 3});
        testPath.add(new int[] {0, 4});
        testPath.add(new int[] {0, 5});
        testPath.add(new int[] {0, 6});
        testPath.add(new int[] {0, 7});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), rook.generatePath(0, 7).get(i)));
        // a1 to h1
        testPath.clear();
        testPath.add(new int[] {1, 0});
        testPath.add(new int[] {2, 0});
        testPath.add(new int[] {3, 0});
        testPath.add(new int[] {4, 0});
        testPath.add(new int[] {5, 0});
        testPath.add(new int[] {6, 0});
        testPath.add(new int[] {7, 0});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), rook.generatePath(7, 0).get(i)));
        // h1 to a1 reverse
        rook.setX(7);
        Collections.reverse(testPath);
        testPath.remove(0);
        testPath.add(new int[] {0, 0});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), rook.generatePath(0, 0).get(i)));
        // h1 to h4
        testPath.clear();
        testPath.add(new int[] {7, 1});
        testPath.add(new int[] {7, 2});
        testPath.add(new int[] {7, 3});
        testPath.add(new int[] {7, 4});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), rook.generatePath(7, 4).get(i)));
        // h5 to d5
        rook.setY(4);
        testPath.clear();
        testPath.add(new int[] {6, 4});
        testPath.add(new int[] {5, 4});
        testPath.add(new int[] {4, 4});
        testPath.add(new int[] {3, 4});
        testPath.add(new int[] {2, 4});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), rook.generatePath(2, 4).get(i)));

    }

    @Test
    public void rookTestSmallMove() {
        List<int[]> testPath = new LinkedList<>();
        // d6 to c6  one move
        rook.setX(3);
        rook.setY(5);
        testPath.clear();
        testPath.add(new int[] {2, 5});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), rook.generatePath(2, 5).get(i)));

    }
}