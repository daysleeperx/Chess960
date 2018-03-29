package pieces;


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
    public int[] drawPath(int startX, int startY, int finalX, int finalY) {
        return new int[0];
    }

    @Override
    public String toString() {
        return "\u2658";
    }

}
