package Pieces;

import Game.Player;
import Game.Type;

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
     * @param player Player object.
     */
    public Queen(int x, int y, Player player) {
        super(x, y, player);
        this.type = Type.QUEEN;
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
        return "\u2655";
    }
}
