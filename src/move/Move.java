package move;

import pieces.Piece;
import square.Square;

/**
 * Represent move class.
 */
public class Move {
    /**
     * Start Square.
     */
    private Square startSquare;
    /**
     * Destination Square.
     */
    private Square targetSquare;
    /**
     * Piece making the move.
     */
    private Piece piece;
    /**
     * Captured piece.
     */
    private Piece capturedPiece;

    /**
     *  Alternative class constructor.
     *
     * @param startSquare Square object
     * @param targetSquare Square object
     * @param piece Piece object
     */
    public Move(Square startSquare, Square targetSquare, Piece piece) {
        this(startSquare, targetSquare, piece, null);
    }

    /**
     * Class constructor.
     *
     * @param startSquare Square object
     * @param targetSquare Square object
     * @param piece Piece object
     */
    public Move(Square startSquare, Square targetSquare, Piece piece, Piece capturedPiece) {
        this.startSquare = startSquare;
        this.targetSquare = targetSquare;
        this.piece = piece;
        this.capturedPiece = capturedPiece;
    }

}
