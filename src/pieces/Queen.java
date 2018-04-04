package pieces;


import java.util.List;

/**
 * Represent Queen class.
 */

public class Queen extends Piece {

    private Type type;

    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
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
    public List<int[]> drawPath(int targetX, int targetY) {

        return null;
    }

    @Override
    public String toString() {
        return "\u2655";
    }
}
