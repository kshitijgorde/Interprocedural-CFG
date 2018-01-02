// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.Rectangle;

public class OutlineButtonWidget extends ButtonWidget
{
    public static final int COLOR_OUTLINE = 0;
    public static final int COLOR_FILL = 1;
    public static final int COLOR_FILL_OVER = 2;
    public static final int COLOR_FILL_DOWN = 3;
    public static final int COLOR_TEXT = 4;
    public static final int COLOR_TEXT_OVER = 5;
    public static final int COLOR_TEXT_DOWN = 6;
    static final int[][] mInitialColors;
    public int mLineSpacing;
    public int mOutlineSize;
    public boolean mHideFill;
    
    public void MouseEnter() {
        super.MouseEnter();
        this.MarkDirtyFull();
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
        sexyGraphics.SetFont(super.mFont);
        if (!this.mHideFill) {
            if (super.mIsDown && super.mIsOver) {
                sexyGraphics.SetColor(super.mColors[3]);
            }
            else if (super.mIsDown || super.mIsOver) {
                sexyGraphics.SetColor(super.mColors[2]);
            }
            else {
                sexyGraphics.SetColor(super.mColors[1]);
            }
            sexyGraphics.FillRect(0, 0, super.mWidth, super.mHeight);
        }
        if (super.mIsDown && super.mIsOver && super.mColors.length > 6) {
            sexyGraphics.SetColor(super.mColors[6]);
        }
        else if ((super.mIsDown || super.mIsOver) && super.mColors.length > 5) {
            sexyGraphics.SetColor(super.mColors[5]);
        }
        else {
            sexyGraphics.SetColor(super.mColors[4]);
        }
        if (super.mLabel != null) {
            final Rectangle rectangle = new Rectangle(0, 0, super.mWidth, super.mHeight);
            final int getWordWrappedHeight = this.GetWordWrappedHeight(sexyGraphics, super.mWidth, super.mLabel, this.mLineSpacing);
            rectangle.y = (super.mHeight - getWordWrappedHeight) / 2;
            rectangle.height = getWordWrappedHeight;
            this.WriteWordWrapped(sexyGraphics, rectangle, super.mLabel, this.mLineSpacing, 0);
        }
        if (super.mColors[0] != null) {
            sexyGraphics.SetColor(super.mColors[0]);
            for (int i = 0; i < this.mOutlineSize; ++i) {
                sexyGraphics.DrawRect(i, i, super.mWidth - i * 2 - 1, super.mHeight - i * 2 - 1);
            }
        }
    }
    
    public OutlineButtonWidget(final WidgetManager widgetManager, final int n, final ButtonListener buttonListener) {
        super(widgetManager, n, buttonListener);
        this.mLineSpacing = -1;
        this.mOutlineSize = 2;
        this.SetColors(OutlineButtonWidget.mInitialColors);
        super.mFont = widgetManager.mApplet.CreateFont("SansSerif", 1, 14);
    }
    
    static {
        mInitialColors = new int[][] { { 192, 192, 192 }, new int[3], { 64, 64, 64 }, { 128, 128, 128 }, { 255, 255, 255 } };
    }
    
    public void MouseLeave() {
        super.MouseLeave();
        this.MarkDirtyFull();
    }
}
