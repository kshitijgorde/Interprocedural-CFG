// 
// Decompiled by Procyon v0.5.30
// 

package sexy.gui;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Enumeration;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.util.Vector;
import java.awt.Image;

public class WidgetManager
{
    public Widget mDefaultTab;
    public SexyApplet mApplet;
    public Image mImage;
    public SexyImage mTransientImage;
    public boolean mLastHadTransients;
    public int mWidth;
    public int mHeight;
    public Widget mPopupCommandWidget;
    Vector mWidgets;
    boolean mHasFocus;
    Widget mFocusWidget;
    Widget mLastDownWidget;
    int mLastDownButtonId;
    public Widget mOverWidget;
    Widget mBaseModalWidget;
    public int mLastMouseX;
    public int mLastMouseY;
    public boolean mMouseIn;
    int mNewWidth;
    int mNewHeight;
    Component mParent;
    MemoryImageSource mMemoryImageSource;
    SexyImageProducer mImageProducer;
    SexyImage mSexyScreenImage;
    
    Image GetScreenImage() {
        if (this.mWidth != this.mNewWidth || this.mHeight != this.mNewHeight) {
            this.mWidth = this.mNewWidth;
            this.mHeight = this.mNewHeight;
            final int[] mBits = new int[this.mWidth * this.mHeight];
            this.mSexyScreenImage = new SexyImage();
            this.mSexyScreenImage.mBits = mBits;
            this.mSexyScreenImage.mWidth = this.mWidth;
            this.mSexyScreenImage.mHeight = this.mHeight;
            this.mSexyScreenImage.SetImageMode(false, false);
            this.mImageProducer = new SexyImageProducer(this.mWidth, this.mHeight, new DirectColorModel(32, 16711680, 65280, 255), mBits);
            this.mImage = this.mApplet.createImage(this.mImageProducer);
            if (this.mTransientImage != null) {
                this.mTransientImage = null;
            }
        }
        return this.mImage;
    }
    
    public void SetBaseModal(final Widget mBaseModalWidget) {
        this.mBaseModalWidget = mBaseModalWidget;
        if (this.mOverWidget != null && this.IsBelow(this.mOverWidget, this.mBaseModalWidget)) {
            this.mOverWidget.MouseLeave();
            this.mOverWidget = null;
        }
        if (this.mLastDownWidget != null && this.IsBelow(this.mLastDownWidget, this.mBaseModalWidget)) {
            this.mLastDownWidget.MouseUp(this.mLastMouseX - this.mLastDownWidget.mX, this.mLastMouseY - this.mLastDownWidget.mY, this.mLastDownButtonId);
            this.mLastDownWidget = null;
        }
    }
    
    public void DisableWidget(final Widget widget) {
        if (this.mOverWidget == widget) {
            final Widget mOverWidget = this.mOverWidget;
            this.mOverWidget = null;
            mOverWidget.MouseLeave();
        }
        if (this.mLastDownWidget == widget) {
            final Widget mLastDownWidget = this.mLastDownWidget;
            this.mLastDownWidget = null;
            mLastDownWidget.MouseUp(this.mLastMouseX - mLastDownWidget.mX, this.mLastMouseY - mLastDownWidget.mY, this.mLastDownButtonId);
        }
        if (this.mFocusWidget == widget) {
            final Widget mFocusWidget = this.mFocusWidget;
            this.mFocusWidget = null;
            mFocusWidget.LostFocus();
        }
        if (this.mBaseModalWidget == widget) {
            this.mBaseModalWidget = null;
        }
    }
    
    public Widget GetWidgetAt(final int n, final int n2) {
        int n3 = 0;
        int i = this.mWidgets.size() - 1;
        while (i >= 0) {
            final Widget widget = this.mWidgets.elementAt(i);
            if (n3 == 0 && widget.mVisible && widget.mMouseVisible && widget.GetInsetRect().contains(n, n2)) {
                if (!widget.mDisabled) {
                    return widget;
                }
                return null;
            }
            else {
                if (widget == this.mBaseModalWidget) {
                    n3 = 1;
                }
                --i;
            }
        }
        return null;
    }
    
    public void FreeResources() {
        if (this.mImage != null) {
            this.mApplet.FreeImage(this.mImage);
            this.mImage = null;
        }
    }
    
    public void Resize(final int mNewWidth, final int mNewHeight) {
        this.mNewWidth = mNewWidth;
        this.mNewHeight = mNewHeight;
    }
    
