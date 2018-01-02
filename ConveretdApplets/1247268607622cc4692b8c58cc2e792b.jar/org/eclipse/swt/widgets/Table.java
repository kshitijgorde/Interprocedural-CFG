// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.internal.win32.NMTTDISPINFOW;
import org.eclipse.swt.internal.win32.NMTTDISPINFOA;
import org.eclipse.swt.internal.win32.NMHEADER;
import org.eclipse.swt.internal.win32.NMRGINFO;
import org.eclipse.swt.internal.win32.NMLISTVIEW;
import org.eclipse.swt.internal.win32.NMLVDISPINFO;
import org.eclipse.swt.internal.win32.NMLVODSTATECHANGE;
import org.eclipse.swt.internal.win32.MEASUREITEMSTRUCT;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.internal.win32.SHDRAGIMAGE;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.win32.HDHITTESTINFO;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.win32.NMTTDISPINFO;
import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.internal.win32.NMTTCUSTOMDRAW;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.LVHITTESTINFO;
import org.eclipse.swt.internal.win32.HDITEM;
import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.LVCOLUMN;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.LVITEM;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.NMLVCUSTOMDRAW;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.ImageList;

public class Table extends Composite
{
    TableItem[] items;
    int[] keys;
    TableColumn[] columns;
    int columnCount;
    int customCount;
    int keyCount;
    ImageList imageList;
    ImageList headerImageList;
    TableItem currentItem;
    TableColumn sortColumn;
    RECT focusRect;
    int headerToolTipHandle;
    boolean ignoreCustomDraw;
    boolean ignoreDrawForeground;
    boolean ignoreDrawBackground;
    boolean ignoreDrawFocus;
    boolean ignoreDrawSelection;
    boolean ignoreDrawHot;
    boolean customDraw;
    boolean dragStarted;
    boolean explorerTheme;
    boolean firstColumnImage;
    boolean fixScrollWidth;
    boolean tipRequested;
    boolean wasSelected;
    boolean wasResized;
    boolean painted;
    boolean ignoreActivate;
    boolean ignoreSelect;
    boolean ignoreShrink;
    boolean ignoreResize;
    boolean ignoreColumnMove;
    boolean ignoreColumnResize;
    boolean fullRowSelect;
    int itemHeight;
    int lastIndexOf;
    int lastWidth;
    int sortDirection;
    int resizeCount;
    int selectionForeground;
    int hotIndex;
    static int HeaderProc;
    static final int INSET = 4;
    static final int GRID_WIDTH = 1;
    static final int SORT_WIDTH = 10;
    static final int HEADER_MARGIN = 12;
    static final int HEADER_EXTRA = 3;
    static final int VISTA_EXTRA = 2;
    static final int EXPLORER_EXTRA = 2;
    static final int H_SCROLL_LIMIT = 32;
    static final int V_SCROLL_LIMIT = 16;
    static final int DRAG_IMAGE_SIZE = 301;
    static final boolean EXPLORER_THEME = true;
    static boolean COMPRESS_ITEMS;
    static final int TableProc;
    static final TCHAR TableClass;
    
    static {
        Table.COMPRESS_ITEMS = true;
        TableClass = new TCHAR(0, "SysListView32", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Table.TableClass, wndclass);
        TableProc = wndclass.lpfnWndProc;
    }
    
    public Table(final Composite composite, final int n) {
        super(composite, checkStyle(n));
    }
    
    void _addListener(final int n, final Listener listener) {
        super._addListener(n, listener);
        switch (n) {
            case 40:
            case 41:
            case 42: {
                this.setCustomDraw(true);
                this.setBackgroundTransparent(true);
                if (OS.COMCTL32_MAJOR < 6) {
                    this.style |= 0x20000000;
                }
                if (OS.IsWinCE) {
                    OS.SendMessage(this.handle, 4150, 16384, 0);
                    break;
                }
                break;
            }
        }
    }
    
    boolean _checkGrow(final int n) {
        if (this.keys == null) {
            if (n == this.items.length) {
                final TableItem[] items = new TableItem[(this.getDrawing() && OS.IsWindowVisible(this.handle)) ? (this.items.length + 4) : Math.max(4, this.items.length * 3 / 2)];
                System.arraycopy(this.items, 0, items, 0, this.items.length);
                this.items = items;
            }
        }
        else {
            if (!this.ignoreShrink && this.keyCount > n / 2) {
                final TableItem[] items2 = new TableItem[(this.getDrawing() && OS.IsWindowVisible(this.handle)) ? (n + 4) : Math.max(4, n * 3 / 2)];
                for (int i = 0; i < this.keyCount; ++i) {
                    items2[this.keys[i]] = this.items[i];
                }
                this.items = items2;
                this.keys = null;
                this.keyCount = 0;
                return true;
            }
            if (this.keyCount == this.keys.length) {
                final int n2 = (this.getDrawing() && OS.IsWindowVisible(this.handle)) ? (this.keys.length + 4) : Math.max(4, this.keys.length * 3 / 2);
                final int[] keys = new int[n2];
                System.arraycopy(this.keys, 0, keys, 0, this.keys.length);
                this.keys = keys;
                final TableItem[] items3 = new TableItem[n2];
                System.arraycopy(this.items, 0, items3, 0, this.items.length);
                this.items = items3;
            }
        }
        return false;
    }
    
    void _checkShrink() {
        if (this.keys == null) {
            if (!this.ignoreShrink) {
                final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
                if (this.items.length > 4 && this.items.length - sendMessage > 3) {
                    final TableItem[] items = new TableItem[Math.max(4, (sendMessage + 3) / 4 * 4)];
                    System.arraycopy(this.items, 0, items, 0, sendMessage);
                    this.items = items;
                }
            }
        }
        else if (!this.ignoreShrink && this.keys.length > 4 && this.keys.length - this.keyCount > 3) {
            final int max = Math.max(4, (this.keyCount + 3) / 4 * 4);
            final int[] keys = new int[max];
            System.arraycopy(this.keys, 0, keys, 0, this.keyCount);
            this.keys = keys;
            final TableItem[] items2 = new TableItem[max];
            System.arraycopy(this.items, 0, items2, 0, this.keyCount);
            this.items = items2;
        }
    }
    
    void _clearItems() {
        this.items = null;
        this.keys = null;
        this.keyCount = 0;
    }
    
    TableItem _getItem(final int n) {
        return this._getItem(n, true);
    }
    
    TableItem _getItem(final int n, final boolean b) {
        return this._getItem(n, b, -1);
    }
    
    TableItem _getItem(final int n, final boolean b, int sendMessage) {
        if (this.keys == null) {
            if ((this.style & 0x10000000) == 0x0 || !b) {
                return this.items[n];
            }
            if (this.items[n] != null) {
                return this.items[n];
            }
            return this.items[n] = new TableItem(this, 0, -1, false);
        }
        else {
            if ((this.style & 0x10000000) == 0x0 || !b) {
                if (this.keyCount == 0) {
                    return null;
                }
                if (n > this.keys[this.keyCount - 1]) {
                    return null;
                }
            }
            int binarySearch = this.binarySearch(this.keys, 0, this.keyCount, n);
            if ((this.style & 0x10000000) == 0x0 || !b) {
                return (binarySearch < 0) ? null : this.items[binarySearch];
            }
            if (binarySearch < 0) {
                if (sendMessage == -1) {
                    sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
                }
                if (this._checkGrow(sendMessage)) {
                    if (this.items[n] != null) {
                        return this.items[n];
                    }
                    return this.items[n] = new TableItem(this, 0, -1, false);
                }
                else {
                    binarySearch = -binarySearch - 1;
                    if (binarySearch < this.keyCount) {
                        System.arraycopy(this.keys, binarySearch, this.keys, binarySearch + 1, this.keyCount - binarySearch);
                        System.arraycopy(this.items, binarySearch, this.items, binarySearch + 1, this.keyCount - binarySearch);
                    }
                    ++this.keyCount;
                    this.keys[binarySearch] = n;
                }
            }
            else if (this.items[binarySearch] != null) {
                return this.items[binarySearch];
            }
            return this.items[binarySearch] = new TableItem(this, 0, -1, false);
        }
    }
    
    void _getItems(final TableItem[] array, final int n) {
        if (this.keys == null) {
            System.arraycopy(this.items, 0, array, 0, n);
        }
        else {
            for (int i = 0; i < this.keyCount; ++i) {
                if (this.keys[i] >= n) {
                    break;
                }
                array[this.keys[i]] = this.items[this.keys[i]];
            }
        }
    }
    
    boolean _hasItems() {
        return this.items != null;
    }
    
    void _initItems() {
        this.items = new TableItem[4];
        if (Table.COMPRESS_ITEMS && (this.style & 0x10000000) != 0x0) {
            this.keyCount = 0;
            this.keys = new int[4];
        }
    }
    
    void _insertItem(final int n, final TableItem tableItem, final int n2) {
        if (this.keys == null) {
            System.arraycopy(this.items, n, this.items, n + 1, n2 - n);
            this.items[n] = tableItem;
        }
        else {
            int binarySearch = this.binarySearch(this.keys, 0, this.keyCount, n);
            if (binarySearch < 0) {
                binarySearch = -binarySearch - 1;
            }
            System.arraycopy(this.keys, binarySearch, this.keys, binarySearch + 1, this.keyCount - binarySearch);
            this.keys[binarySearch] = n;
            System.arraycopy(this.items, binarySearch, this.items, binarySearch + 1, this.keyCount - binarySearch);
            this.items[binarySearch] = tableItem;
            ++this.keyCount;
            for (int i = binarySearch + 1; i < this.keyCount; ++i) {
                final int[] keys = this.keys;
                final int n3 = i;
                ++keys[n3];
            }
        }
    }
    
    void _removeItem(final int n, int n2) {
        if (this.keys == null) {
            System.arraycopy(this.items, n + 1, this.items, n, --n2 - n);
            this.items[n2] = null;
        }
        else {
            int binarySearch = this.binarySearch(this.keys, 0, this.keyCount, n);
            if (binarySearch < 0) {
                binarySearch = -binarySearch - 1;
            }
            else {
                --this.keyCount;
                System.arraycopy(this.keys, binarySearch + 1, this.keys, binarySearch, this.keyCount - binarySearch);
                this.keys[this.keyCount] = 0;
                System.arraycopy(this.items, binarySearch + 1, this.items, binarySearch, this.keyCount - binarySearch);
                this.items[this.keyCount] = null;
            }
            for (int i = binarySearch; i < this.keyCount; ++i) {
                final int[] keys = this.keys;
                final int n3 = i;
                --keys[n3];
            }
        }
    }
    
    void _removeItems(final int n, final int n2, final int n3) {
        if (this.keys == null) {
            System.arraycopy(this.items, n2, this.items, n, n3 - n2);
            for (int i = n3 - (n2 - n); i < n3; ++i) {
                this.items[i] = null;
            }
        }
        else {
            int binarySearch = this.binarySearch(this.keys, 0, this.keyCount, n);
            if (binarySearch < 0) {
                binarySearch = -binarySearch - 1;
            }
            int binarySearch2 = this.binarySearch(this.keys, binarySearch, this.keyCount, n2);
            if (binarySearch2 < 0) {
                binarySearch2 = -binarySearch2 - 1;
            }
            System.arraycopy(this.keys, binarySearch2, this.keys, binarySearch, this.keyCount - binarySearch2);
            for (int j = this.keyCount - (binarySearch2 - binarySearch); j < this.keyCount; ++j) {
                this.keys[j] = 0;
            }
            System.arraycopy(this.items, binarySearch2, this.items, binarySearch, this.keyCount - binarySearch2);
            for (int k = this.keyCount - (binarySearch2 - binarySearch); k < this.keyCount; ++k) {
                this.items[k] = null;
            }
            this.keyCount -= binarySearch2 - binarySearch;
            for (int l = binarySearch; l < this.keyCount; ++l) {
                final int[] keys = this.keys;
                final int n4 = l;
                keys[n4] -= binarySearch2 - binarySearch;
            }
        }
    }
    
    void _setItemCount(final int n, final int n2) {
        if (this.keys == null) {
            final TableItem[] items = new TableItem[Math.max(4, (n + 3) / 4 * 4)];
            System.arraycopy(this.items, 0, items, 0, Math.min(n, n2));
            this.items = items;
        }
        else {
            this.keyCount = this.binarySearch(this.keys, 0, this.keyCount, Math.min(n, n2));
            if (this.keyCount < 0) {
                this.keyCount = -this.keyCount - 1;
            }
            final int max = Math.max(4, (this.keyCount + 3) / 4 * 4);
            final int[] keys = new int[max];
            System.arraycopy(this.keys, 0, keys, 0, this.keyCount);
            this.keys = keys;
            final TableItem[] items2 = new TableItem[max];
            System.arraycopy(this.items, 0, items2, 0, this.keyCount);
            this.items = items2;
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
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        return this.callWindowProc(n, n2, n3, n4, false);
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (this.handle == 0) {
            return 0;
        }
        if (this.handle != n) {
            return OS.CallWindowProc(Table.HeaderProc, n, n2, n3, n4);
        }
        int sendMessage = 0;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        switch (n2) {
            case 256: {
                b3 = true;
            }
            case 71:
            case 257:
            case 258:
            case 260:
            case 261:
            case 262:
            case 276:
            case 277:
            case 646: {
                b4 = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (b4) {
                    OS.DefWindowProc(this.handle, 11, 0, 0);
                    OS.SendMessage(this.handle, 4097, 0, 16777215);
                }
            }
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
                b2 = true;
            }
            case 48:
            case 275: {
                if (this.findImageControl() != null) {
                    sendMessage = OS.SendMessage(this.handle, 4135, 0, 0);
                    break;
                }
                break;
            }
        }
        final boolean wasSelected = this.wasSelected;
        if (b2) {
            this.wasSelected = false;
        }
        if (b3) {
            this.ignoreActivate = true;
        }
        boolean b5 = false;
        if (n2 == 15 && (OS.GetWindowLong(this.handle, -16) & 0x4000) == 0x0) {
            for (int i = OS.GetParent(this.handle); i != 0; i = OS.GetParent(i)) {
                if ((OS.GetWindowLong(i, -20) & 0x2000000) != 0x0) {
                    b5 = true;
                    break;
                }
                if (OS.GetWindow(i, 4) != 0) {
                    break;
                }
            }
        }
        boolean b6 = false;
        if ((this.style & 0x100) == 0x0 || (this.style & 0x200) == 0x0) {
            switch (n2) {
                case 15:
                case 70:
                case 133: {
                    int getWindowLong = OS.GetWindowLong(n, -16);
                    if ((this.style & 0x100) == 0x0 && (getWindowLong & 0x100000) != 0x0) {
                        b6 = true;
                        getWindowLong &= 0xFFEFFFFF;
                    }
                    if ((this.style & 0x200) == 0x0 && (getWindowLong & 0x200000) != 0x0) {
                        b6 = true;
                        getWindowLong &= 0xFFDFFFFF;
                    }
                    if (b6) {
                        OS.SetWindowLong(this.handle, -16, getWindowLong);
                        break;
                    }
                    break;
                }
            }
        }
        int n5;
        if (b5) {
            final PAINTSTRUCT paintstruct = new PAINTSTRUCT();
            n5 = OS.CallWindowProc(Table.TableProc, n, 15, OS.BeginPaint(n, paintstruct), n4);
            OS.EndPaint(n, paintstruct);
        }
        else {
            n5 = OS.CallWindowProc(Table.TableProc, n, n2, n3, n4);
        }
        if (b6) {
            OS.RedrawWindow(this.handle, null, 0, 1025);
        }
        if (b3) {
            this.ignoreActivate = false;
        }
        if (b2) {
            if (this.wasSelected || b) {
                final Event event = new Event();
                final int sendMessage2 = OS.SendMessage(this.handle, 4108, -1, 1);
                if (sendMessage2 != -1) {
                    event.item = this._getItem(sendMessage2);
                }
                this.sendSelectionEvent(13, event, false);
            }
            this.wasSelected = wasSelected;
        }
        Label_1111: {
            switch (n2) {
                case 71:
                case 256:
                case 257:
                case 258:
                case 260:
                case 261:
                case 262:
                case 276:
                case 277:
                case 646: {
                    if (!b4) {
                        break Label_1111;
                    }
                    OS.SendMessage(this.handle, 4097, 0, -1);
                    OS.DefWindowProc(this.handle, 11, 1, 0);
                    OS.InvalidateRect(this.handle, null, true);
                    final int sendMessage3 = OS.SendMessage(this.handle, 4127, 0, 0);
                    if (sendMessage3 != 0) {
                        OS.InvalidateRect(sendMessage3, null, true);
                    }
                    break Label_1111;
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
                    if (this.findImageControl() != null && sendMessage != OS.SendMessage(this.handle, 4135, 0, 0)) {
                        OS.InvalidateRect(this.handle, null, true);
                        break;
                    }
                    break;
                }
                case 15: {
                    this.painted = true;
                    break;
                }
            }
        }
        return n5;
    }
    
    static int checkStyle(int n) {
        if ((n & 0x10) == 0x0) {
            n |= 0x300;
        }
        return Widget.checkBits(n, 4, 2, 0, 0, 0, 0);
    }
    
    LRESULT CDDS_ITEMPOSTPAINT(final NMLVCUSTOMDRAW nmlvcustomdraw, final int n, final int n2) {
        final int hdc = nmlvcustomdraw.hdc;
        if (this.explorerTheme && !this.ignoreCustomDraw) {
            this.hotIndex = -1;
            if (this.hooks(40) && nmlvcustomdraw.left != nmlvcustomdraw.right) {
                OS.RestoreDC(hdc, -1);
            }
        }
        if (!this.ignoreCustomDraw && !this.ignoreDrawFocus && nmlvcustomdraw.left != nmlvcustomdraw.right && OS.IsWindowVisible(this.handle) && OS.IsWindowEnabled(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && OS.SendMessage(this.handle, 4096, 0, 0) == -1 && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x20) == 0x0 && OS.SendMessage(this.handle, 4108, -1, 1) == nmlvcustomdraw.dwItemSpec && this.handle == OS.GetFocus() && (OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0) {
            final RECT rect = new RECT();
            rect.left = 0;
            final boolean ignoreCustomDraw = this.ignoreCustomDraw;
            this.ignoreCustomDraw = true;
            OS.SendMessage(this.handle, 4110, nmlvcustomdraw.dwItemSpec, rect);
            final int sendMessage = OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 4623, 0, 0);
            final RECT rect2 = new RECT();
            if (sendMessage == 0) {
                rect2.left = 2;
                OS.SendMessage(this.handle, 4110, sendMessage, rect2);
            }
            else {
                rect2.top = sendMessage;
                rect2.left = 1;
                OS.SendMessage(this.handle, 4152, nmlvcustomdraw.dwItemSpec, rect2);
            }
            this.ignoreCustomDraw = ignoreCustomDraw;
            rect.left = rect2.left;
            OS.DrawFocusRect(nmlvcustomdraw.hdc, rect);
        }
        return null;
    }
    
    LRESULT CDDS_ITEMPREPAINT(final NMLVCUSTOMDRAW nmlvcustomdraw, final int n, final int n2) {
        if (!this.ignoreCustomDraw && OS.IsWindowVisible(this.handle) && OS.IsWindowEnabled(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && OS.SendMessage(this.handle, 4096, 0, 0) == -1 && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x20) == 0x0 && (nmlvcustomdraw.uItemState & 0x10) != 0x0) {
            nmlvcustomdraw.uItemState &= 0xFFFFFFEF;
            OS.MoveMemory(n2, nmlvcustomdraw, NMLVCUSTOMDRAW.sizeof);
        }
        if (this.explorerTheme && !this.ignoreCustomDraw) {
            this.hotIndex = (((nmlvcustomdraw.uItemState & 0x40) != 0x0) ? nmlvcustomdraw.dwItemSpec : -1);
            if (this.hooks(40) && nmlvcustomdraw.left != nmlvcustomdraw.right) {
                OS.SaveDC(nmlvcustomdraw.hdc);
                final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                OS.SelectClipRgn(nmlvcustomdraw.hdc, createRectRgn);
                OS.DeleteObject(createRectRgn);
            }
        }
        return new LRESULT(48);
    }
    
