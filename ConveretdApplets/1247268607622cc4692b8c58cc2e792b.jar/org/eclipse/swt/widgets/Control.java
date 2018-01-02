// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.FLICK_POINT;
import org.eclipse.swt.internal.win32.FLICK_DATA;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.win32.MENUITEMINFO;
import org.eclipse.swt.internal.win32.MEASUREITEMSTRUCT;
import org.eclipse.swt.internal.win32.HELPINFO;
import org.eclipse.swt.internal.win32.DRAWITEMSTRUCT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.TOUCHINPUT;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.win32.GESTUREINFO;
import org.eclipse.swt.internal.gdip.Gdip;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.MONITORINFO;
import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.internal.win32.GESTURECONFIG;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TouchListener;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.HelpListener;
import org.eclipse.swt.events.GestureListener;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Drawable;

public abstract class Control extends Widget implements Drawable
{
    public int handle;
    Composite parent;
    Cursor cursor;
    Menu menu;
    String toolTipText;
    Object layoutData;
    Accessible accessible;
    Image backgroundImage;
    Region region;
    Font font;
    int drawCount;
    int foreground;
    int background;
    
    Control() {
    }
    
    public Control(final Composite parent, final int n) {
        super(parent, n);
        this.parent = parent;
        this.createWidget();
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
    
    public void addDragDetectListener(final DragDetectListener dragDetectListener) {
        this.checkWidget();
        if (dragDetectListener == null) {
            this.error(4);
        }
        this.addListener(29, new TypedListener(dragDetectListener));
    }
    
    public void addFocusListener(final FocusListener focusListener) {
        this.checkWidget();
        if (focusListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(focusListener);
        this.addListener(15, typedListener);
        this.addListener(16, typedListener);
    }
    
    public void addGestureListener(final GestureListener gestureListener) {
        this.checkWidget();
        if (gestureListener == null) {
            this.error(4);
        }
        this.addListener(48, new TypedListener(gestureListener));
    }
    
    public void addHelpListener(final HelpListener helpListener) {
        this.checkWidget();
        if (helpListener == null) {
            this.error(4);
        }
        this.addListener(28, new TypedListener(helpListener));
    }
    
    public void addKeyListener(final KeyListener keyListener) {
        this.checkWidget();
        if (keyListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(keyListener);
        this.addListener(2, typedListener);
        this.addListener(1, typedListener);
    }
    
    public void addMenuDetectListener(final MenuDetectListener menuDetectListener) {
        this.checkWidget();
        if (menuDetectListener == null) {
            this.error(4);
        }
        this.addListener(35, new TypedListener(menuDetectListener));
    }
    
    public void addMouseListener(final MouseListener mouseListener) {
        this.checkWidget();
        if (mouseListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(mouseListener);
        this.addListener(3, typedListener);
        this.addListener(4, typedListener);
        this.addListener(8, typedListener);
    }
    
    public void addMouseTrackListener(final MouseTrackListener mouseTrackListener) {
        this.checkWidget();
        if (mouseTrackListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(mouseTrackListener);
        this.addListener(6, typedListener);
        this.addListener(7, typedListener);
        this.addListener(32, typedListener);
    }
    
    public void addMouseMoveListener(final MouseMoveListener mouseMoveListener) {
        this.checkWidget();
        if (mouseMoveListener == null) {
            this.error(4);
        }
        this.addListener(5, new TypedListener(mouseMoveListener));
    }
    
    public void addMouseWheelListener(final MouseWheelListener mouseWheelListener) {
        this.checkWidget();
        if (mouseWheelListener == null) {
            this.error(4);
        }
        this.addListener(37, new TypedListener(mouseWheelListener));
    }
    
    public void addPaintListener(final PaintListener paintListener) {
        this.checkWidget();
        if (paintListener == null) {
            this.error(4);
        }
        this.addListener(9, new TypedListener(paintListener));
    }
    
    public void addTouchListener(final TouchListener touchListener) {
        this.checkWidget();
        if (touchListener == null) {
            this.error(4);
        }
        this.addListener(47, new TypedListener(touchListener));
    }
    
    public void addTraverseListener(final TraverseListener traverseListener) {
        this.checkWidget();
        if (traverseListener == null) {
            this.error(4);
        }
        this.addListener(31, new TypedListener(traverseListener));
    }
    
    int binarySearch(final int[] array, final int n, final int n2, final int n3) {
        int i = n;
        int n4 = n2 - 1;
        while (i <= n4) {
            final int n5 = i + n4 >>> 1;
            if (array[n5] == n3) {
                return n5;
            }
            if (array[n5] < n3) {
                i = n5 + 1;
            }
            else {
                n4 = n5 - 1;
            }
        }
        return -i - 1;
    }
    
    int borderHandle() {
        return this.handle;
    }
    
    void checkBackground() {
        final Shell shell = this.getShell();
        if (this == shell) {
            return;
        }
        this.state &= 0xFFFFFBFF;
        Composite composite = this.parent;
        while (true) {
            final int backgroundMode = composite.backgroundMode;
            if (backgroundMode != 0) {
                Label_0071: {
                    if (backgroundMode == 1) {
                        Control parent = this;
                        while ((parent.state & 0x100) != 0x0) {
                            parent = parent.parent;
                            if (parent == composite) {
                                break Label_0071;
                            }
                        }
                        return;
                    }
                }
                this.state |= 0x400;
                return;
            }
            if (composite == shell) {
                return;
            }
            composite = composite.parent;
        }
    }
    
    void checkBorder() {
        if (this.getBorderWidth() == 0) {
            this.style &= 0xFFFFF7FF;
        }
    }
    
    void checkBuffered() {
        this.style &= 0xDFFFFFFF;
    }
    
    void checkComposited() {
    }
    
    boolean checkHandle(final int n) {
        return n == this.handle;
    }
    
    void checkMirrored() {
        if ((this.style & 0x4000000) != 0x0 && (OS.GetWindowLong(this.handle, -20) & 0x400000) != 0x0) {
            this.style |= 0x8000000;
        }
    }
    
    public Point computeSize(final int n, final int n2) {
        return this.computeSize(n, n2, true);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 64;
        int n4 = 64;
        if (n != -1) {
            n3 = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        final int borderWidth = this.getBorderWidth();
        return new Point(n3 + borderWidth * 2, n4 + borderWidth * 2);
    }
    
    Widget computeTabGroup() {
        if (this.isTabGroup()) {
            return this;
        }
        return this.parent.computeTabGroup();
    }
    
    Control computeTabRoot() {
        final Control[] getTabList = this.parent._getTabList();
        if (getTabList != null) {
            int n;
            for (n = 0; n < getTabList.length && getTabList[n] != this; ++n) {}
            if (n == getTabList.length && this.isTabGroup()) {
                return this;
            }
        }
        return this.parent.computeTabRoot();
    }
    
    Widget[] computeTabList() {
        if (this.isTabGroup() && this.getVisible() && this.getEnabled()) {
            return new Widget[] { this };
        }
        return new Widget[0];
    }
    
    void createHandle() {
        final int widgetParent = this.widgetParent();
        this.handle = OS.CreateWindowEx(this.widgetExtStyle(), this.windowClass(), null, this.widgetStyle(), Integer.MIN_VALUE, 0, Integer.MIN_VALUE, 0, widgetParent, 0, OS.GetModuleHandle(null), this.widgetCreateStruct());
        if (this.handle == 0) {
            this.error(2);
        }
        if ((OS.GetWindowLong(this.handle, -16) & 0x40000000) != 0x0) {
            OS.SetWindowLongPtr(this.handle, -12, this.handle);
        }
        if (OS.IsDBLocale && widgetParent != 0) {
            final int immGetContext = OS.ImmGetContext(widgetParent);
            OS.ImmAssociateContext(this.handle, immGetContext);
            OS.ImmReleaseContext(widgetParent, immGetContext);
        }
    }
    
    void checkGesture() {
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 1) && (OS.GetSystemMetrics(94) & 0xC0) != 0x0) {
            final int getProcessHeap = OS.GetProcessHeap();
            final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, GESTURECONFIG.sizeof);
            if (heapAlloc != 0) {
                final GESTURECONFIG gestureconfig = new GESTURECONFIG();
                gestureconfig.dwID = 5;
                gestureconfig.dwWant = 1;
                gestureconfig.dwBlock = 0;
                OS.MoveMemory(heapAlloc, gestureconfig, GESTURECONFIG.sizeof);
                OS.SetGestureConfig(this.handle, 0, 1, heapAlloc, GESTURECONFIG.sizeof);
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
            }
        }
    }
    
    void createWidget() {
        this.state |= 0x8000;
        final int n = -1;
        this.background = n;
        this.foreground = n;
        this.checkOrientation(this.parent);
        this.createHandle();
        this.checkBackground();
        this.checkBuffered();
        this.checkComposited();
        this.register();
        this.subclass();
        this.setDefaultFont();
        this.checkMirrored();
        this.checkBorder();
        this.checkGesture();
        if ((this.state & 0x400) != 0x0) {
            this.setBackground();
        }
    }
    
    int defaultBackground() {
        if (OS.IsWinCE) {
            return OS.GetSysColor(OS.COLOR_WINDOW);
        }
        return OS.GetSysColor(OS.COLOR_BTNFACE);
    }
    
    int defaultFont() {
        return this.display.getSystemFont().handle;
    }
    
    int defaultForeground() {
        return OS.GetSysColor(OS.COLOR_WINDOWTEXT);
    }
    
    void deregister() {
        this.display.removeControl(this.handle);
    }
    
    void destroyWidget() {
        final int topHandle = this.topHandle();
        this.releaseHandle();
        if (topHandle != 0) {
            OS.DestroyWindow(topHandle);
        }
    }
    
    public boolean dragDetect(final Event event) {
        this.checkWidget();
        if (event == null) {
            this.error(4);
        }
        return this.dragDetect(event.button, event.count, event.stateMask, event.x, event.y);
    }
    
    public boolean dragDetect(final MouseEvent mouseEvent) {
        this.checkWidget();
        if (mouseEvent == null) {
            this.error(4);
        }
        return this.dragDetect(mouseEvent.button, mouseEvent.count, mouseEvent.stateMask, mouseEvent.x, mouseEvent.y);
    }
    
    boolean dragDetect(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n != 1 || n2 != 1) {
            return false;
        }
        final boolean dragDetect = this.dragDetect(this.handle, n4, n5, false, null, null);
        if (OS.GetKeyState(1) < 0 && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        if (!dragDetect) {
            if (n == 1 && OS.GetKeyState(27) >= 0) {
                int n6 = 0;
                if ((n3 & 0x40000) != 0x0) {
                    n6 |= 0x8;
                }
                if ((n3 & 0x20000) != 0x0) {
                    n6 |= 0x4;
                }
                if ((n3 & 0x10000) != 0x0) {
                    n6 |= 0x20;
                }
                if ((n3 & 0x80000) != 0x0) {
                    n6 |= 0x1;
                }
                if ((n3 & 0x100000) != 0x0) {
                    n6 |= 0x10;
                }
                if ((n3 & 0x200000) != 0x0) {
                    n6 |= 0x2;
                }
                if ((n3 & 0x800000) != 0x0) {
                    n6 |= 0x20;
                }
                if ((n3 & 0x2000000) != 0x0) {
                    n6 |= 0x40;
                }
                OS.SendMessage(this.handle, 514, n6, OS.MAKELPARAM(n4, n5));
            }
            return false;
        }
        return this.sendDragEvent(n, n3, n4, n5);
    }
    
    void drawBackground(final int n) {
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        this.drawBackground(n, rect);
    }
    
    void drawBackground(final int n, final RECT rect) {
        this.drawBackground(n, rect, -1, 0, 0);
    }
    
    void drawBackground(final int n, final RECT rect, int n2, final int n3, final int n4) {
        final Control backgroundControl = this.findBackgroundControl();
        if (backgroundControl != null) {
            if (backgroundControl.backgroundImage != null) {
                this.fillImageBackground(n, backgroundControl, rect, n3, n4);
                return;
            }
            n2 = backgroundControl.getBackgroundPixel();
        }
        if (n2 == -1 && (this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final Control themeControl = this.findThemeControl();
            if (themeControl != null) {
                this.fillThemeBackground(n, themeControl, rect);
                return;
            }
        }
        if (n2 == -1) {
            n2 = this.getBackgroundPixel();
        }
        this.fillBackground(n, n2, rect);
    }
    
    void drawImageBackground(final int n, final int n2, final int n3, final RECT rect, final int n4, final int n5) {
        final RECT rect2 = new RECT();
        OS.GetClientRect(n2, rect2);
        OS.MapWindowPoints(n2, this.handle, rect2, 2);
        final int brush = this.findBrush(n3, 3);
        final POINT point = new POINT();
        OS.GetWindowOrgEx(n, point);
        OS.SetBrushOrgEx(n, -rect2.left - point.x - n4, -rect2.top - point.y - n5, point);
        final int selectObject = OS.SelectObject(n, brush);
        OS.PatBlt(n, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 15728673);
        OS.SetBrushOrgEx(n, point.x, point.y, null);
        OS.SelectObject(n, selectObject);
    }
    
    void drawThemeBackground(final int n, final int n2, final RECT rect) {
    }
    
    void enableDrag(final boolean b) {
    }
    
    void enableWidget(final boolean b) {
        OS.EnableWindow(this.handle, b);
    }
    
    void fillBackground(final int n, final int n2, final RECT rect) {
        if (rect.left > rect.right || rect.top > rect.bottom) {
            return;
        }
        final int hPalette = this.display.hPalette;
        if (hPalette != 0) {
            OS.SelectPalette(n, hPalette, false);
            OS.RealizePalette(n);
        }
        OS.FillRect(n, rect, this.findBrush(n2, 0));
    }
    
    void fillImageBackground(final int n, final Control control, final RECT rect, final int n2, final int n3) {
        if (rect.left > rect.right || rect.top > rect.bottom) {
            return;
        }
        if (control != null) {
            final Image backgroundImage = control.backgroundImage;
            if (backgroundImage != null) {
                control.drawImageBackground(n, this.handle, backgroundImage.handle, rect, n2, n3);
            }
        }
    }
    
    void fillThemeBackground(final int n, final Control control, final RECT rect) {
        if (rect.left > rect.right || rect.top > rect.bottom) {
            return;
        }
        if (control != null) {
            control.drawThemeBackground(n, this.handle, rect);
        }
    }
    
    Control findBackgroundControl() {
        if (this.background != -1 || this.backgroundImage != null) {
            return this;
        }
        return ((this.state & 0x400) != 0x0) ? this.parent.findBackgroundControl() : null;
    }
    
    int findBrush(final int n, final int n2) {
        return this.parent.findBrush(n, n2);
    }
    
    Cursor findCursor() {
        if (this.cursor != null) {
            return this.cursor;
        }
        return this.parent.findCursor();
    }
    
    Control findImageControl() {
        final Control backgroundControl = this.findBackgroundControl();
        return (backgroundControl != null && backgroundControl.backgroundImage != null) ? backgroundControl : null;
    }
    
    Control findThemeControl() {
        return (this.background == -1 && this.backgroundImage == null) ? this.parent.findThemeControl() : null;
    }
    
    Menu[] findMenus(final Control control) {
        if (this.menu != null && this != control) {
            return new Menu[] { this.menu };
        }
        return new Menu[0];
    }
    
    char findMnemonic(final String s) {
        int n = 0;
        final int length = s.length();
        while (true) {
            if (n >= length || s.charAt(n) == '&') {
                if (++n >= length) {
                    return '\0';
                }
                if (s.charAt(n) != '&') {
                    return s.charAt(n);
                }
                if (++n >= length) {
                    return '\0';
                }
                continue;
            }
            else {
                ++n;
            }
        }
    }
    
    void fixChildren(final Shell shell, final Shell shell2, final Decorations decorations, final Decorations decorations2, final Menu[] array) {
        shell2.fixShell(shell, this);
        decorations2.fixDecorations(decorations, this, array);
    }
    
    void fixFocus(final Control savedFocus) {
        final Shell shell = this.getShell();
        Control parent = this;
        final Display display = this.display;
        final boolean fixFocus = display.fixFocus;
        display.fixFocus = true;
        try {
            while (parent != shell) {
                if ((parent = parent.parent) == null) {
                    break;
                }
                if (parent.setFocus()) {
                    return;
                }
            }
        }
        finally {
            display.fixFocus = fixFocus;
        }
        display.fixFocus = fixFocus;
        shell.setSavedFocus(savedFocus);
        OS.SetFocus(0);
    }
    
    public boolean forceFocus() {
        this.checkWidget();
        if (this.display.focusEvent == 16) {
            return false;
        }
        final Decorations menuShell = this.menuShell();
        menuShell.setSavedFocus(this);
        if (!this.isEnabled() || !this.isVisible() || !this.isActive()) {
            return false;
        }
        if (this.isFocusControl()) {
            return true;
        }
        menuShell.setSavedFocus(null);
        OS.SetFocus(this.handle);
        if (this.isDisposed()) {
            return false;
        }
        menuShell.setSavedFocus(this);
        return this.isFocusControl();
    }
    
    void forceResize() {
        if (this.parent == null) {
            return;
        }
        final WINDOWPOS[] lpwp = this.parent.lpwp;
        if (lpwp == null) {
            return;
        }
        for (int i = 0; i < lpwp.length; ++i) {
            final WINDOWPOS windowpos = lpwp[i];
            if (windowpos != null && windowpos.hwnd == this.handle) {
                this.SetWindowPos(windowpos.hwnd, 0, windowpos.x, windowpos.y, windowpos.cx, windowpos.cy, windowpos.flags);
                lpwp[i] = null;
                return;
            }
        }
    }
    
    public Accessible getAccessible() {
        this.checkWidget();
        if (this.accessible == null) {
            this.accessible = this.new_Accessible(this);
        }
        return this.accessible;
    }
    
    public Color getBackground() {
        this.checkWidget();
        Control backgroundControl = this.findBackgroundControl();
        if (backgroundControl == null) {
            backgroundControl = this;
        }
        return Color.win32_new(this.display, backgroundControl.getBackgroundPixel());
    }
    
    public Image getBackgroundImage() {
        this.checkWidget();
        Control backgroundControl = this.findBackgroundControl();
        if (backgroundControl == null) {
            backgroundControl = this;
        }
        return backgroundControl.backgroundImage;
    }
    
    int getBackgroundPixel() {
        return (this.background != -1) ? this.background : this.defaultBackground();
    }
    
    public int getBorderWidth() {
        this.checkWidget();
        final int borderHandle = this.borderHandle();
        final int getWindowLong = OS.GetWindowLong(borderHandle, -20);
        if ((getWindowLong & 0x200) != 0x0) {
            return OS.GetSystemMetrics(45);
        }
        if ((getWindowLong & 0x20000) != 0x0) {
            return OS.GetSystemMetrics(5);
        }
        if ((OS.GetWindowLong(borderHandle, -16) & 0x800000) != 0x0) {
            return OS.GetSystemMetrics(5);
        }
        return 0;
    }
    
    public Rectangle getBounds() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.topHandle(), rect);
        OS.MapWindowPoints(0, (this.parent == null) ? 0 : this.parent.handle, rect, 2);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    int getCodePage() {
        if (OS.IsUnicode) {
            return 0;
        }
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        final LOGFONT logfont = OS.IsUnicode ? new LOGFONTW() : new LOGFONTA();
        OS.GetObject(sendMessage, LOGFONT.sizeof, logfont);
        final int n = logfont.lfCharSet & 0xFF;
        final int[] array = new int[8];
        if (OS.TranslateCharsetInfo(n, array, 1)) {
            return array[1];
        }
        return OS.GetACP();
    }
    
    String getClipboardText() {
        String string = "";
        if (OS.OpenClipboard(0)) {
            final int getClipboardData = OS.GetClipboardData(OS.IsUnicode ? 13 : 1);
            if (getClipboardData != 0) {
                final int n = OS.GlobalSize(getClipboardData) / TCHAR.sizeof * TCHAR.sizeof;
                final int globalLock = OS.GlobalLock(getClipboardData);
                if (globalLock != 0) {
                    final TCHAR tchar = new TCHAR(0, n / TCHAR.sizeof);
                    OS.MoveMemory(tchar, globalLock, n);
                    string = tchar.toString(0, tchar.strlen());
                    OS.GlobalUnlock(getClipboardData);
                }
            }
            OS.CloseClipboard();
        }
        return string;
    }
    
    public Cursor getCursor() {
        this.checkWidget();
        return this.cursor;
    }
    
    public boolean getDragDetect() {
        this.checkWidget();
        return (this.state & 0x8000) != 0x0;
    }
    
    boolean getDrawing() {
        return this.drawCount <= 0;
    }
    
    public boolean getEnabled() {
        this.checkWidget();
        return OS.IsWindowEnabled(this.handle);
    }
    
    public Font getFont() {
        this.checkWidget();
        if (this.font != null) {
            return this.font;
        }
        int n = OS.SendMessage(this.handle, 49, 0, 0);
        if (n == 0) {
            n = this.defaultFont();
        }
        return Font.win32_new(this.display, n);
    }
    
    public Color getForeground() {
        this.checkWidget();
        return Color.win32_new(this.display, this.getForegroundPixel());
    }
    
    int getForegroundPixel() {
        return (this.foreground != -1) ? this.foreground : this.defaultForeground();
    }
    
    public Object getLayoutData() {
        this.checkWidget();
        return this.layoutData;
    }
    
    public Point getLocation() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.topHandle(), rect);
        OS.MapWindowPoints(0, (this.parent == null) ? 0 : this.parent.handle, rect, 2);
        return new Point(rect.left, rect.top);
    }
    
    public Menu getMenu() {
        this.checkWidget();
        return this.menu;
    }
    
    public Monitor getMonitor() {
        this.checkWidget();
        if (OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return this.display.getPrimaryMonitor();
        }
        final int monitorFromWindow = OS.MonitorFromWindow(this.handle, 2);
        final MONITORINFO monitorinfo = new MONITORINFO();
        monitorinfo.cbSize = MONITORINFO.sizeof;
        OS.GetMonitorInfo(monitorFromWindow, monitorinfo);
        final Monitor monitor = new Monitor();
        monitor.handle = monitorFromWindow;
        monitor.x = monitorinfo.rcMonitor_left;
        monitor.y = monitorinfo.rcMonitor_top;
        monitor.width = monitorinfo.rcMonitor_right - monitorinfo.rcMonitor_left;
        monitor.height = monitorinfo.rcMonitor_bottom - monitorinfo.rcMonitor_top;
        monitor.clientX = monitorinfo.rcWork_left;
        monitor.clientY = monitorinfo.rcWork_top;
        monitor.clientWidth = monitorinfo.rcWork_right - monitorinfo.rcWork_left;
        monitor.clientHeight = monitorinfo.rcWork_bottom - monitorinfo.rcWork_top;
        return monitor;
    }
    
    public int getOrientation() {
        this.checkWidget();
        return this.style & 0x6000000;
    }
    
    public Composite getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    Control[] getPath() {
        int n = 0;
        final Shell shell = this.getShell();
        for (Control parent = this; parent != shell; parent = parent.parent) {
            ++n;
        }
        Control parent2 = this;
        final Control[] array = new Control[n];
        while (parent2 != shell) {
            array[--n] = parent2;
            parent2 = parent2.parent;
        }
        return array;
    }
    
    public Region getRegion() {
        this.checkWidget();
        return this.region;
    }
    
    public Shell getShell() {
        this.checkWidget();
        return this.parent.getShell();
    }
    
    public Point getSize() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetWindowRect(this.topHandle(), rect);
        return new Point(rect.right - rect.left, rect.bottom - rect.top);
    }
    
