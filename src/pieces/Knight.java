package pieces;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent Knight class.
 */

public class Knight extends Piece {

    private Type type;

    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param color Color object.
     */
    public Knight(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.KNIGHT;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        return ((row == 2 && col == 1) || (row == 1 && col == 2));

    }

    @Override
    public List<int[]> drawPath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();

        int[] currentPos = new int[2];
        currentPos[0] = targetX;
        currentPos[1] = targetY;
        path.add(currentPos);

        return path;
    }

    @Override
    public String toString() {
        return "\u2658";
    }

    public static void main(String[] args) {
        Knight knight = new Knight(3, 3, null);
        for (int[] step: knight.drawPath(2, 5)) {
            System.out.println(Arrays.toString(step));
        }

    }

}
