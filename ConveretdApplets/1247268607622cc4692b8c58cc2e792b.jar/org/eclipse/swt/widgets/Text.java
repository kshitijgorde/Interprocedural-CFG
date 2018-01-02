// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.SHRGINFO;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.GUITHREADINFO;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;

public class Text extends Scrollable
{
    int tabs;
    int oldStart;
    int oldEnd;
    boolean doubleClick;
    boolean ignoreModify;
    boolean ignoreVerify;
    boolean ignoreCharacter;
    String message;
    public static final int LIMIT;
    public static final String DELIMITER;
    static final int EditProc;
    static final TCHAR EditClass;
    
    static {
        LIMIT = (OS.IsWinNT ? Integer.MAX_VALUE : 32767);
        DELIMITER = "\r\n";
        EditClass = new TCHAR(0, "EDIT", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, Text.EditClass, wndclass);
        EditProc = wndclass.lpfnWndProc;
    }
    
    public Text(final Composite composite, final int n) {
        super(composite, checkStyle(n));
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        if (this.handle == 0) {
            return 0;
        }
        boolean b = false;
        switch (n2) {
            case 20: {
                if (this.findImageControl() != null) {
                    return 0;
                }
                break;
            }
            case 276:
            case 277: {
                b = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (b) {
                    OS.DefWindowProc(this.handle, 11, 0, 0);
                    break;
                }
                break;
            }
            case 15: {
                final boolean b2 = this.findImageControl() != null;
                boolean b3 = false;
                if ((this.style & 0x4) != 0x0 && this.message.length() > 0 && !OS.IsWinCE && OS.WIN32_VERSION < OS.VERSION(6, 0)) {
                    b3 = (n != OS.GetFocus() && OS.GetWindowTextLength(this.handle) == 0);
                }
                if (b2 || b3) {
                    final PAINTSTRUCT paintstruct = new PAINTSTRUCT();
                    final int beginPaint = OS.BeginPaint(this.handle, paintstruct);
                    final int n5 = paintstruct.right - paintstruct.left;
                    final int n6 = paintstruct.bottom - paintstruct.top;
                    if (n5 != 0 && n6 != 0) {
                        int createCompatibleDC = beginPaint;
                        int createCompatibleBitmap = 0;
                        int selectObject = 0;
                        POINT point = null;
                        POINT point2 = null;
                        if (b2) {
                            createCompatibleDC = OS.CreateCompatibleDC(beginPaint);
                            point = new POINT();
                            point2 = new POINT();
                            OS.SetWindowOrgEx(createCompatibleDC, paintstruct.left, paintstruct.top, point);
                            OS.SetBrushOrgEx(createCompatibleDC, paintstruct.left, paintstruct.top, point2);
                            createCompatibleBitmap = OS.CreateCompatibleBitmap(beginPaint, n5, n6);
                            selectObject = OS.SelectObject(createCompatibleDC, createCompatibleBitmap);
                            final RECT rect = new RECT();
                            OS.SetRect(rect, paintstruct.left, paintstruct.top, paintstruct.right, paintstruct.bottom);
                            this.drawBackground(createCompatibleDC, rect);
                        }
                        OS.CallWindowProc(Text.EditProc, n, 15, createCompatibleDC, n4);
                        if (b3) {
                            final RECT rect2 = new RECT();
                            OS.GetClientRect(this.handle, rect2);
                            final int sendMessage = OS.SendMessage(this.handle, 212, 0, 0);
                            final RECT rect3 = rect2;
                            rect3.left += OS.LOWORD(sendMessage);
                            final RECT rect4 = rect2;
                            rect4.right -= OS.HIWORD(sendMessage);
                            if ((this.style & 0x800) != 0x0) {
                                final RECT rect5 = rect2;
                                ++rect5.left;
                                final RECT rect6 = rect2;
                                ++rect6.top;
                                final RECT rect7 = rect2;
                                --rect7.right;
                                final RECT rect8 = rect2;
                                --rect8.bottom;
                            }
                            final TCHAR tchar = new TCHAR(this.getCodePage(), this.message, false);
                            int n7 = 8192;
                            final boolean b4 = (this.style & 0x4000000) != 0x0;
                            if (b4) {
                                n7 |= 0x20000;
                            }
                            switch (this.style & 0x1024000) {
                                case 16384: {
                                    n7 |= (b4 ? 2 : 0);
                                    break;
                                }
                                case 16777216: {
                                    n7 |= 0x1;
                                }
                                case 131072: {
                                    n7 |= (b4 ? 0 : 2);
                                    break;
                                }
                            }
                            final int selectObject2 = OS.SelectObject(createCompatibleDC, OS.SendMessage(n, 49, 0, 0));
                            OS.SetTextColor(createCompatibleDC, OS.GetSysColor(OS.COLOR_GRAYTEXT));
                            OS.SetBkMode(createCompatibleDC, 1);
                            OS.DrawText(createCompatibleDC, tchar, tchar.length(), rect2, n7);
                            OS.SelectObject(createCompatibleDC, selectObject2);
                        }
                        if (b2) {
                            OS.SetWindowOrgEx(createCompatibleDC, point.x, point.y, null);
                            OS.SetBrushOrgEx(createCompatibleDC, point2.x, point2.y, null);
                            OS.BitBlt(beginPaint, paintstruct.left, paintstruct.top, n5, n6, createCompatibleDC, 0, 0, 13369376);
                            OS.SelectObject(createCompatibleDC, selectObject);
                            OS.DeleteObject(createCompatibleBitmap);
                            OS.DeleteObject(createCompatibleDC);
                        }
                    }
                    OS.EndPaint(this.handle, paintstruct);
                    return 0;
                }
                break;
            }
        }
        final int callWindowProc = OS.CallWindowProc(Text.EditProc, n, n2, n3, n4);
        switch (n2) {
            case 276:
            case 277: {
                if (b) {
                    OS.DefWindowProc(this.handle, 11, 1, 0);
                    OS.InvalidateRect(this.handle, null, true);
                    break;
                }
                break;
            }
        }
        return callWindowProc;
    }
    