    LRESULT CDDS_POSTPAINT(final NMLVCUSTOMDRAW nmlvcustomdraw, final int n, final int n2) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        final int customCount = this.customCount - 1;
        this.customCount = customCount;
        if (customCount == 0 && OS.IsWindowVisible(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && OS.SendMessage(this.handle, 4096, 0, 0) == -1 && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x20) == 0x0) {
            final int n3 = 32;
            final int sendMessage = OS.SendMessage(this.handle, 4170, 0, 0);
            if (OS.IsWinCE) {
                final RECT rect = new RECT();
                final boolean getUpdateRect = OS.GetUpdateRect(this.handle, rect, true);
                OS.SendMessage(this.handle, 4150, n3, n3);
                OS.ValidateRect(this.handle, null);
                if (getUpdateRect) {
                    OS.InvalidateRect(this.handle, rect, true);
                }
            }
            else {
                final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                final int getUpdateRgn = OS.GetUpdateRgn(this.handle, createRectRgn, true);
                OS.SendMessage(this.handle, 4150, n3, n3);
                OS.ValidateRect(this.handle, null);
                if (getUpdateRgn != 1) {
                    OS.InvalidateRgn(this.handle, createRectRgn, true);
                }
                OS.DeleteObject(createRectRgn);
            }
            OS.SendMessage(this.handle, 4170, sendMessage, sendMessage);
        }
        return null;
    }
    
    LRESULT CDDS_PREPAINT(final NMLVCUSTOMDRAW nmlvcustomdraw, final int n, final int n2) {
        if (this.ignoreCustomDraw) {
            return new LRESULT(48);
        }
        if (this.customCount++ == 0 && OS.IsWindowVisible(this.handle) && !this.explorerTheme && (this.style & 0x10000) != 0x0 && OS.SendMessage(this.handle, 4096, 0, 0) == -1 && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x20) != 0x0) {
            final int n3 = 32;
            final int sendMessage = OS.SendMessage(this.handle, 4170, 0, 0);
            if (OS.IsWinCE) {
                final RECT rect = new RECT();
                final boolean getUpdateRect = OS.GetUpdateRect(this.handle, rect, true);
                OS.SendMessage(this.handle, 4150, n3, 0);
                OS.ValidateRect(this.handle, null);
                if (getUpdateRect) {
                    OS.InvalidateRect(this.handle, rect, true);
                }
            }
            else {
                final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                final int getUpdateRgn = OS.GetUpdateRgn(this.handle, createRectRgn, true);
                OS.SendMessage(this.handle, 4150, n3, 0);
                OS.ValidateRect(this.handle, null);
                if (getUpdateRgn != 1) {
                    OS.InvalidateRgn(this.handle, createRectRgn, true);
                }
                OS.DeleteObject(createRectRgn);
            }
            OS.SendMessage(this.handle, 4170, sendMessage, sendMessage);
        }
        if (OS.IsWindowVisible(this.handle)) {
            boolean b = true;
            if (this.explorerTheme && this.columnCount == 0) {
                final int hdc = nmlvcustomdraw.hdc;
                final RECT rect2 = new RECT();
                OS.SetRect(rect2, nmlvcustomdraw.left, nmlvcustomdraw.top, nmlvcustomdraw.right, nmlvcustomdraw.bottom);
                if (OS.IsWindowEnabled(this.handle) || this.findImageControl() != null) {
                    this.drawBackground(hdc, rect2);
                }
                else {
                    this.fillBackground(hdc, OS.GetSysColor(OS.COLOR_3DFACE), rect2);
                }
                b = false;
            }
            if (b) {
                Control backgroundControl = this.findBackgroundControl();
                if (backgroundControl != null && backgroundControl.backgroundImage != null) {
                    final RECT rect3 = new RECT();
                    OS.SetRect(rect3, nmlvcustomdraw.left, nmlvcustomdraw.top, nmlvcustomdraw.right, nmlvcustomdraw.bottom);
                    this.fillImageBackground(nmlvcustomdraw.hdc, backgroundControl, rect3, 0, 0);
                }
                else if (OS.SendMessage(this.handle, 4096, 0, 0) == -1 && OS.IsWindowEnabled(this.handle)) {
                    final RECT rect4 = new RECT();
                    OS.SetRect(rect4, nmlvcustomdraw.left, nmlvcustomdraw.top, nmlvcustomdraw.right, nmlvcustomdraw.bottom);
                    if (backgroundControl == null) {
                        backgroundControl = this;
                    }
                    this.fillBackground(nmlvcustomdraw.hdc, backgroundControl.getBackgroundPixel(), rect4);
                    if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0) {
                        final int index = this.indexOf(this.sortColumn);
                        if (index != -1) {
                            this.parent.forceResize();
                            final int sortColumnPixel = this.getSortColumnPixel();
                            final RECT rect5 = new RECT();
                            final RECT rect6 = new RECT();
                            OS.GetClientRect(this.handle, rect5);
                            final int sendMessage2 = OS.SendMessage(this.handle, 4127, 0, 0);
                            if (OS.SendMessage(sendMessage2, 4615, index, rect6) != 0) {
                                OS.MapWindowPoints(sendMessage2, this.handle, rect6, 2);
                                rect5.left = rect6.left;
                                rect5.right = rect6.right;
                                if (OS.IntersectRect(rect5, rect5, rect4)) {
                                    this.fillBackground(nmlvcustomdraw.hdc, sortColumnPixel, rect5);
                                }
                            }
                        }
                    }
                }
            }
        }
        return new LRESULT(48);
    }
    
    LRESULT CDDS_SUBITEMPOSTPAINT(final NMLVCUSTOMDRAW nmlvcustomdraw, final int n, final int n2) {
        if (this.ignoreCustomDraw) {
            return null;
        }
        if (nmlvcustomdraw.left == nmlvcustomdraw.right) {
            return new LRESULT(0);
        }
        final int hdc = nmlvcustomdraw.hdc;
        if (this.ignoreDrawForeground) {
            OS.RestoreDC(hdc, -1);
        }
        if (OS.IsWindowVisible(this.handle)) {
            if (OS.SendMessage(this.handle, 4096, 0, 0) != -1 && (this.sortDirection & 0x480) != 0x0 && this.sortColumn != null && !this.sortColumn.isDisposed() && OS.SendMessage(this.handle, 4270, 0, 0) == -1) {
                final int index = this.indexOf(this.sortColumn);
                int getUpdateRgn = 0;
                int createRectRgn = 0;
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                    getUpdateRgn = OS.GetUpdateRgn(this.handle, createRectRgn, true);
                }
                OS.SendMessage(this.handle, 4236, index, 0);
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    OS.ValidateRect(this.handle, null);
                    if (getUpdateRgn != 1) {
                        OS.InvalidateRgn(this.handle, createRectRgn, true);
                    }
                    OS.DeleteObject(createRectRgn);
                }
            }
            if (this.hooks(42)) {
                this.sendPaintItemEvent(this._getItem(nmlvcustomdraw.dwItemSpec), nmlvcustomdraw);
            }
            if (!this.ignoreDrawFocus && this.focusRect != null) {
                OS.SetTextColor(nmlvcustomdraw.hdc, 0);
                OS.SetBkColor(nmlvcustomdraw.hdc, 16777215);
                OS.DrawFocusRect(nmlvcustomdraw.hdc, this.focusRect);
                this.focusRect = null;
            }
        }
        return null;
    }
    
    LRESULT CDDS_SUBITEMPREPAINT(final NMLVCUSTOMDRAW nmlvcustomdraw, final int n, final int n2) {
        final int hdc = nmlvcustomdraw.hdc;
        if (this.explorerTheme && !this.ignoreCustomDraw && this.hooks(40) && nmlvcustomdraw.left != nmlvcustomdraw.right) {
            OS.RestoreDC(hdc, -1);
        }
        final TableItem getItem = this._getItem(nmlvcustomdraw.dwItemSpec);
        if (getItem == null || getItem.isDisposed()) {
            return null;
        }
        int n3 = getItem.fontHandle(nmlvcustomdraw.iSubItem);
        if (n3 != -1) {
            OS.SelectObject(hdc, n3);
        }
        if (this.ignoreCustomDraw || nmlvcustomdraw.left == nmlvcustomdraw.right) {
            return new LRESULT((n3 == -1) ? 0 : 2);
        }
        int n4 = 0;
        this.selectionForeground = -1;
        final boolean b = false;
        this.ignoreDrawBackground = b;
        this.ignoreDrawFocus = b;
        this.ignoreDrawSelection = b;
        this.ignoreDrawForeground = b;
        if (OS.IsWindowVisible(this.handle)) {
            Event sendMeasureItemEvent = null;
            if (this.hooks(41)) {
                sendMeasureItemEvent = this.sendMeasureItemEvent(getItem, nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, nmlvcustomdraw.hdc);
                if (this.isDisposed() || getItem.isDisposed()) {
                    return null;
                }
            }
            if (this.hooks(40)) {
                this.sendEraseItemEvent(getItem, nmlvcustomdraw, n2, sendMeasureItemEvent);
                if (this.isDisposed() || getItem.isDisposed()) {
                    return null;
                }
                n4 |= 0x10;
            }
            if (this.ignoreDrawForeground || this.hooks(42)) {
                n4 |= 0x10;
            }
        }
        int n5 = (getItem.cellForeground != null) ? getItem.cellForeground[nmlvcustomdraw.iSubItem] : -1;
        if (n5 == -1) {
            n5 = getItem.foreground;
        }
        int n6 = (getItem.cellBackground != null) ? getItem.cellBackground[nmlvcustomdraw.iSubItem] : -1;
        if (n6 == -1) {
            n6 = getItem.background;
        }
        if (this.selectionForeground != -1) {
            n5 = this.selectionForeground;
        }
        if (OS.IsWindowVisible(this.handle) && OS.IsWindowEnabled(this.handle) && !this.explorerTheme && !this.ignoreDrawSelection && (this.style & 0x10000) != 0x0 && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x20) == 0x0) {
            final LVITEM lvitem = new LVITEM();
            lvitem.mask = 8;
            lvitem.stateMask = 2;
            lvitem.iItem = nmlvcustomdraw.dwItemSpec;
            if (OS.SendMessage(this.handle, OS.LVM_GETITEM, 0, lvitem) != 0 && (lvitem.state & 0x2) != 0x0) {
                int n7 = -1;
                if (nmlvcustomdraw.iSubItem == 0) {
                    if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                        n7 = OS.GetSysColor(OS.COLOR_HIGHLIGHT);
                    }
                    else if ((this.style & 0x8000) == 0x0) {
                        n7 = OS.GetSysColor(OS.COLOR_3DFACE);
                    }
                }
                else if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                    n5 = OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT);
                    n7 = (n6 = OS.GetSysColor(OS.COLOR_HIGHLIGHT));
                }
                else if ((this.style & 0x8000) == 0x0) {
                    n7 = (n6 = OS.GetSysColor(OS.COLOR_3DFACE));
                }
                if (n7 != -1) {
                    this.fillBackground(hdc, n7, getItem.getBounds(nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, true, nmlvcustomdraw.iSubItem != 0, true, false, hdc));
                }
            }
        }
        if (!this.ignoreDrawForeground) {
            boolean b2 = true;
            if (n3 == -1 && n5 == -1 && n6 == -1 && getItem.cellForeground == null && getItem.cellBackground == null && getItem.cellFont == null && OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 4608, 0, 0) == 1) {
                b2 = false;
            }
            if (b2) {
                if (n3 == -1) {
                    n3 = OS.SendMessage(this.handle, 49, 0, 0);
                }
                OS.SelectObject(hdc, n3);
                if (OS.IsWindowEnabled(this.handle)) {
                    nmlvcustomdraw.clrText = ((n5 == -1) ? this.getForegroundPixel() : n5);
                    if (n6 == -1) {
                        nmlvcustomdraw.clrTextBk = -1;
                        if (this.selectionForeground == -1) {
                            Control backgroundControl = this.findBackgroundControl();
                            if (backgroundControl == null) {
                                backgroundControl = this;
                            }
                            if (backgroundControl.backgroundImage == null && OS.SendMessage(this.handle, 4096, 0, 0) != -1) {
                                nmlvcustomdraw.clrTextBk = backgroundControl.getBackgroundPixel();
                            }
                        }
                    }
                    else {
                        nmlvcustomdraw.clrTextBk = ((this.selectionForeground != -1) ? -1 : n6);
                    }
                    OS.MoveMemory(n2, nmlvcustomdraw, NMLVCUSTOMDRAW.sizeof);
                }
                n4 |= 0x2;
            }
        }
        if (OS.IsWindowEnabled(this.handle)) {
            if (n6 != -1) {
                final int sendMessage = OS.SendMessage(this.handle, 4270, 0, 0);
                if (sendMessage != -1 && sendMessage == nmlvcustomdraw.iSubItem) {
                    int getUpdateRgn = 0;
                    int createRectRgn = 0;
                    if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                        createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                        getUpdateRgn = OS.GetUpdateRgn(this.handle, createRectRgn, true);
                    }
                    OS.SendMessage(this.handle, 4236, -1, 0);
                    if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                        OS.ValidateRect(this.handle, null);
                        if (getUpdateRgn != 1) {
                            OS.InvalidateRgn(this.handle, createRectRgn, true);
                        }
                        OS.DeleteObject(createRectRgn);
                    }
                    n4 |= 0x10;
                }
            }
        }
        else {
            nmlvcustomdraw.clrText = OS.GetSysColor(OS.COLOR_GRAYTEXT);
            if (this.findImageControl() != null) {
                nmlvcustomdraw.clrTextBk = -1;
            }
            else {
                nmlvcustomdraw.clrTextBk = OS.GetSysColor(OS.COLOR_3DFACE);
            }
            nmlvcustomdraw.uItemState &= 0xFFFFFFFE;
            OS.MoveMemory(n2, nmlvcustomdraw, NMLVCUSTOMDRAW.sizeof);
            n4 |= 0x2;
        }
        return new LRESULT(n4);
    }
    
    void checkBuffered() {
        super.checkBuffered();
        if (OS.COMCTL32_MAJOR >= 6) {
            this.style |= 0x20000000;
        }
        if ((this.style & 0x10000000) != 0x0) {
            this.style |= 0x20000000;
        }
    }
    
    boolean checkData(final TableItem tableItem, final boolean b) {
        return (this.style & 0x10000000) == 0x0 || this.checkData(tableItem, this.indexOf(tableItem), b);
    }
    
    boolean checkData(final TableItem tableItem, final int index, final boolean b) {
        if ((this.style & 0x10000000) == 0x0) {
            return true;
        }
        if (!tableItem.cached) {
            tableItem.cached = true;
            final Event event = new Event();
            event.item = tableItem;
            event.index = index;
            this.currentItem = tableItem;
            this.sendEvent(36, event);
            this.currentItem = null;
            if (this.isDisposed() || tableItem.isDisposed()) {
                return false;
            }
            if (b && !this.setScrollWidth(tableItem, false)) {
                tableItem.redraw();
            }
        }
        return true;
    }
    
    boolean checkHandle(final int n) {
        return n == this.handle || n == OS.SendMessage(this.handle, 4127, 0, 0);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public void clear(final int iItem) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (iItem < 0 || iItem >= sendMessage) {
            this.error(6);
        }
        final TableItem getItem = this._getItem(iItem, false);
        if (getItem != null) {
            if (getItem != this.currentItem) {
                getItem.clear();
            }
            if ((this.style & 0x10000000) == 0x0 && getItem.cached) {
                final LVITEM lvitem = new LVITEM();
                lvitem.mask = 17;
                lvitem.pszText = -1;
                lvitem.iItem = iItem;
                OS.SendMessage(this.handle, OS.LVM_SETITEM, 0, lvitem);
                getItem.cached = false;
            }
            if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                OS.SendMessage(this.handle, 4117, iItem, iItem);
            }
            this.setScrollWidth(getItem, false);
        }
    }
    
    public void clear(final int n, final int n2) {
        this.checkWidget();
        if (n > n2) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (n < 0 || n > n2 || n2 >= sendMessage) {
            this.error(6);
        }
        if (n == 0 && n2 == sendMessage - 1) {
            this.clearAll();
        }
        else {
            LVITEM lvitem = null;
            boolean b = false;
            for (int i = n; i <= n2; ++i) {
                final TableItem getItem = this._getItem(i, false);
                if (getItem != null) {
                    if (getItem != this.currentItem) {
                        b = true;
                        getItem.clear();
                    }
                    if ((this.style & 0x10000000) == 0x0 && getItem.cached) {
                        if (lvitem == null) {
                            lvitem = new LVITEM();
                            lvitem.mask = 17;
                            lvitem.pszText = -1;
                        }
                        lvitem.iItem = i;
                        OS.SendMessage(this.handle, OS.LVM_SETITEM, 0, lvitem);
                        getItem.cached = false;
                    }
                }
            }
            if (b) {
                if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                    OS.SendMessage(this.handle, 4117, n, n2);
                }
                this.setScrollWidth((n == n2) ? this._getItem(n, false) : null, false);
            }
        }
    }
    
    public void clear(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        if (array.length == 0) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < 0 || array[i] >= sendMessage) {
                this.error(6);
            }
        }
        LVITEM lvitem = null;
        boolean b = false;
        for (int j = 0; j < array.length; ++j) {
            final int n = array[j];
            final TableItem getItem = this._getItem(n, false);
            if (getItem != null) {
                if (getItem != this.currentItem) {
                    b = true;
                    getItem.clear();
                }
                if ((this.style & 0x10000000) == 0x0 && getItem.cached) {
                    if (lvitem == null) {
                        lvitem = new LVITEM();
                        lvitem.mask = 17;
                        lvitem.pszText = -1;
                    }
                    lvitem.iItem = j;
                    OS.SendMessage(this.handle, OS.LVM_SETITEM, 0, lvitem);
                    getItem.cached = false;
                }
                if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                    OS.SendMessage(this.handle, 4117, n, n);
                }
            }
        }
        if (b) {
            this.setScrollWidth(null, false);
        }
    }
    
    public void clearAll() {
        this.checkWidget();
        LVITEM lvitem = null;
        boolean b = false;
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        for (int i = 0; i < sendMessage; ++i) {
            final TableItem getItem = this._getItem(i, false);
            if (getItem != null) {
                if (getItem != this.currentItem) {
                    b = true;
                    getItem.clear();
                }
                if ((this.style & 0x10000000) == 0x0 && getItem.cached) {
                    if (lvitem == null) {
                        lvitem = new LVITEM();
                        lvitem.mask = 17;
                        lvitem.pszText = -1;
                    }
                    lvitem.iItem = i;
                    OS.SendMessage(this.handle, OS.LVM_SETITEM, 0, lvitem);
                    getItem.cached = false;
                }
            }
        }
        if (b) {
            if (this.currentItem == null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
                OS.SendMessage(this.handle, 4117, 0, sendMessage - 1);
            }
            this.setScrollWidth(null, false);
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        if (this.fixScrollWidth) {
            this.setScrollWidth(null, true);
        }
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        final RECT rect = new RECT();
        OS.GetWindowRect(sendMessage, rect);
        final int n3 = rect.bottom - rect.top;
        final boolean b2 = false;
        int n4;
        if (n != -1) {
            n4 = ((b2 ? 1 : 0) | (n & 0xFFFF));
        }
        else {
            int n5 = 0;
            for (int sendMessage2 = OS.SendMessage(sendMessage, 4608, 0, 0), i = 0; i < sendMessage2; ++i) {
                n5 += OS.SendMessage(this.handle, 4125, i, 0);
            }
            n4 = ((b2 ? 1 : 0) | (n5 & 0xFFFF));
        }
        int loword = OS.LOWORD(OS.SendMessage(this.handle, 4160, -1, OS.MAKELPARAM(n4, 65535)));
        int n6 = n3 + OS.SendMessage(this.handle, 4100, 0, 0) * (OS.HIWORD(OS.SendMessage(this.handle, 4160, 1, 0)) - OS.HIWORD(OS.SendMessage(this.handle, 4160, 0, 0)));
        if (loword == 0) {
            loword = 64;
        }
        if (n6 == 0) {
            n6 = 64;
        }
        if (n != -1) {
            loword = n;
        }
        if (n2 != -1) {
            n6 = n2;
        }
        final int borderWidth = this.getBorderWidth();
        int n7 = loword + borderWidth * 2;
        int n8 = n6 + borderWidth * 2;
        if ((this.style & 0x200) != 0x0) {
            n7 += OS.GetSystemMetrics(2);
        }
        if ((this.style & 0x100) != 0x0) {
            n8 += OS.GetSystemMetrics(3);
        }
        return new Point(n7, n8);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.IsAppThemed()) {
            this.explorerTheme = true;
            OS.SetWindowTheme(this.handle, Display.EXPLORER, null);
        }
        if (Table.HeaderProc == 0) {
            Table.HeaderProc = OS.GetWindowLongPtr(OS.SendMessage(this.handle, 4127, 0, 0), -4);
        }
        if (!OS.IsWinCE && OS.COMCTL32_MAJOR < 6) {
            OS.SendMessage(this.handle, 8199, 5, 0);
        }
        if ((this.style & 0x20) != 0x0) {
            final int n = OS.HIWORD(OS.SendMessage(this.handle, 4160, 1, 0)) - OS.HIWORD(OS.SendMessage(this.handle, 4160, 0, 0));
            this.setCheckboxImageList(n, n, false);
            OS.SendMessage(this.handle, 4107, 61440, 0);
        }
        OS.SendMessage(this.handle, 48, OS.GetStockObject(13), 0);
        final LVCOLUMN lvcolumn = new LVCOLUMN();
        lvcolumn.mask = 6;
        final int getProcessHeap = OS.GetProcessHeap();
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, TCHAR.sizeof);
        lvcolumn.pszText = heapAlloc;
        OS.SendMessage(this.handle, OS.LVM_INSERTCOLUMN, 0, lvcolumn);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
        int n2 = 16384;
        if ((this.style & 0x10000) != 0x0) {
            n2 |= 0x20;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            n2 |= 0x10000;
        }
        OS.SendMessage(this.handle, 4150, n2, n2);
        if (OS.WIN32_VERSION >= OS.VERSION(4, 10) && (this.style & 0x4000000) != 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
            OS.SetWindowLong(sendMessage, -20, OS.GetWindowLong(sendMessage, -20) | 0x400000);
            final int sendMessage2 = OS.SendMessage(this.handle, 4174, 0, 0);
            OS.SetWindowLong(sendMessage2, -20, OS.GetWindowLong(sendMessage2, -20) | 0x400000);
        }
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
    
    void createItem(final TableColumn tableColumn, final int n) {
        if (n < 0 || n > this.columnCount) {
            this.error(6);
        }
        final int sendMessage = OS.SendMessage(this.handle, 4270, 0, 0);
        if (sendMessage >= n) {
            OS.SendMessage(this.handle, 4236, sendMessage + 1, 0);
        }
        if (this.columnCount == this.columns.length) {
            final TableColumn[] columns = new TableColumn[this.columns.length + 4];
            System.arraycopy(this.columns, 0, columns, 0, this.columns.length);
            this.columns = columns;
        }
        final int sendMessage2 = OS.SendMessage(this.handle, 4100, 0, 0);
        for (int i = 0; i < sendMessage2; ++i) {
            final TableItem getItem = this._getItem(i, false);
            if (getItem != null) {
                final String[] strings = getItem.strings;
                if (strings != null) {
                    final String[] strings2 = new String[this.columnCount + 1];
                    System.arraycopy(strings, 0, strings2, 0, n);
                    System.arraycopy(strings, n, strings2, n + 1, this.columnCount - n);
                    getItem.strings = strings2;
                }
                final Image[] images = getItem.images;
                if (images != null) {
                    final Image[] images2 = new Image[this.columnCount + 1];
                    System.arraycopy(images, 0, images2, 0, n);
                    System.arraycopy(images, n, images2, n + 1, this.columnCount - n);
                    getItem.images = images2;
                }
                if (n == 0 && this.columnCount != 0) {
                    if (strings == null) {
                        (getItem.strings = new String[this.columnCount + 1])[1] = getItem.text;
                    }
                    getItem.text = "";
                    if (images == null) {
                        (getItem.images = new Image[this.columnCount + 1])[1] = getItem.image;
                    }
                    getItem.image = null;
                }
                if (getItem.cellBackground != null) {
                    final int[] cellBackground = getItem.cellBackground;
                    final int[] cellBackground2 = new int[this.columnCount + 1];
                    System.arraycopy(cellBackground, 0, cellBackground2, 0, n);
                    System.arraycopy(cellBackground, n, cellBackground2, n + 1, this.columnCount - n);
                    cellBackground2[n] = -1;
                    getItem.cellBackground = cellBackground2;
                }
                if (getItem.cellForeground != null) {
                    final int[] cellForeground = getItem.cellForeground;
                    final int[] cellForeground2 = new int[this.columnCount + 1];
                    System.arraycopy(cellForeground, 0, cellForeground2, 0, n);
                    System.arraycopy(cellForeground, n, cellForeground2, n + 1, this.columnCount - n);
                    cellForeground2[n] = -1;
                    getItem.cellForeground = cellForeground2;
                }
                if (getItem.cellFont != null) {
                    final Font[] cellFont = getItem.cellFont;
                    final Font[] cellFont2 = new Font[this.columnCount + 1];
                    System.arraycopy(cellFont, 0, cellFont2, 0, n);
                    System.arraycopy(cellFont, n, cellFont2, n + 1, this.columnCount - n);
                    getItem.cellFont = cellFont2;
                }
            }
        }
        System.arraycopy(this.columns, n, this.columns, n + 1, this.columnCount++ - n);
        this.columns[n] = tableColumn;
        this.ignoreColumnResize = true;
        if (n == 0) {
            if (this.columnCount > 1) {
                final LVCOLUMN lvcolumn = new LVCOLUMN();
                lvcolumn.mask = 2;
                OS.SendMessage(this.handle, OS.LVM_INSERTCOLUMN, 1, lvcolumn);
                OS.SendMessage(this.handle, OS.LVM_GETCOLUMN, 1, lvcolumn);
                final int cx = lvcolumn.cx;
                final int cchTextMax = 1024;
                final int getProcessHeap = OS.GetProcessHeap();
                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, cchTextMax * TCHAR.sizeof);
                lvcolumn.mask = 23;
                lvcolumn.pszText = heapAlloc;
                lvcolumn.cchTextMax = cchTextMax;
                OS.SendMessage(this.handle, OS.LVM_GETCOLUMN, 0, lvcolumn);
                OS.SendMessage(this.handle, OS.LVM_SETCOLUMN, 1, lvcolumn);
                lvcolumn.fmt = 2048;
                lvcolumn.cx = cx;
                lvcolumn.iImage = -2;
                final LVCOLUMN lvcolumn2 = lvcolumn;
                final LVCOLUMN lvcolumn3 = lvcolumn;
                final boolean b = false;
                lvcolumn3.cchTextMax = (b ? 1 : 0);
                lvcolumn2.pszText = (b ? 1 : 0);
                OS.SendMessage(this.handle, OS.LVM_SETCOLUMN, 0, lvcolumn);
                lvcolumn.mask = 1;
                lvcolumn.fmt = 0;
                OS.SendMessage(this.handle, OS.LVM_SETCOLUMN, 0, lvcolumn);
                if (heapAlloc != 0) {
                    OS.HeapFree(getProcessHeap, 0, heapAlloc);
                }
            }
            else {
                OS.SendMessage(this.handle, 4126, 0, 0);
            }
            if ((this.style & 0x10000000) == 0x0) {
                final LVITEM lvitem = new LVITEM();
                lvitem.mask = 3;
                lvitem.pszText = -1;
                lvitem.iImage = -1;
                for (int j = 0; j < sendMessage2; ++j) {
                    lvitem.iItem = j;
                    OS.SendMessage(this.handle, OS.LVM_SETITEM, 0, lvitem);
                }
            }
        }
        else {
            int fmt = 0;
            if ((tableColumn.style & 0x1000000) == 0x1000000) {
                fmt = 2;
            }
            if ((tableColumn.style & 0x20000) == 0x20000) {
                fmt = 1;
            }
            final LVCOLUMN lvcolumn4 = new LVCOLUMN();
            lvcolumn4.mask = 3;
            lvcolumn4.fmt = fmt;
            OS.SendMessage(this.handle, OS.LVM_INSERTCOLUMN, n, lvcolumn4);
        }
        this.ignoreColumnResize = false;
        if (this.headerToolTipHandle != 0) {
            final RECT rect = new RECT();
            final int sendMessage3 = OS.SendMessage(this.handle, 4127, 0, 0);
            if (OS.SendMessage(sendMessage3, 4615, n, rect) != 0) {
                final TOOLINFO toolinfo = new TOOLINFO();
                toolinfo.cbSize = TOOLINFO.sizeof;
                toolinfo.uFlags = 16;
                toolinfo.hwnd = sendMessage3;
                final TOOLINFO toolinfo2 = toolinfo;
                final int n2 = this.display.nextToolTipId++;
                tableColumn.id = n2;
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
    
    void createItem(final TableItem tableItem, final int iItem) {
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (iItem < 0 || iItem > sendMessage) {
            this.error(6);
        }
        this._checkGrow(sendMessage);
        final LVITEM lvitem = new LVITEM();
        lvitem.mask = 3;
        lvitem.iItem = iItem;
        lvitem.pszText = -1;
        lvitem.iImage = -1;
        this.setDeferResize(true);
        final boolean b = true;
        this.ignoreShrink = b;
        this.ignoreSelect = b;
        final int sendMessage2 = OS.SendMessage(this.handle, OS.LVM_INSERTITEM, 0, lvitem);
        final boolean b2 = false;
        this.ignoreShrink = b2;
        this.ignoreSelect = b2;
        if (sendMessage2 == -1) {
            this.error(14);
        }
        this._insertItem(iItem, tableItem, sendMessage);
        this.setDeferResize(false);
        if (sendMessage == 0) {
            this.setScrollWidth(tableItem, false);
        }
    }
    
    void createWidget() {
        super.createWidget();
        final int n = -1;
        this.hotIndex = n;
        this.itemHeight = n;
        this._initItems();
        this.columns = new TableColumn[4];
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_WINDOW);
    }
    
    void deregister() {
        super.deregister();
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        if (sendMessage != 0) {
            this.display.removeControl(sendMessage);
        }
    }
    
    public void deselect(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        if (array.length == 0) {
            return;
        }
        final LVITEM lvitem = new LVITEM();
        lvitem.stateMask = 2;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] >= 0) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, array[i], lvitem);
                this.ignoreSelect = false;
            }
        }
    }
    
    public void deselect(final int n) {
        this.checkWidget();
        if (n < 0) {
            return;
        }
        final LVITEM lvitem = new LVITEM();
        lvitem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, n, lvitem);
        this.ignoreSelect = false;
    }
    
    public void deselect(int n, final int n2) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (n == 0 && n2 == sendMessage - 1) {
            this.deselectAll();
        }
        else {
            final LVITEM lvitem = new LVITEM();
            lvitem.stateMask = 2;
            int i;
            for (n = (i = Math.max(0, n)); i <= n2; ++i) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, i, lvitem);
                this.ignoreSelect = false;
            }
        }
    }
    
    public void deselectAll() {
        this.checkWidget();
        final LVITEM lvitem = new LVITEM();
        lvitem.mask = 8;
        lvitem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, -1, lvitem);
        this.ignoreSelect = false;
    }
    
    void destroyItem(final TableColumn tableColumn) {
        int n;
        for (n = 0; n < this.columnCount && this.columns[n] != tableColumn; ++n) {}
        final int sendMessage = OS.SendMessage(this.handle, 4270, 0, 0);
        if (sendMessage == n) {
            OS.SendMessage(this.handle, 4236, -1, 0);
        }
        else if (sendMessage > n) {
            OS.SendMessage(this.handle, 4236, sendMessage - 1, 0);
        }
        int n2 = 0;
        final int[] array = new int[this.columnCount];
        OS.SendMessage(this.handle, 4155, this.columnCount, array);
        while (n2 < this.columnCount && array[n2] != n) {
            ++n2;
        }
        this.ignoreColumnResize = true;
        boolean b = false;
        if (n == 0) {
            b = true;
            this.setRedraw(false);
            if (this.columnCount > 1) {
                n = 1;
                final int cchTextMax = 1024;
                final int getProcessHeap = OS.GetProcessHeap();
                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, cchTextMax * TCHAR.sizeof);
                final LVCOLUMN lvcolumn = new LVCOLUMN();
                lvcolumn.mask = 23;
                lvcolumn.pszText = heapAlloc;
                lvcolumn.cchTextMax = cchTextMax;
                OS.SendMessage(this.handle, OS.LVM_GETCOLUMN, 1, lvcolumn);
                final LVCOLUMN lvcolumn2 = lvcolumn;
                lvcolumn2.fmt &= 0xFFFFFFFC;
                final LVCOLUMN lvcolumn3 = lvcolumn;
                lvcolumn3.fmt |= 0x0;
                OS.SendMessage(this.handle, OS.LVM_SETCOLUMN, 0, lvcolumn);
                if (heapAlloc != 0) {
                    OS.HeapFree(getProcessHeap, 0, heapAlloc);
                }
            }
            else {
                final int getProcessHeap2 = OS.GetProcessHeap();
                final int heapAlloc2 = OS.HeapAlloc(getProcessHeap2, 8, TCHAR.sizeof);
                final LVCOLUMN lvcolumn4 = new LVCOLUMN();
                lvcolumn4.mask = 23;
                lvcolumn4.pszText = heapAlloc2;
                lvcolumn4.iImage = -2;
                lvcolumn4.fmt = 0;
                OS.SendMessage(this.handle, OS.LVM_SETCOLUMN, 0, lvcolumn4);
                if (heapAlloc2 != 0) {
                    OS.HeapFree(getProcessHeap2, 0, heapAlloc2);
                }
                if (OS.COMCTL32_MAJOR >= 6) {
                    final HDITEM hditem = new HDITEM();
                    hditem.mask = 4;
                    hditem.fmt = 0;
                    OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), OS.HDM_SETITEM, n, hditem);
                }
            }
            this.setRedraw(true);
            if ((this.style & 0x10000000) == 0x0) {
                final LVITEM lvitem = new LVITEM();
                lvitem.mask = 3;
                lvitem.pszText = -1;
                lvitem.iImage = -1;
                for (int sendMessage2 = OS.SendMessage(this.handle, 4100, 0, 0), i = 0; i < sendMessage2; ++i) {
                    lvitem.iItem = i;
                    OS.SendMessage(this.handle, OS.LVM_SETITEM, 0, lvitem);
                }
            }
        }
        if (this.columnCount > 1 && OS.SendMessage(this.handle, 4124, n, 0) == 0) {
            this.error(15);
        }
        if (b) {
            n = 0;
        }
        System.arraycopy(this.columns, n + 1, this.columns, n, --this.columnCount - n);
        this.columns[this.columnCount] = null;
        for (int sendMessage3 = OS.SendMessage(this.handle, 4100, 0, 0), j = 0; j < sendMessage3; ++j) {
            final TableItem getItem = this._getItem(j, false);
            if (getItem != null) {
                if (this.columnCount == 0) {
                    getItem.strings = null;
                    getItem.images = null;
                    getItem.cellBackground = null;
                    getItem.cellForeground = null;
                    getItem.cellFont = null;
                }
                else {
                    if (getItem.strings != null) {
                        final String[] strings = getItem.strings;
                        if (n == 0) {
                            getItem.text = ((strings[1] != null) ? strings[1] : "");
                        }
                        final String[] strings2 = new String[this.columnCount];
                        System.arraycopy(strings, 0, strings2, 0, n);
                        System.arraycopy(strings, n + 1, strings2, n, this.columnCount - n);
                        getItem.strings = strings2;
                    }
                    else if (n == 0) {
                        getItem.text = "";
                    }
                    if (getItem.images != null) {
                        final Image[] images = getItem.images;
                        if (n == 0) {
                            getItem.image = images[1];
                        }
                        final Image[] images2 = new Image[this.columnCount];
                        System.arraycopy(images, 0, images2, 0, n);
                        System.arraycopy(images, n + 1, images2, n, this.columnCount - n);
                        getItem.images = images2;
                    }
                    else if (n == 0) {
                        getItem.image = null;
                    }
                    if (getItem.cellBackground != null) {
                        final int[] cellBackground = getItem.cellBackground;
                        final int[] cellBackground2 = new int[this.columnCount];
                        System.arraycopy(cellBackground, 0, cellBackground2, 0, n);
                        System.arraycopy(cellBackground, n + 1, cellBackground2, n, this.columnCount - n);
                        getItem.cellBackground = cellBackground2;
                    }
                    if (getItem.cellForeground != null) {
                        final int[] cellForeground = getItem.cellForeground;
                        final int[] cellForeground2 = new int[this.columnCount];
                        System.arraycopy(cellForeground, 0, cellForeground2, 0, n);
                        System.arraycopy(cellForeground, n + 1, cellForeground2, n, this.columnCount - n);
                        getItem.cellForeground = cellForeground2;
                    }
                    if (getItem.cellFont != null) {
                        final Font[] cellFont = getItem.cellFont;
                        final Font[] cellFont2 = new Font[this.columnCount];
                        System.arraycopy(cellFont, 0, cellFont2, 0, n);
                        System.arraycopy(cellFont, n + 1, cellFont2, n, this.columnCount - n);
                        getItem.cellFont = cellFont2;
                    }
                }
            }
        }
        if (this.columnCount == 0) {
            this.setScrollWidth(null, true);
        }
        this.updateMoveable();
        this.ignoreColumnResize = false;
        if (this.columnCount != 0) {
            int n3 = 0;
            final int n4 = array[n2];
            final int[] array2 = new int[this.columnCount];
            for (int k = 0; k < array.length; ++k) {
                if (array[k] != n4) {
                    array2[n3++] = ((array[k] <= n4) ? array[k] : (array[k] - 1));
                }
            }
            OS.SendMessage(this.handle, 4155, this.columnCount, array);
            int n5;
            for (n5 = 0; n5 < array2.length && array[n5] == array2[n5]; ++n5) {}
            if (n5 != array2.length) {
                OS.SendMessage(this.handle, 4154, array2.length, array2);
                OS.InvalidateRect(this.handle, null, true);
            }
            final TableColumn[] array3 = new TableColumn[this.columnCount - n2];
            for (int l = n2; l < array2.length; ++l) {
                (array3[l - n2] = this.columns[array2[l]]).updateToolTip(array2[l]);
            }
            for (int n6 = 0; n6 < array3.length; ++n6) {
                if (!array3[n6].isDisposed()) {
                    array3[n6].sendEvent(10);
                }
            }
        }
        if (this.headerToolTipHandle != 0) {
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            toolinfo.uId = tableColumn.id;
            toolinfo.hwnd = OS.SendMessage(this.handle, 4127, 0, 0);
            OS.SendMessage(this.headerToolTipHandle, OS.TTM_DELTOOL, 0, toolinfo);
        }
    }
    
    void destroyItem(final TableItem tableItem) {
        int sendMessage;
        int n;
        for (sendMessage = OS.SendMessage(this.handle, 4100, 0, 0), n = 0; n < sendMessage && this._getItem(n, false) != tableItem; ++n) {}
        if (n == sendMessage) {
            return;
        }
        this.setDeferResize(true);
        final boolean b = true;
        this.ignoreShrink = b;
        this.ignoreSelect = b;
        final int sendMessage2 = OS.SendMessage(this.handle, 4104, n, 0);
        final boolean b2 = false;
        this.ignoreShrink = b2;
        this.ignoreSelect = b2;
        if (sendMessage2 == 0) {
            this.error(15);
        }
        this._removeItem(n, sendMessage);
        if (--sendMessage == 0) {
            this.setTableEmpty();
        }
        this.setDeferResize(false);
    }
    
    void fixCheckboxImageList(final boolean b) {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4098, 1, 0);
        if (sendMessage == 0) {
            return;
        }
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.ImageList_GetIconSize(sendMessage, array, array2);
        final int sendMessage2 = OS.SendMessage(this.handle, 4098, 2, 0);
        if (sendMessage2 == 0) {
            return;
        }
        final int[] array3 = { 0 };
        final int[] array4 = { 0 };
        OS.ImageList_GetIconSize(sendMessage2, array3, array4);
        if (array[0] == array3[0] && array2[0] == array4[0]) {
            return;
        }
        this.setCheckboxImageList(array[0], array2[0], b);
    }
    
    void fixCheckboxImageListColor(final boolean b) {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4098, 2, 0);
        if (sendMessage == 0) {
            return;
        }
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.ImageList_GetIconSize(sendMessage, array, array2);
        this.setCheckboxImageList(array[0], array2[0], b);
    }
    
    void fixItemHeight(final boolean b) {
        if (this.itemHeight != -1) {
            return;
        }
        if (OS.COMCTL32_VERSION >= OS.VERSION(5, 80)) {
            return;
        }
        if ((OS.SendMessage(this.handle, 4151, 0, 0) & 0x1) == 0x0) {
            return;
        }
        if ((OS.GetWindowLong(this.handle, -16) & 0x4000) != 0x0) {
            return;
        }
        final int topIndex = this.getTopIndex();
        if (b && topIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        if (OS.SendMessage(this.handle, 4098, 1, 0) != 0) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        final RECT rect = new RECT();
        OS.GetWindowRect(sendMessage, rect);
        final int imageList_Create = OS.ImageList_Create(1, rect.bottom - rect.top - 1, 0, 0, 0);
        OS.SendMessage(this.handle, 4099, 1, imageList_Create);
        this.fixCheckboxImageList(false);
        OS.SendMessage(this.handle, 4099, 1, 0);
        if (this.headerImageList != null) {
            OS.SendMessage(sendMessage, 4616, 0, this.headerImageList.getHandle());
        }
        OS.ImageList_Destroy(imageList_Create);
        if (b && topIndex != 0) {
            this.setTopIndex(topIndex);
            this.setRedraw(true);
        }
    }
    
    public TableColumn getColumn(final int n) {
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
        OS.SendMessage(this.handle, 4155, this.columnCount, array);
        return array;
    }
    
    public TableColumn[] getColumns() {
        this.checkWidget();
        final TableColumn[] array = new TableColumn[this.columnCount];
        System.arraycopy(this.columns, 0, array, 0, this.columnCount);
        return array;
    }
    
    int getFocusIndex() {
        return OS.SendMessage(this.handle, 4108, -1, 1);
    }
    
    public int getGridLineWidth() {
        this.checkWidget();
        return 1;
    }
    
    public int getHeaderHeight() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        if (sendMessage == 0) {
            return 0;
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(sendMessage, rect);
        return rect.bottom - rect.top;
    }
    
    public boolean getHeaderVisible() {
        this.checkWidget();
        return (OS.GetWindowLong(this.handle, -16) & 0x4000) == 0x0;
    }
    
    public TableItem getItem(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (n < 0 || n >= sendMessage) {
            this.error(6);
        }
        return this._getItem(n);
    }
    
    public TableItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        if (OS.SendMessage(this.handle, 4100, 0, 0) == 0) {
            return null;
        }
        final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
        lvhittestinfo.x = point.x;
        lvhittestinfo.y = point.y;
        if ((this.style & 0x10000) == 0x0 && this.hooks(41)) {
            if (OS.SendMessage(this.handle, 4153, 0, lvhittestinfo) < 0) {
                final RECT rect = new RECT();
                rect.left = 1;
                this.ignoreCustomDraw = true;
                final int sendMessage = OS.SendMessage(this.handle, 4110, 0, rect);
                this.ignoreCustomDraw = false;
                if (sendMessage != 0) {
                    lvhittestinfo.x = rect.left;
                    OS.SendMessage(this.handle, 4153, 0, lvhittestinfo);
                    if (lvhittestinfo.iItem < 0) {
                        lvhittestinfo.iItem = -1;
                    }
                }
            }
            if (lvhittestinfo.iItem != -1 && lvhittestinfo.iSubItem == 0 && this.hitTestSelection(lvhittestinfo.iItem, lvhittestinfo.x, lvhittestinfo.y)) {
                return this._getItem(lvhittestinfo.iItem);
            }
            return null;
        }
        else {
            OS.SendMessage(this.handle, 4114, 0, lvhittestinfo);
            if (lvhittestinfo.iItem != -1) {
                if (lvhittestinfo.iItem == 0 && (OS.GetWindowLong(this.handle, -16) & 0x4000) == 0x0) {
                    final int sendMessage2 = OS.SendMessage(this.handle, 4127, 0, 0);
                    if (sendMessage2 != 0) {
                        final RECT rect2 = new RECT();
                        OS.GetWindowRect(sendMessage2, rect2);
                        final POINT point2 = new POINT();
                        point2.x = lvhittestinfo.x;
                        point2.y = lvhittestinfo.y;
                        OS.MapWindowPoints(this.handle, 0, point2, 1);
                        if (OS.PtInRect(rect2, point2)) {
                            return null;
                        }
                    }
                }
                return this._getItem(lvhittestinfo.iItem);
            }
            return null;
        }
    }
    
    public int getItemCount() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 4100, 0, 0);
    }
    
    public int getItemHeight() {
        this.checkWidget();
        if (!this.painted && this.hooks(41)) {
            this.hitTestSelection(0, 0, 0);
        }
        return OS.HIWORD(OS.SendMessage(this.handle, 4160, 1, 0)) - OS.HIWORD(OS.SendMessage(this.handle, 4160, 0, 0));
    }
    
    public TableItem[] getItems() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        final TableItem[] array = new TableItem[sendMessage];
        if ((this.style & 0x10000000) != 0x0) {
            for (int i = 0; i < sendMessage; ++i) {
                array[i] = this._getItem(i);
            }
        }
        else {
            this._getItems(array, sendMessage);
        }
        return array;
    }
    
    public boolean getLinesVisible() {
        this.checkWidget();
        return (OS.SendMessage(this.handle, 4151, 0, 0) & 0x1) != 0x0;
    }
    
    public TableItem[] getSelection() {
        this.checkWidget();
        int sendMessage = -1;
        int n = 0;
        final TableItem[] array = new TableItem[OS.SendMessage(this.handle, 4146, 0, 0)];
        while ((sendMessage = OS.SendMessage(this.handle, 4108, sendMessage, 2)) != -1) {
            array[n++] = this._getItem(sendMessage);
        }
        return array;
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 4146, 0, 0);
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4108, -1, 1);
        final int sendMessage2 = OS.SendMessage(this.handle, 4108, -1, 2);
        if (sendMessage == sendMessage2) {
            return sendMessage2;
        }
        int sendMessage3 = -1;
        while ((sendMessage3 = OS.SendMessage(this.handle, 4108, sendMessage3, 2)) != -1) {
            if (sendMessage3 == sendMessage) {
                return sendMessage3;
            }
        }
        return sendMessage2;
    }
    
    public int[] getSelectionIndices() {
        this.checkWidget();
        int sendMessage = -1;
        int n = 0;
        final int[] array = new int[OS.SendMessage(this.handle, 4146, 0, 0)];
        while ((sendMessage = OS.SendMessage(this.handle, 4108, sendMessage, 2)) != -1) {
            array[n++] = sendMessage;
        }
        return array;
    }
    
    public TableColumn getSortColumn() {
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
    
    public int getTopIndex() {
        this.checkWidget();
        return Math.max(0, OS.SendMessage(this.handle, 4135, 0, 0));
    }
    
    boolean hasChildren() {
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        for (int i = OS.GetWindow(this.handle, 5); i != 0; i = OS.GetWindow(i, 2)) {
            if (i != sendMessage) {
                return true;
            }
        }
        return false;
    }
    
    boolean hitTestSelection(final int n, final int n2, final int n3) {
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (sendMessage == 0) {
            return false;
        }
        if (!this.hooks(41)) {
            return false;
        }
        boolean b = false;
        if (n >= 0 && n < sendMessage) {
            final TableItem getItem = this._getItem(n);
            final int getDC = OS.GetDC(this.handle);
            int selectObject = 0;
            final int sendMessage2 = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage2 != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage2);
            }
            int n4 = getItem.fontHandle(0);
            if (n4 != -1) {
                n4 = OS.SelectObject(getDC, n4);
            }
            if (this.sendMeasureItemEvent(getItem, n, 0, getDC).getBounds().contains(n2, n3)) {
                b = true;
            }
            if (n4 != -1) {
                OS.SelectObject(getDC, n4);
            }
            if (sendMessage2 != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
        }
        return b;
    }
    
    int imageIndex(final Image image, final int n) {
        if (image == null) {
            return -2;
        }
        if (n == 0) {
            this.firstColumnImage = true;
        }
        else {
            this.setSubImagesVisible(true);
        }
        if (this.imageList == null) {
            final Rectangle bounds = image.getBounds();
            this.imageList = this.display.getImageList(0, bounds.width, bounds.height);
            int n2 = this.imageList.indexOf(image);
            if (n2 == -1) {
                n2 = this.imageList.add(image);
            }
            final int handle = this.imageList.getHandle();
            final int topIndex = this.getTopIndex();
            if (topIndex != 0) {
                this.setRedraw(false);
                this.setTopIndex(0);
            }
            OS.SendMessage(this.handle, 4099, 1, handle);
            if (this.headerImageList != null) {
                OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 4616, 0, this.headerImageList.getHandle());
            }
            this.fixCheckboxImageList(false);
            this.setItemHeight(false);
            if (topIndex != 0) {
                this.setTopIndex(topIndex);
                this.setRedraw(true);
            }
            return n2;
        }
        final int index = this.imageList.indexOf(image);
        if (index != -1) {
            return index;
        }
        return this.imageList.add(image);
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
            OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 4616, 0, this.headerImageList.getHandle());
            return n;
        }
        final int index = this.headerImageList.indexOf(image);
        if (index != -1) {
            return index;
        }
        return this.headerImageList.add(image);
    }
    
    public int indexOf(final TableColumn tableColumn) {
        this.checkWidget();
        if (tableColumn == null) {
            this.error(4);
        }
        for (int i = 0; i < this.columnCount; ++i) {
            if (this.columns[i] == tableColumn) {
                return i;
            }
        }
        return -1;
    }
    
    public int indexOf(final TableItem tableItem) {
        this.checkWidget();
        if (tableItem == null) {
            this.error(4);
        }
        if (this.keys == null) {
            final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
            if (1 <= this.lastIndexOf && this.lastIndexOf < sendMessage - 1) {
                if (this._getItem(this.lastIndexOf, false) == tableItem) {
                    return this.lastIndexOf;
                }
                if (this._getItem(this.lastIndexOf + 1, false) == tableItem) {
                    return ++this.lastIndexOf;
                }
                if (this._getItem(this.lastIndexOf - 1, false) == tableItem) {
                    return --this.lastIndexOf;
                }
            }
            if (this.lastIndexOf < sendMessage / 2) {
                for (int i = 0; i < sendMessage; ++i) {
                    if (this._getItem(i, false) == tableItem) {
                        return this.lastIndexOf = i;
                    }
                }
            }
            else {
                for (int j = sendMessage - 1; j >= 0; --j) {
                    if (this._getItem(j, false) == tableItem) {
                        return this.lastIndexOf = j;
                    }
                }
            }
        }
        else {
            for (int k = 0; k < this.keyCount; ++k) {
                if (this.items[k] == tableItem) {
                    return this.keys[k];
                }
            }
        }
        return -1;
    }
    
    boolean isCustomToolTip() {
        return this.hooks(41);
    }
    
    boolean isOptimizedRedraw() {
        return (this.style & 0x100) != 0x0 && (this.style & 0x200) != 0x0 && (!this.hasChildren() && !this.hooks(9) && !this.filters(9));
    }
    
    public boolean isSelected(final int iItem) {
        this.checkWidget();
        final LVITEM lvitem = new LVITEM();
        lvitem.mask = 8;
        lvitem.stateMask = 2;
        lvitem.iItem = iItem;
        return OS.SendMessage(this.handle, OS.LVM_GETITEM, 0, lvitem) != 0 && (lvitem.state & 0x2) != 0x0;
    }
    
    void register() {
        super.register();
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        if (sendMessage != 0) {
            this.display.addControl(sendMessage, this);
        }
    }
    
    void releaseChildren(final boolean b) {
        if (this._hasItems()) {
            final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
            if (OS.IsWin95 && this.columnCount > 1) {
                this.resizeCount = 1;
                OS.SendMessage(this.handle, 11, 0, 0);
                for (int i = sendMessage - 1; i >= 0; --i) {
                    final TableItem getItem = this._getItem(i, false);
                    if (getItem != null && !getItem.isDisposed()) {
                        getItem.release(false);
                    }
                    final boolean b2 = true;
                    this.ignoreShrink = b2;
                    this.ignoreSelect = b2;
                    OS.SendMessage(this.handle, 4104, i, 0);
                    final boolean b3 = false;
                    this.ignoreShrink = b3;
                    this.ignoreSelect = b3;
                }
            }
            else if (this.keys == null) {
                for (int j = 0; j < sendMessage; ++j) {
                    final TableItem getItem2 = this._getItem(j, false);
                    if (getItem2 != null && !getItem2.isDisposed()) {
                        getItem2.release(false);
                    }
                }
            }
            else {
                for (int k = 0; k < this.keyCount; ++k) {
                    final TableItem tableItem = this.items[k];
                    if (tableItem != null && !tableItem.isDisposed()) {
                        tableItem.release(false);
                    }
                }
            }
            this._clearItems();
        }
        if (this.columns != null) {
            for (int l = 0; l < this.columnCount; ++l) {
                final TableColumn tableColumn = this.columns[l];
                if (!tableColumn.isDisposed()) {
                    tableColumn.release(false);
                }
            }
            this.columns = null;
        }
        super.releaseChildren(b);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.customDraw = false;
        this.currentItem = null;
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 4099, 1, 0);
            this.display.releaseImageList(this.imageList);
        }
        if (this.headerImageList != null) {
            OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 4616, 0, 0);
            this.display.releaseImageList(this.headerImageList);
        }
        final ImageList list = null;
        this.headerImageList = list;
        this.imageList = list;
        final int sendMessage = OS.SendMessage(this.handle, 4098, 2, 0);
        OS.SendMessage(this.handle, 4099, 2, 0);
        if (sendMessage != 0) {
            OS.ImageList_Destroy(sendMessage);
        }
        if (this.headerToolTipHandle != 0) {
            OS.DestroyWindow(this.headerToolTipHandle);
        }
        this.headerToolTipHandle = 0;
    }
    
    public void remove(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        if (array.length == 0) {
            return;
        }
        final int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        this.sort(array2);
        final int n = array2[array2.length - 1];
        final int n2 = array2[0];
        int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (n < 0 || n > n2 || n2 >= sendMessage) {
            this.error(6);
        }
        this.setDeferResize(true);
        int n3 = -1;
        for (int i = 0; i < array2.length; ++i) {
            final int n4 = array2[i];
            if (n4 != n3) {
                final TableItem getItem = this._getItem(n4, false);
                if (getItem != null && !getItem.isDisposed()) {
                    getItem.release(false);
                }
                final boolean b = true;
                this.ignoreShrink = b;
                this.ignoreSelect = b;
                final int sendMessage2 = OS.SendMessage(this.handle, 4104, n4, 0);
                final boolean b2 = false;
                this.ignoreShrink = b2;
                this.ignoreSelect = b2;
                if (sendMessage2 == 0) {
                    this.error(15);
                }
                this._removeItem(n4, sendMessage);
                --sendMessage;
                n3 = n4;
            }
        }
        if (sendMessage == 0) {
            this.setTableEmpty();
        }
        this.setDeferResize(false);
    }
    
    public void remove(final int n) {
        this.checkWidget();
        int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (n < 0 || n >= sendMessage) {
            this.error(6);
        }
        final TableItem getItem = this._getItem(n, false);
        if (getItem != null && !getItem.isDisposed()) {
            getItem.release(false);
        }
        this.setDeferResize(true);
        final boolean b = true;
        this.ignoreShrink = b;
        this.ignoreSelect = b;
        final int sendMessage2 = OS.SendMessage(this.handle, 4104, n, 0);
        final boolean b2 = false;
        this.ignoreShrink = b2;
        this.ignoreSelect = b2;
        if (sendMessage2 == 0) {
            this.error(15);
        }
        this._removeItem(n, sendMessage);
        if (--sendMessage == 0) {
            this.setTableEmpty();
        }
        this.setDeferResize(false);
    }
    
    public void remove(final int n, final int n2) {
        this.checkWidget();
        if (n > n2) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (n < 0 || n > n2 || n2 >= sendMessage) {
            this.error(6);
        }
        if (n == 0 && n2 == sendMessage - 1) {
            this.removeAll();
        }
        else {
            this.setDeferResize(true);
            int i;
            for (i = n; i <= n2; ++i) {
                final TableItem getItem = this._getItem(i, false);
                if (getItem != null && !getItem.isDisposed()) {
                    getItem.release(false);
                }
                final boolean b = true;
                this.ignoreShrink = b;
                this.ignoreSelect = b;
                final int sendMessage2 = OS.SendMessage(this.handle, 4104, n, 0);
                final boolean b2 = false;
                this.ignoreShrink = b2;
                this.ignoreSelect = b2;
                if (sendMessage2 == 0) {
                    break;
                }
            }
            this._removeItems(n, i, sendMessage);
            if (i <= n2) {
                this.error(15);
            }
            this.setDeferResize(false);
        }
    }
    
    public void removeAll() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        for (int i = 0; i < sendMessage; ++i) {
            final TableItem getItem = this._getItem(i, false);
            if (getItem != null && !getItem.isDisposed()) {
                getItem.release(false);
            }
        }
        this.setDeferResize(true);
        if (OS.IsWin95 && this.columnCount > 1) {
            final boolean b = this.getDrawing() && OS.IsWindowVisible(this.handle);
            if (b) {
                OS.SendMessage(this.handle, 11, 0, 0);
            }
            int j;
            for (j = sendMessage - 1; j >= 0; --j) {
                final boolean b2 = true;
                this.ignoreShrink = b2;
                this.ignoreSelect = b2;
                final int sendMessage2 = OS.SendMessage(this.handle, 4104, j, 0);
                final boolean b3 = false;
                this.ignoreShrink = b3;
                this.ignoreSelect = b3;
                if (sendMessage2 == 0) {
                    break;
                }
            }
            if (b) {
                OS.SendMessage(this.handle, 11, 1, 0);
            }
            if (j != -1) {
                this.error(15);
            }
        }
        else {
            final boolean b4 = true;
            this.ignoreShrink = b4;
            this.ignoreSelect = b4;
            final int sendMessage3 = OS.SendMessage(this.handle, 4105, 0, 0);
            final boolean b5 = false;
            this.ignoreShrink = b5;
            this.ignoreSelect = b5;
            if (sendMessage3 == 0) {
                this.error(15);
            }
        }
        this.setTableEmpty();
        this.setDeferResize(false);
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
    
    public void select(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        final int length = array.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        final LVITEM lvitem = new LVITEM();
        lvitem.state = 2;
        lvitem.stateMask = 2;
        for (int i = length - 1; i >= 0; --i) {
            if (array[i] >= 0) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, array[i], lvitem);
                this.ignoreSelect = false;
            }
        }
    }
    
    void reskinChildren(final int n) {
        if (this._hasItems()) {
            for (int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0), i = 0; i < sendMessage; ++i) {
                final TableItem getItem = this._getItem(i, false);
                if (getItem != null) {
                    getItem.reskin(n);
                }
            }
        }
        if (this.columns != null) {
            for (int j = 0; j < this.columnCount; ++j) {
                final TableColumn tableColumn = this.columns[j];
                if (!tableColumn.isDisposed()) {
                    tableColumn.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    public void select(final int n) {
        this.checkWidget();
        if (n < 0) {
            return;
        }
        final LVITEM lvitem = new LVITEM();
        lvitem.state = 2;
        lvitem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, n, lvitem);
        this.ignoreSelect = false;
    }
    
    public void select(int max, int min) {
        this.checkWidget();
        if (min < 0 || max > min || ((this.style & 0x4) != 0x0 && max != min)) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (sendMessage == 0 || max >= sendMessage) {
            return;
        }
        max = Math.max(0, max);
        min = Math.min(min, sendMessage - 1);
        if (max == 0 && min == sendMessage - 1) {
            this.selectAll();
        }
        else {
            final LVITEM lvitem = new LVITEM();
            lvitem.state = 2;
            lvitem.stateMask = 2;
            for (int i = max; i <= min; ++i) {
                this.ignoreSelect = true;
                OS.SendMessage(this.handle, 4139, i, lvitem);
                this.ignoreSelect = false;
            }
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        final LVITEM lvitem = new LVITEM();
        lvitem.mask = 8;
        lvitem.state = 2;
        lvitem.stateMask = 2;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, -1, lvitem);
        this.ignoreSelect = false;
    }
    
    void sendEraseItemEvent(final TableItem item, final NMLVCUSTOMDRAW nmlvcustomdraw, final int n, final Event event) {
        final int hdc = nmlvcustomdraw.hdc;
        int n2 = (item.cellForeground != null) ? item.cellForeground[nmlvcustomdraw.iSubItem] : -1;
        if (n2 == -1) {
            n2 = item.foreground;
        }
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.sortColumn != null && this.sortDirection != 0 && this.findImageControl() == null && this.indexOf(this.sortColumn) == nmlvcustomdraw.iSubItem) {
            this.getSortColumnPixel();
        }
        int n3 = (item.cellBackground != null) ? item.cellBackground[nmlvcustomdraw.iSubItem] : -1;
        if (n3 == -1) {
            n3 = item.background;
        }
        final LVITEM lvitem = new LVITEM();
        lvitem.mask = 8;
        lvitem.stateMask = 2;
        lvitem.iItem = nmlvcustomdraw.dwItemSpec;
        final boolean b = OS.SendMessage(this.handle, OS.LVM_GETITEM, 0, lvitem) != 0 && (lvitem.state & 0x2) != 0x0;
        final GCData gcData = new GCData();
        gcData.device = this.display;
        int background = -1;
        boolean b2 = false;
        boolean b3 = false;
        boolean b4 = false;
        boolean b5 = false;
        if (nmlvcustomdraw.iSubItem == 0 || (this.style & 0x10000) != 0x0) {
            b4 = (this.hotIndex == nmlvcustomdraw.dwItemSpec);
            b5 = ((nmlvcustomdraw.uItemState & 0x1000) != 0x0);
        }
        if (OS.IsWindowEnabled(this.handle)) {
            if (b && (nmlvcustomdraw.iSubItem == 0 || (this.style & 0x10000) != 0x0)) {
                if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                    b2 = true;
                    gcData.foreground = OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT);
                    background = (gcData.background = OS.GetSysColor(OS.COLOR_HIGHLIGHT));
                }
                else {
                    b2 = ((this.style & 0x8000) == 0x0);
                    gcData.foreground = OS.GetTextColor(hdc);
                    background = (gcData.background = OS.GetSysColor(OS.COLOR_3DFACE));
                }
                if (this.explorerTheme) {
                    gcData.foreground = ((n2 != -1) ? n2 : this.getForegroundPixel());
                }
            }
            else {
                b3 = (n3 != -1);
                if (n2 == -1 || n3 == -1) {
                    Control backgroundControl = this.findBackgroundControl();
                    if (backgroundControl == null) {
                        backgroundControl = this;
                    }
                    if (n2 == -1) {
                        n2 = backgroundControl.getForegroundPixel();
                    }
                    if (n3 == -1) {
                        n3 = backgroundControl.getBackgroundPixel();
                    }
                }
                gcData.foreground = ((n2 != -1) ? n2 : OS.GetTextColor(hdc));
                gcData.background = ((n3 != -1) ? n3 : OS.GetBkColor(hdc));
            }
        }
        else {
            gcData.foreground = OS.GetSysColor(OS.COLOR_GRAYTEXT);
            gcData.background = OS.GetSysColor(OS.COLOR_3DFACE);
            if (b) {
                background = gcData.background;
            }
        }
        gcData.font = item.getFont(nmlvcustomdraw.iSubItem);
        gcData.uiState = OS.SendMessage(this.handle, 297, 0, 0);
        final int saveDC = OS.SaveDC(hdc);
        final GC win32_new = GC.win32_new(hdc, gcData);
        final RECT bounds = item.getBounds(nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, true, true, true, true, hdc);
        final Event event2 = new Event();
        event2.item = item;
        event2.gc = win32_new;
        event2.index = nmlvcustomdraw.iSubItem;
        final Event event3 = event2;
        event3.detail |= 0x10;
        if (OS.SendMessage(this.handle, 4108, -1, 1) == nmlvcustomdraw.dwItemSpec && (nmlvcustomdraw.iSubItem == 0 || (this.style & 0x10000) != 0x0) && this.handle == OS.GetFocus() && (OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0) {
            final Event event4 = event2;
            event4.detail |= 0x4;
        }
        final boolean b6 = (event2.detail & 0x4) != 0x0;
        if (b4) {
            final Event event5 = event2;
            event5.detail |= 0x20;
        }
        if (b2) {
            final Event event6 = event2;
            event6.detail |= 0x2;
        }
        if (b3) {
            final Event event7 = event2;
            event7.detail |= 0x8;
        }
        event2.x = bounds.left;
        event2.y = bounds.top;
        event2.width = bounds.right - bounds.left;
        event2.height = bounds.bottom - bounds.top;
        win32_new.setClipping(event2.x, event2.y, event2.width, event2.height);
        this.sendEvent(40, event2);
        event2.gc = null;
        final int foreground = gcData.foreground;
        win32_new.dispose();
        OS.RestoreDC(hdc, saveDC);
        if (this.isDisposed() || item.isDisposed()) {
            return;
        }
        if (event2.doit) {
            this.ignoreDrawForeground = ((event2.detail & 0x10) == 0x0);
            this.ignoreDrawBackground = ((event2.detail & 0x8) == 0x0);
            this.ignoreDrawSelection = ((event2.detail & 0x2) == 0x0);
            this.ignoreDrawFocus = ((event2.detail & 0x4) == 0x0);
            this.ignoreDrawHot = ((event2.detail & 0x20) == 0x0);
        }
        else {
            final boolean ignoreDrawForeground = true;
            this.ignoreDrawHot = ignoreDrawForeground;
            this.ignoreDrawFocus = ignoreDrawForeground;
            this.ignoreDrawSelection = ignoreDrawForeground;
            this.ignoreDrawBackground = ignoreDrawForeground;
            this.ignoreDrawForeground = ignoreDrawForeground;
        }
        if (b2) {
            if (this.ignoreDrawSelection) {
                this.ignoreDrawHot = true;
                if (nmlvcustomdraw.iSubItem == 0 || (this.style & 0x10000) != 0x0) {
                    this.selectionForeground = foreground;
                }
                nmlvcustomdraw.uItemState &= 0xFFFFFFFE;
                OS.MoveMemory(n, nmlvcustomdraw, NMLVCUSTOMDRAW.sizeof);
            }
        }
        else if (this.ignoreDrawSelection) {
            nmlvcustomdraw.uItemState |= 0x1;
            OS.MoveMemory(n, nmlvcustomdraw, NMLVCUSTOMDRAW.sizeof);
        }
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        final boolean b7 = nmlvcustomdraw.iSubItem == OS.SendMessage(sendMessage, 4623, 0, 0);
        if (this.ignoreDrawForeground && this.ignoreDrawHot && !b5 && !this.ignoreDrawBackground && b3) {
            this.fillBackground(hdc, n3, item.getBounds(nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, true, false, true, false, hdc));
        }
        this.focusRect = null;
        if (!this.ignoreDrawHot || !this.ignoreDrawSelection || !this.ignoreDrawFocus || b5) {
            final RECT bounds2 = item.getBounds(nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, true, false, (this.style & 0x10000) != 0x0 || !b7, false, hdc);
            if ((this.style & 0x10000) == 0x0) {
                if (event != null) {
                    bounds2.right = Math.min(bounds.right, event.x + event.width);
                }
                if (!this.ignoreDrawFocus) {
                    nmlvcustomdraw.uItemState &= 0xFFFFFFEF;
                    OS.MoveMemory(n, nmlvcustomdraw, NMLVCUSTOMDRAW.sizeof);
                    this.focusRect = bounds2;
                }
            }
            if (this.explorerTheme) {
                if (!this.ignoreDrawHot || b5 || (!this.ignoreDrawSelection && background != -1)) {
                    final RECT rect = new RECT();
                    OS.SetRect(rect, nmlvcustomdraw.left, nmlvcustomdraw.top, nmlvcustomdraw.right, nmlvcustomdraw.bottom);
                    final RECT rect2 = new RECT();
                    OS.SetRect(rect2, nmlvcustomdraw.left, nmlvcustomdraw.top, nmlvcustomdraw.right, nmlvcustomdraw.bottom);
                    if ((this.style & 0x10000) != 0x0) {
                        final int sendMessage2 = OS.SendMessage(sendMessage, 4623, OS.SendMessage(sendMessage, 4608, 0, 0) - 1, 0);
                        final RECT rect3 = new RECT();
                        OS.SendMessage(sendMessage, 4615, sendMessage2, rect3);
                        OS.MapWindowPoints(sendMessage, this.handle, rect3, 2);
                        rect2.right = rect3.right;
                        OS.SendMessage(sendMessage, 4615, OS.SendMessage(sendMessage, 4623, 0, 0), rect3);
                        OS.MapWindowPoints(sendMessage, this.handle, rect3, 2);
                        rect2.left = rect3.left;
                        rect.left = bounds.left;
                        final RECT rect4 = rect;
                        rect4.right += 2;
                    }
                    else {
                        final RECT rect5 = rect2;
                        rect5.right += 2;
                        final RECT rect6 = rect;
                        rect6.right += 2;
                    }
                    final int openThemeData = OS.OpenThemeData(this.handle, Display.LISTVIEW);
                    int n4 = b ? 3 : 2;
                    if (OS.GetFocus() != this.handle && b && !b4) {
                        n4 = 5;
                    }
                    if (b5) {
                        n4 = 3;
                    }
                    OS.DrawThemeBackground(openThemeData, hdc, 1, n4, rect2, rect);
                    OS.CloseThemeData(openThemeData);
                }
            }
            else if (!this.ignoreDrawSelection && background != -1) {
                this.fillBackground(hdc, background, bounds2);
            }
        }
        if (b6 && this.ignoreDrawFocus) {
            nmlvcustomdraw.uItemState &= 0xFFFFFFEF;
            OS.MoveMemory(n, nmlvcustomdraw, NMLVCUSTOMDRAW.sizeof);
        }
        if (this.ignoreDrawForeground) {
            final RECT bounds3 = item.getBounds(nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, true, true, true, false, hdc);
            OS.SaveDC(hdc);
            OS.SelectClipRgn(hdc, 0);
            OS.ExcludeClipRect(hdc, bounds3.left, bounds3.top, bounds3.right, bounds3.bottom);
        }
    }
    
    Event sendEraseItemEvent(final TableItem item, final NMTTCUSTOMDRAW nmttcustomdraw, final int index, final RECT rect) {
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
    
    Event sendMeasureItemEvent(final TableItem item, final int iItem, final int index, final int n) {
        final GCData gcData = new GCData();
        gcData.device = this.display;
        gcData.font = item.getFont(index);
        final int saveDC = OS.SaveDC(n);
        final GC win32_new = GC.win32_new(n, gcData);
        final RECT bounds = item.getBounds(iItem, index, true, true, false, false, n);
        final Event event = new Event();
        event.item = item;
        event.gc = win32_new;
        event.index = index;
        event.x = bounds.left;
        event.y = bounds.top;
        event.width = bounds.right - bounds.left;
        event.height = bounds.bottom - bounds.top;
        int n2 = 0;
        if (OS.IsWindowEnabled(this.handle)) {
            final LVITEM lvitem = new LVITEM();
            lvitem.mask = 8;
            lvitem.stateMask = 2;
            lvitem.iItem = iItem;
            if (OS.SendMessage(this.handle, OS.LVM_GETITEM, 0, lvitem) != 0 && (lvitem.state & 0x2) != 0x0 && (index == 0 || (this.style & 0x10000) != 0x0)) {
                n2 = ((OS.GetFocus() == this.handle || this.display.getHighContrast() || (this.style & 0x8000) == 0x0) ? 1 : 0);
            }
        }
        if (n2 != 0) {
            final Event event2 = event;
            event2.detail |= 0x2;
        }
        this.sendEvent(41, event);
        event.gc = null;
        win32_new.dispose();
        OS.RestoreDC(n, saveDC);
        if (!this.isDisposed() && !item.isDisposed()) {
            if (this.columnCount == 0 && event.x + event.width > OS.SendMessage(this.handle, 4125, 0, 0)) {
                this.setScrollWidth(event.x + event.width);
            }
            if (event.height > OS.HIWORD(OS.SendMessage(this.handle, 4160, 1, 0)) - OS.HIWORD(OS.SendMessage(this.handle, 4160, 0, 0))) {
                this.setItemHeight(event.height);
            }
        }
        return event;
    }
    
    LRESULT sendMouseDownEvent(final int n, final int n2, final int n3, final int n4, final int n5) {
        final Display display = this.display;
        display.captureChanged = false;
        if (!this.sendMouseEvent(n, n2, this.handle, n3, n4, n5)) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return LRESULT.ZERO;
        }
        final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
        lvhittestinfo.x = OS.GET_X_LPARAM(n5);
        lvhittestinfo.y = OS.GET_Y_LPARAM(n5);
        OS.SendMessage(this.handle, 4114, 0, lvhittestinfo);
        if ((this.style & 0x10000) == 0x0 && this.hooks(41)) {
            if (OS.SendMessage(this.handle, 4153, 0, lvhittestinfo) < 0) {
                if (OS.SendMessage(this.handle, 4100, 0, 0) != 0) {
                    final RECT rect = new RECT();
                    rect.left = 1;
                    this.ignoreCustomDraw = true;
                    final int sendMessage = OS.SendMessage(this.handle, 4110, 0, rect);
                    this.ignoreCustomDraw = false;
                    if (sendMessage != 0) {
                        lvhittestinfo.x = rect.left;
                        OS.SendMessage(this.handle, 4153, 0, lvhittestinfo);
                        if (lvhittestinfo.iItem < 0) {
                            lvhittestinfo.iItem = -1;
                        }
                        final LVHITTESTINFO lvhittestinfo2 = lvhittestinfo;
                        lvhittestinfo2.flags &= 0xFFFFFFF9;
                    }
                }
            }
            else if (lvhittestinfo.iSubItem != 0) {
                lvhittestinfo.iItem = -1;
            }
        }
        OS.SetFocus(this.handle);
        if (((this.style & 0x4) != 0x0 || this.hooks(3) || this.hooks(4)) && lvhittestinfo.iItem == -1) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return LRESULT.ZERO;
        }
        boolean b = false;
        if (OS.SendMessage(this.handle, 4146, 0, 0) == 1 && lvhittestinfo.iItem != -1) {
            final LVITEM lvitem = new LVITEM();
            lvitem.mask = 8;
            lvitem.stateMask = 2;
            lvitem.iItem = lvhittestinfo.iItem;
            OS.SendMessage(this.handle, OS.LVM_GETITEM, 0, lvitem);
            if ((lvitem.state & 0x2) != 0x0) {
                b = true;
            }
        }
        this.fullRowSelect = false;
        if (lvhittestinfo.iItem != -1 && (this.style & 0x10000) == 0x0 && this.hooks(41)) {
            this.fullRowSelect = this.hitTestSelection(lvhittestinfo.iItem, lvhittestinfo.x, lvhittestinfo.y);
            if (this.fullRowSelect && (lvhittestinfo.flags & 0x6) != 0x0) {
                this.fullRowSelect = false;
            }
        }
        int n6 = ((this.state & 0x8000) != 0x0 && this.hooks(29)) ? 1 : 0;
        if (n6 == 0) {
            final int n7 = 6;
            n6 = ((lvhittestinfo.iItem == -1 || (lvhittestinfo.flags & n7) == 0x0) ? 1 : 0);
            if (this.fullRowSelect) {
                n6 = 1;
            }
        }
        if (this.fullRowSelect) {
            OS.UpdateWindow(this.handle);
            OS.DefWindowProc(this.handle, 11, 0, 0);
            OS.SendMessage(this.handle, 4150, 32, 32);
        }
        this.dragStarted = false;
        display.dragCancelled = false;
        if (n6 == 0) {
            display.runDragDrop = false;
        }
        final int callWindowProc = this.callWindowProc(this.handle, n3, n4, n5, b);
        if (n6 == 0) {
            display.runDragDrop = true;
        }
        if (this.fullRowSelect) {
            this.fullRowSelect = false;
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.SendMessage(this.handle, 4150, 32, 0);
        }
        if (this.dragStarted || display.dragCancelled) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
        }
        else {
            boolean b2 = (lvhittestinfo.flags & 0x6) != 0x0;
            if (!b2 && (this.style & 0x2) != 0x0) {
                b2 = ((lvhittestinfo.flags & 0x8) == 0x0);
            }
            if (b2) {
                this.sendMouseEvent(4, n2, this.handle, n3, n4, n5);
            }
        }
        return new LRESULT(callWindowProc);
    }
    
    void sendPaintItemEvent(final TableItem item, final NMLVCUSTOMDRAW nmlvcustomdraw) {
        final int hdc = nmlvcustomdraw.hdc;
        final GCData gcData = new GCData();
        gcData.device = this.display;
        gcData.font = item.getFont(nmlvcustomdraw.iSubItem);
        final LVITEM lvitem = new LVITEM();
        lvitem.mask = 8;
        lvitem.stateMask = 2;
        lvitem.iItem = nmlvcustomdraw.dwItemSpec;
        final boolean b = OS.SendMessage(this.handle, OS.LVM_GETITEM, 0, lvitem) != 0 && (lvitem.state & 0x2) != 0x0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        if (nmlvcustomdraw.iSubItem == 0 || (this.style & 0x10000) != 0x0) {
            n3 = ((this.hotIndex == nmlvcustomdraw.dwItemSpec) ? 1 : 0);
        }
        if (OS.IsWindowEnabled(this.handle)) {
            if (b && (nmlvcustomdraw.iSubItem == 0 || (this.style & 0x10000) != 0x0)) {
                if (OS.GetFocus() == this.handle || this.display.getHighContrast()) {
                    n = 1;
                    if (this.selectionForeground != -1) {
                        gcData.foreground = this.selectionForeground;
                    }
                    else {
                        gcData.foreground = OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT);
                    }
                    gcData.background = OS.GetSysColor(OS.COLOR_HIGHLIGHT);
                }
                else {
                    n = (((this.style & 0x8000) == 0x0) ? 1 : 0);
                    gcData.foreground = OS.GetTextColor(hdc);
                    gcData.background = OS.GetSysColor(OS.COLOR_3DFACE);
                }
                if (this.explorerTheme && this.selectionForeground == -1) {
                    int foreground = (item.cellForeground != null) ? item.cellForeground[nmlvcustomdraw.iSubItem] : -1;
                    if (foreground == -1) {
                        foreground = item.foreground;
                    }
                    gcData.foreground = ((foreground != -1) ? foreground : this.getForegroundPixel());
                }
            }
            else {
                int n4 = (item.cellForeground != null) ? item.cellForeground[nmlvcustomdraw.iSubItem] : -1;
                if (n4 == -1) {
                    n4 = item.foreground;
                }
                int n5 = (item.cellBackground != null) ? item.cellBackground[nmlvcustomdraw.iSubItem] : -1;
                if (n5 == -1) {
                    n5 = item.background;
                }
                n2 = ((n5 != -1) ? 1 : 0);
                if (n4 == -1 || n5 == -1) {
                    Control backgroundControl = this.findBackgroundControl();
                    if (backgroundControl == null) {
                        backgroundControl = this;
                    }
                    if (n4 == -1) {
                        n4 = backgroundControl.getForegroundPixel();
                    }
                    if (n5 == -1) {
                        n5 = backgroundControl.getBackgroundPixel();
                    }
                }
                gcData.foreground = ((n4 != -1) ? n4 : OS.GetTextColor(hdc));
                gcData.background = ((n5 != -1) ? n5 : OS.GetBkColor(hdc));
            }
        }
        else {
            gcData.foreground = OS.GetSysColor(OS.COLOR_GRAYTEXT);
            gcData.background = OS.GetSysColor(OS.COLOR_3DFACE);
        }
        gcData.uiState = OS.SendMessage(this.handle, 297, 0, 0);
        final int saveDC = OS.SaveDC(hdc);
        final GC win32_new = GC.win32_new(hdc, gcData);
        final RECT bounds = item.getBounds(nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, true, true, false, false, hdc);
        final Event event = new Event();
        event.item = item;
        event.gc = win32_new;
        event.index = nmlvcustomdraw.iSubItem;
        final Event event2 = event;
        event2.detail |= 0x10;
        if (OS.SendMessage(this.handle, 4108, -1, 1) == nmlvcustomdraw.dwItemSpec && (nmlvcustomdraw.iSubItem == 0 || (this.style & 0x10000) != 0x0) && this.handle == OS.GetFocus() && (OS.SendMessage(this.handle, 297, 0, 0) & 0x1) == 0x0) {
            final Event event3 = event;
            event3.detail |= 0x4;
        }
        if (n3 != 0) {
            final Event event4 = event;
            event4.detail |= 0x20;
        }
        if (n != 0) {
            final Event event5 = event;
            event5.detail |= 0x2;
        }
        if (n2 != 0) {
            final Event event6 = event;
            event6.detail |= 0x8;
        }
        event.x = bounds.left;
        event.y = bounds.top;
        event.width = bounds.right - bounds.left;
        event.height = bounds.bottom - bounds.top;
        final RECT bounds2 = item.getBounds(nmlvcustomdraw.dwItemSpec, nmlvcustomdraw.iSubItem, true, true, true, true, hdc);
        win32_new.setClipping(bounds2.left, bounds2.top, bounds2.right - bounds2.left, bounds2.bottom - bounds2.top);
        this.sendEvent(42, event);
        if (gcData.focusDrawn) {
            this.focusRect = null;
        }
        event.gc = null;
        win32_new.dispose();
        OS.RestoreDC(hdc, saveDC);
    }
    
    Event sendPaintItemEvent(final TableItem item, final NMTTCUSTOMDRAW nmttcustomdraw, final int index, final RECT rect) {
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
            this.setBackgroundTransparent(true);
        }
        else if (!this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
            this.setBackgroundTransparent(false);
        }
    }
    
    void setBackgroundPixel(int defaultBackground) {
        final int sendMessage = OS.SendMessage(this.handle, 4096, 0, 0);
        if (sendMessage != -1) {
            if (this.findImageControl() != null) {
                return;
            }
            if (defaultBackground == -1) {
                defaultBackground = this.defaultBackground();
            }
            if (sendMessage != defaultBackground) {
                OS.SendMessage(this.handle, 4097, 0, defaultBackground);
                OS.SendMessage(this.handle, 4134, 0, defaultBackground);
                if ((this.style & 0x20) != 0x0) {
                    this.fixCheckboxImageListColor(true);
                }
            }
        }
        OS.InvalidateRect(this.handle, null, true);
    }
    
    void setBackgroundTransparent(final boolean b) {
        final int sendMessage = OS.SendMessage(this.handle, 4096, 0, 0);
        if (b) {
            if (sendMessage != -1) {
                OS.SendMessage(this.handle, 4097, 0, -1);
                OS.SendMessage(this.handle, 4134, 0, -1);
                OS.InvalidateRect(this.handle, null, true);
                if (!this.explorerTheme && (this.style & 0x10000) != 0x0) {
                    OS.SendMessage(this.handle, 4150, 32, 0);
                }
                if ((this.sortDirection & 0x480) != 0x0 && this.sortColumn != null && !this.sortColumn.isDisposed()) {
                    OS.SendMessage(this.handle, 4236, -1, 0);
                    OS.InvalidateRect(this.handle, null, true);
                }
            }
        }
        else if (sendMessage == -1) {
            Control backgroundControl = this.findBackgroundControl();
            if (backgroundControl == null) {
                backgroundControl = this;
            }
            if (backgroundControl.backgroundImage == null) {
                final int backgroundPixel = backgroundControl.getBackgroundPixel();
                OS.SendMessage(this.handle, 4097, 0, backgroundPixel);
                OS.SendMessage(this.handle, 4134, 0, backgroundPixel);
                if ((this.style & 0x20) != 0x0) {
                    this.fixCheckboxImageListColor(true);
                }
                OS.InvalidateRect(this.handle, null, true);
            }
            if (!this.explorerTheme && (this.style & 0x10000) != 0x0 && !this.hooks(40) && !this.hooks(42)) {
                final int n = 32;
                OS.SendMessage(this.handle, 4150, n, n);
            }
            if ((this.sortDirection & 0x480) != 0x0 && this.sortColumn != null && !this.sortColumn.isDisposed()) {
                final int index = this.indexOf(this.sortColumn);
                if (index != -1) {
                    OS.SendMessage(this.handle, 4236, index, 0);
                    OS.InvalidateRect(this.handle, null, true);
                }
            }
        }
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        this.setDeferResize(true);
        super.setBounds(n, n2, n3, n4, n5, false);
        this.setDeferResize(false);
    }
    
    public void setColumnOrder(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
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
        OS.SendMessage(this.handle, 4155, this.columnCount, array2);
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
                OS.SendMessage(sendMessage, 4615, j, array4[j] = new RECT());
            }
            OS.SendMessage(this.handle, 4154, array.length, array);
            OS.InvalidateRect(this.handle, null, true);
            final TableColumn[] array5 = new TableColumn[this.columnCount];
            System.arraycopy(this.columns, 0, array5, 0, this.columnCount);
            final RECT rect = new RECT();
            for (int k = 0; k < this.columnCount; ++k) {
                final TableColumn tableColumn = array5[k];
                if (!tableColumn.isDisposed()) {
                    OS.SendMessage(sendMessage, 4615, k, rect);
                    if (rect.left != array4[k].left) {
                        tableColumn.updateToolTip(k);
                        tableColumn.sendEvent(10);
                    }
                }
            }
        }
    }
    
    void setCustomDraw(final boolean customDraw) {
        if (this.customDraw == customDraw) {
            return;
        }
        if (!this.customDraw && customDraw && this.currentItem != null) {
            OS.InvalidateRect(this.handle, null, true);
        }
        this.customDraw = customDraw;
    }
    
    void setDeferResize(final boolean b) {
        if (b) {
            if (this.resizeCount++ == 0) {
                this.wasResized = false;
                if ((this.hooks(41) || this.hooks(40) || this.hooks(42)) && this.drawCount++ == 0 && OS.IsWindowVisible(this.handle)) {
                    OS.DefWindowProc(this.handle, 11, 0, 0);
                    OS.SendMessage(this.handle, 4097, 0, 16777215);
                }
            }
        }
        else if (--this.resizeCount == 0) {
            if ((this.hooks(41) || this.hooks(40) || this.hooks(42)) && --this.drawCount == 0) {
                OS.SendMessage(this.handle, 4097, 0, -1);
                OS.DefWindowProc(this.handle, 11, 1, 0);
                if (OS.IsWinCE) {
                    final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
                    if (sendMessage != 0) {
                        OS.InvalidateRect(sendMessage, null, true);
                    }
                    OS.InvalidateRect(this.handle, null, true);
                }
                else {
                    OS.RedrawWindow(this.handle, null, 0, 1157);
                }
            }
            if (this.wasResized) {
                this.setResizeChildren(this.wasResized = false);
                this.sendEvent(11);
                if (this.isDisposed()) {
                    return;
                }
                if (this.layout != null) {
                    this.markLayout(false, false);
                    this.updateLayout(false, false);
                }
                this.setResizeChildren(true);
            }
        }
    }
    
    void setCheckboxImageList(final int n, final int n2, final boolean b) {
        if ((this.style & 0x20) == 0x0) {
            return;
        }
        final int n3 = 8;
        final boolean b2 = false;
        int n4;
        if (OS.IsWinCE) {
            n4 = ((b2 | false) ? 1 : 0);
        }
        else {
            final int getDC = OS.GetDC(this.handle);
            final int getDeviceCaps = OS.GetDeviceCaps(getDC, 12);
            final int getDeviceCaps2 = OS.GetDeviceCaps(getDC, 14);
            OS.ReleaseDC(this.handle, getDC);
            switch (getDeviceCaps * getDeviceCaps2) {
                case 4: {
                    n4 = ((b2 ? 1 : 0) | 0x4);
                    break;
                }
                case 8: {
                    n4 = ((b2 ? 1 : 0) | 0x8);
                    break;
                }
                case 16: {
                    n4 = ((b2 ? 1 : 0) | 0x10);
                    break;
                }
                case 24: {
                    n4 = ((b2 ? 1 : 0) | 0x18);
                    break;
                }
                case 32: {
                    n4 = ((b2 ? 1 : 0) | 0x20);
                    break;
                }
                default: {
                    n4 = ((b2 | false) ? 1 : 0);
                    break;
                }
            }
        }
        if ((this.style & 0x4000000) != 0x0) {
            n4 |= 0x2000;
        }
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            n4 |= 0x1;
        }
        final int imageList_Create = OS.ImageList_Create(n, n2, n4, n3, n3);
        final int getDC2 = OS.GetDC(this.handle);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC2);
        final int createCompatibleBitmap = OS.CreateCompatibleBitmap(getDC2, n * n3, n2);
        final int selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, n * n3, n2);
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
        final int min = Math.min(textmetric.tmHeight, n);
        final int min2 = Math.min(textmetric.tmHeight, n2);
        final int n5 = (n - min) / 2;
        final int n6 = (n2 - min2) / 2 + 1;
        OS.SetRect(rect, n5, n6, n5 + min, n6 + min2);
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int hButtonTheme = this.display.hButtonTheme();
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 1, rect, null);
            final RECT rect2 = rect;
            rect2.left += n;
            final RECT rect3 = rect;
            rect3.right += n;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 5, rect, null);
            final RECT rect4 = rect;
            rect4.left += n;
            final RECT rect5 = rect;
            rect5.right += n;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 1, rect, null);
            final RECT rect6 = rect;
            rect6.left += n;
            final RECT rect7 = rect;
            rect7.right += n;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 9, rect, null);
            final RECT rect8 = rect;
            rect8.left += n;
            final RECT rect9 = rect;
            rect9.right += n;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 4, rect, null);
            final RECT rect10 = rect;
            rect10.left += n;
            final RECT rect11 = rect;
            rect11.right += n;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 8, rect, null);
            final RECT rect12 = rect;
            rect12.left += n;
            final RECT rect13 = rect;
            rect13.right += n;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 4, rect, null);
            final RECT rect14 = rect;
            rect14.left += n;
            final RECT rect15 = rect;
            rect15.right += n;
            OS.DrawThemeBackground(hButtonTheme, createCompatibleDC, 3, 12, rect, null);
        }
        else {
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 16384);
            final RECT rect16 = rect;
            rect16.left += n;
            final RECT rect17 = rect;
            rect17.right += n;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 17408);
            final RECT rect18 = rect;
            rect18.left += n;
            final RECT rect19 = rect;
            rect19.right += n;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 16640);
            final RECT rect20 = rect;
            rect20.left += n;
            final RECT rect21 = rect;
            rect21.right += n;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 17664);
            final RECT rect22 = rect;
            rect22.left += n;
            final RECT rect23 = rect;
            rect23.right += n;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 16384);
            final RECT rect24 = rect;
            rect24.left += n;
            final RECT rect25 = rect;
            rect25.right += n;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 17408);
            final RECT rect26 = rect;
            rect26.left += n;
            final RECT rect27 = rect;
            rect27.right += n;
            OS.DrawFrameControl(createCompatibleDC, rect, 4, 16640);
            final RECT rect28 = rect;
            rect28.left += n;
            final RECT rect29 = rect;
            rect29.right += n;
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
        final int topIndex = this.getTopIndex();
        if (b && topIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        final int sendMessage = OS.SendMessage(this.handle, 4098, 2, 0);
        OS.SendMessage(this.handle, 4099, 2, imageList_Create);
        if (sendMessage != 0) {
            OS.ImageList_Destroy(sendMessage);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            OS.SendMessage(this.handle, 4099, 1, OS.SendMessage(this.handle, 4098, 1, 0));
        }
        if (b && topIndex != 0) {
            this.setTopIndex(topIndex);
            this.setRedraw(true);
        }
    }
    
    void setFocusIndex(final int n) {
        if (n < 0) {
            return;
        }
        final LVITEM lvitem = new LVITEM();
        lvitem.state = 1;
        lvitem.stateMask = 1;
        this.ignoreSelect = true;
        OS.SendMessage(this.handle, 4139, n, lvitem);
        this.ignoreSelect = false;
        OS.SendMessage(this.handle, 4163, 0, n);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        final int topIndex = this.getTopIndex();
        if (topIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        if (this.itemHeight != -1) {
            OS.SetWindowLong(this.handle, -16, OS.GetWindowLong(this.handle, -16) | 0x400);
        }
        super.setFont(font);
        if (this.itemHeight != -1) {
            OS.SetWindowLong(this.handle, -16, OS.GetWindowLong(this.handle, -16) & 0xFFFFFBFF);
        }
        this.setScrollWidth(null, true);
        if (topIndex != 0) {
            this.setTopIndex(topIndex);
            this.setRedraw(true);
        }
        OS.InvalidateRect(OS.SendMessage(this.handle, 4127, 0, 0), null, true);
    }
    
    void setForegroundPixel(int n) {
        if (n == -1) {
            n = -16777216;
        }
        OS.SendMessage(this.handle, 4132, 0, n);
        OS.InvalidateRect(this.handle, null, true);
    }
    
    public void setHeaderVisible(final boolean b) {
        this.checkWidget();
        int n = OS.GetWindowLong(this.handle, -16) & 0xFFFFBFFF;
        if (!b) {
            n |= 0x4000;
        }
        final int topIndex = this.getTopIndex();
        OS.SetWindowLong(this.handle, -16, n);
        final int topIndex2 = this.getTopIndex();
        if (topIndex2 != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        if (b && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x1) != 0x0) {
            this.fixItemHeight(false);
        }
        this.setTopIndex(topIndex);
        if (topIndex2 != 0) {
            this.setRedraw(true);
        }
        this.updateHeaderToolTips();
    }
    
    public void setItemCount(int max) {
        this.checkWidget();
        max = Math.max(0, max);
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (max == sendMessage) {
            return;
        }
        this.setDeferResize(true);
        final boolean b = (this.style & 0x10000000) != 0x0;
        if (!b) {
            this.setRedraw(false);
        }
        int i;
        for (i = max; i < sendMessage; ++i) {
            final TableItem getItem = this._getItem(i, false);
            if (getItem != null && !getItem.isDisposed()) {
                getItem.release(false);
            }
            if (!b) {
                final boolean b2 = true;
                this.ignoreShrink = b2;
                this.ignoreSelect = b2;
                final int sendMessage2 = OS.SendMessage(this.handle, 4104, max, 0);
                final boolean b3 = false;
                this.ignoreShrink = b3;
                this.ignoreSelect = b3;
                if (sendMessage2 == 0) {
                    break;
                }
            }
        }
        if (i < sendMessage) {
            this.error(15);
        }
        this._setItemCount(max, sendMessage);
        if (b) {
            OS.SendMessage(this.handle, 4143, max, 3);
            if (max == 0 && sendMessage != 0) {
                OS.InvalidateRect(this.handle, null, true);
            }
        }
        else {
            for (int j = sendMessage; j < max; ++j) {
                new TableItem(this, 0, j, true);
            }
        }
        if (!b) {
            this.setRedraw(true);
        }
        if (sendMessage == 0) {
            this.setScrollWidth(null, false);
        }
        this.setDeferResize(false);
    }
    
    void setItemHeight(final boolean b) {
        final int topIndex = this.getTopIndex();
        if (b && topIndex != 0) {
            this.setRedraw(false);
            this.setTopIndex(0);
        }
        if (this.itemHeight == -1) {
            OS.SendMessage(this.handle, 48, OS.SendMessage(this.handle, 49, 0, 0), 0);
        }
        else {
            this.forceResize();
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            final int n = rect.right - rect.left;
            final int n2 = rect.bottom - rect.top;
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            OS.SetWindowLong(this.handle, -16, getWindowLong | 0x400);
            final int n3 = 30;
            this.ignoreResize = true;
            this.SetWindowPos(this.handle, 0, 0, 0, n, n2 + 1, n3);
            this.SetWindowPos(this.handle, 0, 0, 0, n, n2, n3);
            this.ignoreResize = false;
            OS.SetWindowLong(this.handle, -16, getWindowLong);
        }
        if (b && topIndex != 0) {
            this.setTopIndex(topIndex);
            this.setRedraw(true);
        }
    }
    
    void setItemHeight(final int itemHeight) {
        this.checkWidget();
        if (itemHeight < -1) {
            this.error(5);
        }
        this.itemHeight = itemHeight;
        this.setItemHeight(true);
        this.setScrollWidth(null, true);
    }
    
    public void setLinesVisible(final boolean b) {
        this.checkWidget();
        OS.SendMessage(this.handle, 4150, 1, b ? 1 : 0);
        if (b && (OS.GetWindowLong(this.handle, -16) & 0x4000) == 0x0) {
            this.fixItemHeight(true);
        }
    }
    
    public void setRedraw(final boolean b) {
        this.checkWidget();
        if (this.drawCount == 0 && (OS.GetWindowLong(this.handle, -16) & 0x10000000) == 0x0) {
            this.state |= 0x10;
        }
        if (b) {
            if (--this.drawCount == 0) {
                this.setScrollWidth(null, true);
                this.setDeferResize(true);
                OS.SendMessage(this.handle, 11, 1, 0);
                final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
                if (sendMessage != 0) {
                    OS.SendMessage(sendMessage, 11, 1, 0);
                }
                if ((this.state & 0x10) != 0x0) {
                    this.state &= 0xFFFFFFEF;
                    OS.ShowWindow(this.handle, 0);
                }
                else if (OS.IsWinCE) {
                    OS.InvalidateRect(this.handle, null, false);
                    if (sendMessage != 0) {
                        OS.InvalidateRect(sendMessage, null, false);
                    }
                }
                else {
                    OS.RedrawWindow(this.handle, null, 0, 1157);
                }
                this.setDeferResize(false);
            }
        }
        else if (this.drawCount++ == 0) {
            OS.SendMessage(this.handle, 11, 0, 0);
            final int sendMessage2 = OS.SendMessage(this.handle, 4127, 0, 0);
            if (sendMessage2 != 0) {
                OS.SendMessage(sendMessage2, 11, 0, 0);
            }
        }
    }
    
    void setScrollWidth(final int n) {
        if (n != OS.SendMessage(this.handle, 4125, 0, 0)) {
            boolean b = false;
            if (this.hooks(41)) {
                b = (this.getDrawing() && OS.IsWindowVisible(this.handle));
            }
            if (b) {
                OS.DefWindowProc(this.handle, 11, 0, 0);
            }
            OS.SendMessage(this.handle, 4126, 0, n);
            if (b) {
                OS.DefWindowProc(this.handle, 11, 1, 0);
                if (OS.IsWinCE) {
                    final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
                    if (sendMessage != 0) {
                        OS.InvalidateRect(sendMessage, null, true);
                    }
                    OS.InvalidateRect(this.handle, null, true);
                }
                else {
                    OS.RedrawWindow(this.handle, null, 0, 1157);
                }
            }
        }
    }
    
    boolean setScrollWidth(final TableItem tableItem, final boolean b) {
        if (this.currentItem != null) {
            if (this.currentItem != tableItem) {
                this.fixScrollWidth = true;
            }
            return false;
        }
        if (!b && (!this.getDrawing() || !OS.IsWindowVisible(this.handle))) {
            this.fixScrollWidth = true;
            return false;
        }
        this.fixScrollWidth = false;
        if (this.columnCount == 0) {
            int scrollWidth = 0;
            int n = 0;
            for (int i = 0; i < OS.SendMessage(this.handle, 4100, 0, 0); ++i) {
                String s = null;
                int n2 = -1;
                if (tableItem != null) {
                    s = tableItem.text;
                    n = Math.max(n, tableItem.imageIndent);
                    n2 = tableItem.fontHandle(0);
                }
                else {
                    final TableItem getItem = this._getItem(i, false);
                    if (getItem != null) {
                        s = getItem.text;
                        n = Math.max(n, getItem.imageIndent);
                        n2 = getItem.fontHandle(0);
                    }
                }
                if (s != null && s.length() != 0) {
                    if (n2 != -1) {
                        final int getDC = OS.GetDC(this.handle);
                        final int selectObject = OS.SelectObject(getDC, n2);
                        final int n3 = 3104;
                        final TCHAR tchar = new TCHAR(this.getCodePage(), s, false);
                        final RECT rect = new RECT();
                        OS.DrawText(getDC, tchar, tchar.length(), rect, n3);
                        OS.SelectObject(getDC, selectObject);
                        OS.ReleaseDC(this.handle, getDC);
                        scrollWidth = Math.max(scrollWidth, rect.right - rect.left);
                    }
                    else {
                        scrollWidth = Math.max(scrollWidth, OS.SendMessage(this.handle, OS.LVM_GETSTRINGWIDTH, 0, new TCHAR(this.getCodePage(), s, true)));
                    }
                }
                if (tableItem != null) {
                    break;
                }
            }
            if (scrollWidth == 0) {
                scrollWidth = Math.max(scrollWidth, OS.SendMessage(this.handle, OS.LVM_GETSTRINGWIDTH, 0, new TCHAR(this.getCodePage(), " ", true)));
            }
            final int sendMessage = OS.SendMessage(this.handle, 4098, 2, 0);
            if (sendMessage != 0) {
                final int[] array = { 0 };
                OS.ImageList_GetIconSize(sendMessage, array, new int[1]);
                scrollWidth += array[0] + 4;
            }
            final int sendMessage2 = OS.SendMessage(this.handle, 4098, 1, 0);
            if (sendMessage2 != 0) {
                final int[] array2 = { 0 };
                OS.ImageList_GetIconSize(sendMessage2, array2, new int[1]);
                scrollWidth += (n + 1) * array2[0];
            }
            else {
                ++scrollWidth;
            }
            scrollWidth += 8;
            final int sendMessage3 = OS.SendMessage(this.handle, 4125, 0, 0);
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                scrollWidth += 2;
            }
            if (scrollWidth > sendMessage3) {
                this.setScrollWidth(scrollWidth);
                return true;
            }
        }
        return false;
    }
    
    public void setSelection(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = array.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        this.select(array);
        final int focusIndex = array[0];
        if (focusIndex != -1) {
            this.setFocusIndex(focusIndex);
        }
        this.showSelection();
    }
    
    public void setSelection(final TableItem tableItem) {
        this.checkWidget();
        if (tableItem == null) {
            this.error(4);
        }
        this.setSelection(new TableItem[] { tableItem });
    }
    
    public void setSelection(final TableItem[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = array.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        int focusIndex = -1;
        for (int i = length - 1; i >= 0; --i) {
            final int index = this.indexOf(array[i]);
            if (index != -1) {
                this.select(focusIndex = index);
            }
        }
        if (focusIndex != -1) {
            this.setFocusIndex(focusIndex);
        }
        this.showSelection();
    }
    
    public void setSelection(final int focusIndex) {
        this.checkWidget();
        this.deselectAll();
        this.select(focusIndex);
        if (focusIndex != -1) {
            this.setFocusIndex(focusIndex);
        }
        this.showSelection();
    }
    
    public void setSelection(int max, int min) {
        this.checkWidget();
        this.deselectAll();
        if (min < 0 || max > min || ((this.style & 0x4) != 0x0 && max != min)) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4100, 0, 0);
        if (sendMessage == 0 || max >= sendMessage) {
            return;
        }
        max = Math.max(0, max);
        min = Math.min(min, sendMessage - 1);
        this.select(max, min);
        this.setFocusIndex(max);
        this.showSelection();
    }
    
    public void setSortColumn(final TableColumn sortColumn) {
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
    
    void setSubImagesVisible(final boolean b) {
        if ((OS.SendMessage(this.handle, 4151, 0, 0) & 0x2) != 0x0 == b) {
            return;
        }
        OS.SendMessage(this.handle, 4150, 2, b ? 2 : 0);
    }
    
    void setTableEmpty() {
        if (this.imageList != null) {
            final int imageList_Create = OS.ImageList_Create(1, 1, 0, 0, 0);
            OS.SendMessage(this.handle, 4099, 1, imageList_Create);
            OS.SendMessage(this.handle, 4099, 1, 0);
            if (this.headerImageList != null) {
                OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 4616, 0, this.headerImageList.getHandle());
            }
            OS.ImageList_Destroy(imageList_Create);
            this.display.releaseImageList(this.imageList);
            this.imageList = null;
            if (this.itemHeight != -1) {
                this.setItemHeight(false);
            }
        }
        if (!this.hooks(41) && !this.hooks(40) && !this.hooks(42)) {
            Control backgroundControl = this.findBackgroundControl();
            if (backgroundControl == null) {
                backgroundControl = this;
            }
            if (backgroundControl.backgroundImage == null) {
                this.setCustomDraw(false);
                this.setBackgroundTransparent(false);
                if (OS.COMCTL32_MAJOR < 6) {
                    this.style &= 0xDFFFFFFF;
                }
            }
        }
        this._initItems();
        if (this.columnCount == 0) {
            OS.SendMessage(this.handle, 4126, 0, 0);
            this.setScrollWidth(null, false);
        }
    }
    
    public void setTopIndex(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4135, 0, 0);
        if (n == sendMessage) {
            return;
        }
        if (!this.painted && this.hooks(41)) {
            this.hitTestSelection(n, 0, 0);
        }
        if (OS.SendMessage(this.handle, 4136, 0, 0) <= 0) {
            OS.SendMessage(this.handle, 4115, n, 1);
            if (n != OS.SendMessage(this.handle, 4135, 0, 0)) {
                OS.SendMessage(this.handle, 4115, n, 1);
            }
            return;
        }
        final RECT rect = new RECT();
        rect.left = 0;
        this.ignoreCustomDraw = true;
        OS.SendMessage(this.handle, 4110, 0, rect);
        this.ignoreCustomDraw = false;
        OS.SendMessage(this.handle, 4116, 0, (n - sendMessage) * (rect.bottom - rect.top));
    }
    
    public void showColumn(final TableColumn tableColumn) {
        this.checkWidget();
        if (tableColumn == null) {
            this.error(4);
        }
        if (tableColumn.isDisposed()) {
            this.error(5);
        }
        if (tableColumn.parent != this) {
            return;
        }
        final int index = this.indexOf(tableColumn);
        if (index < 0 || index >= this.columnCount) {
            return;
        }
        final RECT rect = new RECT();
        rect.left = 0;
        if (index == 0) {
            rect.top = 1;
            this.ignoreCustomDraw = true;
            OS.SendMessage(this.handle, 4152, -1, rect);
            this.ignoreCustomDraw = false;
            rect.right = rect.left;
            rect.left = rect.right - OS.SendMessage(this.handle, 4125, 0, 0);
        }
        else {
            rect.top = index;
            this.ignoreCustomDraw = true;
            OS.SendMessage(this.handle, 4152, -1, rect);
            this.ignoreCustomDraw = false;
        }
        int nPos = 0;
        final int sendMessage = OS.SendMessage(this.handle, 4151, 0, 0);
        if ((sendMessage & 0x1) != 0x0) {
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, scrollinfo);
            nPos = scrollinfo.nPos;
        }
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        if (rect.left < rect2.left) {
            OS.SendMessage(this.handle, 4116, rect.left - rect2.left, 0);
        }
        else {
            final int min = Math.min(rect2.right - rect2.left, rect.right - rect.left);
            if (rect.left + min > rect2.right) {
                OS.SendMessage(this.handle, 4116, rect.left + min - rect2.right, 0);
            }
        }
        if ((sendMessage & 0x1) != 0x0) {
            final SCROLLINFO scrollinfo2 = new SCROLLINFO();
            scrollinfo2.cbSize = SCROLLINFO.sizeof;
            scrollinfo2.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, scrollinfo2);
            final int nPos2 = scrollinfo2.nPos;
            if (nPos2 < nPos) {
                rect2.right = nPos - nPos2 + 1;
                OS.InvalidateRect(this.handle, rect2, true);
            }
        }
    }
    
    void showItem(final int n) {
        if (!this.painted && this.hooks(41)) {
            this.hitTestSelection(n, 0, 0);
        }
        if (OS.SendMessage(this.handle, 4136, 0, 0) <= 0) {
            OS.SendMessage(this.handle, 4115, n, 1);
            if (n != OS.SendMessage(this.handle, 4135, 0, 0)) {
                OS.SendMessage(this.handle, 4115, n, 1);
            }
        }
        else {
            OS.SendMessage(this.handle, 4115, n, 0);
        }
    }
    
    public void showItem(final TableItem tableItem) {
        this.checkWidget();
        if (tableItem == null) {
            this.error(4);
        }
        if (tableItem.isDisposed()) {
            this.error(5);
        }
        final int index = this.indexOf(tableItem);
        if (index != -1) {
            this.showItem(index);
        }
    }
    
    public void showSelection() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4108, -1, 2);
        if (sendMessage != -1) {
            this.showItem(sendMessage);
        }
    }
    
    void sort() {
        this.checkWidget();
    }
    
    void subclass() {
        super.subclass();
        if (Table.HeaderProc != 0) {
            OS.SetWindowLongPtr(OS.SendMessage(this.handle, 4127, 0, 0), -4, this.display.windowProc);
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
            final int sendMessage = OS.SendMessage(this.handle, 4174, 0, 0);
            OS.SetRect(rect2, rect.left, rect.top, rect.right, rect.bottom);
            OS.AdjustWindowRectEx(rect2, OS.GetWindowLong(sendMessage, -16), false, OS.GetWindowLong(sendMessage, -20));
        }
        return rect2;
    }
    
    String toolTipText(final NMTTDISPINFO nmttdispinfo) {
        if (OS.SendMessage(this.handle, 4174, 0, 0) == nmttdispinfo.hwndFrom && this.toolTipText != null) {
            return "";
        }
        if (this.headerToolTipHandle == nmttdispinfo.hwndFrom) {
            for (int i = 0; i < this.columnCount; ++i) {
                final TableColumn tableColumn = this.columns[i];
                if (tableColumn.id == nmttdispinfo.idFrom) {
                    return tableColumn.toolTipText;
                }
            }
        }
        return super.toolTipText(nmttdispinfo);
    }
    
    void unsubclass() {
        super.unsubclass();
        if (Table.HeaderProc != 0) {
            OS.SetWindowLongPtr(OS.SendMessage(this.handle, 4127, 0, 0), -4, Table.HeaderProc);
        }
    }
    
    void update(final boolean b) {
        int setWindowLongPtr = 0;
        int setWindowLongPtr2 = 0;
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        final boolean optimizedRedraw = this.isOptimizedRedraw();
        if (optimizedRedraw) {
            setWindowLongPtr2 = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
            setWindowLongPtr = OS.SetWindowLongPtr(sendMessage, -4, Table.HeaderProc);
        }
        super.update(b);
        if (optimizedRedraw) {
            OS.SetWindowLongPtr(this.handle, -4, setWindowLongPtr2);
            OS.SetWindowLongPtr(sendMessage, -4, setWindowLongPtr);
        }
    }
    
    void updateHeaderToolTips() {
        if (this.headerToolTipHandle == 0) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        final RECT rect = new RECT();
        final TOOLINFO toolinfo = new TOOLINFO();
        toolinfo.cbSize = TOOLINFO.sizeof;
        toolinfo.uFlags = 16;
        toolinfo.hwnd = sendMessage;
        toolinfo.lpszText = -1;
        for (int i = 0; i < this.columnCount; ++i) {
            final TableColumn tableColumn = this.columns[i];
            if (OS.SendMessage(sendMessage, 4615, i, rect) != 0) {
                final TOOLINFO toolinfo2 = toolinfo;
                final TableColumn tableColumn2 = tableColumn;
                final int n = this.display.nextToolTipId++;
                tableColumn2.id = n;
                toolinfo2.uId = n;
                toolinfo.left = rect.left;
                toolinfo.top = rect.top;
                toolinfo.right = rect.right;
                toolinfo.bottom = rect.bottom;
                OS.SendMessage(this.headerToolTipHandle, OS.TTM_ADDTOOL, 0, toolinfo);
            }
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
    
    void updateMoveable() {
        int n;
        for (n = 0; n < this.columnCount && !this.columns[n].moveable; ++n) {}
        OS.SendMessage(this.handle, 4150, 16, (n < this.columnCount) ? 16 : 0);
    }
    
    void updateOrientation() {
        super.updateOrientation();
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        if (sendMessage != 0) {
            final int getWindowLong = OS.GetWindowLong(sendMessage, -20);
            int n;
            if ((this.style & 0x4000000) != 0x0) {
                n = (getWindowLong | 0x400000);
            }
            else {
                n = (getWindowLong & 0xFFBFFFFF);
            }
            OS.SetWindowLong(sendMessage, -20, n);
            OS.InvalidateRect(sendMessage, null, true);
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            final int n2 = rect.right - rect.left;
            final int n3 = rect.bottom - rect.top;
            OS.SetWindowPos(this.handle, 0, 0, 0, n2 - 1, n3 - 1, 6);
            OS.SetWindowPos(this.handle, 0, 0, 0, n2, n3, 6);
        }
        if ((this.style & 0x20) != 0x0) {
            this.fixCheckboxImageListColor(false);
        }
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x40;
        if ((this.style & 0x8000) == 0x0) {
            n |= 0x8;
        }
        if ((this.style & 0x4) != 0x0) {
            n |= 0x4;
        }
        int n2 = n | 0x4001;
        if ((this.style & 0x10000000) != 0x0) {
            n2 |= 0x1000;
        }
        return n2;
    }
    
    TCHAR windowClass() {
        return Table.TableClass;
    }
    
    int windowProc() {
        return Table.TableProc;
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (n != this.handle) {
            Label_0389: {
                switch (n2) {
                    case 123: {
                        final LRESULT wmContextMenu = this.wmContextMenu(n, n3, n4);
                        if (wmContextMenu != null) {
                            return wmContextMenu.value;
                        }
                        break;
                    }
                    case 533: {
                        if (OS.COMCTL32_MAJOR >= 6 || n4 == 0) {
                            break;
                        }
                        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
                        if (n4 != sendMessage) {
                            OS.InvalidateRect(sendMessage, null, true);
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
                                break Label_0389;
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
                        final int sendMessage2 = OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 4614, 0, hdhittestinfo);
                        if (sendMessage2 >= 0 && sendMessage2 < this.columnCount && !this.columns[sendMessage2].resizable && (hdhittestinfo.flags & 0xC) != 0x0) {
                            OS.SetCursor(OS.LoadCursor(0, 32512));
                            return 1;
                        }
                        break;
                    }
                }
            }
            return this.callWindowProc(n, n2, n3, n4);
        }
        if (n2 != Display.DI_GETDRAGIMAGE || ((OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(6, 0)) && (this.style & 0x10000000) == 0x0 && !this.hooks(40) && !this.hooks(42))) {
            return super.windowProc(n, n2, n3, n4);
        }
        int n5 = OS.SendMessage(this.handle, 4108, OS.SendMessage(this.handle, 4135, 0, 0) - 1, 2);
        if (n5 == -1) {
            return 0;
        }
        final POINT point2 = new POINT();
        OS.POINTSTOPOINT(point2, OS.GetMessagePos());
        OS.MapWindowPoints(0, this.handle, point2, 1);
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final TableItem getItem = this._getItem(n5);
        final RECT bounds = getItem.getBounds(n5, 0, true, true, true);
        if ((this.style & 0x10000) != 0x0) {
            final int n6 = 301;
            bounds.left = Math.max(rect.left, point2.x - n6 / 2);
            if (rect.right > bounds.left + n6) {
                bounds.right = bounds.left + n6;
            }
            else {
                bounds.right = rect.right;
                bounds.left = Math.max(rect.left, bounds.right - n6);
            }
        }
        final int createRectRgn = OS.CreateRectRgn(bounds.left, bounds.top, bounds.right, bounds.bottom);
        while ((n5 = OS.SendMessage(this.handle, 4108, n5, 2)) != -1) {
            if (bounds.bottom - bounds.top > 301) {
                break;
            }
            if (bounds.bottom > rect.bottom) {
                break;
            }
            final RECT bounds2 = getItem.getBounds(n5, 0, true, true, true);
            final int createRectRgn2 = OS.CreateRectRgn(bounds.left, bounds2.top, bounds.right, bounds2.bottom);
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
        final byte[] array = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        final int createDIBSection = OS.CreateDIBSection(0, array, 0, new int[1], 0, 0);
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
                if ((this.style & 0x20) != 0x0) {
                    final int sendMessage = OS.SendMessage(this.handle, 4108, -1, 1);
                    if (sendMessage != -1) {
                        final TableItem getItem = this._getItem(sendMessage);
                        getItem.setChecked(!getItem.getChecked(), true);
                        if (!OS.IsWinCE) {
                            OS.NotifyWinEvent(32773, this.handle, -4, sendMessage + 1);
                        }
                    }
                }
                return new LRESULT(this.callWindowProc(this.handle, 256, n, n2));
            }
            case 13: {
                final int sendMessage2 = OS.SendMessage(this.handle, 4108, -1, 1);
                if (sendMessage2 != -1) {
                    final Event event = new Event();
                    event.item = this._getItem(sendMessage2);
                    this.sendSelectionEvent(14, event, false);
                }
                return LRESULT.ZERO;
            }
            default: {
                return wm_CHAR;
            }
        }
    }
    
    LRESULT WM_CONTEXTMENU(final int n, final int n2) {
        if (!this.display.runDragDrop) {
            return LRESULT.ZERO;
        }
        return super.WM_CONTEXTMENU(n, n2);
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if (this.findImageControl() != null) {
            return LRESULT.ONE;
        }
        if (!OS.IsWinCE && OS.COMCTL32_MAJOR < 6 && (this.style & 0x20000000) != 0x0 && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x10000) == 0x0) {
            return LRESULT.ONE;
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_GETOBJECT(final int n, final int n2) {
        if ((this.style & 0x20) != 0x0 && this.accessible == null) {
            this.accessible = this.new_Accessible(this);
        }
        return super.WM_GETOBJECT(n, n2);
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        switch (n) {
            case 32: {
                return LRESULT.ZERO;
            }
            case 107: {
                if (OS.GetKeyState(17) >= 0) {
                    break;
                }
                int n3;
                for (n3 = 0; n3 < this.columnCount && this.columns[n3].getResizable(); ++n3) {}
                if (n3 != this.columnCount || this.hooks(41)) {
                    final TableColumn[] array = new TableColumn[this.columnCount];
                    System.arraycopy(this.columns, 0, array, 0, this.columnCount);
                    for (int i = 0; i < array.length; ++i) {
                        final TableColumn tableColumn = array[i];
                        if (!tableColumn.isDisposed() && tableColumn.getResizable()) {
                            tableColumn.pack();
                        }
                    }
                    return LRESULT.ZERO;
                }
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36: {
                int setWindowLongPtr = 0;
                int setWindowLongPtr2 = 0;
                final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
                final boolean optimizedRedraw = this.isOptimizedRedraw();
                if (optimizedRedraw) {
                    setWindowLongPtr2 = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
                    setWindowLongPtr = OS.SetWindowLongPtr(sendMessage, -4, Table.HeaderProc);
                }
                final int callWindowProc = this.callWindowProc(this.handle, 256, n, n2);
                wm_KEYDOWN = ((callWindowProc == 0) ? LRESULT.ZERO : new LRESULT(callWindowProc));
                if (optimizedRedraw) {
                    OS.SetWindowLongPtr(this.handle, -4, setWindowLongPtr2);
                    OS.SetWindowLongPtr(sendMessage, -4, setWindowLongPtr);
                }
            }
            case 38:
            case 40: {
                OS.SendMessage(this.handle, 295, 3, 0);
                break;
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        final LRESULT wm_KILLFOCUS = super.WM_KILLFOCUS(n, n2);
        if (this.imageList != null || (this.style & 0x20) != 0x0) {
            OS.InvalidateRect(this.handle, null, false);
        }
        return wm_KILLFOCUS;
    }
    
    LRESULT WM_LBUTTONDBLCLK(final int n, final int n2) {
        final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
        lvhittestinfo.x = OS.GET_X_LPARAM(n2);
        lvhittestinfo.y = OS.GET_Y_LPARAM(n2);
        final int sendMessage = OS.SendMessage(this.handle, 4114, 0, lvhittestinfo);
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 1, this.handle, 513, n, n2);
        if (!this.sendMouseEvent(8, 1, this.handle, 515, n, n2)) {
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return LRESULT.ZERO;
        }
        if (lvhittestinfo.iItem != -1) {
            this.callWindowProc(this.handle, 515, n, n2);
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        if ((this.style & 0x20) != 0x0 && sendMessage != -1 && lvhittestinfo.flags == 8) {
            final TableItem getItem = this._getItem(sendMessage);
            getItem.setChecked(!getItem.getChecked(), true);
            if (!OS.IsWinCE) {
                OS.NotifyWinEvent(32773, this.handle, -4, sendMessage + 1);
            }
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final LRESULT sendMouseDownEvent = this.sendMouseDownEvent(3, 1, 513, n, n2);
        if (sendMouseDownEvent == LRESULT.ZERO) {
            return sendMouseDownEvent;
        }
        if ((this.style & 0x20) != 0x0) {
            final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
            lvhittestinfo.x = OS.GET_X_LPARAM(n2);
            lvhittestinfo.y = OS.GET_Y_LPARAM(n2);
            final int sendMessage = OS.SendMessage(this.handle, 4114, 0, lvhittestinfo);
            if (sendMessage != -1 && lvhittestinfo.flags == 8) {
                final TableItem getItem = this._getItem(sendMessage);
                getItem.setChecked(!getItem.getChecked(), true);
                if (!OS.IsWinCE) {
                    OS.NotifyWinEvent(32773, this.handle, -4, sendMessage + 1);
                }
            }
        }
        return sendMouseDownEvent;
    }
    
    LRESULT WM_MOUSEHOVER(final int n, final int n2) {
        final LRESULT wm_MOUSEHOVER = super.WM_MOUSEHOVER(n, n2);
        if ((OS.SendMessage(this.handle, 4151, 0, 0) & 0xC8) != 0x0) {
            return wm_MOUSEHOVER;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        this._checkShrink();
        if (this.fixScrollWidth) {
            this.setScrollWidth(null, true);
        }
        if (!OS.IsWinCE && OS.COMCTL32_MAJOR < 6 && ((this.style & 0x20000000) != 0x0 || this.findImageControl() != null) && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x10000) == 0x0) {
            GC win32_new = null;
            final PAINTSTRUCT ps = new PAINTSTRUCT();
            final boolean b = this.hooks(9) || this.filters(9);
            int n3;
            if (b) {
                final GCData gcData = new GCData();
                gcData.ps = ps;
                gcData.hwnd = this.handle;
                win32_new = GC.win32_new(this, gcData);
                n3 = win32_new.handle;
            }
            else {
                n3 = OS.BeginPaint(this.handle, ps);
            }
            final int n4 = ps.right - ps.left;
            final int n5 = ps.bottom - ps.top;
            if (n4 != 0 && n5 != 0) {
                final int createCompatibleDC = OS.CreateCompatibleDC(n3);
                final POINT point = new POINT();
                final POINT point2 = new POINT();
                OS.SetWindowOrgEx(createCompatibleDC, ps.left, ps.top, point);
                OS.SetBrushOrgEx(createCompatibleDC, ps.left, ps.top, point2);
                final int createCompatibleBitmap = OS.CreateCompatibleBitmap(n3, n4, n5);
                final int selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
                if (OS.SendMessage(this.handle, 4096, 0, 0) != -1) {
                    final RECT rect = new RECT();
                    OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                    this.drawBackground(createCompatibleDC, rect);
                }
                this.callWindowProc(this.handle, 15, createCompatibleDC, 0);
                OS.SetWindowOrgEx(createCompatibleDC, point.x, point.y, null);
                OS.SetBrushOrgEx(createCompatibleDC, point2.x, point2.y, null);
                OS.BitBlt(n3, ps.left, ps.top, n4, n5, createCompatibleDC, 0, 0, 13369376);
                OS.SelectObject(createCompatibleDC, selectObject);
                OS.DeleteObject(createCompatibleBitmap);
                OS.DeleteObject(createCompatibleDC);
                if (b) {
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
            if (b) {
                win32_new.dispose();
            }
            else {
                OS.EndPaint(this.handle, ps);
            }
            return LRESULT.ZERO;
        }
        return super.WM_PAINT(n, n2);
    }
    
    LRESULT WM_RBUTTONDBLCLK(final int n, final int n2) {
        final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
        lvhittestinfo.x = OS.GET_X_LPARAM(n2);
        lvhittestinfo.y = OS.GET_Y_LPARAM(n2);
        OS.SendMessage(this.handle, 4114, 0, lvhittestinfo);
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 3, this.handle, 516, n, n2);
        if (this.sendMouseEvent(8, 3, this.handle, 518, n, n2) && lvhittestinfo.iItem != -1) {
            this.callWindowProc(this.handle, 518, n, n2);
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_RBUTTONDOWN(final int n, final int n2) {
        return this.sendMouseDownEvent(3, 3, 516, n, n2);
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        final LRESULT wm_SETFOCUS = super.WM_SETFOCUS(n, n2);
        if (this.imageList != null || (this.style & 0x20) != 0x0) {
            OS.InvalidateRect(this.handle, null, false);
        }
        if (OS.SendMessage(this.handle, 4100, 0, 0) == 0) {
            return wm_SETFOCUS;
        }
        if (OS.SendMessage(this.handle, 4108, -1, 1) == -1) {
            final LVITEM lvitem = new LVITEM();
            lvitem.state = 1;
            lvitem.stateMask = 1;
            this.ignoreSelect = true;
            OS.SendMessage(this.handle, 4139, 0, lvitem);
            this.ignoreSelect = false;
        }
        return wm_SETFOCUS;
    }
    
    LRESULT WM_SETFONT(final int n, final int n2) {
        final LRESULT wm_SETFONT = super.WM_SETFONT(n, n2);
        if (wm_SETFONT != null) {
            return wm_SETFONT;
        }
        OS.SendMessage(OS.SendMessage(this.handle, 4127, 0, 0), 48, 0, n2);
        if (this.headerToolTipHandle != 0) {
            OS.SendMessage(this.headerToolTipHandle, 48, n, n2);
        }
        return wm_SETFONT;
    }
    
    LRESULT WM_SETREDRAW(final int n, final int n2) {
        final LRESULT wm_SETREDRAW = super.WM_SETREDRAW(n, n2);
        if (wm_SETREDRAW != null) {
            return wm_SETREDRAW;
        }
        if (n == 1 && OS.SendMessage(this.handle, 4096, 0, 0) != -1 && (this.hooks(41) || this.hooks(40) || this.hooks(42))) {
            OS.SendMessage(this.handle, 4097, 0, -1);
        }
        OS.DefWindowProc(this.handle, 11, n, n2);
        final int callWindowProc = this.callWindowProc(this.handle, 11, n, n2);
        if (n == 0 && OS.SendMessage(this.handle, 4096, 0, 0) == -1) {
            OS.SendMessage(this.handle, 4097, 0, 16777215);
        }
        return (callWindowProc == 0) ? LRESULT.ZERO : new LRESULT(callWindowProc);
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        if (this.ignoreResize) {
            return null;
        }
        if (this.hooks(40) || this.hooks(42)) {
            OS.InvalidateRect(this.handle, null, true);
        }
        if (this.resizeCount != 0) {
            this.wasResized = true;
            return null;
        }
        return super.WM_SIZE(n, n2);
    }
    
    LRESULT WM_SYSCOLORCHANGE(final int n, final int n2) {
        final LRESULT wm_SYSCOLORCHANGE = super.WM_SYSCOLORCHANGE(n, n2);
        if (wm_SYSCOLORCHANGE != null) {
            return wm_SYSCOLORCHANGE;
        }
        if (this.findBackgroundControl() == null) {
            this.setBackgroundPixel(this.defaultBackground());
        }
        else if (OS.SendMessage(this.handle, 4096, 0, 0) != -1 && this.findImageControl() == null && (this.style & 0x20) != 0x0) {
            this.fixCheckboxImageListColor(true);
        }
        return wm_SYSCOLORCHANGE;
    }
    
    LRESULT WM_HSCROLL(final int n, final int n2) {
        int nPos = 0;
        final int sendMessage = OS.SendMessage(this.handle, 4151, 0, 0);
        if ((sendMessage & 0x1) != 0x0) {
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, scrollinfo);
            nPos = scrollinfo.nPos;
        }
        int setWindowLongPtr = 0;
        int setWindowLongPtr2 = 0;
        final int sendMessage2 = OS.SendMessage(this.handle, 4127, 0, 0);
        final boolean optimizedRedraw = this.isOptimizedRedraw();
        if (optimizedRedraw) {
            setWindowLongPtr2 = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
            setWindowLongPtr = OS.SetWindowLongPtr(sendMessage2, -4, Table.HeaderProc);
        }
        boolean b = false;
        if (OS.LOWORD(n) != 8 && OS.COMCTL32_MAJOR >= 6 && this.columnCount > 32 && OS.SendMessage(this.handle, 4136, 0, 0) > 16) {
            b = (this.getDrawing() && OS.IsWindowVisible(this.handle));
        }
        if (b) {
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        final LRESULT wm_HSCROLL = super.WM_HSCROLL(n, n2);
        if (b) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.RedrawWindow(this.handle, null, 0, 1157);
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                final RECT rect = new RECT();
                final RECT rect2 = new RECT();
                OS.GetClientRect(this.handle, rect2);
                final boolean[] columnVisible = new boolean[this.columnCount];
                for (int i = 0; i < this.columnCount; ++i) {
                    columnVisible[i] = true;
                    if (OS.SendMessage(sendMessage2, 4615, i, rect) != 0) {
                        OS.MapWindowPoints(sendMessage2, this.handle, rect, 2);
                        columnVisible[i] = OS.IntersectRect(rect, rect2, rect);
                    }
                }
                try {
                    this.display.hwndParent = OS.GetParent(this.handle);
                    this.display.columnVisible = columnVisible;
                    OS.UpdateWindow(this.handle);
                }
                finally {
                    this.display.columnVisible = null;
                }
                this.display.columnVisible = null;
            }
        }
        if (optimizedRedraw) {
            OS.SetWindowLongPtr(this.handle, -4, setWindowLongPtr2);
            OS.SetWindowLongPtr(sendMessage2, -4, setWindowLongPtr);
        }
        if ((sendMessage & 0x1) != 0x0) {
            final SCROLLINFO scrollinfo2 = new SCROLLINFO();
            scrollinfo2.cbSize = SCROLLINFO.sizeof;
            scrollinfo2.fMask = 4;
            OS.GetScrollInfo(this.handle, 0, scrollinfo2);
            final int nPos2 = scrollinfo2.nPos;
            if (nPos2 < nPos) {
                final RECT rect3 = new RECT();
                OS.GetClientRect(this.handle, rect3);
                rect3.right = nPos - nPos2 + 1;
                OS.InvalidateRect(this.handle, rect3, true);
            }
        }
        return wm_HSCROLL;
    }
    
    LRESULT WM_VSCROLL(final int n, final int n2) {
        int setWindowLongPtr = 0;
        int setWindowLongPtr2 = 0;
        final int sendMessage = OS.SendMessage(this.handle, 4127, 0, 0);
        final boolean optimizedRedraw = this.isOptimizedRedraw();
        if (optimizedRedraw) {
            setWindowLongPtr2 = OS.SetWindowLongPtr(this.handle, -4, Table.TableProc);
            setWindowLongPtr = OS.SetWindowLongPtr(sendMessage, -4, Table.HeaderProc);
        }
        boolean b = false;
        if (OS.LOWORD(n) != 8 && OS.COMCTL32_MAJOR >= 6 && this.columnCount > 32 && OS.SendMessage(this.handle, 4136, 0, 0) > 16) {
            b = (this.getDrawing() && OS.IsWindowVisible(this.handle));
        }
        if (b) {
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        final LRESULT wm_VSCROLL = super.WM_VSCROLL(n, n2);
        if (b) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
            OS.RedrawWindow(this.handle, null, 0, 1157);
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                final RECT rect = new RECT();
                final RECT rect2 = new RECT();
                OS.GetClientRect(this.handle, rect2);
                final boolean[] columnVisible = new boolean[this.columnCount];
                for (int i = 0; i < this.columnCount; ++i) {
                    columnVisible[i] = true;
                    if (OS.SendMessage(sendMessage, 4615, i, rect) != 0) {
                        OS.MapWindowPoints(sendMessage, this.handle, rect, 2);
                        columnVisible[i] = OS.IntersectRect(rect, rect2, rect);
                    }
                }
                try {
                    this.display.hwndParent = OS.GetParent(this.handle);
                    this.display.columnVisible = columnVisible;
                    OS.UpdateWindow(this.handle);
                }
                finally {
                    this.display.columnVisible = null;
                }
                this.display.columnVisible = null;
            }
        }
        if (optimizedRedraw) {
            OS.SetWindowLongPtr(this.handle, -4, setWindowLongPtr2);
            OS.SetWindowLongPtr(sendMessage, -4, setWindowLongPtr);
        }
        if ((OS.SendMessage(this.handle, 4151, 0, 0) & 0x1) != 0x0) {
            final int loword = OS.LOWORD(n);
            switch (loword) {
                case 0:
                case 1: {
                    final RECT rect3 = new RECT();
                    OS.GetWindowRect(sendMessage, rect3);
                    final int n3 = rect3.bottom - rect3.top;
                    final RECT rect4 = new RECT();
                    OS.GetClientRect(this.handle, rect4);
                    final RECT rect5 = rect4;
                    rect5.top += n3;
                    final int n4 = OS.HIWORD(OS.SendMessage(this.handle, 4160, 1, 0)) - OS.HIWORD(OS.SendMessage(this.handle, 4160, 0, 0));
                    if (loword == 1) {
                        rect4.top = rect4.bottom - n4 - 1;
                    }
                    else {
                        rect4.bottom = rect4.top + n4 + 1;
                    }
                    OS.InvalidateRect(this.handle, rect4, true);
                    break;
                }
                case 2:
                case 3: {
                    OS.InvalidateRect(this.handle, null, true);
                    break;
                }
            }
        }
        return wm_VSCROLL;
    }
    
    LRESULT wmMeasureChild(final int n, final int n2) {
        final MEASUREITEMSTRUCT measureitemstruct = new MEASUREITEMSTRUCT();
        OS.MoveMemory(measureitemstruct, n2, MEASUREITEMSTRUCT.sizeof);
        if (this.itemHeight == -1) {
            measureitemstruct.itemHeight = OS.HIWORD(OS.SendMessage(this.handle, 4160, 1, 0)) - OS.HIWORD(OS.SendMessage(this.handle, 4160, 0, 0));
        }
        else {
            measureitemstruct.itemHeight = this.itemHeight;
        }
        OS.MoveMemory(n2, measureitemstruct, MEASUREITEMSTRUCT.sizeof);
        return null;
    }
    
    LRESULT wmNotify(final NMHDR nmhdr, final int n, final int n2) {
        if (nmhdr.hwndFrom == OS.SendMessage(this.handle, 4174, 0, 0)) {
            final LRESULT wmNotifyToolTip = this.wmNotifyToolTip(nmhdr, n, n2);
            if (wmNotifyToolTip != null) {
                return wmNotifyToolTip;
            }
        }
        if (nmhdr.hwndFrom == OS.SendMessage(this.handle, 4127, 0, 0)) {
            final LRESULT wmNotifyHeader = this.wmNotifyHeader(nmhdr, n, n2);
            if (wmNotifyHeader != null) {
                return wmNotifyHeader;
            }
        }
        return super.wmNotify(nmhdr, n, n2);
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        Label_1905: {
            switch (nmhdr.code) {
                case -179:
                case -152: {
                    if ((this.style & 0x10000000) != 0x0) {
                        return new LRESULT(-1);
                    }
                    break;
                }
                case -115: {
                    if ((this.style & 0x10000000) == 0x0 || this.ignoreSelect) {
                        break;
                    }
                    final NMLVODSTATECHANGE nmlvodstatechange = new NMLVODSTATECHANGE();
                    OS.MoveMemory(nmlvodstatechange, n2, NMLVODSTATECHANGE.sizeof);
                    if ((nmlvodstatechange.uOldState & 0x2) != 0x0 != ((nmlvodstatechange.uNewState & 0x2) != 0x0)) {
                        this.wasSelected = true;
                        break;
                    }
                    break;
                }
                case -177:
                case -150: {
                    final NMLVDISPINFO nmlvdispinfo = new NMLVDISPINFO();
                    OS.MoveMemory(nmlvdispinfo, n2, NMLVDISPINFO.sizeof);
                    final boolean[] columnVisible = this.display.columnVisible;
                    if (columnVisible != null && !columnVisible[nmlvdispinfo.iSubItem]) {
                        break;
                    }
                    final TableItem getItem = this._getItem(nmlvdispinfo.iItem);
                    if (getItem == null) {
                        break;
                    }
                    if (this.ignoreShrink) {
                        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                            final RECT rect = new RECT();
                            rect.left = 0;
                            this.ignoreCustomDraw = true;
                            final int sendMessage = OS.SendMessage(this.handle, 4110, nmlvdispinfo.iItem, rect);
                            this.ignoreCustomDraw = false;
                            if (sendMessage != 0) {
                                OS.InvalidateRect(this.handle, rect, true);
                                break;
                            }
                            break;
                        }
                        else if ((this.style & 0x10000000) != 0x0 && !getItem.cached) {
                            OS.SendMessage(this.handle, 4117, nmlvdispinfo.iItem, nmlvdispinfo.iItem);
                            break;
                        }
                    }
                    if (!getItem.cached) {
                        if ((this.style & 0x10000000) != 0x0) {
                            this.lastIndexOf = nmlvdispinfo.iItem;
                            if (!this.checkData(getItem, this.lastIndexOf, false)) {
                                break;
                            }
                            if (this.setScrollWidth(this.fixScrollWidth ? null : getItem, true)) {
                                OS.InvalidateRect(this.handle, null, true);
                            }
                        }
                        getItem.cached = true;
                    }
                    if ((nmlvdispinfo.mask & 0x1) != 0x0) {
                        String text = null;
                        if (nmlvdispinfo.iSubItem == 0) {
                            text = getItem.text;
                        }
                        else {
                            final String[] strings = getItem.strings;
                            if (strings != null) {
                                text = strings[nmlvdispinfo.iSubItem];
                            }
                        }
                        if (text != null) {
                            int min = Math.min(text.length(), nmlvdispinfo.cchTextMax - 1);
                            if (!this.tipRequested && nmlvdispinfo.iSubItem == 0 && min == 0) {
                                text = " ";
                                min = 1;
                            }
                            char[] tableBuffer = this.display.tableBuffer;
                            if (tableBuffer == null || nmlvdispinfo.cchTextMax > tableBuffer.length) {
                                final Display display = this.display;
                                final char[] tableBuffer2 = new char[nmlvdispinfo.cchTextMax];
                                display.tableBuffer = tableBuffer2;
                                tableBuffer = tableBuffer2;
                            }
                            text.getChars(0, min, tableBuffer, 0);
                            tableBuffer[min++] = '\0';
                            if (OS.IsUnicode) {
                                OS.MoveMemory(nmlvdispinfo.pszText, tableBuffer, min * 2);
                            }
                            else {
                                OS.WideCharToMultiByte(this.getCodePage(), 0, tableBuffer, min, nmlvdispinfo.pszText, nmlvdispinfo.cchTextMax, null, null);
                                OS.MoveMemory(nmlvdispinfo.pszText + nmlvdispinfo.cchTextMax - 1, new byte[1], 1);
                            }
                        }
                    }
                    boolean b = false;
                    if ((nmlvdispinfo.mask & 0x2) != 0x0) {
                        Image image = null;
                        if (nmlvdispinfo.iSubItem == 0) {
                            image = getItem.image;
                        }
                        else {
                            final Image[] images = getItem.images;
                            if (images != null) {
                                image = images[nmlvdispinfo.iSubItem];
                            }
                        }
                        if (image != null) {
                            nmlvdispinfo.iImage = this.imageIndex(image, nmlvdispinfo.iSubItem);
                            b = true;
                        }
                    }
                    if ((nmlvdispinfo.mask & 0x8) != 0x0 && nmlvdispinfo.iSubItem == 0) {
                        int n3 = 1;
                        if (getItem.checked) {
                            ++n3;
                        }
                        if (getItem.grayed) {
                            n3 += 2;
                        }
                        if (!OS.IsWindowEnabled(this.handle)) {
                            n3 += 4;
                        }
                        nmlvdispinfo.state = n3 << 12;
                        nmlvdispinfo.stateMask = 61440;
                        b = true;
                    }
                    if ((nmlvdispinfo.mask & 0x10) != 0x0 && nmlvdispinfo.iSubItem == 0) {
                        nmlvdispinfo.iIndent = getItem.imageIndent;
                        b = true;
                    }
                    if (b) {
                        OS.MoveMemory(n2, nmlvdispinfo, NMLVDISPINFO.sizeof);
                        break;
                    }
                    break;
                }
                case -12: {
                    if (nmhdr.hwndFrom == OS.SendMessage(this.handle, 4127, 0, 0)) {
                        break;
                    }
                    if (!this.customDraw && this.findImageControl() == null && OS.IsWindowEnabled(this.handle)) {
                        if (!this.explorerTheme) {
                            break;
                        }
                        if (this.columnCount != 0) {
                            break;
                        }
                    }
                    final NMLVCUSTOMDRAW nmlvcustomdraw = new NMLVCUSTOMDRAW();
                    OS.MoveMemory(nmlvcustomdraw, n2, NMLVCUSTOMDRAW.sizeof);
                    switch (nmlvcustomdraw.dwDrawStage) {
                        case 1: {
                            return this.CDDS_PREPAINT(nmlvcustomdraw, n, n2);
                        }
                        case 65537: {
                            return this.CDDS_ITEMPREPAINT(nmlvcustomdraw, n, n2);
                        }
                        case 65538: {
                            return this.CDDS_ITEMPOSTPAINT(nmlvcustomdraw, n, n2);
                        }
                        case 196609: {
                            return this.CDDS_SUBITEMPREPAINT(nmlvcustomdraw, n, n2);
                        }
                        case 196610: {
                            return this.CDDS_SUBITEMPOSTPAINT(nmlvcustomdraw, n, n2);
                        }
                        case 2: {
                            return this.CDDS_POSTPAINT(nmlvcustomdraw, n, n2);
                        }
                        default: {
                            break Label_1905;
                        }
                    }
                    break;
                }
                case -156: {
                    if ((this.style & 0x4) != 0x0) {
                        return LRESULT.ONE;
                    }
                    if (this.hooks(3) || this.hooks(4)) {
                        return LRESULT.ONE;
                    }
                    if ((this.style & 0x4000000) != 0x0 && this.findImageControl() != null) {
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -111:
                case -109: {
                    if (OS.GetKeyState(1) >= 0) {
                        break;
                    }
                    this.dragStarted = true;
                    if (nmhdr.code == -109) {
                        final int getMessagePos = OS.GetMessagePos();
                        final POINT point = new POINT();
                        OS.POINTSTOPOINT(point, getMessagePos);
                        OS.ScreenToClient(this.handle, point);
                        this.sendDragEvent(1, point.x, point.y);
                        break;
                    }
                    break;
                }
                case -108: {
                    final NMLISTVIEW nmlistview = new NMLISTVIEW();
                    OS.MoveMemory(nmlistview, n2, NMLISTVIEW.sizeof);
                    final TableColumn tableColumn = this.columns[nmlistview.iSubItem];
                    if (tableColumn != null) {
                        tableColumn.sendSelectionEvent(13);
                        break;
                    }
                    break;
                }
                case -114: {
                    if (this.ignoreActivate) {
                        break;
                    }
                    final NMLISTVIEW nmlistview2 = new NMLISTVIEW();
                    OS.MoveMemory(nmlistview2, n2, NMLISTVIEW.sizeof);
                    if (nmlistview2.iItem != -1) {
                        final Event event = new Event();
                        event.item = this._getItem(nmlistview2.iItem);
                        this.sendSelectionEvent(14, event, false);
                        break;
                    }
                    break;
                }
                case -101: {
                    if (this.fullRowSelect) {
                        this.fullRowSelect = false;
                        OS.DefWindowProc(this.handle, 11, 1, 0);
                        OS.SendMessage(this.handle, 4150, 32, 0);
                    }
                    if (!this.ignoreSelect) {
                        final NMLISTVIEW nmlistview3 = new NMLISTVIEW();
                        OS.MoveMemory(nmlistview3, n2, NMLISTVIEW.sizeof);
                        if ((nmlistview3.uChanged & 0x8) != 0x0) {
                            if (nmlistview3.iItem == -1) {
                                this.wasSelected = true;
                            }
                            else if ((nmlistview3.uOldState & 0x2) != 0x0 != ((nmlistview3.uNewState & 0x2) != 0x0)) {
                                this.wasSelected = true;
                            }
                        }
                    }
                    if (!this.hooks(40) && !this.hooks(42)) {
                        break;
                    }
                    final int sendMessage2 = OS.SendMessage(this.handle, 4127, 0, 0);
                    final int sendMessage3 = OS.SendMessage(sendMessage2, 4608, 0, 0);
                    if (sendMessage3 == 0) {
                        break;
                    }
                    this.forceResize();
                    final RECT rect2 = new RECT();
                    OS.GetClientRect(this.handle, rect2);
                    final NMLISTVIEW nmlistview4 = new NMLISTVIEW();
                    OS.MoveMemory(nmlistview4, n2, NMLISTVIEW.sizeof);
                    if (nmlistview4.iItem != -1) {
                        final RECT rect3 = new RECT();
                        rect3.left = 0;
                        this.ignoreCustomDraw = true;
                        OS.SendMessage(this.handle, 4110, nmlistview4.iItem, rect3);
                        this.ignoreCustomDraw = false;
                        final RECT rect4 = new RECT();
                        OS.SendMessage(sendMessage2, 4615, OS.SendMessage(sendMessage2, 4623, sendMessage3 - 1, 0), rect4);
                        OS.MapWindowPoints(sendMessage2, this.handle, rect4, 2);
                        rect2.left = rect4.right;
                        rect2.top = rect3.top;
                        rect2.bottom = rect3.bottom;
                        OS.InvalidateRect(this.handle, rect2, true);
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
                        return LRESULT.ONE;
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(nmhdr, n, n2);
    }
    
    LRESULT wmNotifyHeader(final NMHDR nmhdr, final int n, final int n2) {
        Label_1069: {
            switch (nmhdr.code) {
                case -326:
                case -325:
                case -306:
                case -305: {
                    if (this.columnCount == 0) {
                        return LRESULT.ONE;
                    }
                    final NMHEADER nmheader = new NMHEADER();
                    OS.MoveMemory(nmheader, n2, NMHEADER.sizeof);
                    final TableColumn tableColumn = this.columns[nmheader.iItem];
                    if (tableColumn != null && !tableColumn.getResizable()) {
                        return LRESULT.ONE;
                    }
                    this.ignoreColumnMove = true;
                    switch (nmhdr.code) {
                        case -325:
                        case -305: {
                            boolean b = false;
                            if (!OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0)) {
                                b = (nmheader.iItem == 0 && !this.firstColumnImage);
                            }
                            if (tableColumn != null && (b || this.hooks(41))) {
                                tableColumn.pack();
                                return LRESULT.ONE;
                            }
                            break Label_1069;
                        }
                        default: {
                            break Label_1069;
                        }
                    }
                    break;
                }
                case -16: {
                    if (!this.ignoreColumnMove) {
                        for (int i = 0; i < this.columnCount; ++i) {
                            this.columns[i].updateToolTip(i);
                        }
                    }
                    this.ignoreColumnMove = false;
                    break;
                }
                case -310: {
                    if (this.ignoreColumnMove) {
                        return LRESULT.ONE;
                    }
                    if ((OS.SendMessage(this.handle, 4151, 0, 0) & 0x10) == 0x0) {
                        break;
                    }
                    if (this.columnCount == 0) {
                        return LRESULT.ONE;
                    }
                    final NMHEADER nmheader2 = new NMHEADER();
                    OS.MoveMemory(nmheader2, n2, NMHEADER.sizeof);
                    if (nmheader2.iItem == -1) {
                        break;
                    }
                    final TableColumn tableColumn2 = this.columns[nmheader2.iItem];
                    if (tableColumn2 != null && !tableColumn2.getMoveable()) {
                        this.ignoreColumnMove = true;
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -311: {
                    if ((OS.SendMessage(this.handle, 4151, 0, 0) & 0x10) == 0x0) {
                        break;
                    }
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
                    if (this.columnCount == 0) {
                        break;
                    }
                    final int[] array = new int[this.columnCount];
                    OS.SendMessage(this.handle, 4155, this.columnCount, array);
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
                    this.ignoreColumnMove = false;
                    for (int j = min; j <= max; ++j) {
                        final TableColumn tableColumn3 = this.columns[array[j]];
                        if (!tableColumn3.isDisposed()) {
                            tableColumn3.postEvent(10);
                        }
                    }
                    break;
                }
                case -321:
                case -301: {
                    final int sendMessage = OS.SendMessage(this.handle, 4125, 0, 0);
                    if (this.lastWidth == 0 && sendMessage > 0 && (OS.SendMessage(this.handle, 4151, 0, 0) & 0x1) != 0x0) {
                        final RECT rect = new RECT();
                        OS.GetClientRect(this.handle, rect);
                        rect.right = rect.left + sendMessage;
                        OS.InvalidateRect(this.handle, rect, true);
                    }
                    this.lastWidth = sendMessage;
                    if (this.ignoreColumnResize) {
                        break;
                    }
                    final NMHEADER nmheader4 = new NMHEADER();
                    OS.MoveMemory(nmheader4, n2, NMHEADER.sizeof);
                    if (nmheader4.pitem == 0) {
                        break;
                    }
                    final HDITEM hditem2 = new HDITEM();
                    OS.MoveMemory(hditem2, nmheader4.pitem, HDITEM.sizeof);
                    if ((hditem2.mask & 0x1) == 0x0) {
                        break;
                    }
                    final TableColumn tableColumn4 = this.columns[nmheader4.iItem];
                    if (tableColumn4 == null) {
                        break;
                    }
                    tableColumn4.updateToolTip(nmheader4.iItem);
                    tableColumn4.sendEvent(11);
                    if (this.isDisposed()) {
                        return LRESULT.ZERO;
                    }
                    final TableColumn[] array2 = new TableColumn[this.columnCount];
                    System.arraycopy(this.columns, 0, array2, 0, this.columnCount);
                    final int[] array3 = new int[this.columnCount];
                    OS.SendMessage(this.handle, 4155, this.columnCount, array3);
                    int n4 = 0;
                    for (int k = 0; k < this.columnCount; ++k) {
                        final TableColumn tableColumn5 = array2[array3[k]];
                        if (n4 != 0 && !tableColumn5.isDisposed()) {
                            tableColumn5.updateToolTip(array3[k]);
                            tableColumn5.sendEvent(10);
                        }
                        if (tableColumn5 == tableColumn4) {
                            n4 = 1;
                        }
                    }
                    break;
                }
                case -323:
                case -303: {
                    final NMHEADER nmheader5 = new NMHEADER();
                    OS.MoveMemory(nmheader5, n2, NMHEADER.sizeof);
                    final TableColumn tableColumn6 = this.columns[nmheader5.iItem];
                    if (tableColumn6 != null) {
                        tableColumn6.sendSelectionEvent(14);
                        break;
                    }
                    break;
                }
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
                if (this.toolTipText != null) {
                    break;
                }
                if (this.isCustomToolTip()) {
                    final NMTTCUSTOMDRAW nmttcustomdraw = new NMTTCUSTOMDRAW();
                    OS.MoveMemory(nmttcustomdraw, n2, NMTTCUSTOMDRAW.sizeof);
                    return this.wmNotifyToolTip(nmttcustomdraw, n2);
                }
                break;
            }
            case -530:
            case -521:
            case -520: {
                final LRESULT wmNotify = super.wmNotify(nmhdr, n, n2);
                if (wmNotify != null) {
                    return wmNotify;
                }
                if (nmhdr.code != -521) {
                    this.tipRequested = true;
                }
                final int callWindowProc = this.callWindowProc(this.handle, 78, n, n2);
                if (nmhdr.code != -521) {
                    this.tipRequested = false;
                }
                if (this.toolTipText != null) {
                    break;
                }
                if (this.isCustomToolTip()) {
                    final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
                    final int getMessagePos = OS.GetMessagePos();
                    final POINT point = new POINT();
                    OS.POINTSTOPOINT(point, getMessagePos);
                    OS.ScreenToClient(this.handle, point);
                    lvhittestinfo.x = point.x;
                    lvhittestinfo.y = point.y;
                    if (OS.SendMessage(this.handle, 4153, 0, lvhittestinfo) >= 0) {
                        final TableItem getItem = this._getItem(lvhittestinfo.iItem);
                        final int getDC = OS.GetDC(this.handle);
                        int selectObject = 0;
                        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
                        if (sendMessage != 0) {
                            selectObject = OS.SelectObject(getDC, sendMessage);
                        }
                        int n3 = getItem.fontHandle(lvhittestinfo.iSubItem);
                        if (n3 != -1) {
                            n3 = OS.SelectObject(getDC, n3);
                        }
                        final Event sendMeasureItemEvent = this.sendMeasureItemEvent(getItem, lvhittestinfo.iItem, lvhittestinfo.iSubItem, getDC);
                        if (!this.isDisposed() && !getItem.isDisposed()) {
                            final RECT rect = new RECT();
                            OS.SetRect(rect, sendMeasureItemEvent.x, sendMeasureItemEvent.y, sendMeasureItemEvent.x + sendMeasureItemEvent.width, sendMeasureItemEvent.y + sendMeasureItemEvent.height);
                            if (nmhdr.code == -521) {
                                final RECT toolTipRect = this.toolTipRect(rect);
                                OS.MapWindowPoints(this.handle, 0, toolTipRect, 2);
                                this.SetWindowPos(OS.SendMessage(this.handle, 4174, 0, 0), 0, toolTipRect.left, toolTipRect.top, toolTipRect.right - toolTipRect.left, toolTipRect.bottom - toolTipRect.top, 20);
                            }
                            else {
                                NMTTDISPINFO nmttdispinfo;
                                if (nmhdr.code == -520) {
                                    nmttdispinfo = new NMTTDISPINFOA();
                                    OS.MoveMemory((NMTTDISPINFOA)nmttdispinfo, n2, NMTTDISPINFOA.sizeof);
                                    if (nmttdispinfo.lpszText != 0) {
                                        OS.MoveMemory(nmttdispinfo.lpszText, new byte[1], 1);
                                        OS.MoveMemory(n2, (NMTTDISPINFOA)nmttdispinfo, NMTTDISPINFOA.sizeof);
                                    }
                                }
                                else {
                                    nmttdispinfo = new NMTTDISPINFOW();
                                    OS.MoveMemory((NMTTDISPINFOW)nmttdispinfo, n2, NMTTDISPINFOW.sizeof);
                                    if (nmttdispinfo.lpszText != 0) {
                                        OS.MoveMemory(nmttdispinfo.lpszText, new char[1], 2);
                                        OS.MoveMemory(n2, (NMTTDISPINFOW)nmttdispinfo, NMTTDISPINFOW.sizeof);
                                    }
                                }
                                final RECT bounds = getItem.getBounds(lvhittestinfo.iItem, lvhittestinfo.iSubItem, true, true, true, true, getDC);
                                final RECT rect2 = new RECT();
                                OS.GetClientRect(this.handle, rect2);
                                if (rect.right > bounds.right || rect.right > rect2.right) {
                                    final String s = " ";
                                    final Shell shell = this.getShell();
                                    final char[] array = new char[s.length() + 1];
                                    s.getChars(0, s.length(), array, 0);
                                    if (nmhdr.code == -520) {
                                        final byte[] array2 = new byte[array.length * 2];
                                        OS.WideCharToMultiByte(this.getCodePage(), 0, array, array.length, array2, array2.length, null, null);
                                        shell.setToolTipText(nmttdispinfo, array2);
                                        OS.MoveMemory(n2, (NMTTDISPINFOA)nmttdispinfo, NMTTDISPINFOA.sizeof);
                                    }
                                    else {
                                        shell.setToolTipText(nmttdispinfo, array);
                                        OS.MoveMemory(n2, (NMTTDISPINFOW)nmttdispinfo, NMTTDISPINFOW.sizeof);
                                    }
                                }
                            }
                        }
                        if (n3 != -1) {
                            OS.SelectObject(getDC, n3);
                        }
                        if (sendMessage != 0) {
                            OS.SelectObject(getDC, selectObject);
                        }
                        OS.ReleaseDC(this.handle, getDC);
                    }
                }
                return new LRESULT(callWindowProc);
            }
        }
        return null;
    }
    
    LRESULT wmNotifyToolTip(final NMTTCUSTOMDRAW nmttcustomdraw, final int n) {
        if (OS.IsWinCE) {
            return null;
        }
        switch (nmttcustomdraw.dwDrawStage) {
            case 1: {
                if (this.isCustomToolTip()) {
                    return new LRESULT(18);
                }
                break;
            }
            case 2: {
                final LVHITTESTINFO lvhittestinfo = new LVHITTESTINFO();
                final int getMessagePos = OS.GetMessagePos();
                final POINT point = new POINT();
                OS.POINTSTOPOINT(point, getMessagePos);
                OS.ScreenToClient(this.handle, point);
                lvhittestinfo.x = point.x;
                lvhittestinfo.y = point.y;
                if (OS.SendMessage(this.handle, 4153, 0, lvhittestinfo) >= 0) {
                    final TableItem getItem = this._getItem(lvhittestinfo.iItem);
                    final int getDC = OS.GetDC(this.handle);
                    int n2 = getItem.fontHandle(lvhittestinfo.iSubItem);
                    if (n2 == -1) {
                        n2 = OS.SendMessage(this.handle, 49, 0, 0);
                    }
                    final int selectObject = OS.SelectObject(getDC, n2);
                    int n3 = 1;
                    final RECT bounds = getItem.getBounds(lvhittestinfo.iItem, lvhittestinfo.iSubItem, true, true, false, false, getDC);
                    if (this.hooks(40)) {
                        final Event sendEraseItemEvent = this.sendEraseItemEvent(getItem, nmttcustomdraw, lvhittestinfo.iSubItem, bounds);
                        if (this.isDisposed()) {
                            break;
                        }
                        if (getItem.isDisposed()) {
                            break;
                        }
                        n3 = ((sendEraseItemEvent.doit && (sendEraseItemEvent.detail & 0x10) != 0x0) ? 1 : 0);
                    }
                    if (n3 != 0) {
                        final int saveDC = OS.SaveDC(nmttcustomdraw.hdc);
                        final int linesVisible = this.getLinesVisible() ? 1 : 0;
                        final RECT toolTipInset = this.toolTipInset(bounds);
                        OS.SetWindowOrgEx(nmttcustomdraw.hdc, toolTipInset.left, toolTipInset.top, null);
                        final GCData gcData = new GCData();
                        gcData.device = this.display;
                        gcData.foreground = OS.GetTextColor(nmttcustomdraw.hdc);
                        gcData.background = OS.GetBkColor(nmttcustomdraw.hdc);
                        gcData.font = Font.win32_new(this.display, n2);
                        final GC win32_new = GC.win32_new(nmttcustomdraw.hdc, gcData);
                        int left = bounds.left;
                        if (lvhittestinfo.iSubItem != 0) {
                            left -= linesVisible;
                        }
                        final Image image = getItem.getImage(lvhittestinfo.iSubItem);
                        if (image != null) {
                            final Rectangle bounds2 = image.getBounds();
                            final RECT bounds3 = getItem.getBounds(lvhittestinfo.iItem, lvhittestinfo.iSubItem, false, true, false, false, getDC);
                            final Point point2 = (this.imageList == null) ? new Point(bounds2.width, bounds2.height) : this.imageList.getImageSize();
                            int top = bounds3.top;
                            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                                top += Math.max(0, (bounds3.bottom - bounds3.top - point2.y) / 2);
                            }
                            win32_new.drawImage(image, bounds2.x, bounds2.y, bounds2.width, bounds2.height, left, top, point2.x, point2.y);
                            left += point2.x + 4 + ((lvhittestinfo.iSubItem == 0) ? -2 : 4);
                        }
                        else {
                            left += 6;
                        }
                        final String text = getItem.getText(lvhittestinfo.iSubItem);
                        if (text != null) {
                            int n4 = 2084;
                            final TableColumn tableColumn = (this.columns != null) ? this.columns[lvhittestinfo.iSubItem] : null;
                            if (tableColumn != null) {
                                if ((tableColumn.style & 0x1000000) != 0x0) {
                                    n4 |= 0x1;
                                }
                                if ((tableColumn.style & 0x20000) != 0x0) {
                                    n4 |= 0x2;
                                }
                            }
                            final TCHAR tchar = new TCHAR(this.getCodePage(), text, false);
                            final RECT rect = new RECT();
                            OS.SetRect(rect, left, bounds.top, bounds.right, bounds.bottom);
                            OS.DrawText(nmttcustomdraw.hdc, tchar, tchar.length(), rect, n4);
                        }
                        win32_new.dispose();
                        OS.RestoreDC(nmttcustomdraw.hdc, saveDC);
                    }
                    if (this.hooks(42)) {
                        this.sendPaintItemEvent(getItem, nmttcustomdraw, lvhittestinfo.iSubItem, getItem.getBounds(lvhittestinfo.iItem, lvhittestinfo.iSubItem, true, true, false, false, getDC));
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
