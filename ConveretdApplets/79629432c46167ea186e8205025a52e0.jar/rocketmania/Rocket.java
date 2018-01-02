// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyColor;

public class Rocket extends Piece
{
    static final int ROCKETTYPE_1 = 0;
    static final int ROCKETTYPE_2 = 1;
    static final int ROCKETTYPE_3 = 2;
    static final int ROCKETTYPE_4 = 3;
    static final int ROCKETTYPE_5 = 4;
    static final int ROCKETTYPE_6 = 5;
    static final int ROCKETTYPE_7 = 6;
    static final int ROCKETTYPE_8 = 7;
    static final int ROCKETTYPE_9 = 8;
    static final int ROCKETTYPE_10 = 9;
    static final int NUMROCKETTYPES = 10;
    static final int ACTION_ATREST = 0;
    static final int ACTION_FLYING = 1;
    static final int ROCKETSXPOS = 404;
    static final int ROCKETSYPOSBASE = -16;
    static final int ROCKETSYPOSDELTA = 34;
    static final int INDICATOR_Y = 148;
    static final int[] skRocketImages;
    static final SexyColor skParticleColour;
    int mSize;
    int mAction;
    
    boolean Update(final Board board) {
        boolean update = super.Update(board);
        if (this.mAction == 0 && super.mMoving) {
            update = true;
        }
        return update;
    }
    
    void Draw(final Board board, final SexyGraphics sexyGraphics) {
        if (this.mAction == 0 && super.mMoving) {
            sexyGraphics.SetColorizeImages(true);
            final int n = (int)(255.0 * super.mMovePercent);
            sexyGraphics.SetColor(new SexyColor(255, 255, 255, (n > 255) ? 255 : n));
        }
        sexyGraphics.DrawImage(board.mApplet.mRes.GetImage(Rocket.skRocketImages[this.mSize]), (int)super.mX, (int)super.mY);
        sexyGraphics.SetColorizeImages(false);
    }
    
    public Rocket(final Board board, final int n, final int mSize) {
        super(board, n, 9);
        super.mX = 404.0;
        super.mY = -16 + 34 * n;
        this.mSize = mSize;
        this.mAction = 0;
        super.mMoving = true;
        super.mDestX = (int)super.mX;
        super.mDestY = (int)super.mY;
        super.mX += 10.0;
        super.mY += 10.0;
        super.mOrigX = (int)super.mX;
        super.mOrigY = (int)super.mY;
        super.mMoveAddPercent = 0.02;
    }
    
    void BonusPotentialMeasure(final Stats stats) {
        Assert.assert(this.mSize >= 0 && this.mSize < 10);
    }
    
    static {
        skRocketImages = new int[] { 14, 39, 40, 41, 42, 43, 44, 45, 46, 47 };
        skParticleColour = new SexyColor(255, 255, 255);
    }
    
    void FinishMove() {
        super.mMovePercent = 1.0;
        super.mMoving = false;
        super.mX = super.mDestX;
        super.mY = super.mDestY;
    }
    
    int GetScore() {
        return 100 * (1 + this.mSize);
    }
    
    void Launch(final Board board) {
        this.mAction = 1;
        super.mOrigX = super.mDestX;
        super.mOrigY = super.mDestY;
        super.mMoveAddPercent = 0.04 - 0.03 * Math.random();
        final int n = 100 * (int)(0.04 / super.mMoveAddPercent);
        super.mDestX = super.mOrigX + n;
        super.mDestY = super.mOrigY - n;
        super.mMovePercent = 0.0;
        super.mMoving = true;
        super.mEmitter = board.mParticleSystem.CreateParticleEmitter(board.mParticleDatabase.mRocket, board.mParticleDatabase.mFlame, Rocket.skParticleColour, super.mOrigX, super.mOrigY, 1.0f, -1.0f);
        super.mEmitterXOff = 20;
        super.mEmitterYOff = 40;
    }
    
    void EndOfMove(final Board board) {
        if (this.mAction == 1) {
            board.mParticleSystem.RemoveParticleEmitter(super.mEmitter);
            board.AddCoins();
            Fireworks.AddFirework(board.mApplet, board.mParticleSystem, board.mParticleDatabase, this.mSize, 0);
            super.EndOfMove(board);
        }
    }
}
