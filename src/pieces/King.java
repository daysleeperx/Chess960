package pieces;


import java.util.List;

/**
 * Represent King class.
 */

public class King extends Piece {

    private Type type;
    private boolean hasMoved;

    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param color Color object.
     */
    public King(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.KING;
    }

    @Override
    public Type getType() {
        return type;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        return (col <= 1 && row <= 1);

        //TODO: Castling move

    }

    @Override
    public List<int[]> drawPath(int targetX, int targetY) {
        return null;
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK) {
            return "\u265A";
        }
        return "\u2654";
    }
}
