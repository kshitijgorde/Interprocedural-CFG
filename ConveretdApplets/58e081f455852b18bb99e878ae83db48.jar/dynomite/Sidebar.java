// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import java.awt.Rectangle;
import java.awt.Color;
import sexy.gui.SexyGraphics;
import sexy.gui.WidgetManager;
import java.awt.Point;
import sexy.util.MTRand;
import sexy.gui.ButtonListener;
import sexy.gui.Widget;

public class Sidebar extends Widget implements ButtonListener
{
    static final int ID_PAUSE = 1;
    static final int ID_SOUND = 2;
    static final int ID_NEWGAME = 3;
    DynomiteApplet mApplet;
    MTRand mRand;
    int mComboLevel;
    int mComboCountdown;
    int mComboWait;
    boolean mComboJuryRig;
    int mScore;
    Button mPauseButton;
    Button mSoundButton;
    Button mNewGameButton;
    boolean mMouthing;
    Point[] mMouthMotion;
    int mMouthMax;
    int mMouthPointer;
    int mMouthCountdown;
    int mMouthPose;
    boolean mBlink;
    int mBlinkCountdown;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        widgetManager.RemoveWidget(this.mPauseButton);
        widgetManager.RemoveWidget(this.mSoundButton);
        widgetManager.RemoveWidget(this.mNewGameButton);
    }
    
    public void Update() {
        super.Update();
        if (this.mComboWait == 0) {
            if (this.mComboCountdown > 0) {
                --this.mComboCountdown;
                if (this.mComboCountdown == 0) {
                    this.MarkDirty();
                }
            }
        }
        else if (this.mComboWait > 0 && --this.mComboWait == 0) {
            switch (this.mComboLevel) {
                case 1: {
                    this.mApplet.PlaySound(6);
                    break;
                }
                case 2: {
                    this.mApplet.PlaySound(7);
                    break;
                }
                case 3: {
                    this.mApplet.PlaySound(13);
                    break;
                }
                case 4: {
                    this.mApplet.PlaySound(14);
                    break;
                }
                case 5: {
                    this.mApplet.PlaySound(15);
                    break;
                }
                case 6: {
                    this.mApplet.PlaySound(16);
                    break;
                }
                case 7: {
                    this.mApplet.PlaySound(17);
                    break;
                }
            }
            this.mMouthing = true;
            this.mMouthMax = 0;
            this.mMouthPointer = -1;
            this.mMouthCountdown = 0;
            switch (this.mComboLevel) {
                case 1: {
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 20);
                    break;
                }
                case 2: {
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 5);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 5);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 35);
                    break;
                }
                case 3: {
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 5);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 5);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 25);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 35);
                    break;
                }
                case 4: {
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 25);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 35);
                    break;
                }
                case 5: {
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 5);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 5);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 25);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 35);
                    break;
                }
                case 6: {
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 20);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 35);
                    break;
                }
                case 7: {
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 5);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(1, 15);
                    this.mMouthMotion[this.mMouthMax++] = new Point(3, 10);
                    this.mMouthMotion[this.mMouthMax++] = new Point(2, 20);
                    this.mMouthMotion[this.mMouthMax++] = new Point(0, 35);
                    break;
                }
            }
            this.MarkDirty();
        }
        this.UpdateMouth();
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        if (this.mApplet.mAd != null) {
            return;
        }
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[7], 0, 0);
        sexyGraphics.DrawImage(this.mApplet.mRes.mImages[55], 0, 199);
        sexyGraphics.SetFont(this.mApplet.mVeryTinyFont);
        String s = "";
        String s2 = "";
        if (this.mApplet.mBoard != null) {
            if (this.mApplet.mSkill == 0) {
                s = "Easy";
            }
            if (this.mApplet.mSkill == 1) {
                s = "Normal";
            }
            if (this.mApplet.mSkill == 2) {
                s = "Hard";
            }
            if (this.mApplet.mGameType == 0) {
                s2 = "Endless";
            }
            if (this.mApplet.mGameType == 1) {
                s2 = "Stomped";
            }
        }
        sexyGraphics.SetColor(new Color(0, 0, 0));
        sexyGraphics.DrawString(s, 53 - this.mApplet.mVeryTinyFont.StringWidth(s) / 2 + 1, 153);
        sexyGraphics.DrawString(s2, 53 - this.mApplet.mVeryTinyFont.StringWidth(s2) / 2 + 1, 162);
        sexyGraphics.SetColor(new Color(255, 255, 255));
        sexyGraphics.DrawString(s, 53 - this.mApplet.mVeryTinyFont.StringWidth(s) / 2, 152);
        sexyGraphics.DrawString(s2, 53 - this.mApplet.mVeryTinyFont.StringWidth(s2) / 2, 161);
        boolean mPause = false;
        if (this.mApplet.mBoard != null) {
            mPause = this.mApplet.mBoard.mPause;
        }
        if (!mPause) {
            if (!this.mPauseButton.mIsOver) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[42], 61, 171, new Rectangle(0, 0, 23, 23));
            }
            else {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[42], 61, 171, new Rectangle(23, 0, 23, 23));
            }
        }
        else if (!this.mPauseButton.mIsOver) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[42], 61, 171, new Rectangle(46, 0, 23, 23));
        }
        else {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[42], 61, 171, new Rectangle(69, 0, 23, 23));
        }
        if (this.mApplet.mSoundOn) {
            if (!this.mSoundButton.mIsOver) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[43], 23, 171, new Rectangle(0, 0, 23, 23));
            }
            else {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImages[43], 23, 171, new Rectangle(23, 0, 23, 23));
            }
        }
        else if (!this.mSoundButton.mIsOver) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[43], 23, 171, new Rectangle(46, 0, 23, 23));
        }
        else {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[43], 23, 171, new Rectangle(69, 0, 23, 23));
        }
        if (!this.mNewGameButton.mIsOver) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[44], 17, 103, new Rectangle(0, 0, 72, 36));
        }
        else {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[44], 17, 103, new Rectangle(72, 0, 72, 36));
        }
        if (this.mComboCountdown > 0 && (this.mComboWait == 0 || this.mComboJuryRig)) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[22], 41, 181);
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[23], 47, 188, new Rectangle((this.mComboLevel - 1) * 49, 0, 49, 17));
        }
        if (this.mMouthing) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[50], 52, 240, new Rectangle(this.mMouthPose * 7, 0, 7, 10));
        }
        if (this.mBlink) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[51], 48, 229);
        }
        this.DrawScore(sexyGraphics, 89, 43);
    }
    
    public Sidebar(final DynomiteApplet mApplet) {
        super(mApplet.mWidgetManager);
        this.mMouthMotion = new Point[20];
        this.mApplet = mApplet;
        this.mRand = new MTRand((int)(Math.random() * 65535.0));
        this.mComboCountdown = 0;
        this.mComboWait = 0;
        this.mComboJuryRig = false;
        this.mScore = 0;
        this.mPauseButton = new Button(this, super.mWidgetManager, 1, this);
        this.mPauseButton.mDoFinger = true;
        this.mPauseButton.Resize(61, 171, 23, 23);
        this.mSoundButton = new Button(this, super.mWidgetManager, 2, this);
        this.mSoundButton.mDoFinger = true;
        this.mSoundButton.Resize(23, 171, 23, 23);
        this.mNewGameButton = new Button(this, super.mWidgetManager, 3, this);
        this.mNewGameButton.mDoFinger = true;
        this.mNewGameButton.Resize(17, 103, 72, 36);
        this.mMouthing = false;
    }
    
    public void Combo(final int mComboLevel) {
        final int[] array = { 0, 40, 60, 65, 65, 65, 80, 75, 0, 0, 0 };
        this.mComboJuryRig = false;
        if (this.mComboCountdown > 0) {
            this.mComboJuryRig = true;
        }
        this.mComboWait = 25;
        this.MarkDirty();
        this.mComboLevel = mComboLevel;
        this.mComboCountdown = array[this.mComboLevel];
    }
    
    public void DrawScore(final SexyGraphics sexyGraphics, int n, final int n2) {
        final String string = "" + this.mScore;
        for (int i = string.length() - 1; i >= 0; --i) {
            int char1 = string.charAt(i);
            char1 -= 48;
            n -= 11;
            sexyGraphics.DrawImage(this.mApplet.mRes.mImages[24], n, n2, new Rectangle(char1 * 12, 0, 12, 33));
        }
    }
    
    public void ButtonPress(final int n) {
        if (n != 2) {
            this.mApplet.PlaySound(5);
        }
    }
    
    public void UpdateMouth() {
        if (this.mMouthing) {
            this.mMouthCountdown -= 2;
            if (this.mMouthCountdown <= 0) {
                ++this.mMouthPointer;
                if (this.mMouthPointer >= this.mMouthMax) {
                    this.mMouthing = false;
                    this.MarkDirty();
                }
                else {
                    this.mMouthPose = this.mMouthMotion[this.mMouthPointer].x;
                    this.mMouthCountdown = this.mMouthMotion[this.mMouthPointer].y;
                    this.MarkDirty();
                }
            }
        }
        this.mBlinkCountdown -= 2;
        if (this.mBlinkCountdown < -50) {
            this.mBlinkCountdown = 250 + this.mRand.Next() % 100;
        }
        final boolean mBlink = this.mBlink;
        if (this.mBlinkCountdown > 20 && this.mBlinkCountdown < 30) {
            this.mBlink = true;
        }
        else if (this.mBlinkCountdown > 0 && this.mBlinkCountdown < 10) {
            this.mBlink = true;
        }
        else {
            this.mBlink = false;
        }
        if (this.mBlink != mBlink) {
            this.MarkDirty();
        }
    }
    
    public void ButtonDepress(final int n) {
        switch (n) {
            case 1: {
                if (this.mApplet.mBoard != null) {
                    this.mApplet.mBoard.SwitchPause();
                    return;
                }
                break;
            }
            case 2: {
                this.mApplet.SwitchSound();
                this.mApplet.PlaySound(5);
            }
            case 3: {
                this.mApplet.ConfirmNewGame();
            }
        }
    }
    
    public void ResetScore() {
        this.mScore = 0;
    }
    
    public void AddScore(final int n) {
        this.mScore += n;
        this.MarkDirty();
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
        widgetManager.AddWidget(this.mPauseButton);
        widgetManager.AddWidget(this.mSoundButton);
        widgetManager.AddWidget(this.mNewGameButton);
    }
    
    public void ButtonDownTick(final int n) {
    }
}
