// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.SIPINFO;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.MINMAXINFO;
import org.eclipse.swt.internal.win32.NMTTDISPINFO;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.win32.STARTUPINFO;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.internal.win32.LOGBRUSH;
import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.SHACTIVATEINFO;

public class Shell extends Decorations
{
    Menu activeMenu;
    ToolTip[] toolTips;
    int hIMC;
    int hwndMDIClient;
    int lpstrTip;
    int toolTipHandle;
    int balloonTipHandle;
    int minWidth;
    int minHeight;
    int[] brushes;
    boolean showWithParent;
    boolean fullScreen;
    boolean wasMaximized;
    boolean modified;
    boolean center;
    String toolTitle;
    String balloonTitle;
    int toolIcon;
    int balloonIcon;
    int windowProc;
    Control lastActive;
    SHACTIVATEINFO psai;
    static int ToolTipProc;
    static final int DialogProc;
    static final TCHAR DialogClass;
    static final int[] SYSTEM_COLORS;
    static final int BRUSHES_SIZE = 32;
    
    static {
        DialogClass = new TCHAR(0, OS.IsWinCE ? "Dialog" : "#32770", true);
        SYSTEM_COLORS = new int[] { OS.COLOR_BTNFACE, OS.COLOR_WINDOW, OS.COLOR_BTNTEXT, OS.COLOR_WINDOWTEXT, OS.COLOR_HIGHLIGHT, OS.COLOR_SCROLLBAR };
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Shell.DialogClass, wndclass);
        DialogProc = wndclass.lpfnWndProc;
    }
    
    public Shell() {
        this((Display)null);
    }
    
    public Shell(final int n) {
        this((Display)null, n);
    }
    
    public Shell(final Display display) {
        this(display, OS.IsWinCE ? 0 : 1264);
    }
    
    public Shell(final Display display, final int n) {
        this(display, null, n, 0, false);
    }
    
    Shell(Display display, final Shell parent, final int n, final int handle, final boolean b) {
        this.minWidth = -1;
        this.minHeight = -1;
        this.checkSubclass();
        if (display == null) {
            display = Display.getCurrent();
        }
        if (display == null) {
            display = Display.getDefault();
        }
        if (!display.isValidThread()) {
            this.error(22);
        }
        if (parent != null && parent.isDisposed()) {
            this.error(5);
        }
        this.center = (parent != null && (n & 0x10000000) != 0x0);
        this.style = checkStyle(parent, n);
        this.parent = parent;
        this.display = display;
        this.handle = handle;
        if (handle != 0 && !b) {
            this.state |= 0x4000;
        }
        this.reskinWidget();
        this.createWidget();
    }
    
    public Shell(final Shell shell) {
        this(shell, OS.IsWinCE ? 0 : 2144);
    }
    
    public Shell(final Shell shell, final int n) {
        this((shell != null) ? shell.display : null, shell, n, 0, false);
    }
    
    public static Shell win32_new(final Display display, final int n) {
        return new Shell(display, null, 8, n, true);
    }
    
    public static Shell internal_new(final Display display, final int n) {
        return new Shell(display, null, 8, n, false);
    }
    
    static int checkStyle(final Shell shell, int checkStyle) {
        checkStyle = Decorations.checkStyle(checkStyle);
        checkStyle &= 0xBFFFFFFF;
        final int n = 229376;
        if ((checkStyle & 0x10000000) != 0x0) {
            checkStyle &= 0xEFFFFFFF;
            checkStyle |= ((shell == null) ? 1264 : 2144);
            if ((checkStyle & n) == 0x0) {
                checkStyle |= ((shell == null) ? 65536 : 32768);
            }
        }
        final int n2 = checkStyle & ~n;
        if ((checkStyle & 0x20000) != 0x0) {
            return n2 | 0x20000;
        }
        if ((checkStyle & 0x10000) != 0x0) {
            return n2 | 0x10000;
        }
        if ((checkStyle & 0x8000) != 0x0) {
            return n2 | 0x8000;
        }
        return n2;
    }
    
    public void addShellListener(final ShellListener shellListener) {
        this.checkWidget();
        if (shellListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(shellListener);
        this.addListener(21, typedListener);
        this.addListener(19, typedListener);
        this.addListener(20, typedListener);
        this.addListener(26, typedListener);
        this.addListener(27, typedListener);
    }
    
    int balloonTipHandle() {
        if (this.balloonTipHandle == 0) {
            this.createBalloonTipHandle();
        }
        return this.balloonTipHandle;
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (n == this.toolTipHandle || n == this.balloonTipHandle) {
            return OS.CallWindowProc(Shell.ToolTipProc, n, n2, n3, n4);
        }
        if (this.hwndMDIClient != 0) {
            return OS.DefFrameProc(n, this.hwndMDIClient, n2, n3, n4);
        }
        if (this.windowProc != 0) {
            return OS.CallWindowProc(this.windowProc, n, n2, n3, n4);
        }
        if ((this.style & 0x4) != 0x0 && (this.style & 0xCF0) == 0x0) {
            return OS.DefWindowProc(n, n2, n3, n4);
        }
        if (this.parent == null) {
            return OS.DefWindowProc(n, n2, n3, n4);
        }
        switch (n2) {
            case 7:
            case 8: {
                return OS.DefWindowProc(n, n2, n3, n4);
            }
            default: {
                return OS.CallWindowProc(Shell.DialogProc, n, n2, n3, n4);
            }
        }
    }
    
    void center() {
        if (this.parent == null) {
            return;
        }
        final Rectangle bounds = this.getBounds();
        final Rectangle map = this.display.map(this.parent, null, this.parent.getClientArea());
        final int max = Math.max(map.x, map.x + (map.width - bounds.width) / 2);
        final int max2 = Math.max(map.y, map.y + (map.height - bounds.height) / 2);
        final Rectangle clientArea = this.parent.getMonitor().getClientArea();
        int n;
        if (max + bounds.width > clientArea.x + clientArea.width) {
            n = Math.max(clientArea.x, clientArea.x + clientArea.width - bounds.width);
        }
        else {
            n = Math.max(max, clientArea.x);
        }
        int n2;
        if (max2 + bounds.height > clientArea.y + clientArea.height) {
            n2 = Math.max(clientArea.y, clientArea.y + clientArea.height - bounds.height);
        }
        else {
            n2 = Math.max(max2, clientArea.y);
        }
        this.setLocation(n, n2);
    }
    
    public void close() {
        this.checkWidget();
        this.closeWidget();
    }
    
    void createBalloonTipHandle() {
        this.balloonTipHandle = OS.CreateWindowEx(0, new TCHAR(0, "tooltips_class32", true), null, 67, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0, OS.GetModuleHandle(null), null);
        if (this.balloonTipHandle == 0) {
            this.error(2);
        }
        if (Shell.ToolTipProc == 0) {
            Shell.ToolTipProc = OS.GetWindowLongPtr(this.balloonTipHandle, -4);
        }
        OS.SendMessage(this.balloonTipHandle, 1048, 0, 32767);
        this.display.addControl(this.balloonTipHandle, this);
        OS.SetWindowLongPtr(this.balloonTipHandle, -4, this.display.windowProc);
    }
    
    void createHandle() {
        final boolean b = this.handle != 0 && (this.state & 0x4000) == 0x0;
        if (this.handle == 0 || b) {
            super.createHandle();
        }
        else {
            this.state |= 0x2;
            if ((this.style & 0x300) == 0x0) {
                this.state |= 0x100;
            }
            this.windowProc = OS.GetWindowLongPtr(this.handle, -4);
        }
        if (!b) {
            int n = OS.GetWindowLong(this.handle, -16) & ~(OS.WS_OVERLAPPED | 0xC00000);
            if (!OS.IsWinCE) {
                n |= Integer.MIN_VALUE;
            }
            if ((this.style & 0x20) != 0x0) {
                n |= 0xC00000;
            }
            if ((this.style & 0x8) == 0x0 && (this.style & 0x810) == 0x0) {
                n |= 0x800000;
            }
            OS.SetWindowLong(this.handle, -16, n);
            this.SetWindowPos(this.handle, 0, 0, 0, 0, 0, 55);
            if (OS.IsWinCE) {
                this._setMaximized(true);
            }
            if (OS.IsPPC) {
                this.psai = new SHACTIVATEINFO();
                this.psai.cbSize = SHACTIVATEINFO.sizeof;
            }
        }
        if (OS.IsDBLocale) {
            this.hIMC = OS.ImmCreateContext();
            if (this.hIMC != 0) {
                OS.ImmAssociateContext(this.handle, this.hIMC);
            }
        }
    }
    
    void createToolTip(final ToolTip toolTip) {
        int n = 0;
        if (this.toolTips == null) {
            this.toolTips = new ToolTip[4];
        }
        while (n < this.toolTips.length && this.toolTips[n] != null) {
            ++n;
        }
        if (n == this.toolTips.length) {
            final ToolTip[] toolTips = new ToolTip[this.toolTips.length + 4];
            System.arraycopy(this.toolTips, 0, toolTips, 0, this.toolTips.length);
            this.toolTips = toolTips;
        }
        this.toolTips[n] = toolTip;
        toolTip.id = n + 108;
        if (OS.IsWinCE) {
            return;
        }
        final TOOLINFO toolinfo = new TOOLINFO();
        toolinfo.cbSize = TOOLINFO.sizeof;
        toolinfo.hwnd = this.handle;
        toolinfo.uId = toolTip.id;
        toolinfo.uFlags = 32;
        toolinfo.lpszText = -1;
        OS.SendMessage(toolTip.hwndToolTip(), OS.TTM_ADDTOOL, 0, toolinfo);
    }
    
    void createToolTipHandle() {
        this.toolTipHandle = OS.CreateWindowEx(0, new TCHAR(0, "tooltips_class32", true), null, 3, Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, this.handle, 0, OS.GetModuleHandle(null), null);
        if (this.toolTipHandle == 0) {
            this.error(2);
        }
        if (Shell.ToolTipProc == 0) {
            Shell.ToolTipProc = OS.GetWindowLongPtr(this.toolTipHandle, -4);
        }
        OS.SendMessage(this.toolTipHandle, 1048, 0, 32767);
        this.display.addControl(this.toolTipHandle, this);
        OS.SetWindowLongPtr(this.toolTipHandle, -4, this.display.windowProc);
    }
    
    void deregister() {
        super.deregister();
        if (this.toolTipHandle != 0) {
            this.display.removeControl(this.toolTipHandle);
        }
        if (this.balloonTipHandle != 0) {
            this.display.removeControl(this.balloonTipHandle);
        }
    }
    
    void destroyToolTip(final ToolTip toolTip) {
        if (this.toolTips == null) {
            return;
        }
        this.toolTips[toolTip.id - 108] = null;
        if (OS.IsWinCE) {
            return;
        }
        if (this.balloonTipHandle != 0) {
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            toolinfo.uId = toolTip.id;
            toolinfo.hwnd = this.handle;
            OS.SendMessage(this.balloonTipHandle, OS.TTM_DELTOOL, 0, toolinfo);
        }
        toolTip.id = -1;
    }
    
    void destroyWidget() {
        this.fixActiveShell();
        super.destroyWidget();
    }
    
    public void dispose() {
        super.dispose();
    }
    
    void enableWidget(final boolean b) {
        if (b) {
            this.state &= 0xFFFFFFF7;
        }
        else {
            this.state |= 0x8;
        }
        if (Display.TrimEnabled) {
            if (this.isActive()) {
                this.setItemEnabled(61536, b);
            }
        }
        else {
            OS.EnableWindow(this.handle, b);
        }
    }
    
    int findBrush(final int n, final int n2) {
        if (n2 == 0) {
            for (int i = 0; i < Shell.SYSTEM_COLORS.length; ++i) {
                if (n == OS.GetSysColor(Shell.SYSTEM_COLORS[i])) {
                    return OS.GetSysColorBrush(Shell.SYSTEM_COLORS[i]);
                }
            }
        }
        if (this.brushes == null) {
            this.brushes = new int[32];
        }
        final LOGBRUSH logbrush = new LOGBRUSH();
        for (int j = 0; j < this.brushes.length; ++j) {
            final int n3 = this.brushes[j];
            if (n3 == 0) {
                break;
            }
            OS.GetObject(n3, LOGBRUSH.sizeof, logbrush);
            switch (logbrush.lbStyle) {
                case 0: {
                    if (n2 == 0 && logbrush.lbColor == n) {
                        return n3;
                    }
                    break;
                }
                case 3: {
                    if (n2 == 3 && logbrush.lbHatch == n) {
                        return n3;
                    }
                    break;
                }
            }
        }
        int length = this.brushes.length;
        int n4 = this.brushes[--length];
        if (n4 != 0) {
            OS.DeleteObject(n4);
        }
        System.arraycopy(this.brushes, 0, this.brushes, 1, length);
        switch (n2) {
            case 0: {
                n4 = OS.CreateSolidBrush(n);
                break;
            }
            case 3: {
                n4 = OS.CreatePatternBrush(n);
                break;
            }
        }
        return this.brushes[0] = n4;
    }
    
    Control findBackgroundControl() {
        return (this.background != -1 || this.backgroundImage != null) ? this : null;
    }
    
    Cursor findCursor() {
        return this.cursor;
    }
    
    Control findThemeControl() {
        return null;
    }
    
    ToolTip findToolTip(int n) {
        if (this.toolTips == null) {
            return null;
        }
        n -= 108;
        return (n >= 0 && n < this.toolTips.length) ? this.toolTips[n] : null;
    }
    
    void fixActiveShell() {
        final int getParent = OS.GetParent(this.handle);
        if (getParent != 0 && this.handle == OS.GetActiveWindow() && !OS.IsWindowEnabled(getParent) && OS.IsWindowVisible(getParent)) {
            OS.SetActiveWindow(getParent);
        }
    }
    
    void fixShell(final Shell shell, final Control control) {
        if (this == shell) {
            return;
        }
        if (control == this.lastActive) {
            this.setActiveControl(null);
        }
        final String toolTipText = control.toolTipText;
        if (toolTipText != null) {
            control.setToolTipText(this, null);
            control.setToolTipText(shell, toolTipText);
        }
    }
    
    void fixToolTip() {
        if (OS.COMCTL32_MAJOR >= 6) {
            if (this.toolTipHandle == 0) {
                return;
            }
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            if (OS.SendMessage(this.toolTipHandle, OS.TTM_GETCURRENTTOOL, 0, toolinfo) != 0 && (toolinfo.uFlags & 0x1) != 0x0) {
                OS.SendMessage(this.toolTipHandle, OS.TTM_DELTOOL, 0, toolinfo);
                OS.SendMessage(this.toolTipHandle, OS.TTM_ADDTOOL, 0, toolinfo);
            }
        }
    }
    
    public void forceActive() {
        this.checkWidget();
        if (!this.isVisible()) {
            return;
        }
        OS.SetForegroundWindow(this.handle);
    }
    
    void forceResize() {
    }
    
    public int getAlpha() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            final byte[] array = { 0 };
            if (OS.GetLayeredWindowAttributes(this.handle, null, array, null)) {
                return array[0] & 0xFF;
            }
        }
        return 255;
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.IsIconic(this.handle)) {
            return super.getBounds();
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    ToolTip getCurrentToolTip() {
        if (this.toolTipHandle != 0) {
            final ToolTip currentToolTip = this.getCurrentToolTip(this.toolTipHandle);
            if (currentToolTip != null) {
                return currentToolTip;
            }
        }
        if (this.balloonTipHandle != 0) {
            final ToolTip currentToolTip2 = this.getCurrentToolTip(this.balloonTipHandle);
            if (currentToolTip2 != null) {
                return currentToolTip2;
            }
        }
        return null;
    }
    
    ToolTip getCurrentToolTip(final int n) {
        if (n == 0) {
            return null;
        }
        if (OS.SendMessage(n, OS.TTM_GETCURRENTTOOL, 0, 0) != 0) {
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            if (OS.SendMessage(n, OS.TTM_GETCURRENTTOOL, 0, toolinfo) != 0 && (toolinfo.uFlags & 0x1) == 0x0) {
                return this.findToolTip(toolinfo.uId);
            }
        }
        return null;
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return (this.state & 0x8) == 0x0;
    }
    
    public boolean getFullScreen() {
        this.checkWidget();
        return this.fullScreen;
    }
    
    public int getImeInputMode() {
        this.checkWidget();
        if (!OS.IsDBLocale) {
            return 0;
        }
        final int immGetContext = OS.ImmGetContext(this.handle);
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        boolean b = OS.ImmGetOpenStatus(immGetContext);
        if (b) {
            b = OS.ImmGetConversionStatus(immGetContext, array, array2);
        }
        OS.ImmReleaseContext(this.handle, immGetContext);
        if (!b) {
            return 0;
        }
        int n = 0;
        if ((array[0] & 0x10) != 0x0) {
            n |= 0x20;
        }
        if ((array[0] & 0x8) != 0x0) {
            n |= 0x2;
        }
        if ((array[0] & 0x2) != 0x0) {
            return n | 0x10;
        }
        if ((array[0] & 0x1) != 0x0) {
            return n | 0x8;
        }
        return n | 0x4;
    }
    
    public Point getLocation() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.IsIconic(this.handle)) {
            return super.getLocation();
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        return new Point(rect.left, rect.top);
    }
    
    public boolean getMaximized() {
        this.checkWidget();
        return !this.fullScreen && super.getMaximized();
    }
    
    public Point getMinimumSize() {
        this.checkWidget();
        int n = Math.max(0, this.minWidth);
        final int n2 = 1248;
        if ((this.style & 0x8) == 0x0 && (this.style & n2) != 0x0) {
            n = Math.max(n, OS.GetSystemMetrics(34));
        }
        int n3 = Math.max(0, this.minHeight);
        if ((this.style & 0x8) == 0x0 && (this.style & n2) != 0x0) {
            if ((this.style & 0x10) != 0x0) {
                n3 = Math.max(n3, OS.GetSystemMetrics(35));
            }
            else {
                final RECT rect = new RECT();
                OS.AdjustWindowRectEx(rect, OS.GetWindowLong(this.handle, -16), false, OS.GetWindowLong(this.handle, -20));
                n3 = Math.max(n3, rect.bottom - rect.top);
            }
        }
        return new Point(n, n3);
    }
    
    public boolean getModified() {
        this.checkWidget();
        return this.modified;
    }
    
    public Region getRegion() {
        this.checkWidget();
        return this.region;
    }
    
    public Shell getShell() {
        this.checkWidget();
        return this;
    }
    
    public Point getSize() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.IsIconic(this.handle)) {
            return super.getSize();
        }
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        return new Point(rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public Shell[] getShells() {
        this.checkWidget();
        int n = 0;
        final Shell[] shells = this.display.getShells();
        for (int i = 0; i < shells.length; ++i) {
            Composite parent = shells[i];
            do {
                parent = parent.parent;
            } while (parent != null && parent != this);
            if (parent == this) {
                ++n;
            }
        }
        int n2 = 0;
        final Shell[] array = new Shell[n];
        for (int j = 0; j < shells.length; ++j) {
            Composite parent2 = shells[j];
            do {
                parent2 = parent2.parent;
            } while (parent2 != null && parent2 != this);
            if (parent2 == this) {
                array[n2++] = shells[j];
            }
        }
        return array;
    }
    
    public ToolBar getToolBar() {
        return null;
    }
    
    Composite findDeferredControl() {
        return (this.layoutCount > 0) ? this : null;
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return this.getVisible();
    }
    
    int hwndMDIClient() {
        if (this.hwndMDIClient == 0) {
            this.hwndMDIClient = OS.CreateWindowEx(0, new TCHAR(0, "MDICLIENT", true), null, 1174405121, 0, 0, 0, 0, this.handle, 0, OS.GetModuleHandle(null), new CREATESTRUCT());
        }
        return this.hwndMDIClient;
    }
    
    public void open() {
        this.checkWidget();
        final STARTUPINFO lpStartupInfo = Display.lpStartupInfo;
        if (lpStartupInfo == null || (lpStartupInfo.dwFlags & 0x1) == 0x0) {
            this.bringToTop();
            if (this.isDisposed()) {
                return;
            }
        }
        if (OS.IsWinCE) {
            OS.SetForegroundWindow(this.handle);
        }
        OS.SendMessage(this.handle, 295, 3, 0);
        this.setVisible(true);
        if (this.isDisposed()) {
            return;
        }
        OS.PeekMessage(new MSG(), 0, 0, 0, 4194306);
        if (!this.restoreFocus() && !this.traverseGroup(true)) {
            this.setFocus();
        }
    }
    
    public boolean print(final GC gc) {
        this.checkWidget();
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        return false;
    }
    
    void register() {
        super.register();
        if (this.toolTipHandle != 0) {
            this.display.addControl(this.toolTipHandle, this);
        }
        if (this.balloonTipHandle != 0) {
            this.display.addControl(this.balloonTipHandle, this);
        }
    }
    
    void releaseBrushes() {
        if (this.brushes != null) {
            for (int i = 0; i < this.brushes.length; ++i) {
                if (this.brushes[i] != 0) {
                    OS.DeleteObject(this.brushes[i]);
                }
            }
        }
        this.brushes = null;
    }
    
    void releaseChildren(final boolean b) {
        final Shell[] shells = this.getShells();
        for (int i = 0; i < shells.length; ++i) {
            final Shell shell = shells[i];
            if (shell != null && !shell.isDisposed()) {
                shell.release(false);
            }
        }
        if (this.toolTips != null) {
            for (int j = 0; j < this.toolTips.length; ++j) {
                final ToolTip toolTip = this.toolTips[j];
                if (toolTip != null && !toolTip.isDisposed()) {
                    toolTip.release(false);
                }
            }
        }
        this.toolTips = null;
        super.releaseChildren(b);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.hwndMDIClient = 0;
    }
    
    void releaseParent() {
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.releaseBrushes();
        this.activeMenu = null;
        this.display.clearModal(this);
        if (this.lpstrTip != 0) {
            OS.HeapFree(OS.GetProcessHeap(), 0, this.lpstrTip);
        }
        this.lpstrTip = 0;
        final boolean b = false;
        this.balloonTipHandle = (b ? 1 : 0);
        this.toolTipHandle = (b ? 1 : 0);
        if (OS.IsDBLocale && this.hIMC != 0) {
            OS.ImmDestroyContext(this.hIMC);
        }
        this.lastActive = null;
        final String s = null;
        this.balloonTitle = s;
        this.toolTitle = s;
    }
    
    void removeMenu(final Menu menu) {
        super.removeMenu(menu);
        if (menu == this.activeMenu) {
            this.activeMenu = null;
        }
    }
    
    public void removeShellListener(final ShellListener shellListener) {
        this.checkWidget();
        if (shellListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(21, shellListener);
        this.eventTable.unhook(19, shellListener);
        this.eventTable.unhook(20, shellListener);
        this.eventTable.unhook(26, shellListener);
        this.eventTable.unhook(27, shellListener);
    }
    
    void reskinChildren(final int n) {
        final Shell[] shells = this.getShells();
        for (int i = 0; i < shells.length; ++i) {
            final Shell shell = shells[i];
            if (shell != null) {
                shell.reskin(n);
            }
        }
        if (this.toolTips != null) {
            for (int j = 0; j < this.toolTips.length; ++j) {
                final ToolTip toolTip = this.toolTips[j];
                if (toolTip != null) {
                    toolTip.reskin(n);
                }
            }
        }
        super.reskinChildren(n);
    }
    
    LRESULT selectPalette(final int n) {
        final int getDC = OS.GetDC(this.handle);
        final int selectPalette = OS.SelectPalette(getDC, n, false);
        final int realizePalette = OS.RealizePalette(getDC);
        if (realizePalette > 0) {
            OS.InvalidateRect(this.handle, null, true);
        }
        else {
            OS.SelectPalette(getDC, selectPalette, true);
            OS.RealizePalette(getDC);
        }
        OS.ReleaseDC(this.handle, getDC);
        return (realizePalette > 0) ? LRESULT.ONE : LRESULT.ZERO;
    }
    
    boolean sendKeyEvent(final int n, final int n2, final int n3, final int n4, final Event event) {
        return this.isEnabled() && this.isActive() && super.sendKeyEvent(n, n2, n3, n4, event);
    }
    
    public void setActive() {
        this.checkWidget();
        if (!this.isVisible()) {
            return;
        }
        this.bringToTop();
    }
    
    void setActiveControl(Control lastActive) {
        if (lastActive != null && lastActive.isDisposed()) {
            lastActive = null;
        }
        if (this.lastActive != null && this.lastActive.isDisposed()) {
            this.lastActive = null;
        }
        if (this.lastActive == lastActive) {
            return;
        }
        final Control[] array = (lastActive == null) ? new Control[0] : lastActive.getPath();
        final Control[] array2 = (this.lastActive == null) ? new Control[0] : this.lastActive.getPath();
        this.lastActive = lastActive;
        int n;
        for (n = 0; n < Math.min(array.length, array2.length) && array[n] == array2[n]; ++n) {}
        for (int i = array2.length - 1; i >= n; --i) {
            if (!array2[i].isDisposed()) {
                array2[i].sendEvent(27);
            }
        }
        for (int j = array.length - 1; j >= n; --j) {
            if (!array[j].isDisposed()) {
                array[j].sendEvent(26);
            }
        }
    }
    
    public void setAlpha(int n) {
        this.checkWidget();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            n &= 0xFF;
            final int getWindowLong = OS.GetWindowLong(this.handle, -20);
            if (n == 255) {
                OS.SetWindowLong(this.handle, -20, getWindowLong & 0xFFF7FFFF);
                OS.RedrawWindow(this.handle, null, 0, 1157);
            }
            else {
                OS.SetWindowLong(this.handle, -20, getWindowLong | 0x80000);
                OS.SetLayeredWindowAttributes(this.handle, 0, (byte)n, 2);
            }
        }
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, int n5, final boolean b) {
        if (this.fullScreen) {
            this.setFullScreen(false);
        }
        if ((OS.GetWindowLong(this.handle, -20) & 0x80000) != 0x0) {
            n5 &= 0xFFFFFFDF;
        }
        super.setBounds(n, n2, n3, n4, n5, false);
    }
    
    public void setEnabled(final boolean enabled) {
        this.checkWidget();
        if ((this.state & 0x8) == 0x0 == enabled) {
            return;
        }
        super.setEnabled(enabled);
        if (enabled && this.handle == OS.GetActiveWindow() && !this.restoreFocus()) {
            this.traverseGroup(true);
        }
    }
    
    public void setFullScreen(final boolean fullScreen) {
        this.checkWidget();
        if (this.fullScreen == fullScreen) {
            return;
        }
        int sw_SHOWMAXIMIZED = fullScreen ? OS.SW_SHOWMAXIMIZED : OS.SW_RESTORE;
        int getWindowLong = OS.GetWindowLong(this.handle, -16);
        if ((this.style & 0x4E0) != 0x0) {
            if (fullScreen) {
                getWindowLong &= ~(0xC00000 | OS.WS_MAXIMIZEBOX | OS.WS_MINIMIZEBOX);
            }
            else {
                getWindowLong |= 0xC00000;
                if ((this.style & 0x400) != 0x0) {
                    getWindowLong |= OS.WS_MAXIMIZEBOX;
                }
                if ((this.style & 0x80) != 0x0) {
                    getWindowLong |= OS.WS_MINIMIZEBOX;
                }
            }
        }
        if (fullScreen) {
            this.wasMaximized = this.getMaximized();
        }
        final boolean visible = this.isVisible();
        OS.SetWindowLong(this.handle, -16, getWindowLong);
        if (this.wasMaximized) {
            OS.ShowWindow(this.handle, 0);
            sw_SHOWMAXIMIZED = OS.SW_SHOWMAXIMIZED;
        }
        if (visible) {
            OS.ShowWindow(this.handle, sw_SHOWMAXIMIZED);
        }
        OS.UpdateWindow(this.handle);
        this.fullScreen = fullScreen;
    }
    
    public void setImeInputMode(final int n) {
        this.checkWidget();
        if (!OS.IsDBLocale) {
            return;
        }
        final boolean b = n != 0;
        final int immGetContext = OS.ImmGetContext(this.handle);
        OS.ImmSetOpenStatus(immGetContext, b);
        if (b) {
            final int[] array = { 0 };
            final int[] array2 = { 0 };
            if (OS.ImmGetConversionStatus(immGetContext, array, array2)) {
                int n2 = 0;
                int n3 = 3;
                if ((n & 0x10) != 0x0) {
                    n2 = 3;
                    n3 = 0;
                }
                else if ((n & 0x8) != 0x0) {
                    n2 = 1;
                    n3 = 2;
                }
                if ((n & 0xA) != 0x0) {
                    n2 |= 0x8;
                }
                else {
                    n3 |= 0x8;
                }
                if ((n & 0x20) != 0x0) {
                    n2 |= 0x10;
                }
                else {
                    n3 |= 0x10;
                }
                final int[] array3 = array;
                final int n4 = 0;
                array3[n4] |= n2;
                final int[] array4 = array;
                final int n5 = 0;
                array4[n5] &= ~n3;
                OS.ImmSetConversionStatus(immGetContext, array[0], array2[0]);
            }
        }
        OS.ImmReleaseContext(this.handle, immGetContext);
    }
    
    public void setMinimumSize(final int n, final int n2) {
        this.checkWidget();
        int getSystemMetrics = 0;
        int getSystemMetrics2 = 0;
        final int n3 = 1248;
        if ((this.style & 0x8) == 0x0 && (this.style & n3) != 0x0) {
            getSystemMetrics = OS.GetSystemMetrics(34);
            if ((this.style & 0x10) != 0x0) {
                getSystemMetrics2 = OS.GetSystemMetrics(35);
            }
            else {
                final RECT rect = new RECT();
                OS.AdjustWindowRectEx(rect, OS.GetWindowLong(this.handle, -16), false, OS.GetWindowLong(this.handle, -20));
                getSystemMetrics2 = rect.bottom - rect.top;
            }
        }
        this.minWidth = Math.max(getSystemMetrics, n);
        this.minHeight = Math.max(getSystemMetrics2, n2);
        final Point size = this.getSize();
        final int max = Math.max(size.x, this.minWidth);
        final int max2 = Math.max(size.y, this.minHeight);
        if (this.minWidth <= getSystemMetrics) {
            this.minWidth = -1;
        }
        if (this.minHeight <= getSystemMetrics2) {
            this.minHeight = -1;
        }
        if (max != size.x || max2 != size.y) {
            this.setSize(max, max2);
        }
    }
    
    public void setMinimumSize(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setMinimumSize(point.x, point.y);
    }
    
    public void setModified(final boolean modified) {
        this.checkWidget();
        this.modified = modified;
    }
    
    void setItemEnabled(final int n, final boolean b) {
        final int getSystemMenu = OS.GetSystemMenu(this.handle, false);
        if (getSystemMenu == 0) {
            return;
        }
        int n2 = 0;
        if (!b) {
            n2 = 3;
        }
        OS.EnableMenuItem(getSystemMenu, n, n2);
    }
    
    void setParent() {
    }
    
    public void setRegion(final Region region) {
        this.checkWidget();
        if ((this.style & 0x8) == 0x0) {
            return;
        }
        super.setRegion(region);
    }
    
    void setToolTipText(final int uId, final String s) {
        if (OS.IsWinCE) {
            return;
        }
        final TOOLINFO toolinfo = new TOOLINFO();
        toolinfo.cbSize = TOOLINFO.sizeof;
        toolinfo.hwnd = this.handle;
        toolinfo.uId = uId;
        final int toolTipHandle = this.toolTipHandle();
        if (s == null) {
            OS.SendMessage(toolTipHandle, OS.TTM_DELTOOL, 0, toolinfo);
        }
        else if (OS.SendMessage(toolTipHandle, OS.TTM_GETTOOLINFO, 0, toolinfo) != 0) {
            OS.SendMessage(toolTipHandle, 1053, 0, 0);
        }
        else {
            toolinfo.uFlags = 17;
            toolinfo.lpszText = -1;
            OS.SendMessage(toolTipHandle, OS.TTM_ADDTOOL, 0, toolinfo);
        }
    }
    
    void setToolTipText(final NMTTDISPINFO nmttdispinfo, final byte[] array) {
        if (!this.hasCursor()) {
            return;
        }
        final int getProcessHeap = OS.GetProcessHeap();
        if (this.lpstrTip != 0) {
            OS.HeapFree(getProcessHeap, 0, this.lpstrTip);
        }
        final int length = array.length;
        OS.MoveMemory(this.lpstrTip = OS.HeapAlloc(getProcessHeap, 8, length), array, length);
        nmttdispinfo.lpszText = this.lpstrTip;
    }
    
    void setToolTipText(final NMTTDISPINFO nmttdispinfo, final char[] array) {
        if (!this.hasCursor()) {
            return;
        }
        final int getProcessHeap = OS.GetProcessHeap();
        if (this.lpstrTip != 0) {
            OS.HeapFree(getProcessHeap, 0, this.lpstrTip);
        }
        final int n = array.length * 2;
        OS.MoveMemory(this.lpstrTip = OS.HeapAlloc(getProcessHeap, 8, n), array, n);
        nmttdispinfo.lpszText = this.lpstrTip;
    }
    
    void setToolTipTitle(final int n, String substring, final int n2) {
        if (n != this.toolTipHandle && n != this.balloonTipHandle) {
            return;
        }
        if (n == this.toolTipHandle) {
            if ((substring == this.toolTitle || (this.toolTitle != null && this.toolTitle.equals(substring))) && n2 == this.toolIcon) {
                return;
            }
            this.toolTitle = substring;
            this.toolIcon = n2;
        }
        else if (n == this.balloonTipHandle) {
            if ((substring == this.balloonTitle || (this.balloonTitle != null && this.balloonTitle.equals(substring))) && n2 == this.toolIcon) {
                return;
            }
            this.balloonTitle = substring;
            this.balloonIcon = n2;
        }
        if (substring != null) {
            if (substring.length() > 99) {
                substring = substring.substring(0, 99);
            }
            OS.SendMessage(n, OS.TTM_SETTITLE, n2, new TCHAR(this.getCodePage(), substring, true));
        }
        else {
            OS.SendMessage(n, OS.TTM_SETTITLE, 0, 0);
        }
    }
    
    public void setVisible(final boolean b) {
        this.checkWidget();
        if ((this.style & 0x38000) != 0x0) {
            if (b) {
                this.display.setModalShell(this);
                if ((this.style & 0x30000) != 0x0) {
                    this.display.setModalDialog(null);
                }
                final Control getFocusControl = this.display._getFocusControl();
                if (getFocusControl != null && !getFocusControl.isActive()) {
                    this.bringToTop();
                    if (this.isDisposed()) {
                        return;
                    }
                }
                int n = OS.GetActiveWindow();
                if (n == 0 && this.parent != null) {
                    n = this.parent.handle;
                }
                if (n != 0) {
                    OS.SendMessage(n, 31, 0, 0);
                }
                OS.ReleaseCapture();
            }
            else {
                this.display.clearModal(this);
            }
        }
        else {
            this.updateModal();
        }
        if (this.showWithParent && !b && !OS.IsWinCE) {
            OS.ShowOwnedPopups(this.handle, false);
        }
        if (!b) {
            this.fixActiveShell();
        }
        if (b && this.center && !this.moved) {
            this.center();
            if (this.isDisposed()) {
                return;
            }
        }
        super.setVisible(b);
        if (this.isDisposed()) {
            return;
        }
        if (this.showWithParent != b && (this.showWithParent = b) && !OS.IsWinCE) {
            OS.ShowOwnedPopups(this.handle, true);
        }
        if (b && this.parent != null && (this.parent.state & 0x4000) != 0x0) {
            final int handle = this.parent.handle;
            final int getWindowLong = OS.GetWindowLong(handle, -20);
            if ((getWindowLong & 0x80) != 0x0) {
                OS.SetWindowLong(handle, -20, getWindowLong & 0xFFFFFF7F);
                OS.ShowWindow(handle, 0);
                OS.ShowWindow(handle, OS.SW_RESTORE);
            }
        }
    }
    
    void subclass() {
        super.subclass();
        if (Shell.ToolTipProc != 0) {
            final int windowProc = this.display.windowProc;
            if (this.toolTipHandle != 0) {
                OS.SetWindowLongPtr(this.toolTipHandle, -4, windowProc);
            }
            if (this.balloonTipHandle != 0) {
                OS.SetWindowLongPtr(this.balloonTipHandle, -4, windowProc);
            }
        }
    }
    
    int toolTipHandle() {
        if (this.toolTipHandle == 0) {
            this.createToolTipHandle();
        }
        return this.toolTipHandle;
    }
    
    boolean translateAccelerator(final MSG msg) {
        return this.isEnabled() && this.isActive() && (this.menuBar == null || this.menuBar.isEnabled()) && (this.translateMDIAccelerator(msg) || this.translateMenuAccelerator(msg));
    }
    
    boolean traverseEscape() {
        if (this.parent == null) {
            return false;
        }
        if (!this.isVisible() || !this.isEnabled()) {
            return false;
        }
        this.close();
        return true;
    }
    
    void unsubclass() {
        super.unsubclass();
        if (Shell.ToolTipProc != 0) {
            if (this.toolTipHandle != 0) {
                OS.SetWindowLongPtr(this.toolTipHandle, -4, Shell.ToolTipProc);
            }
            if (this.toolTipHandle != 0) {
                OS.SetWindowLongPtr(this.toolTipHandle, -4, Shell.ToolTipProc);
            }
        }
    }
    
    void updateModal() {
        if (Display.TrimEnabled) {
            this.setItemEnabled(61536, this.isActive());
        }
        else {
            OS.EnableWindow(this.handle, this.isActive());
        }
    }
    
    CREATESTRUCT widgetCreateStruct() {
        return null;
    }
    
    int widgetParent() {
        if (this.handle != 0) {
            return this.handle;
        }
        return (this.parent != null) ? this.parent.handle : 0;
    }
    
    int widgetExtStyle() {
        int n = super.widgetExtStyle() & 0xFFFFFFBF;
        if ((this.style & 0x4) != 0x0) {
            n |= 0x80;
        }
        if (!OS.IsWinCE && this.parent == null && (this.style & 0x4000) != 0x0) {
            final int n2 = 1248;
            if ((this.style & 0x8) != 0x0 || (this.style & n2) == 0x0) {
                n |= 0x80;
            }
        }
        if (this.parent != null) {
            if (OS.IsWin95) {
                return n;
            }
            if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
                return n;
            }
        }
        if ((this.style & 0x4000) != 0x0) {
            n |= 0x8;
        }
        return n;
    }
    
    TCHAR windowClass() {
        if (OS.IsSP) {
            return Shell.DialogClass;
        }
        if ((this.style & 0x4) != 0x0 && (this.style & 0xCF0) == 0x0) {
            return this.display.windowShadowClass;
        }
        return (this.parent != null) ? Shell.DialogClass : super.windowClass();
    }
    
    int windowProc() {
        if (this.windowProc != 0) {
            return this.windowProc;
        }
        if (OS.IsSP) {
            return Shell.DialogProc;
        }
        if ((this.style & 0x4) != 0x0 && (this.style & 0xCF0) == 0x0) {
            return super.windowProc();
        }
        return (this.parent != null) ? Shell.DialogProc : super.windowProc();
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (n == this.toolTipHandle || n == this.balloonTipHandle) {
            switch (n2) {
                case 275: {
                    if (n3 != 100) {
                        break;
                    }
                    final ToolTip currentToolTip = this.getCurrentToolTip(n);
                    if (currentToolTip != null && currentToolTip.autoHide) {
                        currentToolTip.setVisible(false);
                        break;
                    }
                    break;
                }
                case 513: {
                    final ToolTip currentToolTip2 = this.getCurrentToolTip(n);
                    if (currentToolTip2 != null) {
                        currentToolTip2.setVisible(false);
                        currentToolTip2.sendSelectionEvent(13);
                        break;
                    }
                    break;
                }
            }
            return this.callWindowProc(n, n2, n3, n4);
        }
        return super.windowProc(n, n2, n3, n4);
    }
    
    int widgetStyle() {
        final int widgetStyle = super.widgetStyle();
        if (this.handle != 0) {
            return widgetStyle | 0x40000000;
        }
        final int n = widgetStyle & 0xBFFFFFFF;
        if (!OS.IsWinCE) {
            return n | OS.WS_OVERLAPPED | 0xC00000;
        }
        if (OS.IsSP) {
            return n | Integer.MIN_VALUE;
        }
        return (this.parent == null) ? n : (n | Integer.MIN_VALUE);
    }
    
    LRESULT WM_ACTIVATE(final int n, final int n2) {
        if (OS.IsPPC) {
            if (this.hooks(33) || this.hooks(34)) {
                final int n3 = (OS.LOWORD(n) != 0) ? this.handle : 0;
                for (int i = 193; i <= 198; ++i) {
                    OS.SHSetAppKeyWndAssoc((byte)i, n3);
                }
            }
            if (OS.LOWORD(n) != 0) {
                OS.SHSipPreference(this.handle, (this.psai.fSipUp == 0) ? 1 : 0);
            }
        }
        if (OS.WIN32_VERSION >= OS.VERSION(5, 1) && OS.LOWORD(n) == 0 && OS.IsDBLocale && this.hIMC != 0 && OS.ImmGetOpenStatus(this.hIMC)) {
            OS.ImmNotifyIME(this.hIMC, 21, 1, 0);
        }
        final LRESULT wm_ACTIVATE = super.WM_ACTIVATE(n, n2);
        if (OS.LOWORD(n) == 0 && (n2 == 0 || (n2 != this.toolTipHandle && n2 != this.balloonTipHandle))) {
            final ToolTip currentToolTip = this.getCurrentToolTip();
            if (currentToolTip != null) {
                currentToolTip.setVisible(false);
            }
        }
        return (this.parent != null) ? LRESULT.ZERO : wm_ACTIVATE;
    }
    
    LRESULT WM_COMMAND(final int n, final int n2) {
        if (OS.IsPPC && OS.LOWORD(n) == 1 && (n2 == 0 || n2 == this.handle)) {
            OS.PostMessage(this.handle, 16, 0, 0);
            return LRESULT.ZERO;
        }
        if ((OS.IsPPC || OS.IsSP) && this.menuBar != null) {
            final int hwndCB = this.menuBar.hwndCB;
            if (n2 != 0 && hwndCB != 0) {
                if (n2 == hwndCB) {
                    return super.WM_COMMAND(n, 0);
                }
                if (n2 == OS.GetWindow(hwndCB, 5)) {
                    return super.WM_COMMAND(n, 0);
                }
            }
        }
        return super.WM_COMMAND(n, n2);
    }
    
    LRESULT WM_DESTROY(final int n, final int n2) {
        final LRESULT wm_DESTROY = super.WM_DESTROY(n, n2);
        if ((OS.GetWindowLong(this.handle, -16) & 0x40000000) != 0x0) {
            this.releaseParent();
            this.release(false);
        }
        return wm_DESTROY;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if (wm_ERASEBKGND != null) {
            return wm_ERASEBKGND;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            this.drawBackground(n);
            return LRESULT.ONE;
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_ENTERIDLE(final int n, final int n2) {
        final LRESULT wm_ENTERIDLE = super.WM_ENTERIDLE(n, n2);
        if (wm_ENTERIDLE != null) {
            return wm_ENTERIDLE;
        }
        final Display display = this.display;
        if (display.runMessages && display.runAsyncMessages(false)) {
            display.wakeThread();
        }
        return wm_ENTERIDLE;
    }
    
    LRESULT WM_GETMINMAXINFO(final int n, final int n2) {
        final LRESULT wm_GETMINMAXINFO = super.WM_GETMINMAXINFO(n, n2);
        if (wm_GETMINMAXINFO != null) {
            return wm_GETMINMAXINFO;
        }
        if (this.minWidth != -1 || this.minHeight != -1) {
            final MINMAXINFO minmaxinfo = new MINMAXINFO();
            OS.MoveMemory(minmaxinfo, n2, MINMAXINFO.sizeof);
            if (this.minWidth != -1) {
                minmaxinfo.ptMinTrackSize_x = this.minWidth;
            }
            if (this.minHeight != -1) {
                minmaxinfo.ptMinTrackSize_y = this.minHeight;
            }
            OS.MoveMemory(n2, minmaxinfo, MINMAXINFO.sizeof);
            return LRESULT.ZERO;
        }
        return wm_GETMINMAXINFO;
    }
    
    LRESULT WM_MOUSEACTIVATE(final int n, final int n2) {
        final LRESULT wm_MOUSEACTIVATE = super.WM_MOUSEACTIVATE(n, n2);
        if (wm_MOUSEACTIVATE != null) {
            return wm_MOUSEACTIVATE;
        }
        final short lastHittest = (short)OS.LOWORD(n2);
        switch (lastHittest) {
            case -2:
            case -1:
            case 0: {
                break;
            }
            default: {
                final Control getFocusControl = this.display._getFocusControl();
                if (getFocusControl == null) {
                    break;
                }
                final Decorations menuShell = getFocusControl.menuShell();
                if (menuShell.getShell() != this || menuShell == this) {
                    break;
                }
                this.display.ignoreRestoreFocus = true;
                this.display.lastHittest = lastHittest;
                this.display.lastHittestControl = null;
                if (lastHittest == 5 || lastHittest == 3) {
                    this.display.lastHittestControl = getFocusControl;
                    return null;
                }
                if (OS.IsWin95 && lastHittest == 2) {
                    this.display.lastHittestControl = getFocusControl;
                }
                return new LRESULT(3);
            }
        }
        if (lastHittest == 5) {
            return null;
        }
        final POINT point = new POINT();
        if (!OS.GetCursorPos(point)) {
            OS.POINTSTOPOINT(point, OS.GetMessagePos());
        }
        final int windowFromPoint = OS.WindowFromPoint(point);
        if (windowFromPoint == 0) {
            return null;
        }
        final Control control = this.display.findControl(windowFromPoint);
        if (control != null && (control.state & 0x2) != 0x0 && (control.style & 0x80000) != 0x0) {
            final int n3 = 540672;
            if ((this.style & n3) == n3 && (lastHittest == 18 || lastHittest == 1)) {
                return new LRESULT(3);
            }
        }
        final int callWindowProc = this.callWindowProc(this.handle, 33, n, n2);
        this.setActiveControl(control);
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_MOVE(final int n, final int n2) {
        final LRESULT wm_MOVE = super.WM_MOVE(n, n2);
        if (wm_MOVE != null) {
            return wm_MOVE;
        }
        final ToolTip currentToolTip = this.getCurrentToolTip();
        if (currentToolTip != null) {
            currentToolTip.setVisible(false);
        }
        return wm_MOVE;
    }
    
    LRESULT WM_NCHITTEST(final int n, final int n2) {
        if (!OS.IsWindowEnabled(this.handle)) {
            return null;
        }
        if (!this.isEnabled() || !this.isActive()) {
            if (!Display.TrimEnabled) {
                return new LRESULT(0);
            }
            int callWindowProc = this.callWindowProc(this.handle, 132, n, n2);
            if (callWindowProc == 1 || callWindowProc == 5) {
                callWindowProc = 18;
            }
            return new LRESULT(callWindowProc);
        }
        else {
            if (this.menuBar != null && !this.menuBar.getEnabled()) {
                int callWindowProc2 = this.callWindowProc(this.handle, 132, n, n2);
                if (callWindowProc2 == 5) {
                    callWindowProc2 = 18;
                }
                return new LRESULT(callWindowProc2);
            }
            return null;
        }
    }
    
    LRESULT WM_NCLBUTTONDOWN(final int n, final int n2) {
        final LRESULT wm_NCLBUTTONDOWN = super.WM_NCLBUTTONDOWN(n, n2);
        if (wm_NCLBUTTONDOWN != null) {
            return wm_NCLBUTTONDOWN;
        }
        if (!this.display.ignoreRestoreFocus) {
            return wm_NCLBUTTONDOWN;
        }
        final Display display = this.display;
        int setActiveWindow = 0;
        final boolean b = OS.IsWin95 && display.lastHittest == 2;
        if (b) {
            setActiveWindow = OS.SetActiveWindow(this.handle);
        }
        display.lockActiveWindow = true;
        final int callWindowProc = this.callWindowProc(this.handle, 161, n, n2);
        display.lockActiveWindow = false;
        if (b) {
            OS.SetActiveWindow(setActiveWindow);
        }
        final Control lastHittestControl = display.lastHittestControl;
        if (lastHittestControl != null && !lastHittestControl.isDisposed()) {
            lastHittestControl.setFocus();
        }
        display.lastHittestControl = null;
        display.ignoreRestoreFocus = false;
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_PALETTECHANGED(final int n, final int n2) {
        if (n != this.handle) {
            final int hPalette = this.display.hPalette;
            if (hPalette != 0) {
                return this.selectPalette(hPalette);
            }
        }
        return super.WM_PALETTECHANGED(n, n2);
    }
    
    LRESULT WM_QUERYNEWPALETTE(final int n, final int n2) {
        final int hPalette = this.display.hPalette;
        if (hPalette != 0) {
            return this.selectPalette(hPalette);
        }
        return super.WM_QUERYNEWPALETTE(n, n2);
    }
    
    LRESULT WM_SETCURSOR(final int n, final int n2) {
        final int hiword = OS.HIWORD(n2);
        if (hiword == 513) {
            if (!Display.TrimEnabled) {
                final Shell modalShell = this.display.getModalShell();
                if (modalShell != null && !this.isActive()) {
                    final int handle = modalShell.handle;
                    if (OS.IsWindowEnabled(handle)) {
                        OS.SetActiveWindow(handle);
                    }
                }
            }
            if (!OS.IsWindowEnabled(this.handle) && !OS.IsWinCE) {
                final int getLastActivePopup = OS.GetLastActivePopup(this.handle);
                if (getLastActivePopup != 0 && getLastActivePopup != this.handle && this.display.getControl(getLastActivePopup) == null && OS.IsWindowEnabled(getLastActivePopup)) {
                    OS.SetActiveWindow(getLastActivePopup);
                }
            }
        }
        if ((short)OS.LOWORD(n2) == -2 && !this.getEnabled() && this.display.getControl(n) == this && this.cursor != null) {
            final POINT point = new POINT();
            OS.POINTSTOPOINT(point, OS.GetMessagePos());
            OS.ScreenToClient(this.handle, point);
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            if (OS.PtInRect(rect, point)) {
                OS.SetCursor(this.cursor.handle);
                switch (hiword) {
                    case 513:
                    case 516:
                    case 519:
                    case 523: {
                        OS.MessageBeep(0);
                        break;
                    }
                }
                return LRESULT.ONE;
            }
        }
        return super.WM_SETCURSOR(n, n2);
    }
    
    LRESULT WM_SETTINGCHANGE(final int n, final int n2) {
        final LRESULT wm_SETTINGCHANGE = super.WM_SETTINGCHANGE(n, n2);
        if (wm_SETTINGCHANGE != null) {
            return wm_SETTINGCHANGE;
        }
        if (OS.IsPPC && n == 224) {
            if ((this.style & 0x10) != 0x0) {
                OS.SHHandleWMSettingChange(this.handle, n, n2, this.psai);
                return LRESULT.ZERO;
            }
            final SIPINFO sipinfo = new SIPINFO();
            sipinfo.cbSize = SIPINFO.sizeof;
            OS.SipGetInfo(sipinfo);
            this.psai.fSipUp = (sipinfo.fdwFlags & 0x1);
        }
        return wm_SETTINGCHANGE;
    }
    
    LRESULT WM_SHOWWINDOW(final int n, final int n2) {
        final LRESULT wm_SHOWWINDOW = super.WM_SHOWWINDOW(n, n2);
        if (wm_SHOWWINDOW != null) {
            return wm_SHOWWINDOW;
        }
        if (n2 == 3) {
            for (Composite parent = this; parent != null; parent = parent.parent) {
                if (!parent.getShell().showWithParent) {
                    return LRESULT.ZERO;
                }
            }
        }
        return wm_SHOWWINDOW;
    }
    
    LRESULT WM_SYSCOMMAND(final int n, final int n2) {
        final LRESULT wm_SYSCOMMAND = super.WM_SYSCOMMAND(n, n2);
        if (wm_SYSCOMMAND != null) {
            return wm_SYSCOMMAND;
        }
        if (OS.IsWinNT) {
            switch (n & 0xFFF0) {
                case 61472: {
                    if (Runtime.getRuntime().totalMemory() >= 33554432L) {
                        OS.ShowWindow(this.handle, 2);
                        return LRESULT.ZERO;
                    }
                    break;
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
        final WINDOWPOS windowpos = new WINDOWPOS();
        OS.MoveMemory(windowpos, n2, WINDOWPOS.sizeof);
        if ((windowpos.flags & 0x1) == 0x0) {
            windowpos.cx = Math.max(windowpos.cx, this.minWidth);
            final int n3 = 1248;
            if ((this.style & 0x8) == 0x0 && (this.style & n3) != 0x0) {
                windowpos.cx = Math.max(windowpos.cx, OS.GetSystemMetrics(34));
            }
            windowpos.cy = Math.max(windowpos.cy, this.minHeight);
            if ((this.style & 0x8) == 0x0 && (this.style & n3) != 0x0) {
                if ((this.style & 0x10) != 0x0) {
                    windowpos.cy = Math.max(windowpos.cy, OS.GetSystemMetrics(35));
                }
                else {
                    final RECT rect = new RECT();
                    OS.AdjustWindowRectEx(rect, OS.GetWindowLong(this.handle, -16), false, OS.GetWindowLong(this.handle, -20));
                    windowpos.cy = Math.max(windowpos.cy, rect.bottom - rect.top);
                }
            }
            OS.MoveMemory(n2, windowpos, WINDOWPOS.sizeof);
        }
        return wm_WINDOWPOSCHANGING;
    }
}
