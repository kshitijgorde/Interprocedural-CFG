// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import sexy.gui.SexyImage;
import sexy.gui.WidgetManager;
import sexy.gui.SexyColor;
import java.util.Enumeration;
import java.awt.Color;
import java.awt.Rectangle;
import sexy.gui.SexyGraphics;
import nc.Assert;
import nc.particle.ParticleSystem;
import sexy.gui.OutlineButtonWidget;
import java.util.Vector;
import sexy.util.MTRand;
import sexy.gui.ButtonListener;
import sexy.gui.Widget;

public class Board extends Widget implements ButtonListener
{
    static final int[][] mButtonColors;
    static final int STATS_MIDX = 306;
    static final int SCORE_INDICATOR_X = 63;
    static final int SCORE_INDICATOR_Y = 88;
    static final int LEVEL_INDICATOR_Y = 111;
    static final int STATE_PLAYING = 0;
    static final int STATE_LEVEL_PAUSE = 1;
    static final int STATE_PRE_LEVEL_TRANSITION = 2;
    static final int STATE_CLEARING = 3;
    static final int STATE_GAME_OVER_ANIM = 4;
    static final int STATE_GAME_OVER = 5;
    static final int STATE_EXPLODING = 6;
    static final boolean[] skScreenShakeInState;
    static final int NUM_COLS = 6;
    static final int NUM_ROWS = 9;
    static final int NEXT_YOFFSET = 6;
    static final int MAXTAGNUMS = 30;
    static final int TOP_LEFT_TILE_X = 194;
    static final int TOP_LEFT_TILE_Y = 33;
    static final int skBarX = 132;
    static final int skBarY = 354;
    static final int skBarWidth = 342;
    static final int skBarHeight = 10;
    MTRand mRand;
    int mTotalGameTime;
    private int mState;
    boolean mMadeFirstMove;
    RocketManiaApplet mApplet;
    Tube[][] mBoard;
    Tube[] mNextTubes;
    Rocket[] mRockets;
    int mUpdateCnt;
    int mLastHitTick;
    int mPoints;
    int mDispPoints;
    double mPointMult;
    Vector mPointsVector;
    Points mLastBonusText;
    int mMouseX;
    int mMouseY;
    int mLastWarnTick;
    Vector mActiveObjects;
    boolean mShowingLevelUp;
    int mLevelUpY;
    boolean mPiecesFalling;
    int mLevel;
    int mPreLevelTransitionDelay;
    int mPauseCount;
    int mLevelIntroDelay;
    boolean mShowingLevelIntro;
    int mLevelIntroLife;
    boolean mShowCountdown;
    int mCountdownNum;
    int mCoinDownDelay;
    OutlineButtonWidget mButton;
    OutlineButtonWidget mNoButton;
    double mSpeedBonusFactor;
    int mSpeedRating;
    int mExplodeDelay;
    int mCoinHelpLife;
    Vector mHelperPositions;
    int mNumColors;
    boolean mAskingUploadScore;
    int mClickUpdateCnt;
    int aSkillLevel;
    static final int GLOWUPSEQUENCETIME = 33;
    static final int NEWBLOCKRELEASEPERIOD = 1;
    private int mNewBlockReleaseTimer;
    private int mNewBlockReleaseColumn;
    Progression mProgression;
    int[] mSidesTouchedByTagNum;
    private int mTagNum_FloodFillGlobal;
    private int mVisitedWalls_FloodFillGlobal;
    private boolean[] mLeftSideColumnTouched;
    private boolean[] mRightSideColumnTouched;
    boolean mInGlowUpSequence;
    int mGlowUpSequenceTag;
    int mGlowUpSequenceTimer;
    int mNumTravellingSparks;
    private boolean mClockMustDrop;
    private boolean mClockDropped;
    double mLevelTimer;
    double mLevelTimerStart;
    double mFreezeTimeTimer;
    int mRocketsNeededToLevel;
    Stats mStats;
    String mLastLevelUpRank;
    static final float GAME_TIMESTEP = 0.02f;
    static final double FREEZETIME = 10.0;
    static final int PARTICLES_MAX_EMITTERS = 50;
    ParticleSystem mParticleSystem;
    ParticleDatabase mParticleDatabase;
    BufferedBackground mBufferedBackground;
    int mCoinsPickedUpDuringTravellingSpark;
    int mGemsPickedUpDuringTravellingSpark;
    private int mDisplayTileCount;
    private int mDisplayTileScoreRow;
    private int mCarriedOverCoinDropDelay;
    private int mGameOverAnimStateCounter;
    int mCoins;
    private boolean mMustCheckForCombo;
    private boolean mMustCheckForBoardInRest;
    private int mComboDisplayTimer;
    private boolean mCockerelHasCrowed;
    DelayedSounds mDelayedSounds;
    private double mScreenShakeTimer;
    private int mScreenShakeAmplitude;
    private double mDelayToWellDone;
    private int[] mMatchFrames;
    private int mNextBellToPlay;
    private int mFlameCounter;
    private int mFlameFrame;
    private int mFireworkTimeDuringLevelChange;
    private int mRankColourPhase;
    
    boolean BoardIsInRest() {
        int n = 0;
        do {
            int n2 = 0;
            do {
                final Tube tube = this.mBoard[n][n2];
                if (tube == null || tube.mAction != 0) {
                    return false;
                }
            } while (++n2 < 6);
        } while (++n < 9);
        return true;
    }
    
    private void HuntAndRemovePieceFromActiveList(final Piece piece) {
        if (piece.mIsActive) {
            piece.mIsActive = false;
            this.mActiveObjects.removeElement(piece);
        }
    }
    
    public void Update() {
        for (int i = 0; i < this.mSpeedRating; ++i) {
            this.UpdateHelper();
        }
    }
    
    private void NewRocket(final int n, final int n2) {
        final Rocket rocket = new Rocket(this, n, n2);
        this.AddPieceToActiveList(rocket);
        this.mRockets[n] = rocket;
    }
    
    void DisplayTileScore_Reset() {
        this.mDisplayTileCount = 0;
        this.mDisplayTileScoreRow = -1;
    }
    
    void SetupBoard(final boolean b, final boolean b2) {
        this.mMustCheckForBoardInRest = false;
        this.mMustCheckForCombo = false;
        this.mComboDisplayTimer = 0;
        this.mDelayToWellDone = 0.0;
        int n = 0;
        do {
            this.mSidesTouchedByTagNum[n] = 0;
        } while (++n < 30);
        this.Empty();
        int n2 = 0;
        do {
            this.mMatchFrames[n2] = (int)(30.0 * Math.random());
        } while (++n2 < 9);
        int n3 = 0;
        do {
            this.mNextTubes[n3] = new Tube(this, this.mUpdateCnt, -1, n3);
        } while (++n3 < 6);
        this.mNewBlockReleaseColumn = 0;
        this.mStats.mLevelStats.Reset();
        this.mNextBellToPlay = 9;
        if (b2) {
            do {
                int n4 = 0;
                do {
                    int n5 = 0;
                    do {
                        this.mBoard[n4][n5] = new Tube(this, this.mUpdateCnt, n4, n5);
                    } while (++n5 < 6);
                } while (++n4 < 9);
            } while (this.CheckForPipeWithoutAffectingGame());
        }
        if (b) {
            int n6 = 0;
            do {
                this.NewRocket(n6, 0);
                this.mRockets[n6].FinishMove();
            } while (++n6 < 9);
            this.mStats.mGameStats.Reset();
            this.RemovePoints();
            this.mDelayedSounds = new DelayedSounds();
            Assert.assert(this.mApplet.mGameType == 0);
        }
    }
    
