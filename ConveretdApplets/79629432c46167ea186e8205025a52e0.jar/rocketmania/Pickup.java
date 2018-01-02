// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;
import sexy.gui.SexyGraphics;
import sexy.gui.SexyImage;
import sexy.gui.SexyColor;

public class Pickup extends Piece
{
    static final int TYPE_GEM = 0;
    static final int TYPE_BOMB = 1;
    static final int TYPE_CLOCK = 2;
    static final int TYPE_COIN = 3;
    static final int ACTION_FALLING = 0;
    static final int ACTION_ATREST = 1;
    static final int ACTION_MOVINGTOUI = 2;
    static final int GEM_LIFETIMEAVERAGE = 1500;
    static final int GEM_LIFETIMEVARIANCE = 250;
    static final int FALLTIME = 45;
    static final int GEMTYPE_1 = 0;
    static final int GEMTYPE_2 = 1;
    static final int GEMTYPE_3 = 2;
    static final int GEMTYPE_4 = 3;
    static final int GEMTYPE_5 = 4;
    static final int NUMGEMTYPES = 5;
    static final int[] COIN_VALUE;
    static final int COINTYPE_BRONZE = 0;
    static final int COINTYPE_SILVER = 1;
    static final int COINTYPE_GOLD = 2;
    static final int COINTYPE_PLATINUM = 3;
    static final int NUMCOINTYPES = 4;
    static final int COIN_INDICATOR_X = 56;
    static final int COIN_INDICATOR_Y = 177;
    static final int HALF_WIDTH = 17;
    static final int HALF_HEIGHT = 17;
    static final double BOMB_BLASTRADIUSSQ = 5780.0;
    static final String skFlash = "1100110011001100111000111000111000111000111100001111000011110000111100001111100000111110000011111000001111100000111111000000111111000000111111000000111111000000";
    static final int[] skCoinImages;
    static final int[] skGemImages;
    static final SexyColor[] skCoinColours;
    static final SexyColor[] skGemColours;
    static final SexyColor skClockColour;
    static final SexyColor skBombColour;
    int mType;
    int mAction;
    int mLifetime;
    int mFallTime;
    int mSize;
    Tube mParent;
    SexyImage mImage;
    SexyColor mParticleColour;
    
    boolean Update(final Board board) {
        boolean update = super.Update(board);
        switch (this.mAction) {
            case 0: {
                ++this.mFallTime;
                if (this.mFallTime < 45) {
                    super.mY = (int)(this.mFallTime * this.mFallTime / 2025.0 * this.mParent.mY);
                    if (super.mEmitter != null) {
                        super.mEmitter.MoveTo((float)(super.mX + 17.0), (float)super.mY);
                    }
                }
                else {
                    super.mY = this.mParent.mY;
                    if (super.mEmitter != null) {
                        board.mParticleSystem.RemoveParticleEmitter(super.mEmitter);
                        super.mEmitter = null;
                    }
                    this.mAction = 1;
                    if (this.mType == 3) {
                        board.mApplet.PlaySound(10);
                    }
                }
                update = true;
                break;
            }
            case 1: {
                super.mY = this.mParent.mY;
                if (--this.mLifetime == 0) {
                    this.mParent.mPickup = null;
                    this.mParent = null;
                    super.mRemoveFromActiveList = true;
                    super.mDestroy = true;
                    board.mApplet.mHints.Queue(7, 0.5f);
                }
                if (this.mType == 0 && this.mLifetime < "1100110011001100111000111000111000111000111100001111000011110000111100001111100000111110000011111000001111100000111111000000111111000000111111000000111111000000".length()) {
                    update = true;
                }
                if (this.mType == 3 && this.glintCount(board) > 0) {
                    update = true;
                    break;
                }
                break;
            }
            case 2: {
                if (this.mType == 1) {
                    update = true;
                    break;
                }
                break;
            }
        }
        return update;
    }
    
