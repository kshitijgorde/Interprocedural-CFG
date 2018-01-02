// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.COMPOSITIONFORM;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;

public class Canvas extends Composite
{
    Caret caret;
    IME ime;
    
    Canvas() {
    }
    
    public Canvas(final Composite composite, final int n) {
        super(composite, n);
    }
    
    void clearArea(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        if (OS.IsWindowVisible(this.handle)) {
            final RECT rect = new RECT();
            OS.SetRect(rect, n, n2, n + n3, n2 + n4);
            final int getDCEx = OS.GetDCEx(this.handle, 0, 26);
            this.drawBackground(getDCEx, rect);
            OS.ReleaseDC(this.handle, getDCEx);
        }
    }
    
    public void drawBackground(final GC gc, final int n, final int n2, final int n3, final int n4) {
        this.drawBackground(gc, n, n2, n3, n4, 0, 0);
    }
    
    public Caret getCaret() {
        this.checkWidget();
        return this.caret;
    }
    
    public IME getIME() {
        this.checkWidget();
        return this.ime;
    }
    
    void releaseChildren(final boolean b) {
        if (this.caret != null) {
            this.caret.release(false);
            this.caret = null;
        }
        if (this.ime != null) {
            this.ime.release(false);
            this.ime = null;
        }
        super.releaseChildren(b);
    }
    
    void reskinChildren(final int n) {
        if (this.caret != null) {
            this.caret.reskin(n);
        }
        if (this.ime != null) {
            this.ime.reskin(n);
        }
        super.reskinChildren(n);
    }
    
    public void scroll(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        this.checkWidget();
        this.forceResize();
        final boolean b2 = this.caret != null && this.caret.isFocusCaret();
        if (b2) {
            this.caret.killFocus();
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, n3, n4, n3 + n5, n4 + n6);
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        if (OS.IntersectRect(rect2, rect, rect2)) {
            if (OS.IsWinCE) {
                OS.UpdateWindow(this.handle);
            }
            else {
                OS.RedrawWindow(this.handle, null, 0, 384);
            }
        }
        final int n7 = n - n3;
        final int n8 = n2 - n4;
        if (this.findImageControl() != null) {
            if (OS.IsWinCE) {
                OS.InvalidateRect(this.handle, rect, true);
            }
            else {
                int n9 = 1029;
                if (b) {
                    n9 |= 0x80;
                }
                OS.RedrawWindow(this.handle, rect, 0, n9);
            }
            OS.OffsetRect(rect, n7, n8);
            if (OS.IsWinCE) {
                OS.InvalidateRect(this.handle, rect, true);
            }
            else {
                int n10 = 1029;
                if (b) {
                    n10 |= 0x80;
                }
                OS.RedrawWindow(this.handle, rect, 0, n10);
            }
        }
        else {
            OS.ScrollWindowEx(this.handle, n7, n8, rect, null, 0, null, 6);
        }
        if (b) {
            final Control[] getChildren = this._getChildren();
            for (int i = 0; i < getChildren.length; ++i) {
                final Control control = getChildren[i];
                final Rectangle bounds = control.getBounds();
                if (Math.min(n3 + n5, bounds.x + bounds.width) >= Math.max(n3, bounds.x) && Math.min(n4 + n6, bounds.y + bounds.height) >= Math.max(n4, bounds.y)) {
                    control.setLocation(bounds.x + n7, bounds.y + n8);
                }
            }
        }
        if (b2) {
            this.caret.setFocus();
        }
    }
    
