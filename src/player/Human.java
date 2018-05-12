package player;

import board.Board;
import game.Game;
import pieces.Color;

import java.util.Scanner;

/**
 * Represent Human Player.
 */

public class Human extends Player {
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

    public Color getColor() {
        return color;
    }

    /**
     * Move method.
     *
     * @param board
     * @param sideToMove
     */
    @Override
    public String move(Board board, Color sideToMove) {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