    void Draw(final Board board, final SexyGraphics sexyGraphics) {
        int n = 1;
        if (this.mLifetime > 0 && this.mLifetime < "1100110011001100111000111000111000111000111100001111000011110000111100001111100000111110000011111000001111100000111111000000111111000000111111000000111111000000".length()) {
            n = (("1100110011001100111000111000111000111000111100001111000011110000111100001111100000111110000011111000001111100000111111000000111111000000111111000000111111000000".charAt(this.mLifetime) == '1') ? 1 : 0);
        }
        if (n != 0) {
            sexyGraphics.DrawImage(this.mImage, (int)super.mX, (int)super.mY);
        }
        switch (this.mType) {
            case 3: {
                if (this.mAction != 1) {
                    break;
                }
                final int glintCount = this.glintCount(board);
                if (glintCount > 0) {
                    final int n2 = (int)(60.0 * Math.sin(3.141592653589793 * glintCount / 50.0));
                    sexyGraphics.SetColorizeImages(true);
                    sexyGraphics.SetColor(new SexyColor(n2, n2, n2));
                    sexyGraphics.SetDrawMode(1);
                    sexyGraphics.DrawImage(board.mApplet.mRes.GetImage(16), (int)super.mX - 2, (int)super.mY - 2);
                    sexyGraphics.SetDrawMode(0);
                    sexyGraphics.SetColorizeImages(false);
                    return;
                }
                break;
            }
            case 1: {
                if (this.mAction == 2) {
                    final SexyGraphics sexyGraphics2 = new SexyGraphics(sexyGraphics);
                    final SexyImage getImage = board.mApplet.mRes.GetImage(9);
                    final int n3 = getImage.GetWidth() / 10;
                    final int getHeight = getImage.GetHeight();
                    final int n4 = (int)(super.mX + 2.0 * super.mMovePercent) + (34 - n3) / 2 - 8;
                    final int n5 = (int)(super.mY + 5.0 * super.mMovePercent) + (34 - getHeight) / 2 - 10;
                    sexyGraphics2.SetDrawMode(1);
                    sexyGraphics2.ClipRect(n4, n5, n3, getHeight);
                    sexyGraphics2.DrawImage(getImage, n4 - board.mRand.Next() % 10 * n3, n5);
                    return;
                }
                break;
            }
        }
    }
    
    public Pickup(final Board board, final int mType, final Tube mParent, final int mSize) {
        super(board, mParent.mBoardRow, mParent.mBoardCol);
        this.mType = mType;
        this.mAction = 1;
        this.mParent = mParent;
        this.mSize = mSize;
        this.mParticleColour = null;
        switch (this.mType) {
            case 3: {
                Assert.assert(this.mSize >= 0 && this.mSize < 4);
                this.mImage = board.mApplet.mRes.GetImage(Pickup.skCoinImages[this.mSize]);
                this.mAction = 0;
                this.mFallTime = 0;
                this.mLifetime = -1;
                super.mY = 0.0;
                board.mApplet.PlaySound(18);
                this.mParticleColour = Pickup.skCoinColours[this.mSize];
            }
            case 0: {
                Assert.assert(this.mSize >= 0 && this.mSize < 5);
                this.mImage = board.mApplet.mRes.GetImage(Pickup.skGemImages[this.mSize]);
                this.mLifetime = 1500 + board.mRand.Next() % 500 - 250;
                board.mApplet.mHints.Queue(2, 3.0f);
                this.mParticleColour = Pickup.skGemColours[this.mSize];
            }
            case 1: {
                this.mImage = board.mApplet.mRes.GetImage(55);
                this.mLifetime = -1;
                board.mApplet.mHints.Queue(3, 3.0f);
            }
            case 2: {
                this.mImage = board.mApplet.mRes.GetImage(56);
                this.mLifetime = -1;
                board.mApplet.mHints.Queue(4, 3.0f);
                this.mParticleColour = Pickup.skClockColour;
            }
            default: {}
        }
    }
    
    void BonusPotentialMeasure(final Stats stats) {
        if (this.mType == 3) {
            Assert.assert(this.mSize >= 0 && this.mSize < 4);
        }
    }
    
