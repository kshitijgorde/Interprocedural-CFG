// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.win32.TRACKMOUSEEVENT;
import org.eclipse.swt.internal.win32.SHRGINFO;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.internal.win32.OS;

public abstract class Widget
{
    int style;
    int state;
    Display display;
    EventTable eventTable;
    Object data;
    static final int DISPOSED = 1;
    static final int CANVAS = 2;
    static final int KEYED_DATA = 4;
    static final int DISABLED = 8;
    static final int HIDDEN = 16;
    static final int LAYOUT_NEEDED = 32;
    static final int LAYOUT_CHANGED = 64;
    static final int LAYOUT_CHILD = 128;
    static final int THEME_BACKGROUND = 256;
    static final int DRAW_BACKGROUND = 512;
    static final int PARENT_BACKGROUND = 1024;
    static final int RELEASED = 2048;
    static final int DISPOSE_SENT = 4096;
    static final int TRACK_MOUSE = 8192;
    static final int FOREIGN_HANDLE = 16384;
    static final int DRAG_DETECT = 32768;
    static final int MOVE_OCCURRED = 65536;
    static final int MOVE_DEFERRED = 131072;
    static final int RESIZE_OCCURRED = 262144;
    static final int RESIZE_DEFERRED = 524288;
    static final int IGNORE_WM_CHANGEUISTATE = 1048576;
    static final int SKIN_NEEDED = 2097152;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    static final int MAJOR = 5;
    static final int MINOR = 80;
    
    static {
        if (!OS.IsWinCE && OS.COMCTL32_VERSION < OS.VERSION(5, 80)) {
            System.out.println("***WARNING: SWT requires comctl32.dll version 5.80 or greater");
            System.out.println("***WARNING: Detected: " + OS.COMCTL32_MAJOR + "." + OS.COMCTL32_MINOR);
        }
        OS.InitCommonControls();
    }
    
    Widget() {
    }
    
    public Widget(final Widget widget, final int style) {
        this.checkSubclass();
        this.checkParent(widget);
        this.style = style;
        this.display = widget.display;
        this.reskinWidget();
    }
    
    void _addListener(final int n, final Listener listener) {
        if (this.eventTable == null) {
            this.eventTable = new EventTable();
        }
        this.eventTable.hook(n, listener);
    }
    
    public void addListener(final int n, final Listener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        this._addListener(n, listener);
    }
    
    public void addDisposeListener(final DisposeListener disposeListener) {
        this.checkWidget();
        if (disposeListener == null) {
            this.error(4);
        }
        this.addListener(12, new TypedListener(disposeListener));
    }
    
    int callWindowProc(final int n, final int n2, final int n3, final int n4) {
        return 0;
    }
    
    static int checkBits(int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final int n8 = n2 | n3 | n4 | n5 | n6 | n7;
        if ((n & n8) == 0x0) {
            n |= n2;
        }
        if ((n & n2) != 0x0) {
            n = ((n & ~n8) | n2);
        }
        if ((n & n3) != 0x0) {
            n = ((n & ~n8) | n3);
        }
        if ((n & n4) != 0x0) {
            n = ((n & ~n8) | n4);
        }
        if ((n & n5) != 0x0) {
            n = ((n & ~n8) | n5);
        }
        if ((n & n6) != 0x0) {
            n = ((n & ~n8) | n6);
        }
        if ((n & n7) != 0x0) {
            n = ((n & ~n8) | n7);
        }
        return n;
    }
    
    void checkOrientation(final Widget widget) {
        this.style &= 0xF7FFFFFF;
        if ((this.style & 0x6000000) == 0x0 && widget != null) {
            if ((widget.style & 0x2000000) != 0x0) {
                this.style |= 0x2000000;
            }
            if ((widget.style & 0x4000000) != 0x0) {
                this.style |= 0x4000000;
            }
        }
        this.style = checkBits(this.style, 33554432, 67108864, 0, 0, 0, 0);
    }
    
    void checkOpened() {
    }
    
    void checkParent(final Widget widget) {
        if (widget == null) {
            this.error(4);
        }
        if (widget.isDisposed()) {
            this.error(5);
        }
        widget.checkWidget();
        widget.checkOpened();
    }
    
    protected void checkSubclass() {
        if (!this.isValidSubclass()) {
            this.error(43);
        }
    }
    
    protected void checkWidget() {
        final Display display = this.display;
        if (display == null) {
            this.error(24);
        }
        if (display.thread != Thread.currentThread() && display.threadId != OS.GetCurrentThreadId()) {
            this.error(22);
        }
        if ((this.state & 0x1) != 0x0) {
            this.error(24);
        }
    }
    
    void destroyWidget() {
        this.releaseHandle();
    }
    
    int DeferWindowPos(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, int n8) {
        if (OS.IsWinCE && (n8 & 0x1) == 0x0) {
            final RECT rect = new RECT();
            OS.GetWindowRect(n2, rect);
            if (n7 == rect.bottom - rect.top && n6 == rect.right - rect.left) {
                n8 &= 0xFFFFFFDF;
                n8 |= 0x1;
            }
        }
        return OS.DeferWindowPos(n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void dispose() {
        if (this.isDisposed()) {
            return;
        }
        if (!this.isValidThread()) {
            this.error(22);
        }
        this.release(true);
    }
    
    boolean dragDetect(final int n, final int x, final int y, final boolean b, final boolean[] array, final boolean[] array2) {
        if (array2 != null) {
            array2[0] = false;
        }
        if (array != null) {
            array[0] = true;
        }
        final POINT point = new POINT();
        point.x = x;
        point.y = y;
        OS.ClientToScreen(n, point);
        return OS.DragDetect(n, point);
    }
    
    void error(final int n) {
        SWT.error(n);
    }
    
    boolean filters(final int n) {
        return this.display.filters(n);
    }
    
    Widget findItem(final int n) {
        return null;
    }
    
    char[] fixMnemonic(final String s) {
        return this.fixMnemonic(s, false);
    }
    
    char[] fixMnemonic(final String s, final boolean b) {
        final char[] array = new char[s.length() + 1];
        s.getChars(0, s.length(), array, 0);
        int i = 0;
        int j = 0;
        while (i < array.length) {
            if (array[i] == '&') {
                if (i + 1 < array.length && array[i + 1] == '&') {
                    array[j++] = (b ? ' ' : array[i]);
                    ++i;
                }
                ++i;
            }
            else {
                array[j++] = array[i++];
            }
        }
        while (j < array.length) {
            array[j++] = '\0';
        }
        return array;
    }
    
    public Object getData() {
        this.checkWidget();
        return ((this.state & 0x4) != 0x0) ? ((Object[])this.data)[0] : this.data;
    }
    
    public Object getData(final String s) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        if ((this.state & 0x4) != 0x0) {
            final Object[] array = (Object[])this.data;
            for (int i = 1; i < array.length; i += 2) {
                if (s.equals(array[i])) {
                    return array[i + 1];
                }
            }
        }
        return null;
    }
    
    public Display getDisplay() {
        final Display display = this.display;
        if (display == null) {
            this.error(24);
        }
        return display;
    }
    
    public Listener[] getListeners(final int n) {
        this.checkWidget();
        if (this.eventTable == null) {
            return new Listener[0];
        }
        return this.eventTable.getListeners(n);
    }
    
    Menu getMenu() {
        return null;
    }
    
    String getName() {
        final String name = this.getClass().getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex == -1) {
            return name;
        }
        return name.substring(lastIndex + 1, name.length());
    }
    
