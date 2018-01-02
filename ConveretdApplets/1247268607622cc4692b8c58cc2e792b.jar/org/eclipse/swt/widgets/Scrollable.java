// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.internal.win32.SCROLLBARINFO;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.OS;

public abstract class Scrollable extends Control
{
    ScrollBar horizontalBar;
    ScrollBar verticalBar;
    
    Scrollable() {
    }
    
    public Scrollable(final Composite composite, final int n) {
        super(composite, n);
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        return OS.DefWindowProc(n, n2, n3, n4);
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        final int scrolledHandle = this.scrolledHandle();
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        OS.AdjustWindowRectEx(rect, OS.GetWindowLong(scrolledHandle, -16), false, OS.GetWindowLong(scrolledHandle, -20));
        if (this.horizontalBar != null) {
            final RECT rect2 = rect;
            rect2.bottom += OS.GetSystemMetrics(3);
        }
        if (this.verticalBar != null) {
            final RECT rect3 = rect;
            rect3.right += OS.GetSystemMetrics(2);
        }
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    ScrollBar createScrollBar(final int n) {
        final ScrollBar scrollBar = new ScrollBar(this, n);
        if ((this.state & 0x2) != 0x0) {
            scrollBar.setMaximum(100);
            scrollBar.setThumb(10);
        }
        return scrollBar;
    }
    
    void createWidget() {
        super.createWidget();
        if ((this.style & 0x100) != 0x0) {
            this.horizontalBar = this.createScrollBar(256);
        }
        if ((this.style & 0x200) != 0x0) {
            this.verticalBar = this.createScrollBar(512);
        }
    }
    
    void destroyScrollBar(final int n) {
        final int scrolledHandle = this.scrolledHandle();
        int getWindowLong = OS.GetWindowLong(scrolledHandle, -16);
        if ((n & 0x100) != 0x0) {
            this.style &= 0xFFFFFEFF;
            getWindowLong &= 0xFFEFFFFF;
        }
        if ((n & 0x200) != 0x0) {
            this.style &= 0xFFFFFDFF;
            getWindowLong &= 0xFFDFFFFF;
        }
        OS.SetWindowLong(scrolledHandle, -16, getWindowLong);
    }
    
    public Rectangle getClientArea() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        final int scrolledHandle = this.scrolledHandle();
        OS.GetClientRect(scrolledHandle, rect);
        int left = rect.left;
        int top = rect.top;
        final int n = rect.right - rect.left;
        final int n2 = rect.bottom - rect.top;
        if (scrolledHandle != this.handle) {
            OS.GetClientRect(this.handle, rect);
            OS.MapWindowPoints(this.handle, scrolledHandle, rect, 2);
            left = -rect.left;
            top = -rect.top;
        }
        return new Rectangle(left, top, n, n2);
    }
    
    public ScrollBar getHorizontalBar() {
        this.checkWidget();
        return this.horizontalBar;
    }
    
    public ScrollBar getVerticalBar() {
        this.checkWidget();
        return this.verticalBar;
    }
    
    void releaseChildren(final boolean b) {
        if (this.horizontalBar != null) {
            this.horizontalBar.release(false);
            this.horizontalBar = null;
        }
        if (this.verticalBar != null) {
            this.verticalBar.release(false);
            this.verticalBar = null;
        }
        super.releaseChildren(b);
    }
    
    void reskinChildren(final int n) {
        if (this.horizontalBar != null) {
            this.horizontalBar.reskin(n);
        }
        if (this.verticalBar != null) {
            this.verticalBar.reskin(n);
        }
        super.reskinChildren(n);
    }
    
    int scrolledHandle() {
        return this.handle;
    }
    
    int widgetExtStyle() {
        return super.widgetExtStyle();
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x10000;
        if ((this.style & 0x100) != 0x0) {
            n |= 0x100000;
        }
        if ((this.style & 0x200) != 0x0) {
            n |= 0x200000;
        }
        return n;
    }
    
    TCHAR windowClass() {
        return this.display.windowClass;
    }
    
    int windowProc() {
        return this.display.windowProc;
    }
    
    LRESULT WM_HSCROLL(final int n, final int n2) {
        final LRESULT wm_HSCROLL = super.WM_HSCROLL(n, n2);
        if (wm_HSCROLL != null) {
            return wm_HSCROLL;
        }
        if (this.horizontalBar != null && (n2 == 0 || n2 == this.handle)) {
            return this.wmScroll(this.horizontalBar, (this.state & 0x2) != 0x0, this.handle, 276, n, n2);
        }
        return wm_HSCROLL;
    }
    
