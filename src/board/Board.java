package board;

import pieces.*;
import square.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Represent Board Class.
 */

public class Board {
    /**
     * Board representation as an array of Square objects.
     */
    public Square[][] boardArray;
    private List<Piece> whitePieces = new ArrayList<>();
    private List<Piece> blackPieces = new ArrayList<>();
    private List<Piece> removedWhitePieces = new LinkedList<>();
    private List<Piece> removedBlackPieces = new LinkedList<>();

    /**
     * Class constructor. Creates and empty board.
     */
    public Board() {
        boardArray = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
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

    /**
     * Set up pieces on the board. Starting position.
     * The 960 shuffle should take also place in this method.
     */
    public void setUpPieces() {
        setUpWhitePieces();
        setUpBlackPieces();
    }

    /**
     * Sets up White's pieces.
     */
    public void setUpWhitePieces() {
        // set up pawns
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1) {
                    boardArray[i][j] = new Square(j, i, new Pawn(j, i, Color.WHITE));
                }
            }
        }

        // set up white pieces
        whitePieces.add(new King(4, 0, Color.WHITE));
        whitePieces.add(new Queen(3, 0, Color.WHITE));
        whitePieces.add(new Knight(1, 0, Color.WHITE));
        whitePieces.add(new Knight(6, 0, Color.WHITE));
        whitePieces.add(new Bishop(2, 0, Color.WHITE));
        whitePieces.add(new Bishop(5, 0, Color.WHITE));
        whitePieces.add(new Rook(0, 0, Color.WHITE));
        whitePieces.add(new Rook(7, 0, Color.WHITE));


        whitePieces.forEach(piece -> boardArray[piece.y][piece.x] = new Square(piece.x, piece.y, piece));
    }

    /**
     * Sets up Black's pieces.
     */
    public void setUpBlackPieces() {
        // set up pawns
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 6) {
                    boardArray[i][j] = new Square(j, i, new Pawn(j, i, Color.BLACK));
                }
            }
        }

        // set up black pieces
        blackPieces.add(new King(4, 7, Color.BLACK));
        blackPieces.add(new Queen(3, 7, Color.BLACK));
        blackPieces.add(new Knight(1, 7, Color.BLACK));
        blackPieces.add(new Knight(6, 7, Color.BLACK));
        blackPieces.add(new Bishop(2, 7, Color.BLACK));
        blackPieces.add(new Bishop(5, 7, Color.BLACK));
        blackPieces.add(new Rook(0, 7, Color.BLACK));
        blackPieces.add(new Rook(7, 7, Color.BLACK));


        blackPieces.forEach(piece -> boardArray[piece.y][piece.x] = new Square(piece.x, piece.y, piece));
    }

    /**
     * Moves the piece.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     */
    public boolean movePiece(Piece piece, int targetX, int targetY) {
        if (piece.getType() == Type.PAWN && pawnCanCapture((Pawn) piece, targetX, targetY)) {
            if (piece.getColor() == Color.WHITE) {
                removedBlackPieces.add(boardArray[targetY][targetX].getPiece());
            } else {
                removedWhitePieces.add(boardArray[targetY][targetX].getPiece());
            }
            executeMove(piece, targetX, targetY);
            return true;
        }

        if (piece.isValidMove(targetX, targetY) && isValidPath(piece, targetX, targetY)) {
            if (isCapture(piece, targetX, targetY)) {
                if (piece.getColor() == Color.WHITE) {
                    removedBlackPieces.add(boardArray[targetY][targetX].getPiece());
                } else {
                    removedWhitePieces.add(boardArray[targetY][targetX].getPiece());
                }
            }
            executeMove(piece, targetX, targetY);
            return true;
        } else {
            System.out.println("Invalid Move!");
            return false;
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
        // pawn logic is handled in pawnCanCapture movement
        if (piece.getType() == Type.PAWN && boardArray[targetY][targetX].isOccupied()) return false;
        return (!boardArray[targetY][targetX].isOccupied()
                || boardArray[targetY][targetX].getPiece().getColor() != piece.getColor());
    }

    public boolean isCapture(Piece piece, int targetX, int targetY) {
        // pawn capture is handled separately in pawnCanCapture method
        if (piece.getType() == Type.PAWN) return false;

        return (boardArray[targetY][targetX].isOccupied()
                && boardArray[targetY][targetX].getPiece().getColor() != piece.getColor());
    }

    /**
     * Executes move. Changes the boardArray and changes the coordinates of Piece.
     *
     * @param piece   Piece object
     * @param targetX int
     * @param targetY int
     */
    public void executeMove(Piece piece, int targetX, int targetY) {
        // original coordinates
        int currentX = piece.getX();
        int currentY = piece.getY();

        // change the board array
        boardArray[targetY][targetX].setPiece(piece);
        boardArray[currentY][currentX].setPiece(null);

        // set new coordinates
        piece.setX(targetX);
        piece.setY(targetY);

        // Casting done in order to set hasMoved to true
        if (piece.getType() == Type.PAWN) ((Pawn) piece).setHasMoved(true);
        if (piece.getType() == Type.ROOK) ((Rook) piece).setHasMoved(true);
        if (piece.getType() == Type.KING) ((King) piece).setHasMoved(true);
    }

    /**
     * Special method for handling pawn capturing logic.
     *
     * @param pawn    Pawn object
     * @param targetX int
     * @param targetY int
     * @return
     */
    public boolean pawnCanCapture(Pawn pawn, int targetX, int targetY) {
        if (pawn.getColor() == Color.WHITE) {
            int col = Math.abs(targetX - pawn.getX());
            int row = targetY - pawn.getY();

            return ((col == 1) && (row == 1) && boardArray[targetY][targetX].isOccupied()
                    && boardArray[targetY][targetX].getPiece().getColor() != Color.WHITE);
        } else {
            int col = Math.abs(targetX - pawn.getX());
            int row = targetY - pawn.getY();

            return ((col == 1) && (row == -1) && boardArray[targetY][targetX].isOccupied()
                    && boardArray[targetY][targetX].getPiece().getColor() != Color.BLACK);
        }
    }

    /**
     * Prints game.
     */
    public void printGame() {
        System.out.println("Removed pieces: \nWhite" + removedWhitePieces);
        System.out.println("Black" + removedBlackPieces);
        for (int row = boardArray.length - 1; row >= 0; row--) {
            System.out.println(Arrays.deepToString(boardArray[row]));
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(boardArray);
    }
}
