// 
// Decompiled by Procyon v0.5.30
// 

package com.chess.game.player;

import com.chess.chessboard.ChessboardModel;
import com.chess.chessboard.Chessboard;
import com.chess.chessboard.MoveListener;
import com.chess.game.Player;

public class HumanPlayer extends Player
{
    private final Object lock;
    private MoveListener.Move lastMove;
    
    public HumanPlayer() {
        this(null, null, null);
    }
    
    public HumanPlayer(final Chessboard chessboard, final String name, final ChessboardModel.PieceColor color) {
        super(chessboard, name, color);
        this.lock = new Object();
    }
    
    public Chessboard getChessboard() {
        return this.chessboard;
    }
    
    public void setChessboard(final Chessboard chessboard) {
        (this.chessboard = chessboard).addMoveListener((MoveListener)this);
    }
    
    public boolean movePlayed(final MoveListener.Move move, final boolean animate) {
        this.lastMove = move;
        synchronized (this.lock) {
            this.lock.notify();
        }
        return true;
    }
    
    public MoveListener.Move doMove() {
        this.lastMove = null;
        if (this.chessboard == null) {
            throw new IllegalStateException("Human player with a null chessboard");
        }
        synchronized (this.lock) {
            try {
                this.lock.wait();
            }
            catch (InterruptedException ex) {
                if (!this.gameOver) {
                    throw new IllegalStateException("error getting move from human player", ex);
                }
            }
        }
        return this.lastMove;
    }
}
