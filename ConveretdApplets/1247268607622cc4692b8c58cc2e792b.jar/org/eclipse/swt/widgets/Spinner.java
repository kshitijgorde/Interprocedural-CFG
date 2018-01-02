// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.NMUPDOWN;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.UDACCEL;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;

public class Spinner extends Composite
{
    int hwndText;
    int hwndUpDown;
    boolean ignoreModify;
    boolean ignoreCharacter;
    int pageIncrement;
    int digits;
    static final int EditProc;
    static final TCHAR EditClass;
    static final int UpDownProc;
    static final TCHAR UpDownClass;
    public static final int LIMIT;
    
    static {
        EditClass = new TCHAR(0, "EDIT", true);
        UpDownClass = new TCHAR(0, "msctls_updown32", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Spinner.EditClass, wndclass);
        EditProc = wndclass.lpfnWndProc;
        OS.GetClassInfo(0, Spinner.UpDownClass, wndclass);
        UpDownProc = wndclass.lpfnWndProc;
        LIMIT = (OS.IsWinNT ? Integer.MAX_VALUE : 32767);
    }
    
    public Spinner(final Composite composite, final int n) {
        super(composite, checkStyle(n));
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        if (n == this.hwndText) {
            return OS.CallWindowProc(Spinner.EditProc, n, n2, n3, n4);
        }
        if (n == this.hwndUpDown) {
            return OS.CallWindowProc(Spinner.UpDownProc, n, n2, n3, n4);
        }
        return OS.DefWindowProc(this.handle, n2, n3, n4);
    }
    
    static int checkStyle(final int n) {
        return n & 0xFFFFFCFF;
    }
    
