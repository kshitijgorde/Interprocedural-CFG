// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICS;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICSA;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICSW;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ExpandListener;

public class ExpandBar extends Composite
{
    ExpandItem[] items;
    int itemCount;
    ExpandItem focusItem;
    int spacing;
    int yCurrentScroll;
    int hFont;
    
    public ExpandBar(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.spacing = 4;
    }
    
    public void addExpandListener(final ExpandListener expandListener) {
        this.checkWidget();
        if (expandListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(expandListener);
        this.addListener(17, typedListener);
        this.addListener(18, typedListener);
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        return OS.DefWindowProc(n, n2, n3, n4);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    static int checkStyle(int n) {
        n &= 0xFFFFFEFF;
        return n | 0x40000;
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int max = 0;
        if ((n == -1 || n2 == -1) && this.itemCount > 0) {
            final int getDC = OS.GetDC(this.handle);
            int hExplorerBarTheme = 0;
            if (this.isAppThemed()) {
                hExplorerBarTheme = this.display.hExplorerBarTheme();
            }
            int n4 = 0;
            int selectObject = 0;
            if (hExplorerBarTheme == 0) {
                if (this.hFont != 0) {
                    n4 = this.hFont;
                }
                else if (!OS.IsWinCE) {
                    final NONCLIENTMETRICS nonclientmetrics = OS.IsUnicode ? new NONCLIENTMETRICSW() : new NONCLIENTMETRICSA();
                    nonclientmetrics.cbSize = NONCLIENTMETRICS.sizeof;
                    if (OS.SystemParametersInfo(41, 0, nonclientmetrics, 0)) {
                        n4 = OS.CreateFontIndirect(OS.IsUnicode ? ((NONCLIENTMETRICSW)nonclientmetrics).lfCaptionFont : ((NONCLIENTMETRICSA)nonclientmetrics).lfCaptionFont);
                    }
                }
                if (n4 != 0) {
                    selectObject = OS.SelectObject(getDC, n4);
                }
            }
            n3 += this.spacing;
            for (int i = 0; i < this.itemCount; ++i) {
                final ExpandItem expandItem = this.items[i];
                int n5 = n3 + expandItem.getHeaderHeight();
                if (expandItem.expanded) {
                    n5 += expandItem.height;
                }
                n3 = n5 + this.spacing;
                max = Math.max(max, expandItem.getPreferredWidth(hExplorerBarTheme, getDC));
            }
            if (n4 != 0) {
                OS.SelectObject(getDC, selectObject);
                if (n4 != this.hFont) {
                    OS.DeleteObject(n4);
                }
            }
            OS.ReleaseDC(this.handle, getDC);
        }
        if (max == 0) {
            max = 64;
        }
        if (n3 == 0) {
            n3 = 64;
        }
        if (n != -1) {
            max = n;
        }
        if (n2 != -1) {
            n3 = n2;
        }
        final Rectangle computeTrim = this.computeTrim(0, 0, max, n3);
        return new Point(computeTrim.width, computeTrim.height);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFFFD;
        this.state |= 0x2000;
    }
    
    void createItem(final ExpandItem focusItem, final int n, final int n2) {
        if (n2 < 0 || n2 > this.itemCount) {
            this.error(6);
        }
        if (this.itemCount == this.items.length) {
            final ExpandItem[] items = new ExpandItem[this.itemCount + 4];
            System.arraycopy(this.items, 0, items, 0, this.items.length);
            this.items = items;
        }
        System.arraycopy(this.items, n2, this.items, n2 + 1, this.itemCount - n2);
        this.items[n2] = focusItem;
        ++this.itemCount;
        if (this.focusItem == null) {
            this.focusItem = focusItem;
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        focusItem.width = Math.max(0, rect.right - rect.left - this.spacing * 2);
        this.layoutItems(n2, true);
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new ExpandItem[4];
        if (!this.isAppThemed()) {
            this.backgroundMode = 1;
        }
    }
    
    int defaultBackground() {
        if (!this.isAppThemed()) {
            return OS.GetSysColor(OS.COLOR_WINDOW);
        }
        return super.defaultBackground();
    }
    
    void destroyItem(final ExpandItem expandItem) {
        int n;
        for (n = 0; n < this.itemCount && this.items[n] != expandItem; ++n) {}
        if (n == this.itemCount) {
            return;
        }
        if (expandItem == this.focusItem) {
            final int n2 = (n > 0) ? (n - 1) : 1;
            if (n2 < this.itemCount) {
                (this.focusItem = this.items[n2]).redraw(true);
            }
            else {
                this.focusItem = null;
            }
        }
        System.arraycopy(this.items, n + 1, this.items, n, --this.itemCount - n);
        this.items[this.itemCount] = null;
        expandItem.redraw(true);
        this.layoutItems(n, true);
    }
    
    void drawThemeBackground(final int n, final int n2, final RECT rect) {
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        OS.MapWindowPoints(this.handle, n2, rect2, 2);
        OS.DrawThemeBackground(this.display.hExplorerBarTheme(), n, 5, 0, rect2, null);
    }
    
    void drawWidget(final GC gc, final RECT rect) {
        int hExplorerBarTheme = 0;
        if (this.isAppThemed()) {
            hExplorerBarTheme = this.display.hExplorerBarTheme();
        }
        if (hExplorerBarTheme != 0) {
            final RECT rect2 = new RECT();
            OS.GetClientRect(this.handle, rect2);
            OS.DrawThemeBackground(hExplorerBarTheme, gc.handle, 1, 0, rect2, rect);
        }
        else {
            this.drawBackground(gc.handle);
        }
        boolean b = false;
        if (this.handle == OS.GetFocus()) {
            b = ((OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0);
        }
        int n = 0;
        int selectObject = 0;
        if (hExplorerBarTheme == 0) {
            if (this.hFont != 0) {
                n = this.hFont;
            }
            else if (!OS.IsWinCE) {
                final NONCLIENTMETRICS nonclientmetrics = OS.IsUnicode ? new NONCLIENTMETRICSW() : new NONCLIENTMETRICSA();
                nonclientmetrics.cbSize = NONCLIENTMETRICS.sizeof;
                if (OS.SystemParametersInfo(41, 0, nonclientmetrics, 0)) {
                    n = OS.CreateFontIndirect(OS.IsUnicode ? ((NONCLIENTMETRICSW)nonclientmetrics).lfCaptionFont : ((NONCLIENTMETRICSA)nonclientmetrics).lfCaptionFont);
                }
            }
            if (n != 0) {
                selectObject = OS.SelectObject(gc.handle, n);
            }
            if (this.foreground != -1) {
                OS.SetTextColor(gc.handle, this.foreground);
            }
        }
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem expandItem = this.items[i];
            expandItem.drawItem(gc, hExplorerBarTheme, rect, expandItem == this.focusItem && b);
        }
        if (n != 0) {
            OS.SelectObject(gc.handle, selectObject);
            if (n != this.hFont) {
                OS.DeleteObject(n);
            }
        }
    }
    
    Control findBackgroundControl() {
        Control backgroundControl = super.findBackgroundControl();
        if (!this.isAppThemed() && backgroundControl == null) {
            backgroundControl = this;
        }
        return backgroundControl;
    }
    
    Control findThemeControl() {
        return this.isAppThemed() ? this : super.findThemeControl();
    }
    
    int getBandHeight() {
        if (this.hFont == 0) {
            return 24;
        }
        final int getDC = OS.GetDC(this.handle);
        final int selectObject = OS.SelectObject(getDC, this.hFont);
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.GetTextMetrics(getDC, textmetric);
        OS.SelectObject(getDC, selectObject);
        OS.ReleaseDC(this.handle, getDC);
        return Math.max(24, textmetric.tmHeight + 4);
    }
    
    public ExpandItem getItem(final int n) {
        this.checkWidget();
        if (n < 0 || n >= this.itemCount) {
            this.error(6);
        }
        return this.items[n];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return this.itemCount;
    }
    
    public ExpandItem[] getItems() {
        this.checkWidget();
        final ExpandItem[] array = new ExpandItem[this.itemCount];
        System.arraycopy(this.items, 0, array, 0, this.itemCount);
        return array;
    }
    
    public int getSpacing() {
        this.checkWidget();
        return this.spacing;
    }
    
    public int indexOf(final ExpandItem expandItem) {
        this.checkWidget();
        if (expandItem == null) {
            this.error(4);
        }
        for (int i = 0; i < this.itemCount; ++i) {
            if (this.items[i] == expandItem) {
                return i;
            }
        }
        return -1;
    }
    
    boolean isAppThemed() {
        return this.background == -1 && this.foreground == -1 && this.hFont == 0 && (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed());
    }
    
    void layoutItems(final int n, final boolean b) {
        if (n < this.itemCount) {
            int n2 = this.spacing - this.yCurrentScroll;
            for (int i = 0; i < n; ++i) {
                final ExpandItem expandItem = this.items[i];
                if (expandItem.expanded) {
                    n2 += expandItem.height;
                }
                n2 += expandItem.getHeaderHeight() + this.spacing;
            }
            for (int j = n; j < this.itemCount; ++j) {
                final ExpandItem expandItem2 = this.items[j];
                expandItem2.setBounds(this.spacing, n2, 0, 0, true, false);
                if (expandItem2.expanded) {
                    n2 += expandItem2.height;
                }
                n2 += expandItem2.getHeaderHeight() + this.spacing;
            }
        }
        if (b) {
            this.setScrollbar();
        }
    }
    
    void releaseChildren(final boolean b) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final ExpandItem expandItem = this.items[i];
                if (expandItem != null && !expandItem.isDisposed()) {
                    expandItem.release(false);
                }
            }
            this.items = null;
        }
        this.focusItem = null;
        super.releaseChildren(b);
    }
    
