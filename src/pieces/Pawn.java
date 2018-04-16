package pieces;


import java.util.LinkedList;
import java.util.List;

/**
 * Represent Pawn class.
 */
public class Pawn extends Piece {

    private Type type;
    private boolean hasMoved;

    /**
     * Class constructor.
     *
     * @param x     X coordinate
     * @param y     Y coordinate
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
        int col = targetX - this.x;
        int row = targetY - this.y;

        switch (color) {
            case WHITE:
                if (!isHasMoved())
                    return (col == 0) && (row == 2 || row == 1);

                return (col == 0) && (row == 1);
            case BLACK:
                if (!isHasMoved())
                    return (col == 0) && (row == -2 || row == -1);

                return (col == 0) && (row == -1);

            default:
                return false;
        }
        // TODO: capture moves
    }

    @Override
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();
        int pathLength = Math.abs(targetY - this.y);

        int directionX = 0;
        int directionY = (this.color == Color.WHITE) ? 1 : -1; // check the Y direction (-1 if Black)

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
        if (color == Color.BLACK) return "\u265F";
        return "\u2659";
    }
}
