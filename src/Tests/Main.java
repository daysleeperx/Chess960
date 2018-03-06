package Tests;

import Pieces.*;

public class Main {
    public static void main(String[] args) {
        Piece k = new King(2, 3, null);
        Piece q = new Queen(2, 3, null);
        Piece r = new Rook(2, 3, null);
        Piece kn = new Knight(2, 3, null);
        Piece b = new Bishop(2, 3, null);
        Piece p = new Pawn(2, 3, null);

        System.out.println(String.format("%s%s%s%s%s%s", k, q, r, kn, b, p));
        System.out.println(k.getType());
        System.out.println(kn.getType());

    }
}
