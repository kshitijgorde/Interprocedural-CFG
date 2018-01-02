// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.NMTBHOTITEM;
import org.eclipse.swt.internal.win32.NMCUSTOMDRAW;
import org.eclipse.swt.internal.win32.NMTOOLBAR;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.NMTTDISPINFO;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.TBBUTTONINFO;
import org.eclipse.swt.internal.win32.TBBUTTON;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.ImageList;

public class ToolBar extends Composite
{
    int lastFocusId;
    int lastArrowId;
    int lastHotId;
    ToolItem[] items;
    ToolItem[] tabItemList;
    boolean ignoreResize;
    boolean ignoreMouse;
    ImageList imageList;
    ImageList disabledImageList;
    ImageList hotImageList;
    static final int ToolBarProc;
    static final TCHAR ToolBarClass;
    static final int DEFAULT_WIDTH = 24;
    static final int DEFAULT_HEIGHT = 22;
    
    static {
        ToolBarClass = new TCHAR(0, "ToolbarWindow32", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, ToolBar.ToolBarClass, wndclass);
        ToolBarProc = wndclass.lpfnWndProc;
    }
    
    public ToolBar(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        if ((n & 0x200) != 0x0) {
            this.style |= 0x200;
            int getWindowLong = OS.GetWindowLong(this.handle, -16);
            if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && (n & 0x20000) != 0x0) {
                getWindowLong |= 0x1000;
            }
            OS.SetWindowLong(this.handle, -16, getWindowLong | 0x80);
        }
        else {
            this.style |= 0x100;
        }
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (n2 == 262) {
            return OS.DefWindowProc(n, n2, n3, n4);
        }
        return OS.CallWindowProc(ToolBar.ToolBarProc, n, n2, n3, n4);
    }
    
    static int checkStyle(int n) {
        if ((n & 0x800000) == 0x0) {
            n |= 0x80000;
        }
        if ((n & 0x200) != 0x0) {
            n &= 0xFFFFFFBF;
        }
        return n & 0xFFFFFCFF;
    }
    
    void checkBuffered() {
        super.checkBuffered();
        if (OS.COMCTL32_MAJOR >= 6) {
            this.style |= 0x20000000;
        }
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int n4 = 0;
        if ((this.style & 0x200) != 0x0) {
            final RECT rect = new RECT();
            final TBBUTTON tbbutton = new TBBUTTON();
            for (int sendMessage = OS.SendMessage(this.handle, 1048, 0, 0), i = 0; i < sendMessage; ++i) {
                OS.SendMessage(this.handle, 1053, i, rect);
                n4 = Math.max(n4, rect.bottom);
                OS.SendMessage(this.handle, 1047, i, tbbutton);
                if ((tbbutton.fsStyle & 0x1) != 0x0) {
                    final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
                    tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
                    tbbuttoninfo.dwMask = 64;
                    OS.SendMessage(this.handle, OS.TB_GETBUTTONINFO, tbbutton.idCommand, tbbuttoninfo);
                    n3 = Math.max(n3, tbbuttoninfo.cx);
                }
                else {
                    n3 = Math.max(n3, rect.right);
                }
            }
        }
        else {
            final RECT rect2 = new RECT();
            OS.GetWindowRect(this.handle, rect2);
            final int n5 = rect2.right - rect2.left;
            final int n6 = rect2.bottom - rect2.top;
            final int borderWidth = this.getBorderWidth();
            final int n7 = (n == -1) ? 16383 : (n + borderWidth * 2);
            final int n8 = (n2 == -1) ? 16383 : (n2 + borderWidth * 2);
            final boolean b2 = this.getDrawing() && OS.IsWindowVisible(this.handle);
            this.ignoreResize = true;
            if (b2) {
                OS.UpdateWindow(this.handle);
            }
            final int n9 = 30;
            this.SetWindowPos(this.handle, 0, 0, 0, n7, n8, n9);
            final int sendMessage2 = OS.SendMessage(this.handle, 1048, 0, 0);
            if (sendMessage2 != 0) {
                final RECT rect3 = new RECT();
                OS.SendMessage(this.handle, 1053, sendMessage2 - 1, rect3);
                n3 = Math.max(n3, rect3.right);
                n4 = Math.max(n4, rect3.bottom);
            }
            this.SetWindowPos(this.handle, 0, 0, 0, n5, n6, n9);
            if (b2) {
                OS.ValidateRect(this.handle, null);
            }
            this.ignoreResize = false;
        }
        if (n3 == 0) {
            n3 = 24;
        }
        if (n4 == 0) {
            n4 = 22;
        }
        if (n != -1) {
            n3 = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        final Rectangle computeTrim = this.computeTrim(0, 0, n3, n4);
        return new Point(computeTrim.width, computeTrim.height);
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        final Rectangle computeTrim = super.computeTrim(n, n2, n3, n4);
        if ((OS.GetWindowLong(this.handle, -16) & 0x40) == 0x0) {
            final Rectangle rectangle = computeTrim;
            rectangle.height += 2;
        }
        return computeTrim;
    }
    
    Widget computeTabGroup() {
        final ToolItem[] getItems = this._getItems();
        if (this.tabItemList == null) {
            int n;
            for (n = 0; n < getItems.length && getItems[n].control == null; ++n) {}
            if (n == getItems.length) {
                return super.computeTabGroup();
            }
        }
        int i = OS.SendMessage(this.handle, 1095, 0, 0);
        if (i == -1) {
            i = this.lastHotId;
        }
        while (i >= 0) {
            final ToolItem toolItem = getItems[i];
            if (toolItem.isTabGroup()) {
                return toolItem;
            }
            --i;
        }
        return super.computeTabGroup();
    }
    
    Widget[] computeTabList() {
        final ToolItem[] getItems = this._getItems();
        if (this.tabItemList == null) {
            int n;
            for (n = 0; n < getItems.length && getItems[n].control == null; ++n) {}
            if (n == getItems.length) {
                return super.computeTabList();
            }
        }
        Widget[] array = new Widget[0];
        if (!this.isTabGroup() || !this.isEnabled() || !this.isVisible()) {
            return array;
        }
        final ToolItem[] array2 = (this.tabList != null) ? this._getTabItemList() : getItems;
        for (int i = 0; i < array2.length; ++i) {
            final Widget[] computeTabList = array2[i].computeTabList();
            if (computeTabList.length != 0) {
                final Widget[] array3 = new Widget[array.length + computeTabList.length];
                System.arraycopy(array, 0, array3, 0, array.length);
                System.arraycopy(computeTabList, 0, array3, array.length, computeTabList.length);
                array = array3;
            }
        }
        if (array.length == 0) {
            array = new Widget[] { this };
        }
        return array;
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFFFD;
        if ((this.style & 0x800000) != 0x0 && (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed())) {
            OS.SetWindowLong(this.handle, -16, OS.GetWindowLong(this.handle, -16) & 0xFFFF7FFF);
        }
        OS.SendMessage(this.handle, 48, OS.GetStockObject(13), 0);
        OS.SendMessage(this.handle, 1054, TBBUTTON.sizeof, 0);
        OS.SendMessage(this.handle, 1056, 0, 0);
        OS.SendMessage(this.handle, 1055, 0, 0);
        int n = 25;
        if (OS.COMCTL32_MAJOR >= 6) {
            n |= 0x80;
        }
        OS.SendMessage(this.handle, 1108, 0, n);
    }
    
    void createItem(final ToolItem toolItem, final int n) {
        final int sendMessage = OS.SendMessage(this.handle, 1048, 0, 0);
        if (n < 0 || n > sendMessage) {
            this.error(6);
        }
        int n2;
        for (n2 = 0; n2 < this.items.length && this.items[n2] != null; ++n2) {}
        if (n2 == this.items.length) {
            final ToolItem[] items = new ToolItem[this.items.length + 4];
            System.arraycopy(this.items, 0, items, 0, this.items.length);
            this.items = items;
        }
        final int widgetStyle = toolItem.widgetStyle();
        final TBBUTTON tbbutton = new TBBUTTON();
        tbbutton.idCommand = n2;
        tbbutton.fsStyle = (byte)widgetStyle;
        tbbutton.fsState = 4;
        if ((widgetStyle & 0x1) == 0x0) {
            tbbutton.iBitmap = -2;
        }
        if (OS.SendMessage(this.handle, OS.TB_INSERTBUTTON, n, tbbutton) == 0) {
            this.error(14);
        }
        this.items[toolItem.id = n2] = toolItem;
        if ((this.style & 0x200) != 0x0) {
            this.setRowCount(sendMessage + 1);
        }
        this.layoutItems();
    }
    
    void createWidget() {
        super.createWidget();
        this.items = new ToolItem[4];
        final int lastFocusId = -1;
        this.lastHotId = lastFocusId;
        this.lastArrowId = lastFocusId;
        this.lastFocusId = lastFocusId;
    }
    
    int defaultBackground() {
        if (OS.IsWinCE) {
            return OS.GetSysColor(OS.COLOR_BTNFACE);
        }
        return super.defaultBackground();
    }
    
    void destroyItem(final ToolItem toolItem) {
        final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
        tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
        tbbuttoninfo.dwMask = 9;
        final int sendMessage = OS.SendMessage(this.handle, OS.TB_GETBUTTONINFO, toolItem.id, tbbuttoninfo);
        if ((tbbuttoninfo.fsStyle & 0x1) == 0x0 && tbbuttoninfo.iImage != -2) {
            if (this.imageList != null) {
                this.imageList.put(tbbuttoninfo.iImage, null);
            }
            if (this.hotImageList != null) {
                this.hotImageList.put(tbbuttoninfo.iImage, null);
            }
            if (this.disabledImageList != null) {
                this.disabledImageList.put(tbbuttoninfo.iImage, null);
            }
        }
        OS.SendMessage(this.handle, 1046, sendMessage, 0);
        if (toolItem.id == this.lastFocusId) {
            this.lastFocusId = -1;
        }
        if (toolItem.id == this.lastArrowId) {
            this.lastArrowId = -1;
        }
        if (toolItem.id == this.lastHotId) {
            this.lastHotId = -1;
        }
        this.items[toolItem.id] = null;
        toolItem.id = -1;
        final int sendMessage2 = OS.SendMessage(this.handle, 1048, 0, 0);
        if (sendMessage2 == 0) {
            if (this.imageList != null) {
                OS.SendMessage(this.handle, 1072, 0, 0);
                this.display.releaseToolImageList(this.imageList);
            }
            if (this.hotImageList != null) {
                OS.SendMessage(this.handle, 1076, 0, 0);
                this.display.releaseToolHotImageList(this.hotImageList);
            }
            if (this.disabledImageList != null) {
                OS.SendMessage(this.handle, 1078, 0, 0);
                this.display.releaseToolDisabledImageList(this.disabledImageList);
            }
            final ImageList imageList = null;
            this.disabledImageList = imageList;
            this.hotImageList = imageList;
            this.imageList = imageList;
            this.items = new ToolItem[4];
        }
        if ((this.style & 0x200) != 0x0) {
            this.setRowCount(sendMessage2 - 1);
        }
        this.layoutItems();
    }
    
    void enableWidget(final boolean b) {
        super.enableWidget(b);
        for (int i = 0; i < this.items.length; ++i) {
            final ToolItem toolItem = this.items[i];
            if (toolItem != null && (toolItem.style & 0x2) == 0x0) {
                toolItem.updateImages(b && toolItem.getEnabled());
            }
        }
    }
    
    ImageList getDisabledImageList() {
        return this.disabledImageList;
    }
    
    ImageList getHotImageList() {
        return this.hotImageList;
    }
    
    ImageList getImageList() {
        return this.imageList;
    }
    
    public ToolItem getItem(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1048, 0, 0);
        if (n < 0 || n >= sendMessage) {
            this.error(6);
        }
        final TBBUTTON tbbutton = new TBBUTTON();
        if (OS.SendMessage(this.handle, 1047, n, tbbutton) == 0) {
            this.error(8);
        }
        return this.items[tbbutton.idCommand];
    }
    
    public ToolItem getItem(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        final ToolItem[] items = this.getItems();
        for (int i = 0; i < items.length; ++i) {
            if (items[i].getBounds().contains(point)) {
                return items[i];
            }
        }
        return null;
    }
    
    public int getItemCount() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1048, 0, 0);
    }
    
    public ToolItem[] getItems() {
        this.checkWidget();
        return this._getItems();
    }
    
    ToolItem[] _getItems() {
        final int sendMessage = OS.SendMessage(this.handle, 1048, 0, 0);
        final TBBUTTON tbbutton = new TBBUTTON();
        final ToolItem[] array = new ToolItem[sendMessage];
        for (int i = 0; i < sendMessage; ++i) {
            OS.SendMessage(this.handle, 1047, i, tbbutton);
            array[i] = this.items[tbbutton.idCommand];
        }
        return array;
    }
    
    public int getRowCount() {
        this.checkWidget();
        if ((this.style & 0x200) != 0x0) {
            return OS.SendMessage(this.handle, 1048, 0, 0);
        }
        return OS.SendMessage(this.handle, 1064, 0, 0);
    }
    
    ToolItem[] _getTabItemList() {
        if (this.tabItemList == null) {
            return this.tabItemList;
        }
        int n = 0;
        for (int i = 0; i < this.tabItemList.length; ++i) {
            if (!this.tabItemList[i].isDisposed()) {
                ++n;
            }
        }
        if (n == this.tabItemList.length) {
            return this.tabItemList;
        }
        final ToolItem[] tabItemList = new ToolItem[n];
        int n2 = 0;
        for (int j = 0; j < this.tabItemList.length; ++j) {
            if (!this.tabItemList[j].isDisposed()) {
                tabItemList[n2++] = this.tabItemList[j];
            }
        }
        return this.tabItemList = tabItemList;
    }
    
    public int indexOf(final ToolItem toolItem) {
        this.checkWidget();
        if (toolItem == null) {
            this.error(4);
        }
        if (toolItem.isDisposed()) {
            this.error(5);
        }
        return OS.SendMessage(this.handle, 1049, toolItem.id, 0);
    }
    
    void layoutItems() {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && (this.style & 0x20000) != 0x0 && (this.style & 0x200) == 0x0) {
            boolean b = false;
            boolean b2 = false;
            for (int i = 0; i < this.items.length; ++i) {
                final ToolItem toolItem = this.items[i];
                if (toolItem != null) {
                    if (!b) {
                        b = (toolItem.text.length() != 0);
                    }
                    if (!b2) {
                        b2 = (toolItem.image != null);
                    }
                    if (b && b2) {
                        break;
                    }
                }
            }
            final int getWindowLong;
            final int n = getWindowLong = OS.GetWindowLong(this.handle, -16);
            int n2;
            if (b && b2) {
                n2 = (getWindowLong | 0x1000);
            }
            else {
                n2 = (getWindowLong & 0xFFFFEFFF);
            }
            if (n2 != n) {
                this.setDropDownItems(false);
                OS.SetWindowLong(this.handle, -16, n2);
                OS.SendMessage(this.handle, 48, OS.SendMessage(this.handle, 49, 0, 0), 0);
                this.setDropDownItems(true);
            }
        }
        if ((this.style & 0x40) != 0x0) {
            OS.SendMessage(this.handle, 1057, 0, 0);
        }
        if ((this.style & 0x200) != 0x0 && OS.SendMessage(this.handle, 1048, 0, 0) > 1) {
            final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
            tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
            tbbuttoninfo.dwMask = 64;
            tbbuttoninfo.cx = (short)OS.LOWORD(OS.SendMessage(this.handle, 1082, 0, 0));
            int j;
            for (j = 0; j < this.items.length; ++j) {
                final ToolItem toolItem2 = this.items[j];
                if (toolItem2 != null && (toolItem2.style & 0x4) != 0x0) {
                    break;
                }
            }
            if (j < this.items.length) {
                final int sendMessage = OS.SendMessage(this.handle, 1110, 0, 0);
                final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
                tbbuttoninfo2.cx += (short)(OS.LOWORD(sendMessage) * 2);
            }
            for (int k = 0; k < this.items.length; ++k) {
                final ToolItem toolItem3 = this.items[k];
                if (toolItem3 != null && (toolItem3.style & 0x2) == 0x0) {
                    OS.SendMessage(this.handle, OS.TB_SETBUTTONINFO, toolItem3.id, tbbuttoninfo);
                }
            }
        }
        if ((this.style & 0x240) != 0x0 && (OS.GetWindowLong(this.handle, -16) & 0x1000) != 0x0) {
            final TBBUTTONINFO tbbuttoninfo3 = new TBBUTTONINFO();
            tbbuttoninfo3.cbSize = TBBUTTONINFO.sizeof;
            tbbuttoninfo3.dwMask = 64;
            for (int l = 0; l < this.items.length; ++l) {
                final ToolItem toolItem4 = this.items[l];
                if (toolItem4 != null && toolItem4.cx > 0) {
                    tbbuttoninfo3.cx = toolItem4.cx;
                    OS.SendMessage(this.handle, OS.TB_SETBUTTONINFO, toolItem4.id, tbbuttoninfo3);
                }
            }
        }
        for (int n3 = 0; n3 < this.items.length; ++n3) {
            final ToolItem toolItem5 = this.items[n3];
            if (toolItem5 != null) {
                toolItem5.resizeControl();
            }
        }
    }
    
    boolean mnemonicHit(final char c) {
        final int wcsToMbcs = Display.wcsToMbcs(c);
        final int[] array = { 0 };
        if (OS.SendMessage(this.handle, OS.TB_MAPACCELERATOR, wcsToMbcs, array) == 0) {
            return false;
        }
        if ((this.style & 0x800000) != 0x0 && !this.setTabGroupFocus()) {
            return false;
        }
        final int sendMessage = OS.SendMessage(this.handle, 1049, array[0], 0);
        if (sendMessage == -1) {
            return false;
        }
        OS.SendMessage(this.handle, 1096, sendMessage, 0);
        this.items[array[0]].click(false);
        return true;
    }
    
    boolean mnemonicMatch(final char c) {
        final int wcsToMbcs = Display.wcsToMbcs(c);
        final int[] array = { 0 };
        return OS.SendMessage(this.handle, OS.TB_MAPACCELERATOR, wcsToMbcs, array) != 0 && OS.SendMessage(this.handle, 1049, array[0], 0) != -1 && this.findMnemonic(this.items[array[0]].text) != '\0';
    }
    
    void releaseChildren(final boolean b) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final ToolItem toolItem = this.items[i];
                if (toolItem != null && !toolItem.isDisposed()) {
                    toolItem.release(false);
                }
            }
            this.items = null;
        }
        super.releaseChildren(b);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.imageList != null) {
            OS.SendMessage(this.handle, 1072, 0, 0);
            this.display.releaseToolImageList(this.imageList);
        }
        if (this.hotImageList != null) {
            OS.SendMessage(this.handle, 1076, 0, 0);
            this.display.releaseToolHotImageList(this.hotImageList);
        }
        if (this.disabledImageList != null) {
            OS.SendMessage(this.handle, 1078, 0, 0);
            this.display.releaseToolDisabledImageList(this.disabledImageList);
        }
        final ImageList imageList = null;
        this.disabledImageList = imageList;
        this.hotImageList = imageList;
        this.imageList = imageList;
    }
    
    void removeControl(final Control control) {
        super.removeControl(control);
        for (int i = 0; i < this.items.length; ++i) {
            final ToolItem toolItem = this.items[i];
            if (toolItem != null && toolItem.control == control) {
                toolItem.setControl(null);
            }
        }
    }
    
    void reskinChildren(final int n) {
        if (this.items != null) {
            for (int i = 0; i < this.items.length; ++i) {
                final ToolItem toolItem = this.items[i];
                if (toolItem != null) {
                    toolItem.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    void setBackgroundImage(final int backgroundImage) {
        super.setBackgroundImage(backgroundImage);
        this.setBackgroundTransparent(backgroundImage != 0);
    }
    
    void setBackgroundPixel(final int backgroundPixel) {
        super.setBackgroundPixel(backgroundPixel);
        this.setBackgroundTransparent(backgroundPixel != -1);
    }
    
    void setBackgroundTransparent(final boolean b) {
        if ((this.style & 0x800000) != 0x0 && (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed())) {
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            int n;
            if (!b && this.findBackgroundControl() == null) {
                n = (getWindowLong & 0xFFFF7FFF);
            }
            else {
                n = (getWindowLong | 0x8000);
            }
            OS.SetWindowLong(this.handle, -16, n);
        }
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.parent.lpwp != null && this.getDrawing() && OS.IsWindowVisible(this.handle)) {
            this.parent.setResizeChildren(false);
            this.parent.setResizeChildren(true);
        }
        super.setBounds(n, n2, n3, n4, n5);
    }
    
    void setDefaultFont() {
        super.setDefaultFont();
        OS.SendMessage(this.handle, 1056, 0, 0);
        OS.SendMessage(this.handle, 1055, 0, 0);
    }
    
    void setDropDownItems(final boolean b) {
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            boolean b2 = false;
            boolean b3 = false;
            for (int i = 0; i < this.items.length; ++i) {
                final ToolItem toolItem = this.items[i];
                if (toolItem != null) {
                    if (!b2) {
                        b2 = (toolItem.text.length() != 0);
                    }
                    if (!b3) {
                        b3 = (toolItem.image != null);
                    }
                    if (b2 && b3) {
                        break;
                    }
                }
            }
            if (b3 && !b2) {
                for (int j = 0; j < this.items.length; ++j) {
                    final ToolItem toolItem2 = this.items[j];
                    if (toolItem2 != null && (toolItem2.style & 0x4) != 0x0) {
                        final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
                        tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
                        tbbuttoninfo.dwMask = 8;
                        OS.SendMessage(this.handle, OS.TB_GETBUTTONINFO, toolItem2.id, tbbuttoninfo);
                        if (b) {
                            final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
                            tbbuttoninfo2.fsStyle |= 0x8;
                        }
                        else {
                            final TBBUTTONINFO tbbuttoninfo3 = tbbuttoninfo;
                            tbbuttoninfo3.fsStyle &= 0xFFFFFFF7;
                        }
                        OS.SendMessage(this.handle, OS.TB_SETBUTTONINFO, toolItem2.id, tbbuttoninfo);
                    }
                }
            }
        }
    }
    
    void setDisabledImageList(final ImageList disabledImageList) {
        if (this.disabledImageList == disabledImageList) {
            return;
        }
        int handle = 0;
        if ((this.disabledImageList = disabledImageList) != null) {
            handle = this.disabledImageList.getHandle();
        }
        this.setDropDownItems(false);
        OS.SendMessage(this.handle, 1078, 0, handle);
        this.setDropDownItems(true);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        this.setDropDownItems(false);
        super.setFont(font);
        this.setDropDownItems(true);
        int i = 0;
        final int n = 60;
        while (i < this.items.length) {
            final ToolItem toolItem = this.items[i];
            if (toolItem != null && (toolItem.style & n) != 0x0) {
                break;
            }
            ++i;
        }
        if (i == this.items.length) {
            OS.SendMessage(this.handle, 1056, 0, 0);
            OS.SendMessage(this.handle, 1055, 0, 0);
        }
        this.layoutItems();
    }
    
    void setHotImageList(final ImageList hotImageList) {
        if (this.hotImageList == hotImageList) {
            return;
        }
        int handle = 0;
        if ((this.hotImageList = hotImageList) != null) {
            handle = this.hotImageList.getHandle();
        }
        this.setDropDownItems(false);
        OS.SendMessage(this.handle, 1076, 0, handle);
        this.setDropDownItems(true);
    }
    
    void setImageList(final ImageList imageList) {
        if (this.imageList == imageList) {
            return;
        }
        int handle = 0;
        if ((this.imageList = imageList) != null) {
            handle = imageList.getHandle();
        }
        this.setDropDownItems(false);
        OS.SendMessage(this.handle, 1072, 0, handle);
        this.setDropDownItems(true);
    }
    
    public boolean setParent(final Composite parent) {
        this.checkWidget();
        if (!super.setParent(parent)) {
            return false;
        }
        OS.SendMessage(this.handle, 1061, parent.handle, 0);
        OS.SetWindowLongPtr(OS.SendMessage(this.handle, 1059, 0, 0), -8, parent.getShell().handle);
        return true;
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        this.setDropDownItems(false);
        super.setRedraw(redraw);
        this.setDropDownItems(true);
    }
    
    void setRowCount(int n) {
        if ((this.style & 0x200) != 0x0) {
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            OS.MapWindowPoints(0, this.parent.handle, rect, 2);
            this.ignoreResize = true;
            n += 2;
            OS.SendMessage(this.handle, 1063, OS.MAKEWPARAM(n, 1), 0);
            this.SetWindowPos(this.handle, 0, 0, 0, rect.right - rect.left, rect.bottom - rect.top, 22);
            this.ignoreResize = false;
        }
    }
    
    void setTabItemList(ToolItem[] tabItemList) {
        this.checkWidget();
        if (tabItemList != null) {
            for (int i = 0; i < tabItemList.length; ++i) {
                final ToolItem toolItem = tabItemList[i];
                if (toolItem == null) {
                    this.error(5);
                }
                if (toolItem.isDisposed()) {
                    this.error(5);
                }
                if (toolItem.parent != this) {
                    this.error(32);
                }
            }
            final ToolItem[] array = new ToolItem[tabItemList.length];
            System.arraycopy(tabItemList, 0, array, 0, tabItemList.length);
            tabItemList = array;
        }
        this.tabItemList = tabItemList;
    }
    
    boolean setTabItemFocus() {
        int i;
        for (i = 0; i < this.items.length; ++i) {
            final ToolItem toolItem = this.items[i];
            if (toolItem != null && (toolItem.style & 0x2) == 0x0 && toolItem.getEnabled()) {
                break;
            }
        }
        return i != this.items.length && super.setTabItemFocus();
    }
    
    String toolTipText(final NMTTDISPINFO nmttdispinfo) {
        if ((nmttdispinfo.uFlags & 0x1) != 0x0) {
            return null;
        }
        if (!this.hasCursor()) {
            return "";
        }
        final int idFrom = nmttdispinfo.idFrom;
        if (OS.SendMessage(this.handle, 1059, 0, 0) == nmttdispinfo.hwndFrom) {
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
                final ToolItem toolItem = this.items[idFrom];
                if (toolItem != null) {
                    if (this.lastArrowId != -1) {
                        return "";
                    }
                    return toolItem.toolTipText;
                }
            }
        }
        return super.toolTipText(nmttdispinfo);
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x4 | 0x100 | 0x2000;
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            n |= 0x8000;
        }
        if ((this.style & 0x8) == 0x0) {
            n |= 0x40;
        }
        if ((this.style & 0x40) != 0x0) {
            n |= 0x200;
        }
        if ((this.style & 0x800000) != 0x0) {
            n |= 0x800;
        }
        if ((OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) && (this.style & 0x20000) != 0x0) {
            n |= 0x1000;
        }
        return n;
    }
    
    TCHAR windowClass() {
        return ToolBar.ToolBarClass;
    }
    
    int windowProc() {
        return ToolBar.ToolBarProc;
    }
    
    LRESULT WM_CAPTURECHANGED(final int n, final int n2) {
        final LRESULT wm_CAPTURECHANGED = super.WM_CAPTURECHANGED(n, n2);
        if (wm_CAPTURECHANGED != null) {
            return wm_CAPTURECHANGED;
        }
        for (int i = 0; i < this.items.length; ++i) {
            final ToolItem toolItem = this.items[i];
            if (toolItem != null) {
                final int sendMessage = OS.SendMessage(this.handle, 1042, toolItem.id, 0);
                if ((sendMessage & 0x2) != 0x0) {
                    OS.SendMessage(this.handle, 1041, toolItem.id, sendMessage & 0xFFFFFFFD);
                }
            }
        }
        return wm_CAPTURECHANGED;
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        final LRESULT wm_CHAR = super.WM_CHAR(n, n2);
        if (wm_CHAR != null) {
            return wm_CHAR;
        }
        switch (n) {
            case 32: {
                final int sendMessage = OS.SendMessage(this.handle, 1095, 0, 0);
                if (sendMessage == -1) {
                    break;
                }
                final TBBUTTON tbbutton = new TBBUTTON();
                if (OS.SendMessage(this.handle, 1047, sendMessage, tbbutton) != 0) {
                    this.items[tbbutton.idCommand].click(false);
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return wm_CHAR;
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
        if (this.findBackgroundControl() != null && OS.COMCTL32_MAJOR < 6) {
            this.drawBackground(n);
            return LRESULT.ONE;
        }
        return wm_ERASEBKGND;
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
            case 32: {
                return LRESULT.ZERO;
            }
            default: {
                return wm_KEYDOWN;
            }
        }
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        final int sendMessage = OS.SendMessage(this.handle, 1095, 0, 0);
        final TBBUTTON tbbutton = new TBBUTTON();
        if (OS.SendMessage(this.handle, 1047, sendMessage, tbbutton) != 0) {
            this.lastFocusId = tbbutton.idCommand;
        }
        return super.WM_KILLFOCUS(n, n2);
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONDOWN(n, n2);
    }
    
    LRESULT WM_LBUTTONUP(final int n, final int n2) {
        if (this.ignoreMouse) {
            return null;
        }
        return super.WM_LBUTTONUP(n, n2);
    }
    
    LRESULT WM_MOUSELEAVE(final int n, final int n2) {
        final LRESULT wm_MOUSELEAVE = super.WM_MOUSELEAVE(n, n2);
        if (wm_MOUSELEAVE != null) {
            return wm_MOUSELEAVE;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            final int sendMessage = OS.SendMessage(this.handle, 1059, 0, 0);
            if (OS.SendMessage(sendMessage, OS.TTM_GETCURRENTTOOL, 0, toolinfo) != 0 && (toolinfo.uFlags & 0x1) == 0x0) {
                OS.SendMessage(sendMessage, OS.TTM_DELTOOL, 0, toolinfo);
                OS.SendMessage(sendMessage, OS.TTM_ADDTOOL, 0, toolinfo);
            }
        }
        return wm_MOUSELEAVE;
    }
    
    LRESULT WM_MOUSEMOVE(final int n, final int n2) {
        if (OS.GetMessagePos() != this.display.lastMouse) {
            this.lastArrowId = -1;
        }
        return super.WM_MOUSEMOVE(n, n2);
    }
    
    LRESULT WM_NOTIFY(final int n, final int n2) {
        final LRESULT wm_NOTIFY = super.WM_NOTIFY(n, n2);
        if (wm_NOTIFY != null) {
            return wm_NOTIFY;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        final LRESULT wm_SETFOCUS = super.WM_SETFOCUS(n, n2);
        if (this.lastFocusId != -1 && this.handle == OS.GetFocus()) {
            OS.SendMessage(this.handle, 1096, OS.SendMessage(this.handle, 1049, this.lastFocusId, 0), 0);
        }
        return wm_SETFOCUS;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        if (this.ignoreResize) {
            final int callWindowProc = this.callWindowProc(this.handle, 5, n, n2);
            if (callWindowProc == 0) {
                return LRESULT.ZERO;
            }
            return new LRESULT(callWindowProc);
        }
        else {
            final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
            if (this.isDisposed()) {
                return wm_SIZE;
            }
            if ((this.style & 0x800) != 0x0 && (this.style & 0x40) != 0x0) {
                final RECT rect = new RECT();
                OS.GetWindowRect(this.handle, rect);
                int i = 0;
                final int n3 = this.getBorderWidth() * 2;
                final RECT rect2 = new RECT();
                int sendMessage;
                for (sendMessage = OS.SendMessage(this.handle, 1048, 0, 0); i < sendMessage; ++i) {
                    OS.SendMessage(this.handle, 1053, i, rect2);
                    OS.MapWindowPoints(this.handle, 0, rect2, 2);
                    if (rect2.right > rect.right - n3 * 2) {
                        break;
                    }
                }
                final int sendMessage2 = OS.SendMessage(this.handle, 1109, 0, 0);
                int n4;
                if (i == sendMessage) {
                    n4 = (sendMessage2 | 0x10);
                }
                else {
                    n4 = (sendMessage2 & 0xFFFFFFEF);
                }
                OS.SendMessage(this.handle, 1108, 0, n4);
            }
            this.layoutItems();
            return wm_SIZE;
        }
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final int n, final int n2) {
        final LRESULT wm_WINDOWPOSCHANGING = super.WM_WINDOWPOSCHANGING(n, n2);
        if (wm_WINDOWPOSCHANGING != null) {
            return wm_WINDOWPOSCHANGING;
        }
        if (this.ignoreResize) {
            return wm_WINDOWPOSCHANGING;
        }
        if (!this.getDrawing()) {
            return wm_WINDOWPOSCHANGING;
        }
        if ((this.style & 0x40) == 0x0) {
            return wm_WINDOWPOSCHANGING;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return wm_WINDOWPOSCHANGING;
        }
        if (OS.SendMessage(this.handle, 1064, 0, 0) == 1) {
            return wm_WINDOWPOSCHANGING;
        }
        final WINDOWPOS windowpos = new WINDOWPOS();
        OS.MoveMemory(windowpos, n2, WINDOWPOS.sizeof);
        if ((windowpos.flags & 0x9) != 0x0) {
            return wm_WINDOWPOSCHANGING;
        }
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final RECT rect2 = new RECT();
        OS.SetRect(rect2, 0, 0, windowpos.cx, windowpos.cy);
        OS.SendMessage(this.handle, 131, 0, rect2);
        final int n3 = rect.right - rect.left;
        if (rect2.right - rect2.left > n3) {
            final RECT rect3 = new RECT();
            OS.SetRect(rect3, n3 - 2, 0, n3, rect2.bottom - rect2.top);
            OS.InvalidateRect(this.handle, rect3, false);
        }
        return wm_WINDOWPOSCHANGING;
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        final ToolItem toolItem = this.items[OS.LOWORD(n)];
        if (toolItem == null) {
            return null;
        }
        return toolItem.wmCommandChild(n, n2);
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        Label_0496: {
            switch (nmhdr.code) {
                case -710: {
                    final NMTOOLBAR nmtoolbar = new NMTOOLBAR();
                    OS.MoveMemory(nmtoolbar, n2, NMTOOLBAR.sizeof);
                    final ToolItem toolItem = this.items[nmtoolbar.iItem];
                    if (toolItem != null) {
                        final Event event = new Event();
                        event.detail = 4;
                        final int sendMessage = OS.SendMessage(this.handle, 1049, nmtoolbar.iItem, 0);
                        final RECT rect = new RECT();
                        OS.SendMessage(this.handle, 1053, sendMessage, rect);
                        event.x = rect.left;
                        event.y = rect.bottom;
                        toolItem.sendSelectionEvent(13, event, false);
                        break;
                    }
                    break;
                }
                case -12: {
                    if (OS.COMCTL32_MAJOR < 6) {
                        break;
                    }
                    final NMCUSTOMDRAW nmcustomdraw = new NMCUSTOMDRAW();
                    OS.MoveMemory(nmcustomdraw, n2, NMCUSTOMDRAW.sizeof);
                    switch (nmcustomdraw.dwDrawStage) {
                        case 3: {
                            if ((OS.GetWindowLong(this.handle, -16) & 0x800) == 0x0) {
                                this.drawBackground(nmcustomdraw.hdc);
                            }
                            else {
                                final RECT rect2 = new RECT();
                                OS.SetRect(rect2, nmcustomdraw.left, nmcustomdraw.top, nmcustomdraw.right, nmcustomdraw.bottom);
                                this.drawBackground(nmcustomdraw.hdc, rect2);
                            }
                            return new LRESULT(4);
                        }
                        default: {
                            break Label_0496;
                        }
                    }
                    break;
                }
                case -713: {
                    if (OS.IsWinCE) {
                        break;
                    }
                    final NMTBHOTITEM nmtbhotitem = new NMTBHOTITEM();
                    OS.MoveMemory(nmtbhotitem, n2, NMTBHOTITEM.sizeof);
                    switch (nmtbhotitem.dwFlags) {
                        case 1: {
                            if (this.lastArrowId != -1) {
                                return LRESULT.ONE;
                            }
                            break;
                        }
                        case 2: {
                            final RECT rect3 = new RECT();
                            OS.GetClientRect(this.handle, rect3);
                            final int sendMessage2 = OS.SendMessage(this.handle, 1049, nmtbhotitem.idNew, 0);
                            final RECT rect4 = new RECT();
                            OS.SendMessage(this.handle, 1053, sendMessage2, rect4);
                            if (rect4.right > rect3.right || rect4.bottom > rect3.bottom) {
                                return LRESULT.ONE;
                            }
                            this.lastArrowId = nmtbhotitem.idNew;
                            break;
                        }
                        default: {
                            this.lastArrowId = -1;
                            break;
                        }
                    }
                    if ((nmtbhotitem.dwFlags & 0x20) == 0x0) {
                        this.lastHotId = nmtbhotitem.idNew;
                        break;
                    }
                    break;
                }
            }
        }
        return super.wmNotifyChild(nmhdr, n, n2);
    }
}
