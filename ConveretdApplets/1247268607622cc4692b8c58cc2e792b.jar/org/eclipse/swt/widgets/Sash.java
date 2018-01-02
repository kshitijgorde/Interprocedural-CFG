// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;

public class Sash extends Control
{
    boolean dragging;
    int startX;
    int startY;
    int lastX;
    int lastY;
    static final int INCREMENT = 1;
    static final int PAGE_INCREMENT = 9;
    
    public Sash(final Composite composite, final int n) {
        super(composite, checkStyle(n));
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
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        return OS.DefWindowProc(n, n2, n3, n4);
    }
    
    void createHandle() {
        super.createHandle();
        this.state |= 0x100;
    }
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 256, 512, 0, 0, 0, 0);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final int borderWidth = this.getBorderWidth();
        int n3 = borderWidth * 2;
        int n4 = borderWidth * 2;
        if ((this.style & 0x100) != 0x0) {
            n3 += 64;
            n4 += 3;
        }
        else {
            n3 += 3;
            n4 += 64;
        }
        if (n != -1) {
            n3 = n + borderWidth * 2;
        }
        if (n2 != -1) {
            n4 = n2 + borderWidth * 2;
        }
        return new Point(n3, n4);
    }
    
    void drawBand(final int n, final int n2, final int n3, final int n4) {
        if ((this.style & 0x10000) != 0x0) {
            return;
        }
        final int handle = this.parent.handle;
        final int createBitmap = OS.CreateBitmap(8, 8, 1, 1, new byte[] { -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0 });
        final int createPatternBrush = OS.CreatePatternBrush(createBitmap);
        final int getDCEx = OS.GetDCEx(handle, 0, 2);
        final int selectObject = OS.SelectObject(getDCEx, createPatternBrush);
        OS.PatBlt(getDCEx, n, n2, n3, n4, 5898313);
        OS.SelectObject(getDCEx, selectObject);
        OS.ReleaseDC(handle, getDCEx);
        OS.DeleteObject(createPatternBrush);
        OS.DeleteObject(createBitmap);
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
    
    TCHAR windowClass() {
        return this.display.windowClass;
    }
    
    int windowProc() {
        return this.display.windowProc;
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        super.WM_ERASEBKGND(n, n2);
        this.drawBackground(n);
        return LRESULT.ONE;
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        final LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        switch (n) {
            case 37:
            case 38:
            case 39:
            case 40: {
                if (OS.GetKeyState(1) < 0) {
                    return wm_KEYDOWN;
                }
                final int n3 = (OS.GetKeyState(17) < 0) ? 1 : 9;
                final POINT point = new POINT();
                if ((this.style & 0x200) != 0x0) {
                    if (n == 38) {
                        break;
                    }
                    if (n == 40) {
                        break;
                    }
                    point.x = ((n == 37) ? (-n3) : n3);
                }
                else {
                    if (n == 37) {
                        break;
                    }
                    if (n == 39) {
                        break;
                    }
                    point.y = ((n == 38) ? (-n3) : n3);
                }
                final int handle = this.parent.handle;
                OS.MapWindowPoints(this.handle, handle, point, 1);
                final RECT rect = new RECT();
                final RECT rect2 = new RECT();
                OS.GetWindowRect(this.handle, rect);
                final int width = rect.right - rect.left;
                final int height = rect.bottom - rect.top;
                OS.GetClientRect(handle, rect2);
                final int n4 = rect2.right - rect2.left;
                final int n5 = rect2.bottom - rect2.top;
                int x = this.lastX;
                int y = this.lastY;
                if ((this.style & 0x200) != 0x0) {
                    x = Math.min(Math.max(0, point.x - this.startX), n4 - width);
                }
                else {
                    y = Math.min(Math.max(0, point.y - this.startY), n5 - height);
                }
                if (x == this.lastX && y == this.lastY) {
                    return wm_KEYDOWN;
                }
                final POINT point2 = new POINT();
                point2.x = point.x;
                point2.y = point.y;
                OS.ClientToScreen(handle, point2);
                if ((this.style & 0x200) != 0x0) {
                    final POINT point3 = point2;
                    point3.y += height / 2;
                }
                else {
                    final POINT point4 = point2;
                    point4.x += width / 2;
                }
                OS.SetCursorPos(point2.x, point2.y);
                final Event event = new Event();
                event.x = x;
                event.y = y;
                event.width = width;
                event.height = height;
                this.sendSelectionEvent(13, event, true);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                if (event.doit && (this.style & 0x10000) != 0x0) {
                    this.setBounds(event.x, event.y, width, height);
                }
                return wm_KEYDOWN;
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        return new LRESULT(256);
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final LRESULT wm_LBUTTONDOWN = super.WM_LBUTTONDOWN(n, n2);
        if (wm_LBUTTONDOWN == LRESULT.ZERO) {
            return wm_LBUTTONDOWN;
        }
        final int handle = this.parent.handle;
        final POINT point = new POINT();
        OS.POINTSTOPOINT(point, n2);
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        OS.MapWindowPoints(this.handle, 0, point, 1);
        this.startX = point.x - rect.left;
        this.startY = point.y - rect.top;
        OS.MapWindowPoints(0, handle, rect, 2);
        this.lastX = rect.left;
        this.lastY = rect.top;
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        final Event event = new Event();
        event.x = this.lastX;
        event.y = this.lastY;
        event.width = width;
        event.height = height;
        if ((this.style & 0x10000) == 0x0) {
            event.detail = 1;
        }
        this.sendSelectionEvent(13, event, true);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (event.doit) {
            this.dragging = true;
            this.lastX = event.x;
            this.lastY = event.y;
            this.menuShell().bringToTop();
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
            if (OS.IsWinCE) {
                OS.UpdateWindow(handle);
            }
            else {
                OS.RedrawWindow(handle, null, 0, 384);
            }
            this.drawBand(event.x, event.y, width, height);
            if ((this.style & 0x10000) != 0x0) {
                this.setBounds(event.x, event.y, width, height);
            }
        }
        return wm_LBUTTONDOWN;
    }
    
    LRESULT WM_LBUTTONUP(final int n, final int n2) {
        final LRESULT wm_LBUTTONUP = super.WM_LBUTTONUP(n, n2);
        if (wm_LBUTTONUP == LRESULT.ZERO) {
            return wm_LBUTTONUP;
        }
        if (!this.dragging) {
            return wm_LBUTTONUP;
        }
        this.dragging = false;
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        final Event event = new Event();
        event.x = this.lastX;
        event.y = this.lastY;
        event.width = width;
        event.height = height;
        this.drawBand(event.x, event.y, width, height);
        this.sendSelectionEvent(13, event, true);
        if (this.isDisposed()) {
            return wm_LBUTTONUP;
        }
        if (event.doit && (this.style & 0x10000) != 0x0) {
            this.setBounds(event.x, event.y, width, height);
        }
        return wm_LBUTTONUP;
    }
    
    LRESULT WM_MOUSEMOVE(final int n, final int n2) {
        final LRESULT wm_MOUSEMOVE = super.WM_MOUSEMOVE(n, n2);
        if (wm_MOUSEMOVE != null) {
            return wm_MOUSEMOVE;
        }
        if (!this.dragging || (n & 0x1) == 0x0) {
            return wm_MOUSEMOVE;
        }
        final POINT point = new POINT();
        OS.POINTSTOPOINT(point, n2);
        final int handle = this.parent.handle;
        OS.MapWindowPoints(this.handle, handle, point, 1);
        final RECT rect = new RECT();
        final RECT rect2 = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int width = rect.right - rect.left;
        final int height = rect.bottom - rect.top;
        OS.GetClientRect(handle, rect2);
        int x = this.lastX;
        int y = this.lastY;
        if ((this.style & 0x200) != 0x0) {
            x = Math.min(Math.max(0, point.x - this.startX), rect2.right - rect2.left - width);
        }
        else {
            y = Math.min(Math.max(0, point.y - this.startY), rect2.bottom - rect2.top - height);
        }
        if (x == this.lastX && y == this.lastY) {
            return wm_MOUSEMOVE;
        }
        this.drawBand(this.lastX, this.lastY, width, height);
        final Event event = new Event();
        event.x = x;
        event.y = y;
        event.width = width;
        event.height = height;
        if ((this.style & 0x10000) == 0x0) {
            event.detail = 1;
        }
        this.sendSelectionEvent(13, event, true);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (event.doit) {
            this.lastX = event.x;
            this.lastY = event.y;
        }
        if (OS.IsWinCE) {
            OS.UpdateWindow(handle);
        }
        else {
            OS.RedrawWindow(handle, null, 0, 384);
        }
        this.drawBand(this.lastX, this.lastY, width, height);
        if ((this.style & 0x10000) != 0x0) {
            this.setBounds(this.lastX, this.lastY, width, height);
        }
        return wm_MOUSEMOVE;
    }
    
    LRESULT WM_SETCURSOR(final int n, final int n2) {
        final LRESULT wm_SETCURSOR = super.WM_SETCURSOR(n, n2);
        if (wm_SETCURSOR != null) {
            return wm_SETCURSOR;
        }
        if ((short)OS.LOWORD(n2) == 1) {
            int n3;
            if ((this.style & 0x100) != 0x0) {
                n3 = OS.LoadCursor(0, 32645);
            }
            else {
                n3 = OS.LoadCursor(0, 32644);
            }
            OS.SetCursor(n3);
            return LRESULT.ONE;
        }
        return wm_SETCURSOR;
    }
}
