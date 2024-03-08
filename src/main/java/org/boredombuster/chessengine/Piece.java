package org.boredombuster.chessengine;

public abstract class Piece {
    protected int positionX;
    protected int positionY;
    protected boolean isWhite;


    public Piece(int positionX, int positionY, boolean isWhite) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(Board board, int destinationX, int destinationY);

    public boolean isWhite() {
        return isWhite;
    }
}