    String getNameText() {
        return "";
    }
    
    public int getStyle() {
        this.checkWidget();
        return this.style;
    }
    
    boolean hooks(final int n) {
        return this.eventTable != null && this.eventTable.hooks(n);
    }
    
    public boolean isDisposed() {
        return (this.state & 0x1) != 0x0;
    }
    
    public boolean isListening(final int n) {
        this.checkWidget();
        return this.hooks(n);
    }
    
    boolean isValidSubclass() {
        return Display.isValidClass(this.getClass());
    }
    
    boolean isValidThread() {
        return this.getDisplay().isValidThread();
    }
    
    void mapEvent(final int n, final Event event) {
    }
    
    GC new_GC(final GCData gcData) {
        return null;
    }
    
    public void notifyListeners(final int n, Event event) {
        this.checkWidget();
        if (event == null) {
            event = new Event();
        }
        this.sendEvent(n, event);
    }
    
    void postEvent(final int n) {
        this.sendEvent(n, null, false);
    }
    
    void postEvent(final int n, final Event event) {
        this.sendEvent(n, event, false);
    }
    
    void release(final boolean b) {
        if ((this.state & 0x1000) == 0x0) {
            this.state |= 0x1000;
            this.sendEvent(12);
        }
        if ((this.state & 0x1) == 0x0) {
            this.releaseChildren(b);
        }
        if ((this.state & 0x800) == 0x0) {
            this.state |= 0x800;
            if (b) {
                this.releaseParent();
                this.releaseWidget();
                this.destroyWidget();
            }
            else {
                this.releaseWidget();
                this.releaseHandle();
            }
        }
    }
    
    void releaseChildren(final boolean b) {
    }
    
    void releaseHandle() {
        this.state |= 0x1;
        this.display = null;
    }
    
    void releaseParent() {
    }
    
    void releaseWidget() {
        this.eventTable = null;
        this.data = null;
    }
    
    public void removeListener(final int n, final Listener listener) {
        this.checkWidget();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(n, listener);
    }
    
