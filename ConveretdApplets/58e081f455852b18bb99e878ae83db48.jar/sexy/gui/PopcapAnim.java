// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.Color;
import sexy.res.ResLoader;

public class PopcapAnim extends Widget
{
    static final int STATE_LOADING = 0;
    static final int STATE_INIT = 1;
    static final int STATE_COBRAND_ON = 2;
    static final int STATE_COBRAND_WAIT = 3;
    static final int STATE_COBRAND_OFF = 4;
    static final int STATE_LOGO_MOVING = 5;
    static final int STATE_FLASH = 6;
    static final int STATE_CONVERT = 7;
    static final int STATE_LOGO_DARKEN = 8;
    static final int STATE_GAMES_URL = 9;
    static final int STATE_SHOW_BAR = 10;
    static final int STATE_END_DELAY = 11;
    static final int STATE_DONE = 12;
    static final int IMAGE_POP = 0;
    static final int IMAGE_CAP = 1;
    static final int IMAGE_LOGO = 2;
    static final int IMAGE_GAMES = 3;
    static final int IMAGE_URL = 4;
    static final int IMAGE_LOADER_EMPTY = 5;
    static final int IMAGE_LOADER_FULL = 6;
    static final String[] mImageNames;
    SexyImage[] mImages;
    static final int LOGO_WIDTH = 107;
    static final int LOGO_HEIGHT = 120;
    int mState;
    int mWordsOffset;
    int mGamesOffset;
    int mURLOffset;
    int mBarOffset;
    SexyFont mLoadingFont;
    int mDelay;
    int mUpdateCnt;
    double mConvFactor;
    ResLoader mRes;
    public String mLoadingString;
    String mCobrandImageName;
    SexyImage mCobrandImage;
    int mCobrandOffset;
    int[] mWordPixels;
    int[] mLogoPixels;
    int[] mConvLogoPixels;
    SexyImage mConvLogoImage;
    int[] mLitLogoPixels;
    double[] mLogoHSL;
    
