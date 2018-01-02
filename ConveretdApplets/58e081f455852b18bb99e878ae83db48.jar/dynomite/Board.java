// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import sexy.gui.SexyColor;
import java.awt.Rectangle;
import java.awt.Color;
import sexy.gui.SexyGraphics;
import java.util.Enumeration;
import java.awt.Point;
import sexy.gui.WidgetManager;
import sexy.gui.OutlineButtonWidget;
import java.util.Vector;
import sexy.gui.ButtonListener;
import sexy.gui.Widget;

public class Board extends Widget implements ButtonListener
{
    static final String[] ScoreNames;
    static final int ROPE_LEVEL = 264;
    static final int MODE_ENDLESS = 0;
    static final int MODE_STOMPED = 1;
    static final int SKILL_EASY = 0;
    static final int SKILL_NORMAL = 1;
    static final int SKILL_HARD = 2;
    static final int BONUS_LIGHTBULB = 1;
    static final int BONUS_2X = 2;
    static final int BONUS_3X = 3;
    static final int BONUS_4X = 4;
    static final int BONUS_RAISE = 5;
    static final int BONUS_WIDEN = 6;
    static final int BONUS_NARROW = 7;
    static final int BONUS_5 = 8;
    static final int BONUS_10 = 9;
    static final int BONUS_15 = 10;
    static final int BUTTON_REPLAY = 0;
    static final int BUTTON_UPLOAD = 1;
    static final String[] ComboName;
    static final double[] mWhirleyAdjustX;
    static final double[] mWhirleyAdjustY;
    int mGameTime;
    int mBestCombo;
    int mWhirleysWhacked;
    int mBabiesHatched;
    DynomiteApplet mApplet;
    EggRack mRack;
    int mMode;
    int mSkill;
    int mCenter;
    int mAimX;
    int mAimY;
    double mAimAngle;
    int mEggSlingshotColor;
    int mEggSlingshotBonus;
    boolean mFirstFired;
    int mLastFiredEgg;
    int mLastRealFiredEgg;
    double mTextFade;
    double mTextFadeDir;
    boolean mEggInAir;
    double mEggInAirX;
    double mEggInAirY;
    double mEggInAirXDir;
    double mEggInAirYDir;
    int mEggInAirColor;
    int mEggInAirBonus;
    String mRoundNumber;
    String mRoundName;
    String mEcho;
    Vector mAnimatorVector;
    boolean mGameOver;
    boolean mHopEgg;
    double mHopEggX;
    double mHopEggY;
    double mHopEggXDir;
    double mHopEggYDir;
    int mHopEggColor;
    int mHopEggBonus;
    int mTankPose;
    int mTankCountdown;
    boolean mSteggyPopdown;
    int mSteggyYpos;
    double mSteggyBounce;
    double mSteggyBounceDir;
    int mKroneyYpos;
    double mKroneySand;
    double mKroneySandMax;
    int mKroneySandWobble;
    boolean mKroneyFlip;
    double mKroneyFlipFrame;
    boolean mWhirley;
    double mWhirleyX;
    double mWhirleyY;
    double mWhirleyXDir;
    double mWhirleyYDir;
    double mWhirleySpeedX;
    double mWhirleySpeedY;
    double mWhirleyFrame;
    int mWhirleyLevel;
    int mWhirleyState;
    int mDynoComboDuration;
    boolean mDynoComboFlash;
    int mDynoComboFlashCountdown;
    int mDynoComboEggType;
    double mBlastLift;
    int mNextBonus;
    boolean[] mBonus;
    int[] mBonusType;
    int[] mBonusCountdown;
    boolean mBonusFlicker;
    int mBonusFlickerCount;
    double mBonusAngle;
    boolean mPause;
    int mGameOverRise;
    int mGameOverPause;
    OutlineButtonWidget mReplayButton;
    OutlineButtonWidget mUploadScoreButton;
    int[] mScoreProgression;
    int mScoreTitle;
    int mMamaMeter;
    int mMamaMeterTop;
    int mMamaMeterLevel;
    int mDesiredMamaMeterLevel;
    int mWarningSoundCountdown;
    int mRound;
    int mRealRound;
    int mRoundViewCountdown;
    boolean mCleared;
    int mClearedCountdown;
    
