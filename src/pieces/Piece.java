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
     * Color.
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

    public abstract Type getType();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Check if move is valid.
     *
     * @param targetX int X coordinate
     * @param targetY int Y coordinate
     * @return true/false
     */
    public abstract boolean isValidMove(int targetX, int targetY);

    /**
     * Generates a path of coordinates.
     *
     * @param targetX int
     * @param targetY int
     * @return List of arrays
     */
    public abstract List<int[]> generatePath(int targetX, int targetY);

}
