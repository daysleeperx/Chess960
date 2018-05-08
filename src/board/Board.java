package board;

import pieces.*;
import player.Human;
import square.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static utils.FenParser.parseToFen;
import static utils.Parser.parseToAlgebraic;

/**
 * Represent Board Class.
 */

public class Board {
    /**
     * Board representation as an array of Square objects.
     */
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    public Square[][] boardArray;
    private List<Piece> whitePieces = new ArrayList<>();
    private List<Piece> blackPieces = new ArrayList<>();
    private List<Piece> removedWhitePieces = new LinkedList<>();
    private List<Piece> removedBlackPieces = new LinkedList<>();
    private Map<String, Boolean> castlingRights = new LinkedHashMap<>();
    private King whiteKing, blackKing;

    /**
     * Class constructor. Creates and empty board.
     */
    public Board() {
        boardArray = new Square[8][8];

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boardArray[i][j] = new Square(j, i);
            }
        }
    }

    public Square[][] getBoardArray() {
        return boardArray;
    }

    public List<Piece> getRemovedWhitePieces() {
        return removedWhitePieces;
    }

    public List<Piece> getRemovedBlackPieces() {
        return removedBlackPieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public Map<String, Boolean> getCastlingRights() {
        return castlingRights;
    }

    /**
     * Clear board. Helper method for testing.
     */
    public void clearBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                boardArray[i][j].setPiece(null);
            }
        }
    }

    /**
     * Return enemy's pieces.
     *
     * @param player Player
     * @return List
     */
    public List<Piece> getEnemyPieces(Human player) {
        if (player.getColor() == Color.WHITE) return blackPieces;
        return whitePieces;
    }

    /**
     * Return specific square object.
     *
     * @param x int
     * @param y int
     * @return Square object
     */
    public Square getSquare(int x, int y) {
        return boardArray[y][x];
    }

    /**
     * Set up pieces on the board. Starting position.
     * The 960 shuffle should take also place in this method.
     */
    public void setUpPieces(Human player1, Human player2) {
        setUpWhitePieces(player1);
        setUpBlackPieces(player2);

        castlingRights.put("K", true);
        castlingRights.put("Q", true);
        castlingRights.put("k", true);
        castlingRights.put("q", true);
    }

    /**
     * Sets up White's pieces.
     */
    public void setUpWhitePieces(Human player) {
        // set up pawns
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1) {
                    Piece currentPawn = new Pawn(j, i, Color.WHITE, player);
                    boardArray[i][j] = new Square(j, i, currentPawn);
                    whitePieces.add(currentPawn);
                }
            }
        }

        // set up white pieces
        whiteKing = new King(4, 0, Color.WHITE, player);
        whitePieces.add(whiteKing);
        whitePieces.add(new Queen(3, 0, Color.WHITE, player));
        whitePieces.add(new Knight(1, 0, Color.WHITE, player));
        whitePieces.add(new Knight(6, 0, Color.WHITE, player));
        whitePieces.add(new Bishop(2, 0, Color.WHITE, player));
        whitePieces.add(new Bishop(5, 0, Color.WHITE, player));
        whitePieces.add(new Rook(0, 0, Color.WHITE, player));
        whitePieces.add(new Rook(7, 0, Color.WHITE, player));


        whitePieces.forEach(piece -> boardArray[piece.y][piece.x] = new Square(piece.x, piece.y, piece));
    }

    /**
     * Sets up Black's pieces.
     */
    public void setUpBlackPieces(Human player) {
        // set up pawns
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 6) {
                    Piece currentPawn = new Pawn(j, i, Color.BLACK, player);
                    boardArray[i][j] = new Square(j, i, currentPawn);
                    blackPieces.add(currentPawn);
                }
            }
        }

        // set up black pieces
        blackKing = new King(4, 7, Color.BLACK, player);
        blackPieces.add(blackKing);
        blackPieces.add(new Queen(3, 7, Color.BLACK, player));
        blackPieces.add(new Knight(1, 7, Color.BLACK, player));
        blackPieces.add(new Knight(6, 7, Color.BLACK, player));
        blackPieces.add(new Bishop(2, 7, Color.BLACK, player));
        blackPieces.add(new Bishop(5, 7, Color.BLACK, player));
        blackPieces.add(new Rook(0, 7, Color.BLACK, player));
        blackPieces.add(new Rook(7, 7, Color.BLACK, player));


        blackPieces.forEach(piece -> boardArray[piece.y][piece.x] = new Square(piece.x, piece.y, piece));
    }

    /**
     * Get List of all possible moves for either black or white.
     *
     * @param color Color
     * @return List
     */
    public List<String> getPossibleMoves(Color color) {
        List<Piece> currentPieces = (color == Color.WHITE) ? getWhitePieces() : getBlackPieces();
        King king = (color == Color.WHITE) ? whiteKing : blackKing;
        List<String> moves = new ArrayList<>();

        for (Piece piece : currentPieces) {
            for (int row = 0; row < HEIGHT; row++) {
                for (int col = 0; col < WIDTH; col++) {
                    if (!boardArray[row][col].isOccupied()
                            || boardArray[row][col].getPiece().getColor() != color) {
                        if (piece.isValidMove(col, row) && isValidPath(piece, col, row)) {
                            if (king.isInCheck()) {
                                // TODO: set piece locations and temporary add/remove piece from corresponding lists
                                // make pseudo-move
                                Piece captured = (boardArray[row][col].isOccupied()) ? boardArray[row][col].getPiece() : null;
                                boardArray[row][col].setPiece(piece);
                                boardArray[row][col].setPiece(null);
                                if (!king.isInCheck()) moves.add(parseToAlgebraic(piece, col, row));
                                // undo move
                                boardArray[row][col].setPiece(captured);
                                boardArray[piece.y][piece.x].setPiece(piece);
                            } else {
                                moves.add(parseToAlgebraic(piece, col, row));
                            }
                        }
                    }
                }
            }
        }

        return moves;
    }

    /**
     * Moves the piece.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     */
    public void movePiece(Piece piece, int targetX, int targetY) {
        if (piece.isValidMove(targetX, targetY) && isValidPath(piece, targetX, targetY)) {
            if (isCapture(piece, targetX, targetY)) capturePiece(piece, targetX, targetY);
            if (isCastling(piece, targetX, targetY)) {
                castle((King) piece, targetX, targetY);
                return;
            }

            setNewPiecePosition(piece, targetX, targetY);
            // TODO: copy this to castle method?
            // Casting done in order to set hasMoved to true
            if (piece.getType() == Type.PAWN) ((Pawn) piece).setHasMoved(true);
            if (piece.getType() == Type.ROOK) ((Rook) piece).setHasMoved(true);
            if (piece.getType() == Type.KING) ((King) piece).setHasMoved(true);
        } else {
            System.out.println("Invalid Move!");
        }
    }

    /**
     * Check if path is valid considering current position on the board.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     * @return {@code true} if is valid, otherwise {@code false}
     */
    public boolean isValidPath(Piece piece, int targetX, int targetY) {
        List<int[]> path = piece.generatePath(targetX, targetY); // current path

        return (isValidLeaping(piece, path) && isNotOrigin(piece, targetX, targetY)
                && isValidTarget(piece, targetX, targetY));
    }

    /**
     * Checks for leaping in current position/ path. Excluding target square.
     *
     * @param piece Piece object
     * @param path  List of coordinates
     * @return boolean
     */
    public boolean isValidLeaping(Piece piece, List<int[]> path) {
        // always true for Knights and Kings
        if (piece.getType() == Type.KNIGHT || piece.getType() == Type.KING) return true;

        for (int i = 0; i < path.size() - 1; i++) {
            int col = path.get(i)[0];
            int row = path.get(i)[1];
            // if one of the squares is occupied return false
            if (boardArray[row][col].isOccupied()) return false;
        }

        return true;
    }

    /**
     * Checks if piece does not move to the same square.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     * @return {@code true} if targetX is not current x or targetY is not current y,
     * otherwise {@code false}
     */
    public boolean isNotOrigin(Piece piece, int targetX, int targetY) {
        return (targetX != piece.getX() || targetY != piece.getY());
    }

    /**
     * Checks if target square is free or is occupied by enemy's piece.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     * @return {@code true} if square is free or is occupied by enemy's piece
     * otherwise {@code false}
     */
    public boolean isValidTarget(Piece piece, int targetX, int targetY) {
        return (!boardArray[targetY][targetX].isOccupied()
                || boardArray[targetY][targetX].getPiece().getColor() != piece.getColor());
    }

    /**
     * Checks if move results in a capture.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */

    public boolean isCapture(Piece piece, int targetX, int targetY) {
        return (boardArray[targetY][targetX].isOccupied()
                && boardArray[targetY][targetX].getPiece().getColor() != piece.getColor());
    }

    /**
     * Checks if move results in castling.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     * @return {@code true}, otherwise {@code false}
     */

    public boolean isCastling(Piece piece, int targetX, int targetY) {
        if (piece.getType() == Type.KING && !((King) piece).isHasMoved()) {
            switch (piece.getColor()) {
                case WHITE:
                    return ((targetX == 6 && targetY == 0)
                            || (targetX == 2 && targetY == 0));
                case BLACK:
                    return ((targetX == 6 && targetY == 7)
                            || (targetX == 2 && targetY == 7));
            }
        }
        return false;
    }

    /**
     * Captures piece.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     */
    public void capturePiece(Piece piece, int targetX, int targetY) {
        Piece capturedPiece = boardArray[targetY][targetX].getPiece();

        switch (piece.getColor()) {
            case WHITE:
                removedBlackPieces.add(capturedPiece);
                blackPieces.remove(capturedPiece);
                break;

            case BLACK:
                removedWhitePieces.add(capturedPiece);
                whitePieces.remove(capturedPiece);
                break;
        }
    }

    /**
     * Handles the castling move.
     *
     * @param move String
     */
    public void castle(King king, int targetX, int targetY) {
        // kingside
        if (targetX == 6) {
            setNewPiecePosition(boardArray[king.y][4].getPiece(), 6, king.y);
            setNewPiecePosition(boardArray[king.y][7].getPiece(), 5, king.y);

        }
        // queenside
        if (targetX == 2) {
            setNewPiecePosition(boardArray[king.y][4].getPiece(), 2, king.y);
            setNewPiecePosition(boardArray[king.y][0].getPiece(), 3, king.y);
        }

        // adjust castling rights
        if (king.getColor() == Color.WHITE) {
            castlingRights.put("K", false);
            castlingRights.put("Q", false);
        } else {
            castlingRights.put("k", false);
            castlingRights.put("q", false);
        }

    }

    /**
     * Executes move. Changes the boardArray and changes the coordinates of Piece.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     */
    public void setNewPiecePosition(Piece piece, int targetX, int targetY) {
        // original coordinates
        int currentX = piece.getX();
        int currentY = piece.getY();

        // TODO: make a separate method?
        // adjust castling rights if piece is rook or king
        if (piece.getType() == Type.ROOK) {
            if (currentX == 7 && currentY == 0) castlingRights.put("K", false);
            if (currentX == 0 && currentY == 0) castlingRights.put("Q", false);
            if (currentX == 7 && currentY == 7) castlingRights.put("k", false);
            if (currentX == 0 && currentY == 7) castlingRights.put("q", false);
        }

        if (piece.getType() == Type.KING) {
            if (piece.getColor() == Color.WHITE) {
                castlingRights.put("K", false);
                castlingRights.put("Q", false);
            } else {
                castlingRights.put("k", false);
                castlingRights.put("q", false);
            }
        }

        // change the board array
        boardArray[targetY][targetX].setPiece(piece);
        boardArray[currentY][currentX].setPiece(null);

        // set new coordinates
        piece.setX(targetX);
        piece.setY(targetY);
    }

    /**
     * Prints game.
     */
    public void printGame() {
        System.out.println("White pieces " + whitePieces);
        System.out.println("Black pieces " + blackPieces);
        System.out.println("Removed pieces: \nWhite" + removedWhitePieces);
        System.out.println("Black" + removedBlackPieces);
        System.out.println(castlingRights);
        for (int row = boardArray.length - 1; row >= 0; row--) {
            System.out.println(Arrays.deepToString(boardArray[row]));
        }
        System.out.println(parseToFen(this));
    }

    @Override
    public String toString() {
        return Arrays.deepToString(boardArray);
    }
}
