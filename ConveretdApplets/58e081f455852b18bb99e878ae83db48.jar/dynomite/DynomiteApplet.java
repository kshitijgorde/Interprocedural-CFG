// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import sexy.gui.SexyImage;
import sexy.util.AsyncJSCall;
import java.awt.Event;
import sexy.gui.Widget;
import sexy.gui.SexyFont;
import sexy.gui.OutlineDialogWidget;
import sexy.res.SoundThread;
import sexy.gui.PopcapAnim;
import java.util.Random;
import sexy.gui.ButtonListener;
import sexy.gui.SexyApplet;

public class DynomiteApplet extends SexyApplet implements ButtonListener
{
    boolean mIsMSN;
    boolean mScoreUpload;
    Random mStartRandom;
    PopcapAnim mPopcapAnim;
    Res mRes;
    SoundThread mSoundThread;
    boolean mSoundOn;
    boolean mLoaded;
    boolean mShowAds;
    Board mBoard;
    Sidebar mSidebar;
    GameWizard mGameWizard;
    Advertisement mAd;
    OutlineDialogWidget mDialogWidget;
    int mDialogType;
    static final int DIALOG_NEWGAME = 0;
    static final int[][] mDialogColors;
    static final int[][] mButtonColors;
    int mGameType;
    int mSkill;
    boolean mAdInitiated;
    SexyFont mAnnounceFont;
    SexyFont mRoundFont;
    SexyFont mSmallFont;
    SexyFont mTinyFont;
    SexyFont mAdTinyFont;
    SexyFont mVeryTinyFont;
    
    public void ShowGameWizard() {
        if (this.mBoard != null) {
            super.mWidgetManager.RemoveWidget(this.mBoard);
            this.mBoard = null;
        }
        (this.mGameWizard = new GameWizard(this)).MarkDirty();
        this.mGameWizard.Resize(103, 0, super.mWidth - 103, super.mHeight);
        super.mWidgetManager.AddWidget(this.mGameWizard);
        this.mSidebar.MarkDirty();
    }
    
    public synchronized boolean keyDown(final Event event, final int n) {
        if ((n == 80 || n == 112 || n == 32) && this.mBoard != null) {
            this.mBoard.SwitchPause();
        }
        return super.keyDown(event, n);
    }
    
    void DoDialog(final String s, final String s2, final String s3, final int n) {
        this.KillDialog();
        (this.mDialogWidget = new OutlineDialogWidget(super.mWidgetManager, this, s, s2, s3, n)).Resize(140, 32, 275, this.mDialogWidget.GetPreferredHeight(275));
        super.mWidgetManager.AddWidget(this.mDialogWidget);
        this.mDialogWidget.SetColors(DynomiteApplet.mDialogColors);
        super.mWidgetManager.SetBaseModal(this.mDialogWidget);
        if (this.mBoard != null && !this.mBoard.mPause) {
            this.mBoard.SwitchPause();
        }
    }
    
    public void Initialize() {
        System.out.println("Build #" + 1494 + " (" + "Thu Aug 28 15:26:54 2003" + ")");
        if (this.mIsMSN) {
            if (!this.CheckHost()) {
                return;
            }
            this.mScoreUpload = this.GetBoolean("ScoreUpload");
        }
        this.mShowAds = this.GetParamBoolean("ShowAds", true);
        final String parameter = this.getParameter("resbase");
        this.mRes = new Res(this);
        if (parameter != null) {
            this.mRes.SetResourceBase(parameter);
        }
        super.mResLoader = this.mRes;
        this.mSoundThread = new SoundThread();
        super.Initialize();
        this.mPopcapAnim = new PopcapAnim(super.mWidgetManager, "Dynomite");
        super.mWidgetManager.AddWidget(this.mPopcapAnim);
        this.ResizeComponents();
        new Board(this);
        this.mAnnounceFont = this.CreateFont("Arial", 1, 20);
        this.mRoundFont = this.CreateFont("Arial", 1, 22);
        this.mSmallFont = this.CreateFont("Arial", 1, 14);
        this.mTinyFont = this.CreateFont("Arial", 1, 12);
        this.mAdTinyFont = this.CreateFont("Arial", 1, 11);
        this.mVeryTinyFont = this.CreateFont("Arial", 0, 10);
        this.mAdInitiated = false;
        this.mGameType = 0;
        this.mSkill = 1;
    }
    