    public void Update() {
        ++this.mUpdateCnt;
        switch (this.mState) {
            case 0: {
                boolean b = true;
                for (int i = 0; i < PopcapAnim.mImageNames.length; ++i) {
                    if (this.mImages[i].HasFailed()) {
                        super.mWidgetManager.mApplet.WriteDebug("Failed to load '" + PopcapAnim.mImageNames[i] + "'");
                        super.mWidgetManager.mApplet.FatalError("loading");
                    }
                    if (!this.mImages[i].IsReady()) {
                        b = false;
                    }
                    if (this.mCobrandImage != null) {
                        if (this.mCobrandImage.HasFailed()) {
                            super.mWidgetManager.mApplet.WriteDebug("Failed to load '" + this.mCobrandImageName + "'");
                            super.mWidgetManager.mApplet.FatalError("loading", "cbimage");
                        }
                        if (!this.mCobrandImage.IsReady()) {
                            b = false;
                        }
                    }
                }
                if (b) {
                    this.mLogoPixels = new int[12840];
                    this.mRes.DrawToPixels(this.mLogoPixels, 0, 0, 107, 120, this.mRes.GetPixels(this.mImages[2]), 107, 120);
                    this.mWordPixels = new int[12840];
                    final int[] getPixels = this.mRes.GetPixels(this.mImages[0]);
                    final int[] getPixels2 = this.mRes.GetPixels(this.mImages[1]);
                    this.mRes.DrawToPixels(this.mWordPixels, 9, 10, 107, 120, getPixels, 88, 59);
                    this.mRes.DrawToPixels(this.mWordPixels, 20, 56, 107, 120, getPixels2, 81, 59);
                    this.mLogoHSL = this.mRes.PixelToHSL(this.mLogoPixels);
                    final double[] array = this.mLogoHSL.clone();
                    int n = 0;
                    do {
                        final double[] array2 = array;
                        final int n2 = n * 3 + 2;
                        array2[n2] *= 2.0;
                    } while (++n < 12840);
                    this.mLitLogoPixels = this.mRes.HSLToPixel(array);
                    super.mWidgetManager.mApplet.ResetUpdateTimer();
                    this.mState = 1;
                    return;
                }
                break;
            }
            case 1: {
                this.mGamesOffset = super.mHeight / 2;
                this.mURLOffset = super.mHeight / 2 + 120;
                this.mWordsOffset = super.mWidth;
                this.mBarOffset = super.mHeight / 2 - 100;
                this.mState = 5;
                if (this.mCobrandImage == null) {
                    this.mState = 5;
                    return;
                }
                this.mCobrandOffset = super.mHeight + this.mCobrandImage.GetHeight() / 2;
                this.mState = 2;
            }
            case 2: {
                this.mCobrandOffset -= 16;
                if (this.mCobrandOffset < super.mHeight / 2) {
                    this.mCobrandOffset = super.mHeight / 2;
                    this.mDelay = 100;
                    this.mState = 3;
                }
                this.MarkDirty();
            }
            case 3: {
                if (this.mDelay > 0) {
                    --this.mDelay;
                    return;
                }
                this.mState = 4;
            }
            case 4: {
                this.mCobrandOffset -= 16;
                if (this.mCobrandOffset < -this.mCobrandImage.GetHeight() / 2) {
                    this.mState = 5;
                }
                this.MarkDirty();
            }
            case 5: {
                this.MarkDirty();
                this.mWordsOffset -= 10;
                if (this.mWordsOffset < 0) {
                    this.mWordsOffset = 0;
                    this.mDelay = 3;
                    this.mState = 6;
                    return;
                }
                break;
            }
            case 6: {
                this.MarkDirty();
                if (this.mDelay > 0) {
                    --this.mDelay;
                    return;
                }
                this.mState = 7;
            }
            case 7: {
                this.MarkDirty();
                this.mConvFactor += 0.08;
                if (this.mConvFactor >= 1.0) {
                    this.mConvFactor = 0.0;
                    this.mState = 8;
                    return;
                }
                break;
            }
            case 8: {
                this.MarkDirty();
                this.mConvFactor += 0.05;
                if (this.mConvFactor >= 1.0) {
                    this.mState = 9;
                    return;
                }
                break;
            }
            case 9: {
                this.MarkDirty();
                this.mGamesOffset = Math.max(this.mGamesOffset - 8, 0);
                this.mURLOffset = Math.max(this.mURLOffset - 10, 0);
                if (this.mGamesOffset == 0 && this.mURLOffset == 0) {
                    this.mState = 10;
                    return;
                }
                break;
            }
            case 10: {
                this.MarkDirty();
                this.mBarOffset -= 4;
                if (this.mBarOffset < 0) {
                    this.mBarOffset = 0;
                    this.mDelay = 60;
                    this.mState = 11;
                    return;
                }
                break;
            }
            case 11: {
                if (this.mUpdateCnt % 5 == 0) {
                    this.MarkDirty();
                }
                if (this.mDelay > 0) {
                    --this.mDelay;
                    return;
                }
                this.mState = 12;
            }
            default: {
                if (this.mUpdateCnt % 5 == 0) {
                    this.MarkDirty();
                    return;
                }
                break;
            }
        }
    }
    
