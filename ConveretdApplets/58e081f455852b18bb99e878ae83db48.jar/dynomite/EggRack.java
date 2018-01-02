// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import java.util.Enumeration;
import java.awt.Rectangle;
import sexy.gui.SexyGraphics;
import java.awt.Point;
import java.util.Vector;
import sexy.gui.SexyImage;
import sexy.util.MTRand;

public class EggRack
{
    static final int COLLIDE_NOTHING = 0;
    static final int COLLIDE_LEFTWALL = 1;
    static final int COLLIDE_RIGHTWALL = 2;
    static final int COLLIDE_CEILING = 4;
    static final int COLLIDE_WHIRLEY = 8;
    static final int COLLIDE_EGG = 16;
    static final int EGG_BOMB = 20;
    DynomiteApplet mApplet;
    Board mBoard;
    MTRand mRand;
    double mCeiling;
    int mWidth;
    int mColors;
    int mLowest;
    int mLowestPixel;
    int mLowerTo;
    boolean mLowerToReached;
    int[][] mEggs;
    int[][] mBonus;
    char[][] mDynamite;
    boolean[][] mMark;
    boolean[][] mShake;
    SexyImage mRackImage;
    SexyImage mPuzzleImage;
    Vector mBreakVector;
    double mFallSpeed;
    double mMinimumFallSpeed;
    int mSpeedChangePause;
    int mLowLevel;
    int mHighLevel;
    int mWarningCountdown;
    boolean mFlashLowest;
    boolean mCrush;
    boolean mCrushCracker;
    int mCombo;
    int mNextEgg;
    int mNextBonus;
    Point mComboCenter;
    int mComboCenterCount;
    boolean mShaker;
    int mShakerX;
    int mShakerY;
    int mShakerCountdown;
    int mShakerChance;
    int mBaseShakerChance;
    int mDropCeiling;
    int[] mEggQueue;
    int[] mBonusQueue;
    int mQueueLength;
    int mQueuePos;
    
