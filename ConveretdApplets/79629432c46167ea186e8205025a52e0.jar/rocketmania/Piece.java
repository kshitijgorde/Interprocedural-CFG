// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;
import sexy.gui.SexyGraphics;
import nc.particle.ParticleEmitter;

public class Piece
{
    static final int CRITTERTYPE_GEM = 0;
    static final int CRITTERTYPE_BOMB = 1;
    static final int CRITTERTYPE_CLOCK = 2;
    double mX;
    double mY;
    boolean mMoving;
    double mMovePercent;
    double mMoveAddPercent;
    int mOrigX;
    int mOrigY;
    int mDestX;
    int mDestY;
    int mBoardRow;
    int mBoardCol;
    int mBoardRowStart;
    int mBoardColStart;
    boolean mIsActive;
    boolean mRemoveFromActiveList;
    boolean mDestroy;
    ParticleEmitter mEmitter;
    int mEmitterXOff;
    int mEmitterYOff;
    
    boolean Update(final Board board) {
        boolean b = false;
        if (this.mMoving) {
            if (this.mEmitter != null) {
                this.mEmitter.MoveTo((float)(this.mX + this.mEmitterXOff), (float)(this.mY + this.mEmitterYOff));
            }
            this.mMovePercent += this.mMoveAddPercent;
            if (this.mMovePercent >= 1.0) {
                this.EndOfMove(board);
                this.mMovePercent = 1.0;
                this.mMoving = false;
            }
            this.mX = (int)(this.mDestX * this.mMovePercent + this.mOrigX * (1.0 - this.mMovePercent));
            this.mY = (int)(this.mDestY * this.mMovePercent + this.mOrigY * (1.0 - this.mMovePercent));
            b = true;
        }
        return b;
    }
    
    void Draw(final Board board, final SexyGraphics sexyGraphics) {
        Assert.assert(false);
    }
    
    public Piece(final Board board, final int n, final int n2) {
        this.mBoardRow = n;
        this.mBoardCol = n2;
        this.mBoardRowStart = n;
        this.mBoardColStart = n2;
        this.mX = Board.GetColX(n2);
        this.mY = Board.GetRowY(n);
        this.mOrigX = (int)this.mX;
        this.mOrigY = (int)this.mY;
        this.mDestX = this.mOrigX;
        this.mDestY = this.mOrigY;
        this.mIsActive = false;
        this.mRemoveFromActiveList = false;
        this.mDestroy = false;
        this.mEmitterXOff = 0;
        this.mEmitterYOff = 0;
        this.mMovePercent = 0.0;
    }
    
    int GetScore() {
        return 0;
    }
    
    void EndOfMove(final Board board) {
        this.mRemoveFromActiveList = true;
        this.mDestroy = true;
    }
}
