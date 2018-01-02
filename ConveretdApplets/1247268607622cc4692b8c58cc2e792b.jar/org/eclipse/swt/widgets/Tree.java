// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.win32.NMHEADER;
import org.eclipse.swt.internal.win32.NMRGINFO;
import org.eclipse.swt.internal.win32.NMTREEVIEW;
import org.eclipse.swt.internal.win32.NMTVITEMCHANGE;
import org.eclipse.swt.internal.win32.NMTVDISPINFO;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.win32.SHDRAGIMAGE;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.win32.HDHITTESTINFO;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.win32.SCROLLBARINFO;
import org.eclipse.swt.internal.win32.NMTTDISPINFO;
import org.eclipse.swt.internal.win32.TVSORTCB;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.HDLAYOUT;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.internal.win32.NMTTCUSTOMDRAW;
import org.eclipse.swt.internal.win32.TVHITTESTINFO;
import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.internal.win32.TVINSERTSTRUCT;
import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.HDITEM;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.NMTVCUSTOMDRAW;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.TVITEM;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.ImageList;

public class Tree extends Composite
{
    TreeItem[] items;
    TreeColumn[] columns;
    int columnCount;
    ImageList imageList;
    ImageList headerImageList;
    TreeItem currentItem;
    TreeColumn sortColumn;
    RECT focusRect;
    int hwndParent;
    int hwndHeader;
    int hAnchor;
    int hInsert;
    int hSelect;
    int lastID;
    int hFirstIndexOf;
    int hLastIndexOf;
    int lastIndexOf;
    int itemCount;
    int sortDirection;
    boolean dragStarted;
    boolean gestureCompleted;
    boolean insertAfter;
    boolean shrink;
    boolean ignoreShrink;
    boolean ignoreSelect;
    boolean ignoreExpand;
    boolean ignoreDeselect;
    boolean ignoreResize;
    boolean lockSelection;
    boolean oldSelected;
    boolean newSelected;
    boolean ignoreColumnMove;
    boolean linesVisible;
    boolean customDraw;
    boolean printClient;
    boolean painted;
    boolean ignoreItemHeight;
    boolean ignoreCustomDraw;
    boolean ignoreDrawForeground;
    boolean ignoreDrawBackground;
    boolean ignoreDrawFocus;
    boolean ignoreDrawSelection;
    boolean ignoreDrawHot;
    boolean ignoreFullSelection;
    boolean explorerTheme;
    boolean createdAsRTL;
    int scrollWidth;
    int selectionForeground;
    int headerToolTipHandle;
    int itemToolTipHandle;
    int lastTimerID;
    int lastTimerCount;
    static final int TIMER_MAX_COUNT = 8;
    static final int INSET = 3;
    static final int GRID_WIDTH = 1;
    static final int SORT_WIDTH = 10;
    static final int HEADER_MARGIN = 12;
    static final int HEADER_EXTRA = 3;
    static final int INCREMENT = 5;
    static final int EXPLORER_EXTRA = 2;
    static final int DRAG_IMAGE_SIZE = 301;
    static final boolean EXPLORER_THEME = true;
    static final int TreeProc;
    static final TCHAR TreeClass;
    static final int HeaderProc;
    static final TCHAR HeaderClass;
    
    static {
        TreeClass = new TCHAR(0, "SysTreeView32", true);
        HeaderClass = new TCHAR(0, "SysHeader32", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Tree.TreeClass, wndclass);
        TreeProc = wndclass.lpfnWndProc;
        OS.GetClassInfo(0, Tree.HeaderClass, wndclass);
        HeaderProc = wndclass.lpfnWndProc;
    }
    
    public Tree(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.lastTimerID = -1;
    }
    
    static int checkStyle(int n) {
        if ((n & 0x10) == 0x0) {
            n |= 0x300;
        }
        if ((n & 0x100) != 0x0 && (n & 0x200) == 0x0) {
            n |= 0x200;
        }
        return Widget.checkBits(n, 4, 2, 0, 0, 0, 0);
    }
    
    void _addListener(final int n, final Listener listener) {
        super._addListener(n, listener);
        switch (n) {
            case 29: {
                if ((this.state & 0x8000) != 0x0) {
                    OS.SetWindowLong(this.handle, -16, OS.GetWindowLong(this.handle, -16) & 0xFFFFFFEF);
                    break;
                }
                break;
            }
            case 40:
            case 41:
            case 42: {
                this.customDraw = true;
                this.style |= 0x20000000;
                if (this.isCustomToolTip()) {
                    this.createItemToolTips();
                }
                OS.SendMessage(this.handle, 4385, 0, 0);
                int getWindowLong = OS.GetWindowLong(this.handle, -16);
                if (n == 41) {
                    getWindowLong |= 0x8000;
                }
                if ((this.style & 0x10000) != 0x0 && n != 41 && !this.explorerTheme) {
                    getWindowLong &= 0xFFFFEFFF;
                }
                if (getWindowLong == OS.GetWindowLong(this.handle, -16)) {
                    break;
                }
                OS.SetWindowLong(this.handle, -16, getWindowLong);
                OS.InvalidateRect(this.handle, null, true);
                if (OS.SendMessage(this.handle, 4357, 0, 0) != 0 && (getWindowLong & 0x8000) != 0x0 && !OS.IsWinCE) {
                    OS.ShowScrollBar(this.handle, 0, false);
                    break;
                }
                break;
            }
        }
    }
    
