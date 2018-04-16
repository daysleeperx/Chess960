package pieces;



import java.util.LinkedList;
import java.util.List;

/**
 * Represent Bishop class.
 */

public class Bishop extends Piece {

    private Type type;

    /**
     * Class constructor.
     *
     * @param x     X coordinate
     * @param y     Y coordinate
     * @param color Color object.
     */
    public Bishop(int x, int y, Color color) {

        super(x, y, color);
        this.type = Type.BISHOP;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        // diagonal moves only
        return col == row;
    }

    @Override
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();
        int pathLength = Math.abs(targetX - this.x);

        int directionX = (targetX > this.x) ? 1 : -1; // check the x direction
        int directionY = (targetY > this.y) ? 1 : -1; // check the y direction

        for (int i = 1; i <= pathLength; i++) {
            int[] currentPos = new int[2];
            currentPos[0] = this.x + directionX * i;
            currentPos[1] = this.y + directionY * i;
            path.add(currentPos);
        }

        return path;
    }

    @Override
    public String toString() {
        if (color == Color.BLACK) return "\u265D";
        return "\u2657";
    }

}

