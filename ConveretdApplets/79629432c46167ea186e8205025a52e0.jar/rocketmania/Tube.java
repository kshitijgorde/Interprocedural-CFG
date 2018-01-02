// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import sexy.gui.SexyColor;
import sexy.gui.SexyGraphics;

public class Tube extends Piece
{
    static final int DIRECTION_N = 1;
    static final int DIRECTION_E = 2;
    static final int DIRECTION_S = 4;
    static final int DIRECTION_W = 8;
    static final int NADIRECTION = 0;
    static final int ACTION_ATREST = 0;
    static final int ACTION_FALLING = 1;
    static final int ACTION_BURNING = 2;
    static final int NAACTION = 3;
    static final int NOSIDE = 0;
    static final int EASTSIDE = 1;
    static final int WESTSIDE = 2;
    static final int BOTHSIDES = 3;
    static final int NASHAPE = -1;
    static final int SHAPE_I = 0;
    static final int SHAPE_L = 1;
    static final int SHAPE_T = 2;
    static final int SHAPE_X = 3;
    static final int SHAPE_CULDESAC = 4;
    static final int NUMSHAPES = 5;
    static final int COLOURUNTOUCHED = 0;
    static final int COLOURLITLEFT = 1;
    static final int COLOURDARKLEFT = 2;
    static final int COLOURLITRIGHT = 3;
    static final int COLOURDARKRIGHT = 4;
    static final int COLOURBURNING = 5;
    static final int COLOURBURNT = 6;
    static final int COLOURWHITE = 7;
    static final int NUMCOLOURS = 8;
    static final int[][] gColours;
    static final int[][] gDirections;
    static final int[][] gDirectionOffsets;
    static final double BOARD_BLOCKMOVEPERCENT = 0.2;
    static final int NEWPIECEYOFFSET = 50;
    int mShape;
    int mAction;
    int mOrientation;
    double mDrawRotatedAngle;
    int mDrawRotatedUpdateCount;
    int mBurningTag;
    boolean mBurnt;
    Pickup mPickup;
    static int mNextCollisionSoundMinimumUpdateCount;
    
    boolean CheckDirection(final int n) {
        return (Tube.gDirections[this.mShape][this.mOrientation] & n) != 0x0;
    }
    
    void Draw(final Board board, final SexyGraphics sexyGraphics) {
        final Res mRes = board.mApplet.mRes;
        boolean b = true;
        if (Math.abs(this.mDrawRotatedAngle) > 0.005) {
            sexyGraphics.DrawImageRotated(mRes.mTubeImages[this.mShape][this.mOrientation][0], (int)super.mX, (int)super.mY, this.mDrawRotatedAngle);
        }
        else {
            int n;
            if (this.mBurningTag != 0) {
                if ((board.mSidesTouchedByTagNum[this.mBurningTag] & 0x2) != 0x0) {
                    n = 1;
                }
                else if ((board.mSidesTouchedByTagNum[this.mBurningTag] & 0x1) != 0x0) {
                    n = 3;
                }
                else if (this.mBurnt) {
                    n = 6;
                    b = false;
                }
                else if (this.mAction == 2 && board.mInGlowUpSequence && board.mGlowUpSequenceTimer <= 0) {
                    n = 5;
                }
                else {
                    n = 7;
                }
            }
            else {
                n = 7;
            }
            sexyGraphics.DrawImage(mRes.mTubeImages[this.mShape][this.mOrientation][n], (int)super.mX, (int)super.mY);
            if (this.mAction == 2 && board.mInGlowUpSequence && board.mGlowUpSequenceTimer > 0 && board.mGlowUpSequenceTimer > 0) {
                sexyGraphics.SetColorizeImages(true);
                sexyGraphics.SetColor(new SexyColor(255, 255, 255, 255 - 255 * board.mGlowUpSequenceTimer / 33));
                sexyGraphics.DrawImage(mRes.mTubeImages[this.mShape][this.mOrientation][5], (int)super.mX, (int)super.mY);
                sexyGraphics.SetColorizeImages(false);
            }
        }
        final int mLastMouseX = board.mApplet.mWidgetManager.mLastMouseX;
        final int mLastMouseY = board.mApplet.mWidgetManager.mLastMouseY;
        if (b && mLastMouseX >= super.mX && mLastMouseX < super.mX + 34.0 && mLastMouseY >= super.mY && mLastMouseY < super.mY + 34.0) {
            sexyGraphics.SetDrawMode(1);
            sexyGraphics.DrawImage(board.mApplet.mRes.mHighlightTile, (int)super.mX, (int)super.mY);
            sexyGraphics.SetDrawMode(0);
        }
    }
    
