package gui;

import game.Game;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import move.Move;
import pieces.Color;
import pieces.Type;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    /**
     * Dimension of board and squares.
     */
    static final int SQUARE_SIZE = 70;
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    /**
     * Observables.
     */
    private Group squareGroup = new Group();
    private Group pieceGroup = new Group();

    /**
     * Board 2D array.
     */
    private Square[][] board = new Square[HEIGHT][WIDTH];

    /**
     * Game currently played.
     */
    private Game game;

    /**
     * Color representing side to move.
     */
    private Color sideToMove;


    private Parent createGame() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH * SQUARE_SIZE, HEIGHT * SQUARE_SIZE);
        root.getChildren().addAll(squareGroup, pieceGroup);

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                Square square = new Square((col + row) % 2 == 0, col, row);
                board[row][col] = square;

                squareGroup.getChildren().add(square);

                Piece piece = null;

                if (row == 1) {
                    piece = createPiece(Type.PAWN, Color.BLACK, col, row);
                }

                if (row == 6) {
                    piece = createPiece(Type.PAWN, Color.WHITE, col, row);
                }

                if (piece != null) {
                    square.setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }

        List<Piece> pieceList = new ArrayList<>();

        pieceList.add(createPiece(Type.KING, Color.WHITE, 4, 7));
        pieceList.add(createPiece(Type.KING, Color.BLACK, 4, 0));
        pieceList.add(createPiece(Type.ROOK, Color.WHITE, 0, 7));
        pieceList.add(createPiece(Type.ROOK, Color.WHITE, 7, 7));
        pieceList.add(createPiece(Type.ROOK, Color.BLACK, 0, 0));
        pieceList.add(createPiece(Type.ROOK, Color.BLACK, 7, 0));
        pieceList.add(createPiece(Type.KNIGHT, Color.WHITE, 1, 7));
        pieceList.add(createPiece(Type.KNIGHT, Color.WHITE, 6, 7));
        pieceList.add(createPiece(Type.KNIGHT, Color.BLACK, 1, 0));
        pieceList.add(createPiece(Type.KNIGHT, Color.BLACK, 6, 0));
        pieceList.add(createPiece(Type.BISHOP, Color.WHITE, 5, 7));
        pieceList.add(createPiece(Type.BISHOP, Color.WHITE, 2, 7));
        pieceList.add(createPiece(Type.BISHOP, Color.BLACK, 5, 0));
        pieceList.add(createPiece(Type.BISHOP, Color.BLACK, 2, 0));
        pieceList.add(createPiece(Type.QUEEN, Color.WHITE, 3, 7));
        pieceList.add(createPiece(Type.QUEEN, Color.BLACK, 3, 0));

        pieceList.forEach(piece -> {
            board[(int) piece.getY()][(int) piece.getX()].setPiece(piece);
            pieceGroup.getChildren().add(piece);
        });

        sideToMove = Color.WHITE;

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createGame());
        primaryStage.setTitle("Chess960");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Color sideToMove = Color.WHITE;

        // Game loop
        new AnimationTimer() {

            /**
             * This method needs to be overridden by extending classes. It is going to
             * be called in every frame while the {@code AnimationTimer} is active.
             *
             * @param now The timestamp of the current frame given in nanoseconds. This
             *            value will be the same for all {@code AnimationTimers} called
             *            during one frame.
             */
            @Override
            public void handle(long now) {
                scene.setOnMousePressed(event -> {
                    int currentX = (int) ((event.getSceneX() - event.getSceneX() % SQUARE_SIZE) / SQUARE_SIZE);
                    int currentY = (int) ((event.getSceneY() - event.getSceneY() % SQUARE_SIZE) / SQUARE_SIZE);

                    Square currentSquare = board[currentY][currentX];
                    Piece currentPiece = currentSquare.getPiece();

                    if (currentPiece != null) System.out.println(currentPiece);
                });
            }
        }.start();


        primaryStage.show();
    }

    private Piece createPiece(Type type, Color color, int x, int y) {
        Piece piece = new Piece(type, color, x, y);

        piece.setOnMouseReleased(event -> {
            int targetX = toBoard(piece.getLayoutX());
            int targetY = toBoard(piece.getLayoutY());

            Move move = tryMove(piece, targetX, targetY);

            int currentX = toBoard(piece.getOldX());
            int currentY = toBoard(piece.getOldY());

            switch (move.getMoveType()) {
                case MOVE:
                    piece.move(targetX, targetY);
                    board[currentY][currentX].setPiece(null);
                    board[targetY][targetX].setPiece(piece);
                    sideToMove = (sideToMove == Color.WHITE) ? Color.BLACK : Color.WHITE;
                    break;
                case NONE:
                    piece.abortMove();
                    break;
            }
        });

        return piece;
    }

    private Move tryMove(Piece piece, int targetX, int targetY) {
        if (piece.getColor() == sideToMove && inBounds(targetX, targetY) && !board[targetY][targetX].hasPiece()) {
            return new Move(MoveType.MOVE);
        }
        return new Move(MoveType.NONE);
    }

    /**
     * Determine if coordinates are in bounds.
     *
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */
    private boolean inBounds(int targetX, int targetY) {
        return targetX >= 0 && targetX < WIDTH && targetY >= 0 && targetY < HEIGHT;

    }

    /**
     * Method converting pixels to board coordinates.
     * Used in piece movement.
     *
     * @param pixel double
     * @return int
     */
    private int toBoard(double pixel) {
        return (int) (pixel + SQUARE_SIZE / 2) / SQUARE_SIZE;
    }

    public void printGroup() {
        for (Node sq: squareGroup.getChildren()) {
            System.out.println(sq);
        }
        System.out.println("**************************");
    }

}
