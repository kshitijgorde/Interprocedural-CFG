// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.STARTUPINFO;
import org.eclipse.swt.internal.win32.MENUITEMINFO;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.WINDOWPLACEMENT;
import org.eclipse.swt.internal.win32.ACCEL;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Image;

public class Decorations extends Canvas
{
    Image image;
    Image smallImage;
    Image largeImage;
    Image[] images;
    Menu menuBar;
    Menu[] menus;
    Control savedFocus;
    Button defaultButton;
    Button saveDefault;
    int swFlags;
    int nAccel;
    int hAccel;
    boolean moved;
    boolean resized;
    boolean opened;
    int oldX;
    int oldY;
    int oldWidth;
    int oldHeight;
    
    Decorations() {
        this.oldX = Integer.MIN_VALUE;
        this.oldY = Integer.MIN_VALUE;
        this.oldWidth = Integer.MIN_VALUE;
        this.oldHeight = Integer.MIN_VALUE;
    }
    
    public Decorations(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.oldX = Integer.MIN_VALUE;
        this.oldY = Integer.MIN_VALUE;
        this.oldWidth = Integer.MIN_VALUE;
        this.oldHeight = Integer.MIN_VALUE;
    }
    
    void _setMaximized(final boolean b) {
        this.swFlags = (b ? OS.SW_SHOWMAXIMIZED : OS.SW_RESTORE);
        if (OS.IsWinCE) {
            if (b) {
                final RECT rect = new RECT();
                OS.SystemParametersInfo(48, 0, rect, 0);
                final int n = rect.right - rect.left;
                int n2 = rect.bottom - rect.top;
                if (OS.IsPPC && this.menuBar != null) {
                    final int hwndCB = this.menuBar.hwndCB;
                    final RECT rect2 = new RECT();
                    OS.GetWindowRect(hwndCB, rect2);
                    n2 -= rect2.bottom - rect2.top;
                }
                this.SetWindowPos(this.handle, 0, rect.left, rect.top, n, n2, 52);
            }
        }
        else {
            if (!OS.IsWindowVisible(this.handle)) {
                return;
            }
            if (b == OS.IsZoomed(this.handle)) {
                return;
            }
            OS.ShowWindow(this.handle, this.swFlags);
            OS.UpdateWindow(this.handle);
        }
    }
    
    void _setMinimized(final boolean b) {
        if (OS.IsWinCE) {
            return;
        }
        this.swFlags = (b ? 7 : OS.SW_RESTORE);
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        if (b == OS.IsIconic(this.handle)) {
            return;
        }
        int swFlags = this.swFlags;
        if (swFlags == 7 && this.handle == OS.GetActiveWindow()) {
            swFlags = 6;
        }
        OS.ShowWindow(this.handle, swFlags);
        OS.UpdateWindow(this.handle);
    }
    
    void addMenu(final Menu menu) {
        if (this.menus == null) {
            this.menus = new Menu[4];
        }
        for (int i = 0; i < this.menus.length; ++i) {
            if (this.menus[i] == null) {
                this.menus[i] = menu;
                return;
            }
        }
        final Menu[] menus = new Menu[this.menus.length + 4];
        menus[this.menus.length] = menu;
        System.arraycopy(this.menus, 0, menus, 0, this.menus.length);
        this.menus = menus;
    }
    
    void bringToTop() {
        OS.BringWindowToTop(this.handle);
    }
    
    static int checkStyle(int n) {
        if ((n & 0x8) != 0x0) {
            n &= 0xFFFFF30F;
        }
        if (OS.IsWinCE) {
            if ((n & 0x80) != 0x0) {
                n &= 0xFFFFFF7F;
            }
            if ((n & 0x400) != 0x0) {
                n &= 0xFFFFFBFF;
            }
            return n;
        }
        if ((n & 0x4C0) != 0x0) {
            n |= 0x20;
        }
        if ((n & 0x480) != 0x0) {
            n |= 0x40;
        }
        if ((n & 0x40) != 0x0) {
            n |= 0x20;
        }
        return n;
    }
    
    void checkBorder() {
    }
    
    void checkComposited(final Composite composite) {
    }
    
    void checkOpened() {
        if (!this.opened) {
            this.resized = false;
        }
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        return OS.DefMDIChildProc(n, n2, n3, n4);
    }
    
    void closeWidget() {
        final Event event = new Event();
        this.sendEvent(21, event);
        if (event.doit && !this.isDisposed()) {
            this.dispose();
        }
    }
    
