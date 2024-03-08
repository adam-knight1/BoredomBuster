package org.boredombuster.chessengine.piecelogic;

import org.boredombuster.chessengine.Board;
import org.boredombuster.chessengine.Piece;

public class Pawn extends Piece {
    public Pawn(boolean isWhite){
        super(isWhite);
    }

    @Override
    public boolean isValidMove(Board board, int startX, int startY, int destinationX, int destinationY) {
        // Implement pawn movement logic here, using startX, startY for the pawn's current position
        return false; // Placeholder return statement
    }


}
