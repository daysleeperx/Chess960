package utils;

import board.Board;
import game.Game;
import pieces.Color;
import player.Human;

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
    public static String parseToFen(Board board, Color sideToMove) {
        StringJoiner stringJoiner = new StringJoiner("/");
        String fenString;

        for (int row = Board.HEIGHT - 1; row >= 0; row--) {
            StringBuilder currentRow = new StringBuilder();
            int count = 0;
            for (int col = 0; col < Board.WIDTH; col++) {
                if (board.getSquare(col, row).isOccupied()) {
                    if (count != 0) currentRow.append(count);
                    count = 0;
                    String piece = board.getSquare(col, row).getPiece().toFen();
                    currentRow.append(piece);
                } else {
                    count++;
                }
            }
            if (count != 0) currentRow.append(count);
            stringJoiner.add(currentRow.toString());
        }

        if (sideToMove == Color.BLACK) {
            fenString = stringJoiner.toString().concat(" b ");
        } else {
            fenString = stringJoiner.toString().concat(" w ");
        }

        for (Map.Entry<String, Boolean> entry: board.getCastlingRights().entrySet()) {
            if (entry.getValue()) {
                fenString = fenString.concat(entry.getKey());
            } else {
                fenString = fenString.concat("-");
            }
        }

        return fenString.replace("--", "-");

    }

    public static void main(String[] args) {
        Game g = new Game();
        Board b = new Board();
        b.setUpPieces(new Human(Color.WHITE, g), new Human(Color.BLACK, g));
        System.out.println(parseToFen(b, Color.BLACK).equals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b"));
        System.out.println(parseToFen(b, Color.BLACK));
        b.setNewPiecePosition(b.getSquare(4, 1).getPiece(), 4, 3);
        System.out.println(parseToFen(b, Color.BLACK).equals("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b"));
        b.setNewPiecePosition(b.getSquare(2, 6).getPiece(), 2, 4);
        System.out.println(parseToFen(b, Color.BLACK).equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR b"));
        b.setNewPiecePosition(b.getSquare(6, 0).getPiece(), 5, 2);
        System.out.println(parseToFen(b, Color.BLACK).equals("rnbqkbnr/pp1ppppp/8/2p5/4P3/5N2/PPPP1PPP/RNBQKB1R b"));

    }

}
