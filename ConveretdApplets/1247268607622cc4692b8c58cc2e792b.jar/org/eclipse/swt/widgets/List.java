// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.SCROLLINFO;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.TCHAR;

public class List extends Scrollable
{
    static final int INSET = 3;
    static final int ListProc;
    static final TCHAR ListClass;
    
    static {
        ListClass = new TCHAR(0, "LISTBOX", true);
        final WNDCLASS wndclass = new WNDCLASS();
        OS.GetClassInfo(0, List.ListClass, wndclass);
        ListProc = wndclass.lpfnWndProc;
    }
    
    public List(final Composite composite, final int n) {
        super(composite, checkStyle(n));
    }
    
    public void add(final String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        final int sendMessage = OS.SendMessage(this.handle, 384, 0, tchar);
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
        if (n == -1) {
            this.error(6);
        }
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        final int sendMessage = OS.SendMessage(this.handle, 385, n, tchar);
        if (sendMessage == -2) {
            this.error(14);
        }
        if (sendMessage == -1) {
            final int sendMessage2 = OS.SendMessage(this.handle, 395, 0, 0);
            if (n >= 0 && n <= sendMessage2) {
                this.error(14);
            }
            else {
                this.error(6);
            }
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(tchar, true);
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
        boolean b = false;
        switch (n2) {
            case 276:
            case 277: {
                b = (this.findImageControl() != null && this.getDrawing() && OS.IsWindowVisible(this.handle));
                if (b) {
                    OS.DefWindowProc(this.handle, 11, 0, 0);
                    break;
                }
                break;
            }
        }
        final int callWindowProc = OS.CallWindowProc(List.ListProc, n, n2, n3, n4);
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
    
    static int checkStyle(final int n) {
        return Widget.checkBits(n, 4, 2, 0, 0, 0, 0);
    }
    
    public Point computeSize(final int n, final int n2, final boolean b) {
        this.checkWidget();
        int n3 = 0;
        int n4 = 0;
        if (n == -1) {
            if ((this.style & 0x100) != 0x0) {
                n3 = OS.SendMessage(this.handle, 403, 0, 0);
                n3 -= 3;
            }
            else {
                final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
                int selectObject = 0;
                final int getDC = OS.GetDC(this.handle);
                final int sendMessage2 = OS.SendMessage(this.handle, 49, 0, 0);
                if (sendMessage2 != 0) {
                    selectObject = OS.SelectObject(getDC, sendMessage2);
                }
                final RECT rect = new RECT();
                final int n5 = 3104;
                final int codePage = this.getCodePage();
                TCHAR tchar = new TCHAR(codePage, 65);
                for (int i = 0; i < sendMessage; ++i) {
                    final int sendMessage3 = OS.SendMessage(this.handle, 394, i, 0);
                    if (sendMessage3 != -1) {
                        if (sendMessage3 + 1 > tchar.length()) {
                            tchar = new TCHAR(codePage, sendMessage3 + 1);
                        }
                        if (OS.SendMessage(this.handle, 393, i, tchar) != -1) {
                            OS.DrawText(getDC, tchar, sendMessage3, rect, n5);
                            n3 = Math.max(n3, rect.right - rect.left);
                        }
                    }
                }
                if (sendMessage2 != 0) {
                    OS.SelectObject(getDC, selectObject);
                }
                OS.ReleaseDC(this.handle, getDC);
            }
        }
        if (n2 == -1) {
            n4 = OS.SendMessage(this.handle, 395, 0, 0) * OS.SendMessage(this.handle, 417, 0, 0);
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
        int n6 = n3 + (borderWidth * 2 + 3);
        int n7 = n4 + borderWidth * 2;
        if ((this.style & 0x200) != 0x0) {
            n6 += OS.GetSystemMetrics(2);
        }
        if ((this.style & 0x100) != 0x0) {
            n7 += OS.GetSystemMetrics(3);
        }
        return new Point(n6, n7);
    }
    
    int defaultBackground() {
        return OS.GetSysColor(OS.COLOR_WINDOW);
    }
    
    public void deselect(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        if (array.length == 0) {
            return;
        }
        if ((this.style & 0x4) == 0x0) {
            for (int i = 0; i < array.length; ++i) {
                final int n = array[i];
                if (n != -1) {
                    OS.SendMessage(this.handle, 389, 0, n);
                }
            }
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 392, 0, 0);
        if (sendMessage == -1) {
            return;
        }
        for (int j = 0; j < array.length; ++j) {
            if (sendMessage == array[j]) {
                OS.SendMessage(this.handle, 390, -1, 0);
                return;
            }
        }
    }
    
    public void deselect(final int n) {
        this.checkWidget();
        if (n == -1) {
            return;
        }
        if ((this.style & 0x4) == 0x0) {
            OS.SendMessage(this.handle, 389, 0, n);
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 392, 0, 0);
        if (sendMessage == -1) {
            return;
        }
        if (sendMessage == n) {
            OS.SendMessage(this.handle, 390, -1, 0);
        }
    }
    
    public void deselect(int min, int min2) {
        this.checkWidget();
        if (min > min2) {
            return;
        }
        if ((this.style & 0x4) != 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 392, 0, 0);
            if (sendMessage == -1) {
                return;
            }
            if (min <= sendMessage && sendMessage <= min2) {
                OS.SendMessage(this.handle, 390, -1, 0);
            }
        }
        else {
            final int sendMessage2 = OS.SendMessage(this.handle, 395, 0, 0);
            if (min < 0 && min2 < 0) {
                return;
            }
            if (min >= sendMessage2 && min2 >= sendMessage2) {
                return;
            }
            min = Math.min(sendMessage2 - 1, Math.max(0, min));
            min2 = Math.min(sendMessage2 - 1, Math.max(0, min2));
            OS.SendMessage(this.handle, 387, min2, min);
        }
    }
    
    public void deselectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            OS.SendMessage(this.handle, 390, -1, 0);
        }
        else {
            OS.SendMessage(this.handle, 389, 0, -1);
        }
    }
    
