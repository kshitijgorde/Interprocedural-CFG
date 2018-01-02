// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.NMTTDISPINFO;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.TCHITTESTINFO;
import org.eclipse.swt.internal.win32.TCITEM;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.ImageList;

public class TabFolder extends Composite
{
    TabItem[] items;
    ImageList imageList;
    static final int TabFolderProc;
    static final TCHAR TabFolderClass;
    boolean createdAsRTL;
    static final int ID_UPDOWN = 1;
    
    static {
        TabFolderClass = new TCHAR(0, "SysTabControl32", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, TabFolder.TabFolderClass, wndclass);
        TabFolderProc = wndclass.lpfnWndProc;
        final int getModuleHandle = OS.GetModuleHandle(null);
        final int getProcessHeap = OS.GetProcessHeap();
        wndclass.hInstance = getModuleHandle;
        final WNDCLASS wndclass2 = wndclass;
        wndclass2.style &= 0xFFFFBFFC;
        final int n = TabFolder.TabFolderClass.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, TabFolder.TabFolderClass, n);
        wndclass.lpszClassName = heapAlloc;
        OS.RegisterClass(wndclass);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
    }
    
    public TabFolder(final Composite composite, final int n) {
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
        return OS.CallWindowProc(TabFolder.TabFolderProc, n, n2, n3, n4);
    }
    
    static int checkStyle(int checkBits) {
        if (OS.IsPPC && (checkBits & 0x80) == 0x0) {
            checkBits |= 0x400;
        }
        checkBits = Widget.checkBits(checkBits, 128, 1024, 0, 0, 0, 0);
        return checkBits & 0xFFFFFCFF;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final Point computeSize = super.computeSize(n, n2, b);
        final RECT rect = new RECT();
        final RECT rect2 = new RECT();
        OS.SendMessage(this.handle, 4904, 0, rect);
        int max = rect.left - rect.right;
        final int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0);
        if (sendMessage != 0) {
            OS.SendMessage(this.handle, 4874, sendMessage - 1, rect2);
            max = Math.max(max, rect2.right - rect.right);
        }
        final RECT rect3 = new RECT();
        OS.SetRect(rect3, 0, 0, max, computeSize.y);
        OS.SendMessage(this.handle, 4904, 1, rect3);
        final int borderWidth = this.getBorderWidth();
        final RECT rect4 = rect3;
        rect4.left -= borderWidth;
        final RECT rect5 = rect3;
        rect5.right += borderWidth;
        computeSize.x = Math.max(rect3.right - rect3.left, computeSize.x);
        return computeSize;
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        OS.SendMessage(this.handle, 4904, 1, rect);
        final int borderWidth = this.getBorderWidth();
        final RECT rect2 = rect;
        rect2.left -= borderWidth;
        final RECT rect3 = rect;
        rect3.right += borderWidth;
        final RECT rect4 = rect;
        rect4.top -= borderWidth;
        final RECT rect5 = rect;
        rect5.bottom += borderWidth;
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    void createItem(final TabItem tabItem, final int n) {
        final int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0);
        if (n < 0 || n > sendMessage) {
            this.error(6);
        }
        if (sendMessage == this.items.length) {
            final TabItem[] items = new TabItem[this.items.length + 4];
            System.arraycopy(this.items, 0, items, 0, this.items.length);
            this.items = items;
        }
        if (OS.SendMessage(this.handle, OS.TCM_INSERTITEM, n, new TCITEM()) == -1) {
            this.error(14);
        }
        System.arraycopy(this.items, n, this.items, n + 1, sendMessage - n);
        this.items[n] = tabItem;
        if (sendMessage == 0) {
            final Event event = new Event();
            event.item = this.items[0];
            this.sendSelectionEvent(13, event, true);
        }
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        if (OS.IsPPC) {
            OS.SendMessage(this.handle, 8199, 524, 0);
        }
        OS.SendMessage(OS.SendMessage(this.handle, 4909, 0, 0), 1048, 0, 32767);
        this.createdAsRTL = ((this.style & 0x4000000) != 0x0);
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new TabItem[4];
    }
    
    void destroyItem(final TabItem tabItem) {
        int sendMessage;
        int n;
        for (sendMessage = OS.SendMessage(this.handle, 4868, 0, 0), n = 0; n < sendMessage && this.items[n] != tabItem; ++n) {}
        if (n == sendMessage) {
            return;
        }
        final int sendMessage2 = OS.SendMessage(this.handle, 4875, 0, 0);
        if (OS.SendMessage(this.handle, 4872, n, 0) == 0) {
            this.error(15);
        }
        System.arraycopy(this.items, n + 1, this.items, n, --sendMessage - n);
        this.items[sendMessage] = null;
        if (sendMessage == 0) {
            if (this.imageList != null) {
                OS.SendMessage(this.handle, 4867, 0, 0);
                this.display.releaseImageList(this.imageList);
            }
            this.imageList = null;
            this.items = new TabItem[4];
        }
        if (sendMessage > 0 && n == sendMessage2) {
            this.setSelection(Math.max(0, sendMessage2 - 1), true);
        }
    }
    
    void drawThemeBackground(final int n, final int n2, final RECT rect) {
        final RECT rect2 = new RECT();
        OS.GetClientRect(this.handle, rect2);
        OS.MapWindowPoints(this.handle, n2, rect2, 2);
        if (OS.IntersectRect(new RECT(), rect2, rect)) {
            OS.DrawThemeBackground(this.display.hTabTheme(), n, 10, 0, rect2, null);
        }
    }
    
    Control findThemeControl() {
        return this;
    }
    
    public Rectangle getClientArea() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        OS.SendMessage(this.handle, 4904, 0, rect);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public TabItem getItem(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0);
        if (n < 0 || n >= sendMessage) {
            this.error(6);
        }
        return this.items[n];
    }
    
    public TabItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        final TCHITTESTINFO tchittestinfo = new TCHITTESTINFO();
        tchittestinfo.x = point.x;
        tchittestinfo.y = point.y;
        final int sendMessage = OS.SendMessage(this.handle, 4877, 0, tchittestinfo);
        if (sendMessage == -1) {
            return null;
        }
        return this.items[sendMessage];
    }
    
    public int getItemCount() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 4868, 0, 0);
    }
    
    public TabItem[] getItems() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0);
        final TabItem[] array = new TabItem[sendMessage];
        System.arraycopy(this.items, 0, array, 0, sendMessage);
        return array;
    }
    
    public TabItem[] getSelection() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4875, 0, 0);
        if (sendMessage == -1) {
            return new TabItem[0];
        }
        return new TabItem[] { this.items[sendMessage] };
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 4875, 0, 0);
    }
    
    int imageIndex(final Image image) {
        if (image == null) {
            return -2;
        }
        if (this.imageList == null) {
            final Rectangle bounds = image.getBounds();
            this.imageList = this.display.getImageList(0, bounds.width, bounds.height);
            final int add = this.imageList.add(image);
            OS.SendMessage(this.handle, 4867, 0, this.imageList.getHandle());
            return add;
        }
        int n = this.imageList.indexOf(image);
        if (n == -1) {
            n = this.imageList.add(image);
        }
        else {
            this.imageList.put(n, image);
        }
        return n;
    }
    
    public int indexOf(final TabItem tabItem) {
        this.checkWidget();
        if (tabItem == null) {
            this.error(4);
        }
        for (int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0), i = 0; i < sendMessage; ++i) {
            if (this.items[i] == tabItem) {
                return i;
            }
        }
        return -1;
    }
    
    Point minimumSize(final int n, final int n2, final boolean b) {
        final Control[] getChildren = this._getChildren();
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < getChildren.length; ++i) {
            Control control;
            int n5;
            int sendMessage;
            for (control = getChildren[i], n5 = 0, sendMessage = OS.SendMessage(this.handle, 4868, 0, 0); n5 < sendMessage && this.items[n5].control != control; ++n5) {}
            if (n5 == sendMessage) {
                final Rectangle bounds = control.getBounds();
                n3 = Math.max(n3, bounds.x + bounds.width);
                n4 = Math.max(n4, bounds.y + bounds.height);
            }
            else {
                final Point computeSize = control.computeSize(n, n2, b);
                n3 = Math.max(n3, computeSize.x);
                n4 = Math.max(n4, computeSize.y);
            }
        }
        return new Point(n3, n4);
    }
    
    boolean mnemonicHit(final char c) {
        for (int i = 0; i < this.items.length; ++i) {
            final TabItem tabItem = this.items[i];
            if (tabItem != null && Character.toUpperCase(c) == Character.toUpperCase(this.findMnemonic(tabItem.getText())) && this.forceFocus()) {
                if (i != this.getSelectionIndex()) {
                    this.setSelection(i, true);
                }
                return true;
            }
        }
        return false;
    }
    
    boolean mnemonicMatch(final char c) {
        for (int i = 0; i < this.items.length; ++i) {
            final TabItem tabItem = this.items[i];
            if (tabItem != null && Character.toUpperCase(c) == Character.toUpperCase(this.findMnemonic(tabItem.getText()))) {
                return true;
            }
        }
        return false;
    }
    
    void releaseChildren(final boolean b) {
        if (this.items != null) {
            for (int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0), i = 0; i < sendMessage; ++i) {
                final TabItem tabItem = this.items[i];
                if (tabItem != null && !tabItem.isDisposed()) {
                    tabItem.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(b);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 4867, 0, 0);
            this.display.releaseImageList(this.imageList);
        }
        this.imageList = null;
    }
    
    void removeControl(final Control control) {
        super.removeControl(control);
        for (int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0), i = 0; i < sendMessage; ++i) {
            final TabItem tabItem = this.items[i];
            if (tabItem.control == control) {
                tabItem.setControl(null);
            }
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
    
    void reskinChildren(final int n) {
        if (this.items != null) {
            for (int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0), i = 0; i < sendMessage; ++i) {
                final TabItem tabItem = this.items[i];
                if (tabItem != null) {
                    tabItem.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    public void setSelection(final TabItem tabItem) {
        this.checkWidget();
        if (tabItem == null) {
            this.error(4);
        }
        this.setSelection(new TabItem[] { tabItem });
    }
    
    public void setSelection(final TabItem[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        if (array.length == 0) {
            this.setSelection(-1, false);
        }
        else {
            for (int i = array.length - 1; i >= 0; --i) {
                final int index = this.indexOf(array[i]);
                if (index != -1) {
                    this.setSelection(index, false);
                }
            }
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        final Rectangle clientArea = this.getClientArea();
        super.setFont(font);
        if (!clientArea.equals(this.getClientArea())) {
            this.sendResize();
            final int sendMessage = OS.SendMessage(this.handle, 4875, 0, 0);
            if (sendMessage != -1) {
                final Control control = this.items[sendMessage].control;
                if (control != null && !control.isDisposed()) {
                    control.setBounds(this.getClientArea());
                }
            }
        }
    }
    
    public void setSelection(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 4868, 0, 0);
        if (n < 0 || n >= sendMessage) {
            return;
        }
        this.setSelection(n, false);
    }
    
    void setSelection(final int n, final boolean b) {
        final int sendMessage = OS.SendMessage(this.handle, 4875, 0, 0);
        if (sendMessage == n) {
            return;
        }
        if (sendMessage != -1) {
            final Control control = this.items[sendMessage].control;
            if (control != null && !control.isDisposed()) {
                control.setVisible(false);
            }
        }
        OS.SendMessage(this.handle, 4876, n, 0);
        final int sendMessage2 = OS.SendMessage(this.handle, 4875, 0, 0);
        if (sendMessage2 != -1) {
            final TabItem item = this.items[sendMessage2];
            final Control control2 = item.control;
            if (control2 != null && !control2.isDisposed()) {
                control2.setBounds(this.getClientArea());
                control2.setVisible(true);
            }
            if (b) {
                final Event event = new Event();
                event.item = item;
                this.sendSelectionEvent(13, event, true);
            }
        }
    }
    
    String toolTipText(final NMTTDISPINFO nmttdispinfo) {
        if ((nmttdispinfo.uFlags & 0x1) != 0x0) {
            return null;
        }
        final int idFrom = nmttdispinfo.idFrom;
        if (OS.SendMessage(this.handle, 4909, 0, 0) == nmttdispinfo.hwndFrom) {
            if ((this.style & 0x4000000) != 0x0) {
                nmttdispinfo.uFlags |= 0x4;
            }
            else {
                nmttdispinfo.uFlags &= 0xFFFFFFFB;
            }
            if (this.toolTipText != null) {
                return "";
            }
            if (idFrom >= 0 && idFrom < this.items.length) {
                final TabItem tabItem = this.items[idFrom];
                if (tabItem != null) {
                    return tabItem.toolTipText;
                }
            }
        }
        return super.toolTipText(nmttdispinfo);
    }
    
    boolean traversePage(final boolean b) {
        final int itemCount = this.getItemCount();
        if (itemCount <= 1) {
            return false;
        }
        final int selectionIndex = this.getSelectionIndex();
        int n;
        if (selectionIndex == -1) {
            n = 0;
        }
        else {
            n = (selectionIndex + (b ? 1 : -1) + itemCount) % itemCount;
        }
        this.setSelection(n, true);
        if (n == this.getSelectionIndex()) {
            OS.SendMessage(this.handle, 295, 3, 0);
            return true;
        }
        return false;
    }
    
    void updateOrientation() {
        super.updateOrientation();
        for (int i = OS.GetWindow(this.handle, 5); i != 0; i = OS.GetWindow(i, 2)) {
            final TCHAR tchar = new TCHAR(0, 128);
            OS.GetClassName(i, tchar, tchar.length());
            if (tchar.toString(0, tchar.strlen()).equals("msctls_updown32")) {
                final int getWindowLong = OS.GetWindowLong(i, -20);
                int n;
                if ((this.style & 0x4000000) != 0x0) {
                    n = (getWindowLong | 0x400000);
                }
                else {
                    n = (getWindowLong & 0xFFBFFFFF);
                }
                OS.SetWindowLong(i, -20, n);
                OS.InvalidateRect(i, null, true);
                break;
            }
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int n2 = rect.right - rect.left;
        final int n3 = rect.bottom - rect.top;
        OS.SetWindowPos(this.handle, 0, 0, 0, n2 - 1, n3 - 1, 6);
        OS.SetWindowPos(this.handle, 0, 0, 0, n2, n3, 6);
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x2000000;
        if ((this.style & 0x80000) != 0x0) {
            n |= 0x8000;
        }
        if ((this.style & 0x400) != 0x0) {
            n |= 0x2;
        }
        return n | 0x4000;
    }
    
    TCHAR windowClass() {
        return TabFolder.TabFolderClass;
    }
    
    int windowProc() {
        return TabFolder.TabFolderProc;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        final LRESULT wm_GETDLGCODE = super.WM_GETDLGCODE(n, n2);
        if (wm_GETDLGCODE != null) {
            return wm_GETDLGCODE;
        }
        return new LRESULT(8193);
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
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_MOUSELEAVE(final int n, final int n2) {
        final LRESULT wm_MOUSELEAVE = super.WM_MOUSELEAVE(n, n2);
        if (wm_MOUSELEAVE != null) {
            return wm_MOUSELEAVE;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            final int sendMessage = OS.SendMessage(this.handle, 4909, 0, 0);
            if (OS.SendMessage(sendMessage, OS.TTM_GETCURRENTTOOL, 0, toolinfo) != 0 && (toolinfo.uFlags & 0x1) == 0x0) {
                OS.SendMessage(sendMessage, OS.TTM_DELTOOL, 0, toolinfo);
                OS.SendMessage(sendMessage, OS.TTM_ADDTOOL, 0, toolinfo);
            }
        }
        return wm_MOUSELEAVE;
    }
    
    LRESULT WM_NCHITTEST(final int n, final int n2) {
        final LRESULT wm_NCHITTEST = super.WM_NCHITTEST(n, n2);
        if (wm_NCHITTEST != null) {
            return wm_NCHITTEST;
        }
        return new LRESULT(OS.DefWindowProc(this.handle, 132, n, n2));
    }
    
    LRESULT WM_NOTIFY(final int n, final int n2) {
        final LRESULT wm_NOTIFY = super.WM_NOTIFY(n, n2);
        if (wm_NOTIFY != null) {
            return wm_NOTIFY;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PARENTNOTIFY(final int n, final int n2) {
        final LRESULT wm_PARENTNOTIFY = super.WM_PARENTNOTIFY(n, n2);
        if (wm_PARENTNOTIFY != null) {
            return wm_PARENTNOTIFY;
        }
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return wm_PARENTNOTIFY;
        }
        if ((this.style & 0x4000000) != 0x0) {
            switch (OS.LOWORD(n)) {
                case 1: {
                    if (OS.HIWORD(n) == 1) {
                        OS.SetWindowLong(n2, -20, OS.GetWindowLong(n2, -20) | 0x400000);
                        break;
                    }
                    break;
                }
            }
        }
        return wm_PARENTNOTIFY;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (this.isDisposed()) {
            return wm_SIZE;
        }
        final int sendMessage = OS.SendMessage(this.handle, 4875, 0, 0);
        if (sendMessage != -1) {
            final Control control = this.items[sendMessage].control;
            if (control != null && !control.isDisposed()) {
                control.setBounds(this.getClientArea());
            }
        }
        return wm_SIZE;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final int n, final int n2) {
        final LRESULT wm_WINDOWPOSCHANGING = super.WM_WINDOWPOSCHANGING(n, n2);
        if (wm_WINDOWPOSCHANGING != null) {
            return wm_WINDOWPOSCHANGING;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return wm_WINDOWPOSCHANGING;
        }
        final WINDOWPOS windowpos = new WINDOWPOS();
        OS.MoveMemory(windowpos, n2, WINDOWPOS.sizeof);
        if ((windowpos.flags & 0x9) != 0x0) {
            return wm_WINDOWPOSCHANGING;
        }
        if ((OS.GetWindowLong(this.handle, -16) & 0x200) != 0x0) {
            OS.InvalidateRect(this.handle, null, true);
            return wm_WINDOWPOSCHANGING;
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, windowpos.cx, windowpos.cy);
        OS.SendMessage(this.handle, 131, 0, rect);
        final int n3 = rect.right - rect.left;
        final int n4 = rect.bottom - rect.top;
        OS.GetClientRect(this.handle, rect);
        int n5 = rect.right - rect.left;
        final int n6 = rect.bottom - rect.top;
        if (n3 == n5 && n4 == n6) {
            return wm_WINDOWPOSCHANGING;
        }
        final RECT rect2 = new RECT();
        OS.SendMessage(this.handle, 4904, 0, rect2);
        final int n7 = -rect2.right;
        final int n8 = -rect2.bottom;
        if (n3 != n5) {
            int n9;
            if (n3 < (n9 = n5)) {
                n9 = n3;
            }
            OS.SetRect(rect, n9 - n7, 0, n3, n4);
            OS.InvalidateRect(this.handle, rect, true);
        }
        if (n4 != n6) {
            int n10;
            if (n4 < (n10 = n6)) {
                n10 = n4;
            }
            if (n3 < n5) {
                n5 -= n7;
            }
            OS.SetRect(rect, 0, n10 - n8, n5, n4);
            OS.InvalidateRect(this.handle, rect, true);
        }
        return wm_WINDOWPOSCHANGING;
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        final int code = nmhdr.code;
        switch (code) {
            case -552:
            case -551: {
                TabItem item = null;
                final int sendMessage = OS.SendMessage(this.handle, 4875, 0, 0);
                if (sendMessage != -1) {
                    item = this.items[sendMessage];
                }
                if (item != null) {
                    final Control control = item.control;
                    if (control != null && !control.isDisposed()) {
                        if (code == -551) {
                            control.setBounds(this.getClientArea());
                        }
                        control.setVisible(code == -551);
                    }
                }
                if (code == -551) {
                    final Event event = new Event();
                    event.item = item;
                    this.sendSelectionEvent(13, event, false);
                    break;
                }
                break;
            }
        }
        return super.wmNotifyChild(nmhdr, n, n2);
    }
}