    LRESULT WM_MOUSEWHEEL(final int n, final int n2) {
        return this.wmScrollWheel((this.state & 0x2) != 0x0, n, n2);
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final int callWindowProc = this.callWindowProc(this.handle, 5, n, n2);
        super.WM_SIZE(n, n2);
        if (callWindowProc == 0) {
            return LRESULT.ZERO;
        }
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_VSCROLL(final int n, final int n2) {
        final LRESULT wm_VSCROLL = super.WM_VSCROLL(n, n2);
        if (wm_VSCROLL != null) {
            return wm_VSCROLL;
        }
        if (this.verticalBar != null && (n2 == 0 || n2 == this.handle)) {
            return this.wmScroll(this.verticalBar, (this.state & 0x2) != 0x0, this.handle, 277, n, n2);
        }
        return wm_VSCROLL;
    }
    
    LRESULT wmNCPaint(final int n, final int n2, final int n3) {
        final LRESULT wmNCPaint = super.wmNCPaint(n, n2, n3);
        if (wmNCPaint != null) {
            return wmNCPaint;
        }
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && !OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0)) {
            final int getWindowLong = OS.GetWindowLong(n, -16);
            if ((getWindowLong & 0x300000) != 0x0) {
                final RECT rect = new RECT();
                OS.GetWindowRect(n, rect);
                final RECT rect2 = new RECT();
                final int getWindowLong2 = OS.GetWindowLong(n, -20);
                OS.AdjustWindowRectEx(rect2, getWindowLong, false, getWindowLong2);
                boolean b = false;
                boolean b2 = false;
                final SCROLLBARINFO scrollbarinfo = new SCROLLBARINFO();
                scrollbarinfo.cbSize = SCROLLBARINFO.sizeof;
                if (OS.GetScrollBarInfo(n, -6, scrollbarinfo)) {
                    b = ((scrollbarinfo.rgstate[0] & 0x8000) == 0x0);
                }
                if (OS.GetScrollBarInfo(n, -5, scrollbarinfo)) {
                    b2 = ((scrollbarinfo.rgstate[0] & 0x8000) == 0x0);
                }
                final RECT rect3 = new RECT();
                rect3.bottom = rect.bottom - rect.top - rect2.bottom;
                rect3.top = rect3.bottom - (b2 ? OS.GetSystemMetrics(3) : 0);
                if ((getWindowLong2 & 0x4000) != 0x0) {
                    rect3.left = rect2.left;
                    rect3.right = rect3.left + (b ? OS.GetSystemMetrics(2) : 0);
                }
                else {
                    rect3.right = rect.right - rect.left - rect2.right;
                    rect3.left = rect3.right - (b ? OS.GetSystemMetrics(2) : 0);
                }
                if (rect3.left != rect3.right && rect3.top != rect3.bottom) {
                    final int getWindowDC = OS.GetWindowDC(n);
                    OS.FillRect(getWindowDC, rect3, OS.COLOR_BTNFACE + 1);
                    final Decorations menuShell = this.menuShell();
                    if ((menuShell.style & 0x10) != 0x0) {
                        final int scrolledHandle = menuShell.scrolledHandle();
                        boolean b3 = n == scrolledHandle;
                        if (!b3) {
                            final RECT rect4 = new RECT();
                            OS.GetClientRect(scrolledHandle, rect4);
                            OS.MapWindowPoints(scrolledHandle, 0, rect4, 2);
                            b3 = (rect4.right == rect.right && rect4.bottom == rect.bottom);
                        }
                        if (b3) {
                            OS.DrawThemeBackground(this.display.hScrollBarTheme(), getWindowDC, 10, 0, rect3, null);
                        }
                    }
                    OS.ReleaseDC(n, getWindowDC);
                }
            }
        }
        return wmNCPaint;
    }
    
    LRESULT wmScrollWheel(final boolean b, final int n, final int n2) {
        final int scrollRemainder = this.display.scrollRemainder;
        final LRESULT wm_MOUSEWHEEL = super.WM_MOUSEWHEEL(n, n2);
        if (wm_MOUSEWHEEL != null) {
            return wm_MOUSEWHEEL;
        }
        if (!b) {
            final int n3 = (this.verticalBar == null) ? 0 : this.verticalBar.getSelection();
            final int n4 = (this.horizontalBar == null) ? 0 : this.horizontalBar.getSelection();
            final int callWindowProc = this.callWindowProc(this.handle, 522, n, n2);
            if (this.verticalBar != null) {
                final int selection = this.verticalBar.getSelection();
                if (selection != n3) {
                    final Event event = new Event();
                    event.detail = ((selection < n3) ? 16777221 : 16777222);
                    this.verticalBar.sendSelectionEvent(13, event, true);
                }
            }
            if (this.horizontalBar != null) {
                final int selection2 = this.horizontalBar.getSelection();
                if (selection2 != n4) {
                    final Event event2 = new Event();
                    event2.detail = ((selection2 < n4) ? 16777221 : 16777222);
                    this.horizontalBar.sendSelectionEvent(13, event2, true);
                }
            }
            return new LRESULT(callWindowProc);
        }
        if ((n & 0xC) != 0x0) {
            return wm_MOUSEWHEEL;
        }
        final boolean b2 = this.verticalBar != null && this.verticalBar.getEnabled();
        final boolean b3 = this.horizontalBar != null && this.horizontalBar.getEnabled();
        final int n5 = b2 ? 277 : (b3 ? 276 : 0);
        if (n5 == 0) {
            return wm_MOUSEWHEEL;
        }
        final int[] array = { 0 };
        OS.SystemParametersInfo(104, 0, array, 0);
        int get_WHEEL_DELTA_WPARAM = OS.GET_WHEEL_DELTA_WPARAM(n);
        final boolean b4 = array[0] == -1;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            final ScrollBar scrollBar = b2 ? this.verticalBar : this.horizontalBar;
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 4;
            OS.GetScrollInfo(this.handle, scrollBar.scrollBarType(), scrollinfo);
            if (b2 && !b4) {
                get_WHEEL_DELTA_WPARAM *= array[0];
            }
            final int n6 = b4 ? scrollBar.getPageIncrement() : scrollBar.getIncrement();
            final SCROLLINFO scrollinfo2 = scrollinfo;
            scrollinfo2.nPos -= n6 * get_WHEEL_DELTA_WPARAM / 120;
            OS.SetScrollInfo(this.handle, scrollBar.scrollBarType(), scrollinfo, true);
            OS.SendMessage(this.handle, n5, 4, 0);
        }
        else {
            int n7;
            if (b4) {
                n7 = ((get_WHEEL_DELTA_WPARAM < 0) ? 3 : 2);
            }
            else {
                n7 = ((get_WHEEL_DELTA_WPARAM < 0) ? 1 : 0);
                if (n5 == 277) {
                    get_WHEEL_DELTA_WPARAM *= array[0];
                }
            }
            if ((get_WHEEL_DELTA_WPARAM ^ scrollRemainder) >= 0) {
                get_WHEEL_DELTA_WPARAM += scrollRemainder;
            }
            for (int n8 = Math.abs(get_WHEEL_DELTA_WPARAM) / 120, i = 0; i < n8; ++i) {
                OS.SendMessage(this.handle, n5, n7, 0);
            }
        }
        return LRESULT.ZERO;
    }
    
    LRESULT wmScroll(final ScrollBar scrollBar, final boolean b, final int n, final int n2, final int n3, final int n4) {
        LRESULT lresult = null;
        if (b) {
            final int n5 = (n2 != 276) ? 1 : 0;
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 21;
            OS.GetScrollInfo(n, n5, scrollinfo);
            scrollinfo.fMask = 4;
            switch (OS.LOWORD(n3)) {
                case 8: {
                    return null;
                }
                case 4:
                case 5: {
                    scrollinfo.nPos = scrollinfo.nTrackPos;
                    break;
                }
                case 6: {
                    scrollinfo.nPos = scrollinfo.nMin;
                    break;
                }
                case 7: {
                    scrollinfo.nPos = scrollinfo.nMax;
                    break;
                }
                case 1: {
                    final SCROLLINFO scrollinfo2 = scrollinfo;
                    scrollinfo2.nPos += scrollBar.getIncrement();
                    break;
                }
                case 0: {
                    scrollinfo.nPos = Math.max(scrollinfo.nMin, scrollinfo.nPos - scrollBar.getIncrement());
                    break;
                }
                case 3: {
                    final SCROLLINFO scrollinfo3 = scrollinfo;
                    scrollinfo3.nPos += scrollBar.getPageIncrement();
                    break;
                }
                case 2: {
                    scrollinfo.nPos = Math.max(scrollinfo.nMin, scrollinfo.nPos - scrollBar.getPageIncrement());
                    break;
                }
            }
            OS.SetScrollInfo(n, n5, scrollinfo, true);
        }
        else {
            final int callWindowProc = this.callWindowProc(n, n2, n3, n4);
            lresult = ((callWindowProc == 0) ? LRESULT.ZERO : new LRESULT(callWindowProc));
        }
        scrollBar.wmScrollChild(n3, n4);
        return lresult;
    }
}
