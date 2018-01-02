// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.win32.WINDOWPOS;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.MONITORINFO;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.COMBOBOXINFO;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;

public class Combo extends Composite
{
    boolean noSelection;
    boolean ignoreDefaultSelection;
    boolean ignoreCharacter;
    boolean ignoreModify;
    boolean ignoreResize;
    boolean lockText;
    int scrollWidth;
    int visibleCount;
    int cbtHook;
    static final int VISIBLE_COUNT = 5;
    public static final int LIMIT;
    static final int CBID_LIST = 1000;
    static final int CBID_EDIT = 1001;
    static int EditProc;
    static int ListProc;
    static final int ComboProc;
    static final TCHAR ComboClass;
    
    static {
        LIMIT = (OS.IsWinNT ? Integer.MAX_VALUE : 32767);
        ComboClass = new TCHAR(0, "COMBOBOX", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Combo.ComboClass, wndclass);
        ComboProc = wndclass.lpfnWndProc;
    }
    
    public Combo(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.style |= 0x100;
    }
    
    public void add(final String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        final int sendMessage = OS.SendMessage(this.handle, 323, 0, tchar);
        if (sendMessage == -1) {
            this.error(14);
        }
        if (sendMessage == -2) {
            this.error(14);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(tchar, true);
        }
    }
    
    public void add(final String s, final int n) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        final int sendMessage = OS.SendMessage(this.handle, 326, 0, 0);
        if (n < 0 || n > sendMessage) {
            this.error(6);
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        final int sendMessage2 = OS.SendMessage(this.handle, 330, n, tchar);
        if (sendMessage2 == -2 || sendMessage2 == -1) {
            this.error(14);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(tchar, true);
        }
    }
    