    void Got(final Board board) {
        this.mAction = 2;
        super.mBoardRow = this.mParent.mBoardRow;
        this.mParent.mPickup = null;
        this.mParent = null;
        super.mMovePercent = 0.0;
        super.mMoveAddPercent = 0.1;
        super.mMoving = true;
        super.mOrigX = (int)super.mX;
        super.mOrigY = (int)super.mY;
        switch (this.mType) {
            case 0: {
                super.mDestX = 46;
                super.mDestY = 71;
                board.AddPointsFloater(board.ScaleScore(Scores.GEMS[this.mSize]), (int)super.mX, (int)super.mY);
                ++board.mGemsPickedUpDuringTravellingSpark;
                break;
            }
            case 3: {
                super.mDestX = 39;
                super.mDestY = 160;
                if (++board.mCoinsPickedUpDuringTravellingSpark > 1) {
                    board.AddTextFloater("x" + board.mCoinsPickedUpDuringTravellingSpark, 100, (int)super.mX, (int)super.mY);
                }
                board.mApplet.PlaySound(18);
                break;
            }
            case 2: {
                super.mDestX = board.GetTimerBarX() - 17;
                super.mDestY = board.GetTimerBarY() - 17;
                break;
            }
            case 1: {
                this.mLifetime = 50;
                super.mDestX = (int)super.mX;
                super.mBoardRow += 2;
                super.mBoardRow = ((super.mBoardRow < 8) ? super.mBoardRow : 8);
                super.mDestY = Board.GetRowY(super.mBoardRow);
                super.mMoveAddPercent = 0.025;
                break;
            }
        }
        if (this.mParticleColour != null) {
            board.mParticleSystem.CreateParticleEmitter(board.mParticleDatabase.mForegroundSplash, board.mParticleDatabase.mStar, this.mParticleColour, 17 + (int)super.mX, 17 + (int)super.mY, 0.0f, 0.0f);
        }
    }
    
    private int glintCount(final Board board) {
        int n = (board.mUpdateCnt + super.mDestX / 10 + super.mDestY / 10) % 400;
        if (n > 50) {
            n = 0;
        }
        return n;
    }
    
    static {
        COIN_VALUE = new int[] { 1, 2, 5, 10 };
        skCoinImages = new int[] { 15, 48, 49, 50 };
        skGemImages = new int[] { 17, 51, 52, 53, 54 };
        skCoinColours = new SexyColor[] { new SexyColor(255, 160, 32), new SexyColor(255, 255, 255), new SexyColor(235, 194, 11), new SexyColor(134, 235, 84) };
        skGemColours = new SexyColor[] { new SexyColor(255, 255, 255), new SexyColor(255, 200, 84), new SexyColor(255, 0, 0), new SexyColor(0, 255, 0), new SexyColor(255, 255, 0) };
        skClockColour = new SexyColor(128, 192, 255);
        skBombColour = new SexyColor(255, 192, 0);
    }
    
    int GetScore() {
        int n = 0;
        switch (this.mType) {
            case 0: {
                n = Scores.GEMS[this.mSize];
                break;
            }
        }
        return n;
    }
    
    private void BombExplodes(final Board board) {
        int n = 0;
        do {
            int n2 = 0;
            do {
                final Tube tube = board.mBoard[n][n2];
                if (tube != null && tube.mAction != 2) {
                    final double n3 = tube.mX - super.mX;
                    final double n4 = tube.mY - super.mY;
                    if (n3 * n3 + n4 * n4 > 5780.0) {
                        continue;
                    }
                    board.mParticleSystem.CreateParticleEmitter(board.mParticleDatabase.mBombSplash, board.mParticleDatabase.mFlame, Pickup.skBombColour, (float)(tube.mX + 17.0), (float)(tube.mY + 17.0), 0.0f, 0.0f);
                    if (tube.mPickup != null) {
                        tube.mPickup.mRemoveFromActiveList = true;
                    }
                    if (tube.mIsActive) {
                        tube.mRemoveFromActiveList = true;
                    }
                    board.mBoard[n][n2] = null;
                }
            } while (++n2 < 6);
        } while (++n < 9);
        board.DropBoardPieces();
        board.mApplet.PlaySound(4);
    }
    
    void EndOfMove(final Board board) {
        if (this.mAction == 2) {
            super.mRemoveFromActiveList = true;
            super.mDestroy = true;
            switch (this.mType) {
                case 0: {
                    board.AddScore(Scores.GEMS[this.mSize]);
                    board.mStats.RecordGemsCollected(1);
                    board.mApplet.PlaySound(10);
                    break;
                }
                case 3: {
                    board.mCoins += Pickup.COIN_VALUE[this.mSize];
                    board.mStats.RecordCoinsCollected(Pickup.COIN_VALUE[this.mSize]);
                    board.mApplet.PlaySound(2);
                    break;
                }
                case 1: {
                    this.BombExplodes(board);
                    break;
                }
                case 2: {
                    board.FreezeTime();
                    board.mApplet.PlaySound(7);
                    break;
                }
            }
            if (this.mParticleColour != null) {
                board.mParticleSystem.CreateParticleEmitter(board.mParticleDatabase.mForegroundSplash, board.mParticleDatabase.mStar, this.mParticleColour, 17 + (int)super.mX, 17 + (int)super.mY, 0.0f, 0.0f);
            }
        }
    }
}
