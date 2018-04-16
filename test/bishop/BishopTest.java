package bishop;

import org.junit.Before;
import org.junit.Test;
import pieces.Bishop;
import pieces.Color;
import pieces.Type;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

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
    public void drawPathBasic() {
        bishop = new Bishop(5, 0, Color.WHITE);
        List<int[]> testPath = new LinkedList<>();
        // from f1 to b5
        testPath.add(new int[] {4, 1});
        testPath.add(new int[] {3, 2});
        testPath.add(new int[] {2, 3});
        testPath.add(new int[] {1, 4});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), bishop.generatePath(1, 4).get(i)));
        // a1 to h8
        bishop.setX(0);
        bishop.setY(0);
        testPath.clear();
        testPath.add(new int[] {1, 1});
        testPath.add(new int[] {2, 2});
        testPath.add(new int[] {3, 3});
        testPath.add(new int[] {4, 4});
        testPath.add(new int[] {5, 5});
        testPath.add(new int[] {6, 6});
        testPath.add(new int[] {7, 7});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), bishop.generatePath(7, 7).get(i)));
        // h8 to a1
        bishop.setX(7);
        bishop.setY(7);
        Collections.reverse(testPath);
        testPath.remove(0);
        testPath.add(new int[] {0, 0});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), bishop.generatePath(0, 0).get(i)));
        // h6 to b4
        bishop.setX(5);
        bishop.setY(7);
        testPath.clear();
        testPath.add(new int[] {4, 6});
        testPath.add(new int[] {3, 5});
        testPath.add(new int[] {2, 4});
        testPath.add(new int[] {1, 3});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), bishop.generatePath(1, 3).get(i)));
        // с8 to h3
        bishop.setX(2);
        bishop.setY(7);
        testPath.clear();
        testPath.add(new int[] {3, 6});
        testPath.add(new int[] {4, 5});
        testPath.add(new int[] {5, 4});
        testPath.add(new int[] {6, 3});
        testPath.add(new int[] {7, 2});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), bishop.generatePath(7, 2).get(i)));
        // h1 to a8
        bishop.setX(7);
        bishop.setY(0);
        testPath.clear();
        testPath.add(new int[] {6, 1});
        testPath.add(new int[] {5, 2});
        testPath.add(new int[] {4, 3});
        testPath.add(new int[] {3, 4});
        testPath.add(new int[] {2, 5});
        testPath.add(new int[] {1, 6});
        testPath.add(new int[] {0, 7});
        IntStream.range(0, testPath.size()).forEach(i -> assertArrayEquals(testPath.get(i), bishop.generatePath(0, 7).get(i)));


    }
}