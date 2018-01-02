// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.MENUINFO;
import org.eclipse.swt.internal.win32.MEASUREITEMSTRUCT;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.DRAWITEMSTRUCT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.internal.win32.MENUITEMINFO;
import org.eclipse.swt.internal.win32.TBBUTTONINFO;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.MENUBARINFO;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.ACCEL;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ArmListener;
import org.eclipse.swt.internal.win32.OS;

public class MenuItem extends Item
{
    Menu parent;
    Menu menu;
    int hBitmap;
    int id;
    int accelerator;
    static final int MARGIN_WIDTH;
    static final int MARGIN_HEIGHT;
    
    static {
        MARGIN_WIDTH = (OS.IsWin95 ? 2 : 1);
        MARGIN_HEIGHT = (OS.IsWin95 ? 2 : 1);
    }
    
    public MenuItem(final Menu parent, final int n) {
        super(parent, checkStyle(n));
        (this.parent = parent).createItem(this, parent.getItemCount());
    }
    
    public MenuItem(final Menu parent, final int n, final int n2) {
        super(parent, checkStyle(n));
        (this.parent = parent).createItem(this, n2);
    }
    
    MenuItem(final Menu parent, final Menu menu, final int n, final int n2) {
        super(parent, checkStyle(n));
        this.parent = parent;
        this.menu = menu;
        if (menu != null) {
            menu.cascade = this;
        }
        this.display.addMenuItem(this);
    }
    
