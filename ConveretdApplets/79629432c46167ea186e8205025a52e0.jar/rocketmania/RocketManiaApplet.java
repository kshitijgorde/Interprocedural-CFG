// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import nc.Assert;
import sexy.util.AsyncJSCall;
import java.awt.image.RGBImageFilter;
import sexy.gui.Widget;
import java.util.Random;
import sexy.gui.ButtonWidget;
import sexy.res.SoundThread;
import sexy.gui.PopcapAnim;
import sexy.gui.OutlineDialogWidget;
import sexy.gui.ButtonListener;
import sexy.gui.SexyApplet;

public class RocketManiaApplet extends SexyApplet implements ButtonListener
{
    static final int BUTTON_NEW_GAME = 0;
    static final int BUTTON_TOGGLE_SOUND = 1;
    static final int BUTTON_SKILL_ONE = 10;
    static final int BUTTON_SKILL_TWO = 11;
    static final int BUTTON_SKILL_THREE = 12;
    static final int BUTTON_YES_FROM_DIALOG = 20;
    static final int BUTTON_NO_FROM_DIALOG = 21;
    static final int BUTTON_HELP = 50;
    static final int BUTTON_PAUSE = 51;
    static final int BUTTON_NO_MORE_HINTS = 100;
    static final int STATE_GAME_SELECTOR = 0;
    static final int STATE_PLAYING = 1;
    static final int DIALOG_NEW_GAME = 0;
    static final int DIALOG_SKILL_CHANGE = 1;
    static final int DIALOG_HINT = 2;
    static final int DIALOG_HELP = 3;
    static final int GAMETYPE_DMLIKE = 0;
    static final int NUMGAMETYPES = 1;
    int mBoardsCreated;
    Res mRes;
    boolean mLoaded;
    int mState;
    OutlineDialogWidget mDialogWidget;
    int mDialogType;
    PopcapAnim mPopcapAnim;
    Board mBoard;
    SoundThread mSoundThread;
    boolean mSoundOn;
    ButtonWidget[] mSkillButton;
    ButtonWidget mSoundButton;
    ButtonWidget mHelpButton;
    ButtonWidget mNewGameButton;
    ButtonWidget mPauseButton;
    int mSkillLevel;
    int mGameType;
    int mNextSkillLevel;
    LevelUp mLevelUpScreen;
    boolean mRecordingEnabled;
    boolean mGotABag;
    Random mStartRandom;
    boolean mNextShowAd;
    boolean mReading;
    boolean mScoreUpload;
    boolean mShowedInstructions;
    boolean mShowAds;
    boolean mCheats;
    Advertisement mAd;
    BuyMe mBuyMe;
    Hints mHints;
    FloatingHead mFloatingHead;
    boolean mLevelLimited;
    int mLastLevel;
    double mAdFrequency;
    static final String HELP_TEXT = "<DialogText><BR/><Color Value=FFFFFF><Font Name=SansSerif Style=Bold Size=18><Center><Shadow>How To Play</Shadow></Center></Font><Shadow><Font Name=SansSerif Style=Bold Size=9><Color Value=FCAE19>Click the&nbsp;<Color Value=FFEB1E>tiles</Color>&nbsp;to rotate them. This way<BR/>you can create&nbsp;<Color Value=FFEB1E>fuses</Color>&nbsp;to light the rockets.<BR/><BR/>Watch the&nbsp;<Color Value=FFEB1E>timer</Color>&nbsp;at the bottom of the screen.<BR/>When time runs out the game is over!<BR/><BR/>The number of&nbsp;<Color Value=FFEB1E>rockets</Color>&nbsp;you need to launch<BR/>is shown above the dragon, as well as<BR/>the number of&nbsp;<Color Value=FFEB1E>coins</Color>&nbsp;you have collected.<BR/><BR/><Color Value=FFEB1E>Fire two or more rockets at the same time</Color><BR/>to receive coins. Five or more coins will<BR/><Color Value=FFEB1E>upgrade your rockets</Color>&nbsp;at the end of the level.<BR/><BR/>Upgraded rockets have&nbsp;<Color Value=FFEB1E>new fireworks<BR/>effects, and earn more points!</Color><BR/></Color></Font></Shadow></DialogText>";
    
