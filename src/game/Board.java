package game;

import pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represent Board.
 */

public class Board {
    /**
     * Board representation as an array of Square objects.
     */
    private Square[][] boardArray;
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
                if (i == 1 || i == 6) {
                    boardArray[i][j] = new Square(j, i, new Pawn(j, i, null));
                } else {
                    boardArray[i][j] = new Square(j, i);
                }
            }
        }

        pieces.add(new King(4, 0, null));
        pieces.add(new King(4, 7, null));
        pieces.add(new Queen(3, 0, null));
        pieces.add(new Queen(3, 7, null));
        pieces.add(new Knight(1, 0, null));
        pieces.add(new Knight(1, 7, null));
        pieces.add(new Knight(6, 0, null));
        pieces.add(new Knight(6, 7, null));
        pieces.add(new Bishop(2, 0, null));
        pieces.add(new Bishop(2, 7, null));
        pieces.add(new Bishop(5, 0, null));
        pieces.add(new Bishop(5, 7, null));
        pieces.add(new Rook(0, 0, null));
        pieces.add(new Rook(0, 7, null));
        pieces.add(new Rook(7, 0, null));
        pieces.add(new Rook(7, 7, null));

        pieces.forEach(piece -> boardArray[piece.y][piece.x] = new Square(piece.x, piece.y, piece));


    }

    @Override
    public String toString() {
        return Arrays.deepToString(boardArray);
    }

    public static void main(String[] args) {
        Board b = new Board();
        b.setUpPieces();
        for (Square[] row: b.boardArray) {
            System.out.println(Arrays.deepToString(row));
        }
    }
}
