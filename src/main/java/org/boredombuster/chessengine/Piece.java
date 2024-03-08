package org.boredombuster.chessengine;

/** This abstract class will serve as a template for the various chess pieces, contained within the piecelogic folder
 */
public abstract class Piece {
    protected int positionX;
    protected int positionY;
    protected boolean isWhite;

    public Piece(int positionX, int positionY, boolean isWhite) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.isWhite = isWhite;
    }

    /** Determine if the move is valid based on piece movement rules.
     *
     * @param board
     * @param destinationX
     * @param destinationY
     * @return
     */
    public abstract boolean isValidMove(Board board, int destinationX, int destinationY);

    public boolean isWhite() {
        return isWhite;
    }
}
