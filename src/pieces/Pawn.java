package pieces;

import game.Player;

/**
 * Represent Pawn class.
 */
public class Pawn extends Piece {

    private Type type;
    private boolean hasMoved;

    /**
     * Class constructor.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param player Player object.
     */
    public Pawn(int x, int y, Player player) {
        super(x, y, player);
        this.type = Type.PAWN;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isValidMove(int targetX, int targetY) {

        return false;
    }

    @Override
    public int[] drawPath(int startX, int startY, int finalX, int finalY) {
        return new int[0];
    }

    @Override
    public String toString() {
        return "\u2659";
    }
}
