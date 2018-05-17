package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import pieces.Color;
import pieces.Type;

import static gui.Main.SQUARE_SIZE;

/**
 * Represent Piece class used in GUI.
 */
public class Piece extends Pane {
    private Type type;
    private Color color;
    private int x, y;
    private ImageView imageView;


    private double mouseX, mouseY;
    private double oldX, oldY;

    /**
     * Creates a StackPane layout with default CENTER alignment.
     */
    public Piece(Type type, Color color, int x, int y) {
        this.type = type;
        this.color = color;
        this.x = x;
        this.y = y;

        move(x, y);

        switch (type) {
            case KING:
                switch (color) {
                    case WHITE:
                        imageView = new ImageView(new Image("img/king_white.png"));
                        break;
                    case BLACK:
                        imageView = new ImageView(new Image("img/king_black.png"));
                        break;
                }
                break;

            case QUEEN:
                switch (color) {
                    case WHITE:
                        imageView = new ImageView(new Image("img/queen_white.png"));
                        break;
                    case BLACK:
                        imageView = new ImageView(new Image("img/queen_black.png"));
                        break;
                }
                break;

            case ROOK:
                switch (color) {
                    case WHITE:
                        imageView = new ImageView(new Image("img/rook_white.png"));
                        break;
                    case BLACK:
                        imageView = new ImageView(new Image("img/rook_black.png"));
                        break;
                }
                break;

            case BISHOP:
                switch (color) {
                    case WHITE:
                        imageView = new ImageView(new Image("img/bishop_white.png"));
                        break;
                    case BLACK:
                        imageView = new ImageView(new Image("img/bishop_black.png"));
                        break;
                }
                break;

            case KNIGHT:
                switch (color) {
                    case WHITE:
                        imageView = new ImageView(new Image("img/knight_white.png"));
                        break;
                    case BLACK:
                        imageView = new ImageView(new Image("img/knight_black.png"));
                        break;
                }
                break;

            case PAWN:
                switch (color) {
                    case WHITE:
                        imageView = new ImageView(new Image("img/pawn_white.png"));
                        break;
                    case BLACK:
                        imageView = new ImageView(new Image("img/pawn_black.png"));
                        break;
                }
                break;
        }

        imageView.setFitWidth(SQUARE_SIZE);
        imageView.setFitHeight(SQUARE_SIZE);
        getChildren().add(imageView);

        setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        setOnMouseDragged(event -> {
            toFront();
            relocate(event.getSceneX() - mouseX + oldX, event.getSceneY() - mouseY + oldY);
        });
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {

        return color;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getOldX() {
        return oldX;
    }

    public double getOldY() {
        return oldY;
    }

    /**
     * Move the piece.
     *
     * @param targetX int
     * @param targetY int
     */
    public void move(int targetX, int targetY) {
        oldX = targetX * SQUARE_SIZE;
        oldY = targetY * SQUARE_SIZE;

        relocate(oldX, oldY);
    }

    /**
     * Abort the move.
     */
    public void abortMove() {
        relocate(oldX, oldY);
    }

    /**
     * Returns a string representation for the object.
     *
     * @return a string representation for the object.
     */
    @Override
    public String toString() {
        return type.toString() + " " + color.toString();
    }
}
