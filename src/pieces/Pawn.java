package pieces;


import player.Human;
import square.Square;

import java.util.LinkedList;
import java.util.List;

/**
 * Represent Pawn class.
 */
public class Pawn extends Piece {

    private Type type;
    private boolean hasMoved;

    /**
     * Class constructor.
     *
     * @param x     int X coordinate
     * @param y     int Y coordinate
     * @param color Color enum
     */
    public Pawn(int x, int y, Color color) {
        super(x, y, color);
        this.type = Type.PAWN;
    }

    /**
     * Alternative constructor with Player object included.
     *
     * @param x      int X coordinate
     * @param y      int Y coordinate
     * @param color  Color enum
     * @param player Player object
     */
    public Pawn(int x, int y, Color color, Human player) {
        super(x, y, color, player);
        this.type = Type.PAWN;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    @Override
    public Type getType() {
        return type;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {
        return (canCapture(targetX, targetY) || canMoveTwo(targetX, targetY) || canMoveForward(targetX, targetY));
    }

    /**
     * Checks if pawn can move two squares forward.
     *
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */
    public boolean canMoveTwo(int targetX, int targetY) {
        int col = targetX - this.x;
        int row = targetY - this.y;

        switch (color) {
            case WHITE:
                if (!isHasMoved())
                    return (col == 0) && (row == 2 || row == 1)
                            && !player.getGame().getBoard().getSquare(targetX, targetY).isOccupied();
            case BLACK:
                if (!isHasMoved())
                    return (col == 0) && (row == -2 || row == -1)
                            && !player.getGame().getBoard().getSquare(targetX, targetY).isOccupied();
            default:
                return false;
        }
    }

    /**
     * Checks if pawn can move forward.
     *
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */
    public boolean canMoveForward(int targetX, int targetY) {
        int col = targetX - this.x;
        int row = targetY - this.y;

        switch (color) {
            case WHITE:
                return (col == 0) && (row == 1)
                        && !player.getGame().getBoard().getSquare(targetX, targetY).isOccupied();
            case BLACK:
                return (col == 0) && (row == -1)
                        && !player.getGame().getBoard().getSquare(targetX, targetY).isOccupied();

            default:
                return false;
        }
    }

    /**
     * Checks if pawn can capture. The enemy piece mus be located on a diagonal square.
     *
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */
    public boolean canCapture(int targetX, int targetY) {
        int col = Math.abs(targetX - this.x);
        int row = targetY - this.y;

        Square targetSquare = player.getGame().getBoard().getSquare(targetX, targetY);

        switch (color) {
            case WHITE:
                return ((col == 1) && (row == 1) && targetSquare.isOccupied()
                        && targetSquare.getPiece().getColor() == Color.BLACK);
            case BLACK:
                return ((col == 1) && (row == -1) && targetSquare.isOccupied()
                        && targetSquare.getPiece().getColor() == Color.WHITE);

            default:
                return false;
        }
    }

    @Override
    public List<int[]> generatePath(int targetX, int targetY) {
        List<int[]> path = new LinkedList<>();
        int pathLength = Math.abs(targetY - this.y);

        int directionX = 0;
        int directionY = (this.color == Color.WHITE) ? 1 : -1; // check the Y direction (-1 if Black)

        for (int i = 1; i <= pathLength; i++) {
            int[] currentPos = new int[2];
            currentPos[0] = this.x + directionX * i;
            currentPos[1] = this.y + directionY * i;
            path.add(currentPos);
        }

        return path;
    }

    /**
     * Temporary parsing to FEN notation.
     *
     * @return String
     */
    @Override
    public String toFen() {
        return (color == Color.WHITE) ? "P" : "p";
    }

    @Override
    public String toString() {
        if (color == Color.BLACK) return "\u265F";
        return "\u2659";
    }
}
