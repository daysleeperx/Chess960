package move;


import gui.MoveType;
import gui.Piece;

/**
 * Represent move class used in Gui.
 */
public class Move {
    /**
     * Piece making the move.
     */
    private Piece piece;
    /**
     * Type of move.
     */
    private MoveType moveType;

    public Move(MoveType moveType) {
        this.moveType = moveType;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public MoveType getMoveType() {
        return moveType;
    }
}