    public void MarkAllDirty() {
        final Enumeration<Widget> elements = this.mWidgets.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().mDirty = true;
        }
    }
    
    public boolean IsBelow(final Widget widget, final Widget widget2) {
        if (widget2 == null || !this.mWidgets.contains(widget2)) {
            return false;
        }
        for (int i = 0; i < this.mWidgets.size(); ++i) {
            if (this.mWidgets.elementAt(i) == widget2) {
                return false;
            }
            if (this.mWidgets.elementAt(i) == widget) {
                return true;
            }
        }
        return false;
    }
    
    public void AddWidget(final Widget widget) {
        this.mWidgets.addElement(widget);
        widget.AddedToManager(this);
        this.MarkDirty(widget);
    }
    
    public void BringToFront(final Widget widget) {
        this.mWidgets.removeElement(widget);
        this.mWidgets.addElement(widget);
    }
    
    public void PutInfront(final Widget widget, final Widget widget2) {
        this.mWidgets.removeElement(widget);
        this.mWidgets.insertElementAt(widget, this.mWidgets.indexOf(widget2) + 1);
    }
    
    public boolean keyDown(final int n, final boolean b, final boolean b2) {
        synchronized (this.mApplet) {
            if (n == 9 && b2) {
                if (this.mDefaultTab != null) {
                    this.mDefaultTab.KeyDown(n, b, b2);
                }
                // monitorexit(this.mApplet)
                return true;
            }
            if (this.mFocusWidget != null && !this.IsBelow(this.mFocusWidget, this.mBaseModalWidget)) {
                this.mFocusWidget.KeyDown(n, b, b2);
            }
        }
        // monitorexit(this.mApplet)
        return true;
    }
    
    public void SetFocus(final Widget mFocusWidget) {
        if (this.mFocusWidget != null) {
            this.mFocusWidget.LostFocus();
        }
        this.mFocusWidget = mFocusWidget;
        if (this.mHasFocus && this.mFocusWidget != null) {
            this.mFocusWidget.GotFocus();
        }
    }
    
    public void LostFocus() {
        if (this.mHasFocus) {
            this.mHasFocus = false;
            if (this.mFocusWidget != null) {
                this.mFocusWidget.LostFocus();
            }
        }
    }
    
    public void RemoveWidget(final Widget widget) {
        this.DisableWidget(widget);
        this.MarkDirtyFull(widget);
        this.mWidgets.removeElement(widget);
        widget.RemovedFromManager(this);
    }
    
    public void RemovePopupCommandWidget() {
        if (this.mPopupCommandWidget != null) {
            this.RemoveWidget(this.mPopupCommandWidget);
            this.mPopupCommandWidget = null;
        }
    }
    
    public boolean mouseExit(final int n, final int n2) {
        synchronized (this.mApplet) {
            this.mMouseIn = false;
            if (this.mOverWidget != null) {
                this.mOverWidget.MouseLeave();
                this.mOverWidget = null;
            }
        }
        // monitorexit(this.mApplet)
        return true;
    }
    
    public void Draw(final Graphics graphics) {
        if (this.mImage != null) {
            graphics.drawImage(this.mImage, 0, 0, null);
        }
    }
    
    public WidgetManager(final SexyApplet mApplet, final Component mParent) {
        this.mWidgets = new Vector();
        this.mHasFocus = true;
        this.mApplet = mApplet;
        this.mParent = mParent;
    }
    
    public void SetDisabled(final Widget widget, final boolean mDisabled) {
        if (mDisabled) {
            this.DisableWidget(widget);
        }
        widget.mDisabled = mDisabled;
        widget.MarkDirty();
        if (!mDisabled && widget.Contains(this.mLastMouseX, this.mLastMouseY)) {
            this.MousePosition(this.mLastMouseX, this.mLastMouseY);
        }
    }
    
    public void PutBehind(final Widget widget, final Widget widget2) {
        this.mWidgets.removeElement(widget);
        this.mWidgets.insertElementAt(widget, this.mWidgets.indexOf(widget2));
    }
    
    public boolean mouseUp(final int n, final int n2, final int n3) {
        synchronized (this.mApplet) {
            if (this.mLastDownWidget != null) {
                final Widget mLastDownWidget = this.mLastDownWidget;
                this.mLastDownWidget = null;
                mLastDownWidget.MouseUp(n - mLastDownWidget.mX, n2 - mLastDownWidget.mY, n3);
            }
            this.MousePosition(n, n2);
        }
        // monitorexit(this.mApplet)
        return true;
    }
    
    synchronized void UpdateFrame() {
        final Enumeration<Widget> elements = this.mWidgets.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().Update();
        }
    }
    
    public void SetPopupCommandWidget(final Widget mPopupCommandWidget) {
        this.AddWidget(this.mPopupCommandWidget = mPopupCommandWidget);
    }
    
    public boolean keyUp(final int n, final boolean b, final boolean b2) {
        synchronized (this.mApplet) {
            if (n == 9 && b2) {
                // monitorexit(this.mApplet)
                return true;
            }
            if (this.mFocusWidget != null && !this.IsBelow(this.mFocusWidget, this.mBaseModalWidget)) {
                this.mFocusWidget.KeyUp(n, b, b2);
            }
        }
        // monitorexit(this.mApplet)
        return true;
    }
    
    public void MarkDirtyFull(final Widget widget) {
        widget.mDirty = true;
        if (widget.mTransient) {
            return;
        }
        int n = -1;
        for (int i = 0; i < this.mWidgets.size(); ++i) {
            if (this.mWidgets.elementAt(i) == widget) {
                n = i;
                break;
            }
        }
        for (int j = n - 1; j >= 0; --j) {
            final Widget widget2 = this.mWidgets.elementAt(j);
            if (widget2.mVisible && !widget2.HasTransparencies() && !widget2.mHasAlpha && widget2.Contains(widget.mX, widget.mY) && widget2.Contains(widget.mX + widget.mWidth - 1, widget.mY + widget.mHeight - 1)) {
                widget2.MarkDirty();
                break;
            }
            if (widget2.Intersects(widget)) {
                this.MarkDirty(widget2);
            }
        }
        for (int k = n + 1; k < this.mWidgets.size(); ++k) {
            final Widget widget3 = this.mWidgets.elementAt(k);
            if (widget3.Intersects(widget)) {
                this.MarkDirty(widget3);
            }
        }
    }
    
    public void GotFocus() {
        if (!this.mHasFocus) {
            this.mHasFocus = true;
            if (this.mFocusWidget != null) {
                this.mFocusWidget.GotFocus();
            }
        }
    }
    
    public void finalize() {
        synchronized (this.mApplet) {
            this.FreeResources();
        }
        // monitorexit(this.mApplet)
    }
    
    public void BringToBack(final Widget widget) {
        this.mWidgets.removeElement(widget);
        this.mWidgets.insertElementAt(widget, 0);
    }
    
    public void MousePosition(final int mLastMouseX, final int mLastMouseY) {
        this.mLastMouseX = mLastMouseX;
        this.mLastMouseY = mLastMouseY;
        final Widget getWidget = this.GetWidgetAt(mLastMouseX, mLastMouseY);
        if (getWidget != this.mOverWidget) {
            if (this.mOverWidget != null) {
                this.mOverWidget.MouseLeave();
            }
            if ((this.mOverWidget = getWidget) != null) {
                getWidget.MouseEnter();
            }
        }
        if (getWidget != null) {
            getWidget.MouseMove(mLastMouseX - getWidget.mX, mLastMouseY - getWidget.mY);
        }
    }
    
    public boolean mouseDown(final int n, final int n2, final int n3) {
        synchronized (this.mApplet) {
            this.MousePosition(n, n2);
            if (this.mPopupCommandWidget != null && !this.mPopupCommandWidget.Contains(n, n2)) {
                this.RemovePopupCommandWidget();
            }
            final Widget getWidget = this.GetWidgetAt(n, n2);
            if (this.mLastDownWidget != null && this.mLastDownWidget != getWidget) {
                this.mLastDownWidget.MouseUp(n - this.mLastDownWidget.mX, n2 - this.mLastDownWidget.mY, this.mLastDownButtonId);
            }
            this.mLastDownWidget = getWidget;
            if (n3 < 0) {
                this.mLastDownButtonId = -1;
            }
            else {
                this.mLastDownButtonId = 1;
            }
            if (getWidget != null) {
                if (getWidget.WantsFocus()) {
                    this.SetFocus(getWidget);
                }
                getWidget.MouseDown(n - getWidget.mX, n2 - getWidget.mY, n3);
            }
        }
        // monitorexit(this.mApplet)
        return true;
    }
    
    public void MarkDirty(final Widget widget) {
        if (widget.mDirty) {
            return;
        }
        widget.mDirty = true;
        if (widget.mTransient) {
            return;
        }
        if (widget.mHasAlpha) {
            this.MarkDirtyFull(widget);
            return;
        }
        boolean b = false;
        final Enumeration<Widget> elements = this.mWidgets.elements();
        while (elements.hasMoreElements()) {
            final Widget widget2 = elements.nextElement();
            if (widget2.equals(widget)) {
                b = true;
            }
            else {
                if (!b || !widget2.Intersects(widget)) {
                    continue;
                }
                this.MarkDirty(widget2);
            }
        }
    }
    
    void DrawScreen() {
        this.GetScreenImage();
        if (this.mImage == null) {
            return;
        }
        boolean b = false;
        int n = 0;
        boolean mLastHadTransients = false;
        boolean b2 = false;
        for (int i = 0; i < this.mWidgets.size(); ++i) {
            final Widget widget = this.mWidgets.elementAt(i);
            if (widget.mDirty) {
                ++n;
            }
            if (widget.mTransient) {
                mLastHadTransients = true;
                if (widget.mDirty) {
                    b2 = true;
                }
            }
        }
        final SexyGraphics sexyGraphics = new SexyGraphics(this.mSexyScreenImage);
        if (n > 0 || this.mLastHadTransients != mLastHadTransients) {
            SexyGraphics sexyGraphics2;
            if (mLastHadTransients) {
                if (this.mTransientImage == null) {
                    (this.mTransientImage = new SexyImage()).Create(this.mWidth, this.mHeight);
                    this.mTransientImage.SetImageMode(false, false);
                }
                sexyGraphics2 = new SexyGraphics(this.mTransientImage);
            }
            else {
                sexyGraphics2 = sexyGraphics;
            }
            for (int j = 0; j < this.mWidgets.size(); ++j) {
                final Widget widget2 = this.mWidgets.elementAt(j);
                if (widget2.mTransient) {
                    break;
                }
                if ((widget2.mDirty || this.mLastHadTransients != mLastHadTransients) && widget2.mVisible) {
                    final SexyGraphics sexyGraphics3 = new SexyGraphics(sexyGraphics2);
                    sexyGraphics3.ClipRect(widget2.mX, widget2.mY, widget2.mWidth, widget2.mHeight);
                    sexyGraphics3.Translate(widget2.mX, widget2.mY);
                    widget2.Draw(sexyGraphics3);
                    sexyGraphics3.Translate(-widget2.mX, -widget2.mY);
                    ++n;
                    b = true;
                    widget2.mDirty = false;
                }
            }
        }
        if (mLastHadTransients && (n > 0 || b2 || !this.mLastHadTransients)) {
            sexyGraphics.DrawImage(this.mTransientImage, 0, 0);
            boolean b3 = false;
            for (int k = 0; k < this.mWidgets.size(); ++k) {
                final Widget widget3 = this.mWidgets.elementAt(k);
                if (widget3.mVisible && widget3.mTransient) {
                    b3 = true;
                }
                if (b3) {
                    final SexyGraphics sexyGraphics4 = new SexyGraphics(sexyGraphics);
                    sexyGraphics4.ClipRect(widget3.mX, widget3.mY, widget3.mWidth, widget3.mHeight);
                    sexyGraphics4.Translate(widget3.mX, widget3.mY);
                    widget3.Draw(sexyGraphics4);
                    sexyGraphics4.Translate(-widget3.mX, -widget3.mY);
                    ++n;
                    b = true;
                    widget3.mDirty = false;
                }
            }
        }
        this.mLastHadTransients = mLastHadTransients;
        if (b) {
            this.mImageProducer.update();
            this.mParent.repaint();
        }
    }
    
    public boolean mouseDrag(final int mLastMouseX, final int mLastMouseY) {
        synchronized (this.mApplet) {
            this.mLastMouseX = mLastMouseX;
            this.mLastMouseY = mLastMouseY;
            if (this.mOverWidget != null && this.mOverWidget != this.mLastDownWidget) {
                this.mOverWidget.MouseLeave();
                this.mOverWidget = null;
            }
            if (this.mLastDownWidget != null) {
                this.mLastDownWidget.MouseDrag(mLastMouseX - this.mLastDownWidget.mX, mLastMouseY - this.mLastDownWidget.mY);
                if (this.mLastDownWidget.Contains(mLastMouseX, mLastMouseY)) {
                    if (this.mOverWidget == null) {
                        (this.mOverWidget = this.mLastDownWidget).MouseEnter();
                    }
                }
                else if (this.mOverWidget != null) {
                    this.mOverWidget.MouseLeave();
                    this.mOverWidget = null;
                }
            }
        }
        // monitorexit(this.mApplet)
        return true;
    }
    
    public boolean mouseMove(final int n, final int n2) {
        synchronized (this.mApplet) {
            this.mMouseIn = true;
            this.MousePosition(n, n2);
        }
        // monitorexit(this.mApplet)
        return true;
    }
}
