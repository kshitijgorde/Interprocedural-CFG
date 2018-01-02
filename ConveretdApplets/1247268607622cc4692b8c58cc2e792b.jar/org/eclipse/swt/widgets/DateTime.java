// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.MCHITTESTINFO;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.win32.SIZE;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.INITCOMMONCONTROLSEX;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.SYSTEMTIME;

public class DateTime extends Composite
{
    static final int MIN_YEAR = 1752;
    static final int MAX_YEAR = 9999;
    boolean doubleClick;
    boolean ignoreSelection;
    SYSTEMTIME lastSystemTime;
    SYSTEMTIME time;
    static final int DateTimeProc;
    static final TCHAR DateTimeClass;
    static final int CalendarProc;
    static final TCHAR CalendarClass;
    static final int MARGIN = 4;
    static final int MAX_DIGIT = 9;
    static final int MAX_DAY = 31;
    static final int MAX_12HOUR = 12;
    static final int MAX_24HOUR = 24;
    static final int MAX_MINUTE = 60;
    static final int MONTH_DAY_YEAR = 0;
    static final int DAY_MONTH_YEAR = 1;
    static final int YEAR_MONTH_DAY = 2;
    static final char SINGLE_QUOTE = '\'';
    static final char DAY_FORMAT_CONSTANT = 'd';
    static final char MONTH_FORMAT_CONSTANT = 'M';
    static final char YEAR_FORMAT_CONSTANT = 'y';
    static final char HOURS_FORMAT_CONSTANT = 'h';
    static final char MINUTES_FORMAT_CONSTANT = 'm';
    static final char SECONDS_FORMAT_CONSTANT = 's';
    static final char AMPM_FORMAT_CONSTANT = 't';
    static final int[] MONTH_NAMES;
    
