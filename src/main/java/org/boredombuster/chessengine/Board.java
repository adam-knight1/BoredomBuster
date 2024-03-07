package org.boredombuster.chessengine;

public class Board {
    private Piece[][] board;
    private Boolean whiteTurn = true;

    public Board(){
        this.board = new Piece[8][8];
    }

    private void setupBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = new pawn(1,i,true);
        }
    }
}
