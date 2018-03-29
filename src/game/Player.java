package game;

import pieces.Color;

/**
 * Represent Player class.
 */

public class Player {
    private Color color;
    private boolean movesFirst;
    /**
     * Class constructor.
     */
    public Player(Color color, boolean movesFirst) {
        this.color = color;
        this.movesFirst = movesFirst;
    }

    public Color getColor() {
        return color;
    }

    public boolean isMovesFirst() {
        return movesFirst;
    }
}