    boolean checkHandle(final int n) {
        return n == this.handle || n == this.hwndText || n == this.hwndUpDown;
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    void createHandle() {
        super.createHandle();
        this.state &= 0xFFFFFEFD;
        final int getModuleHandle = OS.GetModuleHandle(null);
        int n = ((this.style & 0x800) != 0x0) ? 512 : 0;
        int n2 = 1409286272;
        if ((this.style & 0x8) != 0x0) {
            n2 |= 0x800;
        }
        if (OS.WIN32_VERSION >= OS.VERSION(4, 10) && (this.style & 0x4000000) != 0x0) {
            n |= 0x400000;
        }
        this.hwndText = OS.CreateWindowEx(n, Spinner.EditClass, null, n2, 0, 0, 0, 0, this.handle, 0, getModuleHandle, null);
        if (this.hwndText == 0) {
            this.error(2);
        }
        OS.SetWindowLongPtr(this.hwndText, -12, this.hwndText);
        int n3 = 1342177296;
        if ((this.style & 0x40) != 0x0) {
            n3 |= 0x1;
        }
        if ((this.style & 0x800) != 0x0) {
            if ((this.style & 0x4000000) != 0x0) {
                n3 |= 0x8;
            }
            else {
                n3 |= 0x4;
            }
        }
        this.hwndUpDown = OS.CreateWindowEx(0, Spinner.UpDownClass, null, n3, 0, 0, 0, 0, this.handle, 0, getModuleHandle, null);
        if (this.hwndUpDown == 0) {
            this.error(2);
        }
        this.SetWindowPos(this.hwndText, this.hwndUpDown, 0, 0, 0, 0, 19);
        OS.SetWindowLongPtr(this.hwndUpDown, -12, this.hwndUpDown);
        if (OS.IsDBLocale) {
            final int immGetContext = OS.ImmGetContext(this.handle);
            OS.ImmAssociateContext(this.hwndText, immGetContext);
            OS.ImmAssociateContext(this.hwndUpDown, immGetContext);
            OS.ImmReleaseContext(this.handle, immGetContext);
        }
        OS.SendMessage(this.hwndUpDown, 1135, 0, 100);
        OS.SendMessage(this.hwndUpDown, OS.IsWinCE ? 1127 : 1137, 0, 0);
        this.pageIncrement = 10;
        this.digits = 0;
        OS.SetWindowText(this.hwndText, new TCHAR(this.getCodePage(), "0", true));
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
    
    void addVerifyListener(final VerifyListener verifyListener) {
        this.checkWidget();
        if (verifyListener == null) {
            this.error(4);
        }
        this.addListener(25, new TypedListener(verifyListener));
    }
    
    int borderHandle() {
        return this.hwndText;
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int tmHeight = 0;
        if (n == -1 || n2 == -1) {
            int selectObject = 0;
            final int getDC = OS.GetDC(this.hwndText);
            final int sendMessage = OS.SendMessage(this.hwndText, 49, 0, 0);
            if (sendMessage != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage);
            }
            final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
            OS.GetTextMetrics(getDC, textmetric);
            tmHeight = textmetric.tmHeight;
            final RECT rect = new RECT();
            final int[] array = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, null, array);
            String s = String.valueOf(array[0]);
            if (this.digits > 0) {
                final StringBuffer sb = new StringBuffer();
                sb.append(s);
                sb.append(this.getDecimalSeparator());
                for (int i = this.digits - s.length(); i >= 0; --i) {
                    sb.append("0");
                }
                s = sb.toString();
            }
            final TCHAR tchar = new TCHAR(this.getCodePage(), s, false);
            OS.DrawText(getDC, tchar, tchar.length(), rect, 11264);
            n3 = rect.right - rect.left;
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.hwndText, getDC);
        }
        if (n3 == 0) {
            n3 = 64;
        }
        if (tmHeight == 0) {
            tmHeight = 64;
        }
        if (n != -1) {
            n3 = n;
        }
        if (n2 != -1) {
            tmHeight = n2;
        }
        final Rectangle computeTrim = this.computeTrim(0, 0, n3, tmHeight);
        if (n2 == -1) {
            int n4 = OS.GetSystemMetrics(20) + 2 * this.getBorderWidth();
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                n4 += (((this.style & 0x800) != 0x0) ? 1 : 3);
            }
            computeTrim.height = Math.max(computeTrim.height, n4);
        }
        return new Point(computeTrim.width, computeTrim.height);
    }
    
    public Rectangle computeTrim(int n, int n2, int n3, int n4) {
        this.checkWidget();
        final RECT rect = new RECT();
        OS.SetRect(rect, n, n2, n + n3, n2 + n4);
        OS.AdjustWindowRectEx(rect, OS.GetWindowLong(this.hwndText, -16), false, OS.GetWindowLong(this.hwndText, -20));
        n3 = rect.right - rect.left;
        n4 = rect.bottom - rect.top;
        final int sendMessage = OS.SendMessage(this.hwndText, 212, 0, 0);
        n -= OS.LOWORD(sendMessage);
        n3 += OS.LOWORD(sendMessage) + OS.HIWORD(sendMessage);
        if ((this.style & 0x800) != 0x0) {
            --n;
            --n2;
            n3 += 2;
            n4 += 2;
        }
        n3 += OS.GetSystemMetrics(2);
        return new Rectangle(n, n2, n3, n4);
    }
    
    public void copy() {
        this.checkWidget();
        OS.SendMessage(this.hwndText, 769, 0, 0);
    }
    
    public void cut() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.hwndText, 768, 0, 0);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_WINDOW);
    }
    
    void enableWidget(final boolean b) {
        super.enableWidget(b);
        OS.EnableWindow(this.hwndText, b);
        OS.EnableWindow(this.hwndUpDown, b);
    }
    
    void deregister() {
        super.deregister();
        this.display.removeControl(this.hwndText);
        this.display.removeControl(this.hwndUpDown);
    }
    
    boolean hasFocus() {
        final int getFocus = OS.GetFocus();
        return getFocus == this.handle || getFocus == this.hwndText || getFocus == this.hwndUpDown;
    }
    
    public int getDigits() {
        this.checkWidget();
        return this.digits;
    }
    
    String getDecimalSeparator() {
        final TCHAR tchar = new TCHAR(this.getCodePage(), 4);
        final int getLocaleInfo = OS.GetLocaleInfo(1024, 14, tchar, 4);
        return (getLocaleInfo != 0) ? tchar.toString(0, getLocaleInfo - 1) : ".";
    }
    
    public int getIncrement() {
        this.checkWidget();
        final UDACCEL udaccel = new UDACCEL();
        OS.SendMessage(this.hwndUpDown, 1132, 1, udaccel);
        return udaccel.nInc;
    }
    
    public int getMaximum() {
        this.checkWidget();
        final int[] array = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, null, array);
        return array[0];
    }
    
    public int getMinimum() {
        this.checkWidget();
        final int[] array = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, array, null);
        return array[0];
    }
    
    public int getPageIncrement() {
        this.checkWidget();
        return this.pageIncrement;
    }
    
    public int getSelection() {
        this.checkWidget();
        if (OS.IsWinCE) {
            return OS.LOWORD(OS.SendMessage(this.hwndUpDown, 1128, 0, 0));
        }
        return OS.SendMessage(this.hwndUpDown, 1138, 0, 0);
    }
    
    int getSelectionText(final boolean[] array) {
        final int getWindowTextLength = OS.GetWindowTextLength(this.hwndText);
        final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
        OS.GetWindowText(this.hwndText, tchar, getWindowTextLength + 1);
        final String string = tchar.toString(0, getWindowTextLength);
        try {
            int n3;
            if (this.digits > 0) {
                final int index = string.indexOf(this.getDecimalSeparator());
                if (index != -1) {
                    final int n = (string.startsWith("+") || string.startsWith("-")) ? 1 : 0;
                    final String s = (n != index) ? string.substring(n, index) : "0";
                    String s2 = string.substring(index + 1);
                    if (s2.length() > this.digits) {
                        s2 = s2.substring(0, this.digits);
                    }
                    else {
                        for (int n2 = this.digits - s2.length(), i = 0; i < n2; ++i) {
                            s2 = String.valueOf(s2) + "0";
                        }
                    }
                    int int1 = Integer.parseInt(s);
                    final int int2 = Integer.parseInt(s2);
                    for (int j = 0; j < this.digits; ++j) {
                        int1 *= 10;
                    }
                    n3 = int1 + int2;
                    if (string.startsWith("-")) {
                        n3 = -n3;
                    }
                }
                else {
                    n3 = Integer.parseInt(string);
                    for (int k = 0; k < this.digits; ++k) {
                        n3 *= 10;
                    }
                }
            }
            else {
                n3 = Integer.parseInt(string);
            }
            final int[] array2 = { 0 };
            final int[] array3 = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, array3, array2);
            if (array3[0] <= n3 && n3 <= array2[0]) {
                return n3;
            }
        }
        catch (NumberFormatException ex) {}
        array[0] = true;
        return -1;
    }
    
    public String getText() {
        this.checkWidget();
        final int getWindowTextLength = OS.GetWindowTextLength(this.hwndText);
        if (getWindowTextLength == 0) {
            return "";
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
        OS.GetWindowText(this.hwndText, tchar, getWindowTextLength + 1);
        return tchar.toString(0, getWindowTextLength);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        return OS.SendMessage(this.hwndText, 213, 0, 0) & Integer.MAX_VALUE;
    }
    
    int mbcsToWcsPos(final int n) {
        if (n <= 0) {
            return 0;
        }
        if (OS.IsUnicode) {
            return n;
        }
        final int getWindowTextLengthA = OS.GetWindowTextLengthA(this.hwndText);
        if (getWindowTextLengthA == 0) {
            return 0;
        }
        if (n >= getWindowTextLengthA) {
            return getWindowTextLengthA;
        }
        final byte[] array = new byte[getWindowTextLengthA + 1];
        OS.GetWindowTextA(this.hwndText, array, getWindowTextLengthA + 1);
        return OS.MultiByteToWideChar(this.getCodePage(), 1, array, n, null, 0);
    }
    
    public void paste() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.hwndText, 770, 0, 0);
    }
    
    void register() {
        super.register();
        this.display.addControl(this.hwndText, this);
        this.display.addControl(this.hwndUpDown, this);
    }
    
    void releaseHandle() {
        super.releaseHandle();
        final boolean b = false;
        this.hwndUpDown = (b ? 1 : 0);
        this.hwndText = (b ? 1 : 0);
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
    
    void removeVerifyListener(final VerifyListener verifyListener) {
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
        if (OS.GetKeyState(1) < 0) {
            return true;
        }
        String s = "";
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.hwndText, 176, array, array2);
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
                    OS.SendMessage(this.hwndText, 177, array[0], array2[0]);
                    OS.SendMessage(this.hwndText, 176, array3, array4);
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
                final int getWindowTextLength = OS.GetWindowTextLength(this.hwndText);
                if (array[0] == getWindowTextLength) {
                    return true;
                }
                ++array2[0];
                if (!OS.IsUnicode && OS.IsDBLocale) {
                    final int[] array5 = { 0 };
                    final int[] array6 = { 0 };
                    OS.SendMessage(this.hwndText, 177, array[0], array2[0]);
                    OS.SendMessage(this.hwndText, 176, array5, array6);
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
        OS.SendMessage(this.hwndText, 177, array[0], array2[0]);
        OS.SendMessage(this.hwndText, 194, 0, tchar);
        return false;
    }
    
    void setBackgroundImage(final int backgroundImage) {
        super.setBackgroundImage(backgroundImage);
        OS.InvalidateRect(this.hwndText, null, true);
    }
    
    void setBackgroundPixel(final int backgroundPixel) {
        super.setBackgroundPixel(backgroundPixel);
        OS.InvalidateRect(this.hwndText, null, true);
    }
    
    public void setDigits(final int digits) {
        this.checkWidget();
        if (digits < 0) {
            this.error(5);
        }
        if (digits == this.digits) {
            return;
        }
        this.digits = digits;
        int n;
        if (OS.IsWinCE) {
            n = OS.LOWORD(OS.SendMessage(this.hwndUpDown, 1128, 0, 0));
        }
        else {
            n = OS.SendMessage(this.hwndUpDown, 1138, 0, 0);
        }
        this.setSelection(n, false, true, false);
    }
    
    void setForegroundPixel(final int foregroundPixel) {
        super.setForegroundPixel(foregroundPixel);
        OS.InvalidateRect(this.hwndText, null, true);
    }
    
    public void setIncrement(final int n) {
        this.checkWidget();
        if (n < 1) {
            return;
        }
        final int getProcessHeap = OS.GetProcessHeap();
        final int sendMessage = OS.SendMessage(this.hwndUpDown, 1132, 0, (UDACCEL)null);
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, UDACCEL.sizeof * sendMessage);
        OS.SendMessage(this.hwndUpDown, 1132, sendMessage, heapAlloc);
        int nInc = -1;
        final UDACCEL udaccel = new UDACCEL();
        for (int i = 0; i < sendMessage; ++i) {
            final int n2 = heapAlloc + i * UDACCEL.sizeof;
            OS.MoveMemory(udaccel, n2, UDACCEL.sizeof);
            if (nInc == -1) {
                nInc = udaccel.nInc;
            }
            udaccel.nInc = udaccel.nInc / nInc * n;
            OS.MoveMemory(n2, udaccel, UDACCEL.sizeof);
        }
        OS.SendMessage(this.hwndUpDown, 1131, sendMessage, heapAlloc);
        OS.HeapFree(getProcessHeap, 0, heapAlloc);
    }
    
    public void setMaximum(final int n) {
        this.checkWidget();
        final int[] array = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, array, null);
        if (n < array[0]) {
            return;
        }
        int n2;
        if (OS.IsWinCE) {
            n2 = OS.LOWORD(OS.SendMessage(this.hwndUpDown, 1128, 0, 0));
        }
        else {
            n2 = OS.SendMessage(this.hwndUpDown, 1138, 0, 0);
        }
        OS.SendMessage(this.hwndUpDown, 1135, array[0], n);
        if (n2 > n) {
            this.setSelection(n, true, true, false);
        }
    }
    
    public void setMinimum(final int n) {
        this.checkWidget();
        final int[] array = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, null, array);
        if (n > array[0]) {
            return;
        }
        int n2;
        if (OS.IsWinCE) {
            n2 = OS.LOWORD(OS.SendMessage(this.hwndUpDown, 1128, 0, 0));
        }
        else {
            n2 = OS.SendMessage(this.hwndUpDown, 1138, 0, 0);
        }
        OS.SendMessage(this.hwndUpDown, 1135, n, array[0]);
        if (n2 < n) {
            this.setSelection(n, true, true, false);
        }
    }
    
    public void setPageIncrement(final int pageIncrement) {
        this.checkWidget();
        if (pageIncrement < 1) {
            return;
        }
        this.pageIncrement = pageIncrement;
    }
    
    public void setSelection(int min) {
        this.checkWidget();
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.hwndUpDown, 1136, array2, array);
        min = Math.min(Math.max(array2[0], min), array[0]);
        this.setSelection(min, true, true, false);
    }
    
    void setSelection(final int n, final boolean b, final boolean b2, final boolean b3) {
        if (b) {
            OS.SendMessage(this.hwndUpDown, OS.IsWinCE ? 1127 : 1137, 0, n);
        }
        if (b2) {
            String s;
            if (this.digits == 0) {
                s = String.valueOf(n);
            }
            else {
                final String value = String.valueOf(Math.abs(n));
                final String decimalSeparator = this.getDecimalSeparator();
                int n2 = value.length() - this.digits;
                final StringBuffer sb = new StringBuffer();
                if (n < 0) {
                    sb.append("-");
                }
                if (n2 > 0) {
                    sb.append(value.substring(0, n2));
                    sb.append(decimalSeparator);
                    sb.append(value.substring(n2));
                }
                else {
                    sb.append("0");
                    sb.append(decimalSeparator);
                    while (n2++ < 0) {
                        sb.append("0");
                    }
                    sb.append(value);
                }
                s = sb.toString();
            }
            if (this.hooks(25) || this.filters(25)) {
                s = this.verifyText(s, 0, OS.GetWindowTextLength(this.hwndText), null);
                if (s == null) {
                    return;
                }
            }
            OS.SetWindowText(this.hwndText, new TCHAR(this.getCodePage(), s, true));
            OS.SendMessage(this.hwndText, 177, 0, -1);
            if (!OS.IsWinCE) {
                OS.NotifyWinEvent(32773, this.hwndText, -4, 0);
            }
        }
        if (b3) {
            this.sendSelectionEvent(13);
        }
    }
    
    public void setTextLimit(final int n) {
        this.checkWidget();
        if (n == 0) {
            this.error(7);
        }
        OS.SendMessage(this.hwndText, 197, n, 0);
    }
    
    void setToolTipText(final Shell shell, final String s) {
        shell.setToolTipText(this.hwndText, s);
        shell.setToolTipText(this.hwndUpDown, s);
    }
    
    public void setValues(int min, final int n, final int n2, final int digits, final int increment, final int pageIncrement) {
        this.checkWidget();
        if (n2 < n) {
            return;
        }
        if (digits < 0) {
            return;
        }
        if (increment < 1) {
            return;
        }
        if (pageIncrement < 1) {
            return;
        }
        min = Math.min(Math.max(n, min), n2);
        this.setIncrement(increment);
        this.pageIncrement = pageIncrement;
        this.digits = digits;
        OS.SendMessage(this.hwndUpDown, 1135, n, n2);
        this.setSelection(min, true, true, false);
    }
    
    void subclass() {
        super.subclass();
        final int windowProc = this.display.windowProc;
        OS.SetWindowLongPtr(this.hwndText, -4, windowProc);
        OS.SetWindowLongPtr(this.hwndUpDown, -4, windowProc);
    }
    
    void unsubclass() {
        super.unsubclass();
        OS.SetWindowLongPtr(this.hwndText, -4, Spinner.EditProc);
        OS.SetWindowLongPtr(this.hwndUpDown, -4, Spinner.UpDownProc);
    }
    
    void updateOrientation() {
        super.updateOrientation();
        final int getWindowLong = OS.GetWindowLong(this.hwndText, -20);
        final int getWindowLong2 = OS.GetWindowLong(this.hwndText, -16);
        int n;
        int n2;
        if ((this.style & 0x4000000) != 0x0) {
            n = (getWindowLong | 0x1000);
            n2 = (getWindowLong2 | 0x2);
        }
        else {
            n = (getWindowLong & 0xFFFFEFFF);
            n2 = (getWindowLong2 & 0xFFFFFFFD);
        }
        OS.SetWindowLong(this.hwndText, -16, n2);
        OS.SetWindowLong(this.hwndText, -20, n);
        final RECT rect = new RECT();
        OS.GetWindowRect(this.handle, rect);
        final int n3 = rect.right - rect.left;
        final int n4 = rect.bottom - rect.top;
        OS.SetWindowPos(this.handle, 0, 0, 0, n3 - 1, n4 - 1, 6);
        OS.SetWindowPos(this.handle, 0, 0, 0, n3, n4, 6);
    }
    
    String verifyText(String string, final int start, final int end, final Event event) {
        final Event event2 = new Event();
        event2.text = string;
        event2.start = start;
        event2.end = end;
        if (event != null) {
            event2.character = event.character;
            event2.keyCode = event.keyCode;
            event2.stateMask = event.stateMask;
        }
        int n = 0;
        if (this.digits > 0) {
            final int index = string.indexOf(this.getDecimalSeparator());
            if (index != -1) {
                string = String.valueOf(string.substring(0, index)) + string.substring(index + 1);
            }
            n = 0;
        }
        if (string.length() > 0) {
            final int[] array = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, array, null);
            if (array[0] < 0 && string.charAt(0) == '-') {
                ++n;
            }
        }
        while (n < string.length() && Character.isDigit(string.charAt(n))) {
            ++n;
        }
        event2.doit = (n == string.length());
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
    
    int widgetExtStyle() {
        return super.widgetExtStyle() & 0xFFFFFDFF;
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        if (n != this.hwndText && n != this.hwndUpDown) {
            return super.windowProc(n, n2, n3, n4);
        }
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
            case 7: {
                lresult = this.wmSetFocus(n, n3, n4);
                break;
            }
            case 8: {
                lresult = this.wmKillFocus(n, n3, n4);
                break;
            }
            case 15: {
                lresult = this.wmPaint(n, n3, n4);
                break;
            }
            case 791: {
                lresult = this.wmPrint(n, n3, n4);
                break;
            }
            case 123: {
                lresult = this.wmContextMenu(n, n3, n4);
                break;
            }
            case 199:
            case 768:
            case 770:
            case 771:
            case 772: {
                if (n == this.hwndText) {
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
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        super.WM_ERASEBKGND(n, n2);
        this.drawBackground(n);
        return LRESULT.ONE;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        return null;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        OS.SetFocus(this.hwndText);
        OS.SendMessage(this.hwndText, 177, 0, -1);
        return null;
    }
    
    LRESULT WM_SETFONT(final int n, final int n2) {
        final LRESULT wm_SETFONT = super.WM_SETFONT(n, n2);
        if (wm_SETFONT != null) {
            return wm_SETFONT;
        }
        OS.SendMessage(this.hwndText, 48, n, n2);
        return wm_SETFONT;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (this.isDisposed()) {
            return wm_SIZE;
        }
        final int loword = OS.LOWORD(n2);
        final int hiword = OS.HIWORD(n2);
        final int getSystemMetrics = OS.GetSystemMetrics(2);
        final int n3 = loword - getSystemMetrics;
        final int getSystemMetrics2 = OS.GetSystemMetrics(45);
        final int n4 = 52;
        this.SetWindowPos(this.hwndText, 0, 0, 0, n3 + getSystemMetrics2, hiword, n4);
        this.SetWindowPos(this.hwndUpDown, 0, n3, 0, getSystemMetrics, hiword, n4);
        return wm_SIZE;
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
    
    LRESULT wmChar(final int n, final int n2, final int n3) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT wmChar = super.wmChar(n, n2, n3);
        if (wmChar != null) {
            return wmChar;
        }
        switch (n2) {
            case 13: {
                this.sendSelectionEvent(14);
            }
            case 9:
            case 27: {
                return LRESULT.ZERO;
            }
            default: {
                return wmChar;
            }
        }
    }
    
    LRESULT wmClipboard(final int n, final int n2, final int n3, final int n4) {
        if ((this.style & 0x8) != 0x0) {
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
                    OS.CallWindowProc(Spinner.EditProc, n, n2, n3, n4);
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
                    OS.CallWindowProc(Spinner.EditProc, n, n2, n3, n4);
                    OS.SendMessage(n, 176, array, array2);
                    this.ignoreModify = false;
                    break;
                }
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
                    OS.CallWindowProc(Spinner.EditProc, n, n2, n3, n4);
                }
                final TCHAR tchar2 = new TCHAR(this.getCodePage(), verifyText, true);
                if (n2 == 12) {
                    final int getProcessHeap = OS.GetProcessHeap();
                    final int n5 = tchar2.length() * TCHAR.sizeof;
                    final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n5);
                    OS.MoveMemory(heapAlloc, tchar2, n5);
                    final int callWindowProc = OS.CallWindowProc(Spinner.EditProc, n, n2, n3, heapAlloc);
                    OS.HeapFree(getProcessHeap, 0, heapAlloc);
                    return new LRESULT(callWindowProc);
                }
                OS.SendMessage(n, 194, 0, tchar2);
                return LRESULT.ZERO;
            }
        }
        return null;
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        switch (OS.HIWORD(n)) {
            case 768: {
                if (this.ignoreModify) {
                    break;
                }
                final boolean[] array = { false };
                final int selectionText = this.getSelectionText(array);
                if (!array[0]) {
                    int n3;
                    if (OS.IsWinCE) {
                        n3 = OS.LOWORD(OS.SendMessage(this.hwndUpDown, 1128, 0, 0));
                    }
                    else {
                        n3 = OS.SendMessage(this.hwndUpDown, 1138, 0, 0);
                    }
                    if (n3 != selectionText) {
                        this.setSelection(selectionText, true, false, true);
                    }
                }
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        return super.wmCommandChild(n, n2);
    }
    
    LRESULT wmKeyDown(final int n, final int n2, final int n3) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT wmKeyDown = super.wmKeyDown(n, n2, n3);
        if (wmKeyDown != null) {
            return wmKeyDown;
        }
        final UDACCEL udaccel = new UDACCEL();
        OS.SendMessage(this.hwndUpDown, 1132, 1, udaccel);
        int n4 = 0;
        switch (n2) {
            case 38: {
                n4 = udaccel.nInc;
                break;
            }
            case 40: {
                n4 = -udaccel.nInc;
                break;
            }
            case 33: {
                n4 = this.pageIncrement;
                break;
            }
            case 34: {
                n4 = -this.pageIncrement;
                break;
            }
        }
        if (n4 != 0) {
            final boolean[] array = { false };
            int n5 = this.getSelectionText(array);
            if (array[0]) {
                if (OS.IsWinCE) {
                    n5 = OS.LOWORD(OS.SendMessage(this.hwndUpDown, 1128, 0, 0));
                }
                else {
                    n5 = OS.SendMessage(this.hwndUpDown, 1138, 0, 0);
                }
            }
            int n6 = n5 + n4;
            final int[] array2 = { 0 };
            final int[] array3 = { 0 };
            OS.SendMessage(this.hwndUpDown, 1136, array3, array2);
            if ((this.style & 0x40) != 0x0) {
                if (n6 < array3[0]) {
                    n6 = array2[0];
                }
                if (n6 > array2[0]) {
                    n6 = array3[0];
                }
            }
            final int min = Math.min(Math.max(array3[0], n6), array2[0]);
            if (n5 != min) {
                this.setSelection(min, true, true, true);
            }
        }
        switch (n2) {
            case 38:
            case 40: {
                return LRESULT.ZERO;
            }
            default: {
                return wmKeyDown;
            }
        }
    }
    
    LRESULT wmKillFocus(final int n, final int n2, final int n3) {
        final boolean[] array = { false };
        this.getSelectionText(array);
        if (array[0]) {
            int n4;
            if (OS.IsWinCE) {
                n4 = OS.LOWORD(OS.SendMessage(this.hwndUpDown, 1128, 0, 0));
            }
            else {
                n4 = OS.SendMessage(this.hwndUpDown, 1138, 0, 0);
            }
            this.setSelection(n4, false, true, false);
        }
        return super.wmKillFocus(n, n2, n3);
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        switch (nmhdr.code) {
            case -722: {
                final NMUPDOWN nmupdown = new NMUPDOWN();
                OS.MoveMemory(nmupdown, n2, NMUPDOWN.sizeof);
                int n3 = nmupdown.iPos + nmupdown.iDelta;
                final int[] array = { 0 };
                final int[] array2 = { 0 };
                OS.SendMessage(this.hwndUpDown, 1136, array2, array);
                if ((this.style & 0x40) != 0x0) {
                    if (n3 < array2[0]) {
                        n3 = array[0];
                    }
                    if (n3 > array[0]) {
                        n3 = array2[0];
                    }
                }
                final int min = Math.min(Math.max(array2[0], n3), array[0]);
                if (min != nmupdown.iPos) {
                    this.setSelection(min, true, true, true);
                }
                return LRESULT.ONE;
            }
            default: {
                return super.wmNotifyChild(nmhdr, n, n2);
            }
        }
    }
    
    LRESULT wmScrollChild(final int n, final int n2) {
        switch (OS.LOWORD(n)) {
            case 4: {
                this.sendSelectionEvent(13);
                break;
            }
        }
        return super.wmScrollChild(n, n2);
    }
}