    static int GetColX(final int n) {
        return 194 + n * 34;
    }
    
    private boolean CheckForPipeWithoutAffectingGame() {
        this.ResetFloodFill();
        return this.DoSideWallCheckForPipes(0, 8, false);
    }
    
    void FreezeTime() {
        final int getTimerBarX = this.GetTimerBarX();
        final int getTimerBarY = this.GetTimerBarY();
        if (this.mFreezeTimeTimer < 9.0) {
            this.AddTextFloater("Time Frozen!", 50, getTimerBarX, getTimerBarY);
        }
        this.mFreezeTimeTimer += 10.0;
    }
    
    private void ResetFloodFill() {
        int n = 8;
        do {
            int n2 = 0;
            do {
                final Tube tube = this.mBoard[n][n2];
                if (tube != null && tube.mAction == 0) {
                    tube.mBurningTag = 0;
                }
            } while (++n2 < 6);
        } while (--n >= 0);
        int n3 = 0;
        do {
            this.mSidesTouchedByTagNum[n3] = 0;
        } while (++n3 < 30);
        this.mTagNum_FloodFillGlobal = 1;
    }
    
    void Empty() {
        int n = 0;
        do {
            int n2 = 0;
            do {
                final Tube tube = this.mBoard[n][n2];
                if (tube != null && tube.mPickup != null) {
                    this.HuntAndRemovePieceFromActiveList(tube.mPickup);
                }
                this.mBoard[n][n2] = null;
            } while (++n2 < 6);
        } while (++n < 9);
        for (int i = 0; i < this.mActiveObjects.size(); ++i) {
            if (((Piece)this.mActiveObjects.elementAt(i)) instanceof Pickup) {
                this.mActiveObjects.removeElementAt(i);
                --i;
            }
        }
    }
    
    public void SendGameBreak(final boolean b) {
        this.mApplet.SendGameBreakNotification(this.mLevel + 1, b, "" + (this.mApplet.mSkillLevel + 1));
    }
    
