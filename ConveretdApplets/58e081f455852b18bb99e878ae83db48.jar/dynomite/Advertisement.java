// 
// Decompiled by Procyon v0.5.30
// 

package dynomite;

import java.net.URL;
import sexy.gui.SexyColor;
import sexy.gui.SexyGraphics;
import sexy.gui.WidgetManager;
import sexy.gui.SexyFont;
import sexy.gui.OutlineButtonWidget;
import sexy.gui.ButtonListener;
import sexy.gui.Widget;

public class Advertisement extends Widget implements ButtonListener
{
    DynomiteApplet mApplet;
    int mSideline;
    boolean mSlideSideline;
    int mTitleFade;
    int mSlideText;
    int mSlideFeatures;
    int mSlideAd;
    int mCurrentAdCountdown;
    int mCurrentAd;
    int mFlicker;
    OutlineButtonWidget mDownloadButton;
    OutlineButtonWidget mReturnButton;
    static int BUTTON_DOWNLOAD;
    static int BUTTON_RETURN;
    SexyFont mSmallFont;
    SexyFont mTinyFont;
    static final int[][] mButtonColors;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        widgetManager.RemoveWidget(this.mDownloadButton);
        widgetManager.RemoveWidget(this.mReturnButton);
    }
    
    public void Update() {
        if (this.mSlideSideline) {
            if (this.mSideline < 30) {
                ++this.mSideline;
            }
            if (this.mSideline == 30) {
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
        if (this.mSlideText <= 250) {
            this.mSlideText = 250;
        }
        this.mSlideFeatures -= 7;
        if (this.mSlideFeatures <= 250) {
            this.mSlideFeatures = 250;
        }
        this.mSlideAd += 8;
        if (this.mSlideAd > 10) {
            this.mSlideAd = 10;
        }
        if (--this.mCurrentAdCountdown <= 0) {
            if (++this.mCurrentAd > 3) {
                this.mCurrentAd = 0;
            }
            this.mCurrentAdCountdown = 200;
        }
        if (--this.mFlicker < 0) {
            this.mFlicker = 100;
        }
        this.MarkDirty();
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        sexyGraphics.SetColor(new SexyColor(0, 0, 0, 255));
        sexyGraphics.FillRect(0, 0, 440, 330);
        sexyGraphics.SetColor(new SexyColor(239, 201, 105, 255));
        sexyGraphics.FillRect(0, -10, 440, this.mSideline);
        sexyGraphics.FillRect(0, 330 - this.mSideline, 440, this.mSideline);
        sexyGraphics.SetColor(new SexyColor(119, 100, 52, 255));
        sexyGraphics.FillRect(0, this.mSideline - 10, 440, 2);
        sexyGraphics.FillRect(0, 328 - this.mSideline, 440, 2);
        if (this.mTitleFade > 0 && this.mTitleFade < 255) {
            sexyGraphics.SetColorizeImages(true);
            sexyGraphics.SetColor(new SexyColor(255, 255, 255, this.mTitleFade));
            sexyGraphics.DrawImage(this.mApplet.mRes.mImageAdTitle, 0, 0);
            sexyGraphics.SetColorizeImages(false);
            return;
        }
        if (this.mTitleFade == 255) {
            sexyGraphics.DrawImage(this.mApplet.mRes.mImageAdTitle, 0, 0);
            sexyGraphics.SetFont(this.mTinyFont);
            sexyGraphics.SetColor(new SexyColor(0, 0, 0, 255));
            sexyGraphics.DrawString("Play it offline, anytime!", 310, 15);
            sexyGraphics.SetColor(new SexyColor(239, 201, 155, 255));
            sexyGraphics.DrawString("Dynomite is available for all", this.mSlideText, 40);
            sexyGraphics.DrawString("Windows PCs!  Play whenever", this.mSlideText, 52);
            sexyGraphics.DrawString("you like, whether or not you're", this.mSlideText, 64);
            sexyGraphics.DrawString("connected to the internet!", this.mSlideText, 76);
            sexyGraphics.DrawString("Plus, get enhanced graphics,", this.mSlideText, 95);
            sexyGraphics.DrawString("great sound, and more!", this.mSlideText, 107);
            sexyGraphics.SetColor(new SexyColor(175, 255, 175, 255));
            sexyGraphics.FillRect(this.mSlideFeatures - 8, 124, 5, 5);
            sexyGraphics.DrawString("New game modes!  Try Time", this.mSlideFeatures, 130);
            sexyGraphics.DrawString("Trial and the Fossil Challenge!", this.mSlideFeatures, 142);
            sexyGraphics.FillRect(this.mSlideFeatures - 8, 154, 5, 5);
            sexyGraphics.DrawString("Save your games!  Continue", this.mSlideFeatures, 160);
            sexyGraphics.DrawString("later, or retry a tricky puzzle!", this.mSlideFeatures, 172);
            sexyGraphics.FillRect(this.mSlideFeatures - 8, 184, 5, 5);
            sexyGraphics.DrawString("Dynamite soundtrack by Skaven!", this.mSlideFeatures, 190);
            sexyGraphics.FillRect(this.mSlideFeatures - 8, 202, 5, 5);
            sexyGraphics.DrawString("Save your high scores!", this.mSlideFeatures, 208);
            sexyGraphics.FillRect(this.mSlideFeatures - 8, 220, 5, 5);
            sexyGraphics.DrawString("Play ten new Stomped levels,", this.mSlideFeatures, 226);
            sexyGraphics.DrawString("or make your own in the", this.mSlideFeatures, 238);
            sexyGraphics.DrawString("Stomped Puzzle Editor!", this.mSlideFeatures, 250);
            if (this.mFlicker < 50) {
                sexyGraphics.SetColor(new SexyColor(175, 255, 175, 255));
            }
            else {
                sexyGraphics.SetColor(new SexyColor(255, 255, 100, 255));
            }
            sexyGraphics.FillRect(this.mSlideFeatures - 8, 262, 5, 5);
            sexyGraphics.DrawString("Only 2mb!  Download while you", this.mSlideFeatures, 268);
            sexyGraphics.DrawString("play your next game!", this.mSlideFeatures, 280);
            sexyGraphics.DrawImage(this.mApplet.mRes.mImageAd[this.mCurrentAd], this.mSlideAd, 81);
        }
    }
    
    public Advertisement(final DynomiteApplet mApplet) {
        super(mApplet.mWidgetManager);
        this.mApplet = mApplet;
        this.mTitleFade = 0;
        this.mSideline = 0;
        this.mSlideAd = -250;
        this.mSlideSideline = true;
        this.mSlideText = 440;
        this.mSlideFeatures = 500;
        this.mSmallFont = this.mApplet.mTinyFont;
        this.mTinyFont = this.mApplet.mAdTinyFont;
        this.mDownloadButton = new OutlineButtonWidget(super.mWidgetManager, Advertisement.BUTTON_DOWNLOAD, this);
        this.mDownloadButton.mDoFinger = true;
        this.mDownloadButton.mFont = this.mSmallFont;
        this.mDownloadButton.mLabel = "Click to Download Free Trial";
        this.mDownloadButton.SetColors(Advertisement.mButtonColors);
        this.mDownloadButton.Resize(5, 304, 213, 22);
        this.mDownloadButton.SetVisible(false);
        this.mReturnButton = new OutlineButtonWidget(super.mWidgetManager, Advertisement.BUTTON_RETURN, this);
        this.mReturnButton.mDoFinger = true;
        this.mReturnButton.mFont = this.mSmallFont;
        this.mReturnButton.mLabel = "Click here to Return to Game";
        this.mReturnButton.SetColors(Advertisement.mButtonColors);
        this.mReturnButton.Resize(222, 304, 213, 22);
        this.mReturnButton.SetVisible(false);
    }
    
    public void ButtonPress(final int n) {
        this.mApplet.PlaySound(5);
    }
    
    static {
        Advertisement.BUTTON_DOWNLOAD = 0;
        Advertisement.BUTTON_RETURN = 1;
        mButtonColors = new int[][] { new int[3], { 250, 211, 110 }, { 255, 255, 255 }, { 250, 211, 110 }, new int[3] };
    }
    
    public void ButtonDepress(final int n) {
        if (n == Advertisement.BUTTON_RETURN) {
            this.mApplet.CloseAd();
        }
        if (n == Advertisement.BUTTON_DOWNLOAD) {
            try {
                String parameter = this.mApplet.getParameter("adUrl");
                if (parameter == null) {
                    parameter = "http://www.popcap.com/dynomite.php";
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
}
