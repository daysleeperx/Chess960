package Pieces;

import Game.Player;

/**
 * Represent Knight class.
 */

public class Knight extends Piece {
    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param player Player object.
     */
    public Knight(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean isValidMove(int goalX, int goalY) {
        return false;
    }

    @Override
    public int[] makeMove(int startX, int startY, int finalX, int finalY) {
        return new int[0];
    }


}