    protected void removeListener(final int n, final SWTEventListener swtEventListener) {
        this.checkWidget();
        if (swtEventListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(n, swtEventListener);
    }
    
    public void removeDisposeListener(final DisposeListener disposeListener) {
        this.checkWidget();
        if (disposeListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(12, disposeListener);
    }
    
    public void reskin(final int n) {
        this.checkWidget();
        this.reskinWidget();
        if ((n & 0x1) != 0x0) {
            this.reskinChildren(n);
        }
    }
    
    void reskinChildren(final int n) {
    }
    
    void reskinWidget() {
        if ((this.state & 0x200000) != 0x200000) {
            this.state |= 0x200000;
            this.display.addSkinnableWidget(this);
        }
    }
    
    boolean sendDragEvent(final int button, final int x, final int y) {
        final Event event = new Event();
        event.button = button;
        event.x = x;
        event.y = y;
        this.setInputState(event, 29);
        this.postEvent(29, event);
        return !this.isDisposed() && event.doit;
    }
    
    boolean sendDragEvent(final int button, final int stateMask, final int x, final int y) {
        final Event event = new Event();
        event.button = button;
        event.x = x;
        event.y = y;
        event.stateMask = stateMask;
        this.postEvent(29, event);
        return !this.isDisposed() && event.doit;
    }
    
    void sendEvent(final Event event) {
        if (!event.display.filterEvent(event) && this.eventTable != null) {
            this.eventTable.sendEvent(event);
        }
    }
    
    void sendEvent(final int n) {
        this.sendEvent(n, null, true);
    }
    
    void sendEvent(final int n, final Event event) {
        this.sendEvent(n, event, true);
    }
    
    void sendEvent(final int type, Event event, final boolean b) {
        if (this.eventTable == null && !this.display.filters(type)) {
            return;
        }
        if (event == null) {
            event = new Event();
        }
        event.type = type;
        event.display = this.display;
        event.widget = this;
        if (event.time == 0) {
            event.time = this.display.getLastEventTime();
        }
        if (b) {
            this.sendEvent(event);
        }
        else {
            this.display.postEvent(event);
        }
    }
    
    void sendSelectionEvent(final int n) {
        this.sendSelectionEvent(n, null, false);
    }
    
    void sendSelectionEvent(final int n, Event event, final boolean b) {
        if (this.eventTable == null && !this.display.filters(n)) {
            return;
        }
        if (event == null) {
            event = new Event();
        }
        this.setInputState(event, n);
        this.sendEvent(n, event, b);
    }
    
    boolean sendKeyEvent(final int n, final int n2, final int n3, final int n4) {
        final Event event = new Event();
        return !this.setKeyState(event, n, n3, n4) || this.sendKeyEvent(n, n2, n3, n4, event);
    }
    
    boolean sendKeyEvent(final int n, final int n2, final int n3, final int n4, final Event event) {
        this.sendEvent(n, event);
        return !this.isDisposed() && event.doit;
    }
    
    boolean sendMouseEvent(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.sendMouseEvent(n, n2, this.display.getClickCount(n, n2, n3, n6), 0, false, n3, n4, n5, n6);
    }
    
    boolean sendMouseEvent(final int n, final int button, final int count, final int detail, final boolean b, final int n2, final int n3, final int n4, final int n5) {
        if (!this.hooks(n) && !this.filters(n)) {
            return true;
        }
        final Event event = new Event();
        event.button = button;
        event.detail = detail;
        event.count = count;
        event.x = OS.GET_X_LPARAM(n5);
        event.y = OS.GET_Y_LPARAM(n5);
        this.setInputState(event, n);
        this.mapEvent(n2, event);
        if (b) {
            this.sendEvent(n, event);
            if (this.isDisposed()) {
                return false;
            }
        }
        else {
            this.postEvent(n, event);
        }
        return event.doit;
    }
    
    boolean sendMouseWheelEvent(final int n, final int n2, final int n3, int makelparam) {
        int get_WHEEL_DELTA_WPARAM = OS.GET_WHEEL_DELTA_WPARAM(n3);
        int n4 = 0;
        if (n == 37) {
            final int[] array = { 0 };
            OS.SystemParametersInfo(104, 0, array, 0);
            if (array[0] == -1) {
                n4 = 2;
            }
            else {
                n4 = 1;
                get_WHEEL_DELTA_WPARAM *= array[0];
            }
            if ((get_WHEEL_DELTA_WPARAM ^ this.display.scrollRemainder) >= 0) {
                get_WHEEL_DELTA_WPARAM += this.display.scrollRemainder;
            }
            this.display.scrollRemainder = get_WHEEL_DELTA_WPARAM % 120;
        }
        else {
            if ((get_WHEEL_DELTA_WPARAM ^ this.display.scrollHRemainder) >= 0) {
                get_WHEEL_DELTA_WPARAM += this.display.scrollHRemainder;
            }
            this.display.scrollHRemainder = get_WHEEL_DELTA_WPARAM % 120;
            get_WHEEL_DELTA_WPARAM = -get_WHEEL_DELTA_WPARAM;
        }
        if (!this.hooks(n) && !this.filters(n)) {
            return true;
        }
        final int n5 = get_WHEEL_DELTA_WPARAM / 120;
        final POINT point = new POINT();
        OS.POINTSTOPOINT(point, makelparam);
        OS.ScreenToClient(n2, point);
        makelparam = OS.MAKELPARAM(point.x, point.y);
        return this.sendMouseEvent(n, 0, n5, n4, true, n2, 522, n3, makelparam);
    }
    
    public void setData(final Object data) {
        this.checkWidget();
        if ((this.state & 0x4) != 0x0) {
            ((Object[])this.data)[0] = data;
        }
        else {
            this.data = data;
        }
    }
    
    public void setData(final String s, final Object o) {
        this.checkWidget();
        if (s == null) {
            this.error(4);
        }
        int i = 1;
        Object data = null;
        if ((this.state & 0x4) != 0x0) {
            for (data = this.data; i < data.length; i += 2) {
                if (s.equals(data[i])) {
                    break;
                }
            }
        }
        if (o != null) {
            if ((this.state & 0x4) != 0x0) {
                if (i == data.length) {
                    final Object[] data2 = new Object[data.length + 2];
                    System.arraycopy(data, 0, data2, 0, data.length);
                    data = (this.data = data2);
                }
            }
            else {
                data = new Object[] { this.data, null, null };
                this.data = data;
                this.state |= 0x4;
            }
            data[i] = s;
            data[i + 1] = o;
        }
        else if ((this.state & 0x4) != 0x0 && i != data.length) {
            final int n = data.length - 2;
            if (n == 1) {
                this.data = data[0];
                this.state &= 0xFFFFFFFB;
            }
            else {
                final Object[] data3 = new Object[n];
                System.arraycopy(data, 0, data3, 0, i);
                System.arraycopy(data, i + 2, data3, i, n - i);
                this.data = data3;
            }
        }
        if (s.equals("org.eclipse.swt.skin.class") || s.equals("org.eclipse.swt.skin.id")) {
            this.reskin(1);
        }
    }
    
    boolean sendFocusEvent(final int n) {
        this.sendEvent(n);
        return true;
    }
    
    boolean setInputState(final Event event, final int n) {
        if (OS.GetKeyState(18) < 0) {
            event.stateMask |= 0x10000;
        }
        if (OS.GetKeyState(16) < 0) {
            event.stateMask |= 0x20000;
        }
        if (OS.GetKeyState(17) < 0) {
            event.stateMask |= 0x40000;
        }
        if (OS.GetKeyState(1) < 0) {
            event.stateMask |= 0x80000;
        }
        if (OS.GetKeyState(4) < 0) {
            event.stateMask |= 0x100000;
        }
        if (OS.GetKeyState(2) < 0) {
            event.stateMask |= 0x200000;
        }
        if (this.display.xMouse) {
            if (OS.GetKeyState(5) < 0) {
                event.stateMask |= 0x800000;
            }
            if (OS.GetKeyState(6) < 0) {
                event.stateMask |= 0x2000000;
            }
        }
        switch (n) {
            case 3:
            case 8: {
                if (event.button == 1) {
                    event.stateMask &= 0xFFF7FFFF;
                }
                if (event.button == 2) {
                    event.stateMask &= 0xFFEFFFFF;
                }
                if (event.button == 3) {
                    event.stateMask &= 0xFFDFFFFF;
                }
                if (event.button == 4) {
                    event.stateMask &= 0xFF7FFFFF;
                }
                if (event.button == 5) {
                    event.stateMask &= 0xFDFFFFFF;
                    break;
                }
                break;
            }
            case 4: {
                if (event.button == 1) {
                    event.stateMask |= 0x80000;
                }
                if (event.button == 2) {
                    event.stateMask |= 0x100000;
                }
                if (event.button == 3) {
                    event.stateMask |= 0x200000;
                }
                if (event.button == 4) {
                    event.stateMask |= 0x800000;
                }
                if (event.button == 5) {
                    event.stateMask |= 0x2000000;
                    break;
                }
                break;
            }
            case 1:
            case 31: {
                if (event.keyCode == 65536) {
                    event.stateMask &= 0xFFFEFFFF;
                }
                if (event.keyCode == 131072) {
                    event.stateMask &= 0xFFFDFFFF;
                }
                if (event.keyCode == 262144) {
                    event.stateMask &= 0xFFFBFFFF;
                    break;
                }
                break;
            }
            case 2: {
                if (event.keyCode == 65536) {
                    event.stateMask |= 0x10000;
                }
                if (event.keyCode == 131072) {
                    event.stateMask |= 0x20000;
                }
                if (event.keyCode == 262144) {
                    event.stateMask |= 0x40000;
                    break;
                }
                break;
            }
        }
        return true;
    }
    
    boolean setKeyState(final Event event, final int n, final int n2, final int n3) {
        switch (this.display.lastAscii) {
            case 127: {
                if (this.display.lastKey == 8) {
                    this.display.lastAscii = 8;
                    break;
                }
                break;
            }
            case 10: {
                if (this.display.lastKey == 13) {
                    this.display.lastAscii = 13;
                    break;
                }
                break;
            }
        }
        if (this.display.lastKey == 13 && this.display.lastAscii == 13 && (n3 & 0x1000000) != 0x0) {
            this.display.lastKey = 16777296;
        }
        this.setLocationMask(event, n, n2, n3);
        if (this.display.lastVirtual) {
            if (this.display.lastKey == 46) {
                this.display.lastAscii = 127;
            }
            if (this.display.lastKey == 3) {
                this.display.lastAscii = 0;
            }
            event.keyCode = Display.translateKey(this.display.lastKey);
        }
        else {
            event.keyCode = this.display.lastKey;
        }
        if (this.display.lastAscii != 0 || this.display.lastNull) {
            event.character = Display.mbcsToWcs((char)this.display.lastAscii);
        }
        return (event.keyCode != 0 || event.character != '\0' || this.display.lastNull) && this.setInputState(event, n);
    }
    
    int setLocationMask(final Event event, final int n, final int n2, final int n3) {
        int keyLocation = 0;
        if (this.display.lastVirtual) {
            switch (this.display.lastKey) {
                case 16: {
                    if (OS.GetKeyState(160) < 0) {
                        keyLocation = 16384;
                    }
                    if (OS.GetKeyState(161) < 0) {
                        keyLocation = 131072;
                        break;
                    }
                    break;
                }
                case 144: {
                    keyLocation = 2;
                    break;
                }
                case 17:
                case 18: {
                    keyLocation = (((n3 & 0x1000000) == 0x0) ? 16384 : 131072);
                    break;
                }
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 45:
                case 46: {
                    if ((n3 & 0x1000000) == 0x0) {
                        keyLocation = 2;
                        break;
                    }
                    break;
                }
            }
            if (this.display.numpadKey(this.display.lastKey) != 0) {
                keyLocation = 2;
            }
        }
        else if (this.display.lastKey == 16777296) {
            keyLocation = 2;
        }
        return event.keyLocation = keyLocation;
    }
    
    boolean setTabGroupFocus() {
        return this.setTabItemFocus();
    }
    
    boolean setTabItemFocus() {
        return false;
    }
    
    boolean SetWindowPos(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, int n7) {
        if (OS.IsWinCE && (n7 & 0x1) == 0x0) {
            final RECT rect = new RECT();
            OS.GetWindowRect(n, rect);
            if (n6 == rect.bottom - rect.top && n5 == rect.right - rect.left) {
                n7 &= 0xFFFFFFDF;
                n7 |= 0x1;
            }
        }
        return OS.SetWindowPos(n, n2, n3, n4, n5, n6, n7);
    }
    
    boolean showMenu(final int x, final int y) {
        final Event event = new Event();
        event.x = x;
        event.y = y;
        this.sendEvent(35, event);
        if (this.isDisposed()) {
            return false;
        }
        if (!event.doit) {
            return true;
        }
        final Menu menu = this.getMenu();
        if (menu != null && !menu.isDisposed()) {
            if (x != event.x || y != event.y) {
                menu.setLocation(event.x, event.y);
            }
            menu.setVisible(true);
            return true;
        }
        return false;
    }
    
    public String toString() {
        String nameText = "*Disposed*";
        if (!this.isDisposed()) {
            nameText = "*Wrong Thread*";
            if (this.isValidThread()) {
                nameText = this.getNameText();
            }
        }
        return String.valueOf(this.getName()) + " {" + nameText + "}";
    }
    
    LRESULT wmCaptureChanged(final int n, final int n2, final int n3) {
        this.display.captureChanged = true;
        return null;
    }
    
    LRESULT wmChar(final int n, final int lastAscii, final int n2) {
        if (!OS.IsUnicode && OS.IsDBLocale && OS.IsDBCSLeadByte((byte)(lastAscii & 0xFF))) {
            return null;
        }
        this.display.lastAscii = lastAscii;
        this.display.lastNull = (lastAscii == 0);
        if (!this.sendKeyEvent(1, 258, lastAscii, n2)) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmContextMenu(final int n, final int n2, final int n3) {
        if (n2 != n) {
            return null;
        }
        if (OS.IsWinCE) {
            return null;
        }
        int n4;
        int n5;
        if (n3 != -1) {
            final POINT point = new POINT();
            OS.POINTSTOPOINT(point, n3);
            n4 = point.x;
            n5 = point.y;
            OS.ScreenToClient(n, point);
            final RECT rect = new RECT();
            OS.GetClientRect(n, rect);
            if (!OS.PtInRect(rect, point)) {
                return null;
            }
        }
        else {
            final int getMessagePos = OS.GetMessagePos();
            n4 = OS.GET_X_LPARAM(getMessagePos);
            n5 = OS.GET_Y_LPARAM(getMessagePos);
        }
        return this.showMenu(n4, n5) ? LRESULT.ZERO : null;
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
            return LRESULT.ONE;
        }
        this.sendKeyEvent(2, 646, lastAscii, n2);
        final Display display5 = display;
        final Display display6 = display;
        final boolean b = false;
        display6.lastAscii = (b ? 1 : 0);
        display5.lastKey = (b ? 1 : 0);
        return LRESULT.ONE;
    }
    
    LRESULT wmKeyDown(final int n, final int lastKey, final int n2) {
        switch (lastKey) {
            case 16:
            case 17:
            case 18:
            case 20:
            case 144:
            case 145: {
                if ((n2 & 0x40000000) != 0x0) {
                    return null;
                }
                break;
            }
        }
        final Display display = this.display;
        final Display display2 = this.display;
        final boolean b = false;
        display2.lastKey = (b ? 1 : 0);
        display.lastAscii = (b ? 1 : 0);
        final Display display3 = this.display;
        final Display display4 = this.display;
        final Display display5 = this.display;
        final boolean lastVirtual = false;
        display5.lastDead = lastVirtual;
        display4.lastNull = lastVirtual;
        display3.lastVirtual = lastVirtual;
        if (!OS.IsUnicode && OS.IsDBLocale && OS.IsDBCSLeadByte((byte)(lastKey & 0xFF))) {
            return null;
        }
        int mapVirtualKey = 0;
        if (OS.IsWinCE) {
            switch (lastKey) {
                case 8: {
                    mapVirtualKey = 8;
                    break;
                }
                case 13: {
                    mapVirtualKey = 13;
                    break;
                }
                case 46: {
                    mapVirtualKey = 127;
                    break;
                }
                case 27: {
                    mapVirtualKey = 27;
                    break;
                }
                case 9: {
                    mapVirtualKey = 9;
                    break;
                }
            }
        }
        else {
            mapVirtualKey = OS.MapVirtualKey(lastKey, 2);
            if ((2534 <= mapVirtualKey && mapVirtualKey <= 2543) || (2406 <= mapVirtualKey && mapVirtualKey <= 2415)) {
                mapVirtualKey = lastKey;
            }
        }
        if (OS.IsWinNT) {
            if ((mapVirtualKey & Integer.MIN_VALUE) != 0x0) {
                return null;
            }
        }
        else if ((mapVirtualKey & 0x8000) != 0x0) {
            return null;
        }
        if (OS.PeekMessage(new MSG(), n, 259, 259, 10420226)) {
            this.display.lastDead = true;
            this.display.lastVirtual = (mapVirtualKey == 0);
            this.display.lastKey = (this.display.lastVirtual ? lastKey : mapVirtualKey);
            return null;
        }
        if (this.isDisposed()) {
            return LRESULT.ONE;
        }
        this.display.lastVirtual = (mapVirtualKey == 0 || this.display.numpadKey(lastKey) != 0);
        if (this.display.lastVirtual) {
            this.display.lastKey = lastKey;
            if (this.display.lastKey == 46) {
                this.display.lastAscii = 127;
            }
            if (96 <= this.display.lastKey && this.display.lastKey <= 111) {
                if (this.display.asciiKey(this.display.lastKey) != 0) {
                    return null;
                }
                this.display.lastAscii = this.display.numpadKey(this.display.lastKey);
            }
        }
        else {
            this.display.lastKey = OS.CharLower((short)mapVirtualKey);
            if (lastKey == 3) {
                this.display.lastVirtual = true;
            }
            final int asciiKey = this.display.asciiKey(lastKey);
            if (asciiKey != 0) {
                if (asciiKey == 32) {
                    return null;
                }
                if (asciiKey != lastKey) {
                    return null;
                }
                if (lastKey == 3) {
                    return null;
                }
            }
            if (OS.GetKeyState(17) >= 0) {
                return null;
            }
            if (OS.GetKeyState(16) < 0) {
                this.display.lastAscii = this.display.shiftedKey(lastKey);
                if (this.display.lastAscii == 0) {
                    this.display.lastAscii = mapVirtualKey;
                }
            }
            else {
                this.display.lastAscii = OS.CharLower((short)mapVirtualKey);
            }
            if (this.display.lastAscii == 64) {
                return null;
            }
            this.display.lastAscii = this.display.controlKey(this.display.lastAscii);
        }
        if (!this.sendKeyEvent(1, 256, lastKey, n2)) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmKeyUp(final int n, final int lastKey, final int n2) {
        final Display display = this.display;
        if (OS.IsWinCE && 193 <= lastKey && lastKey <= 198) {
            final Display display2 = display;
            final Display display3 = display;
            final boolean b = false;
            display3.lastAscii = (b ? 1 : 0);
            display2.lastKey = (b ? 1 : 0);
            final Display display4 = display;
            final Display display5 = display;
            final Display display6 = display;
            final boolean lastVirtual = false;
            display6.lastDead = lastVirtual;
            display5.lastNull = lastVirtual;
            display4.lastVirtual = lastVirtual;
            final Event event = new Event();
            event.detail = lastKey - 193 + 1;
            final int n3 = ((n2 & 0x40000000) != 0x0) ? 34 : 33;
            if (this.setInputState(event, n3)) {
                this.sendEvent(n3, event);
            }
            return null;
        }
        if (!this.hooks(2) && !display.filters(2)) {
            final Display display7 = display;
            final Display display8 = display;
            final boolean b2 = false;
            display8.lastAscii = (b2 ? 1 : 0);
            display7.lastKey = (b2 ? 1 : 0);
            final Display display9 = display;
            final Display display10 = display;
            final Display display11 = display;
            final boolean lastVirtual2 = false;
            display11.lastDead = lastVirtual2;
            display10.lastNull = lastVirtual2;
            display9.lastVirtual = lastVirtual2;
            return null;
        }
        int mapVirtualKey = 0;
        if (OS.IsWinCE) {
            switch (lastKey) {
                case 8: {
                    mapVirtualKey = 8;
                    break;
                }
                case 13: {
                    mapVirtualKey = 13;
                    break;
                }
                case 46: {
                    mapVirtualKey = 127;
                    break;
                }
                case 27: {
                    mapVirtualKey = 27;
                    break;
                }
                case 9: {
                    mapVirtualKey = 9;
                    break;
                }
            }
        }
        else {
            mapVirtualKey = OS.MapVirtualKey(lastKey, 2);
        }
        if (OS.IsWinNT) {
            if ((mapVirtualKey & Integer.MIN_VALUE) != 0x0) {
                return null;
            }
        }
        else if ((mapVirtualKey & 0x8000) != 0x0) {
            return null;
        }
        if (display.lastDead) {
            return null;
        }
        display.lastVirtual = (mapVirtualKey == 0 || display.numpadKey(lastKey) != 0);
        if (display.lastVirtual) {
            display.lastKey = lastKey;
        }
        else {
            if (lastKey == 3) {
                display.lastVirtual = true;
            }
            if (display.lastKey == 0) {
                display.lastAscii = 0;
                final Display display12 = display;
                final Display display13 = display;
                final boolean b3 = false;
                display13.lastDead = b3;
                display12.lastNull = b3;
                return null;
            }
        }
        LRESULT one = null;
        if (!this.sendKeyEvent(2, 257, lastKey, n2)) {
            one = LRESULT.ONE;
        }
        final Display display14 = display;
        final Display display15 = display;
        final boolean b4 = false;
        display15.lastAscii = (b4 ? 1 : 0);
        display14.lastKey = (b4 ? 1 : 0);
        final Display display16 = display;
        final Display display17 = display;
        final Display display18 = display;
        final boolean lastVirtual3 = false;
        display18.lastDead = lastVirtual3;
        display17.lastNull = lastVirtual3;
        display16.lastVirtual = lastVirtual3;
        return one;
    }
    
    LRESULT wmKillFocus(final int n, final int n2, final int n3) {
        final Display display = this.display;
        final Display display2 = this.display;
        final boolean b = false;
        display2.scrollHRemainder = (b ? 1 : 0);
        display.scrollRemainder = (b ? 1 : 0);
        final int callWindowProc = this.callWindowProc(n, 8, n2, n3);
        this.sendFocusEvent(16);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (callWindowProc == 0) {
            return LRESULT.ZERO;
        }
        return new LRESULT(callWindowProc);
    }
    
    LRESULT wmLButtonDblClk(final int n, final int n2, final int n3) {
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 1, n, 513, n2, n3);
        LRESULT zero;
        if (this.sendMouseEvent(8, 1, n, 515, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 515, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != n) {
            OS.SetCapture(n);
        }
        return zero;
    }
    
    LRESULT wmLButtonDown(final int hwndClient, final int n, final int n2) {
        final Display display = this.display;
        final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
        final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
        boolean[] array = null;
        boolean[] array2 = null;
        boolean dragDetect = false;
        boolean b = true;
        final int clickCount = display.getClickCount(3, 1, hwndClient, n2);
        if (clickCount == 1 && (this.state & 0x8000) != 0x0 && this.hooks(29) && !OS.IsWinCE) {
            array2 = new boolean[] { false };
            array = new boolean[] { false };
            dragDetect = this.dragDetect(hwndClient, get_X_LPARAM, get_Y_LPARAM, true, array2, array);
            if (this.isDisposed()) {
                return LRESULT.ZERO;
            }
            b = (OS.GetKeyState(1) < 0);
        }
        display.captureChanged = false;
        LRESULT zero;
        if (this.sendMouseEvent(3, 1, clickCount, 0, false, hwndClient, 513, n, n2) && (array == null || !array[0])) {
            zero = new LRESULT(this.callWindowProc(hwndClient, 513, n, n2));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (OS.IsPPC) {
            final Menu menu = this.getMenu();
            if ((menu != null && !menu.isDisposed()) || this.hooks(35)) {
                final SHRGINFO shrginfo = new SHRGINFO();
                shrginfo.cbSize = SHRGINFO.sizeof;
                shrginfo.hwndClient = hwndClient;
                shrginfo.ptDown_x = get_X_LPARAM;
                shrginfo.ptDown_y = get_Y_LPARAM;
                shrginfo.dwFlags = 1;
                if (OS.SHRecognizeGesture(shrginfo) == 1000) {
                    this.showMenu(get_X_LPARAM, get_Y_LPARAM);
                }
            }
        }
        if (b && !display.captureChanged && !this.isDisposed() && OS.GetCapture() != hwndClient) {
            OS.SetCapture(hwndClient);
        }
        if (dragDetect) {
            this.sendDragEvent(1, get_X_LPARAM, get_Y_LPARAM);
        }
        else if (array2 != null && array2[0] && OS.GetKeyState(27) >= 0) {
            OS.SendMessage(hwndClient, 514, n, n2);
        }
        return zero;
    }
    
    LRESULT wmLButtonUp(final int n, final int n2, final int n3) {
        final Display display = this.display;
        LRESULT zero;
        if (this.sendMouseEvent(4, 1, n, 514, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 514, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        int n4 = 19;
        if (display.xMouse) {
            n4 |= 0x60;
        }
        if ((n2 & n4) == 0x0 && OS.GetCapture() == n) {
            OS.ReleaseCapture();
        }
        return zero;
    }
    
    LRESULT wmMButtonDblClk(final int n, final int n2, final int n3) {
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 2, n, 519, n2, n3);
        LRESULT zero;
        if (this.sendMouseEvent(8, 2, n, 521, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 521, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != n) {
            OS.SetCapture(n);
        }
        return zero;
    }
    
    LRESULT wmMButtonDown(final int n, final int n2, final int n3) {
        final Display display = this.display;
        display.captureChanged = false;
        LRESULT zero;
        if (this.sendMouseEvent(3, 2, n, 519, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 519, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != n) {
            OS.SetCapture(n);
        }
        return zero;
    }
    
    LRESULT wmMButtonUp(final int n, final int n2, final int n3) {
        final Display display = this.display;
        LRESULT zero;
        if (this.sendMouseEvent(4, 2, n, 520, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 520, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        int n4 = 19;
        if (display.xMouse) {
            n4 |= 0x60;
        }
        if ((n2 & n4) == 0x0 && OS.GetCapture() == n) {
            OS.ReleaseCapture();
        }
        return zero;
    }
    
    LRESULT wmMouseHover(final int n, final int n2, final int n3) {
        if (!this.sendMouseEvent(32, 0, n, 673, n2, n3)) {
            return LRESULT.ZERO;
        }
        return null;
    }
    
    LRESULT wmMouseLeave(final int n, final int n2, int makelparam) {
        if (!this.hooks(7) && !this.filters(7)) {
            return null;
        }
        final int getMessagePos = OS.GetMessagePos();
        final POINT point = new POINT();
        OS.POINTSTOPOINT(point, getMessagePos);
        OS.ScreenToClient(n, point);
        makelparam = OS.MAKELPARAM(point.x, point.y);
        if (!this.sendMouseEvent(7, 0, n, 675, n2, makelparam)) {
            return LRESULT.ZERO;
        }
        return null;
    }
    
    LRESULT wmMouseMove(final int n, final int n2, final int n3) {
        LRESULT zero = null;
        final Display display = this.display;
        final int getMessagePos = OS.GetMessagePos();
        if (getMessagePos != display.lastMouse || display.captureChanged) {
            if (!OS.IsWinCE) {
                final boolean b = (this.state & 0x2000) != 0x0;
                final boolean b2 = this.hooks(6) || display.filters(6);
                final boolean b3 = this.hooks(7) || display.filters(7);
                final boolean b4 = this.hooks(32) || display.filters(32);
                if (b || b2 || b3 || b4) {
                    final TRACKMOUSEEVENT trackmouseevent = new TRACKMOUSEEVENT();
                    trackmouseevent.cbSize = TRACKMOUSEEVENT.sizeof;
                    trackmouseevent.dwFlags = 1073741824;
                    trackmouseevent.hwndTrack = n;
                    OS.TrackMouseEvent(trackmouseevent);
                    if (trackmouseevent.dwFlags == 0) {
                        trackmouseevent.dwFlags = 3;
                        trackmouseevent.hwndTrack = n;
                        OS.TrackMouseEvent(trackmouseevent);
                        if (b2) {
                            final MSG msg = new MSG();
                            while (OS.PeekMessage(msg, 0, 675, 675, 10420227)) {
                                OS.TranslateMessage(msg);
                                OS.DispatchMessage(msg);
                            }
                            this.sendMouseEvent(6, 0, n, 512, n2, n3);
                        }
                    }
                    else {
                        trackmouseevent.dwFlags = 1;
                        OS.TrackMouseEvent(trackmouseevent);
                    }
                }
            }
            if (getMessagePos != display.lastMouse) {
                display.lastMouse = getMessagePos;
                if (!this.sendMouseEvent(5, 0, n, 512, n2, n3)) {
                    zero = LRESULT.ZERO;
                }
            }
        }
        display.captureChanged = false;
        return zero;
    }
    
    LRESULT wmMouseWheel(final int n, final int n2, final int n3) {
        return this.sendMouseWheelEvent(37, n, n2, n3) ? null : LRESULT.ZERO;
    }
    
    LRESULT wmMouseHWheel(final int n, final int n2, final int n3) {
        return this.sendMouseWheelEvent(38, n, n2, n3) ? null : LRESULT.ZERO;
    }
    
    LRESULT wmNCPaint(final int n, final int n2, final int n3) {
        return null;
    }
    
    LRESULT wmPaint(final int n, final int n2, final int n3) {
        if (!this.hooks(9) && !this.filters(9)) {
            return null;
        }
        int n4;
        if (OS.IsWinCE) {
            final RECT rect = new RECT();
            OS.GetUpdateRect(n, rect, false);
            n4 = this.callWindowProc(n, 15, n2, n3);
            OS.HideCaret(n);
            OS.InvalidateRect(n, rect, false);
            OS.ShowCaret(n);
            final PAINTSTRUCT ps = new PAINTSTRUCT();
            final GCData gcData = new GCData();
            gcData.ps = ps;
            gcData.hwnd = n;
            final GC new_GC = this.new_GC(gcData);
            if (new_GC != null) {
                final int width = ps.right - ps.left;
                final int height = ps.bottom - ps.top;
                if (width != 0 && height != 0) {
                    final Event event = new Event();
                    event.gc = new_GC;
                    event.x = ps.left;
                    event.y = ps.top;
                    event.width = width;
                    event.height = height;
                    this.sendEvent(9, event);
                    event.gc = null;
                }
                new_GC.dispose();
            }
        }
        else {
            final int createRectRgn = OS.CreateRectRgn(0, 0, 0, 0);
            OS.GetUpdateRgn(n, createRectRgn, false);
            n4 = this.callWindowProc(n, 15, n2, n3);
            final GCData gcData2 = new GCData();
            gcData2.hwnd = n;
            final GC new_GC2 = this.new_GC(gcData2);
            if (new_GC2 != null) {
                OS.HideCaret(n);
                final RECT rect2 = new RECT();
                OS.GetRgnBox(createRectRgn, rect2);
                final int width2 = rect2.right - rect2.left;
                final int height2 = rect2.bottom - rect2.top;
                if (width2 != 0 && height2 != 0) {
                    final int handle = new_GC2.handle;
                    OS.SelectClipRgn(handle, createRectRgn);
                    OS.SetMetaRgn(handle);
                    final Event event2 = new Event();
                    event2.gc = new_GC2;
                    event2.x = rect2.left;
                    event2.y = rect2.top;
                    event2.width = width2;
                    event2.height = height2;
                    this.sendEvent(9, event2);
                    event2.gc = null;
                }
                new_GC2.dispose();
                OS.ShowCaret(n);
            }
            OS.DeleteObject(createRectRgn);
        }
        if (n4 == 0) {
            return LRESULT.ZERO;
        }
        return new LRESULT(n4);
    }
    
    LRESULT wmPrint(final int n, final int n2, final int n3) {
        if ((n3 & 0x2) != 0x0 && OS.COMCTL32_MAJOR >= 6 && OS.IsAppThemed() && (OS.GetWindowLong(n, -20) & 0x200) != 0x0) {
            final int callWindowProc = this.callWindowProc(n, 791, n2, n3);
            final RECT rect = new RECT();
            OS.GetWindowRect(n, rect);
            final RECT rect2 = rect;
            rect2.right -= rect.left;
            final RECT rect3 = rect;
            rect3.bottom -= rect.top;
            final RECT rect4 = rect;
            final RECT rect5 = rect;
            final boolean b = false;
            rect5.top = (b ? 1 : 0);
            rect4.left = (b ? 1 : 0);
            final int getSystemMetrics = OS.GetSystemMetrics(45);
            OS.ExcludeClipRect(n2, getSystemMetrics, getSystemMetrics, rect.right - getSystemMetrics, rect.bottom - getSystemMetrics);
            OS.DrawThemeBackground(this.display.hEditTheme(), n2, 1, 1, rect, null);
            return new LRESULT(callWindowProc);
        }
        return null;
    }
    
    LRESULT wmRButtonDblClk(final int n, final int n2, final int n3) {
        final Display display = this.display;
        display.captureChanged = false;
        this.sendMouseEvent(3, 3, n, 516, n2, n3);
        LRESULT zero;
        if (this.sendMouseEvent(8, 3, n, 518, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 518, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != n) {
            OS.SetCapture(n);
        }
        return zero;
    }
    
    LRESULT wmRButtonDown(final int n, final int n2, final int n3) {
        final Display display = this.display;
        display.captureChanged = false;
        LRESULT zero;
        if (this.sendMouseEvent(3, 3, n, 516, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 516, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != n) {
            OS.SetCapture(n);
        }
        return zero;
    }
    
    LRESULT wmRButtonUp(final int n, final int n2, final int n3) {
        final Display display = this.display;
        LRESULT zero;
        if (this.sendMouseEvent(4, 3, n, 517, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 517, n2, n3));
        }
        else {
            OS.DefWindowProc(n, 517, n2, n3);
            zero = LRESULT.ZERO;
        }
        int n4 = 19;
        if (display.xMouse) {
            n4 |= 0x60;
        }
        if ((n2 & n4) == 0x0 && OS.GetCapture() == n) {
            OS.ReleaseCapture();
        }
        return zero;
    }
    
    LRESULT wmSetFocus(final int n, final int n2, final int n3) {
        final int callWindowProc = this.callWindowProc(n, 7, n2, n3);
        this.sendFocusEvent(15);
        if (this.isDisposed()) {
            return LRESULT.ZERO;
        }
        if (callWindowProc == 0) {
            return LRESULT.ZERO;
        }
        return new LRESULT(callWindowProc);
    }
    
    LRESULT wmSysChar(final int n, final int lastAscii, final int n2) {
        final Display display = this.display;
        display.lastAscii = lastAscii;
        display.lastNull = (lastAscii == 0);
        if (!this.hooks(1) && !display.filters(1)) {
            return null;
        }
        final boolean mnemonicKeyHit = display.mnemonicKeyHit;
        display.mnemonicKeyHit = true;
        final int callWindowProc = this.callWindowProc(n, 262, lastAscii, n2);
        boolean b = false;
        if (!display.mnemonicKeyHit) {
            b = !this.sendKeyEvent(1, 262, lastAscii, n2);
        }
        final boolean b2 = b | display.mnemonicKeyHit;
        display.mnemonicKeyHit = mnemonicKeyHit;
        return b2 ? LRESULT.ONE : new LRESULT(callWindowProc);
    }
    
    LRESULT wmSysKeyDown(final int n, final int lastKey, final int n2) {
        if (lastKey != 121 && (n2 & 0x20000000) == 0x0) {
            return null;
        }
        switch (lastKey) {
            case 115: {
                int getParent;
                for (getParent = n; OS.GetParent(getParent) != 0 && OS.GetWindow(getParent, 4) == 0; getParent = OS.GetParent(getParent)) {}
                if ((OS.GetWindowLong(getParent, -16) & 0x80000) != 0x0) {
                    return null;
                }
                break;
            }
        }
        switch (lastKey) {
            case 16:
            case 17:
            case 18:
            case 20:
            case 144:
            case 145: {
                if ((n2 & 0x40000000) != 0x0) {
                    return null;
                }
                break;
            }
        }
        final Display display = this.display;
        final Display display2 = this.display;
        final boolean b = false;
        display2.lastKey = (b ? 1 : 0);
        display.lastAscii = (b ? 1 : 0);
        final Display display3 = this.display;
        final Display display4 = this.display;
        final Display display5 = this.display;
        final boolean lastVirtual = false;
        display5.lastDead = lastVirtual;
        display4.lastNull = lastVirtual;
        display3.lastVirtual = lastVirtual;
        int mapVirtualKey = 0;
        if (OS.IsWinCE) {
            switch (lastKey) {
                case 8: {
                    mapVirtualKey = 8;
                    break;
                }
                case 13: {
                    mapVirtualKey = 13;
                    break;
                }
                case 46: {
                    mapVirtualKey = 127;
                    break;
                }
                case 27: {
                    mapVirtualKey = 27;
                    break;
                }
                case 9: {
                    mapVirtualKey = 9;
                    break;
                }
            }
        }
        else {
            mapVirtualKey = OS.MapVirtualKey(lastKey, 2);
        }
        this.display.lastVirtual = (mapVirtualKey == 0 || this.display.numpadKey(lastKey) != 0);
        if (this.display.lastVirtual) {
            this.display.lastKey = lastKey;
            if (this.display.lastKey == 46) {
                this.display.lastAscii = 127;
            }
            if (96 <= this.display.lastKey && this.display.lastKey <= 111) {
                switch (this.display.lastKey) {
                    case 106:
                    case 107:
                    case 109:
                    case 110:
                    case 111: {
                        return null;
                    }
                    default: {
                        this.display.lastAscii = this.display.numpadKey(this.display.lastKey);
                        break;
                    }
                }
            }
        }
        else {
            this.display.lastKey = OS.CharLower((short)mapVirtualKey);
            if (OS.IsWinNT) {
                return null;
            }
            if (lastKey != 13) {
                return null;
            }
            this.display.lastAscii = 13;
        }
        if (!this.sendKeyEvent(1, 260, lastKey, n2)) {
            return LRESULT.ONE;
        }
        return null;
    }
    
    LRESULT wmSysKeyUp(final int n, final int n2, final int n3) {
        return this.wmKeyUp(n, n2, n3);
    }
    
    LRESULT wmXButtonDblClk(final int n, final int n2, final int n3) {
        final Display display = this.display;
        display.captureChanged = false;
        final int n4 = (OS.HIWORD(n2) == 1) ? 4 : 5;
        this.sendMouseEvent(3, n4, n, 523, n2, n3);
        LRESULT zero;
        if (this.sendMouseEvent(8, n4, n, 525, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 525, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != n) {
            OS.SetCapture(n);
        }
        return zero;
    }
    
    LRESULT wmXButtonDown(final int n, final int n2, final int n3) {
        final Display display = this.display;
        display.captureChanged = false;
        display.xMouse = true;
        LRESULT zero;
        if (this.sendMouseEvent(3, (OS.HIWORD(n2) == 1) ? 4 : 5, n, 523, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 523, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        if (!display.captureChanged && !this.isDisposed() && OS.GetCapture() != n) {
            OS.SetCapture(n);
        }
        return zero;
    }
    
    LRESULT wmXButtonUp(final int n, final int n2, final int n3) {
        final Display display = this.display;
        LRESULT zero;
        if (this.sendMouseEvent(4, (OS.HIWORD(n2) == 1) ? 4 : 5, n, 524, n2, n3)) {
            zero = new LRESULT(this.callWindowProc(n, 524, n2, n3));
        }
        else {
            zero = LRESULT.ZERO;
        }
        int n4 = 19;
        if (display.xMouse) {
            n4 |= 0x60;
        }
        if ((n2 & n4) == 0x0 && OS.GetCapture() == n) {
            OS.ReleaseCapture();
        }
        return zero;
    }
}