    public int CountEggs() {
        int n = 0;
        this.FindLowest();
        for (int i = 0; i <= this.mLowest; ++i) {
            int mWidth = this.mWidth;
            if (i % 2 != 0) {
                --mWidth;
            }
            for (int j = 0; j < mWidth; ++j) {
                if (this.mEggs[j][i] != 0 && this.mEggs[j][i] < 18) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    Point ScreenToTiles(int mWidth, int y) {
        final Point point = new Point(0, 0);
        mWidth -= this.mBoard.mCenter - this.mWidth * 23 / 2;
        y -= (int)this.mCeiling;
        y /= 23;
        if (y % 2 != 0) {
            mWidth -= 12;
        }
        mWidth /= 23;
        if (mWidth < 0) {
            mWidth = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (mWidth > this.mWidth) {
            mWidth = this.mWidth;
        }
        if (y > 39) {
            y = 39;
        }
        point.x = mWidth;
        point.y = y;
        return point;
    }
    
    public void Scrolldown() {
        for (int i = this.mLowest; i >= 0; --i) {
            for (int j = 0; j < this.mWidth; ++j) {
                this.mEggs[j][i + 2] = this.mEggs[j][i];
                this.mDynamite[j][i + 2] = this.mDynamite[j][i];
                this.mShake[j][i + 2] = this.mShake[j][i];
                this.mBonus[j][i + 2] = this.mBonus[j][i];
            }
        }
        this.AddRow(0);
        this.AddRow(1);
        this.mLowest += 2;
        this.mShakerY += 2;
        this.FindLowest();
        this.mLowestPixel = (int)this.mCeiling + this.mLowest * 23 + 27;
    }
    
    public void TripBonus(final int n, final int n2) {
        if (this.mBonus[n][n2] != 0 && this.mEggs[n][n2] > 0) {
            final Point tilesToScreen = this.TilesToScreen(n, n2);
            final Animator animator = new Animator(this.mBoard, 7);
            animator.mPosX = tilesToScreen.x;
            animator.mPosY = tilesToScreen.y;
            animator.mValue = this.mBonus[n][n2];
            this.mBoard.mAnimatorVector.addElement(animator);
            this.mApplet.PlaySound(11);
        }
    }
    
    public void Update() {
        final int n = (int)this.mCeiling;
        if (this.mBoard.mMode == 0) {
            if (!this.mLowerToReached) {
                if (this.mLowestPixel < this.mLowerTo) {
                    int n2 = (this.mLowerTo - this.mLowestPixel) / 5;
                    if (n2 < 1) {
                        n2 = 1;
                    }
                    this.mCeiling += n2;
                }
                else {
                    this.mLowerToReached = true;
                }
            }
        }
        else if (!this.mLowerToReached) {
            if (this.mCeiling < this.mLowerTo) {
                this.mCeiling += 2.0;
            }
            else if (!this.mLowerToReached) {
                this.mCeiling = this.mLowerTo;
                this.mLowerToReached = true;
            }
        }
        if (this.mCrush) {
            this.mCeiling += 70.0;
            if (this.mCeiling > 330.0) {
                if (!this.mBoard.mGameOver) {
                    this.mBoard.mGameOver = true;
                    this.mApplet.PlaySound(18);
                    this.mBoard.SoundFix();
                }
                this.mCeiling = 330.0;
            }
            this.mLowestPixel = (int)this.mCeiling + this.mLowest * 23 + 27;
            if (this.mLowestPixel >= 303) {
                for (int i = this.ScreenToTiles(0, 303).x; i < this.mLowest; ++i) {
                    for (int j = 0; j < this.mWidth; ++j) {
                        if (this.mEggs[j][this.mLowest] != 0) {
                            this.CrackEgg(this.TilesToScreen(j, this.mLowest).x, 303 + (this.mRand.Next() % 20 - 10), this.mEggs[j][this.mLowest] - 1, true);
                            this.mEggs[j][this.mLowest] = 0;
                            this.mBonus[j][this.mLowest] = 0;
                        }
                    }
                }
                if (!this.mCrushCracker) {
                    this.mApplet.PlaySound(2);
                    this.mCrushCracker = true;
                }
                this.FindLowest();
                this.mLowestPixel = (int)this.mCeiling + this.mLowest * 23 + 27;
            }
            return;
        }
        if (this.mBoard.mFirstFired) {
            this.mLowestPixel = (int)this.mCeiling + this.mLowest * 23 + 27;
            if (this.mLowestPixel >= 264 && !this.mBoard.mCleared) {
                this.WidthCleanup(false);
                this.mLowestPixel = (int)this.mCeiling + this.mLowest * 23 + 27;
                if (this.mLowestPixel >= 264 && !this.mBoard.mCleared) {
                    if (this.mWarningCountdown == 0) {
                        this.mApplet.PlaySound(8);
                        this.mFlashLowest = false;
                    }
                    ++this.mWarningCountdown;
                    if (this.mWarningCountdown % 10 == 0) {
                        if (this.mFlashLowest) {
                            this.mFlashLowest = false;
                        }
                        else {
                            this.mFlashLowest = true;
                        }
                    }
                    if (this.mWarningCountdown == 170) {
                        this.mCrush = true;
                    }
                }
            }
        }
        if (this.mBoard.mMode == 0) {
            this.UpdateEndless();
        }
        if (this.mBoard.mMode == 1) {
            this.UpdateStomped();
        }
        if (!this.mShaker) {
            if (this.mRand.Next() % this.mShakerChance == 0) {
                this.FindLowest();
                this.mShakerX = this.mRand.Next() % this.mWidth;
                this.mShakerY = this.mRand.Next() % this.mLowest;
                if (this.mEggs[this.mShakerX][this.mShakerY] != 0) {
                    this.mShake[this.mShakerX][this.mShakerY] = true;
                    this.mShakerCountdown = (this.mRand.Next() % 8 * 100 + 200) / 2;
                    this.mShaker = true;
                }
            }
        }
        else {
            if (this.mEggs[this.mShakerX][this.mShakerY] == 0 || this.mCrush) {
                this.mShakerCountdown = 0;
            }
            if (--this.mShakerCountdown < 0) {
                this.mShake[this.mShakerX][this.mShakerY] = false;
                this.mShaker = false;
            }
        }
        this.mLowestPixel = (int)this.mCeiling + this.mLowest * 23 + 27;
        if ((int)this.mCeiling != n) {
            this.mBoard.MarkDirty();
        }
    }
    
    public void Combo(final boolean b) {
        if (!b) {
            this.mCombo = -2;
            return;
        }
        ++this.mCombo;
        if (this.mCombo > 0) {
            if (this.mCombo + 2 > this.mBoard.mBestCombo) {
                this.mBoard.mBestCombo = this.mCombo + 2;
            }
            this.mApplet.mSidebar.Combo(this.mCombo);
            if (this.mCombo >= 7) {
                this.mBoard.mDynoComboDuration = 350;
                this.mCombo = -2;
            }
        }
    }
    
    public void UpdateEndless() {
        if (this.mBoard.mFirstFired) {
            if (this.mLowestPixel < 264) {
                this.mCeiling += this.mFallSpeed;
                this.mCeiling += this.mFallSpeed;
                this.mWarningCountdown = 0;
                this.mFlashLowest = false;
            }
            if (!this.mCrush && --this.mSpeedChangePause <= 0) {
                this.mSpeedChangePause = 0;
                if (this.mLowestPixel > this.mLowLevel) {
                    this.mSpeedChangePause = 50;
                    this.mFallSpeed *= 0.5;
                    if (this.mFallSpeed < this.mMinimumFallSpeed) {
                        this.mFallSpeed = this.mMinimumFallSpeed;
                    }
                }
                if (this.mLowestPixel < this.mHighLevel) {
                    this.mSpeedChangePause = 50;
                    this.mFallSpeed *= 1.25;
                    this.mFallSpeed *= 1.25;
                    if (this.mFallSpeed > 0.3449999988079071) {
                        this.mFallSpeed = 0.3449999988079071;
                    }
                }
            }
        }
        if (this.mCeiling > -27.0) {
            this.Scrolldown();
            this.mCeiling -= 46.0;
        }
    }
    
    public void ComputeBabyChance() {
        double n = 0.0;
        switch (this.mColors) {
            case 3: {
                n = 10.0;
                break;
            }
            case 4: {
                n = 5.0;
                break;
            }
            case 5: {
                n = 3.0;
                break;
            }
            case 6: {
                n = 2.0;
                break;
            }
            case 7: {
                n = 1.0;
                break;
            }
            default: {
                n = 0.5;
                break;
            }
        }
        this.mShakerChance = (int)(this.mBaseShakerChance * n);
        this.mShakerChance /= (int)1.25;
    }
    
    public void Bomb(final int n, final int n2) {
        final Point tilesToScreen = this.TilesToScreen(n, n2);
        final Animator animator = new Animator(this.mBoard, 5);
        animator.mPosX = tilesToScreen.x;
        animator.mPosY = tilesToScreen.y;
        animator.mValue = 0;
        animator.mCountdown = 10 + this.mRand.Next() % 10;
        this.mBoard.mAnimatorVector.addElement(animator);
        this.mComboCenter.x = 0;
        this.mComboCenter.y = 0;
        this.mComboCenterCount = 0;
        if (n2 % 2 != 0) {
            this.SuperBlast(n, n2 - 1);
            this.SuperBlast(n + 1, n2 - 1);
            this.SuperBlast(n - 1, n2);
            this.SuperBlast(n + 1, n2);
            this.SuperBlast(n, n2 + 1);
            this.SuperBlast(n + 1, n2 + 1);
            this.SuperBlast(n - 1, n2 - 2);
            this.SuperBlast(n, n2 - 2);
            this.SuperBlast(n + 1, n2 - 2);
            this.SuperBlast(n - 1, n2 - 1);
            this.SuperBlast(n + 2, n2 - 1);
            this.SuperBlast(n - 2, n2);
            this.SuperBlast(n + 2, n2);
            this.SuperBlast(n - 1, n2 + 1);
            this.SuperBlast(n + 2, n2 + 1);
            this.SuperBlast(n - 1, n2 + 2);
            this.SuperBlast(n, n2 + 2);
            this.SuperBlast(n + 1, n2 + 2);
        }
        else {
            this.SuperBlast(n - 1, n2 - 1);
            this.SuperBlast(n, n2 - 1);
            this.SuperBlast(n - 1, n2);
            this.SuperBlast(n + 1, n2);
            this.SuperBlast(n - 1, n2 + 1);
            this.SuperBlast(n, n2 + 1);
            this.SuperBlast(n - 1, n2 - 2);
            this.SuperBlast(n, n2 - 2);
            this.SuperBlast(n + 1, n2 - 2);
            this.SuperBlast(n - 2, n2 - 1);
            this.SuperBlast(n + 1, n2 - 1);
            this.SuperBlast(n - 2, n2);
            this.SuperBlast(n + 2, n2);
            this.SuperBlast(n - 2, n2 + 1);
            this.SuperBlast(n + 1, n2 + 1);
            this.SuperBlast(n - 1, n2 + 2);
            this.SuperBlast(n, n2 + 2);
            this.SuperBlast(n + 1, n2 + 2);
        }
        if (this.mComboCenterCount != 0) {
            final Point mComboCenter = this.mComboCenter;
            mComboCenter.x /= this.mComboCenterCount;
            final Point mComboCenter2 = this.mComboCenter;
            mComboCenter2.y /= this.mComboCenterCount;
            this.mBoard.Score(this.mComboCenterCount * 10, this.mComboCenter);
        }
        this.DropUnsupportedEggs();
    }
    
    public void AddRow(final int mLowest) {
        int n = this.mBoard.mCenter - this.mWidth * 23 / 2;
        int mWidth = this.mWidth;
        if (mLowest % 2 != 0) {
            n += 12;
            --mWidth;
        }
        for (int i = 0; i < mWidth + 2; ++i) {
            this.mEggs[i][mLowest] = this.mRand.Next() % this.mColors + 1;
            this.mShake[i][mLowest] = false;
            this.mBonus[i][mLowest] = 0;
            this.Dynamite(i, mLowest);
            n += 23;
            if (n - 12 > this.mBoard.mCenter + this.mWidth * 23 / 2) {
                break;
            }
        }
        if (mLowest > this.mLowest) {
            this.mLowest = mLowest;
        }
        this.WidthCleanup(false);
    }
    
    public void DrawEgg(final SexyGraphics sexyGraphics, final int n, final int n2, final int n3, final int n4) {
        if (n3 < 0) {
            return;
        }
        if (n3 != 20) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[10], n, n2, new Rectangle(n3 * 24, 0, 24, 27));
            if (n4 > 0) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[38], n + 3, n2 + 3, new Rectangle((n4 - 1) * 18, 0, 18, 20));
            }
        }
        else {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[36], n - 6, n2 - 21);
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[29], n - 6, n2 - 21 + 12, new Rectangle(this.mRand.Next() % 3 * 7, 0, 7, 7));
        }
    }
    
    public void LandEgg(final int n, final int n2, final int n3, final int n4) {
        this.WidthCleanup(false);
        final Point screenToTiles = this.ScreenToTiles(n, n2);
        if (n3 == 20) {
            if (this.mBoard.mMode == 0) {
                this.mBoard.mBlastLift = 4.83;
            }
            this.mFallSpeed = this.mMinimumFallSpeed;
            this.mApplet.PlaySound(4);
            this.mApplet.PlaySound(2);
            this.Bomb(screenToTiles.x, screenToTiles.y);
            this.FindLowest();
            return;
        }
        this.mEggs[screenToTiles.x][screenToTiles.y] = n3 + 1;
        this.mBonus[screenToTiles.x][screenToTiles.y] = n4;
        this.mShake[screenToTiles.x][screenToTiles.y] = false;
        this.Dynamite(screenToTiles.x, screenToTiles.y);
        this.TilesToScreen(screenToTiles.x, screenToTiles.y);
        final Point point = screenToTiles;
        int n5 = 0;
        while (new PathFinder(this).Go(point.x, point.y) == 0) {
            final int getBestNextEgg = this.GetBestNextEgg();
            final Point point2 = point;
            --point2.y;
            this.mEggs[point.x][point.y] = getBestNextEgg + 1;
            if (++n5 >= 10) {
                break;
            }
        }
        if (screenToTiles.y > this.mLowest) {
            this.mLowest = screenToTiles.y + 1;
        }
        this.Break3(screenToTiles.x, screenToTiles.y);
        this.FindLowest();
        this.mLowestPixel = (int)this.mCeiling + this.mLowest * 23 + 27;
        this.mBoard.EggLandResponse();
    }
    
    public void DrawDynamite(final SexyGraphics sexyGraphics, final int n, final int n2, final int n3) {
        if (n3 <= 0) {
            return;
        }
        final SexyGraphics create = sexyGraphics.create();
        create.ClipRect(n - 10, n2 - 10, 33, 33);
        final Rectangle rectangle = new Rectangle(this.mRand.Next() % 3 * 7, 0, 7, 7);
        switch (n3) {
            case 1: {
                create.DrawImage(this.mApplet.mRes.mImages[20], n - (n3 - 1) * 33 - 10, n2 - 10);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[29], n - (n3 - 1) * 33 - 10, n2 - 8, rectangle);
            }
            case 2: {
                create.DrawImage(this.mApplet.mRes.mImages[20], n - (n3 - 1) * 33 - 8, n2 - 12);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[29], n - (n3 - 1) * 33 + 30, n2 - 14, rectangle);
            }
            case 3: {
                create.DrawImage(this.mApplet.mRes.mImages[20], n - (n3 - 1) * 33 - 5, n2 - 12);
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[29], n - (n3 - 1) * 33 + 75, n2 - 16, rectangle);
            }
            default: {}
        }
    }
    
    Point TilesToScreen(final int x, final int y) {
        final Point point = new Point(0, 0);
        point.x = x;
        point.y = y;
        final Point point2 = point;
        point2.x *= 23;
        final Point point3 = point;
        point3.y *= 23;
        if (y % 2 != 0) {
            final Point point4 = point;
            point4.x += 12;
        }
        final Point point5 = point;
        point5.x += 12;
        final Point point6 = point;
        point6.y += 14;
        final Point point7 = point;
        point7.x += this.mBoard.mCenter - this.mWidth * 23 / 2;
        final Point point8 = point;
        point8.y += (int)this.mCeiling;
        return point;
    }
    
    public void UpdateStomped() {
        if (--this.mDropCeiling > 0) {
            this.mCeiling += 2.0;
        }
        else {
            this.mDropCeiling = 0;
        }
        if (this.mLowestPixel < 264) {
            this.mWarningCountdown = 0;
            this.mFlashLowest = false;
        }
    }
    
    public void FindLowest() {
        int mLowest = 0;
        do {
            int mWidth = this.mWidth;
            if (mLowest % 2 != 0) {
                --mWidth;
            }
            for (int i = 0; i < mWidth; ++i) {
                if (this.mEggs[i][mLowest] != 0) {
                    this.mLowest = mLowest;
                }
            }
        } while (++mLowest < 40);
    }
    
    public void WidthCleanup(final boolean b) {
        int n = 0;
        this.FindLowest();
        int n2 = 0;
        do {
            int mWidth = this.mWidth;
            if (n2 % 2 != 0) {
                --mWidth;
            }
            for (int i = mWidth; i < 14; ++i) {
                if (this.mEggs[i][n2] != 0) {
                    if (b) {
                        n = 1;
                        final Point tilesToScreen = this.TilesToScreen(i, n2);
                        this.CrackEgg(tilesToScreen.x, tilesToScreen.y, this.mEggs[i][n2] - 1, false);
                    }
                    this.mEggs[i][n2] = 0;
                    this.mBonus[i][n2] = 0;
                    this.mShake[i][n2] = false;
                    this.mDynamite[i][n2] = '\0';
                }
            }
        } while (++n2 < 40);
        if (n == 1) {
            this.mApplet.PlaySound(2);
        }
    }
    
    public int NextEgg() {
        final int mNextEgg = this.mNextEgg;
        final int mNextBonus = this.mNextBonus;
        if (this.mBoard.mMode == 0) {
            this.mNextEgg = this.GetBestNextEgg();
            this.mNextBonus = this.mBoard.mNextBonus;
        }
        else {
            final Point getQueueEntry = this.GetQueueEntry();
            this.mNextEgg = getQueueEntry.x;
            this.mNextBonus = getQueueEntry.y;
        }
        this.mBoard.mNextBonus = 0;
        this.mBoard.mHopEgg = true;
        this.mBoard.mHopEggX = this.mBoard.mCenter - 77 + 12;
        this.mBoard.mHopEggY = this.mBoard.mHeight - 57 + 14;
        this.mBoard.mHopEggColor = mNextEgg;
        this.mBoard.mHopEggBonus = mNextBonus;
        this.mBoard.mHopEggXDir = 4.2;
        this.mBoard.mHopEggYDir = -6.0;
        this.mBoard.mSteggyPopdown = true;
        this.mBoard.MarkDirty();
        this.mBoard.mEggSlingshotBonus = mNextBonus;
        return mNextEgg;
    }
    
    public void CrackEgg(final int n, final int n2, final int n3, final boolean b) {
        if (n3 >= 18) {
            return;
        }
        final Point screenToTiles = this.ScreenToTiles(n, n2);
        if (b && (this.mBoard.IsBonus(2) || this.mBoard.IsBonus(3) || this.mBoard.IsBonus(4))) {
            this.mBoard.Glitter(new Point(n, n2));
            this.mApplet.PlaySound(12);
        }
        final Animator animator = new Animator(this.mBoard, 2);
        animator.mPosX = n;
        animator.mPosY = n2;
        animator.mValue2 = 0;
        animator.mValue = n3;
        this.mBoard.mAnimatorVector.addElement(animator);
        final Animator animator2 = new Animator(this.mBoard, 2);
        animator2.mPosX = n;
        animator2.mPosY = n2;
        animator2.mValue2 = 1;
        animator2.mValue = n3;
        this.mBoard.mAnimatorVector.addElement(animator2);
        final Animator animator3 = new Animator(this.mBoard, 2);
        animator3.mPosX = n;
        animator3.mPosY = n2;
        animator3.mValue2 = 2;
        animator3.mValue = n3;
        this.mBoard.mAnimatorVector.addElement(animator3);
        final Animator animator4 = new Animator(this.mBoard, 2);
        animator4.mPosX = n;
        animator4.mPosY = n2;
        animator4.mValue2 = 3;
        animator4.mValue = n3;
        this.mBoard.mAnimatorVector.addElement(animator4);
        if (!this.mShake[screenToTiles.x][screenToTiles.y] || !b) {
            final Animator animator5 = new Animator(this.mBoard, 0);
            animator5.mPosX = n;
            animator5.mPosY = n2 + 2;
            animator5.mXDir = 0.0;
            animator5.mYDir = 2.0;
            this.mBoard.mAnimatorVector.addElement(animator5);
            return;
        }
        final Animator animator6 = new Animator(this.mBoard, 6);
        animator6.mPosX = n;
        animator6.mPosY = n2 + 2;
        animator6.mXDir = 0.0;
        animator6.mYDir = 2.0;
        animator6.mCountdown = 25;
        animator6.mValue = this.mRand.Next() % 3;
        this.mBoard.mAnimatorVector.addElement(animator6);
        final Board mBoard = this.mBoard;
        ++mBoard.mBabiesHatched;
        this.mApplet.PlaySound(9);
        this.mBoard.Score(25, new Point(n, n2 - 12));
    }
    
    public void SpeedUp() {
        this.mMinimumFallSpeed *= 1.2;
        if (this.mMinimumFallSpeed > 0.41) {
            this.mMinimumFallSpeed = 0.41;
        }
        this.mLowLevel += 5;
        if (this.mLowLevel > 225) {
            this.mLowLevel = 225;
        }
    }
    
    public void Break3(final int n, final int n2) {
        this.mBreakVector.removeAllElements();
        this.mMark = new boolean[15][40];
        this.BreakTestAdjacent(n, n2, this.mEggs[n][n2]);
        this.mMark = null;
        final int size = this.mBreakVector.size();
        if (size > 2) {
            final Point point = new Point(0, 0);
            int n3 = 0;
            this.mApplet.PlaySound(2);
            this.mApplet.PlaySound(4);
            final Enumeration<Point> elements = (Enumeration<Point>)this.mBreakVector.elements();
            while (elements.hasMoreElements()) {
                final Point point2 = elements.nextElement();
                final Point tilesToScreen = this.TilesToScreen(point2.x, point2.y);
                final Point point3 = point;
                point3.x += tilesToScreen.x;
                final Point point4 = point;
                point4.y += tilesToScreen.y;
                ++n3;
                if (this.mDynamite[point2.x][point2.y] != '\0') {
                    final Animator animator = new Animator(this.mBoard, 5);
                    animator.mPosX = tilesToScreen.x - 5;
                    animator.mPosY = tilesToScreen.y - 5;
                    animator.mValue = 0;
                    animator.mCountdown = 10 + this.mRand.Next() % 10;
                    this.mBoard.mAnimatorVector.addElement(animator);
                    this.mDynamite[point2.x][point2.y] = '\0';
                }
                this.CrackEgg(tilesToScreen.x, tilesToScreen.y, this.mEggs[point2.x][point2.y] - 1, true);
                this.TripBonus(point2.x, point2.y);
                this.mEggs[point2.x][point2.y] = 0;
                this.mBonus[point2.x][point2.y] = 0;
            }
            final int dropUnsupportedEggs = this.DropUnsupportedEggs();
            final Point point5 = point;
            point5.x /= n3;
            final Point point6 = point;
            point6.y /= n3;
            int n4 = size;
            if (dropUnsupportedEggs > 0) {
                n4 *= dropUnsupportedEggs;
            }
            this.Combo(true);
            this.mBoard.Score(n4, point);
            this.mBoard.mTankCountdown = 20;
            this.mBoard.mTankPose = 1;
            return;
        }
        this.Combo(false);
    }
    
    public void LoadRound(final String s) {
        this.Reset();
        this.mQueueLength = 0;
        int n = 0;
        while (true) {
            final int index = s.indexOf(124, n);
            if (index == -1) {
                break;
            }
            final String substring = s.substring(n, index);
            final String substring2 = substring.substring(2);
            if (substring.startsWith("N:")) {
                this.mBoard.mRoundName = substring2;
            }
            if (substring.startsWith("W:")) {
                this.mWidth = Integer.parseInt(substring2);
            }
            if (substring.startsWith("C:")) {
                this.mLowerTo = Integer.parseInt(substring2) - 12;
            }
            if (substring.startsWith("S:")) {
                final Board mBoard = this.mBoard;
                final Board mBoard2 = this.mBoard;
                final int int1 = Integer.parseInt(substring2);
                mBoard2.mMamaMeterTop = int1;
                mBoard.mMamaMeter = int1;
            }
            if (substring.startsWith("B:")) {
                this.mShakerChance = (int)(100.0 + (1.0 - Integer.parseInt(substring2) / 1000) * 10000.0);
                this.mShakerChance /= (int)1.25;
            }
            if (substring.startsWith("G:")) {
                final int n2 = substring.indexOf(":") + 1;
                final int index2 = substring.indexOf(",", n2);
                final int int2 = Integer.parseInt(substring.substring(n2, index2));
                final int n3 = index2 + 1;
                final int index3 = substring.indexOf(",", n3);
                final int int3 = Integer.parseInt(substring.substring(n3, index3));
                final int n4 = index3 + 1;
                final int index4 = substring.indexOf(",", n4);
                final int int4 = Integer.parseInt(substring.substring(n4, index4));
                final int int5 = Integer.parseInt(substring.substring(index4 + 1));
                this.mEggs[int2][int3] = int4;
                this.mBonus[int2][int3] = int5;
                this.Dynamite(int2, int3);
            }
            if (substring.startsWith("Q:")) {
                final int n5 = substring.indexOf(":") + 1;
                final int index5 = substring.indexOf(",", n5);
                final int int6 = Integer.parseInt(substring.substring(n5, index5));
                final int int7 = Integer.parseInt(substring.substring(index5 + 1));
                this.mEggQueue[this.mQueueLength] = int6;
                this.mBonusQueue[this.mQueueLength] = int7;
                ++this.mQueueLength;
            }
            this.mQueuePos = 0;
            n = index + 1;
        }
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        for (int n = (int)this.mCeiling, n2 = 0; n2 <= this.mLowest && (!this.mFlashLowest || n + 27 < 264); n += 23, ++n2) {
            int n3 = this.mBoard.mCenter - this.mWidth * 23 / 2;
            int mWidth = this.mWidth;
            if (n2 % 2 != 0) {
                n3 += 12;
                --mWidth;
            }
            for (int i = 0; i < mWidth; ++i) {
                int n4 = n3;
                int n5 = n;
                if (this.mShake[i][n2]) {
                    n4 += this.mRand.Next() % 9 - 4;
                    n5 += this.mRand.Next() % 9 - 4;
                }
                this.DrawDynamite(sexyGraphics, n4, n5, this.mDynamite[i][n2]);
                this.DrawEgg(sexyGraphics, n4, n5, this.mEggs[i][n2] - 1, this.mBonus[i][n2]);
                n3 += 23;
                if (n3 - 12 > this.mBoard.mCenter + this.mWidth * 23 / 2) {
                    break;
                }
            }
        }
    }
    
    public EggRack(final DynomiteApplet mApplet, final Board mBoard) {
        this.mBreakVector = new Vector();
        this.mComboCenter = new Point(0, 0);
        this.mEggQueue = new int[256];
        this.mBonusQueue = new int[256];
        this.mRand = new MTRand((int)(Math.random() * 65535.0));
        this.mApplet = mApplet;
        this.mBoard = mBoard;
        this.mCeiling = -14.0;
        this.mCombo = -2;
        if (this.mApplet.mSkill == 0) {
            this.mColors = 3;
        }
        if (this.mApplet.mSkill == 1) {
            this.mColors = 4;
        }
        if (this.mApplet.mSkill == 2) {
            this.mColors = 6;
        }
        this.mWidth = 8;
        this.mEggs = new int[15][40];
        this.mBonus = new int[15][40];
        this.mShake = new boolean[15][40];
        this.mDynamite = new char[15][40];
        this.mLowerToReached = false;
        this.mLowerTo = 165;
        (this.mRackImage = new SexyImage()).Create(337, 330);
        this.mRackImage.SetImageMode(false, false);
        (this.mPuzzleImage = new SexyImage()).Create(337, 380);
        this.mPuzzleImage.SetImageMode(false, false);
        this.AddRow(this.mLowest = 0);
        this.AddRow(1);
        this.AddRow(2);
        this.AddRow(3);
        this.mLowLevel = 180;
        this.mHighLevel = 120;
        final double n = 0.0276;
        this.mFallSpeed = n;
        this.mMinimumFallSpeed = n;
        this.mSpeedChangePause = 0;
        this.mCrush = false;
        this.mCrushCracker = false;
        this.mShaker = false;
        this.mShakerChance = 1;
        this.mNextEgg = this.GetBestNextEgg();
        this.mNextBonus = 0;
        this.mBaseShakerChance = 200;
        this.ComputeBabyChance();
        this.mDropCeiling = 0;
        this.Lift();
    }
    
    public int Collide(final int n, final int n2) {
        int n3 = 0;
        final int n4 = this.mBoard.mCenter - this.mWidth * 23 / 2 - 37 + 47;
        final int n5 = this.mBoard.mCenter + this.mWidth * 23 / 2 - 5 + 10 - 15;
        if (n < n4) {
            n3 |= 0x1;
        }
        if (n > n5) {
            n3 |= 0x2;
        }
        if (n2 < (int)this.mCeiling) {
            n3 |= 0x4;
        }
        if (this.mBoard.mWhirley && this.mBoard.mWhirleyState < 2 && new Rectangle((int)(this.mBoard.mWhirleyX - 12.0 - 5.0), (int)(this.mBoard.mWhirleyY + 12.0), 28, 25).contains(n, n2)) {
            n3 |= 0x8;
        }
        final Point screenToTiles = this.ScreenToTiles(n, n2);
        if (this.mEggs[screenToTiles.x][screenToTiles.y] != 0 && screenToTiles.x < this.mWidth) {
            final Point tilesToScreen = this.TilesToScreen(screenToTiles.x, screenToTiles.y);
            if (new Rectangle(tilesToScreen.x - 12, tilesToScreen.y - 14, 23, 23).contains(n, n2)) {
                n3 |= 0x10;
            }
        }
        return n3;
    }
    
    public void BreakTestAdjacent(final int n, final int n2, final int n3) {
        if (n3 >= 18) {
            return;
        }
        if (n < 0) {
            return;
        }
        if (n2 < 0) {
            return;
        }
        if (n >= this.mWidth) {
            return;
        }
        if (n2 > this.mLowest) {
            return;
        }
        if (this.mEggs[n][n2] == n3 && !this.mMark[n][n2]) {
            this.mMark[n][n2] = true;
            this.mBreakVector.addElement(new Point(n, n2));
            this.BreakTestAdjacent(n - 1, n2, n3);
            this.BreakTestAdjacent(n + 1, n2, n3);
            if (n2 % 2 == 0) {
                this.BreakTestAdjacent(n - 1, n2 - 1, n3);
                this.BreakTestAdjacent(n, n2 - 1, n3);
                this.BreakTestAdjacent(n - 1, n2 + 1, n3);
                this.BreakTestAdjacent(n, n2 + 1, n3);
                return;
            }
            this.BreakTestAdjacent(n, n2 - 1, n3);
            this.BreakTestAdjacent(n + 1, n2 - 1, n3);
            this.BreakTestAdjacent(n, n2 + 1, n3);
            this.BreakTestAdjacent(n + 1, n2 + 1, n3);
        }
    }
    
    public void Dynamite(final int n, final int n2) {
        this.mDynamite[n][n2] = '\0';
        final int n3 = this.mEggs[n][n2];
        int n4 = 0;
        if (n > 0 && this.mEggs[n - 1][n2] == n3 && this.mDynamite[n - 1][n2] == '\0') {
            ++n4;
        }
        if (n < 14 && this.mEggs[n + 1][n2] == n3 && this.mDynamite[n + 1][n2] == '\0') {
            ++n4;
        }
        if (n2 % 2 != 0) {
            if (n2 > 0) {
                if (n > 0 && this.mEggs[n - 1][n2 - 1] == n3 && this.mDynamite[n - 1][n2 - 1] == '\0') {
                    ++n4;
                }
                if (this.mEggs[n][n2 - 1] == n3 && this.mDynamite[n][n2 - 1] == '\0') {
                    ++n4;
                }
            }
            if (n2 < 39) {
                if (n > 0 && this.mEggs[n - 1][n2 + 1] == n3 && this.mDynamite[n - 1][n2 + 1] == '\0') {
                    ++n4;
                }
                if (this.mEggs[n][n2 + 1] == n3 && this.mDynamite[n][n2 + 1] == '\0') {
                    ++n4;
                }
            }
        }
        else {
            if (n2 > 0) {
                if (this.mEggs[n][n2 - 1] == n3 && this.mDynamite[n][n2 - 1] == '\0') {
                    ++n4;
                }
                if (n < 14 && this.mEggs[n + 1][n2 - 1] == n3 && this.mDynamite[n + 1][n2 - 1] == '\0') {
                    ++n4;
                }
            }
            if (n2 < 39) {
                if (this.mEggs[n][n2 + 1] == n3 && this.mDynamite[n][n2 + 1] == '\0') {
                    ++n4;
                }
                if (n < 14 && this.mEggs[n + 1][n2 + 1] == n3 && this.mDynamite[n + 1][n2 + 1] == '\0') {
                    ++n4;
                }
            }
        }
        if (n4 > 0) {
            this.mDynamite[n][n2] = (char)(this.mRand.Next() % 3 + 1);
        }
    }
    
    public int DropUnsupportedEggs() {
        int n = 0;
        this.mBreakVector.removeAllElements();
        final PathFinder pathFinder = new PathFinder(this);
        int i = 1;
        while (i == 1) {
            i = 0;
            for (int j = 0; j <= this.mLowest; ++j) {
                for (int k = 0; k < this.mWidth; ++k) {
                    if (this.mEggs[k][j] != 0 && pathFinder.Go(k, j) == 0) {
                        final Point tilesToScreen = this.TilesToScreen(k, j);
                        if (this.mDynamite[k][j] != '\0') {
                            final Animator animator = new Animator(this.mBoard, 4);
                            animator.mPosX = tilesToScreen.x;
                            animator.mPosY = tilesToScreen.y;
                            animator.mXDir = 0.0;
                            animator.mYDir = 1.0;
                            animator.mValue = this.mDynamite[k][j];
                            this.mBoard.mAnimatorVector.addElement(animator);
                            this.mDynamite[k][j] = '\0';
                        }
                        final Animator animator2 = new Animator(this.mBoard, 1);
                        animator2.mPosX = tilesToScreen.x;
                        animator2.mPosY = tilesToScreen.y + 2;
                        animator2.mXDir = 0.0;
                        animator2.mYDir = 2.0;
                        animator2.mValue = this.mEggs[k][j] - 1;
                        animator2.mValue2 = this.mBonus[k][j];
                        this.mBoard.mAnimatorVector.addElement(animator2);
                        ++n;
                        this.mEggs[k][j] = 0;
                        this.mBonus[k][j] = 0;
                        i = 1;
                    }
                }
            }
        }
        this.FindLowest();
        this.mMark = null;
        return n;
    }
    
    public void Lift() {
        this.FindLowest();
        this.mCeiling = -this.mLowest * 27;
    }
    
    public void SuperBlast(final int n, final int n2) {
        if (n < 0) {
            return;
        }
        if (n > 14) {
            return;
        }
        if (n2 < 0) {
            return;
        }
        if (n2 > 39) {
            return;
        }
        if (this.mEggs[n][n2] == 0) {
            return;
        }
        final Point tilesToScreen = this.TilesToScreen(n, n2);
        final Animator animator = new Animator(this.mBoard, 5);
        animator.mPosX = tilesToScreen.x;
        animator.mPosY = tilesToScreen.y;
        animator.mValue = 0;
        animator.mCountdown = 10 + this.mRand.Next() % 10;
        this.mBoard.mAnimatorVector.addElement(animator);
        final Point mComboCenter = this.mComboCenter;
        mComboCenter.x += tilesToScreen.x;
        final Point mComboCenter2 = this.mComboCenter;
        mComboCenter2.y += tilesToScreen.y;
        ++this.mComboCenterCount;
        this.CrackEgg(tilesToScreen.x, tilesToScreen.y, this.mEggs[n][n2], true);
        this.TripBonus(n, n2);
        this.mEggs[n][n2] = 0;
        this.mBonus[n][n2] = 0;
        this.mDynamite[n][n2] = '\0';
    }
    
    public void SpeedReset() {
        final double n = 0.0276;
        this.mFallSpeed = n;
        this.mMinimumFallSpeed = n;
        this.mLowLevel = 180;
    }
    
    public void Reset() {
        this.mEggs = new int[15][40];
        this.mBonus = new int[15][40];
        this.mShake = new boolean[15][40];
        this.mDynamite = new char[15][40];
    }
    
    public int GetBestNextEgg() {
        final int[] array = new int[20];
        int n = this.mLowest - 10;
        if (n < 0) {
            n = 0;
        }
        for (int i = 0; i < this.mWidth; ++i) {
            for (int j = n; j <= this.mLowest; ++j) {
                if (this.mEggs[i][j] != 0 && this.mEggs[i][j] < 18) {
                    array[this.mEggs[i][j]] = 1;
                }
            }
        }
        final int[] array2 = new int[20];
        int n2 = 0;
        int n3 = 0;
        do {
            if (array[n3] == 1) {
                array2[n2++] = n3;
            }
        } while (++n3 < 20);
        int k = 0;
        int n4 = 0;
        array[0] = 0;
        while (true) {
            if (k == 0) {
                k = array2[this.mRand.Next() % n2];
                if (k == this.mBoard.mLastFiredEgg) {
                    k = 0;
                }
                if (++n4 <= 50) {
                    continue;
                }
            }
            if (n4 <= 50) {
                break;
            }
            if (this.mBoard.mLastFiredEgg != -1) {
                this.mBoard.mLastFiredEgg = -1;
            }
            else {
                if (this.mBoard.mLastRealFiredEgg != -1) {
                    k = this.mBoard.mLastRealFiredEgg;
                    break;
                }
                k = this.mRand.Next() % this.mColors + 1;
                break;
            }
        }
        while (k == 0) {
            k = this.mRand.Next() % this.mColors + 1;
        }
        if (k <= 0 || k >= 18) {
            k = 1;
        }
        return k - 1;
    }
    
    public Point GetQueueEntry() {
        if (this.mBoard.mMode == 0) {
            return new Point(this.GetBestNextEgg(), 0);
        }
        if (this.mQueuePos < this.mQueueLength) {
            int getBestNextEgg = this.mEggQueue[this.mQueuePos] - 1;
            final int n = this.mBonusQueue[this.mQueuePos];
            ++this.mQueuePos;
            if (getBestNextEgg == 19) {
                getBestNextEgg = this.GetBestNextEgg();
            }
            return new Point(getBestNextEgg, n);
        }
        return new Point(this.GetBestNextEgg(), 0);
    }
}
