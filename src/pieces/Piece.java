package pieces;

import java.util.List;

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
    Color color;

    /**
     * Class constructor.
     *  @param x int X coordinate
     * @param y int Y coordinate
     * @param color Player object
     */
    public Piece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
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

    public abstract List<int[]> drawPath(int targetX, int targetY);

}
