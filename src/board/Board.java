package board;

import pieces.*;
import square.Square;

import java.util.*;

/**
 * Represent Board.
 */

public class Board {
    /**
     * Board representation as an array of Square objects.
     */
    public Square[][] boardArray;

    /**
     * Class constructor.
     */
    public Board() {
        boardArray = new Square[8][8];
    }

    /**
     * Set up pieces on the board. Starting position.
     * The 960 shuffle should take also place in this method.
     */
    public void setUpPieces() {

        List<Piece> pieces = new ArrayList<>();

        // adding Square objects and pawns
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 1) {
                    boardArray[i][j] = new Square(j, i, new Pawn(j, i, Color.WHITE));
                } else if (i == 6) {
                    boardArray[i][j] = new Square(j, i, new Pawn(j, i, Color.BLACK));
                } else {
                    boardArray[i][j] = new Square(j, i);
                }
            }
        }

        pieces.add(new King(4, 0, Color.WHITE));
        pieces.add(new King(4, 7, Color.BLACK));

        pieces.add(new Queen(3, 0, Color.WHITE));
        pieces.add(new Queen(3, 7, Color.BLACK));

        pieces.add(new Knight(1, 0, Color.WHITE));
        pieces.add(new Knight(1, 7, Color.BLACK));
        pieces.add(new Knight(6, 0, Color.WHITE));
        pieces.add(new Knight(6, 7, Color.BLACK));

        pieces.add(new Bishop(2, 0, Color.WHITE));
        pieces.add(new Bishop(2, 7, Color.BLACK));
        pieces.add(new Bishop(5, 0, Color.WHITE));
        pieces.add(new Bishop(5, 7, Color.BLACK));

        pieces.add(new Rook(0, 0, Color.WHITE));
        pieces.add(new Rook(0, 7, Color.BLACK));
        pieces.add(new Rook(7, 0, Color.WHITE));
        pieces.add(new Rook(7, 7, Color.BLACK));

        pieces.forEach(piece -> boardArray[piece.y][piece.x] = new Square(piece.x, piece.y, piece));


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
                || boardArray[targetY][targetY].getPiece().getColor() != piece.getColor());
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
    }

    /**
     * Prints game.
     */
    public void printGame() {
        for (int row = boardArray.length - 1; row >= 0; row--) {
            System.out.println(Arrays.deepToString(boardArray[row]));
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(boardArray);
    }

    public static void main(String[] args) {


    }
}
