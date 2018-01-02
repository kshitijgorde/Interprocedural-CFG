// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;

public class Scale extends Control
{
    boolean ignoreResize;
    boolean ignoreSelection;
    static final int TrackBarProc;
    static final TCHAR TrackBarClass;
    boolean createdAsRTL;
    
    static {
        TrackBarClass = new TCHAR(0, "msctls_trackbar32", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Scale.TrackBarClass, wndclass);
        TrackBarProc = wndclass.lpfnWndProc;
        final int getModuleHandle = OS.GetModuleHandle(null);
        final int getProcessHeap = OS.GetProcessHeap();
        wndclass.hInstance = getModuleHandle;
        final WNDCLASS wndclass2 = wndclass;
        wndclass2.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass3 = wndclass;
        wndclass3.style |= 0x8;
        final int n = Scale.TrackBarClass.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, Scale.TrackBarClass, n);
        wndclass.lpszClassName = heapAlloc;
        OS.RegisterClass(wndclass);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
    }
    
    public Scale(final Composite composite, final int n) {
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
        return OS.CallWindowProc(Scale.TrackBarProc, n, n2, n3, n4);
    }
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 256, 512, 0, 0, 0, 0);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final int borderWidth = this.getBorderWidth();
        final int n3 = borderWidth * 2;
        final int n4 = borderWidth * 2;
        final RECT rect = new RECT();
        OS.SendMessage(this.handle, 1049, 0, rect);
        int n5;
        int n6;
        if ((this.style & 0x100) != 0x0) {
            n5 = n3 + OS.GetSystemMetrics(21) * 10;
            final int getSystemMetrics = OS.GetSystemMetrics(3);
            n6 = n4 + (rect.top * 2 + getSystemMetrics + getSystemMetrics / 3);
        }
        else {
            final int getSystemMetrics2 = OS.GetSystemMetrics(2);
            n5 = n3 + (rect.left * 2 + getSystemMetrics2 + getSystemMetrics2 / 3);
            n6 = n4 + OS.GetSystemMetrics(20) * 10;
        }
        if (n != -1) {
            n5 = n + borderWidth * 2;
        }
        if (n2 != -1) {
            n6 = n2 + borderWidth * 2;
        }
        return new Point(n5, n6);
    }
    
    void createHandle() {
        super.createHandle();
        this.state |= 0x300;
        OS.SendMessage(this.handle, 1032, 0, 100);
        OS.SendMessage(this.handle, 1045, 0, 10);
        OS.SendMessage(this.handle, 1044, 10, 0);
        this.createdAsRTL = ((this.style & 0x4000000) != 0x0);
    }
    
    int defaultForeground() {
        return OS.GetSysColor(OS.COLOR_BTNFACE);
    }
    
    public int getIncrement() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1048, 0, 0);
    }
    
    public int getMaximum() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1026, 0, 0);
    }
    
    public int getMinimum() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1025, 0, 0);
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1046, 0, 0);
    }
    
    public int getSelection() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1024, 0, 0);
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
    
    void setBackgroundImage(final int backgroundImage) {
        super.setBackgroundImage(backgroundImage);
        this.ignoreResize = true;
        OS.SendMessage(this.handle, 5, 0, 0);
        this.ignoreResize = false;
    }
    
    void setBackgroundPixel(final int backgroundPixel) {
        super.setBackgroundPixel(backgroundPixel);
        this.ignoreResize = true;
        OS.SendMessage(this.handle, 5, 0, 0);
        this.ignoreResize = false;
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, int n5, final boolean b) {
        n5 &= 0xFFFFFFDF;
        super.setBounds(n, n2, n3, n4, n5, true);
    }
    
    public void setIncrement(final int n) {
        this.checkWidget();
        if (n < 1) {
            return;
        }
        if (n > OS.SendMessage(this.handle, 1026, 0, 0) - OS.SendMessage(this.handle, 1025, 0, 0)) {
            return;
        }
        OS.SendMessage(this.handle, 1047, 0, n);
    }
    
    public void setMaximum(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1025, 0, 0);
        if (sendMessage >= 0 && sendMessage < n) {
            OS.SendMessage(this.handle, 1032, 1, n);
        }
    }
    
    public void setMinimum(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1026, 0, 0);
        if (n >= 0 && n < sendMessage) {
            OS.SendMessage(this.handle, 1031, 1, n);
        }
    }
    
    public void setPageIncrement(final int n) {
        this.checkWidget();
        if (n < 1) {
            return;
        }
        if (n > OS.SendMessage(this.handle, 1026, 0, 0) - OS.SendMessage(this.handle, 1025, 0, 0)) {
            return;
        }
        OS.SendMessage(this.handle, 1045, 0, n);
        OS.SendMessage(this.handle, 1044, n, 0);
    }
    
    public void setSelection(final int n) {
        this.checkWidget();
        OS.SendMessage(this.handle, 1029, 1, n);
    }
    
    int widgetStyle() {
        final int n = super.widgetStyle() | 0x10000 | 0x8 | 0x1;
        if ((this.style & 0x100) != 0x0) {
            return n | 0x400;
        }
        return n | 0x2;
    }
    
    TCHAR windowClass() {
        return Scale.TrackBarClass;
    }
    
    int windowProc() {
        return Scale.TrackBarProc;
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        final LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        switch (n) {
            case 37:
            case 39: {
                if ((this.style & 0x4000000) != 0x0 != this.createdAsRTL) {
                    return new LRESULT(this.callWindowProc(this.handle, 256, (n == 39) ? 37 : 39, n2));
                }
                break;
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_MOUSEWHEEL(final int n, final int n2) {
        final LRESULT wm_MOUSEWHEEL = super.WM_MOUSEWHEEL(n, n2);
        if (wm_MOUSEWHEEL != null) {
            return wm_MOUSEWHEEL;
        }
        final int sendMessage = OS.SendMessage(this.handle, 1024, 0, 0);
        this.ignoreSelection = true;
        final int callWindowProc = this.callWindowProc(this.handle, 522, n, n2);
        this.ignoreSelection = false;
        if (sendMessage != OS.SendMessage(this.handle, 1024, 0, 0)) {
            this.sendSelectionEvent(13, null, true);
        }
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        boolean b = this.findBackgroundControl() != null;
        if (!b && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            b = (this.findThemeControl() != null);
        }
        if (b) {
            final boolean b2 = this.getDrawing() && OS.IsWindowVisible(this.handle);
            if (b2) {
                OS.SendMessage(this.handle, 11, 0, 0);
            }
            this.ignoreResize = true;
            OS.SendMessage(this.handle, 5, 0, 0);
            this.ignoreResize = false;
            if (b2) {
                OS.SendMessage(this.handle, 11, 1, 0);
                OS.InvalidateRect(this.handle, null, false);
            }
        }
        return super.WM_PAINT(n, n2);
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        if (this.ignoreResize) {
            return null;
        }
        return super.WM_SIZE(n, n2);
    }
    
    LRESULT wmScrollChild(final int n, final int n2) {
        switch (OS.LOWORD(n)) {
            case 4:
            case 8: {
                return null;
            }
            default: {
                if (!this.ignoreSelection) {
                    this.sendSelectionEvent(13, new Event(), true);
                }
                return null;
            }
        }
    }
}
