// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.SCROLLBARINFO;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;

public class ScrollBar extends Widget
{
    Scrollable parent;
    int increment;
    int pageIncrement;
    
    ScrollBar(final Scrollable parent, final int n) {
        super(parent, checkStyle(n));
        this.parent = parent;
        this.createWidget();
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
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 256, 512, 0, 0, 0, 0);
    }
    
    void createWidget() {
        this.increment = 1;
        this.pageIncrement = 10;
    }
    
    void destroyWidget() {
        final int hwndScrollBar = this.hwndScrollBar();
        final int scrollBarType = this.scrollBarType();
        if (OS.IsWinCE) {
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 3;
            scrollinfo.nPage = 101;
            scrollinfo.nMax = 100;
            scrollinfo.nMin = 0;
            OS.SetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo, true);
        }
        else {
            OS.ShowScrollBar(hwndScrollBar, scrollBarType, false);
        }
        this.parent.destroyScrollBar(this.style);
        this.releaseHandle();
    }
    
    Rectangle getBounds() {
        this.parent.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.parent.scrolledHandle(), rect);
        int n = 0;
        int n2 = 0;
        int getSystemMetrics;
        int getSystemMetrics2;
        if ((this.style & 0x100) != 0x0) {
            n2 = rect.bottom - rect.top;
            getSystemMetrics = rect.right - rect.left;
            getSystemMetrics2 = OS.GetSystemMetrics(3);
        }
        else {
            n = rect.right - rect.left;
            getSystemMetrics = OS.GetSystemMetrics(2);
            getSystemMetrics2 = rect.bottom - rect.top;
        }
        return new Rectangle(n, n2, getSystemMetrics, getSystemMetrics2);
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
        OS.GetScrollInfo(this.hwndScrollBar(), this.scrollBarType(), scrollinfo);
        return scrollinfo.nMax;
    }
    
    public int getMinimum() {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 1;
        OS.GetScrollInfo(this.hwndScrollBar(), this.scrollBarType(), scrollinfo);
        return scrollinfo.nMin;
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return this.pageIncrement;
    }
    
    public Scrollable getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public int getSelection() {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 4;
        OS.GetScrollInfo(this.hwndScrollBar(), this.scrollBarType(), scrollinfo);
        return scrollinfo.nPos;
    }
    
    public Point getSize() {
        this.checkWidget();
        this.parent.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.parent.scrolledHandle(), rect);
        int getSystemMetrics;
        int getSystemMetrics2;
        if ((this.style & 0x100) != 0x0) {
            getSystemMetrics = rect.right - rect.left;
            getSystemMetrics2 = OS.GetSystemMetrics(3);
        }
        else {
            getSystemMetrics = OS.GetSystemMetrics(2);
            getSystemMetrics2 = rect.bottom - rect.top;
        }
        return new Point(getSystemMetrics, getSystemMetrics2);
    }
    
    public int getThumb() {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 2;
        OS.GetScrollInfo(this.hwndScrollBar(), this.scrollBarType(), scrollinfo);
        if (scrollinfo.nPage != 0) {
            final SCROLLINFO scrollinfo2 = scrollinfo;
            --scrollinfo2.nPage;
        }
        return scrollinfo.nPage;
    }
    
    public Rectangle getThumbBounds() {
        this.checkWidget();
        this.parent.forceResize();
        final SCROLLBARINFO scrollbarinfo = new SCROLLBARINFO();
        scrollbarinfo.cbSize = SCROLLBARINFO.sizeof;
        int left;
        int top;
        int n;
        int n2;
        if ((this.style & 0x100) != 0x0) {
            OS.GetScrollBarInfo(this.parent.handle, -6, scrollbarinfo);
            left = scrollbarinfo.rcScrollBar.left + scrollbarinfo.xyThumbTop;
            top = scrollbarinfo.rcScrollBar.top;
            n = scrollbarinfo.xyThumbBottom - scrollbarinfo.xyThumbTop;
            n2 = scrollbarinfo.rcScrollBar.bottom - scrollbarinfo.rcScrollBar.top;
        }
        else {
            OS.GetScrollBarInfo(this.parent.handle, -5, scrollbarinfo);
            left = scrollbarinfo.rcScrollBar.left;
            top = scrollbarinfo.rcScrollBar.top + scrollbarinfo.xyThumbTop;
            n = scrollbarinfo.rcScrollBar.right - scrollbarinfo.rcScrollBar.left;
            n2 = scrollbarinfo.xyThumbBottom - scrollbarinfo.xyThumbTop;
        }
        final RECT rect = new RECT();
        rect.left = left;
        rect.top = top;
        rect.right = left + n;
        rect.bottom = top + n2;
        OS.MapWindowPoints(0, this.parent.handle, rect, 2);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public Rectangle getThumbTrackBounds() {
        this.checkWidget();
        this.parent.forceResize();
        final SCROLLBARINFO scrollbarinfo = new SCROLLBARINFO();
        scrollbarinfo.cbSize = SCROLLBARINFO.sizeof;
        int top;
        int n2;
        int left;
        int n3;
        if ((this.style & 0x100) != 0x0) {
            OS.GetScrollBarInfo(this.parent.handle, -6, scrollbarinfo);
            final int getSystemMetrics = OS.GetSystemMetrics(3);
            top = scrollbarinfo.rcScrollBar.top;
            final int n = scrollbarinfo.rcScrollBar.right - scrollbarinfo.rcScrollBar.left;
            n2 = getSystemMetrics;
            if (n <= 2 * getSystemMetrics) {
                left = scrollbarinfo.rcScrollBar.left + n / 2;
                n3 = 0;
            }
            else {
                left = scrollbarinfo.rcScrollBar.left + getSystemMetrics;
                n3 = n - 2 * getSystemMetrics;
            }
        }
        else {
            OS.GetScrollBarInfo(this.parent.handle, -5, scrollbarinfo);
            final int getSystemMetrics2 = OS.GetSystemMetrics(20);
            left = scrollbarinfo.rcScrollBar.left;
            n3 = getSystemMetrics2;
            final int n4 = scrollbarinfo.rcScrollBar.bottom - scrollbarinfo.rcScrollBar.top;
            if (n4 <= 2 * getSystemMetrics2) {
                top = scrollbarinfo.rcScrollBar.top + n4 / 2;
                n2 = 0;
            }
            else {
                top = scrollbarinfo.rcScrollBar.top + getSystemMetrics2;
                n2 = n4 - 2 * getSystemMetrics2;
            }
        }
        final RECT rect = new RECT();
        rect.left = left;
        rect.top = top;
        rect.right = left + n3;
        rect.bottom = top + n2;
        OS.MapWindowPoints(0, this.parent.handle, rect, 2);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public boolean getVisible() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            final SCROLLBARINFO scrollbarinfo = new SCROLLBARINFO();
            scrollbarinfo.cbSize = SCROLLBARINFO.sizeof;
            OS.GetScrollBarInfo(this.hwndScrollBar(), ((this.style & 0x200) != 0x0) ? -5 : -6, scrollbarinfo);
            return (scrollbarinfo.rgstate[0] & 0x8000) == 0x0;
        }
        return (this.state & 0x10) == 0x0;
    }
    
    int hwndScrollBar() {
        return this.parent.scrolledHandle();
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.getVisible() && this.parent.isVisible();
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.parent.horizontalBar == this) {
            this.parent.horizontalBar = null;
        }
        if (this.parent.verticalBar == this) {
            this.parent.verticalBar = null;
        }
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
    
    int scrollBarType() {
        return ((this.style & 0x200) != 0x0) ? 1 : 0;
    }
    
    public void setEnabled(final boolean b) {
        this.checkWidget();
        if (!OS.IsWinCE) {
            OS.EnableScrollBar(this.hwndScrollBar(), this.scrollBarType(), b ? 0 : 3);
            if (b) {
                this.state &= 0xFFFFFFF7;
            }
            else {
                this.state |= 0x8;
            }
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
        final int hwndScrollBar = this.hwndScrollBar();
        final int scrollBarType = this.scrollBarType();
        scrollinfo.fMask = 9;
        OS.GetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo);
        if (nMax - scrollinfo.nMin - scrollinfo.nPage < 1) {
            return;
        }
        scrollinfo.nMax = nMax;
        this.SetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo, true);
    }
    
    public void setMinimum(final int nMin) {
        this.checkWidget();
        if (nMin < 0) {
            return;
        }
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        final int hwndScrollBar = this.hwndScrollBar();
        final int scrollBarType = this.scrollBarType();
        scrollinfo.fMask = 9;
        OS.GetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo);
        if (scrollinfo.nMax - nMin - scrollinfo.nPage < 1) {
            return;
        }
        scrollinfo.nMin = nMin;
        this.SetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo, true);
    }
    
    public void setPageIncrement(final int pageIncrement) {
        this.checkWidget();
        if (pageIncrement < 1) {
            return;
        }
        this.pageIncrement = pageIncrement;
    }
    
    boolean SetScrollInfo(final int n, final int n2, final SCROLLINFO scrollinfo, boolean b) {
        boolean b2 = false;
        final boolean visible = this.getVisible();
        ScrollBar scrollBar = null;
        if (!OS.IsWinCE) {
            switch (n2) {
                case 0: {
                    scrollBar = this.parent.getVerticalBar();
                    break;
                }
                case 1: {
                    scrollBar = this.parent.getHorizontalBar();
                    break;
                }
            }
            b2 = (scrollBar != null && scrollBar.getVisible());
        }
        if (!visible || (this.state & 0x8) != 0x0) {
            b = false;
        }
        final boolean setScrollInfo = OS.SetScrollInfo(n, n2, scrollinfo, b);
        if (!visible && !OS.IsWinCE) {
            OS.ShowScrollBar(n, (!b2) ? 3 : n2, false);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && visible && scrollBar != null && !b2) {
            OS.ShowScrollBar(n, (n2 == 0) ? 1 : 0, false);
        }
        if ((this.state & 0x8) != 0x0 && !OS.IsWinCE) {
            OS.EnableScrollBar(n, n2, 3);
        }
        return setScrollInfo;
    }
    
    public void setSelection(final int nPos) {
        this.checkWidget();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        final int hwndScrollBar = this.hwndScrollBar();
        final int scrollBarType = this.scrollBarType();
        scrollinfo.fMask = 4;
        scrollinfo.nPos = nPos;
        this.SetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo, true);
    }
    
    public void setThumb(final int nPage) {
        this.checkWidget();
        if (nPage < 1) {
            return;
        }
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        final int hwndScrollBar = this.hwndScrollBar();
        final int scrollBarType = this.scrollBarType();
        scrollinfo.fMask = 11;
        OS.GetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo);
        scrollinfo.nPage = nPage;
        if (scrollinfo.nPage != 0) {
            final SCROLLINFO scrollinfo2 = scrollinfo;
            ++scrollinfo2.nPage;
        }
        this.SetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo, true);
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
        this.SetScrollInfo(this.hwndScrollBar(), this.scrollBarType(), scrollinfo, true);
    }
    
    public void setVisible(final boolean b) {
        this.checkWidget();
        if (b == this.getVisible()) {
            return;
        }
        if (OS.IsWinCE) {
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            final int hwndScrollBar = this.hwndScrollBar();
            final int scrollBarType = this.scrollBarType();
            scrollinfo.fMask = 3;
            if (b) {
                final SCROLLINFO scrollinfo2 = scrollinfo;
                scrollinfo2.fMask |= 0x8;
            }
            OS.GetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo);
            if (scrollinfo.nPage == scrollinfo.nMax - scrollinfo.nMin + 1) {
                final int nMax = scrollinfo.nMax;
                final SCROLLINFO scrollinfo3 = scrollinfo;
                ++scrollinfo3.nMax;
                OS.SetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo, false);
                scrollinfo.nMax = nMax;
                OS.SetScrollInfo(hwndScrollBar, scrollBarType, scrollinfo, true);
            }
            return;
        }
        this.state = (b ? (this.state & 0xFFFFFFEF) : (this.state | 0x10));
        final int hwndScrollBar2 = this.hwndScrollBar();
        final int scrollBarType2 = this.scrollBarType();
        if (OS.ShowScrollBar(hwndScrollBar2, scrollBarType2, b)) {
            if ((this.state & 0x8) == 0x0) {
                final SCROLLINFO scrollinfo4 = new SCROLLINFO();
                scrollinfo4.cbSize = SCROLLINFO.sizeof;
                scrollinfo4.fMask = 3;
                OS.GetScrollInfo(hwndScrollBar2, scrollBarType2, scrollinfo4);
                if (scrollinfo4.nMax - scrollinfo4.nMin - scrollinfo4.nPage >= 0) {
                    OS.EnableScrollBar(hwndScrollBar2, scrollBarType2, 0);
                }
            }
            this.sendEvent(b ? 22 : 23);
        }
    }
    
    LRESULT wmScrollChild(final int n, final int n2) {
        final int loword = OS.LOWORD(n);
        if (loword == 8) {
            return null;
        }
        final Event event = new Event();
        switch (loword) {
            case 4: {
                event.detail = 0;
                break;
            }
            case 5: {
                event.detail = 1;
                break;
            }
            case 6: {
                event.detail = 16777223;
                break;
            }
            case 7: {
                event.detail = 16777224;
                break;
            }
            case 1: {
                event.detail = 16777218;
                break;
            }
            case 0: {
                event.detail = 16777217;
                break;
            }
            case 3: {
                event.detail = 16777222;
                break;
            }
            case 2: {
                event.detail = 16777221;
                break;
            }
        }
        this.sendSelectionEvent(13, event, true);
        return null;
    }
}
