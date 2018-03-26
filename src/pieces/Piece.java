package pieces;

import game.Player;

/**
 * Represent Piece class.
 */

public abstract class Piece {
    /**
     * Coordinates.
     */
    public int x, y;
    /**
     * Player.
     */
    private Player player;

    /**
     * Class constructor.
     *
     * @param x int X coordinate
     * @param y int Y coordinate
     * @param player Player object
     */
    public Piece(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
    /**
     * Get Type.
     *
     * @return Type
     */
    public abstract Type getType();

    /**
     * Check if move is valid.
     *
     * @param targetX int X coordinate
     * @param targetY int Y coordinate
     * @return true/false
     */
    public abstract boolean isValidMove(int targetX, int targetY);

    public abstract int[] drawPath(int startX, int startY, int finalX, int finalY);

}
