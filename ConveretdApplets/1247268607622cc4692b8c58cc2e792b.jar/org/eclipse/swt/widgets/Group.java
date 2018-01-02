// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;

public class Group extends Composite
{
    String text;
    static final int CLIENT_INSET = 3;
    static final int GroupProc;
    static final TCHAR GroupClass;
    
    static {
        GroupClass = new TCHAR(0, OS.IsWinCE ? "BUTTON" : "SWT_GROUP", true);
        final WNDCLASS wndclass = new WNDCLASS();
        if (OS.IsWinCE) {
            OS.GetClassInfo(0, Group.GroupClass, wndclass);
            GroupProc = wndclass.lpfnWndProc;
        }
        else {
            OS.GetClassInfo(0, new TCHAR(0, "BUTTON", true), wndclass);
            GroupProc = wndclass.lpfnWndProc;
            final int getModuleHandle = OS.GetModuleHandle(null);
            if (!OS.GetClassInfo(getModuleHandle, Group.GroupClass, wndclass)) {
                final int getProcessHeap = OS.GetProcessHeap();
                wndclass.hInstance = getModuleHandle;
                final WNDCLASS wndclass2 = wndclass;
                wndclass2.style &= 0xFFFFFFFC;
                final int n = Group.GroupClass.length() * TCHAR.sizeof;
                final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
                OS.MoveMemory(heapAlloc, Group.GroupClass, n);
                wndclass.lpszClassName = heapAlloc;
                OS.RegisterClass(wndclass);
                OS.HeapFree(getProcessHeap, 0, heapAlloc);
            }
        }
    }
    
    public Group(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.text = "";
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        switch (n2) {
            case 513:
            case 515: {
                return OS.DefWindowProc(n, n2, n3, n4);
            }
            default: {
                return OS.CallWindowProc(Group.GroupProc, n, n2, n3, n4);
            }
        }
    }
    
