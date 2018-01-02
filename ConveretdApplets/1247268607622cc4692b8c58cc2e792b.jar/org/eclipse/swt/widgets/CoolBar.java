// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.NMCUSTOMDRAW;
import org.eclipse.swt.internal.win32.NMREBARCHEVRON;
import org.eclipse.swt.internal.win32.NMREBARCHILDSIZE;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.MARGINS;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.REBARBANDINFO;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.INITCOMMONCONTROLSEX;
import org.eclipse.swt.internal.win32.TCHAR;

public class CoolBar extends Composite
{
    CoolItem[] items;
    CoolItem[] originalItems;
    boolean locked;
    boolean ignoreResize;
    static final int ReBarProc;
    static final TCHAR ReBarClass;
    static final int SEPARATOR_WIDTH = 2;
    static final int MAX_WIDTH = 32767;
    static final int DEFAULT_COOLBAR_WIDTH = 0;
    static final int DEFAULT_COOLBAR_HEIGHT = 0;
    
    static {
        ReBarClass = new TCHAR(0, "ReBarWindow32", true);
        final INITCOMMONCONTROLSEX initcommoncontrolsex = new INITCOMMONCONTROLSEX();
        initcommoncontrolsex.dwSize = INITCOMMONCONTROLSEX.sizeof;
        initcommoncontrolsex.dwICC = 1024;
        OS.InitCommonControlsEx(initcommoncontrolsex);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, CoolBar.ReBarClass, wndclass);
        ReBarProc = wndclass.lpfnWndProc;
    }
    
    public CoolBar(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        if ((n & 0x200) != 0x0) {
            this.style |= 0x200;
            OS.SetWindowLong(this.handle, -16, OS.GetWindowLong(this.handle, -16) | 0x80);
        }
        else {
            this.style |= 0x100;
        }
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        return OS.CallWindowProc(CoolBar.ReBarProc, n, n2, n3, n4);
    }
    
    static int checkStyle(int n) {
        n |= 0x80000;
        return n & 0xFFFFFCFF;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int max = 0;
        final int borderWidth = this.getBorderWidth();
        final int n4 = (n == -1) ? 16383 : (n + borderWidth * 2);
        final int n5 = (n2 == -1) ? 16383 : (n2 + borderWidth * 2);
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        if (sendMessage != 0) {
            this.ignoreResize = true;
            boolean drawing = false;
            if (OS.IsWindowVisible(this.handle)) {
                if (OS.COMCTL32_MAJOR >= 6) {
                    drawing = true;
                    OS.UpdateWindow(this.handle);
                    OS.DefWindowProc(this.handle, 11, 0, 0);
                }
                else {
                    drawing = this.getDrawing();
                    if (drawing) {
                        OS.UpdateWindow(this.handle);
                        OS.SendMessage(this.handle, 11, 0, 0);
                    }
                }
            }
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            final int n6 = rect.right - rect.left;
            final int n7 = rect.bottom - rect.top;
            final int n8 = 30;
            this.SetWindowPos(this.handle, 0, 0, 0, n4, n5, n8);
            final RECT rect2 = new RECT();
            OS.SendMessage(this.handle, 1033, sendMessage - 1, rect2);
            max = Math.max(max, rect2.bottom);
            this.SetWindowPos(this.handle, 0, 0, 0, n6, n7, n8);
            final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
            rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
            rebarbandinfo.fMask = 513;
            int n9 = 0;
            for (int i = 0; i < sendMessage; ++i) {
                OS.SendMessage(this.handle, OS.RB_GETBANDINFO, i, rebarbandinfo);
                if ((rebarbandinfo.fStyle & 0x1) != 0x0) {
                    n3 = Math.max(n3, n9);
                    n9 = 0;
                }
                n9 += rebarbandinfo.cxIdeal + this.getMargin(i);
            }
            n3 = Math.max(n3, n9);
            if (drawing) {
                if (OS.COMCTL32_MAJOR >= 6) {
                    OS.DefWindowProc(this.handle, 11, 1, 0);
                }
                else {
                    OS.SendMessage(this.handle, 11, 1, 0);
                }
            }
            this.ignoreResize = false;
        }
        if (n3 == 0) {
            n3 = 0;
        }
        if (max == 0) {
            max = 0;
        }
        if ((this.style & 0x200) != 0x0) {
            final int n10 = n3;
            n3 = max;
            max = n10;
        }
        if (n != -1) {
            n3 = n;
        }
        if (n2 != -1) {
            max = n2;
        }
        return new Point(n3 + borderWidth * 2, max + borderWidth * 2);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        OS.SendMessage(this.handle, 48, OS.GetStockObject(13), 0);
    }
    
    void createItem(final CoolItem coolItem, final int n) {
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        if (n < 0 || n > sendMessage) {
            this.error(6);
        }
        int n2;
        for (n2 = 0; n2 < this.items.length && this.items[n2] != null; ++n2) {}
        if (n2 == this.items.length) {
            final CoolItem[] items = new CoolItem[this.items.length + 4];
            System.arraycopy(this.items, 0, items, 0, this.items.length);
            this.items = items;
        }
        final int getProcessHeap = OS.GetProcessHeap();
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, TCHAR.sizeof);
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 261;
        rebarbandinfo.fStyle = 192;
        if ((coolItem.style & 0x4) != 0x0) {
            final REBARBANDINFO rebarbandinfo2 = rebarbandinfo;
            rebarbandinfo2.fStyle |= 0x200;
        }
        rebarbandinfo.lpText = heapAlloc;
        rebarbandinfo.wID = n2;
        final int lastIndexOfRow = this.getLastIndexOfRow(n - 1);
        final boolean b = n == lastIndexOfRow + 1;
        if (b) {
            final REBARBANDINFO rebarbandinfo3 = rebarbandinfo;
            rebarbandinfo3.fMask |= 0x40;
            rebarbandinfo.cx = 32767;
        }
        if (n == 0 && sendMessage > 0) {
            this.getItem(0).setWrap(false);
        }
        if (OS.SendMessage(this.handle, OS.RB_INSERTBAND, n, rebarbandinfo) == 0) {
            this.error(14);
        }
        if (b) {
            this.resizeToPreferredWidth(lastIndexOfRow);
        }
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
        this.items[coolItem.id = n2] = coolItem;
        final int length = this.originalItems.length;
        final CoolItem[] originalItems = new CoolItem[length + 1];
        System.arraycopy(this.originalItems, 0, originalItems, 0, n);
        System.arraycopy(this.originalItems, n, originalItems, n + 1, length - n);
        originalItems[n] = coolItem;
        this.originalItems = originalItems;
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new CoolItem[4];
        this.originalItems = new CoolItem[0];
    }
    
    void destroyItem(final CoolItem coolItem) {
        final int sendMessage = OS.SendMessage(this.handle, 1040, coolItem.id, 0);
        final int sendMessage2 = OS.SendMessage(this.handle, 1036, 0, 0);
        if (sendMessage2 != 0) {
            final int lastIndexOfRow = this.getLastIndexOfRow(sendMessage);
            if (sendMessage == lastIndexOfRow) {
                this.resizeToMaximumWidth(lastIndexOfRow - 1);
            }
        }
        final Control control = coolItem.control;
        final boolean b = control != null && !control.isDisposed() && control.getVisible();
        CoolItem item = null;
        if (coolItem.getWrap() && sendMessage + 1 < sendMessage2) {
            item = this.getItem(sendMessage + 1);
            this.ignoreResize = !item.getWrap();
        }
        if (OS.SendMessage(this.handle, 1026, sendMessage, 0) == 0) {
            this.error(15);
        }
        this.items[coolItem.id] = null;
        coolItem.id = -1;
        if (this.ignoreResize) {
            item.setWrap(true);
            this.ignoreResize = false;
        }
        if (b) {
            control.setVisible(true);
        }
        int n;
        for (n = 0; n < this.originalItems.length && this.originalItems[n] != coolItem; ++n) {}
        final int n2 = this.originalItems.length - 1;
        final CoolItem[] originalItems = new CoolItem[n2];
        System.arraycopy(this.originalItems, 0, originalItems, 0, n);
        System.arraycopy(this.originalItems, n + 1, originalItems, n, n2 - n);
        this.originalItems = originalItems;
    }
    
    void drawThemeBackground(final int n, final int n2, final RECT rect) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.background == -1 && (this.style & 0x800000) != 0x0) {
            final Control backgroundControl = this.findBackgroundControl();
            if (backgroundControl != null && backgroundControl.backgroundImage != null) {
                this.fillBackground(n, backgroundControl.getBackgroundPixel(), rect);
                return;
            }
        }
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        OS.MapWindowPoints(this.handle, n2, rect2, 2);
        final POINT point = new POINT();
        OS.SetWindowOrgEx(n, -rect2.left, -rect2.top, point);
        OS.SendMessage(this.handle, 791, n, 12);
        OS.SetWindowOrgEx(n, point.x, point.y, null);
    }
    
    Control findThemeControl() {
        if ((this.style & 0x800000) != 0x0) {
            return this;
        }
        return (this.background == -1 && this.backgroundImage == null) ? this : super.findThemeControl();
    }
    
    int getMargin(final int n) {
        int n2 = 0;
        if (OS.COMCTL32_MAJOR >= 6) {
            final MARGINS margins = new MARGINS();
            OS.SendMessage(this.handle, 1064, 0, margins);
            n2 += margins.cxLeftWidth + margins.cxRightWidth;
        }
        final RECT rect = new RECT();
        OS.SendMessage(this.handle, 1058, n, rect);
        int n3;
        if ((this.style & 0x800000) != 0x0) {
            if ((this.style & 0x200) != 0x0) {
                n3 = n2 + (rect.top + 4);
            }
            else {
                n3 = n2 + (rect.left + 4);
            }
        }
        else if ((this.style & 0x200) != 0x0) {
            n3 = n2 + (rect.top + rect.bottom);
        }
        else {
            n3 = n2 + (rect.left + rect.right);
        }
        if ((this.style & 0x800000) == 0x0 && !this.isLastItemOfRow(n)) {
            n3 += 2;
        }
        return n3;
    }
    
    public CoolItem getItem(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        if (n < 0 || n >= sendMessage) {
            this.error(6);
        }
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 256;
        OS.SendMessage(this.handle, OS.RB_GETBANDINFO, n, rebarbandinfo);
        return this.items[rebarbandinfo.wID];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1036, 0, 0);
    }
    
    public int[] getItemOrder() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        final int[] array = new int[sendMessage];
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 256;
        for (int i = 0; i < sendMessage; ++i) {
            OS.SendMessage(this.handle, OS.RB_GETBANDINFO, i, rebarbandinfo);
            CoolItem coolItem;
            int n;
            for (coolItem = this.items[rebarbandinfo.wID], n = 0; n < this.originalItems.length && this.originalItems[n] != coolItem; ++n) {}
            if (n == this.originalItems.length) {
                this.error(8);
            }
            array[i] = n;
        }
        return array;
    }
    
    public CoolItem[] getItems() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        final CoolItem[] array = new CoolItem[sendMessage];
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 256;
        for (int i = 0; i < sendMessage; ++i) {
            OS.SendMessage(this.handle, OS.RB_GETBANDINFO, i, rebarbandinfo);
            array[i] = this.items[rebarbandinfo.wID];
        }
        return array;
    }
    
    public Point[] getItemSizes() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        final Point[] array = new Point[sendMessage];
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 32;
        final int n = ((this.style & 0x800000) == 0x0) ? 2 : 0;
        final MARGINS margins = new MARGINS();
        for (int i = 0; i < sendMessage; ++i) {
            final RECT rect = new RECT();
            OS.SendMessage(this.handle, 1033, i, rect);
            OS.SendMessage(this.handle, OS.RB_GETBANDINFO, i, rebarbandinfo);
            if (OS.COMCTL32_MAJOR >= 6) {
                OS.SendMessage(this.handle, 1064, 0, margins);
                final RECT rect2 = rect;
                rect2.left -= margins.cxLeftWidth;
                final RECT rect3 = rect;
                rect3.right += margins.cxRightWidth;
            }
            if (!this.isLastItemOfRow(i)) {
                final RECT rect4 = rect;
                rect4.right += n;
            }
            if ((this.style & 0x200) != 0x0) {
                array[i] = new Point(rebarbandinfo.cyChild, rect.right - rect.left);
            }
            else {
                array[i] = new Point(rect.right - rect.left, rebarbandinfo.cyChild);
            }
        }
        return array;
    }
    
    int getLastIndexOfRow(final int n) {
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        if (sendMessage == 0) {
            return -1;
        }
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 1;
        for (int i = n + 1; i < sendMessage; ++i) {
            OS.SendMessage(this.handle, OS.RB_GETBANDINFO, i, rebarbandinfo);
            if ((rebarbandinfo.fStyle & 0x1) != 0x0) {
                return i - 1;
            }
        }
        return sendMessage - 1;
    }
    
    boolean isLastItemOfRow(final int n) {
        if (n + 1 == OS.SendMessage(this.handle, 1036, 0, 0)) {
            return true;
        }
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 1;
        OS.SendMessage(this.handle, OS.RB_GETBANDINFO, n + 1, rebarbandinfo);
        return (rebarbandinfo.fStyle & 0x1) != 0x0;
    }
    
    public boolean getLocked() {
        this.checkWidget();
        return this.locked;
    }
    
    public int[] getWrapIndices() {
        this.checkWidget();
        final CoolItem[] items = this.getItems();
        final int[] array = new int[items.length];
        int n = 0;
        for (int i = 0; i < items.length; ++i) {
            if (items[i].getWrap()) {
                array[n++] = i;
            }
        }
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public int indexOf(final CoolItem coolItem) {
        this.checkWidget();
        if (coolItem == null) {
            this.error(4);
        }
        if (coolItem.isDisposed()) {
            this.error(5);
        }
        return OS.SendMessage(this.handle, 1040, coolItem.id, 0);
    }
    
    void resizeToPreferredWidth(final int n) {
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        if (n >= 0 && n < sendMessage) {
            final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
            rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
            rebarbandinfo.fMask = 512;
            OS.SendMessage(this.handle, OS.RB_GETBANDINFO, n, rebarbandinfo);
            final RECT rect = new RECT();
            OS.SendMessage(this.handle, 1058, n, rect);
            rebarbandinfo.cx = rebarbandinfo.cxIdeal + rect.left;
            if ((this.style & 0x800000) == 0x0) {
                final REBARBANDINFO rebarbandinfo2 = rebarbandinfo;
                rebarbandinfo2.cx += rect.right;
            }
            rebarbandinfo.fMask = 64;
            OS.SendMessage(this.handle, OS.RB_SETBANDINFO, n, rebarbandinfo);
        }
    }
    
    void resizeToMaximumWidth(final int n) {
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 64;
        rebarbandinfo.cx = 32767;
        OS.SendMessage(this.handle, OS.RB_SETBANDINFO, n, rebarbandinfo);
    }
    
    void releaseChildren(final boolean b) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final CoolItem coolItem = this.items[i];
                if (coolItem != null && !coolItem.isDisposed()) {
                    coolItem.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(b);
    }
    
    void removeControl(final Control control) {
        super.removeControl(control);
        for (int i = 0; i < this.items.length; ++i) {
            final CoolItem coolItem = this.items[i];
            if (coolItem != null && coolItem.control == control) {
                coolItem.setControl(null);
            }
        }
    }
    
    void reskinChildren(final int n) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final CoolItem coolItem = this.items[i];
                if (coolItem != null) {
                    coolItem.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    void setBackgroundPixel(int defaultBackground) {
        if (defaultBackground == -1) {
            defaultBackground = this.defaultBackground();
        }
        OS.SendMessage(this.handle, 1043, 0, defaultBackground);
        this.setItemColors(OS.SendMessage(this.handle, 1046, 0, 0), defaultBackground);
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        if (OS.IsWinCE) {
            OS.InvalidateRect(this.handle, null, true);
        }
        else {
            OS.RedrawWindow(this.handle, null, 0, 1157);
        }
    }
    
    void setForegroundPixel(int defaultForeground) {
        if (defaultForeground == -1) {
            defaultForeground = this.defaultForeground();
        }
        OS.SendMessage(this.handle, 1045, 0, defaultForeground);
        this.setItemColors(defaultForeground, OS.SendMessage(this.handle, 1044, 0, 0));
    }
    
    void setItemColors(final int clrFore, final int clrBack) {
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 2;
        rebarbandinfo.clrFore = clrFore;
        rebarbandinfo.clrBack = clrBack;
        for (int i = 0; i < sendMessage; ++i) {
            OS.SendMessage(this.handle, OS.RB_SETBANDINFO, i, rebarbandinfo);
        }
    }
    
    public void setItemLayout(final int[] itemOrder, final int[] wrapIndices, final Point[] itemSizes) {
        this.checkWidget();
        this.setRedraw(false);
        this.setItemOrder(itemOrder);
        this.setWrapIndices(wrapIndices);
        this.setItemSizes(itemSizes);
        this.setRedraw(true);
    }
    
    void setItemOrder(final int[] array) {
        if (array == null) {
            this.error(4);
        }
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        if (array.length != sendMessage) {
            this.error(5);
        }
        final boolean[] array2 = new boolean[sendMessage];
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i];
            if (n < 0 || n >= sendMessage) {
                this.error(6);
            }
            if (array2[n]) {
                this.error(5);
            }
            array2[n] = true;
        }
        new REBARBANDINFO().cbSize = REBARBANDINFO.sizeof;
        for (int j = 0; j < array.length; ++j) {
            final int sendMessage2 = OS.SendMessage(this.handle, 1040, this.originalItems[array[j]].id, 0);
            if (sendMessage2 != j) {
                final int lastIndexOfRow = this.getLastIndexOfRow(sendMessage2);
                final int lastIndexOfRow2 = this.getLastIndexOfRow(j);
                if (sendMessage2 == lastIndexOfRow) {
                    this.resizeToPreferredWidth(sendMessage2);
                }
                if (j == lastIndexOfRow2) {
                    this.resizeToPreferredWidth(j);
                }
                OS.SendMessage(this.handle, 1063, sendMessage2, j);
                if (sendMessage2 == lastIndexOfRow && sendMessage2 - 1 >= 0) {
                    this.resizeToMaximumWidth(sendMessage2 - 1);
                }
                if (j == lastIndexOfRow2) {
                    this.resizeToMaximumWidth(j);
                }
            }
        }
    }
    
    void setItemSizes(final Point[] array) {
        if (array == null) {
            this.error(4);
        }
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        if (array.length != sendMessage) {
            this.error(5);
        }
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 256;
        for (int i = 0; i < sendMessage; ++i) {
            OS.SendMessage(this.handle, OS.RB_GETBANDINFO, i, rebarbandinfo);
            this.items[rebarbandinfo.wID].setSize(array[i].x, array[i].y);
        }
    }
    
    public void setLocked(final boolean locked) {
        this.checkWidget();
        this.locked = locked;
        final int sendMessage = OS.SendMessage(this.handle, 1036, 0, 0);
        final REBARBANDINFO rebarbandinfo = new REBARBANDINFO();
        rebarbandinfo.cbSize = REBARBANDINFO.sizeof;
        rebarbandinfo.fMask = 1;
        for (int i = 0; i < sendMessage; ++i) {
            OS.SendMessage(this.handle, OS.RB_GETBANDINFO, i, rebarbandinfo);
            if (locked) {
                final REBARBANDINFO rebarbandinfo2 = rebarbandinfo;
                rebarbandinfo2.fStyle |= 0x100;
            }
            else {
                final REBARBANDINFO rebarbandinfo3 = rebarbandinfo;
                rebarbandinfo3.fStyle &= 0xFFFFFEFF;
            }
            OS.SendMessage(this.handle, OS.RB_SETBANDINFO, i, rebarbandinfo);
        }
    }
    
    public void setWrapIndices(int[] array) {
        this.checkWidget();
        if (array == null) {
            array = new int[0];
        }
        final int itemCount = this.getItemCount();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < 0 || array[i] >= itemCount) {
                this.error(6);
            }
        }
        this.setRedraw(false);
        final CoolItem[] items = this.getItems();
        for (int j = 0; j < items.length; ++j) {
            final CoolItem coolItem = items[j];
            if (coolItem.getWrap()) {
                this.resizeToPreferredWidth(j - 1);
                coolItem.setWrap(false);
            }
        }
        this.resizeToMaximumWidth(itemCount - 1);
        for (int k = 0; k < array.length; ++k) {
            final int n = array[k];
            if (n >= 0 && n < items.length) {
                items[n].setWrap(true);
                this.resizeToMaximumWidth(n - 1);
            }
        }
        this.setRedraw(true);
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x40 | 0x4 | 0x8200;
        if ((this.style & 0x800000) == 0x0) {
            n |= 0x400;
        }
        return n;
    }
    
    TCHAR windowClass() {
        return CoolBar.ReBarClass;
    }
    
    int windowProc() {
        return CoolBar.ReBarProc;
    }
    
    LRESULT WM_COMMAND(final int n, final int n2) {
        final LRESULT wm_COMMAND = super.WM_COMMAND(n, n2);
        if (wm_COMMAND != null) {
            return wm_COMMAND;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) {
            this.drawBackground(n);
            return null;
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_NOTIFY(final int n, final int n2) {
        final LRESULT wm_NOTIFY = super.WM_NOTIFY(n, n2);
        if (wm_NOTIFY != null) {
            return wm_NOTIFY;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_SETREDRAW(final int n, final int n2) {
        final LRESULT wm_SETREDRAW = super.WM_SETREDRAW(n, n2);
        if (wm_SETREDRAW != null) {
            return wm_SETREDRAW;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            return LRESULT.ZERO;
        }
        final Rectangle bounds = this.getBounds();
        final int callWindowProc = this.callWindowProc(this.handle, 11, n, n2);
        OS.DefWindowProc(this.handle, 11, n, n2);
        if (!bounds.equals(this.getBounds())) {
            this.parent.redraw(bounds.x, bounds.y, bounds.width, bounds.height, true);
        }
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        if (!this.ignoreResize) {
            return super.WM_SIZE(n, n2);
        }
        final int callWindowProc = this.callWindowProc(this.handle, 5, n, n2);
        if (callWindowProc == 0) {
            return LRESULT.ZERO;
        }
        return new LRESULT(callWindowProc);
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        Label_0523: {
            switch (nmhdr.code) {
                case -835: {
                    final int getMessagePos = OS.GetMessagePos();
                    final POINT point = new POINT();
                    OS.POINTSTOPOINT(point, getMessagePos);
                    OS.ScreenToClient(this.handle, point);
                    if (!this.sendDragEvent((this.display.lastButton != 0) ? this.display.lastButton : 1, point.x, point.y)) {
                        return LRESULT.ONE;
                    }
                    break;
                }
                case -839: {
                    final NMREBARCHILDSIZE nmrebarchildsize = new NMREBARCHILDSIZE();
                    OS.MoveMemory(nmrebarchildsize, n2, NMREBARCHILDSIZE.sizeof);
                    if (nmrebarchildsize.uBand == -1) {
                        break;
                    }
                    final Control control = this.items[nmrebarchildsize.wID].control;
                    if (control != null) {
                        control.setBounds(nmrebarchildsize.rcChild_left, nmrebarchildsize.rcChild_top, nmrebarchildsize.rcChild_right - nmrebarchildsize.rcChild_left, nmrebarchildsize.rcChild_bottom - nmrebarchildsize.rcChild_top);
                        break;
                    }
                    break;
                }
                case -831: {
                    if (this.ignoreResize) {
                        break;
                    }
                    final Point size = this.getSize();
                    final int borderWidth = this.getBorderWidth();
                    final int sendMessage = OS.SendMessage(this.handle, 1051, 0, 0);
                    if ((this.style & 0x200) != 0x0) {
                        this.setSize(sendMessage + 2 * borderWidth, size.y);
                        break;
                    }
                    this.setSize(size.x, sendMessage + 2 * borderWidth);
                    break;
                }
                case -841: {
                    final NMREBARCHEVRON nmrebarchevron = new NMREBARCHEVRON();
                    OS.MoveMemory(nmrebarchevron, n2, NMREBARCHEVRON.sizeof);
                    final CoolItem coolItem = this.items[nmrebarchevron.wID];
                    if (coolItem != null) {
                        final Event event = new Event();
                        event.detail = 4;
                        if ((this.style & 0x200) != 0x0) {
                            event.x = nmrebarchevron.right;
                            event.y = nmrebarchevron.top;
                        }
                        else {
                            event.x = nmrebarchevron.left;
                            event.y = nmrebarchevron.bottom;
                        }
                        coolItem.sendSelectionEvent(13, event, false);
                        break;
                    }
                    break;
                }
                case -12: {
                    if (OS.COMCTL32_MAJOR < 6) {
                        break;
                    }
                    if (this.findBackgroundControl() == null && (this.style & 0x800000) == 0x0) {
                        break;
                    }
                    final NMCUSTOMDRAW nmcustomdraw = new NMCUSTOMDRAW();
                    OS.MoveMemory(nmcustomdraw, n2, NMCUSTOMDRAW.sizeof);
                    switch (nmcustomdraw.dwDrawStage) {
                        case 3: {
                            return new LRESULT(68);
                        }
                        case 4: {
                            this.drawBackground(nmcustomdraw.hdc);
                            break Label_0523;
                        }
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(nmhdr, n, n2);
    }
}
