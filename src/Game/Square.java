package Game;

import Pieces.Piece;

/**
 * Represent Square class.
 */

public abstract class Square {

    private int squareCoord;
    /**
     * Class constructor.
     */
    public Square(int squareCoord) {
        this.squareCoord = squareCoord;
    }

    public int getSquareCoord() {
        return squareCoord;
    }

    /**
     * Check if Square is occupied.
     *
     * @return true/ false
     */
    public abstract boolean isOccupied();

    /**
     * Return current Piece.
     *
     * @return Piece
     */
    public abstract Piece getPiece();

}
