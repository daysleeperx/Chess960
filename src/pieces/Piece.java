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
    public Player player;

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

    //TODO: methods

    /**
     * Get Type.
     *
     * @return Type
     */
    public abstract Type getType();

    /**
     * Check if move is valid.
     *
     * @param goalX int X coordinate
     * @param goalY int Y coordinate
     * @return true/false
     */
    public abstract boolean isValidMove(int goalX, int goalY);

    /**
     * Make the move. Draw the path.
     *
     * @param startX int starting point
     * @param startY int starting point
     * @param finalX int goal
     * @param finalY int goal
     * @return int array
     */
    public abstract int[] makeMove(int startX, int startY, int finalX, int finalY);

}