    public void removeExpandListener(final ExpandListener expandListener) {
        this.checkWidget();
        if (expandListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(17, expandListener);
        this.eventTable.unhook(18, expandListener);
    }
    
    void reskinChildren(final int n) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final ExpandItem expandItem = this.items[i];
                if (expandItem != null) {
                    expandItem.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    void setBackgroundPixel(final int backgroundPixel) {
        super.setBackgroundPixel(backgroundPixel);
        if (!OS.IsWinCE) {
            OS.RedrawWindow(this.handle, null, 0, 1157);
        }
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.hFont = ((font != null) ? font.handle : 0);
        this.layoutItems(0, true);
    }
    
    void setForegroundPixel(final int foregroundPixel) {
        super.setForegroundPixel(foregroundPixel);
        if (!OS.IsWinCE) {
            OS.RedrawWindow(this.handle, null, 0, 1157);
        }
    }
    
    void setScrollbar() {
        if (this.itemCount == 0) {
            return;
        }
        if ((this.style & 0x200) == 0x0) {
            return;
        }
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int nPage = rect.bottom - rect.top;
        final ExpandItem expandItem = this.items[this.itemCount - 1];
        int n = expandItem.y + this.getBandHeight() + this.spacing;
        if (expandItem.expanded) {
            n += expandItem.height;
        }
        if (this.yCurrentScroll > 0 && nPage > n) {
            this.yCurrentScroll = Math.max(0, this.yCurrentScroll + n - nPage);
            this.layoutItems(0, false);
        }
        final int nMax = n + this.yCurrentScroll;
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 7;
        scrollinfo.nMin = 0;
        scrollinfo.nMax = nMax;
        scrollinfo.nPage = nPage;
        scrollinfo.nPos = Math.min(this.yCurrentScroll, scrollinfo.nMax);
        if (scrollinfo.nPage != 0) {
            final SCROLLINFO scrollinfo2 = scrollinfo;
            ++scrollinfo2.nPage;
        }
        OS.SetScrollInfo(this.handle, 1, scrollinfo, true);
    }
    
    public void setSpacing(final int spacing) {
        this.checkWidget();
        if (spacing < 0) {
            return;
        }
        if (spacing == this.spacing) {
            return;
        }
        this.spacing = spacing;
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int max = Math.max(0, rect.right - rect.left - spacing * 2);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem expandItem = this.items[i];
            if (expandItem.width != max) {
                expandItem.setBounds(0, 0, max, expandItem.height, false, true);
            }
        }
        this.layoutItems(0, true);
        OS.InvalidateRect(this.handle, null, true);
    }
    
    void showItem(final ExpandItem expandItem) {
        final Control control = expandItem.control;
        if (control != null && !control.isDisposed()) {
            control.setVisible(expandItem.expanded);
        }
        expandItem.redraw(true);
        this.layoutItems(this.indexOf(expandItem) + 1, true);
    }
    
    void showFocus(final boolean b) {
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int n = rect.bottom - rect.top;
        int min = 0;
        if (b) {
            if (this.focusItem.y < 0) {
                min = Math.min(this.yCurrentScroll, -this.focusItem.y);
            }
        }
        else {
            int n2 = this.focusItem.y + this.getBandHeight();
            if (this.focusItem.expanded && n >= this.getBandHeight() + this.focusItem.height) {
                n2 += this.focusItem.height;
            }
            if (n2 > n) {
                min = n - n2;
            }
        }
        if (min != 0) {
            this.yCurrentScroll = Math.max(0, this.yCurrentScroll - min);
            if ((this.style & 0x200) != 0x0) {
                final SCROLLINFO scrollinfo = new SCROLLINFO();
                scrollinfo.cbSize = SCROLLINFO.sizeof;
                scrollinfo.fMask = 4;
                scrollinfo.nPos = this.yCurrentScroll;
                OS.SetScrollInfo(this.handle, 1, scrollinfo, true);
            }
            OS.ScrollWindowEx(this.handle, 0, min, null, null, 0, null, 3);
            for (int i = 0; i < this.itemCount; ++i) {
                final ExpandItem expandItem = this.items[i];
                expandItem.y += min;
            }
        }
    }
    
    TCHAR windowClass() {
        return this.display.windowClass;
    }
    
    int windowProc() {
        return this.display.windowProc;
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        final LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        if (this.focusItem == null) {
            return wm_KEYDOWN;
        }
        switch (n) {
            case 13:
            case 32: {
                final Event event = new Event();
                event.item = this.focusItem;
                this.sendEvent(this.focusItem.expanded ? 18 : 17, event);
                this.focusItem.expanded = !this.focusItem.expanded;
                this.showItem(this.focusItem);
                return LRESULT.ZERO;
            }
            case 38: {
                final int index = this.indexOf(this.focusItem);
                if (index > 0) {
                    this.focusItem.redraw(true);
                    (this.focusItem = this.items[index - 1]).redraw(true);
                    this.showFocus(true);
                    return LRESULT.ZERO;
                }
                break;
            }
            case 40: {
                final int index2 = this.indexOf(this.focusItem);
                if (index2 < this.itemCount - 1) {
                    this.focusItem.redraw(true);
                    (this.focusItem = this.items[index2 + 1]).redraw(true);
                    this.showFocus(false);
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        final LRESULT wm_KILLFOCUS = super.WM_KILLFOCUS(n, n2);
        if (this.focusItem != null) {
            this.focusItem.redraw(true);
        }
        return wm_KILLFOCUS;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final LRESULT wm_LBUTTONDOWN = super.WM_LBUTTONDOWN(n, n2);
        if (wm_LBUTTONDOWN == LRESULT.ZERO) {
            return wm_LBUTTONDOWN;
        }
        final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
        final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem focusItem = this.items[i];
            if (focusItem.isHover(get_X_LPARAM, get_Y_LPARAM) && this.focusItem != focusItem) {
                this.focusItem.redraw(true);
                (this.focusItem = focusItem).redraw(true);
                this.forceFocus();
                break;
            }
        }
        return wm_LBUTTONDOWN;
    }
    
    LRESULT WM_LBUTTONUP(final int n, final int n2) {
        final LRESULT wm_LBUTTONUP = super.WM_LBUTTONUP(n, n2);
        if (wm_LBUTTONUP == LRESULT.ZERO) {
            return wm_LBUTTONUP;
        }
        if (this.focusItem == null) {
            return wm_LBUTTONUP;
        }
        if (this.focusItem.isHover(OS.GET_X_LPARAM(n2), OS.GET_Y_LPARAM(n2))) {
            final Event event = new Event();
            event.item = this.focusItem;
            this.sendEvent(this.focusItem.expanded ? 18 : 17, event);
            this.focusItem.expanded = !this.focusItem.expanded;
            this.showItem(this.focusItem);
        }
        return wm_LBUTTONUP;
    }
    
    LRESULT WM_MOUSELEAVE(final int n, final int n2) {
        final LRESULT wm_MOUSELEAVE = super.WM_MOUSELEAVE(n, n2);
        if (wm_MOUSELEAVE != null) {
            return wm_MOUSELEAVE;
        }
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem expandItem = this.items[i];
            if (expandItem.hover) {
                expandItem.redraw(expandItem.hover = false);
                break;
            }
        }
        return wm_MOUSELEAVE;
    }
    
    LRESULT WM_MOUSEMOVE(final int n, final int n2) {
        final LRESULT wm_MOUSEMOVE = super.WM_MOUSEMOVE(n, n2);
        if (wm_MOUSEMOVE == LRESULT.ZERO) {
            return wm_MOUSEMOVE;
        }
        final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
        final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem expandItem = this.items[i];
            final boolean hover = expandItem.isHover(get_X_LPARAM, get_Y_LPARAM);
            if (expandItem.hover != hover) {
                expandItem.hover = hover;
                expandItem.redraw(false);
            }
        }
        return wm_MOUSEMOVE;
    }
    
    LRESULT WM_MOUSEWHEEL(final int n, final int n2) {
        return this.wmScrollWheel(true, n, n2);
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        final PAINTSTRUCT ps = new PAINTSTRUCT();
        final GCData gcData = new GCData();
        gcData.ps = ps;
        gcData.hwnd = this.handle;
        final GC new_GC = this.new_GC(gcData);
        if (new_GC != null) {
            final int width = ps.right - ps.left;
            final int height = ps.bottom - ps.top;
            if (width != 0 && height != 0) {
                final RECT rect = new RECT();
                OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                this.drawWidget(new_GC, rect);
                if (this.hooks(9) || this.filters(9)) {
                    final Event event = new Event();
                    event.gc = new_GC;
                    event.x = rect.left;
                    event.y = rect.top;
                    event.width = width;
                    event.height = height;
                    this.sendEvent(9, event);
                    event.gc = null;
                }
            }
            new_GC.dispose();
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PRINTCLIENT(final int n, final int n2) {
        final LRESULT wm_PRINTCLIENT = super.WM_PRINTCLIENT(n, n2);
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final GCData gcData = new GCData();
        gcData.device = this.display;
        gcData.foreground = this.getForegroundPixel();
        final GC win32_new = GC.win32_new(n, gcData);
        this.drawWidget(win32_new, rect);
        win32_new.dispose();
        return wm_PRINTCLIENT;
    }
    
    LRESULT WM_SETCURSOR(final int n, final int n2) {
        final LRESULT wm_SETCURSOR = super.WM_SETCURSOR(n, n2);
        if (wm_SETCURSOR != null) {
            return wm_SETCURSOR;
        }
        if ((short)OS.LOWORD(n2) == 1) {
            for (int i = 0; i < this.itemCount; ++i) {
                if (this.items[i].hover) {
                    OS.SetCursor(OS.LoadCursor(0, 32649));
                    return LRESULT.ONE;
                }
            }
        }
        return wm_SETCURSOR;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        final LRESULT wm_SETFOCUS = super.WM_SETFOCUS(n, n2);
        if (this.focusItem != null) {
            this.focusItem.redraw(true);
        }
        return wm_SETFOCUS;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int max = Math.max(0, rect.right - rect.left - this.spacing * 2);
        for (int i = 0; i < this.itemCount; ++i) {
            final ExpandItem expandItem = this.items[i];
            if (expandItem.width != max) {
                expandItem.setBounds(0, 0, max, expandItem.height, false, true);
            }
        }
        this.setScrollbar();
        OS.InvalidateRect(this.handle, null, true);
        return wm_SIZE;
    }
    
    LRESULT wmScroll(final ScrollBar scrollBar, final boolean b, final int n, final int n2, final int n3, final int n4) {
        final LRESULT wmScroll = super.wmScroll(scrollBar, true, n, n2, n3, n4);
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 4;
        OS.GetScrollInfo(this.handle, 1, scrollinfo);
        final int n5 = this.yCurrentScroll - scrollinfo.nPos;
        OS.ScrollWindowEx(this.handle, 0, n5, null, null, 0, null, 3);
        this.yCurrentScroll = scrollinfo.nPos;
        if (n5 != 0) {
            for (int i = 0; i < this.itemCount; ++i) {
                final ExpandItem expandItem = this.items[i];
                expandItem.y += n5;
            }
        }
        return wmScroll;
    }
}