    void KillDialog() {
        if (this.mDialogWidget != null) {
            super.mWidgetManager.RemoveWidget(this.mDialogWidget);
            this.mDialogWidget = null;
        }
        if (this.mBoard != null && this.mBoard.mPause) {
            this.mBoard.SwitchPause();
        }
    }
    
    boolean GetBoolean(final String s) {
        final String parameter = this.getParameter(s);
        return parameter != null && (parameter.equalsIgnoreCase("1") || parameter.equalsIgnoreCase("on") || parameter.equalsIgnoreCase("yes") || parameter.equalsIgnoreCase("true"));
    }
    
    public void StopSound(final int n) {
        if (n >= this.mRes.mNumSoundsLoaded) {
            return;
        }
        this.mSoundThread.PushCommand(this.mRes.mSounds[n], 2);
    }
    
    public void CloseAd() {
        if (this.mAd != null) {
            super.mWidgetManager.RemoveWidget(this.mAd);
            this.mAd = null;
        }
    }
    
    public void BeginAdGraphicsStream() {
        this.mRes.mImageAdTitle = this.mRes.AsyncGetImage("images/ad/title.gif");
        this.mRes.mImageAd[0] = this.mRes.AsyncGetImage("images/ad/ad1.jpg");
        this.mRes.mImageAd[1] = this.mRes.AsyncGetImage("images/ad/ad2.jpg");
        this.mRes.mImageAd[2] = this.mRes.AsyncGetImage("images/ad/ad3.jpg");
        this.mRes.mImageAd[3] = this.mRes.AsyncGetImage("images/ad/ad4.jpg");
        this.mAdInitiated = true;
    }
    
    public void ThreadInit() {
        this.mPopcapAnim.GetImages();
    }
    
    public void LoopSound(final int n) {
        if (!this.mSoundOn) {
            return;
        }
        if (n >= this.mRes.mNumSoundsLoaded) {
            return;
        }
        this.mSoundThread.PushCommand(this.mRes.mSounds[n], 1);
    }
    
    public DynomiteApplet() {
        this.mIsMSN = false;
        this.mScoreUpload = false;
        this.mStartRandom = new Random();
        this.mSoundOn = true;
        this.mLoaded = false;
        this.mShowAds = true;
    }
    
    public void ButtonPress(final int n) {
        this.PlaySound(5);
    }
    
