package Pieces;

import Game.Player;
import Game.Type;

/**
 * Represent King class.
 */

public class King extends Piece {

    private Type type;

    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param player Player object.
     */
    public King(int x, int y, Player player) {
        super(x, y, player);
        this.type = Type.KING;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isValidMove(int goalX, int goalY) {
        return false;
    }

    @Override
    public int[] makeMove(int startX, int startY, int finalX, int finalY) {
        return new int[0];
    }

    @Override
    public String toString() {
        return "\u2654";
    }
}
