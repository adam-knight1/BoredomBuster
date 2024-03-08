package org.boredombuster.chessengine.piecelogic;

import org.boredombuster.chessengine.Board;
import org.boredombuster.chessengine.Piece;

public class Rook extends Piece {
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int destinationX, int destinationY) {
        return false;
    }
}