    public void ResizeComponents() {
        if (!this.mLoaded) {
            this.mPopcapAnim.Resize(0, 0, super.mWidth, super.mHeight);
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
    
    public void Shudown() {
        this.StopSound(26);
        super.Shutdown();
    }
    
    public void PlaySound(final int n) {
        if (!this.mSoundOn) {
            return;
        }
        if (n >= this.mRes.mNumSoundsLoaded) {
            return;
        }
        this.mSoundThread.PushCommand(this.mRes.mSounds[n], 0);
    }
    
    public void ResourcesLoaded() {
        if (this.mIsMSN) {
            new AsyncJSCall(this, "SessionStart", null);
        }
        this.mLoaded = true;
        this.mRes.mImageLeg = this.mRes.AsyncGetImage("images/leg.gif");
        this.mRes.mImageWhirley = this.mRes.AsyncGetImage("images/whirley.gif");
        this.mRes.mImageKroneyflip = this.mRes.AsyncGetImage("images/kroneyflip.gif");
        this.mRes.mImageGameOver = this.mRes.AsyncGetImage("images/gameover.gif");
        int n = 0;
        do {
            this.mRes.mEggShell[n] = this.MakeEggshell(n);
        } while (++n < 18);
        super.mWidgetManager.RemoveWidget(this.mPopcapAnim);
        this.mPopcapAnim = null;
        (this.mSidebar = new Sidebar(this)).Resize(0, 0, 103, super.mHeight);
        super.mWidgetManager.AddWidget(this.mSidebar);
        super.mWidgetManager.BringToBack(this.mSidebar);
        this.ShowGameWizard();
    }
    
    public void SwitchSound() {
        this.mSoundOn = !this.mSoundOn;
        this.mSidebar.MarkDirty();
        if (this.mBoard != null) {
            this.mBoard.SoundFix();
        }
    }
    
    static {
        mDialogColors = new int[][] { { 255, 200, 0 }, { 255, 255, 255 }, { 255, 255, 255 }, { 255, 255, 255 }, { 25, 25, 0 } };
        mButtonColors = new int[][] { { 229, 180, 0 }, { 43, 30, 0 }, { 100, 100, 0 }, { 53, 40, 0 }, { 255, 255, 255 } };
    }
    
    public void ButtonDepress(final int n) {
        Label_0060: {
            switch (n) {
                case 20: {
                    switch (this.mDialogType) {
                        case 0: {
                            this.ShowGameWizard();
                            this.ShowAd();
                            this.KillDialog();
                            break Label_0060;
                        }
                    }
                    break;
                }
                case 21: {
                    switch (this.mDialogType) {
                        default: {
                            this.KillDialog();
                            return;
                        }
                    }
                    break;
                }
                default: {}
            }
        }
    }
    
    public SexyImage MakeEggshell(final int n) {
        final SexyImage sexyImage = new SexyImage();
        sexyImage.Create(96, 27);
        final int[] getBits = sexyImage.GetBits();
        final int[] getBits2 = this.mRes.mImages[10].GetBits();
        final int[] getBits3 = this.mRes.mImages[18].GetBits();
        int n2 = 0;
        do {
            int n3 = n * 24 + n2 * this.mRes.mImages[10].GetWidth();
            int n4 = n2 * this.mRes.mImages[18].GetWidth();
            int n5 = n2 * sexyImage.GetWidth();
            int n6 = 0;
            do {
                if (getBits3[n4] != -16777216) {
                    if (getBits3[n4] == -65536) {
                        getBits[n5] = getBits2[n3];
                    }
                    if (getBits3[n4] == -16776961) {
                        getBits[n5 + 24] = getBits2[n3];
                    }
                    if (getBits3[n4] == -16711936) {
                        getBits[n5 + 24 + 24] = getBits2[n3];
                    }
                    if (getBits3[n4] == -1) {
                        getBits[n5 + 24 + 24 + 24] = getBits2[n3];
                    }
                }
                ++n3;
                ++n4;
                ++n5;
            } while (++n6 < 24);
        } while (++n2 < 27);
        sexyImage.SetImageMode(true, false);
        sexyImage.BitsChanged();
        return sexyImage;
    }
    
    public void NewGame() {
        if (this.mGameWizard != null) {
            super.mWidgetManager.RemoveWidget(this.mGameWizard);
            this.mGameWizard = null;
        }
        if (this.mBoard != null) {
            super.mWidgetManager.RemoveWidget(this.mBoard);
            this.mBoard = null;
        }
        if (this.mGameType == 0) {
            this.mBoard = new Board(this, 0, this.mSkill);
        }
        else {
            this.mBoard = new Board(this, 1, this.mSkill);
        }
        this.mBoard.Resize(103, 0, super.mWidth - 103, super.mHeight);
        super.mWidgetManager.AddWidget(this.mBoard);
        super.mWidgetManager.BringToBack(this.mBoard);
        super.mWidgetManager.SetFocus(this.mBoard);
        this.mSidebar.MarkDirty();
    }
    
    public void ButtonDownTick(final int n) {
    }
    
    public String GetScoreHook() {
        if (this.mSidebar != null) {
            return "" + this.mSidebar.mScore;
        }
        return null;
    }
    
    public void ShowAd() {
        if (!this.mShowAds) {
            return;
        }
        if (!this.mAdInitiated) {
            this.BeginAdGraphicsStream();
            return;
        }
        if (this.mRes.mImageAdTitle.IsReady() && this.mRes.mImageAd[0].IsReady() && this.mRes.mImageAd[1].IsReady() && this.mRes.mImageAd[2].IsReady() && this.mRes.mImageAd[3].IsReady()) {
            (this.mAd = new Advertisement(this)).Resize(0, 0, super.mWidth, super.mHeight);
            super.mWidgetManager.AddWidget(this.mAd);
        }
    }
    
    public void ConfirmNewGame() {
        if (this.mBoard != null) {
            if (!this.mBoard.mGameOver) {
                this.DoDialog("New Game?", "Are you sure you want to\nstart a new game?", null, 1);
                this.mDialogType = 0;
                return;
            }
            this.ShowGameWizard();
            this.ShowAd();
        }
        if (this.mGameWizard == null) {
            this.ShowGameWizard();
        }
    }
}
