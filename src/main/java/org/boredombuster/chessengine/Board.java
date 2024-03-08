package org.boredombuster.chessengine;

import org.boredombuster.chessengine.piecelogic.*;

public class Board {
    private Piece[][] board;
    private Boolean whiteTurn = true;

    public Board() {
        this.board = new Piece[8][8];
    }

    private void setupBoard() {

        //initializing the white pawns on row 1
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(1, i, true);
        }
        //initializing the black pawns on row 6

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(6, i, false);
        }

        //white rooks

        board[0][0] = new Rook(0, 0, true);
        board[0][7] = new Rook(0, 7, true);

        // black rooks

        board[7][0] = new Rook(7, 0, false);
        board[7][7] = new Rook(7, 7, false);


        //white knights
        board[0][1] = new Knight(true);
        board[0][6] = new Knight(true);

        //black knights
        board[7][1] = new Knight(false);
        board[7][6] = new Knight(false);

        // White bishops
        board[0][2] = new Bishop(true);
        board[0][5] = new Bishop(true);

        // Black bishops
        board[7][2] = new Bishop(false);
        board[7][5] = new Bishop(false);

        // White queen and king
        board[0][3] = new Queen(true);
        board[0][4] = new King(true);

        // Black queen and king
        board[7][3] = new Queen(false);
        board[7][4] = new King(false);
    }
}


