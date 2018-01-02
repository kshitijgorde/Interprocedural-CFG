// 
// Decompiled by Procyon v0.5.30
// 

package rocketmania;

import java.net.URL;
import sexy.gui.SexyColor;
import java.awt.Color;
import sexy.gui.SexyGraphics;
import sexy.gui.WidgetManager;
import nc.particle.ParticleElement;
import sexy.gui.OutlineButtonWidget;
import sexy.gui.ButtonListener;
import sexy.gui.Widget;

public class BuyMe extends Widget implements ButtonListener
{
    static final int skTouchHack = 1;
    RocketManiaApplet mApplet;
    OutlineButtonWidget mDownloadButton;
    static int BUTTON_DOWNLOAD;
    static int BUYMEXCENTRE;
    static int BUYMEYCENTRE;
    static int BUYMEWIDTH;
    static int BUYMEHEIGHT;
    static int BUYMEBUTTONWIDTH;
    static int BUYMEBUTTONHEIGHT;
    static int DELUXEX;
    static int DELUXEY;
    static int[][] skTwinklePos;
    static int[] skTwinkleTime;
    static int skTwinkleCycle;
    int mTwinkleTime;
    ParticleElement[] mTwinkle;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        widgetManager.RemoveWidget(this.mDownloadButton);
    }
    
    public void Update() {
        super.Update();
        final Board mBoard = this.mApplet.mBoard;
        if (mBoard != null) {
            if (mBoard.mRand.Next() % 75 == 0) {
                Fireworks.AddFirework(this.mApplet, mBoard.mParticleSystem, mBoard.mParticleDatabase, mBoard.mRand.Next() % 10, 60);
            }
            mBoard.mParticleSystem.Update(0.02f);
            mBoard.MarkDirty();
            this.mTwinkleTime = super.mUpdateCnt % BuyMe.skTwinkleCycle;
            for (int i = 0; i < BuyMe.skTwinkleTime.length; ++i) {
                if (this.mTwinkle[i] != null) {
                    this.mTwinkle[i].Update(mBoard.mParticleDatabase.mTwinkle, 0.02f);
                    if (this.mTwinkle[i].isDead()) {
                        this.mTwinkle[i] = null;
                    }
                }
                if (this.mTwinkleTime == BuyMe.skTwinkleTime[i]) {
                    (this.mTwinkle[i] = new ParticleElement(mBoard.mParticleDatabase.mTwinkle)).Setup(mBoard.mParticleDatabase.mTwinkle, BuyMe.DELUXEX + BuyMe.skTwinklePos[i][0], BuyMe.DELUXEY + BuyMe.skTwinklePos[i][1], 0.0f, 0.0f, 1.0f);
                }
            }
        }
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        sexyGraphics.SetColor(new Color(120, 40, 90));
        sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
        sexyGraphics.SetColor(new SexyColor(200, 200, 200));
        sexyGraphics.FillRect(0, 0, super.mWidth, 2);
        sexyGraphics.FillRect(0, 0, 2, super.mHeight);
        sexyGraphics.FillRect(0, super.mHeight - 2, super.mWidth, 2);
        sexyGraphics.FillRect(super.mWidth - 2, 0, 2, super.mHeight);
        if (this.mApplet.mRes.mImageAd[5] != null && this.mApplet.mRes.mImageAd[5].IsReady()) {
            sexyGraphics.SetColor(Color.white);
            new SpacedFont(sexyGraphics, this.mApplet.mRes.mLineFont, 0).DrawString("Go", 56, 40);
            sexyGraphics.DrawImage(this.mApplet.mRes.mImageAd[5], BuyMe.DELUXEX, BuyMe.DELUXEY);
            for (int i = 0; i < BuyMe.skTwinkleTime.length; ++i) {
                if (this.mTwinkle[i] != null) {
                    sexyGraphics.SetDrawMode(1);
                    this.mTwinkle[i].Render(sexyGraphics, this.mApplet.mBoard.mParticleDatabase.mTwinkle, new SexyColor(255, 255, 255));
                    sexyGraphics.SetDrawMode(0);
                }
            }
        }
        else {
            sexyGraphics.SetColor(Color.white);
            new SpacedFont(sexyGraphics, this.mApplet.mRes.mLineFont, 0).DrawString("Go Deluxe", BuyMe.BUYMEWIDTH / 2, 30);
        }
        final SpacedFont spacedFont = new SpacedFont(sexyGraphics, this.mApplet.mRes.mLineFontSmall, 0);
        sexyGraphics.SetColor(new Color(255, 255, 255));
        spacedFont.DrawString("Well done! You have completed", BuyMe.BUYMEWIDTH / 2, 73);
        spacedFont.DrawString("the web version of Rocket Mania!", BuyMe.BUYMEWIDTH / 2, 91);
        sexyGraphics.SetColor(new Color(255, 235, 30));
        spacedFont.DrawString("Try the Deluxe Version with", BuyMe.BUYMEWIDTH / 2, 121);
        spacedFont.SetJustification(0);
        spacedFont.DrawString("Unlimited Levels!", 60, 142);
        spacedFont.DrawString("2 New Game Modes!", 60, 160);
        spacedFont.DrawString("Deluxe Graphics!", 60, 178);
        spacedFont.DrawString("Deluxe Sound & Music!", 60, 196);
        spacedFont.SetJustification(1);
        sexyGraphics.SetColorizeImages(true);
        int n = 0;
        do {
            final double sin = Math.sin(0.1 * ((10 * (6 - n) + super.mUpdateCnt) % 64));
            sexyGraphics.SetColor(new SexyColor(255, (int)(191.0 + 63.0 * sin), (int)(223.0 + 31.0 * sin)));
            sexyGraphics.DrawImage(this.mApplet.mRes.GetImage(30), 40, 139 + 18 * n - 13 + 3);
        } while (++n < 4);
        sexyGraphics.SetColorizeImages(false);
        spacedFont.DrawString("Upgrade all your rockets to", BuyMe.BUYMEWIDTH / 2, 253);
        spacedFont.DrawString("DRAGON ROCKETS!", BuyMe.BUYMEWIDTH / 2, 271);
        sexyGraphics.SetColor(new Color(255, 255, 255));
        spacedFont.DrawString("Play full screen and play offline!", BuyMe.BUYMEWIDTH / 2, 223);
    }
    
    public BuyMe(final RocketManiaApplet mApplet) {
        super(mApplet.mWidgetManager);
        this.mApplet = mApplet;
        this.Resize(BuyMe.BUYMEXCENTRE - BuyMe.BUYMEWIDTH / 2, BuyMe.BUYMEYCENTRE - BuyMe.BUYMEHEIGHT / 2, BuyMe.BUYMEWIDTH, BuyMe.BUYMEHEIGHT);
        this.mDownloadButton = new OutlineButtonWidget(super.mWidgetManager, BuyMe.BUTTON_DOWNLOAD, this);
        this.mDownloadButton.mOutlineSize = 2;
        this.mDownloadButton.mDoFinger = true;
        this.mDownloadButton.mFont = this.mApplet.mRes.mLineFontSmall;
        this.mDownloadButton.mLabel = "Click to download FREE TRIAL!";
        this.mDownloadButton.SetColors(HintDialog.mContinueButtonColors);
        this.mDownloadButton.Resize(BuyMe.BUYMEXCENTRE - BuyMe.BUYMEBUTTONWIDTH / 2, BuyMe.BUYMEYCENTRE + BuyMe.BUYMEHEIGHT / 2 - BuyMe.BUYMEBUTTONHEIGHT - 10, BuyMe.BUYMEBUTTONWIDTH, BuyMe.BUYMEBUTTONHEIGHT);
        this.mTwinkle = new ParticleElement[BuyMe.skTwinkleTime.length];
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
        BuyMe.BUTTON_DOWNLOAD = 0;
        BuyMe.BUYMEXCENTRE = 294;
        BuyMe.BUYMEYCENTRE = 180;
        BuyMe.BUYMEWIDTH = 270;
        BuyMe.BUYMEHEIGHT = 322;
        BuyMe.BUYMEBUTTONWIDTH = 240;
        BuyMe.BUYMEBUTTONHEIGHT = 24;
        BuyMe.DELUXEX = 70;
        BuyMe.DELUXEY = 16;
        BuyMe.skTwinklePos = new int[][] { { 81, 3 }, { 142, 13 } };
        BuyMe.skTwinkleTime = new int[] { 85, 50 };
        BuyMe.skTwinkleCycle = 300;
    }
    
    public void ButtonDepress(final int n) {
        if (n == BuyMe.BUTTON_DOWNLOAD) {
            this.mApplet.StatsGroupBegin("Ad");
            this.mApplet.StatsValue("Clicked");
            this.mApplet.StatsGroupEnd();
            try {
                String s = this.mApplet.getParameter("limitUrl");
                if (s == null) {
                    s = this.mApplet.getParameter("adUrl");
                    if (s == null) {
                        s = "http://www.popcap.com/rocketmania.php";
                    }
                }
                this.mApplet.getAppletContext().showDocument(new URL(s), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
        widgetManager.AddWidget(this.mDownloadButton);
    }
    
    public void ButtonDownTick(final int n) {
    }
    
    public void ButtonMouseLeave(final int n) {
    }
}
