// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.Color;

public class ButtonWidget extends Widget
{
    public int mId;
    public String mLabel;
    public SexyFont mFont;
    public SexyImage mButtonImage;
    public SexyImage mOverImage;
    public SexyImage mDownImage;
    public SexyImage mDisabledImage;
    public boolean mInverted;
    public boolean mNoDraw;
    public int mImageAlpha;
    ButtonListener mButtonListener;
    
    public void MouseEnter() {
        if (!super.mDisabled) {
            this.mButtonListener.ButtonMouseEnter(this.mId);
        }
        super.MouseEnter();
        if (super.mIsDown || this.mOverImage != null) {
            this.MarkDirtyFull();
        }
    }
    
    public boolean IsButtonDown() {
        return super.mIsDown && super.mIsOver && !super.mDisabled;
    }
    
    public void Update() {
        if (super.mIsDown && super.mIsOver && !super.mDisabled) {
            this.mButtonListener.ButtonDownTick(this.mId);
        }
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        if (this.mNoDraw) {
            return;
        }
        final boolean b = (super.mIsDown && super.mIsOver && !super.mDisabled) ^ this.mInverted;
        if (this.mButtonImage == null && this.mDownImage == null) {
            sexyGraphics.SetColor(new Color(212, 212, 212));
            sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
            sexyGraphics.SetFont(this.mFont);
            final int n = (super.mWidth - sexyGraphics.GetFont().StringWidth(this.mLabel)) / 2;
            final int n2 = (super.mHeight - sexyGraphics.GetFont().GetHeight()) / 2 + sexyGraphics.GetFont().GetAscent();
            if (b) {
                sexyGraphics.SetColor(Color.black);
                sexyGraphics.FillRect(0, 0, super.mWidth - 1, 1);
                sexyGraphics.FillRect(0, 0, 1, super.mHeight - 1);
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.FillRect(0, super.mHeight - 1, super.mWidth, 1);
                sexyGraphics.FillRect(super.mWidth - 1, 0, 1, super.mHeight);
                sexyGraphics.SetColor(new Color(132, 132, 132));
                sexyGraphics.FillRect(1, 1, super.mWidth - 3, 1);
                sexyGraphics.FillRect(1, 1, 1, super.mHeight - 3);
                if (this.mLabel != null) {
                    sexyGraphics.SetColor(Color.black);
                    sexyGraphics.DrawString(this.mLabel, n + 1, n2 + 1);
                }
            }
            else {
                sexyGraphics.SetColor(new Color(255, 255, 255));
                sexyGraphics.FillRect(0, 0, super.mWidth - 1, 1);
                sexyGraphics.FillRect(0, 0, 1, super.mHeight - 1);
                sexyGraphics.SetColor(Color.black);
                sexyGraphics.FillRect(0, super.mHeight - 1, super.mWidth, 1);
                sexyGraphics.FillRect(super.mWidth - 1, 0, 1, super.mHeight);
                sexyGraphics.SetColor(new Color(132, 132, 132));
                sexyGraphics.FillRect(1, super.mHeight - 2, super.mWidth - 2, 1);
                sexyGraphics.FillRect(super.mWidth - 2, 1, 1, super.mHeight - 2);
                if (this.mLabel != null) {
                    sexyGraphics.SetColor(Color.black);
                    sexyGraphics.DrawString(this.mLabel, n, n2);
                }
            }
        }
        else {
            sexyGraphics.SetColorizeImages(true);
            sexyGraphics.SetColor(new SexyColor(255, 255, 255, this.mImageAlpha));
            if (!b) {
                if (super.mDisabled && this.mDisabledImage != null) {
                    sexyGraphics.DrawImage(this.mDisabledImage, 0, 0);
                }
                else if ((super.mIsOver || super.mIsDown) && this.mOverImage != null) {
                    sexyGraphics.DrawImage(this.mOverImage, 0, 0);
                }
                else if (this.mButtonImage != null) {
                    sexyGraphics.DrawImage(this.mButtonImage, 0, 0);
                }
            }
            else if (this.mDownImage != null) {
                sexyGraphics.DrawImage(this.mDownImage, 0, 0);
            }
            else if (this.mOverImage != null) {
                sexyGraphics.DrawImage(this.mOverImage, 1, 1);
            }
            else {
                sexyGraphics.DrawImage(this.mButtonImage, 1, 1);
            }
            sexyGraphics.SetColorizeImages(false);
        }
    }
    
    public ButtonWidget(final WidgetManager widgetManager, final int mId, final ButtonListener mButtonListener) {
        super(widgetManager);
        this.mLabel = "";
        this.mImageAlpha = 255;
        this.mId = mId;
        this.mButtonListener = mButtonListener;
        this.mFont = widgetManager.mApplet.CreateFont("SansSerif", 0, 12);
    }
    
    public void SetDisabled(final boolean b) {
        super.SetDisabled(b);
        if (this.mDisabledImage != null) {
            this.MarkDirty();
        }
    }
    
    public void MouseUp(final int n, final int n2) {
        super.MouseUp(n, n2);
        if (super.mIsOver && !super.mDisabled) {
            this.mButtonListener.ButtonDepress(this.mId);
        }
        this.MarkDirtyFull();
    }
    
    public void MouseLeave() {
        if (!super.mDisabled) {
            this.mButtonListener.ButtonMouseLeave(this.mId);
        }
        super.MouseLeave();
        if (super.mIsDown || this.mOverImage != null) {
            this.MarkDirtyFull();
        }
    }
    
    public void MouseDown(final int n, final int n2, final int n3) {
        super.MouseDown(n, n2, n3);
        if (!super.mDisabled) {
            this.mButtonListener.ButtonPress(this.mId);
        }
        this.MarkDirtyFull();
    }
}