    public void addArmListener(final ArmListener armListener) {
        this.checkWidget();
        if (armListener == null) {
            this.error(4);
        }
        this.addListener(30, new TypedListener(armListener));
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.checkWidget();
        if (helpListener == null) {
            this.error(4);
        }
        this.addListener(28, new TypedListener(helpListener));
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
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 8, 32, 16, 2, 64, 0);
    }
    
    void destroyWidget() {
        this.parent.destroyItem(this);
        this.releaseHandle();
    }
    
    boolean fillAccel(final ACCEL accel) {
        final boolean cmd = false;
        accel.fVirt = (byte)(cmd ? 1 : 0);
        accel.key = (short)(cmd ? 1 : 0);
        accel.cmd = (short)(cmd ? 1 : 0);
        if (this.accelerator == 0 || !this.getEnabled()) {
            return false;
        }
        if ((this.accelerator & 0x400000) != 0x0) {
            return false;
        }
        boolean b = true;
        final int n = this.accelerator & 0x100FFFF;
        final int untranslateKey = Display.untranslateKey(n);
        int n2 = 0;
        if (untranslateKey != 0) {
            n2 = untranslateKey;
        }
        else {
            switch (n) {
                case 27: {
                    n2 = 27;
                    break;
                }
                case 127: {
                    n2 = 46;
                    break;
                }
                default: {
                    n2 = Display.wcsToMbcs((char)n);
                    if (n2 == 0) {
                        return false;
                    }
                    if (OS.IsWinCE) {
                        n2 = OS.CharUpper((short)n2);
                        break;
                    }
                    final short n3 = (short)(OS.VkKeyScan((short)n2) & 0xFF);
                    if (n3 == -1) {
                        b = false;
                        break;
                    }
                    n2 = n3;
                    break;
                }
            }
        }
        accel.key = (short)n2;
        accel.cmd = (short)this.id;
        accel.fVirt = (byte)(b ? 1 : 0);
        if ((this.accelerator & 0x10000) != 0x0) {
            accel.fVirt |= 0x10;
        }
        if ((this.accelerator & 0x20000) != 0x0) {
            accel.fVirt |= 0x4;
        }
        if ((this.accelerator & 0x40000) != 0x0) {
            accel.fVirt |= 0x8;
        }
        return true;
    }
    
    void fixMenus(final Decorations decorations) {
        if (this.menu != null) {
            this.menu.fixMenus(decorations);
        }
    }
    
    public int getAccelerator() {
        this.checkWidget();
        return this.accelerator;
    }
    
    Rectangle getBounds() {
        this.checkWidget();
        if (OS.IsWinCE) {
            return new Rectangle(0, 0, 0, 0);
        }
        final int index = this.parent.indexOf(this);
        if (index == -1) {
            return new Rectangle(0, 0, 0, 0);
        }
        if ((this.parent.style & 0x2) != 0x0) {
            final Decorations parent = this.parent.parent;
            if (parent.menuBar != this.parent) {
                return new Rectangle(0, 0, 0, 0);
            }
            final int handle = parent.handle;
            final MENUBARINFO menubarinfo = new MENUBARINFO();
            menubarinfo.cbSize = MENUBARINFO.sizeof;
            if (!OS.GetMenuBarInfo(handle, -3, 1, menubarinfo)) {
                return new Rectangle(0, 0, 0, 0);
            }
            final MENUBARINFO menubarinfo2 = new MENUBARINFO();
            menubarinfo2.cbSize = MENUBARINFO.sizeof;
            if (!OS.GetMenuBarInfo(handle, -3, index + 1, menubarinfo2)) {
                return new Rectangle(0, 0, 0, 0);
            }
            return new Rectangle(menubarinfo2.left - menubarinfo.left, menubarinfo2.top - menubarinfo.top, menubarinfo2.right - menubarinfo2.left, menubarinfo2.bottom - menubarinfo2.top);
        }
        else {
            final int handle2 = this.parent.handle;
            final RECT rect = new RECT();
            if (!OS.GetMenuItemRect(0, handle2, 0, rect)) {
                return new Rectangle(0, 0, 0, 0);
            }
            final RECT rect2 = new RECT();
            if (!OS.GetMenuItemRect(0, handle2, index, rect2)) {
                return new Rectangle(0, 0, 0, 0);
            }
            return new Rectangle(rect2.left - rect.left + 2, rect2.top - rect.top + 2, rect2.right - rect2.left, rect2.bottom - rect2.top);
        }
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        if ((OS.IsPPC || OS.IsSP) && this.parent.hwndCB != 0) {
            final int hwndCB = this.parent.hwndCB;
            final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
            tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
            tbbuttoninfo.dwMask = 4;
            OS.SendMessage(hwndCB, OS.TB_GETBUTTONINFO, this.id, tbbuttoninfo);
            return (tbbuttoninfo.fsState & 0x4) != 0x0;
        }
        if ((this.style & 0x2) != 0x0) {
            return (this.state & 0x8) == 0x0;
        }
        final int handle = this.parent.handle;
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 1;
        boolean b;
        if (OS.IsWinCE) {
            final int index = this.parent.indexOf(this);
            if (index == -1) {
                this.error(31);
            }
            b = OS.GetMenuItemInfo(handle, index, true, menuiteminfo);
        }
        else {
            b = OS.GetMenuItemInfo(handle, this.id, false, menuiteminfo);
        }
        if (!b) {
            this.error(31);
        }
        return (menuiteminfo.fState & 0x3) == 0x0;
    }
    
    public Menu getMenu() {
        this.checkWidget();
        return this.menu;
    }
    
    String getNameText() {
        if ((this.style & 0x2) != 0x0) {
            return "|";
        }
        return super.getNameText();
    }
    
    public Menu getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public boolean getSelection() {
        this.checkWidget();
        if ((this.style & 0x30) == 0x0) {
            return false;
        }
        if ((OS.IsPPC || OS.IsSP) && this.parent.hwndCB != 0) {
            return false;
        }
        final int handle = this.parent.handle;
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 1;
        if (!OS.GetMenuItemInfo(handle, this.id, false, menuiteminfo)) {
            this.error(9);
        }
        return (menuiteminfo.fState & 0x8) != 0x0;
    }
    
    public boolean isEnabled() {
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    void releaseChildren(final boolean b) {
        if (this.menu != null) {
            this.menu.release(false);
            this.menu = null;
        }
        super.releaseChildren(b);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.id = -1;
    }
    
    void releaseParent() {
        super.releaseParent();
        if (this.menu != null) {
            this.menu.dispose();
        }
        this.menu = null;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.hBitmap != 0) {
            OS.DeleteObject(this.hBitmap);
        }
        this.hBitmap = 0;
        if (this.accelerator != 0) {
            this.parent.destroyAccelerators();
        }
        this.accelerator = 0;
        this.display.removeMenuItem(this);
    }
    
    public void removeArmListener(final ArmListener armListener) {
        this.checkWidget();
        if (armListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(30, armListener);
    }
    
    public void removeHelpListener(final HelpListener helpListener) {
        this.checkWidget();
        if (helpListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(28, helpListener);
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
        if (this.menu != null) {
            this.menu.reskin(n);
        }
        super.reskinChildren(n);
    }
    
    void selectRadio() {
        int n;
        MenuItem[] items;
        for (n = 0, items = this.parent.getItems(); n < items.length && items[n] != this; ++n) {}
        for (int n2 = n - 1; n2 >= 0 && items[n2].setRadioSelection(false); --n2) {}
        for (int n3 = n + 1; n3 < items.length && items[n3].setRadioSelection(false); ++n3) {}
        this.setSelection(true);
    }
    
    public void setAccelerator(final int accelerator) {
        this.checkWidget();
        if (this.accelerator == accelerator) {
            return;
        }
        this.accelerator = accelerator;
        this.parent.destroyAccelerators();
    }
    
    public void setEnabled(final boolean b) {
        this.checkWidget();
        if ((OS.IsPPC || OS.IsSP) && this.parent.hwndCB != 0) {
            final int hwndCB = this.parent.hwndCB;
            final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
            tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
            tbbuttoninfo.dwMask = 4;
            OS.SendMessage(hwndCB, OS.TB_GETBUTTONINFO, this.id, tbbuttoninfo);
            final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
            tbbuttoninfo2.fsState &= 0xFFFFFFFB;
            if (b) {
                final TBBUTTONINFO tbbuttoninfo3 = tbbuttoninfo;
                tbbuttoninfo3.fsState |= 0x4;
            }
            OS.SendMessage(hwndCB, OS.TB_SETBUTTONINFO, this.id, tbbuttoninfo);
        }
        else {
            if ((this.style & 0x2) != 0x0) {
                if (b) {
                    this.state &= 0xFFFFFFF7;
                }
                else {
                    this.state |= 0x8;
                }
            }
            final int handle = this.parent.handle;
            if (OS.IsWinCE) {
                final int index = this.parent.indexOf(this);
                if (index == -1) {
                    return;
                }
                OS.EnableMenuItem(handle, index, 0x400 | (b ? 0 : 1));
            }
            else {
                final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
                menuiteminfo.cbSize = MENUITEMINFO.sizeof;
                menuiteminfo.fMask = 1;
                if (!OS.GetMenuItemInfo(handle, this.id, false, menuiteminfo)) {
                    this.error(30);
                }
                final int n = 3;
                if (b) {
                    if ((menuiteminfo.fState & n) == 0x0) {
                        return;
                    }
                    final MENUITEMINFO menuiteminfo2 = menuiteminfo;
                    menuiteminfo2.fState &= ~n;
                }
                else {
                    if ((menuiteminfo.fState & n) == n) {
                        return;
                    }
                    final MENUITEMINFO menuiteminfo3 = menuiteminfo;
                    menuiteminfo3.fState |= n;
                }
                boolean setMenuItemInfo = OS.SetMenuItemInfo(handle, this.id, false, menuiteminfo);
                if (!setMenuItemInfo) {
                    if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                        setMenuItemInfo = (this.id == OS.GetMenuDefaultItem(handle, 0, 1));
                    }
                    if (!setMenuItemInfo) {
                        this.error(30);
                    }
                }
            }
        }
        this.parent.destroyAccelerators();
        this.parent.redraw();
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        super.setImage(image);
        if (OS.IsWinCE) {
            if ((OS.IsPPC || OS.IsSP) && this.parent.hwndCB != 0) {
                final int hwndCB = this.parent.hwndCB;
                final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
                tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
                tbbuttoninfo.dwMask = 1;
                tbbuttoninfo.iImage = this.parent.imageIndex(image);
                OS.SendMessage(hwndCB, OS.TB_SETBUTTONINFO, this.id, tbbuttoninfo);
            }
            return;
        }
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return;
        }
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 128;
        if (this.parent.foreground != -1) {
            menuiteminfo.hbmpItem = -1;
        }
        else if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.IsAppThemed()) {
            if (this.hBitmap != 0) {
                OS.DeleteObject(this.hBitmap);
            }
            final MENUITEMINFO menuiteminfo2 = menuiteminfo;
            final int n = (image != null) ? Display.create32bitDIB(image) : 0;
            this.hBitmap = n;
            menuiteminfo2.hbmpItem = n;
        }
        else {
            menuiteminfo.hbmpItem = ((image != null) ? -1 : 0);
        }
        OS.SetMenuItemInfo(this.parent.handle, this.id, false, menuiteminfo);
        this.parent.redraw();
    }
    
    public void setMenu(final Menu menu) {
        this.checkWidget();
        if ((this.style & 0x40) == 0x0) {
            this.error(27);
        }
        if (menu != null) {
            if (menu.isDisposed()) {
                this.error(5);
            }
            if ((menu.style & 0x4) == 0x0) {
                this.error(21);
            }
            if (menu.parent != this.parent.parent) {
                this.error(32);
            }
        }
        this.setMenu(menu, false);
    }
    
    void setMenu(final Menu menu, final boolean b) {
        final Menu menu2 = this.menu;
        if (menu2 == menu) {
            return;
        }
        if (menu2 != null) {
            menu2.cascade = null;
        }
        this.menu = menu;
        if ((OS.IsPPC || OS.IsSP) && this.parent.hwndCB != 0) {
            if (OS.IsPPC) {
                OS.SendMessage(this.parent.hwndCB, 1424, this.id, (menu == null) ? 0 : menu.handle);
            }
            if (OS.IsSP) {
                this.error(29);
            }
        }
        else {
            final int handle = this.parent.handle;
            final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
            menuiteminfo.cbSize = MENUITEMINFO.sizeof;
            menuiteminfo.fMask = 32;
            int n;
            for (n = 0; OS.GetMenuItemInfo(handle, n, true, menuiteminfo) && menuiteminfo.dwItemData != this.id; ++n) {}
            if (menuiteminfo.dwItemData != this.id) {
                return;
            }
            final int cch = 128;
            final int getProcessHeap = OS.GetProcessHeap();
            final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, cch * TCHAR.sizeof);
            menuiteminfo.fMask = 35;
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
                final MENUITEMINFO menuiteminfo2 = menuiteminfo;
                menuiteminfo2.fMask |= 0xC0;
            }
            else {
                final MENUITEMINFO menuiteminfo3 = menuiteminfo;
                menuiteminfo3.fMask |= 0x10;
            }
            menuiteminfo.dwTypeData = heapAlloc;
            menuiteminfo.cch = cch;
            OS.GetMenuItemInfo(handle, n, true, menuiteminfo);
            if (menu != null) {
                menu.cascade = this;
                final MENUITEMINFO menuiteminfo4 = menuiteminfo;
                menuiteminfo4.fMask |= 0x4;
                menuiteminfo.hSubMenu = menu.handle;
            }
            boolean b2;
            if (OS.IsWinCE) {
                OS.RemoveMenu(handle, n, 1024);
                int n2 = this.id;
                int n3 = 1024;
                if (menu != null) {
                    n3 |= 0x10;
                    n2 = menu.handle;
                }
                b2 = OS.InsertMenu(handle, n, n3, n2, new TCHAR(0, " ", true));
                if (b2) {
                    menuiteminfo.fMask = 48;
                    b2 = OS.SetMenuItemInfo(handle, n, true, menuiteminfo);
                    if ((menuiteminfo.fState & 0x3) != 0x0) {
                        OS.EnableMenuItem(handle, n, 1025);
                    }
                    if ((menuiteminfo.fState & 0x8) != 0x0) {
                        OS.CheckMenuItem(handle, n, 1032);
                    }
                }
            }
            else if (b || menu2 == null) {
                b2 = OS.SetMenuItemInfo(handle, n, true, menuiteminfo);
            }
            else {
                OS.RemoveMenu(handle, n, 1024);
                b2 = OS.InsertMenuItem(handle, n, true, menuiteminfo);
            }
            if (heapAlloc != 0) {
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
            }
            if (!b2) {
                this.error(29);
            }
        }
        this.parent.destroyAccelerators();
    }
    
    boolean setRadioSelection(final boolean selection) {
        if ((this.style & 0x10) == 0x0) {
            return false;
        }
        if (this.getSelection() != selection) {
            this.setSelection(selection);
            this.sendSelectionEvent(13);
        }
        return true;
    }
    
    void setOrientation(final int n) {
        final int handle = this.parent.handle;
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 256;
        menuiteminfo.fType = this.widgetStyle();
        OS.SetMenuItemInfo(handle, this.id, false, menuiteminfo);
        if (this.menu != null) {
            this.menu._setOrientation(n);
        }
    }
    
    public void setSelection(final boolean b) {
        this.checkWidget();
        if ((this.style & 0x30) == 0x0) {
            return;
        }
        if ((OS.IsPPC || OS.IsSP) && this.parent.hwndCB != 0) {
            return;
        }
        final int handle = this.parent.handle;
        if (OS.IsWinCE) {
            final int index = this.parent.indexOf(this);
            if (index == -1) {
                return;
            }
            OS.CheckMenuItem(handle, index, 0x400 | (b ? 8 : 0));
        }
        else {
            final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
            menuiteminfo.cbSize = MENUITEMINFO.sizeof;
            menuiteminfo.fMask = 1;
            if (!OS.GetMenuItemInfo(handle, this.id, false, menuiteminfo)) {
                this.error(28);
            }
            final MENUITEMINFO menuiteminfo2 = menuiteminfo;
            menuiteminfo2.fState &= 0xFFFFFFF7;
            if (b) {
                final MENUITEMINFO menuiteminfo3 = menuiteminfo;
                menuiteminfo3.fState |= 0x8;
            }
            boolean setMenuItemInfo = OS.SetMenuItemInfo(handle, this.id, false, menuiteminfo);
            if (!setMenuItemInfo) {
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    setMenuItemInfo = (this.id == OS.GetMenuDefaultItem(handle, 0, 1));
                }
                if (!setMenuItemInfo) {
                    this.error(28);
                }
            }
        }
        this.parent.redraw();
    }
    
    public void setText(String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (this.text.equals(text)) {
            return;
        }
        super.setText(text);
        final int getProcessHeap = OS.GetProcessHeap();
        int n3;
        boolean setMenuItemInfo;
        if ((OS.IsPPC || OS.IsSP) && this.parent.hwndCB != 0) {
            if (text.indexOf(38) != -1) {
                final int length = text.length();
                final char[] array = new char[length];
                text.getChars(0, length, array, 0);
                int n = 0;
                int i;
                for (i = 0; i < length; ++i) {
                    if (array[i] != '&') {
                        array[n++] = array[i];
                    }
                }
                if (n < i) {
                    text = new String(array, 0, n);
                }
            }
            final TCHAR tchar = new TCHAR(0, text, true);
            final int n2 = tchar.length() * TCHAR.sizeof;
            n3 = OS.HeapAlloc(getProcessHeap, 8, n2);
            OS.MoveMemory(n3, tchar, n2);
            final int hwndCB = this.parent.hwndCB;
            final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
            tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
            tbbuttoninfo.dwMask = 2;
            tbbuttoninfo.pszText = n3;
            setMenuItemInfo = (OS.SendMessage(hwndCB, OS.TB_SETBUTTONINFO, this.id, tbbuttoninfo) != 0);
        }
        else {
            final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
            menuiteminfo.cbSize = MENUITEMINFO.sizeof;
            final int handle = this.parent.handle;
            final TCHAR tchar2 = new TCHAR(0, text, true);
            final int n4 = tchar2.length() * TCHAR.sizeof;
            n3 = OS.HeapAlloc(getProcessHeap, 8, n4);
            OS.MoveMemory(n3, tchar2, n4);
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
                menuiteminfo.fMask = 64;
            }
            else {
                menuiteminfo.fMask = 16;
                menuiteminfo.fType = this.widgetStyle();
            }
            menuiteminfo.dwTypeData = n3;
            setMenuItemInfo = OS.SetMenuItemInfo(handle, this.id, false, menuiteminfo);
        }
        if (n3 != 0) {
            OS.HeapFree(getProcessHeap, 0, n3);
        }
        if (!setMenuItemInfo) {
            this.error(13);
        }
        this.parent.redraw();
    }
    
    int widgetStyle() {
        int n = 0;
        if ((this.parent.parent.style & 0x8000000) != 0x0) {
            if ((this.parent.style & 0x2000000) != 0x0) {
                n |= 0x6000;
            }
        }
        else if ((this.parent.style & 0x4000000) != 0x0) {
            n |= 0x6000;
        }
        if ((this.style & 0x2) != 0x0) {
            return n | 0x800;
        }
        if ((this.style & 0x10) != 0x0) {
            return n | 0x200;
        }
        return n;
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        if ((this.style & 0x20) != 0x0) {
            this.setSelection(!this.getSelection());
        }
        else if ((this.style & 0x10) != 0x0) {
            if ((this.parent.getStyle() & 0x400000) != 0x0) {
                this.setSelection(!this.getSelection());
            }
            else {
                this.selectRadio();
            }
        }
        this.sendSelectionEvent(13);
        return null;
    }
    
    LRESULT wmDrawChild(final int n, final int n2) {
        final DRAWITEMSTRUCT drawitemstruct = new DRAWITEMSTRUCT();
        OS.MoveMemory(drawitemstruct, n2, DRAWITEMSTRUCT.sizeof);
        if (this.image != null) {
            final GCData gcData = new GCData();
            gcData.device = this.display;
            final GC win32_new = GC.win32_new(drawitemstruct.hDC, gcData);
            final int n3 = ((this.parent.style & 0x2) != 0x0) ? (MenuItem.MARGIN_WIDTH * 2) : drawitemstruct.left;
            final Image image = this.getEnabled() ? this.image : new Image(this.display, this.image, 1);
            win32_new.drawImage(image, n3, drawitemstruct.top + MenuItem.MARGIN_HEIGHT);
            if (this.image != image) {
                image.dispose();
            }
            win32_new.dispose();
        }
        if (this.parent.foreground != -1) {
            OS.SetTextColor(drawitemstruct.hDC, this.parent.foreground);
        }
        return null;
    }
    
    LRESULT wmMeasureChild(final int n, final int n2) {
        final MEASUREITEMSTRUCT measureitemstruct = new MEASUREITEMSTRUCT();
        OS.MoveMemory(measureitemstruct, n2, MEASUREITEMSTRUCT.sizeof);
        int n3 = 0;
        int height = 0;
        if (this.image != null) {
            final Rectangle bounds = this.image.getBounds();
            n3 = bounds.width;
            height = bounds.height;
        }
        else {
            final MENUINFO menuinfo = new MENUINFO();
            menuinfo.cbSize = MENUINFO.sizeof;
            menuinfo.fMask = 16;
            OS.GetMenuInfo(this.parent.handle, menuinfo);
            if ((menuinfo.dwStyle & 0x4000000) == 0x0) {
                final MenuItem[] items = this.parent.getItems();
                for (int i = 0; i < items.length; ++i) {
                    final MenuItem menuItem = items[i];
                    if (menuItem.image != null) {
                        n3 = Math.max(n3, menuItem.image.getBounds().width);
                    }
                }
            }
        }
        if (n3 != 0 || height != 0) {
            measureitemstruct.itemWidth = n3 + MenuItem.MARGIN_WIDTH * 2;
            measureitemstruct.itemHeight = height + MenuItem.MARGIN_HEIGHT * 2;
            OS.MoveMemory(n2, measureitemstruct, MEASUREITEMSTRUCT.sizeof);
        }
        return null;
    }
}
