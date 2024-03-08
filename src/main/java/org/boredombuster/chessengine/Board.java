package org.boredombuster.chessengine;

import org.boredombuster.chessengine.piecelogic.Knight;
import org.boredombuster.chessengine.piecelogic.Pawn;
import org.boredombuster.chessengine.piecelogic.Rook;

public class Board {
    private Piece[][] board;
    private Boolean whiteTurn = true;

    public Board(){
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
        board[0][1] = new Rook(0,1,true);
        board[0][6] = new Rook(0,6,true);
    }
    }

