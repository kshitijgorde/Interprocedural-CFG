// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.internal.win32.HDITEM;
import org.eclipse.swt.internal.win32.LVCOLUMN;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ControlListener;

public class TableColumn extends Item
{
    Table parent;
    boolean resizable;
    boolean moveable;
    String toolTipText;
    int id;
    
    public TableColumn(final Table parent, final int n) {
        super(parent, checkStyle(n));
        this.resizable = true;
        (this.parent = parent).createItem(this, parent.getColumnCount());
    }
    
    public TableColumn(final Table parent, final int n, final int n2) {
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
    
    String getNameText() {
        return this.getText();
    }
    
    public Table getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public boolean getMoveable() {
        this.checkWidget();
        return this.moveable;
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
        return OS.SendMessage(this.parent.handle, 4125, index, 0);
    }
    
    public void pack() {
        this.checkWidget();
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final int handle = this.parent.handle;
        final int sendMessage = OS.SendMessage(handle, 4125, index, 0);
        int n = OS.SendMessage(handle, OS.LVM_GETSTRINGWIDTH, 0, new TCHAR(this.parent.getCodePage(), this.text, true)) + 12;
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            n += 3;
        }
        boolean b = false;
        if (this.image != null || this.parent.sortColumn == this) {
            b = true;
            Image image = null;
            if (this.parent.sortColumn == this && this.parent.sortDirection != 0) {
                if (OS.COMCTL32_MAJOR < 6) {
                    image = this.display.getSortImage(this.parent.sortDirection);
                }
                else {
                    n += 10;
                }
            }
            else {
                image = this.image;
            }
            if (image != null) {
                n += image.getBounds().width;
            }
            int sendMessage2;
            if (OS.COMCTL32_VERSION >= OS.VERSION(5, 80)) {
                sendMessage2 = OS.SendMessage(OS.SendMessage(handle, 4127, 0, 0), 4629, 0, 0);
            }
            else {
                sendMessage2 = OS.GetSystemMetrics(45) * 3;
            }
            n += sendMessage2 * 4;
        }
        this.parent.ignoreColumnResize = true;
        int n2 = 0;
        if (this.parent.hooks(41)) {
            final RECT rect = new RECT();
            final int sendMessage3 = OS.SendMessage(handle, 4127, 0, 0);
            OS.SendMessage(sendMessage3, 4615, index, rect);
            OS.MapWindowPoints(sendMessage3, handle, rect, 2);
            final int getDC = OS.GetDC(handle);
            int selectObject = 0;
            final int sendMessage4 = OS.SendMessage(handle, 49, 0, 0);
            if (sendMessage4 != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage4);
            }
            for (int sendMessage5 = OS.SendMessage(handle, 4100, 0, 0), i = 0; i < sendMessage5; ++i) {
                final TableItem getItem = this.parent._getItem(i, false);
                if (getItem != null) {
                    int n3 = getItem.fontHandle(index);
                    if (n3 != -1) {
                        n3 = OS.SelectObject(getDC, n3);
                    }
                    final Event sendMeasureItemEvent = this.parent.sendMeasureItemEvent(getItem, i, index, getDC);
                    if (n3 != -1) {
                        OS.SelectObject(getDC, n3);
                    }
                    if (this.isDisposed()) {
                        break;
                    }
                    if (this.parent.isDisposed()) {
                        break;
                    }
                    n2 = Math.max(n2, sendMeasureItemEvent.x + sendMeasureItemEvent.width - rect.left);
                }
            }
            if (sendMessage4 != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(handle, getDC);
            OS.SendMessage(handle, 4126, index, n2);
        }
        else {
            OS.SendMessage(handle, 4126, index, -1);
            n2 = OS.SendMessage(handle, 4125, index, 0);
            if (index == 0) {
                if (this.parent.imageList == null) {
                    n2 += 2;
                }
                if (!OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0) && !this.parent.firstColumnImage) {
                    final int sendMessage6 = OS.SendMessage(handle, 4098, 1, 0);
                    if (sendMessage6 != 0) {
                        final int[] array = { 0 };
                        OS.ImageList_GetIconSize(sendMessage6, array, new int[1]);
                        n2 += array[0];
                    }
                }
                if ((this.parent.style & 0x20) != 0x0) {
                    final int sendMessage7 = OS.SendMessage(handle, 4098, 2, 0);
                    if (sendMessage7 != 0) {
                        final int[] array2 = { 0 };
                        OS.ImageList_GetIconSize(sendMessage7, array2, new int[1]);
                        n2 += array2[0];
                    }
                }
            }
        }
        if (n > n2) {
            if (!b) {
                RECT rect2 = null;
                final boolean b2 = index == this.parent.getColumnCount() - 1;
                if (b2) {
                    rect2 = new RECT();
                    OS.GetWindowRect(handle, rect2);
                    OS.UpdateWindow(handle);
                    this.SetWindowPos(handle, 0, 0, 0, 0, rect2.bottom - rect2.top, 30);
                }
                OS.SendMessage(handle, 4126, index, -2);
                if (b2) {
                    this.SetWindowPos(handle, 0, 0, 0, rect2.right - rect2.left, rect2.bottom - rect2.top, 22);
                }
            }
            else {
                OS.SendMessage(handle, 4126, index, n);
            }
        }
        else if (index == 0) {
            OS.SendMessage(handle, 4126, index, n2);
        }
        this.parent.ignoreColumnResize = false;
        if (sendMessage != OS.SendMessage(handle, 4125, index, 0)) {
            this.updateToolTip(index);
            this.sendEvent(11);
            if (this.isDisposed()) {
                return;
            }
            int n4 = 0;
            final int[] columnOrder = this.parent.getColumnOrder();
            final TableColumn[] columns = this.parent.getColumns();
            for (int j = 0; j < columnOrder.length; ++j) {
                final TableColumn tableColumn = columns[columnOrder[j]];
                if (n4 != 0 && !tableColumn.isDisposed()) {
                    tableColumn.updateToolTip(columnOrder[j]);
                    tableColumn.sendEvent(10);
                }
                if (tableColumn == this) {
                    n4 = 1;
                }
            }
        }
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
        final int handle = this.parent.handle;
        final LVCOLUMN lvcolumn = new LVCOLUMN();
        lvcolumn.mask = 1;
        OS.SendMessage(handle, OS.LVM_GETCOLUMN, index, lvcolumn);
        final LVCOLUMN lvcolumn2 = lvcolumn;
        lvcolumn2.fmt &= 0xFFFFFFFC;
        int n2 = 0;
        if ((this.style & 0x4000) == 0x4000) {
            n2 = 0;
        }
        if ((this.style & 0x1000000) == 0x1000000) {
            n2 = 2;
        }
        if ((this.style & 0x20000) == 0x20000) {
            n2 = 1;
        }
        final LVCOLUMN lvcolumn3 = lvcolumn;
        lvcolumn3.fmt |= n2;
        OS.SendMessage(handle, OS.LVM_SETCOLUMN, index, lvcolumn);
        if (index != 0) {
            this.parent.forceResize();
            final RECT rect = new RECT();
            final RECT rect2 = new RECT();
            OS.GetClientRect(handle, rect);
            final int sendMessage = OS.SendMessage(handle, 4127, 0, 0);
            OS.SendMessage(sendMessage, 4615, index, rect2);
            OS.MapWindowPoints(sendMessage, handle, rect2, 2);
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
        final int handle = this.parent.handle;
        if (OS.COMCTL32_MAJOR < 6) {
            final int sendMessage = OS.SendMessage(handle, 4127, 0, 0);
            final HDITEM hditem = new HDITEM();
            hditem.mask = 52;
            OS.SendMessage(sendMessage, OS.HDM_GETITEM, index, hditem);
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
                hditem10.fmt &= 0xFFFFD7FF;
            }
            OS.SendMessage(sendMessage, OS.HDM_SETITEM, index, hditem);
        }
        else {
            final LVCOLUMN lvcolumn = new LVCOLUMN();
            lvcolumn.mask = 17;
            OS.SendMessage(handle, OS.LVM_GETCOLUMN, index, lvcolumn);
            if (image != null) {
                final LVCOLUMN lvcolumn2 = lvcolumn;
                lvcolumn2.fmt |= 0x800;
                lvcolumn.iImage = this.parent.imageIndexHeader(image);
                if (b2) {
                    final LVCOLUMN lvcolumn3 = lvcolumn;
                    lvcolumn3.fmt |= 0x1000;
                }
            }
            else {
                final LVCOLUMN lvcolumn4 = lvcolumn;
                lvcolumn4.mask &= 0xFFFFFFEF;
                final LVCOLUMN lvcolumn5 = lvcolumn;
                lvcolumn5.fmt &= 0xFFFFE7FF;
            }
            OS.SendMessage(handle, OS.LVM_SETCOLUMN, index, lvcolumn);
        }
    }
    
    public void setMoveable(final boolean moveable) {
        this.checkWidget();
        this.moveable = moveable;
        this.parent.updateMoveable();
    }
    
    public void setResizable(final boolean resizable) {
        this.checkWidget();
        this.resizable = resizable;
    }
    
    void setSortDirection(final int n) {
        if (OS.COMCTL32_MAJOR >= 6) {
            final int index = this.parent.indexOf(this);
            if (index == -1) {
                return;
            }
            final int handle = this.parent.handle;
            final int sendMessage = OS.SendMessage(handle, 4127, 0, 0);
            final HDITEM hditem = new HDITEM();
            hditem.mask = 36;
            OS.SendMessage(sendMessage, OS.HDM_GETITEM, index, hditem);
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
            OS.SendMessage(sendMessage, OS.HDM_SETITEM, index, hditem);
            this.parent.forceResize();
            final RECT rect = new RECT();
            OS.GetClientRect(handle, rect);
            if (OS.SendMessage(handle, 4096, 0, 0) != -1) {
                final int sendMessage2 = OS.SendMessage(handle, 4270, 0, 0);
                OS.SendMessage(handle, 4236, (n == 0) ? -1 : index, 0);
                final RECT rect2 = new RECT();
                if (sendMessage2 != -1 && OS.SendMessage(sendMessage, 4615, sendMessage2, rect2) != 0) {
                    OS.MapWindowPoints(sendMessage, handle, rect2, 2);
                    rect.left = rect2.left;
                    rect.right = rect2.right;
                    OS.InvalidateRect(handle, rect, true);
                }
            }
            final RECT rect3 = new RECT();
            if (OS.SendMessage(sendMessage, 4615, index, rect3) != 0) {
                OS.MapWindowPoints(sendMessage, handle, rect3, 2);
                rect.left = rect3.left;
                rect.right = rect3.right;
                OS.InvalidateRect(handle, rect, true);
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
        final int handle = this.parent.handle;
        final LVCOLUMN lvcolumn = new LVCOLUMN();
        lvcolumn.mask = 1;
        OS.SendMessage(handle, OS.LVM_GETCOLUMN, index, lvcolumn);
        final int getProcessHeap = OS.GetProcessHeap();
        final TCHAR tchar = new TCHAR(this.parent.getCodePage(), this.fixMnemonic(text, true), true);
        final int n = tchar.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, tchar, n);
        final LVCOLUMN lvcolumn2 = lvcolumn;
        lvcolumn2.mask |= 0x4;
        lvcolumn.pszText = heapAlloc;
        final int sendMessage = OS.SendMessage(handle, OS.LVM_SETCOLUMN, index, lvcolumn);
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
    
    public void setWidth(final int n) {
        this.checkWidget();
        if (n < 0) {
            return;
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return;
        }
        final int handle = this.parent.handle;
        if (n != OS.SendMessage(handle, 4125, index, 0)) {
            OS.SendMessage(handle, 4126, index, n);
        }
    }
    
    void updateToolTip(final int n) {
        final int headerToolTipHandle = this.parent.headerToolTipHandle;
        if (headerToolTipHandle != 0) {
            final int sendMessage = OS.SendMessage(this.parent.handle, 4127, 0, 0);
            final RECT rect = new RECT();
            if (OS.SendMessage(sendMessage, 4615, n, rect) != 0) {
                final TOOLINFO toolinfo = new TOOLINFO();
                toolinfo.cbSize = TOOLINFO.sizeof;
                toolinfo.hwnd = sendMessage;
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
