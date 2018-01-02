// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.TVITEM;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.HDITEM;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ControlListener;

public class TreeColumn extends Item
{
    Tree parent;
    boolean resizable;
    boolean moveable;
    String toolTipText;
    int id;
    
    public TreeColumn(final Tree parent, final int n) {
        super(parent, checkStyle(n));
        this.resizable = true;
        (this.parent = parent).createItem(this, parent.getColumnCount());
    }
    
    public TreeColumn(final Tree parent, final int n, final int n2) {
        super(parent, checkStyle(n));
        this.resizable = true;
        (this.parent = parent).createItem(this, n2);
    }
    
    public void addControlListener(final ControlListener controlListener) {
        this.checkWidget();
        if (controlListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(controlListener);
        this.addListener(11, typedListener);
        this.addListener(10, typedListener);
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
        return Widget.checkBits(n, 16384, 16777216, 131072, 0, 0, 0);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    public int getAlignment() {
        this.checkWidget();
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
    
    public boolean getMoveable() {
        this.checkWidget();
        return this.moveable;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public Tree getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public boolean getResizable() {
        this.checkWidget();
        return this.resizable;
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public int getWidth() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return 0;
        }
        final int hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0) {
            return 0;
        }
        final HDITEM hditem = new HDITEM();
        hditem.mask = 1;
        OS.SendMessage(hwndHeader, OS.HDM_GETITEM, index, hditem);
        return hditem.cxy;
    }
    
    public void pack() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        int max = 0;
        final int handle = this.parent.handle;
        final int hwndHeader = this.parent.hwndHeader;
        final RECT rect = new RECT();
        OS.SendMessage(hwndHeader, 4615, index, rect);
        final int getDC = OS.GetDC(handle);
        int selectObject = 0;
        final int sendMessage = OS.SendMessage(handle, 49, 0, 0);
        if (sendMessage != 0) {
            selectObject = OS.SelectObject(getDC, sendMessage);
        }
        final TVITEM tvitem = new TVITEM();
        tvitem.mask = 28;
        tvitem.hItem = OS.SendMessage(handle, 4362, 0, 0);
        while (tvitem.hItem != 0) {
            OS.SendMessage(handle, OS.TVM_GETITEM, 0, tvitem);
            final TreeItem treeItem = (tvitem.lParam != -1) ? this.parent.items[tvitem.lParam] : null;
            if (treeItem != null) {
                int right;
                if (this.parent.hooks(41)) {
                    final Event sendMeasureItemEvent = this.parent.sendMeasureItemEvent(treeItem, index, getDC, ((tvitem.state & 0x2) != 0x0) ? 2 : 0);
                    if (this.isDisposed()) {
                        break;
                    }
                    if (this.parent.isDisposed()) {
                        break;
                    }
                    right = sendMeasureItemEvent.x + sendMeasureItemEvent.width;
                }
                else {
                    int n = treeItem.fontHandle(index);
                    if (n != -1) {
                        n = OS.SelectObject(getDC, n);
                    }
                    final RECT bounds = treeItem.getBounds(index, true, true, false, false, false, getDC);
                    if (n != -1) {
                        OS.SelectObject(getDC, n);
                    }
                    right = bounds.right;
                }
                max = Math.max(max, right - rect.left);
            }
            tvitem.hItem = OS.SendMessage(handle, 4362, 6, tvitem.hItem);
        }
        final RECT rect2 = new RECT();
        final int n2 = 3072;
        final TCHAR tchar = new TCHAR(this.parent.getCodePage(), this.text, false);
        OS.DrawText(getDC, tchar, tchar.length(), rect2, n2);
        int n3 = rect2.right - rect2.left + 12;
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            n3 += 3;
        }
        if (this.image != null || this.parent.sortColumn == this) {
            Image image = null;
            if (this.parent.sortColumn == this && this.parent.sortDirection != 0) {
                if (OS.COMCTL32_MAJOR < 6) {
                    image = this.display.getSortImage(this.parent.sortDirection);
                }
                else {
                    n3 += 10;
                }
            }
            else {
                image = this.image;
            }
            if (image != null) {
                n3 += image.getBounds().width;
            }
            int sendMessage2;
            if (hwndHeader != 0 && OS.COMCTL32_VERSION >= OS.VERSION(5, 80)) {
                sendMessage2 = OS.SendMessage(hwndHeader, 4629, 0, 0);
            }
            else {
                sendMessage2 = OS.GetSystemMetrics(45) * 3;
            }
            n3 += sendMessage2 * 2;
        }
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(handle, getDC);
        this.setWidth(Math.max(n3, max + (this.parent.linesVisible ? 1 : 0)));
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.parent.sortColumn == this) {
            this.parent.sortColumn = null;
        }
    }
    
    public void removeControlListener(final ControlListener controlListener) {
        this.checkWidget();
        if (controlListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(10, controlListener);
        this.eventTable.unhook(11, controlListener);
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
    
    public void setAlignment(final int n) {
        this.checkWidget();
        if ((n & 0x1024000) == 0x0) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1 || index == 0) {
            return;
        }
        this.style &= 0xFEFDBFFF;
        this.style |= (n & 0x1024000);
        final int hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0) {
            return;
        }
        final HDITEM hditem = new HDITEM();
        hditem.mask = 4;
        OS.SendMessage(hwndHeader, OS.HDM_GETITEM, index, hditem);
        final HDITEM hditem2 = hditem;
        hditem2.fmt &= 0xFFFFFFFC;
        if ((this.style & 0x4000) == 0x4000) {
            final HDITEM hditem3 = hditem;
            hditem3.fmt |= 0x0;
        }
        if ((this.style & 0x1000000) == 0x1000000) {
            final HDITEM hditem4 = hditem;
            hditem4.fmt |= 0x2;
        }
        if ((this.style & 0x20000) == 0x20000) {
            final HDITEM hditem5 = hditem;
            hditem5.fmt |= 0x1;
        }
        OS.SendMessage(hwndHeader, OS.HDM_SETITEM, index, hditem);
        if (index != 0) {
            final int handle = this.parent.handle;
            this.parent.forceResize();
            final RECT rect = new RECT();
            final RECT rect2 = new RECT();
            OS.GetClientRect(handle, rect);
            OS.SendMessage(hwndHeader, 4615, index, rect2);
            rect.left = rect2.left;
            rect.right = rect2.right;
            OS.InvalidateRect(handle, rect, true);
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        super.setImage(image);
        if (this.parent.sortColumn != this || this.parent.sortDirection != 0) {
            this.setImage(image, false, false);
        }
    }
    
    void setImage(final Image image, final boolean b, final boolean b2) {
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final int hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0) {
            return;
        }
        final HDITEM hditem = new HDITEM();
        hditem.mask = 52;
        OS.SendMessage(hwndHeader, OS.HDM_GETITEM, index, hditem);
        final HDITEM hditem2 = hditem;
        hditem2.fmt &= 0xFFFFEFFF;
        if (image != null) {
            if (b) {
                final HDITEM hditem3 = hditem;
                hditem3.mask &= 0xFFFFFFDF;
                final HDITEM hditem4 = hditem;
                hditem4.fmt &= 0xFFFFF7FF;
                final HDITEM hditem5 = hditem;
                hditem5.fmt |= 0x2000;
                hditem.hbm = image.handle;
            }
            else {
                final HDITEM hditem6 = hditem;
                hditem6.mask &= 0xFFFFFFEF;
                final HDITEM hditem7 = hditem;
                hditem7.fmt &= 0xFFFFDFFF;
                final HDITEM hditem8 = hditem;
                hditem8.fmt |= 0x800;
                hditem.iImage = this.parent.imageIndexHeader(image);
            }
            if (b2) {
                final HDITEM hditem9 = hditem;
                hditem9.fmt |= 0x1000;
            }
        }
        else {
            final HDITEM hditem10 = hditem;
            hditem10.mask &= 0xFFFFFFCF;
            final HDITEM hditem11 = hditem;
            hditem11.fmt &= 0xFFFFD7FF;
        }
        OS.SendMessage(hwndHeader, OS.HDM_SETITEM, index, hditem);
    }
    
    public void setMoveable(final boolean moveable) {
        this.checkWidget();
        this.moveable = moveable;
    }
    
    public void setResizable(final boolean resizable) {
        this.checkWidget();
        this.resizable = resizable;
    }
    
    void setSortDirection(final int n) {
        if (OS.COMCTL32_MAJOR >= 6) {
            final int hwndHeader = this.parent.hwndHeader;
            if (hwndHeader != 0) {
                final int index = this.parent.indexOf(this);
                if (index == -1) {
                    return;
                }
                final HDITEM hditem = new HDITEM();
                hditem.mask = 36;
                OS.SendMessage(hwndHeader, OS.HDM_GETITEM, index, hditem);
                switch (n) {
                    case 128: {
                        final HDITEM hditem2 = hditem;
                        hditem2.fmt &= 0xFFFFF5FF;
                        final HDITEM hditem3 = hditem;
                        hditem3.fmt |= 0x400;
                        if (this.image == null) {
                            final HDITEM hditem4 = hditem;
                            hditem4.mask &= 0xFFFFFFDF;
                            break;
                        }
                        break;
                    }
                    case 1024: {
                        final HDITEM hditem5 = hditem;
                        hditem5.fmt &= 0xFFFFF3FF;
                        final HDITEM hditem6 = hditem;
                        hditem6.fmt |= 0x200;
                        if (this.image == null) {
                            final HDITEM hditem7 = hditem;
                            hditem7.mask &= 0xFFFFFFDF;
                            break;
                        }
                        break;
                    }
                    case 0: {
                        final HDITEM hditem8 = hditem;
                        hditem8.fmt &= 0xFFFFF9FF;
                        if (this.image != null) {
                            final HDITEM hditem9 = hditem;
                            hditem9.fmt |= 0x800;
                            hditem.iImage = this.parent.imageIndexHeader(this.image);
                            break;
                        }
                        final HDITEM hditem10 = hditem;
                        hditem10.fmt &= 0xFFFFF7FF;
                        final HDITEM hditem11 = hditem;
                        hditem11.mask &= 0xFFFFFFDF;
                        break;
                    }
                }
                OS.SendMessage(hwndHeader, OS.HDM_SETITEM, index, hditem);
                if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
                    final int handle = this.parent.handle;
                    this.parent.forceResize();
                    final RECT rect = new RECT();
                    final RECT rect2 = new RECT();
                    OS.GetClientRect(handle, rect);
                    OS.SendMessage(hwndHeader, 4615, index, rect2);
                    rect.left = rect2.left;
                    rect.right = rect2.right;
                    OS.InvalidateRect(handle, rect, true);
                }
            }
        }
        else {
            switch (n) {
                case 128:
                case 1024: {
                    this.setImage(this.display.getSortImage(n), true, true);
                    break;
                }
                case 0: {
                    this.setImage(this.image, false, false);
                    break;
                }
            }
        }
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        if (text.equals(this.text)) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        super.setText(text);
        final int getProcessHeap = OS.GetProcessHeap();
        final TCHAR tchar = new TCHAR(this.parent.getCodePage(), this.fixMnemonic(text, true), true);
        final int n = tchar.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, tchar, n);
        final int hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0) {
            return;
        }
        final HDITEM hditem = new HDITEM();
        hditem.mask = 2;
        hditem.pszText = heapAlloc;
        final int sendMessage = OS.SendMessage(hwndHeader, OS.HDM_SETITEM, index, hditem);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        if (sendMessage == 0) {
            this.error(13);
        }
    }
    
    public void setToolTipText(final String toolTipText) {
        this.checkWidget();
        this.toolTipText = toolTipText;
        if (this.parent.headerToolTipHandle == 0) {
            this.parent.createHeaderToolTips();
            this.parent.updateHeaderToolTips();
        }
    }
    
    public void setWidth(final int cxy) {
        this.checkWidget();
        if (cxy < 0) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final int hwndHeader = this.parent.hwndHeader;
        if (hwndHeader == 0) {
            return;
        }
        final HDITEM hditem = new HDITEM();
        hditem.mask = 1;
        hditem.cxy = cxy;
        OS.SendMessage(hwndHeader, OS.HDM_SETITEM, index, hditem);
        final RECT rect = new RECT();
        OS.SendMessage(hwndHeader, 4615, index, rect);
        this.parent.forceResize();
        final int handle = this.parent.handle;
        final RECT rect2 = new RECT();
        OS.GetClientRect(handle, rect2);
        rect2.left = rect.left;
        OS.InvalidateRect(handle, rect2, true);
        this.parent.setScrollWidth();
    }
    
    void updateToolTip(final int n) {
        final int headerToolTipHandle = this.parent.headerToolTipHandle;
        if (headerToolTipHandle != 0) {
            final int hwndHeader = this.parent.hwndHeader;
            final RECT rect = new RECT();
            if (OS.SendMessage(hwndHeader, 4615, n, rect) != 0) {
                final TOOLINFO toolinfo = new TOOLINFO();
                toolinfo.cbSize = TOOLINFO.sizeof;
                toolinfo.hwnd = hwndHeader;
                toolinfo.uId = this.id;
                toolinfo.left = rect.left;
                toolinfo.top = rect.top;
                toolinfo.right = rect.right;
                toolinfo.bottom = rect.bottom;
                OS.SendMessage(headerToolTipHandle, OS.TTM_NEWTOOLRECT, 0, toolinfo);
            }
        }
    }
}
