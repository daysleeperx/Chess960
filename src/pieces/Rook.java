package pieces;


/**
 * Represent Rook class.
 */

public class Rook extends Piece {

    private Type type;
    private boolean hasMoved;

    /**
     * Class constructor.
     *  @param x X coordinate
     * @param y Y coordinate
     * @param color Color object.
     */
    public Rook(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.ROOK;

    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    @Override
    public Type getType() { return type; }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        // horizontal and vertical only
        return (col == 0) || (row == 0);

    }

    @Override
    public int[] drawPath(int startX, int startY, int finalX, int finalY) {
        return new int[0];
    }

    @Override
    public String toString() {
        return "\u2656";
    }
}
