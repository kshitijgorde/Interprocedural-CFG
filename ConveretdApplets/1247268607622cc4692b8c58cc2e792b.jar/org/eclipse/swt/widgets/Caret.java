// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.internal.win32.COMPOSITIONFORM;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.CANDIDATEFORM;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

public class Caret extends Widget
{
    Canvas parent;
    int x;
    int y;
    int width;
    int height;
    boolean moved;
    boolean resized;
    boolean isVisible;
    Image image;
    Font font;
    LOGFONT oldFont;
    
    public Caret(final Canvas parent, final int n) {
        super(parent, n);
        this.parent = parent;
        this.createWidget();
    }
    
    void createWidget() {
        this.isVisible = true;
        if (this.parent.getCaret() == null) {
            this.parent.setCaret(this);
        }
    }
    
    int defaultFont() {
        final int handle = this.parent.handle;
        final int immGetDefaultIMEWnd = OS.ImmGetDefaultIMEWnd(handle);
        int n = 0;
        if (immGetDefaultIMEWnd != 0) {
            n = OS.SendMessage(immGetDefaultIMEWnd, 49, 0, 0);
        }
        if (n == 0) {
            n = OS.SendMessage(handle, 49, 0, 0);
        }
        if (n == 0) {
            return this.parent.defaultFont();
        }
        return n;
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        if (this.image != null) {
            final Rectangle bounds = this.image.getBounds();
            return new Rectangle(this.x, this.y, bounds.width, bounds.height);
        }
        if (!OS.IsWinCE && this.width == 0) {
            final int[] array = { 0 };
            if (OS.SystemParametersInfo(8198, 0, array, 0)) {
                return new Rectangle(this.x, this.y, array[0], this.height);
            }
        }
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public Font getFont() {
        this.checkWidget();
        if (this.font == null) {
            return Font.win32_new(this.display, this.defaultFont());
        }
        return this.font;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    public Point getLocation() {
        this.checkWidget();
        return new Point(this.x, this.y);
    }
    
    public Canvas getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public Point getSize() {
        this.checkWidget();
        if (this.image != null) {
            final Rectangle bounds = this.image.getBounds();
            return new Point(bounds.width, bounds.height);
        }
        if (!OS.IsWinCE && this.width == 0) {
            final int[] array = { 0 };
            if (OS.SystemParametersInfo(8198, 0, array, 0)) {
                return new Point(array[0], this.height);
            }
        }
        return new Point(this.width, this.height);
    }
    
    public boolean getVisible() {
        this.checkWidget();
        return this.isVisible;
    }
    
    boolean hasFocus() {
        return this.parent.handle == OS.GetFocus();
    }
    
    boolean isFocusCaret() {
        return this.parent.caret == this && this.hasFocus();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.isVisible && this.parent.isVisible() && this.hasFocus();
    }
    
    void killFocus() {
        OS.DestroyCaret();
        this.restoreIMEFont();
    }
    
    void move() {
        this.moved = false;
        if (!OS.SetCaretPos(this.x, this.y)) {
            return;
        }
        this.resizeIME();
    }
    
    void resizeIME() {
        if (!OS.IsDBLocale) {
            return;
        }
        final POINT ptCurrentPos = new POINT();
        if (!OS.GetCaretPos(ptCurrentPos)) {
            return;
        }
        final int handle = this.parent.handle;
        final int immGetContext = OS.ImmGetContext(handle);
        final IME ime = this.parent.getIME();
        if (ime != null && ime.isInlineEnabled()) {
            final Point size = this.getSize();
            final CANDIDATEFORM candidateform = new CANDIDATEFORM();
            candidateform.dwStyle = 128;
            candidateform.ptCurrentPos = ptCurrentPos;
            OS.SetRect(candidateform.rcArea = new RECT(), ptCurrentPos.x, ptCurrentPos.y, ptCurrentPos.x + size.x, ptCurrentPos.y + size.y);
            OS.ImmSetCandidateWindow(immGetContext, candidateform);
        }
        else {
            final RECT rect = new RECT();
            OS.GetClientRect(handle, rect);
            final COMPOSITIONFORM compositionform = new COMPOSITIONFORM();
            compositionform.dwStyle = 1;
            compositionform.x = ptCurrentPos.x;
            compositionform.y = ptCurrentPos.y;
            compositionform.left = rect.left;
            compositionform.right = rect.right;
            compositionform.top = rect.top;
            compositionform.bottom = rect.bottom;
            OS.ImmSetCompositionWindow(immGetContext, compositionform);
        }
        OS.ImmReleaseContext(handle, immGetContext);
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this == this.parent.getCaret()) {
            this.parent.setCaret(null);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.parent = null;
        this.image = null;
        this.font = null;
        this.oldFont = null;
    }
    
    void resize() {
        this.resized = false;
        final int handle = this.parent.handle;
        OS.DestroyCaret();
        final int n = (this.image != null) ? this.image.handle : 0;
        int width = this.width;
        if (!OS.IsWinCE && this.image == null && width == 0) {
            final int[] array = { 0 };
            if (OS.SystemParametersInfo(8198, 0, array, 0)) {
                width = array[0];
            }
        }
        OS.CreateCaret(handle, n, width, this.height);
        OS.SetCaretPos(this.x, this.y);
        OS.ShowCaret(handle);
        this.move();
    }
    
    void restoreIMEFont() {
        if (!OS.IsDBLocale) {
            return;
        }
        if (this.oldFont == null) {
            return;
        }
        final int handle = this.parent.handle;
        final int immGetContext = OS.ImmGetContext(handle);
        OS.ImmSetCompositionFont(immGetContext, this.oldFont);
        OS.ImmReleaseContext(handle, immGetContext);
        this.oldFont = null;
    }
    
    public void setBounds(final int x, final int y, final int width, final int height) {
        this.checkWidget();
        final boolean b = this.x == x && this.y == y;
        final boolean b2 = this.width == width && this.height == height;
        if (b && b2) {
            return;
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        if (b2) {
            this.moved = true;
            if (this.isVisible && this.hasFocus()) {
                this.move();
            }
        }
        else {
            this.resized = true;
            if (this.isVisible && this.hasFocus()) {
                this.resize();
            }
        }
    }
    
    public void setBounds(final Rectangle rectangle) {
        if (rectangle == null) {
            this.error(4);
        }
        this.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    void setFocus() {
        final int handle = this.parent.handle;
        int handle2 = 0;
        if (this.image != null) {
            handle2 = this.image.handle;
        }
        int width = this.width;
        if (!OS.IsWinCE && this.image == null && width == 0) {
            final int[] array = { 0 };
            if (OS.SystemParametersInfo(8198, 0, array, 0)) {
                width = array[0];
            }
        }
        OS.CreateCaret(handle, handle2, width, this.height);
        this.move();
        this.setIMEFont();
        if (this.isVisible) {
            OS.ShowCaret(handle);
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (font != null && font.isDisposed()) {
            this.error(5);
        }
        this.font = font;
        if (this.hasFocus()) {
            this.setIMEFont();
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.image = image;
        if (this.isVisible && this.hasFocus()) {
            this.resize();
        }
    }
    
    void setIMEFont() {
        if (!OS.IsDBLocale) {
            return;
        }
        int n = 0;
        if (this.font != null) {
            n = this.font.handle;
        }
        if (n == 0) {
            n = this.defaultFont();
        }
        final int handle = this.parent.handle;
        final int immGetContext = OS.ImmGetContext(handle);
        if (this.oldFont == null) {
            this.oldFont = (OS.IsUnicode ? new LOGFONTW() : new LOGFONTA());
            if (!OS.ImmGetCompositionFont(immGetContext, this.oldFont)) {
                this.oldFont = null;
            }
        }
        final LOGFONT logfont = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
        if (OS.GetObject(n, LOGFONT.sizeof, logfont) != 0) {
            OS.ImmSetCompositionFont(immGetContext, logfont);
        }
        OS.ImmReleaseContext(handle, immGetContext);
    }
    
    public void setLocation(final int x, final int y) {
        this.checkWidget();
        if (this.x == x && this.y == y) {
            return;
        }
        this.x = x;
        this.y = y;
        this.moved = true;
        if (this.isVisible && this.hasFocus()) {
            this.move();
        }
    }
    
    public void setLocation(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setLocation(point.x, point.y);
    }
    
    public void setSize(final int width, final int height) {
        this.checkWidget();
        if (this.width == width && this.height == height) {
            return;
        }
        this.width = width;
        this.height = height;
        this.resized = true;
        if (this.isVisible && this.hasFocus()) {
            this.resize();
        }
    }
    
    public void setSize(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setSize(point.x, point.y);
    }
    
    public void setVisible(final boolean isVisible) {
        this.checkWidget();
        if (isVisible == this.isVisible) {
            return;
        }
        this.isVisible = isVisible;
        final int handle = this.parent.handle;
        if (OS.GetFocus() != handle) {
            return;
        }
        if (!this.isVisible) {
            OS.HideCaret(handle);
        }
        else {
            if (this.resized) {
                this.resize();
            }
            else if (this.moved) {
                this.move();
            }
            OS.ShowCaret(handle);
        }
    }
}
