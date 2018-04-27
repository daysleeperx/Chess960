package board;

import pieces.*;
import player.Human;
import square.Square;

import java.util.*;

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
    private Map<String, Boolean> castlingRights = new HashMap<>();

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
                    boardArray[i][j] = new Square(j, i, new Pawn(j, i, Color.WHITE, player));
                }
            }
        }

        // set up white pieces
        whitePieces.add(new King(4, 0, Color.WHITE, player));
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
                    boardArray[i][j] = new Square(j, i, new Pawn(j, i, Color.BLACK, player));
                }
            }
        }

        // set up black pieces
        blackPieces.add(new King(4, 7, Color.BLACK, player));
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

    public boolean isCapture(Piece piece, int targetX, int targetY) {
        return (boardArray[targetY][targetX].isOccupied()
                && boardArray[targetY][targetX].getPiece().getColor() != piece.getColor());
    }

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
            return;

        }
        // queenside
        if (targetX == 2) {
            setNewPiecePosition(boardArray[king.y][4].getPiece(), 6, king.y);
            setNewPiecePosition(boardArray[king.y][7].getPiece(), 5, king.y);
//
        }

//        switch (move) {
//            case "e1g1":
//                setNewPiecePosition(boardArray[0][4].getPiece(), 6, 0);
//                setNewPiecePosition(boardArray[0][7].getPiece(), 5, 0);
//                castlingRights.put("K", false);
//                castlingRights.put("Q", false);
//                break;
//
//            case "e1c1":
//                setNewPiecePosition(boardArray[0][4].getPiece(), 2, 0);
//                setNewPiecePosition(boardArray[0][0].getPiece(), 3, 0);
//                castlingRights.put("K", false);
//                castlingRights.put("Q", false);
//                break;
//
//            case "e8g8":
//                setNewPiecePosition(boardArray[7][4].getPiece(), 6, 7);
//                setNewPiecePosition(boardArray[7][7].getPiece(), 5, 7);
//                castlingRights.put("k", false);
//                castlingRights.put("q", false);
//                break;
//
//            case "e8c8":
//                setNewPiecePosition(boardArray[7][4].getPiece(), 2, 7);
//                setNewPiecePosition(boardArray[7][0].getPiece(), 3, 7);
//                castlingRights.put("k", false);
//                castlingRights.put("q", false);
//                break;
//        }
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
     * Prints game.
     */
    public void printGame() {
        System.out.println("Removed pieces: \nWhite" + removedWhitePieces);
        System.out.println("Black" + removedBlackPieces);
        System.out.println(castlingRights);
        for (int row = boardArray.length - 1; row >= 0; row--) {
            System.out.println(Arrays.deepToString(boardArray[row]));
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(boardArray);
    }
}