    static {
        DateTimeClass = new TCHAR(0, "SysDateTimePick32", true);
        CalendarClass = new TCHAR(0, "SysMonthCal32", true);
        final INITCOMMONCONTROLSEX initcommoncontrolsex = new INITCOMMONCONTROLSEX();
        initcommoncontrolsex.dwSize = INITCOMMONCONTROLSEX.sizeof;
        initcommoncontrolsex.dwICC = 256;
        OS.InitCommonControlsEx(initcommoncontrolsex);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, DateTime.DateTimeClass, wndclass);
        DateTimeProc = wndclass.lpfnWndProc;
        final int getModuleHandle = OS.GetModuleHandle(null);
        final int getProcessHeap = OS.GetProcessHeap();
        wndclass.hInstance = getModuleHandle;
        final WNDCLASS wndclass2 = wndclass;
        wndclass2.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass3 = wndclass;
        wndclass3.style |= 0x8;
        final int n = DateTime.DateTimeClass.length() * TCHAR.sizeof;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
        OS.MoveMemory(heapAlloc, DateTime.DateTimeClass, n);
        wndclass.lpszClassName = heapAlloc;
        OS.RegisterClass(wndclass);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
        final WNDCLASS wndclass4 = new WNDCLASS();
        OS.GetClassInfo(0, DateTime.CalendarClass, wndclass4);
        CalendarProc = wndclass4.lpfnWndProc;
        final int getModuleHandle2 = OS.GetModuleHandle(null);
        final int getProcessHeap2 = OS.GetProcessHeap();
        wndclass4.hInstance = getModuleHandle2;
        final WNDCLASS wndclass5 = wndclass4;
        wndclass5.style &= 0xFFFFBFFF;
        final WNDCLASS wndclass6 = wndclass4;
        wndclass6.style |= 0x8;
        final int n2 = DateTime.CalendarClass.length() * TCHAR.sizeof;
        final int heapAlloc2 = OS.HeapAlloc(getProcessHeap2, 8, n2);
        OS.MoveMemory(heapAlloc2, DateTime.CalendarClass, n2);
        wndclass4.lpszClassName = heapAlloc2;
        OS.RegisterClass(wndclass4);
        OS.HeapFree(getProcessHeap2, 0, heapAlloc2);
        MONTH_NAMES = new int[] { 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67 };
    }
    
    public DateTime(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.time = new SYSTEMTIME();
        if ((this.style & 0x8000) != 0x0) {
            OS.SendMessage(this.handle, OS.DTM_SETFORMAT, 0, new TCHAR(0, ((this.style & 0x20) != 0x0) ? this.getCustomShortDateFormat() : this.getCustomShortTimeFormat(), true));
        }
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
        return OS.CallWindowProc(this.windowProc(), n, n2, n3, n4);
    }
    
    static int checkStyle(int n) {
        n &= 0xFFFFFCFF;
        n = Widget.checkBits(n, 32, 128, 1024, 0, 0, 0);
        n = Widget.checkBits(n, 65536, 32768, 268435456, 0, 0, 0);
        if ((n & 0x20) == 0x0) {
            n &= 0xFFFFFFFB;
        }
        return n;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int n4 = 0;
        if (n == -1 || n2 == -1) {
            if ((this.style & 0x400) != 0x0) {
                final RECT rect = new RECT();
                OS.SendMessage(this.handle, 4105, 0, rect);
                n3 = rect.right;
                n4 = rect.bottom;
            }
            else {
                int cy;
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    final SIZE size = new SIZE();
                    OS.SendMessage(this.handle, 4111, 0, size);
                    n3 = size.cx;
                    cy = size.cy;
                }
                else {
                    int selectObject = 0;
                    final int getDC = OS.GetDC(this.handle);
                    final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
                    if (sendMessage != 0) {
                        selectObject = OS.SelectObject(getDC, sendMessage);
                    }
                    final RECT rect2 = new RECT();
                    if ((this.style & 0x20) != 0x0) {
                        int n5 = 0;
                        TCHAR tchar = null;
                        if ((this.style & 0x8000) != 0x0) {
                            tchar = new TCHAR(0, this.getCustomShortDateFormat(), true);
                        }
                        else {
                            n5 = (((this.style & 0x10000) != 0x0) ? 1 : 2);
                        }
                        final int getDateFormat = OS.GetDateFormat(1024, n5, null, tchar, null, 0);
                        if (getDateFormat > 0) {
                            final TCHAR tchar2 = new TCHAR(this.getCodePage(), getDateFormat);
                            OS.GetDateFormat(1024, n5, null, tchar, tchar2, tchar2.length());
                            OS.DrawText(getDC, tchar2, getDateFormat, rect2, 9216);
                        }
                    }
                    else if ((this.style & 0x80) != 0x0) {
                        int n6 = 0;
                        TCHAR tchar3 = null;
                        if ((this.style & 0x8000) != 0x0) {
                            n6 = 2;
                            tchar3 = new TCHAR(0, this.getCustomShortTimeFormat(), true);
                        }
                        final int getTimeFormat = OS.GetTimeFormat(1024, n6, null, tchar3, null, 0);
                        if (getTimeFormat > 0) {
                            final TCHAR tchar4 = new TCHAR(this.getCodePage(), getTimeFormat);
                            OS.GetTimeFormat(1024, n6, null, tchar3, tchar4, tchar4.length());
                            OS.DrawText(getDC, tchar4, getTimeFormat, rect2, 9216);
                        }
                    }
                    final int n7 = rect2.right - rect2.left;
                    cy = rect2.bottom - rect2.top;
                    if (sendMessage != 0) {
                        OS.SelectObject(getDC, selectObject);
                    }
                    OS.ReleaseDC(this.handle, getDC);
                    n3 = n7 + (OS.GetSystemMetrics(2) + 4);
                }
                int getSystemMetrics = OS.GetSystemMetrics(20);
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    getSystemMetrics += 7;
                }
                n4 = Math.max(cy, getSystemMetrics);
            }
        }
        if (n3 == 0) {
            n3 = 64;
        }
        if (n4 == 0) {
            n4 = 64;
        }
        if (n != -1) {
            n3 = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        final int borderWidth = this.getBorderWidth();
        return new Point(n3 + borderWidth * 2, n4 + borderWidth * 2);
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        if ((this.style & 0x800) == 0x0) {
            OS.SetWindowLong(this.handle, -20, OS.GetWindowLong(this.handle, -20) & 0xFFFDFDFF);
        }
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_WINDOW);
    }
    
    String getCustomShortDateFormat() {
        final TCHAR tchar = new TCHAR(this.getCodePage(), 80);
        final int getLocaleInfo = OS.GetLocaleInfo(1024, 4102, tchar, 80);
        return (getLocaleInfo != 0) ? tchar.toString(0, getLocaleInfo - 1) : "M/yyyy";
    }
    
    String getCustomShortTimeFormat() {
        final StringBuffer sb = new StringBuffer(this.getTimeFormat());
        final int length = sb.length();
        boolean b = false;
        int i = 0;
        int j = 0;
        while (i < length) {
            final char char1 = sb.charAt(i);
            if (char1 == '\'') {
                b = !b;
            }
            else if (char1 == 's' && !b) {
                for (j = i + 1; j < length; ++j) {
                    if (sb.charAt(j) != 's') {
                        break;
                    }
                }
                while (i > 0 && sb.charAt(i) != 'm') {
                    --i;
                }
                ++i;
                break;
            }
            ++i;
        }
        if (i < j) {
            sb.delete(i, j);
        }
        return sb.toString();
    }
    
    String getTimeFormat() {
        final TCHAR tchar = new TCHAR(this.getCodePage(), 80);
        final int getLocaleInfo = OS.GetLocaleInfo(1024, 4099, tchar, 80);
        return (getLocaleInfo > 0) ? tchar.toString(0, getLocaleInfo - 1) : "h:mm:ss tt";
    }
    
    public int getDay() {
        this.checkWidget();
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        return systemtime.wDay;
    }
    
    public int getHours() {
        this.checkWidget();
        if ((this.style & 0x400) != 0x0) {
            return this.time.wHour;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        return systemtime.wHour;
    }
    
    public int getMinutes() {
        this.checkWidget();
        if ((this.style & 0x400) != 0x0) {
            return this.time.wMinute;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        return systemtime.wMinute;
    }
    
    public int getMonth() {
        this.checkWidget();
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        return systemtime.wMonth - 1;
    }
    
    String getNameText() {
        return ((this.style & 0x80) != 0x0) ? (String.valueOf(this.getHours()) + ":" + this.getMinutes() + ":" + this.getSeconds()) : (String.valueOf(this.getMonth() + 1) + "/" + this.getDay() + "/" + this.getYear());
    }
    
    public int getSeconds() {
        this.checkWidget();
        if ((this.style & 0x400) != 0x0) {
            return this.time.wSecond;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        return systemtime.wSecond;
    }
    
    public int getYear() {
        this.checkWidget();
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        return systemtime.wYear;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.lastSystemTime = null;
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
    
    public void setDate(final int n, final int n2, final int n3) {
        this.checkWidget();
        if (n < 1752 || n > 9999) {
            return;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n4 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wYear = (short)n;
        systemtime.wMonth = (short)(n2 + 1);
        systemtime.wDay = (short)n3;
        OS.SendMessage(this.handle, n4, 0, systemtime);
        this.lastSystemTime = null;
    }
    
    public void setDay(final int n) {
        this.checkWidget();
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n2 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wDay = (short)n;
        OS.SendMessage(this.handle, n2, 0, systemtime);
        this.lastSystemTime = null;
    }
    
    public void setHours(final int n) {
        this.checkWidget();
        if (n < 0 || n > 23) {
            return;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n2 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wHour = (short)n;
        OS.SendMessage(this.handle, n2, 0, systemtime);
        if ((this.style & 0x400) != 0x0 && n >= 0 && n <= 23) {
            this.time.wHour = (short)n;
        }
    }
    
    public void setMinutes(final int n) {
        this.checkWidget();
        if (n < 0 || n > 59) {
            return;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n2 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wMinute = (short)n;
        OS.SendMessage(this.handle, n2, 0, systemtime);
        if ((this.style & 0x400) != 0x0 && n >= 0 && n <= 59) {
            this.time.wMinute = (short)n;
        }
    }
    
    public void setMonth(final int n) {
        this.checkWidget();
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n2 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wMonth = (short)(n + 1);
        OS.SendMessage(this.handle, n2, 0, systemtime);
        this.lastSystemTime = null;
    }
    
    public void setOrientation(final int orientation) {
        if ((this.style & 0x400) != 0x0) {
            super.setOrientation(orientation);
        }
    }
    
    public void setSeconds(final int n) {
        this.checkWidget();
        if (n < 0 || n > 59) {
            return;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n2 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wSecond = (short)n;
        OS.SendMessage(this.handle, n2, 0, systemtime);
        if ((this.style & 0x400) != 0x0 && n >= 0 && n <= 59) {
            this.time.wSecond = (short)n;
        }
    }
    
    public void setTime(final int n, final int n2, final int n3) {
        this.checkWidget();
        if (n < 0 || n > 23 || n2 < 0 || n2 > 59 || n3 < 0 || n3 > 59) {
            return;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n4 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wHour = (short)n;
        systemtime.wMinute = (short)n2;
        systemtime.wSecond = (short)n3;
        OS.SendMessage(this.handle, n4, 0, systemtime);
        if ((this.style & 0x400) != 0x0 && n >= 0 && n <= 23 && n2 >= 0 && n2 <= 59 && n3 >= 0 && n3 <= 59) {
            this.time.wHour = (short)n;
            this.time.wMinute = (short)n2;
            this.time.wSecond = (short)n3;
        }
    }
    
    public void setYear(final int n) {
        this.checkWidget();
        if (n < 1752 || n > 9999) {
            return;
        }
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.SendMessage(this.handle, ((this.style & 0x400) != 0x0) ? 4097 : 4097, 0, systemtime);
        final int n2 = ((this.style & 0x400) != 0x0) ? 4098 : 4098;
        systemtime.wYear = (short)n;
        OS.SendMessage(this.handle, n2, 0, systemtime);
        this.lastSystemTime = null;
    }
    
    int widgetStyle() {
        final int n = super.widgetStyle() | 0x10000;
        if ((this.style & 0x400) != 0x0) {
            return n | 0x10;
        }
        int n2 = n & 0xFDFFFFFF;
        if ((this.style & 0x80) != 0x0) {
            n2 |= 0x9;
        }
        if ((this.style & 0x20) != 0x0) {
            n2 |= (((this.style & 0x10000) != 0x0) ? 12 : 4);
            if ((this.style & 0x4) == 0x0) {
                n2 |= 0x1;
            }
        }
        return n2;
    }
    
    TCHAR windowClass() {
        return ((this.style & 0x400) != 0x0) ? DateTime.CalendarClass : DateTime.DateTimeClass;
    }
    
    int windowProc() {
        return ((this.style & 0x400) != 0x0) ? DateTime.CalendarProc : DateTime.DateTimeProc;
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        switch (nmhdr.code) {
            case -753: {
                this.display.captureChanged = true;
                break;
            }
            case -749: {
                if (this.ignoreSelection) {
                    break;
                }
                OS.SendMessage(this.handle, 4097, 0, new SYSTEMTIME());
                this.sendSelectionEvent(13);
                break;
            }
            case -759: {
                final SYSTEMTIME lastSystemTime = new SYSTEMTIME();
                OS.SendMessage(this.handle, 4097, 0, lastSystemTime);
                if (this.lastSystemTime != null && lastSystemTime.wDay == this.lastSystemTime.wDay && lastSystemTime.wMonth == this.lastSystemTime.wMonth && lastSystemTime.wYear == this.lastSystemTime.wYear) {
                    break;
                }
                this.sendSelectionEvent(13);
                if ((this.style & 0x80) == 0x0) {
                    this.lastSystemTime = lastSystemTime;
                    break;
                }
                break;
            }
        }
        return super.wmNotifyChild(nmhdr, n, n2);
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        final LRESULT wm_CHAR = super.WM_CHAR(n, n2);
        if (wm_CHAR != null) {
            return wm_CHAR;
        }
        switch (n) {
            case 13: {
                this.sendSelectionEvent(14);
            }
            case 9:
            case 27: {
                return LRESULT.ZERO;
            }
            default: {
                return wm_CHAR;
            }
        }
    }
    
    LRESULT WM_LBUTTONDBLCLK(final int n, final int n2) {
        final LRESULT wm_LBUTTONDBLCLK = super.WM_LBUTTONDBLCLK(n, n2);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if ((this.style & 0x400) != 0x0) {
            final MCHITTESTINFO mchittestinfo = new MCHITTESTINFO();
            mchittestinfo.cbSize = MCHITTESTINFO.sizeof;
            final POINT pt = new POINT();
            pt.x = OS.GET_X_LPARAM(n2);
            pt.y = OS.GET_Y_LPARAM(n2);
            mchittestinfo.pt = pt;
            if ((OS.SendMessage(this.handle, 4110, 0, mchittestinfo) & 0x20001) == 0x20001) {
                this.doubleClick = true;
            }
        }
        return wm_LBUTTONDBLCLK;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final LRESULT wm_LBUTTONDOWN = super.WM_LBUTTONDOWN(n, n2);
        if (wm_LBUTTONDOWN == LRESULT.ZERO) {
            return wm_LBUTTONDOWN;
        }
        this.doubleClick = false;
        if ((this.style & 0x400) != 0x0 && (this.style & 0x80000) == 0x0) {
            OS.SetFocus(this.handle);
        }
        return wm_LBUTTONDOWN;
    }
    
    LRESULT WM_LBUTTONUP(final int n, final int n2) {
        final LRESULT wm_LBUTTONUP = super.WM_LBUTTONUP(n, n2);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (this.doubleClick) {
            this.sendSelectionEvent(14);
        }
        this.doubleClick = false;
        return wm_LBUTTONUP;
    }
    
    LRESULT WM_TIMER(final int n, final int n2) {
        final LRESULT wm_TIMER = super.WM_TIMER(n, n2);
        if (wm_TIMER != null) {
            return wm_TIMER;
        }
        this.ignoreSelection = true;
        final int callWindowProc = this.callWindowProc(this.handle, 275, n, n2);
        this.ignoreSelection = false;
        return (callWindowProc == 0) ? LRESULT.ZERO : new LRESULT(callWindowProc);
    }
}
