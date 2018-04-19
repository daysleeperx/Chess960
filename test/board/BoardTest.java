package board;

import game.Game;
import org.junit.Before;
import org.junit.Test;
import pieces.*;
import player.Human;

import static org.junit.Assert.*;

public class BoardTest {
    private Human player1;
    private Human player2;
    private Board board;
    private Bishop bishop;
    private Queen queen;
    private Rook rook;
    private King king;
    private Knight knight;
    private Knight blackKnight;
    private Pawn whitePawn;
    private Pawn blackPawn;

    @Before
    public void setUp() {
        // set up board and pieces for testing
        Game g = new Game();
        board = new Board();
        player1 = new Human(Color.WHITE, g);
        player2 = new Human(Color.BLACK, g);
        bishop = new Bishop(0, 0, Color.WHITE);
        queen = new Queen(0, 0, Color.BLACK);
        rook = new Rook(0, 0, Color.WHITE);
        king = new King(0, 0, Color.WHITE);
        knight = new Knight(0, 0, Color.WHITE);
        blackKnight = new Knight(0, 0, Color.BLACK);
        whitePawn = new Pawn(0, 0, Color.WHITE);
        blackPawn = new Pawn(0, 0, Color.BLACK);

    }

    @Test
    public void isValidPathEmptyBoard() {
        // bishop from a1 to h8
        board.boardArray[0][0].setPiece(bishop);
        assertTrue(board.isValidPath(bishop, 7, 7));
        // rook from h1 to h8
        rook.setX(7);
        board.boardArray[0][7].setPiece(rook);
        assertTrue(board.isValidPath(rook, 7, 7));
        // knight from e4 to f6
        knight.setX(4);
        knight.setY(3);
        board.boardArray[3][4].setPiece(knight);
        assertTrue(board.isValidPath(knight, 4, 5));
        // pawn from e7 to e5
        blackPawn.setX(4);
        blackPawn.setY(6);
        board.boardArray[6][4].setPiece(blackPawn);
        assertTrue(board.isValidPath(blackPawn, 4, 4));
        // queen from h8 to a8
        queen.setY(7);
        queen.setX(7);
        board.boardArray[7][7].setPiece(queen);
        assertTrue(board.isValidPath(queen, 0, 7));
    }

    @Test
    public void isValidPathLandingOnAlliedPieces() {
        // knight tries to jump from c3 to e4
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♙,  ,  ,  ]
        //[ ,  , ♘,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]

        whitePawn.setY(3);
        whitePawn.setX(4);
        board.boardArray[3][4].setPiece(whitePawn);
        board.boardArray[2][2].setPiece(knight);
        assertFalse(board.isValidPath(knight, 4, 3));
        // king tries to go from e3 to e4
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♙,  ,  ,  ]
        //[ ,  , ♘,  , ♔,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]

        king.setX(4);
        king.setY(2);
        board.boardArray[2][4].setPiece(king);
        assertFalse(board.isValidPath(king, 4, 3));
        // queen tries to go from a8 to h1
        //[♛,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♙,  ,  ,  ]
        //[ ,  , ♘,  , ♔,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]

        queen.setY(7);
        board.boardArray[7][0].setPiece(queen);
        assertFalse(board.isValidPath(queen, 7, 0));
        // rook tries to go from h8 to a8
        //[♛,  ,  ,  ,  ,  ,  , ♖]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♙,  ,  ,  ]
        //[ ,  , ♘,  , ♔,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]

        rook.setX(7);
        rook.setY(7);
        board.boardArray[7][7].setPiece(rook);

        // black pawn tries to go from c7 to c6
        //[♛,  ,  ,  ,  ,  ,  , ♖]
        //[ ,  , ♟,  ,  ,  ,  ,  ]
        //[ ,  , ♞,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♙,  ,  ,  ]
        //[ ,  , ♘,  , ♔,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]

        blackPawn.setX(2);
        blackPawn.setY(6);
        blackKnight.setY(5);
        blackKnight.setX(2);
        board.boardArray[6][2].setPiece(blackPawn);
        board.boardArray[5][2].setPiece(blackKnight);
        assertFalse(board.isValidPath(blackPawn, 2, 5));
    }

    @Test
    public void isValidPathLeapingStartPosition() {
        // start position rook tries to move from a1 to a8
        board.setUpPieces(player1, player2);
        assertFalse(board.isValidPath(board.boardArray[0][0].getPiece(), 0, 7));
        // black queen tries to move from d8 to d1
        assertFalse(board.isValidPath(board.boardArray[7][3].getPiece(), 0, 3));
        // white bishop tries to move fro  f1 to b5
        assertFalse(board.isValidPath(board.boardArray[0][5].getPiece(), 1, 4));
    }

    @Test
    public void isValidPathLeapingGeneral() {
        // queen tries tries to move from a8 to h1
        //[♛,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♙,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]

        queen.setY(7);
        whitePawn.setY(3);
        whitePawn.setX(4);
        board.boardArray[7][0].setPiece(queen);
        board.boardArray[3][4].setPiece(whitePawn);
        assertFalse(board.isValidPath(queen, 7, 0));
        // queen tries to capture pawn on e7
        //[♜, ♞, ♝, ♛, ♚, ♝, ♞, ♜]
        //[♟, ♟, ♟, ♟, ♟, ♟, ♟, ♟]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♙,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♛,  ,  ,  ]

        board.setUpBlackPieces(player2);
        queen.setX(4);
        queen.setY(0);
        board.boardArray[0][4].setPiece(queen);
        assertFalse(board.isValidPath(queen, 4, 6));
    }

    @Test
    public void isValidPathLandingOnEnemyPieces() {
        // black bishop captures rook
        //[♝,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  , ♖]

        Bishop blackBishop = new Bishop(0, 7, Color.BLACK);
        rook.setX(7);
        board.boardArray[7][0].setPiece(blackBishop);
        board.boardArray[0][7].setPiece(rook);
        assertTrue(board.isValidPath(blackBishop, 7, 0));

        // queens captures pawn on e7
        //[♜, ♞, ♝, ♛, ♚, ♝, ♞, ♜]
        //[♟, ♟, ♟, ♟, ♟, ♟, ♟, ♟]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  ,  ,  ,  ,  ]
        //[ ,  ,  ,  , ♛,  ,  ,  ]

        board.setUpBlackPieces(player2);
        Queen whiteQueen = new Queen(4, 0, Color.WHITE);
        board.boardArray[0][4].setPiece(whiteQueen);
        assertTrue(board.isValidPath(whiteQueen, 4, 6));

    }


    @Test
    public void executeMove() {
        // check if pawn coordinates change
        board.setUpPieces(player1, player2);
        Piece testPawn = board.boardArray[1][4].getPiece();
        assertEquals(1, testPawn.getY());
        assertEquals(4, testPawn.getX());
        board.setNewPiecePosition(testPawn, 4, 3);
        assertEquals(3, testPawn.getY());
        assertEquals(4, testPawn.getX());

        // same thing for black Knight
        Piece testKnight = board.boardArray[7][1].getPiece();
        assertEquals(7, testKnight.getY());
        assertEquals(1, testKnight.getX());
        board.setNewPiecePosition(testKnight, 2, 5);
        assertEquals(5, testKnight.getY());
        assertEquals(2, testKnight.getX());

    }
}