    public boolean IsDone() {
        return this.mState == 12;
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        if (this.mState == 0) {
            sexyGraphics.SetColor(Color.black);
            sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
            return;
        }
        if (this.mState == 6) {
            sexyGraphics.SetColor(Color.white);
            sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
            return;
        }
        sexyGraphics.SetColor(Color.black);
        sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
        final int n = super.mWidth / 2;
        final int n2 = super.mHeight / 2;
        if (this.mState == 2 || this.mState == 3 || this.mState == 4) {
            sexyGraphics.DrawImage(this.mCobrandImage, n - this.mCobrandImage.GetWidth() / 2, this.mCobrandOffset - this.mCobrandImage.GetHeight() / 2);
        }
        if (this.mState == 7 || this.mState == 8) {
            int[] array;
            if (this.mState == 7) {
                array = this.mRes.FadeBetween(this.mWordPixels, this.mLitLogoPixels, 107, 120, this.mConvFactor);
            }
            else {
                final double[] array2 = this.mLogoHSL.clone();
                int n3 = 0;
                do {
                    final double[] array3 = array2;
                    final int n4 = n3 * 3 + 2;
                    array3[n4] *= 2.0 - this.mConvFactor;
                } while (++n3 < 12840);
                array = this.mRes.HSLToPixel(array2);
            }
            System.arraycopy(array, 0, this.mConvLogoImage.GetBits(), 0, 12840);
            sexyGraphics.DrawImage(this.mConvLogoImage, n - this.mWordsOffset - 44 - 8 - 3 - 9, n2 - 32 - 53 - 10);
        }
        else if (this.mState >= 7) {
            sexyGraphics.DrawImage(this.mImages[2], n - this.mWordsOffset - 44 - 8 - 3 - 9, n2 - 32 - 53 - 10);
        }
        else if (this.mState >= 5) {
            sexyGraphics.DrawImage(this.mImages[0], n - this.mWordsOffset - 44 - 8 - 3, n2 - 32 - 53);
            sexyGraphics.DrawImage(this.mImages[1], n + this.mWordsOffset - 40 - 8 + 3, n2 - 32 - 7);
        }
        if (this.mState >= 9) {
            sexyGraphics.DrawImage(this.mImages[3], n - 8 + 50, n2 - 32 - 52 - this.mGamesOffset);
            sexyGraphics.DrawImage(this.mImages[4], n - 56 - 8, n2 - 32 + 58 + this.mURLOffset);
        }
        if (this.mState >= 10) {
            final int n5 = n - this.mImages[5].GetWidth() / 2;
            final int n6 = n2 - 32 + 120 + this.mBarOffset;
            sexyGraphics.DrawImage(this.mImages[5], n5, n6);
            final SexyGraphics sexyGraphics2 = new SexyGraphics(sexyGraphics);
            sexyGraphics2.ClipRect(n5, n6, (int)(this.mRes.GetProgressPercent() * this.mImages[5].GetWidth() / 100.0), this.mImages[5].GetHeight());
            sexyGraphics2.DrawImage(this.mImages[6], n5, n6);
            sexyGraphics.SetFont(this.mLoadingFont);
            sexyGraphics.SetColor(new Color(41, 239, 255));
            sexyGraphics.DrawString(this.mLoadingString, n - sexyGraphics.GetFont().StringWidth(this.mLoadingString) / 2, n2 - 32 + 118 + this.mBarOffset);
        }
    }
    
    public PopcapAnim(final WidgetManager widgetManager, final String s) {
        super(widgetManager);
        this.mState = 0;
        this.mLoadingFont = widgetManager.mApplet.CreateFont("SansSerif", 1, 14);
        (this.mConvLogoImage = new SexyImage()).Create(107, 120);
        this.mRes = super.mWidgetManager.mApplet.mResLoader;
        this.mLoadingString = "Now Loading " + s + "... Please wait...";
    }
    
    public void GetImages() {
        this.mImages = new SexyImage[PopcapAnim.mImageNames.length];
        for (int i = 0; i < PopcapAnim.mImageNames.length; ++i) {
            this.mImages[i] = this.mRes.AsyncGetImage("images/" + PopcapAnim.mImageNames[i]);
        }
        this.mCobrandImageName = super.mWidgetManager.mApplet.getParameter("cbimage");
        if (this.mCobrandImageName != null) {
            this.mCobrandImage = this.mRes.AsyncGetImage("images/" + this.mCobrandImageName);
        }
        this.mState = 0;
    }
    
    public boolean IsLoaded() {
        return this.mState != 0;
    }
    
    static {
        mImageNames = new String[] { "zz_pop2.gif", "zz_cap2.gif", "zz_logo3.gif", "zz_games1.gif", "zz_url1.gif", "zz_loaderbar2.gif", "zz_loaderbar1.gif" };
    }
}