    void createHandle() {
        super.createHandle();
        OS.SendMessage(this.handle, 197, 0, 0);
        if ((this.style & 0x8) != 0x0 && (this.style & 0xB00) == 0x0) {
            this.state |= 0x100;
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
    
    public void append(String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        s = Display.withCrLf(s);
        final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
        if (this.hooks(25) || this.filters(25)) {
            s = this.verifyText(s, getWindowTextLength, getWindowTextLength, null);
            if (s == null) {
                return;
            }
        }
        OS.SendMessage(this.handle, 177, getWindowTextLength, getWindowTextLength);
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        this.ignoreCharacter = true;
        OS.SendMessage(this.handle, 194, 0, tchar);
        this.ignoreCharacter = false;
        OS.SendMessage(this.handle, 183, 0, 0);
    }
    
    static int checkStyle(int checkBits) {
        if ((checkBits & 0x80) != 0x0) {
            checkBits |= 0x804;
            checkBits &= 0xFFBFFFFF;
        }
        if ((checkBits & 0x4) != 0x0 && (checkBits & 0x2) != 0x0) {
            checkBits &= 0xFFFFFFFD;
        }
        checkBits = Widget.checkBits(checkBits, 16384, 16777216, 131072, 0, 0, 0);
        if ((checkBits & 0x4) != 0x0) {
            checkBits &= 0xFFFFFCBF;
        }
        if ((checkBits & 0x40) != 0x0) {
            checkBits |= 0x2;
            checkBits &= 0xFFFFFEFF;
        }
        if ((checkBits & 0x2) != 0x0) {
            checkBits &= 0xFFBFFFFF;
        }
        if ((checkBits & 0x6) != 0x0) {
            return checkBits;
        }
        if ((checkBits & 0x300) != 0x0) {
            return checkBits | 0x2;
        }
        return checkBits | 0x4;
    }
    
    public void clearSelection() {
        this.checkWidget();
        if (OS.IsWinCE) {
            final int[] array = { 0 };
            OS.SendMessage(this.handle, 176, null, array);
            OS.SendMessage(this.handle, 177, array[0], array[0]);
        }
        else {
            OS.SendMessage(this.handle, 177, -1, 0);
        }
    }
    
    public Point computeSize(final int right, final int n, final boolean b) {
        this.checkWidget();
        int n2 = 0;
        int max = 0;
        if (right == -1 || n == -1) {
            int selectObject = 0;
            final int getDC = OS.GetDC(this.handle);
            final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage);
            }
            final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
            OS.GetTextMetrics(getDC, textmetric);
            n2 = (((this.style & 0x4) != 0x0) ? 1 : OS.SendMessage(this.handle, 186, 0, 0)) * textmetric.tmHeight;
            final RECT rect = new RECT();
            int n3 = 11264;
            final boolean b2 = (this.style & 0x2) != 0x0 && (this.style & 0x40) != 0x0;
            if (b2 && right != -1) {
                n3 |= 0x10;
                rect.right = right;
            }
            final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
            if (getWindowTextLength != 0) {
                final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
                OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
                OS.DrawText(getDC, tchar, getWindowTextLength, rect, n3);
                max = rect.right - rect.left;
            }
            if (b2 && n == -1) {
                final int n4 = rect.bottom - rect.top;
                if (n4 != 0) {
                    n2 = n4;
                }
            }
            if ((this.style & 0x4) != 0x0 && this.message.length() > 0) {
                OS.SetRect(rect, 0, 0, 0, 0);
                final TCHAR tchar2 = new TCHAR(this.getCodePage(), this.message, false);
                OS.DrawText(getDC, tchar2, tchar2.length(), rect, n3);
                max = Math.max(max, rect.right - rect.left);
            }
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
        }
        if (max == 0) {
            max = 64;
        }
        if (n2 == 0) {
            n2 = 64;
        }
        if (right != -1) {
            max = right;
        }
        if (n != -1) {
            n2 = n;
        }
        final Rectangle computeTrim = this.computeTrim(0, 0, max, n2);
        return new Point(computeTrim.width, computeTrim.height);
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        final Rectangle computeTrim = super.computeTrim(n, n2, n3, n4);
        final int sendMessage = OS.SendMessage(this.handle, 212, 0, 0);
        final Rectangle rectangle = computeTrim;
        rectangle.x -= OS.LOWORD(sendMessage);
        final Rectangle rectangle2 = computeTrim;
        rectangle2.width += OS.LOWORD(sendMessage) + OS.HIWORD(sendMessage);
        if ((this.style & 0x100) != 0x0) {
            final Rectangle rectangle3 = computeTrim;
            ++rectangle3.width;
        }
        if ((this.style & 0x800) != 0x0) {
            final Rectangle rectangle4 = computeTrim;
            --rectangle4.x;
            final Rectangle rectangle5 = computeTrim;
            --rectangle5.y;
            final Rectangle rectangle6 = computeTrim;
            rectangle6.width += 2;
            final Rectangle rectangle7 = computeTrim;
            rectangle7.height += 2;
        }
        return computeTrim;
    }
    
    public void copy() {
        this.checkWidget();
        OS.SendMessage(this.handle, 769, 0, 0);
    }
    
    void createWidget() {
        super.createWidget();
        this.message = "";
        this.doubleClick = true;
        this.setTabStops(this.tabs = 8);
        this.fixAlignment();
    }
    
