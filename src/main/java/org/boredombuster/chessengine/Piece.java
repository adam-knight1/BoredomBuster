package org.boredombuster.chessengine;

/** This abstract class will serve as a template for the various chess pieces, contained within the piecelogic folder
 */
public abstract class Piece {
    protected int positionX;
    protected int positionY;
    protected boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    /** Determine if the move is valid based on piece movement rules.
     *
     * @param board
     * @param startX
     * @param startY
     * @param destinationX
     * @param destinationY
     * @return
     */
    public abstract boolean isValidMove(Board board, int startX, int startY, int destinationX, int destinationY);

    public boolean isWhite() {
        return isWhite;
    }
}
