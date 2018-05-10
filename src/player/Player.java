package player;

import board.Board;
import game.Game;
import pieces.Color;

/**
 * Player class.
 */

public abstract class Player {
    /**
     * Color of Player's pieces.
     */
    protected Color color;
    /**
     * Current game.
     */
    protected Game game;
    /**
     * Move method.
     */
    public abstract String move(Board board, Color sideToMove);

    public Color getColor() {
        return color;
    }

    public Game getGame() {
        return game;
    }
}
