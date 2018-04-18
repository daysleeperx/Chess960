package pieces;


import java.util.LinkedList;
import java.util.List;

/**
 * Represent Rook class.
 */

public class Rook extends Piece {

    private Type type;
    private boolean hasMoved;

    /**
     * Class constructor.
     *
     * @param x     X coordinate
     * @param y     Y coordinate
     * @param color Color object.
     */
    public Rook(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.ROOK;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        // horizontal and vertical only
        return (col == 0) || (row == 0);

    }

    @Override
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();
        int directionX = 0;
        int directionY = 0;
        int pathLength = (this.x == targetX) ? Math.abs(targetY - this.y) : Math.abs(targetX - this.x);

        // if rook moves vertically
        if (this.x == targetX) directionY = (targetY > this.y) ? 1 : -1; // check the y direction

        // if rook moves horizontally
        if (this.y == targetY) directionX = (targetX > this.x) ? 1 : -1; // check the x direction

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
        if (color == Color.BLACK) return "\u265C";
        return "\u2656";
    }
}