    public void drawInPlayStuff(final SexyGraphics sexyGraphics) {
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[2], 149, 2);
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[3], 392, 2);
        if (this.mApplet.mBuyMe == null) {
            sexyGraphics.SetDrawMode(1);
            int n = 0;
            do {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[9], 158, 28 + 34 * n, new Rectangle(25 * ((this.mMatchFrames[n] + this.mFlameFrame) % 10), 0, 25, 30));
            } while (++n < 9);
            sexyGraphics.SetDrawMode(0);
        }
        int n2 = 0;
        do {
            int n3 = 8;
            do {
                final Tube tube = this.mBoard[n3][n2];
                if (tube != null) {
                    tube.Draw(this, sexyGraphics);
                }
            } while (--n3 >= 0);
        } while (++n2 < 6);
        for (int i = 0; i < this.mActiveObjects.size(); ++i) {
            final Piece piece = this.mActiveObjects.elementAt(i);
            if (!(piece instanceof Tube)) {
                piece.Draw(this, sexyGraphics);
            }
        }
        this.drawTimerBar(sexyGraphics);
        this.mParticleSystem.Render(sexyGraphics, 1);
        this.DisplayTileScore_Display(sexyGraphics);
        if (this.mState == 0 && this.mLevelTimer < 10.0) {
            final int n4 = (int)this.mLevelTimer;
            sexyGraphics.SetFont(this.mApplet.mRes.mFont16Outlined);
            sexyGraphics.SetColor(Color.orange);
            sexyGraphics.DrawString("" + n4, this.mMouseX + 12, this.mMouseY + 27);
        }
    }
    
    public void SetPause() {
        if (++this.mPauseCount == 1) {
            final Enumeration<Points> elements = this.mPointsVector.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().SetVisible(false);
            }
            if (this.mButton != null) {
                this.mButton.SetVisible(false);
            }
            this.MarkDirty();
        }
    }
    
    public void UpdateHelper() {
        this.mDelayedSounds.Update(this.mApplet, 0.019999999552965164);
        this.mApplet.mRes.UpdateStreamingStatus();
        if (this.mPauseCount > 0) {
            if (this.mApplet.mLevelUpScreen != null) {
                this.MarkDirty();
            }
            return;
        }
        ++this.mUpdateCnt;
        if (this.mApplet.mNeedSendGameStart) {
            this.mApplet.SendGameStartNotification(null);
        }
        if (this._inPlayDraw() && this.UpdateFlames()) {
            this.MarkDirty();
        }
        if (this.mScreenShakeTimer > 0.0) {
            this.MarkDirty();
            this.mScreenShakeTimer -= 0.019999999552965164;
            if (this.mScreenShakeTimer < 0.0) {
                this.mScreenShakeTimer = 0.0;
                this.mScreenShakeAmplitude = 0;
            }
        }
        if (this.mLevelIntroDelay > 0 && --this.mLevelIntroDelay == 0) {
            this.mApplet.mFloatingHead.PlaySequence(5);
            this.mLevelIntroLife = 90;
        }
        if (this.mLevelIntroLife > 0) {
            --this.mLevelIntroLife;
            this.mShowingLevelIntro = (this.mLevelIntroLife > 0);
            this.MarkDirty();
        }
        if (this.mInGlowUpSequence) {
            if (--this.mGlowUpSequenceTimer <= 0) {
                if (this.mGlowUpSequenceTimer == 0) {
                    this.StartTravellingSparks();
                }
                else if (this.mGlowUpSequenceTimer < -1 && this.mNumTravellingSparks == 0) {
                    this.EndGlowUpSequence();
                }
            }
        }
        else if (++this.mNewBlockReleaseTimer >= 1) {
            this.mNewBlockReleaseTimer = 0;
            this.DropNewTube();
        }
        this.UpdateActiveObjects();
        if (this.mMustCheckForBoardInRest && this.BoardIsInRest()) {
            this.mMustCheckForBoardInRest = false;
            this.mMustCheckForCombo = true;
        }
        if (this.mUpdateCnt % 25 == 24) {
            this.DropBoardPieces();
        }
        this.UpdateTubes();
        this.mMustCheckForCombo = false;
        if (this.mParticleSystem.Update(0.02f)) {
            this.MarkDirty();
        }
        switch (this.mState) {
            case 2: {
                if (this.mPreLevelTransitionDelay > 0) {
                    --this.mPreLevelTransitionDelay;
                    break;
                }
                this.mState = 1;
                this.mShowingLevelUp = false;
                this.SetupStatsWidgets();
                this.RemovePoints();
                this.mFireworkTimeDuringLevelChange = 0;
                break;
            }
            case 4: {
                if (--this.mGameOverAnimStateCounter <= 0) {
                    this.mState = 5;
                    this.mFireworkTimeDuringLevelChange = 0;
                    this.SetupStatsWidgets();
                    break;
                }
                break;
            }
            case 0: {
                if (this.mComboDisplayTimer > 0) {
                    --this.mComboDisplayTimer;
                }
                if (this.mDelayToWellDone > 0.0) {
                    this.mDelayToWellDone -= 0.019999999552965164;
                    if (this.mDelayToWellDone <= 0.0) {
                        this.mDelayToWellDone = 0.0;
                        this.mApplet.mFloatingHead.PlaySequence(7);
                    }
                }
                this.mApplet.mHints.Update(this.mApplet, 0.02f);
                if (this.mFreezeTimeTimer > 0.0) {
                    this.mFreezeTimeTimer -= 0.019999999552965164;
                    if (this.mFreezeTimeTimer <= 0.0) {
                        this.UnfreezeTime();
                    }
                }
                else if (!this.mShowingLevelIntro) {
                    this.mLevelTimer -= 0.019999999552965164;
                }
                if (this.mLevelTimer < 0.0) {
                    this.mLevelTimer = 0.0;
                    this.GameOver();
                }
                if (!this.mCockerelHasCrowed && this.mLevelTimer < 10.0) {
                    this.mApplet.PlaySound(12);
                    this.mCockerelHasCrowed = true;
                }
                if (this.mLevelTimer <= this.mNextBellToPlay) {
                    this.mApplet.PlaySound(20);
                    --this.mNextBellToPlay;
                }
                if (this.mLevelTimer < 30.0) {
                    this.mApplet.mHints.Queue(6, 0.1f);
                }
                if (this.mCarriedOverCoinDropDelay > 0 && --this.mCarriedOverCoinDropDelay == 0) {
                    this.AddCoins();
                    break;
                }
                break;
            }
            case 1:
            case 5: {
                if (++this.mFireworkTimeDuringLevelChange < 750 && this.mRand.Next() % 75 == 0) {
                    Fireworks.AddFirework(this.mApplet, this.mParticleSystem, this.mParticleDatabase, this.mRand.Next() % 10, 60);
                }
                if (this.mFireworkTimeDuringLevelChange >= 500) {
                    break;
                }
                final int mRankColourPhase = this.mRankColourPhase;
                this.mRankColourPhase = 127 + (int)(127.0 * Math.sin(this.mUpdateCnt / 20.0));
                if (this.mRankColourPhase != mRankColourPhase) {
                    this.MarkDirty();
                    break;
                }
                break;
            }
        }
        final Enumeration<Points> elements = (Enumeration<Points>)this.mPointsVector.elements();
        while (elements.hasMoreElements()) {
            final Points points = elements.nextElement();
            points.MarkDirty();
            if (this.mUpdateCnt % 2 == 0 && points.mY > 0) {
                points.Move(points.mX, points.mY - 1);
            }
            if (--points.mLife <= 0) {
                this.mPointsVector.removeElement(points);
                super.mWidgetManager.RemoveWidget(points);
            }
        }
        if (this.mDispPoints != this.mPoints) {
            this.MarkDirty();
            final double n = (this.mPoints - this.mDispPoints) / this.mPointMult;
            if (this.mDispPoints < this.mPoints) {
                this.mDispPoints += (int)((4.0 + n / 50.0) * this.mPointMult);
            }
            if (this.mDispPoints > this.mPoints) {
                this.mDispPoints = this.mPoints;
            }
        }
    }
    
    void drawTimerBar(final SexyGraphics sexyGraphics) {
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[29], 128, 349);
        final int n = 342 - (int)(342.0 * (1.0 - this.mLevelTimer / this.mLevelTimerStart));
        if (n > 0) {
            if (this.mFreezeTimeTimer > 0.0) {
                sexyGraphics.SetColor(new SexyColor(63, 191, 191, 100));
            }
            else if (this.mLevelTimer < 10.0 && (this.mUpdateCnt & 0x10) == 0x0) {
                sexyGraphics.SetColor(new SexyColor(255, 40, 20));
            }
            else {
                sexyGraphics.SetColor(new SexyColor(252, 174, 25));
            }
            sexyGraphics.FillRect(132, 354, n, 10);
        }
        if (n < 342) {
            sexyGraphics.SetColor(new SexyColor(0, 21, 36, 128));
            sexyGraphics.FillRect(132 + n, 354, 342 - n, 10);
        }
        sexyGraphics.SetColor(new SexyColor(0, 42, 72));
        sexyGraphics.DrawRect(132, 354, 342, 10);
    }
    
    void DisplayTileScore_Inc() {
        ++this.mDisplayTileCount;
    }
    
    public void ButtonDownTick(final int n) {
    }
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        this.mApplet.StatsGroupBegin("Game");
        this.mApplet.StatsValue("SkillLevel", this.aSkillLevel);
        this.mApplet.StatsValue("Score", this.mPoints);
        this.mApplet.StatsValue("Time", this.mTotalGameTime);
        this.mApplet.StatsValue("LastLevel", this.mLevel);
        this.mApplet.StatsValue("WasAbandoned", this.mState != 5);
        if (this.mState != 5) {
            this.mApplet.StatsValue("Abandoned");
        }
        this.mApplet.StatsGroupEnd();
        if (this.mButton != null) {
            widgetManager.RemoveWidget(this.mButton);
            this.mButton = null;
        }
        if (this.mNoButton != null) {
            widgetManager.RemoveWidget(this.mNoButton);
            this.mNoButton = null;
        }
        final Enumeration<Points> elements = this.mPointsVector.elements();
        while (elements.hasMoreElements()) {
            widgetManager.RemoveWidget(elements.nextElement());
        }
    }
    
    private void UntagPieces(final int n) {
        if (this.mNumTravellingSparks == 0) {
            int n2 = 8;
            do {
                int n3 = 0;
                do {
                    final Tube tube = this.mBoard[n2][n3];
                    if (tube != null && tube.mAction == 2 && tube.mBurningTag == n) {
                        tube.mAction = 0;
                        tube.mBurningTag = 0;
                    }
                } while (++n3 < 6);
            } while (--n2 >= 0);
            this.mInGlowUpSequence = false;
            this.mGlowUpSequenceTag = -1;
            this.mGlowUpSequenceTimer = 0;
        }
    }
    
    void UpdateActiveObjects() {
        boolean b = false;
        for (int i = 0; i < this.mActiveObjects.size(); ++i) {
            final Piece piece = this.mActiveObjects.elementAt(i);
            if (!piece.mRemoveFromActiveList) {
                b |= piece.Update(this);
            }
        }
        for (int j = 0; j < this.mActiveObjects.size(); ++j) {
            final Piece piece2 = this.mActiveObjects.elementAt(j);
            if (piece2.mRemoveFromActiveList) {
                this.mActiveObjects.removeElementAt(j);
                --j;
                piece2.mIsActive = false;
            }
            if (piece2.mDestroy) {
                if (piece2.mEmitter != null) {
                    this.mParticleSystem.RemoveParticleEmitter(piece2.mEmitter);
                    piece2.mEmitter = null;
                }
                b = true;
            }
        }
        for (int k = 0; k < this.mActiveObjects.size(); ++k) {
            final Piece piece3 = this.mActiveObjects.elementAt(k);
            piece3.mRemoveFromActiveList = false;
            if (piece3 instanceof Pickup) {
                final Pickup pickup = (Pickup)piece3;
                if (pickup.mAction != 2) {
                    boolean b2 = true;
                    if (pickup.mParent != null) {
                        b2 = false;
                        final Tube mParent = pickup.mParent;
                        if (mParent.mBoardCol < 0 || mParent.mBoardCol >= 6 || mParent.mBoardRow < 0 || mParent.mBoardRow >= 9 || this.mBoard[mParent.mBoardRow][mParent.mBoardCol] != mParent) {
                            b2 = true;
                        }
                    }
                    if (b2) {
                        pickup.mParent = null;
                        pickup.mIsActive = false;
                        this.mActiveObjects.removeElementAt(k);
                        --k;
                    }
                }
            }
        }
        if (b) {
            this.MarkDirty();
        }
    }
    
    void DropNewTube() {
        if (this.mBoard[0][this.mNewBlockReleaseColumn] == null) {
            final Tube tube = this.mNextTubes[this.mNewBlockReleaseColumn];
            if (tube != null) {
                tube.mBoardCol = this.mNewBlockReleaseColumn;
                tube.mBoardRow = -1;
                tube.Drop(this);
                tube.mPickup = this.NewPickupOrNull(tube);
                final Tube tube2 = new Tube(this, this.mUpdateCnt, -1, this.mNewBlockReleaseColumn);
                tube2.mY = GetRowY(0) - 50;
                this.mNextTubes[this.mNewBlockReleaseColumn] = tube2;
            }
            else {
                Assert.assert(false);
            }
        }
        this.mNewBlockReleaseColumn = (this.mNewBlockReleaseColumn + 1) % 6;
    }
    
    public void ButtonMouseEnter(final int n) {
    }
    
    void AddPointsFloater(final int n, final int n2, final int n3) {
        final Points points = new Points(this.mApplet, this.mApplet.mRes.mFont14Outlined, "" + (int)(n * this.mPointMult), super.mX + n2, super.mY + n3, Math.max(50, Math.min(100, n / 3)), 0);
        this.mPointsVector.addElement(points);
        super.mWidgetManager.AddWidget(points);
    }
    
    void AddTextFloater(final String s, final int n, final int n2, final int n3) {
        final Points points = new Points(this.mApplet, this.mApplet.mRes.mFont16Outlined, s, super.mX + n2, super.mY + n3, n, 0);
        this.mPointsVector.addElement(points);
        super.mWidgetManager.AddWidget(points);
    }
    
    void SetupStatsWidgets() {
        this.mTotalGameTime += (int)(this.mLevelTimerStart - this.mLevelTimer);
        this.MakeReplayButton();
        this.MarkDirty();
    }
    
    void UnfreezeTime() {
        this.mFreezeTimeTimer = 0.0;
        final int getTimerBarX = this.GetTimerBarX();
        final int getTimerBarY = this.GetTimerBarY();
        this.mParticleSystem.CreateParticleEmitter(this.mParticleDatabase.mForegroundSplash, this.mParticleDatabase.mStar, Pickup.skClockColour, getTimerBarX, getTimerBarY, 0.0f, 0.0f);
        this.AddTextFloater("Unfreezing!", 50, getTimerBarX, getTimerBarY);
    }
    
    void AddPieceToActiveList(final Piece piece) {
        if (!this.mActiveObjects.contains(piece)) {
            piece.mIsActive = true;
            this.mActiveObjects.addElement(piece);
        }
    }
    
    void AddScreenShake(final int n) {
        final int[] array = { 0, 0, 0, 1, 1, 2, 3, 4, 4, 5 };
        final double[] array2 = { 0.0, 0.0, 0.0, 0.2, 0.3, 0.45, 0.6, 0.75, 0.9, 1.0 };
        this.mScreenShakeAmplitude += array[n];
        this.mScreenShakeTimer += array2[n];
    }
    
    private void BlowUpBlocksWithTagNum(final int n) {
        int n2 = 0;
        int n3 = 8;
        do {
            int n4 = 0;
            do {
                final Tube tube = this.mBoard[n3][n4];
                if (tube != null && tube.mAction == 2 && tube.mBurningTag == n) {
                    this.mBoard[n3][n4] = null;
                    ++n2;
                }
            } while (++n4 < 6);
        } while (--n3 >= 0);
        this.mStats.CheckLongestFuse(n2);
    }
    
    int ScaleScore(final int n) {
        return (int)(Scores.SCALES[this.mApplet.mSkillLevel] * n);
    }
    
    int AddScore(final int n) {
        int scaleScore = this.ScaleScore(n);
        if (this.mCoinsPickedUpDuringTravellingSpark > 1) {
            scaleScore *= this.mCoinsPickedUpDuringTravellingSpark;
        }
        this.mPoints += scaleScore;
        return scaleScore;
    }
    
    Pickup NewPickupOrNull(final Tube tube) {
        int getRandomPickupType = -1;
        if (this.mClockMustDrop && !this.mClockDropped) {
            this.mClockMustDrop = false;
            getRandomPickupType = 2;
        }
        else if (this.mProgression.ShouldAddRandomPickup()) {
            getRandomPickupType = this.mProgression.GetRandomPickupType();
        }
        if (getRandomPickupType != -1) {
            final Pickup pickup = new Pickup(this, getRandomPickupType, tube, 0);
            this.AddPieceToActiveList(pickup);
            return pickup;
        }
        return null;
    }
    
    void RemovePoints() {
        final Enumeration<Points> elements = this.mPointsVector.elements();
        while (elements.hasMoreElements()) {
            super.mWidgetManager.RemoveWidget(elements.nextElement());
        }
        this.mPointsVector.removeAllElements();
    }
    
    public void MouseDown(final int n, final int n2, final int n3) {
        super.MouseDown(n, n2, n3);
        final boolean b = this.mPauseCount > 0;
        if (this.mApplet.mBuyMe == null) {
            this.SetUnpause();
        }
        if (b || this.mState != 0) {
            return;
        }
        this.ProcessClick(n, n2, n3);
    }
    
    private void _drawStats(final SexyGraphics sexyGraphics) {
        final SpacedFont spacedFont = new SpacedFont(sexyGraphics, this.mApplet.mRes.mLineFontOutlined, -2);
        final SpacedFont spacedFont2 = new SpacedFont(sexyGraphics, this.mApplet.mRes.mLineFontSmallOutlined, -2);
        final SpacedFont spacedFont3 = new SpacedFont(sexyGraphics, this.mApplet.mRes.mFont20Outlined, -1);
        final SpacedFont spacedFont4 = new SpacedFont(sexyGraphics, this.mApplet.mRes.mFont18Outlined, -1);
        final boolean b = this.mState == 5;
        final Stats.StatsGroup statsGroup = b ? this.mStats.mGameStats : this.mStats.mLevelStats;
        final int n = 240;
        final int n2 = 306;
        final int n3 = n2 - n / 2;
        final int n4 = n2 + n / 2;
        final int n5 = 50;
        final int n6 = 22;
        final Color yellow = Color.yellow;
        final Color orange = Color.orange;
        sexyGraphics.SetColor(yellow);
        spacedFont3.DrawString(b ? "Well Done!" : "Level Up!", n2, n5 - 5);
        sexyGraphics.SetColor(orange);
        String s;
        if (b) {
            s = "Your Rank:";
        }
        else if (Ranks.GetName(this.mPoints) != this.mLastLevelUpRank) {
            s = "You have gained a new rank!";
        }
        else {
            s = "Your rank is";
        }
        spacedFont.DrawString(s, n2, n5 + 26);
        sexyGraphics.SetColor(new Color(255, this.mRankColourPhase, 0));
        spacedFont4.DrawString(Ranks.GetName(this.mPoints), n2, n5 + 55);
        if (Ranks.HasNextRank(this.mPoints)) {
            sexyGraphics.SetColor(orange);
            spacedFont2.DrawString("Next Rank at " + Ranks.NextRankAt(this.mPoints) + " points", n2, n5 + 83);
        }
        spacedFont2.SetJustification(0);
        sexyGraphics.SetColor(orange);
        spacedFont2.DrawString(b ? "Game Time:" : "Current Game Time:", n3, n5 + 7 * n6);
        spacedFont2.DrawString("Most rockets launched:", n3, n5 + 8 * n6);
        spacedFont2.DrawString("Longest fuse burned:", n3, n5 + 9 * n6);
        spacedFont2.DrawString("Coins collected:", n3, n5 + 10 * n6);
        spacedFont2.DrawString("Gems collected:", n3, n5 + 11 * n6);
        spacedFont2.SetJustification(2);
        sexyGraphics.SetColor(yellow);
        String s2 = this.mTotalGameTime / 60 + ":";
        if (this.mTotalGameTime % 60 < 10) {
            s2 += "0";
        }
        spacedFont2.DrawString(s2 + this.mTotalGameTime % 60, n4, n5 + 7 * n6);
        spacedFont2.DrawString("" + statsGroup.mMostRocketsFiredAtOnce, n4, n5 + 8 * n6);
        spacedFont2.DrawString("" + statsGroup.mLongestFuseNumSegments, n4, n5 + 9 * n6);
        spacedFont2.DrawString("" + statsGroup.mCoinValueCollected, n4, n5 + 10 * n6);
        spacedFont2.DrawString("" + statsGroup.mGemsCollected, n4, n5 + 11 * n6);
    }
    
    void AddCoins() {
        if (this.mState != 0) {
            return;
        }
        int n = 3;
        while (true) {
            if (this.mProgression.mNumBonusesWaitingToBeDropped < Pickup.COIN_VALUE[n]) {
                if (--n < 0) {
                    break;
                }
                continue;
            }
            else {
                final Progression mProgression = this.mProgression;
                mProgression.mNumBonusesWaitingToBeDropped -= Pickup.COIN_VALUE[n];
                this.NewCoin(n);
            }
        }
    }
    
    boolean SidePiecesLinked() {
        int n = 0;
        while (!this.mLeftSideColumnTouched[n]) {
            if (++n >= 9) {
                return false;
            }
        }
        int n2 = 0;
        while (!this.mRightSideColumnTouched[n2]) {
            if (++n2 >= 9) {
                return false;
            }
        }
        return true;
    }
    
    private boolean _inPlayDraw() {
        return this.mApplet.mBuyMe != null || ((this.mApplet.mDialogWidget == null || this.mApplet.mDialogType == 2) && this.mState != 5 && this.mState != 1 && (this.mPauseCount == 0 || (this.mPauseCount == 1 && (this.mApplet.mDialogWidget != null || this.mApplet.mLevelUpScreen != null))));
    }
    
    public void MouseMove(final int mMouseX, final int mMouseY) {
        super.MouseMove(mMouseX, mMouseY);
        this.mMouseX = mMouseX;
        this.mMouseY = mMouseY;
    }
    
    void MakeReplayButton() {
        Assert.assert(this.mButton == null);
        if (this.mButton != null) {
            super.mWidgetManager.RemoveWidget(this.mButton);
            this.mButton = null;
        }
        this.mButton = new OutlineButtonWidget(super.mWidgetManager, 0, this);
        this.mButton.mLabel = ((this.mState == 5) ? "Click here to play again" : "Click here to play next level!");
        this.mButton.mFont = this.mApplet.mRes.mFont18;
        this.mButton.Resize(306 - this.mButton.mFont.StringWidth(this.mButton.mLabel) / 2, 344, this.mButton.mFont.StringWidth(this.mButton.mLabel), 21);
        this.mButton.SetColors(Board.mButtonColors);
        this.mButton.mOutlineSize = 0;
        this.mButton.mHideFill = true;
        this.mButton.mDoFinger = true;
        super.mWidgetManager.AddWidget(this.mButton);
        super.mWidgetManager.PutInfront(this.mButton, this);
    }
    
    private void GameOver() {
        this.mState = 4;
        this.mGameOverAnimStateCounter = 150;
        this.mApplet.mFloatingHead.PlaySequence(8);
        this.mApplet.ShowLevelUpScreen(true);
    }
    
    void UpdateTubes() {
        boolean b = false;
        int n = 8;
        do {
            int n2 = 0;
            do {
                final Tube tube = this.mBoard[n][n2];
                if (tube != null) {
                    b |= tube.UpdateRotation();
                }
            } while (++n2 < 6);
        } while (--n >= 0);
        if (b) {
            this.MarkDirty();
        }
        this.UpdatePipes();
    }
    
    boolean UpdateFlames() {
        boolean b = false;
        if (++this.mFlameCounter == 3) {
            ++this.mFlameFrame;
            this.mFlameCounter = 0;
            b = true;
        }
        return b;
    }
    
    void MarkPipes(final int n, final int n2) {
        final Tube tube = this.mBoard[n2][n];
        if (tube.mBurningTag != this.mTagNum_FloodFillGlobal) {
            tube.mBurningTag = this.mTagNum_FloodFillGlobal;
            if (n == 0 && tube.CheckDirection(8)) {
                this.mVisitedWalls_FloodFillGlobal |= 0x2;
                this.mLeftSideColumnTouched[n2] = true;
                final int[] mSidesTouchedByTagNum = this.mSidesTouchedByTagNum;
                final int mTagNum_FloodFillGlobal = this.mTagNum_FloodFillGlobal;
                mSidesTouchedByTagNum[mTagNum_FloodFillGlobal] |= 0x2;
            }
            else if (n == 5 && tube.CheckDirection(2)) {
                this.mVisitedWalls_FloodFillGlobal |= 0x1;
                this.mRightSideColumnTouched[n2] = true;
                final int[] mSidesTouchedByTagNum2 = this.mSidesTouchedByTagNum;
                final int mTagNum_FloodFillGlobal2 = this.mTagNum_FloodFillGlobal;
                mSidesTouchedByTagNum2[mTagNum_FloodFillGlobal2] |= 0x1;
            }
            int n3 = 0;
            do {
                if (tube.CheckDirection(Tube.gDirectionOffsets[n3][2])) {
                    final int n4 = n2 + Tube.gDirectionOffsets[n3][0];
                    final int n5 = n + Tube.gDirectionOffsets[n3][1];
                    if (n5 < 0 || n5 >= 6 || n4 < 0 || n4 >= 9 || this.mBoard[n4][n5] == null || !this.mBoard[n4][n5].IsStart(Tube.gDirectionOffsets[n3][3])) {
                        continue;
                    }
                    this.MarkPipes(n5, n4);
                }
            } while (++n3 < 4);
        }
    }
    
    void NextLevel() {
        ++this.mLevel;
        this.mState = 0;
        if (this.mButton != null) {
            super.mWidgetManager.RemoveWidget(this.mButton);
            this.mButton = null;
        }
        if (this.mNoButton != null) {
            super.mWidgetManager.RemoveWidget(this.mNoButton);
            this.mNoButton = null;
        }
        this.mStats.mLevelStats.Reset();
        this.mLevelIntroDelay = 1;
        this.SetBoardVars();
        this.SetupBoard(false, false);
    }
    
    Tube FindTubeAt(final int n, final int n2) {
        int n3 = 0;
        do {
            int n4 = 0;
            do {
                final Tube tube = this.mBoard[n3][n4];
                if (tube != null && n >= tube.mX && n2 >= tube.mY && n < tube.mX + 34.0 && n2 < tube.mY + 34.0) {
                    return tube;
                }
            } while (++n4 < 6);
        } while (++n3 < 9);
        return null;
    }
    
    void drawDisplayString(final SexyGraphics sexyGraphics) {
        String s = "";
        int n = -1;
        if (this.mShowingLevelIntro && (this.mApplet.mBoardsCreated != 1 || this.mLevel != 0)) {
            n = 38;
        }
        else if (this.mShowingLevelUp) {
            n = 36;
            s = "Level Up!";
        }
        else if (this.mState == 4) {
            n = 37;
            s = "Game Over!";
        }
        else if (this.mComboDisplayTimer > 0) {
            n = 35;
            s = "Combo!";
        }
        if (n > 0 && this.mApplet.mRes.IsImageReady(n)) {
            final SexyImage getImage = this.mApplet.mRes.GetImage(n);
            sexyGraphics.DrawImage(getImage, GetColX(3) - getImage.GetWidth() / 2, 120 + (int)(10.0 * Math.sin(this.mUpdateCnt / 10.0)));
            return;
        }
        if (s.length() > 0) {
            sexyGraphics.SetColor(Color.orange);
            sexyGraphics.SetFont(this.mApplet.mRes.mFont20Outlined);
            sexyGraphics.DrawString(s, GetColX(3) - sexyGraphics.GetFont().StringWidth(s) / 2, 120 + (int)(10.0 * Math.sin(this.mUpdateCnt / 10.0)));
        }
    }
    
    public void ButtonPress(final int n) {
    }
    
    void DisplayTileScore_Clear() {
        this.AddPointsFloater(this.AddScore(this.mDisplayTileCount * 5), GetColX(0) - 40, GetRowY(this.mDisplayTileScoreRow) + 10);
        this.DisplayTileScore_Reset();
    }
    
    private void UpdatePipes() {
        this.ResetFloodFill();
        this.DoSideWallCheckForPipes(0, 8, true);
        this.DoSideWallCheckForPipes(5, 2, true);
    }
    
    boolean HasHitLevelLimit() {
        return this.mApplet.mLevelLimited && this.mApplet.mBoard.mLevel >= this.mApplet.mLastLevel - 1;
    }
    
    int GetTimerBarY() {
        return 359;
    }
    
    public void MouseLeave() {
        super.MouseLeave();
    }
    
    private void drawSidebar(final SexyGraphics sexyGraphics) {
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[25], 0, 0);
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[15], 34, 159);
        sexyGraphics.SetFont(this.mApplet.mRes.mFont16);
        final String string = "" + this.mDispPoints;
        sexyGraphics.SetColor(new Color(192, 0, 0));
        sexyGraphics.DrawString(string, 63 - sexyGraphics.GetFont().StringWidth(string) / 2, 88 - sexyGraphics.GetFont().mHeight / 2 + 18);
        sexyGraphics.SetFont(this.mApplet.mRes.mFont12);
        final String string2 = "Level " + (this.mLevel + 1);
        sexyGraphics.SetColor(Color.orange);
        sexyGraphics.DrawString(string2, 63 - sexyGraphics.GetFont().StringWidth(string2) / 2, 111 - sexyGraphics.GetFont().mHeight / 2 + 1 + 14);
        final int max = Math.max(0, this.mRocketsNeededToLevel - this.mStats.mRocketsLaunchedThisLevel);
        sexyGraphics.SetFont(this.mApplet.mRes.mFont14);
        final String string3 = "" + max;
        sexyGraphics.SetColor(Color.orange);
        sexyGraphics.DrawString(string3, 43 - sexyGraphics.GetFont().StringWidth(string3), 148 - sexyGraphics.GetFont().mHeight / 2 + 2 + 15);
        sexyGraphics.SetFont(this.mApplet.mRes.mFont14);
        final String string4 = "" + this.mCoins;
        sexyGraphics.SetColor(Color.orange);
        sexyGraphics.DrawString(string4, 39 - sexyGraphics.GetFont().StringWidth(string4), 177 - sexyGraphics.GetFont().mHeight / 2 + 1 + 15);
    }
    
    int DestroyLinkedRockets(final int n) {
        int n2 = 0;
        int n3 = 0;
        do {
            final Tube tube = this.mBoard[n3][5];
            if (tube != null && tube.mAction == 2 && tube.mBurningTag == n && this.mRockets[n3] != null && tube.CheckDirection(2)) {
                this.AddPointsFloater(this.AddScore(this.mRockets[n3].GetScore()), (int)this.mRockets[n3].mX + 10, (int)this.mRockets[n3].mY + 60);
                this.mRockets[n3].Launch(this);
                ++n2;
                this.mRockets[n3].BonusPotentialMeasure(this.mStats);
                final Stats mStats = this.mStats;
                ++mStats.mRocketsLaunchedThisLevel;
                this.NewRocket(n3, this.mRockets[n3].mSize);
            }
        } while (++n3 < 9);
        if (n2 > 0) {
            this.mApplet.PlaySound(6);
            this.mStats.CheckMostRockets(n2);
            double n4 = 1.5;
            for (int i = 0; i < 1 + n2; ++i) {
                this.mDelayedSounds.Add(3, n4);
                n4 += 0.2 + 0.1 * (this.mRand.Next() % 5);
            }
            if (n2 > 1) {
                this.mDelayedSounds.Add(((this.mRand.Next() & 0x1) == 0x0) ? 15 : 14, 1.0);
            }
        }
        return n2;
    }
    
    void NewCoin(final int n) {
        boolean b = false;
        Tube tube = null;
        int n2 = this.mRand.Next() % 6;
        int n3 = this.mRand.Next() % 4;
        for (int n4 = 0; n4 < 6 && !b; ++n4) {
            int n5 = 0;
            do {
                tube = this.mBoard[n3][n2];
                if (tube != null && tube.mPickup == null && tube.mAction != 2) {
                    b = true;
                    break;
                }
                n3 = (n3 + 1) % 9;
            } while (++n5 < 9);
            n2 = (n3 + 1) % 6;
        }
        if (b) {
            this.AddPieceToActiveList(tube.mPickup = new Pickup(this, 3, tube, n));
        }
    }
    
    static int GetRowY(final int n) {
        return 33 + n * 34;
    }
    
    int GetTimerBarX() {
        return 132 + (int)(342.0 * this.mLevelTimer / this.mLevelTimerStart);
    }
    
    public void ButtonMouseLeave(final int n) {
    }
    
    public void KeyDown(final int n, final boolean b, final boolean b2) {
        switch (n) {
            case 32: {
                this.TogglePause();
                break;
            }
        }
        if (this.mApplet.mCheats) {
            if (n >= 48 && n <= 57) {
                Fireworks.AddFirework(this.mApplet, this.mParticleSystem, this.mParticleDatabase, n - 48, 0);
            }
            switch (n) {
                case 114: {
                    int n2 = 0;
                    do {
                        this.mProgression.UpgradeRocketAtRow(n2);
                    } while (++n2 < 9);
                }
                case 115: {
                    this.AddScreenShake(this.mRand.Next() % 10);
                }
                case 101: {
                    this.mStats.mRocketsLaunchedThisLevel = this.mRocketsNeededToLevel;
                }
                case 111: {
                    this.mLevelTimer = 15.0;
                }
                case 118: {
                    this.mApplet.mFloatingHead.PlaySequence(5 + this.mRand.Next() % 4);
                }
                case 102: {
                    Fireworks.AddFirework(this.mApplet, this.mParticleSystem, this.mParticleDatabase, this.mRand.Next() % 10, 0);
                }
                case 97:
                case 99:
                case 103:
                case 116: {
                    final Tube tube = this.mBoard[this.mRand.Next() % 9][this.mRand.Next() % 6];
                    if (tube != null && !tube.mBurnt && tube.mPickup == null) {
                        int n3 = 1;
                        int n4 = 0;
                        switch (n) {
                            case 99: {
                                n3 = 3;
                                n4 = this.mRand.Next() % 4;
                                break;
                            }
                            case 116: {
                                n3 = 2;
                                break;
                            }
                            case 103: {
                                n3 = 0;
                                n4 = this.mRand.Next() % 5;
                                break;
                            }
                            case 97: {
                                n3 = 1;
                                break;
                            }
                        }
                        this.AddPieceToActiveList(tube.mPickup = new Pickup(this, n3, tube, n4));
                        return;
                    }
                    break;
                }
            }
        }
    }
    
    private void drawBackground(final SexyGraphics sexyGraphics) {
        this.mBufferedBackground.Render(this.mApplet, sexyGraphics, this.mLevelTimer);
        final int n = 370 - this.mApplet.mRes.mImages[24].mHeight;
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[24], 128, n);
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[23], 128, n - this.mApplet.mRes.mImages[23].mHeight);
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        if (this.mApplet.mAd == null) {
            if (this.mPauseCount == 0 && this.mScreenShakeTimer > 0.0 && Board.skScreenShakeInState[this.mState]) {
                this.Resize(this.mScreenShakeAmplitude - this.mRand.Next() % (2 * this.mScreenShakeAmplitude), 0, super.mWidth, super.mHeight);
            }
            else {
                this.Resize(0, 0, super.mWidth, super.mHeight);
            }
            this.drawBackground(sexyGraphics);
            final SexyGraphics sexyGraphics2 = new SexyGraphics(sexyGraphics);
            sexyGraphics2.ClipRect(120, 0, 360, 370);
            this.mParticleSystem.Render(sexyGraphics2, 0);
            this.drawSidebar(sexyGraphics);
            if (this._inPlayDraw()) {
                this.drawInPlayStuff(sexyGraphics);
            }
            if (this.mPauseCount > 0) {
                if (this.mApplet.mDialogWidget == null && this.mApplet.mLevelUpScreen == null && this.mApplet.mBuyMe == null) {
                    sexyGraphics.SetColor(Color.orange);
                    final SpacedFont spacedFont = new SpacedFont(sexyGraphics, this.mApplet.mRes.mFont18Outlined, -2);
                    if (this.mApplet.mRes.IsImageReady(57)) {
                        final SexyImage getImage = this.mApplet.mRes.GetImage(57);
                        sexyGraphics.DrawImage(getImage, GetColX(3) - getImage.GetWidth() / 2, 110 - getImage.GetHeight());
                    }
                    else {
                        spacedFont.DrawString("** Paused **", GetColX(3), 110);
                    }
                    spacedFont.DrawString("Click to Continue!", GetColX(3), 150);
                }
            }
            else {
                if (this.mState == 5 || this.mState == 1) {
                    this._drawStats(sexyGraphics);
                    return;
                }
                this.drawDisplayString(sexyGraphics);
            }
        }
    }
    
    public Board(final RocketManiaApplet mApplet) {
        super(mApplet.mWidgetManager);
        this.mBoard = new Tube[9][6];
        this.mNextTubes = new Tube[6];
        this.mRockets = new Rocket[9];
        this.mPointMult = 1.0;
        this.mPointsVector = new Vector();
        this.mActiveObjects = new Vector();
        this.mSpeedRating = 1;
        this.mHelperPositions = new Vector();
        this.mAskingUploadScore = false;
        this.mSidesTouchedByTagNum = new int[30];
        this.mLeftSideColumnTouched = new boolean[9];
        this.mRightSideColumnTouched = new boolean[9];
        this.mMatchFrames = new int[9];
        this.aSkillLevel = mApplet.mSkillLevel;
        this.mRand = new MTRand((int)(Math.random() * 65535.0));
        this.mTotalGameTime = 0;
        this.mApplet = mApplet;
        this.mLevelIntroDelay = 1;
        this.mStats = new Stats();
        this.mProgression = new Progression(mApplet, this, this.mRand, this.mStats);
        this.mInGlowUpSequence = false;
        this.mGlowUpSequenceTag = -1;
        this.mGlowUpSequenceTimer = 0;
        this.mNumTravellingSparks = 0;
        this.mCarriedOverCoinDropDelay = 0;
        this.mFlameCounter = 0;
        this.mFlameFrame = 0;
        this.mParticleSystem = new ParticleSystem(50);
        this.mParticleDatabase = new ParticleDatabase(this.mApplet.mRes);
        this.mBufferedBackground = new BufferedBackground(this.mApplet);
        this.mApplet.mHints.Queue(0, 1.0E-5f);
        this.SetBoardVars();
        this.SetupBoard(true, this.mApplet.mBoardsCreated == 0);
    }
    
    void DisplayTileScore_DoDraw(final SexyGraphics sexyGraphics, final int n, final int n2, final int n3) {
        Assert.assert(n3 >= 0 && n3 < 256);
        if (n3 > 0) {
            sexyGraphics.SetColor(new SexyColor(255, 192, 0, n3));
            sexyGraphics.SetFont(this.mApplet.mRes.mFont14Outlined);
            final String string = "" + this.ScaleScore(n * 5);
            sexyGraphics.DrawString(string, GetColX(0) - 40 - sexyGraphics.GetFont().StringWidth(string) / 2, GetRowY(n2) + 10);
        }
    }
    
    public void SetUnpause() {
        if (this.mPauseCount > 0 && --this.mPauseCount == 0) {
            final Enumeration<Points> elements = this.mPointsVector.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().SetVisible(true);
            }
            if (this.mButton != null) {
                this.mButton.SetVisible(true);
            }
            this.MarkDirty();
        }
    }
    
    public void TogglePause() {
        if (this.mPauseCount > 0) {
            this.SetUnpause();
            return;
        }
        this.SetPause();
    }
    
    void AdvanceToNextLevel() {
        if (this.HasHitLevelLimit()) {
            this.NextLevel();
            this.mApplet.ShowBuyMe();
            return;
        }
        this.mApplet.ShowLevelUpScreen(false);
        this.NextLevel();
        this.mCarriedOverCoinDropDelay = 100;
    }
    
    static {
        mButtonColors = new int[][] { new int[3], new int[3], new int[3], new int[3], { 255, 128, 0 }, { 255, 255, 0 }, { 255, 255, 255 } };
        skScreenShakeInState = new boolean[] { true, false, true, false, true, false, true };
    }
    
    public void ButtonDepress(final int n) {
        this.mLastLevelUpRank = Ranks.GetName(this.mPoints);
        if (this.mState == 5) {
            this.SendGameBreak(true);
            this.mApplet.NewGame();
        }
        else {
            this.SendGameBreak(false);
            this.AdvanceToNextLevel();
        }
        if (this.mApplet.mNextShowAd) {
            if (Math.random() < this.mApplet.mAdFrequency && (this.mState == 5 || !this.HasHitLevelLimit())) {
                this.mApplet.ShowAd();
            }
            this.mApplet.mNextShowAd = false;
        }
    }
    
    void StartTravellingSparks() {
        final boolean[] array = new boolean[9];
        Assert.assert(this.mCoinsPickedUpDuringTravellingSpark == 0);
        Assert.assert(this.mGemsPickedUpDuringTravellingSpark == 0);
        this.mCoinsPickedUpDuringTravellingSpark = 0;
        this.mGemsPickedUpDuringTravellingSpark = 0;
        int n = 0;
        this.DisplayTileScore_Reset();
        int n2 = 0;
        do {
            final Tube tube = this.mBoard[n2][0];
            if (tube != null && tube.mAction == 2 && tube.CheckDirection(8)) {
                this.AddPieceToActiveList(new TravellingSpark(this, n2, -1, n2, 0, 1));
                array[n2] = true;
                ++n;
            }
            else {
                array[n2] = false;
            }
        } while (++n2 < 9);
        this.mApplet.PlaySound(5);
        int n3 = 0;
        int mDisplayTileScoreRow = 0;
        while (!array[mDisplayTileScoreRow] || ++n3 < n / 2) {
            if (++mDisplayTileScoreRow >= 9) {
                return;
            }
        }
        this.mDisplayTileScoreRow = mDisplayTileScoreRow;
    }
    
    boolean DoSideWallCheckForPipes(final int n, final int n2, final boolean b) {
        boolean b2 = false;
        int n3 = 0;
        do {
            b2 |= this.PipeCheck(n3, n, n2, b);
        } while (++n3 < 9);
        return b2;
    }
    
    public void ProcessClick(final int n, final int n2, final int n3) {
        final Tube findTube = this.FindTubeAt(n, n2);
        if (findTube != null && (findTube.mAction != 2 || this.mNumTravellingSparks == 0)) {
            if (n3 > 0) {
                findTube.mOrientation = (findTube.mOrientation + 3) % 4;
                findTube.mDrawRotatedAngle = -1.5707963267948966;
            }
            else {
                findTube.mOrientation = (findTube.mOrientation + 1) % 4;
                findTube.mDrawRotatedAngle = 1.5707963267948966;
            }
            findTube.mDrawRotatedUpdateCount = this.mUpdateCnt;
            this.mApplet.PlaySound(1);
            this.mMadeFirstMove = true;
            this.MarkDirty();
            if (findTube.mAction == 2) {
                Assert.assert(findTube.mBurningTag != 0);
                this.UntagPieces(findTube.mBurningTag);
            }
        }
    }
    
    private boolean PipeCheck(final int n, final int n2, final int n3, final boolean b) {
        final Tube tube = this.mBoard[n][n2];
        boolean b2 = false;
        int n4 = 0;
        do {
            this.mLeftSideColumnTouched[n4] = false;
            this.mRightSideColumnTouched[n4] = false;
        } while (++n4 < 9);
        if (tube != null && tube.IsStart(n3)) {
            this.mVisitedWalls_FloodFillGlobal = 0;
            this.MarkPipes(n2, n);
            if ((this.mVisitedWalls_FloodFillGlobal & 0x3) == 0x3) {
                if (b && this.SidePiecesLinked() && !this.mInGlowUpSequence) {
                    this.StartGlowUpSequence(this.mTagNum_FloodFillGlobal);
                }
                b2 = true;
            }
        }
        ++this.mTagNum_FloodFillGlobal;
        return b2;
    }
    
    void EndGlowUpSequence() {
        if (this.mApplet.mShowAds) {
            this.mApplet.mNextShowAd = true;
        }
        this.mMustCheckForBoardInRest = true;
        Assert.assert(this.mNumTravellingSparks == 0);
        Assert.assert(this.mInGlowUpSequence);
        Assert.assert(this.mGlowUpSequenceTag != -1);
        final int destroyLinkedRockets = this.DestroyLinkedRockets(this.mGlowUpSequenceTag);
        this.AddScreenShake(destroyLinkedRockets);
        this.BlowUpBlocksWithTagNum(this.mGlowUpSequenceTag);
        this.mProgression.BonusPotentialDetermine(destroyLinkedRockets);
        this.DropBoardPieces();
        this.mGlowUpSequenceTag = -1;
        this.mInGlowUpSequence = false;
        if (this.mStats.mRocketsLaunchedThisLevel >= 3 && this.mStats.mRocketsLaunchedThisLevel < this.mRocketsNeededToLevel) {
            this.mApplet.mHints.Queue(1, 2.6f);
        }
        if (this.mStats.mRocketsLaunchedThisLevel >= this.mRocketsNeededToLevel) {
            if (this.mState != 2 && this.mState != 1 && this.mState != 5) {
                this.mState = 2;
                this.mPreLevelTransitionDelay = 100;
                this.mApplet.mFloatingHead.PlaySequence(6);
                this.mShowingLevelUp = true;
            }
        }
        else {
            this.mApplet.mFloatingHead.PlaySequence(2);
            if (destroyLinkedRockets > 3) {
                this.mDelayToWellDone = 2.0;
            }
        }
        this.mCoinsPickedUpDuringTravellingSpark = 0;
        this.mGemsPickedUpDuringTravellingSpark = 0;
        this.DisplayTileScore_Clear();
    }
    
    void DropBoardPieces() {
        int n = 0;
        do {
            boolean b = false;
            int n2 = 8;
            do {
                final Tube tube = this.mBoard[n2][n];
                if (tube == null) {
                    b = true;
                }
                else {
                    if (!b) {
                        continue;
                    }
                    tube.Drop(this);
                }
            } while (--n2 >= 0);
        } while (++n < 6);
    }
    
    void DisplayTileScore_Display(final SexyGraphics sexyGraphics) {
        if (this.mDisplayTileScoreRow != -1 && this.mDisplayTileCount != 0) {
            this.DisplayTileScore_DoDraw(sexyGraphics, this.mDisplayTileCount, this.mDisplayTileScoreRow, 255);
        }
    }
    
    void SetBoardVars() {
        this.mProgression.SetBoardVars();
        this.mStats.mRocketsLaunchedThisLevel = 0;
        this.mLevelTimer = this.mLevelTimerStart;
        this.mFreezeTimeTimer = 0.0;
        this.mCockerelHasCrowed = false;
        this.mScreenShakeAmplitude = 0;
        this.mScreenShakeTimer = 0.0;
        this.mBufferedBackground.SetStageDuration(this.mApplet, this.mLevelTimerStart);
        this.mProgression.PieceFrequenciesRecalculate(this.mLevel, this.mApplet.mSkillLevel);
        this.DisplayTileScore_Reset();
    }
    
    private void StartGlowUpSequence(final int mGlowUpSequenceTag) {
        Assert.assert(!this.mInGlowUpSequence);
        Assert.assert(this.mGlowUpSequenceTag == -1);
        this.mInGlowUpSequence = true;
        this.mGlowUpSequenceTag = mGlowUpSequenceTag;
        this.mGlowUpSequenceTimer = 33;
        int n = 8;
        do {
            int n2 = 0;
            do {
                final Tube tube = this.mBoard[n][n2];
                if (tube != null && tube.mAction == 0 && tube.mBurningTag == mGlowUpSequenceTag) {
                    tube.mAction = 2;
                }
            } while (++n2 < 6);
        } while (--n >= 0);
        if (this.mMustCheckForCombo || this.mMustCheckForBoardInRest) {
            this.mComboDisplayTimer = 125;
            this.mApplet.PlaySound(3);
            this.AddScore(2000);
        }
    }
}
