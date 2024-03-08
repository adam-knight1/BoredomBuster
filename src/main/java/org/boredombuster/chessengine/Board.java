package org.boredombuster.chessengine;

import org.boredombuster.chessengine.piecelogic.Pawn;

public class Board {
    private Piece[][] board;
    private Boolean whiteTurn = true;

    public Board(){
        this.board = new Piece[8][8];
    }

    private void setupBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(1,i,true);
        }
    }
}