    int compare(final ImageData imageData, final ImageData imageData2, final int n, final int n2, final int n3) {
        final int abs = Math.abs(imageData.width - n);
        final int abs2 = Math.abs(imageData2.width - n);
        if (abs != abs2) {
            return (abs < abs2) ? -1 : 1;
        }
        final int transparencyType = imageData.getTransparencyType();
        final int transparencyType2 = imageData2.getTransparencyType();
        if (transparencyType == transparencyType2) {
            if (imageData.depth == imageData2.depth) {
                return 0;
            }
            return (imageData.depth > imageData2.depth && imageData.depth <= n3) ? -1 : 1;
        }
        else {
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
                if (transparencyType == 1) {
                    return -1;
                }
                if (transparencyType2 == 1) {
                    return 1;
                }
            }
            if (transparencyType == 2) {
                return -1;
            }
            if (transparencyType2 == 2) {
                return 1;
            }
            if (transparencyType == 4) {
                return -1;
            }
            if (transparencyType2 == 4) {
                return 1;
            }
            return 0;
        }
    }
    
    Widget computeTabGroup() {
        return this;
    }
    
    Control computeTabRoot() {
        return this;
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        final int getWindowLong2 = OS.GetWindowLong(this.handle, -20);
        final boolean b = !OS.IsWinCE && OS.GetMenu(this.handle) != 0;
        OS.AdjustWindowRectEx(rect, getWindowLong, b, getWindowLong2);
        if (this.horizontalBar != null) {
            final RECT rect2 = rect;
            rect2.bottom += OS.GetSystemMetrics(3);
        }
        if (this.verticalBar != null) {
            final RECT rect3 = rect;
            rect3.right += OS.GetSystemMetrics(2);
        }
        if (b) {
            final RECT rect4 = new RECT();
            OS.SetRect(rect4, 0, 0, rect.right - rect.left, rect.bottom - rect.top);
            OS.SendMessage(this.handle, 131, 0, rect4);
            while (rect4.bottom - rect4.top < n4) {
                if (rect4.bottom - rect4.top == 0) {
                    break;
                }
                final RECT rect5 = rect;
                rect5.top -= OS.GetSystemMetrics(15) - OS.GetSystemMetrics(6);
                OS.SetRect(rect4, 0, 0, rect.right - rect.left, rect.bottom - rect.top);
                OS.SendMessage(this.handle, 131, 0, rect4);
            }
        }
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    void createAccelerators() {
        final boolean b = false;
        this.nAccel = (b ? 1 : 0);
        this.hAccel = (b ? 1 : 0);
        final MenuItem[] items = this.display.items;
        int n;
        if (this.menuBar == null || items == null) {
            if (!OS.IsPPC) {
                return;
            }
            n = 1;
        }
        else {
            n = (OS.IsPPC ? (items.length + 1) : items.length);
        }
        final ACCEL accel = new ACCEL();
        final byte[] array = new byte[ACCEL.sizeof];
        final byte[] array2 = new byte[n * ACCEL.sizeof];
        if (this.menuBar != null && items != null) {
            for (int i = 0; i < items.length; ++i) {
                final MenuItem menuItem = items[i];
                if (menuItem != null && menuItem.accelerator != 0) {
                    Menu menu = menuItem.parent;
                    if (menu.parent == this) {
                        while (menu != null && menu != this.menuBar) {
                            menu = menu.getParentMenu();
                        }
                        if (menu == this.menuBar && menuItem.fillAccel(accel)) {
                            OS.MoveMemory(array, accel, ACCEL.sizeof);
                            System.arraycopy(array, 0, array2, this.nAccel * ACCEL.sizeof, ACCEL.sizeof);
                            ++this.nAccel;
                        }
                    }
                }
            }
        }
        if (OS.IsPPC) {
            accel.fVirt = 9;
            accel.key = 81;
            accel.cmd = 1;
            OS.MoveMemory(array, accel, ACCEL.sizeof);
            System.arraycopy(array, 0, array2, this.nAccel * ACCEL.sizeof, ACCEL.sizeof);
            ++this.nAccel;
        }
        if (this.nAccel != 0) {
            this.hAccel = OS.CreateAcceleratorTable(array2, this.nAccel);
        }
    }
    
    void createHandle() {
        super.createHandle();
        if (this.parent != null || (this.style & 0x4) != 0x0) {
            this.setParent();
            this.setSystemMenu();
        }
    }
    
    void createWidget() {
        super.createWidget();
        this.swFlags = (OS.IsWinCE ? OS.SW_SHOWMAXIMIZED : 4);
        this.hAccel = -1;
    }
    
    void destroyAccelerators() {
        if (this.hAccel != 0 && this.hAccel != -1) {
            OS.DestroyAcceleratorTable(this.hAccel);
        }
        this.hAccel = -1;
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        if (!this.isValidThread()) {
            this.error(22);
        }
        if (!(this instanceof Shell)) {
            if (!this.traverseDecorations(true)) {
                this.getShell().setFocus();
            }
            this.setVisible(false);
        }
        super.dispose();
    }
    
    Menu findMenu(final int n) {
        if (this.menus == null) {
            return null;
        }
        for (int i = 0; i < this.menus.length; ++i) {
            final Menu menu = this.menus[i];
            if (menu != null && n == menu.handle) {
                return menu;
            }
        }
        return null;
    }
    
    void fixDecorations(final Decorations decorations, final Control control, final Menu[] array) {
        if (this == decorations) {
            return;
        }
        if (control == this.savedFocus) {
            this.savedFocus = null;
        }
        if (control == this.defaultButton) {
            this.defaultButton = null;
        }
        if (control == this.saveDefault) {
            this.saveDefault = null;
        }
        if (array == null) {
            return;
        }
        final Menu menu = control.menu;
        if (menu != null) {
            for (int i = 0; i < array.length; ++i) {
                if (array[i] == menu) {
                    control.setMenu(null);
                    return;
                }
            }
            menu.fixMenus(decorations);
            this.destroyAccelerators();
            decorations.destroyAccelerators();
        }
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.IsIconic(this.handle)) {
            final WINDOWPLACEMENT windowplacement = new WINDOWPLACEMENT();
            windowplacement.length = WINDOWPLACEMENT.sizeof;
            OS.GetWindowPlacement(this.handle, windowplacement);
            return new Rectangle(windowplacement.left, windowplacement.top, windowplacement.right - windowplacement.left, windowplacement.bottom - windowplacement.top);
        }
        return super.getBounds();
    }
    
    public Rectangle getClientArea() {
        this.checkWidget();
        if (OS.IsHPC) {
            final Rectangle clientArea = super.getClientArea();
            if (this.menuBar != null) {
                final int commandBar_Height = OS.CommandBar_Height(this.menuBar.hwndCB);
                final Rectangle rectangle = clientArea;
                rectangle.y += commandBar_Height;
                clientArea.height = Math.max(0, clientArea.height - commandBar_Height);
            }
            return clientArea;
        }
        if (!OS.IsWinCE && OS.IsIconic(this.handle)) {
            final WINDOWPLACEMENT windowplacement = new WINDOWPLACEMENT();
            windowplacement.length = WINDOWPLACEMENT.sizeof;
            OS.GetWindowPlacement(this.handle, windowplacement);
            int n = windowplacement.right - windowplacement.left;
            int n2 = windowplacement.bottom - windowplacement.top;
            if (this.horizontalBar != null) {
                n -= OS.GetSystemMetrics(3);
            }
            if (this.verticalBar != null) {
                n2 -= OS.GetSystemMetrics(2);
            }
            final RECT rect = new RECT();
            OS.AdjustWindowRectEx(rect, OS.GetWindowLong(this.handle, -16), !OS.IsWinCE && OS.GetMenu(this.handle) != 0, OS.GetWindowLong(this.handle, -20));
            return new Rectangle(0, 0, Math.max(0, n - (rect.right - rect.left)), Math.max(0, n2 - (rect.bottom - rect.top)));
        }
        return super.getClientArea();
    }
    
    public Button getDefaultButton() {
        this.checkWidget();
        if (this.defaultButton != null && this.defaultButton.isDisposed()) {
            return null;
        }
        return this.defaultButton;
    }
    
    public Image getImage() {
        this.checkWidget();
        return this.image;
    }
    
    public Image[] getImages() {
        this.checkWidget();
        if (this.images == null) {
            return new Image[0];
        }
        final Image[] array = new Image[this.images.length];
        System.arraycopy(this.images, 0, array, 0, this.images.length);
        return array;
    }
    
    public Point getLocation() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.IsIconic(this.handle)) {
            final WINDOWPLACEMENT windowplacement = new WINDOWPLACEMENT();
            windowplacement.length = WINDOWPLACEMENT.sizeof;
            OS.GetWindowPlacement(this.handle, windowplacement);
            return new Point(windowplacement.left, windowplacement.top);
        }
        return super.getLocation();
    }
    
    public boolean getMaximized() {
        this.checkWidget();
        if (OS.IsWinCE) {
            return this.swFlags == OS.SW_SHOWMAXIMIZED;
        }
        if (OS.IsWindowVisible(this.handle)) {
            return OS.IsZoomed(this.handle);
        }
        return this.swFlags == OS.SW_SHOWMAXIMIZED;
    }
    
    public Menu getMenuBar() {
        this.checkWidget();
        return this.menuBar;
    }
    
    public boolean getMinimized() {
        this.checkWidget();
        if (OS.IsWinCE) {
            return false;
        }
        if (OS.IsWindowVisible(this.handle)) {
            return OS.IsIconic(this.handle);
        }
        return this.swFlags == 7;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public Point getSize() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.IsIconic(this.handle)) {
            final WINDOWPLACEMENT windowplacement = new WINDOWPLACEMENT();
            windowplacement.length = WINDOWPLACEMENT.sizeof;
            OS.GetWindowPlacement(this.handle, windowplacement);
            return new Point(windowplacement.right - windowplacement.left, windowplacement.bottom - windowplacement.top);
        }
        return super.getSize();
    }
    
    public String getText() {
        this.checkWidget();
        final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
        if (getWindowTextLength == 0) {
            return "";
        }
        final TCHAR tchar = new TCHAR(0, getWindowTextLength + 1);
        OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
        return tchar.toString(0, getWindowTextLength);
    }
    
    public boolean isReparentable() {
        this.checkWidget();
        return false;
    }
    
    boolean isTabGroup() {
        return true;
    }
    
    boolean isTabItem() {
        return false;
    }
    
    Decorations menuShell() {
        return this;
    }
    
    void releaseChildren(final boolean b) {
        if (this.menuBar != null) {
            this.menuBar.release(false);
            this.menuBar = null;
        }
        super.releaseChildren(b);
        if (this.menus != null) {
            for (int i = 0; i < this.menus.length; ++i) {
                final Menu menu = this.menus[i];
                if (menu != null && !menu.isDisposed()) {
                    menu.dispose();
                }
            }
            this.menus = null;
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.smallImage != null) {
            this.smallImage.dispose();
        }
        if (this.largeImage != null) {
            this.largeImage.dispose();
        }
        final Image smallImage = null;
        this.image = smallImage;
        this.largeImage = smallImage;
        this.smallImage = smallImage;
        this.images = null;
        this.savedFocus = null;
        final Button button = null;
        this.saveDefault = button;
        this.defaultButton = button;
        if (this.hAccel != 0 && this.hAccel != -1) {
            OS.DestroyAcceleratorTable(this.hAccel);
        }
        this.hAccel = -1;
    }
    
    void removeMenu(final Menu menu) {
        if (this.menus == null) {
            return;
        }
        for (int i = 0; i < this.menus.length; ++i) {
            if (this.menus[i] == menu) {
                this.menus[i] = null;
                return;
            }
        }
    }
    
    void reskinChildren(final int n) {
        if (this.menuBar != null) {
            this.menuBar.reskin(n);
        }
        if (this.menus != null) {
            for (int i = 0; i < this.menus.length; ++i) {
                final Menu menu = this.menus[i];
                if (menu != null) {
                    menu.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    boolean restoreFocus() {
        if (this.display.ignoreRestoreFocus) {
            return true;
        }
        if (this.savedFocus != null && this.savedFocus.isDisposed()) {
            this.savedFocus = null;
        }
        return this.savedFocus != null && this.savedFocus.setSavedFocus();
    }
    
    void saveFocus() {
        final Control getFocusControl = this.display._getFocusControl();
        if (getFocusControl != null && getFocusControl != this && this == getFocusControl.menuShell()) {
            this.setSavedFocus(getFocusControl);
        }
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        this.swFlags = 4;
        if (OS.IsWinCE) {
            this.swFlags = OS.SW_RESTORE;
        }
        else if (OS.IsIconic(this.handle)) {
            this.setPlacement(n, n2, n3, n4, n5);
            return;
        }
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        boolean b2 = true;
        if ((0x2 & n5) == 0x0) {
            b2 = (rect.left == n && rect.top == n2);
            if (!b2) {
                this.moved = true;
            }
        }
        boolean b3 = true;
        if ((0x1 & n5) == 0x0) {
            b3 = (rect.right - rect.left == n3 && rect.bottom - rect.top == n4);
            if (!b3) {
                this.resized = true;
            }
        }
        if (OS.IsWinCE || !OS.IsZoomed(this.handle)) {
            super.setBounds(n, n2, n3, n4, n5, b);
            return;
        }
        if (b2 && b3) {
            return;
        }
        this.setPlacement(n, n2, n3, n4, n5);
        this._setMaximized(false);
    }
    
    public void setDefaultButton(final Button button) {
        this.checkWidget();
        if (button != null) {
            if (button.isDisposed()) {
                this.error(5);
            }
            if (button.menuShell() != this) {
                this.error(32);
            }
        }
        this.setDefaultButton(button, true);
    }
    
    void setDefaultButton(final Button defaultButton, final boolean b) {
        if (defaultButton == null) {
            if (this.defaultButton == this.saveDefault) {
                if (b) {
                    this.saveDefault = null;
                }
                return;
            }
        }
        else {
            if ((defaultButton.style & 0x8) == 0x0) {
                return;
            }
            if (defaultButton == this.defaultButton) {
                if (b) {
                    this.saveDefault = this.defaultButton;
                }
                return;
            }
        }
        if (this.defaultButton != null && !this.defaultButton.isDisposed()) {
            this.defaultButton.setDefault(false);
        }
        if ((this.defaultButton = defaultButton) == null) {
            this.defaultButton = this.saveDefault;
        }
        if (this.defaultButton != null && !this.defaultButton.isDisposed()) {
            this.defaultButton.setDefault(true);
        }
        if (b) {
            this.saveDefault = this.defaultButton;
        }
        if (this.saveDefault != null && this.saveDefault.isDisposed()) {
            this.saveDefault = null;
        }
    }
    
    public void setImage(final Image image) {
        this.checkWidget();
        if (image != null && image.isDisposed()) {
            this.error(5);
        }
        this.setImages(this.image = image, null);
    }
    
    void setImages(final Image image, Image[] array) {
        if (OS.IsWinCE) {
            return;
        }
        if (this.smallImage != null) {
            this.smallImage.dispose();
        }
        if (this.largeImage != null) {
            this.largeImage.dispose();
        }
        final Image image2 = null;
        this.largeImage = image2;
        this.smallImage = image2;
        int n = 0;
        int n2 = 0;
        Image image3 = null;
        Image image4 = null;
        if (image != null) {
            image4 = image;
            image3 = image;
        }
        else if (array != null && array.length > 0) {
            final int iconDepth = this.display.getIconDepth();
            ImageData[] array2 = null;
            if (array.length > 1) {
                final Image[] array3 = new Image[array.length];
                System.arraycopy(array, 0, array3, 0, array.length);
                array2 = new ImageData[array.length];
                for (int i = 0; i < array2.length; ++i) {
                    array2[i] = array[i].getImageData();
                }
                array = array3;
                this.sort(array, array2, OS.GetSystemMetrics(49), OS.GetSystemMetrics(50), iconDepth);
            }
            image3 = array[0];
            if (array.length > 1) {
                this.sort(array, array2, OS.GetSystemMetrics(11), OS.GetSystemMetrics(12), iconDepth);
            }
            image4 = array[0];
        }
        if (image3 != null) {
            switch (image3.type) {
                case 0: {
                    this.smallImage = Display.createIcon(image3);
                    n = this.smallImage.handle;
                    break;
                }
                case 1: {
                    n = image3.handle;
                    break;
                }
            }
        }
        OS.SendMessage(this.handle, 128, 0, n);
        if (image4 != null) {
            switch (image4.type) {
                case 0: {
                    this.largeImage = Display.createIcon(image4);
                    n2 = this.largeImage.handle;
                    break;
                }
                case 1: {
                    n2 = image4.handle;
                    break;
                }
            }
        }
        OS.SendMessage(this.handle, 128, 1, n2);
        if (!OS.IsWinCE && n == 0 && n2 == 0 && (this.style & 0x800) != 0x0) {
            OS.RedrawWindow(this.handle, null, 0, 1025);
        }
    }
    
    public void setImages(final Image[] images) {
        this.checkWidget();
        if (images == null) {
            this.error(5);
        }
        for (int i = 0; i < images.length; ++i) {
            if (images[i] == null || images[i].isDisposed()) {
                this.error(5);
            }
        }
        this.setImages(null, this.images = images);
    }
    
    public void setMaximized(final boolean b) {
        this.checkWidget();
        Display.lpStartupInfo = null;
        this._setMaximized(b);
    }
    
    public void setMenuBar(final Menu menu) {
        this.checkWidget();
        if (this.menuBar == menu) {
            return;
        }
        if (menu != null) {
            if (menu.isDisposed()) {
                this.error(5);
            }
            if ((menu.style & 0x2) == 0x0) {
                this.error(33);
            }
            if (menu.parent != this) {
                this.error(32);
            }
        }
        if (OS.IsWinCE) {
            if (OS.IsHPC) {
                final boolean b = this.menuBar != menu;
                if (this.menuBar != null) {
                    OS.CommandBar_Show(this.menuBar.hwndCB, false);
                }
                this.menuBar = menu;
                if (this.menuBar != null) {
                    OS.CommandBar_Show(this.menuBar.hwndCB, true);
                }
                if (b) {
                    this.sendEvent(11);
                    if (this.isDisposed()) {
                        return;
                    }
                    if (this.layout != null) {
                        this.markLayout(false, false);
                        this.updateLayout(true, false);
                    }
                }
            }
            else {
                if (OS.IsPPC) {
                    final boolean b2 = this.getMaximized() && this.menuBar != menu;
                    if (this.menuBar != null) {
                        OS.ShowWindow(this.menuBar.hwndCB, 0);
                    }
                    this.menuBar = menu;
                    if (this.menuBar != null) {
                        OS.ShowWindow(this.menuBar.hwndCB, 5);
                    }
                    if (b2) {
                        this._setMaximized(true);
                    }
                }
                if (OS.IsSP) {
                    if (this.menuBar != null) {
                        OS.ShowWindow(this.menuBar.hwndCB, 0);
                    }
                    this.menuBar = menu;
                    if (this.menuBar != null) {
                        OS.ShowWindow(this.menuBar.hwndCB, 5);
                    }
                }
            }
        }
        else {
            if (menu != null) {
                this.display.removeBar(menu);
            }
            this.menuBar = menu;
            OS.SetMenu(this.handle, (this.menuBar != null) ? this.menuBar.handle : 0);
        }
        this.destroyAccelerators();
    }
    
    public void setMinimized(final boolean b) {
        this.checkWidget();
        Display.lpStartupInfo = null;
        this._setMinimized(b);
    }
    
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
        if (this.menus != null) {
            for (int i = 0; i < this.menus.length; ++i) {
                final Menu menu = this.menus[i];
                if (menu != null && !menu.isDisposed() && (menu.getStyle() & 0x8) != 0x0) {
                    menu._setOrientation(menu.getOrientation());
                }
            }
        }
    }
    
    void setParent() {
        final int handle = this.parent.handle;
        this.display.lockActiveWindow = true;
        OS.SetParent(this.handle, handle);
        if (!OS.IsWindowVisible(handle)) {
            OS.ShowWindow(this.handle, 8);
        }
        OS.SetWindowLong(this.handle, -16, (OS.GetWindowLong(this.handle, -16) & 0xBFFFFFFF) | Integer.MIN_VALUE);
        OS.SetWindowLongPtr(this.handle, -12, 0);
        this.SetWindowPos(this.handle, 1, 0, 0, 0, 0, 19);
        this.display.lockActiveWindow = false;
    }
    
    void setPlacement(final int left, final int top, final int n, final int n2, final int n3) {
        final WINDOWPLACEMENT windowplacement = new WINDOWPLACEMENT();
        windowplacement.length = WINDOWPLACEMENT.sizeof;
        OS.GetWindowPlacement(this.handle, windowplacement);
        windowplacement.showCmd = 8;
        if (OS.IsIconic(this.handle)) {
            windowplacement.showCmd = 7;
        }
        else if (OS.IsZoomed(this.handle)) {
            windowplacement.showCmd = OS.SW_SHOWMAXIMIZED;
        }
        int n4 = 1;
        if ((n3 & 0x2) == 0x0) {
            n4 = ((windowplacement.left != left || windowplacement.top != top) ? 1 : 0);
            windowplacement.right = left + (windowplacement.right - windowplacement.left);
            windowplacement.bottom = top + (windowplacement.bottom - windowplacement.top);
            windowplacement.left = left;
            windowplacement.top = top;
        }
        int n5 = 1;
        if ((n3 & 0x1) == 0x0) {
            n5 = ((windowplacement.right - windowplacement.left != n || windowplacement.bottom - windowplacement.top != n2) ? 1 : 0);
            windowplacement.right = windowplacement.left + n;
            windowplacement.bottom = windowplacement.top + n2;
        }
        OS.SetWindowPlacement(this.handle, windowplacement);
        if (OS.IsIconic(this.handle)) {
            if (n4 != 0) {
                this.moved = true;
                final Point location = this.getLocation();
                this.oldX = location.x;
                this.oldY = location.y;
                this.sendEvent(10);
                if (this.isDisposed()) {
                    return;
                }
            }
            if (n5 != 0) {
                this.resized = true;
                final Rectangle clientArea = this.getClientArea();
                this.oldWidth = clientArea.width;
                this.oldHeight = clientArea.height;
                this.sendEvent(11);
                if (this.isDisposed()) {
                    return;
                }
                if (this.layout != null) {
                    this.markLayout(false, false);
                    this.updateLayout(true, false);
                }
            }
        }
    }
    
    void setSavedFocus(final Control savedFocus) {
        this.savedFocus = savedFocus;
    }
    
    void setSystemMenu() {
        if (OS.IsWinCE) {
            return;
        }
        final int getSystemMenu = OS.GetSystemMenu(this.handle, false);
        if (getSystemMenu == 0) {
            return;
        }
        final int getMenuItemCount = OS.GetMenuItemCount(getSystemMenu);
        if ((this.style & 0x10) == 0x0) {
            OS.DeleteMenu(getSystemMenu, 61440, 0);
        }
        if ((this.style & 0x80) == 0x0) {
            OS.DeleteMenu(getSystemMenu, 61472, 0);
        }
        if ((this.style & 0x400) == 0x0) {
            OS.DeleteMenu(getSystemMenu, 61488, 0);
        }
        if ((this.style & 0x480) == 0x0) {
            OS.DeleteMenu(getSystemMenu, 61728, 0);
        }
        final int getMenuItemCount2 = OS.GetMenuItemCount(getSystemMenu);
        if ((this.style & 0x40) == 0x0 || getMenuItemCount2 != getMenuItemCount) {
            OS.DeleteMenu(getSystemMenu, 61744, 0);
            final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
            menuiteminfo.cbSize = MENUITEMINFO.sizeof;
            menuiteminfo.fMask = 2;
            int n;
            for (n = 0; n < getMenuItemCount2 && (!OS.GetMenuItemInfo(getSystemMenu, n, true, menuiteminfo) || menuiteminfo.wID != 61536); ++n) {}
            if (n != getMenuItemCount2) {
                OS.DeleteMenu(getSystemMenu, n - 1, 1024);
                if ((this.style & 0x40) == 0x0) {
                    OS.DeleteMenu(getSystemMenu, 61536, 0);
                }
            }
        }
    }
    
    public void setText(final String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        final TCHAR tchar = new TCHAR(0, s, true);
        if ((this.state & 0x4000) != 0x0) {
            final int getProcessHeap = OS.GetProcessHeap();
            final int n = tchar.length() * TCHAR.sizeof;
            final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
            OS.MoveMemory(heapAlloc, tchar, n);
            OS.DefWindowProc(this.handle, 12, 0, heapAlloc);
            if (heapAlloc != 0) {
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
            }
        }
        else {
            OS.SetWindowText(this.handle, tchar);
        }
    }
    
    public void setVisible(final boolean b) {
        this.checkWidget();
        if (!this.getDrawing()) {
            if ((this.state & 0x10) == 0x0 == b) {
                return;
            }
        }
        else if (b == OS.IsWindowVisible(this.handle)) {
            return;
        }
        if (b) {
            this.sendEvent(22);
            if (this.isDisposed()) {
                return;
            }
            if (OS.IsHPC && this.menuBar != null) {
                OS.CommandBar_DrawMenuBar(this.menuBar.hwndCB, 0);
            }
            if (!this.getDrawing()) {
                this.state &= 0xFFFFFFEF;
            }
            else {
                if (OS.IsWinCE) {
                    OS.ShowWindow(this.handle, 5);
                }
                else {
                    if (this.menuBar != null) {
                        this.display.removeBar(this.menuBar);
                        OS.DrawMenuBar(this.handle);
                    }
                    final STARTUPINFO lpStartupInfo = Display.lpStartupInfo;
                    if (lpStartupInfo != null && (lpStartupInfo.dwFlags & 0x1) != 0x0) {
                        OS.ShowWindow(this.handle, lpStartupInfo.wShowWindow);
                    }
                    else {
                        OS.ShowWindow(this.handle, this.swFlags);
                    }
                }
                if (this.isDisposed()) {
                    return;
                }
                this.opened = true;
                if (!this.moved) {
                    this.moved = true;
                    final Point location = this.getLocation();
                    this.oldX = location.x;
                    this.oldY = location.y;
                }
                if (!this.resized) {
                    this.resized = true;
                    final Rectangle clientArea = this.getClientArea();
                    this.oldWidth = clientArea.width;
                    this.oldHeight = clientArea.height;
                }
                int n = 1;
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && !OS.IsAppThemed()) {
                    n = (OS.IsHungAppWindow(this.handle) ? 0 : 1);
                }
                if (n != 0) {
                    OS.UpdateWindow(this.handle);
                }
            }
        }
        else {
            if (!OS.IsWinCE) {
                if (OS.IsIconic(this.handle)) {
                    this.swFlags = 7;
                }
                else if (OS.IsZoomed(this.handle)) {
                    this.swFlags = OS.SW_SHOWMAXIMIZED;
                }
                else {
                    this.swFlags = 4;
                }
            }
            if (!this.getDrawing()) {
                this.state |= 0x10;
            }
            else {
                OS.ShowWindow(this.handle, 0);
            }
            if (this.isDisposed()) {
                return;
            }
            this.sendEvent(23);
        }
    }
    
    void sort(final Image[] array, final ImageData[] array2, final int n, final int n2, final int n3) {
        final int length = array.length;
        if (length <= 1) {
            return;
        }
        for (int i = length / 2; i > 0; i /= 2) {
            for (int j = i; j < length; ++j) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (this.compare(array2[k], array2[k + i], n, n2, n3) >= 0) {
                        final Image image = array[k];
                        array[k] = array[k + i];
                        array[k + i] = image;
                        final ImageData imageData = array2[k];
                        array2[k] = array2[k + i];
                        array2[k + i] = imageData;
                    }
                }
            }
        }
    }
    
    boolean translateAccelerator(final MSG msg) {
        return this.isEnabled() && this.isActive() && (this.menuBar == null || this.menuBar.isEnabled()) && (this.translateMDIAccelerator(msg) || this.translateMenuAccelerator(msg) || this.parent.menuShell().translateAccelerator(msg));
    }
    
    boolean translateMenuAccelerator(final MSG msg) {
        if (this.hAccel == -1) {
            this.createAccelerators();
        }
        return this.hAccel != 0 && OS.TranslateAccelerator(this.handle, this.hAccel, msg) != 0;
    }
    
    boolean translateMDIAccelerator(final MSG msg) {
        if (!(this instanceof Shell)) {
            final Shell shell = this.getShell();
            final int hwndMDIClient = shell.hwndMDIClient;
            if (hwndMDIClient != 0 && OS.TranslateMDISysAccel(hwndMDIClient, msg)) {
                return true;
            }
            if (msg.message == 256) {
                if (OS.GetKeyState(17) >= 0) {
                    return false;
                }
                switch (msg.wParam) {
                    case 115: {
                        OS.PostMessage(this.handle, 16, 0, 0);
                        return true;
                    }
                    case 117: {
                        if (this.traverseDecorations(true)) {
                            return true;
                        }
                        break;
                    }
                }
                return false;
            }
            else if (msg.message == 260) {
                switch (msg.wParam) {
                    case 115: {
                        OS.PostMessage(shell.handle, 16, 0, 0);
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
        }
        return false;
    }
    
    boolean traverseDecorations(final boolean b) {
        Control[] getChildren;
        int length;
        int n;
        for (getChildren = this.parent._getChildren(), length = getChildren.length, n = 0; n < length && getChildren[n] != this; ++n) {}
        while ((n = (n + (b ? 1 : -1) + length) % length) != n) {
            final Control control = getChildren[n];
            if (!control.isDisposed() && control instanceof Decorations && control.setFocus()) {
                return true;
            }
        }
        return false;
    }
    
    boolean traverseItem(final boolean b) {
        return false;
    }
    
    boolean traverseReturn() {
        if (this.defaultButton == null || this.defaultButton.isDisposed()) {
            return false;
        }
        if (!this.defaultButton.isVisible() || !this.defaultButton.isEnabled()) {
            return false;
        }
        this.defaultButton.click();
        return true;
    }
    
    CREATESTRUCT widgetCreateStruct() {
        return new CREATESTRUCT();
    }
    
    int widgetExtStyle() {
        int n = (super.widgetExtStyle() | 0x40) & 0xFFFFFDFF;
        if ((this.style & 0x8) != 0x0) {
            return n;
        }
        if (OS.IsPPC && (this.style & 0x40) != 0x0) {
            n |= Integer.MIN_VALUE;
        }
        if ((this.style & 0x10) != 0x0) {
            return n;
        }
        if ((this.style & 0x800) != 0x0) {
            n |= 0x1;
        }
        return n;
    }
    
    int widgetParent() {
        return this.getShell().hwndMDIClient();
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() & 0xEFFEFFFF & 0xFF7FFFFF;
        if ((this.style & 0x8) != 0x0) {
            if (this.parent == null) {
                n |= (0x80000 | OS.WS_MINIMIZEBOX);
            }
            return n;
        }
        if ((this.style & 0x20) != 0x0) {
            n |= 0xC00000;
        }
        if ((this.style & 0x80) != 0x0) {
            n |= OS.WS_MINIMIZEBOX;
        }
        if ((this.style & 0x400) != 0x0) {
            n |= OS.WS_MAXIMIZEBOX;
        }
        if ((this.style & 0x10) != 0x0) {
            if (!OS.IsPPC) {
                n |= 0x40000;
            }
        }
        else if ((this.style & 0x800) == 0x0) {
            n |= 0x800000;
        }
        if (!OS.IsPPC && !OS.IsSP && (this.style & 0x40) != 0x0) {
            n |= 0x80000;
        }
        return n;
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 32768:
            case 32769: {
                if (this.hAccel == -1) {
                    this.createAccelerators();
                }
                return (n2 == 32768) ? this.nAccel : this.hAccel;
            }
            default: {
                return super.windowProc(n, n2, n3, n4);
            }
        }
    }
    
    LRESULT WM_ACTIVATE(final int n, final int n2) {
        final LRESULT wm_ACTIVATE = super.WM_ACTIVATE(n, n2);
        if (wm_ACTIVATE != null) {
            return wm_ACTIVATE;
        }
        if (OS.GetParent(n2) == this.handle) {
            final TCHAR tchar = new TCHAR(0, 128);
            OS.GetClassName(n2, tchar, tchar.length());
            if (tchar.toString(0, tchar.strlen()).equals("SunAwtWindow")) {
                return LRESULT.ZERO;
            }
        }
        if (OS.LOWORD(n) != 0) {
            if (OS.HIWORD(n) != 0) {
                return wm_ACTIVATE;
            }
            final Control control = this.display.findControl(n2);
            if ((control == null || control instanceof Shell) && this instanceof Shell) {
                this.sendEvent(26);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
            }
            if (this.restoreFocus()) {
                return LRESULT.ZERO;
            }
        }
        else {
            final Display display = this.display;
            final boolean xMouseActive = display.isXMouseActive();
            if (xMouseActive) {
                display.lockActiveWindow = true;
            }
            final Control control2 = display.findControl(n2);
            if ((control2 == null || control2 instanceof Shell) && this instanceof Shell) {
                this.sendEvent(27);
                if (!this.isDisposed()) {
                    this.getShell().setActiveControl(null);
                }
            }
            if (xMouseActive) {
                display.lockActiveWindow = false;
            }
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
            this.saveFocus();
        }
        return wm_ACTIVATE;
    }
    
    LRESULT WM_CLOSE(final int n, final int n2) {
        final LRESULT wm_CLOSE = super.WM_CLOSE(n, n2);
        if (wm_CLOSE != null) {
            return wm_CLOSE;
        }
        if (this.isEnabled() && this.isActive()) {
            this.closeWidget();
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_HOTKEY(final int n, final int n2) {
        final LRESULT wm_HOTKEY = super.WM_HOTKEY(n, n2);
        if (wm_HOTKEY != null) {
            return wm_HOTKEY;
        }
        if (OS.IsSP && OS.HIWORD(n2) == 27) {
            if ((this.style & 0x40) != 0x0) {
                OS.PostMessage(this.handle, 16, 0, 0);
            }
            else {
                OS.SHSendBackToFocusWindow(786, n, n2);
            }
            return LRESULT.ZERO;
        }
        return wm_HOTKEY;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        final LRESULT wm_KILLFOCUS = super.WM_KILLFOCUS(n, n2);
        this.saveFocus();
        return wm_KILLFOCUS;
    }
    
    LRESULT WM_MOVE(final int n, final int n2) {
        if (this.moved) {
            final Point location = this.getLocation();
            if (location.x == this.oldX && location.y == this.oldY) {
                return null;
            }
            this.oldX = location.x;
            this.oldY = location.y;
        }
        return super.WM_MOVE(n, n2);
    }
    
    LRESULT WM_NCACTIVATE(final int n, final int n2) {
        LRESULT lresult = super.WM_NCACTIVATE(n, n2);
        if (lresult != null) {
            return lresult;
        }
        if (n == 0) {
            if (this.display.lockActiveWindow) {
                return LRESULT.ZERO;
            }
            final Control control = this.display.findControl(n2);
            if (control != null && control.menuShell().getShell() == this.getShell()) {
                if (this instanceof Shell) {
                    return LRESULT.ONE;
                }
                if (this.display.ignoreRestoreFocus && this.display.lastHittest != 1) {
                    lresult = LRESULT.ONE;
                }
            }
        }
        if (!(this instanceof Shell)) {
            OS.SendMessage(this.getShell().handle, 134, n, n2);
        }
        return lresult;
    }
    
    LRESULT WM_QUERYOPEN(final int n, final int n2) {
        final LRESULT wm_QUERYOPEN = super.WM_QUERYOPEN(n, n2);
        if (wm_QUERYOPEN != null) {
            return wm_QUERYOPEN;
        }
        this.sendEvent(20);
        return wm_QUERYOPEN;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        final LRESULT wm_SETFOCUS = super.WM_SETFOCUS(n, n2);
        if (this.isDisposed()) {
            return wm_SETFOCUS;
        }
        if (this.savedFocus != this) {
            this.restoreFocus();
        }
        return wm_SETFOCUS;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        LRESULT wm_SIZE = null;
        boolean b = true;
        if (this.resized) {
            int oldWidth = 0;
            int oldHeight = 0;
            switch (n) {
                case 0:
                case 2: {
                    oldWidth = OS.LOWORD(n2);
                    oldHeight = OS.HIWORD(n2);
                    break;
                }
                case 1: {
                    final Rectangle clientArea = this.getClientArea();
                    oldWidth = clientArea.width;
                    oldHeight = clientArea.height;
                    break;
                }
            }
            b = (oldWidth != this.oldWidth || oldHeight != this.oldHeight);
            if (b) {
                this.oldWidth = oldWidth;
                this.oldHeight = oldHeight;
            }
        }
        if (b) {
            wm_SIZE = super.WM_SIZE(n, n2);
            if (this.isDisposed()) {
                return wm_SIZE;
            }
        }
        if (n == 1) {
            this.sendEvent(19);
        }
        return wm_SIZE;
    }
    
    LRESULT WM_SYSCOMMAND(final int n, final int n2) {
        final LRESULT wm_SYSCOMMAND = super.WM_SYSCOMMAND(n, n2);
        if (wm_SYSCOMMAND != null) {
            return wm_SYSCOMMAND;
        }
        if (!(this instanceof Shell)) {
            switch (n & 0xFFF0) {
                case 61536: {
                    OS.PostMessage(this.handle, 16, 0, 0);
                    return LRESULT.ZERO;
                }
                case 61504: {
                    this.traverseDecorations(true);
                    return LRESULT.ZERO;
                }
            }
        }
        return wm_SYSCOMMAND;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final int n, final int n2) {
        final LRESULT wm_WINDOWPOSCHANGING = super.WM_WINDOWPOSCHANGING(n, n2);
        if (wm_WINDOWPOSCHANGING != null) {
            return wm_WINDOWPOSCHANGING;
        }
        if (this.display.lockActiveWindow) {
            final WINDOWPOS windowpos = new WINDOWPOS();
            OS.MoveMemory(windowpos, n2, WINDOWPOS.sizeof);
            final WINDOWPOS windowpos2 = windowpos;
            windowpos2.flags |= 0x4;
            OS.MoveMemory(n2, windowpos, WINDOWPOS.sizeof);
        }
        return wm_WINDOWPOSCHANGING;
    }
}