    TreeItem _getItem(final int hItem) {
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        tvitem.hItem = hItem;
        if (OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem) != 0) {
            return this._getItem(tvitem.hItem, tvitem.lParam);
        }
        return null;
    }
    
    TreeItem _getItem(final int n, final int n2) {
        if ((this.style & 0x10000000) == 0x0) {
            return this.items[n2];
        }
        return (n2 != -1) ? this.items[n2] : new TreeItem(this, 0, -1, -1, n);
    }
    
    void _setBackgroundPixel(final int n) {
        final int sendMessage = OS.SendMessage(this.handle, 4383, 0, 0);
        if (sendMessage != n) {
            if (sendMessage != -1) {
                OS.SendMessage(this.handle, 4381, 0, -1);
            }
            OS.SendMessage(this.handle, 4381, 0, n);
            if (this.explorerTheme) {
                final int sendMessage2 = OS.SendMessage(this.handle, 4397, 0, 0);
                int n2;
                if (n == -1 && this.findImageControl() == null) {
                    n2 = (sendMessage2 | 0x40);
                }
                else {
                    n2 = (sendMessage2 & 0xFFFFFFBF);
                }
                OS.SendMessage(this.handle, 4396, 0, n2);
            }
            if ((this.style & 0x20) != 0x0) {
                this.setCheckboxImageList();
            }
        }
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
    
    public void addTreeListener(final TreeListener treeListener) {
        this.checkWidget();
        if (treeListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(treeListener);
        this.addListener(17, typedListener);
        this.addListener(18, typedListener);
    }
    
    int borderHandle() {
        return (this.hwndParent != 0) ? this.hwndParent : this.handle;
    }
    
    LRESULT CDDS_ITEMPOSTPAINT(final NMTVCUSTOMDRAW nmtvcustomdraw, final int n, final int n2) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        if (nmtvcustomdraw.left == nmtvcustomdraw.right) {
            return new LRESULT(0);
        }
        final int hdc = nmtvcustomdraw.hdc;
        OS.RestoreDC(hdc, -1);
        final TreeItem item = this.getItem(nmtvcustomdraw);
        if (item == null) {
            return null;
        }
        if (nmtvcustomdraw.left >= nmtvcustomdraw.right || nmtvcustomdraw.top >= nmtvcustomdraw.bottom) {
            return null;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return null;
        }
        if ((this.style & 0x10000) != 0x0 || this.findImageControl() != null || this.ignoreDrawSelection || this.explorerTheme) {
            OS.SetBkMode(hdc, 1);
        }
        final boolean itemSelected = this.isItemSelected(nmtvcustomdraw);
        final boolean b = this.explorerTheme && (nmtvcustomdraw.uItemState & 0x40) != 0x0;
        if (OS.IsWindowEnabled(this.handle) && this.explorerTheme && (OS.GetWindowLong(this.handle, -16) & 0x200) != 0x0) {
            if ((this.style & 0x10000) != 0x0 && (itemSelected || b)) {
                OS.SetTextColor(hdc, OS.GetSysColor(OS.COLOR_WINDOWTEXT));
            }
            else {
                OS.SetTextColor(hdc, this.getForegroundPixel());
            }
        }
        int[] array = null;
        final RECT rect = new RECT();
        OS.GetClientRect(this.scrolledHandle(), rect);
        if (this.hwndHeader != 0) {
            OS.MapWindowPoints(this.hwndParent, this.handle, rect, 2);
            if (this.columnCount != 0) {
                array = new int[this.columnCount];
                OS.SendMessage(this.hwndHeader, 4625, this.columnCount, array);
            }
        }
        int index = -1;
        int sortColumnPixel = -1;
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0 && this.findImageControl() == null) {
            index = this.indexOf(this.sortColumn);
            sortColumnPixel = this.getSortColumnPixel();
        }
        int n3 = 0;
        Point point = null;
        for (int i = 0; i < Math.max(1, this.columnCount); ++i) {
            final int n4 = (array == null) ? i : array[i];
            int cxy = nmtvcustomdraw.right - nmtvcustomdraw.left;
            if (this.columnCount > 0 && this.hwndHeader != 0) {
                final HDITEM hditem = new HDITEM();
                hditem.mask = 1;
                OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, n4, hditem);
                cxy = hditem.cxy;
            }
            if (i == 0 && (this.style & 0x10000) != 0x0 && ((!this.explorerTheme && !this.ignoreDrawSelection && this.findImageControl() == null) || (itemSelected && !this.ignoreDrawSelection) || (b && !this.ignoreDrawHot))) {
                boolean b2 = true;
                final RECT rect2 = new RECT();
                OS.SetRect(rect2, cxy, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                if (this.explorerTheme) {
                    if (this.hooks(40)) {
                        final RECT bounds;
                        final RECT rect3 = bounds = item.getBounds(n4, 1 != 0, 1 != 0, 0 != 0, 0 != 0, 1 != 0, hdc);
                        bounds.left -= 2;
                        final RECT rect4 = rect3;
                        rect4.right += 3;
                        rect2.left = rect3.left;
                        rect2.right = rect3.right;
                        if (this.columnCount > 0 && this.hwndHeader != 0) {
                            final HDITEM hditem2 = new HDITEM();
                            hditem2.mask = 1;
                            OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, n4, hditem2);
                            rect2.right = Math.min(rect2.right, nmtvcustomdraw.left + hditem2.cxy);
                        }
                    }
                    final RECT rect5 = new RECT();
                    OS.SetRect(rect5, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                    if (this.columnCount > 0 && this.hwndHeader != 0) {
                        int right = 0;
                        final HDITEM hditem3 = new HDITEM();
                        hditem3.mask = 1;
                        for (int j = 0; j < this.columnCount; ++j) {
                            OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, j, hditem3);
                            right += hditem3.cxy;
                        }
                        if (right > rect.right - rect.left) {
                            rect5.left = 0;
                            rect5.right = right;
                        }
                        else {
                            rect5.left = rect.left;
                            rect5.right = rect.right;
                        }
                    }
                    b2 = false;
                    final int openThemeData = OS.OpenThemeData(this.handle, Display.TREEVIEW);
                    int n5 = itemSelected ? 3 : 2;
                    if (OS.GetFocus() != this.handle && itemSelected && !b) {
                        n5 = 5;
                    }
                    OS.DrawThemeBackground(openThemeData, hdc, 1, n5, rect5, rect2);
                    OS.CloseThemeData(openThemeData);
                }
                if (b2) {
                    this.fillBackground(hdc, OS.GetBkColor(hdc), rect2);
                }
            }
            if (n3 + cxy > rect.left) {
                RECT rect6 = new RECT();
                int n6 = 1;
                int n7 = 1;
                int n8 = 1;
                int n9 = 0;
                RECT bounds4;
                if (i == 0) {
                    n8 = (n6 = (n7 = 0));
                    if (this.findImageControl() != null) {
                        if (this.explorerTheme) {
                            if (OS.IsWindowEnabled(this.handle) && !this.hooks(40)) {
                                Image image = null;
                                if (n4 == 0) {
                                    image = item.image;
                                }
                                else {
                                    final Image[] images = item.images;
                                    if (images != null) {
                                        image = images[n4];
                                    }
                                }
                                if (image != null) {
                                    final Rectangle bounds2 = image.getBounds();
                                    if (point == null) {
                                        point = this.getImageSize();
                                    }
                                    if (!this.ignoreDrawForeground) {
                                        final GCData gcData = new GCData();
                                        gcData.device = this.display;
                                        final GC win32_new = GC.win32_new(hdc, gcData);
                                        final RECT bounds3 = item.getBounds(n4, false, true, false, false, true, hdc);
                                        win32_new.setClipping(bounds3.left, bounds3.top, bounds3.right - bounds3.left, bounds3.bottom - bounds3.top);
                                        win32_new.drawImage(image, 0, 0, bounds2.width, bounds2.height, bounds3.left, bounds3.top, point.x, point.y);
                                        OS.SelectClipRgn(hdc, 0);
                                        win32_new.dispose();
                                    }
                                }
                            }
                        }
                        else {
                            n7 = (n6 = (n9 = 1));
                            rect6 = item.getBounds(n4, true, false, false, false, true, hdc);
                            if (this.linesVisible) {
                                final RECT rect7 = rect6;
                                ++rect7.right;
                                final RECT rect8 = rect6;
                                ++rect8.bottom;
                            }
                        }
                    }
                    if (itemSelected && !this.ignoreDrawSelection && !this.ignoreDrawBackground) {
                        if (!this.explorerTheme) {
                            this.fillBackground(hdc, OS.GetBkColor(hdc), rect6);
                        }
                        n9 = 0;
                    }
                    bounds4 = rect6;
                    if (this.hooks(40)) {
                        n7 = (n6 = (n8 = 1));
                        rect6 = item.getBounds(n4, true, true, false, false, true, hdc);
                        if ((this.style & 0x10000) != 0x0) {
                            bounds4 = rect6;
                        }
                        else {
                            bounds4 = item.getBounds(n4, true, false, false, false, true, hdc);
                        }
                    }
                }
                else {
                    this.selectionForeground = -1;
                    final boolean ignoreDrawForeground = false;
                    this.ignoreDrawHot = ignoreDrawForeground;
                    this.ignoreDrawFocus = ignoreDrawForeground;
                    this.ignoreDrawSelection = ignoreDrawForeground;
                    this.ignoreDrawBackground = ignoreDrawForeground;
                    this.ignoreDrawForeground = ignoreDrawForeground;
                    OS.SetRect(rect6, n3, nmtvcustomdraw.top, n3 + cxy, nmtvcustomdraw.bottom);
                    bounds4 = rect6;
                }
                int n10 = -1;
                int n11 = -1;
                int n12 = item.fontHandle(n4);
                if (this.selectionForeground != -1) {
                    n10 = this.selectionForeground;
                }
                if (OS.IsWindowEnabled(this.handle)) {
                    boolean b3 = false;
                    if (itemSelected) {
                        if (i != 0 && (this.style & 0x10000) == 0x0) {
                            OS.SetTextColor(hdc, this.getForegroundPixel());
                            OS.SetBkColor(hdc, this.getBackgroundPixel());
                            n9 = ((b3 = true) ? 1 : 0);
                        }
                    }
                    else {
                        n9 = ((b3 = true) ? 1 : 0);
                    }
                    if (b3) {
                        n10 = ((item.cellForeground != null) ? item.cellForeground[n4] : -1);
                        if (n10 == -1) {
                            n10 = item.foreground;
                        }
                    }
                    if (n9 != 0) {
                        n11 = ((item.cellBackground != null) ? item.cellBackground[n4] : -1);
                        if (n11 == -1) {
                            n11 = item.background;
                        }
                        if (n11 == -1 && n4 == index) {
                            n11 = sortColumnPixel;
                        }
                    }
                }
                else if (n11 == -1 && n4 == index) {
                    n9 = 1;
                    n11 = sortColumnPixel;
                }
                if (this.explorerTheme && (itemSelected || (nmtvcustomdraw.uItemState & 0x40) != 0x0)) {
                    if ((this.style & 0x10000) != 0x0) {
                        n9 = 0;
                    }
                    else if (i == 0) {
                        n9 = 0;
                        if (!this.hooks(40)) {
                            n7 = 0;
                        }
                    }
                }
                if (n6 != 0) {
                    if (i != 0) {
                        if (this.hooks(41)) {
                            this.sendMeasureItemEvent(item, n4, hdc, itemSelected ? 2 : 0);
                            if (this.isDisposed()) {
                                break;
                            }
                            if (item.isDisposed()) {
                                break;
                            }
                        }
                        if (this.hooks(40)) {
                            final RECT bounds5 = item.getBounds(n4, true, true, true, true, true, hdc);
                            final int saveDC = OS.SaveDC(hdc);
                            final GCData gcData2 = new GCData();
                            gcData2.device = this.display;
                            gcData2.foreground = OS.GetTextColor(hdc);
                            gcData2.background = OS.GetBkColor(hdc);
                            if (!itemSelected || (this.style & 0x10000) == 0x0) {
                                if (n10 != -1) {
                                    gcData2.foreground = n10;
                                }
                                if (n11 != -1) {
                                    gcData2.background = n11;
                                }
                            }
                            gcData2.font = item.getFont(n4);
                            gcData2.uiState = OS.SendMessage(this.handle, 297, 0, 0);
                            final GC win32_new2 = GC.win32_new(hdc, gcData2);
                            final Event event = new Event();
                            event.item = item;
                            event.index = n4;
                            event.gc = win32_new2;
                            final Event event2 = event;
                            event2.detail |= 0x10;
                            if (n11 != -1) {
                                final Event event3 = event;
                                event3.detail |= 0x8;
                            }
                            if ((this.style & 0x10000) != 0x0) {
                                if (b) {
                                    final Event event4 = event;
                                    event4.detail |= 0x20;
                                }
                                if (itemSelected) {
                                    final Event event5 = event;
                                    event5.detail |= 0x2;
                                }
                                if (!this.explorerTheme && OS.SendMessage(this.handle, 4362, 9, 0) == nmtvcustomdraw.dwItemSpec && this.handle == OS.GetFocus() && (OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0) {
                                    final Event event6 = event;
                                    event6.detail |= 0x4;
                                }
                            }
                            event.x = bounds5.left;
                            event.y = bounds5.top;
                            event.width = bounds5.right - bounds5.left;
                            event.height = bounds5.bottom - bounds5.top;
                            win32_new2.setClipping(event.x, event.y, event.width, event.height);
                            this.sendEvent(40, event);
                            event.gc = null;
                            final int foreground = gcData2.foreground;
                            win32_new2.dispose();
                            OS.RestoreDC(hdc, saveDC);
                            if (this.isDisposed()) {
                                break;
                            }
                            if (item.isDisposed()) {
                                break;
                            }
                            if (event.doit) {
                                this.ignoreDrawForeground = ((event.detail & 0x10) == 0x0);
                                this.ignoreDrawBackground = ((event.detail & 0x8) == 0x0);
                                if ((this.style & 0x10000) != 0x0) {
                                    this.ignoreDrawSelection = ((event.detail & 0x2) == 0x0);
                                    this.ignoreDrawFocus = ((event.detail & 0x4) == 0x0);
                                    this.ignoreDrawHot = ((event.detail & 0x20) == 0x0);
                                }
                            }
                            else {
                                final boolean ignoreDrawForeground2 = true;
                                this.ignoreDrawHot = ignoreDrawForeground2;
                                this.ignoreDrawFocus = ignoreDrawForeground2;
                                this.ignoreDrawSelection = ignoreDrawForeground2;
                                this.ignoreDrawBackground = ignoreDrawForeground2;
                                this.ignoreDrawForeground = ignoreDrawForeground2;
                            }
                            if (itemSelected && this.ignoreDrawSelection) {
                                this.ignoreDrawHot = true;
                            }
                            if ((this.style & 0x10000) != 0x0) {
                                if (this.ignoreDrawSelection) {
                                    this.ignoreFullSelection = true;
                                }
                                if (!this.ignoreDrawSelection || !this.ignoreDrawHot) {
                                    if (!itemSelected && !b) {
                                        this.selectionForeground = OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT);
                                    }
                                    else if (!this.explorerTheme) {
                                        n9 = 1;
                                        this.ignoreDrawBackground = false;
                                        if ((this.handle == OS.GetFocus() || this.display.getHighContrast()) && OS.IsWindowEnabled(this.handle)) {
                                            n11 = OS.GetSysColor(OS.COLOR_HIGHLIGHT);
                                        }
                                        else {
                                            n11 = OS.GetSysColor(OS.COLOR_3DFACE);
                                        }
                                        if (!this.ignoreFullSelection && n4 == this.columnCount - 1) {
                                            final RECT rect9 = new RECT();
                                            OS.SetRect(rect9, bounds4.left, bounds4.top, nmtvcustomdraw.right, bounds4.bottom);
                                            bounds4 = rect9;
                                        }
                                    }
                                    else {
                                        final RECT rect10 = new RECT();
                                        OS.SetRect(rect10, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                                        if (this.columnCount > 0 && this.hwndHeader != 0) {
                                            int right2 = 0;
                                            final HDITEM hditem4 = new HDITEM();
                                            hditem4.mask = 1;
                                            for (int k = 0; k < this.columnCount; ++k) {
                                                OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, k, hditem4);
                                                right2 += hditem4.cxy;
                                            }
                                            if (right2 > rect.right - rect.left) {
                                                rect10.left = 0;
                                                rect10.right = right2;
                                            }
                                            else {
                                                rect10.left = rect.left;
                                                rect10.right = rect.right;
                                            }
                                            if (n4 == this.columnCount - 1) {
                                                final RECT rect11 = new RECT();
                                                OS.SetRect(rect11, bounds4.left, bounds4.top, rect10.right, bounds4.bottom);
                                                bounds4 = rect11;
                                            }
                                        }
                                        final int openThemeData2 = OS.OpenThemeData(this.handle, Display.TREEVIEW);
                                        int n13 = itemSelected ? 3 : 2;
                                        if (OS.GetFocus() != this.handle && itemSelected && !b) {
                                            n13 = 5;
                                        }
                                        OS.DrawThemeBackground(openThemeData2, hdc, 1, n13, rect10, bounds4);
                                        OS.CloseThemeData(openThemeData2);
                                    }
                                }
                                else if (itemSelected) {
                                    this.selectionForeground = foreground;
                                    if (!this.explorerTheme && n11 == -1 && OS.IsWindowEnabled(this.handle)) {
                                        Control backgroundControl = this.findBackgroundControl();
                                        if (backgroundControl == null) {
                                            backgroundControl = this;
                                        }
                                        n11 = backgroundControl.getBackgroundPixel();
                                    }
                                }
                            }
                        }
                        if (this.selectionForeground != -1) {
                            n10 = this.selectionForeground;
                        }
                    }
                    if (!this.ignoreDrawBackground) {
                        if (n11 != -1) {
                            if (n9 != 0) {
                                this.fillBackground(hdc, n11, bounds4);
                            }
                        }
                        else {
                            final Control imageControl = this.findImageControl();
                            if (imageControl != null) {
                                if (i == 0) {
                                    OS.SetRect(rect6, rect6.left, rect6.top, Math.min(rect6.right, cxy), rect6.bottom);
                                    if (n9 != 0) {
                                        this.fillImageBackground(hdc, imageControl, rect6, 0, 0);
                                    }
                                }
                                else if (n9 != 0) {
                                    this.fillImageBackground(hdc, imageControl, rect6, 0, 0);
                                }
                            }
                        }
                    }
                    final RECT rect12 = rect6;
                    rect12.left += 2;
                    if (n8 != 0) {
                        Image image2 = null;
                        if (n4 == 0) {
                            image2 = item.image;
                        }
                        else {
                            final Image[] images2 = item.images;
                            if (images2 != null) {
                                image2 = images2[n4];
                            }
                        }
                        final int n14 = (i != 0) ? 3 : 0;
                        final int n15 = (i != 0) ? 3 : 5;
                        if (image2 != null) {
                            final Rectangle bounds6 = image2.getBounds();
                            if (point == null) {
                                point = this.getImageSize();
                            }
                            if (!this.ignoreDrawForeground) {
                                final int top = rect6.top;
                                final int max = Math.max(rect6.left, rect6.left - n14 + 1);
                                final GCData gcData3 = new GCData();
                                gcData3.device = this.display;
                                final GC win32_new3 = GC.win32_new(hdc, gcData3);
                                win32_new3.setClipping(max, rect6.top, rect6.right - max, rect6.bottom - rect6.top);
                                win32_new3.drawImage(image2, 0, 0, bounds6.width, bounds6.height, max, top, point.x, point.y);
                                OS.SelectClipRgn(hdc, 0);
                                win32_new3.dispose();
                            }
                            OS.SetRect(rect6, rect6.left + point.x + n15, rect6.top, rect6.right - n14, rect6.bottom);
                        }
                        else if (i == 0) {
                            if (OS.SendMessage(this.handle, 4360, 0, 0) != 0) {
                                if (point == null) {
                                    point = this.getImageSize();
                                }
                                rect6.left = Math.min(rect6.left + point.x + n15, rect6.right);
                            }
                        }
                        else {
                            OS.SetRect(rect6, rect6.left + n15, rect6.top, rect6.right - n14, rect6.bottom);
                        }
                    }
                    if (n7 && rect6.left < rect6.right) {
                        String text = null;
                        if (n4 == 0) {
                            text = item.text;
                        }
                        else {
                            final String[] strings = item.strings;
                            if (strings != null) {
                                text = strings[n4];
                            }
                        }
                        if (text != null) {
                            if (n12 != -1) {
                                n12 = OS.SelectObject(hdc, n12);
                            }
                            if (n10 != -1) {
                                n10 = OS.SetTextColor(hdc, n10);
                            }
                            if (n11 != -1) {
                                n11 = OS.SetBkColor(hdc, n11);
                            }
                            int n16 = 2084;
                            if (i != 0) {
                                n16 |= 0x8000;
                            }
                            final TreeColumn treeColumn = (this.columns != null) ? this.columns[n4] : null;
                            if (treeColumn != null) {
                                if ((treeColumn.style & 0x1000000) != 0x0) {
                                    n16 |= 0x1;
                                }
                                if ((treeColumn.style & 0x20000) != 0x0) {
                                    n16 |= 0x2;
                                }
                            }
                            final TCHAR tchar = new TCHAR(this.getCodePage(), text, false);
                            if (!this.ignoreDrawForeground) {
                                OS.DrawText(hdc, tchar, tchar.length(), rect6, n16);
                            }
                            OS.DrawText(hdc, tchar, tchar.length(), rect6, n16 | 0x400);
                            if (n12 != -1) {
                                OS.SelectObject(hdc, n12);
                            }
                            if (n10 != -1) {
                                n10 = OS.SetTextColor(hdc, n10);
                            }
                            if (n11 != -1) {
                                n11 = OS.SetBkColor(hdc, n11);
                            }
                        }
                    }
                }
                if (this.selectionForeground != -1) {
                    n10 = this.selectionForeground;
                }
                if (this.hooks(42)) {
                    final RECT bounds7 = item.getBounds(n4, true, true, false, false, false, hdc);
                    final int saveDC2 = OS.SaveDC(hdc);
                    final GCData gcData4 = new GCData();
                    gcData4.device = this.display;
                    gcData4.font = item.getFont(n4);
                    gcData4.foreground = OS.GetTextColor(hdc);
                    gcData4.background = OS.GetBkColor(hdc);
                    if (itemSelected && (this.style & 0x10000) != 0x0) {
                        if (this.selectionForeground != -1) {
                            gcData4.foreground = this.selectionForeground;
                        }
                    }
                    else {
                        if (n10 != -1) {
                            gcData4.foreground = n10;
                        }
                        if (n11 != -1) {
                            gcData4.background = n11;
                        }
                    }
                    gcData4.uiState = OS.SendMessage(this.handle, 297, 0, 0);
                    final GC win32_new4 = GC.win32_new(hdc, gcData4);
                    final Event event7 = new Event();
                    event7.item = item;
                    event7.index = n4;
                    event7.gc = win32_new4;
                    final Event event8 = event7;
                    event8.detail |= 0x10;
                    if (n11 != -1) {
                        final Event event9 = event7;
                        event9.detail |= 0x8;
                    }
                    if (b) {
                        final Event event10 = event7;
                        event10.detail |= 0x20;
                    }
                    if (itemSelected && (i == 0 || (this.style & 0x10000) != 0x0)) {
                        final Event event11 = event7;
                        event11.detail |= 0x2;
                    }
                    if (!this.explorerTheme && OS.SendMessage(this.handle, 4362, 9, 0) == nmtvcustomdraw.dwItemSpec && (i == 0 || (this.style & 0x10000) != 0x0) && this.handle == OS.GetFocus() && (OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0) {
                        final Event event12 = event7;
                        event12.detail |= 0x4;
                    }
                    event7.x = bounds7.left;
                    event7.y = bounds7.top;
                    event7.width = bounds7.right - bounds7.left;
                    event7.height = bounds7.bottom - bounds7.top;
                    final RECT bounds8 = item.getBounds(n4, true, true, true, true, true, hdc);
                    win32_new4.setClipping(bounds8.left, bounds8.top, bounds8.right - bounds8.left, bounds8.bottom - bounds8.top);
                    this.sendEvent(42, event7);
                    if (gcData4.focusDrawn) {
                        this.focusRect = null;
                    }
                    event7.gc = null;
                    win32_new4.dispose();
                    OS.RestoreDC(hdc, saveDC2);
                    if (this.isDisposed()) {
                        break;
                    }
                    if (item.isDisposed()) {
                        break;
                    }
                }
            }
            n3 += cxy;
            if (n3 > rect.right) {
                break;
            }
        }
        if (this.linesVisible) {
            if ((this.style & 0x10000) != 0x0 && this.columnCount != 0) {
                final HDITEM hditem5 = new HDITEM();
                hditem5.mask = 1;
                OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, 0, hditem5);
                final RECT rect13 = new RECT();
                OS.SetRect(rect13, nmtvcustomdraw.left + hditem5.cxy, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                OS.DrawEdge(hdc, rect13, 8, 8);
            }
            final RECT rect14 = new RECT();
            OS.SetRect(rect14, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
            OS.DrawEdge(hdc, rect14, 8, 8);
        }
        if (!this.ignoreDrawFocus && this.focusRect != null) {
            OS.DrawFocusRect(hdc, this.focusRect);
            this.focusRect = null;
        }
        else if (!this.explorerTheme && this.handle == OS.GetFocus() && (OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0 && OS.SendMessage(this.handle, 4362, 9, 0) == item.handle && !this.ignoreDrawFocus && this.findImageControl() != null) {
            if ((this.style & 0x10000) != 0x0) {
                final RECT rect15 = new RECT();
                OS.SetRect(rect15, 0, nmtvcustomdraw.top, rect.right + 1, nmtvcustomdraw.bottom);
                OS.DrawFocusRect(hdc, rect15);
            }
            else {
                final int sendMessage = OS.SendMessage(this.hwndHeader, 4623, 0, 0);
                final RECT bounds9 = item.getBounds(sendMessage, true, false, false, false, false, hdc);
                final RECT bounds10 = item.getBounds(sendMessage, true, false, false, false, true, hdc);
                OS.IntersectClipRect(hdc, bounds10.left, bounds10.top, bounds10.right, bounds10.bottom);
                OS.DrawFocusRect(hdc, bounds9);
                OS.SelectClipRgn(hdc, 0);
            }
        }
        return new LRESULT(0);
    }
    
    LRESULT CDDS_ITEMPREPAINT(final NMTVCUSTOMDRAW nmtvcustomdraw, final int n, final int n2) {
        final TreeItem item = this.getItem(nmtvcustomdraw);
        if (item == null) {
            return null;
        }
        final int hdc = nmtvcustomdraw.hdc;
        final int index = (this.hwndHeader != 0) ? OS.SendMessage(this.hwndHeader, 4623, 0, 0) : 0;
        final int fontHandle = item.fontHandle(index);
        if (fontHandle != -1) {
            OS.SelectObject(hdc, fontHandle);
        }
        if (this.ignoreCustomDraw || nmtvcustomdraw.left == nmtvcustomdraw.right) {
            return new LRESULT((fontHandle == -1) ? 0 : 2);
        }
        RECT rect = null;
        if (this.columnCount != 0) {
            boolean b = !this.printClient;
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                b = true;
            }
            if (b) {
                rect = new RECT();
                final HDITEM hditem = new HDITEM();
                hditem.mask = 1;
                OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, index, hditem);
                OS.SetRect(rect, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.left + hditem.cxy, nmtvcustomdraw.bottom);
            }
        }
        int foreground = -1;
        int background = -1;
        if (OS.IsWindowEnabled(this.handle)) {
            foreground = ((item.cellForeground != null) ? item.cellForeground[index] : -1);
            if (foreground == -1) {
                foreground = item.foreground;
            }
            background = ((item.cellBackground != null) ? item.cellBackground[index] : -1);
            if (background == -1) {
                background = item.background;
            }
        }
        int sortColumnPixel = -1;
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0 && this.findImageControl() == null && this.indexOf(this.sortColumn) == index) {
            sortColumnPixel = this.getSortColumnPixel();
            if (background == -1) {
                background = sortColumnPixel;
            }
        }
        final boolean itemSelected = this.isItemSelected(nmtvcustomdraw);
        final boolean b2 = this.explorerTheme && (nmtvcustomdraw.uItemState & 0x40) != 0x0;
        boolean b3 = this.explorerTheme && (nmtvcustomdraw.uItemState & 0x10) != 0x0;
        if (OS.IsWindowVisible(this.handle) && nmtvcustomdraw.left < nmtvcustomdraw.right && nmtvcustomdraw.top < nmtvcustomdraw.bottom) {
            if (fontHandle != -1) {
                OS.SelectObject(hdc, fontHandle);
            }
            if (this.linesVisible) {
                final RECT rect2 = new RECT();
                OS.SetRect(rect2, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                OS.DrawEdge(hdc, rect2, 8, 8);
            }
            Event sendMeasureItemEvent = null;
            if (this.hooks(41)) {
                sendMeasureItemEvent = this.sendMeasureItemEvent(item, index, hdc, itemSelected ? 2 : 0);
                if (this.isDisposed() || item.isDisposed()) {
                    return null;
                }
            }
            this.selectionForeground = -1;
            final boolean b4 = false;
            this.ignoreFullSelection = b4;
            this.ignoreDrawHot = b4;
            this.ignoreDrawFocus = b4;
            this.ignoreDrawSelection = b4;
            this.ignoreDrawBackground = b4;
            this.ignoreDrawForeground = b4;
            if (this.hooks(40)) {
                final RECT rect3 = new RECT();
                OS.SetRect(rect3, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                final RECT bounds = item.getBounds(index, true, true, true, true, true, hdc);
                if (sortColumnPixel != -1) {
                    this.drawBackground(hdc, bounds, sortColumnPixel, 0, 0);
                }
                else if (OS.IsWindowEnabled(this.handle) || this.findImageControl() != null) {
                    this.drawBackground(hdc, rect3);
                }
                else {
                    this.fillBackground(hdc, OS.GetBkColor(hdc), rect3);
                }
                final int saveDC = OS.SaveDC(hdc);
                final GCData gcData = new GCData();
                gcData.device = this.display;
                if (itemSelected && this.explorerTheme) {
                    gcData.foreground = OS.GetSysColor(OS.COLOR_WINDOWTEXT);
                }
                else {
                    gcData.foreground = OS.GetTextColor(hdc);
                }
                gcData.background = OS.GetBkColor(hdc);
                if (!itemSelected) {
                    if (foreground != -1) {
                        gcData.foreground = foreground;
                    }
                    if (background != -1) {
                        gcData.background = background;
                    }
                }
                gcData.uiState = OS.SendMessage(this.handle, 297, 0, 0);
                gcData.font = item.getFont(index);
                final GC win32_new = GC.win32_new(hdc, gcData);
                final Event event = new Event();
                event.index = index;
                event.item = item;
                event.gc = win32_new;
                final Event event2 = event;
                event2.detail |= 0x10;
                if (background != -1) {
                    final Event event3 = event;
                    event3.detail |= 0x8;
                }
                if (b2) {
                    final Event event4 = event;
                    event4.detail |= 0x20;
                }
                if (itemSelected) {
                    final Event event5 = event;
                    event5.detail |= 0x2;
                }
                if (!this.explorerTheme && OS.SendMessage(this.handle, 4362, 9, 0) == nmtvcustomdraw.dwItemSpec && this.handle == OS.GetFocus() && (OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0) {
                    b3 = true;
                    final Event event6 = event;
                    event6.detail |= 0x4;
                }
                event.x = bounds.left;
                event.y = bounds.top;
                event.width = bounds.right - bounds.left;
                event.height = bounds.bottom - bounds.top;
                win32_new.setClipping(event.x, event.y, event.width, event.height);
                this.sendEvent(40, event);
                event.gc = null;
                final int foreground2 = gcData.foreground;
                win32_new.dispose();
                OS.RestoreDC(hdc, saveDC);
                if (this.isDisposed() || item.isDisposed()) {
                    return null;
                }
                if (event.doit) {
                    this.ignoreDrawForeground = ((event.detail & 0x10) == 0x0);
                    this.ignoreDrawBackground = ((event.detail & 0x8) == 0x0);
                    this.ignoreDrawSelection = ((event.detail & 0x2) == 0x0);
                    this.ignoreDrawFocus = ((event.detail & 0x4) == 0x0);
                    this.ignoreDrawHot = ((event.detail & 0x20) == 0x0);
                }
                else {
                    final boolean ignoreDrawForeground = true;
                    this.ignoreDrawHot = ignoreDrawForeground;
                    this.ignoreDrawFocus = ignoreDrawForeground;
                    this.ignoreDrawSelection = ignoreDrawForeground;
                    this.ignoreDrawBackground = ignoreDrawForeground;
                    this.ignoreDrawForeground = ignoreDrawForeground;
                }
                if (itemSelected && this.ignoreDrawSelection) {
                    this.ignoreDrawHot = true;
                }
                if (!this.ignoreDrawBackground && background != -1) {
                    boolean b5 = !itemSelected && !b2;
                    if (!this.explorerTheme && itemSelected) {
                        b5 = !this.ignoreDrawSelection;
                    }
                    if (b5) {
                        if (this.columnCount == 0) {
                            if ((this.style & 0x10000) != 0x0) {
                                this.fillBackground(hdc, background, rect3);
                            }
                            else {
                                final RECT bounds2 = item.getBounds(index, true, false, false, false, true, hdc);
                                if (sendMeasureItemEvent != null) {
                                    bounds2.right = Math.min(bounds.right, sendMeasureItemEvent.x + sendMeasureItemEvent.width);
                                }
                                this.fillBackground(hdc, background, bounds2);
                            }
                        }
                        else {
                            this.fillBackground(hdc, background, bounds);
                        }
                    }
                }
                if (this.ignoreDrawSelection) {
                    this.ignoreFullSelection = true;
                }
                if (!this.ignoreDrawSelection || !this.ignoreDrawHot) {
                    if (!itemSelected && !b2) {
                        this.selectionForeground = OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT);
                    }
                    if (this.explorerTheme) {
                        if ((this.style & 0x10000) == 0x0) {
                            final RECT bounds3 = item.getBounds(index, true, true, false, false, false, hdc);
                            final RECT bounds4 = item.getBounds(index, true, true, true, false, true, hdc);
                            if (sendMeasureItemEvent != null) {
                                bounds3.right = Math.min(bounds4.right, sendMeasureItemEvent.x + sendMeasureItemEvent.width);
                            }
                            else {
                                final RECT rect4 = bounds3;
                                rect4.right += 2;
                                final RECT rect5 = bounds4;
                                rect5.right += 2;
                            }
                            final RECT rect6 = bounds3;
                            rect6.left -= 2;
                            final RECT rect7 = bounds4;
                            rect7.left -= 2;
                            final int openThemeData = OS.OpenThemeData(this.handle, Display.TREEVIEW);
                            int n3 = itemSelected ? 3 : 2;
                            if (OS.GetFocus() != this.handle && itemSelected && !b2) {
                                n3 = 5;
                            }
                            OS.DrawThemeBackground(openThemeData, hdc, 1, n3, bounds3, bounds4);
                            OS.CloseThemeData(openThemeData);
                        }
                    }
                    else if ((this.style & 0x10000) != 0x0) {
                        if ((this.style & 0x10000) != 0x0 && this.columnCount == 0) {
                            this.fillBackground(hdc, OS.GetBkColor(hdc), rect3);
                        }
                        else {
                            this.fillBackground(hdc, OS.GetBkColor(hdc), bounds);
                        }
                    }
                    else {
                        final RECT bounds5 = item.getBounds(index, true, false, false, false, true, hdc);
                        if (sendMeasureItemEvent != null) {
                            bounds5.right = Math.min(bounds.right, sendMeasureItemEvent.x + sendMeasureItemEvent.width);
                        }
                        this.fillBackground(hdc, OS.GetBkColor(hdc), bounds5);
                    }
                }
                else {
                    if (itemSelected || b2) {
                        foreground = (this.selectionForeground = foreground2);
                        final boolean b6 = true;
                        this.ignoreDrawHot = b6;
                        this.ignoreDrawSelection = b6;
                    }
                    if (this.explorerTheme) {
                        nmtvcustomdraw.uItemState |= 0x4;
                        final int clrText = (foreground == -1) ? this.getForegroundPixel() : foreground;
                        if (nmtvcustomdraw.clrText == clrText) {
                            nmtvcustomdraw.clrText |= 0x20000000;
                            if (nmtvcustomdraw.clrText == clrText) {
                                nmtvcustomdraw.clrText &= 0xDFFFFFFF;
                            }
                        }
                        else {
                            nmtvcustomdraw.clrText = clrText;
                        }
                        OS.MoveMemory(n2, nmtvcustomdraw, NMTVCUSTOMDRAW.sizeof);
                    }
                }
                if (b3 && !this.ignoreDrawFocus && (this.style & 0x10000) == 0x0) {
                    final RECT bounds6 = item.getBounds(index, true, this.explorerTheme, false, false, true, hdc);
                    if (sendMeasureItemEvent != null) {
                        bounds6.right = Math.min(bounds.right, sendMeasureItemEvent.x + sendMeasureItemEvent.width);
                    }
                    nmtvcustomdraw.uItemState &= 0xFFFFFFEF;
                    OS.MoveMemory(n2, nmtvcustomdraw, NMTVCUSTOMDRAW.sizeof);
                    this.focusRect = bounds6;
                }
                if (this.explorerTheme) {
                    if (itemSelected || (b2 && this.ignoreDrawHot)) {
                        nmtvcustomdraw.uItemState &= 0xFFFFFFBF;
                    }
                    OS.MoveMemory(n2, nmtvcustomdraw, NMTVCUSTOMDRAW.sizeof);
                }
                final RECT bounds7 = item.getBounds(index, true, true, false, false, false, hdc);
                OS.SaveDC(hdc);
                OS.SelectClipRgn(hdc, 0);
                if (this.explorerTheme) {
                    final RECT rect8 = bounds7;
                    rect8.left -= 2;
                    final RECT rect9 = bounds7;
                    rect9.right += 2;
                }
                final RECT rect10 = bounds7;
                ++rect10.right;
                if (this.linesVisible) {
                    final RECT rect11 = bounds7;
                    ++rect11.bottom;
                }
                if (rect != null) {
                    OS.IntersectClipRect(hdc, rect.left, rect.top, rect.right, rect.bottom);
                }
                OS.ExcludeClipRect(hdc, bounds7.left, bounds7.top, bounds7.right, bounds7.bottom);
                return new LRESULT(16);
            }
            else if ((this.style & 0x10000) != 0x0 && (OS.GetWindowLong(this.handle, -16) & 0x1000) == 0x0) {
                final RECT rect12 = new RECT();
                OS.SetRect(rect12, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                if (itemSelected) {
                    this.fillBackground(hdc, OS.GetBkColor(hdc), rect12);
                }
                else if (OS.IsWindowEnabled(this.handle)) {
                    this.drawBackground(hdc, rect12);
                }
                nmtvcustomdraw.uItemState &= 0xFFFFFFEF;
                OS.MoveMemory(n2, nmtvcustomdraw, NMTVCUSTOMDRAW.sizeof);
            }
        }
        LRESULT lresult;
        if (foreground == -1 && background == -1 && fontHandle == -1) {
            lresult = new LRESULT(16);
        }
        else {
            lresult = new LRESULT(18);
            if (fontHandle != -1) {
                OS.SelectObject(hdc, fontHandle);
            }
            if (OS.IsWindowEnabled(this.handle) && OS.IsWindowVisible(this.handle)) {
                if (background != -1 && (OS.GetWindowLong(this.handle, -16) & 0x1000) == 0x0) {
                    if (this.columnCount != 0 && this.hwndHeader != 0) {
                        final RECT rect13 = new RECT();
                        final HDITEM hditem2 = new HDITEM();
                        hditem2.mask = 1;
                        OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, index, hditem2);
                        OS.SetRect(rect13, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.left + hditem2.cxy, nmtvcustomdraw.bottom);
                        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
                            final RECT rect14 = new RECT();
                            if (OS.TreeView_GetItemRect(this.handle, item.handle, rect14, true)) {
                                rect13.left = Math.min(rect14.left, rect13.right);
                            }
                        }
                        if ((this.style & 0x10000) != 0x0) {
                            if (!itemSelected) {
                                this.fillBackground(hdc, background, rect13);
                            }
                        }
                        else if (this.explorerTheme) {
                            if (!itemSelected && !b2) {
                                this.fillBackground(hdc, background, rect13);
                            }
                        }
                        else {
                            this.fillBackground(hdc, background, rect13);
                        }
                    }
                    else if ((this.style & 0x10000) != 0x0) {
                        final RECT rect15 = new RECT();
                        OS.SetRect(rect15, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                        if (!itemSelected) {
                            this.fillBackground(hdc, background, rect15);
                        }
                    }
                }
                if (!itemSelected) {
                    nmtvcustomdraw.clrText = ((foreground == -1) ? this.getForegroundPixel() : foreground);
                    nmtvcustomdraw.clrTextBk = ((background == -1) ? this.getBackgroundPixel() : background);
                    OS.MoveMemory(n2, nmtvcustomdraw, NMTVCUSTOMDRAW.sizeof);
                }
            }
        }
        if (OS.IsWindowEnabled(this.handle)) {
            if (this.explorerTheme && this.findImageControl() != null && !itemSelected && (nmtvcustomdraw.uItemState & 0x41) == 0x0) {
                nmtvcustomdraw.uItemState |= 0x4;
                final int clrText2 = (foreground == -1) ? this.getForegroundPixel() : foreground;
                if (nmtvcustomdraw.clrText == clrText2) {
                    nmtvcustomdraw.clrText |= 0x20000000;
                    if (nmtvcustomdraw.clrText == clrText2) {
                        nmtvcustomdraw.clrText &= 0xDFFFFFFF;
                    }
                }
                else {
                    nmtvcustomdraw.clrText = clrText2;
                }
                OS.MoveMemory(n2, nmtvcustomdraw, NMTVCUSTOMDRAW.sizeof);
                if (background != -1) {
                    if ((this.style & 0x10000) != 0x0) {
                        final RECT rect16 = new RECT();
                        if (this.columnCount != 0) {
                            final HDITEM hditem3 = new HDITEM();
                            hditem3.mask = 1;
                            OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, index, hditem3);
                            OS.SetRect(rect16, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.left + hditem3.cxy, nmtvcustomdraw.bottom);
                        }
                        else {
                            OS.SetRect(rect16, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                        }
                        this.fillBackground(hdc, background, rect16);
                    }
                    else {
                        this.fillBackground(hdc, background, item.getBounds(index, true, false, true, false, true, hdc));
                    }
                }
            }
        }
        else if (sortColumnPixel != -1) {
            final RECT rect17 = new RECT();
            final HDITEM hditem4 = new HDITEM();
            hditem4.mask = 1;
            OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, index, hditem4);
            OS.SetRect(rect17, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.left + hditem4.cxy, nmtvcustomdraw.bottom);
            this.fillBackground(hdc, sortColumnPixel, rect17);
        }
        OS.SaveDC(hdc);
        if (rect != null) {
            final int createRectRgn = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
            final POINT point = new POINT();
            OS.GetWindowOrgEx(hdc, point);
            OS.OffsetRgn(createRectRgn, -point.x, -point.y);
            OS.SelectClipRgn(hdc, createRectRgn);
            OS.DeleteObject(createRectRgn);
        }
        return lresult;
    }
    
    LRESULT CDDS_POSTPAINT(final NMTVCUSTOMDRAW nmtvcustomdraw, final int n, final int n2) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        if (OS.IsWindowVisible(this.handle)) {
            if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0 && this.findImageControl() == null) {
                final int index = this.indexOf(this.sortColumn);
                if (index != -1) {
                    int n3 = nmtvcustomdraw.top;
                    int n4;
                    if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                        n4 = this.getBottomItem();
                    }
                    else {
                        n4 = OS.SendMessage(this.handle, 4362, 10, 0);
                    }
                    if (n4 != 0) {
                        final RECT rect = new RECT();
                        if (OS.TreeView_GetItemRect(this.handle, n4, rect, false)) {
                            n3 = rect.bottom;
                        }
                    }
                    final RECT rect2 = new RECT();
                    OS.SetRect(rect2, nmtvcustomdraw.left, n3, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
                    final RECT rect3 = new RECT();
                    OS.SendMessage(this.hwndHeader, 4615, index, rect3);
                    rect2.left = rect3.left;
                    rect2.right = rect3.right;
                    this.fillBackground(nmtvcustomdraw.hdc, this.getSortColumnPixel(), rect2);
                }
            }
            if (this.linesVisible) {
                final int hdc = nmtvcustomdraw.hdc;
                if (this.hwndHeader != 0) {
                    int n5 = 0;
                    final RECT rect4 = new RECT();
                    final HDITEM hditem = new HDITEM();
                    hditem.mask = 1;
                    for (int i = 0; i < this.columnCount; ++i) {
                        OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, OS.SendMessage(this.hwndHeader, 4623, i, 0), hditem);
                        OS.SetRect(rect4, n5, nmtvcustomdraw.top, n5 + hditem.cxy, nmtvcustomdraw.bottom);
                        OS.DrawEdge(hdc, rect4, 8, 4);
                        n5 += hditem.cxy;
                    }
                }
                int sendMessage = 0;
                final RECT rect5 = new RECT();
                int n6;
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    n6 = this.getBottomItem();
                }
                else {
                    n6 = OS.SendMessage(this.handle, 4362, 10, 0);
                }
                if (n6 != 0 && OS.TreeView_GetItemRect(this.handle, n6, rect5, false)) {
                    sendMessage = rect5.bottom - rect5.top;
                }
                if (sendMessage == 0) {
                    sendMessage = OS.SendMessage(this.handle, 4380, 0, 0);
                    OS.GetClientRect(this.handle, rect5);
                    OS.SetRect(rect5, rect5.left, rect5.top, rect5.right, rect5.top + sendMessage);
                    OS.DrawEdge(hdc, rect5, 8, 8);
                }
                if (sendMessage != 0) {
                    while (rect5.bottom < nmtvcustomdraw.bottom) {
                        final int n7 = rect5.top + sendMessage;
                        OS.SetRect(rect5, rect5.left, n7, rect5.right, n7 + sendMessage);
                        OS.DrawEdge(hdc, rect5, 8, 8);
                    }
                }
            }
        }
        return new LRESULT(0);
    }
    
    LRESULT CDDS_PREPAINT(final NMTVCUSTOMDRAW nmtvcustomdraw, final int n, final int n2) {
        if (this.explorerTheme && ((OS.IsWindowEnabled(this.handle) && this.hooks(40)) || this.findImageControl() != null)) {
            final RECT rect = new RECT();
            OS.SetRect(rect, nmtvcustomdraw.left, nmtvcustomdraw.top, nmtvcustomdraw.right, nmtvcustomdraw.bottom);
            this.drawBackground(nmtvcustomdraw.hdc, rect);
        }
        return new LRESULT(48);
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (this.hwndParent != 0 && n == this.hwndParent) {
            return OS.DefWindowProc(n, n2, n3, n4);
        }
        if (this.hwndHeader != 0 && n == this.hwndHeader) {
            return OS.CallWindowProc(Tree.HeaderProc, n, n2, n3, n4);
        }
        switch (n2) {
            case 7: {
                if ((this.style & 0x4) != 0x0) {
                    break;
                }
                if (OS.SendMessage(this.handle, 4362, 9, 0) != 0) {
                    break;
                }
                final int sendMessage = OS.SendMessage(this.handle, 4362, 5, 0);
                if (sendMessage == 0) {
                    break;
                }
                final TVITEM tvitem = new TVITEM();
                tvitem.mask = 24;
                tvitem.hItem = sendMessage;
                OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                this.hSelect = sendMessage;
                final boolean ignoreDeselect = true;
                this.lockSelection = ignoreDeselect;
                this.ignoreSelect = ignoreDeselect;
                this.ignoreDeselect = ignoreDeselect;
                OS.SendMessage(this.handle, 4363, 9, sendMessage);
                final boolean ignoreDeselect2 = false;
                this.lockSelection = ignoreDeselect2;
                this.ignoreSelect = ignoreDeselect2;
                this.ignoreDeselect = ignoreDeselect2;
                this.hSelect = 0;
                if ((tvitem.state & 0x2) == 0x0) {
                    OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                    break;
                }
                break;
            }
        }
        int sendMessage2 = 0;
        boolean b = false;
        switch (n2) {
            case 256: {
                if (n3 == 17) {
                    break;
                }
                if (n3 == 16) {
                    break;
                }
            }
            case 5:
            case 257:
            case 258:
            case 260:
            case 261:
            case 262:
            case 276:
            case 277:
            case 646: {
                b = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (b) {
                    OS.DefWindowProc(this.handle, 11, 0, 0);
                }
            }
            case 48:
            case 275:
            case 512:
            case 513:
            case 514:
            case 515:
            case 516:
            case 517:
            case 518:
            case 519:
            case 520:
            case 521:
            case 522:
            case 523:
            case 524:
            case 525:
            case 673:
            case 675: {
                if (this.findImageControl() != null) {
                    sendMessage2 = OS.SendMessage(this.handle, 4362, 5, 0);
                    break;
                }
                break;
            }
        }
        final int callWindowProc = OS.CallWindowProc(Tree.TreeProc, n, n2, n3, n4);
        Label_0887: {
            switch (n2) {
                case 256: {
                    if (n3 == 17) {
                        break;
                    }
                    if (n3 == 16) {
                        break;
                    }
                }
                case 5:
                case 257:
                case 258:
                case 260:
                case 261:
                case 262:
                case 276:
                case 277:
                case 646: {
                    if (!b) {
                        break Label_0887;
                    }
                    OS.DefWindowProc(this.handle, 11, 1, 0);
                    OS.InvalidateRect(this.handle, null, true);
                    if (this.hwndHeader != 0) {
                        OS.InvalidateRect(this.hwndHeader, null, true);
                    }
                    break Label_0887;
                }
                case 48:
                case 275:
                case 512:
                case 513:
                case 514:
                case 515:
                case 516:
                case 517:
                case 518:
                case 519:
                case 520:
                case 521:
                case 522:
                case 523:
                case 524:
                case 525:
                case 673:
                case 675: {
                    if (this.findImageControl() != null && sendMessage2 != OS.SendMessage(this.handle, 4362, 5, 0)) {
                        OS.InvalidateRect(this.handle, null, true);
                    }
                    this.updateScrollBar();
                    break;
                }
                case 15: {
                    this.painted = true;
                    break;
                }
            }
        }
        return callWindowProc;
    }
    
    void checkBuffered() {
        super.checkBuffered();
        if ((this.style & 0x10000000) != 0x0) {
            this.style |= 0x20000000;
            OS.SendMessage(this.handle, 4385, 0, 0);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.IsAppThemed() && (OS.SendMessage(this.handle, 4397, 0, 0) & 0x4) != 0x0) {
            this.style |= 0x20000000;
        }
    }
    
    boolean checkData(final TreeItem treeItem, final boolean b) {
        if ((this.style & 0x10000000) == 0x0) {
            return true;
        }
        if (!treeItem.cached) {
            final TreeItem parentItem = treeItem.getParentItem();
            return this.checkData(treeItem, (parentItem == null) ? this.indexOf(treeItem) : parentItem.indexOf(treeItem), b);
        }
        return true;
    }
    
    boolean checkData(final TreeItem treeItem, final int index, final boolean b) {
        if ((this.style & 0x10000000) == 0x0) {
            return true;
        }
        if (!treeItem.cached) {
            treeItem.cached = true;
            final Event event = new Event();
            event.item = treeItem;
            event.index = index;
            final TreeItem currentItem = this.currentItem;
            this.currentItem = treeItem;
            final int sendMessage = OS.SendMessage(this.handle, 4362, 5, 0);
            this.sendEvent(36, event);
            this.currentItem = currentItem;
            if (this.isDisposed() || treeItem.isDisposed()) {
                return false;
            }
            if (b) {
                treeItem.redraw();
            }
            if (sendMessage != OS.SendMessage(this.handle, 4362, 5, 0)) {
                OS.InvalidateRect(this.handle, null, true);
            }
        }
        return true;
    }
    
    boolean checkHandle(final int n) {
        return n == this.handle || (this.hwndParent != 0 && n == this.hwndParent) || (this.hwndHeader != 0 && n == this.hwndHeader);
    }
    
    boolean checkScroll(final int n) {
        if (this.getDrawing()) {
            return false;
        }
        int sendMessage;
        int n2;
        for (sendMessage = OS.SendMessage(this.handle, 4362, 0, 0), n2 = OS.SendMessage(this.handle, 4362, 3, n); n2 != sendMessage && n2 != 0; n2 = OS.SendMessage(this.handle, 4362, 3, n2)) {}
        return n2 == 0;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public void clear(final int n, final boolean b) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4362, 0, 0);
        if (sendMessage == 0) {
            this.error(6);
        }
        final int item = this.findItem(sendMessage, n);
        if (item == 0) {
            this.error(6);
        }
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        this.clear(item, tvitem);
        if (b) {
            this.clearAll(OS.SendMessage(this.handle, 4362, 4, item), tvitem, b);
        }
    }
    
    void clear(final int hItem, final TVITEM tvitem) {
        tvitem.hItem = hItem;
        TreeItem treeItem = null;
        if (OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem) != 0) {
            treeItem = ((tvitem.lParam != -1) ? this.items[tvitem.lParam] : null);
        }
        if (treeItem != null) {
            if ((this.style & 0x10000000) != 0x0 && !treeItem.cached) {
                return;
            }
            treeItem.clear();
            treeItem.redraw();
        }
    }
    
    public void clearAll(final boolean b) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4362, 0, 0);
        if (sendMessage == 0) {
            return;
        }
        if (b) {
            boolean b2 = false;
            for (int i = 0; i < this.items.length; ++i) {
                final TreeItem treeItem = this.items[i];
                if (treeItem != null && treeItem != this.currentItem) {
                    treeItem.clear();
                    b2 = true;
                }
            }
            if (b2) {
                OS.InvalidateRect(this.handle, null, true);
            }
        }
        else {
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 20;
            this.clearAll(sendMessage, tvitem, b);
        }
    }
    
    void clearAll(int i, final TVITEM tvitem, final boolean b) {
        while (i != 0) {
            this.clear(i, tvitem);
            if (b) {
                this.clearAll(OS.SendMessage(this.handle, 4362, 4, i), tvitem, b);
            }
            i = OS.SendMessage(this.handle, 4362, 1, i);
        }
    }
    
    int CompareFunc(final int n, final int n2, final int n3) {
        final TreeItem treeItem = this.items[n];
        final TreeItem treeItem2 = this.items[n2];
        final String text = treeItem.getText(n3);
        final String text2 = treeItem2.getText(n3);
        return (this.sortDirection == 128) ? text.compareTo(text2) : text2.compareTo(text);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int max = 0;
        int n3 = 0;
        if (this.hwndHeader != 0) {
            final HDITEM hditem = new HDITEM();
            hditem.mask = 1;
            for (int i = 0; i < this.columnCount; ++i) {
                OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, i, hditem);
                max += hditem.cxy;
            }
            final RECT rect = new RECT();
            OS.GetWindowRect(this.hwndHeader, rect);
            n3 += rect.bottom - rect.top;
        }
        final RECT rect2 = new RECT();
        for (int j = OS.SendMessage(this.handle, 4362, 0, 0); j != 0; j = OS.SendMessage(this.handle, 4362, 6, j)) {
            if ((this.style & 0x10000000) == 0x0 && !this.painted) {
                final TVITEM tvitem = new TVITEM();
                tvitem.mask = 17;
                tvitem.hItem = j;
                tvitem.pszText = -1;
                this.ignoreCustomDraw = true;
                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                this.ignoreCustomDraw = false;
            }
            if (OS.TreeView_GetItemRect(this.handle, j, rect2, true)) {
                max = Math.max(max, rect2.right);
                n3 += rect2.bottom - rect2.top;
            }
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
        final int borderWidth = this.getBorderWidth();
        int n4 = max + borderWidth * 2;
        int n5 = n3 + borderWidth * 2;
        if ((this.style & 0x200) != 0x0) {
            n4 += OS.GetSystemMetrics(2);
        }
        if ((this.style & 0x100) != 0x0) {
            n5 += OS.GetSystemMetrics(3);
        }
        return new Point(n4, n5);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.IsAppThemed()) {
            this.explorerTheme = true;
            OS.SetWindowTheme(this.handle, Display.EXPLORER, null);
            OS.SendMessage(this.handle, 4396, 0, 84);
            this.setForegroundPixel(-1);
        }
        if (!OS.IsWinCE && OS.COMCTL32_MAJOR < 6) {
            OS.SendMessage(this.handle, 8199, 5, 0);
        }
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
        OS.SendMessage(this.handle, 48, OS.GetStockObject(13), 0);
        this.createdAsRTL = ((this.style & 0x4000000) != 0x0);
    }
    
    void createHeaderToolTips() {
        if (OS.IsWinCE) {
            return;
        }
        if (this.headerToolTipHandle != 0) {
            return;
        }
        int n = 0;
        if (OS.WIN32_VERSION >= OS.VERSION(4, 10) && (this.style & 0x4000000) != 0x0) {
            n |= 0x400000;
        }
        this.headerToolTipHandle = OS.CreateWindowEx(n, new TCHAR(0, "tooltips_class32", true), null, 2, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0, OS.GetModuleHandle(null), null);
        if (this.headerToolTipHandle == 0) {
            this.error(2);
        }
        OS.SendMessage(this.headerToolTipHandle, 1048, 0, 32767);
    }
    
    void createItem(final TreeColumn treeColumn, final int n) {
        if (this.hwndHeader == 0) {
            this.createParent();
        }
        if (n < 0 || n > this.columnCount) {
            this.error(6);
        }
        if (this.columnCount == this.columns.length) {
            final TreeColumn[] columns = new TreeColumn[this.columns.length + 4];
            System.arraycopy(this.columns, 0, columns, 0, this.columns.length);
            this.columns = columns;
        }
        for (int i = 0; i < this.items.length; ++i) {
            final TreeItem treeItem = this.items[i];
            if (treeItem != null) {
                final String[] strings = treeItem.strings;
                if (strings != null) {
                    final String[] strings2 = new String[this.columnCount + 1];
                    System.arraycopy(strings, 0, strings2, 0, n);
                    System.arraycopy(strings, n, strings2, n + 1, this.columnCount - n);
                    treeItem.strings = strings2;
                }
                final Image[] images = treeItem.images;
                if (images != null) {
                    final Image[] images2 = new Image[this.columnCount + 1];
                    System.arraycopy(images, 0, images2, 0, n);
                    System.arraycopy(images, n, images2, n + 1, this.columnCount - n);
                    treeItem.images = images2;
                }
                if (n == 0 && this.columnCount != 0) {
                    if (strings == null) {
                        (treeItem.strings = new String[this.columnCount + 1])[1] = treeItem.text;
                    }
                    treeItem.text = "";
                    if (images == null) {
                        (treeItem.images = new Image[this.columnCount + 1])[1] = treeItem.image;
                    }
                    treeItem.image = null;
                }
                if (treeItem.cellBackground != null) {
                    final int[] cellBackground = treeItem.cellBackground;
                    final int[] cellBackground2 = new int[this.columnCount + 1];
                    System.arraycopy(cellBackground, 0, cellBackground2, 0, n);
                    System.arraycopy(cellBackground, n, cellBackground2, n + 1, this.columnCount - n);
                    cellBackground2[n] = -1;
                    treeItem.cellBackground = cellBackground2;
                }
                if (treeItem.cellForeground != null) {
                    final int[] cellForeground = treeItem.cellForeground;
                    final int[] cellForeground2 = new int[this.columnCount + 1];
                    System.arraycopy(cellForeground, 0, cellForeground2, 0, n);
                    System.arraycopy(cellForeground, n, cellForeground2, n + 1, this.columnCount - n);
                    cellForeground2[n] = -1;
                    treeItem.cellForeground = cellForeground2;
                }
                if (treeItem.cellFont != null) {
                    final Font[] cellFont = treeItem.cellFont;
                    final Font[] cellFont2 = new Font[this.columnCount + 1];
                    System.arraycopy(cellFont, 0, cellFont2, 0, n);
                    System.arraycopy(cellFont, n, cellFont2, n + 1, this.columnCount - n);
                    treeItem.cellFont = cellFont2;
                }
            }
        }
        System.arraycopy(this.columns, n, this.columns, n + 1, this.columnCount++ - n);
        this.columns[n] = treeColumn;
        final int getProcessHeap = OS.GetProcessHeap();
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, TCHAR.sizeof);
        final HDITEM hditem = new HDITEM();
        hditem.mask = 6;
        hditem.pszText = heapAlloc;
        if ((treeColumn.style & 0x4000) == 0x4000) {
            hditem.fmt = 0;
        }
        if ((treeColumn.style & 0x1000000) == 0x1000000) {
            hditem.fmt = 2;
        }
        if ((treeColumn.style & 0x20000) == 0x20000) {
            hditem.fmt = 1;
        }
        OS.SendMessage(this.hwndHeader, OS.HDM_INSERTITEM, n, hditem);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        if (this.columnCount == 1) {
            this.scrollWidth = 0;
            if ((this.style & 0x100) != 0x0) {
                OS.SetWindowLong(this.handle, -16, OS.GetWindowLong(this.handle, -16) | 0x8000);
            }
            if (OS.SendMessage(this.handle, 4357, 0, 0) != 0 && !OS.IsWinCE) {
                OS.ShowScrollBar(this.handle, 0, false);
            }
            this.createItemToolTips();
            if (this.itemToolTipHandle != 0) {
                OS.SendMessage(this.itemToolTipHandle, 1027, 0, -1);
            }
        }
        this.setScrollWidth();
        this.updateImageList();
        this.updateScrollBar();
        if (this.columnCount == 1 && OS.SendMessage(this.handle, 4357, 0, 0) != 0) {
            OS.InvalidateRect(this.handle, null, true);
        }
        if (this.headerToolTipHandle != 0) {
            final RECT rect = new RECT();
            if (OS.SendMessage(this.hwndHeader, 4615, n, rect) != 0) {
                final TOOLINFO toolinfo = new TOOLINFO();
                toolinfo.cbSize = TOOLINFO.sizeof;
                toolinfo.uFlags = 16;
                toolinfo.hwnd = this.hwndHeader;
                final TOOLINFO toolinfo2 = toolinfo;
                final int n2 = this.display.nextToolTipId++;
                treeColumn.id = n2;
                toolinfo2.uId = n2;
                toolinfo.left = rect.left;
                toolinfo.top = rect.top;
                toolinfo.right = rect.right;
                toolinfo.bottom = rect.bottom;
                toolinfo.lpszText = -1;
                OS.SendMessage(this.headerToolTipHandle, OS.TTM_ADDTOOL, 0, toolinfo);
            }
        }
    }
    
    void createItem(final TreeItem treeItem, final int hParent, final int hInsertAfter, final int hItem) {
        int n = -1;
        if (treeItem != null) {
            for (n = ((this.lastID < this.items.length) ? this.lastID : 0); n < this.items.length && this.items[n] != null; ++n) {}
            if (n == this.items.length) {
                int max;
                if (this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                    max = this.items.length + 4;
                }
                else {
                    this.shrink = true;
                    max = Math.max(4, this.items.length * 3 / 2);
                }
                final TreeItem[] items = new TreeItem[max];
                System.arraycopy(this.items, 0, items, 0, this.items.length);
                this.items = items;
            }
            this.lastID = n + 1;
        }
        int sendMessage = OS.SendMessage(this.handle, 4362, 4, hParent);
        final boolean b = sendMessage == 0;
        int sendMessage2;
        if (hItem == 0) {
            final TVINSERTSTRUCT tvinsertstruct = new TVINSERTSTRUCT();
            tvinsertstruct.hParent = hParent;
            tvinsertstruct.hInsertAfter = hInsertAfter;
            tvinsertstruct.lParam = n;
            tvinsertstruct.pszText = -1;
            final TVINSERTSTRUCT tvinsertstruct2 = tvinsertstruct;
            final TVINSERTSTRUCT tvinsertstruct3 = tvinsertstruct;
            final int n2 = -1;
            tvinsertstruct3.iSelectedImage = n2;
            tvinsertstruct2.iImage = n2;
            tvinsertstruct.mask = 55;
            if ((this.style & 0x20) != 0x0) {
                tvinsertstruct.mask |= 0x8;
                tvinsertstruct.state = 4096;
                tvinsertstruct.stateMask = 61440;
            }
            this.ignoreCustomDraw = true;
            sendMessage2 = OS.SendMessage(this.handle, OS.TVM_INSERTITEM, 0, tvinsertstruct);
            this.ignoreCustomDraw = false;
            if (sendMessage2 == 0) {
                this.error(14);
            }
        }
        else {
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 20;
            final TVITEM tvitem2 = tvitem;
            sendMessage2 = hItem;
            tvitem2.hItem = hItem;
            tvitem.lParam = n;
            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
        }
        if (treeItem != null) {
            treeItem.handle = sendMessage2;
            this.items[n] = treeItem;
        }
        if (sendMessage == 0 && (hInsertAfter == -65535 || hInsertAfter == -65534)) {
            final int n3 = sendMessage = sendMessage2;
            this.hLastIndexOf = n3;
            this.hFirstIndexOf = n3;
            final boolean b2 = false;
            this.lastIndexOf = (b2 ? 1 : 0);
            this.itemCount = (b2 ? 1 : 0);
        }
        if (sendMessage == this.hFirstIndexOf && this.itemCount != -1) {
            ++this.itemCount;
        }
        if (hItem == 0) {
            if (b && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                final RECT rect = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, hParent, rect, false)) {
                    OS.InvalidateRect(this.handle, rect, true);
                }
            }
            if ((this.style & 0x10000000) != 0x0 && this.currentItem != null) {
                final RECT rect2 = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, sendMessage2, rect2, false)) {
                    final RECT rect3 = new RECT();
                    if (OS.GetUpdateRect(this.handle, rect3, true) && rect3.top < rect2.bottom) {
                        if (OS.IsWinCE) {
                            OS.OffsetRect(rect3, 0, rect2.bottom - rect2.top);
                            OS.InvalidateRect(this.handle, rect3, true);
                        }
                        else {
                            final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                            if (OS.GetUpdateRgn(this.handle, createRectRgn, true) != 1) {
                                OS.OffsetRgn(createRectRgn, 0, rect2.bottom - rect2.top);
                                OS.InvalidateRgn(this.handle, createRectRgn, true);
                            }
                            OS.DeleteObject(createRectRgn);
                        }
                    }
                }
            }
            this.updateScrollBar();
        }
    }
    
    void createItemToolTips() {
        if (OS.IsWinCE) {
            return;
        }
        if (this.itemToolTipHandle != 0) {
            return;
        }
        OS.SetWindowLong(this.handle, -16, OS.GetWindowLong(this.handle, -16) | 0x80);
        int n = 0;
        if (OS.WIN32_VERSION >= OS.VERSION(4, 10) && (this.style & 0x4000000) != 0x0) {
            n |= 0x400000;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            n |= 0x20;
        }
        this.itemToolTipHandle = OS.CreateWindowEx(n, new TCHAR(0, "tooltips_class32", true), null, 50, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0, OS.GetModuleHandle(null), null);
        if (this.itemToolTipHandle == 0) {
            this.error(2);
        }
        OS.SendMessage(this.itemToolTipHandle, 1027, 3, 0);
        final TOOLINFO toolinfo = new TOOLINFO();
        toolinfo.cbSize = TOOLINFO.sizeof;
        toolinfo.hwnd = this.handle;
        toolinfo.uId = this.handle;
        toolinfo.uFlags = 272;
        toolinfo.lpszText = -1;
        OS.SendMessage(this.itemToolTipHandle, OS.TTM_ADDTOOL, 0, toolinfo);
    }
    
    void createParent() {
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        OS.MapWindowPoints(0, this.parent.handle, rect, 2);
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        int n = super.widgetStyle() & 0xEFFFFFFF;
        if ((getWindowLong & 0x4000000) != 0x0) {
            n |= 0x4000000;
        }
        this.hwndParent = OS.CreateWindowEx(super.widgetExtStyle(), super.windowClass(), null, n, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, this.parent.handle, 0, OS.GetModuleHandle(null), null);
        if (this.hwndParent == 0) {
            this.error(2);
        }
        OS.SetWindowLongPtr(this.hwndParent, -12, this.hwndParent);
        int n2 = 0;
        if (OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
            n2 |= 0x100000;
            if ((this.style & 0x4000000) != 0x0) {
                n2 |= 0x400000;
            }
        }
        this.hwndHeader = OS.CreateWindowEx(n2, Tree.HeaderClass, null, 1140850890, 0, 0, 0, 0, this.hwndParent, 0, OS.GetModuleHandle(null), null);
        if (this.hwndHeader == 0) {
            this.error(2);
        }
        OS.SetWindowLongPtr(this.hwndHeader, -12, this.hwndHeader);
        if (OS.IsDBLocale) {
            final int immGetContext = OS.ImmGetContext(this.handle);
            OS.ImmAssociateContext(this.hwndParent, immGetContext);
            OS.ImmAssociateContext(this.hwndHeader, immGetContext);
            OS.ImmReleaseContext(this.handle, immGetContext);
        }
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        if (sendMessage != 0) {
            OS.SendMessage(this.hwndHeader, 48, sendMessage, 0);
        }
        this.SetWindowPos(this.hwndParent, OS.GetWindow(this.handle, 3), 0, 0, 0, 0, 19);
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 3;
        OS.GetScrollInfo(this.hwndParent, 0, scrollinfo);
        scrollinfo.nPage = scrollinfo.nMax + 1;
        OS.SetScrollInfo(this.hwndParent, 0, scrollinfo, true);
        OS.GetScrollInfo(this.hwndParent, 1, scrollinfo);
        scrollinfo.nPage = scrollinfo.nMax + 1;
        OS.SetScrollInfo(this.hwndParent, 1, scrollinfo, true);
        this.customDraw = true;
        this.deregister();
        if ((getWindowLong & 0x10000000) != 0x0) {
            OS.ShowWindow(this.hwndParent, 5);
        }
        final int getFocus = OS.GetFocus();
        if (getFocus == this.handle) {
            OS.SetFocus(this.hwndParent);
        }
        OS.SetParent(this.handle, this.hwndParent);
        if (getFocus == this.handle) {
            OS.SetFocus(this.handle);
        }
        this.register();
        this.subclass();
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new TreeItem[4];
        this.columns = new TreeColumn[4];
        this.itemCount = -1;
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_WINDOW);
    }
    
    void deregister() {
        super.deregister();
        if (this.hwndParent != 0) {
            this.display.removeControl(this.hwndParent);
        }
        if (this.hwndHeader != 0) {
            this.display.removeControl(this.hwndHeader);
        }
    }
    
    void deselect(int i, final TVITEM tvitem, final int n) {
        while (i != 0) {
            if (i != n) {
                tvitem.hItem = i;
                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
            }
            this.deselect(OS.SendMessage(this.handle, 4362, 4, i), tvitem, n);
            i = OS.SendMessage(this.handle, 4362, 1, i);
        }
    }
    
    public void deselect(final TreeItem treeItem) {
        this.checkWidget();
        if (treeItem == null) {
            this.error(4);
        }
        if (treeItem.isDisposed()) {
            this.error(5);
        }
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 24;
        tvitem.stateMask = 2;
        tvitem.hItem = treeItem.handle;
        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
    }
    
    public void deselectAll() {
        this.checkWidget();
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 24;
        tvitem.stateMask = 2;
        if ((this.style & 0x4) != 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
            if (sendMessage != 0) {
                tvitem.hItem = sendMessage;
                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
            }
        }
        else {
            final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            if ((this.style & 0x10000000) != 0x0) {
                this.deselect(OS.SendMessage(this.handle, 4362, 0, 0), tvitem, 0);
            }
            else {
                for (int i = 0; i < this.items.length; ++i) {
                    final TreeItem treeItem = this.items[i];
                    if (treeItem != null) {
                        tvitem.hItem = treeItem.handle;
                        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
        }
    }
    
    void destroyItem(final TreeColumn treeColumn) {
        if (this.hwndHeader == 0) {
            this.error(15);
        }
        int n;
        for (n = 0; n < this.columnCount && this.columns[n] != treeColumn; ++n) {}
        final int[] array = new int[this.columnCount];
        OS.SendMessage(this.hwndHeader, 4625, this.columnCount, array);
        int n2;
        for (n2 = 0; n2 < this.columnCount && array[n2] != n; ++n2) {}
        final RECT rect = new RECT();
        OS.SendMessage(this.hwndHeader, 4615, n, rect);
        if (OS.SendMessage(this.hwndHeader, 4610, n, 0) == 0) {
            this.error(15);
        }
        System.arraycopy(this.columns, n + 1, this.columns, n, --this.columnCount - n);
        this.columns[this.columnCount] = null;
        for (int i = 0; i < this.items.length; ++i) {
            final TreeItem treeItem = this.items[i];
            if (treeItem != null) {
                if (this.columnCount == 0) {
                    treeItem.strings = null;
                    treeItem.images = null;
                    treeItem.cellBackground = null;
                    treeItem.cellForeground = null;
                    treeItem.cellFont = null;
                }
                else {
                    if (treeItem.strings != null) {
                        final String[] strings = treeItem.strings;
                        if (n == 0) {
                            treeItem.text = ((strings[1] != null) ? strings[1] : "");
                        }
                        final String[] strings2 = new String[this.columnCount];
                        System.arraycopy(strings, 0, strings2, 0, n);
                        System.arraycopy(strings, n + 1, strings2, n, this.columnCount - n);
                        treeItem.strings = strings2;
                    }
                    else if (n == 0) {
                        treeItem.text = "";
                    }
                    if (treeItem.images != null) {
                        final Image[] images = treeItem.images;
                        if (n == 0) {
                            treeItem.image = images[1];
                        }
                        final Image[] images2 = new Image[this.columnCount];
                        System.arraycopy(images, 0, images2, 0, n);
                        System.arraycopy(images, n + 1, images2, n, this.columnCount - n);
                        treeItem.images = images2;
                    }
                    else if (n == 0) {
                        treeItem.image = null;
                    }
                    if (treeItem.cellBackground != null) {
                        final int[] cellBackground = treeItem.cellBackground;
                        final int[] cellBackground2 = new int[this.columnCount];
                        System.arraycopy(cellBackground, 0, cellBackground2, 0, n);
                        System.arraycopy(cellBackground, n + 1, cellBackground2, n, this.columnCount - n);
                        treeItem.cellBackground = cellBackground2;
                    }
                    if (treeItem.cellForeground != null) {
                        final int[] cellForeground = treeItem.cellForeground;
                        final int[] cellForeground2 = new int[this.columnCount];
                        System.arraycopy(cellForeground, 0, cellForeground2, 0, n);
                        System.arraycopy(cellForeground, n + 1, cellForeground2, n, this.columnCount - n);
                        treeItem.cellForeground = cellForeground2;
                    }
                    if (treeItem.cellFont != null) {
                        final Font[] cellFont = treeItem.cellFont;
                        final Font[] cellFont2 = new Font[this.columnCount];
                        System.arraycopy(cellFont, 0, cellFont2, 0, n);
                        System.arraycopy(cellFont, n + 1, cellFont2, n, this.columnCount - n);
                        treeItem.cellFont = cellFont2;
                    }
                }
            }
        }
        if (this.columnCount == 0) {
            this.scrollWidth = 0;
            if (!this.hooks(41)) {
                int getWindowLong = OS.GetWindowLong(this.handle, -16);
                if ((this.style & 0x100) != 0x0) {
                    getWindowLong &= 0xFFFF7FFF;
                }
                OS.SetWindowLong(this.handle, -16, getWindowLong);
                OS.InvalidateRect(this.handle, null, true);
            }
            if (this.itemToolTipHandle != 0) {
                OS.SendMessage(this.itemToolTipHandle, 1027, 3, 0);
            }
        }
        else {
            if (n == 0) {
                final TreeColumn treeColumn2 = this.columns[0];
                treeColumn2.style &= 0xFEFDBFFF;
                final TreeColumn treeColumn3 = this.columns[0];
                treeColumn3.style |= 0x4000;
                final HDITEM hditem = new HDITEM();
                hditem.mask = 36;
                OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, n, hditem);
                final HDITEM hditem2 = hditem;
                hditem2.fmt &= 0xFFFFFFFC;
                final HDITEM hditem3 = hditem;
                hditem3.fmt |= 0x0;
                OS.SendMessage(this.hwndHeader, OS.HDM_SETITEM, n, hditem);
            }
            final RECT rect2 = new RECT();
            OS.GetClientRect(this.handle, rect2);
            rect2.left = rect.left;
            OS.InvalidateRect(this.handle, rect2, true);
        }
        this.setScrollWidth();
        this.updateImageList();
        this.updateScrollBar();
        if (this.columnCount != 0) {
            final int[] array2 = new int[this.columnCount];
            OS.SendMessage(this.hwndHeader, 4625, this.columnCount, array2);
            final TreeColumn[] array3 = new TreeColumn[this.columnCount - n2];
            for (int j = n2; j < array2.length; ++j) {
                (array3[j - n2] = this.columns[array2[j]]).updateToolTip(array2[j]);
            }
            for (int k = 0; k < array3.length; ++k) {
                if (!array3[k].isDisposed()) {
                    array3[k].sendEvent(10);
                }
            }
        }
        if (this.headerToolTipHandle != 0) {
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            toolinfo.uId = treeColumn.id;
            toolinfo.hwnd = this.hwndHeader;
            OS.SendMessage(this.headerToolTipHandle, OS.TTM_DELTOOL, 0, toolinfo);
        }
    }
    
    void destroyItem(final TreeItem treeItem, final int n) {
        final boolean b = false;
        this.hLastIndexOf = (b ? 1 : 0);
        this.hFirstIndexOf = (b ? 1 : 0);
        this.itemCount = -1;
        int sendMessage = 0;
        boolean b2 = false;
        if ((this.style & 0x20000000) == 0x0 && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
            b2 = !OS.TreeView_GetItemRect(this.handle, n, new RECT(), false);
        }
        if (b2) {
            sendMessage = OS.SendMessage(this.handle, 4362, 3, n);
            OS.UpdateWindow(this.handle);
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        if ((this.style & 0x2) != 0x0) {
            final boolean ignoreDeselect = true;
            this.lockSelection = ignoreDeselect;
            this.ignoreSelect = ignoreDeselect;
            this.ignoreDeselect = ignoreDeselect;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            final int sendMessage2 = OS.SendMessage(this.handle, 4377, 0, 0);
            if (sendMessage2 != 0) {
                OS.SendMessage(sendMessage2, 1052, 0, 0);
            }
        }
        final boolean b3 = true;
        this.ignoreShrink = b3;
        this.shrink = b3;
        OS.SendMessage(this.handle, 4353, 0, n);
        this.ignoreShrink = false;
        if ((this.style & 0x2) != 0x0) {
            final boolean ignoreDeselect2 = false;
            this.lockSelection = ignoreDeselect2;
            this.ignoreSelect = ignoreDeselect2;
            this.ignoreDeselect = ignoreDeselect2;
        }
        if (b2) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.ValidateRect(this.handle, null);
            if (OS.SendMessage(this.handle, 4362, 4, sendMessage) == 0) {
                final RECT rect = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, sendMessage, rect, false)) {
                    OS.InvalidateRect(this.handle, rect, true);
                }
            }
        }
        if (OS.SendMessage(this.handle, 4357, 0, 0) == 0) {
            if (this.imageList != null) {
                OS.SendMessage(this.handle, 4361, 0, 0);
                this.display.releaseImageList(this.imageList);
            }
            this.imageList = null;
            if (this.hwndParent == 0 && !this.linesVisible && !this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
                this.customDraw = false;
            }
            this.items = new TreeItem[4];
            this.scrollWidth = 0;
            this.setScrollWidth();
        }
        this.updateScrollBar();
    }
    
    void destroyScrollBar(final int n) {
        super.destroyScrollBar(n);
        int getWindowLong = OS.GetWindowLong(this.handle, -16);
        if ((this.style & 0x300) == 0x0) {
            getWindowLong = ((getWindowLong & 0xFFCFFFFF) | 0x2000);
        }
        else if ((this.style & 0x100) == 0x0) {
            getWindowLong = ((getWindowLong & 0xFFEFFFFF) | 0x8000);
        }
        OS.SetWindowLong(this.handle, -16, getWindowLong);
    }
    
    void enableDrag(final boolean b) {
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        int n;
        if (b && this.hooks(29)) {
            n = (getWindowLong & 0xFFFFFFEF);
        }
        else {
            n = (getWindowLong | 0x10);
        }
        OS.SetWindowLong(this.handle, -16, n);
    }
    
    void enableWidget(final boolean b) {
        super.enableWidget(b);
        Control backgroundControl = this.findBackgroundControl();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && backgroundControl == null) {
            backgroundControl = this;
        }
        if (backgroundControl != null && backgroundControl.backgroundImage == null) {
            this._setBackgroundPixel(b ? backgroundControl.getBackgroundPixel() : -1);
        }
        if (this.hwndParent != 0) {
            OS.EnableWindow(this.hwndParent, b);
        }
        this.updateFullSelection();
    }
    
    boolean findCell(final int n, final int n2, final TreeItem[] array, final int[] array2, final RECT[] array3, final RECT[] array4) {
        boolean b = false;
        final TVHITTESTINFO tvhittestinfo = new TVHITTESTINFO();
        tvhittestinfo.x = n;
        tvhittestinfo.y = n2;
        OS.SendMessage(this.handle, 4369, 0, tvhittestinfo);
        if (tvhittestinfo.hItem != 0) {
            array[0] = this._getItem(tvhittestinfo.hItem);
            final POINT point = new POINT();
            point.x = n;
            point.y = n2;
            final int getDC = OS.GetDC(this.handle);
            int selectObject = 0;
            final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage);
            }
            final RECT rect = new RECT();
            if (this.hwndParent != 0) {
                OS.GetClientRect(this.hwndParent, rect);
                OS.MapWindowPoints(this.hwndParent, this.handle, rect, 2);
            }
            else {
                OS.GetClientRect(this.handle, rect);
            }
            final int max = Math.max(1, this.columnCount);
            final int[] array5 = new int[max];
            if (this.hwndHeader != 0) {
                OS.SendMessage(this.hwndHeader, 4625, max, array5);
            }
            array2[0] = 0;
            boolean b2 = false;
            while (array2[0] < max && !b2) {
                int n3 = array[0].fontHandle(array5[array2[0]]);
                if (n3 != -1) {
                    n3 = OS.SelectObject(getDC, n3);
                }
                array3[0] = array[0].getBounds(array5[array2[0]], true, false, true, false, true, getDC);
                if (array3[0].left > rect.right) {
                    b2 = true;
                }
                else {
                    array3[0].right = Math.min(array3[0].right, rect.right);
                    if (OS.PtInRect(array3[0], point)) {
                        if (this.isCustomToolTip()) {
                            final Event sendMeasureItemEvent = this.sendMeasureItemEvent(array[0], array5[array2[0]], getDC, ((OS.SendMessage(this.handle, 4391, tvhittestinfo.hItem, 2) & 0x2) != 0x0) ? 2 : 0);
                            if (this.isDisposed()) {
                                break;
                            }
                            if (array[0].isDisposed()) {
                                break;
                            }
                            array4[0] = new RECT();
                            array4[0].left = sendMeasureItemEvent.x;
                            array4[0].right = sendMeasureItemEvent.x + sendMeasureItemEvent.width;
                            array4[0].top = sendMeasureItemEvent.y;
                            array4[0].bottom = sendMeasureItemEvent.y + sendMeasureItemEvent.height;
                        }
                        else {
                            array4[0] = array[0].getBounds(array5[array2[0]], true, false, false, false, false, getDC);
                        }
                        if (array4[0].right > array3[0].right) {
                            b = true;
                        }
                        b2 = true;
                    }
                }
                if (n3 != -1) {
                    OS.SelectObject(getDC, n3);
                }
                if (!b) {
                    final int n4 = 0;
                    ++array2[n4];
                }
            }
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
        }
        return b;
    }
    
    int findIndex(final int hFirstIndexOf, final int n) {
        if (hFirstIndexOf == 0) {
            return -1;
        }
        if (hFirstIndexOf == this.hFirstIndexOf) {
            if (this.hFirstIndexOf == n) {
                this.hLastIndexOf = this.hFirstIndexOf;
                return this.lastIndexOf = 0;
            }
            if (this.hLastIndexOf == n) {
                return this.lastIndexOf;
            }
            int n2 = OS.SendMessage(this.handle, 4362, 2, this.hLastIndexOf);
            if (n2 == n) {
                this.hLastIndexOf = n2;
                return --this.lastIndexOf;
            }
            int n3 = OS.SendMessage(this.handle, 4362, 1, this.hLastIndexOf);
            if (n3 == n) {
                this.hLastIndexOf = n3;
                return ++this.lastIndexOf;
            }
            int lastIndexOf;
            for (lastIndexOf = this.lastIndexOf - 1; n2 != 0 && n2 != n; n2 = OS.SendMessage(this.handle, 4362, 2, n2), --lastIndexOf) {}
            if (n2 == n) {
                this.hLastIndexOf = n2;
                return this.lastIndexOf = lastIndexOf;
            }
            int lastIndexOf2;
            for (lastIndexOf2 = this.lastIndexOf + 1; n3 != 0 && n3 != n; n3 = OS.SendMessage(this.handle, 4362, 1, n3), ++lastIndexOf2) {}
            if (n3 == n) {
                this.hLastIndexOf = n3;
                return this.lastIndexOf = lastIndexOf2;
            }
            return -1;
        }
        else {
            int lastIndexOf3;
            int sendMessage;
            for (lastIndexOf3 = 0, sendMessage = hFirstIndexOf; sendMessage != 0 && sendMessage != n; sendMessage = OS.SendMessage(this.handle, 4362, 1, sendMessage), ++lastIndexOf3) {}
            if (sendMessage == n) {
                this.itemCount = -1;
                this.hFirstIndexOf = hFirstIndexOf;
                this.hLastIndexOf = sendMessage;
                return this.lastIndexOf = lastIndexOf3;
            }
            return -1;
        }
    }
    
    Widget findItem(final int n) {
        return this._getItem(n);
    }
    
    int findItem(final int hFirstIndexOf, final int n) {
        if (hFirstIndexOf == 0) {
            return 0;
        }
        if (hFirstIndexOf == this.hFirstIndexOf) {
            if (n == 0) {
                this.lastIndexOf = 0;
                return this.hLastIndexOf = this.hFirstIndexOf;
            }
            if (this.lastIndexOf == n) {
                return this.hLastIndexOf;
            }
            if (this.lastIndexOf - 1 == n) {
                --this.lastIndexOf;
                return this.hLastIndexOf = OS.SendMessage(this.handle, 4362, 2, this.hLastIndexOf);
            }
            if (this.lastIndexOf + 1 == n) {
                ++this.lastIndexOf;
                return this.hLastIndexOf = OS.SendMessage(this.handle, 4362, 1, this.hLastIndexOf);
            }
            if (n < this.lastIndexOf) {
                int lastIndexOf;
                int hLastIndexOf;
                for (lastIndexOf = this.lastIndexOf - 1, hLastIndexOf = OS.SendMessage(this.handle, 4362, 2, this.hLastIndexOf); hLastIndexOf != 0 && n < lastIndexOf; hLastIndexOf = OS.SendMessage(this.handle, 4362, 2, hLastIndexOf), --lastIndexOf) {}
                if (n == lastIndexOf) {
                    this.lastIndexOf = lastIndexOf;
                    return this.hLastIndexOf = hLastIndexOf;
                }
            }
            else {
                int lastIndexOf2;
                int hLastIndexOf2;
                for (lastIndexOf2 = this.lastIndexOf + 1, hLastIndexOf2 = OS.SendMessage(this.handle, 4362, 1, this.hLastIndexOf); hLastIndexOf2 != 0 && lastIndexOf2 < n; hLastIndexOf2 = OS.SendMessage(this.handle, 4362, 1, hLastIndexOf2), ++lastIndexOf2) {}
                if (n == lastIndexOf2) {
                    this.lastIndexOf = lastIndexOf2;
                    return this.hLastIndexOf = hLastIndexOf2;
                }
            }
            return 0;
        }
        else {
            int lastIndexOf3;
            int sendMessage;
            for (lastIndexOf3 = 0, sendMessage = hFirstIndexOf; sendMessage != 0 && lastIndexOf3 < n; sendMessage = OS.SendMessage(this.handle, 4362, 1, sendMessage), ++lastIndexOf3) {}
            if (n == lastIndexOf3) {
                this.itemCount = -1;
                this.lastIndexOf = lastIndexOf3;
                this.hFirstIndexOf = hFirstIndexOf;
                return this.hLastIndexOf = sendMessage;
            }
            return 0;
        }
    }
    
    TreeItem getFocusItem() {
        final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
        return (sendMessage != 0) ? this._getItem(sendMessage) : null;
    }
    
    public int getGridLineWidth() {
        this.checkWidget();
        return 1;
    }
    
    public int getHeaderHeight() {
        this.checkWidget();
        if (this.hwndHeader == 0) {
            return 0;
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.hwndHeader, rect);
        return rect.bottom - rect.top;
    }
    
    public boolean getHeaderVisible() {
        this.checkWidget();
        return this.hwndHeader != 0 && (OS.GetWindowLong(this.hwndHeader, -16) & 0x10000000) != 0x0;
    }
    
    Point getImageSize() {
        if (this.imageList != null) {
            return this.imageList.getImageSize();
        }
        return new Point(0, this.getItemHeight());
    }
    
    int getBottomItem() {
        int sendMessage = OS.SendMessage(this.handle, 4362, 5, 0);
        if (sendMessage == 0) {
            return 0;
        }
        for (int i = 0; i <= OS.SendMessage(this.handle, 4368, 0, 0); ++i) {
            final int sendMessage2 = OS.SendMessage(this.handle, 4362, 6, sendMessage);
            if (sendMessage2 == 0) {
                return sendMessage;
            }
            sendMessage = sendMessage2;
        }
        return sendMessage;
    }
    
    public TreeColumn getColumn(final int n) {
        this.checkWidget();
        if (n < 0 || n >= this.columnCount) {
            this.error(6);
        }
        return this.columns[n];
    }
    
    public int getColumnCount() {
        this.checkWidget();
        return this.columnCount;
    }
    
    public int[] getColumnOrder() {
        this.checkWidget();
        if (this.columnCount == 0) {
            return new int[0];
        }
        final int[] array = new int[this.columnCount];
        OS.SendMessage(this.hwndHeader, 4625, this.columnCount, array);
        return array;
    }
    
    public TreeColumn[] getColumns() {
        this.checkWidget();
        final TreeColumn[] array = new TreeColumn[this.columnCount];
        System.arraycopy(this.columns, 0, array, 0, this.columnCount);
        return array;
    }
    
    public TreeItem getItem(final int n) {
        this.checkWidget();
        if (n < 0) {
            this.error(6);
        }
        final int sendMessage = OS.SendMessage(this.handle, 4362, 0, 0);
        if (sendMessage == 0) {
            this.error(6);
        }
        final int item = this.findItem(sendMessage, n);
        if (item == 0) {
            this.error(6);
        }
        return this._getItem(item);
    }
    
    TreeItem getItem(final NMTVCUSTOMDRAW nmtvcustomdraw) {
        int n = nmtvcustomdraw.lItemlParam;
        if ((this.style & 0x10000000) != 0x0 && n == -1) {
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 20;
            tvitem.hItem = nmtvcustomdraw.dwItemSpec;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
            n = tvitem.lParam;
        }
        return this._getItem(nmtvcustomdraw.dwItemSpec, n);
    }
    
    public TreeItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        final TVHITTESTINFO tvhittestinfo = new TVHITTESTINFO();
        tvhittestinfo.x = point.x;
        tvhittestinfo.y = point.y;
        OS.SendMessage(this.handle, 4369, 0, tvhittestinfo);
        if (tvhittestinfo.hItem != 0) {
            int n = 70;
            if ((this.style & 0x10000) != 0x0) {
                n |= 0x28;
            }
            else if (this.hooks(41)) {
                final TVHITTESTINFO tvhittestinfo2 = tvhittestinfo;
                tvhittestinfo2.flags &= 0xFFFFFFF9;
                if (this.hitTestSelection(tvhittestinfo.hItem, tvhittestinfo.x, tvhittestinfo.y)) {
                    final TVHITTESTINFO tvhittestinfo3 = tvhittestinfo;
                    tvhittestinfo3.flags |= 0x6;
                }
            }
            if ((tvhittestinfo.flags & n) != 0x0) {
                return this._getItem(tvhittestinfo.hItem);
            }
        }
        return null;
    }
    
    public int getItemCount() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4362, 0, 0);
        if (sendMessage == 0) {
            return 0;
        }
        return this.getItemCount(sendMessage);
    }
    
    int getItemCount(final int n) {
        int lastIndexOf = 0;
        int i = n;
        if (n == this.hFirstIndexOf) {
            if (this.itemCount != -1) {
                return this.itemCount;
            }
            i = this.hLastIndexOf;
            lastIndexOf = this.lastIndexOf;
        }
        while (i != 0) {
            i = OS.SendMessage(this.handle, 4362, 1, i);
            ++lastIndexOf;
        }
        if (n == this.hFirstIndexOf) {
            this.itemCount = lastIndexOf;
        }
        return lastIndexOf;
    }
    
    public int getItemHeight() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 4380, 0, 0);
    }
    
    public TreeItem[] getItems() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4362, 0, 0);
        if (sendMessage == 0) {
            return new TreeItem[0];
        }
        return this.getItems(sendMessage);
    }
    
    TreeItem[] getItems(final int hItem) {
        int n = 0;
        for (int i = hItem; i != 0; i = OS.SendMessage(this.handle, 4362, 1, i), ++n) {}
        int n2 = 0;
        TreeItem[] array = new TreeItem[n];
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        tvitem.hItem = hItem;
        while (tvitem.hItem != 0) {
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
            final TreeItem getItem = this._getItem(tvitem.hItem, tvitem.lParam);
            if (getItem != null) {
                array[n2++] = getItem;
            }
            tvitem.hItem = OS.SendMessage(this.handle, 4362, 1, tvitem.hItem);
        }
        if (n2 != n) {
            final TreeItem[] array2 = new TreeItem[n2];
            System.arraycopy(array, 0, array2, 0, n2);
            array = array2;
        }
        return array;
    }
    
    public boolean getLinesVisible() {
        this.checkWidget();
        return this.linesVisible;
    }
    
    int getNextSelection(int i, final TVITEM tvitem) {
        while (i != 0) {
            int n;
            if (OS.IsWinCE) {
                tvitem.hItem = i;
                OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                n = tvitem.state;
            }
            else {
                n = OS.SendMessage(this.handle, 4391, i, 2);
            }
            if ((n & 0x2) != 0x0) {
                return i;
            }
            final int nextSelection = this.getNextSelection(OS.SendMessage(this.handle, 4362, 4, i), tvitem);
            if (nextSelection != 0) {
                return nextSelection;
            }
            i = OS.SendMessage(this.handle, 4362, 1, i);
        }
        return 0;
    }
    
    public TreeItem getParentItem() {
        this.checkWidget();
        return null;
    }
    
    int getSelection(int i, final TVITEM tvitem, final TreeItem[] array, int selection, final int n, final boolean b, final boolean b2) {
        while (i != 0) {
            if (OS.IsWinCE || b) {
                tvitem.hItem = i;
                OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                if ((tvitem.state & 0x2) != 0x0) {
                    if (array != null && selection < array.length) {
                        array[selection] = this._getItem(i, tvitem.lParam);
                    }
                    ++selection;
                }
            }
            else if ((OS.SendMessage(this.handle, 4391, i, 2) & 0x2) != 0x0) {
                if (tvitem != null && array != null && selection < array.length) {
                    tvitem.hItem = i;
                    OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                    array[selection] = this._getItem(i, tvitem.lParam);
                }
                ++selection;
            }
            if (selection == n) {
                break;
            }
            if (b2) {
                if ((selection = this.getSelection(OS.SendMessage(this.handle, 4362, 4, i), tvitem, array, selection, n, b, b2)) == n) {
                    break;
                }
                i = OS.SendMessage(this.handle, 4362, 1, i);
            }
            else {
                i = OS.SendMessage(this.handle, 4362, 6, i);
            }
        }
        return selection;
    }
    
    public TreeItem[] getSelection() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
            if (sendMessage == 0) {
                return new TreeItem[0];
            }
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 28;
            tvitem.hItem = sendMessage;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
            if ((tvitem.state & 0x2) == 0x0) {
                return new TreeItem[0];
            }
            return new TreeItem[] { this._getItem(tvitem.hItem, tvitem.lParam) };
        }
        else {
            int selection = 0;
            final TreeItem[] array = new TreeItem[((this.style & 0x10000000) != 0x0) ? 8 : 1];
            final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            if ((this.style & 0x10000000) != 0x0) {
                final TVITEM tvitem2 = new TVITEM();
                tvitem2.mask = 28;
                selection = this.getSelection(OS.SendMessage(this.handle, 4362, 0, 0), tvitem2, array, 0, -1, false, true);
            }
            else {
                TVITEM tvitem3 = null;
                if (OS.IsWinCE) {
                    tvitem3 = new TVITEM();
                    tvitem3.mask = 8;
                }
                for (int i = 0; i < this.items.length; ++i) {
                    final TreeItem treeItem = this.items[i];
                    if (treeItem != null) {
                        final int handle = treeItem.handle;
                        int n;
                        if (OS.IsWinCE) {
                            tvitem3.hItem = handle;
                            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem3);
                            n = tvitem3.state;
                        }
                        else {
                            n = OS.SendMessage(this.handle, 4391, handle, 2);
                        }
                        if ((n & 0x2) != 0x0) {
                            if (selection < array.length) {
                                array[selection] = treeItem;
                            }
                            ++selection;
                        }
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
            if (selection == 0) {
                return new TreeItem[0];
            }
            if (selection == array.length) {
                return array;
            }
            final TreeItem[] array2 = new TreeItem[selection];
            if (selection < array.length) {
                System.arraycopy(array, 0, array2, 0, selection);
                return array2;
            }
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            final TVITEM tvitem4 = new TVITEM();
            tvitem4.mask = 28;
            final int sendMessage2 = OS.SendMessage(this.handle, 4362, 0, 0);
            final boolean b = array2.length > OS.SendMessage(this.handle, 4357, 0, 0) / 2;
            if (selection != this.getSelection(sendMessage2, tvitem4, array2, 0, selection, b, false)) {
                this.getSelection(sendMessage2, tvitem4, array2, 0, selection, b, true);
            }
            OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
            return array2;
        }
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        if ((this.style & 0x4) == 0x0) {
            int selection = 0;
            final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
            TVITEM tvitem = null;
            if (OS.IsWinCE) {
                tvitem = new TVITEM();
                tvitem.mask = 8;
            }
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            if ((this.style & 0x10000000) != 0x0) {
                selection = this.getSelection(OS.SendMessage(this.handle, 4362, 0, 0), tvitem, null, 0, -1, false, true);
            }
            else {
                for (int i = 0; i < this.items.length; ++i) {
                    final TreeItem treeItem = this.items[i];
                    if (treeItem != null) {
                        final int handle = treeItem.handle;
                        int n;
                        if (OS.IsWinCE) {
                            tvitem.hItem = handle;
                            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                            n = tvitem.state;
                        }
                        else {
                            n = OS.SendMessage(this.handle, 4391, handle, 2);
                        }
                        if ((n & 0x2) != 0x0) {
                            ++selection;
                        }
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
            return selection;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
        if (sendMessage == 0) {
            return 0;
        }
        int n2;
        if (OS.IsWinCE) {
            final TVITEM tvitem2 = new TVITEM();
            tvitem2.hItem = sendMessage;
            tvitem2.mask = 8;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem2);
            n2 = tvitem2.state;
        }
        else {
            n2 = OS.SendMessage(this.handle, 4391, sendMessage, 2);
        }
        return ((n2 & 0x2) != 0x0) ? 1 : 0;
    }
    
    public TreeColumn getSortColumn() {
        this.checkWidget();
        return this.sortColumn;
    }
    
    int getSortColumnPixel() {
        final int n = OS.IsWindowEnabled(this.handle) ? this.getBackgroundPixel() : OS.GetSysColor(OS.COLOR_3DFACE);
        int min = n & 0xFF;
        int min2 = (n & 0xFF00) >> 8;
        int min3 = (n & 0xFF0000) >> 16;
        if (min > 240 && min2 > 240 && min3 > 240) {
            min -= 8;
            min2 -= 8;
            min3 -= 8;
        }
        else {
            min = Math.min(255, min / 10 + min);
            min2 = Math.min(255, min2 / 10 + min2);
            min3 = Math.min(255, min3 / 10 + min3);
        }
        return (min & 0xFF) | (min2 & 0xFF) << 8 | (min3 & 0xFF) << 16;
    }
    
    public int getSortDirection() {
        this.checkWidget();
        return this.sortDirection;
    }
    
    public TreeItem getTopItem() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4362, 5, 0);
        return (sendMessage != 0) ? this._getItem(sendMessage) : null;
    }
    
    boolean hitTestSelection(final int n, final int n2, final int n3) {
        if (n == 0) {
            return false;
        }
        final TreeItem getItem = this._getItem(n);
        if (getItem == null) {
            return false;
        }
        if (!this.hooks(41)) {
            return false;
        }
        boolean b = false;
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        final int getDC = OS.GetDC(this.handle);
        int selectObject = 0;
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        if (sendMessage != 0) {
            selectObject = OS.SelectObject(getDC, sendMessage);
        }
        final int fontHandle = getItem.fontHandle(array[array2[0]]);
        if (fontHandle != -1) {
            OS.SelectObject(getDC, fontHandle);
        }
        if (this.sendMeasureItemEvent(getItem, array[array2[0]], getDC, ((OS.SendMessage(this.handle, 4391, n, 2) & 0x2) != 0x0) ? 2 : 0).getBounds().contains(n2, n3)) {
            b = true;
        }
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(this.handle, getDC);
        return b;
    }
    
    int imageIndex(final Image image, final int n) {
        if (image == null) {
            return -2;
        }
        if (this.imageList == null) {
            final Rectangle bounds = image.getBounds();
            this.imageList = this.display.getImageList(0, bounds.width, bounds.height);
        }
        int n2 = this.imageList.indexOf(image);
        if (n2 == -1) {
            n2 = this.imageList.add(image);
        }
        if (this.hwndHeader == 0 || OS.SendMessage(this.hwndHeader, 4623, 0, 0) == n) {
            final int handle = this.imageList.getHandle();
            if (OS.SendMessage(this.handle, 4360, 0, 0) != handle) {
                OS.SendMessage(this.handle, 4361, 0, handle);
                this.updateScrollBar();
            }
        }
        return n2;
    }
    
    int imageIndexHeader(final Image image) {
        if (image == null) {
            return -2;
        }
        if (this.headerImageList == null) {
            final Rectangle bounds = image.getBounds();
            this.headerImageList = this.display.getImageList(0, bounds.width, bounds.height);
            int n = this.headerImageList.indexOf(image);
            if (n == -1) {
                n = this.headerImageList.add(image);
            }
            final int handle = this.headerImageList.getHandle();
            if (this.hwndHeader != 0) {
                OS.SendMessage(this.hwndHeader, 4616, 0, handle);
            }
            this.updateScrollBar();
            return n;
        }
        final int index = this.headerImageList.indexOf(image);
        if (index != -1) {
            return index;
        }
        return this.headerImageList.add(image);
    }
    
    public int indexOf(final TreeColumn treeColumn) {
        this.checkWidget();
        if (treeColumn == null) {
            this.error(4);
        }
        if (treeColumn.isDisposed()) {
            this.error(5);
        }
        for (int i = 0; i < this.columnCount; ++i) {
            if (this.columns[i] == treeColumn) {
                return i;
            }
        }
        return -1;
    }
    
    public int indexOf(final TreeItem treeItem) {
        this.checkWidget();
        if (treeItem == null) {
            this.error(4);
        }
        if (treeItem.isDisposed()) {
            this.error(5);
        }
        final int sendMessage = OS.SendMessage(this.handle, 4362, 0, 0);
        return (sendMessage == 0) ? -1 : this.findIndex(sendMessage, treeItem.handle);
    }
    
    boolean isCustomToolTip() {
        return this.hooks(41);
    }
    
    boolean isItemSelected(final NMTVCUSTOMDRAW nmtvcustomdraw) {
        boolean b = false;
        if (OS.IsWindowEnabled(this.handle)) {
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 24;
            tvitem.hItem = nmtvcustomdraw.dwItemSpec;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
            if ((tvitem.state & 0xA) != 0x0) {
                b = true;
                if (this.handle == OS.GetFocus()) {
                    if (OS.GetTextColor(nmtvcustomdraw.hdc) != OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT)) {
                        b = false;
                    }
                    else if (OS.GetBkColor(nmtvcustomdraw.hdc) != OS.GetSysColor(OS.COLOR_HIGHLIGHT)) {
                        b = false;
                    }
                }
            }
            else if (nmtvcustomdraw.dwDrawStage == 65538 && OS.GetTextColor(nmtvcustomdraw.hdc) == OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT) && OS.GetBkColor(nmtvcustomdraw.hdc) == OS.GetSysColor(OS.COLOR_HIGHLIGHT)) {
                b = true;
            }
        }
        return b;
    }
    
    void redrawSelection() {
        if ((this.style & 0x4) != 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
            if (sendMessage != 0) {
                final RECT rect = new RECT();
                if (OS.TreeView_GetItemRect(this.handle, sendMessage, rect, false)) {
                    OS.InvalidateRect(this.handle, rect, true);
                }
            }
        }
        else {
            int hItem = OS.SendMessage(this.handle, 4362, 5, 0);
            if (hItem != 0) {
                TVITEM tvitem = null;
                if (OS.IsWinCE) {
                    tvitem = new TVITEM();
                    tvitem.mask = 8;
                }
                final RECT rect2 = new RECT();
                for (int n = 0; n <= OS.SendMessage(this.handle, 4368, 0, 0) && hItem != 0; hItem = OS.SendMessage(this.handle, 4362, 6, hItem), ++n) {
                    int n2;
                    if (OS.IsWinCE) {
                        tvitem.hItem = hItem;
                        OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                        n2 = tvitem.state;
                    }
                    else {
                        n2 = OS.SendMessage(this.handle, 4391, hItem, 2);
                    }
                    if ((n2 & 0x2) != 0x0 && OS.TreeView_GetItemRect(this.handle, hItem, rect2, false)) {
                        OS.InvalidateRect(this.handle, rect2, true);
                    }
                }
            }
        }
    }
    
    void register() {
        super.register();
        if (this.hwndParent != 0) {
            this.display.addControl(this.hwndParent, this);
        }
        if (this.hwndHeader != 0) {
            this.display.addControl(this.hwndHeader, this);
        }
    }
    
    void releaseItem(final int hItem, final TVITEM tvitem, final boolean b) {
        if (hItem == this.hAnchor) {
            this.hAnchor = 0;
        }
        if (hItem == this.hInsert) {
            this.hInsert = 0;
        }
        tvitem.hItem = hItem;
        if (OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem) != 0 && tvitem.lParam != -1) {
            if (tvitem.lParam < this.lastID) {
                this.lastID = tvitem.lParam;
            }
            if (b) {
                final TreeItem treeItem = this.items[tvitem.lParam];
                if (treeItem != null) {
                    treeItem.release(false);
                }
            }
            this.items[tvitem.lParam] = null;
        }
    }
    
    void releaseItems(int i, final TVITEM tvitem) {
        for (i = OS.SendMessage(this.handle, 4362, 4, i); i != 0; i = OS.SendMessage(this.handle, 4362, 1, i)) {
            this.releaseItems(i, tvitem);
            this.releaseItem(i, tvitem, true);
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        final boolean b = false;
        this.hwndHeader = (b ? 1 : 0);
        this.hwndParent = (b ? 1 : 0);
    }
    
    void releaseChildren(final boolean b) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final TreeItem treeItem = this.items[i];
                if (treeItem != null && !treeItem.isDisposed()) {
                    treeItem.release(false);
                }
            }
            this.items = null;
        }
        if (this.columns != null) {
            for (int j = 0; j < this.columns.length; ++j) {
                final TreeColumn treeColumn = this.columns[j];
                if (treeColumn != null && !treeColumn.isDisposed()) {
                    treeColumn.release(false);
                }
            }
            this.columns = null;
        }
        super.releaseChildren(b);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.customDraw = false;
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 4361, 0, 0);
            this.display.releaseImageList(this.imageList);
        }
        if (this.headerImageList != null) {
            if (this.hwndHeader != 0) {
                OS.SendMessage(this.hwndHeader, 4616, 0, 0);
            }
            this.display.releaseImageList(this.headerImageList);
        }
        final ImageList list = null;
        this.headerImageList = list;
        this.imageList = list;
        final int sendMessage = OS.SendMessage(this.handle, 4360, 2, 0);
        OS.SendMessage(this.handle, 4361, 2, 0);
        if (sendMessage != 0) {
            OS.ImageList_Destroy(sendMessage);
        }
        if (this.itemToolTipHandle != 0) {
            OS.DestroyWindow(this.itemToolTipHandle);
        }
        if (this.headerToolTipHandle != 0) {
            OS.DestroyWindow(this.headerToolTipHandle);
        }
        final boolean b = false;
        this.headerToolTipHandle = (b ? 1 : 0);
        this.itemToolTipHandle = (b ? 1 : 0);
    }
    
    public void removeAll() {
        this.checkWidget();
        final boolean b = false;
        this.hLastIndexOf = (b ? 1 : 0);
        this.hFirstIndexOf = (b ? 1 : 0);
        this.itemCount = -1;
        for (int i = 0; i < this.items.length; ++i) {
            final TreeItem treeItem = this.items[i];
            if (treeItem != null && !treeItem.isDisposed()) {
                treeItem.release(false);
            }
        }
        final boolean b2 = true;
        this.ignoreSelect = b2;
        this.ignoreDeselect = b2;
        final boolean b3 = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (b3) {
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        final boolean b4 = true;
        this.ignoreShrink = b4;
        this.shrink = b4;
        final int sendMessage = OS.SendMessage(this.handle, 4353, 0, -65536);
        this.ignoreShrink = false;
        if (b3) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.InvalidateRect(this.handle, null, true);
        }
        final boolean b5 = false;
        this.ignoreSelect = b5;
        this.ignoreDeselect = b5;
        if (sendMessage == 0) {
            this.error(15);
        }
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 4361, 0, 0);
            this.display.releaseImageList(this.imageList);
        }
        this.imageList = null;
        if (this.hwndParent == 0 && !this.linesVisible && !this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
            this.customDraw = false;
        }
        final boolean b6 = false;
        this.hLastIndexOf = (b6 ? 1 : 0);
        this.hFirstIndexOf = (b6 ? 1 : 0);
        this.hInsert = (b6 ? 1 : 0);
        this.hAnchor = (b6 ? 1 : 0);
        this.itemCount = -1;
        this.items = new TreeItem[4];
        this.scrollWidth = 0;
        this.setScrollWidth();
        this.updateScrollBar();
    }
    
    public void removeSelectionListener(final SelectionListener selectionListener) {
        this.checkWidget();
        if (selectionListener == null) {
            this.error(4);
        }
        this.eventTable.unhook(13, selectionListener);
        this.eventTable.unhook(14, selectionListener);
    }
    
    public void removeTreeListener(final TreeListener treeListener) {
        this.checkWidget();
        if (treeListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(17, treeListener);
        this.eventTable.unhook(18, treeListener);
    }
    
    void reskinChildren(final int n) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final TreeItem treeItem = this.items[i];
                if (treeItem != null) {
                    treeItem.reskinChildren(n);
                }
            }
        }
        if (this.columns != null) {
            for (int j = 0; j < this.columns.length; ++j) {
                final TreeColumn treeColumn = this.columns[j];
                if (treeColumn != null) {
                    treeColumn.reskinChildren(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    public void setInsertMark(final TreeItem treeItem, final boolean b) {
        this.checkWidget();
        int handle = 0;
        if (treeItem != null) {
            if (treeItem.isDisposed()) {
                this.error(5);
            }
            handle = treeItem.handle;
        }
        this.hInsert = handle;
        this.insertAfter = !b;
        OS.SendMessage(this.handle, 4378, this.insertAfter ? 1 : 0, this.hInsert);
    }
    
    public void setItemCount(int max) {
        this.checkWidget();
        max = Math.max(0, max);
        this.setItemCount(max, 0, OS.SendMessage(this.handle, 4362, 0, 0));
    }
    
    void setItemCount(final int n, final int hItem, int i) {
        boolean b = false;
        if (OS.SendMessage(this.handle, 4357, 0, 0) == 0) {
            b = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            if (b) {
                OS.DefWindowProc(this.handle, 11, 0, 0);
            }
        }
        int n2;
        for (n2 = 0; i != 0 && n2 < n; i = OS.SendMessage(this.handle, 4362, 1, i), ++n2) {}
        boolean b2 = false;
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 20;
        if (!b && (this.style & 0x10000000) != 0x0) {
            if (OS.IsWinCE) {
                tvitem.hItem = hItem;
                tvitem.mask = 8;
                OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                b2 = ((tvitem.state & 0x20) != 0x0);
            }
            else {
                b2 = ((OS.SendMessage(this.handle, 4391, hItem, 32) & 0x20) != 0x0);
            }
        }
        while (i != 0) {
            tvitem.hItem = i;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
            i = OS.SendMessage(this.handle, 4362, 1, i);
            final TreeItem treeItem = (tvitem.lParam != -1) ? this.items[tvitem.lParam] : null;
            if (treeItem != null && !treeItem.isDisposed()) {
                treeItem.dispose();
            }
            else {
                this.releaseItem(tvitem.hItem, tvitem, false);
                this.destroyItem(null, tvitem.hItem);
            }
        }
        if ((this.style & 0x10000000) != 0x0) {
            for (int j = n2; j < n; ++j) {
                if (b2) {
                    this.ignoreShrink = true;
                }
                this.createItem(null, hItem, -65534, 0);
                if (b2) {
                    this.ignoreShrink = false;
                }
            }
        }
        else {
            this.shrink = true;
            final TreeItem[] items = new TreeItem[this.items.length + Math.max(4, (n + 3) / 4 * 4)];
            System.arraycopy(this.items, 0, items, 0, this.items.length);
            this.items = items;
            for (int k = n2; k < n; ++k) {
                new TreeItem(this, 0, hItem, -65534, 0);
            }
        }
        if (b) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.InvalidateRect(this.handle, null, true);
        }
    }
    
    void setItemHeight(final int n) {
        this.checkWidget();
        if (n < -1) {
            this.error(5);
        }
        OS.SendMessage(this.handle, 4379, n, 0);
    }
    
    public void setLinesVisible(final boolean linesVisible) {
        this.checkWidget();
        if (this.linesVisible == linesVisible) {
            return;
        }
        this.linesVisible = linesVisible;
        if (this.hwndParent == 0 && this.linesVisible) {
            this.customDraw = true;
        }
        OS.InvalidateRect(this.handle, null, true);
    }
    
    int scrolledHandle() {
        if (this.hwndHeader == 0) {
            return this.handle;
        }
        return (this.columnCount == 0 && this.scrollWidth == 0) ? this.handle : this.hwndParent;
    }
    
    void select(int i, final TVITEM tvitem) {
        while (i != 0) {
            tvitem.hItem = i;
            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
            this.select(OS.SendMessage(this.handle, 4362, 4, i), tvitem);
            i = OS.SendMessage(this.handle, 4362, 1, i);
        }
    }
    
    public void select(final TreeItem selection) {
        this.checkWidget();
        if (selection == null) {
            this.error(4);
        }
        if (selection.isDisposed()) {
            this.error(5);
        }
        if ((this.style & 0x4) == 0x0) {
            final TVITEM tvitem = new TVITEM();
            tvitem.mask = 24;
            tvitem.stateMask = 2;
            tvitem.state = 2;
            tvitem.hItem = selection.handle;
            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
            return;
        }
        final int handle = selection.handle;
        int n;
        if (OS.IsWinCE) {
            final TVITEM tvitem2 = new TVITEM();
            tvitem2.hItem = handle;
            tvitem2.mask = 8;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem2);
            n = tvitem2.state;
        }
        else {
            n = OS.SendMessage(this.handle, 4391, handle, 2);
        }
        if ((n & 0x2) != 0x0) {
            return;
        }
        SCROLLINFO scrollinfo = null;
        if ((OS.GetWindowLong(this.handle, -16) & 0xA000) == 0x0) {
            scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 23;
            OS.GetScrollInfo(this.handle, 0, scrollinfo);
        }
        final SCROLLINFO scrollinfo2 = new SCROLLINFO();
        scrollinfo2.cbSize = SCROLLINFO.sizeof;
        scrollinfo2.fMask = 23;
        OS.GetScrollInfo(this.handle, 1, scrollinfo2);
        final boolean b = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (b) {
            OS.UpdateWindow(this.handle);
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        this.setSelection(selection);
        if (scrollinfo != null) {
            OS.SendMessage(this.handle, 276, OS.MAKELPARAM(4, scrollinfo.nPos), 0);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            OS.SetScrollInfo(this.handle, 1, scrollinfo2, true);
        }
        OS.SendMessage(this.handle, 277, OS.MAKELPARAM(4, scrollinfo2.nPos), 0);
        if (b) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.InvalidateRect(this.handle, null, true);
            if ((this.style & 0x20000000) == 0x0) {
                final int style = this.style;
                this.style |= 0x20000000;
                OS.UpdateWindow(this.handle);
                this.style = style;
            }
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 24;
        tvitem.state = 2;
        tvitem.stateMask = 2;
        final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
        OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
        if ((this.style & 0x10000000) != 0x0) {
            this.select(OS.SendMessage(this.handle, 4362, 0, 0), tvitem);
        }
        else {
            for (int i = 0; i < this.items.length; ++i) {
                final TreeItem treeItem = this.items[i];
                if (treeItem != null) {
                    tvitem.hItem = treeItem.handle;
                    OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                }
            }
        }
        OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
    }
    
    Event sendEraseItemEvent(final TreeItem item, final NMTTCUSTOMDRAW nmttcustomdraw, final int index, final RECT rect) {
        final int saveDC = OS.SaveDC(nmttcustomdraw.hdc);
        final RECT toolTipInset = this.toolTipInset(rect);
        OS.SetWindowOrgEx(nmttcustomdraw.hdc, toolTipInset.left, toolTipInset.top, null);
        final GCData gcData = new GCData();
        gcData.device = this.display;
        gcData.foreground = OS.GetTextColor(nmttcustomdraw.hdc);
        gcData.background = OS.GetBkColor(nmttcustomdraw.hdc);
        gcData.font = item.getFont(index);
        gcData.uiState = OS.SendMessage(this.handle, 297, 0, 0);
        final GC win32_new = GC.win32_new(nmttcustomdraw.hdc, gcData);
        final Event event = new Event();
        event.item = item;
        event.index = index;
        event.gc = win32_new;
        final Event event2 = event;
        event2.detail |= 0x10;
        event.x = rect.left;
        event.y = rect.top;
        event.width = rect.right - rect.left;
        event.height = rect.bottom - rect.top;
        this.sendEvent(40, event);
        event.gc = null;
        win32_new.dispose();
        OS.RestoreDC(nmttcustomdraw.hdc, saveDC);
        return event;
    }
    
    Event sendMeasureItemEvent(final TreeItem item, final int index, final int n, final int detail) {
        final RECT bounds = item.getBounds(index, true, true, false, false, false, n);
        final int saveDC = OS.SaveDC(n);
        final GCData gcData = new GCData();
        gcData.device = this.display;
        gcData.font = item.getFont(index);
        final GC win32_new = GC.win32_new(n, gcData);
        final Event event = new Event();
        event.item = item;
        event.gc = win32_new;
        event.index = index;
        event.x = bounds.left;
        event.y = bounds.top;
        event.width = bounds.right - bounds.left;
        event.height = bounds.bottom - bounds.top;
        event.detail = detail;
        this.sendEvent(41, event);
        event.gc = null;
        win32_new.dispose();
        OS.RestoreDC(n, saveDC);
        if (this.isDisposed() || item.isDisposed()) {
            return null;
        }
        if (this.hwndHeader != 0 && this.columnCount == 0 && event.x + event.width > this.scrollWidth) {
            this.setScrollWidth(this.scrollWidth = event.x + event.width);
        }
        if (event.height > this.getItemHeight()) {
            this.setItemHeight(event.height);
        }
        return event;
    }
    
    Event sendPaintItemEvent(final TreeItem item, final NMTTCUSTOMDRAW nmttcustomdraw, final int index, final RECT rect) {
        final int saveDC = OS.SaveDC(nmttcustomdraw.hdc);
        final RECT toolTipInset = this.toolTipInset(rect);
        OS.SetWindowOrgEx(nmttcustomdraw.hdc, toolTipInset.left, toolTipInset.top, null);
        final GCData gcData = new GCData();
        gcData.device = this.display;
        gcData.font = item.getFont(index);
        gcData.foreground = OS.GetTextColor(nmttcustomdraw.hdc);
        gcData.background = OS.GetBkColor(nmttcustomdraw.hdc);
        gcData.uiState = OS.SendMessage(this.handle, 297, 0, 0);
        final GC win32_new = GC.win32_new(nmttcustomdraw.hdc, gcData);
        final Event event = new Event();
        event.item = item;
        event.index = index;
        event.gc = win32_new;
        final Event event2 = event;
        event2.detail |= 0x10;
        event.x = rect.left;
        event.y = rect.top;
        event.width = rect.right - rect.left;
        event.height = rect.bottom - rect.top;
        this.sendEvent(42, event);
        event.gc = null;
        win32_new.dispose();
        OS.RestoreDC(nmttcustomdraw.hdc, saveDC);
        return event;
    }
    
    void setBackgroundImage(final int backgroundImage) {
        super.setBackgroundImage(backgroundImage);
        if (backgroundImage != 0) {
            if (OS.SendMessage(this.handle, 4383, 0, 0) == -1) {
                OS.SendMessage(this.handle, 4381, 0, -1);
            }
            this._setBackgroundPixel(-1);
        }
        else {
            Control backgroundControl = this.findBackgroundControl();
            if (backgroundControl == null) {
                backgroundControl = this;
            }
            if (backgroundControl.backgroundImage == null) {
                this.setBackgroundPixel(backgroundControl.getBackgroundPixel());
            }
        }
        this.updateFullSelection();
    }
    
    void setBackgroundPixel(final int n) {
        final Control imageControl = this.findImageControl();
        if (imageControl != null) {
            this.setBackgroundImage(imageControl.backgroundImage);
            return;
        }
        if (OS.IsWindowEnabled(this.handle)) {
            this._setBackgroundPixel(n);
        }
        this.updateFullSelection();
    }
    
    void setCursor() {
        final Cursor cursor = this.findCursor();
        OS.SetCursor((cursor == null) ? OS.LoadCursor(0, 32512) : cursor.handle);
    }
    
    public void setColumnOrder(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        if (this.columnCount == 0) {
            if (array.length != 0) {
                this.error(5);
            }
            return;
        }
        if (array.length != this.columnCount) {
            this.error(5);
        }
        final int[] array2 = new int[this.columnCount];
        OS.SendMessage(this.hwndHeader, 4625, this.columnCount, array2);
        boolean b = false;
        final boolean[] array3 = new boolean[this.columnCount];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i];
            if (n < 0 || n >= this.columnCount) {
                this.error(6);
            }
            if (array3[n]) {
                this.error(5);
            }
            array3[n] = true;
            if (n != array2[i]) {
                b = true;
            }
        }
        if (b) {
            final RECT[] array4 = new RECT[this.columnCount];
            for (int j = 0; j < this.columnCount; ++j) {
                array4[j] = new RECT();
                OS.SendMessage(this.hwndHeader, 4615, j, array4[j]);
            }
            OS.SendMessage(this.hwndHeader, 4626, array.length, array);
            OS.InvalidateRect(this.handle, null, true);
            this.updateImageList();
            final TreeColumn[] array5 = new TreeColumn[this.columnCount];
            System.arraycopy(this.columns, 0, array5, 0, this.columnCount);
            final RECT rect = new RECT();
            for (int k = 0; k < this.columnCount; ++k) {
                final TreeColumn treeColumn = array5[k];
                if (!treeColumn.isDisposed()) {
                    OS.SendMessage(this.hwndHeader, 4615, k, rect);
                    if (rect.left != array4[k].left) {
                        treeColumn.updateToolTip(k);
                        treeColumn.sendEvent(10);
                    }
                }
            }
        }
    }
    
    void setCheckboxImageList() {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final int n = 5;
        final boolean b = false;
        int n2;
        if (OS.IsWinCE) {
            n2 = ((b | false) ? 1 : 0);
        }
        else if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            n2 = ((b ? 1 : 0) | 0x20);
        }
        else {
            final int getDC = OS.GetDC(this.handle);
            final int getDeviceCaps = OS.GetDeviceCaps(getDC, 12);
            final int getDeviceCaps2 = OS.GetDeviceCaps(getDC, 14);
            OS.ReleaseDC(this.handle, getDC);
            int n3 = 0;
            switch (getDeviceCaps * getDeviceCaps2) {
                case 4: {
                    n3 = ((b ? 1 : 0) | 0x4);
                    break;
                }
                case 8: {
                    n3 = ((b ? 1 : 0) | 0x8);
                    break;
                }
                case 16: {
                    n3 = ((b ? 1 : 0) | 0x10);
                    break;
                }
                case 24: {
                    n3 = ((b ? 1 : 0) | 0x18);
                    break;
                }
                case 32: {
                    n3 = ((b ? 1 : 0) | 0x20);
                    break;
                }
                default: {
                    n3 = ((b | false) ? 1 : 0);
                    break;
                }
            }
            n2 = (n3 | 0x1);
        }
        if ((this.style & 0x4000000) != 0x0) {
            n2 |= 0x2000;
        }
        final int sendMessage;
        final int n4 = sendMessage = OS.SendMessage(this.handle, 4380, 0, 0);
        final int imageList_Create = OS.ImageList_Create(sendMessage, n4, n2, n, n);
        final int getDC2 = OS.GetDC(this.handle);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC2);
        final int createCompatibleBitmap = OS.CreateCompatibleBitmap(getDC2, sendMessage * n, n4);
        final int selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, sendMessage * n, n4);
        int backgroundPixel;
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            Control backgroundControl = this.findBackgroundControl();
            if (backgroundControl == null) {
                backgroundControl = this;
            }
            backgroundPixel = backgroundControl.getBackgroundPixel();
        }
        else {
            backgroundPixel = 33554687;
            if ((backgroundPixel & 0xFFFFFF) == OS.GetSysColor(OS.COLOR_WINDOW)) {
                backgroundPixel = 33619712;
            }
        }
        final int createSolidBrush = OS.CreateSolidBrush(backgroundPixel);
        OS.FillRect(createCompatibleDC, rect, createSolidBrush);
        OS.DeleteObject(createSolidBrush);
        final int selectObject2 = OS.SelectObject(getDC2, this.defaultFont());
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.GetTextMetrics(getDC2, textmetric);
        OS.SelectObject(getDC2, selectObject2);
        final int min = Math.min(textmetric.tmHeight, sendMessage);
        final int min2 = Math.min(textmetric.tmHeight, n4);
        final int n5 = (sendMessage - min) / 2;
        final int n6 = (n4 - min2) / 2 + 1;
        OS.SetRect(rect, n5 + sendMessage, n6, n5 + sendMessage + min, n6 + min2);
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int hButtonTheme = this.display.hButtonTheme();
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 1, rect, null);
            final RECT rect2 = rect;
            rect2.left += sendMessage;
            final RECT rect3 = rect;
            rect3.right += sendMessage;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 5, rect, null);
            final RECT rect4 = rect;
            rect4.left += sendMessage;
            final RECT rect5 = rect;
            rect5.right += sendMessage;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 1, rect, null);
            final RECT rect6 = rect;
            rect6.left += sendMessage;
            final RECT rect7 = rect;
            rect7.right += sendMessage;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 9, rect, null);
        }
        else {
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 16384);
            final RECT rect8 = rect;
            rect8.left += sendMessage;
            final RECT rect9 = rect;
            rect9.right += sendMessage;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 17408);
            final RECT rect10 = rect;
            rect10.left += sendMessage;
            final RECT rect11 = rect;
            rect11.right += sendMessage;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 16640);
            final RECT rect12 = rect;
            rect12.left += sendMessage;
            final RECT rect13 = rect;
            rect13.right += sendMessage;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 17664);
        }
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
        OS.ReleaseDC(this.handle, getDC2);
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            OS.ImageList_Add(imageList_Create, createCompatibleBitmap, 0);
        }
        else {
            OS.ImageList_AddMasked(imageList_Create, createCompatibleBitmap, backgroundPixel);
        }
        OS.DeleteObject(createCompatibleBitmap);
        final int sendMessage2 = OS.SendMessage(this.handle, 4360, 2, 0);
        OS.SendMessage(this.handle, 4361, 2, imageList_Create);
        if (sendMessage2 != 0) {
            OS.ImageList_Destroy(sendMessage2);
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        super.setFont(font);
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
    }
    
    void setForegroundPixel(int defaultForeground) {
        if (this.explorerTheme && defaultForeground == -1) {
            defaultForeground = this.defaultForeground();
        }
        OS.SendMessage(this.handle, 4382, 0, defaultForeground);
    }
    
    public void setHeaderVisible(final boolean b) {
        this.checkWidget();
        if (this.hwndHeader == 0) {
            if (!b) {
                return;
            }
            this.createParent();
        }
        final int getWindowLong = OS.GetWindowLong(this.hwndHeader, -16);
        if (b) {
            if ((getWindowLong & 0x8) == 0x0) {
                return;
            }
            OS.SetWindowLong(this.hwndHeader, -16, getWindowLong & 0xFFFFFFF7);
            OS.ShowWindow(this.hwndHeader, 5);
        }
        else {
            if ((getWindowLong & 0x8) != 0x0) {
                return;
            }
            OS.SetWindowLong(this.hwndHeader, -16, getWindowLong | 0x8);
            OS.ShowWindow(this.hwndHeader, 0);
        }
        this.setScrollWidth();
        this.updateHeaderToolTips();
        this.updateScrollBar();
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        int sendMessage = 0;
        if (redraw && this.drawCount == 1) {
            if (OS.SendMessage(this.handle, 4357, 0, 0) == 0) {
                final TVINSERTSTRUCT tvinsertstruct = new TVINSERTSTRUCT();
                tvinsertstruct.hInsertAfter = -65535;
                sendMessage = OS.SendMessage(this.handle, OS.TVM_INSERTITEM, 0, tvinsertstruct);
            }
            OS.DefWindowProc(this.handle, 11, 1, 0);
            this.updateScrollBar();
        }
        super.setRedraw(redraw);
        if (!redraw && this.drawCount == 1) {
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        if (sendMessage != 0) {
            this.ignoreShrink = true;
            OS.SendMessage(this.handle, 4353, 0, sendMessage);
            this.ignoreShrink = false;
        }
    }
    
    void setScrollWidth() {
        if (this.hwndHeader == 0 || this.hwndParent == 0) {
            return;
        }
        int n = 0;
        final HDITEM hditem = new HDITEM();
        for (int i = 0; i < this.columnCount; ++i) {
            hditem.mask = 1;
            OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, i, hditem);
            n += hditem.cxy;
        }
        this.setScrollWidth(Math.max(this.scrollWidth, n));
    }
    
    void setScrollWidth(final int nMax) {
        if (this.hwndHeader == 0 || this.hwndParent == 0) {
            return;
        }
        int nPos = 0;
        final RECT rect = new RECT();
        final SCROLLINFO scrollinfo = new SCROLLINFO();
        scrollinfo.cbSize = SCROLLINFO.sizeof;
        scrollinfo.fMask = 3;
        if (this.columnCount == 0 && nMax == 0) {
            OS.GetScrollInfo(this.hwndParent, 0, scrollinfo);
            scrollinfo.nPage = scrollinfo.nMax + 1;
            OS.SetScrollInfo(this.hwndParent, 0, scrollinfo, true);
            OS.GetScrollInfo(this.hwndParent, 1, scrollinfo);
            scrollinfo.nPage = scrollinfo.nMax + 1;
            OS.SetScrollInfo(this.hwndParent, 1, scrollinfo, true);
        }
        else if ((this.style & 0x100) != 0x0) {
            OS.GetClientRect(this.hwndParent, rect);
            OS.GetScrollInfo(this.hwndParent, 0, scrollinfo);
            scrollinfo.nMax = nMax;
            scrollinfo.nPage = rect.right - rect.left + 1;
            OS.SetScrollInfo(this.hwndParent, 0, scrollinfo, true);
            scrollinfo.fMask = 4;
            OS.GetScrollInfo(this.hwndParent, 0, scrollinfo);
            nPos = scrollinfo.nPos;
        }
        if (this.horizontalBar != null) {
            this.horizontalBar.setIncrement(5);
            this.horizontalBar.setPageIncrement(scrollinfo.nPage);
        }
        OS.GetClientRect(this.hwndParent, rect);
        final int getProcessHeap = OS.GetProcessHeap();
        final HDLAYOUT hdlayout = new HDLAYOUT();
        hdlayout.prc = OS.HeapAlloc(getProcessHeap, 8, RECT.sizeof);
        hdlayout.pwpos = OS.HeapAlloc(getProcessHeap, 8, WINDOWPOS.sizeof);
        OS.MoveMemory(hdlayout.prc, rect, RECT.sizeof);
        OS.SendMessage(this.hwndHeader, 4613, 0, hdlayout);
        final WINDOWPOS windowpos = new WINDOWPOS();
        OS.MoveMemory(windowpos, hdlayout.pwpos, WINDOWPOS.sizeof);
        if (hdlayout.prc != 0) {
            OS.HeapFree(getProcessHeap, 0, hdlayout.prc);
        }
        if (hdlayout.pwpos != 0) {
            OS.HeapFree(getProcessHeap, 0, hdlayout.pwpos);
        }
        this.SetWindowPos(this.hwndHeader, 0, windowpos.x - nPos, windowpos.y, windowpos.cx + nPos, windowpos.cy, 16);
        final int n = ((OS.GetWindowLong(this.handle, -20) & 0x200) != 0x0) ? OS.GetSystemMetrics(45) : 0;
        final int n2 = windowpos.cx + ((this.columnCount == 0 && nMax == 0) ? 0 : OS.GetSystemMetrics(2));
        final int n3 = rect.bottom - rect.top - windowpos.cy;
        final boolean ignoreResize = this.ignoreResize;
        this.ignoreResize = true;
        this.SetWindowPos(this.handle, 0, windowpos.x - nPos - n, windowpos.y + windowpos.cy - n, n2 + nPos + n * 2, n3 + n * 2, 20);
        this.ignoreResize = ignoreResize;
    }
    
    void setSelection(int i, final TVITEM tvitem, final TreeItem[] array) {
        while (i != 0) {
            int j;
            for (j = 0; j < array.length; ++j) {
                final TreeItem treeItem = array[j];
                if (treeItem != null && treeItem.handle == i) {
                    break;
                }
            }
            tvitem.hItem = i;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
            if ((tvitem.state & 0x2) != 0x0) {
                if (j == array.length) {
                    tvitem.state = 0;
                    OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                }
            }
            else if (j != array.length) {
                tvitem.state = 2;
                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
            }
            this.setSelection(OS.SendMessage(this.handle, 4362, 4, i), tvitem, array);
            i = OS.SendMessage(this.handle, 4362, 1, i);
        }
    }
    
    public void setSelection(final TreeItem treeItem) {
        this.checkWidget();
        if (treeItem == null) {
            this.error(4);
        }
        this.setSelection(new TreeItem[] { treeItem });
    }
    
    public void setSelection(final TreeItem[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        final int length = array.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            this.deselectAll();
            return;
        }
        final TreeItem treeItem = array[0];
        if (treeItem != null) {
            if (treeItem.isDisposed()) {
                this.error(5);
            }
            final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
            final int handle = treeItem.handle;
            this.hAnchor = handle;
            final int hItem = handle;
            final boolean checkScroll = this.checkScroll(hItem);
            if (checkScroll) {
                OS.SendMessage(this.handle, 11, 1, 0);
                OS.DefWindowProc(this.handle, 11, 0, 0);
            }
            this.ignoreSelect = true;
            OS.SendMessage(this.handle, 4363, 9, hItem);
            this.ignoreSelect = false;
            if (OS.SendMessage(this.handle, 4368, 0, 0) == 0) {
                OS.SendMessage(this.handle, 4363, 5, hItem);
                if (OS.SendMessage(this.handle, 4362, 3, hItem) == 0) {
                    OS.SendMessage(this.handle, 276, 6, 0);
                }
            }
            if (checkScroll) {
                OS.DefWindowProc(this.handle, 11, 1, 0);
                OS.SendMessage(this.handle, 11, 0, 0);
            }
            if (sendMessage == hItem) {
                final TVITEM tvitem = new TVITEM();
                tvitem.mask = 24;
                tvitem.state = 2;
                tvitem.stateMask = 2;
                tvitem.hItem = hItem;
                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                this.showItem(hItem);
            }
        }
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        final TVITEM tvitem2 = new TVITEM();
        tvitem2.mask = 24;
        tvitem2.stateMask = 2;
        final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
        OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
        if ((this.style & 0x10000000) != 0x0) {
            this.setSelection(OS.SendMessage(this.handle, 4362, 0, 0), tvitem2, array);
        }
        else {
            for (int i = 0; i < this.items.length; ++i) {
                final TreeItem treeItem2 = this.items[i];
                if (treeItem2 != null) {
                    int n;
                    for (n = 0; n < length && array[n] != treeItem2; ++n) {}
                    tvitem2.hItem = treeItem2.handle;
                    OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem2);
                    if ((tvitem2.state & 0x2) != 0x0) {
                        if (n == length) {
                            tvitem2.state = 0;
                            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem2);
                        }
                    }
                    else if (n != length) {
                        tvitem2.state = 2;
                        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem2);
                    }
                }
            }
        }
        OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
    }
    
    public void setSortColumn(final TreeColumn sortColumn) {
        this.checkWidget();
        if (sortColumn != null && sortColumn.isDisposed()) {
            this.error(5);
        }
        if (this.sortColumn != null && !this.sortColumn.isDisposed()) {
            this.sortColumn.setSortDirection(0);
        }
        this.sortColumn = sortColumn;
        if (this.sortColumn != null && this.sortDirection != 0) {
            this.sortColumn.setSortDirection(this.sortDirection);
        }
    }
    
    public void setSortDirection(final int n) {
        this.checkWidget();
        if ((n & 0x480) == 0x0 && n != 0) {
            return;
        }
        this.sortDirection = n;
        if (this.sortColumn != null && !this.sortColumn.isDisposed()) {
            this.sortColumn.setSortDirection(n);
        }
    }
    
    public void setTopItem(final TreeItem treeItem) {
        this.checkWidget();
        if (treeItem == null) {
            SWT.error(4);
        }
        if (treeItem.isDisposed()) {
            SWT.error(5);
        }
        final int handle = treeItem.handle;
        if (handle == OS.SendMessage(this.handle, 4362, 5, 0)) {
            return;
        }
        final boolean checkScroll = this.checkScroll(handle);
        boolean b = false;
        if (checkScroll) {
            OS.SendMessage(this.handle, 11, 1, 0);
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        else {
            b = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            if (b) {
                OS.DefWindowProc(this.handle, 11, 0, 0);
            }
        }
        OS.SendMessage(this.handle, 4363, 5, handle);
        if (OS.SendMessage(this.handle, 4362, 3, handle) == 0) {
            OS.SendMessage(this.handle, 276, 6, 0);
        }
        if (checkScroll) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.SendMessage(this.handle, 11, 0, 0);
        }
        else if (b) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.InvalidateRect(this.handle, null, true);
        }
        this.updateScrollBar();
    }
    
    void showItem(final int n) {
        if (OS.SendMessage(this.handle, 4368, 0, 0) == 0) {
            final boolean checkScroll = this.checkScroll(n);
            if (checkScroll) {
                OS.SendMessage(this.handle, 11, 1, 0);
                OS.DefWindowProc(this.handle, 11, 0, 0);
            }
            OS.SendMessage(this.handle, 4363, 5, n);
            OS.SendMessage(this.handle, 276, 6, 0);
            if (checkScroll) {
                OS.DefWindowProc(this.handle, 11, 1, 0);
                OS.SendMessage(this.handle, 11, 0, 0);
            }
        }
        else {
            boolean b = true;
            final RECT rect = new RECT();
            if (OS.TreeView_GetItemRect(this.handle, n, rect, true)) {
                this.forceResize();
                final RECT rect2 = new RECT();
                OS.GetClientRect(this.handle, rect2);
                final POINT point = new POINT();
                point.x = rect.left;
                point.y = rect.top;
                if (OS.PtInRect(rect2, point)) {
                    point.y = rect.bottom;
                    if (OS.PtInRect(rect2, point)) {
                        b = false;
                    }
                }
            }
            if (b) {
                final boolean checkScroll2 = this.checkScroll(n);
                if (checkScroll2) {
                    OS.SendMessage(this.handle, 11, 1, 0);
                    OS.DefWindowProc(this.handle, 11, 0, 0);
                }
                OS.SendMessage(this.handle, 4372, 0, n);
                if (checkScroll2) {
                    OS.DefWindowProc(this.handle, 11, 1, 0);
                    OS.SendMessage(this.handle, 11, 0, 0);
                }
            }
        }
        if (this.hwndParent != 0) {
            final RECT rect3 = new RECT();
            if (OS.TreeView_GetItemRect(this.handle, n, rect3, true)) {
                this.forceResize();
                final RECT rect4 = new RECT();
                OS.GetClientRect(this.hwndParent, rect4);
                OS.MapWindowPoints(this.hwndParent, this.handle, rect4, 2);
                final POINT point2 = new POINT();
                point2.x = rect3.left;
                point2.y = rect3.top;
                if (!OS.PtInRect(rect4, point2)) {
                    point2.y = rect3.bottom;
                    if (!OS.PtInRect(rect4, point2)) {
                        final SCROLLINFO scrollinfo = new SCROLLINFO();
                        scrollinfo.cbSize = SCROLLINFO.sizeof;
                        scrollinfo.fMask = 4;
                        scrollinfo.nPos = Math.max(0, point2.x - 1);
                        OS.SetScrollInfo(this.hwndParent, 0, scrollinfo, true);
                        this.setScrollWidth();
                    }
                }
            }
        }
        this.updateScrollBar();
    }
    
    public void showColumn(final TreeColumn treeColumn) {
        this.checkWidget();
        if (treeColumn == null) {
            this.error(4);
        }
        if (treeColumn.isDisposed()) {
            this.error(5);
        }
        if (treeColumn.parent != this) {
            return;
        }
        final int index = this.indexOf(treeColumn);
        if (index == -1) {
            return;
        }
        if (index >= 0 && index < this.columnCount) {
            this.forceResize();
            final RECT rect = new RECT();
            OS.GetClientRect(this.hwndParent, rect);
            OS.MapWindowPoints(this.hwndParent, this.handle, rect, 2);
            final RECT rect2 = new RECT();
            OS.SendMessage(this.hwndHeader, 4615, index, rect2);
            boolean b = rect2.left < rect.left;
            if (!b) {
                b = (rect2.left + Math.min(rect.right - rect.left, rect2.right - rect2.left) > rect.right);
            }
            if (b) {
                final SCROLLINFO scrollinfo = new SCROLLINFO();
                scrollinfo.cbSize = SCROLLINFO.sizeof;
                scrollinfo.fMask = 4;
                scrollinfo.nPos = Math.max(0, rect2.left - 1);
                OS.SetScrollInfo(this.hwndParent, 0, scrollinfo, true);
                this.setScrollWidth();
            }
        }
    }
    
    public void showItem(final TreeItem treeItem) {
        this.checkWidget();
        if (treeItem == null) {
            this.error(4);
        }
        if (treeItem.isDisposed()) {
            this.error(5);
        }
        this.showItem(treeItem.handle);
    }
    
    public void showSelection() {
        this.checkWidget();
        int hItem = 0;
        if ((this.style & 0x4) != 0x0) {
            hItem = OS.SendMessage(this.handle, 4362, 9, 0);
            if (hItem == 0) {
                return;
            }
            int n;
            if (OS.IsWinCE) {
                final TVITEM tvitem = new TVITEM();
                tvitem.hItem = hItem;
                tvitem.mask = 8;
                OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                n = tvitem.state;
            }
            else {
                n = OS.SendMessage(this.handle, 4391, hItem, 2);
            }
            if ((n & 0x2) == 0x0) {
                return;
            }
        }
        else {
            final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
            OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
            TVITEM tvitem2 = null;
            if (OS.IsWinCE) {
                tvitem2 = new TVITEM();
                tvitem2.mask = 8;
            }
            if ((this.style & 0x10000000) != 0x0) {
                hItem = this.getNextSelection(OS.SendMessage(this.handle, 4362, 0, 0), tvitem2);
            }
            else {
                for (int i = 0; i < this.items.length; ++i) {
                    final TreeItem treeItem = this.items[i];
                    if (treeItem != null) {
                        int n2;
                        if (OS.IsWinCE) {
                            tvitem2.hItem = treeItem.handle;
                            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem2);
                            n2 = tvitem2.state;
                        }
                        else {
                            n2 = OS.SendMessage(this.handle, 4391, treeItem.handle, 2);
                        }
                        if ((n2 & 0x2) != 0x0) {
                            hItem = treeItem.handle;
                            break;
                        }
                    }
                }
            }
            OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
        }
        if (hItem != 0) {
            this.showItem(hItem);
        }
    }
    
    void sort() {
        this.checkWidget();
        if ((this.style & 0x10000000) != 0x0) {
            return;
        }
        this.sort(-65536, false);
    }
    
    void sort(final int hParent, final boolean b) {
        final int sendMessage = OS.SendMessage(this.handle, 4357, 0, 0);
        if (sendMessage == 0 || sendMessage == 1) {
            return;
        }
        final boolean b2 = false;
        this.hLastIndexOf = (b2 ? 1 : 0);
        this.hFirstIndexOf = (b2 ? 1 : 0);
        if (this.sortDirection == 128 || this.sortDirection == 0) {
            OS.SendMessage(this.handle, 4371, b ? 1 : 0, hParent);
        }
        else {
            final Callback callback = new Callback(this, "CompareFunc", 3);
            final int address = callback.getAddress();
            final TVSORTCB tvsortcb = new TVSORTCB();
            tvsortcb.hParent = hParent;
            tvsortcb.lpfnCompare = address;
            tvsortcb.lParam = ((this.sortColumn == null) ? 0 : this.indexOf(this.sortColumn));
            OS.SendMessage(this.handle, 4373, b ? 1 : 0, tvsortcb);
            callback.dispose();
        }
    }
    
    void subclass() {
        super.subclass();
        if (this.hwndHeader != 0) {
            OS.SetWindowLongPtr(this.hwndHeader, -4, this.display.windowProc);
        }
    }
    
    RECT toolTipInset(final RECT rect) {
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            final RECT rect2 = new RECT();
            OS.SetRect(rect2, rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1);
            return rect2;
        }
        return rect;
    }
    
    RECT toolTipRect(final RECT rect) {
        final RECT rect2 = new RECT();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            OS.SetRect(rect2, rect.left - 1, rect.top - 1, rect.right + 1, rect.bottom + 1);
        }
        else {
            OS.SetRect(rect2, rect.left, rect.top, rect.right, rect.bottom);
            OS.AdjustWindowRectEx(rect2, OS.GetWindowLong(this.itemToolTipHandle, -16), false, OS.GetWindowLong(this.itemToolTipHandle, -20));
        }
        return rect2;
    }
    
    String toolTipText(final NMTTDISPINFO nmttdispinfo) {
        if (OS.SendMessage(this.handle, 4377, 0, 0) == nmttdispinfo.hwndFrom && this.toolTipText != null) {
            return "";
        }
        if (this.headerToolTipHandle == nmttdispinfo.hwndFrom) {
            for (int i = 0; i < this.columnCount; ++i) {
                final TreeColumn treeColumn = this.columns[i];
                if (treeColumn.id == nmttdispinfo.idFrom) {
                    return treeColumn.toolTipText;
                }
            }
            return super.toolTipText(nmttdispinfo);
        }
        if (this.itemToolTipHandle == nmttdispinfo.hwndFrom) {
            if (this.toolTipText != null) {
                return "";
            }
            final int getMessagePos = OS.GetMessagePos();
            final POINT point = new POINT();
            OS.POINTSTOPOINT(point, getMessagePos);
            OS.ScreenToClient(this.handle, point);
            final int[] array = { 0 };
            final TreeItem[] array2 = { null };
            if (this.findCell(point.x, point.y, array2, array, new RECT[1], new RECT[1])) {
                String text = null;
                if (array[0] == 0) {
                    text = array2[0].text;
                }
                else {
                    final String[] strings = array2[0].strings;
                    if (strings != null) {
                        text = strings[array[0]];
                    }
                }
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && this.isCustomToolTip()) {
                    text = " ";
                }
                if (text != null) {
                    return text;
                }
            }
        }
        return super.toolTipText(nmttdispinfo);
    }
    
    int topHandle() {
        return (this.hwndParent != 0) ? this.hwndParent : this.handle;
    }
    
    void updateFullSelection() {
        if ((this.style & 0x10000) != 0x0) {
            int getWindowLong;
            final int n = getWindowLong = OS.GetWindowLong(this.handle, -16);
            if ((getWindowLong & 0x1000) != 0x0) {
                if ((!OS.IsWindowEnabled(this.handle) || this.findImageControl() != null) && !this.explorerTheme) {
                    getWindowLong &= 0xFFFFEFFF;
                }
            }
            else if (OS.IsWindowEnabled(this.handle) && this.findImageControl() == null && !this.hooks(40) && !this.hooks(42)) {
                getWindowLong |= 0x1000;
            }
            if (getWindowLong != n) {
                OS.SetWindowLong(this.handle, -16, getWindowLong);
                OS.InvalidateRect(this.handle, null, true);
            }
        }
    }
    
    void updateHeaderToolTips() {
        if (this.headerToolTipHandle == 0) {
            return;
        }
        final RECT rect = new RECT();
        final TOOLINFO toolinfo = new TOOLINFO();
        toolinfo.cbSize = TOOLINFO.sizeof;
        toolinfo.uFlags = 16;
        toolinfo.hwnd = this.hwndHeader;
        toolinfo.lpszText = -1;
        for (int i = 0; i < this.columnCount; ++i) {
            final TreeColumn treeColumn = this.columns[i];
            if (OS.SendMessage(this.hwndHeader, 4615, i, rect) != 0) {
                final TOOLINFO toolinfo2 = toolinfo;
                final TreeColumn treeColumn2 = treeColumn;
                final int n = this.display.nextToolTipId++;
                treeColumn2.id = n;
                toolinfo2.uId = n;
                toolinfo.left = rect.left;
                toolinfo.top = rect.top;
                toolinfo.right = rect.right;
                toolinfo.bottom = rect.bottom;
                OS.SendMessage(this.headerToolTipHandle, OS.TTM_ADDTOOL, 0, toolinfo);
            }
        }
    }
    
    void updateImageList() {
        if (this.imageList == null) {
            return;
        }
        if (this.hwndHeader == 0) {
            return;
        }
        int i = 0;
        final int sendMessage = OS.SendMessage(this.hwndHeader, 4623, 0, 0);
        while (i < this.items.length) {
            final TreeItem treeItem = this.items[i];
            if (treeItem != null) {
                Image image = null;
                if (sendMessage == 0) {
                    image = treeItem.image;
                }
                else {
                    final Image[] images = treeItem.images;
                    if (images != null) {
                        image = images[sendMessage];
                    }
                }
                if (image != null) {
                    break;
                }
            }
            ++i;
        }
        final int n = (i == this.items.length) ? 0 : this.imageList.getHandle();
        if (n != OS.SendMessage(this.handle, 4360, 0, 0)) {
            OS.SendMessage(this.handle, 4361, 0, n);
        }
    }
    
    void updateImages() {
        if (this.sortColumn != null && !this.sortColumn.isDisposed() && OS.COMCTL32_MAJOR < 6) {
            switch (this.sortDirection) {
                case 128:
                case 1024: {
                    this.sortColumn.setImage(this.display.getSortImage(this.sortDirection), true, true);
                    break;
                }
            }
        }
    }
    
    void updateOrientation() {
        super.updateOrientation();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int n = rect.right - rect.left;
        final int n2 = rect.bottom - rect.top;
        OS.SetWindowPos(this.handle, 0, 0, 0, n - 1, n2 - 1, 6);
        OS.SetWindowPos(this.handle, 0, 0, 0, n, n2, 6);
        if (this.hwndParent != 0) {
            final int getWindowLong = OS.GetWindowLong(this.hwndParent, -20);
            int n3;
            if ((this.style & 0x4000000) != 0x0) {
                n3 = (getWindowLong | 0x400000);
            }
            else {
                n3 = (getWindowLong & 0xFFBFFFFF);
            }
            OS.SetWindowLong(this.hwndParent, -20, n3);
            final RECT rect2 = new RECT();
            OS.GetWindowRect(this.hwndParent, rect2);
            final int n4 = rect2.right - rect2.left;
            final int n5 = rect2.bottom - rect2.top;
            OS.SetWindowPos(this.hwndParent, 0, 0, 0, n4 - 1, n5 - 1, 6);
            OS.SetWindowPos(this.hwndParent, 0, 0, 0, n4, n5, 6);
        }
        if (this.hwndHeader != 0) {
            final int getWindowLong2 = OS.GetWindowLong(this.hwndHeader, -20);
            int n6;
            if ((this.style & 0x4000000) != 0x0) {
                n6 = (getWindowLong2 | 0x400000);
            }
            else {
                n6 = (getWindowLong2 & 0xFFBFFFFF);
            }
            OS.SetWindowLong(this.hwndHeader, -20, n6);
            OS.InvalidateRect(this.hwndHeader, null, true);
        }
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
    }
    
    void updateScrollBar() {
        if (this.hwndParent != 0 && (this.columnCount != 0 || this.scrollWidth != 0)) {
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 23;
            if (OS.SendMessage(this.handle, 4357, 0, 0) == 0) {
                OS.GetScrollInfo(this.hwndParent, 1, scrollinfo);
                scrollinfo.nPage = scrollinfo.nMax + 1;
                OS.SetScrollInfo(this.hwndParent, 1, scrollinfo, true);
            }
            else {
                OS.GetScrollInfo(this.handle, 1, scrollinfo);
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10) && scrollinfo.nPage == 0) {
                    final SCROLLBARINFO scrollbarinfo = new SCROLLBARINFO();
                    scrollbarinfo.cbSize = SCROLLBARINFO.sizeof;
                    OS.GetScrollBarInfo(this.handle, -5, scrollbarinfo);
                    if ((scrollbarinfo.rgstate[0] & 0x8000) != 0x0) {
                        scrollinfo.nPage = scrollinfo.nMax + 1;
                    }
                }
                OS.SetScrollInfo(this.hwndParent, 1, scrollinfo, true);
            }
        }
    }
    
    void unsubclass() {
        super.unsubclass();
        if (this.hwndHeader != 0) {
            OS.SetWindowLongPtr(this.hwndHeader, -4, Tree.HeaderProc);
        }
    }
    
    int widgetStyle() {
        final int n = super.widgetStyle() | 0x20 | 0x4 | 0x1 | 0x4000;
        int n2;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.IsAppThemed()) {
            n2 = (n | 0x200);
            if ((this.style & 0x10000) != 0x0) {
                n2 |= 0x1000;
            }
        }
        else if ((this.style & 0x10000) != 0x0) {
            n2 = (n | 0x1000);
        }
        else {
            n2 = (n | 0x2);
        }
        if ((this.style & 0x300) == 0x0) {
            n2 = ((n2 & 0xFFCFFFFF) | 0x2000);
        }
        else if ((this.style & 0x100) == 0x0) {
            n2 = ((n2 & 0xFFEFFFFF) | 0x8000);
        }
        return n2 | 0x10;
    }
    
    TCHAR windowClass() {
        return Tree.TreeClass;
    }
    
    int windowProc() {
        return Tree.TreeProc;
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.hwndHeader != 0 && n == this.hwndHeader) {
            Label_0365: {
                switch (n2) {
                    case 123: {
                        final LRESULT wmContextMenu = this.wmContextMenu(n, n3, n4);
                        if (wmContextMenu != null) {
                            return wmContextMenu.value;
                        }
                        break;
                    }
                    case 533: {
                        if (OS.COMCTL32_MAJOR < 6 && n4 != 0 && n4 != this.hwndHeader) {
                            OS.InvalidateRect(this.hwndHeader, null, true);
                            break;
                        }
                        break;
                    }
                    case 675: {
                        if (OS.COMCTL32_MAJOR >= 6) {
                            this.updateHeaderToolTips();
                        }
                        this.updateHeaderToolTips();
                        break;
                    }
                    case 78: {
                        final NMHDR nmhdr = new NMHDR();
                        OS.MoveMemory(nmhdr, n4, NMHDR.sizeof);
                        switch (nmhdr.code) {
                            case -530:
                            case -522:
                            case -521:
                            case -520: {
                                return OS.SendMessage(this.handle, n2, n3, n4);
                            }
                            default: {
                                break Label_0365;
                            }
                        }
                        break;
                    }
                    case 32: {
                        if (n3 != n || (short)OS.LOWORD(n4) != 1) {
                            break;
                        }
                        final HDHITTESTINFO hdhittestinfo = new HDHITTESTINFO();
                        final int getMessagePos = OS.GetMessagePos();
                        final POINT point = new POINT();
                        OS.POINTSTOPOINT(point, getMessagePos);
                        OS.ScreenToClient(n, point);
                        hdhittestinfo.x = point.x;
                        hdhittestinfo.y = point.y;
                        final int sendMessage = OS.SendMessage(this.hwndHeader, 4614, 0, hdhittestinfo);
                        if (sendMessage >= 0 && sendMessage < this.columnCount && !this.columns[sendMessage].resizable && (hdhittestinfo.flags & 0xC) != 0x0) {
                            OS.SetCursor(OS.LoadCursor(0, 32512));
                            return 1;
                        }
                        break;
                    }
                }
            }
            return this.callWindowProc(n, n2, n3, n4);
        }
        if (this.hwndParent != 0 && n == this.hwndParent) {
            switch (n2) {
                case 3: {
                    this.sendEvent(10);
                    return 0;
                }
                case 5: {
                    this.setScrollWidth();
                    if (this.ignoreResize) {
                        return 0;
                    }
                    this.setResizeChildren(false);
                    final int callWindowProc = this.callWindowProc(n, 5, n3, n4);
                    this.sendEvent(11);
                    if (this.isDisposed()) {
                        return 0;
                    }
                    if (this.layout != null) {
                        this.markLayout(false, false);
                        this.updateLayout(false, false);
                    }
                    this.setResizeChildren(true);
                    this.updateScrollBar();
                    return callWindowProc;
                }
                case 133: {
                    final LRESULT wmNCPaint = this.wmNCPaint(n, n3, n4);
                    if (wmNCPaint != null) {
                        return wmNCPaint.value;
                    }
                    break;
                }
                case 791: {
                    final LRESULT wmPrint = this.wmPrint(n, n3, n4);
                    if (wmPrint != null) {
                        return wmPrint.value;
                    }
                    break;
                }
                case 21:
                case 78:
                case 273: {
                    return OS.SendMessage(this.handle, n2, n3, n4);
                }
                case 276: {
                    if (this.horizontalBar != null && (n4 == 0 || n4 == this.hwndParent)) {
                        this.wmScroll(this.horizontalBar, true, this.hwndParent, 276, n3, n4);
                    }
                    this.setScrollWidth();
                    break;
                }
                case 277: {
                    final SCROLLINFO scrollinfo = new SCROLLINFO();
                    scrollinfo.cbSize = SCROLLINFO.sizeof;
                    scrollinfo.fMask = 23;
                    OS.GetScrollInfo(this.hwndParent, 1, scrollinfo);
                    if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.LOWORD(n3) == 5) {
                        scrollinfo.nPos = scrollinfo.nTrackPos;
                    }
                    OS.SetScrollInfo(this.handle, 1, scrollinfo, true);
                    final int sendMessage2 = OS.SendMessage(this.handle, 277, n3, n4);
                    OS.GetScrollInfo(this.handle, 1, scrollinfo);
                    OS.SetScrollInfo(this.hwndParent, 1, scrollinfo, true);
                    return sendMessage2;
                }
            }
            return this.callWindowProc(n, n2, n3, n4);
        }
        if (n2 != Display.DI_GETDRAGIMAGE || ((this.style & 0x2) == 0x0 && !this.hooks(40) && !this.hooks(42))) {
            return super.windowProc(n, n2, n3, n4);
        }
        final int sendMessage3 = OS.SendMessage(this.handle, 4362, 5, 0);
        final TreeItem[] array = new TreeItem[10];
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 28;
        final int selection = this.getSelection(sendMessage3, tvitem, array, 0, 10, false, true);
        if (selection == 0) {
            return 0;
        }
        final POINT point2 = new POINT();
        OS.POINTSTOPOINT(point2, OS.GetMessagePos());
        OS.MapWindowPoints(0, this.handle, point2, 1);
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final RECT bounds = array[0].getBounds(0, true, true, false);
        if ((this.style & 0x10000) != 0x0) {
            final int n5 = 301;
            bounds.left = Math.max(rect.left, point2.x - n5 / 2);
            if (rect.right > bounds.left + n5) {
                bounds.right = bounds.left + n5;
            }
            else {
                bounds.right = rect.right;
                bounds.left = Math.max(rect.left, bounds.right - n5);
            }
        }
        else {
            bounds.left = Math.max(bounds.left, rect.left);
            bounds.right = Math.min(bounds.right, rect.right);
        }
        final int createRectRgn = OS.CreateRectRgn(bounds.left, bounds.top, bounds.right, bounds.bottom);
        for (int i = 1; i < selection; ++i) {
            if (bounds.bottom - bounds.top > 301) {
                break;
            }
            if (bounds.bottom > rect.bottom) {
                break;
            }
            final RECT bounds2 = array[i].getBounds(0, true, true, false);
            if ((this.style & 0x10000) != 0x0) {
                bounds2.left = bounds.left;
                bounds2.right = bounds.right;
            }
            else {
                bounds2.left = Math.max(bounds2.left, rect.left);
                bounds2.right = Math.min(bounds2.right, rect.right);
            }
            final int createRectRgn2 = OS.CreateRectRgn(bounds2.left, bounds2.top, bounds2.right, bounds2.bottom);
            OS.CombineRgn(createRectRgn, createRectRgn, createRectRgn2, 2);
            OS.DeleteObject(createRectRgn2);
            bounds.bottom = bounds2.bottom;
        }
        OS.GetRgnBox(createRectRgn, bounds);
        final int getDC = OS.GetDC(this.handle);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = bounds.right - bounds.left;
        bitmapinfoheader.biHeight = -(bounds.bottom - bounds.top);
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = 32;
        bitmapinfoheader.biCompression = 0;
        final byte[] array2 = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(array2, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        final int createDIBSection = OS.CreateDIBSection(0, array2, 0, new int[1], 0, 0);
        if (createDIBSection == 0) {
            SWT.error(2);
        }
        final int selectObject = OS.SelectObject(createCompatibleDC, createDIBSection);
        final int crColorKey = 253;
        final POINT point3 = new POINT();
        OS.SetWindowOrgEx(createCompatibleDC, bounds.left, bounds.top, point3);
        OS.FillRect(createCompatibleDC, bounds, this.findBrush(crColorKey, 0));
        OS.OffsetRgn(createRectRgn, -bounds.left, -bounds.top);
        OS.SelectClipRgn(createCompatibleDC, createRectRgn);
        OS.PrintWindow(this.handle, createCompatibleDC, 0);
        OS.SetWindowOrgEx(createCompatibleDC, point3.x, point3.y, null);
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.DeleteDC(createCompatibleDC);
        OS.ReleaseDC(0, getDC);
        OS.DeleteObject(createRectRgn);
        final SHDRAGIMAGE shdragimage = new SHDRAGIMAGE();
        shdragimage.hbmpDragImage = createDIBSection;
        shdragimage.crColorKey = crColorKey;
        shdragimage.sizeDragImage.cx = bitmapinfoheader.biWidth;
        shdragimage.sizeDragImage.cy = -bitmapinfoheader.biHeight;
        shdragimage.ptOffset.x = point2.x - bounds.left;
        shdragimage.ptOffset.y = point2.y - bounds.top;
        if ((this.style & 0x8000000) != 0x0) {
            shdragimage.ptOffset.x = shdragimage.sizeDragImage.cx - shdragimage.ptOffset.x;
        }
        OS.MoveMemory(n4, shdragimage, SHDRAGIMAGE.sizeof);
        return 1;
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        final LRESULT wm_CHAR = super.WM_CHAR(n, n2);
        if (wm_CHAR != null) {
            return wm_CHAR;
        }
        switch (n) {
            case 32: {
                final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
                if (sendMessage != 0) {
                    this.hAnchor = sendMessage;
                    OS.SendMessage(this.handle, 4372, 0, sendMessage);
                    final TVITEM tvitem = new TVITEM();
                    tvitem.mask = 28;
                    tvitem.hItem = sendMessage;
                    if ((this.style & 0x20) != 0x0) {
                        tvitem.stateMask = 61440;
                        OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                        int n3 = tvitem.state >> 12;
                        if ((n3 & 0x1) != 0x0) {
                            ++n3;
                        }
                        else {
                            --n3;
                        }
                        tvitem.state = n3 << 12;
                        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                        if (!OS.IsWinCE) {
                            int sendMessage2 = sendMessage;
                            if (OS.COMCTL32_MAJOR >= 6) {
                                sendMessage2 = OS.SendMessage(this.handle, 4395, sendMessage, 0);
                            }
                            OS.NotifyWinEvent(32773, this.handle, -4, sendMessage2);
                        }
                    }
                    tvitem.stateMask = 2;
                    OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                    if ((this.style & 0x2) != 0x0 && OS.GetKeyState(17) < 0) {
                        if ((tvitem.state & 0x2) != 0x0) {
                            final TVITEM tvitem2 = tvitem;
                            tvitem2.state &= 0xFFFFFFFD;
                        }
                        else {
                            final TVITEM tvitem3 = tvitem;
                            tvitem3.state |= 0x2;
                        }
                    }
                    else {
                        final TVITEM tvitem4 = tvitem;
                        tvitem4.state |= 0x2;
                    }
                    OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                    final TreeItem getItem = this._getItem(sendMessage, tvitem.lParam);
                    final Event event = new Event();
                    event.item = getItem;
                    this.sendSelectionEvent(13, event, false);
                    if ((this.style & 0x20) != 0x0) {
                        final Event event2 = new Event();
                        event2.item = getItem;
                        event2.detail = 32;
                        this.sendSelectionEvent(13, event2, false);
                    }
                }
                return LRESULT.ZERO;
            }
            case 13: {
                final Event event3 = new Event();
                final int sendMessage3 = OS.SendMessage(this.handle, 4362, 9, 0);
                if (sendMessage3 != 0) {
                    event3.item = this._getItem(sendMessage3);
                }
                this.sendSelectionEvent(14, event3, false);
                return LRESULT.ZERO;
            }
            case 27: {
                return LRESULT.ZERO;
            }
            default: {
                return wm_CHAR;
            }
        }
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if ((this.style & 0x20000000) != 0x0) {
            return LRESULT.ONE;
        }
        if (this.findImageControl() != null) {
            return LRESULT.ONE;
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_GETOBJECT(final int n, final int n2) {
        if (((this.style & 0x20) != 0x0 || this.hwndParent != 0) && this.accessible == null) {
            this.accessible = this.new_Accessible(this);
        }
        return super.WM_GETOBJECT(n, n2);
    }
    
    LRESULT WM_HSCROLL(final int n, final int n2) {
        boolean b = false;
        if ((this.style & 0x20000000) != 0x0) {
            b = ((this.style & 0x10000000) != 0x0 || this.hooks(40) || this.hooks(42));
        }
        if (b) {
            this.style &= 0xDFFFFFFF;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4, 0);
            }
        }
        final LRESULT wm_HSCROLL = super.WM_HSCROLL(n, n2);
        if (b) {
            this.style |= 0x20000000;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4, 4);
            }
        }
        if (wm_HSCROLL != null) {
            return wm_HSCROLL;
        }
        return wm_HSCROLL;
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        final LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        switch (n) {
            case 37:
            case 39: {
                if ((this.style & 0x4000000) != 0x0 != this.createdAsRTL) {
                    return new LRESULT(this.callWindowProc(this.handle, 256, (n == 39) ? 37 : 39, n2));
                }
                break;
            }
            case 32: {
                return LRESULT.ZERO;
            }
            case 107: {
                if (OS.GetKeyState(17) < 0 && this.hwndHeader != 0) {
                    final TreeColumn[] array = new TreeColumn[this.columnCount];
                    System.arraycopy(this.columns, 0, array, 0, this.columnCount);
                    for (int i = 0; i < this.columnCount; ++i) {
                        final TreeColumn treeColumn = array[i];
                        if (!treeColumn.isDisposed() && treeColumn.getResizable()) {
                            treeColumn.pack();
                        }
                    }
                    break;
                }
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36:
            case 38:
            case 40: {
                OS.SendMessage(this.handle, 295, 3, 0);
                if (this.itemToolTipHandle != 0) {
                    OS.ShowWindow(this.itemToolTipHandle, 0);
                }
                if ((this.style & 0x4) != 0x0) {
                    break;
                }
                if (OS.GetKeyState(16) < 0) {
                    final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
                    if (sendMessage != 0) {
                        if (this.hAnchor == 0) {
                            this.hAnchor = sendMessage;
                        }
                        final boolean b = true;
                        this.ignoreDeselect = b;
                        this.ignoreSelect = b;
                        final int callWindowProc = this.callWindowProc(this.handle, 256, n, n2);
                        final boolean b2 = false;
                        this.ignoreDeselect = b2;
                        this.ignoreSelect = b2;
                        final int sendMessage2 = OS.SendMessage(this.handle, 4362, 9, 0);
                        final TVITEM tvitem = new TVITEM();
                        tvitem.mask = 24;
                        tvitem.stateMask = 2;
                        int j = sendMessage;
                        final RECT rect = new RECT();
                        if (!OS.TreeView_GetItemRect(this.handle, this.hAnchor, rect, false)) {
                            this.hAnchor = sendMessage;
                            OS.TreeView_GetItemRect(this.handle, this.hAnchor, rect, false);
                        }
                        final RECT rect2 = new RECT();
                        OS.TreeView_GetItemRect(this.handle, j, rect2, false);
                        for (int n3 = (rect.top < rect2.top) ? 7 : 6; j != this.hAnchor; j = OS.SendMessage(this.handle, 4362, n3, j)) {
                            tvitem.hItem = j;
                            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                        }
                        int k = this.hAnchor;
                        OS.TreeView_GetItemRect(this.handle, sendMessage2, rect, false);
                        OS.TreeView_GetItemRect(this.handle, k, rect2, false);
                        tvitem.state = 2;
                        for (int n4 = (rect.top < rect2.top) ? 7 : 6; k != sendMessage2; k = OS.SendMessage(this.handle, 4362, n4, k)) {
                            tvitem.hItem = k;
                            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                        }
                        tvitem.hItem = sendMessage2;
                        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                        tvitem.mask = 20;
                        tvitem.hItem = sendMessage2;
                        OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                        final Event event = new Event();
                        event.item = this._getItem(sendMessage2, tvitem.lParam);
                        this.sendSelectionEvent(13, event, false);
                        return new LRESULT(callWindowProc);
                    }
                }
                if (OS.GetKeyState(17) < 0) {
                    final int sendMessage3 = OS.SendMessage(this.handle, 4362, 9, 0);
                    if (sendMessage3 != 0) {
                        final TVITEM tvitem2 = new TVITEM();
                        tvitem2.mask = 24;
                        tvitem2.stateMask = 2;
                        tvitem2.hItem = sendMessage3;
                        OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem2);
                        final boolean b3 = (tvitem2.state & 0x2) != 0x0;
                        int l = 0;
                        switch (n) {
                            case 38: {
                                l = OS.SendMessage(this.handle, 4362, 7, sendMessage3);
                                break;
                            }
                            case 40: {
                                l = OS.SendMessage(this.handle, 4362, 6, sendMessage3);
                                break;
                            }
                            case 36: {
                                l = OS.SendMessage(this.handle, 4362, 0, 0);
                                break;
                            }
                            case 33: {
                                l = OS.SendMessage(this.handle, 4362, 5, 0);
                                if (l == sendMessage3) {
                                    OS.SendMessage(this.handle, 277, 2, 0);
                                    l = OS.SendMessage(this.handle, 4362, 5, 0);
                                    break;
                                }
                                break;
                            }
                            case 34: {
                                final RECT rect3 = new RECT();
                                final RECT rect4 = new RECT();
                                OS.GetClientRect(this.handle, rect4);
                                l = OS.SendMessage(this.handle, 4362, 5, 0);
                                do {
                                    final int sendMessage4 = OS.SendMessage(this.handle, 4362, 6, l);
                                    if (sendMessage4 == 0) {
                                        break;
                                    }
                                    if (!OS.TreeView_GetItemRect(this.handle, sendMessage4, rect3, false)) {
                                        break;
                                    }
                                    if (rect3.bottom > rect4.bottom) {
                                        break;
                                    }
                                    if ((l = sendMessage4) != sendMessage3) {
                                        continue;
                                    }
                                    OS.SendMessage(this.handle, 277, 3, 0);
                                } while (l != 0);
                                break;
                            }
                            case 35: {
                                l = OS.SendMessage(this.handle, 4362, 10, 0);
                                break;
                            }
                        }
                        if (l != 0) {
                            OS.SendMessage(this.handle, 4372, 0, l);
                            tvitem2.hItem = l;
                            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem2);
                            final boolean b4 = (tvitem2.state & 0x2) != 0x0;
                            final boolean b5 = !b4 && this.getDrawing() && OS.IsWindowVisible(this.handle);
                            if (b5) {
                                OS.UpdateWindow(this.handle);
                                OS.DefWindowProc(this.handle, 11, 0, 0);
                            }
                            this.hSelect = l;
                            this.ignoreSelect = true;
                            OS.SendMessage(this.handle, 4363, 9, l);
                            this.ignoreSelect = false;
                            this.hSelect = 0;
                            if (b3) {
                                tvitem2.state = 2;
                                tvitem2.hItem = sendMessage3;
                                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem2);
                            }
                            if (!b4) {
                                tvitem2.state = 0;
                                tvitem2.hItem = l;
                                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem2);
                            }
                            if (b5) {
                                final RECT rect5 = new RECT();
                                final RECT rect6 = new RECT();
                                boolean b6 = (this.style & 0x10000) == 0x0;
                                if (this.hooks(40) || this.hooks(42)) {
                                    b6 = false;
                                }
                                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                                    b6 = false;
                                }
                                OS.TreeView_GetItemRect(this.handle, sendMessage3, rect5, b6);
                                OS.TreeView_GetItemRect(this.handle, l, rect6, b6);
                                OS.DefWindowProc(this.handle, 11, 1, 0);
                                OS.InvalidateRect(this.handle, rect5, true);
                                OS.InvalidateRect(this.handle, rect6, true);
                                OS.UpdateWindow(this.handle);
                            }
                            return LRESULT.ZERO;
                        }
                    }
                }
                final int callWindowProc2 = this.callWindowProc(this.handle, 256, n, n2);
                this.hAnchor = OS.SendMessage(this.handle, 4362, 9, 0);
                return new LRESULT(callWindowProc2);
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        int n3 = ((this.style & 0x2) != 0x0) ? 1 : 0;
        if (n3 == 0 && !OS.IsWinCE && OS.COMCTL32_MAJOR >= 6 && this.imageList != null && (OS.GetWindowLong(this.handle, -16) & 0x1000) == 0x0) {
            n3 = 1;
        }
        if (n3 != 0) {
            this.redrawSelection();
        }
        return super.WM_KILLFOCUS(n, n2);
    }
    
    LRESULT WM_LBUTTONDBLCLK(final int n, final int n2) {
        final TVHITTESTINFO tvhittestinfo = new TVHITTESTINFO();
        tvhittestinfo.x = OS.GET_X_LPARAM(n2);
        tvhittestinfo.y = OS.GET_Y_LPARAM(n2);
        OS.SendMessage(this.handle, 4369, 0, tvhittestinfo);
        if (tvhittestinfo.hItem != 0 && (this.style & 0x20) != 0x0 && (tvhittestinfo.flags & 0x40) != 0x0) {
            final Display display = this.display;
            display.captureChanged = false;
            this.sendMouseEvent(3, 1, this.handle, 513, n, n2);
            if (!this.sendMouseEvent(8, 1, this.handle, 515, n, n2)) {
                if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return LRESULT.ZERO;
            }
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            OS.SetFocus(this.handle);
            final TVITEM tvitem = new TVITEM();
            tvitem.hItem = tvhittestinfo.hItem;
            tvitem.mask = 28;
            tvitem.stateMask = 61440;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
            int n3 = tvitem.state >> 12;
            if ((n3 & 0x1) != 0x0) {
                ++n3;
            }
            else {
                --n3;
            }
            tvitem.state = n3 << 12;
            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
            if (!OS.IsWinCE) {
                int n4 = tvitem.hItem;
                if (OS.COMCTL32_MAJOR >= 6) {
                    n4 = OS.SendMessage(this.handle, 4395, tvitem.hItem, 0);
                }
                OS.NotifyWinEvent(32773, this.handle, -4, n4);
            }
            final Event event = new Event();
            event.item = this._getItem(tvitem.hItem, tvitem.lParam);
            event.detail = 32;
            this.sendSelectionEvent(13, event, false);
            return LRESULT.ZERO;
        }
        else {
            final LRESULT wm_LBUTTONDBLCLK = super.WM_LBUTTONDBLCLK(n, n2);
            if (wm_LBUTTONDBLCLK == LRESULT.ZERO) {
                return wm_LBUTTONDBLCLK;
            }
            if (tvhittestinfo.hItem != 0) {
                int n5 = 70;
                if ((this.style & 0x10000) != 0x0) {
                    n5 |= 0x28;
                }
                else if (this.hooks(41)) {
                    final TVHITTESTINFO tvhittestinfo2 = tvhittestinfo;
                    tvhittestinfo2.flags &= 0xFFFFFFF9;
                    if (this.hitTestSelection(tvhittestinfo.hItem, tvhittestinfo.x, tvhittestinfo.y)) {
                        final TVHITTESTINFO tvhittestinfo3 = tvhittestinfo;
                        tvhittestinfo3.flags |= 0x6;
                    }
                }
                if ((tvhittestinfo.flags & n5) != 0x0) {
                    final Event event2 = new Event();
                    event2.item = this._getItem(tvhittestinfo.hItem);
                    this.sendSelectionEvent(14, event2, false);
                }
            }
            return wm_LBUTTONDBLCLK;
        }
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final TVHITTESTINFO tvhittestinfo = new TVHITTESTINFO();
        tvhittestinfo.x = OS.GET_X_LPARAM(n2);
        tvhittestinfo.y = OS.GET_Y_LPARAM(n2);
        OS.SendMessage(this.handle, 4369, 0, tvhittestinfo);
        if (tvhittestinfo.hItem == 0 || (tvhittestinfo.flags & 0x10) != 0x0) {
            final Display display = this.display;
            display.captureChanged = false;
            if (!this.sendMouseEvent(3, 1, this.handle, 513, n, n2)) {
                if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return LRESULT.ZERO;
            }
            boolean b = false;
            boolean b2 = false;
            final int sendMessage = OS.SendMessage(this.handle, 4362, 9, 0);
            if (tvhittestinfo.hItem != 0 && (this.style & 0x2) != 0x0 && sendMessage != 0) {
                final TVITEM tvitem = new TVITEM();
                tvitem.mask = 24;
                tvitem.hItem = tvhittestinfo.hItem;
                OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                if ((tvitem.state & 0x20) != 0x0) {
                    b = true;
                    tvitem.stateMask = 2;
                    int i = OS.SendMessage(this.handle, 4362, 6, tvhittestinfo.hItem);
                    while (i != 0) {
                        if (i == this.hAnchor) {
                            this.hAnchor = 0;
                        }
                        tvitem.hItem = i;
                        OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                        if ((tvitem.state & 0x2) != 0x0) {
                            b2 = true;
                        }
                        tvitem.state = 0;
                        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem);
                        int n3;
                        for (i = (n3 = OS.SendMessage(this.handle, 4362, 6, i)); n3 != 0 && n3 != tvhittestinfo.hItem; n3 = OS.SendMessage(this.handle, 4362, 3, n3)) {}
                        if (n3 == 0) {
                            break;
                        }
                    }
                }
            }
            final boolean b3 = false;
            this.gestureCompleted = b3;
            this.dragStarted = b3;
            if (b) {
                final boolean ignoreDeselect = true;
                this.lockSelection = ignoreDeselect;
                this.ignoreSelect = ignoreDeselect;
                this.ignoreDeselect = ignoreDeselect;
            }
            final int callWindowProc = this.callWindowProc(this.handle, 513, n, n2);
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.GetFocus() != this.handle) {
                OS.SetFocus(this.handle);
            }
            if (b) {
                final boolean ignoreDeselect2 = false;
                this.lockSelection = ignoreDeselect2;
                this.ignoreSelect = ignoreDeselect2;
                this.ignoreDeselect = ignoreDeselect2;
            }
            final int sendMessage2 = OS.SendMessage(this.handle, 4362, 9, 0);
            if (sendMessage != sendMessage2) {
                this.hAnchor = sendMessage2;
            }
            if (this.dragStarted && !display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            if ((tvhittestinfo.flags & 0x10) != 0x0 && (OS.GetWindowLong(this.handle, -16) & 0x1000) == 0x0 && OS.SendMessage(this.handle, 4360, 0, 0) == 0) {
                final int sendMessage3 = OS.SendMessage(this.handle, 4362, 9, 0);
                if (sendMessage3 != 0) {
                    final RECT rect = new RECT();
                    if (OS.TreeView_GetItemRect(this.handle, sendMessage3, rect, false)) {
                        OS.InvalidateRect(this.handle, rect, true);
                    }
                }
            }
            if (b2) {
                final Event event = new Event();
                event.item = this._getItem(tvhittestinfo.hItem);
                this.sendSelectionEvent(13, event, false);
            }
            return new LRESULT(callWindowProc);
        }
        else if ((this.style & 0x20) != 0x0 && (tvhittestinfo.flags & 0x40) != 0x0) {
            final Display display2 = this.display;
            display2.captureChanged = false;
            if (!this.sendMouseEvent(3, 1, this.handle, 513, n, n2)) {
                if (!display2.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return LRESULT.ZERO;
            }
            if (!display2.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            OS.SetFocus(this.handle);
            final TVITEM tvitem2 = new TVITEM();
            tvitem2.hItem = tvhittestinfo.hItem;
            tvitem2.mask = 28;
            tvitem2.stateMask = 61440;
            OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem2);
            int n4 = tvitem2.state >> 12;
            if ((n4 & 0x1) != 0x0) {
                ++n4;
            }
            else {
                --n4;
            }
            tvitem2.state = n4 << 12;
            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem2);
            if (!OS.IsWinCE) {
                int n5 = tvitem2.hItem;
                if (OS.COMCTL32_MAJOR >= 6) {
                    n5 = OS.SendMessage(this.handle, 4395, tvitem2.hItem, 0);
                }
                OS.NotifyWinEvent(32773, this.handle, -4, n5);
            }
            final Event event2 = new Event();
            event2.item = this._getItem(tvitem2.hItem, tvitem2.lParam);
            event2.detail = 32;
            this.sendSelectionEvent(13, event2, false);
            return LRESULT.ZERO;
        }
        else {
            boolean hitTestSelection = false;
            boolean b4 = false;
            if (tvhittestinfo.hItem != 0) {
                if ((this.style & 0x10000) != 0x0) {
                    if ((OS.GetWindowLong(this.handle, -16) & 0x1000) == 0x0) {
                        b4 = true;
                    }
                }
                else if (this.hooks(41)) {
                    hitTestSelection = this.hitTestSelection(tvhittestinfo.hItem, tvhittestinfo.x, tvhittestinfo.y);
                    if (hitTestSelection && (tvhittestinfo.flags & 0x46) == 0x0) {
                        b4 = true;
                    }
                }
            }
            if (!hitTestSelection && (this.style & 0x10000) == 0x0 && (tvhittestinfo.flags & 0x46) == 0x0) {
                final Display display3 = this.display;
                display3.captureChanged = false;
                if (!this.sendMouseEvent(3, 1, this.handle, 513, n, n2)) {
                    if (!display3.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                        OS.SetCapture(this.handle);
                    }
                    return LRESULT.ZERO;
                }
                final int callWindowProc2 = this.callWindowProc(this.handle, 513, n, n2);
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.GetFocus() != this.handle) {
                    OS.SetFocus(this.handle);
                }
                if (!display3.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                return new LRESULT(callWindowProc2);
            }
            else {
                final TVITEM tvitem3 = new TVITEM();
                tvitem3.mask = 24;
                tvitem3.stateMask = 2;
                boolean b5 = false;
                if ((this.style & 0x2) != 0x0) {
                    tvitem3.hItem = tvhittestinfo.hItem;
                    OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem3);
                    b5 = ((tvitem3.state & 0x2) != 0x0);
                }
                final int sendMessage4 = OS.SendMessage(this.handle, 4362, 9, 0);
                if ((this.style & 0x2) != 0x0) {
                    tvitem3.hItem = sendMessage4;
                    OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem3);
                    if (b5 || (n & 0x8) != 0x0) {
                        if ((OS.SendMessage(this.handle, 297, 0, 0) & 0x1) != 0x0) {
                            OS.SendMessage(this.handle, 295, 3, 0);
                        }
                        OS.UpdateWindow(this.handle);
                        OS.DefWindowProc(this.handle, 11, 0, 0);
                    }
                    else {
                        this.deselectAll();
                    }
                }
                final Display display4 = this.display;
                display4.captureChanged = false;
                if (!this.sendMouseEvent(3, 1, this.handle, 513, n, n2)) {
                    if (!display4.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                        OS.SetCapture(this.handle);
                    }
                    return LRESULT.ZERO;
                }
                this.hSelect = tvhittestinfo.hItem;
                final boolean b6 = false;
                this.gestureCompleted = b6;
                this.dragStarted = b6;
                final boolean b7 = true;
                this.ignoreSelect = b7;
                this.ignoreDeselect = b7;
                final int callWindowProc3 = this.callWindowProc(this.handle, 513, n, n2);
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.GetFocus() != this.handle) {
                    OS.SetFocus(this.handle);
                }
                int hItem = OS.SendMessage(this.handle, 4362, 9, 0);
                if (b4) {
                    if (sendMessage4 == 0 || (hItem == sendMessage4 && tvhittestinfo.hItem != sendMessage4)) {
                        OS.SendMessage(this.handle, 4363, 9, tvhittestinfo.hItem);
                        hItem = OS.SendMessage(this.handle, 4362, 9, 0);
                    }
                    if (!this.dragStarted && (this.state & 0x8000) != 0x0 && this.hooks(29)) {
                        this.dragStarted = this.dragDetect(this.handle, tvhittestinfo.x, tvhittestinfo.y, false, null, null);
                    }
                }
                final boolean b8 = false;
                this.ignoreSelect = b8;
                this.ignoreDeselect = b8;
                this.hSelect = 0;
                if (this.dragStarted && !display4.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                    OS.SetCapture(this.handle);
                }
                if ((this.style & 0x4) != 0x0 && sendMessage4 == hItem) {
                    tvitem3.mask = 24;
                    tvitem3.state = 2;
                    tvitem3.stateMask = 2;
                    tvitem3.hItem = hItem;
                    OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                }
                if ((this.style & 0x2) != 0x0) {
                    if (b5 || (n & 0x8) != 0x0) {
                        if (sendMessage4 == hItem && sendMessage4 == tvhittestinfo.hItem) {
                            if ((n & 0x8) != 0x0) {
                                final TVITEM tvitem4 = tvitem3;
                                tvitem4.state ^= 0x2;
                                if (this.dragStarted) {
                                    tvitem3.state = 2;
                                }
                                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                            }
                        }
                        else {
                            if ((tvitem3.state & 0x2) != 0x0) {
                                tvitem3.state = 2;
                                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                            }
                            if ((n & 0x8) != 0x0 && !this.dragStarted && b5) {
                                tvitem3.state = 0;
                                tvitem3.hItem = tvhittestinfo.hItem;
                                OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                            }
                        }
                        final RECT rect2 = new RECT();
                        final RECT rect3 = new RECT();
                        boolean b9 = (this.style & 0x10000) == 0x0;
                        if (this.hooks(40) || this.hooks(42)) {
                            b9 = false;
                        }
                        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                            b9 = false;
                        }
                        OS.TreeView_GetItemRect(this.handle, sendMessage4, rect2, b9);
                        OS.TreeView_GetItemRect(this.handle, hItem, rect3, b9);
                        OS.DefWindowProc(this.handle, 11, 1, 0);
                        OS.InvalidateRect(this.handle, rect2, true);
                        OS.InvalidateRect(this.handle, rect3, true);
                        OS.UpdateWindow(this.handle);
                    }
                    if ((n & 0x8) == 0x0 && (!b5 || !this.dragStarted)) {
                        tvitem3.state = 0;
                        final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
                        OS.SetWindowLongPtr(this.handle, -4, Tree.TreeProc);
                        if ((this.style & 0x10000000) != 0x0) {
                            this.deselect(OS.SendMessage(this.handle, 4362, 0, 0), tvitem3, hItem);
                        }
                        else {
                            for (int j = 0; j < this.items.length; ++j) {
                                final TreeItem treeItem = this.items[j];
                                if (treeItem != null && treeItem.handle != hItem) {
                                    tvitem3.hItem = treeItem.handle;
                                    OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                                }
                            }
                        }
                        tvitem3.hItem = hItem;
                        tvitem3.state = 2;
                        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                        OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
                        if ((n & 0x4) != 0x0) {
                            final RECT rect4 = new RECT();
                            if (this.hAnchor == 0) {
                                this.hAnchor = hItem;
                            }
                            if (OS.TreeView_GetItemRect(this.handle, this.hAnchor, rect4, false)) {
                                final RECT rect5 = new RECT();
                                if (OS.TreeView_GetItemRect(this.handle, hItem, rect5, false)) {
                                    final int n6 = (rect4.top < rect5.top) ? 6 : 7;
                                    tvitem3.state = 2;
                                    final TVITEM tvitem5 = tvitem3;
                                    final int hAnchor = this.hAnchor;
                                    tvitem5.hItem = hAnchor;
                                    int k = hAnchor;
                                    OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                                    while (k != hItem) {
                                        tvitem3.hItem = k;
                                        OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, tvitem3);
                                        k = OS.SendMessage(this.handle, 4362, n6, k);
                                    }
                                }
                            }
                        }
                    }
                }
                if ((n & 0x4) == 0x0) {
                    this.hAnchor = hItem;
                }
                if (!this.gestureCompleted) {
                    tvitem3.hItem = hItem;
                    tvitem3.mask = 20;
                    OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem3);
                    final Event event3 = new Event();
                    event3.item = this._getItem(tvitem3.hItem, tvitem3.lParam);
                    this.sendSelectionEvent(13, event3, false);
                }
                this.gestureCompleted = false;
                if (this.dragStarted) {
                    this.sendDragEvent(1, OS.GET_X_LPARAM(n2), OS.GET_Y_LPARAM(n2));
                }
                else if ((OS.GetWindowLong(this.handle, -16) & 0x10) == 0x0) {
                    this.sendMouseEvent(4, 1, this.handle, 514, n, n2);
                }
                this.dragStarted = false;
                return new LRESULT(callWindowProc3);
            }
        }
    }
    
    LRESULT WM_MOUSEMOVE(final int n, final int n2) {
        final Display display = this.display;
        final LRESULT wm_MOUSEMOVE = super.WM_MOUSEMOVE(n, n2);
        if (wm_MOUSEMOVE != null) {
            return wm_MOUSEMOVE;
        }
        if (this.itemToolTipHandle != 0) {
            int n3 = 19;
            if (display.xMouse) {
                n3 |= 0x60;
            }
            if ((n & n3) == 0x0) {
                final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
                final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
                final int[] array = { 0 };
                final TreeItem[] array2 = { null };
                final RECT[] array3 = { null };
                if (this.findCell(get_X_LPARAM, get_Y_LPARAM, array2, array, array3, new RECT[1])) {
                    if (OS.SendMessage(this.itemToolTipHandle, OS.TTM_GETCURRENTTOOL, 0, 0) == 0 && OS.IsWindowVisible(this.itemToolTipHandle)) {
                        OS.ShowWindow(this.itemToolTipHandle, 0);
                    }
                    final TOOLINFO toolinfo = new TOOLINFO();
                    toolinfo.cbSize = TOOLINFO.sizeof;
                    toolinfo.hwnd = this.handle;
                    toolinfo.uId = this.handle;
                    toolinfo.uFlags = 272;
                    toolinfo.left = array3[0].left;
                    toolinfo.top = array3[0].top;
                    toolinfo.right = array3[0].right;
                    toolinfo.bottom = array3[0].bottom;
                    OS.SendMessage(this.itemToolTipHandle, OS.TTM_NEWTOOLRECT, 0, toolinfo);
                }
            }
        }
        return wm_MOUSEMOVE;
    }
    
    LRESULT WM_MOUSEWHEEL(final int n, final int n2) {
        final LRESULT wm_MOUSEWHEEL = super.WM_MOUSEWHEEL(n, n2);
        if (this.itemToolTipHandle != 0) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        return wm_MOUSEWHEEL;
    }
    
    LRESULT WM_MOVE(final int n, final int n2) {
        if (this.itemToolTipHandle != 0) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        if (this.ignoreResize) {
            return null;
        }
        return super.WM_MOVE(n, n2);
    }
    
    LRESULT WM_RBUTTONDOWN(final int n, final int n2) {
        final Display display = this.display;
        display.captureChanged = false;
        if (!this.sendMouseEvent(3, 3, this.handle, 516, n, n2)) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return LRESULT.ZERO;
        }
        if (OS.GetFocus() != this.handle) {
            OS.SetFocus(this.handle);
        }
        final TVHITTESTINFO tvhittestinfo = new TVHITTESTINFO();
        tvhittestinfo.x = OS.GET_X_LPARAM(n2);
        tvhittestinfo.y = OS.GET_Y_LPARAM(n2);
        OS.SendMessage(this.handle, 4369, 0, tvhittestinfo);
        if (tvhittestinfo.hItem != 0) {
            boolean hitTestSelection = (this.style & 0x10000) != 0x0;
            if (!hitTestSelection) {
                if (this.hooks(41)) {
                    hitTestSelection = this.hitTestSelection(tvhittestinfo.hItem, tvhittestinfo.x, tvhittestinfo.y);
                }
                else {
                    hitTestSelection = ((tvhittestinfo.flags & 0x6) != 0x0);
                }
            }
            if (hitTestSelection && (n & 0xC) == 0x0) {
                final TVITEM tvitem = new TVITEM();
                tvitem.mask = 24;
                tvitem.stateMask = 2;
                tvitem.hItem = tvhittestinfo.hItem;
                OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                if ((tvitem.state & 0x2) == 0x0) {
                    this.ignoreSelect = true;
                    OS.SendMessage(this.handle, 4363, 9, 0);
                    this.ignoreSelect = false;
                    OS.SendMessage(this.handle, 4363, 9, tvhittestinfo.hItem);
                }
            }
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        if (this.shrink && !this.ignoreShrink) {
            int n3;
            for (n3 = this.items.length - 1; n3 >= 0 && this.items[n3] == null; --n3) {}
            ++n3;
            if (this.items.length > 4 && this.items.length - n3 > 3) {
                final TreeItem[] items = new TreeItem[Math.max(4, (n3 + 3) / 4 * 4)];
                System.arraycopy(this.items, 0, items, 0, n3);
                this.items = items;
            }
            this.shrink = false;
        }
        if ((this.style & 0x20000000) != 0x0 || this.findImageControl() != null) {
            boolean b = true;
            if (this.explorerTheme && (OS.SendMessage(this.handle, 4397, 0, 0) & 0x4) != 0x0) {
                b = false;
            }
            if (b) {
                GC win32_new = null;
                final PAINTSTRUCT ps = new PAINTSTRUCT();
                final boolean b2 = this.hooks(9) || this.filters(9);
                int n4;
                if (b2) {
                    final GCData gcData = new GCData();
                    gcData.ps = ps;
                    gcData.hwnd = this.handle;
                    win32_new = GC.win32_new(this, gcData);
                    n4 = win32_new.handle;
                }
                else {
                    n4 = OS.BeginPaint(this.handle, ps);
                }
                final int n5 = ps.right - ps.left;
                final int n6 = ps.bottom - ps.top;
                if (n5 != 0 && n6 != 0) {
                    final int createCompatibleDC = OS.CreateCompatibleDC(n4);
                    final POINT point = new POINT();
                    final POINT point2 = new POINT();
                    OS.SetWindowOrgEx(createCompatibleDC, ps.left, ps.top, point);
                    OS.SetBrushOrgEx(createCompatibleDC, ps.left, ps.top, point2);
                    final int createCompatibleBitmap = OS.CreateCompatibleBitmap(n4, n5, n6);
                    final int selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
                    final RECT rect = new RECT();
                    OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                    this.drawBackground(createCompatibleDC, rect);
                    this.callWindowProc(this.handle, 15, createCompatibleDC, 0);
                    OS.SetWindowOrgEx(createCompatibleDC, point.x, point.y, null);
                    OS.SetBrushOrgEx(createCompatibleDC, point2.x, point2.y, null);
                    OS.BitBlt(n4, ps.left, ps.top, n5, n6, createCompatibleDC, 0, 0, 13369376);
                    OS.SelectObject(createCompatibleDC, selectObject);
                    OS.DeleteObject(createCompatibleBitmap);
                    OS.DeleteObject(createCompatibleDC);
                    if (b2) {
                        final Event event = new Event();
                        event.gc = win32_new;
                        event.x = ps.left;
                        event.y = ps.top;
                        event.width = ps.right - ps.left;
                        event.height = ps.bottom - ps.top;
                        this.sendEvent(9, event);
                        event.gc = null;
                    }
                }
                if (b2) {
                    win32_new.dispose();
                }
                else {
                    OS.EndPaint(this.handle, ps);
                }
                return LRESULT.ZERO;
            }
        }
        return super.WM_PAINT(n, n2);
    }
    
    LRESULT WM_PRINTCLIENT(final int n, final int n2) {
        final LRESULT wm_PRINTCLIENT = super.WM_PRINTCLIENT(n, n2);
        if (wm_PRINTCLIENT != null) {
            return wm_PRINTCLIENT;
        }
        this.printClient = true;
        final int callWindowProc = this.callWindowProc(this.handle, 792, n, n2);
        this.printClient = false;
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_SETCURSOR(final int n, final int n2) {
        final LRESULT wm_SETCURSOR = super.WM_SETCURSOR(n, n2);
        if (wm_SETCURSOR != null) {
            return wm_SETCURSOR;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 1) && n == this.handle && (short)OS.LOWORD(n2) == 1) {
            OS.SetCursor(OS.LoadCursor(0, 32512));
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        int n3 = ((this.style & 0x2) != 0x0) ? 1 : 0;
        if (n3 == 0 && !OS.IsWinCE && OS.COMCTL32_MAJOR >= 6 && this.imageList != null && (OS.GetWindowLong(this.handle, -16) & 0x1000) == 0x0) {
            n3 = 1;
        }
        if (n3 != 0) {
            this.redrawSelection();
        }
        return super.WM_SETFOCUS(n, n2);
    }
    
    LRESULT WM_SETFONT(final int n, final int n2) {
        final LRESULT wm_SETFONT = super.WM_SETFONT(n, n2);
        if (wm_SETFONT != null) {
            return wm_SETFONT;
        }
        if (this.hwndHeader != 0) {
            OS.SendMessage(this.hwndHeader, 48, 0, n2);
            OS.SendMessage(this.hwndHeader, 48, n, n2);
        }
        if (this.itemToolTipHandle != 0) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
            OS.SendMessage(this.itemToolTipHandle, 48, n, n2);
        }
        if (this.headerToolTipHandle != 0) {
            OS.SendMessage(this.headerToolTipHandle, 48, n, n2);
            this.updateHeaderToolTips();
        }
        return wm_SETFONT;
    }
    
    LRESULT WM_SETREDRAW(final int n, final int n2) {
        final LRESULT wm_SETREDRAW = super.WM_SETREDRAW(n, n2);
        if (wm_SETREDRAW != null) {
            return wm_SETREDRAW;
        }
        if (this.itemToolTipHandle != 0) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            final int defWindowProc = OS.DefWindowProc(this.handle, 11, n, n2);
            return (defWindowProc == 0) ? LRESULT.ZERO : new LRESULT(defWindowProc);
        }
        return wm_SETREDRAW;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        if (this.itemToolTipHandle != 0) {
            OS.ShowWindow(this.itemToolTipHandle, 0);
        }
        if ((OS.GetWindowLong(this.handle, -16) & 0x8000) != 0x0 && !OS.IsWinCE) {
            OS.ShowScrollBar(this.handle, 0, false);
        }
        if (this.explorerTheme && (this.style & 0x10000) != 0x0) {
            OS.InvalidateRect(this.handle, null, false);
        }
        if (this.ignoreResize) {
            return null;
        }
        return super.WM_SIZE(n, n2);
    }
    
    LRESULT WM_SYSCOLORCHANGE(final int n, final int n2) {
        final LRESULT wm_SYSCOLORCHANGE = super.WM_SYSCOLORCHANGE(n, n2);
        if (wm_SYSCOLORCHANGE != null) {
            return wm_SYSCOLORCHANGE;
        }
        if (this.explorerTheme && this.foreground == -1) {
            this.setForegroundPixel(-1);
        }
        if ((this.style & 0x20) != 0x0) {
            this.setCheckboxImageList();
        }
        return wm_SYSCOLORCHANGE;
    }
    
    LRESULT WM_VSCROLL(final int n, final int n2) {
        boolean b = false;
        if ((this.style & 0x20000000) != 0x0) {
            switch (OS.LOWORD(n)) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 6:
                case 7: {
                    b = ((this.style & 0x10000000) != 0x0 || this.hooks(40) || this.hooks(42));
                    break;
                }
            }
        }
        if (b) {
            this.style &= 0xDFFFFFFF;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4, 0);
            }
        }
        final LRESULT wm_VSCROLL = super.WM_VSCROLL(n, n2);
        if (b) {
            this.style |= 0x20000000;
            if (this.explorerTheme) {
                OS.SendMessage(this.handle, 4396, 4, 4);
            }
        }
        if (wm_VSCROLL != null) {
            return wm_VSCROLL;
        }
        return wm_VSCROLL;
    }
    
    LRESULT WM_TIMER(final int lastTimerID, final int n) {
        final LRESULT wm_TIMER = super.WM_TIMER(lastTimerID, n);
        if (wm_TIMER != null) {
            return wm_TIMER;
        }
        if ((OS.SendMessage(this.handle, 4397, 0, 0) & 0x40) != 0x0) {
            if (!OS.IsWindowVisible(this.handle)) {
                if (this.lastTimerID == lastTimerID) {
                    ++this.lastTimerCount;
                }
                else {
                    this.lastTimerCount = 0;
                }
                this.lastTimerID = lastTimerID;
                if (this.lastTimerCount >= 8) {
                    OS.CallWindowProc(Tree.TreeProc, this.handle, 512, 0, 0);
                    this.lastTimerID = -1;
                    this.lastTimerCount = 0;
                }
            }
            else {
                this.lastTimerID = -1;
                this.lastTimerCount = 0;
            }
        }
        return wm_TIMER;
    }
    
    LRESULT wmColorChild(final int n, final int n2) {
        if (this.findImageControl() == null) {
            return null;
        }
        if (OS.COMCTL32_MAJOR < 6) {
            return super.wmColorChild(n, n2);
        }
        return new LRESULT(OS.GetStockObject(5));
    }
    
    LRESULT wmNotify(final NMHDR nmhdr, final int n, final int n2) {
        if (nmhdr.hwndFrom == this.itemToolTipHandle) {
            final LRESULT wmNotifyToolTip = this.wmNotifyToolTip(nmhdr, n, n2);
            if (wmNotifyToolTip != null) {
                return wmNotifyToolTip;
            }
        }
        if (nmhdr.hwndFrom == this.hwndHeader) {
            final LRESULT wmNotifyHeader = this.wmNotifyHeader(nmhdr, n, n2);
            if (wmNotifyHeader != null) {
                return wmNotifyHeader;
            }
        }
        return super.wmNotify(nmhdr, n, n2);
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        Label_2381: {
            switch (nmhdr.code) {
                case -452:
                case -403: {
                    final NMTVDISPINFO nmtvdispinfo = new NMTVDISPINFO();
                    OS.MoveMemory(nmtvdispinfo, n2, NMTVDISPINFO.sizeof);
                    if ((this.style & 0x10000000) != 0x0) {
                        boolean b = true;
                        if (!this.ignoreShrink && this.items != null && nmtvdispinfo.lParam != -1 && this.items[nmtvdispinfo.lParam] != null && this.items[nmtvdispinfo.lParam].cached) {
                            b = false;
                        }
                        if (b) {
                            if (!this.getDrawing()) {
                                break;
                            }
                            if (!OS.IsWindowVisible(this.handle)) {
                                break;
                            }
                            final RECT rect = new RECT();
                            if (!OS.TreeView_GetItemRect(this.handle, nmtvdispinfo.hItem, rect, false)) {
                                break;
                            }
                            final RECT rect2 = new RECT();
                            OS.GetClientRect(this.handle, rect2);
                            if (!OS.IntersectRect(rect2, rect2, rect)) {
                                break;
                            }
                            if (this.ignoreShrink) {
                                OS.InvalidateRect(this.handle, rect2, true);
                                break;
                            }
                        }
                    }
                    if (this.items == null) {
                        break;
                    }
                    int n3 = nmtvdispinfo.lParam;
                    if ((this.style & 0x10000000) != 0x0 && n3 == -1) {
                        final TVITEM tvitem = new TVITEM();
                        tvitem.mask = 20;
                        tvitem.hItem = nmtvdispinfo.hItem;
                        OS.SendMessage(this.handle, OS.TVM_GETITEM, 0, tvitem);
                        n3 = tvitem.lParam;
                    }
                    final TreeItem getItem = this._getItem(nmtvdispinfo.hItem, n3);
                    if (getItem == null) {
                        break;
                    }
                    if (getItem.isDisposed()) {
                        break;
                    }
                    if (!getItem.cached) {
                        if ((this.style & 0x10000000) != 0x0 && !this.checkData(getItem, false)) {
                            break;
                        }
                        if (this.painted) {
                            getItem.cached = true;
                        }
                    }
                    int sendMessage = 0;
                    if (this.hwndHeader != 0) {
                        sendMessage = OS.SendMessage(this.hwndHeader, 4623, 0, 0);
                    }
                    if ((nmtvdispinfo.mask & 0x1) != 0x0) {
                        String text = null;
                        if (sendMessage == 0) {
                            text = getItem.text;
                        }
                        else {
                            final String[] strings = getItem.strings;
                            if (strings != null) {
                                text = strings[sendMessage];
                            }
                        }
                        if (text != null) {
                            final TCHAR tchar = new TCHAR(this.getCodePage(), text, false);
                            final int n4 = Math.min(tchar.length(), nmtvdispinfo.cchTextMax - 1) * TCHAR.sizeof;
                            OS.MoveMemory(nmtvdispinfo.pszText, tchar, n4);
                            OS.MoveMemory(nmtvdispinfo.pszText + n4, new byte[TCHAR.sizeof], TCHAR.sizeof);
                            nmtvdispinfo.cchTextMax = Math.min(nmtvdispinfo.cchTextMax, text.length() + 1);
                        }
                    }
                    if ((nmtvdispinfo.mask & 0x22) != 0x0) {
                        Image image = null;
                        if (sendMessage == 0) {
                            image = getItem.image;
                        }
                        else {
                            final Image[] images = getItem.images;
                            if (images != null) {
                                image = images[sendMessage];
                            }
                        }
                        final NMTVDISPINFO nmtvdispinfo2 = nmtvdispinfo;
                        final NMTVDISPINFO nmtvdispinfo3 = nmtvdispinfo;
                        final int n5 = -2;
                        nmtvdispinfo3.iSelectedImage = n5;
                        nmtvdispinfo2.iImage = n5;
                        if (image != null) {
                            final NMTVDISPINFO nmtvdispinfo4 = nmtvdispinfo;
                            final NMTVDISPINFO nmtvdispinfo5 = nmtvdispinfo;
                            final int imageIndex = this.imageIndex(image, sendMessage);
                            nmtvdispinfo5.iSelectedImage = imageIndex;
                            nmtvdispinfo4.iImage = imageIndex;
                        }
                        if (this.explorerTheme && OS.IsWindowEnabled(this.handle) && this.findImageControl() != null) {
                            final NMTVDISPINFO nmtvdispinfo6 = nmtvdispinfo;
                            final NMTVDISPINFO nmtvdispinfo7 = nmtvdispinfo;
                            final int n6 = -2;
                            nmtvdispinfo7.iSelectedImage = n6;
                            nmtvdispinfo6.iImage = n6;
                        }
                    }
                    OS.MoveMemory(n2, nmtvdispinfo, NMTVDISPINFO.sizeof);
                    break;
                }
                case -12: {
                    if (nmhdr.hwndFrom == this.hwndHeader) {
                        break;
                    }
                    if (this.hooks(41) && this.hwndHeader == 0) {
                        this.createParent();
                    }
                    if (!this.customDraw && this.findImageControl() == null && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
                        if (this.sortColumn == null) {
                            break;
                        }
                        if (this.sortDirection == 0) {
                            break;
                        }
                    }
                    final NMTVCUSTOMDRAW nmtvcustomdraw = new NMTVCUSTOMDRAW();
                    OS.MoveMemory(nmtvcustomdraw, n2, NMTVCUSTOMDRAW.sizeof);
                    switch (nmtvcustomdraw.dwDrawStage) {
                        case 1: {
                            return this.CDDS_PREPAINT(nmtvcustomdraw, n, n2);
                        }
                        case 65537: {
                            return this.CDDS_ITEMPREPAINT(nmtvcustomdraw, n, n2);
                        }
                        case 65538: {
                            return this.CDDS_ITEMPOSTPAINT(nmtvcustomdraw, n, n2);
                        }
                        case 2: {
                            return this.CDDS_POSTPAINT(nmtvcustomdraw, n, n2);
                        }
                        default: {
                            break Label_2381;
                        }
                    }
                    break;
                }
                case -3: {
                    if (this.hooks(41)) {
                        return LRESULT.ONE;
                    }
                    if (!this.hooks(14)) {
                        break;
                    }
                    final POINT point = new POINT();
                    OS.POINTSTOPOINT(point, OS.GetMessagePos());
                    OS.ScreenToClient(this.handle, point);
                    final TVHITTESTINFO tvhittestinfo = new TVHITTESTINFO();
                    tvhittestinfo.x = point.x;
                    tvhittestinfo.y = point.y;
                    OS.SendMessage(this.handle, 4369, 0, tvhittestinfo);
                    if (tvhittestinfo.hItem != 0 && (tvhittestinfo.flags & 0x46) != 0x0) {
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -417:
                case -416: {
                    if (OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(6, 0) || (this.style & 0x2) == 0x0 || this.hSelect == 0) {
                        break;
                    }
                    final NMTVITEMCHANGE nmtvitemchange = new NMTVITEMCHANGE();
                    OS.MoveMemory(nmtvitemchange, n2, NMTVITEMCHANGE.sizeof);
                    if (this.hSelect == nmtvitemchange.hItem) {
                        break;
                    }
                    return LRESULT.ONE;
                }
                case -450:
                case -401: {
                    if ((this.style & 0x2) != 0x0 && this.lockSelection) {
                        final NMTREEVIEW nmtreeview = new NMTREEVIEW();
                        OS.MoveMemory(nmtreeview, n2, NMTREEVIEW.sizeof);
                        this.oldSelected = ((nmtreeview.itemOld.state & 0x2) != 0x0);
                        this.newSelected = ((nmtreeview.itemNew.state & 0x2) != 0x0);
                    }
                    if (this.ignoreSelect || this.ignoreDeselect) {
                        break;
                    }
                    this.hAnchor = 0;
                    if ((this.style & 0x2) != 0x0) {
                        this.deselectAll();
                        break;
                    }
                    break;
                }
                case -451:
                case -402: {
                    NMTREEVIEW nmtreeview2 = null;
                    if ((this.style & 0x2) != 0x0 && this.lockSelection) {
                        if (this.oldSelected) {
                            nmtreeview2 = new NMTREEVIEW();
                            OS.MoveMemory(nmtreeview2, n2, NMTREEVIEW.sizeof);
                            final TVITEM itemOld = nmtreeview2.itemOld;
                            itemOld.mask = 8;
                            itemOld.stateMask = 2;
                            itemOld.state = 2;
                            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, itemOld);
                        }
                        if (!this.newSelected && this.ignoreSelect) {
                            if (nmtreeview2 == null) {
                                nmtreeview2 = new NMTREEVIEW();
                                OS.MoveMemory(nmtreeview2, n2, NMTREEVIEW.sizeof);
                            }
                            final TVITEM itemNew = nmtreeview2.itemNew;
                            itemNew.mask = 8;
                            itemNew.stateMask = 2;
                            itemNew.state = 0;
                            OS.SendMessage(this.handle, OS.TVM_SETITEM, 0, itemNew);
                        }
                    }
                    if (!this.ignoreSelect) {
                        if (nmtreeview2 == null) {
                            nmtreeview2 = new NMTREEVIEW();
                            OS.MoveMemory(nmtreeview2, n2, NMTREEVIEW.sizeof);
                        }
                        final TVITEM itemNew2 = nmtreeview2.itemNew;
                        this.hAnchor = itemNew2.hItem;
                        final Event event = new Event();
                        event.item = this._getItem(itemNew2.hItem, itemNew2.lParam);
                        this.sendSelectionEvent(13, event, false);
                    }
                    this.updateScrollBar();
                    break;
                }
                case -454:
                case -405: {
                    if (this.itemToolTipHandle != 0) {
                        OS.ShowWindow(this.itemToolTipHandle, 0);
                    }
                    int n7 = 0;
                    if ((this.style & 0x10000000) != 0x0) {
                        this.style &= 0xDFFFFFFF;
                    }
                    if (this.hooks(40) || this.hooks(42)) {
                        this.style &= 0xDFFFFFFF;
                    }
                    if (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                        OS.DefWindowProc(this.handle, 11, 0, 0);
                    }
                    if (this.hInsert != 0) {
                        OS.SendMessage(this.handle, 4378, 0, 0);
                    }
                    if (!this.ignoreExpand) {
                        final NMTREEVIEW nmtreeview3 = new NMTREEVIEW();
                        OS.MoveMemory(nmtreeview3, n2, NMTREEVIEW.sizeof);
                        final TVITEM itemNew3 = nmtreeview3.itemNew;
                        if (this.items == null) {
                            break;
                        }
                        final TreeItem getItem2 = this._getItem(itemNew3.hItem, itemNew3.lParam);
                        if (getItem2 == null) {
                            break;
                        }
                        final Event event2 = new Event();
                        event2.item = getItem2;
                        switch (nmtreeview3.action) {
                            case 2: {
                                if ((itemNew3.state & 0x20) != 0x0) {
                                    break;
                                }
                                this.sendEvent(17, event2);
                                if (this.isDisposed()) {
                                    return LRESULT.ZERO;
                                }
                                break;
                            }
                            case 1: {
                                this.sendEvent(18, event2);
                                if (this.isDisposed()) {
                                    return LRESULT.ZERO;
                                }
                                break;
                            }
                        }
                        n7 = ((OS.SendMessage(this.handle, 4362, 4, itemNew3.hItem) == 0) ? 1 : 0);
                    }
                    if (n7 == 0) {
                        break;
                    }
                }
                case -455:
                case -406: {
                    if ((this.style & 0x10000000) != 0x0) {
                        this.style |= 0x20000000;
                    }
                    if (this.hooks(40) || this.hooks(42)) {
                        this.style |= 0x20000000;
                    }
                    if (this.findImageControl() != null && this.getDrawing()) {
                        OS.DefWindowProc(this.handle, 11, 1, 0);
                        OS.InvalidateRect(this.handle, null, true);
                    }
                    if (this.hInsert != 0) {
                        OS.SendMessage(this.handle, 4378, this.insertAfter ? 1 : 0, this.hInsert);
                    }
                    if (!OS.IsWinCE && OS.COMCTL32_MAJOR >= 6 && this.imageList != null) {
                        final NMTREEVIEW nmtreeview4 = new NMTREEVIEW();
                        OS.MoveMemory(nmtreeview4, n2, NMTREEVIEW.sizeof);
                        final TVITEM itemNew4 = nmtreeview4.itemNew;
                        if (itemNew4.hItem != 0 && (OS.GetWindowLong(this.handle, -16) & 0x1000) == 0x0) {
                            final RECT rect3 = new RECT();
                            if (OS.TreeView_GetItemRect(this.handle, itemNew4.hItem, rect3, false)) {
                                OS.InvalidateRect(this.handle, rect3, true);
                            }
                        }
                    }
                    this.updateScrollBar();
                    break;
                }
                case -456:
                case -407: {
                    if (OS.GetKeyState(1) >= 0) {
                        break;
                    }
                }
                case -457:
                case -408: {
                    this.dragStarted = true;
                    final NMTREEVIEW nmtreeview5 = new NMTREEVIEW();
                    OS.MoveMemory(nmtreeview5, n2, NMTREEVIEW.sizeof);
                    final TVITEM itemNew5 = nmtreeview5.itemNew;
                    if (itemNew5.hItem != 0 && (itemNew5.state & 0x2) == 0x0) {
                        this.hSelect = itemNew5.hItem;
                        final boolean b2 = true;
                        this.ignoreDeselect = b2;
                        this.ignoreSelect = b2;
                        OS.SendMessage(this.handle, 4363, 9, itemNew5.hItem);
                        final boolean b3 = false;
                        this.ignoreDeselect = b3;
                        this.ignoreSelect = b3;
                        this.hSelect = 0;
                        break;
                    }
                    break;
                }
                case -16: {
                    if (OS.IsPPC && (this.menu == null || this.menu.isDisposed()) && !this.hooks(35)) {
                        return LRESULT.ONE;
                    }
                    break;
                }
                case 1000: {
                    if (OS.IsPPC && ((this.menu != null && !this.menu.isDisposed()) || this.hooks(35))) {
                        final NMRGINFO nmrginfo = new NMRGINFO();
                        OS.MoveMemory(nmrginfo, n2, NMRGINFO.sizeof);
                        this.showMenu(nmrginfo.x, nmrginfo.y);
                        this.gestureCompleted = true;
                        return LRESULT.ONE;
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(nmhdr, n, n2);
    }
    
    LRESULT wmNotifyHeader(final NMHDR nmhdr, final int n, final int n2) {
        switch (nmhdr.code) {
            case -326:
            case -325:
            case -306:
            case -305: {
                final NMHEADER nmheader = new NMHEADER();
                OS.MoveMemory(nmheader, n2, NMHEADER.sizeof);
                final TreeColumn treeColumn = this.columns[nmheader.iItem];
                if (treeColumn != null && !treeColumn.getResizable()) {
                    return LRESULT.ONE;
                }
                this.ignoreColumnMove = true;
                switch (nmhdr.code) {
                    case -325:
                    case -305: {
                        if (treeColumn != null) {
                            treeColumn.pack();
                            break;
                        }
                        break;
                    }
                }
                break;
            }
            case -16: {
                if (!this.ignoreColumnMove) {
                    for (int i = 0; i < this.columnCount; ++i) {
                        this.columns[i].updateToolTip(i);
                    }
                    this.updateImageList();
                }
                this.ignoreColumnMove = false;
                break;
            }
            case -310: {
                if (this.ignoreColumnMove) {
                    return LRESULT.ONE;
                }
                final NMHEADER nmheader2 = new NMHEADER();
                OS.MoveMemory(nmheader2, n2, NMHEADER.sizeof);
                if (nmheader2.iItem == -1) {
                    break;
                }
                final TreeColumn treeColumn2 = this.columns[nmheader2.iItem];
                if (treeColumn2 != null && !treeColumn2.getMoveable()) {
                    this.ignoreColumnMove = true;
                    return LRESULT.ONE;
                }
                break;
            }
            case -311: {
                final NMHEADER nmheader3 = new NMHEADER();
                OS.MoveMemory(nmheader3, n2, NMHEADER.sizeof);
                if (nmheader3.iItem == -1 || nmheader3.pitem == 0) {
                    break;
                }
                final HDITEM hditem = new HDITEM();
                OS.MoveMemory(hditem, nmheader3.pitem, HDITEM.sizeof);
                if ((hditem.mask & 0x80) == 0x0 || hditem.iOrder == -1) {
                    break;
                }
                final int[] array = new int[this.columnCount];
                OS.SendMessage(this.hwndHeader, 4625, this.columnCount, array);
                int n3;
                for (n3 = 0; n3 < array.length && array[n3] != nmheader3.iItem; ++n3) {}
                if (n3 == array.length) {
                    n3 = 0;
                }
                if (n3 == hditem.iOrder) {
                    break;
                }
                final int min = Math.min(n3, hditem.iOrder);
                final int max = Math.max(n3, hditem.iOrder);
                final RECT rect = new RECT();
                final RECT rect2 = new RECT();
                OS.GetClientRect(this.handle, rect);
                OS.SendMessage(this.hwndHeader, 4615, array[min], rect2);
                rect.left = Math.max(rect.left, rect2.left);
                OS.SendMessage(this.hwndHeader, 4615, array[max], rect2);
                rect.right = Math.min(rect.right, rect2.right);
                OS.InvalidateRect(this.handle, rect, true);
                this.ignoreColumnMove = false;
                for (int j = min; j <= max; ++j) {
                    final TreeColumn treeColumn3 = this.columns[array[j]];
                    if (!treeColumn3.isDisposed()) {
                        treeColumn3.postEvent(10);
                    }
                }
                break;
            }
            case -320:
            case -300: {
                final NMHEADER nmheader4 = new NMHEADER();
                OS.MoveMemory(nmheader4, n2, NMHEADER.sizeof);
                if (nmheader4.pitem == 0) {
                    break;
                }
                final HDITEM hditem2 = new HDITEM();
                OS.MoveMemory(hditem2, nmheader4.pitem, HDITEM.sizeof);
                if ((hditem2.mask & 0x1) != 0x0) {
                    final RECT rect3 = new RECT();
                    OS.GetClientRect(this.handle, rect3);
                    final HDITEM hditem3 = new HDITEM();
                    hditem3.mask = 1;
                    OS.SendMessage(this.hwndHeader, OS.HDM_GETITEM, nmheader4.iItem, hditem3);
                    final int n4 = hditem2.cxy - hditem3.cxy;
                    final RECT rect4 = new RECT();
                    OS.SendMessage(this.hwndHeader, 4615, nmheader4.iItem, rect4);
                    rect3.left = rect4.right - (this.linesVisible ? 1 : 0);
                    final int right = rect3.left + n4;
                    rect3.right = Math.max(rect3.right, rect3.left + Math.abs(n4));
                    if (this.explorerTheme || this.findImageControl() != null || this.hooks(41) || this.hooks(40) || this.hooks(42)) {
                        final RECT rect5 = rect3;
                        rect5.left -= OS.GetSystemMetrics(83);
                        OS.InvalidateRect(this.handle, rect3, true);
                        OS.OffsetRect(rect3, n4, 0);
                        OS.InvalidateRect(this.handle, rect3, true);
                    }
                    else {
                        OS.ScrollWindowEx(this.handle, n4, 0, rect3, null, 0, null, 6);
                    }
                    if (OS.SendMessage(this.hwndHeader, 4623, nmheader4.iItem, 0) != 0) {
                        rect3.left = rect4.left;
                        rect3.right = right;
                        OS.InvalidateRect(this.handle, rect3, true);
                    }
                    this.setScrollWidth();
                    break;
                }
                break;
            }
            case -321:
            case -301: {
                final NMHEADER nmheader5 = new NMHEADER();
                OS.MoveMemory(nmheader5, n2, NMHEADER.sizeof);
                if (nmheader5.pitem != 0) {
                    final HDITEM hditem4 = new HDITEM();
                    OS.MoveMemory(hditem4, nmheader5.pitem, HDITEM.sizeof);
                    if ((hditem4.mask & 0x1) != 0x0) {
                        if (this.ignoreColumnMove) {
                            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                                OS.RedrawWindow(this.handle, null, 0, 384);
                            }
                            else if ((this.style & 0x20000000) == 0x0) {
                                final int style = this.style;
                                this.style |= 0x20000000;
                                OS.UpdateWindow(this.handle);
                                this.style = style;
                            }
                        }
                        final TreeColumn treeColumn4 = this.columns[nmheader5.iItem];
                        if (treeColumn4 != null) {
                            treeColumn4.updateToolTip(nmheader5.iItem);
                            treeColumn4.sendEvent(11);
                            if (this.isDisposed()) {
                                return LRESULT.ZERO;
                            }
                            final TreeColumn[] array2 = new TreeColumn[this.columnCount];
                            System.arraycopy(this.columns, 0, array2, 0, this.columnCount);
                            final int[] array3 = new int[this.columnCount];
                            OS.SendMessage(this.hwndHeader, 4625, this.columnCount, array3);
                            int n5 = 0;
                            for (int k = 0; k < this.columnCount; ++k) {
                                final TreeColumn treeColumn5 = array2[array3[k]];
                                if (n5 != 0 && !treeColumn5.isDisposed()) {
                                    treeColumn5.updateToolTip(array3[k]);
                                    treeColumn5.sendEvent(10);
                                }
                                if (treeColumn5 == treeColumn4) {
                                    n5 = 1;
                                }
                            }
                        }
                    }
                    this.setScrollWidth();
                    break;
                }
                break;
            }
            case -322:
            case -302: {
                final NMHEADER nmheader6 = new NMHEADER();
                OS.MoveMemory(nmheader6, n2, NMHEADER.sizeof);
                final TreeColumn treeColumn6 = this.columns[nmheader6.iItem];
                if (treeColumn6 != null) {
                    treeColumn6.sendSelectionEvent(13);
                    break;
                }
                break;
            }
            case -323:
            case -303: {
                final NMHEADER nmheader7 = new NMHEADER();
                OS.MoveMemory(nmheader7, n2, NMHEADER.sizeof);
                final TreeColumn treeColumn7 = this.columns[nmheader7.iItem];
                if (treeColumn7 != null) {
                    treeColumn7.sendSelectionEvent(14);
                    break;
                }
                break;
            }
        }
        return null;
    }
    
    LRESULT wmNotifyToolTip(final NMHDR nmhdr, final int n, final int n2) {
        if (OS.IsWinCE) {
            return null;
        }
        switch (nmhdr.code) {
            case -12: {
                final NMTTCUSTOMDRAW nmttcustomdraw = new NMTTCUSTOMDRAW();
                OS.MoveMemory(nmttcustomdraw, n2, NMTTCUSTOMDRAW.sizeof);
                return this.wmNotifyToolTip(nmttcustomdraw, n2);
            }
            case -521: {
                final LRESULT wmNotify = super.wmNotify(nmhdr, n, n2);
                if (wmNotify != null) {
                    return wmNotify;
                }
                final int getMessagePos = OS.GetMessagePos();
                final POINT point = new POINT();
                OS.POINTSTOPOINT(point, getMessagePos);
                OS.ScreenToClient(this.handle, point);
                final int[] array = { 0 };
                final TreeItem[] array2 = { null };
                final RECT[] array3 = { null };
                final RECT[] array4 = { null };
                if (this.findCell(point.x, point.y, array2, array, array3, array4)) {
                    final RECT toolTipRect = this.toolTipRect(array4[0]);
                    OS.MapWindowPoints(this.handle, 0, toolTipRect, 2);
                    final int n3 = toolTipRect.right - toolTipRect.left;
                    final int n4 = toolTipRect.bottom - toolTipRect.top;
                    int n5 = 21;
                    if (this.isCustomToolTip()) {
                        n5 &= 0xFFFFFFFE;
                    }
                    this.SetWindowPos(this.itemToolTipHandle, 0, toolTipRect.left, toolTipRect.top, n3, n4, n5);
                    return LRESULT.ONE;
                }
                return wmNotify;
            }
            default: {
                return null;
            }
        }
    }
    
    LRESULT wmNotifyToolTip(final NMTTCUSTOMDRAW nmttcustomdraw, final int n) {
        if (OS.IsWinCE) {
            return null;
        }
        switch (nmttcustomdraw.dwDrawStage) {
            case 1: {
                if (this.isCustomToolTip()) {
                    if (!OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0)) {
                        OS.SetTextColor(nmttcustomdraw.hdc, OS.GetSysColor(OS.COLOR_INFOBK));
                    }
                    return new LRESULT(18);
                }
                break;
            }
            case 2: {
                if (!OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0)) {
                    OS.SetTextColor(nmttcustomdraw.hdc, OS.GetSysColor(OS.COLOR_INFOTEXT));
                }
                if (OS.SendMessage(this.itemToolTipHandle, OS.TTM_GETCURRENTTOOL, 0, 0) == 0) {
                    break;
                }
                final TOOLINFO toolinfo = new TOOLINFO();
                toolinfo.cbSize = TOOLINFO.sizeof;
                if (OS.SendMessage(this.itemToolTipHandle, OS.TTM_GETCURRENTTOOL, 0, toolinfo) == 0) {
                    break;
                }
                final int[] array = { 0 };
                final TreeItem[] array2 = { null };
                final RECT[] array3 = { null };
                final RECT[] array4 = { null };
                final int getMessagePos = OS.GetMessagePos();
                final POINT point = new POINT();
                OS.POINTSTOPOINT(point, getMessagePos);
                OS.ScreenToClient(this.handle, point);
                if (this.findCell(point.x, point.y, array2, array, array3, array4)) {
                    final int getDC = OS.GetDC(this.handle);
                    int n2 = array2[0].fontHandle(array[0]);
                    if (n2 == -1) {
                        n2 = OS.SendMessage(this.handle, 49, 0, 0);
                    }
                    final int selectObject = OS.SelectObject(getDC, n2);
                    int n3 = 1;
                    array3[0] = array2[0].getBounds(array[0], true, true, false, false, false, getDC);
                    if (this.hooks(40)) {
                        final Event sendEraseItemEvent = this.sendEraseItemEvent(array2[0], nmttcustomdraw, array[0], array3[0]);
                        if (this.isDisposed()) {
                            break;
                        }
                        if (array2[0].isDisposed()) {
                            break;
                        }
                        n3 = ((sendEraseItemEvent.doit && (sendEraseItemEvent.detail & 0x10) != 0x0) ? 1 : 0);
                    }
                    if (n3 != 0) {
                        final int saveDC = OS.SaveDC(nmttcustomdraw.hdc);
                        final int linesVisible = this.getLinesVisible() ? 1 : 0;
                        final RECT toolTipInset = this.toolTipInset(array3[0]);
                        OS.SetWindowOrgEx(nmttcustomdraw.hdc, toolTipInset.left, toolTipInset.top, null);
                        final GCData gcData = new GCData();
                        gcData.device = this.display;
                        gcData.foreground = OS.GetTextColor(nmttcustomdraw.hdc);
                        gcData.background = OS.GetBkColor(nmttcustomdraw.hdc);
                        gcData.font = Font.win32_new(this.display, n2);
                        final GC win32_new = GC.win32_new(nmttcustomdraw.hdc, gcData);
                        int n4 = array3[0].left + 3;
                        if (array[0] != 0) {
                            n4 -= linesVisible;
                        }
                        final Image image = array2[0].getImage(array[0]);
                        if (image != null || array[0] == 0) {
                            final Point imageSize = this.getImageSize();
                            final RECT bounds = array2[0].getBounds(array[0], false, true, false, false, false, getDC);
                            if (this.imageList == null) {
                                imageSize.x = bounds.right - bounds.left;
                            }
                            if (image != null) {
                                final Rectangle bounds2 = image.getBounds();
                                win32_new.drawImage(image, bounds2.x, bounds2.y, bounds2.width, bounds2.height, n4, bounds.top, imageSize.x, imageSize.y);
                                n4 += 3 + ((array[0] == 0) ? 1 : 0);
                            }
                            n4 += imageSize.x;
                        }
                        else {
                            n4 += 3;
                        }
                        final String text = array2[0].getText(array[0]);
                        if (text != null) {
                            int n5 = 2084;
                            final TreeColumn treeColumn = (this.columns != null) ? this.columns[array[0]] : null;
                            if (treeColumn != null) {
                                if ((treeColumn.style & 0x1000000) != 0x0) {
                                    n5 |= 0x1;
                                }
                                if ((treeColumn.style & 0x20000) != 0x0) {
                                    n5 |= 0x2;
                                }
                            }
                            final TCHAR tchar = new TCHAR(this.getCodePage(), text, false);
                            final RECT rect = new RECT();
                            OS.SetRect(rect, n4, array3[0].top, array3[0].right, array3[0].bottom);
                            OS.DrawText(nmttcustomdraw.hdc, tchar, tchar.length(), rect, n5);
                        }
                        win32_new.dispose();
                        OS.RestoreDC(nmttcustomdraw.hdc, saveDC);
                    }
                    if (this.hooks(42)) {
                        array4[0] = array2[0].getBounds(array[0], true, true, false, false, false, getDC);
                        this.sendPaintItemEvent(array2[0], nmttcustomdraw, array[0], array4[0]);
                    }
                    OS.SelectObject(getDC, selectObject);
                    OS.ReleaseDC(this.handle, getDC);
                    break;
                }
                break;
            }
        }
        return null;
    }
}