    public int getFocusIndex() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 415, 0, 0);
        if (sendMessage == 0 && OS.SendMessage(this.handle, 395, 0, 0) == 0) {
            return -1;
        }
        return sendMessage;
    }
    
    public String getItem(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 394, n, 0);
        if (sendMessage != -1) {
            final TCHAR tchar = new TCHAR(this.getCodePage(), sendMessage + 1);
            if (OS.SendMessage(this.handle, 393, n, tchar) != -1) {
                return tchar.toString(0, sendMessage);
            }
        }
        final int sendMessage2 = OS.SendMessage(this.handle, 395, 0, 0);
        if (n >= 0 && n < sendMessage2) {
            this.error(8);
        }
        this.error(6);
        return "";
    }
    
    public int getItemCount() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
        if (sendMessage == -1) {
            this.error(36);
        }
        return sendMessage;
    }
    
    public int getItemHeight() {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 417, 0, 0);
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
    
    public String[] getSelection() {
        this.checkWidget();
        final int[] selectionIndices = this.getSelectionIndices();
        final String[] array = new String[selectionIndices.length];
        for (int i = 0; i < selectionIndices.length; ++i) {
            array[i] = this.getItem(selectionIndices[i]);
        }
        return array;
    }
    
    public int getSelectionCount() {
        this.checkWidget();
        if ((this.style & 0x4) == 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 400, 0, 0);
            if (sendMessage == -1) {
                this.error(36);
            }
            return sendMessage;
        }
        if (OS.SendMessage(this.handle, 392, 0, 0) == -1) {
            return 0;
        }
        return 1;
    }
    
    public int getSelectionIndex() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return OS.SendMessage(this.handle, 392, 0, 0);
        }
        final int sendMessage = OS.SendMessage(this.handle, 400, 0, 0);
        if (sendMessage == -1) {
            this.error(9);
        }
        if (sendMessage == 0) {
            return -1;
        }
        final int sendMessage2 = OS.SendMessage(this.handle, 415, 0, 0);
        final int sendMessage3 = OS.SendMessage(this.handle, 391, sendMessage2, 0);
        if (sendMessage3 == -1) {
            this.error(9);
        }
        if (sendMessage3 != 0) {
            return sendMessage2;
        }
        final int[] array = { 0 };
        if (OS.SendMessage(this.handle, 401, 1, array) != 1) {
            this.error(9);
        }
        return array[0];
    }
    
    public int[] getSelectionIndices() {
        this.checkWidget();
        if ((this.style & 0x4) == 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 400, 0, 0);
            if (sendMessage == -1) {
                this.error(9);
            }
            final int[] array = new int[sendMessage];
            if (OS.SendMessage(this.handle, 401, sendMessage, array) != sendMessage) {
                this.error(9);
            }
            return array;
        }
        final int sendMessage2 = OS.SendMessage(this.handle, 392, 0, 0);
        if (sendMessage2 == -1) {
            return new int[0];
        }
        return new int[] { sendMessage2 };
    }
    
    public int getTopIndex() {
        this.checkWidget();
        return OS.SendMessage(this.handle, 398, 0, 0);
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
        final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
        if (n < 0 || n >= sendMessage) {
            return -1;
        }
        int sendMessage2 = n - 1;
        final TCHAR tchar = new TCHAR(this.getCodePage(), s, true);
        do {
            final int n2;
            sendMessage2 = OS.SendMessage(this.handle, 418, n2 = sendMessage2, tchar);
            if (sendMessage2 == -1 || sendMessage2 <= n2) {
                return -1;
            }
        } while (!s.equals(this.getItem(sendMessage2)));
        return sendMessage2;
    }
    
    public boolean isSelected(final int n) {
        this.checkWidget();
        final int sendMessage = OS.SendMessage(this.handle, 391, n, 0);
        return sendMessage != 0 && sendMessage != -1;
    }
    
    public void remove(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        if (array.length == 0) {
            return;
        }
        final int[] array2 = new int[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        this.sort(array2);
        final int n = array2[array2.length - 1];
        final int n2 = array2[0];
        final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
        if (n < 0 || n > n2 || n2 >= sendMessage) {
            this.error(6);
        }
        int sendMessage2 = OS.SendMessage(this.handle, 398, 0, 0);
        RECT rect = null;
        int getDC = 0;
        int selectObject = 0;
        int sendMessage3 = 0;
        int max = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            getDC = OS.GetDC(this.handle);
            sendMessage3 = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage3 != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage3);
            }
        }
        final int codePage = this.getCodePage();
        int i = 0;
        int n3 = 0;
        int n4 = -1;
        while (i < array2.length) {
            final int n5 = array2[i];
            if (n5 != n4) {
                TCHAR tchar = null;
                if ((this.style & 0x100) != 0x0) {
                    final int sendMessage4 = OS.SendMessage(this.handle, 394, n5, 0);
                    if (sendMessage4 == -1) {
                        break;
                    }
                    tchar = new TCHAR(codePage, sendMessage4 + 1);
                    if (OS.SendMessage(this.handle, 393, n5, tchar) == -1) {
                        break;
                    }
                }
                if (OS.SendMessage(this.handle, 386, n5, 0) == -1) {
                    break;
                }
                if ((this.style & 0x100) != 0x0) {
                    OS.DrawText(getDC, tchar, -1, rect, 3104);
                    max = Math.max(max, rect.right - rect.left);
                }
                if (n5 < sendMessage2) {
                    ++n3;
                }
                n4 = n5;
            }
            ++i;
        }
        if ((this.style & 0x100) != 0x0) {
            if (sendMessage3 != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            this.setScrollWidth(max, false);
        }
        if (n3 > 0) {
            sendMessage2 -= n3;
        }
        OS.SendMessage(this.handle, 407, sendMessage2, 0);
        if (i < array2.length) {
            this.error(15);
        }
    }
    
    public void remove(final int n) {
        this.checkWidget();
        TCHAR tchar = null;
        if ((this.style & 0x100) != 0x0) {
            final int sendMessage = OS.SendMessage(this.handle, 394, n, 0);
            if (sendMessage == -1) {
                final int sendMessage2 = OS.SendMessage(this.handle, 395, 0, 0);
                if (n >= 0 && n < sendMessage2) {
                    this.error(15);
                }
                this.error(6);
            }
            tchar = new TCHAR(this.getCodePage(), sendMessage + 1);
            if (OS.SendMessage(this.handle, 393, n, tchar) == -1) {
                final int sendMessage3 = OS.SendMessage(this.handle, 395, 0, 0);
                if (n >= 0 && n < sendMessage3) {
                    this.error(15);
                }
                this.error(6);
            }
        }
        int sendMessage4 = OS.SendMessage(this.handle, 398, 0, 0);
        if (OS.SendMessage(this.handle, 386, n, 0) == -1) {
            final int sendMessage5 = OS.SendMessage(this.handle, 395, 0, 0);
            if (n >= 0 && n < sendMessage5) {
                this.error(15);
            }
            this.error(6);
        }
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth(tchar, false);
        }
        if (n < sendMessage4) {
            --sendMessage4;
        }
        OS.SendMessage(this.handle, 407, sendMessage4, 0);
    }
    
    public void remove(final int n, final int n2) {
        this.checkWidget();
        if (n > n2) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
        if (n < 0 || n > n2 || n2 >= sendMessage) {
            this.error(6);
        }
        if (n == 0 && n2 == sendMessage - 1) {
            this.removeAll();
            return;
        }
        int sendMessage2 = OS.SendMessage(this.handle, 398, 0, 0);
        RECT rect = null;
        int getDC = 0;
        int selectObject = 0;
        int sendMessage3 = 0;
        int max = 0;
        if ((this.style & 0x100) != 0x0) {
            rect = new RECT();
            getDC = OS.GetDC(this.handle);
            sendMessage3 = OS.SendMessage(this.handle, 49, 0, 0);
            if (sendMessage3 != 0) {
                selectObject = OS.SelectObject(getDC, sendMessage3);
            }
        }
        final int codePage = this.getCodePage();
        int i = n;
        final int n3 = 3104;
        while (i <= n2) {
            TCHAR tchar = null;
            if ((this.style & 0x100) != 0x0) {
                final int sendMessage4 = OS.SendMessage(this.handle, 394, n, 0);
                if (sendMessage4 == -1) {
                    break;
                }
                tchar = new TCHAR(codePage, sendMessage4 + 1);
                if (OS.SendMessage(this.handle, 393, n, tchar) == -1) {
                    break;
                }
            }
            if (OS.SendMessage(this.handle, 386, n, 0) == -1) {
                break;
            }
            if ((this.style & 0x100) != 0x0) {
                OS.DrawText(getDC, tchar, -1, rect, n3);
                max = Math.max(max, rect.right - rect.left);
            }
            ++i;
        }
        if ((this.style & 0x100) != 0x0) {
            if (sendMessage3 != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            this.setScrollWidth(max, false);
        }
        if (n2 < sendMessage2) {
            sendMessage2 -= n2 - n + 1;
        }
        OS.SendMessage(this.handle, 407, sendMessage2, 0);
        if (i <= n2) {
            this.error(15);
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
        OS.SendMessage(this.handle, 388, 0, 0);
        if ((this.style & 0x100) != 0x0) {
            OS.SendMessage(this.handle, 404, 0, 0);
        }
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
    
    public void select(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        final int length = array.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        this.select(array, false);
    }
    
    void select(final int[] array, final boolean b) {
        for (int i = 0; i < array.length; ++i) {
            final int n = array[i];
            if (n != -1) {
                this.select(n, false);
            }
        }
        if (b) {
            this.showSelection();
        }
    }
    
    public void select(final int n) {
        this.checkWidget();
        this.select(n, false);
    }
    
    void select(final int n, final boolean b) {
        if (n < 0) {
            return;
        }
        if (n >= OS.SendMessage(this.handle, 395, 0, 0)) {
            return;
        }
        if (b) {
            if ((this.style & 0x4) != 0x0) {
                OS.SendMessage(this.handle, 390, n, 0);
            }
            else {
                OS.SendMessage(this.handle, 389, 1, n);
            }
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 398, 0, 0);
        final RECT rect = new RECT();
        RECT rect2 = null;
        OS.SendMessage(this.handle, 408, n, rect);
        final boolean b2 = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (b2) {
            OS.UpdateWindow(this.handle);
            OS.SendMessage(this.handle, 11, 0, 0);
        }
        int sendMessage2 = -1;
        if ((this.style & 0x4) != 0x0) {
            final int sendMessage3 = OS.SendMessage(this.handle, 392, 0, 0);
            if (sendMessage3 != -1) {
                rect2 = new RECT();
                OS.SendMessage(this.handle, 408, sendMessage3, rect2);
            }
            OS.SendMessage(this.handle, 390, n, 0);
        }
        else {
            sendMessage2 = OS.SendMessage(this.handle, 415, 0, 0);
            OS.SendMessage(this.handle, 389, 1, n);
        }
        if ((this.style & 0x2) != 0x0 && sendMessage2 != -1) {
            OS.SendMessage(this.handle, 414, sendMessage2, 0);
        }
        OS.SendMessage(this.handle, 407, sendMessage, 0);
        if (b2) {
            OS.SendMessage(this.handle, 11, 1, 0);
            OS.ValidateRect(this.handle, null);
            OS.InvalidateRect(this.handle, rect, true);
            if (rect2 != null) {
                OS.InvalidateRect(this.handle, rect2, true);
            }
        }
    }
    
    public void select(int max, int min) {
        this.checkWidget();
        if (min < 0 || max > min || ((this.style & 0x4) != 0x0 && max != min)) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
        if (sendMessage == 0 || max >= sendMessage) {
            return;
        }
        max = Math.max(0, max);
        min = Math.min(min, sendMessage - 1);
        if ((this.style & 0x4) != 0x0) {
            this.select(max, false);
        }
        else {
            this.select(max, min, false);
        }
    }
    
    void select(final int n, final int n2, final boolean b) {
        if (n == n2) {
            this.select(n, b);
            return;
        }
        OS.SendMessage(this.handle, 387, n, n2);
        if (b) {
            this.showSelection();
        }
    }
    
    public void selectAll() {
        this.checkWidget();
        if ((this.style & 0x4) != 0x0) {
            return;
        }
        OS.SendMessage(this.handle, 389, 1, -1);
    }
    
    void setFocusIndex(final int n) {
        final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
        if (n < 0 || n >= sendMessage) {
            return;
        }
        OS.SendMessage(this.handle, 414, n, 0);
    }
    
    public void setFont(final Font font) {
        this.checkWidget();
        super.setFont(font);
        if ((this.style & 0x100) != 0x0) {
            this.setScrollWidth();
        }
    }
    
    public void setItem(final int n, final String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        final int topIndex = this.getTopIndex();
        final boolean selected = this.isSelected(n);
        this.remove(n);
        this.add(s, n);
        if (selected) {
            this.select(n, false);
        }
        this.setTopIndex(topIndex);
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
        final int getWindowLongPtr = OS.GetWindowLongPtr(this.handle, -4);
        OS.SetWindowLongPtr(this.handle, -4, List.ListProc);
        final boolean b = this.getDrawing() && OS.IsWindowVisible(this.handle);
        if (b) {
            OS.SendMessage(this.handle, 11, 0, 0);
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
            OS.SendMessage(this.handle, 404, 0, 0);
        }
        final int length = array.length;
        OS.SendMessage(this.handle, 388, 0, 0);
        OS.SendMessage(this.handle, 424, length, length * 32);
        int j = 0;
        final int codePage = this.getCodePage();
        while (j < length) {
            final TCHAR tchar = new TCHAR(codePage, array[j], true);
            final int sendMessage2 = OS.SendMessage(this.handle, 384, 0, tchar);
            if (sendMessage2 == -1) {
                break;
            }
            if (sendMessage2 == -2) {
                break;
            }
            if ((this.style & 0x100) != 0x0) {
                OS.DrawText(getDC, tchar, -1, rect, 3104);
                max = Math.max(max, rect.right - rect.left);
            }
            ++j;
        }
        if ((this.style & 0x100) != 0x0) {
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
            OS.SendMessage(this.handle, 404, max + 3, 0);
        }
        if (b) {
            OS.SendMessage(this.handle, 11, 1, 0);
        }
        OS.SetWindowLongPtr(this.handle, -4, getWindowLongPtr);
        if (j < array.length) {
            this.error(14);
        }
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
        final int sendMessage2 = OS.SendMessage(this.handle, 395, 0, 0);
        final int n = 3104;
        for (int i = 0; i < sendMessage2; ++i) {
            final int sendMessage3 = OS.SendMessage(this.handle, 394, i, 0);
            if (sendMessage3 != -1) {
                final TCHAR tchar = new TCHAR(codePage, sendMessage3 + 1);
                if (OS.SendMessage(this.handle, 393, i, tchar) != -1) {
                    OS.DrawText(getDC, tchar, -1, rect, n);
                    max = Math.max(max, rect.right - rect.left);
                }
            }
        }
        if (sendMessage != 0) {
            OS.SelectObject(getDC, selectObject);
        }
        OS.ReleaseDC(this.handle, getDC);
        OS.SendMessage(this.handle, 404, max + 3, 0);
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
    
    void setScrollWidth(int n, final boolean b) {
        n += 3;
        final int sendMessage = OS.SendMessage(this.handle, 403, 0, 0);
        if (b) {
            if (n <= sendMessage) {
                return;
            }
            OS.SendMessage(this.handle, 404, n, 0);
        }
        else {
            if (n < sendMessage) {
                return;
            }
            this.setScrollWidth();
        }
    }
    
    public void setSelection(final int[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = array.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        this.select(array, true);
        if ((this.style & 0x2) != 0x0) {
            final int focusIndex = array[0];
            if (focusIndex >= 0) {
                this.setFocusIndex(focusIndex);
            }
        }
    }
    
    public void setSelection(final String[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        this.deselectAll();
        final int length = array.length;
        if (length == 0 || ((this.style & 0x4) != 0x0 && length > 1)) {
            return;
        }
        int focusIndex = -1;
        for (int i = length - 1; i >= 0; --i) {
            final String s = array[i];
            int index = 0;
            if (s != null) {
                int n = -1;
                while ((index = this.indexOf(s, index)) != -1) {
                    if (n == -1) {
                        n = index;
                    }
                    this.select(index, false);
                    if ((this.style & 0x4) != 0x0 && this.isSelected(index)) {
                        this.showSelection();
                        return;
                    }
                    ++index;
                }
                if (n != -1) {
                    focusIndex = n;
                }
            }
        }
        if ((this.style & 0x2) != 0x0 && focusIndex >= 0) {
            this.setFocusIndex(focusIndex);
        }
    }
    
    public void setSelection(final int focusIndex) {
        this.checkWidget();
        this.deselectAll();
        this.select(focusIndex, true);
        if ((this.style & 0x2) != 0x0 && focusIndex >= 0) {
            this.setFocusIndex(focusIndex);
        }
    }
    
    public void setSelection(int max, int min) {
        this.checkWidget();
        this.deselectAll();
        if (min < 0 || max > min || ((this.style & 0x4) != 0x0 && max != min)) {
            return;
        }
        final int sendMessage = OS.SendMessage(this.handle, 395, 0, 0);
        if (sendMessage == 0 || max >= sendMessage) {
            return;
        }
        max = Math.max(0, max);
        min = Math.min(min, sendMessage - 1);
        if ((this.style & 0x4) != 0x0) {
            this.select(max, true);
        }
        else {
            this.select(max, min, true);
            this.setFocusIndex(max);
        }
    }
    
    public void setTopIndex(int min) {
        this.checkWidget();
        if (OS.SendMessage(this.handle, 407, min, 0) == -1) {
            min = Math.min(OS.SendMessage(this.handle, 395, 0, 0) - 1, Math.max(0, min));
            OS.SendMessage(this.handle, 407, min, 0);
        }
    }
    
    public void showSelection() {
        this.checkWidget();
        int sendMessage;
        if ((this.style & 0x4) != 0x0) {
            sendMessage = OS.SendMessage(this.handle, 392, 0, 0);
        }
        else {
            final int[] array = { 0 };
            final int sendMessage2 = OS.SendMessage(this.handle, 401, 1, array);
            sendMessage = array[0];
            if (sendMessage2 != 1) {
                sendMessage = -1;
            }
        }
        if (sendMessage == -1) {
            return;
        }
        final int sendMessage3 = OS.SendMessage(this.handle, 395, 0, 0);
        if (sendMessage3 == 0) {
            return;
        }
        final int sendMessage4 = OS.SendMessage(this.handle, 417, 0, 0);
        this.forceResize();
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        final int sendMessage5 = OS.SendMessage(this.handle, 398, 0, 0);
        final int max = Math.max(rect.bottom / sendMessage4, 1);
        final int n = Math.min(sendMessage5 + max, sendMessage3) - 1;
        if (sendMessage5 <= sendMessage && sendMessage <= n) {
            return;
        }
        OS.SendMessage(this.handle, 407, Math.min(Math.max(sendMessage - max / 2, 0), sendMessage3 - 1), 0);
    }
    
    int widgetStyle() {
        final int n = super.widgetStyle() | 0x1 | 0x100;
        if ((this.style & 0x4) != 0x0) {
            return n;
        }
        if ((this.style & 0x2) == 0x0) {
            return n;
        }
        if ((this.style & 0x40) != 0x0) {
            return n | 0x8;
        }
        return n | 0x800;
    }
    
    TCHAR windowClass() {
        return List.ListClass;
    }
    
    int windowProc() {
        return List.ListProc;
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        final LRESULT wm_CHAR = super.WM_CHAR(n, n2);
        if (wm_CHAR != null) {
            return wm_CHAR;
        }
        if (OS.GetKeyState(17) < 0 && OS.GetKeyState(16) >= 0 && (OS.GetWindowLong(this.handle, -16) & 0x800) != 0x0) {
            switch (n) {
                case 32: {
                    final int sendMessage = OS.SendMessage(this.handle, 415, 0, 0);
                    final int sendMessage2 = OS.SendMessage(this.handle, 391, sendMessage, 0);
                    if (sendMessage2 == -1) {
                        break;
                    }
                    OS.SendMessage(this.handle, 389, (sendMessage2 == 0) ? 1 : 0, sendMessage);
                    OS.SendMessage(this.handle, 61852, sendMessage, 0);
                    this.sendSelectionEvent(13);
                    return LRESULT.ZERO;
                }
            }
        }
        return wm_CHAR;
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        final LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        if (OS.GetKeyState(17) < 0 && OS.GetKeyState(16) >= 0 && (OS.GetWindowLong(this.handle, -16) & 0x800) != 0x0) {
            int n3 = -1;
            switch (n) {
                case 32: {
                    return LRESULT.ZERO;
                }
                case 38:
                case 40: {
                    n3 = Math.max(0, OS.SendMessage(this.handle, 415, 0, 0) + ((n == 38) ? -1 : 1));
                    break;
                }
                case 33: {
                    final int sendMessage = OS.SendMessage(this.handle, 398, 0, 0);
                    if (OS.SendMessage(this.handle, 415, 0, 0) != sendMessage) {
                        n3 = sendMessage;
                        break;
                    }
                    this.forceResize();
                    final RECT rect = new RECT();
                    OS.GetClientRect(this.handle, rect);
                    n3 = Math.max(0, sendMessage - (Math.max(2, rect.bottom / OS.SendMessage(this.handle, 417, 0, 0)) - 1));
                    break;
                }
                case 34: {
                    final int sendMessage2 = OS.SendMessage(this.handle, 398, 0, 0);
                    final int sendMessage3 = OS.SendMessage(this.handle, 415, 0, 0);
                    this.forceResize();
                    final RECT rect2 = new RECT();
                    OS.GetClientRect(this.handle, rect2);
                    final int max = Math.max(2, rect2.bottom / OS.SendMessage(this.handle, 417, 0, 0));
                    final int n4 = sendMessage2 + max - 1;
                    if (sendMessage3 != n4) {
                        n3 = n4;
                    }
                    else {
                        n3 = n4 + max - 1;
                    }
                    final int sendMessage4 = OS.SendMessage(this.handle, 395, 0, 0);
                    if (sendMessage4 != -1) {
                        n3 = Math.min(sendMessage4 - 1, n3);
                        break;
                    }
                    break;
                }
                case 36: {
                    n3 = 0;
                    break;
                }
                case 35: {
                    final int sendMessage5 = OS.SendMessage(this.handle, 395, 0, 0);
                    if (sendMessage5 == -1) {
                        break;
                    }
                    n3 = sendMessage5 - 1;
                    break;
                }
            }
            if (n3 != -1) {
                if ((OS.SendMessage(this.handle, 297, 0, 0) & 0x1) != 0x0) {
                    OS.SendMessage(this.handle, 295, 3, 0);
                    final RECT rect3 = new RECT();
                    OS.SendMessage(this.handle, 408, OS.SendMessage(this.handle, 415, 0, 0), rect3);
                    OS.InvalidateRect(this.handle, rect3, true);
                }
                OS.SendMessage(this.handle, 414, n3, 0);
                return LRESULT.ZERO;
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_SETREDRAW(final int n, final int n2) {
        final LRESULT wm_SETREDRAW = super.WM_SETREDRAW(n, n2);
        if (wm_SETREDRAW != null) {
            return wm_SETREDRAW;
        }
        OS.DefWindowProc(this.handle, 11, n, n2);
        return wm_SETREDRAW;
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final int sendMessage = OS.SendMessage(this.handle, 398, 0, 0);
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (!this.isDisposed()) {
            final SCROLLINFO scrollinfo = new SCROLLINFO();
            scrollinfo.cbSize = SCROLLINFO.sizeof;
            scrollinfo.fMask = 4;
            if (OS.GetScrollInfo(this.handle, 0, scrollinfo) && scrollinfo.nPos != 0) {
                OS.InvalidateRect(this.handle, null, true);
            }
            if (sendMessage != OS.SendMessage(this.handle, 398, 0, 0)) {
                OS.InvalidateRect(this.handle, null, true);
            }
        }
        return wm_SIZE;
    }
    
    LRESULT wmCommandChild(final int n, final int n2) {
        switch (OS.HIWORD(n)) {
            case 1: {
                this.sendSelectionEvent(13);
                break;
            }
            case 2: {
                this.sendSelectionEvent(14);
                break;
            }
        }
        return super.wmCommandChild(n, n2);
    }
}
