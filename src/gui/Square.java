package gui;


import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static gui.Main.SQUARE_SIZE;

/**
 * Represent Square class used in GUI.
 */
public class Square extends Rectangle {
    private Piece piece;
    private boolean isLight;
    int x, y;

    /**
     * Class constructor.
     *
     * @param isLight boolean
     * @param x       int
     * @param y       int
     */
    public Square(boolean isLight, int x, int y) {
        this.isLight = isLight;
        this.x = x;
        this.y = y;

        setWidth(SQUARE_SIZE);
        setHeight(SQUARE_SIZE);

        relocate(x * SQUARE_SIZE, y * SQUARE_SIZE);
        setFill(isLight ? Color.valueOf("#9f9f9f") : Color.valueOf("#666666"));
    }

    /**
     * Checks if square is occupied.
     * @return {@code true}, otherwise {@code false}
     */
    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return "Square{" +
                "piece=" + piece +
                ", isLight=" + isLight +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
