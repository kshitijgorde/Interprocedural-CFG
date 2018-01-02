// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.MENUINFO;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.MENUBARINFO;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.internal.win32.MENUITEMINFO;
import org.eclipse.swt.internal.win32.TBBUTTON;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.TBBUTTONINFO;
import org.eclipse.swt.internal.win32.SHMENUBARINFO;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ImageList;
import org.eclipse.swt.graphics.Image;

public class Menu extends Widget
{
    public int handle;
    int x;
    int y;
    int hBrush;
    int hwndCB;
    int id0;
    int id1;
    int foreground;
    int background;
    Image backgroundImage;
    boolean hasLocation;
    MenuItem cascade;
    Decorations parent;
    ImageList imageList;
    static final int ID_PPC = 100;
    static final int ID_SPMM = 102;
    static final int ID_SPBM = 103;
    static final int ID_SPMB = 104;
    static final int ID_SPBB = 105;
    static final int ID_SPSOFTKEY0 = 106;
    static final int ID_SPSOFTKEY1 = 107;
    
    public Menu(final Control control) {
        this(checkNull(control).menuShell(), 8);
    }
    
    public Menu(final Decorations decorations, final int n) {
        this(decorations, checkStyle(n), 0);
    }
    
    public Menu(final Menu menu) {
        this(checkNull(menu).parent, 4);
    }
    
    public Menu(final MenuItem menuItem) {
        this(checkNull(menuItem).parent);
    }
    
    Menu(final Decorations parent, final int n, final int handle) {
        super(parent, checkStyle(n));
        this.foreground = -1;
        this.background = -1;
        this.parent = parent;
        this.handle = handle;
        this.checkOrientation(parent);
        this.createWidget();
    }
    
    void _setVisible(final boolean b) {
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        final int handle = this.parent.handle;
        if (b) {
            int n = 0;
            if (OS.GetKeyState(1) >= 0) {
                n |= 0x2;
            }
            if ((this.style & 0x4000000) != 0x0) {
                n |= 0x8;
            }
            if ((this.parent.style & 0x8000000) != 0x0) {
                n &= 0xFFFFFFF7;
                if ((this.style & 0x2000000) != 0x0) {
                    n |= 0x8;
                }
            }
            int n2 = this.x;
            int n3 = this.y;
            if (!this.hasLocation) {
                final int getMessagePos = OS.GetMessagePos();
                n2 = OS.GET_X_LPARAM(getMessagePos);
                n3 = OS.GET_Y_LPARAM(getMessagePos);
            }
            if (!OS.TrackPopupMenu(this.handle, n, n2, n3, 0, handle, null) && this.GetMenuItemCount(this.handle) == 0) {
                OS.SendMessage(handle, 287, OS.MAKEWPARAM(0, 65535), 0);
            }
        }
        else {
            OS.SendMessage(handle, 31, 0, 0);
        }
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.checkWidget();
        if (helpListener == null) {
            this.error(4);
        }
        this.addListener(28, new TypedListener(helpListener));
    }
    
