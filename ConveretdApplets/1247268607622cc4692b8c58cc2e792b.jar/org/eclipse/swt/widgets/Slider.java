// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;

public class Slider extends Control
{
    int increment;
    int pageIncrement;
    boolean ignoreFocus;
    static final int ScrollBarProc;
    static final TCHAR ScrollBarClass;
    
    static {
        ScrollBarClass = new TCHAR(0, "SCROLLBAR", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Slider.ScrollBarClass, wndclass);
        ScrollBarProc = wndclass.lpfnWndProc;
    }
    
    public Slider(final Composite composite, final int n) {
        super(composite, checkStyle(n));
    }
    
    public void addSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(selectionListener);
        this.addListener(13, typedListener);
        this.addListener(14, typedListener);
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        switch (n2) {
            case 513:
            case 515: {
                this.display.runDeferredEvents();
                break;
            }
        }
        return OS.CallWindowProc(Slider.ScrollBarProc, n, n2, n3, n4);
    }
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 256, 512, 0, 0, 0, 0);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final int borderWidth = this.getBorderWidth();
        final int n3 = borderWidth * 2;
        final int n4 = borderWidth * 2;
        int n5;
        int n6;
        if ((this.style & 0x100) != 0x0) {
            n5 = n3 + OS.GetSystemMetrics(21) * 10;
            n6 = n4 + OS.GetSystemMetrics(3);
        }
        else {
            n5 = n3 + OS.GetSystemMetrics(2);
            n6 = n4 + OS.GetSystemMetrics(20) * 10;
        }
        if (n != -1) {
            n5 = n + borderWidth * 2;
        }
        if (n2 != -1) {
            n6 = n2 + borderWidth * 2;
        }
        return new Point(n5, n6);
    }
    
    void createWidget() {
        super.createWidget();
        this.increment = 1;
        this.pageIncrement = 10;
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 23;
        scrollinfo.nMax = 100;
        scrollinfo.nPage = 11;
        OS.SetScrollInfo(this.handle, 2, scrollinfo, true);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_SCROLLBAR);
    }
    
    int defaultForeground() {
        return OS.GetSysColor(OS.COLOR_BTNFACE);
    }
    
    void enableWidget(final boolean b) {
        super.enableWidget(b);
        if (!OS.IsWinCE) {
            OS.EnableScrollBar(this.handle, 2, b ? 0 : 3);
        }
        if (b) {
            this.state &= 0xFFFFFFF7;
        }
        else {
            this.state |= 0x8;
        }
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return (this.state & 0x8) == 0x0;
    }
    
    public int getIncrement() {
        this.checkWidget();
        return this.increment;
    }
    
    public int getMaximum() {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 1;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        return scrollinfo.nMax;
    }
    
    public int getMinimum() {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 1;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        return scrollinfo.nMin;
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return this.pageIncrement;
    }
    
    public int getSelection() {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 4;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        return scrollinfo.nPos;
    }
    
    public int getThumb() {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 2;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        if (scrollinfo.nPage != 0) {
            final SCROLLINFO scrollinfo2 = scrollinfo;
            --scrollinfo2.nPage;
        }
        return scrollinfo.nPage;
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(13, selectionListener);
        this.eventTable.unhook(14, selectionListener);
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, final int n5) {
        super.setBounds(n, n2, n3, n4, n5);
        if (OS.GetFocus() == this.handle) {
            this.ignoreFocus = true;
            OS.SendMessage(this.handle, 7, 0, 0);
            this.ignoreFocus = false;
        }
    }
    
    public void setIncrement(final int increment) {
        this.checkWidget();
        if (increment < 1) {
            return;
        }
        this.increment = increment;
    }
    
    public void setMaximum(final int nMax) {
        this.checkWidget();
        if (nMax < 0) {
            return;
        }
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 9;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        if (nMax - scrollinfo.nMin - scrollinfo.nPage < 1) {
            return;
        }
        scrollinfo.nMax = nMax;
        this.SetScrollInfo(this.handle, 2, scrollinfo, true);
    }
    
    public void setMinimum(final int nMin) {
        this.checkWidget();
        if (nMin < 0) {
            return;
        }
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 9;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        if (scrollinfo.nMax - nMin - scrollinfo.nPage < 1) {
            return;
        }
        scrollinfo.nMin = nMin;
        this.SetScrollInfo(this.handle, 2, scrollinfo, true);
    }
    
    public void setPageIncrement(final int pageIncrement) {
        this.checkWidget();
        if (pageIncrement < 1) {
            return;
        }
        this.pageIncrement = pageIncrement;
    }
    
    boolean SetScrollInfo(final int n, final int n2, final SCROLLINFO scrollinfo, boolean b) {
        if ((this.state & 0x8) != 0x0) {
            b = false;
        }
        final boolean setScrollInfo = OS.SetScrollInfo(n, n2, scrollinfo, b);
        if ((this.state & 0x8) != 0x0) {
            OS.EnableWindow(this.handle, false);
            if (!OS.IsWinCE) {
                OS.EnableScrollBar(this.handle, 2, 3);
            }
        }
        if (OS.GetFocus() == this.handle) {
            this.ignoreFocus = true;
            OS.SendMessage(this.handle, 7, 0, 0);
            this.ignoreFocus = false;
        }
        return setScrollInfo;
    }
    
    public void setSelection(final int nPos) {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 4;
        scrollinfo.nPos = nPos;
        this.SetScrollInfo(this.handle, 2, scrollinfo, true);
    }
    
    public void setThumb(final int nPage) {
        this.checkWidget();
        if (nPage < 1) {
            return;
        }
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 11;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        scrollinfo.nPage = nPage;
        if (scrollinfo.nPage != 0) {
            final SCROLLINFO scrollinfo2 = scrollinfo;
            ++scrollinfo2.nPage;
        }
        this.SetScrollInfo(this.handle, 2, scrollinfo, true);
    }
    
    public void setValues(final int nPos, final int nMin, final int nMax, final int nPage, final int increment, final int pageIncrement) {
        this.checkWidget();
        if (nMin < 0) {
            return;
        }
        if (nMax < 0) {
            return;
        }
        if (nPage < 1) {
            return;
        }
        if (increment < 1) {
            return;
        }
        if (pageIncrement < 1) {
            return;
        }
        this.increment = increment;
        this.pageIncrement = pageIncrement;
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 15;
        scrollinfo.nPos = nPos;
        scrollinfo.nMin = nMin;
        scrollinfo.nMax = nMax;
        scrollinfo.nPage = nPage;
        if (scrollinfo.nPage != 0) {
            final SCROLLINFO scrollinfo2 = scrollinfo;
            ++scrollinfo2.nPage;
        }
        this.SetScrollInfo(this.handle, 2, scrollinfo, true);
    }
    
    int widgetExtStyle() {
        int widgetExtStyle = super.widgetExtStyle();
        if ((this.style & 0x800) != 0x0) {
            widgetExtStyle &= 0xFFFFFDFF;
        }
        return widgetExtStyle;
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x10000;
        if ((this.style & 0x800) != 0x0) {
            n &= 0xFF7FFFFF;
        }
        if ((this.style & 0x100) != 0x0) {
            return n;
        }
        return n | 0x1;
    }
    
    TCHAR windowClass() {
        return Slider.ScrollBarClass;
    }
    
    int windowProc() {
        return Slider.ScrollBarProc;
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        final LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        if ((this.style & 0x200) != 0x0) {
            return wm_KEYDOWN;
        }
        if ((this.style & 0x8000000) != 0x0) {
            switch (n) {
                case 37:
                case 39: {
                    return new LRESULT(this.callWindowProc(this.handle, 256, (n == 37) ? 39 : 37, n2));
                }
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_LBUTTONDBLCLK(final int n, final int n2) {
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        OS.SetWindowLong(this.handle, -16, getWindowLong & 0xFFFEFFFF);
        final LRESULT wm_LBUTTONDBLCLK = super.WM_LBUTTONDBLCLK(n, n2);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        OS.SetWindowLong(this.handle, -16, getWindowLong);
        if (wm_LBUTTONDBLCLK == LRESULT.ZERO) {
            return wm_LBUTTONDBLCLK;
        }
        if (!OS.IsWinCE) {
            if (OS.GetCapture() == this.handle) {
                OS.ReleaseCapture();
            }
            if (!this.sendMouseEvent(4, 1, this.handle, 514, n, n2)) {
                return LRESULT.ZERO;
            }
        }
        return wm_LBUTTONDBLCLK;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        OS.SetWindowLong(this.handle, -16, getWindowLong & 0xFFFEFFFF);
        final LRESULT wm_LBUTTONDOWN = super.WM_LBUTTONDOWN(n, n2);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        OS.SetWindowLong(this.handle, -16, getWindowLong);
        if (wm_LBUTTONDOWN == LRESULT.ZERO) {
            return wm_LBUTTONDOWN;
        }
        if (!OS.IsWinCE) {
            if (OS.GetCapture() == this.handle) {
                OS.ReleaseCapture();
            }
            if (!this.sendMouseEvent(4, 1, this.handle, 514, n, n2)) {
                return LRESULT.ONE;
            }
        }
        return wm_LBUTTONDOWN;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        if (this.ignoreFocus) {
            return null;
        }
        return super.WM_SETFOCUS(n, n2);
    }
    
    LRESULT wmScrollChild(final int n, final int n2) {
        final int loword = OS.LOWORD(n);
        if (loword == 8) {
            return null;
        }
        final Event event = new Event();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 21;
        OS.GetScrollInfo(this.handle, 2, scrollinfo);
        scrollinfo.fMask = 4;
        switch (loword) {
            case 4: {
                event.detail = 0;
                scrollinfo.nPos = scrollinfo.nTrackPos;
                break;
            }
            case 5: {
                event.detail = 1;
                scrollinfo.nPos = scrollinfo.nTrackPos;
                break;
            }
            case 6: {
                event.detail = 16777223;
                scrollinfo.nPos = scrollinfo.nMin;
                break;
            }
            case 7: {
                event.detail = 16777224;
                scrollinfo.nPos = scrollinfo.nMax;
                break;
            }
            case 1: {
                event.detail = 16777218;
                final SCROLLINFO scrollinfo2 = scrollinfo;
                scrollinfo2.nPos += this.increment;
                break;
            }
            case 0: {
                event.detail = 16777217;
                scrollinfo.nPos = Math.max(scrollinfo.nMin, scrollinfo.nPos - this.increment);
                break;
            }
            case 3: {
                event.detail = 16777222;
                final SCROLLINFO scrollinfo3 = scrollinfo;
                scrollinfo3.nPos += this.pageIncrement;
                break;
            }
            case 2: {
                event.detail = 16777221;
                scrollinfo.nPos = Math.max(scrollinfo.nMin, scrollinfo.nPos - this.pageIncrement);
                break;
            }
        }
        OS.SetScrollInfo(this.handle, 2, scrollinfo, true);
        this.sendSelectionEvent(13, event, true);
        return null;
    }
}