    public void MouseMove(final int mAimX, final int mAimY) {
        super.MouseMove(mAimX, mAimY);
        if (this.mPause) {
            return;
        }
        this.mAimX = mAimX;
        this.mAimY = mAimY;
        this.mAimAngle = this.GetPointAngle(this.mCenter, 298, this.mAimX, this.mAimY);
    }
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        if (this.mReplayButton != null) {
            widgetManager.RemoveWidget(this.mReplayButton);
        }
        if (this.mUploadScoreButton != null) {
            widgetManager.RemoveWidget(this.mUploadScoreButton);
        }
        this.mApplet.StopSound(26);
    }
    
    public void NewRandomBonus() {
        this.mNextBonus = -1;
        if (this.mRack.mLowestPixel > 193 && this.mRack.mRand.Next() % 3 == 0) {
            this.mNextBonus = 5;
        }
        if (this.mNextBonus == -1 && this.mRack.mLowestPixel > 234) {
            this.mNextBonus = 5;
        }
        if (this.mNextBonus == -1 && this.mRack.mRand.Next() % 5 == 2) {
            switch (this.mRack.mRand.Next() % 3) {
                case 0: {
                    this.mNextBonus = 1;
                    break;
                }
                case 1: {
                    this.mNextBonus = 6;
                    break;
                }
                case 2: {
                    this.mNextBonus = 7;
                    break;
                }
            }
            if (this.mMode == 1) {
                this.mNextBonus = 1;
            }
        }
        if (this.mNextBonus == -1) {
            this.mNextBonus = this.mRack.mRand.Next() % 3 + 2;
            if (this.mRack.mRand.Next() % 2 == 0) {
                this.mNextBonus += 6;
            }
        }
    }
    
    public void TraceEggBackwards() {
        int n = 0;
        int n2;
        do {
            this.mEggInAirX -= this.mEggInAirXDir;
            this.mEggInAirY -= this.mEggInAirYDir;
            n2 = (0x0 | this.mRack.Collide((int)this.mEggInAirX, (int)this.mEggInAirY - 8) | this.mRack.Collide((int)this.mEggInAirX - 4, (int)this.mEggInAirY - 6) | this.mRack.Collide((int)this.mEggInAirX + 4, (int)this.mEggInAirY - 6) | this.mRack.Collide((int)this.mEggInAirX - 6, (int)this.mEggInAirY) | this.mRack.Collide((int)this.mEggInAirX + 6, (int)this.mEggInAirY) | this.mRack.Collide((int)this.mEggInAirX, (int)this.mEggInAirY + 5));
            final Point screenToTiles = this.mRack.ScreenToTiles((int)this.mEggInAirX, (int)this.mEggInAirY);
            if (this.mRack.mEggs[screenToTiles.x][screenToTiles.y] != 0) {
                n2 = 16;
            }
            if ((n2 & 0x1) != 0x0) {
                this.mEggInAirXDir = -Math.abs(this.mEggInAirXDir);
            }
            if ((n2 & 0x2) != 0x0) {
                this.mEggInAirXDir = Math.abs(this.mEggInAirXDir);
            }
        } while (n2 != 0 && ++n < 100);
    }
    
    public void Update() {
        if (this.mApplet.mRes.mImageLeg.IsReady() && this.mApplet.mRes.mImageWhirley.IsReady() && this.mApplet.mRes.mImageKroneyflip.IsReady() && this.mApplet.mRes.mImageGameOver.IsReady() && !this.mApplet.mAdInitiated) {
            this.mApplet.BeginAdGraphicsStream();
        }
        if (this.mGameOver && --this.mGameOverPause <= 0) {
            this.mGameOverPause = 0;
            this.mGameOverRise += 2;
            if (this.mGameOverRise > 100) {
                if (this.mReplayButton == null) {
                    this.mReplayButton = new OutlineButtonWidget(super.mWidgetManager, 0, this);
                    this.mReplayButton.mDoFinger = true;
                    this.mReplayButton.mFont = this.mApplet.mSmallFont;
                    this.mReplayButton.mLabel = "Play this puzzle again!";
                    this.mReplayButton.Resize(165, 275, 228, 25);
                    super.mWidgetManager.AddWidget(this.mReplayButton);
                    if (this.mApplet.mIsMSN) {
                        this.mUploadScoreButton = new OutlineButtonWidget(super.mWidgetManager, 1, this);
                        this.mUploadScoreButton.mDoFinger = true;
                        this.mUploadScoreButton.mFont = this.mApplet.mSmallFont;
                        this.mUploadScoreButton.mLabel = "Post Your Score";
                        this.mUploadScoreButton.Resize(165, 247, 228, 25);
                        super.mWidgetManager.AddWidget(this.mUploadScoreButton);
                    }
                }
                this.mGameOverRise = 100;
            }
        }
        if (this.mPause) {
            return;
        }
        if (!this.mGameOver && this.mFirstFired) {
            ++this.mGameTime;
        }
        if (this.mApplet.mNeedSendGameStart) {
            String s;
            if (this.mMode == 0) {
                s = "endless ";
            }
            else {
                s = "stomped ";
            }
            this.mApplet.SendGameStartNotification(s + this.mSkill);
        }
        if (this.mRoundViewCountdown > 0) {
            --this.mRoundViewCountdown;
        }
        if (this.mCleared) {
            final EggRack mRack = this.mRack;
            mRack.mCeiling -= 2.0;
            if (this.mRack.mCeiling < -120.0) {
                this.mRack.mCeiling = -120.0;
            }
            if (this.mClearedCountdown > 0) {
                --this.mClearedCountdown;
                if (this.mClearedCountdown == 250) {
                    this.mApplet.PlaySound(29);
                }
                if (this.mClearedCountdown == 225) {
                    this.mApplet.PlaySound(29);
                    this.Score(100, new Point(this.mCenter, 190));
                }
                if (this.mClearedCountdown == 125) {
                    this.SendGameBreak(false);
                    this.NextRound();
                }
            }
        }
        if (this.mDynoComboDuration > 0) {
            this.mDynoComboDuration -= 2;
            if (this.mDynoComboDuration < 175) {
                if (this.mDynoComboDuration >= 173) {
                    this.mApplet.PlaySound(27);
                    this.mDynoComboFlash = true;
                    this.mDynoComboFlashCountdown = 15;
                    this.mDynoComboEggType = this.mEggSlingshotColor;
                }
                this.mDynoComboFlashCountdown -= 2;
                if (this.mDynoComboFlashCountdown < 0) {
                    this.mEggSlingshotBonus = 0;
                    if (this.mDynoComboFlash) {
                        this.mDynoComboFlash = false;
                    }
                    else {
                        this.mDynoComboFlash = true;
                    }
                    this.mDynoComboFlashCountdown = 15;
                    if (!this.mDynoComboFlash) {
                        this.mEggSlingshotColor = 20;
                    }
                    else {
                        this.mEggSlingshotColor = this.mDynoComboEggType;
                    }
                }
                this.MarkDirty();
                return;
            }
        }
        this.mBonusAngle -= 3.0;
        if (this.mBonusAngle < 0.0) {
            this.mBonusAngle += 360.0;
        }
        this.mRack.Update();
        if (!this.mFirstFired) {
            this.mTextFade += this.mTextFadeDir;
            if (this.mTextFade > 1.0) {
                this.mTextFade = 1.0;
                this.mTextFadeDir = -Math.abs(this.mTextFadeDir);
            }
            if (this.mTextFade < 0.5) {
                this.mTextFade = 0.5;
                this.mTextFadeDir = Math.abs(this.mTextFadeDir);
            }
            this.MarkDirty();
        }
        if (this.mHopEgg) {
            this.mHopEggX += this.mHopEggXDir;
            this.mHopEggY += this.mHopEggYDir;
            ++this.mHopEggYDir;
            if (this.mHopEggX > this.mCenter) {
                this.mHopEgg = false;
            }
            this.MarkDirty();
        }
        if (this.mEggInAir) {
            int n = 0;
            do {
                this.mEggInAirX += this.mEggInAirXDir;
                this.mEggInAirY += this.mEggInAirYDir;
                final int n2 = 0x0 | this.mRack.Collide((int)this.mEggInAirX, (int)this.mEggInAirY - 8) | this.mRack.Collide((int)this.mEggInAirX - 4, (int)this.mEggInAirY - 6) | this.mRack.Collide((int)this.mEggInAirX + 4, (int)this.mEggInAirY - 6) | this.mRack.Collide((int)this.mEggInAirX - 6, (int)this.mEggInAirY) | this.mRack.Collide((int)this.mEggInAirX + 6, (int)this.mEggInAirY) | this.mRack.Collide((int)this.mEggInAirX, (int)this.mEggInAirY + 5);
                if ((n2 & 0x8) != 0x0) {
                    this.mRack.CrackEgg((int)(this.mWhirleyX - 12.0) + 12, (int)(this.mWhirleyY + 12.0) + 12, this.mRack.mColors, true);
                    if (this.mEggInAirColor != 20) {
                        this.mRack.CrackEgg((int)this.mEggInAirX, (int)this.mEggInAirY, this.mEggInAirColor, true);
                        this.mEggInAir = false;
                    }
                    this.mWhirleyState = 2;
                    ++this.mWhirleysWhacked;
                    this.mApplet.PlaySound(24);
                    this.mApplet.PlaySound(2);
                    this.Score(25, new Point((int)this.mWhirleyX, (int)this.mWhirleyY));
                    break;
                }
                if ((n2 & 0x4) != 0x0 || (n2 & 0x10) != 0x0) {
                    this.mApplet.PlaySound(1);
                    this.TraceEggBackwards();
                    this.mRack.LandEgg((int)this.mEggInAirX, (int)this.mEggInAirY, this.mEggInAirColor, this.mEggInAirBonus);
                    this.mEggInAir = false;
                    break;
                }
                if ((n2 & 0x1) != 0x0) {
                    this.mEggInAirXDir = Math.abs(this.mEggInAirXDir);
                }
                if ((n2 & 0x2) == 0x0) {
                    continue;
                }
                this.mEggInAirXDir = -Math.abs(this.mEggInAirXDir);
            } while (++n < 6);
        }
        if (this.mTankCountdown > 0) {
            --this.mTankCountdown;
            if (this.mTankCountdown == 0) {
                this.mTankPose = 0;
            }
        }
        this.mSteggyBounce += this.mSteggyBounceDir;
        if (this.mSteggyBounce > 3.0) {
            this.mSteggyBounceDir = -0.5;
        }
        if (this.mSteggyBounce < -3.0) {
            this.mSteggyBounceDir = 0.5;
        }
        if (this.mMode == 1) {
            if (this.mMamaMeterLevel != this.mDesiredMamaMeterLevel) {
                if (this.mMamaMeterLevel < this.mDesiredMamaMeterLevel) {
                    ++this.mMamaMeterLevel;
                }
                if (this.mMamaMeterLevel > this.mDesiredMamaMeterLevel) {
                    --this.mMamaMeterLevel;
                }
            }
            if (this.mMamaMeter < 3 && --this.mWarningSoundCountdown <= 0) {
                if (!this.mGameOver) {
                    this.mWarningSoundCountdown = 75;
                    this.mApplet.PlaySound(25);
                }
                else {
                    this.mWarningSoundCountdown = 0;
                }
            }
        }
        if (this.mMode == 0) {
            if (!this.mRack.mCrush) {
                if (this.mKroneyYpos < 30) {
                    ++this.mKroneyYpos;
                }
                if (!this.mKroneyFlip) {
                    if (this.mFirstFired) {
                        --this.mKroneySand;
                        if (!this.mApplet.mRes.mImageWhirley.IsReady() && this.mKroneySand < 300.0) {
                            this.mKroneySand = 300.0;
                        }
                        if (this.mKroneySand == 250.0) {
                            this.mApplet.PlaySound(21);
                        }
                        if (this.mKroneySand == 150.0) {
                            this.mApplet.PlaySound(22);
                        }
                        if (this.mKroneySand == 100.0) {
                            this.mApplet.PlaySound(23);
                        }
                        if (this.mKroneySand < 0.0) {
                            this.mKroneySand = this.mKroneySandMax;
                            this.mKroneyFlip = true;
                            this.mKroneyFlipFrame = 0.0;
                            this.StartWhirley();
                        }
                    }
                }
                else {
                    this.mKroneyFlipFrame += 0.25;
                    if (this.mKroneyFlipFrame >= 5.0) {
                        this.mKroneyFlip = false;
                        this.mKroneyFlipFrame = 0.0;
                    }
                }
            }
            else {
                this.mKroneyYpos -= 25;
                if (this.mKroneyYpos < -150) {
                    this.mKroneyYpos = -150;
                }
            }
            this.mKroneySandWobble += 5;
            if (this.mKroneySandWobble >= 40) {
                this.mKroneySandWobble = 0;
            }
        }
        if (this.mSteggyPopdown) {
            this.mSteggyYpos += 10;
            if (this.mSteggyYpos > 330) {
                this.mSteggyPopdown = false;
            }
        }
        else if (this.mSteggyYpos > 250) {
            this.mSteggyYpos -= 10;
        }
        final Enumeration<Animator> elements = (Enumeration<Animator>)this.mAnimatorVector.elements();
        while (elements.hasMoreElements()) {
            final Animator animator = elements.nextElement();
            if (!animator.Update()) {
                this.mAnimatorVector.removeElement(animator);
            }
        }
        if (this.mWhirley) {
            this.mWhirleyFrame += 0.3;
            if (this.mWhirleyFrame >= 6.0) {
                this.mWhirleyFrame -= 6.0;
            }
            switch (this.mWhirleyState) {
                case 0: {
                    this.mWhirleyY += this.mWhirleyYDir;
                    this.mWhirleyYDir *= 0.9;
                    if (Math.abs(this.mWhirleyYDir) < this.mWhirleySpeedY) {
                        this.mWhirleyYDir = -this.mWhirleySpeedY;
                        this.mWhirleyXDir = this.mWhirleySpeedX;
                        this.mWhirleyState = 1;
                        break;
                    }
                    break;
                }
                case 1: {
                    this.mWhirleyX += this.mWhirleyXDir;
                    this.mWhirleyY += this.mWhirleyYDir;
                    if (this.mWhirleyX > this.mCenter + this.mRack.mWidth * 23 / 2 - 24) {
                        this.mWhirleyXDir = -Math.abs(this.mWhirleyXDir);
                    }
                    if (this.mWhirleyX < this.mCenter - this.mRack.mWidth * 23 / 2 + 24) {
                        this.mWhirleyXDir = Math.abs(this.mWhirleyXDir);
                    }
                    if (this.mWhirleyY < -40.0) {
                        final EggRack mRack2 = this.mRack;
                        ++mRack2.mColors;
                        if (this.mRack.mColors > 8) {
                            this.mRack.mColors = 8;
                        }
                        this.mRack.ComputeBabyChance();
                        this.mWhirley = false;
                        this.mWhirleyLevel = 1;
                        this.mRack.SpeedReset();
                        break;
                    }
                    break;
                }
                case 2: {
                    this.mWhirleyY -= 10.0;
                    this.mWhirleyFrame += 0.7;
                    if (this.mWhirleyFrame >= 6.0) {
                        this.mWhirleyFrame -= 6.0;
                    }
                    if (this.mWhirleyY < -40.0) {
                        this.mWhirley = false;
                        ++this.mWhirleyLevel;
                        this.mRack.SpeedUp();
                        break;
                    }
                    break;
                }
            }
        }
        if (this.mBlastLift != 0.0 && this.mBlastLift != 0.0) {
            int n3 = 0;
            do {
                final EggRack mRack3 = this.mRack;
                mRack3.mCeiling -= this.mBlastLift;
                this.mBlastLift *= 0.949999988079071;
                if (this.mBlastLift < 1.0) {
                    this.mBlastLift = 0.0;
                }
            } while (++n3 < 3);
        }
        if (--this.mBonusFlickerCount < 0) {
            this.mBonusFlickerCount = 5;
            if (!this.mBonusFlicker) {
                this.mBonusFlicker = true;
            }
            else {
                this.mBonusFlicker = false;
            }
        }
        int n4 = 0;
        do {
            if (--this.mBonusCountdown[n4] < 0) {
                this.mBonus[n4] = false;
            }
        } while (++n4 < 8);
        if (this.IsBonus(5) && this.mRack.mLowestPixel > -37) {
            if (this.mMode == 0) {
                final EggRack mRack4 = this.mRack;
                mRack4.mCeiling -= this.mRack.mFallSpeed;
                final EggRack mRack5 = this.mRack;
                mRack5.mCeiling -= 2.76;
                this.mRack.SpeedReset();
            }
            else {
                final EggRack mRack6 = this.mRack;
                mRack6.mCeiling -= 0.552;
            }
        }
        this.MarkDirty();
    }
    
    public Point GetXPoint() {
        this.mAimAngle = this.GetPointAngle(this.mCenter, 298, this.mAimX, this.mAimY);
        final double n = Math.sin(this.mAimAngle) * 10.0;
        final double n2 = Math.cos(this.mAimAngle) * 10.0;
        double n3 = this.mCenter + (int)n;
        double n4 = 298 + (int)n2;
        double n5 = -Math.sin(this.mAimAngle) * 2.0;
        final double n6 = -Math.cos(this.mAimAngle) * 2.0;
        int n7;
        do {
            n3 += n5;
            n4 += n6;
            n7 = (0x0 | this.mRack.Collide((int)n3, (int)n4 - 8) | this.mRack.Collide((int)n3 - 4, (int)n4 - 6) | this.mRack.Collide((int)n3 + 4, (int)n4 - 6) | this.mRack.Collide((int)n3 - 6, (int)n4) | this.mRack.Collide((int)n3 + 6, (int)n4) | this.mRack.Collide((int)n3, (int)n4 + 5));
            if ((n7 & 0x1) != 0x0) {
                n5 = Math.abs(n5);
            }
            if ((n7 & 0x2) != 0x0) {
                n5 = -Math.abs(n5);
            }
            if ((n7 & 0x8) != 0x0) {
                return new Point((int)n3, (int)n4);
            }
        } while ((n7 & 0x4) == 0x0 && (n7 & 0x10) == 0x0);
        int n8 = 0;
        int n9;
        do {
            n3 -= n5;
            n4 -= n6;
            n9 = (0x0 | this.mRack.Collide((int)n3, (int)n4 - 8) | this.mRack.Collide((int)n3 - 4, (int)n4 - 6) | this.mRack.Collide((int)n3 + 4, (int)n4 - 6) | this.mRack.Collide((int)n3 - 6, (int)n4) | this.mRack.Collide((int)n3 + 6, (int)n4) | this.mRack.Collide((int)n3, (int)n4 + 5));
            final Point screenToTiles = this.mRack.ScreenToTiles((int)n3, (int)n4);
            if (this.mRack.mEggs[screenToTiles.x][screenToTiles.y] != 0) {
                n9 = 16;
            }
            if ((n9 & 0x1) != 0x0) {
                n5 = -Math.abs(n5);
            }
            if ((n9 & 0x2) != 0x0) {
                n5 = Math.abs(n5);
            }
        } while (n9 != 0 && ++n8 < 100);
        final Point screenToTiles2 = this.mRack.ScreenToTiles((int)n3, (int)n4);
        return this.mRack.TilesToScreen(screenToTiles2.x, screenToTiles2.y);
    }
    
    public void NextRound() {
        ++this.mRound;
        ++this.mRealRound;
        if (this.mRealRound > 19) {
            this.mRealRound = 1;
        }
        this.mRack.LoadRound(StompedRounds.Rounds[this.mRealRound - 1]);
        this.mRoundNumber = "Round #" + this.mRound;
        this.mRack.Lift();
        this.mRack.mLowerToReached = false;
        this.mCleared = false;
        this.mFirstFired = false;
        this.mRoundViewCountdown = 250;
        ++this.mMamaMeter;
        this.EggLandResponse();
        final Point getQueueEntry = this.mRack.GetQueueEntry();
        this.mEggSlingshotColor = getQueueEntry.x;
        this.mEggSlingshotBonus = getQueueEntry.y;
        final Point getQueueEntry2 = this.mRack.GetQueueEntry();
        this.mRack.mNextEgg = getQueueEntry2.x;
        this.mRack.mNextBonus = getQueueEntry2.y;
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
        final Point point = new Point(0, 0);
        if (this.mMode == 1 && this.mMamaMeter == 0 && !this.mPause && !this.mGameOver) {
            point.x = this.mRack.mRand.Next() % 2 - 1;
            point.y = this.mRack.mRand.Next() % 2 - 1;
        }
        sexyGraphics.Translate(point.x, point.y);
        if (!this.mDynoComboFlash) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[28], 0, 0);
        }
        else {
            sexyGraphics.SetColor(new Color(255, 255, 255));
            sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
        }
        this.mRack.Draw(sexyGraphics);
        if (this.mMode == 0) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[8], this.mCenter - 100, (int)this.mRack.mCeiling - 84);
            if (this.mApplet.mRes.mImageLeg.IsReady()) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImageLeg, this.mCenter - 100, (int)this.mRack.mCeiling - 330);
            }
        }
        else {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[8], this.mCenter - 100, (int)this.mRack.mCeiling - 84 - 12);
            if (this.mApplet.mRes.mImageLeg.IsReady()) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImageLeg, this.mCenter - 100, (int)this.mRack.mCeiling - 330);
            }
            for (int i = this.mCenter - this.mRack.mWidth * 23 / 2; i < this.mCenter + this.mRack.mWidth * 23 / 2 - 5 + 18; i += 34) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[46], i, (int)this.mRack.mCeiling - 12);
            }
        }
        for (int j = this.mCenter - this.mRack.mWidth * 23 / 2 - 37; j < this.mCenter + this.mRack.mWidth * 23 / 2 - 5 + 18; j += 18) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[19], j, 264);
        }
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[13], this.mCenter - this.mRack.mWidth * 23 / 2 - 37, 0);
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[14], this.mCenter + this.mRack.mWidth * 23 / 2 - 5 - 10, 0);
        if (this.mMode == 1) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[47], this.mCenter + this.mRack.mWidth * 23 / 2 - 10, 100);
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[48], this.mCenter + this.mRack.mWidth * 23 / 2 + 11 - 10, 181, new Rectangle(0, 0, 27, 128));
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[48], this.mCenter + this.mRack.mWidth * 23 / 2 + 11 - 10, 181 + this.mMamaMeterLevel, new Rectangle(27, this.mMamaMeterLevel, 27, 128 - this.mMamaMeterLevel));
            if (this.mMamaMeter < 3) {
                sexyGraphics.SetColorizeImages(true);
                sexyGraphics.SetColor(new SexyColor(255, 255, 255, (int)(255.0 * (this.mWarningSoundCountdown / 75.0))));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[49], this.mCenter + this.mRack.mWidth * 23 / 2 - 1 - 10, 262);
                sexyGraphics.SetColorizeImages(false);
            }
        }
        if (this.mMode == 0) {
            if (!this.mKroneyFlip || !this.mApplet.mRes.mImageKroneyflip.IsReady()) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[30], this.mCenter + this.mRack.mWidth * 23 / 2 + 5, this.mKroneyYpos - (int)(this.mSteggyBounce / 2.0));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[31], this.mCenter + this.mRack.mWidth * 23 / 2 + 21, this.mKroneyYpos + 33 - (int)(this.mSteggyBounce / 2.0), new Rectangle(this.mKroneySandWobble / 20 * 29, 0, 29, 46));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[32], this.mCenter + this.mRack.mWidth * 23 / 2 + 21, this.mKroneyYpos + 34 - (int)(this.mSteggyBounce / 2.0) + (int)(20.0 - this.mKroneySand / this.mKroneySandMax * 20.0), new Rectangle(0, (int)(20.0 - this.mKroneySand / this.mKroneySandMax * 20.0), 29, (int)(this.mKroneySand / this.mKroneySandMax * 20.0)));
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[32], this.mCenter + this.mRack.mWidth * 23 / 2 + 21, this.mKroneyYpos + 33 - (int)(this.mSteggyBounce / 2.0) + 20 + (int)(this.mKroneySand / this.mKroneySandMax * 25.0), new Rectangle(0, 20 + (int)(this.mKroneySand / this.mKroneySandMax * 25.0), 29, 1 + (int)(25.0 - this.mKroneySand / this.mKroneySandMax * 25.0)));
                if (this.mKroneySand <= 150.0 && this.mKroneySand >= 100.0) {
                    sexyGraphics.DrawImage(this.mApplet.mRes.mImages[33], this.mCenter + this.mRack.mWidth * 23 / 2 - 45, this.mKroneyYpos - 5);
                }
                if (this.mKroneySand <= 100.0) {
                    sexyGraphics.DrawImage(this.mApplet.mRes.mImages[34], this.mCenter + this.mRack.mWidth * 23 / 2 - 55, this.mKroneyYpos - 15);
                }
            }
            else {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImageKroneyflip, this.mCenter + this.mRack.mWidth * 23 / 2 + 5 + 30 - 53, this.mKroneyYpos + 53 - 67, new Rectangle((int)this.mKroneyFlipFrame * 106, 0, 106, 135));
            }
        }
        final Point getBonusPoint = this.GetBonusPoint();
        double mBonusAngle = this.mBonusAngle;
        int n = 0;
        do {
            if (this.mBonus[n]) {
                final double n2 = getBonusPoint.x + Math.sin(mBonusAngle * 3.141592653589793 / 180.0) * 40.0;
                final double n3 = getBonusPoint.y + Math.cos(mBonusAngle * 3.141592653589793 / 180.0) * 40.0;
                if (this.mBonusCountdown[n] > 150) {
                    sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], (int)n2 - 12, (int)n3 - 12, new Rectangle((this.mBonusType[n] - 1) * 23, 0, 23, 24));
                }
                else if (this.mBonusFlicker) {
                    sexyGraphics.DrawImage(this.mApplet.mRes.mImages[39], (int)n2 - 12, (int)n3 - 12, new Rectangle((this.mBonusType[n] - 1) * 23, 0, 23, 24));
                }
            }
            mBonusAngle += 25.0;
            if (mBonusAngle >= 360.0) {
                mBonusAngle -= 360.0;
            }
        } while (++n < 8);
        if (this.mSteggyYpos < this.mRack.mCeiling && this.mRack.mCrush) {
            this.mSteggyYpos = (int)this.mRack.mCeiling + 3;
        }
        if (!this.mSteggyPopdown) {
            this.mRack.DrawEgg(sexyGraphics, this.mCenter - 77, (int)(this.mSteggyYpos + 23 + this.mSteggyBounce), this.mRack.mNextEgg, this.mRack.mNextBonus);
        }
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[25], this.mCenter - 120, (int)(this.mSteggyYpos + this.mSteggyBounce));
        int n4 = 247;
        if (n4 < this.mRack.mCeiling && this.mRack.mCrush) {
            n4 = (int)this.mRack.mCeiling;
        }
        if (this.mTankPose == 0) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[26], 218, n4);
        }
        if (this.mTankPose == 1) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[27], 214, n4 - 7);
        }
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[11], super.mWidth - 124, super.mHeight - 40);
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[12], 0, super.mHeight - 50);
        this.DrawSlingshot(sexyGraphics);
        if (this.mHopEgg) {
            this.mRack.DrawEgg(sexyGraphics, (int)this.mHopEggX - 12, (int)this.mHopEggY - 14, this.mHopEggColor, this.mHopEggBonus);
        }
        if (this.mEggInAir) {
            this.mRack.DrawEgg(sexyGraphics, (int)this.mEggInAirX - 12, (int)this.mEggInAirY - 14, this.mEggInAirColor, this.mEggInAirBonus);
        }
        final Enumeration<Animator> elements = (Enumeration<Animator>)this.mAnimatorVector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().Draw(sexyGraphics);
        }
        if (this.mWhirley) {
            final Rectangle rectangle = new Rectangle(0, 0, 41, 42);
            if (this.mWhirleyXDir > 0.0) {
                final Rectangle rectangle2 = rectangle;
                rectangle2.y += 42;
            }
            rectangle.x = (int)this.mWhirleyFrame * 41;
            if (this.mWhirleyState != 2) {
                if (this.mWhirleyXDir > 0.0) {
                    this.mRack.DrawEgg(sexyGraphics, (int)(this.mWhirleyX - 12.0 - 5.0 - Board.mWhirleyAdjustX[(int)this.mWhirleyFrame]), (int)(this.mWhirleyY + 12.0 + Board.mWhirleyAdjustY[(int)this.mWhirleyFrame]), this.mRack.mColors, 0);
                }
                else {
                    this.mRack.DrawEgg(sexyGraphics, (int)(this.mWhirleyX - 12.0 + 5.0 + Board.mWhirleyAdjustX[(int)this.mWhirleyFrame]), (int)(this.mWhirleyY + 12.0 + Board.mWhirleyAdjustY[(int)this.mWhirleyFrame]), this.mRack.mColors, 0);
                }
            }
            sexyGraphics.DrawImage(this.mApplet.mRes.mImageWhirley, (int)this.mWhirleyX - 20, (int)this.mWhirleyY - 22, rectangle);
        }
        if (!this.mDynoComboFlash && this.mDynoComboDuration > 0 && this.mDynoComboDuration < 175) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[35], this.mCenter - 21 + 4, 180);
        }
        if (this.mGameOver) {
            if (this.mApplet.mRes.mImageGameOver.IsReady()) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImageGameOver, 90, 121 - this.mGameOverRise);
            }
            if (this.mGameOverRise >= 100) {
                final Rectangle rectangle3 = new Rectangle(57, 120, 238, 185);
                sexyGraphics.SetColor(new SexyColor(25, 25, 0, 220));
                sexyGraphics.FillRect(rectangle3.x, rectangle3.y, rectangle3.width + 1, rectangle3.height + 1);
                sexyGraphics.SetColor(new SexyColor(255, 255, 150, 255));
                sexyGraphics.SetFont(this.mApplet.mTinyFont);
                sexyGraphics.DrawString("Game Time:", rectangle3.x + 5, rectangle3.y + 15);
                sexyGraphics.DrawString("Best Combo:", rectangle3.x + 5, rectangle3.y + 15 + 15);
                sexyGraphics.DrawString("Babies Hatched:", rectangle3.x + 5, rectangle3.y + 15 + 30);
                if (this.mMode == 0) {
                    sexyGraphics.DrawString("Whirleys Whacked:", rectangle3.x + 5, rectangle3.y + 15 + 45);
                }
                else {
                    sexyGraphics.DrawString("Rounds Cleared:", rectangle3.x + 5, rectangle3.y + 15 + 45);
                }
                sexyGraphics.DrawString("Final Score:", rectangle3.x + 5, rectangle3.y + 15 + 60);
                String s = "" + this.mGameTime / 50 / 60 + ":";
                if (this.mGameTime % 50 % 60 < 10) {
                    s += "0";
                }
                final String string = s + this.mGameTime % 50 % 60;
                sexyGraphics.DrawString(string, rectangle3.x + rectangle3.width - 5 - this.mApplet.mTinyFont.StringWidth(string), rectangle3.y + 15);
                sexyGraphics.DrawString(Board.ComboName[this.mBestCombo], rectangle3.x + rectangle3.width - 5 - this.mApplet.mTinyFont.StringWidth(Board.ComboName[this.mBestCombo]), rectangle3.y + 15 + 15);
                final String string2 = "" + this.mBabiesHatched;
                sexyGraphics.DrawString(string2, rectangle3.x + rectangle3.width - 5 - this.mApplet.mTinyFont.StringWidth(string2), rectangle3.y + 15 + 30);
                String s2;
                if (this.mMode == 0) {
                    s2 = "" + this.mWhirleysWhacked;
                }
                else {
                    s2 = "" + (this.mRound - 1);
                }
                sexyGraphics.DrawString(s2, rectangle3.x + rectangle3.width - 5 - this.mApplet.mTinyFont.StringWidth(s2), rectangle3.y + 15 + 45);
                final String string3 = "" + this.mApplet.mSidebar.mScore;
                sexyGraphics.DrawString(string3, rectangle3.x + rectangle3.width - 5 - this.mApplet.mTinyFont.StringWidth(string3), rectangle3.y + 15 + 60);
                sexyGraphics.SetFont(this.mApplet.mSmallFont);
                sexyGraphics.SetColor(new SexyColor(150, 150, 255, 255));
                if (this.mUploadScoreButton == null) {
                    final String s3 = "Ranking";
                    sexyGraphics.DrawString(s3, rectangle3.x + rectangle3.width / 2 - this.mApplet.mSmallFont.StringWidth(s3) / 2, rectangle3.y + 15 + 100);
                    final String s4 = Board.ScoreNames[this.mScoreTitle];
                    sexyGraphics.SetColor(new SexyColor(200, 255, 150, 255));
                    sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
                    sexyGraphics.DrawString(s4, rectangle3.x + rectangle3.width / 2 - this.mApplet.mAnnounceFont.StringWidth(s4) / 2, rectangle3.y + 15 + 100 + 22);
                }
                else {
                    final String s5 = "Ranking";
                    sexyGraphics.DrawString(s5, rectangle3.x + rectangle3.width / 2 - this.mApplet.mSmallFont.StringWidth(s5) / 2, rectangle3.y + 15 + 100 - 20);
                    final String s6 = Board.ScoreNames[this.mScoreTitle];
                    sexyGraphics.SetColor(new SexyColor(200, 255, 150, 255));
                    sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
                    sexyGraphics.DrawString(s6, rectangle3.x + rectangle3.width / 2 - this.mApplet.mAnnounceFont.StringWidth(s6) / 2, rectangle3.y + 15 + 100 + 22 - 20);
                }
                sexyGraphics.SetColor(new SexyColor(255, 200, 0, 255));
                final Rectangle rectangle4 = rectangle3;
                --rectangle4.x;
                final Rectangle rectangle5 = rectangle3;
                --rectangle5.y;
                final Rectangle rectangle6 = rectangle3;
                rectangle6.width += 2;
                final Rectangle rectangle7 = rectangle3;
                rectangle7.height += 2;
                sexyGraphics.DrawRect(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                final Rectangle rectangle8 = rectangle3;
                --rectangle8.x;
                final Rectangle rectangle9 = rectangle3;
                --rectangle9.y;
                final Rectangle rectangle10 = rectangle3;
                rectangle10.width += 2;
                final Rectangle rectangle11 = rectangle3;
                rectangle11.height += 2;
                sexyGraphics.DrawRect(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                sexyGraphics.SetColor(new SexyColor(200, 180, 0, 255));
                final Rectangle rectangle12 = rectangle3;
                --rectangle12.x;
                final Rectangle rectangle13 = rectangle3;
                --rectangle13.y;
                final Rectangle rectangle14 = rectangle3;
                rectangle14.width += 2;
                final Rectangle rectangle15 = rectangle3;
                rectangle15.height += 2;
                sexyGraphics.DrawRect(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                sexyGraphics.SetColor(new SexyColor(150, 100, 0, 255));
                final Rectangle rectangle16 = rectangle3;
                --rectangle16.x;
                final Rectangle rectangle17 = rectangle3;
                --rectangle17.y;
                final Rectangle rectangle18 = rectangle3;
                rectangle18.width += 2;
                final Rectangle rectangle19 = rectangle3;
                rectangle19.height += 2;
                sexyGraphics.DrawRect(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
            }
        }
        else if (this.mPause) {
            final int[] getBits = sexyGraphics.mImage.GetBits();
            for (int k = 0; k < getBits.length; ++k) {
                final int n5 = (getBits[k] & 0xFF00) >> 10;
                getBits[k] = (n5 | n5 << 8 | n5 << 16);
            }
            sexyGraphics.mImage.BitsChanged();
            sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
            final String s7 = "Paused";
            sexyGraphics.SetColor(new Color(0, 0, 0));
            sexyGraphics.DrawString(s7, this.mCenter - sexyGraphics.GetFont().StringWidth(s7) / 2 + 2, 287);
            sexyGraphics.SetColor(new Color(255, 0, 0));
            sexyGraphics.DrawString(s7, this.mCenter - sexyGraphics.GetFont().StringWidth(s7) / 2, 285);
        }
        sexyGraphics.Translate(-point.x, -point.y);
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[15], 0, 0);
    }
    
    public Board(final DynomiteApplet dynomiteApplet) {
        super(dynomiteApplet.mWidgetManager);
        this.mAnimatorVector = new Vector();
        this.mBonus = new boolean[8];
        this.mBonusType = new int[8];
        this.mBonusCountdown = new int[8];
        this.mScoreProgression = new int[20];
        new PathFinder(new EggRack(dynomiteApplet, this));
        new Animator(this, 0);
        new Advertisement(dynomiteApplet);
        new Button(this, dynomiteApplet.mWidgetManager, 0, this);
    }
    
    public Board(final DynomiteApplet mApplet, final int mMode, final int mSkill) {
        super(mApplet.mWidgetManager);
        this.mAnimatorVector = new Vector();
        this.mBonus = new boolean[8];
        this.mBonusType = new int[8];
        this.mBonusCountdown = new int[8];
        this.mScoreProgression = new int[20];
        this.mApplet = mApplet;
        this.mMode = mMode;
        this.mSkill = mSkill;
        this.mApplet.mNeedSendGameStart = true;
        this.mRack = new EggRack(this.mApplet, this);
        this.mEggSlingshotColor = this.mRack.GetBestNextEgg();
        this.mEggSlingshotBonus = 0;
        this.MouseMove(this.mCenter = 168, 165);
        this.mFirstFired = false;
        this.mTextFade = 0.0;
        this.mTextFadeDir = 0.02;
        this.mEggInAir = false;
        this.mEcho = "No Error";
        this.mGameOver = false;
        this.mHopEgg = false;
        this.mTankPose = 0;
        this.mTankCountdown = 0;
        this.mSteggyPopdown = false;
        this.mSteggyYpos = 250;
        this.mSteggyBounce = 0.0;
        this.mSteggyBounceDir = 1.0;
        this.mKroneyYpos = -100;
        this.mKroneySandMax = 3000.0;
        this.mKroneySand = this.mKroneySandMax;
        this.mApplet.mSidebar.ResetScore();
        this.mKroneyFlip = false;
        this.mWhirley = false;
        this.mWhirleyLevel = 1;
        this.mDynoComboDuration = 0;
        this.mBlastLift = 0.0;
        this.mNextBonus = 1;
        this.mBonusAngle = 0.0;
        this.mBonusFlickerCount = 0;
        this.mPause = false;
        this.mGameOverRise = 0;
        this.mGameOverPause = 75;
        this.mScoreProgression[0] = 0;
        this.mScoreProgression[1] = 1500;
        this.mScoreProgression[2] = 2500;
        this.mScoreProgression[3] = 5000;
        this.mScoreProgression[4] = 8000;
        this.mScoreProgression[5] = 12000;
        this.mScoreProgression[6] = 16000;
        this.mScoreProgression[7] = 20000;
        this.mScoreProgression[8] = 25000;
        this.mScoreProgression[9] = 30000;
        this.mScoreProgression[10] = 37000;
        this.mScoreProgression[11] = 50000;
        if (this.mMode == 0) {
            this.mScoreProgression[12] = 65000;
            this.mScoreProgression[13] = 70000;
            this.mScoreProgression[14] = 85000;
            this.mScoreProgression[15] = 100000;
            this.mScoreProgression[16] = 120000;
            this.mScoreProgression[17] = 145000;
            this.mScoreProgression[18] = 170000;
            this.mScoreProgression[19] = 200000;
        }
        else {
            this.mScoreProgression[12] = 60000;
            this.mScoreProgression[13] = 70000;
            this.mScoreProgression[14] = 80000;
            this.mScoreProgression[15] = 90000;
            this.mScoreProgression[16] = 100000;
            this.mScoreProgression[17] = 115000;
            this.mScoreProgression[18] = 130000;
            this.mScoreProgression[19] = 150000;
        }
        this.mMamaMeterTop = 10;
        this.mMamaMeter = this.mMamaMeterTop;
        this.mDesiredMamaMeterLevel = 0;
        this.EggLandResponse();
        this.mMamaMeterLevel = this.mDesiredMamaMeterLevel;
        switch (this.mSkill) {
            case 0: {
                this.mRealRound = 0;
                break;
            }
            case 1: {
                this.mRealRound = 6;
                break;
            }
            case 2: {
                this.mRealRound = 12;
                break;
            }
        }
        this.mRound = 0;
        this.mLastFiredEgg = -1;
        this.mLastRealFiredEgg = -1;
        if (this.mMode == 1) {
            this.NextRound();
        }
    }
    
    public void ButtonPress(final int n) {
        this.mApplet.PlaySound(5);
    }
    
    public void Glitter(final Point point) {
        int n = 0;
        do {
            final double n2 = this.mRack.mRand.Next() % 360.0;
            final Animator animator = new Animator(this, 8);
            animator.mXDir = Math.sin(n2 * 3.141592653589793 / 180.0) * this.mRack.mRand.Next() % 3.0;
            animator.mYDir = Math.cos(n2 * 3.141592653589793 / 180.0) * this.mRack.mRand.Next() % 3.0;
            animator.mPosX = point.x + animator.mXDir * this.mRack.mRand.Next() % 5.0;
            animator.mPosY = point.y + animator.mYDir * this.mRack.mRand.Next() % 5.0;
            animator.mCountdown = 10 + this.mRack.mRand.Next() % 10;
            animator.mValue = this.mRack.mRand.Next() % 5;
            this.mAnimatorVector.addElement(animator);
        } while (++n < 50);
    }
    
    public boolean IsBonus(final int n) {
        int n2 = 0;
        while (!this.mBonus[n2] || this.mBonusType[n2] != n) {
            if (++n2 >= 8) {
                return false;
            }
        }
        return true;
    }
    
    public void SwitchPause() {
        if (this.mGameOver) {
            this.mPause = false;
        }
        else {
            this.mPause = !this.mPause;
        }
        this.MarkDirty();
        this.mApplet.mSidebar.MarkDirty();
        this.SoundFix();
    }
    
    public void DrawSlingshot(final SexyGraphics sexyGraphics) {
        if (this.mAimY < 298 && super.mIsOver && !this.mRack.mCrush && !this.mGameOver) {
            if (this.IsBonus(1) && this.mAimY <= 285) {
                final Point getXPoint = this.GetXPoint();
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[41], getXPoint.x - 18, getXPoint.y - 18);
                sexyGraphics.SetColor(new SexyColor(0, 255, 255, 128));
            }
            else {
                sexyGraphics.SetColor(new SexyColor(255, 0, 0, 128));
            }
            this.DrawArrow(sexyGraphics, this.mCenter, 298, this.mAimX, this.mAimY);
        }
        int n = 253;
        if (n < this.mRack.mCeiling) {
            n = (int)this.mRack.mCeiling;
        }
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[9], this.mCenter - 41, n);
        final double n2 = Math.sin(this.mAimAngle) * 10.0;
        final double n3 = Math.cos(this.mAimAngle) * 10.0;
        n += 45;
        if (!this.mHopEgg) {
            this.mRack.DrawEgg(sexyGraphics, this.mCenter - 12 + (int)n2, n - 14 + (int)n3, this.mEggSlingshotColor, this.mEggSlingshotBonus);
        }
        final int[] array = { this.mCenter - 38, this.mCenter + (int)n2 - 12, this.mCenter + (int)n2, this.mCenter + (int)n2 + 12, this.mCenter + 38, this.mCenter + 38, this.mCenter + (int)n2 + 12, this.mCenter + (int)n2, this.mCenter + (int)n2 - 12, this.mCenter - 38 };
        final int[] array2 = { n - 32, n + (int)n3 - 3, n + (int)n3, n + (int)n3 - 3, n - 33, n - 33 + 8, n + (int)n3 + 7, n + (int)n3 + 10, n + (int)n3 + 7, n - 32 + 10 };
        sexyGraphics.SetColor(new Color(128, 0, 0));
        if (this.mHopEgg) {
            int n4 = (int)this.mEggInAirY;
            if (n4 < 253) {
                n4 = 268;
            }
            if (this.mHopEggX > this.mCenter - 25 && n4 < this.mHopEggY) {
                n4 = (int)this.mHopEggY;
            }
            array2[2] = (array2[1] = n4);
            array2[6] = (array2[3] = n4) + 7;
            array2[8] = (array2[7] = n4 + 7);
        }
        sexyGraphics.PolyFill(new Point[] { new Point(array[0], array2[0]), new Point(array[1], array2[1]), new Point(array[2], array2[2]), new Point(array[3], array2[3]), new Point(array[4], array2[4]), new Point(array[5], array2[5]), new Point(array[6], array2[6]), new Point(array[7], array2[7]), new Point(array[8], array2[8]), new Point(array[9], array2[9]) });
        if (!this.mHopEgg) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[16], this.mCenter + (int)n2 - 12, n + (int)n3 - 5);
        }
        else {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[16], this.mCenter + (int)n2 - 12, 311);
        }
        if (!this.mFirstFired) {
            if (this.mMode == 1 && this.mRoundViewCountdown > 0) {
                sexyGraphics.SetFont(this.mApplet.mRoundFont);
                sexyGraphics.SetColor(new Color(0, 0, 0));
                sexyGraphics.DrawString(this.mRoundNumber, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundNumber) / 2 + 2, 77);
                sexyGraphics.DrawString(this.mRoundNumber, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundNumber) / 2 + 3, 78);
                sexyGraphics.DrawString(this.mRoundNumber, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundNumber) / 2 - 1, 74);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawString(this.mRoundNumber, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundNumber) / 2, 75);
                sexyGraphics.SetColor(new Color(0, 0, 0));
                sexyGraphics.DrawString(this.mRoundName, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundName) / 2 + 2, 97);
                sexyGraphics.DrawString(this.mRoundName, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundName) / 2 + 3, 98);
                sexyGraphics.DrawString(this.mRoundName, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundName) / 2 - 1, 94);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.DrawString(this.mRoundName, this.mCenter - sexyGraphics.GetFont().StringWidth(this.mRoundName) / 2, 95);
            }
            sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
            sexyGraphics.SetColor(new Color((int)(192.0 * this.mTextFade), (int)(192.0 * this.mTextFade), (int)(255.0 * this.mTextFade)));
            final String s = "Fire your first";
            sexyGraphics.DrawString(s, this.mCenter - sexyGraphics.GetFont().StringWidth(s) / 2, 225);
            final String s2 = "egg to begin!";
            sexyGraphics.DrawString(s2, this.mCenter - sexyGraphics.GetFont().StringWidth(s2) / 2, 242);
        }
        if (this.mCleared) {
            sexyGraphics.SetFont(this.mApplet.mAnnounceFont);
            sexyGraphics.SetColor(new Color(0, 0, 0));
            final String s3 = "Round Cleared!";
            sexyGraphics.DrawString(s3, this.mCenter - sexyGraphics.GetFont().StringWidth(s3) / 2 + 2, 127);
            sexyGraphics.SetColor(new Color(255, 255, 100));
            sexyGraphics.DrawString(s3, this.mCenter - sexyGraphics.GetFont().StringWidth(s3) / 2, 125);
            if (this.mClearedCountdown < 250) {
                sexyGraphics.SetColor(new Color(0, 0, 0));
                final String s4 = "Bonus";
                sexyGraphics.DrawString(s4, this.mCenter - sexyGraphics.GetFont().StringWidth(s4) / 2 + 2, 152);
                sexyGraphics.SetColor(new Color(255, 255, 100));
                final String s5 = "Bonus";
                sexyGraphics.DrawString(s5, this.mCenter - sexyGraphics.GetFont().StringWidth(s5) / 2, 150);
            }
        }
    }
    
    public void DrawArrow(final SexyGraphics sexyGraphics, final int n, final int n2, final int n3, final int n4) {
        final double n6;
        final double n5 = n6 = n3;
        final double n8;
        final double n7 = n8 = n4;
        final double n9 = n;
        final double n10 = n2;
        final double mAimAngle = this.mAimAngle;
        final double n11 = Math.sin(mAimAngle) * 14.0;
        final double n12 = Math.cos(mAimAngle) * 14.0;
        final double n13 = n6 - n11 / 2.0;
        final double n14 = n8 - n12 / 2.0;
        final double n15 = n9 + n11;
        final double n16 = n10 + n12;
        final double n17 = n9 + n11;
        final double n18 = n10 + n12;
        final double addAngle = this.AddAngle(mAimAngle, 90.0);
        final double n19 = Math.sin(addAngle) * 8.0;
        final double n20 = Math.cos(addAngle) * 8.0;
        final double n21 = n5 + n19;
        final double n22 = n7 + n20;
        final double n23 = n21 + n11;
        final double n24 = n22 + n12;
        final double n25 = n15 + n19;
        final double n26 = n16 + n20;
        final double n27 = n23 + n19 / 2.0;
        final double n28 = n24 + n20 / 2.0;
        final double addAngle2 = this.AddAngle(mAimAngle, -90.0);
        final double n29 = Math.sin(addAngle2) * 8.0;
        final double n30 = Math.cos(addAngle2) * 8.0;
        final double n31 = n5 + n29;
        final double n32 = n7 + n30;
        final double n33 = n31 + n11;
        final double n34 = n32 + n12;
        sexyGraphics.PolyFill(new Point[] { new Point((int)n25, (int)n26), new Point((int)n23, (int)n24), new Point((int)n27, (int)n28), new Point((int)n13, (int)n14), new Point((int)(n33 + n29 / 2.0), (int)(n34 + n30 / 2.0)), new Point((int)n33, (int)n34), new Point((int)(n17 + n29), (int)(n18 + n30)) });
    }
    
    public void EggLandResponse() {
        if (this.mMode == 1) {
            --this.mMamaMeter;
            if (this.mMamaMeter == 3) {
                this.mWarningSoundCountdown = 0;
            }
            if (this.mMamaMeter == 0) {
                this.SoundFix();
            }
            if (this.mMamaMeter < 0) {
                this.SoundFix();
                this.mMamaMeter = this.mMamaMeterTop;
                final EggRack mRack = this.mRack;
                mRack.mDropCeiling += 12;
                this.mApplet.PlaySound(20);
            }
            if (this.mRack.CountEggs() == 0 && !this.mCleared) {
                this.mApplet.PlaySound(28);
                this.mCleared = true;
                this.mMamaMeter = this.mMamaMeterTop;
                this.mRack.mCombo = -2;
                this.mBonusCountdown = new int[8];
                this.mClearedCountdown = 300;
                this.SoundFix();
            }
            this.mDesiredMamaMeterLevel = (int)(97.0 * (this.mMamaMeter / (this.mMamaMeterTop - 1)));
        }
    }
    
    public Point GetBonusPoint() {
        if (this.mMode == 0) {
            return new Point(this.mCenter + this.mRack.mWidth * 23 / 2 + 5 + 30, this.mKroneyYpos + 53);
        }
        return new Point(this.mCenter + this.mRack.mWidth * 23 / 2 + 29 - 10, 139);
    }
    
    public void SendGameBreak(final boolean b) {
        final String string = "" + (this.mSkill + 1);
        int mRound = this.mRound;
        String s;
        if (this.mMode == 0) {
            s = string + ",endless";
            mRound = 1;
        }
        else {
            s = string + ",stomped";
        }
        this.mApplet.SendGameBreakNotification(mRound, b, s);
    }
    
    static {
        ScoreNames = new String[] { "Petrified", "Fossil", "Hatchling", "Scavenger", "Eggsucker", "Herbivore", "Omnivore", "Carnivore", "Raptor", "Horned Lizard", "Thunder Lizard", "Tyrant Lizard", "Terrible Claw", "Supersaurus", "Megasaurus", "Gigasaurus", "Ultrasaurus", "Hypersaurus", "Tyrannosaurus Rex", "Lord of the Lizards" };
        ComboName = new String[] { "None", "None", "None", "Combo", "Super", "Cambrian", "Triassic", "Jurassic", "Cretaceous", "Dynomite" };
        mWhirleyAdjustX = new double[] { 0.0, -1.38, -2.76, -2.07, -2.07, -0.69 };
        mWhirleyAdjustY = new double[] { 0.0, 1.38, 1.38, 0.69, 0.0, 0.0 };
    }
    
    public void SoundFix() {
        this.mApplet.StopSound(26);
        if (this.mPause) {
            return;
        }
        if (this.mCleared) {
            return;
        }
        if (this.mGameOver) {
            return;
        }
        if (!this.mApplet.mSoundOn) {
            return;
        }
        if (this.mMode == 1 && this.mMamaMeter == 0) {
            this.mApplet.LoopSound(26);
        }
    }
    
    public void ButtonDepress(final int n) {
        switch (n) {
            case 0: {
                this.SendGameBreak(true);
                this.mApplet.NewGame();
                this.mApplet.ShowAd();
            }
            case 1: {
                if (this.mUploadScoreButton != null) {
                    super.mWidgetManager.RemoveWidget(this.mUploadScoreButton);
                }
                this.mUploadScoreButton = null;
                try {
                    Class.forName("dynomite.ScoreThread").getConstructor(Class.forName("dynomite.DynomiteApplet"), Integer.TYPE).newInstance(this.mApplet, new Integer(this.mApplet.mSidebar.mScore));
                    return;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                break;
            }
        }
    }
    
    public double GetPointAngle(final int n, final int n2, final int n3, final int n4) {
        final double n5 = n3 - n;
        final double n6 = n4 - n2;
        if (n6 != 0.0) {
            return Math.atan(n5 / n6);
        }
        if (n5 <= 0.0) {
            return 270.0;
        }
        return 90.0;
    }
    
    public void AddBonus(final int n) {
        int n2 = 0;
        while (this.mBonus[n2] && ++n2 < 8) {}
        if (n2 == 8) {
            n2 = 0;
        }
        this.mBonus[n2] = true;
        switch (this.mBonusType[n2] = n) {
            case 1: {
                this.mBonusCountdown[n2] = 3000;
            }
            case 2:
            case 3:
            case 4: {
                this.mBonusCountdown[n2] = 1500;
            }
            case 8: {
                this.Glitter(this.GetBonusPoint());
                this.Score(5, this.GetBonusPoint());
                this.mApplet.PlaySound(12);
            }
            case 9: {
                this.Glitter(this.GetBonusPoint());
                this.Score(10, this.GetBonusPoint());
                this.mApplet.PlaySound(12);
            }
            case 10: {
                this.Glitter(this.GetBonusPoint());
                this.Score(15, this.GetBonusPoint());
                this.mApplet.PlaySound(12);
            }
            case 5: {
                this.mApplet.PlaySound(19);
                this.mBonusCountdown[n2] = 50;
            }
            case 6: {
                this.mRack.WidthCleanup(false);
                this.mApplet.PlaySound(20);
                this.mBonusCountdown[n2] = 50;
                if (this.mRack.mWidth < 13) {
                    final EggRack mRack = this.mRack;
                    ++mRack.mWidth;
                    return;
                }
                break;
            }
            case 7: {
                this.mApplet.PlaySound(20);
                this.mBonusCountdown[n2] = 50;
                if (this.mRack.mWidth > 5) {
                    final EggRack mRack2 = this.mRack;
                    --mRack2.mWidth;
                    this.mRack.WidthCleanup(true);
                    this.mRack.DropUnsupportedEggs();
                    return;
                }
                break;
            }
        }
    }
    
    public void MouseDown(final int n, final int n2, final int n3) {
        super.MouseDown(n, n2, n3);
        if (this.mPause) {
            return;
        }
        if (this.mCleared) {
            return;
        }
        if (this.mDynoComboDuration > 0 && this.mDynoComboDuration < 215) {
            return;
        }
        if (this.mHopEgg) {
            return;
        }
        this.mFirstFired = true;
        if (this.mEggInAir || this.mGameOver || this.mRack.mCrush) {
            return;
        }
        this.mAimAngle = this.GetPointAngle(this.mCenter, 298, this.mAimX, this.mAimY);
        final double n4 = Math.sin(this.mAimAngle) * 10.0;
        final double n5 = Math.cos(this.mAimAngle) * 10.0;
        this.mEggInAir = true;
        this.mEggInAirX = this.mCenter + (int)n4;
        this.mEggInAirY = 298 + (int)n5;
        this.mEggInAirXDir = -Math.sin(this.mAimAngle) * 2.0;
        this.mEggInAirYDir = -Math.cos(this.mAimAngle) * 2.0;
        this.mEggInAirColor = this.mEggSlingshotColor;
        this.mEggInAirBonus = this.mEggSlingshotBonus;
        final int mEggSlingshotColor = this.mEggSlingshotColor;
        this.mLastRealFiredEgg = mEggSlingshotColor;
        this.mLastFiredEgg = mEggSlingshotColor;
        this.mApplet.PlaySound(3);
        this.mEggSlingshotColor = this.mRack.NextEgg();
    }
    
    public void StartWhirley() {
        this.mWhirley = true;
        if (this.mRack.mRand.Next() % 2 == 0) {
            this.mWhirleyX = this.mCenter + this.mRack.mRand.Next() % (this.mRack.mWidth * 23 / 2);
        }
        else {
            this.mWhirleyX = this.mCenter - this.mRack.mRand.Next() % (this.mRack.mWidth * 23 / 2);
        }
        this.mWhirleyY = 390.0;
        this.mWhirleyFrame = 0.0;
        this.mWhirleyXDir = 0.0;
        this.mWhirleyYDir = -13.8;
        this.mWhirleySpeedX = 1.0;
        this.mWhirleySpeedY = 1.0;
        for (int i = 0; i < this.mWhirleyLevel; ++i) {
            this.mWhirleySpeedX *= 1.2000000476837158;
            this.mWhirleySpeedY += 0.25;
        }
        if (this.mWhirleySpeedY > 8.28) {
            this.mWhirleySpeedY = 8.28;
        }
        if (this.mRack.mRand.Next() % 2 == 0) {
            this.mWhirleySpeedX = -this.mWhirleySpeedX;
            this.mWhirleySpeedX = this.mWhirleySpeedX;
        }
        this.mWhirleyState = 0;
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
        if (this.mReplayButton != null) {
            widgetManager.AddWidget(this.mReplayButton);
        }
        if (this.mUploadScoreButton != null) {
            widgetManager.AddWidget(this.mUploadScoreButton);
        }
    }
    
    public void ButtonDownTick(final int n) {
    }
    
    public void MouseDrag(final int n, final int n2) {
        this.MouseMove(n, n2);
    }
    
    public void Score(int n, final Point point) {
        if (this.mRack.mCombo > 0) {
            int n2 = 0;
            switch (this.mRack.mCombo) {
                case 1: {
                    n2 = 3;
                    break;
                }
                case 2: {
                    n2 = 4;
                    break;
                }
                case 3: {
                    n2 = 6;
                    break;
                }
                case 4: {
                    n2 = 8;
                    break;
                }
                case 5: {
                    n2 = 10;
                    break;
                }
                case 6: {
                    n2 = 15;
                    break;
                }
            }
            n += n2;
        }
        if (this.mMode == 0) {
            switch (this.mApplet.mSkill) {
                case 0: {
                    n /= 5;
                    if (n < 1) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 2: {
                    n *= 2;
                    break;
                }
            }
        }
        else {
            switch (this.mApplet.mSkill) {
                case 0: {
                    n /= 2;
                    if (n < 1) {
                        n = 1;
                        break;
                    }
                    break;
                }
                case 2: {
                    n *= 2;
                    break;
                }
            }
        }
        n *= 10;
        String mText = "+" + n;
        if (this.IsBonus(2)) {
            n *= 2;
            mText += "x2";
        }
        if (this.IsBonus(3)) {
            n *= 3;
            mText += "x3";
        }
        if (this.IsBonus(4)) {
            n *= 4;
            mText += "x4";
        }
        final Animator animator = new Animator(this, 3);
        animator.mPosX = point.x;
        animator.mPosY = point.y;
        animator.mXDir = 0.0;
        if (!this.mCleared) {
            animator.mYDir = -0.1;
            animator.mCountdown = 50;
        }
        else {
            animator.mYDir = 0.0;
            animator.mCountdown = 100;
        }
        animator.mText = mText;
        this.mAnimatorVector.addElement(animator);
        this.MarkDirty();
        this.mApplet.mSidebar.AddScore(n);
        int mScoreTitle = 0;
        do {
            if (this.mApplet.mSidebar.mScore > this.mScoreProgression[mScoreTitle]) {
                this.mScoreTitle = mScoreTitle;
            }
        } while (++mScoreTitle < 20);
    }
    
    public double AddAngle(final double n, final double n2) {
        return n + n2 * 3.141592653589793 / 180.0;
    }
}
