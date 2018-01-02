// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.NMTTDISPINFOW;
import org.eclipse.swt.internal.win32.NMTTDISPINFOA;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.BP_PAINTPARAMS;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.NMTTDISPINFO;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WINDOWPOS;

public class Composite extends Scrollable
{
    Layout layout;
    WINDOWPOS[] lpwp;
    Control[] tabList;
    int layoutCount;
    int backgroundMode;
    
    Composite() {
    }
    
    public Composite(final Composite composite, final int n) {
        super(composite, n);
    }
    
    Control[] _getChildren() {
        int n = 0;
        int i = OS.GetWindow(this.handle, 5);
        if (i == 0) {
            return new Control[0];
        }
        while (i != 0) {
            ++n;
            i = OS.GetWindow(i, 2);
        }
        final Control[] array = new Control[n];
        int n2 = 0;
        for (int j = OS.GetWindow(this.handle, 5); j != 0; j = OS.GetWindow(j, 2)) {
            final Control control = this.display.getControl(j);
            if (control != null && control != this) {
                array[n2++] = control;
            }
        }
        if (n == n2) {
            return array;
        }
        final Control[] array2 = new Control[n2];
        System.arraycopy(array, 0, array2, 0, n2);
        return array2;
    }
    
    Control[] _getTabList() {
        if (this.tabList == null) {
            return this.tabList;
        }
        int n = 0;
        for (int i = 0; i < this.tabList.length; ++i) {
            if (!this.tabList[i].isDisposed()) {
                ++n;
            }
        }
        if (n == this.tabList.length) {
            return this.tabList;
        }
        final Control[] tabList = new Control[n];
        int n2 = 0;
        for (int j = 0; j < this.tabList.length; ++j) {
            if (!this.tabList[j].isDisposed()) {
                tabList[n2++] = this.tabList[j];
            }
        }
        return this.tabList = tabList;
    }
    
    public void changed(final Control[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(5);
        }
        for (int i = 0; i < array.length; ++i) {
            final Control control = array[i];
            if (control == null) {
                this.error(5);
            }
            if (control.isDisposed()) {
                this.error(5);
            }
            boolean b = false;
            for (Composite composite = control.parent; composite != null; composite = composite.parent) {
                b = (composite == this);
                if (b) {
                    break;
                }
            }
            if (!b) {
                this.error(32);
            }
        }
        for (int j = 0; j < array.length; ++j) {
            Control control2 = array[j];
            for (Composite composite2 = control2.parent; control2 != this; control2 = composite2, composite2 = control2.parent) {
                if (composite2.layout == null || !composite2.layout.flushCache(control2)) {
                    final Composite composite3 = composite2;
                    composite3.state |= 0x40;
                }
            }
        }
    }
    
    void checkBuffered() {
        if (OS.IsWinCE || (this.state & 0x2) == 0x0) {
            super.checkBuffered();
        }
    }
    
    void checkComposited() {
        if ((this.state & 0x2) != 0x0 && (this.style & 0x40000000) != 0x0) {
            final int handle = this.parent.handle;
            OS.SetWindowLong(handle, -20, OS.GetWindowLong(handle, -20) | 0x2000000);
        }
    }
    
    protected void checkSubclass() {
    }
    
    Widget[] computeTabList() {
        Widget[] computeTabList = super.computeTabList();
        if (computeTabList.length == 0) {
            return computeTabList;
        }
        final Control[] array = (this.tabList != null) ? this._getTabList() : this._getChildren();
        for (int i = 0; i < array.length; ++i) {
            final Widget[] computeTabList2 = array[i].computeTabList();
            if (computeTabList2.length != 0) {
                final Widget[] array2 = new Widget[computeTabList.length + computeTabList2.length];
                System.arraycopy(computeTabList, 0, array2, 0, computeTabList.length);
                System.arraycopy(computeTabList2, 0, array2, computeTabList.length, computeTabList2.length);
                computeTabList = array2;
            }
        }
        return computeTabList;
    }
    
    public Point computeSize(final int x, final int y, final boolean b) {
        this.checkWidget();
        this.display.runSkin();
        Point point;
        if (this.layout != null) {
            if (x == -1 || y == -1) {
                final boolean b2 = b | (this.state & 0x40) != 0x0;
                this.state &= 0xFFFFFFBF;
                point = this.layout.computeSize(this, x, y, b2);
            }
            else {
                point = new Point(x, y);
            }
        }
        else {
            point = this.minimumSize(x, y, b);
            if (point.x == 0) {
                point.x = 64;
            }
            if (point.y == 0) {
                point.y = 64;
            }
        }
        if (x != -1) {
            point.x = x;
        }
        if (y != -1) {
            point.y = y;
        }
        final Rectangle computeTrim = this.computeTrim(0, 0, point.x, point.y);
        return new Point(computeTrim.width, computeTrim.height);
    }
    
    void copyArea(final GC gc, int n, int n2, final int n3, final int n4) {
        this.checkWidget();
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        final int handle = gc.handle;
        final int saveDC = OS.SaveDC(handle);
        OS.IntersectClipRect(handle, 0, 0, n3, n4);
        final POINT point = new POINT();
        OS.MapWindowPoints(this.handle, OS.GetParent(this.handle), point, 1);
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final POINT point2 = new POINT();
        final POINT point3 = new POINT();
        n += point.x - rect.left;
        n2 += point.y - rect.top;
        OS.SetWindowOrgEx(handle, n, n2, point2);
        OS.SetBrushOrgEx(handle, n, n2, point3);
        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
        if ((getWindowLong & 0x10000000) == 0x0) {
            OS.DefWindowProc(this.handle, 11, 1, 0);
        }
        OS.RedrawWindow(this.handle, null, 0, 384);
        OS.PrintWindow(this.handle, handle, 0);
        if ((getWindowLong & 0x10000000) == 0x0) {
            OS.DefWindowProc(this.handle, 11, 0, 0);
        }
        OS.RestoreDC(handle, saveDC);
    }
    