    public void cut() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 768, 0, 0);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(((OS.GetWindowLong(this.handle, -16) & 0x800) != 0x0) ? OS.COLOR_3DFACE : OS.COLOR_WINDOW);
    }
    
    boolean dragDetect(final int n, final int n2, final int n3, final boolean b, final boolean[] array, final boolean[] array2) {
        if (b) {
            final int[] array3 = { 0 };
            final int[] array4 = { 0 };
            OS.SendMessage(this.handle, 176, array3, array4);
            if (array3[0] != array4[0]) {
                final int loword = OS.LOWORD(OS.SendMessage(this.handle, 215, 0, OS.MAKELPARAM(n2, n3)));
                if (array3[0] <= loword && loword < array4[0] && super.dragDetect(n, n2, n3, b, array, array2)) {
                    if (array2 != null) {
                        array2[0] = true;
                    }
                    return true;
                }
            }
            return false;
        }
        return super.dragDetect(n, n2, n3, b, array, array2);
    }
    
    void fixAlignment() {
        if ((this.style & 0x8000000) != 0x0) {
            return;
        }
        int getWindowLong = OS.GetWindowLong(this.handle, -20);
        int getWindowLong2 = OS.GetWindowLong(this.handle, -16);
        if ((this.style & 0x2000000) != 0x0) {
            getWindowLong &= 0xFFFFBFFF;
            if ((this.style & 0x20000) != 0x0) {
                getWindowLong |= 0x1000;
                getWindowLong2 |= 0x2;
            }
            if ((this.style & 0x4000) != 0x0) {
                getWindowLong &= 0xFFFFEFFF;
                getWindowLong2 &= 0xFFFFFFFD;
            }
        }
        else {
            if ((this.style & 0x20000) != 0x0) {
                getWindowLong &= 0xFFFFEFFF;
                getWindowLong2 &= 0xFFFFFFFD;
            }
            if ((this.style & 0x4000) != 0x0) {
                getWindowLong |= 0x1000;
                getWindowLong2 |= 0x2;
            }
        }
        if ((this.style & 0x1000000) != 0x0) {
            getWindowLong2 |= 0x1;
        }
        OS.SetWindowLong(this.handle, -20, getWindowLong);
        OS.SetWindowLong(this.handle, -16, getWindowLong2);
    }
    
    public int getBorderWidth() {
        this.checkWidget();
        return super.getBorderWidth();
    }
    
    public int getCaretLineNumber() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 201, -1, 0);
    }
    
    public Point getCaretLocation() {
        this.checkWidget();
        final int caretPosition = this.getCaretPosition();
        int n = OS.SendMessage(this.handle, 214, caretPosition, 0);
        if (n == -1) {
            n = 0;
            if (caretPosition >= OS.GetWindowTextLength(this.handle)) {
                final int codePage = this.getCodePage();
                final int[] array = { 0 };
                final int[] array2 = { 0 };
                OS.SendMessage(this.handle, 176, array, array2);
                OS.SendMessage(this.handle, 177, caretPosition, caretPosition);
                final boolean b = true;
                this.ignoreModify = b;
                this.ignoreCharacter = b;
                OS.SendMessage(this.handle, 194, 0, new TCHAR(codePage, " ", true));
                n = OS.SendMessage(this.handle, 214, caretPosition, 0);
                OS.SendMessage(this.handle, 177, caretPosition, caretPosition + 1);
                OS.SendMessage(this.handle, 194, 0, new TCHAR(codePage, "", true));
                final boolean b2 = false;
                this.ignoreModify = b2;
                this.ignoreCharacter = b2;
                OS.SendMessage(this.handle, 177, array[0], array[0]);
                OS.SendMessage(this.handle, 177, array[0], array2[0]);
            }
        }
        return new Point(OS.GET_X_LPARAM(n), OS.GET_Y_LPARAM(n));
    }
    
    public int getCaretPosition() {
        this.checkWidget();
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.handle, 176, array, array2);
        int mbcsToWcsPos = array[0];
        if (array[0] != array2[0]) {
            final int sendMessage = OS.SendMessage(this.handle, 201, array[0], 0);
            final int sendMessage2 = OS.SendMessage(this.handle, 201, array2[0], 0);
            if (sendMessage == sendMessage2) {
                if (!OS.IsWinCE) {
                    final int getWindowThreadProcessId = OS.GetWindowThreadProcessId(this.handle, null);
                    final GUITHREADINFO guithreadinfo = new GUITHREADINFO();
                    guithreadinfo.cbSize = GUITHREADINFO.sizeof;
                    if (OS.GetGUIThreadInfo(getWindowThreadProcessId, guithreadinfo) && (guithreadinfo.hwndCaret == this.handle || guithreadinfo.hwndCaret == 0)) {
                        final POINT point = new POINT();
                        if (OS.GetCaretPos(point)) {
                            final int sendMessage3 = OS.SendMessage(this.handle, 214, array2[0], 0);
                            if (sendMessage3 == -1) {
                                if (point.x > OS.GET_X_LPARAM(OS.SendMessage(this.handle, 214, array[0], 0))) {
                                    mbcsToWcsPos = array2[0];
                                }
                            }
                            else if (point.x >= OS.GET_X_LPARAM(sendMessage3)) {
                                mbcsToWcsPos = array2[0];
                            }
                        }
                    }
                }
            }
            else if (OS.SendMessage(this.handle, 201, OS.SendMessage(this.handle, 187, -1, 0), 0) == sendMessage2) {
                mbcsToWcsPos = array2[0];
            }
        }
        if (!OS.IsUnicode && OS.IsDBLocale) {
            mbcsToWcsPos = this.mbcsToWcsPos(mbcsToWcsPos);
        }
        return mbcsToWcsPos;
    }
    
    public int getCharCount() {
        this.checkWidget();
        int n = OS.GetWindowTextLength(this.handle);
        if (!OS.IsUnicode && OS.IsDBLocale) {
            n = this.mbcsToWcsPos(n);
        }
        return n;
    }
    
    public boolean getDoubleClickEnabled() {
        this.checkWidget();
        return this.doubleClick;
    }
    
    public char getEchoChar() {
        this.checkWidget();
        char mbcsToWcs = (char)OS.SendMessage(this.handle, 210, 0, 0);
        if (mbcsToWcs != '\0' && (mbcsToWcs = Display.mbcsToWcs(mbcsToWcs, this.getCodePage())) == '\0') {
            mbcsToWcs = '*';
        }
        return mbcsToWcs;
    }
    
    public boolean getEditable() {
        this.checkWidget();
        return (OS.GetWindowLong(this.handle, -16) & 0x800) == 0x0;
    }
    
    public int getLineCount() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 186, 0, 0);
    }
    
    public String getLineDelimiter() {
        this.checkWidget();
        return Text.DELIMITER;
    }
    
    public int getLineHeight() {
        this.checkWidget();
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
        return textmetric.tmHeight;
    }
    
    public int getOrientation() {
        return super.getOrientation();
    }
    
    public String getMessage() {
        this.checkWidget();
        return this.message;
    }
    
    int getPosition(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        int n = OS.LOWORD(OS.SendMessage(this.handle, 215, 0, OS.MAKELPARAM(point.x, point.y)));
        if (!OS.IsUnicode && OS.IsDBLocale) {
            n = this.mbcsToWcsPos(n);
        }
        return n;
    }
    
    public Point getSelection() {
        this.checkWidget();
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.handle, 176, array, array2);
        if (!OS.IsUnicode && OS.IsDBLocale) {
            array[0] = this.mbcsToWcsPos(array[0]);
            array2[0] = this.mbcsToWcsPos(array2[0]);
        }
        return new Point(array[0], array2[0]);
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        final Point selection = this.getSelection();
        return selection.y - selection.x;
    }
    
    public String getSelectionText() {
        this.checkWidget();
        final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
        if (getWindowTextLength == 0) {
            return "";
        }
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.handle, 176, array, array2);
        if (array[0] == array2[0]) {
            return "";
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
        OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
        return tchar.toString(array[0], array2[0] - array[0]);
    }
    
    public int getTabs() {
        this.checkWidget();
        return this.tabs;
    }
    
    int getTabWidth(final int n) {
        int selectObject = 0;
        final RECT rect = new RECT();
        final int getDC = OS.GetDC(this.handle);
        final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
        if (sendMessage != 0) {
            selectObject = OS.SelectObject(getDC, sendMessage);
        }
        final int n2 = 3104;
        final TCHAR tchar = new TCHAR(this.getCodePage(), " ", false);
        OS.DrawText(getDC, tchar, tchar.length(), rect, n2);
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(this.handle, getDC);
        return (rect.right - rect.left) * n;
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
    
    public char[] getTextChars() {
        this.checkWidget();
        final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
        if (getWindowTextLength == 0) {
            return new char[0];
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
        OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
        final char[] array = new char[getWindowTextLength];
        System.arraycopy(tchar.chars, 0, array, 0, getWindowTextLength);
        return array;
    }
    
    public String getText(int max, int min) {
        this.checkWidget();
        if (max > min || min < 0) {
            return "";
        }
        int n = OS.GetWindowTextLength(this.handle);
        if (!OS.IsUnicode && OS.IsDBLocale) {
            n = this.mbcsToWcsPos(n);
        }
        min = Math.min(min, n - 1);
        if (max > min) {
            return "";
        }
        max = Math.max(0, max);
        return this.getText().substring(max, min + 1);
    }
    
    public int getTextLimit() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 213, 0, 0) & Integer.MAX_VALUE;
    }
    
    public int getTopIndex() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return 0;
        }
        return OS.SendMessage(this.handle, 206, 0, 0);
    }
    
    public int getTopPixel() {
        this.checkWidget();
        final int[] array = new int[2];
        if (OS.SendMessage(this.handle, 1245, 0, array) == 1) {
            return array[1];
        }
        return this.getTopIndex() * this.getLineHeight();
    }
    
    public void insert(String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        s = Display.withCrLf(s);
        if (this.hooks(25) || this.filters(25)) {
            final int[] array = { 0 };
            final int[] array2 = { 0 };
            OS.SendMessage(this.handle, 176, array, array2);
            s = this.verifyText(s, array[0], array2[0], null);
            if (s == null) {
                return;
            }
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        this.ignoreCharacter = true;
        OS.SendMessage(this.handle, 194, 0, tchar);
        this.ignoreCharacter = false;
    }
    
    int mbcsToWcsPos(final int n) {
        if (n <= 0) {
            return 0;
        }
        if (OS.IsUnicode) {
            return n;
        }
        final int codePage = this.getCodePage();
        int n2 = 0;
        int n3 = 0;
        byte[] array = new byte[128];
        final String lineDelimiter = this.getLineDelimiter();
        final int length = lineDelimiter.length();
        for (int sendMessageA = OS.SendMessageA(this.handle, 186, 0, 0), i = 0; i < sendMessageA; ++i) {
            int multiByteToWideChar = 0;
            int n4 = OS.SendMessageA(this.handle, 193, OS.SendMessageA(this.handle, 187, i, 0), 0);
            if (n4 != 0) {
                if (n4 + length > array.length) {
                    array = new byte[n4 + length];
                }
                array[0] = (byte)(n4 & 0xFF);
                array[1] = (byte)(n4 >> 8);
                n4 = OS.SendMessageA(this.handle, 196, i, array);
                multiByteToWideChar = OS.MultiByteToWideChar(codePage, 1, array, n4, null, 0);
            }
            if (i - 1 != sendMessageA) {
                for (int j = 0; j < length; ++j) {
                    array[n4++] = (byte)lineDelimiter.charAt(j);
                }
                multiByteToWideChar += length;
            }
            if (n3 + n4 >= n) {
                return n2 + OS.MultiByteToWideChar(codePage, 1, array, n - n3, null, 0);
            }
            n2 += multiByteToWideChar;
            n3 += n4;
        }
        return n2;
    }
    
    public void paste() {
        this.checkWidget();
        if ((this.style & 0x8) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 770, 0, 0);
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.message = null;
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
    
    public void selectAll() {
        this.checkWidget();
        OS.SendMessage(this.handle, 177, 0, -1);
    }
    
    boolean sendKeyEvent(final int n, final int n2, final int n3, final int n4, final Event event) {
        if (!super.sendKeyEvent(n, n2, n3, n4, event)) {
            return false;
        }
        if ((this.style & 0x8) != 0x0) {
            return true;
        }
        if (this.ignoreVerify) {
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
        if (OS.GetKeyState(1) < 0 && this.handle == OS.GetCapture()) {
            return true;
        }
        String delimiter = "";
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.handle, 176, array, array2);
        switch (character) {
            case 8: {
                if (array[0] != array2[0]) {
                    break;
                }
                if (array[0] == 0) {
                    return true;
                }
                if (array[0] == OS.SendMessage(this.handle, 187, -1, 0)) {
                    array[0] -= Text.DELIMITER.length();
                }
                else {
                    --array[0];
                    if (!OS.IsUnicode && OS.IsDBLocale) {
                        final int[] array3 = { 0 };
                        final int[] array4 = { 0 };
                        OS.SendMessage(this.handle, 177, array[0], array2[0]);
                        OS.SendMessage(this.handle, 176, array3, array4);
                        if (array[0] != array3[0]) {
                            --array[0];
                        }
                    }
                }
                array[0] = Math.max(array[0], 0);
                break;
            }
            case 127: {
                if (array[0] != array2[0]) {
                    break;
                }
                final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
                if (array[0] == getWindowTextLength) {
                    return true;
                }
                if (array2[0] == OS.SendMessage(this.handle, 187, OS.SendMessage(this.handle, 201, array2[0], 0) + 1, 0) - Text.DELIMITER.length()) {
                    array2[0] += Text.DELIMITER.length();
                }
                else {
                    ++array2[0];
                    if (!OS.IsUnicode && OS.IsDBLocale) {
                        final int[] array5 = { 0 };
                        final int[] array6 = { 0 };
                        OS.SendMessage(this.handle, 177, array[0], array2[0]);
                        OS.SendMessage(this.handle, 176, array5, array6);
                        if (array2[0] != array6[0]) {
                            ++array2[0];
                        }
                    }
                }
                array2[0] = Math.min(array2[0], getWindowTextLength);
                break;
            }
            case 13: {
                if ((this.style & 0x4) != 0x0) {
                    return true;
                }
                delimiter = Text.DELIMITER;
                break;
            }
            default: {
                if (character != '\t' && character < ' ') {
                    return true;
                }
                delimiter = new String(new char[] { character });
                break;
            }
        }
        final String verifyText = this.verifyText(delimiter, array[0], array2[0], event);
        if (verifyText == null) {
            return false;
        }
        if (verifyText == delimiter) {
            return true;
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), Display.withCrLf(verifyText), true);
        OS.SendMessage(this.handle, 177, array[0], array2[0]);
        this.ignoreCharacter = true;
        OS.SendMessage(this.handle, 194, 0, tchar);
        return this.ignoreCharacter = false;
    }
    
    void setBounds(final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n5 & 0x1) == 0x0 && n3 != 0) {
            final RECT rect = new RECT();
            OS.GetWindowRect(this.handle, rect);
            final int sendMessage = OS.SendMessage(this.handle, 212, 0, 0);
            if (rect.right - rect.left <= OS.LOWORD(sendMessage) + OS.HIWORD(sendMessage)) {
                final int[] array = { 0 };
                final int[] array2 = { 0 };
                OS.SendMessage(this.handle, 176, array, array2);
                if (array[0] != 0 || array2[0] != 0) {
                    this.SetWindowPos(this.handle, 0, n, n2, n3, n4, n5);
                    OS.SendMessage(this.handle, 177, 0, 0);
                    OS.SendMessage(this.handle, 177, array[0], array2[0]);
                    return;
                }
            }
        }
        super.setBounds(n, n2, n3, n4, n5);
        if ((n5 & 0x1) == 0x0 && (OS.GetWindowLong(this.handle, -16) & 0x4) != 0x0) {
            int selectObject = 0;
            final int getDC = OS.GetDC(this.handle);
            final int sendMessage2 = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage2 != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage2);
            }
            final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
            OS.GetTextMetrics(getDC, textmetric);
            if (sendMessage2 != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            final RECT rect2 = new RECT();
            OS.GetClientRect(this.handle, rect2);
            if (rect2.bottom - rect2.top < textmetric.tmHeight) {
                final int sendMessage3 = OS.SendMessage(this.handle, 212, 0, 0);
                final RECT rect3 = rect2;
                rect3.left += OS.LOWORD(sendMessage3);
                final RECT rect4 = rect2;
                rect4.right -= OS.HIWORD(sendMessage3);
                rect2.top = 0;
                rect2.bottom = textmetric.tmHeight;
                OS.SendMessage(this.handle, 179, 0, rect2);
            }
        }
    }
    
    void setDefaultFont() {
        super.setDefaultFont();
        this.setMargins();
    }
    
    public void setDoubleClickEnabled(final boolean doubleClick) {
        this.checkWidget();
        this.doubleClick = doubleClick;
    }
    
    public void setEchoChar(char c) {
        this.checkWidget();
        if ((this.style & 0x2) != 0x0) {
            return;
        }
        if (c != '\0' && (c = (char)Display.wcsToMbcs(c, this.getCodePage())) == '\0') {
            c = '*';
        }
        OS.SendMessage(this.handle, 204, c, 0);
        OS.InvalidateRect(this.handle, null, true);
    }
    
    public void setEditable(final boolean b) {
        this.checkWidget();
        this.style &= 0xFFFFFFF7;
        if (!b) {
            this.style |= 0x8;
        }
        OS.SendMessage(this.handle, 207, b ? 0 : 1, 0);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        super.setFont(font);
        this.setTabStops(this.tabs);
        this.setMargins();
    }
    
    void setMargins() {
        if ((this.style & 0x80) != 0x0 && !OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            OS.SendMessage(this.handle, 211, 3, 0);
        }
    }
    
    public void setMessage(final String message) {
        this.checkWidget();
        if (message == null) {
            this.error(4);
        }
        this.message = message;
        if (!OS.IsWinCE) {
            if (OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                if ((OS.GetWindowLong(this.handle, -16) & 0x4) == 0x0) {
                    final int length = message.length();
                    final char[] array = new char[length + 1];
                    message.getChars(0, length, array, 0);
                    OS.SendMessage(this.handle, 5377, 0, array);
                }
            }
            else {
                OS.InvalidateRect(this.handle, null, true);
            }
        }
    }
    
    public void setOrientation(final int orientation) {
        super.setOrientation(orientation);
    }
    
    public void setSelection(int wcsToMbcsPos) {
        this.checkWidget();
        if (!OS.IsUnicode && OS.IsDBLocale) {
            wcsToMbcsPos = this.wcsToMbcsPos(wcsToMbcsPos);
        }
        OS.SendMessage(this.handle, 177, wcsToMbcsPos, wcsToMbcsPos);
        OS.SendMessage(this.handle, 183, 0, 0);
    }
    
    public void setSelection(int wcsToMbcsPos, int wcsToMbcsPos2) {
        this.checkWidget();
        if (!OS.IsUnicode && OS.IsDBLocale) {
            wcsToMbcsPos = this.wcsToMbcsPos(wcsToMbcsPos);
            wcsToMbcsPos2 = this.wcsToMbcsPos(wcsToMbcsPos2);
        }
        OS.SendMessage(this.handle, 177, wcsToMbcsPos, wcsToMbcsPos2);
        OS.SendMessage(this.handle, 183, 0, 0);
    }
    
    public void setRedraw(final boolean redraw) {
        this.checkWidget();
        super.setRedraw(redraw);
        if (!this.getDrawing()) {
            return;
        }
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.handle, 176, array, array2);
        if (!redraw) {
            this.oldStart = array[0];
            this.oldEnd = array2[0];
        }
        else {
            if (this.oldStart == array[0] && this.oldEnd == array2[0]) {
                return;
            }
            OS.SendMessage(this.handle, 183, 0, 0);
        }
    }
    
    public void setSelection(final Point point) {
        this.checkWidget();
        if (point == null) {
            this.error(4);
        }
        this.setSelection(point.x, point.y);
    }
    
    public void setTabs(final int tabs) {
        this.checkWidget();
        if (tabs < 0) {
            return;
        }
        this.setTabStops(this.tabs = tabs);
    }
    
    void setTabStops(final int n) {
        OS.SendMessage(this.handle, 203, 1, new int[] { this.getTabWidth(n) * 4 / OS.LOWORD(OS.GetDialogBaseUnits()) });
    }
    
    public void setText(String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        s = Display.withCrLf(s);
        if (this.hooks(25) || this.filters(25)) {
            s = this.verifyText(s, 0, OS.GetWindowTextLength(this.handle), null);
            if (s == null) {
                return;
            }
        }
        final int n = OS.SendMessage(this.handle, 213, 0, 0) & Integer.MAX_VALUE;
        if (s.length() > n) {
            s = s.substring(0, n);
        }
        OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), s, true));
        if ((OS.GetWindowLong(this.handle, -16) & 0x4) != 0x0) {
            this.sendEvent(24);
        }
    }
    
    public void setTextChars(char[] withCrLf) {
        this.checkWidget();
        if (withCrLf == null) {
            this.error(4);
        }
        withCrLf = Display.withCrLf(withCrLf);
        if (this.hooks(25) || this.filters(25)) {
            final String verifyText = this.verifyText(new String(withCrLf), 0, OS.GetWindowTextLength(this.handle), null);
            if (verifyText == null) {
                return;
            }
            withCrLf = new char[verifyText.length()];
            verifyText.getChars(0, withCrLf.length, withCrLf, 0);
        }
        final int n = OS.SendMessage(this.handle, 213, 0, 0) & Integer.MAX_VALUE;
        if (withCrLf.length > n) {
            final char[] array = new char[n];
            for (int i = 0; i < n; ++i) {
                array[i] = withCrLf[i];
            }
            withCrLf = array;
        }
        OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), withCrLf, true));
        if ((OS.GetWindowLong(this.handle, -16) & 0x4) != 0x0) {
            this.sendEvent(24);
        }
    }
    
    public void setTextLimit(final int n) {
        this.checkWidget();
        if (n == 0) {
            this.error(7);
        }
        OS.SendMessage(this.handle, 197, n, 0);
    }
    
    public void setTopIndex(int min) {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        min = Math.min(Math.max(min, 0), OS.SendMessage(this.handle, 186, 0, 0) - 1);
        OS.SendMessage(this.handle, 182, 0, min - OS.SendMessage(this.handle, 206, 0, 0));
    }
    
    public void showSelection() {
        this.checkWidget();
        OS.SendMessage(this.handle, 183, 0, 0);
    }
    
    void updateOrientation() {
        final int getWindowLong = OS.GetWindowLong(this.handle, -20);
        int n;
        if ((this.style & 0x4000000) != 0x0) {
            n = (getWindowLong | 0x6000);
        }
        else {
            n = (getWindowLong & 0xFFFF9FFF);
        }
        OS.SetWindowLong(this.handle, -20, n);
        this.fixAlignment();
    }
    
    String verifyText(final String text, final int start, final int end, final Event event) {
        if (this.ignoreVerify) {
            return text;
        }
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
        final int codePage = this.getCodePage();
        int n2 = 0;
        int n3 = 0;
        byte[] array = new byte[128];
        final String lineDelimiter = this.getLineDelimiter();
        final int length = lineDelimiter.length();
        for (int sendMessageA = OS.SendMessageA(this.handle, 186, 0, 0), i = 0; i < sendMessageA; ++i) {
            int multiByteToWideChar = 0;
            int n4 = OS.SendMessageA(this.handle, 193, OS.SendMessageA(this.handle, 187, i, 0), 0);
            if (n4 != 0) {
                if (n4 + length > array.length) {
                    array = new byte[n4 + length];
                }
                array[0] = (byte)(n4 & 0xFF);
                array[1] = (byte)(n4 >> 8);
                n4 = OS.SendMessageA(this.handle, 196, i, array);
                multiByteToWideChar = OS.MultiByteToWideChar(codePage, 1, array, n4, null, 0);
            }
            if (i - 1 != sendMessageA) {
                for (int j = 0; j < length; ++j) {
                    array[n4++] = (byte)lineDelimiter.charAt(j);
                }
                multiByteToWideChar += length;
            }
            if (n2 + multiByteToWideChar >= n) {
                int n5 = 0;
                int k = 0;
                while (k < n4) {
                    if (n2 + n5 == n) {
                        return n3 + k;
                    }
                    if (OS.IsDBCSLeadByte(array[k++])) {
                        ++k;
                    }
                    ++n5;
                }
                return n3 + n4;
            }
            n2 += multiByteToWideChar;
            n3 += n4;
        }
        return n3;
    }
    
    int widgetStyle() {
        int n = super.widgetStyle() | 0x80;
        if ((this.style & 0x400000) != 0x0) {
            n |= 0x20;
        }
        if ((this.style & 0x1000000) != 0x0) {
            n |= 0x1;
        }
        if ((this.style & 0x20000) != 0x0) {
            n |= 0x2;
        }
        if ((this.style & 0x8) != 0x0) {
            n |= 0x800;
        }
        if ((this.style & 0x4) != 0x0) {
            if ((this.style & 0x8) != 0x0 && (this.style & 0xB00) == 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
                n |= 0x4;
            }
            return n;
        }
        int n2 = n | 0x144;
        if ((this.style & 0x40) != 0x0) {
            n2 &= 0xFFEFFF7F;
        }
        return n2;
    }
    
    TCHAR windowClass() {
        return Text.EditClass;
    }
    
    int windowProc() {
        return Text.EditProc;
    }
    
    int windowProc(final int n, final int n2, final int n3, final int n4) {
        if (n2 == 199 && (OS.GetWindowLong(this.handle, -16) & 0x4) == 0x0) {
            final LRESULT wmClipboard = this.wmClipboard(199, n3, n4);
            if (wmClipboard != null) {
                return wmClipboard.value;
            }
            return this.callWindowProc(n, 199, n3, n4);
        }
        else {
            if (n2 == Display.SWT_RESTORECARET) {
                this.callWindowProc(n, 8, 0, 0);
                this.callWindowProc(n, 7, 0, 0);
                return 1;
            }
            return super.windowProc(n, n2, n3, n4);
        }
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        if (this.ignoreCharacter) {
            return null;
        }
        final LRESULT wm_CHAR = super.WM_CHAR(n, n2);
        if (wm_CHAR != null) {
            return wm_CHAR;
        }
        switch (n) {
            case 127: {
                if (OS.GetKeyState(17) < 0) {
                    return LRESULT.ZERO;
                }
                break;
            }
        }
        if ((this.style & 0x4) != 0x0) {
            switch (n) {
                case 13: {
                    this.sendSelectionEvent(14);
                }
                case 9:
                case 27: {
                    return LRESULT.ZERO;
                }
            }
        }
        return wm_CHAR;
    }
    
    LRESULT WM_CLEAR(final int n, final int n2) {
        final LRESULT wm_CLEAR = super.WM_CLEAR(n, n2);
        if (wm_CLEAR != null) {
            return wm_CLEAR;
        }
        return this.wmClipboard(771, n, n2);
    }
    
    LRESULT WM_CUT(final int n, final int n2) {
        final LRESULT wm_CUT = super.WM_CUT(n, n2);
        if (wm_CUT != null) {
            return wm_CUT;
        }
        return this.wmClipboard(768, n, n2);
    }
    
    LRESULT WM_ERASEBKGND(final int n, final int n2) {
        final LRESULT wm_ERASEBKGND = super.WM_ERASEBKGND(n, n2);
        if ((this.style & 0x8) != 0x0 && (this.style & 0xB00) == 0x0 && (OS.GetWindowLong(this.handle, -16) & 0x4) != 0x0 && this.findBackgroundControl() == null && this.background == -1 && (this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed()) {
            final Control themeControl = this.findThemeControl();
            if (themeControl != null) {
                final RECT rect = new RECT();
                OS.GetClientRect(this.handle, rect);
                this.fillThemeBackground(n, themeControl, rect);
                return LRESULT.ONE;
            }
        }
        return wm_ERASEBKGND;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        final LRESULT wm_GETDLGCODE = super.WM_GETDLGCODE(n, n2);
        if (wm_GETDLGCODE != null) {
            return wm_GETDLGCODE;
        }
        if (OS.IsPPC && (this.style & 0x2) != 0x0 && (this.style & 0x8) == 0x0 && n2 == 0) {
            return new LRESULT(140);
        }
        if ((this.style & 0x8) != 0x0) {
            return new LRESULT(this.callWindowProc(this.handle, 135, n, n2) & 0xFFFFFFF9);
        }
        return null;
    }
    
    LRESULT WM_IME_CHAR(final int lastAscii, final int n) {
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
        if (!this.sendKeyEvent(1, 646, lastAscii, n)) {
            return LRESULT.ZERO;
        }
        this.ignoreCharacter = true;
        final int callWindowProc = this.callWindowProc(this.handle, 646, lastAscii, n);
        final MSG msg = new MSG();
        while (OS.PeekMessage(msg, this.handle, 258, 258, 10420227)) {
            OS.TranslateMessage(msg);
            OS.DispatchMessage(msg);
        }
        this.ignoreCharacter = false;
        this.sendKeyEvent(2, 646, lastAscii, n);
        final Display display5 = display;
        final Display display6 = display;
        final boolean b = false;
        display6.lastAscii = (b ? 1 : 0);
        display5.lastKey = (b ? 1 : 0);
        return new LRESULT(callWindowProc);
    }
    
    LRESULT WM_LBUTTONDBLCLK(final int n, final int n2) {
        LRESULT zero = null;
        this.sendMouseEvent(3, 1, this.handle, 513, n, n2);
        if (!this.sendMouseEvent(8, 1, this.handle, 515, n, n2)) {
            zero = LRESULT.ZERO;
        }
        if (!this.display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
            OS.SetCapture(this.handle);
        }
        if (!this.doubleClick) {
            return LRESULT.ZERO;
        }
        final int[] array = { 0 };
        final int[] array2 = { 0 };
        OS.SendMessage(this.handle, 176, array, array2);
        if (array[0] == array2[0]) {
            final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
            if (getWindowTextLength == array[0] && OS.SendMessage(this.handle, 193, getWindowTextLength, 0) == 0) {
                return LRESULT.ZERO;
            }
        }
        return zero;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        if (OS.IsPPC) {
            final Display display = this.display;
            display.captureChanged = false;
            final boolean sendMouseEvent = this.sendMouseEvent(3, 1, this.handle, 513, n, n2);
            if ((this.menu != null && !this.menu.isDisposed()) || this.hooks(35)) {
                final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
                final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
                final SHRGINFO shrginfo = new SHRGINFO();
                shrginfo.cbSize = SHRGINFO.sizeof;
                shrginfo.hwndClient = this.handle;
                shrginfo.ptDown_x = get_X_LPARAM;
                shrginfo.ptDown_y = get_Y_LPARAM;
                shrginfo.dwFlags = 1;
                if (OS.SHRecognizeGesture(shrginfo) == 1000) {
                    this.showMenu(get_X_LPARAM, get_Y_LPARAM);
                    return LRESULT.ONE;
                }
            }
            LRESULT zero;
            if (sendMouseEvent) {
                zero = new LRESULT(this.callWindowProc(this.handle, 513, n, n2));
            }
            else {
                zero = LRESULT.ZERO;
            }
            if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != this.handle) {
                OS.SetCapture(this.handle);
            }
            return zero;
        }
        return super.WM_LBUTTONDOWN(n, n2);
    }
    
    LRESULT WM_PASTE(final int n, final int n2) {
        final LRESULT wm_PASTE = super.WM_PASTE(n, n2);
        if (wm_PASTE != null) {
            return wm_PASTE;
        }
        return this.wmClipboard(770, n, n2);
    }
    
    LRESULT WM_UNDO(final int n, final int n2) {
        final LRESULT wm_UNDO = super.WM_UNDO(n, n2);
        if (wm_UNDO != null) {
            return wm_UNDO;
        }
        return this.wmClipboard(772, n, n2);
    }
    
    LRESULT wmClipboard(final int n, final int n2, final int n3) {
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
        switch (n) {
            case 768:
            case 771: {
                OS.SendMessage(this.handle, 176, array, array2);
                if (array[0] != array2[0]) {
                    s = "";
                    b = true;
                    break;
                }
                break;
            }
            case 770: {
                OS.SendMessage(this.handle, 176, array, array2);
                s = this.getClipboardText();
                break;
            }
            case 199:
            case 772: {
                if (OS.SendMessage(this.handle, 198, 0, 0) != 0) {
                    final boolean b2 = true;
                    this.ignoreCharacter = b2;
                    this.ignoreModify = b2;
                    this.callWindowProc(this.handle, n, n2, n3);
                    final int getWindowTextLength = OS.GetWindowTextLength(this.handle);
                    final int[] array3 = { 0 };
                    final int[] array4 = { 0 };
                    OS.SendMessage(this.handle, 176, array3, array4);
                    if (getWindowTextLength != 0 && array3[0] != array4[0]) {
                        final TCHAR tchar = new TCHAR(this.getCodePage(), getWindowTextLength + 1);
                        OS.GetWindowText(this.handle, tchar, getWindowTextLength + 1);
                        s = tchar.toString(array3[0], array4[0] - array3[0]);
                    }
                    else {
                        s = "";
                    }
                    this.callWindowProc(this.handle, n, n2, n3);
                    OS.SendMessage(this.handle, 176, array, array2);
                    final boolean b3 = false;
                    this.ignoreCharacter = b3;
                    this.ignoreModify = b3;
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
                    this.callWindowProc(this.handle, n, n2, n3);
                }
                final TCHAR tchar2 = new TCHAR(this.getCodePage(), Display.withCrLf(verifyText), true);
                this.ignoreCharacter = true;
                OS.SendMessage(this.handle, 194, 0, tchar2);
                this.ignoreCharacter = false;
                return LRESULT.ZERO;
            }
        }
        if (n == 772) {
            final boolean b4 = true;
            this.ignoreCharacter = b4;
            this.ignoreVerify = b4;
            this.callWindowProc(this.handle, 772, n2, n3);
            final boolean b5 = false;
            this.ignoreCharacter = b5;
            this.ignoreVerify = b5;
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmColorChild(final int n, final int n2) {
        if ((this.style & 0x8) != 0x0 && (this.style & 0xB00) == 0x0 && (OS.GetWindowLong(this.handle, -16) & 0x4) != 0x0 && this.findBackgroundControl() == null && this.background == -1 && (this.state & 0x100) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && this.findThemeControl() != null) {
            OS.SetTextColor(n, this.getForegroundPixel());
            OS.SetBkColor(n, this.getBackgroundPixel());
            OS.SetBkMode(n, 1);
            return new LRESULT(OS.GetStockObject(5));
        }
        return super.wmColorChild(n, n2);
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        final int hiword = OS.HIWORD(n);
        switch (hiword) {
            case 768: {
                if (this.findImageControl() != null) {
                    OS.InvalidateRect(this.handle, null, true);
                }
                if (this.ignoreModify) {
                    break;
                }
                this.sendEvent(24);
                if (this.isDisposed()) {
                    return LRESULT.ZERO;
                }
                break;
            }
            case 1792:
            case 1793: {
                final int getWindowLong = OS.GetWindowLong(this.handle, -20);
                if ((getWindowLong & 0x2000) != 0x0) {
                    this.style &= 0xFDFFFFFF;
                    this.style |= 0x4000000;
                }
                else {
                    this.style &= 0xFBFFFFFF;
                    this.style |= 0x2000000;
                }
                final Event event = new Event();
                event.doit = true;
                this.sendEvent(44, event);
                if (!event.doit) {
                    int n3;
                    if (hiword == 1792) {
                        n3 = (getWindowLong | 0x6000);
                        this.style &= 0xFDFFFFFF;
                        this.style |= 0x4000000;
                    }
                    else {
                        n3 = (getWindowLong & 0xFFFF9FFF);
                        this.style &= 0xFBFFFFFF;
                        this.style |= 0x2000000;
                    }
                    OS.SetWindowLong(this.handle, -20, n3);
                }
                this.fixAlignment();
                break;
            }
        }
        return super.wmCommandChild(n, n2);
    }
}
