// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;

public class ProgressBar extends Control
{
    static final int DELAY = 100;
    static final int TIMER_ID = 100;
    static final int MINIMUM_WIDTH = 100;
    static final int ProgressBarProc;
    static final TCHAR ProgressBarClass;
    
    static {
        ProgressBarClass = new TCHAR(0, "msctls_progress32", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, ProgressBar.ProgressBarClass, wndclass);
        ProgressBarProc = wndclass.lpfnWndProc;
        final int getModuleHandle = OS.GetModuleHandle(null);
        final int getProcessHeap = OS.GetProcessHeap();
        wndclass.hInstance = getModuleHandle;
        final WNDCLASS wndclass2 = wndclass;
        wndclass2.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass3 = wndclass;
        wndclass3.style |= 0x8;
        final int n = ProgressBar.ProgressBarClass.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, ProgressBar.ProgressBarClass, n);
        wndclass.lpszClassName = heapAlloc;
        OS.RegisterClass(wndclass);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
    }
    
    public ProgressBar(final Composite composite, final int n) {
        super(composite, checkStyle(n));
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        return OS.CallWindowProc(ProgressBar.ProgressBarProc, n, n2, n3, n4);
    }
    
    static int checkStyle(int n) {
        n |= 0x80000;
        return Widget.checkBits(n, 256, 512, 0, 0, 0, 0);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        final int borderWidth = this.getBorderWidth();
        final int n3 = borderWidth * 2;
        final int n4 = borderWidth * 2;
        int n5;
        int n6;
        if ((this.style & 0x100) != 0x0) {
            n5 = n3 + OS.GetSystemMetrics(21) * 10;
            n6 = n4 + OS.GetSystemMetrics(3);
        }
        else {
            n5 = n3 + OS.GetSystemMetrics(2);
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
        this.startTimer();
    }
    
    int defaultForeground() {
        return OS.GetSysColor(OS.COLOR_HIGHLIGHT);
    }
    
    public int getMaximum() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1031, 0, 0);
    }
    
    public int getMinimum() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1031, 1, 0);
    }
    
    public int getSelection() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 1032, 0, 0);
    }
    
    public int getState() {
        this.checkWidget();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            switch (OS.SendMessage(this.handle, 1041, 0, 0)) {
                case 1: {
                    return 0;
                }
                case 2: {
                    return 1;
                }
                case 3: {
                    return 4;
                }
            }
        }
        return 0;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.stopTimer();
    }
    
    void startTimer() {
        if ((this.style & 0x2) != 0x0) {
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            if (OS.COMCTL32_MAJOR < 6 || (getWindowLong & 0x8) == 0x0) {
                OS.SetTimer(this.handle, 100, 100, 0);
            }
            else {
                OS.SendMessage(this.handle, 1034, 1, 100);
            }
        }
    }
    
    void stopTimer() {
        if ((this.style & 0x2) != 0x0) {
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            if (OS.COMCTL32_MAJOR < 6 || (getWindowLong & 0x8) == 0x0) {
                OS.KillTimer(this.handle, 100);
            }
            else {
                OS.SendMessage(this.handle, 1034, 0, 0);
            }
        }
    }
    
    void setBackgroundPixel(int n) {
        if (n == -1) {
            n = -16777216;
        }
        OS.SendMessage(this.handle, 8193, 0, n);
    }
    
    void setForegroundPixel(int n) {
        if (n == -1) {
            n = -16777216;
        }
        OS.SendMessage(this.handle, 1033, 0, n);
    }
    
    public void setMaximum(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1031, 1, 0);
        if (sendMessage >= 0 && sendMessage < n) {
            OS.SendMessage(this.handle, 1030, sendMessage, n);
        }
    }
    
    public void setMinimum(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 1031, 0, 0);
        if (n >= 0 && n < sendMessage) {
            OS.SendMessage(this.handle, 1030, n, sendMessage);
        }
    }
    
    public void setSelection(final int n) {
        this.checkWidget();
        OS.SendMessage(this.handle, 1026, n, 0);
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0) && OS.SendMessage(this.handle, 1041, 0, 0) != 1) {
            OS.SendMessage(this.handle, 1026, n, 0);
        }
    }
    
    public void setState(final int n) {
        this.checkWidget();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            switch (n) {
                case 0: {
                    OS.SendMessage(this.handle, 1040, 1, 0);
                    break;
                }
                case 1: {
                    OS.SendMessage(this.handle, 1040, 2, 0);
                    break;
                }
                case 4: {
                    OS.SendMessage(this.handle, 1040, 3, 0);
                    break;
                }
            }
        }
    }
    
    int widgetStyle() {
        int widgetStyle = super.widgetStyle();
        if ((this.style & 0x10000) != 0x0) {
            widgetStyle |= 0x1;
        }
        if ((this.style & 0x200) != 0x0) {
            widgetStyle |= 0x4;
        }
        if ((this.style & 0x2) != 0x0) {
            widgetStyle |= 0x8;
        }
        return widgetStyle;
    }
    
    TCHAR windowClass() {
        return ProgressBar.ProgressBarClass;
    }
    
    int windowProc() {
        return ProgressBar.ProgressBarProc;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        final LRESULT wm_GETDLGCODE = super.WM_GETDLGCODE(n, n2);
        if (wm_GETDLGCODE != null) {
            return wm_GETDLGCODE;
        }
        return new LRESULT(256);
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (wm_SIZE != null) {
            return wm_SIZE;
        }
        if ((this.style & 0x2) != 0x0 && (OS.WIN32_VERSION == OS.VERSION(5, 1) || (OS.COMCTL32_MAJOR >= 6 && !OS.IsAppThemed()))) {
            this.forceResize();
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            final int getWindowLong;
            final int n3 = getWindowLong = OS.GetWindowLong(this.handle, -16);
            int n4;
            if (rect.right - rect.left < 100) {
                n4 = (getWindowLong & 0xFFFFFFF7);
            }
            else {
                n4 = (getWindowLong | 0x8);
            }
            if (n4 != n3) {
                this.stopTimer();
                OS.SetWindowLong(this.handle, -16, n4);
                this.startTimer();
            }
        }
        return wm_SIZE;
    }
    
    LRESULT WM_TIMER(final int n, final int n2) {
        final LRESULT wm_TIMER = super.WM_TIMER(n, n2);
        if (wm_TIMER != null) {
            return wm_TIMER;
        }
        if ((this.style & 0x2) != 0x0) {
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            if ((OS.COMCTL32_MAJOR < 6 || (getWindowLong & 0x8) == 0x0) && n == 100) {
                OS.SendMessage(this.handle, 1029, 0, 0);
            }
        }
        return wm_TIMER;
    }
}
