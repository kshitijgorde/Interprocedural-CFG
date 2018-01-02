// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.win32.DRAWITEMSTRUCT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.Image;

public class Label extends Control
{
    String text;
    Image image;
    static final int MARGIN = 4;
    static boolean IMAGE_AND_TEXT;
    static final int LabelProc;
    static final TCHAR LabelClass;
    
    static {
        Label.IMAGE_AND_TEXT = false;
        LabelClass = new TCHAR(0, "STATIC", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Label.LabelClass, wndclass);
        LabelProc = wndclass.lpfnWndProc;
    }
    
    public Label(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.text = "";
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 1)) {
            switch (n2) {
                case 515: {
                    return OS.DefWindowProc(n, n2, n3, n4);
                }
            }
        }
        return OS.CallWindowProc(Label.LabelProc, n, n2, n3, n4);
    }
    
    static int checkStyle(int checkBits) {
        checkBits |= 0x80000;
        if ((checkBits & 0x2) != 0x0) {
            checkBits = Widget.checkBits(checkBits, 512, 256, 0, 0, 0, 0);
            return Widget.checkBits(checkBits, 8, 4, 32, 0, 0, 0);
        }
        return Widget.checkBits(checkBits, 16384, 16777216, 131072, 0, 0, 0);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int n4 = 0;
        final int borderWidth = this.getBorderWidth();
        if ((this.style & 0x2) != 0x0) {
            final int getSystemMetrics = OS.GetSystemMetrics(5);
            int n5;
            int n6;
            if ((this.style & 0x100) != 0x0) {
                n5 = 64;
                n6 = getSystemMetrics * 2;
            }
            else {
                n5 = getSystemMetrics * 2;
                n6 = 64;
            }
            if (n != -1) {
                n5 = n;
            }
            if (n2 != -1) {
                n6 = n2;
            }
            return new Point(n5 + borderWidth * 2, n6 + borderWidth * 2);
        }
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        boolean b2 = true;
        final boolean b3 = (getWindowLong & 0xD) == 0xD;
        if (b3 && this.image != null) {
            final Rectangle bounds = this.image.getBounds();
            n3 += bounds.width;
            n4 += bounds.height;
            if (Label.IMAGE_AND_TEXT) {
                if (this.text.length() != 0) {
                    n3 += 4;
                }
            }
            else {
                b2 = false;
            }
        }
        if (b2) {
            final int getDC = OS.GetDC(this.handle);
            final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            final int selectObject = OS.SelectObject(getDC, sendMessage);
            final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
            if (getWindowTextLength == 0) {
                final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
                OS.GetTextMetrics(getDC, textmetric);
                n4 = Math.max(n4, textmetric.tmHeight);
            }
            else {
                final RECT rect = new RECT();
                int n7 = 9280;
                if ((this.style & 0x40) != 0x0 && n != -1) {
                    n7 |= 0x10;
                    rect.right = Math.max(0, n - n3);
                }
                final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
                OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
                OS.DrawText(getDC, tchar, getWindowTextLength, rect, n7);
                n3 += rect.right - rect.left;
                n4 = Math.max(n4, rect.bottom - rect.top);
            }
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
        }
        if (n != -1) {
            n3 = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        int n8 = n3 + borderWidth * 2;
        final int n9 = n4 + borderWidth * 2;
        if (OS.IsWinCE && !b3) {
            n8 += 2;
        }
        return new Point(n8, n9);
    }
    
    void createHandle() {
        super.createHandle();
        this.state |= 0x100;
    }
    
    public int getAlignment() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return 0;
        }
        if ((this.style & 0x4000) != 0x0) {
            return 16384;
        }
        if ((this.style & 0x1000000) != 0x0) {
            return 16777216;
        }
        if ((this.style & 0x20000) != 0x0) {
            return 131072;
        }
        return 16384;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public String getText() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return "";
        }
        return this.text;
    }
    
    boolean mnemonicHit(final char c) {
        for (Composite composite = this.parent; composite != null; composite = composite.parent) {
            Control[] getChildren;
            int n;
            for (getChildren = composite._getChildren(), n = 0; n < getChildren.length && getChildren[n] != this; ++n) {}
            if (++n < getChildren.length && getChildren[n].setFocus()) {
                return true;
            }
        }
        return false;
    }
    
    boolean mnemonicMatch(final char c) {
        final char mnemonic = this.findMnemonic(this.getText());
        return mnemonic != '\0' && Character.toUpperCase(c) == Character.toUpperCase(mnemonic);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.text = null;
        this.image = null;
    }
    
    public void setAlignment(final int n) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if ((n & 0x1024000) == 0x0) {
            return;
        }
        this.style &= 0xFEFDBFFF;
        this.style |= (n & 0x1024000);
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        if ((getWindowLong & 0xD) != 0xD) {
            int n2 = getWindowLong & 0xFFFFFFF0;
            if ((this.style & 0x4000) != 0x0) {
                if ((this.style & 0x40) != 0x0) {
                    n2 |= 0x0;
                }
                else {
                    n2 |= 0xC;
                }
            }
            if ((this.style & 0x1000000) != 0x0) {
                n2 |= 0x1;
            }
            if ((this.style & 0x20000) != 0x0) {
                n2 |= 0x2;
            }
            OS.SetWindowLong(this.handle, -16, n2);
        }
        OS.InvalidateRect(this.handle, null, true);
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.image = image;
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        if ((getWindowLong & 0xD) != 0xD) {
            OS.SetWindowLong(this.handle, -16, (getWindowLong & 0xFFFFFFF0) | 0xD);
        }
        OS.InvalidateRect(this.handle, null, true);
    }
    
    public void setText(String withCrLf) {
        this.checkWidget();
        if (withCrLf == null) {
            this.error(4);
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (withCrLf.equals(this.text)) {
            return;
        }
        this.text = withCrLf;
        if (this.image == null || !Label.IMAGE_AND_TEXT) {
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            int n = getWindowLong & 0xFFFFFFF2;
            if ((this.style & 0x4000) != 0x0) {
                if ((this.style & 0x40) != 0x0) {
                    n |= 0x0;
                }
                else {
                    n |= 0xC;
                }
            }
            if ((this.style & 0x1000000) != 0x0) {
                n |= 0x1;
            }
            if ((this.style & 0x20000) != 0x0) {
                n |= 0x2;
            }
            if (getWindowLong != n) {
                OS.SetWindowLong(this.handle, -16, n);
            }
        }
        withCrLf = Display.withCrLf(withCrLf);
        OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), withCrLf, true));
        if (OS.COMCTL32_MAJOR < 6 && this.findImageControl() != null) {
            OS.InvalidateRect(this.handle, null, true);
        }
    }
    
    int widgetExtStyle() {
        final int n = super.widgetExtStyle() & 0xFFFFFDFF;
        if ((this.style & 0x800) != 0x0) {
            return n | 0x20000;
        }
        return n;
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x100;
        if ((this.style & 0x2) != 0x0) {
            return n | 0xD;
        }
        if (OS.WIN32_VERSION >= OS.VERSION(5, 0) && (this.style & 0x40) != 0x0) {
            n |= 0x2000;
        }
        if ((this.style & 0x1000000) != 0x0) {
            return n | 0x1;
        }
        if ((this.style & 0x20000) != 0x0) {
            return n | 0x2;
        }
        if ((this.style & 0x40) != 0x0) {
            return n;
        }
        return n | 0xC;
    }
    
    TCHAR windowClass() {
        return Label.LabelClass;
    }
    
    int windowProc() {
        return Label.LabelProc;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if (wm_ERASEBKGND != null) {
            return wm_ERASEBKGND;
        }
        if ((OS.GetWindowLong(this.handle, -16) & 0xD) == 0xD) {
            return LRESULT.ONE;
        }
        if (OS.COMCTL32_MAJOR < 6 && this.findImageControl() != null) {
            this.drawBackground(n);
            return LRESULT.ONE;
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (this.isDisposed()) {
            return wm_SIZE;
        }
        if ((this.style & 0x2) != 0x0) {
            OS.InvalidateRect(this.handle, null, true);
            return wm_SIZE;
        }
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        if ((getWindowLong & 0xD) == 0xD) {
            OS.InvalidateRect(this.handle, null, true);
            return wm_SIZE;
        }
        if ((getWindowLong & 0xC) != 0xC) {
            OS.InvalidateRect(this.handle, null, true);
            return wm_SIZE;
        }
        return wm_SIZE;
    }
    
    LRESULT WM_UPDATEUISTATE(final int n, final int n2) {
        final LRESULT wm_UPDATEUISTATE = super.WM_UPDATEUISTATE(n, n2);
        if (wm_UPDATEUISTATE != null) {
            return wm_UPDATEUISTATE;
        }
        boolean b = this.findImageControl() != null;
        if (!b && (this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            b = (this.findThemeControl() != null);
        }
        if (b) {
            OS.InvalidateRect(this.handle, null, false);
            return new LRESULT(OS.DefWindowProc(this.handle, 296, n, n2));
        }
        return wm_UPDATEUISTATE;
    }
    
    LRESULT wmColorChild(final int n, final int n2) {
        final LRESULT wmColorChild = super.wmColorChild(n, n2);
        if (OS.COMCTL32_MAJOR < 6 && (OS.GetWindowLong(this.handle, -16) & 0xD) != 0xD && this.findImageControl() != null) {
            OS.SetBkMode(n, 1);
            return new LRESULT(OS.GetStockObject(5));
        }
        return wmColorChild;
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        if (OS.IsWinCE) {
            final boolean b = this.image != null;
            final boolean b2 = (this.style & 0x2) != 0x0 && (this.style & 0x20) == 0x0;
            if (b || b2) {
                LRESULT lresult = null;
                final PAINTSTRUCT ps = new PAINTSTRUCT();
                final GCData gcData = new GCData();
                gcData.ps = ps;
                gcData.hwnd = this.handle;
                final GC new_GC = this.new_GC(gcData);
                if (new_GC != null) {
                    this.drawBackground(new_GC.handle);
                    final RECT rect = new RECT();
                    OS.GetClientRect(this.handle, rect);
                    if (b2) {
                        final RECT rect2 = new RECT();
                        final int getSystemMetrics = OS.GetSystemMetrics(5);
                        final int n3 = ((this.style & 0x4) != 0x0) ? 10 : 6;
                        if ((this.style & 0x100) != 0x0) {
                            OS.SetRect(rect2, rect.left, rect.top, rect.right, rect.top + Math.max(getSystemMetrics * 2, (rect.bottom - rect.top) / 2));
                            OS.DrawEdge(new_GC.handle, rect2, n3, 8);
                        }
                        else {
                            OS.SetRect(rect2, rect.left, rect.top, rect.left + Math.max(getSystemMetrics * 2, (rect.right - rect.left) / 2), rect.bottom);
                            OS.DrawEdge(new_GC.handle, rect2, n3, 4);
                        }
                        lresult = LRESULT.ONE;
                    }
                    if (b) {
                        final Rectangle bounds = this.image.getBounds();
                        int n4 = 0;
                        if ((this.style & 0x1000000) != 0x0) {
                            n4 = Math.max(0, (rect.right - bounds.width) / 2);
                        }
                        else if ((this.style & 0x20000) != 0x0) {
                            n4 = Math.max(0, rect.right - bounds.width);
                        }
                        new_GC.drawImage(this.image, n4, Math.max(0, (rect.bottom - bounds.height) / 2));
                        lresult = LRESULT.ONE;
                    }
                    final int width = ps.right - ps.left;
                    final int height = ps.bottom - ps.top;
                    if (width != 0 && height != 0) {
                        final Event event = new Event();
                        event.gc = new_GC;
                        event.x = ps.left;
                        event.y = ps.top;
                        event.width = width;
                        event.height = height;
                        this.sendEvent(9, event);
                        event.gc = null;
                    }
                    new_GC.dispose();
                }
                return lresult;
            }
        }
        return super.WM_PAINT(n, n2);
    }
    
    LRESULT wmDrawChild(final int n, final int n2) {
        final DRAWITEMSTRUCT drawitemstruct = new DRAWITEMSTRUCT();
        OS.MoveMemory(drawitemstruct, n2, DRAWITEMSTRUCT.sizeof);
        this.drawBackground(drawitemstruct.hDC);
        if ((this.style & 0x2) != 0x0) {
            if ((this.style & 0x20) != 0x0) {
                return null;
            }
            final RECT rect = new RECT();
            final int getSystemMetrics = OS.GetSystemMetrics(5);
            final int n3 = ((this.style & 0x4) != 0x0) ? 10 : 6;
            if ((this.style & 0x100) != 0x0) {
                OS.SetRect(rect, drawitemstruct.left, drawitemstruct.top, drawitemstruct.right, drawitemstruct.top + Math.max(getSystemMetrics * 2, (drawitemstruct.bottom - drawitemstruct.top) / 2));
                OS.DrawEdge(drawitemstruct.hDC, rect, n3, 8);
            }
            else {
                OS.SetRect(rect, drawitemstruct.left, drawitemstruct.top, drawitemstruct.left + Math.max(getSystemMetrics * 2, (drawitemstruct.right - drawitemstruct.left) / 2), drawitemstruct.bottom);
                OS.DrawEdge(drawitemstruct.hDC, rect, n3, 4);
            }
        }
        else {
            final int n4 = drawitemstruct.right - drawitemstruct.left;
            final int n5 = drawitemstruct.bottom - drawitemstruct.top;
            if (n4 != 0 && n5 != 0) {
                final boolean b = this.image != null;
                final boolean b2 = Label.IMAGE_AND_TEXT && this.text.length() != 0;
                final int n6 = (b2 && b) ? 4 : 0;
                int width = 0;
                int height = 0;
                if (b) {
                    final Rectangle bounds = this.image.getBounds();
                    width = bounds.width;
                    height = bounds.height;
                }
                RECT rect2 = null;
                TCHAR tchar = null;
                int n7 = 0;
                int n8 = 0;
                int n9 = 0;
                if (b2) {
                    rect2 = new RECT();
                    n9 = 9280;
                    if ((this.style & 0x4000) != 0x0) {
                        n9 |= 0x0;
                    }
                    if ((this.style & 0x1000000) != 0x0) {
                        n9 |= 0x1;
                    }
                    if ((this.style & 0x20000) != 0x0) {
                        n9 |= 0x2;
                    }
                    if ((this.style & 0x40) != 0x0) {
                        n9 |= 0x10;
                        rect2.right = Math.max(0, n4 - width - n6);
                    }
                    tchar = new TCHAR(this.getCodePage(), this.text, true);
                    OS.DrawText(drawitemstruct.hDC, tchar, -1, rect2, n9);
                    n7 = rect2.right - rect2.left;
                    n8 = rect2.bottom - rect2.top;
                }
                int max = 0;
                if ((this.style & 0x1000000) != 0x0) {
                    max = Math.max(0, (n4 - width - n7 - n6) / 2);
                }
                else if ((this.style & 0x20000) != 0x0) {
                    max = n4 - width - n7 - n6;
                }
                if (b) {
                    final GCData gcData = new GCData();
                    gcData.device = this.display;
                    final GC win32_new = GC.win32_new(drawitemstruct.hDC, gcData);
                    final Image image = this.getEnabled() ? this.image : new Image(this.display, this.image, 1);
                    win32_new.drawImage(image, max, Math.max(0, (n5 - height) / 2));
                    if (image != this.image) {
                        image.dispose();
                    }
                    win32_new.dispose();
                    max += width + n6;
                }
                if (b2) {
                    final int n10 = n9 & 0xFFFFFBFF;
                    rect2.left = max;
                    final RECT rect3 = rect2;
                    rect3.right += rect2.left;
                    rect2.top = Math.max(0, (n5 - n8) / 2);
                    final RECT rect4 = rect2;
                    rect4.bottom += rect2.top;
                    OS.DrawText(drawitemstruct.hDC, tchar, -1, rect2, n10);
                }
            }
        }
        return null;
    }
}
