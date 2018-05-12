package pieces;


import board.Board;
import player.Player;

import java.util.LinkedList;
import java.util.List;

/**
 * Represent King class.
 */

public class King extends Piece {

    private Type type;
    private boolean hasMoved;



    /**
     * Class constructor.
     *
     * @param x     int X coordinate
     * @param y     int Y coordinate
     * @param color Color enum
     */
    public King(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.KING;
    }

    /**
     * Alternative constructor with Player object included.
     *  @param x      int X coordinate
     * @param y      int Y coordinate
     * @param color  Color enum
     * @param player Player object
     */
    public King(int x, int y, Color color, Player player) {
        super(x, y, color, player);
        this.type = Type.KING;
    }

    @Override
    public Type getType() {
        return type;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }


    @Override
    public boolean isValidMove(int targetX, int targetY) {
        return (canCastleKingSide(targetX, targetY) || canCastleQueenSide(targetX, targetY)
                || canMove(targetX, targetY));
    }

    /**
     * Check if King is in check.
     *
     * @return {@code true}, otherwise {@code false}
     */
    public boolean isInCheck() {
        Board board = player.getGame().getBoard();
        List<Piece> enemyPieces = player.getGame().getBoard().getEnemyPieces(player);

        // check if enemy pieces are attacking king
        for (Piece piece: enemyPieces) {
            if (piece.isValidMove(this.x, this.y) && board.isValidPath(piece, this.x, this.y)) return true;
        }

        return false;
    }
    /**
     * Checks if Kingside castling is possible.
     *
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */
    public boolean canCastleKingSide(int targetX, int targetY) {
        Board board = player.getGame().getBoard();

        switch (color) {
            case WHITE:
                return (this.x == 4 && this.y == 0)
                        && (targetX == 6 && targetY == 0)
                        && (!this.hasMoved)
                        && (!this.isInCheck())
                        && board.getSquare(7, 0).isOccupied()
                        && board.getSquare(7, 0).getPiece().getType() == Type.ROOK
                        && (!((Rook) board.getSquare(7, 0).getPiece()).isHasMoved())
                        && (!board.getSquare(6, 0).isOccupied())
                        && (!board.getSquare(5, 0).isOccupied());

            case BLACK:
                return (this.x == 4 && this.y == 7)
                        && (targetX == 6 && targetY == 7)
                        && (!this.hasMoved)
                        && (!this.isInCheck())
                        && board.getSquare(7, 7).isOccupied()
                        && board.getSquare(7, 7).getPiece().getType() == Type.ROOK
                        && (!((Rook) board.getSquare(7, 7).getPiece()).isHasMoved())
                        && (!board.getSquare(6, 7).isOccupied())
                        && (!board.getSquare(5, 7).isOccupied());

            default: return false;
        }

    }

    /**
     * Checks if Queenside castling is possible.
     *
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */
    public boolean canCastleQueenSide(int targetX, int targetY) {
        Board board = player.getGame().getBoard();

        switch (color) {
            case WHITE:
                return (this.x == 4 && this.y == 0)
                        && (targetX == 2 && targetY == 0)
                        && (!this.hasMoved)
                        && (!this.isInCheck())
                        && board.getSquare(0, 0).isOccupied()
                        && board.getSquare(0, 0).getPiece().getType() == Type.ROOK
                        && (!((Rook) board.getSquare(0, 0).getPiece()).isHasMoved())
                        && (!board.getSquare(3, 0).isOccupied())
                        && (!board.getSquare(2, 0).isOccupied())
                        && (!board.getSquare(1, 0).isOccupied());

            case BLACK:
                return (this.x == 4 && this.y == 7)
                        && (targetX == 2 && targetY == 7)
                        && (!this.hasMoved)
                        && (!this.isInCheck())
                        && board.getSquare(0, 7).isOccupied()
                        && board.getSquare(0, 7).getPiece().getType() == Type.ROOK
                        && (!((Rook) board.getSquare(0, 7).getPiece()).isHasMoved())
                        && (!board.getSquare(3, 7).isOccupied())
                        && (!board.getSquare(2, 7).isOccupied())
                        && (!board.getSquare(1, 7).isOccupied());

            default: return false;
        }

    }

    /**
     * Checks if King can move.
     *
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */
    public boolean canMove(int targetX, int targetY) {
        int col = Math.abs(this.x - targetX);
        int row = Math.abs(this.y - targetY);

        return (col <= 1 && row <= 1);
    }

    @Override
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();

        // path consists only of one element
        int[] currentPos = {targetX, targetY};
        path.add(currentPos);

        return path;
    }

    /**
     * Temporary parsing to FEN notation.
     *
     * @return String
     */
    @Override
    public String toFen() {
        return (color == Color.WHITE) ? "K" : "k";
    }

    @Override
    public String toString() {
        if (this.color == Color.BLACK) return "\u265A";
        return "\u2654";
    }
}
