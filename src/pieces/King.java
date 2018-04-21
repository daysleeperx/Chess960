package pieces;


import player.Human;

import java.util.LinkedList;
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
     * @param x     int X coordinate
     * @param y     int Y coordinate
     * @param color Color enum
     */
    public King(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.KING;
    }

    /**
     * Alternative constructor with Player object included.
     *
     * @param x      int X coordinate
     * @param y      int Y coordinate
     * @param color  Color enum
     * @param player Player object
     */
    public King(int x, int y, Color color, Human player) {
        super(x, y, color, player);
        this.type = Type.KING;
    }

    @Override
    public Type getType() {
        return type;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        return (col <= 1 && row <= 1);

        //TODO: Castling move

    }

    @Override
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();

        // path consists only of one element
        int[] currentPos = {targetX, targetY};
        path.add(currentPos);

        return path;
    }

    /**
     * Temporary parsing to FEN notation.
     *
     * @return String
     */
    @Override
    public String toFen() {
        return (color == Color.WHITE) ? "K" : "k";
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK) return "\u265A";
        return "\u2654";
    }
}
