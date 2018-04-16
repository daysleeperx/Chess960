package pieces;


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
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();

        // path consists only of one element
        int[] currentPos = {targetX, targetY};
        path.add(currentPos);

        return path;
    }

    @Override
    public String toString() {
        if (color == Color.BLACK) return "\u265E";
        return "\u2658";
    }
}

