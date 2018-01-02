// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.NMLINK;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.accessibility.Accessible;
import org.eclipse.swt.accessibility.AccessibleControlListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleListener;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.internal.win32.LITEM;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.internal.win32.TEXTMETRIC;
import org.eclipse.swt.internal.win32.TEXTMETRICA;
import org.eclipse.swt.internal.win32.TEXTMETRICW;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.TextLayout;

public class Link extends Control
{
    String text;
    TextLayout layout;
    Color linkColor;
    Color disabledColor;
    Point[] offsets;
    Point selection;
    String[] ids;
    int[] mnemonics;
    int focusIndex;
    int mouseDownIndex;
    int font;
    static final RGB LINK_FOREGROUND;
    static final int LinkProc;
    static final TCHAR LinkClass;
    
    static {
        LINK_FOREGROUND = new RGB(0, 51, 153);
        LinkClass = new TCHAR(0, "SysLink", true);
        if (OS.COMCTL32_MAJOR >= 6) {
            final WNDCLASS wndclass = new WNDCLASS();
            OS.GetClassInfo(0, Link.LinkClass, wndclass);
            LinkProc = wndclass.lpfnWndProc;
            final int getModuleHandle = OS.GetModuleHandle(null);
            final int getProcessHeap = OS.GetProcessHeap();
            wndclass.hInstance = getModuleHandle;
            final WNDCLASS wndclass2 = wndclass;
            wndclass2.style &= 0xFFFFBFFF;
            final WNDCLASS wndclass3 = wndclass;
            wndclass3.style |= 0x8;
            final int n = Link.LinkClass.length() * TCHAR.sizeof;
            final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n);
            OS.MoveMemory(heapAlloc, Link.LinkClass, n);
            wndclass.lpszClassName = heapAlloc;
            OS.RegisterClass(wndclass);
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        else {
            LinkProc = 0;
        }
    }
    
    public Link(final Composite composite, final int n) {
        super(composite, n);
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
        if (Link.LinkProc != 0) {
            switch (n2) {
                case 15: {
                    if (n3 != 0) {
                        OS.SendMessage(n, 792, n3, 0);
                        return 0;
                    }
                    break;
                }
            }
            return OS.CallWindowProc(Link.LinkProc, n, n2, n3, n4);
        }
        return OS.DefWindowProc(n, n2, n3, n4);
    }
    
    public Point computeSize(int n, int n2, final boolean b) {
        this.checkWidget();
        if (n != -1 && n < 0) {
            n = 0;
        }
        if (n2 != -1 && n2 < 0) {
            n2 = 0;
        }
        int width;
        int n4;
        if (OS.COMCTL32_MAJOR >= 6) {
            final int getDC = OS.GetDC(this.handle);
            final int sendMessage = OS.SendMessage(this.handle, 49, 0, 0);
            final int selectObject = OS.SelectObject(getDC, sendMessage);
            if (this.text.length() > 0) {
                final TCHAR tchar = new TCHAR(this.getCodePage(), this.parse(this.text), false);
                final RECT rect = new RECT();
                int n3 = 3072;
                if (n != -1) {
                    n3 |= 0x10;
                    rect.right = n;
                }
                OS.DrawText(getDC, tchar, tchar.length(), rect, n3);
                width = rect.right - rect.left;
                n4 = rect.bottom;
            }
            else {
                final TEXTMETRIC textmetric = OS.IsUnicode ? new TEXTMETRICW() : new TEXTMETRICA();
                OS.GetTextMetrics(getDC, textmetric);
                width = 0;
                n4 = textmetric.tmHeight;
            }
            if (sendMessage != 0) {
                OS.SelectObject(getDC, selectObject);
            }
            OS.ReleaseDC(this.handle, getDC);
        }
        else {
            final int width2 = this.layout.getWidth();
            if (n == 0) {
                this.layout.setWidth(1);
                final Rectangle bounds = this.layout.getBounds();
                width = 0;
                n4 = bounds.height;
            }
            else {
                this.layout.setWidth(n);
                final Rectangle bounds2 = this.layout.getBounds();
                width = bounds2.width;
                n4 = bounds2.height;
            }
            this.layout.setWidth(width2);
        }
        if (n != -1) {
            width = n;
        }
        if (n2 != -1) {
            n4 = n2;
        }
        final int borderWidth = this.getBorderWidth();
        return new Point(width + borderWidth * 2, n4 + borderWidth * 2);
    }
    
