package queen;

import org.junit.Before;
import org.junit.Test;
import pieces.Color;
import pieces.Queen;
import pieces.Type;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class QueenTest {
    private Queen queen;

    @Before
    public void setUp() {
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
    public void drawPathDiagonal() {
        queen = new Queen(5, 0, Color.WHITE);
        List<int[]> testPath = new LinkedList<>();
        // from f1 to b5
        testPath.add(new int[] {4, 1});
        testPath.add(new int[] {3, 2});
        testPath.add(new int[] {2, 3});
        testPath.add(new int[] {1, 4});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(1, 4).get(i)));
        // a1 to h8
        queen.setX(0);
        queen.setY(0);
        testPath.clear();
        testPath.add(new int[] {1, 1});
        testPath.add(new int[] {2, 2});
        testPath.add(new int[] {3, 3});
        testPath.add(new int[] {4, 4});
        testPath.add(new int[] {5, 5});
        testPath.add(new int[] {6, 6});
        testPath.add(new int[] {7, 7});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(7, 7).get(i)));
        // h8 to a1
        queen.setX(7);
        queen.setY(7);
        Collections.reverse(testPath);
        testPath.remove(0);
        testPath.add(new int[] {0, 0});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(0, 0).get(i)));
        // h6 to b4
        queen.setX(5);
        queen.setY(7);
        testPath.clear();
        testPath.add(new int[] {4, 6});
        testPath.add(new int[] {3, 5});
        testPath.add(new int[] {2, 4});
        testPath.add(new int[] {1, 3});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(1, 3).get(i)));
        // с8 to h3
        queen.setX(2);
        queen.setY(7);
        testPath.clear();
        testPath.add(new int[] {3, 6});
        testPath.add(new int[] {4, 5});
        testPath.add(new int[] {5, 4});
        testPath.add(new int[] {6, 3});
        testPath.add(new int[] {7, 2});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(7, 2).get(i)));
        // h1 to a8
        queen.setX(7);
        queen.setY(0);
        testPath.clear();
        testPath.add(new int[] {6, 1});
        testPath.add(new int[] {5, 2});
        testPath.add(new int[] {4, 3});
        testPath.add(new int[] {3, 4});
        testPath.add(new int[] {2, 5});
        testPath.add(new int[] {1, 6});
        testPath.add(new int[] {0, 7});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(0, 7).get(i)));
    }

    @Test
    public void drawPathHorizontalAndVertical() {
        queen = new Queen(0, 0, Color.WHITE);
        List<int[]> testPath = new LinkedList<>();
        // from a1 to a8
        testPath.add(new int[] {0, 1});
        testPath.add(new int[] {0, 2});
        testPath.add(new int[] {0, 3});
        testPath.add(new int[] {0, 4});
        testPath.add(new int[] {0, 5});
        testPath.add(new int[] {0, 6});
        testPath.add(new int[] {0, 7});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(0, 7).get(i)));
        // a1 to h1
        testPath.clear();
        testPath.add(new int[] {1, 0});
        testPath.add(new int[] {2, 0});
        testPath.add(new int[] {3, 0});
        testPath.add(new int[] {4, 0});
        testPath.add(new int[] {5, 0});
        testPath.add(new int[] {6, 0});
        testPath.add(new int[] {7, 0});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(7, 0).get(i)));
        // h1 to a1 reverse
        queen.setX(7);
        Collections.reverse(testPath);
        testPath.remove(0);
        testPath.add(new int[] {0, 0});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(0, 0).get(i)));
        // h1 to h4
        testPath.clear();
        testPath.add(new int[] {7, 1});
        testPath.add(new int[] {7, 2});
        testPath.add(new int[] {7, 3});
        testPath.add(new int[] {7, 4});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(7, 4).get(i)));
        // h5 to d5
        queen.setY(4);
        testPath.clear();
        testPath.add(new int[] {6, 4});
        testPath.add(new int[] {5, 4});
        testPath.add(new int[] {4, 4});
        testPath.add(new int[] {3, 4});
        testPath.add(new int[] {2, 4});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(2, 4).get(i)));

    }

    @Test
    public void drawPathSmallMoves() {
        queen.setX(4);
        queen.setY(4);
        // all possible directions
        List<int[]> testPath = new LinkedList<>();
        testPath.add(new int[]{4, 5});
        assertEquals(1, queen.generatePath(4, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(4, 5).get(i)));

        testPath.clear();
        testPath.add(new int[]{4, 3});
        assertEquals(1, queen.generatePath(4, 3).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(4, 3).get(i)));

        testPath.clear();
        testPath.add(new int[]{5, 3});
        assertEquals(1, queen.generatePath(5, 3).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(5, 3).get(i)));

        testPath.clear();
        testPath.add(new int[]{5, 5});
        assertEquals(1, queen.generatePath(5, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(5, 5).get(i)));

        testPath.clear();
        testPath.add(new int[]{3, 5});
        assertEquals(1, queen.generatePath(3, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(3, 5).get(i)));

        testPath.clear();
        testPath.add(new int[]{3, 3});
        assertEquals(1, queen.generatePath(3, 3).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(3, 3).get(i)));

        testPath.clear();
        testPath.add(new int[]{5, 4});
        assertEquals(1, queen.generatePath(5, 4).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(5, 4).get(i)));

        testPath.clear();
        testPath.add(new int[]{3, 5});
        assertEquals(1, queen.generatePath(3, 5).size());
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), queen.generatePath(3, 5).get(i)));

    }
}