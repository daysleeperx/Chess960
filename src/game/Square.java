package game;

import pieces.Piece;


/**
 * Represent Square class.
 */

public class Square {
    /**
     * Coordinates on the board array.
     */
    private int x;
    private int y;
    /**
     * Piece object.
     */
    private Piece piece;

    /**
     * Class constructor.
     *
     * @param x int
     * @param y int
     * @param piece Piece object
     */
    public Square(int x, int y, Piece piece) {
        this.x = x;
        this.y = y;
        this.piece = piece;
    }
    /**
     * Alternative constructor.
     *
     * @param x int
     * @param y int
     */
    public Square(int x, int y) {
        new Square(x, y, null);
    }
    /**
     * Check if Square is occupied.
     *
     * @return true/ false
     */
    public boolean isOccupied() {
        return this.piece != null;

    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        if (this.isOccupied()) {
            return piece.toString();
        }
        return " ";
    }
}