    void createHandle() {
        super.createHandle();
        this.state |= 0x100;
        if (OS.COMCTL32_MAJOR < 6) {
            this.layout = new TextLayout(this.display);
            if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10)) {
                this.linkColor = Color.win32_new(this.display, OS.GetSysColor(OS.COLOR_HOTLIGHT));
            }
            else {
                this.linkColor = new Color(this.display, Link.LINK_FOREGROUND);
            }
            this.disabledColor = Color.win32_new(this.display, OS.GetSysColor(OS.COLOR_GRAYTEXT));
            this.offsets = new Point[0];
            this.ids = new String[0];
            this.mnemonics = new int[0];
            this.selection = new Point(-1, -1);
            final int n = -1;
            this.mouseDownIndex = n;
            this.focusIndex = n;
        }
    }
    
    void createWidget() {
        super.createWidget();
        this.text = "";
        if (OS.COMCTL32_MAJOR < 6) {
            if ((this.style & 0x8000000) != 0x0) {
                this.layout.setOrientation(67108864);
            }
            this.initAccessible();
        }
    }
    
    void drawWidget(final GC gc, final RECT rect) {
        this.drawBackground(gc.handle, rect);
        if (this.selection.x > this.selection.y) {
            final int y = this.selection.y;
            final int x = this.selection.x;
        }
        final int n = -1;
        if (!OS.IsWindowEnabled(this.handle)) {
            gc.setForeground(this.disabledColor);
        }
        this.layout.draw(gc, 0, 0, n, n, null, null);
        if (this.hasFocus() && this.focusIndex != -1) {
            final Rectangle[] rectangles = this.getRectangles(this.focusIndex);
            for (int i = 0; i < rectangles.length; ++i) {
                final Rectangle rectangle = rectangles[i];
                gc.drawFocus(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
        }
        if (this.hooks(9) || this.filters(9)) {
            final Event event = new Event();
            event.gc = gc;
            event.x = rect.left;
            event.y = rect.top;
            event.width = rect.right - rect.left;
            event.height = rect.bottom - rect.top;
            this.sendEvent(9, event);
            event.gc = null;
        }
    }
    
    void enableWidget(final boolean b) {
        if (OS.COMCTL32_MAJOR >= 6) {
            final LITEM litem = new LITEM();
            litem.mask = 3;
            litem.stateMask = 2;
            litem.state = (b ? 2 : 0);
            while (OS.SendMessage(this.handle, 1794, 0, litem) != 0) {
                final LITEM litem2 = litem;
                ++litem2.iLink;
            }
        }
        else {
            final TextStyle textStyle = new TextStyle(null, b ? this.linkColor : this.disabledColor, null);
            textStyle.underline = true;
            for (int i = 0; i < this.offsets.length; ++i) {
                final Point point = this.offsets[i];
                this.layout.setStyle(textStyle, point.x, point.y);
            }
            this.redraw();
        }
        super.enableWidget(b);
    }
    
    void initAccessible() {
        final Accessible accessible = this.getAccessible();
        accessible.addAccessibleListener(new AccessibleAdapter() {
            public void getName(final AccessibleEvent accessibleEvent) {
                accessibleEvent.result = Link.this.parse(Link.this.text);
            }
        });
        accessible.addAccessibleControlListener(new AccessibleControlAdapter() {
            public void getChildAtPoint(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.childID = -1;
            }
            
            public void getLocation(final AccessibleControlEvent accessibleControlEvent) {
                final Rectangle map = Link.this.display.map(Link.this.getParent(), null, Link.this.getBounds());
                accessibleControlEvent.x = map.x;
                accessibleControlEvent.y = map.y;
                accessibleControlEvent.width = map.width;
                accessibleControlEvent.height = map.height;
            }
            
            public void getChildCount(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 0;
            }
            
            public void getRole(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 30;
            }
            
            public void getState(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.detail = 1048576;
                if (Link.this.hasFocus()) {
                    accessibleControlEvent.detail |= 0x4;
                }
            }
            
            public void getDefaultAction(final AccessibleControlEvent accessibleControlEvent) {
                accessibleControlEvent.result = SWT.getMessage("SWT_Press");
            }
            
            public void getSelection(final AccessibleControlEvent accessibleControlEvent) {
                if (Link.this.hasFocus()) {
                    accessibleControlEvent.childID = -1;
                }
            }
            
            public void getFocus(final AccessibleControlEvent accessibleControlEvent) {
                if (Link.this.hasFocus()) {
                    accessibleControlEvent.childID = -1;
                }
            }
        });
    }
    
    String getNameText() {
        return this.getText();
    }
    
    Rectangle[] getRectangles(final int n) {
        Rectangle[] array = new Rectangle[this.layout.getLineCount()];
        int[] lineOffsets;
        Point point;
        int n2;
        for (lineOffsets = this.layout.getLineOffsets(), point = this.offsets[n], n2 = 1; point.x > lineOffsets[n2]; ++n2) {}
        int n3;
        for (n3 = 1; point.y > lineOffsets[n3]; ++n3) {}
        int n4 = 0;
        if (n2 == n3) {
            array[n4++] = this.layout.getBounds(point.x, point.y);
        }
        else {
            array[n4++] = this.layout.getBounds(point.x, lineOffsets[n2] - 1);
            array[n4++] = this.layout.getBounds(lineOffsets[n3 - 1], point.y);
            if (n3 - n2 > 1) {
                for (int i = n2; i < n3 - 1; ++i) {
                    array[n4++] = this.layout.getLineBounds(i);
                }
            }
        }
        if (array.length != n4) {
            final Rectangle[] array2 = new Rectangle[n4];
            System.arraycopy(array, 0, array2, 0, n4);
            array = array2;
        }
        return array;
    }
    
    public String getText() {
        this.checkWidget();
        return this.text;
    }
    
    boolean mnemonicHit(final char c) {
        if (this.mnemonics != null) {
            final char upperCase = Character.toUpperCase(c);
            final String parse = this.parse(this.text);
            int i = 0;
            while (i < this.mnemonics.length - 1) {
                if (this.mnemonics[i] != -1 && upperCase == Character.toUpperCase(parse.charAt(this.mnemonics[i]))) {
                    if (!this.setFocus()) {
                        return false;
                    }
                    if (OS.COMCTL32_MAJOR >= 6) {
                        final int getWindowLong = OS.GetWindowLong(this.handle, -16);
                        final LITEM litem = new LITEM();
                        litem.mask = 3;
                        litem.stateMask = 1;
                        while (litem.iLink < this.mnemonics.length) {
                            if (litem.iLink != i) {
                                OS.SendMessage(this.handle, 1794, 0, litem);
                            }
                            final LITEM litem2 = litem;
                            ++litem2.iLink;
                        }
                        litem.iLink = i;
                        litem.state = 1;
                        OS.SendMessage(this.handle, 1794, 0, litem);
                        OS.SetWindowLong(this.handle, -16, getWindowLong);
                    }
                    else {
                        this.focusIndex = i;
                        this.redraw();
                    }
                    return true;
                }
                else {
                    ++i;
                }
            }
        }
        return false;
    }
    
    boolean mnemonicMatch(final char c) {
        if (this.mnemonics != null) {
            final char upperCase = Character.toUpperCase(c);
            final String parse = this.parse(this.text);
            for (int i = 0; i < this.mnemonics.length - 1; ++i) {
                if (this.mnemonics[i] != -1 && upperCase == Character.toUpperCase(parse.charAt(this.mnemonics[i]))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    String parse(final String s) {
        final int length = s.length();
        this.offsets = new Point[length / 4];
        this.ids = new String[length / 4];
        this.mnemonics = new int[length / 4 + 1];
        final StringBuffer sb = new StringBuffer();
        final char[] array = new char[length];
        s.getChars(0, s.length(), array, 0);
        int i = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        while (i < length) {
            final char lowerCase = Character.toLowerCase(array[i]);
            Label_0669: {
                switch (n) {
                    case 0: {
                        if (lowerCase == '<') {
                            n4 = i;
                            ++n;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        if (lowerCase == 'a') {
                            ++n;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        switch (lowerCase) {
                            case 104: {
                                n = 7;
                                break Label_0669;
                            }
                            case 62: {
                                n5 = i + 1;
                                ++n;
                                break Label_0669;
                            }
                            default: {
                                if (Character.isWhitespace(lowerCase)) {
                                    break Label_0669;
                                }
                                n = 13;
                                break Label_0669;
                            }
                        }
                        break;
                    }
                    case 3: {
                        if (lowerCase == '<') {
                            n6 = i;
                            ++n;
                            break;
                        }
                        break;
                    }
                    case 4: {
                        n = ((lowerCase == '/') ? (n + 1) : 3);
                        break;
                    }
                    case 5: {
                        n = ((lowerCase == 'a') ? (n + 1) : 3);
                        break;
                    }
                    case 6: {
                        if (lowerCase == '>') {
                            this.mnemonics[n2] = this.parseMnemonics(array, n3, n4, sb);
                            final int length2 = sb.length();
                            this.parseMnemonics(array, n5, n6, sb);
                            this.offsets[n2] = new Point(length2, sb.length() - 1);
                            if (this.ids[n2] == null) {
                                this.ids[n2] = new String(array, n5, n6 - n5);
                            }
                            ++n2;
                            n4 = (n3 = (n5 = (n6 = (n7 = i + 1))));
                            n = 0;
                            break;
                        }
                        n = 3;
                        break;
                    }
                    case 7: {
                        n = ((lowerCase == 'r') ? (n + 1) : 0);
                        break;
                    }
                    case 8: {
                        n = ((lowerCase == 'e') ? (n + 1) : 0);
                        break;
                    }
                    case 9: {
                        n = ((lowerCase == 'f') ? (n + 1) : 0);
                        break;
                    }
                    case 10: {
                        n = ((lowerCase == '=') ? (n + 1) : 0);
                        break;
                    }
                    case 11: {
                        if (lowerCase == '\"') {
                            ++n;
                            n7 = i + 1;
                            break;
                        }
                        n = 0;
                        break;
                    }
                    case 12: {
                        if (lowerCase == '\"') {
                            this.ids[n2] = new String(array, n7, i - n7);
                            n = 2;
                            break;
                        }
                        break;
                    }
                    case 13: {
                        if (Character.isWhitespace(lowerCase)) {
                            n = 0;
                            break;
                        }
                        if (lowerCase == '=') {
                            ++n;
                            break;
                        }
                        break;
                    }
                    case 14: {
                        n = ((lowerCase == '\"') ? (n + 1) : 0);
                        break;
                    }
                    case 15: {
                        if (lowerCase == '\"') {
                            n = 2;
                            break;
                        }
                        break;
                    }
                    default: {
                        n = 0;
                        break;
                    }
                }
            }
            ++i;
        }
        if (n3 < length) {
            final int mnemonics = this.parseMnemonics(array, n3, n4, sb);
            int mnemonics2 = this.parseMnemonics(array, Math.max(n4, n5), length, sb);
            if (mnemonics2 == -1) {
                mnemonics2 = mnemonics;
            }
            this.mnemonics[n2] = mnemonics2;
        }
        else {
            this.mnemonics[n2] = -1;
        }
        if (this.offsets.length != n2) {
            final Point[] offsets = new Point[n2];
            System.arraycopy(this.offsets, 0, offsets, 0, n2);
            this.offsets = offsets;
            final String[] ids = new String[n2];
            System.arraycopy(this.ids, 0, ids, 0, n2);
            this.ids = ids;
            final int[] mnemonics3 = new int[n2 + 1];
            System.arraycopy(this.mnemonics, 0, mnemonics3, 0, n2 + 1);
            this.mnemonics = mnemonics3;
        }
        return sb.toString();
    }
    
    int parseMnemonics(final char[] array, final int n, final int n2, final StringBuffer sb) {
        int length = -1;
        for (int i = n; i < n2; ++i) {
            if (array[i] == '&') {
                if (i + 1 < n2 && array[i + 1] == '&') {
                    sb.append(array[i]);
                    ++i;
                }
                else {
                    length = sb.length();
                }
            }
            else {
                sb.append(array[i]);
            }
        }
        return length;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        if (this.layout != null) {
            this.layout.dispose();
        }
        this.layout = null;
        if (this.linkColor != null) {
            this.linkColor.dispose();
        }
        this.linkColor = null;
        this.disabledColor = null;
        this.offsets = null;
        this.ids = null;
        this.mnemonics = null;
        this.text = null;
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
    
    public void setText(String text) {
        this.checkWidget();
        if (text == null) {
            this.error(4);
        }
        if (text.equals(this.text)) {
            return;
        }
        this.text = text;
        if (OS.COMCTL32_MAJOR >= 6) {
            final boolean isWindowEnabled = OS.IsWindowEnabled(this.handle);
            if (text.length() == 0) {
                text = " ";
            }
            OS.SetWindowText(this.handle, new TCHAR(this.getCodePage(), text, true));
            this.parse(this.text);
            this.enableWidget(isWindowEnabled);
        }
        else {
            this.layout.setText(this.parse(this.text));
            this.focusIndex = ((this.offsets.length > 0) ? 0 : -1);
            final Point selection = this.selection;
            final Point selection2 = this.selection;
            final int n = -1;
            selection2.y = n;
            selection.x = n;
            final int getWindowLong = OS.GetWindowLong(this.handle, -16);
            int n2;
            if (this.offsets.length > 0) {
                n2 = (getWindowLong | 0x10000);
            }
            else {
                n2 = (getWindowLong & 0xFFFEFFFF);
            }
            OS.SetWindowLong(this.handle, -16, n2);
            final TextStyle textStyle = new TextStyle(null, OS.IsWindowEnabled(this.handle) ? this.linkColor : this.disabledColor, null);
            textStyle.underline = true;
            for (int i = 0; i < this.offsets.length; ++i) {
                final Point point = this.offsets[i];
                this.layout.setStyle(textStyle, point.x, point.y);
            }
            final TextStyle textStyle2 = new TextStyle(null, null, null);
            textStyle2.underline = true;
            for (int j = 0; j < this.mnemonics.length; ++j) {
                final int n3 = this.mnemonics[j];
                if (n3 != -1) {
                    this.layout.setStyle(textStyle2, n3, n3);
                }
            }
            this.redraw();
        }
    }
    
    int widgetStyle() {
        return super.widgetStyle() | 0x10000;
    }
    
    TCHAR windowClass() {
        return (OS.COMCTL32_MAJOR >= 6) ? Link.LinkClass : this.display.windowClass;
    }
    
    int windowProc() {
        return (Link.LinkProc != 0) ? Link.LinkProc : this.display.windowProc;
    }
    
    LRESULT WM_CHAR(final int n, final int n2) {
        final LRESULT wm_CHAR = super.WM_CHAR(n, n2);
        if (wm_CHAR != null) {
            return wm_CHAR;
        }
        if (OS.COMCTL32_MAJOR < 6) {
            if (this.focusIndex == -1) {
                return wm_CHAR;
            }
            switch (n) {
                case 13:
                case 32: {
                    final Event event = new Event();
                    event.text = this.ids[this.focusIndex];
                    this.sendSelectionEvent(13, event, true);
                    break;
                }
                case 9: {
                    if (OS.GetKeyState(16) >= 0) {
                        if (this.focusIndex < this.offsets.length - 1) {
                            ++this.focusIndex;
                            this.redraw();
                            break;
                        }
                        break;
                    }
                    else {
                        if (this.focusIndex > 0) {
                            --this.focusIndex;
                            this.redraw();
                            break;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        else {
            switch (n) {
                case 9:
                case 13:
                case 32: {
                    return new LRESULT(this.callWindowProc(this.handle, 256, n, n2));
                }
            }
        }
        return wm_CHAR;
    }
    
    LRESULT WM_GETDLGCODE(final int n, final int n2) {
        final LRESULT wm_GETDLGCODE = super.WM_GETDLGCODE(n, n2);
        if (wm_GETDLGCODE != null) {
            return wm_GETDLGCODE;
        }
        int callWindowProc = 0;
        int n3;
        int n4;
        if (OS.COMCTL32_MAJOR >= 6) {
            final LITEM litem = new LITEM();
            litem.mask = 3;
            litem.stateMask = 1;
            n3 = 0;
            while (OS.SendMessage(this.handle, 1795, 0, litem) != 0) {
                if ((litem.state & 0x1) != 0x0) {
                    n3 = litem.iLink;
                }
                final LITEM litem2 = litem;
                ++litem2.iLink;
            }
            n4 = litem.iLink;
            callWindowProc = this.callWindowProc(this.handle, 135, n, n2);
        }
        else {
            n3 = this.focusIndex;
            n4 = this.offsets.length;
        }
        if (n4 == 0) {
            return new LRESULT(callWindowProc | 0x100);
        }
        final boolean b = OS.GetKeyState(16) >= 0;
        if (b && n3 < n4 - 1) {
            return new LRESULT(callWindowProc | 0x2);
        }
        if (!b && n3 > 0) {
            return new LRESULT(callWindowProc | 0x2);
        }
        return wm_GETDLGCODE;
    }
    
    LRESULT WM_GETFONT(final int n, final int n2) {
        final LRESULT wm_GETFONT = super.WM_GETFONT(n, n2);
        if (wm_GETFONT != null) {
            return wm_GETFONT;
        }
        final int callWindowProc = this.callWindowProc(this.handle, 49, n, n2);
        if (callWindowProc != 0) {
            return new LRESULT(callWindowProc);
        }
        if (this.font == 0) {
            this.font = this.defaultFont();
        }
        return new LRESULT(this.font);
    }
    
    LRESULT WM_KEYDOWN(final int n, final int n2) {
        final LRESULT wm_KEYDOWN = super.WM_KEYDOWN(n, n2);
        if (wm_KEYDOWN != null) {
            return wm_KEYDOWN;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            switch (n) {
                case 9:
                case 13:
                case 32: {
                    return LRESULT.ZERO;
                }
            }
        }
        return wm_KEYDOWN;
    }
    
    LRESULT WM_KILLFOCUS(final int n, final int n2) {
        final LRESULT wm_KILLFOCUS = super.WM_KILLFOCUS(n, n2);
        if (OS.COMCTL32_MAJOR < 6) {
            this.redraw();
        }
        return wm_KILLFOCUS;
    }
    
    LRESULT WM_LBUTTONDOWN(final int n, final int n2) {
        final LRESULT wm_LBUTTONDOWN = super.WM_LBUTTONDOWN(n, n2);
        if (wm_LBUTTONDOWN == LRESULT.ZERO) {
            return wm_LBUTTONDOWN;
        }
        if (OS.COMCTL32_MAJOR < 6) {
            if (this.focusIndex != -1) {
                this.setFocus();
            }
            final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
            final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
            final int offset = this.layout.getOffset(get_X_LPARAM, get_Y_LPARAM, null);
            int x = this.selection.x;
            int y = this.selection.y;
            this.selection.x = offset;
            this.selection.y = -1;
            if (x != -1 && y != -1) {
                if (x > y) {
                    final int n3 = x;
                    x = y;
                    y = n3;
                }
                final Rectangle bounds = this.layout.getBounds(x, y);
                this.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
            }
            for (int i = 0; i < this.offsets.length; ++i) {
                final Rectangle[] rectangles = this.getRectangles(i);
                for (int j = 0; j < rectangles.length; ++j) {
                    if (rectangles[j].contains(get_X_LPARAM, get_Y_LPARAM)) {
                        if (i != this.focusIndex) {
                            this.redraw();
                        }
                        final int n4 = i;
                        this.mouseDownIndex = n4;
                        this.focusIndex = n4;
                        return wm_LBUTTONDOWN;
                    }
                }
            }
        }
        return wm_LBUTTONDOWN;
    }
    
    LRESULT WM_LBUTTONUP(final int n, final int n2) {
        final LRESULT wm_LBUTTONUP = super.WM_LBUTTONUP(n, n2);
        if (wm_LBUTTONUP == LRESULT.ZERO) {
            return wm_LBUTTONUP;
        }
        if (OS.COMCTL32_MAJOR < 6) {
            if (this.mouseDownIndex == -1) {
                return wm_LBUTTONUP;
            }
            final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
            final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
            final Rectangle[] rectangles = this.getRectangles(this.mouseDownIndex);
            for (int i = 0; i < rectangles.length; ++i) {
                if (rectangles[i].contains(get_X_LPARAM, get_Y_LPARAM)) {
                    final Event event = new Event();
                    event.text = this.ids[this.mouseDownIndex];
                    this.sendSelectionEvent(13, event, true);
                    break;
                }
            }
        }
        this.mouseDownIndex = -1;
        return wm_LBUTTONUP;
    }
    
    LRESULT WM_NCHITTEST(final int n, final int n2) {
        final LRESULT wm_NCHITTEST = super.WM_NCHITTEST(n, n2);
        if (wm_NCHITTEST != null) {
            return wm_NCHITTEST;
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            return new LRESULT(1);
        }
        return wm_NCHITTEST;
    }
    
    LRESULT WM_MOUSEMOVE(final int n, final int n2) {
        final LRESULT wm_MOUSEMOVE = super.WM_MOUSEMOVE(n, n2);
        if (OS.COMCTL32_MAJOR < 6) {
            final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
            final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
            if (OS.GetKeyState(1) < 0) {
                int y = this.selection.y;
                this.selection.y = this.layout.getOffset(get_X_LPARAM, get_Y_LPARAM, null);
                if (this.selection.y != y) {
                    int y2 = this.selection.y;
                    if (y > y2) {
                        final int n3 = y;
                        y = y2;
                        y2 = n3;
                    }
                    final Rectangle bounds = this.layout.getBounds(y, y2);
                    this.redraw(bounds.x, bounds.y, bounds.width, bounds.height, false);
                }
            }
            else {
                for (int i = 0; i < this.offsets.length; ++i) {
                    final Rectangle[] rectangles = this.getRectangles(i);
                    for (int j = 0; j < rectangles.length; ++j) {
                        if (rectangles[j].contains(get_X_LPARAM, get_Y_LPARAM)) {
                            this.setCursor(this.display.getSystemCursor(21));
                            return wm_MOUSEMOVE;
                        }
                    }
                }
                this.setCursor(null);
            }
        }
        return wm_MOUSEMOVE;
    }
    
    LRESULT WM_PAINT(final int n, final int n2) {
        if (OS.COMCTL32_MAJOR >= 6) {
            return super.WM_PAINT(n, n2);
        }
        final PAINTSTRUCT ps = new PAINTSTRUCT();
        final GCData gcData = new GCData();
        gcData.ps = ps;
        gcData.hwnd = this.handle;
        final GC new_GC = this.new_GC(gcData);
        if (new_GC != null) {
            final int n3 = ps.right - ps.left;
            final int n4 = ps.bottom - ps.top;
            if (n3 != 0 && n4 != 0) {
                final RECT rect = new RECT();
                OS.SetRect(rect, ps.left, ps.top, ps.right, ps.bottom);
                this.drawWidget(new_GC, rect);
            }
            new_GC.dispose();
        }
        return LRESULT.ZERO;
    }
    
    LRESULT WM_PRINTCLIENT(final int n, final int n2) {
        final LRESULT wm_PRINTCLIENT = super.WM_PRINTCLIENT(n, n2);
        if (OS.COMCTL32_MAJOR < 6) {
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            final GCData gcData = new GCData();
            gcData.device = this.display;
            gcData.foreground = this.getForegroundPixel();
            final GC win32_new = GC.win32_new(n, gcData);
            this.drawWidget(win32_new, rect);
            win32_new.dispose();
        }
        return wm_PRINTCLIENT;
    }
    
    LRESULT WM_SETFOCUS(final int n, final int n2) {
        final LRESULT wm_SETFOCUS = super.WM_SETFOCUS(n, n2);
        if (OS.COMCTL32_MAJOR < 6) {
            this.redraw();
        }
        return wm_SETFOCUS;
    }
    
    LRESULT WM_SETFONT(final int font, final int n) {
        if (OS.COMCTL32_MAJOR < 6) {
            this.layout.setFont(Font.win32_new(this.display, font));
        }
        if (n != 0) {
            OS.InvalidateRect(this.handle, null, true);
        }
        this.font = font;
        return super.WM_SETFONT(font, n);
    }
    
    LRESULT WM_SIZE(final int n, final int n2) {
        final LRESULT wm_SIZE = super.WM_SIZE(n, n2);
        if (OS.COMCTL32_MAJOR < 6) {
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            this.layout.setWidth((rect.right > 0) ? rect.right : -1);
            this.redraw();
        }
        return wm_SIZE;
    }
    
    LRESULT wmColorChild(final int n, final int n2) {
        final LRESULT wmColorChild = super.wmColorChild(n, n2);
        if (OS.COMCTL32_MAJOR >= 6 && !OS.IsWindowEnabled(this.handle)) {
            OS.SetTextColor(n, OS.GetSysColor(OS.COLOR_GRAYTEXT));
            if (wmColorChild == null) {
                final int backgroundPixel = this.getBackgroundPixel();
                OS.SetBkColor(n, backgroundPixel);
                return new LRESULT(this.findBrush(backgroundPixel, 0));
            }
        }
        return wmColorChild;
    }
    
    LRESULT wmNotifyChild(final NMHDR nmhdr, final int n, final int n2) {
        if (OS.COMCTL32_MAJOR >= 6) {
            switch (nmhdr.code) {
                case -4:
                case -2: {
                    final NMLINK nmlink = new NMLINK();
                    OS.MoveMemory(nmlink, n2, NMLINK.sizeof);
                    final Event event = new Event();
                    event.text = this.ids[nmlink.iLink];
                    this.sendSelectionEvent(13, event, true);
                    break;
                }
            }
        }
        return super.wmNotifyChild(nmhdr, n, n2);
    }
}