    static int checkStyle(int n) {
        n |= 0x80000;
        return n & 0xFFFFFCFF;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final Point computeSize = super.computeSize(n, n2, b);
        if (this.text.length() != 0) {
            String s = this.text;
            if ((this.style & 0x4000000) != 0x0 && (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed())) {
                s = " " + s + " ";
            }
            final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
            int selectObject = 0;
            final int getDC = OS.GetDC(this.handle);
            final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage);
            }
            final RECT rect = new RECT();
            OS.DrawText(getDC, tchar, -1, rect, 1056);
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            computeSize.x = Math.max(computeSize.x, rect.right - rect.left + 18 + ((OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) ? 1 : 0));
        }
        return computeSize;
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        final Rectangle computeTrim = super.computeTrim(n, n2, n3, n4);
        int selectObject = 0;
        final int getDC = OS.GetDC(this.handle);
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        if (sendMessage != 0) {
            selectObject = OS.SelectObject(getDC, sendMessage);
        }
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.GetTextMetrics(getDC, textmetric);
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(this.handle, getDC);
        final int n5 = (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) ? 1 : 0;
        final Rectangle rectangle = computeTrim;
        rectangle.x -= 3;
        final Rectangle rectangle2 = computeTrim;
        rectangle2.y -= textmetric.tmHeight + n5;
        final Rectangle rectangle3 = computeTrim;
        rectangle3.width += 6;
        final Rectangle rectangle4 = computeTrim;
        rectangle4.height += textmetric.tmHeight + 3 + n5;
        return computeTrim;
    }
    
    void createHandle() {
        final Composite parent = this.parent;
        parent.state |= 0x100000;
        super.createHandle();
        final Composite parent2 = this.parent;
        parent2.state &= 0xFFEFFFFF;
        this.state |= 0x200;
        this.state &= 0xFFFFFFFD;
    }
    
    void enableWidget(final boolean b) {
        super.enableWidget(b);
        if ((this.style & 0x4000000) != 0x0 && (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed())) {
            OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), (b || this.text.length() == 0) ? this.text : (" " + this.text + " "), true));
        }
    }
    
    public Rectangle getClientArea() {
        this.checkWidget();
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        int selectObject = 0;
        final int getDC = OS.GetDC(this.handle);
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        if (sendMessage != 0) {
            selectObject = OS.SelectObject(getDC, sendMessage);
        }
        final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
        OS.GetTextMetrics(getDC, textmetric);
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(this.handle, getDC);
        final int n = (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) ? 1 : 0;
        final int n2 = 3;
        final int n3 = textmetric.tmHeight + n;
        return new Rectangle(n2, n3, Math.max(0, rect.right - 6), Math.max(0, rect.bottom - n3 - 3));
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    boolean mnemonicHit(final char c) {
        return this.setFocus();
    }
    
    boolean mnemonicMatch(final char c) {
        final char mnemonic = this.findMnemonic(this.getText());
        return mnemonic != '\0' && Character.toUpperCase(c) == Character.toUpperCase(mnemonic);
    }
    
    void printWidget(final int n, final int n2, final GC gc) {
        boolean printWindow = false;
        if (OS.GetDeviceCaps(gc.handle, 2) != 2) {
            final int getWindowLong = OS.GetWindowLong(n, -16);
            if ((getWindowLong & 0x10000000) == 0x0) {
                OS.ShowWindow(n, 5);
            }
            printWindow = OS.PrintWindow(n, n2, 0);
            if ((getWindowLong & 0x10000000) == 0x0) {
                OS.ShowWindow(n, 0);
            }
        }
        if (!printWindow) {
            OS.SendMessage(n, 791, n2, 14);
            final int saveDC = OS.SaveDC(n2);
            final Control[] getChildren = this._getChildren();
            final Rectangle bounds = this.getBounds();
            OS.IntersectClipRect(n2, 0, 0, bounds.width, bounds.height);
            for (int i = getChildren.length - 1; i >= 0; --i) {
                final Point location = getChildren[i].getLocation();
                final int getGraphicsMode = OS.GetGraphicsMode(n2);
                if (getGraphicsMode == 2) {
                    OS.ModifyWorldTransform(n2, new float[] { 1.0f, 0.0f, 0.0f, 1.0f, location.x, location.y }, 2);
                }
                else {
                    OS.SetWindowOrgEx(n2, -location.x, -location.y, null);
                }
                final int topHandle = getChildren[i].topHandle();
                if ((OS.GetWindowLong(topHandle, -16) & 0x10000000) != 0x0) {
                    getChildren[i].printWidget(topHandle, n2, gc);
                }
                if (getGraphicsMode == 2) {
                    OS.ModifyWorldTransform(n2, new float[] { 1.0f, 0.0f, 0.0f, 1.0f, -location.x, -location.y }, 2);
                }
            }
            OS.RestoreDC(n2, saveDC);
        }
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.text = null;
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        final Rectangle clientArea = this.getClientArea();
        super.setFont(font);
        if (!clientArea.equals(this.getClientArea())) {
            this.sendResize();
        }
    }
    
    public void setText(String string) {
        this.checkWidget();
        if (string == null) {
            this.error(4);
        }
        this.text = string;
        if ((this.style & 0x4000000) != 0x0 && (OS.COMCTL32_MAJOR < 6 || !OS.IsAppThemed()) && !OS.IsWindowEnabled(this.handle) && string.length() != 0) {
            string = " " + string + " ";
        }
        OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), string, true));
    }
    
    int widgetStyle() {
        return super.widgetStyle() | 0x7 | 0x2000000 | 0x4000000;
    }
    
    TCHAR windowClass() {
        return Group.GroupClass;
    }
    
    int windowProc() {
        return Group.GroupProc;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if (wm_ERASEBKGND != null) {
            return wm_ERASEBKGND;
        }
        this.drawBackground(n);
        return LRESULT.ONE;
    }
    
    LRESULT WM_NCHITTEST(final int n, final int n2) {
        final LRESULT wm_NCHITTEST = super.WM_NCHITTEST(n, n2);
        if (wm_NCHITTEST != null) {
            return wm_NCHITTEST;
        }
        int callWindowProc = this.callWindowProc(this.handle, 132, n, n2);
        if (callWindowProc == -1) {
            callWindowProc = 1;
        }
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_MOUSEMOVE(final int n, final int n2) {
        final LRESULT wm_MOUSEMOVE = super.WM_MOUSEMOVE(n, n2);
        if (wm_MOUSEMOVE != null) {
            return wm_MOUSEMOVE;
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PRINTCLIENT(final int n, final int n2) {
        final LRESULT wm_PRINTCLIENT = super.WM_PRINTCLIENT(n, n2);
        if (wm_PRINTCLIENT != null) {
            return wm_PRINTCLIENT;
        }
        if (OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final int saveDC = OS.SaveDC(n);
            final int callWindowProc = this.callWindowProc(this.handle, 792, n, n2);
            OS.RestoreDC(n, saveDC);
            return new LRESULT(callWindowProc);
        }
        return wm_PRINTCLIENT;
    }
    
    LRESULT WM_UPDATEUISTATE(final int n, final int n2) {
        final LRESULT wm_UPDATEUISTATE = super.WM_UPDATEUISTATE(n, n2);
        if (wm_UPDATEUISTATE != null) {
            return wm_UPDATEUISTATE;
        }
        boolean b = this.findImageControl() != null;
        if (!b) {
            if ((this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
                b = (this.findThemeControl() != null);
            }
            if (!b) {
                b = (this.findBackgroundControl() != null);
            }
        }
        if (b) {
            OS.InvalidateRect(this.handle, null, false);
            return new LRESULT(OS.DefWindowProc(this.handle, 296, n, n2));
        }
        return wm_UPDATEUISTATE;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final int n, final int n2) {
        final LRESULT wm_WINDOWPOSCHANGING = super.WM_WINDOWPOSCHANGING(n, n2);
        if (wm_WINDOWPOSCHANGING != null) {
            return wm_WINDOWPOSCHANGING;
        }
        if (OS.IsWinCE) {
            return wm_WINDOWPOSCHANGING;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return wm_WINDOWPOSCHANGING;
        }
        final WINDOWPOS windowpos = new WINDOWPOS();
        OS.MoveMemory(windowpos, n2, WINDOWPOS.sizeof);
        if ((windowpos.flags & 0x9) != 0x0) {
            return wm_WINDOWPOSCHANGING;
        }
        final RECT rect = new RECT();
        OS.SetRect(rect, 0, 0, windowpos.cx, windowpos.cy);
        OS.SendMessage(this.handle, 131, 0, rect);
        final int n3 = rect.right - rect.left;
        final int n4 = rect.bottom - rect.top;
        OS.GetClientRect(this.handle, rect);
        int n5 = rect.right - rect.left;
        final int n6 = rect.bottom - rect.top;
        if (n3 == n5 && n4 == n6) {
            return wm_WINDOWPOSCHANGING;
        }
        if (n3 != n5) {
            int n7;
            if (n3 < (n7 = n5)) {
                n7 = n3;
            }
            OS.SetRect(rect, n7 - 3, 0, n3, n4);
            OS.InvalidateRect(this.handle, rect, true);
        }
        if (n4 != n6) {
            int n8;
            if (n4 < (n8 = n6)) {
                n8 = n4;
            }
            if (n3 < n5) {
                n5 -= 3;
            }
            OS.SetRect(rect, 0, n8 - 3, n5, n4);
            OS.InvalidateRect(this.handle, rect, true);
        }
        return wm_WINDOWPOSCHANGING;
    }
}