    public String getToolTipText() {
        this.checkWidget();
        return this.toolTipText;
    }
    
    public boolean getVisible() {
        this.checkWidget();
        if (!this.getDrawing()) {
            return (this.state & 0x10) == 0x0;
        }
        return (OS.GetWindowLong(this.handle, -16) & 0x10000000) != 0x0;
    }
    
    boolean hasCursor() {
        final RECT rect = new RECT();
        if (!OS.GetClientRect(this.handle, rect)) {
            return false;
        }
        OS.MapWindowPoints(this.handle, 0, rect, 2);
        final POINT point = new POINT();
        return OS.GetCursorPos(point) && OS.PtInRect(rect, point);
    }
    
    boolean hasFocus() {
        for (int i = OS.GetFocus(); i != 0; i = OS.GetParent(i)) {
            if (i == this.handle) {
                return true;
            }
            if (this.display.getControl(i) != null) {
                return false;
            }
        }
        return false;
    }
    
    public int internal_new_GC(final GCData gcData) {
        this.checkWidget();
        int hwnd = this.handle;
        if (gcData != null && gcData.hwnd != 0) {
            hwnd = gcData.hwnd;
        }
        if (gcData != null) {
            gcData.hwnd = hwnd;
        }
        int n;
        if (gcData == null || gcData.ps == null) {
            n = OS.GetDC(hwnd);
        }
        else {
            n = OS.BeginPaint(hwnd, gcData.ps);
        }
        if (n == 0) {
            SWT.error(2);
        }
        if (gcData != null) {
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
                if ((gcData.style & 0x6000000) != 0x0) {
                    gcData.layout = (((gcData.style & 0x4000000) != 0x0) ? 1 : 0);
                }
                else if ((OS.GetLayout(n) & 0x1) != 0x0) {
                    gcData.style |= 0xC000000;
                }
                else {
                    gcData.style |= 0x2000000;
                }
            }
            else {
                gcData.style |= 0x2000000;
            }
            gcData.device = this.display;
            final int foregroundPixel = this.getForegroundPixel();
            if (foregroundPixel != OS.GetTextColor(n)) {
                gcData.foreground = foregroundPixel;
            }
            Control backgroundControl = this.findBackgroundControl();
            if (backgroundControl == null) {
                backgroundControl = this;
            }
            final int backgroundPixel = backgroundControl.getBackgroundPixel();
            if (backgroundPixel != OS.GetBkColor(n)) {
                gcData.background = backgroundPixel;
            }
            gcData.font = ((this.font != null) ? this.font : Font.win32_new(this.display, OS.SendMessage(hwnd, 49, 0, 0)));
            gcData.uiState = OS.SendMessage(hwnd, 297, 0, 0);
        }
        return n;
    }
    
    public void internal_dispose_GC(final int n, final GCData gcData) {
        this.checkWidget();
        int n2 = this.handle;
        if (gcData != null && gcData.hwnd != 0) {
            n2 = gcData.hwnd;
        }
        if (gcData == null || gcData.ps == null) {
            OS.ReleaseDC(n2, n);
        }
        else {
            OS.EndPaint(n2, gcData.ps);
        }
    }
    
    boolean isActive() {
        final Dialog modalDialog = this.display.getModalDialog();
        if (modalDialog != null) {
            final Shell parent = modalDialog.parent;
            if (parent != null && !parent.isDisposed() && parent != this.getShell()) {
                return false;
            }
        }
        Shell shell = null;
        final Shell[] modalShells = this.display.modalShells;
        if (modalShells != null) {
            final int n = 196608;
            int length = modalShells.length;
            while (--length >= 0) {
                final Shell shell2 = modalShells[length];
                if (shell2 != null) {
                    if ((shell2.style & n) != 0x0) {
                        Control parent2;
                        for (parent2 = this; parent2 != null && parent2 != shell2; parent2 = parent2.parent) {}
                        if (parent2 != shell2) {
                            return false;
                        }
                        break;
                    }
                    else {
                        if ((shell2.style & 0x8000) == 0x0) {
                            continue;
                        }
                        if (shell == null) {
                            shell = this.getShell();
                        }
                        if (shell2.parent == shell) {
                            return false;
                        }
                        continue;
                    }
                }
            }
        }
        if (shell == null) {
            shell = this.getShell();
        }
        return shell.getEnabled();
    }
    
    public boolean isEnabled() {
        this.checkWidget();
        return this.getEnabled() && this.parent.isEnabled();
    }
    
    public boolean isFocusControl() {
        this.checkWidget();
        final Control focusControl = this.display.focusControl;
        if (focusControl != null && !focusControl.isDisposed()) {
            return this == focusControl;
        }
        return this.hasFocus();
    }
    
    boolean isFocusAncestor(Control parent) {
        while (parent != null && parent != this && !(parent instanceof Shell)) {
            parent = parent.parent;
        }
        return parent == this;
    }
    
    public boolean isReparentable() {
        this.checkWidget();
        return true;
    }
    
    boolean isShowing() {
        if (!this.isVisible()) {
            return false;
        }
        for (Control parent = this; parent != null; parent = parent.parent) {
            final Point size = parent.getSize();
            if (size.x == 0 || size.y == 0) {
                return false;
            }
        }
        return true;
    }
    
    boolean isTabGroup() {
        final Control[] getTabList = this.parent._getTabList();
        if (getTabList != null) {
            for (int i = 0; i < getTabList.length; ++i) {
                if (getTabList[i] == this) {
                    return true;
                }
            }
        }
        return (OS.GetWindowLong(this.handle, -16) & 0x10000) != 0x0;
    }
    
    boolean isTabItem() {
        final Control[] getTabList = this.parent._getTabList();
        if (getTabList != null) {
            for (int i = 0; i < getTabList.length; ++i) {
                if (getTabList[i] == this) {
                    return false;
                }
            }
        }
        if ((OS.GetWindowLong(this.handle, -16) & 0x10000) != 0x0) {
            return false;
        }
        final int sendMessage = OS.SendMessage(this.handle, 135, 0, 0);
        return (sendMessage & 0x100) == 0x0 && (sendMessage & 0x4) == 0x0 && (sendMessage & 0x1) == 0x0 && (sendMessage & 0x2) == 0x0;
    }
    
    public boolean isTouchEnabled() {
        this.checkWidget();
        return OS.IsTouchWindow(this.handle, null);
    }
    
    public boolean isVisible() {
        this.checkWidget();
        return OS.IsWindowVisible(this.handle) || (this.getVisible() && this.parent.isVisible());
    }
    
    void mapEvent(final int n, final Event event) {
        if (n != this.handle) {
            final POINT point = new POINT();
            point.x = event.x;
            point.y = event.y;
            OS.MapWindowPoints(n, this.handle, point, 1);
            event.x = point.x;
            event.y = point.y;
        }
    }
    
    void markLayout(final boolean b, final boolean b2) {
    }
    
    Decorations menuShell() {
        return this.parent.menuShell();
    }
    
    boolean mnemonicHit(final char c) {
        return false;
    }
    
    boolean mnemonicMatch(final char c) {
        return false;
    }
    
    public void moveAbove(final Control control) {
        this.checkWidget();
        final int topHandle = this.topHandle();
        int getWindow = 0;
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (this.parent != control.parent) {
                return;
            }
            final int topHandle2 = control.topHandle();
            if (topHandle2 == 0 || topHandle2 == topHandle) {
                return;
            }
            getWindow = OS.GetWindow(topHandle2, 3);
            if (getWindow == 0 || getWindow == topHandle2) {
                getWindow = 0;
            }
        }
        this.SetWindowPos(topHandle, getWindow, 0, 0, 0, 0, 19);
    }
    
    public void moveBelow(final Control control) {
        this.checkWidget();
        final int topHandle = this.topHandle();
        int n = 1;
        if (control != null) {
            if (control.isDisposed()) {
                this.error(5);
            }
            if (this.parent != control.parent) {
                return;
            }
            n = control.topHandle();
        }
        else if (this == this.getShell() && this.parent != null) {
            int handle;
            int n2;
            for (n2 = (handle = this.parent.handle), n = OS.GetWindow(handle, 3); n != 0 && n != handle && OS.GetWindow(n, 4) != n2; n = OS.GetWindow(handle = n, 3)) {}
            if (n == handle) {
                return;
            }
        }
        if (n == 0 || n == topHandle) {
            return;
        }
        this.SetWindowPos(topHandle, n, 0, 0, 0, 0, 19);
    }
    
    Accessible new_Accessible(final Control control) {
        return Accessible.internal_new_Accessible(this);
    }
    
    GC new_GC(final GCData gcData) {
        return GC.win32_new(this, gcData);
    }
    
    public void pack() {
        this.checkWidget();
        this.pack(true);
    }
    
    public void pack(final boolean b) {
        this.checkWidget();
        this.setSize(this.computeSize(-1, -1, b));
    }
    
    public boolean print(final GC gc) {
        this.checkWidget();
        if (gc == null) {
            this.error(4);
        }
        if (gc.isDisposed()) {
            this.error(5);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            final int topHandle = this.topHandle();
            int n = gc.handle;
            int saveDC = 0;
            final int gdipGraphics = gc.getGCData().gdipGraphics;
            if (gdipGraphics != 0) {
                int region_GetHRGN = 0;
                Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 3);
                final int region_new = Gdip.Region_new();
                if (region_new == 0) {
                    SWT.error(2);
                }
                Gdip.Graphics_GetClip(gdipGraphics, region_new);
                if (!Gdip.Region_IsInfinite(region_new, gdipGraphics)) {
                    region_GetHRGN = Gdip.Region_GetHRGN(region_new, gdipGraphics);
                }
                Gdip.Region_delete(region_new);
                Gdip.Graphics_SetPixelOffsetMode(gdipGraphics, 4);
                float[] array = null;
                final int matrix_new = Gdip.Matrix_new(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f);
                if (matrix_new == 0) {
                    SWT.error(2);
                }
                Gdip.Graphics_GetTransform(gdipGraphics, matrix_new);
                if (!Gdip.Matrix_IsIdentity(matrix_new)) {
                    array = new float[6];
                    Gdip.Matrix_GetElements(matrix_new, array);
                }
                Gdip.Matrix_delete(matrix_new);
                n = Gdip.Graphics_GetHDC(gdipGraphics);
                saveDC = OS.SaveDC(n);
                if (array != null) {
                    OS.SetGraphicsMode(n, 2);
                    OS.SetWorldTransform(n, array);
                }
                if (region_GetHRGN != 0) {
                    OS.SelectClipRgn(n, region_GetHRGN);
                    OS.DeleteObject(region_GetHRGN);
                }
            }
            if (OS.IsWinCE) {
                OS.UpdateWindow(topHandle);
            }
            else {
                OS.RedrawWindow(topHandle, null, 0, 384);
            }
            this.printWidget(topHandle, n, gc);
            if (gdipGraphics != 0) {
                OS.RestoreDC(n, saveDC);
                Gdip.Graphics_ReleaseHDC(gdipGraphics, n);
            }
            return true;
        }
        return false;
    }
    
    void printWidget(final int n, final int n2, final GC gc) {
        boolean printWindow = false;
        if (OS.GetDeviceCaps(gc.handle, 2) != 2) {
            int n4;
            int n3;
            for (n3 = (n4 = OS.GetParent(n)); OS.GetParent(n4) != 0 && OS.GetWindow(n4, 4) == 0; n4 = OS.GetParent(n4)) {}
            final RECT rect = new RECT();
            OS.GetWindowRect(n, rect);
            int n5 = OS.IsWindowVisible(n) ? 0 : 1;
            if (n5 == 0) {
                final RECT rect2 = new RECT();
                OS.GetWindowRect(n4, rect2);
                OS.IntersectRect(rect2, rect, rect2);
                n5 = (OS.EqualRect(rect2, rect) ? 0 : 1);
            }
            if (n5 == 0) {
                final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
                for (int n6 = OS.GetParent(n); n6 != n4 && n5 == 0; n6 = OS.GetParent(n6)) {
                    if (OS.GetWindowRgn(n6, createRectRgn) != 0) {
                        n5 = 1;
                    }
                }
                OS.DeleteObject(createRectRgn);
            }
            final int getWindowLong = OS.GetWindowLong(n, -16);
            final int getWindowLong2 = OS.GetWindowLong(n, -20);
            int getWindow = OS.GetWindow(n, 3);
            if (getWindow == 0 || getWindow == n) {
                getWindow = 0;
            }
            if (n5 != 0) {
                final int getSystemMetrics = OS.GetSystemMetrics(76);
                final int getSystemMetrics2 = OS.GetSystemMetrics(77);
                final int getSystemMetrics3 = OS.GetSystemMetrics(78);
                final int getSystemMetrics4 = OS.GetSystemMetrics(79);
                final int n7 = 53;
                if ((getWindowLong & 0x10000000) != 0x0) {
                    OS.DefWindowProc(n, 11, 0, 0);
                }
                this.SetWindowPos(n, 0, getSystemMetrics + getSystemMetrics3, getSystemMetrics2 + getSystemMetrics4, 0, 0, n7);
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    OS.SetWindowLong(n, -16, (getWindowLong & 0xBFFFFFFF) | Integer.MIN_VALUE);
                    OS.SetWindowLong(n, -20, getWindowLong2 | 0x80);
                }
                final Shell shell = this.getShell();
                final Control savedFocus = shell.savedFocus;
                OS.SetParent(n, 0);
                shell.setSavedFocus(savedFocus);
                if ((getWindowLong & 0x10000000) != 0x0) {
                    OS.DefWindowProc(n, 11, 1, 0);
                }
            }
            if ((getWindowLong & 0x10000000) == 0x0) {
                OS.ShowWindow(n, 5);
            }
            printWindow = OS.PrintWindow(n, n2, 0);
            if ((getWindowLong & 0x10000000) == 0x0) {
                OS.ShowWindow(n, 0);
            }
            if (n5 != 0) {
                if ((getWindowLong & 0x10000000) != 0x0) {
                    OS.DefWindowProc(n, 11, 0, 0);
                }
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    OS.SetWindowLong(n, -16, getWindowLong);
                    OS.SetWindowLong(n, -20, getWindowLong2);
                }
                OS.SetParent(n, n3);
                OS.MapWindowPoints(0, n3, rect, 2);
                this.SetWindowPos(n, getWindow, rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top, 49);
                if ((getWindowLong & 0x10000000) != 0x0) {
                    OS.DefWindowProc(n, 11, 1, 0);
                }
            }
        }
        if (!printWindow) {
            OS.SendMessage(n, 791, n2, 30);
        }
    }
    
    public void redraw() {
        this.checkWidget();
        this.redraw(false);
    }
    
    void redraw(final boolean b) {
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        if (OS.IsWinCE) {
            OS.InvalidateRect(this.handle, null, true);
        }
        else {
            int n = 1029;
            if (b) {
                n |= 0x80;
            }
            OS.RedrawWindow(this.handle, null, 0, n);
        }
    }
    
    public void redraw(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.checkWidget();
        if (n3 <= 0 || n4 <= 0) {
            return;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return;
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        if (OS.IsWinCE) {
            OS.InvalidateRect(this.handle, rect, true);
        }
        else {
            int n5 = 1029;
            if (b) {
                n5 |= 0x80;
            }
            OS.RedrawWindow(this.handle, rect, 0, n5);
        }
    }
    
    boolean redrawChildren() {
        if (!OS.IsWindowVisible(this.handle)) {
            return false;
        }
        final Control backgroundControl = this.findBackgroundControl();
        if (backgroundControl == null) {
            if ((this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
                OS.InvalidateRect(this.handle, null, true);
                return true;
            }
        }
        else if (backgroundControl.backgroundImage != null) {
            OS.InvalidateRect(this.handle, null, true);
            return true;
        }
        return false;
    }
    
    void register() {
        this.display.addControl(this.handle, this);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.handle = 0;
        this.parent = null;
    }
    
    void releaseParent() {
        this.parent.removeControl(this);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (OS.IsDBLocale) {
            OS.ImmAssociateContext(this.handle, 0);
        }
        if (this.toolTipText != null) {
            this.setToolTipText(this.getShell(), null);
        }
        this.toolTipText = null;
        if (this.menu != null && !this.menu.isDisposed()) {
            this.menu.dispose();
        }
        this.backgroundImage = null;
        this.menu = null;
        this.cursor = null;
        this.unsubclass();
        this.deregister();
        this.layoutData = null;
        if (this.accessible != null) {
            this.accessible.internal_dispose_Accessible();
        }
        this.accessible = null;
        this.region = null;
        this.font = null;
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
    
    public void removeDragDetectListener(final DragDetectListener dragDetectListener) {
        this.checkWidget();
        if (dragDetectListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(29, dragDetectListener);
    }
    
    public void removeFocusListener(final FocusListener focusListener) {
        this.checkWidget();
        if (focusListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(15, focusListener);
        this.eventTable.unhook(16, focusListener);
    }
    
    public void removeGestureListener(final GestureListener gestureListener) {
        this.checkWidget();
        if (gestureListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(48, gestureListener);
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
    
    public void removeKeyListener(final KeyListener keyListener) {
        this.checkWidget();
        if (keyListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(2, keyListener);
        this.eventTable.unhook(1, keyListener);
    }
    
    public void removeMenuDetectListener(final MenuDetectListener menuDetectListener) {
        this.checkWidget();
        if (menuDetectListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(35, menuDetectListener);
    }
    
    public void removeMouseTrackListener(final MouseTrackListener mouseTrackListener) {
        this.checkWidget();
        if (mouseTrackListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(6, mouseTrackListener);
        this.eventTable.unhook(7, mouseTrackListener);
        this.eventTable.unhook(32, mouseTrackListener);
    }
    
    public void removeMouseListener(final MouseListener mouseListener) {
        this.checkWidget();
        if (mouseListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(3, mouseListener);
        this.eventTable.unhook(4, mouseListener);
        this.eventTable.unhook(8, mouseListener);
    }
    
    public void removeMouseMoveListener(final MouseMoveListener mouseMoveListener) {
        this.checkWidget();
        if (mouseMoveListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(5, mouseMoveListener);
    }
    
    public void removeMouseWheelListener(final MouseWheelListener mouseWheelListener) {
        this.checkWidget();
        if (mouseWheelListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(37, mouseWheelListener);
    }
    
    public void removePaintListener(final PaintListener paintListener) {
        this.checkWidget();
        if (paintListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(9, paintListener);
    }
    
    public void removeTouchListener(final TouchListener touchListener) {
        this.checkWidget();
        if (touchListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(47, touchListener);
    }
    
    public void removeTraverseListener(final TraverseListener traverseListener) {
        this.checkWidget();
        if (traverseListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(31, traverseListener);
    }
    
    void showWidget(final boolean b) {
        final int topHandle = this.topHandle();
        OS.ShowWindow(topHandle, b ? 5 : 0);
        if (this.handle != topHandle) {
            OS.ShowWindow(this.handle, b ? 5 : 0);
        }
    }
    
    boolean sendFocusEvent(final int focusEvent) {
        final Shell shell = this.getShell();
        final Display display = this.display;
        display.focusEvent = focusEvent;
        (display.focusControl = this).sendEvent(focusEvent);
        display.focusEvent = 0;
        display.focusControl = null;
        if (!shell.isDisposed()) {
            switch (focusEvent) {
                case 15: {
                    shell.setActiveControl(this);
                    break;
                }
                case 16: {
                    if (shell != display.getActiveShell()) {
                        shell.setActiveControl(null);
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    boolean sendGestureEvent(final GESTUREINFO gestureinfo) {
        if (gestureinfo.hwndTarget != this.handle) {
            return true;
        }
        final Event event = new Event();
        int n = 0;
        final Point control = this.toControl(new Point(gestureinfo.x, gestureinfo.y));
        event.x = control.x;
        event.y = control.y;
        switch (gestureinfo.dwID) {
            case 3: {
                n = 48;
                event.detail = 32;
                final int lodword = OS.LODWORD(gestureinfo.ullArguments);
                if ((gestureinfo.dwFlags & 0x1) != 0x0) {
                    event.detail = 2;
                    final Display display = this.display;
                    final Display display2 = this.display;
                    final double n2 = lodword;
                    display2.lastDistance = n2;
                    display.magStartDistance = n2;
                }
                else if ((gestureinfo.dwFlags & 0x4) != 0x0) {
                    event.detail = 4;
                }
                if (lodword == this.display.lastDistance && event.detail == 32) {
                    return true;
                }
                if (lodword != 0) {
                    event.magnification = lodword / this.display.magStartDistance;
                }
                this.display.lastDistance = lodword;
                break;
            }
            case 4: {
                n = 48;
                event.detail = 64;
                if ((gestureinfo.dwFlags & 0x1) != 0x0) {
                    event.detail = 2;
                    this.display.lastX = control.x;
                    this.display.lastY = control.y;
                }
                else if ((gestureinfo.dwFlags & 0x4) != 0x0) {
                    event.detail = 4;
                }
                if (this.display.lastX == control.x && this.display.lastY == control.y && event.detail == 64) {
                    return true;
                }
                event.xDirection = control.x - this.display.lastX;
                event.yDirection = control.y - this.display.lastY;
                this.display.lastX = control.x;
                this.display.lastY = control.y;
                break;
            }
            case 5: {
                n = 48;
                event.detail = 8;
                final double gid_ROTATE_ANGLE_FROM_ARGUMENT = OS.GID_ROTATE_ANGLE_FROM_ARGUMENT(OS.LODWORD(gestureinfo.ullArguments));
                if ((gestureinfo.dwFlags & 0x1) != 0x0) {
                    event.detail = 2;
                    this.display.rotationAngle = gid_ROTATE_ANGLE_FROM_ARGUMENT;
                }
                else if ((gestureinfo.dwFlags & 0x4) != 0x0) {
                    event.detail = 4;
                }
                if (this.display.rotationAngle == gid_ROTATE_ANGLE_FROM_ARGUMENT && event.detail == 8) {
                    return true;
                }
                event.rotation = gid_ROTATE_ANGLE_FROM_ARGUMENT * 180.0 / Compatibility.PI;
                this.display.rotationAngle = gid_ROTATE_ANGLE_FROM_ARGUMENT;
                break;
            }
        }
        if (n == 0) {
            return true;
        }
        this.setInputState(event, n);
        this.sendEvent(n, event);
        return event.doit;
    }
    
    void sendMove() {
        this.sendEvent(10);
    }
    
    void sendResize() {
        this.sendEvent(11);
    }
    
    void sendTouchEvent(final TOUCHINPUT[] array) {
        final Event event = new Event();
        final POINT point = new POINT();
        OS.GetCursorPos(point);
        OS.ScreenToClient(this.handle, point);
        event.x = point.x;
        event.y = point.y;
        final Touch[] touches = new Touch[array.length];
        final Monitor monitor = this.getMonitor();
        for (int i = 0; i < array.length; ++i) {
            final TOUCHINPUT touchinput = array[i];
            final TouchSource touchSource = this.display.findTouchSource(touchinput.hSource, monitor);
            int n = 0;
            if ((touchinput.dwFlags & 0x2) != 0x0) {
                n = 1;
            }
            if ((touchinput.dwFlags & 0x4) != 0x0) {
                n = 4;
            }
            if ((touchinput.dwFlags & 0x1) != 0x0) {
                n = 2;
            }
            touches[i] = new Touch(touchinput.dwID, touchSource, n, (touchinput.dwFlags & 0x10) != 0x0, (int)OS.TOUCH_COORD_TO_PIXEL(touchinput.x), (int)OS.TOUCH_COORD_TO_PIXEL(touchinput.y));
        }
        event.touches = touches;
        this.setInputState(event, 47);
        this.postEvent(47, event);
    }
    
    void setBackground() {
        Control backgroundControl = this.findBackgroundControl();
        if (backgroundControl == null) {
            backgroundControl = this;
        }
        if (backgroundControl.backgroundImage != null) {
            this.getShell().releaseBrushes();
            this.setBackgroundImage(backgroundControl.backgroundImage.handle);
        }
        else {
            this.setBackgroundPixel((backgroundControl.background == -1) ? backgroundControl.defaultBackground() : backgroundControl.background);
        }
    }
    
    public void setBackground(final Color color) {
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
        this.updateBackgroundColor();
    }
    
    public void setBackgroundImage(final Image backgroundImage) {
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
        this.getShell().releaseBrushes();
        this.updateBackgroundImage();
    }
    
    void setBackgroundImage(final int n) {
        if (OS.IsWinCE) {
            OS.InvalidateRect(this.handle, null, true);
        }
        else {
            OS.RedrawWindow(this.handle, null, 0, 1029);
        }
    }
    
    void setBackgroundPixel(final int n) {
        if (OS.IsWinCE) {
            OS.InvalidateRect(this.handle, null, true);
        }
        else {
            OS.RedrawWindow(this.handle, null, 0, 1029);
        }
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        this.setBounds(n, n2, Math.max(0, n3), Math.max(0, n4), 52);
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.setBounds(n, n2, n3, n4, n5, true);
    }
    
    void setBounds(final int x, final int y, final int cx, final int cy, int flags, final boolean b) {
        if (this.findImageControl() != null) {
            if (this.backgroundImage == null) {
                flags |= 0x100;
            }
        }
        else if (OS.GetWindow(this.handle, 5) == 0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.findThemeControl() != null) {
            flags |= 0x100;
        }
        final int topHandle = this.topHandle();
        if (b && this.parent != null) {
            this.forceResize();
            if (this.parent.lpwp != null) {
                int n;
                WINDOWPOS[] lpwp;
                for (n = 0, lpwp = this.parent.lpwp; n < lpwp.length && lpwp[n] != null; ++n) {}
                if (n == lpwp.length) {
                    final WINDOWPOS[] lpwp2 = new WINDOWPOS[lpwp.length + 4];
                    System.arraycopy(lpwp, 0, lpwp2, 0, lpwp.length);
                    lpwp = (this.parent.lpwp = lpwp2);
                }
                final WINDOWPOS windowpos = new WINDOWPOS();
                windowpos.hwnd = topHandle;
                windowpos.x = x;
                windowpos.y = y;
                windowpos.cx = cx;
                windowpos.cy = cy;
                windowpos.flags = flags;
                lpwp[n] = windowpos;
                return;
            }
        }
        this.SetWindowPos(topHandle, 0, x, y, cx, cy, flags);
    }
    
    public void setBounds(final Rectangle rectangle) {
        this.checkWidget();
        if (rectangle == null) {
            this.error(4);
        }
        this.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void setCapture(final boolean b) {
        this.checkWidget();
        if (b) {
            OS.SetCapture(this.handle);
        }
        else if (OS.GetCapture() == this.handle) {
            OS.ReleaseCapture();
        }
    }
    
    void setCursor() {
        OS.SendMessage(this.handle, 32, this.handle, OS.MAKELPARAM(1, 512));
    }
    
    public void setCursor(final Cursor cursor) {
        this.checkWidget();
        if (cursor != null && cursor.isDisposed()) {
            SWT.error(5);
        }
        this.cursor = cursor;
        if (OS.IsWinCE) {
            OS.SetCursor((cursor != null) ? cursor.handle : 0);
            return;
        }
        int getCapture = OS.GetCapture();
        if (getCapture == 0) {
            final POINT point = new POINT();
            if (!OS.GetCursorPos(point)) {
                return;
            }
            int n;
            for (getCapture = (n = OS.WindowFromPoint(point)); n != 0 && n != this.handle; n = OS.GetParent(n)) {}
            if (n == 0) {
                return;
            }
        }
        Control control = this.display.getControl(getCapture);
        if (control == null) {
            control = this;
        }
        control.setCursor();
    }
    
    void setDefaultFont() {
        OS.SendMessage(this.handle, 48, this.display.getSystemFont().handle, 0);
    }
    
    public void setDragDetect(final boolean b) {
        this.checkWidget();
        if (b) {
            this.state |= 0x8000;
        }
        else {
            this.state &= 0xFFFF7FFF;
        }
        this.enableDrag(b);
    }
    
    public void setEnabled(final boolean b) {
        this.checkWidget();
        Control focusControl = null;
        boolean focusAncestor = false;
        if (!b && this.display.focusEvent != 16) {
            focusControl = this.display.getFocusControl();
            focusAncestor = this.isFocusAncestor(focusControl);
        }
        this.enableWidget(b);
        if (focusAncestor) {
            this.fixFocus(focusControl);
        }
    }
    
    public boolean setFocus() {
        this.checkWidget();
        return (this.style & 0x80000) == 0x0 && this.forceFocus();
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        int n = 0;
        if (font != null) {
            if (font.isDisposed()) {
                SWT.error(5);
            }
            n = font.handle;
        }
        this.font = font;
        if (n == 0) {
            n = this.defaultFont();
        }
        OS.SendMessage(this.handle, 48, n, 1);
    }
    
    public void setForeground(final Color color) {
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
        this.setForegroundPixel(this.foreground = handle);
    }
    
    void setForegroundPixel(final int n) {
        OS.InvalidateRect(this.handle, null, true);
    }
    
    public void setLayoutData(final Object layoutData) {
        this.checkWidget();
        this.layoutData = layoutData;
    }
    
    public void setLocation(final int n, final int n2) {
        this.checkWidget();
        int n3 = 21;
        if (!OS.IsWinCE) {
            n3 |= 0x20;
        }
        this.setBounds(n, n2, 0, 0, n3);
    }
    
    public void setLocation(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setLocation(point.x, point.y);
    }
    
    public void setMenu(final Menu menu) {
        this.checkWidget();
        if (menu != null) {
            if (menu.isDisposed()) {
                SWT.error(5);
            }
            if ((menu.style & 0x8) == 0x0) {
                this.error(37);
            }
            if (menu.parent != this.menuShell()) {
                this.error(32);
            }
        }
        this.menu = menu;
    }
    
    public void setOrientation(final int n) {
        this.checkWidget();
        if (OS.IsWinCE) {
            return;
        }
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return;
        }
        final int n2 = 100663296;
        if ((n & n2) == 0x0 || (n & n2) == n2) {
            return;
        }
        this.style &= 0xF7FFFFFF;
        this.style &= ~n2;
        this.style |= (n & n2);
        this.updateOrientation();
        this.checkMirrored();
    }
    
    boolean setRadioFocus(final boolean b) {
        return false;
    }
    
    boolean setRadioSelection(final boolean b) {
        return false;
    }
    
    public void setRedraw(final boolean b) {
        this.checkWidget();
        if (this.drawCount == 0 && (OS.GetWindowLong(this.handle, -16) & 0x10000000) == 0x0) {
            this.state |= 0x10;
        }
        if (b) {
            if (--this.drawCount == 0) {
                final int topHandle = this.topHandle();
                OS.SendMessage(topHandle, 11, 1, 0);
                if (this.handle != topHandle) {
                    OS.SendMessage(this.handle, 11, 1, 0);
                }
                if ((this.state & 0x10) != 0x0) {
                    this.state &= 0xFFFFFFEF;
                    OS.ShowWindow(topHandle, 0);
                    if (this.handle != topHandle) {
                        OS.ShowWindow(this.handle, 0);
                    }
                }
                else if (OS.IsWinCE) {
                    OS.InvalidateRect(topHandle, null, true);
                    if (this.handle != topHandle) {
                        OS.InvalidateRect(this.handle, null, true);
                    }
                }
                else {
                    OS.RedrawWindow(topHandle, null, 0, 1157);
                }
            }
        }
        else if (this.drawCount++ == 0) {
            final int topHandle2 = this.topHandle();
            OS.SendMessage(topHandle2, 11, 0, 0);
            if (this.handle != topHandle2) {
                OS.SendMessage(this.handle, 11, 0, 0);
            }
        }
    }
    
    public void setRegion(final Region region) {
        this.checkWidget();
        if (region != null && region.isDisposed()) {
            this.error(5);
        }
        int createRectRgn = 0;
        if (region != null) {
            createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
            OS.CombineRgn(createRectRgn, region.handle, createRectRgn, 2);
        }
        OS.SetWindowRgn(this.handle, createRectRgn, true);
        this.region = region;
    }
    
    boolean setSavedFocus() {
        return this.forceFocus();
    }
    
    public void setSize(final int n, final int n2) {
        this.checkWidget();
        this.setBounds(0, 0, Math.max(0, n), Math.max(0, n2), 54);
    }
    
    public void setSize(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setSize(point.x, point.y);
    }
    
    boolean setTabItemFocus() {
        return this.isShowing() && this.forceFocus();
    }
    
    public void setToolTipText(final String toolTipText) {
        this.checkWidget();
        this.toolTipText = toolTipText;
        this.setToolTipText(this.getShell(), toolTipText);
    }
    
    void setToolTipText(final Shell shell, final String s) {
        shell.setToolTipText(this.handle, s);
    }
    
    public void setTouchEventsEnabled(final boolean b) {
        this.checkWidget();
        if (b) {
            OS.RegisterTouchWindow(this.handle, 0);
        }
        else {
            OS.UnregisterTouchWindow(this.handle);
        }
    }
    
    public void setVisible(final boolean b) {
        this.checkWidget();
        if (!this.getDrawing()) {
            if ((this.state & 0x10) == 0x0 == b) {
                return;
            }
        }
        else if ((OS.GetWindowLong(this.handle, -16) & 0x10000000) != 0x0 == b) {
            return;
        }
        if (b) {
            this.sendEvent(22);
            if (this.isDisposed()) {
                return;
            }
        }
        Control focusControl = null;
        boolean focusAncestor = false;
        if (!b && this.display.focusEvent != 16) {
            focusControl = this.display.getFocusControl();
            focusAncestor = this.isFocusAncestor(focusControl);
        }
        if (!this.getDrawing()) {
            this.state = (b ? (this.state & 0xFFFFFFEF) : (this.state | 0x10));
        }
        else {
            this.showWidget(b);
            if (this.isDisposed()) {
                return;
            }
        }
        if (!b) {
            this.sendEvent(23);
            if (this.isDisposed()) {
                return;
            }
        }
        if (focusAncestor) {
            this.fixFocus(focusControl);
        }
    }
    
    void sort(final int[] array) {
        final int length = array.length;
        for (int i = length / 2; i > 0; i /= 2) {
            for (int j = i; j < length; ++j) {
                for (int k = j - i; k >= 0; k -= i) {
                    if (array[k] <= array[k + i]) {
                        final int n = array[k];
                        array[k] = array[k + i];
                        array[k + i] = n;
                    }
                }
            }
        }
    }
    
    void subclass() {
        final int windowProc = this.windowProc();
        final int windowProc2 = this.display.windowProc;
        if (windowProc == windowProc2) {
            return;
        }
        OS.SetWindowLongPtr(this.handle, -4, windowProc2);
    }
    
    public Point toControl(final int x, final int y) {
        this.checkWidget();
        final POINT point = new POINT();
        point.x = x;
        point.y = y;
        OS.ScreenToClient(this.handle, point);
        return new Point(point.x, point.y);
    }
    
    public Point toControl(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        return this.toControl(point.x, point.y);
    }
    
    public Point toDisplay(final int x, final int y) {
        this.checkWidget();
        final POINT point = new POINT();
        point.x = x;
        point.y = y;
        OS.ClientToScreen(this.handle, point);
        return new Point(point.x, point.y);
    }
    
    public Point toDisplay(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        return this.toDisplay(point.x, point.y);
    }
    
    int topHandle() {
        return this.handle;
    }
    
    boolean translateAccelerator(final MSG msg) {
        return this.menuShell().translateAccelerator(msg);
    }
    
    boolean translateMnemonic(final Event event, final Control control) {
        if (control == this) {
            return false;
        }
        if (!this.isVisible() || !this.isEnabled()) {
            return false;
        }
        event.doit = this.mnemonicMatch(event.character);
        return this.traverse(event);
    }
    
    boolean translateMnemonic(final MSG msg) {
        if (msg.wParam < 32) {
            return false;
        }
        final int hwnd = msg.hwnd;
        if (OS.GetKeyState(18) >= 0) {
            final int sendMessage = OS.SendMessage(hwnd, 135, 0, 0);
            if ((sendMessage & 0x4) != 0x0) {
                return false;
            }
            if ((sendMessage & 0x2000) == 0x0) {
                return false;
            }
        }
        final Decorations menuShell = this.menuShell();
        if (menuShell.isVisible() && menuShell.isEnabled()) {
            this.display.lastAscii = msg.wParam;
            final Display display = this.display;
            final Display display2 = this.display;
            final boolean b = false;
            display2.lastDead = b;
            display.lastNull = b;
            final Event event = new Event();
            event.detail = 128;
            if (this.setKeyState(event, 31, msg.wParam, msg.lParam)) {
                return this.translateMnemonic(event, null) || menuShell.translateMnemonic(event, this);
            }
        }
        return false;
    }
    
    boolean translateTraversal(final MSG msg) {
        final int hwnd = msg.hwnd;
        final int wParam = msg.wParam;
        if (wParam == 18) {
            if ((msg.lParam & 0x40000000) == 0x0) {
                OS.SendMessage(hwnd, 295, 3, 0);
            }
            return false;
        }
        boolean doit = true;
        boolean b = false;
        boolean lastVirtual = false;
        final int lastKey = wParam;
        int lastAscii = 0;
        int detail = 0;
        switch (wParam) {
            case 27: {
                b = true;
                lastAscii = 27;
                final int sendMessage = OS.SendMessage(hwnd, 135, 0, 0);
                if ((sendMessage & 0x4) != 0x0 && (sendMessage & 0x8) == 0x0) {
                    doit = false;
                }
                detail = 2;
                break;
            }
            case 13: {
                b = true;
                lastAscii = 13;
                if ((OS.SendMessage(hwnd, 135, 0, 0) & 0x4) != 0x0) {
                    doit = false;
                }
                detail = 4;
                break;
            }
            case 9: {
                lastAscii = 9;
                final boolean b2 = OS.GetKeyState(16) >= 0;
                final int sendMessage2 = OS.SendMessage(hwnd, 135, 0, 0);
                if ((sendMessage2 & 0x6) != 0x0) {
                    if ((sendMessage2 & 0x8) != 0x0) {
                        if (b2 && OS.GetKeyState(17) >= 0) {
                            doit = false;
                        }
                    }
                    else {
                        doit = false;
                    }
                }
                detail = (b2 ? 16 : 8);
                break;
            }
            case 37:
            case 38:
            case 39:
            case 40: {
                if (OS.IsSP && (wParam == 37 || wParam == 39)) {
                    return false;
                }
                lastVirtual = true;
                if ((OS.SendMessage(hwnd, 135, 0, 0) & 0x1) != 0x0) {
                    doit = false;
                }
                boolean b3 = wParam == 40 || wParam == 39;
                if (this.parent != null && (this.parent.style & 0x8000000) != 0x0 && (wParam == 37 || wParam == 39)) {
                    b3 = !b3;
                }
                detail = (b3 ? 64 : 32);
                break;
            }
            case 33:
            case 34: {
                b = true;
                lastVirtual = true;
                if (OS.GetKeyState(17) >= 0) {
                    return false;
                }
                final int sendMessage3 = OS.SendMessage(hwnd, 135, 0, 0);
                if ((sendMessage3 & 0x4) != 0x0 && (sendMessage3 & 0x8) == 0x0) {
                    doit = false;
                }
                detail = ((wParam == 33) ? 256 : 512);
                break;
            }
            default: {
                return false;
            }
        }
        final Event event = new Event();
        event.doit = doit;
        event.detail = detail;
        this.display.lastKey = lastKey;
        this.display.lastAscii = lastAscii;
        this.display.lastVirtual = lastVirtual;
        final Display display = this.display;
        final Display display2 = this.display;
        final boolean b4 = false;
        display2.lastDead = b4;
        display.lastNull = b4;
        if (!this.setKeyState(event, 31, msg.wParam, msg.lParam)) {
            return false;
        }
        final Shell shell = this.getShell();
        Control parent = this;
        while (!parent.traverse(event)) {
            if (!event.doit && parent.hooks(31)) {
                return false;
            }
            if (parent == shell) {
                return false;
            }
            parent = parent.parent;
            if (!b || parent == null) {
                return false;
            }
        }
        OS.SendMessage(hwnd, 295, 3, 0);
        return true;
    }
    
    boolean traverse(final Event event) {
        this.sendEvent(31, event);
        if (this.isDisposed()) {
            return true;
        }
        if (!event.doit) {
            return false;
        }
        switch (event.detail) {
            case 0: {
                return true;
            }
            case 2: {
                return this.traverseEscape();
            }
            case 4: {
                return this.traverseReturn();
            }
            case 16: {
                return this.traverseGroup(true);
            }
            case 8: {
                return this.traverseGroup(false);
            }
            case 64: {
                return this.traverseItem(true);
            }
            case 32: {
                return this.traverseItem(false);
            }
            case 128: {
                return this.traverseMnemonic(event.character);
            }
            case 512: {
                return this.traversePage(true);
            }
            case 256: {
                return this.traversePage(false);
            }
            default: {
                return false;
            }
        }
    }
    
    public boolean traverse(final int detail) {
        this.checkWidget();
        final Event event = new Event();
        event.doit = true;
        event.detail = detail;
        return this.traverse(event);
    }
    
    public boolean traverse(final int n, final Event event) {
        this.checkWidget();
        if (event == null) {
            this.error(4);
        }
        return this.traverse(n, event.character, event.keyCode, event.keyLocation, event.stateMask, event.doit);
    }
    
    public boolean traverse(final int n, final KeyEvent keyEvent) {
        this.checkWidget();
        if (keyEvent == null) {
            this.error(4);
        }
        return this.traverse(n, keyEvent.character, keyEvent.keyCode, keyEvent.keyLocation, keyEvent.stateMask, keyEvent.doit);
    }
    
    boolean traverse(int detail, final char character, final int keyCode, final int keyLocation, final int stateMask, boolean doit) {
        if (detail == 0) {
            switch (keyCode) {
                case 27: {
                    detail = 2;
                    doit = true;
                    break;
                }
                case 13: {
                    detail = 4;
                    doit = true;
                    break;
                }
                case 16777218:
                case 16777220: {
                    detail = 64;
                    doit = false;
                    break;
                }
                case 16777217:
                case 16777219: {
                    detail = 32;
                    doit = false;
                    break;
                }
                case 9: {
                    detail = (((stateMask & 0x20000) != 0x0) ? 8 : 16);
                    doit = true;
                    break;
                }
                case 16777222: {
                    if ((stateMask & 0x40000) != 0x0) {
                        detail = 512;
                        doit = true;
                        break;
                    }
                    break;
                }
                case 16777221: {
                    if ((stateMask & 0x40000) != 0x0) {
                        detail = 256;
                        doit = true;
                        break;
                    }
                    break;
                }
                default: {
                    if (character != '\0' && (stateMask & 0x50000) == 0x10000) {
                        detail = 128;
                        doit = true;
                        break;
                    }
                    break;
                }
            }
        }
        final Event event = new Event();
        event.character = character;
        event.detail = detail;
        event.doit = doit;
        event.keyCode = keyCode;
        event.keyLocation = keyLocation;
        event.stateMask = stateMask;
        final Shell shell = this.getShell();
        boolean b = false;
        switch (detail) {
            case 2:
            case 4:
            case 256:
            case 512: {
                b = true;
            }
            case 8:
            case 16:
            case 32:
            case 64: {
                Control parent = this;
                while (!parent.traverse(event)) {
                    if (!event.doit && parent.hooks(31)) {
                        return false;
                    }
                    if (parent == shell) {
                        return false;
                    }
                    parent = parent.parent;
                    if (!b || parent == null) {
                        return false;
                    }
                }
                OS.SendMessage(this.handle, 295, 3, 0);
                return true;
            }
            case 128: {
                return this.translateMnemonic(event, null) || shell.translateMnemonic(event, this);
            }
            default: {
                return false;
            }
        }
    }
    
    boolean traverseEscape() {
        return false;
    }
    
    boolean traverseGroup(final boolean b) {
        final Control computeTabRoot = this.computeTabRoot();
        Widget computeTabGroup;
        Widget[] computeTabList;
        int length;
        int n;
        for (computeTabGroup = this.computeTabGroup(), computeTabList = computeTabRoot.computeTabList(), length = computeTabList.length, n = 0; n < length && computeTabList[n] != computeTabGroup; ++n) {}
        if (n == length) {
            return false;
        }
        while ((n = (n + (b ? 1 : -1) + length) % length) != n) {
            final Widget widget = computeTabList[n];
            if (!widget.isDisposed() && widget.setTabGroupFocus()) {
                return true;
            }
        }
        return !computeTabGroup.isDisposed() && computeTabGroup.setTabGroupFocus();
    }
    
    boolean traverseItem(final boolean b) {
        Control[] getChildren;
        int length;
        int n;
        for (getChildren = this.parent._getChildren(), length = getChildren.length, n = 0; n < length && getChildren[n] != this; ++n) {}
        if (n == length) {
            return false;
        }
        while ((n = (n + (b ? 1 : -1) + length) % length) != n) {
            final Control control = getChildren[n];
            if (!control.isDisposed() && control.isTabItem() && control.setTabItemFocus()) {
                return true;
            }
        }
        return false;
    }
    
    boolean traverseMnemonic(final char c) {
        if (this.mnemonicHit(c)) {
            OS.SendMessage(this.handle, 295, 3, 0);
            return true;
        }
        return false;
    }
    
    boolean traversePage(final boolean b) {
        return false;
    }
    
    boolean traverseReturn() {
        return false;
    }
    
    void unsubclass() {
        final int windowProc = this.windowProc();
        if (this.display.windowProc == windowProc) {
            return;
        }
        OS.SetWindowLongPtr(this.handle, -4, windowProc);
    }
    
    public void update() {
        this.checkWidget();
        this.update(false);
    }
    
    void update(final boolean b) {
        if (OS.IsWinCE) {
            OS.UpdateWindow(this.handle);
        }
        else {
            int n = 256;
            if (b) {
                n |= 0x80;
            }
            OS.RedrawWindow(this.handle, null, 0, n);
        }
    }
    
    void updateBackgroundColor() {
        Control backgroundControl = this.findBackgroundControl();
        if (backgroundControl == null) {
            backgroundControl = this;
        }
        this.setBackgroundPixel(backgroundControl.background);
    }
    
    void updateBackgroundImage() {
        final Control backgroundControl = this.findBackgroundControl();
        final Image image = (backgroundControl != null) ? backgroundControl.backgroundImage : this.backgroundImage;
        this.setBackgroundImage((image != null) ? image.handle : 0);
    }
    
    void updateBackgroundMode() {
        final int n = this.state & 0x400;
        this.checkBackground();
        if (n != (this.state & 0x400)) {
            this.setBackground();
        }
    }
    
    void updateFont(final Font font, final Font font2) {
        if (this.getFont().equals(font)) {
            this.setFont(font2);
        }
    }
    
    void updateImages() {
    }
    
    void updateLayout(final boolean b, final boolean b2) {
    }
    
    void updateOrientation() {
        final int getWindowLong = OS.GetWindowLong(this.handle, -20);
        int n;
        if ((this.style & 0x4000000) != 0x0) {
            n = (getWindowLong | 0x400000);
        }
        else {
            n = (getWindowLong & 0xFFBFFFFF);
        }
        OS.SetWindowLong(this.handle, -20, n);
        OS.InvalidateRect(this.handle, null, true);
    }
    
    CREATESTRUCT widgetCreateStruct() {
        return null;
    }
    
    int widgetExtStyle() {
        int n = 0;
        if (!OS.IsPPC && (this.style & 0x800) != 0x0) {
            n |= 0x200;
        }
        if (OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return n;
        }
        int n2 = n | 0x100000;
        if ((this.style & 0x4000000) != 0x0) {
            n2 |= 0x400000;
        }
        return n2;
    }
    
    int widgetParent() {
        return this.parent.handle;
    }
    
    int widgetStyle() {
        int n = 1409286144;
        if (OS.IsPPC && (this.style & 0x800) != 0x0) {
            n |= 0x800000;
        }
        return n;
    }
    
    public boolean setParent(final Composite parent) {
        this.checkWidget();
        if (parent == null) {
            this.error(4);
        }
        if (parent.isDisposed()) {
            SWT.error(5);
        }
        if (this.parent == parent) {
            return true;
        }
        if (!this.isReparentable()) {
            return false;
        }
        this.releaseParent();
        final Shell shell = parent.getShell();
        final Shell shell2 = this.getShell();
        final Decorations menuShell = parent.menuShell();
        final Decorations menuShell2 = this.menuShell();
        if (shell2 != shell || menuShell2 != menuShell) {
            this.fixChildren(shell, shell2, menuShell, menuShell2, shell2.findMenus(this));
        }
        final int topHandle = this.topHandle();
        if (OS.SetParent(topHandle, parent.handle) == 0) {
            return false;
        }
        this.parent = parent;
        this.SetWindowPos(topHandle, 1, 0, 0, 0, 0, 19);
        this.reskin(1);
        return true;
    }
    
    abstract TCHAR windowClass();
    
    abstract int windowProc();
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        LRESULT lresult = null;
        switch (n2) {
            case 6: {
                lresult = this.WM_ACTIVATE(n3, n4);
                break;
            }
            case 533: {
                lresult = this.WM_CAPTURECHANGED(n3, n4);
                break;
            }
            case 295: {
                lresult = this.WM_CHANGEUISTATE(n3, n4);
                break;
            }
            case 258: {
                lresult = this.WM_CHAR(n3, n4);
                break;
            }
            case 771: {
                lresult = this.WM_CLEAR(n3, n4);
                break;
            }
            case 16: {
                lresult = this.WM_CLOSE(n3, n4);
                break;
            }
            case 273: {
                lresult = this.WM_COMMAND(n3, n4);
                break;
            }
            case 123: {
                lresult = this.WM_CONTEXTMENU(n3, n4);
                break;
            }
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
            case 311:
            case 312: {
                lresult = this.WM_CTLCOLOR(n3, n4);
                break;
            }
            case 768: {
                lresult = this.WM_CUT(n3, n4);
                break;
            }
            case 2: {
                lresult = this.WM_DESTROY(n3, n4);
                break;
            }
            case 43: {
                lresult = this.WM_DRAWITEM(n3, n4);
                break;
            }
            case 22: {
                lresult = this.WM_ENDSESSION(n3, n4);
                break;
            }
            case 289: {
                lresult = this.WM_ENTERIDLE(n3, n4);
                break;
            }
            case 20: {
                lresult = this.WM_ERASEBKGND(n3, n4);
                break;
            }
            case 281: {
                lresult = this.WM_GESTURE(n3, n4);
                break;
            }
            case 135: {
                lresult = this.WM_GETDLGCODE(n3, n4);
                break;
            }
            case 49: {
                lresult = this.WM_GETFONT(n3, n4);
                break;
            }
            case 61: {
                lresult = this.WM_GETOBJECT(n3, n4);
                break;
            }
            case 36: {
                lresult = this.WM_GETMINMAXINFO(n3, n4);
                break;
            }
            case 83: {
                lresult = this.WM_HELP(n3, n4);
                break;
            }
            case 276: {
                lresult = this.WM_HSCROLL(n3, n4);
                break;
            }
            case 646: {
                lresult = this.WM_IME_CHAR(n3, n4);
                break;
            }
            case 271: {
                lresult = this.WM_IME_COMPOSITION(n3, n4);
                break;
            }
            case 269: {
                lresult = this.WM_IME_COMPOSITION_START(n3, n4);
                break;
            }
            case 270: {
                lresult = this.WM_IME_ENDCOMPOSITION(n3, n4);
                break;
            }
            case 279: {
                lresult = this.WM_INITMENUPOPUP(n3, n4);
                break;
            }
            case 81: {
                lresult = this.WM_INPUTLANGCHANGE(n3, n4);
                break;
            }
            case 786: {
                lresult = this.WM_HOTKEY(n3, n4);
                break;
            }
            case 256: {
                lresult = this.WM_KEYDOWN(n3, n4);
                break;
            }
            case 257: {
                lresult = this.WM_KEYUP(n3, n4);
                break;
            }
            case 8: {
                lresult = this.WM_KILLFOCUS(n3, n4);
                break;
            }
            case 515: {
                lresult = this.WM_LBUTTONDBLCLK(n3, n4);
                break;
            }
            case 513: {
                lresult = this.WM_LBUTTONDOWN(n3, n4);
                break;
            }
            case 514: {
                lresult = this.WM_LBUTTONUP(n3, n4);
                break;
            }
            case 521: {
                lresult = this.WM_MBUTTONDBLCLK(n3, n4);
                break;
            }
            case 519: {
                lresult = this.WM_MBUTTONDOWN(n3, n4);
                break;
            }
            case 520: {
                lresult = this.WM_MBUTTONUP(n3, n4);
                break;
            }
            case 44: {
                lresult = this.WM_MEASUREITEM(n3, n4);
                break;
            }
            case 288: {
                lresult = this.WM_MENUCHAR(n3, n4);
                break;
            }
            case 287: {
                lresult = this.WM_MENUSELECT(n3, n4);
                break;
            }
            case 33: {
                lresult = this.WM_MOUSEACTIVATE(n3, n4);
                break;
            }
            case 673: {
                lresult = this.WM_MOUSEHOVER(n3, n4);
                break;
            }
            case 675: {
                lresult = this.WM_MOUSELEAVE(n3, n4);
                break;
            }
            case 512: {
                lresult = this.WM_MOUSEMOVE(n3, n4);
                break;
            }
            case 522: {
                lresult = this.WM_MOUSEWHEEL(n3, n4);
                break;
            }
            case 526: {
                lresult = this.WM_MOUSEHWHEEL(n3, n4);
                break;
            }
            case 3: {
                lresult = this.WM_MOVE(n3, n4);
                break;
            }
            case 134: {
                lresult = this.WM_NCACTIVATE(n3, n4);
                break;
            }
            case 131: {
                lresult = this.WM_NCCALCSIZE(n3, n4);
                break;
            }
            case 132: {
                lresult = this.WM_NCHITTEST(n3, n4);
                break;
            }
            case 161: {
                lresult = this.WM_NCLBUTTONDOWN(n3, n4);
                break;
            }
            case 133: {
                lresult = this.WM_NCPAINT(n3, n4);
                break;
            }
            case 78: {
                lresult = this.WM_NOTIFY(n3, n4);
                break;
            }
            case 15: {
                lresult = this.WM_PAINT(n3, n4);
                break;
            }
            case 785: {
                lresult = this.WM_PALETTECHANGED(n3, n4);
                break;
            }
            case 528: {
                lresult = this.WM_PARENTNOTIFY(n3, n4);
                break;
            }
            case 770: {
                lresult = this.WM_PASTE(n3, n4);
                break;
            }
            case 791: {
                lresult = this.WM_PRINT(n3, n4);
                break;
            }
            case 792: {
                lresult = this.WM_PRINTCLIENT(n3, n4);
                break;
            }
            case 17: {
                lresult = this.WM_QUERYENDSESSION(n3, n4);
                break;
            }
            case 783: {
                lresult = this.WM_QUERYNEWPALETTE(n3, n4);
                break;
            }
            case 19: {
                lresult = this.WM_QUERYOPEN(n3, n4);
                break;
            }
            case 518: {
                lresult = this.WM_RBUTTONDBLCLK(n3, n4);
                break;
            }
            case 516: {
                lresult = this.WM_RBUTTONDOWN(n3, n4);
                break;
            }
            case 517: {
                lresult = this.WM_RBUTTONUP(n3, n4);
                break;
            }
            case 32: {
                lresult = this.WM_SETCURSOR(n3, n4);
                break;
            }
            case 7: {
                lresult = this.WM_SETFOCUS(n3, n4);
                break;
            }
            case 48: {
                lresult = this.WM_SETFONT(n3, n4);
                break;
            }
            case 26: {
                lresult = this.WM_SETTINGCHANGE(n3, n4);
                break;
            }
            case 11: {
                lresult = this.WM_SETREDRAW(n3, n4);
                break;
            }
            case 24: {
                lresult = this.WM_SHOWWINDOW(n3, n4);
                break;
            }
            case 5: {
                lresult = this.WM_SIZE(n3, n4);
                break;
            }
            case 262: {
                lresult = this.WM_SYSCHAR(n3, n4);
                break;
            }
            case 21: {
                lresult = this.WM_SYSCOLORCHANGE(n3, n4);
                break;
            }
            case 274: {
                lresult = this.WM_SYSCOMMAND(n3, n4);
                break;
            }
            case 260: {
                lresult = this.WM_SYSKEYDOWN(n3, n4);
                break;
            }
            case 261: {
                lresult = this.WM_SYSKEYUP(n3, n4);
                break;
            }
            case 715: {
                lresult = this.WM_TABLET_FLICK(n3, n4);
                break;
            }
            case 275: {
                lresult = this.WM_TIMER(n3, n4);
                break;
            }
            case 576: {
                lresult = this.WM_TOUCH(n3, n4);
                break;
            }
            case 772: {
                lresult = this.WM_UNDO(n3, n4);
                break;
            }
            case 296: {
                lresult = this.WM_UPDATEUISTATE(n3, n4);
                break;
            }
            case 277: {
                lresult = this.WM_VSCROLL(n3, n4);
                break;
            }
            case 71: {
                lresult = this.WM_WINDOWPOSCHANGED(n3, n4);
                break;
            }
            case 70: {
                lresult = this.WM_WINDOWPOSCHANGING(n3, n4);
                break;
            }
            case 525: {
                lresult = this.WM_XBUTTONDBLCLK(n3, n4);
                break;
            }
            case 523: {
                lresult = this.WM_XBUTTONDOWN(n3, n4);
                break;
            }
            case 524: {
                lresult = this.WM_XBUTTONUP(n3, n4);
                break;
            }
        }
        if (lresult != null) {
            return lresult.value;
        }
        return this.callWindowProc(n, n2, n3, n4);
    }
    
    LRESULT WM_ACTIVATE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_CAPTURECHANGED(final int n, final int n2) {
        return this.wmCaptureChanged(this.handle, n, n2);
    }
    
    LRESULT WM_CHANGEUISTATE(final int n, final int n2) {
        if ((this.state & 0x100000) != 0x0) {
            return LRESULT.ZERO;
        }
        return null;
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        return this.wmChar(this.handle, n, n2);
    }
    
    LRESULT WM_CLEAR(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_CLOSE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_COMMAND(final int n, final int n2) {
        if (n2 == 0) {
            if (this.menuShell().isEnabled()) {
                final MenuItem menuItem = this.display.getMenuItem(OS.LOWORD(n));
                if (menuItem != null && menuItem.isEnabled()) {
                    return menuItem.wmCommandChild(n, n2);
                }
            }
            return null;
        }
        final Control control = this.display.getControl(n2);
        if (control == null) {
            return null;
        }
        return control.wmCommandChild(n, n2);
    }
    
    LRESULT WM_CONTEXTMENU(final int n, final int n2) {
        return this.wmContextMenu(this.handle, n, n2);
    }
    
    LRESULT WM_CTLCOLOR(final int n, final int n2) {
        final int hPalette = this.display.hPalette;
        if (hPalette != 0) {
            OS.SelectPalette(n, hPalette, false);
            OS.RealizePalette(n);
        }
        final Control control = this.display.getControl(n2);
        if (control == null) {
            return null;
        }
        return control.wmColorChild(n, n2);
    }
    
    LRESULT WM_CUT(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_DESTROY(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_DRAWITEM(final int n, final int n2) {
        final DRAWITEMSTRUCT drawitemstruct = new DRAWITEMSTRUCT();
        OS.MoveMemory(drawitemstruct, n2, DRAWITEMSTRUCT.sizeof);
        if (drawitemstruct.CtlType == 1) {
            final MenuItem menuItem = this.display.getMenuItem(drawitemstruct.itemID);
            if (menuItem == null) {
                return null;
            }
            return menuItem.wmDrawChild(n, n2);
        }
        else {
            final Control control = this.display.getControl(drawitemstruct.hwndItem);
            if (control == null) {
                return null;
            }
            return control.wmDrawChild(n, n2);
        }
    }
    
    LRESULT WM_ENDSESSION(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_ENTERIDLE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        if ((this.state & 0x200) != 0x0 && this.findImageControl() != null) {
            return LRESULT.ONE;
        }
        if ((this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.findThemeControl() != null) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT WM_GESTURE(final int n, final int n2) {
        if (this.hooks(48) || this.filters(48)) {
            final GESTUREINFO gestureinfo = new GESTUREINFO();
            gestureinfo.cbSize = GESTUREINFO.sizeof;
            if (OS.GetGestureInfo(n2, gestureinfo) && !this.sendGestureEvent(gestureinfo)) {
                OS.CloseGestureInfoHandle(n2);
                return LRESULT.ZERO;
            }
        }
        return null;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_GETFONT(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_GETOBJECT(final int n, final int n2) {
        if (this.accessible != null) {
            final int internal_WM_GETOBJECT = this.accessible.internal_WM_GETOBJECT(n, n2);
            if (internal_WM_GETOBJECT != 0) {
                return new LRESULT(internal_WM_GETOBJECT);
            }
        }
        return null;
    }
    
    LRESULT WM_GETMINMAXINFO(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_HOTKEY(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_HELP(final int n, final int n2) {
        if (OS.IsWinCE) {
            return null;
        }
        final HELPINFO helpinfo = new HELPINFO();
        OS.MoveMemory(helpinfo, n2, HELPINFO.sizeof);
        final Decorations menuShell = this.menuShell();
        if (!menuShell.isEnabled()) {
            return null;
        }
        if (helpinfo.iContextType == 2) {
            final MenuItem menuItem = this.display.getMenuItem(helpinfo.iCtrlId);
            if (menuItem != null && menuItem.isEnabled()) {
                Widget widget = null;
                if (menuItem.hooks(28)) {
                    widget = menuItem;
                }
                else {
                    final Menu parent = menuItem.parent;
                    if (parent.hooks(28)) {
                        widget = parent;
                    }
                }
                if (widget != null) {
                    OS.SendMessage(menuShell.handle, 31, 0, 0);
                    widget.postEvent(28);
                    return LRESULT.ONE;
                }
            }
            return null;
        }
        if (this.hooks(28)) {
            this.postEvent(28);
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT WM_HSCROLL(final int n, final int n2) {
        final Control control = this.display.getControl(n2);
        if (control == null) {
            return null;
        }
        return control.wmScrollChild(n, n2);
    }
    
    LRESULT WM_IME_CHAR(final int n, final int n2) {
        return this.wmIMEChar(this.handle, n, n2);
    }
    
    LRESULT WM_IME_COMPOSITION(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_IME_COMPOSITION_START(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_IME_ENDCOMPOSITION(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_INITMENUPOPUP(final int n, final int n2) {
        if (this.display.accelKeyHit) {
            return null;
        }
        final Shell shell = this.getShell();
        final Menu activeMenu = shell.activeMenu;
        Menu menu = null;
        if (OS.HIWORD(n2) == 0) {
            menu = this.menuShell().findMenu(n);
            if (menu != null) {
                menu.update();
            }
        }
        Menu parentMenu;
        for (parentMenu = menu; parentMenu != null && parentMenu != activeMenu; parentMenu = parentMenu.getParentMenu()) {}
        if (parentMenu == null) {
            Menu menu2 = shell.activeMenu;
            while (menu2 != null) {
                menu2.sendEvent(23);
                if (menu2.isDisposed()) {
                    break;
                }
                Menu parentMenu2;
                for (menu2 = menu2.getParentMenu(), parentMenu2 = menu; parentMenu2 != null && parentMenu2 != menu2; parentMenu2 = parentMenu2.getParentMenu()) {}
                if (parentMenu2 != null) {
                    break;
                }
            }
        }
        if (menu != null && menu.isDisposed()) {
            menu = null;
        }
        if ((shell.activeMenu = menu) != null && menu != activeMenu) {
            menu.sendEvent(22);
        }
        return null;
    }
    
    LRESULT WM_INPUTLANGCHANGE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        return this.wmKeyDown(this.handle, n, n2);
    }
    
    LRESULT WM_KEYUP(final int n, final int n2) {
        return this.wmKeyUp(this.handle, n, n2);
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        return this.wmKillFocus(this.handle, n, n2);
    }
    
    LRESULT WM_LBUTTONDBLCLK(final int n, final int n2) {
        return this.wmLButtonDblClk(this.handle, n, n2);
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        return this.wmLButtonDown(this.handle, n, n2);
    }
    
    LRESULT WM_LBUTTONUP(final int n, final int n2) {
        return this.wmLButtonUp(this.handle, n, n2);
    }
    
    LRESULT WM_MBUTTONDBLCLK(final int n, final int n2) {
        return this.wmMButtonDblClk(this.handle, n, n2);
    }
    
    LRESULT WM_MBUTTONDOWN(final int n, final int n2) {
        return this.wmMButtonDown(this.handle, n, n2);
    }
    
    LRESULT WM_MBUTTONUP(final int n, final int n2) {
        return this.wmMButtonUp(this.handle, n, n2);
    }
    
    LRESULT WM_MEASUREITEM(final int n, final int n2) {
        final MEASUREITEMSTRUCT measureitemstruct = new MEASUREITEMSTRUCT();
        OS.MoveMemory(measureitemstruct, n2, MEASUREITEMSTRUCT.sizeof);
        if (measureitemstruct.CtlType == 1) {
            final MenuItem menuItem = this.display.getMenuItem(measureitemstruct.itemID);
            if (menuItem == null) {
                return null;
            }
            return menuItem.wmMeasureChild(n, n2);
        }
        else {
            final Control control = this.display.getControl(OS.GetDlgItem(this.handle, measureitemstruct.CtlID));
            if (control == null) {
                return null;
            }
            return control.wmMeasureChild(n, n2);
        }
    }
    
    LRESULT WM_MENUCHAR(final int n, final int n2) {
        final int hiword = OS.HIWORD(n);
        if (hiword == 0 || hiword == 8192) {
            this.display.mnemonicKeyHit = false;
            return new LRESULT(OS.MAKELRESULT(0, 1));
        }
        return null;
    }
    
    LRESULT WM_MENUSELECT(final int n, final int n2) {
        final int hiword = OS.HIWORD(n);
        final Shell shell = this.getShell();
        if (hiword == 65535 && n2 == 0) {
            for (Menu menu = shell.activeMenu; menu != null; menu = menu.getParentMenu()) {
                this.display.mnemonicKeyHit = true;
                menu.sendEvent(23);
                if (menu.isDisposed()) {
                    break;
                }
            }
            shell.activeMenu = null;
            return null;
        }
        if ((hiword & 0x2000) != 0x0) {
            return null;
        }
        if ((hiword & 0x80) != 0x0) {
            MenuItem menuItem = null;
            final Decorations menuShell = this.menuShell();
            if ((hiword & 0x10) != 0x0) {
                final int loword = OS.LOWORD(n);
                final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
                menuiteminfo.cbSize = MENUITEMINFO.sizeof;
                menuiteminfo.fMask = 4;
                if (OS.GetMenuItemInfo(n2, loword, true, menuiteminfo)) {
                    final Menu menu2 = menuShell.findMenu(menuiteminfo.hSubMenu);
                    if (menu2 != null) {
                        menuItem = menu2.cascade;
                    }
                }
            }
            else {
                Menu menu3 = menuShell.findMenu(n2);
                if (menu3 != null) {
                    menuItem = this.display.getMenuItem(OS.LOWORD(n));
                }
                final Menu activeMenu = shell.activeMenu;
                if (activeMenu != null) {
                    Menu parentMenu;
                    for (parentMenu = activeMenu; parentMenu != null && parentMenu != menu3; parentMenu = parentMenu.getParentMenu()) {}
                    if (parentMenu == menu3) {
                        Menu parentMenu2 = activeMenu;
                        while (parentMenu2 != menu3) {
                            parentMenu2.sendEvent(23);
                            if (parentMenu2.isDisposed()) {
                                break;
                            }
                            parentMenu2 = parentMenu2.getParentMenu();
                            if (parentMenu2 == null) {
                                break;
                            }
                        }
                        if (!shell.isDisposed()) {
                            if (menu3 != null && menu3.isDisposed()) {
                                menu3 = null;
                            }
                            shell.activeMenu = menu3;
                        }
                        if (menuItem != null && menuItem.isDisposed()) {
                            menuItem = null;
                        }
                    }
                }
            }
            if (menuItem != null) {
                menuItem.sendEvent(30);
            }
        }
        return null;
    }
    
    LRESULT WM_MOUSEACTIVATE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_MOUSEHOVER(final int n, final int n2) {
        return this.wmMouseHover(this.handle, n, n2);
    }
    
    LRESULT WM_MOUSELEAVE(final int n, final int n2) {
        if (OS.COMCTL32_MAJOR >= 6) {
            this.getShell().fixToolTip();
        }
        return this.wmMouseLeave(this.handle, n, n2);
    }
    
    LRESULT WM_MOUSEMOVE(final int n, final int n2) {
        return this.wmMouseMove(this.handle, n, n2);
    }
    
    LRESULT WM_MOUSEWHEEL(final int n, final int n2) {
        return this.wmMouseWheel(this.handle, n, n2);
    }
    
    LRESULT WM_MOUSEHWHEEL(final int n, final int n2) {
        return this.wmMouseHWheel(this.handle, n, n2);
    }
    
    LRESULT WM_MOVE(final int n, final int n2) {
        this.state |= 0x10000;
        if (this.findImageControl() != null) {
            if (this != this.getShell()) {
                this.redrawChildren();
            }
        }
        else if ((this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && OS.IsWindowVisible(this.handle) && this.findThemeControl() != null) {
            this.redrawChildren();
        }
        if ((this.state & 0x20000) == 0x0) {
            this.sendEvent(10);
        }
        return null;
    }
    
    LRESULT WM_NCACTIVATE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_NCCALCSIZE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_NCHITTEST(final int n, final int n2) {
        if (!OS.IsWindowEnabled(this.handle)) {
            return null;
        }
        if (!this.isActive()) {
            return new LRESULT(-1);
        }
        return null;
    }
    
    LRESULT WM_NCLBUTTONDOWN(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_NCPAINT(final int n, final int n2) {
        return this.wmNCPaint(this.handle, n, n2);
    }
    
    LRESULT WM_NOTIFY(final int n, final int n2) {
        final NMHDR nmhdr = new NMHDR();
        OS.MoveMemory(nmhdr, n2, NMHDR.sizeof);
        return this.wmNotify(nmhdr, n, n2);
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        return this.wmPaint(this.handle, n, n2);
    }
    
    LRESULT WM_PALETTECHANGED(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_PARENTNOTIFY(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_PASTE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_PRINT(final int n, final int n2) {
        return this.wmPrint(this.handle, n, n2);
    }
    
    LRESULT WM_PRINTCLIENT(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_QUERYENDSESSION(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_QUERYNEWPALETTE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_QUERYOPEN(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_RBUTTONDBLCLK(final int n, final int n2) {
        return this.wmRButtonDblClk(this.handle, n, n2);
    }
    
    LRESULT WM_RBUTTONDOWN(final int n, final int n2) {
        return this.wmRButtonDown(this.handle, n, n2);
    }
    
    LRESULT WM_RBUTTONUP(final int n, final int n2) {
        return this.wmRButtonUp(this.handle, n, n2);
    }
    
    LRESULT WM_SETCURSOR(final int n, final int n2) {
        if ((short)OS.LOWORD(n2) == 1) {
            final Control control = this.display.getControl(n);
            if (control == null) {
                return null;
            }
            final Cursor cursor = control.findCursor();
            if (cursor != null) {
                OS.SetCursor(cursor.handle);
                return LRESULT.ONE;
            }
        }
        return null;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        return this.wmSetFocus(this.handle, n, n2);
    }
    
    LRESULT WM_SETTINGCHANGE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_SETFONT(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_SETREDRAW(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_SHOWWINDOW(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        this.state |= 0x40000;
        if ((this.state & 0x80000) == 0x0) {
            this.sendEvent(11);
        }
        return null;
    }
    
    LRESULT WM_SYSCHAR(final int n, final int n2) {
        return this.wmSysChar(this.handle, n, n2);
    }
    
    LRESULT WM_SYSCOLORCHANGE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_SYSCOMMAND(final int n, final int n2) {
        if ((n & 0xF000) == 0x0) {
            if (this.menuShell().isEnabled()) {
                final MenuItem menuItem = this.display.getMenuItem(OS.LOWORD(n));
                if (menuItem != null) {
                    menuItem.wmCommandChild(n, n2);
                }
            }
            return LRESULT.ZERO;
        }
        Label_0363: {
            switch (n & 0xFFF0) {
                case 61536: {
                    if ((OS.GetWindowLong(this.menuShell().handle, -16) & 0x80000) == 0x0) {
                        return LRESULT.ZERO;
                    }
                    break;
                }
                case 61696: {
                    if (n2 == 0) {
                        if (this.menuShell().getMenuBar() != null) {
                            break Label_0363;
                        }
                        final Control getFocusControl = this.display._getFocusControl();
                        if (getFocusControl != null && (getFocusControl.hooks(1) || getFocusControl.hooks(2))) {
                            this.display.mnemonicKeyHit = false;
                            return LRESULT.ZERO;
                        }
                        break Label_0363;
                    }
                    else {
                        if ((!this.hooks(1) && !this.hooks(2)) || n2 == 32) {
                            break Label_0363;
                        }
                        final Menu menuBar = this.menuShell().getMenuBar();
                        if (menuBar == null) {
                            this.display.mnemonicKeyHit = false;
                            break Label_0363;
                        }
                        final char mbcsToWcs = Display.mbcsToWcs(n2);
                        if (mbcsToWcs != '\0') {
                            final char upperCase = Character.toUpperCase(mbcsToWcs);
                            final MenuItem[] items = menuBar.getItems();
                            for (int i = 0; i < items.length; ++i) {
                                final String text = items[i].getText();
                                final char mnemonic = this.findMnemonic(text);
                                if (text.length() > 0 && mnemonic == '\0' && Character.toUpperCase(text.charAt(0)) == upperCase) {
                                    this.display.mnemonicKeyHit = false;
                                    return LRESULT.ZERO;
                                }
                            }
                        }
                        break Label_0363;
                    }
                    break;
                }
                case 61552:
                case 61568: {
                    final Decorations menuShell = this.menuShell();
                    if (!menuShell.isEnabled() || !menuShell.isActive()) {
                        return LRESULT.ZERO;
                    }
                    break;
                }
                case 61472: {
                    this.menuShell().saveFocus();
                    break;
                }
            }
        }
        return null;
    }
    
    LRESULT WM_SYSKEYDOWN(final int n, final int n2) {
        return this.wmSysKeyDown(this.handle, n, n2);
    }
    
    LRESULT WM_SYSKEYUP(final int n, final int n2) {
        return this.wmSysKeyUp(this.handle, n, n2);
    }
    
    LRESULT WM_TABLET_FLICK(final int n, final int n2) {
        if (!this.hooks(48) && !this.filters(48)) {
            return null;
        }
        final Event event = new Event();
        final FLICK_DATA flick_DATA = new FLICK_DATA();
        final int[] array = { n };
        OS.MoveMemory(flick_DATA, array, OS.FLICK_DATA_sizeof());
        final FLICK_POINT flick_POINT = new FLICK_POINT();
        array[0] = n2;
        OS.MoveMemory(flick_POINT, array, OS.FLICK_POINT_sizeof());
        switch (flick_DATA.iFlickDirection) {
            case 0: {
                event.xDirection = 1;
                event.yDirection = 0;
                break;
            }
            case 1: {
                event.xDirection = 1;
                event.yDirection = -1;
                break;
            }
            case 2: {
                event.xDirection = 0;
                event.yDirection = -1;
                break;
            }
            case 3: {
                event.xDirection = -1;
                event.yDirection = -1;
                break;
            }
            case 4: {
                event.xDirection = -1;
                event.yDirection = 0;
                break;
            }
            case 5: {
                event.xDirection = -1;
                event.yDirection = 1;
                break;
            }
            case 6: {
                event.xDirection = 0;
                event.yDirection = 1;
                break;
            }
            case 7: {
                event.xDirection = 1;
                event.yDirection = 1;
                break;
            }
        }
        event.x = flick_POINT.x;
        event.y = flick_POINT.y;
        event.type = 48;
        event.detail = 16;
        this.setInputState(event, 48);
        this.sendEvent(48, event);
        return event.doit ? null : LRESULT.ONE;
    }
    
    LRESULT WM_TOUCH(final int n, final int n2) {
        LRESULT zero = null;
        if (this.hooks(47) || this.filters(47)) {
            final int loword = OS.LOWORD(n);
            final int getProcessHeap = OS.GetProcessHeap();
            final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, loword * TOUCHINPUT.sizeof);
            if (heapAlloc != 0) {
                if (OS.GetTouchInputInfo(n2, loword, heapAlloc, TOUCHINPUT.sizeof)) {
                    final TOUCHINPUT[] array = new TOUCHINPUT[loword];
                    for (int i = 0; i < loword; ++i) {
                        OS.MoveMemory(array[i] = new TOUCHINPUT(), heapAlloc + i * TOUCHINPUT.sizeof, TOUCHINPUT.sizeof);
                    }
                    this.sendTouchEvent(array);
                    OS.CloseTouchInputHandle(n2);
                    zero = LRESULT.ZERO;
                }
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
            }
        }
        return zero;
    }
    
    LRESULT WM_TIMER(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_UNDO(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_UPDATEUISTATE(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_VSCROLL(final int n, final int n2) {
        final Control control = this.display.getControl(n2);
        if (control == null) {
            return null;
        }
        return control.wmScrollChild(n, n2);
    }
    
    LRESULT WM_WINDOWPOSCHANGED(final int n, final int n2) {
        try {
            final Display display = this.display;
            ++display.resizeCount;
            final int callWindowProc = this.callWindowProc(this.handle, 71, n, n2);
            return (callWindowProc == 0) ? LRESULT.ZERO : new LRESULT(callWindowProc);
        }
        finally {
            final Display display2 = this.display;
            --display2.resizeCount;
        }
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final int n, final int n2) {
        if (!this.getDrawing() && this.getShell() != this) {
            final WINDOWPOS windowpos = new WINDOWPOS();
            OS.MoveMemory(windowpos, n2, WINDOWPOS.sizeof);
            if ((windowpos.flags & 0x2) == 0x0 || (windowpos.flags & 0x1) == 0x0) {
                final RECT rect = new RECT();
                OS.GetWindowRect(this.topHandle(), rect);
                final int n3 = rect.right - rect.left;
                final int n4 = rect.bottom - rect.top;
                if (n3 != 0 && n4 != 0) {
                    final int n5 = (this.parent == null) ? 0 : this.parent.handle;
                    OS.MapWindowPoints(0, n5, rect, 2);
                    if (OS.IsWinCE) {
                        OS.InvalidateRect(n5, rect, true);
                    }
                    else {
                        final int createRectRgn = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
                        final int createRectRgn2 = OS.CreateRectRgn(windowpos.x, windowpos.y, windowpos.x + windowpos.cx, windowpos.y + windowpos.cy);
                        OS.CombineRgn(createRectRgn, createRectRgn, createRectRgn2, 4);
                        OS.RedrawWindow(n5, null, createRectRgn, 1157);
                        OS.DeleteObject(createRectRgn);
                        OS.DeleteObject(createRectRgn2);
                    }
                }
            }
        }
        return null;
    }
    
    LRESULT WM_XBUTTONDBLCLK(final int n, final int n2) {
        return this.wmXButtonDblClk(this.handle, n, n2);
    }
    
    LRESULT WM_XBUTTONDOWN(final int n, final int n2) {
        return this.wmXButtonDown(this.handle, n, n2);
    }
    
    LRESULT WM_XBUTTONUP(final int n, final int n2) {
        return this.wmXButtonUp(this.handle, n, n2);
    }
    
    LRESULT wmColorChild(final int n, final int n2) {
        Control control = this.findBackgroundControl();
        if (control == null) {
            if ((this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
                control = this.findThemeControl();
                if (control != null) {
                    final RECT rect = new RECT();
                    OS.GetClientRect(this.handle, rect);
                    OS.SetTextColor(n, this.getForegroundPixel());
                    OS.SetBkColor(n, this.getBackgroundPixel());
                    this.fillThemeBackground(n, control, rect);
                    OS.SetBkMode(n, 1);
                    return new LRESULT(OS.GetStockObject(5));
                }
            }
            if (this.foreground == -1) {
                return null;
            }
        }
        if (control == null) {
            control = this;
        }
        final int foregroundPixel = this.getForegroundPixel();
        final int backgroundPixel = control.getBackgroundPixel();
        OS.SetTextColor(n, foregroundPixel);
        OS.SetBkColor(n, backgroundPixel);
        if (control.backgroundImage != null) {
            final RECT rect2 = new RECT();
            OS.GetClientRect(this.handle, rect2);
            final int handle = control.handle;
            final int handle2 = control.backgroundImage.handle;
            OS.MapWindowPoints(this.handle, handle, rect2, 2);
            final POINT point = new POINT();
            OS.GetWindowOrgEx(n, point);
            OS.SetBrushOrgEx(n, -rect2.left - point.x, -rect2.top - point.y, point);
            final int brush = this.findBrush(handle2, 3);
            if ((this.state & 0x200) != 0x0) {
                final int selectObject = OS.SelectObject(n, brush);
                OS.MapWindowPoints(handle, this.handle, rect2, 2);
                OS.PatBlt(n, rect2.left, rect2.top, rect2.right - rect2.left, rect2.bottom - rect2.top, 15728673);
                OS.SelectObject(n, selectObject);
            }
            OS.SetBkMode(n, 1);
            return new LRESULT(brush);
        }
        final int brush2 = this.findBrush(backgroundPixel, 0);
        if ((this.state & 0x200) != 0x0) {
            final RECT rect3 = new RECT();
            OS.GetClientRect(this.handle, rect3);
            final int selectObject2 = OS.SelectObject(n, brush2);
            OS.PatBlt(n, rect3.left, rect3.top, rect3.right - rect3.left, rect3.bottom - rect3.top, 15728673);
            OS.SelectObject(n, selectObject2);
        }
        return new LRESULT(brush2);
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        return null;
    }
    
    LRESULT wmDrawChild(final int n, final int n2) {
        return null;
    }
    
    LRESULT wmMeasureChild(final int n, final int n2) {
        return null;
    }
    
    LRESULT wmNotify(final NMHDR nmhdr, final int n, final int n2) {
        final Control control = this.display.getControl(nmhdr.hwndFrom);
        if (control == null) {
            return null;
        }
        return control.wmNotifyChild(nmhdr, n, n2);
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        return null;
    }
    
    LRESULT wmScrollChild(final int n, final int n2) {
        return null;
    }
}
