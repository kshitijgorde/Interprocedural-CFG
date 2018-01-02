// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.LRESULT;
import org.eclipse.swt.internal.win32.PAINTSTRUCT;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;

public class Tracker extends Widget
{
    Control parent;
    boolean tracking;
    boolean cancelled;
    boolean stippled;
    Rectangle[] rectangles;
    Rectangle[] proportions;
    Rectangle bounds;
    int resizeCursor;
    Cursor clientCursor;
    int cursorOrientation;
    boolean inEvent;
    int hwndTransparent;
    int hwndOpaque;
    int oldTransparentProc;
    int oldOpaqueProc;
    int oldX;
    int oldY;
    static boolean IsVista;
    static final int STEPSIZE_SMALL = 1;
    static final int STEPSIZE_LARGE = 9;
    
    static {
        Tracker.IsVista = (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0));
    }
    
    public Tracker(final Composite parent, final int n) {
        super(parent, checkStyle(n));
        this.rectangles = new Rectangle[0];
        this.proportions = this.rectangles;
        this.cursorOrientation = 0;
        this.inEvent = false;
        this.parent = parent;
    }
    
    public Tracker(Display display, final int n) {
        this.rectangles = new Rectangle[0];
        this.proportions = this.rectangles;
        this.cursorOrientation = 0;
        this.inEvent = false;
        if (display == null) {
            display = Display.getCurrent();
        }
        if (display == null) {
            display = Display.getDefault();
        }
        if (!display.isValidThread()) {
            this.error(22);
        }
        this.style = checkStyle(n);
        this.display = display;
        this.reskinWidget();
    }
    
    public void addControlListener(final ControlListener controlListener) {
        this.checkWidget();
        if (controlListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(controlListener);
        this.addListener(11, typedListener);
        this.addListener(10, typedListener);
    }
    
    public void addKeyListener(final KeyListener keyListener) {
        this.checkWidget();
        if (keyListener == null) {
            this.error(4);
        }
        final TypedListener typedListener = new TypedListener(keyListener);
        this.addListener(2, typedListener);
        this.addListener(1, typedListener);
    }
    
    Point adjustMoveCursor() {
        if (this.bounds == null) {
            return null;
        }
        final int x = this.bounds.x + this.bounds.width / 2;
        final int y = this.bounds.y;
        final POINT point = new POINT();
        point.x = x;
        point.y = y;
        if (this.parent != null) {
            OS.ClientToScreen(this.parent.handle, point);
        }
        OS.SetCursorPos(point.x, point.y);
        return new Point(point.x, point.y);
    }
    
    Point adjustResizeCursor() {
        if (this.bounds == null) {
            return null;
        }
        int x;
        if ((this.cursorOrientation & 0x4000) != 0x0) {
            x = this.bounds.x;
        }
        else if ((this.cursorOrientation & 0x20000) != 0x0) {
            x = this.bounds.x + this.bounds.width;
        }
        else {
            x = this.bounds.x + this.bounds.width / 2;
        }
        int y;
        if ((this.cursorOrientation & 0x80) != 0x0) {
            y = this.bounds.y;
        }
        else if ((this.cursorOrientation & 0x400) != 0x0) {
            y = this.bounds.y + this.bounds.height;
        }
        else {
            y = this.bounds.y + this.bounds.height / 2;
        }
        final POINT point = new POINT();
        point.x = x;
        point.y = y;
        if (this.parent != null) {
            OS.ClientToScreen(this.parent.handle, point);
        }
        OS.SetCursorPos(point.x, point.y);
        if (this.clientCursor == null) {
            int resizeCursor = 0;
            switch (this.cursorOrientation) {
                case 128: {
                    resizeCursor = OS.LoadCursor(0, 32645);
                    break;
                }
                case 1024: {
                    resizeCursor = OS.LoadCursor(0, 32645);
                    break;
                }
                case 16384: {
                    resizeCursor = OS.LoadCursor(0, 32644);
                    break;
                }
                case 131072: {
                    resizeCursor = OS.LoadCursor(0, 32644);
                    break;
                }
                case 16512: {
                    resizeCursor = OS.LoadCursor(0, 32642);
                    break;
                }
                case 132096: {
                    resizeCursor = OS.LoadCursor(0, 32642);
                    break;
                }
                case 17408: {
                    resizeCursor = OS.LoadCursor(0, 32643);
                    break;
                }
                case 131200: {
                    resizeCursor = OS.LoadCursor(0, 32643);
                    break;
                }
                default: {
                    resizeCursor = OS.LoadCursor(0, 32646);
                    break;
                }
            }
            OS.SetCursor(resizeCursor);
            if (this.resizeCursor != 0) {
                OS.DestroyCursor(this.resizeCursor);
            }
            this.resizeCursor = resizeCursor;
        }
        return new Point(point.x, point.y);
    }
    
    static int checkStyle(int n) {
        if ((n & 0x24480) == 0x0) {
            n |= 0x24480;
        }
        return n;
    }
    
    public void close() {
        this.checkWidget();
        this.tracking = false;
    }
    
    Rectangle computeBounds() {
        if (this.rectangles.length == 0) {
            return null;
        }
        int n = this.rectangles[0].x;
        int n2 = this.rectangles[0].y;
        int n3 = this.rectangles[0].x + this.rectangles[0].width;
        int n4 = this.rectangles[0].y + this.rectangles[0].height;
        for (int i = 1; i < this.rectangles.length; ++i) {
            if (this.rectangles[i].x < n) {
                n = this.rectangles[i].x;
            }
            if (this.rectangles[i].y < n2) {
                n2 = this.rectangles[i].y;
            }
            final int n5 = this.rectangles[i].x + this.rectangles[i].width;
            if (n5 > n3) {
                n3 = n5;
            }
            final int n6 = this.rectangles[i].y + this.rectangles[i].height;
            if (n6 > n4) {
                n4 = n6;
            }
        }
        return new Rectangle(n, n2, n3 - n, n4 - n2);
    }
    
    Rectangle[] computeProportions(final Rectangle[] array) {
        final Rectangle[] array2 = new Rectangle[array.length];
        this.bounds = this.computeBounds();
        if (this.bounds != null) {
            for (int i = 0; i < array.length; ++i) {
                int n = 0;
                int n2 = 0;
                int n3;
                if (this.bounds.width != 0) {
                    n = (array[i].x - this.bounds.x) * 100 / this.bounds.width;
                    n3 = array[i].width * 100 / this.bounds.width;
                }
                else {
                    n3 = 100;
                }
                int n4;
                if (this.bounds.height != 0) {
                    n2 = (array[i].y - this.bounds.y) * 100 / this.bounds.height;
                    n4 = array[i].height * 100 / this.bounds.height;
                }
                else {
                    n4 = 100;
                }
                array2[i] = new Rectangle(n, n2, n3, n4);
            }
        }
        return array2;
    }
    
    void drawRectangles(final Rectangle[] array, final boolean b) {
        if (this.hwndOpaque != 0) {
            final RECT rect = new RECT();
            final int n = b ? 3 : 1;
            for (int i = 0; i < array.length; ++i) {
                final Rectangle rectangle = array[i];
                rect.left = rectangle.x - n;
                rect.top = rectangle.y - n;
                rect.right = rectangle.x + rectangle.width + n * 2;
                rect.bottom = rectangle.y + rectangle.height + n * 2;
                OS.MapWindowPoints(0, this.hwndOpaque, rect, 2);
                OS.RedrawWindow(this.hwndOpaque, rect, 0, 1);
            }
            return;
        }
        int n2 = 1;
        final int n3 = (this.parent == null) ? OS.GetDesktopWindow() : this.parent.handle;
        final int getDCEx = OS.GetDCEx(n3, 0, 2);
        int createBitmap = 0;
        int createPatternBrush = 0;
        int selectObject = 0;
        if (b) {
            n2 = 3;
            createBitmap = OS.CreateBitmap(8, 8, 1, 1, new byte[] { -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0 });
            createPatternBrush = OS.CreatePatternBrush(createBitmap);
            selectObject = OS.SelectObject(getDCEx, createPatternBrush);
        }
        for (int j = 0; j < array.length; ++j) {
            final Rectangle rectangle2 = array[j];
            OS.PatBlt(getDCEx, rectangle2.x, rectangle2.y, rectangle2.width, n2, 5898313);
            OS.PatBlt(getDCEx, rectangle2.x, rectangle2.y + n2, n2, rectangle2.height - n2 * 2, 5898313);
            OS.PatBlt(getDCEx, rectangle2.x + rectangle2.width - n2, rectangle2.y + n2, n2, rectangle2.height - n2 * 2, 5898313);
            OS.PatBlt(getDCEx, rectangle2.x, rectangle2.y + rectangle2.height - n2, rectangle2.width, n2, 5898313);
        }
        if (b) {
            OS.SelectObject(getDCEx, selectObject);
            OS.DeleteObject(createPatternBrush);
            OS.DeleteObject(createBitmap);
        }
        OS.ReleaseDC(n3, getDCEx);
    }
    
    public Rectangle[] getRectangles() {
        this.checkWidget();
        final Rectangle[] array = new Rectangle[this.rectangles.length];
        for (int i = 0; i < this.rectangles.length; ++i) {
            final Rectangle rectangle = this.rectangles[i];
            array[i] = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        return array;
    }
    
    public boolean getStippled() {
        this.checkWidget();
        return this.stippled;
    }
    
    void moveRectangles(int n, int n2) {
        if (this.bounds == null) {
            return;
        }
        if (n < 0 && (this.style & 0x4000) == 0x0) {
            n = 0;
        }
        if (n > 0 && (this.style & 0x20000) == 0x0) {
            n = 0;
        }
        if (n2 < 0 && (this.style & 0x80) == 0x0) {
            n2 = 0;
        }
        if (n2 > 0 && (this.style & 0x400) == 0x0) {
            n2 = 0;
        }
        if (n == 0 && n2 == 0) {
            return;
        }
        final Rectangle bounds = this.bounds;
        bounds.x += n;
        final Rectangle bounds2 = this.bounds;
        bounds2.y += n2;
        for (int i = 0; i < this.rectangles.length; ++i) {
            final Rectangle rectangle = this.rectangles[i];
            rectangle.x += n;
            final Rectangle rectangle2 = this.rectangles[i];
            rectangle2.y += n2;
        }
    }
    
    public boolean open() {
        this.checkWidget();
        this.cancelled = false;
        this.tracking = true;
        final int n = this.style & 0x480;
        if (n == 128 || n == 1024) {
            this.cursorOrientation |= n;
        }
        final int n2 = this.style & 0x24000;
        if (n2 == 16384 || n2 == 131072) {
            this.cursorOrientation |= n2;
        }
        Callback callback = null;
        final boolean b = OS.GetKeyState(1) < 0;
        if (Tracker.IsVista && this.parent == null) {
            final Rectangle bounds = this.display.getBounds();
            OS.SetLayeredWindowAttributes(this.hwndTransparent = OS.CreateWindowEx(134742016, this.display.windowClass, null, Integer.MIN_VALUE, bounds.x, bounds.y, bounds.width, bounds.height, 0, 0, OS.GetModuleHandle(null), null), 0, (byte)1, 2);
            OS.SetLayeredWindowAttributes(this.hwndOpaque = OS.CreateWindowEx(134742016, this.display.windowClass, null, Integer.MIN_VALUE, bounds.x, bounds.y, bounds.width, bounds.height, this.hwndTransparent, 0, OS.GetModuleHandle(null), null), 16777215, (byte)(-1), 3);
            callback = new Callback(this, "transparentProc", 4);
            final int address = callback.getAddress();
            if (address == 0) {
                SWT.error(3);
            }
            this.oldTransparentProc = OS.GetWindowLongPtr(this.hwndTransparent, -4);
            OS.SetWindowLongPtr(this.hwndTransparent, -4, address);
            this.oldOpaqueProc = OS.GetWindowLongPtr(this.hwndOpaque, -4);
            OS.SetWindowLongPtr(this.hwndOpaque, -4, address);
            OS.ShowWindow(this.hwndTransparent, 4);
            OS.ShowWindow(this.hwndOpaque, 4);
        }
        else if (!b) {
            final Rectangle bounds2 = this.display.getBounds();
            this.hwndTransparent = OS.CreateWindowEx(32, this.display.windowClass, null, Integer.MIN_VALUE, bounds2.x, bounds2.y, bounds2.width, bounds2.height, 0, 0, OS.GetModuleHandle(null), null);
            callback = new Callback(this, "transparentProc", 4);
            final int address2 = callback.getAddress();
            if (address2 == 0) {
                SWT.error(3);
            }
            this.oldTransparentProc = OS.GetWindowLongPtr(this.hwndTransparent, -4);
            OS.SetWindowLongPtr(this.hwndTransparent, -4, address2);
            OS.ShowWindow(this.hwndTransparent, 4);
        }
        this.update();
        this.drawRectangles(this.rectangles, this.stippled);
        Point point2;
        if (b) {
            final POINT point = new POINT();
            OS.GetCursorPos(point);
            point2 = new Point(point.x, point.y);
        }
        else if ((this.style & 0x10) != 0x0) {
            point2 = this.adjustResizeCursor();
        }
        else {
            point2 = this.adjustMoveCursor();
        }
        if (point2 != null) {
            this.oldX = point2.x;
            this.oldY = point2.y;
        }
        try {
            final MSG msg = new MSG();
            while (this.tracking && !this.cancelled && (this.parent == null || !this.parent.isDisposed())) {
                OS.GetMessage(msg, 0, 0, 0);
                OS.TranslateMessage(msg);
                switch (msg.message) {
                    case 512:
                    case 514: {
                        this.wmMouse(msg.message, msg.wParam, msg.lParam);
                        break;
                    }
                    case 646: {
                        this.wmIMEChar(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 258: {
                        this.wmChar(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 256: {
                        this.wmKeyDown(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 257: {
                        this.wmKeyUp(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 262: {
                        this.wmSysChar(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 260: {
                        this.wmSysKeyDown(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                    case 261: {
                        this.wmSysKeyUp(msg.hwnd, msg.wParam, msg.lParam);
                        break;
                    }
                }
                if (256 <= msg.message && msg.message <= 264) {
                    continue;
                }
                if (512 <= msg.message && msg.message <= 525) {
                    continue;
                }
                if (this.hwndOpaque == 0 && msg.message == 15) {
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                OS.DispatchMessage(msg);
                if (this.hwndOpaque != 0 || msg.message != 15) {
                    continue;
                }
                this.drawRectangles(this.rectangles, this.stippled);
            }
            if (b) {
                OS.ReleaseCapture();
            }
            if (!this.isDisposed()) {
                this.update();
                this.drawRectangles(this.rectangles, this.stippled);
            }
        }
        finally {
            if (this.hwndTransparent != 0) {
                OS.DestroyWindow(this.hwndTransparent);
                this.hwndTransparent = 0;
            }
            this.hwndOpaque = 0;
            if (callback != null) {
                callback.dispose();
                final boolean b2 = false;
                this.oldOpaqueProc = (b2 ? 1 : 0);
                this.oldTransparentProc = (b2 ? 1 : 0);
            }
            if (this.resizeCursor != 0) {
                OS.DestroyCursor(this.resizeCursor);
                this.resizeCursor = 0;
            }
        }
        if (this.hwndTransparent != 0) {
            OS.DestroyWindow(this.hwndTransparent);
            this.hwndTransparent = 0;
        }
        this.hwndOpaque = 0;
        if (callback != null) {
            callback.dispose();
            final boolean b3 = false;
            this.oldOpaqueProc = (b3 ? 1 : 0);
            this.oldTransparentProc = (b3 ? 1 : 0);
        }
        if (this.resizeCursor != 0) {
            OS.DestroyCursor(this.resizeCursor);
            this.resizeCursor = 0;
        }
        this.tracking = false;
        return !this.cancelled;
    }
    
    void releaseWidget() {
        super.releaseWidget();
        this.parent = null;
        final Rectangle[] array = null;
        this.proportions = array;
        this.rectangles = array;
        this.bounds = null;
    }
    
    public void removeControlListener(final ControlListener controlListener) {
        this.checkWidget();
        if (controlListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(11, controlListener);
        this.eventTable.unhook(10, controlListener);
    }
    
    public void removeKeyListener(final KeyListener keyListener) {
        this.checkWidget();
        if (keyListener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(2, keyListener);
        this.eventTable.unhook(1, keyListener);
    }
    
    void resizeRectangles(int n, int n2) {
        if (this.bounds == null) {
            return;
        }
        if (n < 0 && (this.style & 0x4000) != 0x0 && (this.cursorOrientation & 0x20000) == 0x0) {
            this.cursorOrientation |= 0x4000;
        }
        if (n > 0 && (this.style & 0x20000) != 0x0 && (this.cursorOrientation & 0x4000) == 0x0) {
            this.cursorOrientation |= 0x20000;
        }
        if (n2 < 0 && (this.style & 0x80) != 0x0 && (this.cursorOrientation & 0x400) == 0x0) {
            this.cursorOrientation |= 0x80;
        }
        if (n2 > 0 && (this.style & 0x400) != 0x0 && (this.cursorOrientation & 0x80) == 0x0) {
            this.cursorOrientation |= 0x400;
        }
        if ((this.cursorOrientation & 0x4000) != 0x0) {
            if (n > this.bounds.width) {
                if ((this.style & 0x20000) == 0x0) {
                    return;
                }
                this.cursorOrientation |= 0x20000;
                this.cursorOrientation &= 0xFFFFBFFF;
                final Rectangle bounds = this.bounds;
                bounds.x += this.bounds.width;
                n -= this.bounds.width;
                this.bounds.width = 0;
                if (this.proportions.length > 1) {
                    for (int i = 0; i < this.proportions.length; ++i) {
                        final Rectangle rectangle = this.proportions[i];
                        rectangle.x = 100 - rectangle.x - rectangle.width;
                    }
                }
            }
        }
        else if ((this.cursorOrientation & 0x20000) != 0x0 && this.bounds.width < -n) {
            if ((this.style & 0x4000) == 0x0) {
                return;
            }
            this.cursorOrientation |= 0x4000;
            this.cursorOrientation &= 0xFFFDFFFF;
            n += this.bounds.width;
            this.bounds.width = 0;
            if (this.proportions.length > 1) {
                for (int j = 0; j < this.proportions.length; ++j) {
                    final Rectangle rectangle2 = this.proportions[j];
                    rectangle2.x = 100 - rectangle2.x - rectangle2.width;
                }
            }
        }
        if ((this.cursorOrientation & 0x80) != 0x0) {
            if (n2 > this.bounds.height) {
                if ((this.style & 0x400) == 0x0) {
                    return;
                }
                this.cursorOrientation |= 0x400;
                this.cursorOrientation &= 0xFFFFFF7F;
                final Rectangle bounds2 = this.bounds;
                bounds2.y += this.bounds.height;
                n2 -= this.bounds.height;
                this.bounds.height = 0;
                if (this.proportions.length > 1) {
                    for (int k = 0; k < this.proportions.length; ++k) {
                        final Rectangle rectangle3 = this.proportions[k];
                        rectangle3.y = 100 - rectangle3.y - rectangle3.height;
                    }
                }
            }
        }
        else if ((this.cursorOrientation & 0x400) != 0x0 && this.bounds.height < -n2) {
            if ((this.style & 0x80) == 0x0) {
                return;
            }
            this.cursorOrientation |= 0x80;
            this.cursorOrientation &= 0xFFFFFBFF;
            n2 += this.bounds.height;
            this.bounds.height = 0;
            if (this.proportions.length > 1) {
                for (int l = 0; l < this.proportions.length; ++l) {
                    final Rectangle rectangle4 = this.proportions[l];
                    rectangle4.y = 100 - rectangle4.y - rectangle4.height;
                }
            }
        }
        if ((this.cursorOrientation & 0x4000) != 0x0) {
            final Rectangle bounds3 = this.bounds;
            bounds3.x += n;
            final Rectangle bounds4 = this.bounds;
            bounds4.width -= n;
        }
        else if ((this.cursorOrientation & 0x20000) != 0x0) {
            final Rectangle bounds5 = this.bounds;
            bounds5.width += n;
        }
        if ((this.cursorOrientation & 0x80) != 0x0) {
            final Rectangle bounds6 = this.bounds;
            bounds6.y += n2;
            final Rectangle bounds7 = this.bounds;
            bounds7.height -= n2;
        }
        else if ((this.cursorOrientation & 0x400) != 0x0) {
            final Rectangle bounds8 = this.bounds;
            bounds8.height += n2;
        }
        final Rectangle[] rectangles = new Rectangle[this.rectangles.length];
        for (int n3 = 0; n3 < this.rectangles.length; ++n3) {
            final Rectangle rectangle5 = this.proportions[n3];
            rectangles[n3] = new Rectangle(rectangle5.x * this.bounds.width / 100 + this.bounds.x, rectangle5.y * this.bounds.height / 100 + this.bounds.y, rectangle5.width * this.bounds.width / 100, rectangle5.height * this.bounds.height / 100);
        }
        this.rectangles = rectangles;
    }
    
    public void setCursor(final Cursor clientCursor) {
        this.checkWidget();
        this.clientCursor = clientCursor;
        if (clientCursor != null && this.inEvent) {
            OS.SetCursor(this.clientCursor.handle);
        }
    }
    
    public void setRectangles(final Rectangle[] array) {
        this.checkWidget();
        if (array == null) {
            this.error(4);
        }
        this.rectangles = new Rectangle[array.length];
        for (int i = 0; i < array.length; ++i) {
            final Rectangle rectangle = array[i];
            if (rectangle == null) {
                this.error(4);
            }
            this.rectangles[i] = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        this.proportions = this.computeProportions(array);
    }
    
    public void setStippled(final boolean stippled) {
        this.checkWidget();
        this.stippled = stippled;
    }
    
    int transparentProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 132: {
                if (this.inEvent) {
                    return -1;
                }
                break;
            }
            case 32: {
                if (this.clientCursor != null) {
                    OS.SetCursor(this.clientCursor.handle);
                    return 1;
                }
                if (this.resizeCursor != 0) {
                    OS.SetCursor(this.resizeCursor);
                    return 1;
                }
                break;
            }
            case 15: {
                if (this.hwndOpaque == n) {
                    final PAINTSTRUCT paintstruct = new PAINTSTRUCT();
                    final int beginPaint = OS.BeginPaint(n, paintstruct);
                    int createBitmap = 0;
                    int createPatternBrush = 0;
                    final int createSolidBrush = OS.CreateSolidBrush(16777215);
                    final int selectObject = OS.SelectObject(beginPaint, createSolidBrush);
                    OS.PatBlt(beginPaint, paintstruct.left, paintstruct.top, paintstruct.right - paintstruct.left, paintstruct.bottom - paintstruct.top, 15728673);
                    OS.SelectObject(beginPaint, selectObject);
                    OS.DeleteObject(createSolidBrush);
                    int n5 = 1;
                    int n6;
                    if (this.stippled) {
                        n5 = 3;
                        createBitmap = OS.CreateBitmap(8, 8, 1, 1, new byte[] { -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0, -86, 0, 85, 0 });
                        createPatternBrush = OS.CreatePatternBrush(createBitmap);
                        n6 = OS.SelectObject(beginPaint, createPatternBrush);
                        OS.SetBkColor(beginPaint, 15790320);
                    }
                    else {
                        n6 = OS.SelectObject(beginPaint, OS.GetStockObject(4));
                    }
                    final Rectangle[] rectangles = this.rectangles;
                    final RECT rect = new RECT();
                    for (int i = 0; i < rectangles.length; ++i) {
                        final Rectangle rectangle = rectangles[i];
                        rect.left = rectangle.x;
                        rect.top = rectangle.y;
                        rect.right = rectangle.x + rectangle.width;
                        rect.bottom = rectangle.y + rectangle.height;
                        OS.MapWindowPoints(0, this.hwndOpaque, rect, 2);
                        final int n7 = rect.right - rect.left;
                        final int n8 = rect.bottom - rect.top;
                        OS.PatBlt(beginPaint, rect.left, rect.top, n7, n5, 15728673);
                        OS.PatBlt(beginPaint, rect.left, rect.top + n5, n5, n8 - n5 * 2, 15728673);
                        OS.PatBlt(beginPaint, rect.right - n5, rect.top + n5, n5, n8 - n5 * 2, 15728673);
                        OS.PatBlt(beginPaint, rect.left, rect.bottom - n5, n7, n5, 15728673);
                    }
                    OS.SelectObject(beginPaint, n6);
                    if (this.stippled) {
                        OS.DeleteObject(createPatternBrush);
                        OS.DeleteObject(createBitmap);
                    }
                    OS.EndPaint(n, paintstruct);
                    return 0;
                }
                break;
            }
        }
        return OS.CallWindowProc((n == this.hwndTransparent) ? this.oldTransparentProc : this.oldOpaqueProc, n, n2, n3, n4);
    }
    
    void update() {
        if (this.hwndOpaque != 0) {
            return;
        }
        if (this.parent != null) {
            if (this.parent.isDisposed()) {
                return;
            }
            this.parent.getShell().update(true);
        }
        else {
            this.display.update();
        }
    }
    
    LRESULT wmKeyDown(final int n, final int n2, final int n3) {
        final LRESULT wmKeyDown = super.wmKeyDown(n, n2, n3);
        if (wmKeyDown != null) {
            return wmKeyDown;
        }
        final boolean b = this.parent != null && (this.parent.style & 0x8000000) != 0x0;
        final int n4 = (OS.GetKeyState(17) < 0) ? 1 : 9;
        int n5 = 0;
        int n6 = 0;
        switch (n2) {
            case 27: {
                this.cancelled = true;
                this.tracking = false;
                break;
            }
            case 13: {
                this.tracking = false;
                break;
            }
            case 37: {
                n5 = (b ? n4 : (-n4));
                break;
            }
            case 39: {
                n5 = (b ? (-n4) : n4);
                break;
            }
            case 38: {
                n6 = -n4;
                break;
            }
            case 40: {
                n6 = n4;
                break;
            }
        }
        if (n5 != 0 || n6 != 0) {
            final Rectangle[] rectangles = this.rectangles;
            final boolean stippled = this.stippled;
            final Rectangle[] array = new Rectangle[this.rectangles.length];
            for (int i = 0; i < this.rectangles.length; ++i) {
                final Rectangle rectangle = this.rectangles[i];
                array[i] = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
            final Event event = new Event();
            event.x = this.oldX + n5;
            event.y = this.oldY + n6;
            Point point;
            if ((this.style & 0x10) != 0x0) {
                this.resizeRectangles(n5, n6);
                this.inEvent = true;
                this.sendEvent(11, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean b2 = false;
                if (this.rectangles != rectangles) {
                    final int length = this.rectangles.length;
                    if (length != array.length) {
                        b2 = true;
                    }
                    else {
                        for (int j = 0; j < length; ++j) {
                            if (!this.rectangles[j].equals(array[j])) {
                                b2 = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    b2 = true;
                }
                if (b2) {
                    this.drawRectangles(array, stippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                point = this.adjustResizeCursor();
            }
            else {
                this.moveRectangles(n5, n6);
                this.inEvent = true;
                this.sendEvent(10, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean b3 = false;
                if (this.rectangles != rectangles) {
                    final int length2 = this.rectangles.length;
                    if (length2 != array.length) {
                        b3 = true;
                    }
                    else {
                        for (int k = 0; k < length2; ++k) {
                            if (!this.rectangles[k].equals(array[k])) {
                                b3 = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    b3 = true;
                }
                if (b3) {
                    this.drawRectangles(array, stippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                point = this.adjustMoveCursor();
            }
            if (point != null) {
                this.oldX = point.x;
                this.oldY = point.y;
            }
        }
        return wmKeyDown;
    }
    
    LRESULT wmSysKeyDown(final int n, final int n2, final int n3) {
        final LRESULT wmSysKeyDown = super.wmSysKeyDown(n, n2, n3);
        if (wmSysKeyDown != null) {
            return wmSysKeyDown;
        }
        this.cancelled = true;
        this.tracking = false;
        return wmSysKeyDown;
    }
    
    LRESULT wmMouse(final int n, final int n2, final int n3) {
        final boolean b = this.parent != null && (this.parent.style & 0x8000000) != 0x0;
        final int getMessagePos = OS.GetMessagePos();
        int n4 = OS.GET_X_LPARAM(getMessagePos);
        int n5 = OS.GET_Y_LPARAM(getMessagePos);
        if (n4 != this.oldX || n5 != this.oldY) {
            final Rectangle[] rectangles = this.rectangles;
            final boolean stippled = this.stippled;
            final Rectangle[] array = new Rectangle[this.rectangles.length];
            for (int i = 0; i < this.rectangles.length; ++i) {
                final Rectangle rectangle = this.rectangles[i];
                array[i] = new Rectangle(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            }
            final Event event = new Event();
            event.x = n4;
            event.y = n5;
            if ((this.style & 0x10) != 0x0) {
                if (b) {
                    this.resizeRectangles(this.oldX - n4, n5 - this.oldY);
                }
                else {
                    this.resizeRectangles(n4 - this.oldX, n5 - this.oldY);
                }
                this.inEvent = true;
                this.sendEvent(11, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean b2 = false;
                if (this.rectangles != rectangles) {
                    final int length = this.rectangles.length;
                    if (length != array.length) {
                        b2 = true;
                    }
                    else {
                        for (int j = 0; j < length; ++j) {
                            if (!this.rectangles[j].equals(array[j])) {
                                b2 = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    b2 = true;
                }
                if (b2) {
                    this.drawRectangles(array, stippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
                final Point adjustResizeCursor = this.adjustResizeCursor();
                if (adjustResizeCursor != null) {
                    n4 = adjustResizeCursor.x;
                    n5 = adjustResizeCursor.y;
                }
            }
            else {
                if (b) {
                    this.moveRectangles(this.oldX - n4, n5 - this.oldY);
                }
                else {
                    this.moveRectangles(n4 - this.oldX, n5 - this.oldY);
                }
                this.inEvent = true;
                this.sendEvent(10, event);
                this.inEvent = false;
                if (this.isDisposed()) {
                    this.cancelled = true;
                    return LRESULT.ONE;
                }
                boolean b3 = false;
                if (this.rectangles != rectangles) {
                    final int length2 = this.rectangles.length;
                    if (length2 != array.length) {
                        b3 = true;
                    }
                    else {
                        for (int k = 0; k < length2; ++k) {
                            if (!this.rectangles[k].equals(array[k])) {
                                b3 = true;
                                break;
                            }
                        }
                    }
                }
                else {
                    b3 = true;
                }
                if (b3) {
                    this.drawRectangles(array, stippled);
                    this.update();
                    this.drawRectangles(this.rectangles, this.stippled);
                }
            }
            this.oldX = n4;
            this.oldY = n5;
        }
        this.tracking = (n != 514);
        return null;
    }
}
