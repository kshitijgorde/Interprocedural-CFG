// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Vector;

public class OutlineDialogWidget extends Widget
{
    public static final int BUTTONS_NONE = 0;
    public static final int BUTTONS_YES_NO = 1;
    public static final int BUTTONS_FOOTER = 2;
    public static final int COLOR_OUTLINE = 0;
    public static final int COLOR_HEADER = 1;
    public static final int COLOR_LINES = 2;
    public static final int COLOR_FOOTER = 3;
    public static final int COLOR_BACKGROUND = 4;
    public static final int COLOR_DROP_SHADOW = 5;
    static final int[][] mInitialColors;
    protected int mButtonMode;
    protected String mHeader;
    protected String mFooter;
    protected String mLines;
    public SexyFont mHeaderFont;
    public SexyFont mLinesFont;
    public ButtonWidget mYesButton;
    public ButtonWidget mNoButton;
    public boolean mNoTrans;
    public boolean mHideOutline;
    public boolean mLighter;
    public int mDropShadowSize;
    public int mPadLine;
    private ButtonListener mButtonListener;
    public Vector mButton1KeyMapping;
    public Vector mButton2KeyMapping;
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        super.RemovedFromManager(widgetManager);
        if (this.mYesButton != null) {
            widgetManager.RemoveWidget(this.mYesButton);
        }
        if (this.mNoButton != null) {
            widgetManager.RemoveWidget(this.mNoButton);
        }
    }
    
    public int GetPreferredHeight(final int n) {
        final int n2 = 72;
        final SexyImage sexyImage = new SexyImage();
        sexyImage.Create(1, 1);
        final SexyGraphics sexyGraphics = new SexyGraphics(sexyImage);
        sexyGraphics.SetFont(this.mLinesFont);
        int n3 = n2 + this.GetWordWrappedHeight(sexyGraphics, n - this.mDropShadowSize - 16, this.mLines, this.mLinesFont.GetHeight() + this.mPadLine);
        if (this.mFooter != null) {
            n3 += 20;
        }
        if (this.mButtonMode == 1) {
            n3 += 28;
        }
        return n3 + this.mDropShadowSize;
    }
    
    public void SetButtonColors(final int[][] array) {
        if (this.mYesButton != null) {
            this.mYesButton.SetColors(array);
        }
        if (this.mNoButton != null) {
            this.mNoButton.SetColors(array);
        }
    }
    
    public OutlineDialogWidget(final WidgetManager widgetManager, final ButtonListener mButtonListener, final String s, final String s2, final String s3, final int n) {
        super(widgetManager);
        this.mButton1KeyMapping = new Vector();
        this.mButton2KeyMapping = new Vector();
        int n2 = 1;
        for (int i = 0; i < s2.length(); ++i) {
            if (s2.charAt(i) == '\n') {
                ++n2;
            }
        }
        final String[] array = new String[n2];
        this.Init(widgetManager, mButtonListener, s, s2, s3, n);
        this.mButtonListener = mButtonListener;
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        if (super.mColors.length > 4) {
            sexyGraphics.SetColor(super.mColors[4]);
            sexyGraphics.FillRect(0, 0, super.mWidth - this.mDropShadowSize, super.mHeight - this.mDropShadowSize);
        }
        if (!this.mHideOutline) {
            sexyGraphics.SetColor(super.mColors[0]);
            sexyGraphics.DrawRect(0, 0, super.mWidth - 1 - this.mDropShadowSize, super.mHeight - 1 - this.mDropShadowSize);
            sexyGraphics.DrawRect(1, 1, super.mWidth - 3 - this.mDropShadowSize, super.mHeight - 3 - this.mDropShadowSize);
        }
        sexyGraphics.SetFont(this.mHeaderFont);
        sexyGraphics.SetColor(super.mColors[1]);
        if (this.mHeader != null) {
            sexyGraphics.DrawString(this.mHeader, (super.mWidth - this.mDropShadowSize - sexyGraphics.GetFont().StringWidth(this.mHeader)) / 2, 10 + sexyGraphics.GetFont().GetAscent());
        }
        sexyGraphics.SetFont(this.mLinesFont);
        sexyGraphics.SetColor(super.mColors[2]);
        final int writeWordWrapped = this.WriteWordWrapped(sexyGraphics, new Rectangle(8, 40, super.mWidth - this.mDropShadowSize - 16, super.mHeight - 40), this.mLines, this.mLinesFont.GetHeight() + this.mPadLine, 0);
        if (this.mButtonMode != 2 && this.mFooter != null) {
            sexyGraphics.SetFont(this.mHeaderFont);
            sexyGraphics.SetColor(super.mColors[3]);
            sexyGraphics.DrawString(this.mFooter, (super.mWidth - this.mDropShadowSize - sexyGraphics.GetFont().StringWidth(this.mFooter)) / 2, writeWordWrapped + 50 + sexyGraphics.GetFont().GetAscent());
        }
        if (this.mDropShadowSize > 0) {
            if (super.mColors.length > 5) {
                sexyGraphics.SetColor(super.mColors[5]);
            }
            else {
                sexyGraphics.SetColor(Color.black);
            }
            sexyGraphics.FillRect(super.mWidth - this.mDropShadowSize, this.mDropShadowSize, this.mDropShadowSize, super.mHeight - this.mDropShadowSize);
            sexyGraphics.FillRect(this.mDropShadowSize, super.mHeight - this.mDropShadowSize, super.mWidth - this.mDropShadowSize * 2, this.mDropShadowSize);
        }
    }
    
    static {
        mInitialColors = new int[][] { { 192, 192, 192 }, { 255, 255, 255 }, { 192, 192, 192 }, { 255, 255, 255 }, { 0, 0, 0, 220 }, { 0, 0, 0, 128 } };
    }
    
    public void Resize(final int n, final int n2, final int n3, final int n4) {
        super.Resize(n, n2, n3, n4);
        if (this.mButtonMode == 1) {
            this.mYesButton.Resize(super.mX + (super.mWidth - this.mDropShadowSize) / 9, super.mY + super.mHeight - this.mDropShadowSize - 40, (super.mWidth - this.mDropShadowSize) / 3, 20);
            this.mNoButton.Resize(super.mX + (super.mWidth - this.mDropShadowSize) * 5 / 9, super.mY + super.mHeight - this.mDropShadowSize - 40, (super.mWidth - this.mDropShadowSize) / 3, 20);
            return;
        }
        if (this.mButtonMode == 2) {
            this.mYesButton.Resize(super.mX + (super.mWidth - this.mDropShadowSize) / 9, super.mY + super.mHeight - this.mDropShadowSize - 40, (super.mWidth - this.mDropShadowSize) * 7 / 9, 20);
        }
    }
    
    public void AddedToManager(final WidgetManager widgetManager) {
        super.AddedToManager(widgetManager);
        if (this.mYesButton != null) {
            widgetManager.AddWidget(this.mYesButton);
        }
        if (this.mNoButton != null) {
            widgetManager.AddWidget(this.mNoButton);
        }
    }
    
    void Init(final WidgetManager widgetManager, final ButtonListener buttonListener, final String mHeader, final String mLines, final String mFooter, final int mButtonMode) {
        this.SetColors(OutlineDialogWidget.mInitialColors);
        this.mHeaderFont = widgetManager.mApplet.CreateFont("SansSerif", 1, 18);
        this.mLinesFont = widgetManager.mApplet.CreateFont("SansSerif", 1, 14);
        this.mHeader = mHeader;
        this.mLines = mLines;
        this.mFooter = mFooter;
        this.mButtonMode = mButtonMode;
        if (this.mButtonMode == 1) {
            this.mYesButton = new OutlineButtonWidget(super.mWidgetManager, 20, buttonListener);
            this.mYesButton.mLabel = widgetManager.mApplet.GetString("DIALOG_BUTTON_YES");
            this.mYesButton.mDoFinger = true;
            this.mNoButton = new OutlineButtonWidget(super.mWidgetManager, 21, buttonListener);
            this.mNoButton.mLabel = widgetManager.mApplet.GetString("DIALOG_BUTTON_NO");
            this.mNoButton.mDoFinger = true;
        }
        else if (this.mButtonMode == 2) {
            this.mYesButton = new OutlineButtonWidget(super.mWidgetManager, 20, buttonListener);
            this.mYesButton.mLabel = this.mFooter;
            this.mYesButton.mDoFinger = true;
        }
        super.mHasAlpha = true;
    }
    
    public void KeyDown(final int n, final boolean b, final boolean b2) {
        if (this.mButton1KeyMapping != null) {
            for (int i = 0; i < this.mButton1KeyMapping.size(); ++i) {
                if ((char)this.mButton1KeyMapping.elementAt(i) == n) {
                    this.mButtonListener.ButtonDepress(20);
                    break;
                }
            }
        }
        if (this.mButton2KeyMapping != null) {
            for (int j = 0; j < this.mButton2KeyMapping.size(); ++j) {
                if ((char)this.mButton2KeyMapping.elementAt(j) == n) {
                    this.mButtonListener.ButtonDepress(21);
                    return;
                }
            }
        }
    }
}