    public Tube(final Board board, final int mDrawRotatedUpdateCount, final int n, final int n2) {
        super(board, n, n2);
        this.mShape = board.mProgression.RandomPieceType(board.mApplet.mGameType);
        this.mOrientation = board.mRand.Next() % 4;
        this.mAction = 0;
        this.mDrawRotatedAngle = 0.0;
        this.mDrawRotatedUpdateCount = mDrawRotatedUpdateCount;
        this.mBurningTag = 0;
        this.mBurnt = false;
        this.mPickup = null;
    }
    
    boolean IsStart(final int n) {
        return this.mAction == 0 && this.mBurningTag == 0 && this.CheckDirection(n);
    }
    
    boolean UpdateRotation() {
        boolean b = false;
        if (this.mDrawRotatedAngle > 0.0) {
            this.mDrawRotatedAngle -= 0.22499999999999998;
            if (this.mDrawRotatedAngle < 0.0) {
                this.mDrawRotatedAngle = 0.0;
            }
            b = true;
        }
        else if (this.mDrawRotatedAngle < 0.0) {
            this.mDrawRotatedAngle += 0.22499999999999998;
            if (this.mDrawRotatedAngle > 0.0) {
                this.mDrawRotatedAngle = 0.0;
            }
            b = true;
        }
        return b;
    }
    
    static {
        gColours = new int[][] { { 255, 255, 255 }, { 255, 200, 0 }, { 255, 160, 32 }, { 255, 50, 50 }, { 200, 0, 100 }, { 255, 0, 0 }, { 100, 255, 0 }, { 255, 255, 255 } };
        gDirections = new int[][] { { 5, 10, 5, 10 }, { 3, 6, 12, 9 }, { 14, 13, 11, 7 }, { 15, 15, 15, 15 }, { 1, 2, 4, 8 } };
        gDirectionOffsets = new int[][] { { -1, 0, 1, 4 }, { 1, 0, 4, 1 }, { 0, -1, 8, 2 }, { 0, 1, 2, 8 } };
        Tube.mNextCollisionSoundMinimumUpdateCount = 0;
    }
    
    int GetScore() {
        return 5;
    }
    
    void Drop(final Board board) {
        int mBoardRow = 8;
        while (board.mBoard[mBoardRow][super.mBoardCol] != null && --mBoardRow >= 0) {}
        if (mBoardRow >= 0) {
            if (this.mAction != 1) {
                board.AddPieceToActiveList(this);
                this.mAction = 1;
            }
            if (super.mBoardRow >= 0) {
                board.mBoard[super.mBoardRow][super.mBoardCol] = null;
            }
            super.mBoardRow = mBoardRow;
            board.mBoard[super.mBoardRow][super.mBoardCol] = this;
            super.mMoving = true;
            super.mRemoveFromActiveList = false;
            super.mOrigX = (int)super.mX;
            super.mOrigY = (int)super.mY;
            super.mDestX = Board.GetColX(super.mBoardCol);
            super.mDestY = Board.GetRowY(super.mBoardRow);
            super.mMovePercent = 0.0;
            super.mMoveAddPercent = 7.0 / (super.mDestY - super.mOrigY);
        }
    }
    
    void EndOfMove(final Board board) {
        this.mAction = 0;
        super.mRemoveFromActiveList = true;
        if (board.mUpdateCnt >= Tube.mNextCollisionSoundMinimumUpdateCount) {
            Tube.mNextCollisionSoundMinimumUpdateCount = board.mUpdateCnt + (1 + board.mRand.Next() % 8);
            board.mApplet.PlaySound(9);
        }
    }
}