    public void setCaret(final Caret caret) {
        this.checkWidget();
        final Caret caret2 = this.caret;
        this.caret = caret;
        if (this.hasFocus()) {
            if (caret2 != null) {
                caret2.killFocus();
            }
            if (caret != null) {
                if (caret.isDisposed()) {
                    this.error(5);
                }
                caret.setFocus();
            }
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        if (this.caret != null) {
            this.caret.setFont(font);
        }
        super.setFont(font);
    }
    
    public void setIME(final IME ime) {
        this.checkWidget();
        if (ime != null && ime.isDisposed()) {
            this.error(5);
        }
        this.ime = ime;
    }
    
    TCHAR windowClass() {
        if (this.display.useOwnDC) {
            return this.display.windowOwnDCClass;
        }
        return super.windowClass();
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        if (n2 == Display.SWT_RESTORECARET && (this.state & 0x2) != 0x0 && this.caret != null) {
            this.caret.killFocus();
            this.caret.setFocus();
            return 1;
        }
        return super.windowProc(n, n2, n3, n4);
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        final LRESULT wm_CHAR = super.WM_CHAR(n, n2);
        if (wm_CHAR != null) {
            return wm_CHAR;
        }
        if (this.caret != null) {
            switch (n) {
                case 8:
                case 27:
                case 127: {
                    break;
                }
                default: {
                    if (OS.GetKeyState(17) < 0) {
                        break;
                    }
                    final int[] array = { 0 };
                    if (OS.SystemParametersInfo(4128, 0, array, 0) && array[0] != 0) {
                        OS.SetCursor(0);
                        break;
                    }
                    break;
                }
            }
        }
        return wm_CHAR;
    }
    
    LRESULT WM_IME_COMPOSITION(final int n, final int n2) {
        if (this.ime != null) {
            final LRESULT wm_IME_COMPOSITION = this.ime.WM_IME_COMPOSITION(n, n2);
            if (wm_IME_COMPOSITION != null) {
                return wm_IME_COMPOSITION;
            }
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION == OS.VERSION(5, 1) && OS.IsDBLocale && OS.PRIMARYLANGID(OS.GetSystemDefaultUILanguage()) == 18 && this.caret != null && this.caret.isFocusCaret()) {
            final POINT point = new POINT();
            if (OS.GetCaretPos(point)) {
                final COMPOSITIONFORM compositionform = new COMPOSITIONFORM();
                compositionform.dwStyle = 2;
                compositionform.x = point.x;
                compositionform.y = point.y;
                final int immGetContext = OS.ImmGetContext(this.handle);
                OS.ImmSetCompositionWindow(immGetContext, compositionform);
                OS.ImmReleaseContext(this.handle, immGetContext);
            }
        }
        return super.WM_IME_COMPOSITION(n, n2);
    }
    
    LRESULT WM_IME_COMPOSITION_START(final int n, final int n2) {
        if (this.ime != null) {
            final LRESULT wm_IME_COMPOSITION_START = this.ime.WM_IME_COMPOSITION_START(n, n2);
            if (wm_IME_COMPOSITION_START != null) {
                return wm_IME_COMPOSITION_START;
            }
        }
        return super.WM_IME_COMPOSITION_START(n, n2);
    }
    
    LRESULT WM_IME_ENDCOMPOSITION(final int n, final int n2) {
        if (this.ime != null) {
            final LRESULT wm_IME_ENDCOMPOSITION = this.ime.WM_IME_ENDCOMPOSITION(n, n2);
            if (wm_IME_ENDCOMPOSITION != null) {
                return wm_IME_ENDCOMPOSITION;
            }
        }
        return super.WM_IME_ENDCOMPOSITION(n, n2);
    }
    
    LRESULT WM_INPUTLANGCHANGE(final int n, final int n2) {
        final LRESULT wm_INPUTLANGCHANGE = super.WM_INPUTLANGCHANGE(n, n2);
        if (this.caret != null && this.caret.isFocusCaret()) {
            this.caret.setIMEFont();
            this.caret.resizeIME();
        }
        return wm_INPUTLANGCHANGE;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        if (this.ime != null) {
            final LRESULT wm_KILLFOCUS = this.ime.WM_KILLFOCUS(n, n2);
            if (wm_KILLFOCUS != null) {
                return wm_KILLFOCUS;
            }
        }
        final Caret caret = this.caret;
        final LRESULT wm_KILLFOCUS2 = super.WM_KILLFOCUS(n, n2);
        if (caret != null) {
            caret.killFocus();
        }
        return wm_KILLFOCUS2;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        if (this.ime != null) {
            final LRESULT wm_LBUTTONDOWN = this.ime.WM_LBUTTONDOWN(n, n2);
            if (wm_LBUTTONDOWN != null) {
                return wm_LBUTTONDOWN;
            }
        }
        return super.WM_LBUTTONDOWN(n, n2);
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        final LRESULT wm_SETFOCUS = super.WM_SETFOCUS(n, n2);
        if (this.caret != null && this.caret.isFocusCaret()) {
            this.caret.setFocus();
        }
        return wm_SETFOCUS;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (this.caret != null && this.caret.isFocusCaret()) {
            this.caret.resizeIME();
        }
        return wm_SIZE;
    }
    
    LRESULT WM_WINDOWPOSCHANGED(final int n, final int n2) {
        final LRESULT wm_WINDOWPOSCHANGED = super.WM_WINDOWPOSCHANGED(n, n2);
        if ((this.style & 0x4000000) != 0x0 && this.caret != null && this.caret.isFocusCaret()) {
            this.caret.setFocus();
        }
        return wm_WINDOWPOSCHANGED;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final int n, final int n2) {
        final LRESULT wm_WINDOWPOSCHANGING = super.WM_WINDOWPOSCHANGING(n, n2);
        if (wm_WINDOWPOSCHANGING != null) {
            return wm_WINDOWPOSCHANGING;
        }
        if ((this.style & 0x4000000) != 0x0 && this.caret != null && this.caret.isFocusCaret()) {
            this.caret.killFocus();
        }
        return wm_WINDOWPOSCHANGING;
    }
}
