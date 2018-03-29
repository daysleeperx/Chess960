package pieces;


/**
 * Represent Pawn class.
 */
public class Pawn extends Piece {

    private Type type;
    private boolean hasMoved;

    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param color Color object.
     */
    public Pawn(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.PAWN;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col =  targetX - this.x;
        int row = targetY - this.y;

        if (this.color == Color.WHITE) {
            if (!isHasMoved()) {
                return (col == 0) && (row == 2 || row == 1);
            }
            return (col == 0) && (row == 1);
        }

        if (this.color == Color.BLACK) {
            if (!isHasMoved()) {
                return (col == 0) && (row == -2 || row == -1);
            }
            return (col == 0) && (row == -1);
        }

        return false;

        // TODO: capture moves

    }

    @Override
    public int[] drawPath(int startX, int startY, int finalX, int finalY) {
        return new int[0];
    }

    @Override
    public String toString() {
        return "\u2659";
    }
}