    void DoDialog(final String s, final String s2, final String s3, final int n) {
        this.KillDialog();
        (this.mDialogWidget = new OutlineDialogWidget(super.mWidgetManager, this, s, s2, s3, n)).Resize(175, 80, 250, this.mDialogWidget.GetPreferredHeight(250));
        this.mDialogWidget.SetColors(HintDialog.mDialogColors);
        this.mDialogWidget.SetButtonColors(HintDialog.mContinueButtonColors);
        this.mDialogWidget.mDropShadowSize = 8;
        super.mWidgetManager.AddWidget(this.mDialogWidget);
        super.mWidgetManager.SetBaseModal(this.mDialogWidget);
        if (this.mBoard != null) {
            this.mBoard.SetPause();
        }
    }
    
    public void Initialize() {
        System.out.println("Build #" + 101 + " (" + "Wed Aug 20 13:32:00 2003" + ")");
        super.mVersion = 101;
        super.mProdName = "tubetricks";
        super.mBuildDate = "Wed Aug 20 13:32:00 2003";
        if (!this.CheckHost()) {
            return;
        }
        this.mShowAds = this.GetParamBoolean("ShowAds", true);
        this.mCheats = this.GetParamBoolean("cheats", false);
        this.mScoreUpload = this.GetParamBoolean("ScoreUpload", false);
        final String parameter = this.getParameter("resbase");
        this.mRes = new Res(this);
        if (parameter != null) {
            this.mRes.SetResourceBase(parameter);
        }
        this.mLevelLimited = this.GetParamBoolean("LevelLimited", true);
        this.mLastLevel = this.GetParamInteger("LastLevel", 10);
        this.mAdFrequency = this.GetParamDouble("AdFrequency", 0.75);
        super.mResLoader = this.mRes;
        this.mSoundThread = new SoundThread();
        super.Initialize();
        this.mPopcapAnim = new PopcapAnim(super.mWidgetManager, "Rocket Mania");
        super.mWidgetManager.AddWidget(this.mPopcapAnim);
        this.ResizeComponents();
        this.mBoardsCreated = 0;
    }
    
    public void Shutdown() {
        if (this.mBoard != null) {
            super.mWidgetManager.RemoveWidget(this.mBoard);
            this.mBoard = null;
        }
        if (this.mSoundThread != null) {
            this.mSoundThread.Shutdown();
        }
        super.Shutdown();
    }
    
    void KillDialog() {
        if (this.mDialogWidget != null) {
            super.mWidgetManager.RemoveWidget(this.mDialogWidget);
            this.mDialogWidget = null;
            if (this.mBoard != null) {
                this.mBoard.SetUnpause();
            }
        }
    }
    
    public ButtonWidget AddButtonNoImage(final int n, final int n2, final int n3, final int n4, final int n5) {
        final ButtonWidget buttonWidget = new ButtonWidget(super.mWidgetManager, n, this);
        buttonWidget.Resize(n2, n3, n4, n5);
        buttonWidget.mDoFinger = true;
        super.mWidgetManager.AddWidget(buttonWidget);
        return buttonWidget;
    }
    
    void KillLevelUpScreen() {
        if (this.mLevelUpScreen != null) {
            if (this.mBoard != null) {
                this.mBoard.SetUnpause();
            }
            super.mWidgetManager.RemoveWidget(this.mLevelUpScreen);
            this.mLevelUpScreen = null;
        }
    }
    
    void SetSkillLevel(final int mSkillLevel) {
        this.mSkillLevel = mSkillLevel;
        int n = 0;
        do {
            this.mSkillButton[n].mInverted = (this.mSkillLevel == n);
            this.mSkillButton[n].mDoFinger = (this.mSkillLevel != n);
            this.mSkillButton[n].MarkDirty();
        } while (++n < 3);
        this.NewGame();
    }
    
    private void showWidget(final Widget widget) {
        if (widget != null) {
            widget.SetVisible(true);
        }
    }
    
    public void ButtonPress(final int n) {
        if (n != 2) {
            this.PlaySound(9);
        }
    }
    
    public synchronized void UpdateFrames() {
        super.UpdateFrames();
        if (!this.mRes.mStarted && this.mPopcapAnim.IsLoaded()) {
            this.mRes.Start();
        }
        if (!this.mLoaded && this.mRes.mLoaded && this.mPopcapAnim.IsDone()) {
            this.ResourcesLoaded();
        }
    }
    
    public void PlaySound(final int n) {
        if (!this.mSoundOn) {
            return;
        }
        this.mSoundThread.PushCommand(this.mRes.GetSound(n), 0);
    }
    
