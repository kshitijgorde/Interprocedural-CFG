// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;
import sexy.gui.SexyImage;
import sexy.gui.SexyGraphics;

public class TravellingSpark extends Piece
{
    static final int skTouchHack = 1;
    static final int MOVE_TOTUBEEDGE = 0;
    static final int MOVE_TONEXTTUBE = 1;
    static final double MOVEADDPERCENT = 0.33333;
    int mMovement;
    int mAnimNum;
    
    void Draw(final Board board, final SexyGraphics sexyGraphics) {
        final SexyGraphics sexyGraphics2 = new SexyGraphics(sexyGraphics);
        final SexyImage sexyImage = board.mApplet.mRes.mImages[9];
        final int n = sexyImage.GetWidth() / 10;
        final int getHeight = sexyImage.GetHeight();
        final int n2 = (int)super.mX + (34 - n) / 2;
        final int n3 = (int)super.mY + (34 - getHeight) / 2;
        sexyGraphics2.SetDrawMode(1);
        sexyGraphics2.ClipRect(n2, n3, n, getHeight);
        sexyGraphics2.DrawImage(sexyImage, n2 - this.mAnimNum * n, n3);
    }
    
    public TravellingSpark(final Board board, final int n, final int n2, final int mBoardRow, final int mBoardCol, final int mMovement) {
        super(board, n, n2);
        super.mMoving = true;
        super.mBoardRow = mBoardRow;
        super.mBoardCol = mBoardCol;
        this.mMovement = mMovement;
        this.mAnimNum = 0;
        switch (this.mMovement) {
            case 0: {
                super.mDestX = (int)(super.mX + Board.GetColX(mBoardCol)) / 2;
                super.mDestY = (int)(super.mY + Board.GetRowY(mBoardRow)) / 2;
                super.mMoveAddPercent = 0.66666;
                break;
            }
            case 1: {
                Assert.assert(mBoardRow >= 0 && mBoardRow < 9);
                Assert.assert(mBoardCol >= 0 && mBoardCol < 6);
                super.mDestX = Board.GetColX(mBoardCol);
                super.mDestY = Board.GetRowY(mBoardRow);
                super.mMoveAddPercent = 0.33333;
                final Tube tube = board.mBoard[mBoardRow][mBoardCol];
                if (tube == null) {
                    break;
                }
                Assert.assert(!tube.mBurnt);
                tube.mBurnt = true;
                board.DisplayTileScore_Inc();
                if (tube.mPickup != null) {
                    tube.mPickup.Got(board);
                    tube.mPickup = null;
                    break;
                }
                break;
            }
        }
        ++board.mNumTravellingSparks;
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    void EndOfMove(final Board board) {
        if (this.mMovement == 1) {
            final Tube tube = board.mBoard[super.mBoardRow][super.mBoardCol];
            int n = 0;
            do {
                if (tube != null && tube.CheckDirection(Tube.gDirectionOffsets[n][2])) {
                    final int n2 = super.mBoardRow + Tube.gDirectionOffsets[n][0];
                    final int n3 = super.mBoardCol + Tube.gDirectionOffsets[n][1];
                    if (n2 == super.mBoardRowStart && n3 == super.mBoardColStart) {
                        continue;
                    }
                    boolean b = false;
                    if (n3 >= 0 && n3 < 6 && n2 >= 0 && n2 < 9) {
                        final Tube tube2 = board.mBoard[n2][n3];
                        if (tube2 != null && !tube2.mBurnt && tube2.CheckDirection(Tube.gDirectionOffsets[n][3])) {
                            board.AddPieceToActiveList(new TravellingSpark(board, super.mBoardRow, super.mBoardCol, n2, n3, 1));
                            b = true;
                            if (tube2.mAction != 2) {
                                tube2.mAction = 2;
                                tube2.mBurningTag = tube.mBurningTag;
                                Assert.assert(tube2.mBurningTag == board.mGlowUpSequenceTag);
                            }
                        }
                    }
                    if (b) {
                        continue;
                    }
                    board.AddPieceToActiveList(new TravellingSpark(board, super.mBoardRow, super.mBoardCol, n2, n3, 0));
                }
            } while (++n < 4);
        }
        super.mRemoveFromActiveList = true;
        super.mDestroy = true;
        --board.mNumTravellingSparks;
    }
}
