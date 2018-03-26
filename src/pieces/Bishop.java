package pieces;

import game.Player;

/**
 * Represent Bishop class.
 */

public class Bishop extends Piece {

    private Type type;

    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param player Player object.
     */
    public Bishop(int x, int y, Player player) {

        super(x, y, player);
        this.type = Type.BISHOP;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        // diagonal moves only
        return col == row;
    }

    @Override
    public int[] drawPath(int startX, int startY, int finalX, int finalY) {
        return new int[0];
    }

    @Override
    public String toString() {
        return "\u2657";
    }
}
