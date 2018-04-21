package pieces;


import player.Human;

import java.util.LinkedList;
import java.util.List;

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

    /**
     * Alternative constructor with Player object included.
     *
     * @param x      int X coordinate
     * @param y      int Y coordinate
     * @param color  Color enum
     * @param player Player object
     */
    public Knight(int x, int y, Color color, Human player) {
        super(x, y, color, player);
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
        return (color == Color.WHITE) ? "N" : "n";
    }

    @Override
    public String toString() {
        if (color == Color.BLACK) return "\u265E";
        return "\u2658";
    }
}

