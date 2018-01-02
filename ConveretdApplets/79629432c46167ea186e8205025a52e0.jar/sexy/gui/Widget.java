// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Insets;

public class Widget
{
    public int mX;
    public int mY;
    public int mWidth;
    public int mHeight;
    public boolean mDirty;
    public boolean mVisible;
    public boolean mMouseVisible;
    public boolean mTransient;
    public boolean mDisabled;
    public boolean mHasFocus;
    public boolean mIsDown;
    public boolean mIsOver;
    public WidgetManager mWidgetManager;
    public SexyColor[] mColors;
    public Insets mMouseInsets;
    public boolean mDoFinger;
    public int mUpdateCnt;
    public boolean mHasTransparencies;
    public boolean mHasAlpha;
    
    public void MouseEnter() {
        this.mIsOver = true;
        if (this.mDoFinger) {
            this.ShowFinger(true);
        }
    }
    
    public boolean HasTransparencies() {
        return this.mHasTransparencies;
    }
    
    public boolean Contains(final int n, final int n2) {
        return n >= this.mX && n < this.mX + this.mWidth && n2 >= this.mY && n2 < this.mY + this.mHeight;
    }
    
    public void WriteNumberFromStrip(final SexyGraphics sexyGraphics, final int i, final int n, final int n2, final SexyImage sexyImage, final int n3) {
        int n4 = 10;
        int n5 = 1;
        while (i >= n4) {
            ++n5;
            n4 *= 10;
        }
        if (i == 0) {
            n4 = 10;
        }
        final int n6 = sexyImage.GetWidth() / 10;
        for (int j = 0; j < n5; ++j) {
            n4 /= 10;
            final int n7 = i / n4 % 10;
            final SexyGraphics sexyGraphics2 = new SexyGraphics(sexyGraphics);
            sexyGraphics2.ClipRect(n + j * (n6 + n3), n2, n6, sexyImage.GetHeight());
            sexyGraphics2.DrawImage(sexyImage, n + j * (n6 + n3) - n7 * n6, n2);
        }
    }
    
    public void Update() {
        ++this.mUpdateCnt;
    }
    
    public int WriteWordWrapped(final SexyGraphics sexyGraphics, final Rectangle rectangle, final String s, int getHeight, final int n) {
        int getAscent = sexyGraphics.GetFont().GetAscent();
        if (getHeight == -1) {
            getHeight = sexyGraphics.GetFont().GetHeight();
        }
        String s2 = "";
        int i = 0;
        while (i < s.length()) {
            int n2;
            for (n2 = i; n2 < s.length() && s.charAt(n2) == ' '; ++n2) {}
            final int index = s.indexOf("\n", n2);
            int n3 = s.indexOf(" ", n2);
            if (n3 == -1) {
                n3 = s.length();
            }
            if (index != -1 && index < n3) {
                n3 = index;
            }
            if (index == n2) {
                this.WriteString(sexyGraphics, s2, rectangle.x, rectangle.y + getAscent, rectangle.width, n);
                s2 = s.substring(n2, n3);
                getAscent += getHeight;
                ++i;
            }
            else {
                final String string = s2 + s.substring(i, n3);
                if (sexyGraphics.GetFont().StringWidth(string) > rectangle.width) {
                    this.WriteString(sexyGraphics, s2, rectangle.x, rectangle.y + getAscent, rectangle.width, n);
                    getAscent += getHeight;
                    s2 = s.substring(n2, n3);
                }
                else {
                    s2 = string;
                }
                i = n3;
            }
        }
        if (!s2.equals("")) {
            this.WriteString(sexyGraphics, s2, rectangle.x, rectangle.y + getAscent, rectangle.width, n);
            getAscent += getHeight;
        }
        return getAscent + sexyGraphics.GetFont().GetHeight() - sexyGraphics.GetFont().GetAscent() - getHeight;
    }
    
    public void ShowFinger(final boolean b) {
        if (b) {
            this.mWidgetManager.mApplet.setCursor(new Cursor(12));
            return;
        }
        this.mWidgetManager.mApplet.setCursor(new Cursor(0));
    }
    
    public Rectangle GetRect() {
        return new Rectangle(this.mX, this.mY, this.mWidth, this.mHeight);
    }
    
    public void SetTransient(final boolean b) {
        if (this.mTransient == b) {
            return;
        }
        if (b) {
            this.MarkDirtyFull();
            this.mTransient = true;
            return;
        }
        this.mTransient = false;
        this.MarkDirty();
    }
    
    public void WriteCenteredLine(final SexyGraphics sexyGraphics, final int n, final String s) {
        sexyGraphics.DrawString(s, (this.mWidth - sexyGraphics.GetFont().StringWidth(s)) / 2, n + sexyGraphics.GetFont().GetAscent());
    }
    
    public void SetColor(final int n, final SexyColor sexyColor) {
        this.mColors[n] = sexyColor;
    }
    
    public void WriteCenteredLine(final SexyGraphics sexyGraphics, final int n, final String s, final Color color, final Color color2) {
        final int stringWidth = sexyGraphics.GetFont().StringWidth(s);
        sexyGraphics.SetColor(color2);
        sexyGraphics.DrawString(s, (this.mWidth - stringWidth) / 2 + 1, n + sexyGraphics.GetFont().GetAscent() + 2);
        sexyGraphics.SetColor(color);
        sexyGraphics.DrawString(s, (this.mWidth - stringWidth) / 2, n + sexyGraphics.GetFont().GetAscent());
    }
    
