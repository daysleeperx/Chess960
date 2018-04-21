package utils;

import board.Board;
import game.Game;
import pieces.Color;
import pieces.Piece;
import pieces.Type;
import player.Human;
import square.Square;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Parses chess position to FEN notation.
 * https://en.wikipedia.org/wiki/Forsyth%E2%80%93Edwards_Notation
 */
public final class FenParser {

    /**
     * Parse to FEN notation.
     * Example FEN after move 1. e4: "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"
     *
     * @param board Square[]
     */
    public static String parseToFen(Square[][] board) {
        StringJoiner fenString = new StringJoiner("/");

        for (int row = board.length - 1; row >= 0; row--) {
            StringBuilder currentRow = new StringBuilder();
            int count = 0;
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isOccupied()) {
                    if (count != 0) currentRow.append(count);
                    count = 0;
                    String piece = board[row][col].getPiece().toFen();
                    currentRow.append(piece);
                } else {
                    count++;
                }

            }
            if (count != 0) currentRow.append(count);
            fenString.add(currentRow.toString());
        }
        // rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1
        return fenString.add(" b").toString();
    }

    public static void main(String[] args) {
        Game g = new Game();
        Board b = new Board();
        b.setUpPieces(new Human(Color.WHITE, g), new Human(Color.BLACK, g));
        System.out.println(parseToFen(b.getBoardArray()).equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"));
        b.setNewPiecePosition(b.getSquare(4, 1).getPiece(), 4, 3);
        System.out.println(parseToFen(b.getBoardArray()).equals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR"));
        b.setNewPiecePosition(b.getSquare(2, 6).getPiece(), 2, 4);
        System.out.println(parseToFen(b.getBoardArray()).equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR"));
        b.setNewPiecePosition(b.getSquare(6, 0).getPiece(), 5, 2);
        System.out.println(parseToFen(b.getBoardArray()).equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R"));

    }

}