    void createHandle() {
        super.createHandle();
        this.state |= 0x2;
        if ((this.style & 0x300) == 0x0) {
            this.state |= 0x100;
        }
        if ((this.style & 0x40000000) != 0x0) {
            OS.SetWindowLong(this.handle, -20, OS.GetWindowLong(this.handle, -20) | 0x20);
        }
    }
    
    public void drawBackground(final GC gc, final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.checkWidget();
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        this.drawBackground(gc.handle, rect, (this.background == -1) ? gc.getBackground().handle : -1, n5, n6);
    }
    
    Composite findDeferredControl() {
        return (this.layoutCount > 0) ? this : this.parent.findDeferredControl();
    }
    
    Menu[] findMenus(final Control control) {
        if (control == this) {
            return new Menu[0];
        }
        Menu[] menus = super.findMenus(control);
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            final Menu[] menus2 = getChildren[i].findMenus(control);
            if (menus2.length != 0) {
                final Menu[] array = new Menu[menus.length + menus2.length];
                System.arraycopy(menus, 0, array, 0, menus.length);
                System.arraycopy(menus2, 0, array, menus.length, menus2.length);
                menus = array;
            }
        }
        return menus;
    }
    
    void fixChildren(final Shell shell, final Shell shell2, final Decorations decorations, final Decorations decorations2, final Menu[] array) {
        super.fixChildren(shell, shell2, decorations, decorations2, array);
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            getChildren[i].fixChildren(shell, shell2, decorations, decorations2, array);
        }
    }
    
    void fixTabList(final Control control) {
        if (this.tabList == null) {
            return;
        }
        int n = 0;
        for (int i = 0; i < this.tabList.length; ++i) {
            if (this.tabList[i] == control) {
                ++n;
            }
        }
        if (n == 0) {
            return;
        }
        Control[] tabList = null;
        final int n2 = this.tabList.length - n;
        if (n2 != 0) {
            tabList = new Control[n2];
            int n3 = 0;
            for (int j = 0; j < this.tabList.length; ++j) {
                if (this.tabList[j] != control) {
                    tabList[n3++] = this.tabList[j];
                }
            }
        }
        this.tabList = tabList;
    }
    
    public int getBackgroundMode() {
        this.checkWidget();
        return this.backgroundMode;
    }
    
    public Control[] getChildren() {
        this.checkWidget();
        return this._getChildren();
    }
    
    int getChildrenCount() {
        int n = 0;
        for (int i = OS.GetWindow(this.handle, 5); i != 0; i = OS.GetWindow(i, 2)) {
            ++n;
        }
        return n;
    }
    
    public Layout getLayout() {
        this.checkWidget();
        return this.layout;
    }
    
    public Control[] getTabList() {
        this.checkWidget();
        Control[] getTabList = this._getTabList();
        if (getTabList == null) {
            int n = 0;
            final Control[] getChildren = this._getChildren();
            for (int i = 0; i < getChildren.length; ++i) {
                if (getChildren[i].isTabGroup()) {
                    ++n;
                }
            }
            getTabList = new Control[n];
            int n2 = 0;
            for (int j = 0; j < getChildren.length; ++j) {
                if (getChildren[j].isTabGroup()) {
                    getTabList[n2++] = getChildren[j];
                }
            }
        }
        return getTabList;
    }
    
    boolean hooksKeys() {
        return this.hooks(1) || this.hooks(2);
    }
    
    public boolean getLayoutDeferred() {
        this.checkWidget();
        return this.layoutCount > 0;
    }
    
    public boolean isLayoutDeferred() {
        this.checkWidget();
        return this.findDeferredControl() != null;
    }
    
    public void layout() {
        this.checkWidget();
        this.layout(true);
    }
    
    public void layout(final boolean b) {
        this.checkWidget();
        if (this.layout == null) {
            return;
        }
        this.layout(b, false);
    }
    
    public void layout(final boolean b, final boolean b2) {
        this.checkWidget();
        if (this.layout == null && !b2) {
            return;
        }
        this.markLayout(b, b2);
        this.updateLayout(b2);
    }
    
    public void layout(final Control[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(5);
        }
        this.layout(array, 0);
    }
    
    public void layout(final Control[] array, final int n) {
        this.checkWidget();
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                final Control control = array[i];
                if (control == null) {
                    this.error(5);
                }
                if (control.isDisposed()) {
                    this.error(5);
                }
                boolean b = false;
                for (Composite composite = control.parent; composite != null; composite = composite.parent) {
                    b = (composite == this);
                    if (b) {
                        break;
                    }
                }
                if (!b) {
                    this.error(32);
                }
            }
            int n2 = 0;
            Composite[] array2 = new Composite[16];
            for (int j = 0; j < array.length; ++j) {
                Control control2 = array[j];
                Composite composite2 = control2.parent;
                while (control2 != this) {
                    if (composite2.layout != null) {
                        final Composite composite3 = composite2;
                        composite3.state |= 0x20;
                        if (!composite2.layout.flushCache(control2)) {
                            final Composite composite4 = composite2;
                            composite4.state |= 0x40;
                        }
                    }
                    if (n2 == array2.length) {
                        final Composite[] array3 = new Composite[array2.length + 16];
                        System.arraycopy(array2, 0, array3, 0, array2.length);
                        array2 = array3;
                    }
                    final Composite[] array4 = array2;
                    final int n3 = n2++;
                    final Composite composite5 = composite2;
                    array4[n3] = composite5;
                    control2 = composite5;
                    composite2 = control2.parent;
                }
            }
            if ((n & 0x4) != 0x0) {
                this.setLayoutDeferred(true);
                this.display.addLayoutDeferred(this);
            }
            for (int k = n2 - 1; k >= 0; --k) {
                array2[k].updateLayout(false);
            }
        }
        else {
            if (this.layout == null && (n & 0x1) == 0x0) {
                return;
            }
            this.markLayout((n & 0x2) != 0x0, (n & 0x1) != 0x0);
            if ((n & 0x4) != 0x0) {
                this.setLayoutDeferred(true);
                this.display.addLayoutDeferred(this);
            }
            this.updateLayout((n & 0x1) != 0x0);
        }
    }
    
    void markLayout(final boolean b, final boolean b2) {
        if (this.layout != null) {
            this.state |= 0x20;
            if (b) {
                this.state |= 0x40;
            }
        }
        if (b2) {
            final Control[] getChildren = this._getChildren();
            for (int i = 0; i < getChildren.length; ++i) {
                getChildren[i].markLayout(b, b2);
            }
        }
    }
    
    Point minimumSize(final int n, final int n2, final boolean b) {
        final Control[] getChildren = this._getChildren();
        final Rectangle clientArea = this.getClientArea();
        int max = 0;
        int max2 = 0;
        for (int i = 0; i < getChildren.length; ++i) {
            final Rectangle bounds = getChildren[i].getBounds();
            max = Math.max(max, bounds.x - clientArea.x + bounds.width);
            max2 = Math.max(max2, bounds.y - clientArea.y + bounds.height);
        }
        return new Point(max, max2);
    }
    
    boolean redrawChildren() {
        if (!super.redrawChildren()) {
            return false;
        }
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            getChildren[i].redrawChildren();
        }
        return true;
    }
    
    void releaseParent() {
        super.releaseParent();
        if ((this.state & 0x2) != 0x0 && (this.style & 0x40000000) != 0x0) {
            final int handle = this.parent.handle;
            for (int i = OS.GetWindow(handle, 5); i != 0; i = OS.GetWindow(i, 2)) {
                if (i != this.handle && (OS.GetWindowLong(handle, -20) & 0x20) != 0x0) {
                    return;
                }
            }
            OS.SetWindowLong(handle, -20, OS.GetWindowLong(handle, -20) & 0xFDFFFFFF);
        }
    }
    
    void releaseChildren(final boolean b) {
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            final Control control = getChildren[i];
            if (control != null && !control.isDisposed()) {
                control.release(false);
            }
        }
        super.releaseChildren(b);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if ((this.state & 0x2) != 0x0 && (this.style & 0x1000000) != 0x0) {
            final int getWindow = OS.GetWindow(this.handle, 5);
            if (getWindow != 0 && OS.GetWindowThreadProcessId(getWindow, null) != OS.GetCurrentThreadId()) {
                OS.ShowWindow(getWindow, 0);
                OS.SetParent(getWindow, 0);
            }
        }
        this.layout = null;
        this.tabList = null;
        this.lpwp = null;
    }
    
    void removeControl(final Control control) {
        this.fixTabList(control);
        this.resizeChildren();
    }
    
    void reskinChildren(final int n) {
        super.reskinChildren(n);
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            final Control control = getChildren[i];
            if (control != null) {
                control.reskin(n);
            }
        }
    }
    
    void resizeChildren() {
        if (this.lpwp == null) {
            return;
        }
        do {
            final WINDOWPOS[] lpwp = this.lpwp;
            this.lpwp = null;
            if (!this.resizeChildren(true, lpwp)) {
                this.resizeChildren(false, lpwp);
            }
        } while (this.lpwp != null);
    }
    
    boolean resizeChildren(final boolean b, final WINDOWPOS[] array) {
        if (array == null) {
            return true;
        }
        int n = 0;
        if (b) {
            n = OS.BeginDeferWindowPos(array.length);
            if (n == 0) {
                return false;
            }
        }
        for (int i = 0; i < array.length; ++i) {
            final WINDOWPOS windowpos = array[i];
            if (windowpos != null) {
                if (b) {
                    n = this.DeferWindowPos(n, windowpos.hwnd, 0, windowpos.x, windowpos.y, windowpos.cx, windowpos.cy, windowpos.flags);
                    if (n == 0) {
                        return false;
                    }
                }
                else {
                    this.SetWindowPos(windowpos.hwnd, 0, windowpos.x, windowpos.y, windowpos.cx, windowpos.cy, windowpos.flags);
                }
            }
        }
        return !b || OS.EndDeferWindowPos(n);
    }
    
    void resizeEmbeddedHandle(final int n, final int n2, final int n3) {
        if (n == 0) {
            return;
        }
        final int[] array = { 0 };
        final int getWindowThreadProcessId = OS.GetWindowThreadProcessId(n, array);
        if (getWindowThreadProcessId != OS.GetCurrentThreadId()) {
            if (array[0] == OS.GetCurrentProcessId() && this.display.msgHook == 0 && !OS.IsWinCE) {
                this.display.getMsgCallback = new Callback(this.display, "getMsgProc", 3);
                this.display.getMsgProc = this.display.getMsgCallback.getAddress();
                if (this.display.getMsgProc == 0) {
                    this.error(3);
                }
                this.display.msgHook = OS.SetWindowsHookEx(3, this.display.getMsgProc, OS.GetLibraryHandle(), getWindowThreadProcessId);
                OS.PostThreadMessage(getWindowThreadProcessId, 0, 0, 0);
            }
            OS.SetWindowPos(n, 0, 0, 0, n2, n3, 16436);
        }
    }
    
    void sendResize() {
        this.setResizeChildren(false);
        super.sendResize();
        if (this.isDisposed()) {
            return;
        }
        if (this.layout != null) {
            this.markLayout(false, false);
            this.updateLayout(false, false);
        }
        this.setResizeChildren(true);
    }
    
    public void setBackgroundMode(final int backgroundMode) {
        this.checkWidget();
        this.backgroundMode = backgroundMode;
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            getChildren[i].updateBackgroundMode();
        }
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, final int n5, boolean b) {
        if (this.display.resizeCount > 4) {
            b = false;
        }
        if (!b && (this.state & 0x2) != 0x0) {
            this.state &= 0xFFFAFFFF;
            this.state |= 0xA0000;
        }
        super.setBounds(n, n2, n3, n4, n5, b);
        if (!b && (this.state & 0x2) != 0x0) {
            final boolean b2 = (this.state & 0x10000) != 0x0;
            final boolean b3 = (this.state & 0x40000) != 0x0;
            this.state &= 0xFFF5FFFF;
            if (b2 && !this.isDisposed()) {
                this.sendMove();
            }
            if (b3 && !this.isDisposed()) {
                this.sendResize();
            }
        }
    }
    
    public boolean setFocus() {
        this.checkWidget();
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            if (getChildren[i].setRadioFocus(false)) {
                return true;
            }
        }
        for (int j = 0; j < getChildren.length; ++j) {
            if (getChildren[j].setFocus()) {
                return true;
            }
        }
        return super.setFocus();
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
        this.layout = layout;
    }
    
    public void setLayoutDeferred(final boolean b) {
        this.checkWidget();
        if (!b) {
            final int layoutCount = this.layoutCount - 1;
            this.layoutCount = layoutCount;
            if (layoutCount == 0 && ((this.state & 0x80) != 0x0 || (this.state & 0x20) != 0x0)) {
                this.updateLayout(true);
            }
        }
        else {
            ++this.layoutCount;
        }
    }
    
    public void setTabList(Control[] tabList) {
        this.checkWidget();
        if (tabList != null) {
            for (int i = 0; i < tabList.length; ++i) {
                final Control control = tabList[i];
                if (control == null) {
                    this.error(5);
                }
                if (control.isDisposed()) {
                    this.error(5);
                }
                if (control.parent != this) {
                    this.error(32);
                }
            }
            final Control[] array = new Control[tabList.length];
            System.arraycopy(tabList, 0, array, 0, tabList.length);
            tabList = array;
        }
        this.tabList = tabList;
    }
    
    void setResizeChildren(final boolean b) {
        if (b) {
            this.resizeChildren();
        }
        else {
            if (this.display.resizeCount > 4) {
                return;
            }
            final int childrenCount = this.getChildrenCount();
            if (childrenCount > 1 && this.lpwp == null) {
                this.lpwp = new WINDOWPOS[childrenCount];
            }
        }
    }
    
    boolean setTabGroupFocus() {
        if (this.isTabItem()) {
            return this.setTabItemFocus();
        }
        boolean hooksKeys = (this.style & 0x80000) == 0x0;
        if ((this.state & 0x2) != 0x0) {
            hooksKeys = this.hooksKeys();
            if ((this.style & 0x1000000) != 0x0) {
                hooksKeys = true;
            }
        }
        if (hooksKeys && this.setTabItemFocus()) {
            return true;
        }
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            final Control control = getChildren[i];
            if (control.isTabItem() && control.setRadioFocus(true)) {
                return true;
            }
        }
        for (int j = 0; j < getChildren.length; ++j) {
            final Control control2 = getChildren[j];
            if (control2.isTabItem() && !control2.isTabGroup() && control2.setTabItemFocus()) {
                return true;
            }
        }
        return false;
    }
    
    String toolTipText(final NMTTDISPINFO nmttdispinfo) {
        final Shell shell = this.getShell();
        if ((nmttdispinfo.uFlags & 0x1) == 0x0) {
            String message = null;
            final ToolTip toolTip = shell.findToolTip(nmttdispinfo.idFrom);
            if (toolTip != null) {
                message = toolTip.message;
                if (message == null || message.length() == 0) {
                    message = " ";
                }
            }
            return message;
        }
        shell.setToolTipTitle(nmttdispinfo.hwndFrom, null, 0);
        OS.SendMessage(nmttdispinfo.hwndFrom, 1048, 0, 32767);
        final Control control = this.display.getControl(nmttdispinfo.idFrom);
        return (control != null) ? control.toolTipText : null;
    }
    
    boolean translateMnemonic(final Event event, final Control control) {
        if (super.translateMnemonic(event, control)) {
            return true;
        }
        if (control != null) {
            final Control[] getChildren = this._getChildren();
            for (int i = 0; i < getChildren.length; ++i) {
                if (getChildren[i].translateMnemonic(event, control)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    boolean translateTraversal(final MSG msg) {
        if ((this.state & 0x2) != 0x0) {
            if ((this.style & 0x1000000) != 0x0) {
                return false;
            }
            switch (msg.wParam) {
                case 33:
                case 34:
                case 37:
                case 38:
                case 39:
                case 40: {
                    if ((OS.SendMessage(msg.hwnd, 297, 0, 0) & 0x1) != 0x0) {
                        OS.SendMessage(msg.hwnd, 296, OS.MAKEWPARAM(2, 1), 0);
                        break;
                    }
                    break;
                }
            }
        }
        return super.translateTraversal(msg);
    }
    
    void updateBackgroundColor() {
        super.updateBackgroundColor();
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            if ((getChildren[i].state & 0x400) != 0x0) {
                getChildren[i].updateBackgroundColor();
            }
        }
    }
    
    void updateBackgroundImage() {
        super.updateBackgroundImage();
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            if ((getChildren[i].state & 0x400) != 0x0) {
                getChildren[i].updateBackgroundImage();
            }
        }
    }
    
    void updateBackgroundMode() {
        super.updateBackgroundMode();
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            getChildren[i].updateBackgroundMode();
        }
    }
    
    void updateFont(final Font font, final Font font2) {
        super.updateFont(font, font2);
        final Control[] getChildren = this._getChildren();
        for (int i = 0; i < getChildren.length; ++i) {
            final Control control = getChildren[i];
            if (!control.isDisposed()) {
                control.updateFont(font, font2);
            }
        }
    }
    
    void updateLayout(final boolean b) {
        this.updateLayout(true, b);
    }
    
    void updateLayout(final boolean b, final boolean b2) {
        final Composite deferredControl = this.findDeferredControl();
        if (deferredControl != null) {
            final Composite composite = deferredControl;
            composite.state |= 0x80;
            return;
        }
        if ((this.state & 0x20) != 0x0) {
            final boolean b3 = (this.state & 0x40) != 0x0;
            this.state &= 0xFFFFFF9F;
            this.display.runSkin();
            if (b) {
                this.setResizeChildren(false);
            }
            this.layout.layout(this, b3);
            if (b) {
                this.setResizeChildren(true);
            }
        }
        if (b2) {
            this.state &= 0xFFFFFF7F;
            final Control[] getChildren = this._getChildren();
            for (int i = 0; i < getChildren.length; ++i) {
                getChildren[i].updateLayout(b, b2);
            }
        }
    }
    
    void updateOrientation() {
        final Control[] getChildren = this._getChildren();
        final RECT[] array = new RECT[getChildren.length];
        for (int i = 0; i < getChildren.length; ++i) {
            final Control control = getChildren[i];
            final RECT[] array2 = array;
            final int n = i;
            final RECT rect = new RECT();
            array2[n] = rect;
            final RECT rect2 = rect;
            control.forceResize();
            OS.GetWindowRect(control.topHandle(), rect2);
            OS.MapWindowPoints(0, this.handle, rect2, 2);
        }
        final int orientation = this.style & 0x6000000;
        super.updateOrientation();
        for (int j = 0; j < getChildren.length; ++j) {
            final Control control2 = getChildren[j];
            final RECT rect3 = array[j];
            control2.setOrientation(orientation);
            this.SetWindowPos(control2.topHandle(), 0, rect3.left, rect3.top, 0, 0, 21);
        }
    }
    
    void updateUIState() {
        final int handle = this.getShell().handle;
        if ((OS.SendMessage(handle, 297, 0, 0) & 0x1) != 0x0) {
            OS.SendMessage(handle, 295, OS.MAKEWPARAM(2, 1), 0);
        }
    }
    
    int widgetStyle() {
        return super.widgetStyle() | 0x2000000;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if (wm_ERASEBKGND != null) {
            return wm_ERASEBKGND;
        }
        if ((this.state & 0x2) != 0x0 && (this.style & 0x40040000) != 0x0) {
            return LRESULT.ZERO;
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        final LRESULT wm_GETDLGCODE = super.WM_GETDLGCODE(n, n2);
        if (wm_GETDLGCODE != null) {
            return wm_GETDLGCODE;
        }
        if ((this.state & 0x2) != 0x0) {
            int n3 = 0;
            if (this.hooksKeys()) {
                n3 |= 0x7;
            }
            if ((this.style & 0x80000) != 0x0) {
                n3 |= 0x100;
            }
            if (OS.GetWindow(this.handle, 5) != 0) {
                n3 |= 0x100;
            }
            if (n3 != 0) {
                return new LRESULT(n3);
            }
        }
        return wm_GETDLGCODE;
    }
    
    LRESULT WM_GETFONT(final int n, final int n2) {
        final LRESULT wm_GETFONT = super.WM_GETFONT(n, n2);
        if (wm_GETFONT != null) {
            return wm_GETFONT;
        }
        final int callWindowProc = this.callWindowProc(this.handle, 49, n, n2);
        if (callWindowProc != 0) {
            return new LRESULT(callWindowProc);
        }
        return new LRESULT((this.font != null) ? this.font.handle : this.defaultFont());
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final LRESULT wm_LBUTTONDOWN = super.WM_LBUTTONDOWN(n, n2);
        if (wm_LBUTTONDOWN == LRESULT.ZERO) {
            return wm_LBUTTONDOWN;
        }
        if ((this.state & 0x2) != 0x0 && (this.style & 0x80000) == 0x0 && this.hooksKeys() && OS.GetWindow(this.handle, 5) == 0) {
            this.setFocus();
        }
        return wm_LBUTTONDOWN;
    }
    
    LRESULT WM_NCHITTEST(final int n, final int n2) {
        final LRESULT wm_NCHITTEST = super.WM_NCHITTEST(n, n2);
        if (wm_NCHITTEST != null) {
            return wm_NCHITTEST;
        }
        if (!OS.IsWinCE && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && (this.state & 0x2) != 0x0) {
            final int callWindowProc = this.callWindowProc(this.handle, 132, n, n2);
            if (callWindowProc == 1) {
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                final POINT point = new POINT();
                point.x = OS.GET_X_LPARAM(n2);
                point.y = OS.GET_Y_LPARAM(n2);
                OS.MapWindowPoints(0, this.handle, point, 1);
                if (!OS.PtInRect(rect, point)) {
                    OS.RedrawWindow(this.handle, null, 0, 1025);
                }
            }
            return new LRESULT(callWindowProc);
        }
        return wm_NCHITTEST;
    }
    
    LRESULT WM_PARENTNOTIFY(final int n, final int n2) {
        if ((this.state & 0x2) != 0x0 && (this.style & 0x1000000) != 0x0 && OS.LOWORD(n) == 1) {
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            this.resizeEmbeddedHandle(n2, rect.right - rect.left, rect.bottom - rect.top);
        }
        return super.WM_PARENTNOTIFY(n, n2);
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        if ((this.state & 0x2) == 0x0 || (this.state & 0x4000) != 0x0) {
            return super.WM_PAINT(n, n2);
        }
        int getWindowLong = 0;
        int n3 = 0;
        if (!OS.IsWinCE) {
            getWindowLong = OS.GetWindowLong(this.handle, -16);
            n3 = (getWindowLong | 0x4000000 | 0x2000000);
            if (n3 != getWindowLong) {
                OS.SetWindowLong(this.handle, -16, n3);
            }
        }
        final PAINTSTRUCT ps = new PAINTSTRUCT();
        if (this.hooks(9) || this.filters(9)) {
            boolean b = false;
            if ((this.style & 0x20000000) != 0x0 && !OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && (this.style & 0x4200000) == 0x0 && (this.style & 0x40000000) == 0x0) {
                b = true;
            }
            if (b) {
                final int beginPaint = OS.BeginPaint(this.handle, ps);
                final int width = ps.right - ps.left;
                final int height = ps.bottom - ps.top;
                if (width != 0 && height != 0) {
                    final int[] array = { 0 };
                    final int n4 = 0;
                    final RECT rect = new RECT();
                    OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                    final int beginBufferedPaint = OS.BeginBufferedPaint(beginPaint, rect, n4, null, array);
                    final GCData gcData = new GCData();
                    gcData.device = this.display;
                    gcData.foreground = this.getForegroundPixel();
                    Control backgroundControl = this.findBackgroundControl();
                    if (backgroundControl == null) {
                        backgroundControl = this;
                    }
                    gcData.background = backgroundControl.getBackgroundPixel();
                    gcData.font = Font.win32_new(this.display, OS.SendMessage(this.handle, 49, 0, 0));
                    gcData.uiState = OS.SendMessage(this.handle, 297, 0, 0);
                    if ((this.style & 0x40000) == 0x0) {
                        final RECT rect2 = new RECT();
                        OS.SetRect(rect2, ps.left, ps.top, ps.right, ps.bottom);
                        this.drawBackground(array[0], rect2);
                    }
                    final GC win32_new = GC.win32_new(array[0], gcData);
                    final Event event = new Event();
                    event.gc = win32_new;
                    event.x = ps.left;
                    event.y = ps.top;
                    event.width = width;
                    event.height = height;
                    this.sendEvent(9, event);
                    if (gcData.focusDrawn && !this.isDisposed()) {
                        this.updateUIState();
                    }
                    win32_new.dispose();
                    OS.EndBufferedPaint(beginBufferedPaint, true);
                }
                OS.EndPaint(this.handle, ps);
            }
            else {
                final GCData gcData2 = new GCData();
                gcData2.ps = ps;
                gcData2.hwnd = this.handle;
                GC win32_new2 = GC.win32_new(this, gcData2);
                int createRectRgn = 0;
                if ((this.style & 0x60000000) != 0x0 || (this.style & 0x200000) != 0x0) {
                    createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                    if (OS.GetRandomRgn(win32_new2.handle, createRectRgn, 4) == 1) {
                        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10) && (OS.GetLayout(win32_new2.handle) & 0x1) != 0x0) {
                            final int getRegionData = OS.GetRegionData(createRectRgn, 0, null);
                            final int[] array2 = new int[getRegionData / 4];
                            OS.GetRegionData(createRectRgn, getRegionData, array2);
                            final int extCreateRegion = OS.ExtCreateRegion(new float[] { -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f }, getRegionData, array2);
                            OS.DeleteObject(createRectRgn);
                            createRectRgn = extCreateRegion;
                        }
                        if (OS.IsWinNT) {
                            final POINT point = new POINT();
                            OS.MapWindowPoints(0, this.handle, point, 1);
                            OS.OffsetRgn(createRectRgn, point.x, point.y);
                        }
                    }
                }
                final int width2 = ps.right - ps.left;
                final int height2 = ps.bottom - ps.top;
                if (width2 != 0 && height2 != 0) {
                    GC gc = null;
                    Image image = null;
                    if ((this.style & 0x60000000) != 0x0) {
                        image = new Image(this.display, width2, height2);
                        gc = win32_new2;
                        win32_new2 = new GC(image, gc.getStyle() & 0x4000000);
                        win32_new2.getGCData().uiState = gcData2.uiState;
                        win32_new2.setForeground(this.getForeground());
                        win32_new2.setBackground(this.getBackground());
                        win32_new2.setFont(this.getFont());
                        if ((this.style & 0x40000000) != 0x0) {
                            OS.BitBlt(win32_new2.handle, 0, 0, width2, height2, gc.handle, ps.left, ps.top, 13369376);
                        }
                        OS.OffsetRgn(createRectRgn, -ps.left, -ps.top);
                        OS.SelectClipRgn(win32_new2.handle, createRectRgn);
                        OS.OffsetRgn(createRectRgn, ps.left, ps.top);
                        OS.SetMetaRgn(win32_new2.handle);
                        OS.SetWindowOrgEx(win32_new2.handle, ps.left, ps.top, null);
                        OS.SetBrushOrgEx(win32_new2.handle, ps.left, ps.top, null);
                        if ((this.style & 0x40040000) == 0x0) {
                            final RECT rect3 = new RECT();
                            OS.SetRect(rect3, ps.left, ps.top, ps.right, ps.bottom);
                            this.drawBackground(win32_new2.handle, rect3);
                        }
                    }
                    final Event event2 = new Event();
                    event2.gc = win32_new2;
                    RECT rect4 = null;
                    if ((this.style & 0x200000) != 0x0 && OS.GetRgnBox(createRectRgn, rect4 = new RECT()) == 3) {
                        final int getRegionData2 = OS.GetRegionData(createRectRgn, 0, null);
                        final int[] array3 = new int[getRegionData2 / 4];
                        OS.GetRegionData(createRectRgn, getRegionData2, array3);
                        for (int n5 = array3[2], i = 0; i < n5; ++i) {
                            final int n6 = 8 + (i << 2);
                            OS.SetRect(rect4, array3[n6], array3[n6 + 1], array3[n6 + 2], array3[n6 + 3]);
                            if ((this.style & 0x60040000) == 0x0) {
                                this.drawBackground(win32_new2.handle, rect4);
                            }
                            event2.x = rect4.left;
                            event2.y = rect4.top;
                            event2.width = rect4.right - rect4.left;
                            event2.height = rect4.bottom - rect4.top;
                            event2.count = n5 - 1 - i;
                            this.sendEvent(9, event2);
                        }
                    }
                    else {
                        if ((this.style & 0x60040000) == 0x0) {
                            if (rect4 == null) {
                                rect4 = new RECT();
                            }
                            OS.SetRect(rect4, ps.left, ps.top, ps.right, ps.bottom);
                            this.drawBackground(win32_new2.handle, rect4);
                        }
                        event2.x = ps.left;
                        event2.y = ps.top;
                        event2.width = width2;
                        event2.height = height2;
                        this.sendEvent(9, event2);
                    }
                    event2.gc = null;
                    if ((this.style & 0x60000000) != 0x0) {
                        if (!win32_new2.isDisposed() && win32_new2.getGCData().focusDrawn && !this.isDisposed()) {
                            this.updateUIState();
                        }
                        win32_new2.dispose();
                        if (!this.isDisposed()) {
                            gc.drawImage(image, ps.left, ps.top);
                        }
                        image.dispose();
                        win32_new2 = gc;
                    }
                }
                if (createRectRgn != 0) {
                    OS.DeleteObject(createRectRgn);
                }
                if (gcData2.focusDrawn && !this.isDisposed()) {
                    this.updateUIState();
                }
                win32_new2.dispose();
            }
        }
        else {
            final int beginPaint2 = OS.BeginPaint(this.handle, ps);
            if ((this.style & 0x40040000) == 0x0) {
                final RECT rect5 = new RECT();
                OS.SetRect(rect5, ps.left, ps.top, ps.right, ps.bottom);
                this.drawBackground(beginPaint2, rect5);
            }
            OS.EndPaint(this.handle, ps);
        }
        if (!OS.IsWinCE && !this.isDisposed() && n3 != getWindowLong && !this.isDisposed()) {
            OS.SetWindowLong(this.handle, -16, getWindowLong);
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PRINTCLIENT(final int n, final int n2) {
        final LRESULT wm_PRINTCLIENT = super.WM_PRINTCLIENT(n, n2);
        if (wm_PRINTCLIENT != null) {
            return wm_PRINTCLIENT;
        }
        if ((this.state & 0x2) != 0x0) {
            this.forceResize();
            final int saveDC = OS.SaveDC(n);
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            if ((this.style & 0x40040000) == 0x0) {
                this.drawBackground(n, rect);
            }
            if (this.hooks(9) || this.filters(9)) {
                final GCData gcData = new GCData();
                gcData.device = this.display;
                gcData.foreground = this.getForegroundPixel();
                Control backgroundControl = this.findBackgroundControl();
                if (backgroundControl == null) {
                    backgroundControl = this;
                }
                gcData.background = backgroundControl.getBackgroundPixel();
                gcData.font = Font.win32_new(this.display, OS.SendMessage(this.handle, 49, 0, 0));
                gcData.uiState = OS.SendMessage(this.handle, 297, 0, 0);
                final GC win32_new = GC.win32_new(n, gcData);
                final Event event = new Event();
                event.gc = win32_new;
                event.x = rect.left;
                event.y = rect.top;
                event.width = rect.right - rect.left;
                event.height = rect.bottom - rect.top;
                this.sendEvent(9, event);
                event.gc = null;
                win32_new.dispose();
            }
            OS.RestoreDC(n, saveDC);
        }
        return wm_PRINTCLIENT;
    }
    
    LRESULT WM_SETFONT(final int n, final int n2) {
        if (n2 != 0) {
            OS.InvalidateRect(this.handle, null, true);
        }
        return super.WM_SETFONT(n, n2);
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        LRESULT lresult;
        if ((this.state & 0x80000) != 0x0) {
            lresult = super.WM_SIZE(n, n2);
        }
        else {
            this.setResizeChildren(false);
            lresult = super.WM_SIZE(n, n2);
            if (this.isDisposed()) {
                return lresult;
            }
            if (this.layout != null) {
                this.markLayout(false, false);
                this.updateLayout(false, false);
            }
            this.setResizeChildren(true);
        }
        if (OS.IsWindowVisible(this.handle)) {
            if ((this.state & 0x2) != 0x0 && (this.style & 0x100000) == 0x0 && this.hooks(9)) {
                OS.InvalidateRect(this.handle, null, true);
            }
            if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.findThemeControl() != null) {
                this.redrawChildren();
            }
        }
        if ((this.state & 0x2) != 0x0 && (this.style & 0x1000000) != 0x0) {
            this.resizeEmbeddedHandle(OS.GetWindow(this.handle, 5), OS.LOWORD(n2), OS.HIWORD(n2));
        }
        return lresult;
    }
    
    LRESULT WM_SYSCOLORCHANGE(final int n, final int n2) {
        final LRESULT wm_SYSCOLORCHANGE = super.WM_SYSCOLORCHANGE(n, n2);
        if (wm_SYSCOLORCHANGE != null) {
            return wm_SYSCOLORCHANGE;
        }
        for (int i = OS.GetWindow(this.handle, 5); i != 0; i = OS.GetWindow(i, 2)) {
            OS.SendMessage(i, 21, 0, 0);
        }
        return wm_SYSCOLORCHANGE;
    }
    
    LRESULT WM_SYSCOMMAND(final int n, final int n2) {
        final LRESULT wm_SYSCOMMAND = super.WM_SYSCOMMAND(n, n2);
        if (wm_SYSCOMMAND != null) {
            return wm_SYSCOMMAND;
        }
        if ((n & 0xF000) == 0x0) {
            return wm_SYSCOMMAND;
        }
        if (!OS.IsWinCE) {
            switch (n & 0xFFF0) {
                case 61552:
                case 61568: {
                    final boolean b = this.horizontalBar != null && this.horizontalBar.getVisible();
                    final boolean b2 = this.verticalBar != null && this.verticalBar.getVisible();
                    final int callWindowProc = this.callWindowProc(this.handle, 274, n, n2);
                    if (b != (this.horizontalBar != null && this.horizontalBar.getVisible()) || b2 != (this.verticalBar != null && this.verticalBar.getVisible())) {
                        OS.RedrawWindow(this.handle, null, 0, 1281);
                    }
                    if (callWindowProc == 0) {
                        return LRESULT.ZERO;
                    }
                    return new LRESULT(callWindowProc);
                }
            }
        }
        return wm_SYSCOMMAND;
    }
    
    LRESULT WM_UPDATEUISTATE(final int n, final int n2) {
        final LRESULT wm_UPDATEUISTATE = super.WM_UPDATEUISTATE(n, n2);
        if (wm_UPDATEUISTATE != null) {
            return wm_UPDATEUISTATE;
        }
        if ((this.state & 0x2) != 0x0 && this.hooks(9)) {
            OS.InvalidateRect(this.handle, null, true);
        }
        return wm_UPDATEUISTATE;
    }
    
    LRESULT wmNCPaint(final int n, final int n2, final int n3) {
        final LRESULT wmNCPaint = super.wmNCPaint(n, n2, n3);
        if (wmNCPaint != null) {
            return wmNCPaint;
        }
        final int borderHandle = this.borderHandle();
        if (((this.state & 0x2) != 0x0 || (n == borderHandle && this.handle != borderHandle)) && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && (OS.GetWindowLong(n, -20) & 0x200) != 0x0) {
            int callWindowProc = 0;
            if ((OS.GetWindowLong(n, -16) & 0x300000) != 0x0) {
                callWindowProc = this.callWindowProc(n, 133, n2, n3);
            }
            final int getWindowDC = OS.GetWindowDC(n);
            final RECT rect = new RECT();
            OS.GetWindowRect(n, rect);
            final RECT rect2 = rect;
            rect2.right -= rect.left;
            final RECT rect3 = rect;
            rect3.bottom -= rect.top;
            final RECT rect4 = rect;
            final RECT rect5 = rect;
            final boolean b = false;
            rect5.top = (b ? 1 : 0);
            rect4.left = (b ? 1 : 0);
            final int getSystemMetrics = OS.GetSystemMetrics(45);
            OS.ExcludeClipRect(getWindowDC, getSystemMetrics, getSystemMetrics, rect.right - getSystemMetrics, rect.bottom - getSystemMetrics);
            OS.DrawThemeBackground(this.display.hEditTheme(), getWindowDC, 1, 1, rect, null);
            OS.ReleaseDC(n, getWindowDC);
            return new LRESULT(callWindowProc);
        }
        return wmNCPaint;
    }
    
    LRESULT wmNotify(final NMHDR nmhdr, final int n, final int n2) {
        if (!OS.IsWinCE) {
            switch (nmhdr.code) {
                case -522:
                case -521: {
                    int n3 = nmhdr.hwndFrom;
                    do {
                        n3 = OS.GetParent(n3);
                        if (n3 == 0) {
                            break;
                        }
                    } while ((OS.GetWindowLong(n3, -20) & 0x8) == 0x0);
                    if (n3 != 0) {
                        break;
                    }
                    this.display.lockActiveWindow = true;
                    this.SetWindowPos(nmhdr.hwndFrom, (nmhdr.code == -521) ? -1 : -2, 0, 0, 0, 0, 19);
                    this.display.lockActiveWindow = false;
                    break;
                }
                case -530:
                case -520: {
                    NMTTDISPINFO nmttdispinfo;
                    if (nmhdr.code == -520) {
                        nmttdispinfo = new NMTTDISPINFOA();
                        OS.MoveMemory((NMTTDISPINFOA)nmttdispinfo, n2, NMTTDISPINFOA.sizeof);
                    }
                    else {
                        nmttdispinfo = new NMTTDISPINFOW();
                        OS.MoveMemory((NMTTDISPINFOW)nmttdispinfo, n2, NMTTDISPINFOW.sizeof);
                    }
                    final String toolTipText = this.toolTipText(nmttdispinfo);
                    if (toolTipText != null) {
                        final Shell shell = this.getShell();
                        final char[] fixMnemonic = this.fixMnemonic(Display.withCrLf(toolTipText));
                        Widget widget = null;
                        final int idFrom = nmhdr.idFrom;
                        if ((nmttdispinfo.uFlags & 0x1) != 0x0) {
                            widget = this.display.getControl(idFrom);
                        }
                        else if (nmhdr.hwndFrom == shell.toolTipHandle || nmhdr.hwndFrom == shell.balloonTipHandle) {
                            widget = shell.findToolTip(nmhdr.idFrom);
                        }
                        if (widget != null) {
                            if ((widget.getStyle() & 0x4000000) != 0x0) {
                                final NMTTDISPINFOW nmttdispinfow = (NMTTDISPINFOW)nmttdispinfo;
                                nmttdispinfow.uFlags |= 0x4;
                            }
                            else {
                                final NMTTDISPINFOW nmttdispinfow2 = (NMTTDISPINFOW)nmttdispinfo;
                                nmttdispinfow2.uFlags &= 0xFFFFFFFB;
                            }
                        }
                        if (nmhdr.code == -520) {
                            final byte[] array = new byte[fixMnemonic.length * 2];
                            OS.WideCharToMultiByte(this.getCodePage(), 0, fixMnemonic, fixMnemonic.length, array, array.length, null, null);
                            shell.setToolTipText(nmttdispinfo, array);
                            OS.MoveMemory(n2, (NMTTDISPINFOA)nmttdispinfo, NMTTDISPINFOA.sizeof);
                        }
                        else {
                            shell.setToolTipText(nmttdispinfo, fixMnemonic);
                            OS.MoveMemory(n2, (NMTTDISPINFOW)nmttdispinfo, NMTTDISPINFOW.sizeof);
                        }
                        return LRESULT.ZERO;
                    }
                    break;
                }
            }
        }
        return super.wmNotify(nmhdr, n, n2);
    }
}
