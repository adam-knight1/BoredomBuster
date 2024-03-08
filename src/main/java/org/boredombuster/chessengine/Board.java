package org.boredombuster.chessengine;

import org.boredombuster.chessengine.piecelogic.*;

public class Board {
    private Piece[][] board;
    private Boolean whiteTurn = true;

    public Board() {
        this.board = new Piece[8][8];
    }

    /** The pieces are placed in their respective starting positions in the following setup method
     *
     */
    private void setupBoard() {

        //initializing the white pawns row 1
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(true);
        }
        //initializing the black pawns on row 6

        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(false);
        }

        //White rooks

        board[0][0] = new Rook(true);
        board[0][7] = new Rook(true);

        // Black rooks

        board[7][0] = new Rook( false);
        board[7][7] = new Rook(false);


        //White knights
        board[0][1] = new Knight(true);
        board[0][6] = new Knight(true);

        //Black knights
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