    public void addModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            this.error(4);
        }
        this.addListener(24, new TypedListener(modifyListener));
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
    
    public void addVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            this.error(4);
        }
        this.addListener(25, new TypedListener(verifyListener));
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (n == this.handle) {
            switch (n2) {
                case 5: {
                    this.ignoreResize = true;
                    final boolean lockText = this.lockText;
                    if ((this.style & 0x8) == 0x0) {
                        this.lockText = true;
                    }
                    final int callWindowProc = OS.CallWindowProc(Combo.ComboProc, n, n2, n3, n4);
                    if ((this.style & 0x8) == 0x0) {
                        this.lockText = lockText;
                    }
                    this.ignoreResize = false;
                    return callWindowProc;
                }
                default: {
                    return OS.CallWindowProc(Combo.ComboProc, n, n2, n3, n4);
                }
            }
        }
        else if (n == OS.GetDlgItem(this.handle, 1001)) {
            if (this.lockText && n2 == 12) {
                return 0;
            }
            return OS.CallWindowProc(Combo.EditProc, n, n2, n3, n4);
        }
        else {
            if (n == OS.GetDlgItem(this.handle, 1000)) {
                return OS.CallWindowProc(Combo.ListProc, n, n2, n3, n4);
            }
            return OS.DefWindowProc(n, n2, n3, n4);
        }
    }
    
    int CBTProc(final int n, final int n2, final int n3) {
        if (n == 3) {
            final TCHAR tchar = new TCHAR(0, 128);
            OS.GetClassName(n2, tchar, tchar.length());
            final String string = tchar.toString(0, tchar.strlen());
            if (string.equals("Edit") || string.equals("EDIT")) {
                OS.SetWindowLong(n2, -16, OS.GetWindowLong(n2, -16) & 0xFFFFFEFF);
            }
        }
        return OS.CallNextHookEx(this.cbtHook, n, n2, n3);
    }
    
    boolean checkHandle(final int n) {
        return n == this.handle || n == OS.GetDlgItem(this.handle, 1001) || n == OS.GetDlgItem(this.handle, 1000);
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    static int checkStyle(int checkBits) {
        checkBits &= 0xFFFFF7FF;
        checkBits &= 0xFFFFFCFF;
        checkBits = Widget.checkBits(checkBits, 4, 64, 0, 0, 0, 0);
        if ((checkBits & 0x40) != 0x0) {
            return checkBits & 0xFFFFFFF7;
        }
        return checkBits;
    }
    
    public void clearSelection() {
        this.checkWidget();
        OS.SendMessage(this.handle, 322, 0, -1);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int n4 = 0;
        if (n == -1) {
            int selectObject = 0;
            final int getDC = OS.GetDC(this.handle);
            final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage);
            }
            final int sendMessage2 = OS.SendMessage(this.handle, 326, 0, 0);
            final RECT rect = new RECT();
            int n5 = 3072;
            if ((this.style & 0x8) == 0x0) {
                n5 |= 0x2000;
            }
            final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
            final int codePage = this.getCodePage();
            TCHAR tchar = new TCHAR(codePage, getWindowTextLength + 1);
            OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
            OS.DrawText(getDC, tchar, getWindowTextLength, rect, n5);
            n3 = Math.max(n3, rect.right - rect.left);
            if ((this.style & 0x100) != 0x0) {
                n3 = Math.max(n3, this.scrollWidth);
            }
            else {
                for (int i = 0; i < sendMessage2; ++i) {
                    final int sendMessage3 = OS.SendMessage(this.handle, 329, i, 0);
                    if (sendMessage3 != -1) {
                        if (sendMessage3 + 1 > tchar.length()) {
                            tchar = new TCHAR(codePage, sendMessage3 + 1);
                        }
                        if (OS.SendMessage(this.handle, 328, i, tchar) != -1) {
                            OS.DrawText(getDC, tchar, sendMessage3, rect, n5);
                            n3 = Math.max(n3, rect.right - rect.left);
                        }
                    }
                }
            }
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
        }
        if (n2 == -1 && (this.style & 0x40) != 0x0) {
            n4 = OS.SendMessage(this.handle, 326, 0, 0) * OS.SendMessage(this.handle, 340, 0, 0);
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
        if ((this.style & 0x8) != 0x0) {
            n3 += 8;
        }
        else {
            final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
            if (getDlgItem != 0) {
                final int sendMessage4 = OS.SendMessage(getDlgItem, 212, 0, 0);
                n3 += OS.LOWORD(sendMessage4) + OS.HIWORD(sendMessage4) + 3;
            }
        }
        final COMBOBOXINFO comboboxinfo = new COMBOBOXINFO();
        comboboxinfo.cbSize = COMBOBOXINFO.sizeof;
        int n6;
        int n7;
        if ((this.style & 0x40) == 0x0 && !OS.IsWinCE && OS.GetComboBoxInfo(this.handle, comboboxinfo)) {
            n6 = n3 + (comboboxinfo.itemLeft + (comboboxinfo.buttonRight - comboboxinfo.buttonLeft));
            n7 = comboboxinfo.buttonBottom - comboboxinfo.buttonTop + comboboxinfo.buttonTop * 2;
        }
        else {
            n6 = n3 + (OS.GetSystemMetrics(2) + OS.GetSystemMetrics(45) * 2);
            final int sendMessage5 = OS.SendMessage(this.handle, 340, -1, 0);
            if ((this.style & 0x4) != 0x0) {
                n7 = sendMessage5 + 6;
            }
            else {
                n7 = n4 + (sendMessage5 + 10);
            }
        }
        if ((this.style & 0x40) != 0x0 && (this.style & 0x100) != 0x0) {
            n7 += OS.GetSystemMetrics(3);
        }
        return new Point(n6, n7);
    }
    
    public void copy() {
        this.checkWidget();
        OS.SendMessage(this.handle, 769, 0, 0);
    }
    
    void createHandle() {
        if (OS.IsWinCE || (this.style & 0x48) != 0x0) {
            super.createHandle();
        }
        else {
            final int getCurrentThreadId = OS.GetCurrentThreadId();
            final Callback callback = new Callback(this, "CBTProc", 3);
            final int address = callback.getAddress();
            if (address == 0) {
                this.error(3);
            }
            this.cbtHook = OS.SetWindowsHookEx(5, address, 0, getCurrentThreadId);
            super.createHandle();
            if (this.cbtHook != 0) {
                OS.UnhookWindowsHookEx(this.cbtHook);
            }
            this.cbtHook = 0;
            callback.dispose();
        }
        this.state &= 0xFFFFFEFD;
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0 && Combo.EditProc == 0) {
            Combo.EditProc = OS.GetWindowLongPtr(getDlgItem, -4);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0 && Combo.ListProc == 0) {
            Combo.ListProc = OS.GetWindowLongPtr(getDlgItem2, -4);
        }
        if ((this.style & 0x40) != 0x0) {
            final int n = 52;
            this.SetWindowPos(this.handle, 0, 0, 0, 16383, 16383, n);
            this.SetWindowPos(this.handle, 0, 0, 0, 0, 0, n);
        }
    }
    
    void createWidget() {
        super.createWidget();
        this.visibleCount = 5;
        if ((this.style & 0x40) == 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 340, 0, 0);
            if (sendMessage != -1 && sendMessage != 0) {
                int n;
                if (OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(4, 10)) {
                    final RECT rect = new RECT();
                    OS.SystemParametersInfo(48, 0, rect, 0);
                    n = (rect.bottom - rect.top) / 3;
                }
                else {
                    final int monitorFromWindow = OS.MonitorFromWindow(this.handle, 2);
                    final MONITORINFO monitorinfo = new MONITORINFO();
                    monitorinfo.cbSize = MONITORINFO.sizeof;
                    OS.GetMonitorInfo(monitorFromWindow, monitorinfo);
                    n = (monitorinfo.rcWork_bottom - monitorinfo.rcWork_top) / 3;
                }
                this.visibleCount = Math.max(this.visibleCount, n / sendMessage);
            }
        }
    }
    
    public void cut() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 768, 0, 0);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_WINDOW);
    }
    
    void deregister() {
        super.deregister();
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0) {
            this.display.removeControl(getDlgItem);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0) {
            this.display.removeControl(getDlgItem2);
        }
    }
    
    public void deselect(final int n) {
        this.checkWidget();
        if (n != OS.SendMessage(this.handle, 327, 0, 0)) {
            return;
        }
        OS.SendMessage(this.handle, 334, -1, 0);
        this.sendEvent(24);
    }
    
    public void deselectAll() {
        this.checkWidget();
        OS.SendMessage(this.handle, 334, -1, 0);
        this.sendEvent(24);
    }
    
    boolean dragDetect(final int n, final int n2, final int n3, final boolean b, final boolean[] array, final boolean[] array2) {
        if (b && (this.style & 0x8) == 0x0) {
            final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
            if (getDlgItem != 0) {
                final int[] array3 = { 0 };
                final int[] array4 = { 0 };
                OS.SendMessage(this.handle, 320, array3, array4);
                if (array3[0] != array4[0]) {
                    final int loword = OS.LOWORD(OS.SendMessage(getDlgItem, 215, 0, OS.MAKELPARAM(n2, n3)));
                    if (array3[0] <= loword && loword < array4[0] && super.dragDetect(n, n2, n3, b, array, array2)) {
                        if (array2 != null) {
                            array2[0] = true;
                        }
                        return true;
                    }
                }
                return false;
            }
        }
        return super.dragDetect(n, n2, n3, b, array, array2);
    }
    
    public String getItem(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 329, n, 0);
        if (sendMessage != -1) {
            final TCHAR tchar = new TCHAR(this.getCodePage(), sendMessage + 1);
            if (OS.SendMessage(this.handle, 328, n, tchar) != -1) {
                return tchar.toString(0, sendMessage);
            }
        }
        final int sendMessage2 = OS.SendMessage(this.handle, 326, 0, 0);
        if (n >= 0 && n < sendMessage2) {
            this.error(8);
        }
        this.error(6);
        return "";
    }
    
    public int getItemCount() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 326, 0, 0);
        if (sendMessage == -1) {
            this.error(36);
        }
        return sendMessage;
    }
    
    public int getItemHeight() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 340, 0, 0);
        if (sendMessage == -1) {
            this.error(11);
        }
        return sendMessage;
    }
    
    public String[] getItems() {
        this.checkWidget();
        final int itemCount = this.getItemCount();
        final String[] array = new String[itemCount];
        for (int i = 0; i < itemCount; ++i) {
            array[i] = this.getItem(i);
        }
        return array;
    }
    
    public boolean getListVisible() {
        this.checkWidget();
        return (this.style & 0x4) == 0x0 || OS.SendMessage(this.handle, 343, 0, 0) != 0;
    }
    
    String getNameText() {
        return this.getText();
    }
    
    public void setListVisible(final boolean b) {
        this.checkWidget();
        OS.SendMessage(this.handle, 335, b ? 1 : 0, 0);
    }
    
    public int getOrientation() {
        return super.getOrientation();
    }
    
    public Point getSelection() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0 && (this.style & 0x8) != 0x0) {
            return new Point(0, OS.GetWindowTextLength(this.handle));
        }
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.handle, 320, array, array2);
        if (!OS.IsUnicode && OS.IsDBLocale) {
            array[0] = this.mbcsToWcsPos(array[0]);
            array2[0] = this.mbcsToWcsPos(array2[0]);
        }
        return new Point(array[0], array2[0]);
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        if (this.noSelection) {
            return -1;
        }
        return OS.SendMessage(this.handle, 327, 0, 0);
    }
    
    public String getText() {
        this.checkWidget();
        final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
        if (getWindowTextLength == 0) {
            return "";
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
        OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
        return tchar.toString(0, getWindowTextLength);
    }
    
    public int getTextHeight() {
        this.checkWidget();
        final COMBOBOXINFO comboboxinfo = new COMBOBOXINFO();
        comboboxinfo.cbSize = COMBOBOXINFO.sizeof;
        if ((this.style & 0x40) == 0x0 && !OS.IsWinCE && OS.GetComboBoxInfo(this.handle, comboboxinfo)) {
            return comboboxinfo.buttonBottom - comboboxinfo.buttonTop + comboboxinfo.buttonTop * 2;
        }
        final int sendMessage = OS.SendMessage(this.handle, 340, -1, 0);
        if (sendMessage == -1) {
            this.error(11);
        }
        return ((this.style & 0x4) != 0x0) ? (sendMessage + 6) : (sendMessage + 10);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem == 0) {
            return Combo.LIMIT;
        }
        return OS.SendMessage(getDlgItem, 213, 0, 0) & Integer.MAX_VALUE;
    }
    
    public int getVisibleItemCount() {
        this.checkWidget();
        return this.visibleCount;
    }
    
    boolean hasFocus() {
        final int getFocus = OS.GetFocus();
        return getFocus == this.handle || (getFocus != 0 && (getFocus == OS.GetDlgItem(this.handle, 1001) || getFocus == OS.GetDlgItem(this.handle, 1000)));
    }
    
    public int indexOf(final String s) {
        return this.indexOf(s, 0);
    }
    
    public int indexOf(final String s, final int n) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        if (s.length() == 0) {
            for (int itemCount = this.getItemCount(), i = n; i < itemCount; ++i) {
                if (s.equals(this.getItem(i))) {
                    return i;
                }
            }
            return -1;
        }
        final int sendMessage = OS.SendMessage(this.handle, 326, 0, 0);
        if (n < 0 || n >= sendMessage) {
            return -1;
        }
        int sendMessage2 = n - 1;
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        do {
            final int n2;
            sendMessage2 = OS.SendMessage(this.handle, 344, n2 = sendMessage2, tchar);
            if (sendMessage2 == -1 || sendMessage2 <= n2) {
                return -1;
            }
        } while (!s.equals(this.getItem(sendMessage2)));
        return sendMessage2;
    }
    
    int mbcsToWcsPos(final int n) {
        if (n <= 0) {
            return 0;
        }
        if (OS.IsUnicode) {
            return n;
        }
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem == 0) {
            return n;
        }
        final int getWindowTextLengthA = OS.GetWindowTextLengthA(getDlgItem);
        if (getWindowTextLengthA == 0) {
            return 0;
        }
        if (n >= getWindowTextLengthA) {
            return getWindowTextLengthA;
        }
        final byte[] array = new byte[getWindowTextLengthA + 1];
        OS.GetWindowTextA(getDlgItem, array, getWindowTextLengthA + 1);
        return OS.MultiByteToWideChar(this.getCodePage(), 1, array, n, null, 0);
    }
    
    public void paste() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 770, 0, 0);
    }
    
    void register() {
        super.register();
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0) {
            this.display.addControl(getDlgItem, this);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0) {
            this.display.addControl(getDlgItem2, this);
        }
    }
    
    public void remove(final int n) {
        this.checkWidget();
        this.remove(n, true);
    }
    
    void remove(final int n, final boolean b) {
        TCHAR tchar = null;
        if ((this.style & 0x100) != 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 329, n, 0);
            if (sendMessage == -1) {
                final int sendMessage2 = OS.SendMessage(this.handle, 326, 0, 0);
                if (n >= 0 && n < sendMessage2) {
                    this.error(15);
                }
                this.error(6);
            }
            tchar = new TCHAR(this.getCodePage(), sendMessage + 1);
            if (OS.SendMessage(this.handle, 328, n, tchar) == -1) {
                final int sendMessage3 = OS.SendMessage(this.handle, 326, 0, 0);
                if (n >= 0 && n < sendMessage3) {
                    this.error(15);
                }
                this.error(6);
            }
        }
        final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
        if (OS.SendMessage(this.handle, 324, n, 0) == -1) {
            final int sendMessage4 = OS.SendMessage(this.handle, 326, 0, 0);
            if (n >= 0 && n < sendMessage4) {
                this.error(15);
            }
            this.error(6);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(tchar, true);
        }
        if (b && getWindowTextLength != OS.GetWindowTextLength(this.handle)) {
            this.sendEvent(24);
            if (this.isDisposed()) {
                return;
            }
        }
        if ((this.style & 0x8) != 0x0 && OS.SendMessage(this.handle, 326, 0, 0) == 0) {
            OS.InvalidateRect(this.handle, null, true);
        }
    }
    
    public void remove(final int n, final int n2) {
        this.checkWidget();
        if (n > n2) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 326, 0, 0);
        if (n < 0 || n > n2 || n2 >= sendMessage) {
            this.error(6);
        }
        final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
        RECT rect = null;
        int getDC = 0;
        int selectObject = 0;
        int sendMessage2 = 0;
        int max = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            getDC = OS.GetDC(this.handle);
            sendMessage2 = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage2 != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage2);
            }
        }
        final int codePage = this.getCodePage();
        final int n3 = 3104;
        for (int i = n; i <= n2; ++i) {
            TCHAR tchar = null;
            if ((this.style & 0x100) != 0x0) {
                final int sendMessage3 = OS.SendMessage(this.handle, 329, n, 0);
                if (sendMessage3 == -1) {
                    break;
                }
                tchar = new TCHAR(codePage, sendMessage3 + 1);
                if (OS.SendMessage(this.handle, 328, n, tchar) == -1) {
                    break;
                }
            }
            if (OS.SendMessage(this.handle, 324, n, 0) == -1) {
                this.error(15);
            }
            if ((this.style & 0x100) != 0x0) {
                OS.DrawText(getDC, tchar, -1, rect, n3);
                max = Math.max(max, rect.right - rect.left);
            }
        }
        if ((this.style & 0x100) != 0x0) {
            if (sendMessage2 != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            this.setScrollWidth(max, false);
        }
        if (getWindowTextLength != OS.GetWindowTextLength(this.handle)) {
            this.sendEvent(24);
            if (this.isDisposed()) {
                return;
            }
        }
        if ((this.style & 0x8) != 0x0 && OS.SendMessage(this.handle, 326, 0, 0) == 0) {
            OS.InvalidateRect(this.handle, null, true);
        }
    }
    
    public void remove(final String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        final int index = this.indexOf(s, 0);
        if (index == -1) {
            this.error(5);
        }
        this.remove(index);
    }
    
    public void removeAll() {
        this.checkWidget();
        OS.SendMessage(this.handle, 331, 0, 0);
        this.sendEvent(24);
        if (this.isDisposed()) {
            return;
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(0);
        }
    }
    
    public void removeModifyListener(final ModifyListener modifyListener) {
        this.checkWidget();
        if (modifyListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(24, modifyListener);
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
    
    public void removeVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(25, verifyListener);
    }
    
    boolean sendKeyEvent(final int n, final int n2, final int n3, final int n4, final Event event) {
        if (!super.sendKeyEvent(n, n2, n3, n4, event)) {
            return false;
        }
        if ((this.style & 0x8) != 0x0) {
            return true;
        }
        if (n != 1) {
            return true;
        }
        if (n2 != 258 && n2 != 256 && n2 != 646) {
            return true;
        }
        if (event.character == '\0') {
            return true;
        }
        if (!this.hooks(25) && !this.filters(25)) {
            return true;
        }
        final char character = event.character;
        final int stateMask = event.stateMask;
        switch (n2) {
            case 258: {
                if (character != '\b' && character != '\u007f' && character != '\r' && character != '\t' && character != '\n') {
                    break;
                }
            }
            case 256: {
                if ((stateMask & 0x70000) != 0x0) {
                    return false;
                }
                break;
            }
        }
        if (OS.GetKeyState(1) < 0 && OS.GetDlgItem(this.handle, 1001) == OS.GetCapture()) {
            return true;
        }
        String s = "";
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem == 0) {
            return true;
        }
        OS.SendMessage(getDlgItem, 176, array, array2);
        switch (character) {
            case 8: {
                if (array[0] != array2[0]) {
                    break;
                }
                if (array[0] == 0) {
                    return true;
                }
                --array[0];
                if (!OS.IsUnicode && OS.IsDBLocale) {
                    final int[] array3 = { 0 };
                    final int[] array4 = { 0 };
                    OS.SendMessage(getDlgItem, 177, array[0], array2[0]);
                    OS.SendMessage(getDlgItem, 176, array3, array4);
                    if (array[0] != array3[0]) {
                        --array[0];
                    }
                }
                array[0] = Math.max(array[0], 0);
                break;
            }
            case 127: {
                if (array[0] != array2[0]) {
                    break;
                }
                final int getWindowTextLength = OS.GetWindowTextLength(getDlgItem);
                if (array[0] == getWindowTextLength) {
                    return true;
                }
                ++array2[0];
                if (!OS.IsUnicode && OS.IsDBLocale) {
                    final int[] array5 = { 0 };
                    final int[] array6 = { 0 };
                    OS.SendMessage(getDlgItem, 177, array[0], array2[0]);
                    OS.SendMessage(getDlgItem, 176, array5, array6);
                    if (array2[0] != array6[0]) {
                        ++array2[0];
                    }
                }
                array2[0] = Math.min(array2[0], getWindowTextLength);
                break;
            }
            case 13: {
                return true;
            }
            default: {
                if (character != '\t' && character < ' ') {
                    return true;
                }
                s = new String(new char[] { character });
                break;
            }
        }
        final String verifyText = this.verifyText(s, array[0], array2[0], event);
        if (verifyText == null) {
            return false;
        }
        if (verifyText == s) {
            return true;
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), verifyText, true);
        OS.SendMessage(getDlgItem, 177, array[0], array2[0]);
        OS.SendMessage(getDlgItem, 194, 0, tchar);
        return false;
    }
    
    public void select(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 326, 0, 0);
        if (n >= 0 && n < sendMessage) {
            final int sendMessage2 = OS.SendMessage(this.handle, 327, 0, 0);
            final int sendMessage3 = OS.SendMessage(this.handle, 334, n, 0);
            if (sendMessage3 != -1 && sendMessage3 != sendMessage2) {
                this.sendEvent(24);
            }
        }
    }
    
    void setBackgroundImage(final int backgroundImage) {
        super.setBackgroundImage(backgroundImage);
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0) {
            OS.InvalidateRect(getDlgItem, null, true);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0) {
            OS.InvalidateRect(getDlgItem2, null, true);
        }
    }
    
    void setBackgroundPixel(final int backgroundPixel) {
        super.setBackgroundPixel(backgroundPixel);
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0) {
            OS.InvalidateRect(getDlgItem, null, true);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0) {
            OS.InvalidateRect(getDlgItem2, null, true);
        }
    }
    
    void setBounds(final int n, final int n2, final int n3, int n4, int n5) {
        if ((this.style & 0x4) != 0x0) {
            n4 = this.getTextHeight() + this.getItemHeight() * ((this.getItemCount() == 0) ? 5 : this.visibleCount) + 2;
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            if (rect.right - rect.left != 0 && OS.SendMessage(this.handle, 338, 0, rect) != 0) {
                final int n6 = rect.right - rect.left;
                final int n7 = rect.bottom - rect.top;
                if (n6 == n3 && n7 == n4) {
                    n5 |= 0x1;
                }
            }
            this.SetWindowPos(this.handle, 0, n, n2, n3, n4, n5);
        }
        else {
            super.setBounds(n, n2, n3, n4, n5);
        }
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        super.setFont(font);
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth();
        }
    }
    
    void setForegroundPixel(final int foregroundPixel) {
        super.setForegroundPixel(foregroundPixel);
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0) {
            OS.InvalidateRect(getDlgItem, null, true);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0) {
            OS.InvalidateRect(getDlgItem2, null, true);
        }
    }
    
    public void setItem(final int n, final String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        final int selectionIndex = this.getSelectionIndex();
        this.remove(n, false);
        if (this.isDisposed()) {
            return;
        }
        this.add(s, n);
        if (selectionIndex != -1) {
            this.select(selectionIndex);
        }
    }
    
    public void setItems(final String[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == null) {
                this.error(5);
            }
        }
        RECT rect = null;
        int getDC = 0;
        int selectObject = 0;
        int sendMessage = 0;
        int max = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            getDC = OS.GetDC(this.handle);
            sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage);
            }
            this.setScrollWidth(0);
        }
        OS.SendMessage(this.handle, 331, 0, 0);
        final int codePage = this.getCodePage();
        for (int j = 0; j < array.length; ++j) {
            final TCHAR tchar = new TCHAR(codePage, array[j], true);
            final int sendMessage2 = OS.SendMessage(this.handle, 323, 0, tchar);
            if (sendMessage2 == -1) {
                this.error(14);
            }
            if (sendMessage2 == -2) {
                this.error(14);
            }
            if ((this.style & 0x100) != 0x0) {
                OS.DrawText(getDC, tchar, -1, rect, 3104);
                max = Math.max(max, rect.right - rect.left);
            }
        }
        if ((this.style & 0x100) != 0x0) {
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            this.setScrollWidth(max + 3);
        }
        this.sendEvent(24);
    }
    
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
    }
    
    void setScrollWidth() {
        int max = 0;
        final RECT rect = new RECT();
        int selectObject = 0;
        final int getDC = OS.GetDC(this.handle);
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        if (sendMessage != 0) {
            selectObject = OS.SelectObject(getDC, sendMessage);
        }
        final int codePage = this.getCodePage();
        final int sendMessage2 = OS.SendMessage(this.handle, 326, 0, 0);
        final int n = 3104;
        for (int i = 0; i < sendMessage2; ++i) {
            final int sendMessage3 = OS.SendMessage(this.handle, 329, i, 0);
            if (sendMessage3 != -1) {
                final TCHAR tchar = new TCHAR(codePage, sendMessage3 + 1);
                if (OS.SendMessage(this.handle, 328, i, tchar) != -1) {
                    OS.DrawText(getDC, tchar, -1, rect, n);
                    max = Math.max(max, rect.right - rect.left);
                }
            }
        }
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(this.handle, getDC);
        this.setScrollWidth(max + 3);
    }
    
    void setScrollWidth(int scrollWidth) {
        this.scrollWidth = scrollWidth;
        if ((this.style & 0x40) != 0x0) {
            OS.SendMessage(this.handle, 350, scrollWidth, 0);
            return;
        }
        int n = 0;
        if (OS.SendMessage(this.handle, 326, 0, 0) > 3) {
            int n2;
            if (OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(4, 10)) {
                final RECT rect = new RECT();
                OS.SystemParametersInfo(48, 0, rect, 0);
                n2 = (rect.right - rect.left) / 4;
            }
            else {
                final int monitorFromWindow = OS.MonitorFromWindow(this.handle, 2);
                final MONITORINFO monitorinfo = new MONITORINFO();
                monitorinfo.cbSize = MONITORINFO.sizeof;
                OS.GetMonitorInfo(monitorFromWindow, monitorinfo);
                n2 = (monitorinfo.rcWork_right - monitorinfo.rcWork_left) / 4;
            }
            n = ((scrollWidth > n2) ? 1 : 0);
        }
        final boolean lockText = this.lockText;
        if ((this.style & 0x8) == 0x0) {
            this.lockText = true;
        }
        if (n != 0) {
            OS.SendMessage(this.handle, 352, 0, 0);
            OS.SendMessage(this.handle, 350, scrollWidth, 0);
        }
        else {
            scrollWidth += OS.GetSystemMetrics(3);
            OS.SendMessage(this.handle, 352, scrollWidth, 0);
            OS.SendMessage(this.handle, 350, 0, 0);
        }
        if ((this.style & 0x8) == 0x0) {
            this.lockText = lockText;
        }
    }
    
    void setScrollWidth(final TCHAR tchar, final boolean b) {
        final RECT rect = new RECT();
        int selectObject = 0;
        final int getDC = OS.GetDC(this.handle);
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        if (sendMessage != 0) {
            selectObject = OS.SelectObject(getDC, sendMessage);
        }
        OS.DrawText(getDC, tchar, -1, rect, 3104);
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(this.handle, getDC);
        this.setScrollWidth(rect.right - rect.left, b);
    }
    
    void setScrollWidth(final int n, final boolean b) {
        if (b) {
            if (n <= this.scrollWidth) {
                return;
            }
            this.setScrollWidth(n + 3);
        }
        else {
            if (n < this.scrollWidth) {
                return;
            }
            this.setScrollWidth();
        }
    }
    
    public void setSelection(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        int n = point.x;
        int n2 = point.y;
        if (!OS.IsUnicode && OS.IsDBLocale) {
            n = this.wcsToMbcsPos(n);
            n2 = this.wcsToMbcsPos(n2);
        }
        OS.SendMessage(this.handle, 322, 0, OS.MAKELPARAM(n, n2));
    }
    
    public void setText(String substring) {
        this.checkWidget();
        if (substring == null) {
            this.error(4);
        }
        if ((this.style & 0x8) != 0x0) {
            final int index = this.indexOf(substring);
            if (index != -1) {
                this.select(index);
            }
            return;
        }
        int limit = Combo.LIMIT;
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0) {
            limit = (OS.SendMessage(getDlgItem, 213, 0, 0) & Integer.MAX_VALUE);
        }
        if (substring.length() > limit) {
            substring = substring.substring(0, limit);
        }
        if (OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), substring, true))) {
            this.sendEvent(24);
        }
    }
    
    public void setTextLimit(final int n) {
        this.checkWidget();
        if (n == 0) {
            this.error(7);
        }
        OS.SendMessage(this.handle, 321, n, 0);
    }
    
    void setToolTipText(final Shell shell, final String s) {
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem != 0) {
            shell.setToolTipText(getDlgItem, s);
        }
        if (getDlgItem2 != 0) {
            shell.setToolTipText(getDlgItem2, s);
        }
        shell.setToolTipText(this.handle, s);
    }
    
    public void setVisibleItemCount(final int visibleCount) {
        this.checkWidget();
        if (visibleCount < 0) {
            return;
        }
        this.visibleCount = visibleCount;
        this.updateDropDownHeight();
    }
    
    void subclass() {
        super.subclass();
        final int windowProc = this.display.windowProc;
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0) {
            OS.SetWindowLongPtr(getDlgItem, -4, windowProc);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0) {
            OS.SetWindowLongPtr(getDlgItem2, -4, windowProc);
        }
    }
    
    boolean translateTraversal(final MSG msg) {
        switch (msg.wParam) {
            case 13:
            case 27: {
                if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0, 0) != 0) {
                    return false;
                }
                break;
            }
        }
        return super.translateTraversal(msg);
    }
    
    boolean traverseEscape() {
        if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0, 0) != 0) {
            OS.SendMessage(this.handle, 335, 0, 0);
            return true;
        }
        return super.traverseEscape();
    }
    
    boolean traverseReturn() {
        if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0, 0) != 0) {
            OS.SendMessage(this.handle, 335, 0, 0);
            return true;
        }
        return super.traverseReturn();
    }
    
    void unsubclass() {
        super.unsubclass();
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem != 0 && Combo.EditProc != 0) {
            OS.SetWindowLongPtr(getDlgItem, -4, Combo.EditProc);
        }
        final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
        if (getDlgItem2 != 0 && Combo.ListProc != 0) {
            OS.SetWindowLongPtr(getDlgItem2, -4, Combo.ListProc);
        }
    }
    
    void updateDropDownHeight() {
        if ((this.style & 0x4) != 0x0) {
            final RECT rect = new RECT();
            OS.SendMessage(this.handle, 338, 0, rect);
            final int n = this.getTextHeight() + this.getItemHeight() * ((this.getItemCount() == 0) ? 5 : this.visibleCount) + 2;
            if (n != rect.bottom - rect.top) {
                this.forceResize();
                OS.GetWindowRect(this.handle, rect);
                this.SetWindowPos(this.handle, 0, 0, 0, rect.right - rect.left, n, 54);
            }
        }
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
        int hwndItem = 0;
        int hwndList = 0;
        final COMBOBOXINFO comboboxinfo = new COMBOBOXINFO();
        comboboxinfo.cbSize = COMBOBOXINFO.sizeof;
        if (OS.GetComboBoxInfo(this.handle, comboboxinfo)) {
            hwndItem = comboboxinfo.hwndItem;
            hwndList = comboboxinfo.hwndList;
        }
        if (hwndItem != 0) {
            final int getWindowLong2 = OS.GetWindowLong(hwndItem, -20);
            final int getWindowLong3 = OS.GetWindowLong(hwndItem, -16);
            int n2;
            int n3;
            if ((this.style & 0x4000000) != 0x0) {
                n2 = (getWindowLong2 | 0x3000);
                n3 = (getWindowLong3 | 0x2);
            }
            else {
                n2 = (getWindowLong2 & 0xFFFFCFFF);
                n3 = (getWindowLong3 & 0xFFFFFFFD);
            }
            OS.SetWindowLong(hwndItem, -20, n2);
            OS.SetWindowLong(hwndItem, -16, n3);
            final RECT rect = new RECT();
            OS.GetWindowRect(hwndItem, rect);
            final int n4 = rect.right - rect.left;
            final int n5 = rect.bottom - rect.top;
            OS.GetWindowRect(this.handle, rect);
            final int n6 = rect.right - rect.left;
            final int n7 = rect.bottom - rect.top;
            final int n8 = 22;
            this.SetWindowPos(hwndItem, 0, 0, 0, n4 - 1, n5 - 1, n8);
            this.SetWindowPos(this.handle, 0, 0, 0, n6 - 1, n7 - 1, n8);
            this.SetWindowPos(hwndItem, 0, 0, 0, n4, n5, n8);
            this.SetWindowPos(this.handle, 0, 0, 0, n6, n7, n8);
            OS.InvalidateRect(this.handle, null, true);
        }
        if (hwndList != 0) {
            final int getWindowLong4 = OS.GetWindowLong(hwndList, -20);
            int n9;
            if ((this.style & 0x4000000) != 0x0) {
                n9 = (getWindowLong4 | 0x400000);
            }
            else {
                n9 = (getWindowLong4 & 0xFFBFFFFF);
            }
            OS.SetWindowLong(hwndList, -20, n9);
        }
    }
    
    String verifyText(final String text, final int start, final int end, final Event event) {
        final Event event2 = new Event();
        event2.text = text;
        event2.start = start;
        event2.end = end;
        if (event != null) {
            event2.character = event.character;
            event2.keyCode = event.keyCode;
            event2.stateMask = event.stateMask;
        }
        if (!OS.IsUnicode && OS.IsDBLocale) {
            event2.start = this.mbcsToWcsPos(start);
            event2.end = this.mbcsToWcsPos(end);
        }
        this.sendEvent(25, event2);
        if (!event2.doit || this.isDisposed()) {
            return null;
        }
        return event2.text;
    }
    
    int wcsToMbcsPos(final int n) {
        if (n <= 0) {
            return 0;
        }
        if (OS.IsUnicode) {
            return n;
        }
        final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
        if (getDlgItem == 0) {
            return n;
        }
        final int getWindowTextLengthA = OS.GetWindowTextLengthA(getDlgItem);
        if (getWindowTextLengthA == 0) {
            return 0;
        }
        final byte[] array = new byte[getWindowTextLengthA + 1];
        OS.GetWindowTextA(getDlgItem, array, getWindowTextLengthA + 1);
        int n2 = 0;
        for (int n3 = 0; n2 < getWindowTextLengthA && n != n3; ++n3) {
            if (OS.IsDBCSLeadByte(array[n2++])) {
                ++n2;
            }
        }
        return n2;
    }
    
    int widgetExtStyle() {
        return super.widgetExtStyle() & 0xFFEFFFFF;
    }
    
    int widgetStyle() {
        final int n = super.widgetStyle() | 0x40 | 0x400 | 0x100000 | 0x200000;
        if ((this.style & 0x40) != 0x0) {
            return n | 0x1;
        }
        if ((this.style & 0x8) != 0x0) {
            return n | 0x3;
        }
        return n | 0x2;
    }
    
    TCHAR windowClass() {
        return Combo.ComboClass;
    }
    
    int windowProc() {
        return Combo.ComboProc;
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (n != this.handle) {
            final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
            final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
            if ((getDlgItem != 0 && n == getDlgItem) || (getDlgItem2 != 0 && n == getDlgItem2)) {
                LRESULT lresult = null;
                switch (n2) {
                    case 258: {
                        lresult = this.wmChar(n, n3, n4);
                        break;
                    }
                    case 646: {
                        lresult = this.wmIMEChar(n, n3, n4);
                        break;
                    }
                    case 256: {
                        lresult = this.wmKeyDown(n, n3, n4);
                        break;
                    }
                    case 257: {
                        lresult = this.wmKeyUp(n, n3, n4);
                        break;
                    }
                    case 262: {
                        lresult = this.wmSysChar(n, n3, n4);
                        break;
                    }
                    case 260: {
                        lresult = this.wmSysKeyDown(n, n3, n4);
                        break;
                    }
                    case 261: {
                        lresult = this.wmSysKeyUp(n, n3, n4);
                        break;
                    }
                    case 533: {
                        lresult = this.wmCaptureChanged(n, n3, n4);
                        break;
                    }
                    case 515: {
                        lresult = this.wmLButtonDblClk(n, n3, n4);
                        break;
                    }
                    case 513: {
                        lresult = this.wmLButtonDown(n, n3, n4);
                        break;
                    }
                    case 514: {
                        lresult = this.wmLButtonUp(n, n3, n4);
                        break;
                    }
                    case 521: {
                        lresult = this.wmMButtonDblClk(n, n3, n4);
                        break;
                    }
                    case 519: {
                        lresult = this.wmMButtonDown(n, n3, n4);
                        break;
                    }
                    case 520: {
                        lresult = this.wmMButtonUp(n, n3, n4);
                        break;
                    }
                    case 673: {
                        lresult = this.wmMouseHover(n, n3, n4);
                        break;
                    }
                    case 675: {
                        lresult = this.wmMouseLeave(n, n3, n4);
                        break;
                    }
                    case 512: {
                        lresult = this.wmMouseMove(n, n3, n4);
                        break;
                    }
                    case 518: {
                        lresult = this.wmRButtonDblClk(n, n3, n4);
                        break;
                    }
                    case 516: {
                        lresult = this.wmRButtonDown(n, n3, n4);
                        break;
                    }
                    case 517: {
                        lresult = this.wmRButtonUp(n, n3, n4);
                        break;
                    }
                    case 525: {
                        lresult = this.wmXButtonDblClk(n, n3, n4);
                        break;
                    }
                    case 523: {
                        lresult = this.wmXButtonDown(n, n3, n4);
                        break;
                    }
                    case 524: {
                        lresult = this.wmXButtonUp(n, n3, n4);
                        break;
                    }
                    case 15: {
                        lresult = this.wmPaint(n, n3, n4);
                        break;
                    }
                    case 123: {
                        lresult = this.wmContextMenu(n, n3, n4);
                        break;
                    }
                    case 12:
                    case 199:
                    case 768:
                    case 770:
                    case 771:
                    case 772: {
                        if (n == getDlgItem) {
                            lresult = this.wmClipboard(n, n2, n3, n4);
                            break;
                        }
                        break;
                    }
                }
                if (lresult != null) {
                    return lresult.value;
                }
                return this.callWindowProc(n, n2, n3, n4);
            }
        }
        if (n2 == 334 && (this.style & 0x8) != 0x0 && (this.hooks(25) || this.filters(25))) {
            final String text = this.getText();
            String item = null;
            if (n3 == -1) {
                item = "";
            }
            else if (n3 >= 0 && n3 < this.getItemCount()) {
                item = this.getItem(n3);
            }
            if (item != null && !item.equals(text)) {
                final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
                final String s = item;
                final String verifyText = this.verifyText(item, 0, getWindowTextLength, null);
                if (verifyText == null) {
                    return 0;
                }
                if (!verifyText.equals(s)) {
                    final int index = this.indexOf(verifyText);
                    if (index != -1 && index != n3) {
                        return this.callWindowProc(this.handle, 334, index, n4);
                    }
                }
            }
        }
        return super.windowProc(n, n2, n3, n4);
    }
    
    LRESULT WM_CTLCOLOR(final int n, final int n2) {
        return this.wmColorChild(n, n2);
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        return new LRESULT(this.callWindowProc(this.handle, 135, n, n2) | 0x1);
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        if ((this.style & 0x8) != 0x0) {
            return super.WM_KILLFOCUS(n, n2);
        }
        return null;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final int sendMessage = OS.SendMessage(this.handle, 327, 0, 0);
        final LRESULT wm_LBUTTONDOWN = super.WM_LBUTTONDOWN(n, n2);
        if (wm_LBUTTONDOWN == LRESULT.ZERO) {
            return wm_LBUTTONDOWN;
        }
        if ((this.style & 0x8) == 0x0 && sendMessage != OS.SendMessage(this.handle, 327, 0, 0)) {
            this.sendEvent(24);
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
            this.sendSelectionEvent(13, null, true);
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
        }
        return wm_LBUTTONDOWN;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        if (this.ignoreResize) {
            return null;
        }
        if ((this.style & 0x40) != 0x0) {
            final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
            if (OS.IsWindowVisible(this.handle)) {
                if (OS.IsWinCE) {
                    final int getDlgItem = OS.GetDlgItem(this.handle, 1001);
                    if (getDlgItem != 0) {
                        OS.InvalidateRect(getDlgItem, null, true);
                    }
                    final int getDlgItem2 = OS.GetDlgItem(this.handle, 1000);
                    if (getDlgItem2 != 0) {
                        OS.InvalidateRect(getDlgItem2, null, true);
                    }
                }
                else {
                    OS.RedrawWindow(this.handle, null, 0, 133);
                }
            }
            return wm_SIZE;
        }
        final boolean lockText = this.lockText;
        if ((this.style & 0x8) == 0x0) {
            this.lockText = true;
        }
        final LRESULT wm_SIZE2 = super.WM_SIZE(n, n2);
        if ((this.style & 0x8) == 0x0) {
            this.lockText = lockText;
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(this.scrollWidth);
        }
        return wm_SIZE2;
    }
    
    LRESULT WM_WINDOWPOSCHANGING(final int n, final int n2) {
        final LRESULT wm_WINDOWPOSCHANGING = super.WM_WINDOWPOSCHANGING(n, n2);
        if (wm_WINDOWPOSCHANGING != null) {
            return wm_WINDOWPOSCHANGING;
        }
        if (OS.IsWinCE) {
            return wm_WINDOWPOSCHANGING;
        }
        if (!this.getDrawing()) {
            return wm_WINDOWPOSCHANGING;
        }
        if (!OS.IsWindowVisible(this.handle)) {
            return wm_WINDOWPOSCHANGING;
        }
        if (this.ignoreResize) {
            final WINDOWPOS windowpos = new WINDOWPOS();
            OS.MoveMemory(windowpos, n2, WINDOWPOS.sizeof);
            if ((windowpos.flags & 0x1) == 0x0) {
                final WINDOWPOS windowpos2 = windowpos;
                windowpos2.flags |= 0x8;
                OS.MoveMemory(n2, windowpos, WINDOWPOS.sizeof);
                OS.InvalidateRect(this.handle, null, true);
                final RECT rect = new RECT();
                OS.GetWindowRect(this.handle, rect);
                final int n3 = rect.right - rect.left;
                final int n4 = rect.bottom - rect.top;
                if (n3 != 0 && n4 != 0) {
                    final int handle = this.parent.handle;
                    int i = OS.GetWindow(handle, 5);
                    OS.MapWindowPoints(0, handle, rect, 2);
                    final int createRectRgn = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
                    while (i != 0) {
                        if (i != this.handle) {
                            OS.GetWindowRect(i, rect);
                            OS.MapWindowPoints(0, handle, rect, 2);
                            final int createRectRgn2 = OS.CreateRectRgn(rect.left, rect.top, rect.right, rect.bottom);
                            OS.CombineRgn(createRectRgn, createRectRgn, createRectRgn2, 4);
                            OS.DeleteObject(createRectRgn2);
                        }
                        i = OS.GetWindow(i, 2);
                    }
                    OS.RedrawWindow(handle, null, createRectRgn, 1029);
                    OS.DeleteObject(createRectRgn);
                }
            }
        }
        return wm_WINDOWPOSCHANGING;
    }
    
    LRESULT wmChar(final int n, final int n2, final int n3) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT wmChar = super.wmChar(n, n2, n3);
        if (wmChar != null) {
            return wmChar;
        }
        switch (n2) {
            case 9: {
                return LRESULT.ZERO;
            }
            case 13: {
                if (!this.ignoreDefaultSelection) {
                    this.sendSelectionEvent(14);
                }
                this.ignoreDefaultSelection = false;
            }
            case 27: {
                if ((this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0, 0) == 0) {
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return wmChar;
    }
    
    LRESULT wmClipboard(final int n, final int n2, final int n3, final int n4) {
        if ((this.style & 0x8) != 0x0) {
            return null;
        }
        if (!this.hooks(25) && !this.filters(25)) {
            return null;
        }
        boolean b = false;
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        String s = null;
        switch (n2) {
            case 768:
            case 771: {
                OS.SendMessage(n, 176, array, array2);
                if (array[0] != array2[0]) {
                    s = "";
                    b = true;
                    break;
                }
                break;
            }
            case 770: {
                OS.SendMessage(n, 176, array, array2);
                s = this.getClipboardText();
                break;
            }
            case 199:
            case 772: {
                if (OS.SendMessage(n, 198, 0, 0) != 0) {
                    this.ignoreModify = true;
                    OS.CallWindowProc(Combo.EditProc, n, n2, n3, n4);
                    final int getWindowTextLength = OS.GetWindowTextLength(n);
                    final int[] array3 = { 0 };
                    final int[] array4 = { 0 };
                    OS.SendMessage(n, 176, array3, array4);
                    if (getWindowTextLength != 0 && array3[0] != array4[0]) {
                        final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
                        OS.GetWindowText(n, tchar, getWindowTextLength + 1);
                        s = tchar.toString(array3[0], array4[0] - array3[0]);
                    }
                    else {
                        s = "";
                    }
                    OS.CallWindowProc(Combo.EditProc, n, n2, n3, n4);
                    OS.SendMessage(n, 176, array, array2);
                    this.ignoreModify = false;
                    break;
                }
                break;
            }
            case 12: {
                array2[0] = OS.GetWindowTextLength(n);
                final int n5 = OS.IsUnicode ? OS.wcslen(n4) : C.strlen(n4);
                final TCHAR tchar2 = new TCHAR(this.getCodePage(), n5);
                OS.MoveMemory(tchar2, n4, tchar2.length() * TCHAR.sizeof);
                s = tchar2.toString(0, n5);
                break;
            }
        }
        if (s != null) {
            final String s2 = s;
            final String verifyText = this.verifyText(s, array[0], array2[0], null);
            if (verifyText == null) {
                return LRESULT.ZERO;
            }
            if (!verifyText.equals(s2)) {
                if (b) {
                    OS.CallWindowProc(Combo.EditProc, n, n2, n3, n4);
                }
                final TCHAR tchar3 = new TCHAR(this.getCodePage(), verifyText, true);
                if (n2 == 12) {
                    final int getProcessHeap = OS.GetProcessHeap();
                    final int n6 = tchar3.length() * TCHAR.sizeof;
                    final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n6);
                    OS.MoveMemory(heapAlloc, tchar3, n6);
                    final int callWindowProc = OS.CallWindowProc(Combo.EditProc, n, n2, n3, heapAlloc);
                    OS.HeapFree(getProcessHeap, 0, heapAlloc);
                    return new LRESULT(callWindowProc);
                }
                OS.SendMessage(n, 194, 0, tchar3);
                return LRESULT.ZERO;
            }
        }
        return null;
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        final int hiword = OS.HIWORD(n);
        switch (hiword) {
            case 5: {
                if (this.ignoreModify) {
                    break;
                }
                this.noSelection = true;
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                this.noSelection = false;
                break;
            }
            case 1: {
                final int sendMessage = OS.SendMessage(this.handle, 327, 0, 0);
                if (sendMessage != -1) {
                    OS.SendMessage(this.handle, 334, sendMessage, 0);
                }
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                this.sendSelectionEvent(13);
                break;
            }
            case 3: {
                this.sendFocusEvent(15);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
            case 7: {
                this.setCursor();
                this.updateDropDownHeight();
                break;
            }
            case 4: {
                if ((this.style & 0x8) != 0x0) {
                    break;
                }
                this.sendFocusEvent(16);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
            case 1792:
            case 1793: {
                final Event event = new Event();
                event.doit = true;
                this.sendEvent(44, event);
                if (!event.doit) {
                    final int getWindowLong = OS.GetWindowLong(n2, -20);
                    final int getWindowLong2 = OS.GetWindowLong(n2, -16);
                    int n3;
                    int n4;
                    if (hiword == 1792) {
                        n3 = (getWindowLong | 0x3000);
                        n4 = (getWindowLong2 | 0x2);
                    }
                    else {
                        n3 = (getWindowLong & 0xFFFFCFFF);
                        n4 = (getWindowLong2 & 0xFFFFFFFD);
                    }
                    OS.SetWindowLong(n2, -20, n3);
                    OS.SetWindowLong(n2, -16, n4);
                    break;
                }
                break;
            }
        }
        return super.wmCommandChild(n, n2);
    }
    
    LRESULT wmIMEChar(final int n, final int lastAscii, final int n2) {
        final Display display = this.display;
        display.lastKey = 0;
        display.lastAscii = lastAscii;
        final Display display2 = display;
        final Display display3 = display;
        final Display display4 = display;
        final boolean lastVirtual = false;
        display4.lastDead = lastVirtual;
        display3.lastNull = lastVirtual;
        display2.lastVirtual = lastVirtual;
        if (!this.sendKeyEvent(1, 646, lastAscii, n2)) {
            return LRESULT.ZERO;
        }
        this.ignoreCharacter = true;
        final int callWindowProc = this.callWindowProc(n, 646, lastAscii, n2);
        final MSG msg = new MSG();
        while (OS.PeekMessage(msg, n, 258, 258, 10420227)) {
            OS.TranslateMessage(msg);
            OS.DispatchMessage(msg);
        }
        this.ignoreCharacter = false;
        this.sendKeyEvent(2, 646, lastAscii, n2);
        final Display display5 = display;
        final Display display6 = display;
        final boolean b = false;
        display6.lastAscii = (b ? 1 : 0);
        display5.lastKey = (b ? 1 : 0);
        return new LRESULT(callWindowProc);
    }
    
    LRESULT wmKeyDown(final int n, final int n2, final int n3) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT wmKeyDown = super.wmKeyDown(n, n2, n3);
        if (wmKeyDown != null) {
            return wmKeyDown;
        }
        this.ignoreDefaultSelection = false;
        if (n2 == 13 && (this.style & 0x4) != 0x0 && OS.SendMessage(this.handle, 343, 0, 0) != 0) {
            this.ignoreDefaultSelection = true;
        }
        return wmKeyDown;
    }
    
    LRESULT wmSysKeyDown(final int n, final int n2, final int n3) {
        final int sendMessage = OS.SendMessage(this.handle, 327, 0, 0);
        final LRESULT wmSysKeyDown = super.wmSysKeyDown(n, n2, n3);
        if (wmSysKeyDown != null) {
            return wmSysKeyDown;
        }
        if ((this.style & 0x8) == 0x0 && n2 == 40) {
            final int callWindowProc = this.callWindowProc(n, 260, n2, n3);
            if (sendMessage != OS.SendMessage(this.handle, 327, 0, 0)) {
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                this.sendSelectionEvent(13, null, true);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
            }
            return new LRESULT(callWindowProc);
        }
        return wmSysKeyDown;
    }
}
