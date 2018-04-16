package pieces;


import java.util.LinkedList;
import java.util.List;

/**
 * Represent Queen class.
 */

public class Queen extends Piece {

    private Type type;

    /**
     * Class constructor.
     *
     * @param x     X coordinate
     * @param y     Y coordinate
     * @param color Color object.
     */
    public Queen(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.QUEEN;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        // diagonal and vertical/ horizontal
        return (col == row) || (col == 0) || (row == 0);
    }

    @Override
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();
        int directionX = 0;
        int directionY = 0;
        int pathLength = (this.x == targetX) ? Math.abs(targetY - this.y) : Math.abs(targetX - this.x);

        // if queen moves vertically
        if (this.x == targetX) {
            directionY = (targetY > this.y) ? 1 : -1;
        } else if (this.y == targetY) {  // if queen moves horizontally
            directionX = (targetX > this.x) ? 1 : -1;
        } else { // if queen moves diagonally
            directionX = (targetX > this.x) ? 1 : -1;
            directionY = (targetY > this.y) ? 1 : -1;
        }

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
        if (color == Color.BLACK) return "\u265B";
        return "\u2655";
    }
}
