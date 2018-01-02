// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.NOTIFYICONDATA;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.NOTIFYICONDATAA;
import org.eclipse.swt.internal.win32.NOTIFYICONDATAW;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.MONITORINFO;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.TOOLINFO;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;

public class ToolTip extends Widget
{
    Shell parent;
    TrayItem item;
    String text;
    String message;
    int id;
    int x;
    int y;
    boolean autoHide;
    boolean hasLocation;
    boolean visible;
    static final int TIMER_ID = 100;
    
    public ToolTip(final Shell parent, final int n) {
        super(parent, checkStyle(n));
        this.text = "";
        this.message = "";
        this.autoHide = true;
        this.checkOrientation(this.parent = parent);
        parent.createToolTip(this);
    }
    
    static int checkStyle(final int n) {
        if ((n & 0xB) == 0x0) {
            return n;
        }
        return Widget.checkBits(n, 2, 8, 1, 0, 0, 0);
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
    
    void destroyWidget() {
        if (this.parent != null) {
            this.parent.destroyToolTip(this);
        }
        this.releaseHandle();
    }
    
    public boolean getAutoHide() {
        this.checkWidget();
        return this.autoHide;
    }
    
    public String getMessage() {
        this.checkWidget();
        return this.message;
    }
    
    public Shell getParent() {
        this.checkWidget();
        return this.parent;
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    public boolean getVisible() {
        this.checkWidget();
        if (OS.IsWinCE) {
            return false;
        }
        if (this.item != null) {
            return this.visible;
        }
        final int hwndToolTip = this.hwndToolTip();
        if (OS.SendMessage(hwndToolTip, OS.TTM_GETCURRENTTOOL, 0, 0) != 0) {
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            if (OS.SendMessage(hwndToolTip, OS.TTM_GETCURRENTTOOL, 0, toolinfo) != 0) {
                return (toolinfo.uFlags & 0x1) == 0x0 && toolinfo.uId == this.id;
            }
        }
        return false;
    }
    
    int hwndToolTip() {
        return ((this.style & 0x1000) != 0x0) ? this.parent.balloonTipHandle() : this.parent.toolTipHandle();
    }
    
    public boolean isVisible() {
        this.checkWidget();
        if (this.item != null) {
            return this.getVisible() && this.item.getVisible();
        }
        return this.getVisible();
    }
    
    void releaseHandle() {
        super.releaseHandle();
        this.parent = null;
        this.item = null;
        this.id = -1;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.item == null && this.autoHide) {
            final int hwndToolTip = this.hwndToolTip();
            if (OS.SendMessage(hwndToolTip, OS.TTM_GETCURRENTTOOL, 0, 0) != 0) {
                final TOOLINFO toolinfo = new TOOLINFO();
                toolinfo.cbSize = TOOLINFO.sizeof;
                if (OS.SendMessage(hwndToolTip, OS.TTM_GETCURRENTTOOL, 0, toolinfo) != 0 && (toolinfo.uFlags & 0x1) == 0x0 && toolinfo.uId == this.id) {
                    OS.SendMessage(hwndToolTip, 1041, 0, toolinfo);
                    OS.SendMessage(hwndToolTip, 1052, 0, 0);
                    OS.KillTimer(hwndToolTip, 100);
                }
            }
        }
        if (this.item != null && this.item.toolTip == this) {
            this.item.toolTip = null;
        }
        this.item = null;
        final String s = null;
        this.message = s;
        this.text = s;
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
    
    public void setAutoHide(final boolean autoHide) {
        this.checkWidget();
        this.autoHide = autoHide;
    }
    
    public void setLocation(final int x, final int y) {
        this.checkWidget();
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
    
    public void setMessage(final String message) {
        this.checkWidget();
        if (message == null) {
            this.error(4);
        }
        this.message = message;
    }
    
    public void setText(final String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        this.text = text;
    }
    
    public void setVisible(final boolean b) {
        this.checkWidget();
        if (OS.IsWinCE) {
            return;
        }
        if (b == this.getVisible()) {
            return;
        }
        if (this.item == null) {
            final int handle = this.parent.handle;
            final TOOLINFO toolinfo = new TOOLINFO();
            toolinfo.cbSize = TOOLINFO.sizeof;
            toolinfo.uId = this.id;
            toolinfo.hwnd = handle;
            final int hwndToolTip = this.hwndToolTip();
            final Shell shell = this.parent.getShell();
            if (this.text.length() != 0) {
                int n = 0;
                if ((this.style & 0x2) != 0x0) {
                    n = 1;
                }
                if ((this.style & 0x8) != 0x0) {
                    n = 2;
                }
                if ((this.style & 0x1) != 0x0) {
                    n = 3;
                }
                shell.setToolTipTitle(hwndToolTip, this.text, n);
            }
            else {
                shell.setToolTipTitle(hwndToolTip, null, 0);
            }
            int n2;
            if (OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(4, 10)) {
                final RECT rect = new RECT();
                OS.SystemParametersInfo(48, 0, rect, 0);
                n2 = (rect.right - rect.left) / 4;
            }
            else {
                final int monitorFromWindow = OS.MonitorFromWindow(handle, 2);
                final MONITORINFO monitorinfo = new MONITORINFO();
                monitorinfo.cbSize = MONITORINFO.sizeof;
                OS.GetMonitorInfo(monitorFromWindow, monitorinfo);
                n2 = (monitorinfo.rcWork_right - monitorinfo.rcWork_left) / 4;
            }
            OS.SendMessage(hwndToolTip, 1048, 0, n2);
            if (b) {
                int n3 = this.x;
                int n4 = this.y;
                if (!this.hasLocation) {
                    final POINT point = new POINT();
                    if (OS.GetCursorPos(point)) {
                        n3 = point.x;
                        n4 = point.y;
                    }
                }
                OS.SendMessage(hwndToolTip, 1042, 0, OS.MAKELPARAM(n3, n4));
                final POINT point2 = new POINT();
                OS.GetCursorPos(point2);
                final RECT rect2 = new RECT();
                OS.GetClientRect(handle, rect2);
                OS.MapWindowPoints(handle, 0, rect2, 2);
                if (!OS.PtInRect(rect2, point2)) {
                    final int getCursor = OS.GetCursor();
                    OS.SetCursor(0);
                    OS.SetCursorPos(rect2.left, rect2.top);
                    OS.SendMessage(hwndToolTip, 1041, 1, toolinfo);
                    OS.SetCursorPos(point2.x, point2.y);
                    OS.SetCursor(getCursor);
                }
                else {
                    OS.SendMessage(hwndToolTip, 1041, 1, toolinfo);
                }
                OS.SetTimer(hwndToolTip, 100, OS.SendMessage(hwndToolTip, 1045, 2, 0), 0);
            }
            else {
                OS.SendMessage(hwndToolTip, 1041, 0, toolinfo);
                OS.SendMessage(hwndToolTip, 1052, 0, 0);
                OS.KillTimer(hwndToolTip, 100);
            }
            return;
        }
        if (this.item != null && OS.SHELL32_MAJOR >= 5 && b) {
            final NOTIFYICONDATA notifyicondata = OS.IsUnicode ? new NOTIFYICONDATAW() : new NOTIFYICONDATAA();
            final TCHAR tchar = new TCHAR(0, this.text, true);
            final TCHAR tchar2 = new TCHAR(0, this.message, true);
            if (OS.IsUnicode) {
                final char[] szInfoTitle = ((NOTIFYICONDATAW)notifyicondata).szInfoTitle;
                System.arraycopy(tchar.chars, 0, szInfoTitle, 0, Math.min(szInfoTitle.length - 1, tchar.length()));
                final char[] szInfo = ((NOTIFYICONDATAW)notifyicondata).szInfo;
                System.arraycopy(tchar2.chars, 0, szInfo, 0, Math.min(szInfo.length - 1, tchar2.length()));
            }
            else {
                final byte[] szInfoTitle2 = ((NOTIFYICONDATAA)notifyicondata).szInfoTitle;
                System.arraycopy(tchar.bytes, 0, szInfoTitle2, 0, Math.min(szInfoTitle2.length - 1, tchar.length()));
                final byte[] szInfo2 = ((NOTIFYICONDATAA)notifyicondata).szInfo;
                System.arraycopy(tchar2.bytes, 0, szInfo2, 0, Math.min(szInfo2.length - 1, tchar2.length()));
            }
            final Display display = this.item.getDisplay();
            notifyicondata.cbSize = NOTIFYICONDATA.sizeof;
            notifyicondata.uID = this.item.id;
            notifyicondata.hWnd = display.hwndMessage;
            notifyicondata.uFlags = 16;
            if ((this.style & 0x2) != 0x0) {
                notifyicondata.dwInfoFlags = 1;
            }
            if ((this.style & 0x8) != 0x0) {
                notifyicondata.dwInfoFlags = 2;
            }
            if ((this.style & 0x1) != 0x0) {
                notifyicondata.dwInfoFlags = 3;
            }
            this.sendEvent(22);
            this.visible = OS.Shell_NotifyIcon(1, notifyicondata);
        }
    }
}