    public void SetVisible(final boolean mVisible) {
        if (this.mVisible == mVisible) {
            return;
        }
        this.mVisible = mVisible;
        if (this.mVisible) {
            this.MarkDirty();
            return;
        }
        this.MarkDirtyFull();
    }
    
    public void MouseLeave() {
        this.mIsOver = false;
        if (this.mDoFinger) {
            this.ShowFinger(false);
        }
    }
    
    public void Resize(final int mx, final int my, final int mWidth, final int mHeight) {
        this.MarkDirtyFull();
        this.mX = mx;
        this.mY = my;
        this.mWidth = mWidth;
        this.mHeight = mHeight;
        this.MarkDirty();
    }
    
    public boolean Intersects(final Widget widget) {
        return this.GetRect().intersects(widget.GetRect());
    }
    
    public void SetColors(final int[][] array) {
        this.mColors = new SexyColor[array.length];
        for (int i = 0; i < array.length; ++i) {
            if (array[i].length == 3) {
                this.mColors[i] = new SexyColor(array[i][0], array[i][1], array[i][2]);
            }
            else {
                this.mColors[i] = new SexyColor(array[i][0], array[i][1], array[i][2], array[i][3]);
            }
        }
        this.MarkDirty();
    }
    
    public void WriteString(final SexyGraphics sexyGraphics, final String s, final int n, final int n2, final int n3, final int n4) {
        switch (n4) {
            case -1: {
                sexyGraphics.DrawString(s, n, n2);
            }
            case 0: {
                sexyGraphics.DrawString(s, n + (n3 - sexyGraphics.GetFont().StringWidth(s)) / 2, n2);
            }
            case 1: {
                sexyGraphics.DrawString(s, n + n3 - sexyGraphics.GetFont().StringWidth(s), n2);
            }
            default: {}
        }
    }
    
    public void Move(final int n, final int n2) {
        this.Resize(n, n2, this.mWidth, this.mHeight);
    }
    
    public void KeyDown(final int n, final boolean b, final boolean b2) {
    }
    
    public void ButtonMouseLeave(final int n) {
    }
    
    public void RemovedFromManager(final WidgetManager widgetManager) {
        this.mWidgetManager = null;
    }
    
    public void LostFocus() {
        this.mHasFocus = false;
    }
    
    public int GetWordWrappedHeight(final SexyGraphics sexyGraphics, final int n, final String s, final int n2) {
        final SexyGraphics sexyGraphics2 = new SexyGraphics(sexyGraphics);
        sexyGraphics2.ClipRect(0, 0, 0, 0);
        return this.WriteWordWrapped(sexyGraphics2, new Rectangle(0, 0, n, 0), s, n2, -1);
    }
    
    public Rectangle GetInsetRect() {
        if (this.mMouseInsets == null) {
            return new Rectangle(this.mX, this.mY, this.mWidth, this.mHeight);
        }
        return new Rectangle(this.mX + this.mMouseInsets.left, this.mY + this.mMouseInsets.top, this.mWidth - this.mMouseInsets.left - this.mMouseInsets.right, this.mHeight - this.mMouseInsets.top - this.mMouseInsets.bottom);
    }
    
    public void Draw(final SexyGraphics sexyGraphics) {
    }
    
    public Widget(final WidgetManager mWidgetManager) {
        this.mDirty = true;
        this.mVisible = true;
        this.mMouseVisible = true;
        this.mWidgetManager = mWidgetManager;
    }
    
    public Widget() {
        this.mDirty = true;
        this.mVisible = true;
        this.mMouseVisible = true;
    }
    
    public void SetDisabled(final boolean b) {
        this.mWidgetManager.SetDisabled(this, b);
    }
    
    public void MouseUp(final int n, final int n2, final int n3) {
        this.MouseUp(n, n2);
        this.mIsDown = false;
    }
    
    public void MouseUp(final int n, final int n2) {
        this.mIsDown = false;
    }
    
    public void ButtonMouseEnter(final int n) {
    }
    
    public void KeyUp(final int n, final boolean b, final boolean b2) {
    }
    
    public void MarkDirtyFull() {
        if (this.mWidgetManager == null) {
            return;
        }
        this.mWidgetManager.MarkDirtyFull(this);
    }
    
    public boolean WantsFocus() {
        return false;
    }
    
    public void GotFocus() {
        this.mHasFocus = true;
    }
    
    public void MouseDown(final int n, final int n2, final int n3) {
        this.mIsDown = true;
    }
    
    public void AddedToManager(final WidgetManager mWidgetManager) {
        this.mWidgetManager = mWidgetManager;
    }
    
    public int GetNumDigits(final int i) {
        int n = 10;
        int n2 = 1;
        while (i >= n) {
            ++n2;
            n *= 10;
        }
        return n2;
    }
    
    public void MarkDirty() {
        if (this.mWidgetManager == null) {
            return;
        }
        this.mWidgetManager.MarkDirty(this);
    }
    
    public void MouseDrag(final int n, final int n2) {
    }
    
    public void MouseMove(final int n, final int n2) {
    }
}
