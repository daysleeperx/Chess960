package gui;

import board.Board;
import game.Game;
import gui.view.MainGameViewController;
import gui.view.MenuController;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import move.Move;
import pieces.Color;
import pieces.Type;
import stockfish.StockFish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.FenParser.parseToFen;
import static utils.Parser.*;

public class Main extends Application {
    /**
     * Primary stage.
     */
    private Stage primaryStage;
    private BorderPane rootLayout;
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
     * Board associated with the current game, that handles the logic.
     */
    private Board logicBoard;

    /**
     * Color representing side to move.
     */
    private Color sideToMove;

    /**
     * Stockfish.
     */
    private StockFish stockFish;
    /**
     * Kings map.
     */
    private Map<Color, Piece> kings = new HashMap<>();


    private Parent createGame() throws IOException {
        squareGroup.getChildren().clear();
        pieceGroup.getChildren().clear();
        game = new Game();
        game.createGame();
        logicBoard = game.getBoard();
        stockFish = new StockFish(Color.BLACK, game);
        stockFish.startEngine();
        sideToMove = Color.WHITE;
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

        Piece whiteKing = createPiece(Type.KING, Color.WHITE, 4, 7);
        Piece blackKing = createPiece(Type.KING, Color.BLACK, 4, 0);
        pieceList.add(whiteKing);
        pieceList.add(blackKing);
        kings.put(Color.WHITE, whiteKing);
        kings.put(Color.BLACK, blackKing);
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

        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("view/menuView.fxml"));
        Parent menu = loader.load();
        MenuController menuController = loader.getController();
        menuController.setMainApp(this);
        primaryStage.setTitle("Chess 960");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(menu));
        primaryStage.show();
    }

    public void goBack() throws IOException {
        start(primaryStage);
    }

    public void toGameScene() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/mainGameView.fxml"));
        rootLayout = loader.load();
        MainGameViewController mainGameViewController = loader.getController();
        mainGameViewController.setMainApp(this);

        // Show the scene containing the root layout.
        Scene scene = new Scene(rootLayout);
        rootLayout.setCenter(createGame());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Chess960");
        primaryStage.setResizable(false);


        // Game loop
        new AnimationTimer() {

            /**
             * This method needs to be overridden by extending classes. It is going to
             * be called in every frame while the {@code AnimationTimer} is active.
             *
             * @param now The timestamp of the current frame given in nanoseconds. This
             *            value will be the same for all {@code AnimationTimers} called
             *            during one frame.
             **/
            @Override
            public void handle(long now) {
                // show animation if game is over
                if (game.isGameOver(sideToMove)) {
                    ImageView im = new ImageView(new Image("img/buble.png"));
                    im.setFitWidth(150);
                    im.setFitHeight(140);
                    im.setLayoutX(kings.get(sideToMove).getOldX());
                    im.setLayoutY(kings.get(sideToMove).getOldY() - SQUARE_SIZE * 2);
                    im.setOpacity(0);
                    pieceGroup.getChildren().add(im);
                    FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), im);
                    fadeTransition.setFromValue(0.0);
                    fadeTransition.setToValue(1.0);
                    fadeTransition.play();
                    stop();
                }

                if (sideToMove == Color.BLACK) {
                    String moveString0 = stockFish.getBestMove(parseToFen(logicBoard, sideToMove), 10);
                    int[] moveArr = parseAlgebraicToGui(moveString0);

                    Piece currentPiece = board[moveArr[1]][moveArr[0]].getPiece();

                    int targetX = moveArr[2];
                    int targetY = moveArr[3];

                    movePiece(currentPiece, targetX, targetY);
                }
            }
        }.start();

        primaryStage.setScene(scene);
    }

    /**
     * Factory creating pieces used in GUI.
     *
     * @param type  Type
     * @param color Color
     * @param x     int
     * @param y     int
     * @return Piece object
     */
    private Piece createPiece(Type type, Color color, int x, int y) {
        Piece piece = new Piece(type, color, x, y);

        piece.setOnMouseReleased(e -> {
            int targetX = toBoard(piece.getLayoutX());
            int targetY = toBoard(piece.getLayoutY());

            movePiece(piece, targetX, targetY);
        });

        return piece;
    }

    /**
     * Execute piece movement on GUI board. Set new piece locations.
     * Handle castling and capturing.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     */
    public void movePiece(Piece piece, int targetX, int targetY) {
        Move move = tryMove(piece, targetX, targetY);

        int currX = toBoard(piece.getOldX());
        int currY = toBoard(piece.getOldY());

        switch (move.getMoveType()) {
            case CAPTURE:
                piece.move(targetX, targetY);
                board[currY][currX].setPiece(null);
                Piece capturedPiece = board[targetY][targetX].getPiece();
                board[targetY][targetX].setPiece(null);
                pieceGroup.getChildren().remove(capturedPiece);
                board[targetY][targetX].setPiece(piece);
                makeLogicMove(currX, currY, targetX, targetY);
                break;
            case MOVE:
                piece.move(targetX, targetY);
                board[currY][currX].setPiece(null);
                board[targetY][targetX].setPiece(piece);
                makeLogicMove(currX, currY, targetX, targetY);
                break;
            case CASTLE:
                String moveString = parseToAlgebraicGui(currX, currY, targetX, targetY);
                piece.move(targetX, targetY);
                board[targetY][targetX].setPiece(piece);
                board[currY][currX].setPiece(null);
                makeLogicMove(currX, currY, targetX, targetY);
                switch (moveString) {
                    case "e1g1":
                        Piece rook = board[7][7].getPiece();
                        rook.move(5, 7);
                        board[7][7].setPiece(null);
                        board[7][5].setPiece(rook);
                        break;
                    case "e1c1":
                        Piece rook1 = board[7][0].getPiece();
                        rook1.move(3, 7);
                        board[7][0].setPiece(null);
                        board[7][3].setPiece(rook1);
                        break;
                    case "e8g8":
                        Piece rook2 = board[0][7].getPiece();
                        rook2.move(5, 0);
                        board[0][7].setPiece(null);
                        board[0][5].setPiece(rook2);
                        break;
                    case "e8c8":
                        Piece rook3 = board[0][0].getPiece();
                        rook3.move(3, 0);
                        board[0][0].setPiece(null);
                        board[0][3].setPiece(rook3);
                        break;
                }
                break;
            case NONE:
                piece.abortMove();
                break;
        }
    }

    /**
     * Update game state on logic board. Switch side to move.
     *
     * @param currentX int
     * @param currentY int
     * @param targetX  int
     * @param targetY  int
     */
    private void makeLogicMove(int currentX, int currentY, int targetX, int targetY) {
        String moveString = parseToAlgebraicGui(currentX, currentY, targetX, targetY);
        int[] moveArray = parseInput(moveString);
        logicBoard.movePiece(logicBoard.getSquare(moveArray[0], moveArray[1]).getPiece(), moveArray[2], moveArray[3]);
        logicBoard.printGame();
        sideToMove = (sideToMove == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Move tryMove(Piece piece, int targetX, int targetY) {
        String move = parseToAlgebraicGui((int) piece.getOldX() / SQUARE_SIZE, (int) piece.getOldY() / SQUARE_SIZE, targetX, targetY);
        System.out.println("Possible moves: " + logicBoard.getPossibleMoves(sideToMove));
        System.out.println(move);


        if (piece.getColor() == sideToMove && inBounds(targetX, targetY) && logicBoard.getPossibleMoves(sideToMove).contains(move)) {
            // TODO: improve check for castling
            if (piece.getType() == Type.KING && (move.equals("e1g1") || move.equals("e1c1") || move.equals("e8g8") || move.equals("e8c8"))) {
                return new Move(MoveType.CASTLE);
            }
            if (!board[targetY][targetX].hasPiece()) {
                return new Move(MoveType.MOVE);
            }

            if (board[targetY][targetX].hasPiece()) {
                return new Move(MoveType.CAPTURE);
            }
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
}