    public double GetParamDouble(final String s, final double n) {
        double doubleValue = n;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                doubleValue = new Double(parameter);
            }
            catch (Exception ex) {}
        }
        return doubleValue;
    }
    
    public int GetParamInteger(final String s, final int n) {
        int intValue = n;
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            try {
                intValue = new Integer(parameter);
            }
            catch (Exception ex) {}
        }
        return intValue;
    }
    
    public void NewGame() {
        if (this.mBoard != null) {
            super.mWidgetManager.RemoveWidget(this.mBoard);
        }
        this.KillLevelUpScreen();
        super.mNeedSendGameStart = true;
        this.mState = 1;
        this.mBoard = new Board(this);
        ++this.mBoardsCreated;
        this.mBoard.Resize(0, 0, super.mWidth, super.mHeight);
        super.mWidgetManager.AddWidget(this.mBoard);
        super.mWidgetManager.BringToBack(this.mBoard);
        super.mWidgetManager.SetFocus(this.mBoard);
    }
    
    void DoFancyDialog(final String s, final String s2, final String s3, final int n) {
        this.KillDialog();
        (this.mDialogWidget = new FancyDialog(super.mWidgetManager, this, s, s2, s3, n)).SetColors(HintDialog.mDialogColors);
        this.mDialogWidget.SetButtonColors(HintDialog.mContinueButtonColors);
        this.mDialogWidget.mDropShadowSize = 8;
        this.mDialogWidget.Resize(146, 60, 250, this.mDialogWidget.GetPreferredHeight(250));
        super.mWidgetManager.AddWidget(this.mDialogWidget);
        super.mWidgetManager.SetBaseModal(this.mDialogWidget);
        if (this.mBoard != null) {
            this.mBoard.SetPause();
        }
    }
    
    public void ButtonDownTick(final int n) {
    }
    
    public String GetScoreHook() {
        if (this.mBoard != null) {
            return "" + this.mBoard.mPoints;
        }
        return null;
    }
    
    void DoHintDialog(final String s, final String s2) {
        this.KillDialog();
        final HintDialog mDialogWidget = new HintDialog(super.mWidgetManager, this, s, s2);
        mDialogWidget.Resize(175, 80, 250, mDialogWidget.GetPreferredHeight(250));
        final int mLastMouseX = super.mWidgetManager.mLastMouseX;
        final int mLastMouseY = super.mWidgetManager.mLastMouseY;
        if (mDialogWidget.mYesButton.Contains(mLastMouseX, mLastMouseY) || mDialogWidget.mNoMoreHintsButton.Contains(mLastMouseX, mLastMouseY)) {
            mDialogWidget.Move(mDialogWidget.mX, mDialogWidget.mY + 64);
        }
        this.mDialogWidget = mDialogWidget;
        super.mWidgetManager.AddWidget(this.mDialogWidget);
        super.mWidgetManager.SetBaseModal(this.mDialogWidget);
        if (this.mBoard != null) {
            this.mBoard.SetPause();
        }
        this.mDialogType = 2;
    }
    
    void DoHelpDialog() {
        this.DoFancyDialog(null, "<DialogText><BR/><Color Value=FFFFFF><Font Name=SansSerif Style=Bold Size=18><Center><Shadow>How To Play</Shadow></Center></Font><Shadow><Font Name=SansSerif Style=Bold Size=9><Color Value=FCAE19>Click the&nbsp;<Color Value=FFEB1E>tiles</Color>&nbsp;to rotate them. This way<BR/>you can create&nbsp;<Color Value=FFEB1E>fuses</Color>&nbsp;to light the rockets.<BR/><BR/>Watch the&nbsp;<Color Value=FFEB1E>timer</Color>&nbsp;at the bottom of the screen.<BR/>When time runs out the game is over!<BR/><BR/>The number of&nbsp;<Color Value=FFEB1E>rockets</Color>&nbsp;you need to launch<BR/>is shown above the dragon, as well as<BR/>the number of&nbsp;<Color Value=FFEB1E>coins</Color>&nbsp;you have collected.<BR/><BR/><Color Value=FFEB1E>Fire two or more rockets at the same time</Color><BR/>to receive coins. Five or more coins will<BR/><Color Value=FFEB1E>upgrade your rockets</Color>&nbsp;at the end of the level.<BR/><BR/>Upgraded rockets have&nbsp;<Color Value=FFEB1E>new fireworks<BR/>effects, and earn more points!</Color><BR/></Color></Font></Shadow></DialogText>", "Click here to continue", 2);
        this.mDialogWidget.mDropShadowSize = 0;
        this.mDialogWidget.Resize(133, 5, super.mWidth - 128 - 10, super.mHeight - 10);
        this.mDialogWidget.mLinesFont = this.mRes.mLineFontSmall;
        final int n = this.mRes.mLineFontSmall.mHeight - 16;
        if (n > 0) {
            ((FancyDialog)this.mDialogWidget).mLayout.PadLine(-n);
        }
        ((FancyDialog)this.mDialogWidget).mLayout.RightJustify(14);
        ((FancyDialog)this.mDialogWidget).mLayout.LeftJustify(14);
        ((FancyDialog)this.mDialogWidget).SetFancyText("<DialogText><BR/><Color Value=FFFFFF><Font Name=SansSerif Style=Bold Size=18><Center><Shadow>How To Play</Shadow></Center></Font><Shadow><Font Name=SansSerif Style=Bold Size=9><Color Value=FCAE19>Click the&nbsp;<Color Value=FFEB1E>tiles</Color>&nbsp;to rotate them. This way<BR/>you can create&nbsp;<Color Value=FFEB1E>fuses</Color>&nbsp;to light the rockets.<BR/><BR/>Watch the&nbsp;<Color Value=FFEB1E>timer</Color>&nbsp;at the bottom of the screen.<BR/>When time runs out the game is over!<BR/><BR/>The number of&nbsp;<Color Value=FFEB1E>rockets</Color>&nbsp;you need to launch<BR/>is shown above the dragon, as well as<BR/>the number of&nbsp;<Color Value=FFEB1E>coins</Color>&nbsp;you have collected.<BR/><BR/><Color Value=FFEB1E>Fire two or more rockets at the same time</Color><BR/>to receive coins. Five or more coins will<BR/><Color Value=FFEB1E>upgrade your rockets</Color>&nbsp;at the end of the level.<BR/><BR/>Upgraded rockets have&nbsp;<Color Value=FFEB1E>new fireworks<BR/>effects, and earn more points!</Color><BR/></Color></Font></Shadow></DialogText>", 0, 0);
        this.mDialogType = 2;
    }
    
    public void ButtonMouseLeave(final int n) {
    }
    
    private void _toggleSoundButtonImages(final boolean b) {
        this.mSoundButton.mButtonImage = this.mRes.CopyAndFilter(this.mRes.mImages[34], 84 * (b ? 0 : 1), 0, 28, 23, null);
        this.mSoundButton.mDownImage = this.mRes.CopyAndFilter(this.mRes.mImages[34], 56 + 84 * (b ? 0 : 1), 0, 28, 23, null);
        this.mSoundButton.mOverImage = this.mRes.CopyAndFilter(this.mRes.mImages[34], 28 + 84 * (b ? 0 : 1), 0, 28, 23, null);
    }
    
    public void CloseBuyMe() {
        if (this.mBuyMe != null) {
            super.mWidgetManager.RemoveWidget(this.mBuyMe);
            this.mBuyMe = null;
            this.showWidget(this.mHelpButton);
            this.showWidget(this.mPauseButton);
        }
    }
    
    public void CloseAd() {
        if (this.mAd != null) {
            super.mWidgetManager.RemoveWidget(this.mAd);
            this.mAd = null;
            if (this.mBoard != null) {
                this.mBoard.SetUnpause();
                this.showWidget(this.mBoard.mButton);
            }
            this.showWidget(this.mBoard);
            this.showWidget(this.mFloatingHead);
            this.showWidget(this.mDialogWidget);
            this.showWidget(this.mSkillButton[0]);
            this.showWidget(this.mSkillButton[1]);
            this.showWidget(this.mSkillButton[2]);
            this.showWidget(this.mSoundButton);
            this.showWidget(this.mHelpButton);
            this.showWidget(this.mNewGameButton);
            this.showWidget(this.mPauseButton);
            this.showWidget(this.mLevelUpScreen);
        }
    }
    
    public void ThreadInit() {
        this.mPopcapAnim.GetImages();
    }
    
    public void ShowLevelUpScreen(final boolean b) {
        this.KillLevelUpScreen();
        if (this.mBoard != null) {
            this.mBoard.SetPause();
        }
        (this.mLevelUpScreen = new LevelUp(this, b)).Resize(0, 0, super.mWidth, super.mHeight);
        super.mWidgetManager.AddWidget(this.mLevelUpScreen);
        if (this.mBoard == null) {
            super.mWidgetManager.BringToBack(this.mLevelUpScreen);
            return;
        }
        if (this.mBoard.mButton == null) {
            super.mWidgetManager.PutInfront(this.mLevelUpScreen, this.mBoard);
            return;
        }
        super.mWidgetManager.PutInfront(this.mLevelUpScreen, this.mBoard.mButton);
    }
    
    public RocketManiaApplet() {
        this.mSoundOn = true;
        this.mSkillButton = new ButtonWidget[3];
        this.mRecordingEnabled = false;
        this.mStartRandom = new Random();
        this.mScoreUpload = false;
        this.mCheats = false;
        this.mHints = new Hints();
    }
    
    void KillBoard() {
        if (this.mBoard != null) {
            super.mWidgetManager.RemoveWidget(this.mBoard);
            this.mBoard = null;
        }
    }
    
    public void ButtonMouseEnter(final int n) {
    }
    
    public void ResizeComponents() {
        if (!this.mLoaded) {
            this.mPopcapAnim.Resize(0, 0, super.mWidth, super.mHeight);
        }
    }
    
    public void ConfirmSkillChange(final int mNextSkillLevel) {
        if (mNextSkillLevel != this.mSkillLevel) {
            switch (this.mState) {
                case 1: {
                    this.DoDialog("Change Difficulty Level?", "You must start a new game to\nchange the difficulty level.", null, 1);
                    this.mNextSkillLevel = mNextSkillLevel;
                    this.mDialogType = 1;
                }
                case 0: {
                    this.SetSkillLevel(mNextSkillLevel);
                }
            }
        }
    }
    
    private void hideWidget(final Widget widget) {
        if (widget != null) {
            widget.SetVisible(false);
        }
    }
    
    public void ResourcesLoaded() {
        new AsyncJSCall(this, "SessionStart", null);
        this.mLoaded = true;
        super.mWidgetManager.RemoveWidget(this.mPopcapAnim);
        this.mPopcapAnim = null;
        int n = 0;
        do {
            final int[] array = { 0, 37, 90 };
            final int[] array2 = { 37, 53, 38 };
            this.mSkillButton[n] = this.AddButtonNoImage(10 + n, array[n], 322, array2[n], 24);
            this.mSkillButton[n].mButtonImage = this.mRes.CopyAndFilter(this.mRes.mImages[26], array[n], 0, array2[n], 24, null);
            this.mSkillButton[n].mDownImage = this.mRes.CopyAndFilter(this.mRes.mImages[28], array[n], 0, array2[n], 24, null);
            this.mSkillButton[n].mOverImage = this.mRes.CopyAndFilter(this.mRes.mImages[27], array[n], 0, array2[n], 24, null);
        } while (++n < 3);
        this.mSkillButton[0].mInverted = true;
        this.mSkillButton[0].mDoFinger = false;
        this.mSkillLevel = 0;
        this.mSoundButton = this.AddButtonNoImage(1, 0, 346, 28, 24);
        this._toggleSoundButtonImages(true);
        this.mNewGameButton = this.AddButtonNoImage(0, 28, 346, 72, 24);
        this.mNewGameButton.mButtonImage = this.mRes.CopyAndFilter(this.mRes.mImages[26], 28, 24, 72, 24, null);
        this.mNewGameButton.mDownImage = this.mRes.CopyAndFilter(this.mRes.mImages[28], 28, 24, 72, 24, null);
        this.mNewGameButton.mOverImage = this.mRes.CopyAndFilter(this.mRes.mImages[27], 28, 24, 72, 24, null);
        this.mPauseButton = this.AddButtonNoImage(51, 100, 346, 28, 24);
        this.mPauseButton.mButtonImage = this.mRes.CopyAndFilter(this.mRes.mImages[26], 100, 24, 28, 24, null);
        this.mPauseButton.mDownImage = this.mRes.CopyAndFilter(this.mRes.mImages[28], 100, 24, 28, 24, null);
        this.mPauseButton.mOverImage = this.mRes.CopyAndFilter(this.mRes.mImages[27], 100, 24, 28, 24, null);
        this.mHelpButton = this.AddButtonNoImage(50, 0, 301, 31, 21);
        this.mHelpButton.mButtonImage = this.mRes.mImages[31];
        this.mHelpButton.mDownImage = this.mRes.mImages[33];
        this.mHelpButton.mOverImage = this.mRes.mImages[32];
        this.NewGame();
        this.mFloatingHead = new FloatingHead(this);
        super.mWidgetManager.AddWidget(this.mFloatingHead);
        super.mWidgetManager.BringToFront(this.mFloatingHead);
        super.mWidgetManager.BringToFront(this.mHelpButton);
        this.ResizeComponents();
    }
    
    public void ButtonDepress(final int n) {
        switch (n) {
            case 100: {
                this.KillDialog();
                this.mHints.mNoMoreHints = true;
            }
            case 0: {
                if (this.mBuyMe != null) {
                    this.CloseBuyMe();
                    this.NewGame();
                    return;
                }
                this.ConfirmNewGame();
            }
            case 1: {
                this._toggleSoundButtonImages(this.mSoundOn = !this.mSoundOn);
                this.PlaySound(9);
                this.mSoundButton.MarkDirty();
            }
            case 10: {
                if (this.mBuyMe != null) {
                    this.CloseBuyMe();
                    this.SetSkillLevel(0);
                    return;
                }
                this.ConfirmSkillChange(0);
            }
            case 11: {
                if (this.mBuyMe != null) {
                    this.CloseBuyMe();
                    this.SetSkillLevel(1);
                    return;
                }
                this.ConfirmSkillChange(1);
            }
            case 12: {
                if (this.mBuyMe != null) {
                    this.CloseBuyMe();
                    this.SetSkillLevel(2);
                    return;
                }
                this.ConfirmSkillChange(2);
            }
            case 20: {
                switch (this.mDialogType) {
                    case 0: {
                        this.KillDialog();
                        this.NewGame();
                        return;
                    }
                    case 1: {
                        this.KillDialog();
                        this.SetSkillLevel(this.mNextSkillLevel);
                        return;
                    }
                    default: {
                        this.KillDialog();
                        return;
                    }
                }
                break;
            }
            case 21: {
                this.KillDialog();
            }
            case 50: {
                this.DoHelpDialog();
            }
            case 51: {
                if (this.mLevelUpScreen == null) {
                    this.mBoard.TogglePause();
                    return;
                }
                break;
            }
            default: {
                Assert.assert(false);
                break;
            }
        }
    }
    
    public void ShowBuyMe() {
        if (this.mBoard != null) {
            this.mBoard.SetPause();
            this.mBoard.Empty();
            this.hideWidget(this.mBoard.mButton);
        }
        this.hideWidget(this.mHelpButton);
        this.hideWidget(this.mPauseButton);
        this.mBuyMe = new BuyMe(this);
        super.mWidgetManager.AddWidget(this.mBuyMe);
    }
    
    public void ShowAd() {
        try {
            boolean b = true;
            for (int i = 0; i < this.mRes.mImageAd.length; ++i) {
                if (this.mRes.mImageAd[i] == null || !this.mRes.mImageAd[i].IsReady()) {
                    b = false;
                }
            }
            if (b) {
                if (this.mBoard != null) {
                    this.mBoard.SetPause();
                    this.hideWidget(this.mBoard.mButton);
                }
                this.hideWidget(this.mBoard);
                this.hideWidget(this.mFloatingHead);
                this.hideWidget(this.mDialogWidget);
                this.hideWidget(this.mSkillButton[0]);
                this.hideWidget(this.mSkillButton[1]);
                this.hideWidget(this.mSkillButton[2]);
                this.hideWidget(this.mSoundButton);
                this.hideWidget(this.mHelpButton);
                this.hideWidget(this.mNewGameButton);
                this.hideWidget(this.mPauseButton);
                this.hideWidget(this.mLevelUpScreen);
                (this.mAd = new Advertisement(this)).Resize(0, 0, super.mWidth, super.mHeight);
                super.mWidgetManager.AddWidget(this.mAd);
            }
        }
        catch (Exception ex) {}
    }
    
    public void ConfirmNewGame() {
        switch (this.mState) {
            case 0: {
                this.NewGame();
            }
            case 1: {
                this.KillDialog();
                this.DoDialog("New Game", "Do you want to start a new game?", null, 1);
                this.mDialogType = 0;
            }
            default: {}
        }
    }
    
    public class COMClassObject
    {
        public COMClassObject() {
            RocketManiaApplet.this.getClass();
        }
    }
}
