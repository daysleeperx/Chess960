package player;

import game.Game;
import pieces.Color;

/**
 * Represent Human Player.
 */

public class Human implements Player {
    /**
     * Color of Player's pieces.
     */
    private Color color;
    /**
     * Current game.
     */
    private Game game;
    /**
     * Indicates if player has won/lost the game.
     */
    private boolean isWinner = false;

    /**
     * Class constructor.
     *
     * @param color Color enum
     * @param game Game object
     */
    public Human(Color color, Game game) {
        this.color = color;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    /**
     * Move method.
     */

    @Override
    public void move() {

    }
}