    public void addMenuListener(final MenuListener menuListener) {
        this.checkWidget();
        if (menuListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(menuListener);
        this.addListener(23, typedListener);
        this.addListener(22, typedListener);
    }
    
    static Control checkNull(final Control control) {
        if (control == null) {
            SWT.error(4);
        }
        return control;
    }
    
    static Menu checkNull(final Menu menu) {
        if (menu == null) {
            SWT.error(4);
        }
        return menu;
    }
    
    static MenuItem checkNull(final MenuItem menuItem) {
        if (menuItem == null) {
            SWT.error(4);
        }
        return menuItem;
    }
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 8, 2, 4, 0, 0, 0);
    }
    
    void createHandle() {
        if (this.handle != 0) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            if (OS.IsPPC) {
                final int handle = this.parent.handle;
                final SHMENUBARINFO shmenubarinfo = new SHMENUBARINFO();
                shmenubarinfo.cbSize = SHMENUBARINFO.sizeof;
                shmenubarinfo.hwndParent = handle;
                shmenubarinfo.dwFlags = 2;
                shmenubarinfo.nToolBarId = 100;
                shmenubarinfo.hInstRes = OS.GetLibraryHandle();
                final boolean shCreateMenuBar = OS.SHCreateMenuBar(shmenubarinfo);
                this.hwndCB = shmenubarinfo.hwndMB;
                if (!shCreateMenuBar) {
                    this.error(2);
                }
                OS.SendMessage(this.hwndCB, 1046, 0, 0);
                return;
            }
            if (OS.IsSP) {
                int nToolBarId;
                if ((this.style & 0x80000) != 0x0) {
                    nToolBarId = (((this.style & 0x100000) != 0x0) ? 105 : 103);
                }
                else {
                    nToolBarId = (((this.style & 0x100000) != 0x0) ? 104 : 102);
                }
                final SHMENUBARINFO shmenubarinfo2 = new SHMENUBARINFO();
                shmenubarinfo2.cbSize = SHMENUBARINFO.sizeof;
                shmenubarinfo2.hwndParent = this.parent.handle;
                shmenubarinfo2.dwFlags = 2;
                shmenubarinfo2.nToolBarId = nToolBarId;
                shmenubarinfo2.hInstRes = OS.GetLibraryHandle();
                if (!OS.SHCreateMenuBar(shmenubarinfo2)) {
                    this.error(2);
                }
                OS.ShowWindow(this.hwndCB = shmenubarinfo2.hwndMB, 0);
                final TBBUTTONINFO tbbuttoninfo = new TBBUTTONINFO();
                tbbuttoninfo.cbSize = TBBUTTONINFO.sizeof;
                tbbuttoninfo.dwMask = 32;
                MenuItem menuItem;
                if (nToolBarId == 102 || nToolBarId == 104) {
                    final int sendMessage = OS.SendMessage(this.hwndCB, 1425, 0, 106);
                    OS.RemoveMenu(sendMessage, 0, 1024);
                    menuItem = new MenuItem(this, new Menu(this.parent, 4, sendMessage), 64, 0);
                }
                else {
                    menuItem = new MenuItem(this, null, 8, 0);
                }
                final TBBUTTONINFO tbbuttoninfo2 = tbbuttoninfo;
                final int id = menuItem.id;
                this.id0 = id;
                tbbuttoninfo2.idCommand = id;
                OS.SendMessage(this.hwndCB, OS.TB_SETBUTTONINFO, 106, tbbuttoninfo);
                MenuItem menuItem2;
                if (nToolBarId == 102 || nToolBarId == 103) {
                    final int sendMessage2 = OS.SendMessage(this.hwndCB, 1425, 0, 107);
                    OS.RemoveMenu(sendMessage2, 0, 1024);
                    menuItem2 = new MenuItem(this, new Menu(this.parent, 4, sendMessage2), 64, 1);
                }
                else {
                    menuItem2 = new MenuItem(this, null, 8, 1);
                }
                final TBBUTTONINFO tbbuttoninfo3 = tbbuttoninfo;
                final int id2 = menuItem2.id;
                this.id1 = id2;
                tbbuttoninfo3.idCommand = id2;
                OS.SendMessage(this.hwndCB, OS.TB_SETBUTTONINFO, 107, tbbuttoninfo);
                final int n = 3;
                OS.SendMessage(this.hwndCB, 1427, 27, OS.MAKELPARAM(n, n));
                return;
            }
            this.handle = OS.CreateMenu();
            if (this.handle == 0) {
                this.error(2);
            }
            if (OS.IsHPC) {
                this.hwndCB = OS.CommandBar_Create(OS.GetModuleHandle(null), this.parent.handle, 1);
                if (this.hwndCB == 0) {
                    this.error(2);
                }
                OS.CommandBar_Show(this.hwndCB, false);
                OS.CommandBar_InsertMenubarEx(this.hwndCB, 0, this.handle, 0);
                if ((this.parent.style & 0x40) != 0x0 && (this.parent.style & 0x20) == 0x0) {
                    OS.CommandBar_AddAdornments(this.hwndCB, 0, 0);
                }
            }
        }
        else {
            this.handle = OS.CreatePopupMenu();
            if (this.handle == 0) {
                this.error(2);
            }
        }
    }
    
    void createItem(final MenuItem menuItem, final int n) {
        final int getMenuItemCount = this.GetMenuItemCount(this.handle);
        if (n < 0 || n > getMenuItemCount) {
            this.error(6);
        }
        this.display.addMenuItem(menuItem);
        boolean b;
        if ((OS.IsPPC || OS.IsSP) && this.hwndCB != 0) {
            if (OS.IsSP) {
                return;
            }
            final TBBUTTON tbbutton = new TBBUTTON();
            tbbutton.idCommand = menuItem.id;
            tbbutton.fsStyle = 16;
            if ((menuItem.style & 0x40) != 0x0) {
                final TBBUTTON tbbutton2 = tbbutton;
                tbbutton2.fsStyle |= (byte)136;
            }
            if ((menuItem.style & 0x2) != 0x0) {
                tbbutton.fsStyle = 1;
            }
            tbbutton.fsState = 4;
            tbbutton.iBitmap = -2;
            b = (OS.SendMessage(this.hwndCB, OS.TB_INSERTBUTTON, n, tbbutton) != 0);
        }
        else if (OS.IsWinCE) {
            int n2 = 1024;
            TCHAR tchar = null;
            if ((menuItem.style & 0x2) != 0x0) {
                n2 |= 0x800;
            }
            else {
                tchar = new TCHAR(0, " ", true);
            }
            b = OS.InsertMenu(this.handle, n, n2, menuItem.id, tchar);
            if (b) {
                final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
                menuiteminfo.cbSize = MENUITEMINFO.sizeof;
                menuiteminfo.fMask = 32;
                menuiteminfo.dwItemData = menuItem.id;
                b = OS.SetMenuItemInfo(this.handle, n, true, menuiteminfo);
            }
        }
        else {
            final int getProcessHeap = OS.GetProcessHeap();
            final TCHAR tchar2 = new TCHAR(0, " ", true);
            final int n3 = tchar2.length() * TCHAR.sizeof;
            final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n3);
            OS.MoveMemory(heapAlloc, tchar2, n3);
            final MENUITEMINFO menuiteminfo2 = new MENUITEMINFO();
            menuiteminfo2.cbSize = MENUITEMINFO.sizeof;
            menuiteminfo2.fMask = 50;
            menuiteminfo2.wID = menuItem.id;
            menuiteminfo2.dwItemData = menuItem.id;
            menuiteminfo2.fType = menuItem.widgetStyle();
            menuiteminfo2.dwTypeData = heapAlloc;
            b = OS.InsertMenuItem(this.handle, n, true, menuiteminfo2);
            if (heapAlloc != 0) {
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
            }
        }
        if (!b) {
            this.display.removeMenuItem(menuItem);
            this.error(14);
        }
        this.redraw();
    }
    
    void createWidget() {
        this.createHandle();
        this.parent.addMenu(this);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_MENU);
    }
    
    int defaultForeground() {
        return OS.GetSysColor(OS.COLOR_MENUTEXT);
    }
    
    void destroyAccelerators() {
        this.parent.destroyAccelerators();
    }
    
    void destroyItem(final MenuItem menuItem) {
        if (OS.IsWinCE) {
            if ((OS.IsPPC || OS.IsSP) && this.hwndCB != 0) {
                if (OS.IsSP) {
                    this.redraw();
                    return;
                }
                if (OS.SendMessage(this.hwndCB, 1046, OS.SendMessage(this.hwndCB, 1049, menuItem.id, 0), 0) == 0) {
                    this.error(15);
                }
                if (OS.SendMessage(this.hwndCB, 1048, 0, 0) == 0 && this.imageList != null) {
                    OS.SendMessage(this.handle, 1072, 0, 0);
                    this.display.releaseImageList(this.imageList);
                    this.imageList = null;
                }
            }
            else {
                int n = 0;
                final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
                menuiteminfo.cbSize = MENUITEMINFO.sizeof;
                menuiteminfo.fMask = 32;
                while (OS.GetMenuItemInfo(this.handle, n, true, menuiteminfo) && menuiteminfo.dwItemData != menuItem.id) {
                    ++n;
                }
                if (menuiteminfo.dwItemData != menuItem.id) {
                    this.error(15);
                }
                if (!OS.DeleteMenu(this.handle, n, 1024)) {
                    this.error(15);
                }
            }
        }
        else if (!OS.DeleteMenu(this.handle, menuItem.id, 0)) {
            this.error(15);
        }
        this.redraw();
    }
    
    void destroyWidget() {
        final MenuItem cascade = this.cascade;
        final int handle = this.handle;
        final int hwndCB = this.hwndCB;
        this.releaseHandle();
        if (OS.IsWinCE && hwndCB != 0) {
            OS.CommandBar_Destroy(hwndCB);
        }
        else if (cascade != null) {
            if (!OS.IsSP) {
                cascade.setMenu(null, true);
            }
        }
        else if (handle != 0) {
            OS.DestroyMenu(handle);
        }
    }
    
    void fixMenus(final Decorations parent) {
        final MenuItem[] items = this.getItems();
        for (int i = 0; i < items.length; ++i) {
            items[i].fixMenus(parent);
        }
        this.parent.removeMenu(this);
        parent.addMenu(this);
        this.parent = parent;
    }
    
    Color getBackground() {
        this.checkWidget();
        return Color.win32_new(this.display, (this.background != -1) ? this.background : this.defaultBackground());
    }
    
    Image getBackgroundImage() {
        this.checkWidget();
        return this.backgroundImage;
    }
    
    Rectangle getBounds() {
        this.checkWidget();
        if (OS.IsWinCE) {
            return new Rectangle(0, 0, 0, 0);
        }
        if ((this.style & 0x2) != 0x0) {
            if (this.parent.menuBar != this) {
                return new Rectangle(0, 0, 0, 0);
            }
            final int handle = this.parent.handle;
            final MENUBARINFO menubarinfo = new MENUBARINFO();
            menubarinfo.cbSize = MENUBARINFO.sizeof;
            if (OS.GetMenuBarInfo(handle, -3, 0, menubarinfo)) {
                return new Rectangle(menubarinfo.left, menubarinfo.top, menubarinfo.right - menubarinfo.left, menubarinfo.bottom - menubarinfo.top);
            }
        }
        else {
            final int getMenuItemCount = this.GetMenuItemCount(this.handle);
            if (getMenuItemCount != 0) {
                final RECT rect = new RECT();
                if (OS.GetMenuItemRect(0, this.handle, 0, rect)) {
                    final RECT rect2 = new RECT();
                    if (OS.GetMenuItemRect(0, this.handle, getMenuItemCount - 1, rect2)) {
                        return new Rectangle(rect.left - 2, rect.top - 2, rect2.right - rect2.left + 4, rect2.bottom - rect.top + 4);
                    }
                }
            }
        }
        return new Rectangle(0, 0, 0, 0);
    }
    
    public MenuItem getDefaultItem() {
        this.checkWidget();
        if (OS.IsWinCE) {
            return null;
        }
        final int getMenuDefaultItem = OS.GetMenuDefaultItem(this.handle, 0, 1);
        if (getMenuDefaultItem == -1) {
            return null;
        }
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 2;
        if (OS.GetMenuItemInfo(this.handle, getMenuDefaultItem, false, menuiteminfo)) {
            return this.display.getMenuItem(menuiteminfo.wID);
        }
        return null;
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return (this.state & 0x8) == 0x0;
    }
    
    Color getForeground() {
        this.checkWidget();
        return Color.win32_new(this.display, (this.foreground != -1) ? this.foreground : this.defaultForeground());
    }
    
    public MenuItem getItem(final int n) {
        this.checkWidget();
        int n2 = 0;
        if ((OS.IsPPC || OS.IsSP) && this.hwndCB != 0) {
            if (OS.IsPPC) {
                final TBBUTTON tbbutton = new TBBUTTON();
                if (OS.SendMessage(this.hwndCB, 1047, n, tbbutton) == 0) {
                    this.error(8);
                }
                n2 = tbbutton.idCommand;
            }
            if (OS.IsSP) {
                if (n < 0 || n > 1) {
                    this.error(8);
                }
                n2 = ((n == 0) ? this.id0 : this.id1);
            }
        }
        else {
            final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
            menuiteminfo.cbSize = MENUITEMINFO.sizeof;
            menuiteminfo.fMask = 32;
            if (!OS.GetMenuItemInfo(this.handle, n, true, menuiteminfo)) {
                this.error(6);
            }
            n2 = menuiteminfo.dwItemData;
        }
        return this.display.getMenuItem(n2);
    }
    
    public int getItemCount() {
        this.checkWidget();
        return this.GetMenuItemCount(this.handle);
    }
    
    public MenuItem[] getItems() {
        this.checkWidget();
        if ((OS.IsPPC || OS.IsSP) && this.hwndCB != 0) {
            if (OS.IsSP) {
                return new MenuItem[] { this.display.getMenuItem(this.id0), this.display.getMenuItem(this.id1) };
            }
            final int sendMessage = OS.SendMessage(this.hwndCB, 1048, 0, 0);
            final TBBUTTON tbbutton = new TBBUTTON();
            final MenuItem[] array = new MenuItem[sendMessage];
            for (int i = 0; i < sendMessage; ++i) {
                OS.SendMessage(this.hwndCB, 1047, i, tbbutton);
                array[i] = this.display.getMenuItem(tbbutton.idCommand);
            }
            return array;
        }
        else {
            int n = 0;
            int n2 = 0;
            MenuItem[] array2 = new MenuItem[OS.IsWinCE ? 4 : OS.GetMenuItemCount(this.handle)];
            final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
            menuiteminfo.cbSize = MENUITEMINFO.sizeof;
            menuiteminfo.fMask = 32;
            while (OS.GetMenuItemInfo(this.handle, n, true, menuiteminfo)) {
                if (n2 == array2.length) {
                    final MenuItem[] array3 = new MenuItem[n2 + 4];
                    System.arraycopy(array2, 0, array3, 0, n2);
                    array2 = array3;
                }
                final MenuItem menuItem = this.display.getMenuItem(menuiteminfo.dwItemData);
                if (menuItem != null) {
                    array2[n2++] = menuItem;
                }
                ++n;
            }
            if (n2 == array2.length) {
                return array2;
            }
            final MenuItem[] array4 = new MenuItem[n2];
            System.arraycopy(array2, 0, array4, 0, n2);
            return array4;
        }
    }
    
    int GetMenuItemCount(final int n) {
        if (!OS.IsWinCE) {
            return OS.GetMenuItemCount(n);
        }
        if ((OS.IsPPC || OS.IsSP) && this.hwndCB != 0) {
            return OS.IsSP ? 2 : OS.SendMessage(this.hwndCB, 1048, 0, 0);
        }
        int n2 = 0;
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        while (OS.GetMenuItemInfo(n, n2, true, menuiteminfo)) {
            ++n2;
        }
        return n2;
    }
    
    String getNameText() {
        String s = "";
        final MenuItem[] items = this.getItems();
        final int length = items.length;
        if (length > 0) {
            for (int i = 0; i < length - 1; ++i) {
                s = String.valueOf(s) + items[i].getNameText() + ", ";
            }
            s = String.valueOf(s) + items[length - 1].getNameText();
        }
        return s;
    }
    
    public int getOrientation() {
        this.checkWidget();
        return this.style & 0x6000000;
    }
    
    public Decorations getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public MenuItem getParentItem() {
        this.checkWidget();
        return this.cascade;
    }
    
    public Menu getParentMenu() {
        this.checkWidget();
        if (this.cascade != null) {
            return this.cascade.parent;
        }
        return null;
    }
    
    public Shell getShell() {
        this.checkWidget();
        return this.parent.getShell();
    }
    
    public boolean getVisible() {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return this == this.parent.menuShell().menuBar;
        }
        if ((this.style & 0x8) != 0x0) {
            final Menu[] popups = this.display.popups;
            if (popups == null) {
                return false;
            }
            for (int i = 0; i < popups.length; ++i) {
                if (popups[i] == this) {
                    return true;
                }
            }
        }
        Menu menu;
        for (menu = this.getShell().activeMenu; menu != null && menu != this; menu = menu.getParentMenu()) {}
        return this == menu;
    }
    
    int imageIndex(final Image image) {
        if (this.hwndCB == 0 || image == null) {
            return -2;
        }
        if (this.imageList == null) {
            final Rectangle bounds = image.getBounds();
            this.imageList = this.display.getImageList(0, bounds.width, bounds.height);
            final int add = this.imageList.add(image);
            OS.SendMessage(this.hwndCB, 1072, 0, this.imageList.getHandle());
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
    
    public int indexOf(final MenuItem menuItem) {
        this.checkWidget();
        if (menuItem == null) {
            this.error(4);
        }
        if (menuItem.isDisposed()) {
            this.error(5);
        }
        if (menuItem.parent != this) {
            return -1;
        }
        if ((OS.IsPPC || OS.IsSP) && this.hwndCB != 0) {
            if (OS.IsPPC) {
                return OS.SendMessage(this.hwndCB, 1049, menuItem.id, 0);
            }
            if (OS.IsSP) {
                if (menuItem.id == this.id0) {
                    return 0;
                }
                if (menuItem.id == this.id1) {
                    return 1;
                }
                return -1;
            }
        }
        int n = 0;
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 32;
        while (OS.GetMenuItemInfo(this.handle, n, true, menuiteminfo)) {
            if (menuiteminfo.dwItemData == menuItem.id) {
                return n;
            }
            ++n;
        }
        return -1;
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        final Menu parentMenu = this.getParentMenu();
        if (parentMenu == null) {
            return this.getEnabled() && this.parent.isEnabled();
        }
        return this.getEnabled() && parentMenu.isEnabled();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.getVisible();
    }
    
    void redraw() {
        if (!this.isVisible()) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            this.display.addBar(this);
        }
        else {
            this.update();
        }
    }
    
    void releaseHandle() {
        super.releaseHandle();
        final boolean b = false;
        this.hwndCB = (b ? 1 : 0);
        this.handle = (b ? 1 : 0);
        this.cascade = null;
    }
    
    void releaseChildren(final boolean b) {
        final MenuItem[] items = this.getItems();
        for (int i = 0; i < items.length; ++i) {
            final MenuItem menuItem = items[i];
            if (menuItem != null && !menuItem.isDisposed()) {
                if (OS.IsPPC && this.hwndCB != 0) {
                    menuItem.dispose();
                }
                else {
                    menuItem.release(false);
                }
            }
        }
        super.releaseChildren(b);
    }
    
    void releaseParent() {
        super.releaseParent();
        if ((this.style & 0x2) != 0x0) {
            this.display.removeBar(this);
            if (this == this.parent.menuBar) {
                this.parent.setMenuBar(null);
            }
        }
        else if ((this.style & 0x8) != 0x0) {
            this.display.removePopup(this);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.backgroundImage = null;
        if (this.hBrush == 0) {
            OS.DeleteObject(this.hBrush);
        }
        this.hBrush = 0;
        if (OS.IsPPC && this.hwndCB != 0 && this.imageList != null) {
            OS.SendMessage(this.hwndCB, 1072, 0, 0);
            this.display.releaseToolImageList(this.imageList);
            this.imageList = null;
        }
        if (this.parent != null) {
            this.parent.removeMenu(this);
        }
        this.parent = null;
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
    
    public void removeMenuListener(final MenuListener menuListener) {
        this.checkWidget();
        if (menuListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(23, menuListener);
        this.eventTable.unhook(22, menuListener);
    }
    
    void reskinChildren(final int n) {
        final MenuItem[] items = this.getItems();
        for (int i = 0; i < items.length; ++i) {
            items[i].reskin(n);
        }
        super.reskinChildren(n);
    }
    
    void setBackground(final Color color) {
        this.checkWidget();
        int handle = -1;
        if (color != null) {
            if (color.isDisposed()) {
                SWT.error(5);
            }
            handle = color.handle;
        }
        if (handle == this.background) {
            return;
        }
        this.background = handle;
        this.updateBackground();
    }
    
    void setBackgroundImage(final Image backgroundImage) {
        this.checkWidget();
        if (backgroundImage != null) {
            if (backgroundImage.isDisposed()) {
                this.error(5);
            }
            if (backgroundImage.type != 0) {
                this.error(5);
            }
        }
        if (this.backgroundImage == backgroundImage) {
            return;
        }
        this.backgroundImage = backgroundImage;
        this.updateBackground();
    }
    
    void setForeground(final Color color) {
        this.checkWidget();
        int handle = -1;
        if (color != null) {
            if (color.isDisposed()) {
                SWT.error(5);
            }
            handle = color.handle;
        }
        if (handle == this.foreground) {
            return;
        }
        this.foreground = handle;
        this.updateForeground();
    }
    
    public void setDefaultItem(final MenuItem menuItem) {
        this.checkWidget();
        int id = -1;
        if (menuItem != null) {
            if (menuItem.isDisposed()) {
                this.error(5);
            }
            if (menuItem.parent != this) {
                return;
            }
            id = menuItem.id;
        }
        if (OS.IsWinCE) {
            return;
        }
        if (id == OS.GetMenuDefaultItem(this.handle, 0, 1)) {
            return;
        }
        OS.SetMenuDefaultItem(this.handle, id, 0);
        this.redraw();
    }
    
    public void setEnabled(final boolean b) {
        this.checkWidget();
        this.state &= 0xFFFFFFF7;
        if (!b) {
            this.state |= 0x8;
        }
    }
    
    public void setLocation(final int x, final int y) {
        this.checkWidget();
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        this.x = x;
        this.y = y;
        this.hasLocation = true;
    }
    
    public void setLocation(final Point point) {
        this.checkWidget();
        if (point == null) {
            SWT.error(4);
        }
        this.setLocation(point.x, point.y);
    }
    
    public void setOrientation(final int n) {
        this.checkWidget();
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        this._setOrientation(n);
    }
    
    void _setOrientation(final int orientation) {
        if (OS.IsWinCE) {
            return;
        }
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return;
        }
        final int n = 100663296;
        if ((orientation & n) == 0x0 || (orientation & n) == n) {
            return;
        }
        this.style &= ~n;
        this.style |= (orientation & n);
        final MenuItem[] items = this.getItems();
        for (int i = 0; i < items.length; ++i) {
            items[i].setOrientation(orientation);
        }
    }
    
    public void setVisible(final boolean b) {
        this.checkWidget();
        if ((this.style & 0x6) != 0x0) {
            return;
        }
        if (b) {
            this.display.addPopup(this);
        }
        else {
            this.display.removePopup(this);
            this._setVisible(false);
        }
    }
    
    void update() {
        if (OS.IsPPC || OS.IsSP) {
            return;
        }
        if (OS.IsHPC) {
            final Menu menuBar = this.parent.menuBar;
            if (menuBar != null) {
                Menu parentMenu;
                for (parentMenu = this; parentMenu != null && parentMenu != menuBar; parentMenu = parentMenu.getParentMenu()) {}
                if (parentMenu == menuBar) {
                    OS.CommandBar_DrawMenuBar(menuBar.hwndCB, 0);
                    OS.CommandBar_Show(menuBar.hwndCB, true);
                }
            }
            return;
        }
        if (OS.IsWinCE) {
            return;
        }
        if ((this.style & 0x2) != 0x0) {
            if (this == this.parent.menuBar) {
                OS.DrawMenuBar(this.parent.handle);
            }
            return;
        }
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return;
        }
        boolean b = false;
        boolean b2 = false;
        final MenuItem[] items = this.getItems();
        for (int i = 0; i < items.length; ++i) {
            final MenuItem menuItem = items[i];
            if (menuItem.image != null && (b2 = true) && b) {
                break;
            }
            if ((menuItem.style & 0x30) != 0x0 && (b = true) && b2) {
                break;
            }
        }
        if (!OS.IsWin95 && OS.WIN32_VERSION < OS.VERSION(6, 0)) {
            final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
            menuiteminfo.cbSize = MENUITEMINFO.sizeof;
            menuiteminfo.fMask = 128;
            for (int j = 0; j < items.length; ++j) {
                final MenuItem menuItem2 = items[j];
                if ((this.style & 0x2) == 0x0 && (menuItem2.image == null || this.foreground != -1)) {
                    menuiteminfo.hbmpItem = ((b2 || this.foreground != -1) ? -1 : 0);
                    OS.SetMenuItemInfo(this.handle, menuItem2.id, false, menuiteminfo);
                }
            }
        }
        final MENUINFO menuinfo = new MENUINFO();
        menuinfo.cbSize = MENUINFO.sizeof;
        menuinfo.fMask = 16;
        OS.GetMenuInfo(this.handle, menuinfo);
        if (b2 && !b) {
            final MENUINFO menuinfo2 = menuinfo;
            menuinfo2.dwStyle |= 0x4000000;
        }
        else {
            final MENUINFO menuinfo3 = menuinfo;
            menuinfo3.dwStyle &= 0xFBFFFFFF;
        }
        OS.SetMenuInfo(this.handle, menuinfo);
    }
    
    void updateBackground() {
        if (this.hBrush == 0) {
            OS.DeleteObject(this.hBrush);
        }
        this.hBrush = 0;
        if (this.backgroundImage != null) {
            this.hBrush = OS.CreatePatternBrush(this.backgroundImage.handle);
        }
        else if (this.background != -1) {
            this.hBrush = OS.CreateSolidBrush(this.background);
        }
        final MENUINFO menuinfo = new MENUINFO();
        menuinfo.cbSize = MENUINFO.sizeof;
        menuinfo.fMask = 2;
        menuinfo.hbrBack = this.hBrush;
        OS.SetMenuInfo(this.handle, menuinfo);
    }
    
    void updateForeground() {
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return;
        }
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        for (int n = 0; OS.GetMenuItemInfo(this.handle, n, true, menuiteminfo); ++n) {
            menuiteminfo.fMask = 128;
            menuiteminfo.hbmpItem = -1;
            OS.SetMenuItemInfo(this.handle, n, true, menuiteminfo);
        }
        this.redraw();
    }
}
