package org.boredombuster.chessengine.piecelogic;

import org.boredombuster.chessengine.Board;
import org.boredombuster.chessengine.Piece;

public class Bishop extends Piece {
    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int destinationX, int destinationY) {
        return false;
    }
}
