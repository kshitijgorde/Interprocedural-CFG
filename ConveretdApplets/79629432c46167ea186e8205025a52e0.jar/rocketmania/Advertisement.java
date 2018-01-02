// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import java.net.URL;
import sexy.gui.SexyImage;
import sexy.gui.SexyColor;
import java.awt.Color;
import sexy.gui.SexyGraphics;
import sexy.gui.WidgetManager;
import sexy.gui.OutlineButtonWidget;
import sexy.gui.ButtonListener;
import sexy.gui.Widget;

public class Advertisement extends Widget implements ButtonListener
{
    static final int skTouchHack = 1;
    RocketManiaApplet mApplet;
    int mSideline;
    boolean mSlideSideline;
    int mTitleFade;
    int mSlideText;
    int mSlideAd;
    int mCurrentAdCountdown;
    int mCurrentAd;
    double mCurrentAdFade;
    OutlineButtonWidget mDownloadButton;
    OutlineButtonWidget mReturnButton;
    static int BUTTON_DOWNLOAD;
    static int BUTTON_RETURN;
    static int TEXTY;
    static final int[][] mButtonColors;
    static final int[] DOLLAR_POSITIONS;
    boolean mPaintItBlack;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        widgetManager.RemoveWidget(this.mDownloadButton);
        widgetManager.RemoveWidget(this.mReturnButton);
    }
    
    public void Update() {
        super.Update();
        if (this.mSlideSideline) {
            if (this.mSideline < 33) {
                ++this.mSideline;
            }
            if (this.mSideline == 33) {
                this.mSlideSideline = false;
                this.mDownloadButton.SetVisible(true);
                this.mReturnButton.SetVisible(true);
            }
            this.MarkDirty();
            return;
        }
        if (this.mTitleFade < 255) {
            this.mTitleFade += 15;
            if (this.mTitleFade > 255) {
                this.mTitleFade = 255;
            }
            this.MarkDirty();
            return;
        }
        this.mSlideText -= 8;
        if (this.mSlideText <= 290) {
            this.mSlideText = 290;
        }
        this.mSlideAd += 8;
        if (this.mSlideAd > 10) {
            this.mSlideAd = 10;
        }
        if (--this.mCurrentAdCountdown <= 0) {
            this.mCurrentAd = (this.mCurrentAd + 1) % 3;
            this.mCurrentAdFade = 0.0;
            this.mCurrentAdCountdown = 200;
        }
        if (this.mCurrentAdFade < 1.0) {
            this.mCurrentAdFade += 0.04;
            if (this.mCurrentAdFade >= 1.0) {
                this.mCurrentAdFade = 1.0;
            }
        }
        this.MarkDirty();
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        if (this.mPaintItBlack) {
            sexyGraphics.SetColor(Color.black);
            sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
            this.mPaintItBlack = false;
        }
        sexyGraphics.SetColor(new SexyColor(178, 1, 0));
        sexyGraphics.FillRect(0, -10, super.mWidth, this.mSideline);
        if (this.mSideline < 33) {
            sexyGraphics.SetColor(Color.black);
            sexyGraphics.FillRect(0, this.mSideline, super.mWidth, 33 - this.mSideline);
        }
        if (this.mTitleFade > 0 && this.mTitleFade < 255) {
            sexyGraphics.SetColorizeImages(true);
            sexyGraphics.SetColor(new SexyColor(this.mTitleFade, this.mTitleFade, this.mTitleFade, 255));
            sexyGraphics.DrawImage(this.mApplet.mRes.mImageAd[0], 0, 26);
            sexyGraphics.SetColorizeImages(false);
            return;
        }
        if (this.mTitleFade == 255) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImageAd[0], 0, 26);
            sexyGraphics.SetFont(this.mApplet.mRes.mLineFontSmall);
            sexyGraphics.SetColor(new SexyColor(252, 174, 25));
            sexyGraphics.DrawString("Love Rocket Mania?", 8, 16);
            final String s = "Play it offline, anytime!";
            sexyGraphics.DrawString(s, super.mWidth - sexyGraphics.GetFont().StringWidth(s) - 8, 16);
            sexyGraphics.SetColor(Color.black);
            if (this.mSlideAd > 0) {
                sexyGraphics.FillRect(0, Advertisement.TEXTY + 6 - 4, this.mSlideAd, 193);
            }
            final SexyImage sexyImage = this.mApplet.mRes.mImageAd[1];
            final int n = this.mSlideText + sexyImage.GetWidth();
            if (n < super.mWidth) {
                sexyGraphics.FillRect(n, Advertisement.TEXTY + 6 - 4, super.mWidth - n, 193);
            }
            sexyGraphics.DrawImage(sexyImage, this.mSlideText, Advertisement.TEXTY);
            sexyGraphics.FillRect(this.mSlideText - 18, Advertisement.TEXTY + 6, 18, 185);
            sexyGraphics.SetColorizeImages(true);
            int n2 = 0;
            do {
                final double sin = Math.sin(0.1 * ((10 * (6 - n2) + super.mUpdateCnt) % 64));
                sexyGraphics.SetColor(new SexyColor(255, (int)(191.0 + 63.0 * sin), (int)(223.0 + 31.0 * sin)));
                sexyGraphics.DrawImage(this.mApplet.mRes.GetImage(30), this.mSlideText - 18, Advertisement.DOLLAR_POSITIONS[n2]);
            } while (++n2 < 6);
            sexyGraphics.SetColorizeImages(false);
            sexyGraphics.SetColor(new SexyColor(255, 200, 72));
            final SexyImage sexyImage2 = this.mApplet.mRes.mImageAd[2 + this.mCurrentAd];
            int n3 = 1;
            do {
                sexyGraphics.DrawRect(this.mSlideAd - n3, Advertisement.TEXTY + 6 - n3, sexyImage2.GetWidth() + 2 * n3 - 1, sexyImage2.GetHeight() + 2 * n3 - 1);
            } while (++n3 < 5);
            if (this.mCurrentAdFade != 0.0) {
                sexyGraphics.DrawImage(this.mApplet.mRes.mImageAd[2 + (this.mCurrentAd + 2) % 3], this.mSlideAd, Advertisement.TEXTY + 6);
            }
            sexyGraphics.SetColorizeImages(true);
            sexyGraphics.SetColor(new SexyColor(255, 255, 255, (int)(this.mCurrentAdFade * 255.0)));
            sexyGraphics.DrawImage(sexyImage2, this.mSlideAd, Advertisement.TEXTY + 6);
            sexyGraphics.SetColorizeImages(false);
        }
    }
    
    public Advertisement(final RocketManiaApplet mApplet) {
        super(mApplet.mWidgetManager);
        this.mCurrentAdCountdown = 100;
        this.mCurrentAd = 0;
        this.mCurrentAdFade = 1.0;
        this.mApplet = mApplet;
        this.mTitleFade = 0;
        this.mSideline = 0;
        this.mSlideAd = -250;
        this.mSlideSideline = true;
        this.mSlideText = 550;
        this.mDownloadButton = new OutlineButtonWidget(super.mWidgetManager, Advertisement.BUTTON_DOWNLOAD, this);
        this.mDownloadButton.mOutlineSize = 0;
        this.mDownloadButton.mDoFinger = true;
        this.mDownloadButton.mFont = this.mApplet.mRes.mLineFontSmall;
        this.mDownloadButton.mLabel = "Click to download FREE TRIAL!";
        this.mDownloadButton.SetColors(Advertisement.mButtonColors);
        this.mDownloadButton.Resize(1, 346, 249, 24);
        this.mDownloadButton.SetVisible(false);
        this.mReturnButton = new OutlineButtonWidget(super.mWidgetManager, Advertisement.BUTTON_RETURN, this);
        this.mReturnButton.mOutlineSize = 0;
        this.mReturnButton.mDoFinger = true;
        this.mReturnButton.mFont = this.mApplet.mRes.mLineFontSmall;
        this.mReturnButton.mLabel = "Click to return to game";
        this.mReturnButton.SetColors(Advertisement.mButtonColors);
        this.mReturnButton.Resize(251, 346, 228, 24);
        this.mReturnButton.SetVisible(false);
        this.mPaintItBlack = true;
    }
    
    public void ButtonPress(final int n) {
        this.mApplet.PlaySound(9);
    }
    
    public void ButtonMouseEnter(final int n) {
    }
    
    public static int TouchHack() {
        return 1;
    }
    
    static {
        Advertisement.BUTTON_DOWNLOAD = 0;
        Advertisement.BUTTON_RETURN = 1;
        Advertisement.TEXTY = 146;
        mButtonColors = new int[][] { new int[3], { 178, 1, 0 }, { 248, 3, 0 }, { 178, 1, 0 }, { 255, 255, 128 } };
        DOLLAR_POSITIONS = new int[] { 152, 185, 210, 243, 280, 319 };
    }
    
    public void ButtonDepress(final int n) {
        if (n == Advertisement.BUTTON_RETURN) {
            this.mApplet.CloseAd();
        }
        if (n == Advertisement.BUTTON_DOWNLOAD) {
            this.mApplet.StatsGroupBegin("Ad");
            this.mApplet.StatsValue("Clicked");
            this.mApplet.StatsGroupEnd();
            try {
                String parameter = this.mApplet.getParameter("adUrl");
                if (parameter == null) {
                    parameter = "http://www.popcap.com/rocketmania.php";
                }
                this.mApplet.getAppletContext().showDocument(new URL(parameter), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
        widgetManager.AddWidget(this.mDownloadButton);
        widgetManager.AddWidget(this.mReturnButton);
    }
    
    public void ButtonDownTick(final int n) {
    }
    
    public void ButtonMouseLeave(final int n) {
    }
}
