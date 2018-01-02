// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.widgets;

import org.eclipse.swt.internal.win32.MOUSEINPUT;
import org.eclipse.swt.internal.win32.INPUT;
import org.eclipse.swt.internal.win32.KEYBDINPUT;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.Library;
import org.eclipse.swt.internal.win32.WNDCLASS;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICS;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICSA;
import org.eclipse.swt.internal.win32.NONCLIENTMETRICSW;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.internal.win32.MONITORINFO;
import org.eclipse.swt.internal.win32.CREATESTRUCT;
import org.eclipse.swt.internal.win32.HIGHCONTRAST;
import org.eclipse.swt.internal.win32.LOGFONTA;
import org.eclipse.swt.internal.win32.LOGFONTW;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.GCData;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.internal.win32.BITMAPINFOHEADER;
import org.eclipse.swt.internal.win32.BITMAP;
import org.eclipse.swt.internal.win32.ICONINFO;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.DeviceData;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.NMLVDISPINFO;
import org.eclipse.swt.internal.win32.NMHDR;
import org.eclipse.swt.internal.ImageList;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.win32.LOGFONT;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.internal.win32.STARTUPINFO;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.graphics.Device;

public class Display extends Device
{
    public MSG msg;
    static String APP_NAME;
    static String APP_VERSION;
    Event[] eventQueue;
    Callback windowCallback;
    int windowProc;
    int threadId;
    TCHAR windowClass;
    TCHAR windowShadowClass;
    TCHAR windowOwnDCClass;
    static int WindowClassCount;
    static final String WindowName = "SWT_Window";
    static final String WindowShadowName = "SWT_WindowShadow";
    static final String WindowOwnDCName = "SWT_WindowOwnDC";
    EventTable eventTable;
    EventTable filterTable;
    boolean useOwnDC;
    int freeSlot;
    int[] indexTable;
    Control lastControl;
    Control lastGetControl;
    int lastHwnd;
    int lastGetHwnd;
    Control[] controlTable;
    static final int GROW_SIZE = 1024;
    static final int SWT_OBJECT_INDEX;
    static final boolean USE_PROPERTY;
    static STARTUPINFO lpStartupInfo;
    int hButtonTheme;
    int hEditTheme;
    int hExplorerBarTheme;
    int hScrollBarTheme;
    int hTabTheme;
    static final char[] BUTTON;
    static final char[] EDIT;
    static final char[] EXPLORER;
    static final char[] EXPLORERBAR;
    static final char[] SCROLLBAR;
    static final char[] LISTVIEW;
    static final char[] TAB;
    static final char[] TREEVIEW;
    int focusEvent;
    Control focusControl;
    boolean fixFocus;
    Menu[] bars;
    Menu[] popups;
    MenuItem[] items;
    static final int ID_START = 108;
    Callback msgFilterCallback;
    int msgFilterProc;
    int filterHook;
    MSG hookMsg;
    boolean runDragDrop;
    boolean dragCancelled;
    Callback foregroundIdleCallback;
    int foregroundIdleProc;
    int idleHook;
    boolean ignoreNextKey;
    Callback getMsgCallback;
    Callback embeddedCallback;
    int getMsgProc;
    int msgHook;
    int embeddedHwnd;
    int embeddedProc;
    static final String AWT_WINDOW_CLASS = "SunAwtWindow";
    static final short[] ACCENTS;
    Synchronizer synchronizer;
    boolean runMessages;
    boolean runMessagesInIdle;
    boolean runMessagesInMessageProc;
    static final String RUN_MESSAGES_IN_IDLE_KEY = "org.eclipse.swt.internal.win32.runMessagesInIdle";
    static final String RUN_MESSAGES_IN_MESSAGE_PROC_KEY = "org.eclipse.swt.internal.win32.runMessagesInMessageProc";
    static final String USE_OWNDC_KEY = "org.eclipse.swt.internal.win32.useOwnDC";
    static final String ACCEL_KEY_HIT = "org.eclipse.swt.internal.win32.accelKeyHit";
    Thread thread;
    Runnable[] disposeList;
    Composite[] layoutDeferred;
    int layoutDeferredCount;
    Tray tray;
    int nextTrayId;
    TaskBar taskBar;
    static final String TASKBAR_EVENT = "/SWTINTERNAL_ID";
    static final String LAUNCHER_PREFIX = "--launcher.openFile ";
    int[] timerIds;
    Runnable[] timerList;
    int nextTimerId;
    static final int SETTINGS_ID = 100;
    static final int SETTINGS_DELAY = 2000;
    boolean lastHighContrast;
    boolean sendSettings;
    RECT clickRect;
    int clickCount;
    int lastTime;
    int lastButton;
    int lastClickHwnd;
    int scrollRemainder;
    int scrollHRemainder;
    int lastKey;
    int lastMouse;
    int lastAscii;
    boolean lastVirtual;
    boolean lastNull;
    boolean lastDead;
    byte[] keyboard;
    boolean accelKeyHit;
    boolean mnemonicKeyHit;
    boolean lockActiveWindow;
    boolean captureChanged;
    boolean xMouse;
    double magStartDistance;
    double lastDistance;
    double rotationAngle;
    int lastX;
    int lastY;
    TouchSource[] touchSources;
    int nextToolTipId;
    boolean ignoreRestoreFocus;
    Control lastHittestControl;
    int lastHittest;
    Callback messageCallback;
    int hwndMessage;
    int messageProc;
    LOGFONT lfSystemFont;
    Font systemFont;
    Image errorImage;
    Image infoImage;
    Image questionImage;
    Image warningIcon;
    Cursor[] cursors;
    Resource[] resources;
    static final int RESOURCE_SIZE = 27;
    ImageList[] imageList;
    ImageList[] toolImageList;
    ImageList[] toolHotImageList;
    ImageList[] toolDisabledImageList;
    int lpCustColors;
    Image upArrow;
    Image downArrow;
    char[] tableBuffer;
    NMHDR hdr;
    NMLVDISPINFO plvfi;
    int hwndParent;
    int columnCount;
    boolean[] columnVisible;
    int resizeCount;
    static final int RESIZE_LIMIT = 4;
    Object data;
    String[] keys;
    Object[] values;
    static final int[][] KeyTable;
    static Display Default;
    static Display[] Displays;
    Monitor[] monitors;
    int monitorCount;
    Shell[] modalShells;
    Dialog modalDialog;
    static boolean TrimEnabled;
    static final int SWT_GETACCELCOUNT = 32768;
    static final int SWT_GETACCEL = 32769;
    static final int SWT_KEYMSG = 32770;
    static final int SWT_DESTROY = 32771;
    static final int SWT_TRAYICONMSG = 32772;
    static final int SWT_NULL = 32773;
    static final int SWT_RUNASYNC = 32774;
    static int TASKBARCREATED;
    static int TASKBARBUTTONCREATED;
    static int SWT_RESTORECARET;
    static int DI_GETDRAGIMAGE;
    static int SWT_OPENDOC;
    int hitCount;
    Widget[] skinList;
    int skinCount;
    static final String PACKAGE_PREFIX = "org.eclipse.swt.widgets.";
    static /* synthetic */ Class class$0;
    
    static {
        Display.APP_NAME = "SWT";
        Display.APP_VERSION = "";
        USE_PROPERTY = !OS.IsWinCE;
        if (Display.USE_PROPERTY) {
            SWT_OBJECT_INDEX = OS.GlobalAddAtom(new TCHAR(0, "SWT_OBJECT_INDEX", true));
        }
        else {
            SWT_OBJECT_INDEX = 0;
        }
        if (!OS.IsWinCE) {
            Display.lpStartupInfo = new STARTUPINFO();
            Display.lpStartupInfo.cb = STARTUPINFO.sizeof;
            OS.GetStartupInfo(Display.lpStartupInfo);
        }
        BUTTON = new char[] { 'B', 'U', 'T', 'T', 'O', 'N', '\0' };
        EDIT = new char[] { 'E', 'D', 'I', 'T', '\0' };
        EXPLORER = new char[] { 'E', 'X', 'P', 'L', 'O', 'R', 'E', 'R', '\0' };
        EXPLORERBAR = new char[] { 'E', 'X', 'P', 'L', 'O', 'R', 'E', 'R', 'B', 'A', 'R', '\0' };
        SCROLLBAR = new char[] { 'S', 'C', 'R', 'O', 'L', 'L', 'B', 'A', 'R', '\0' };
        LISTVIEW = new char[] { 'L', 'I', 'S', 'T', 'V', 'I', 'E', 'W', '\0' };
        TAB = new char[] { 'T', 'A', 'B', '\0' };
        TREEVIEW = new char[] { 'T', 'R', 'E', 'E', 'V', 'I', 'E', 'W', '\0' };
        ACCENTS = new short[] { 126, 96, 39, 94, 34 };
        KeyTable = new int[][] { { 18, 65536 }, { 16, 131072 }, { 17, 262144 }, { 38, 16777217 }, { 40, 16777218 }, { 37, 16777219 }, { 39, 16777220 }, { 33, 16777221 }, { 34, 16777222 }, { 36, 16777223 }, { 35, 16777224 }, { 45, 16777225 }, { 8, 8 }, { 13, 13 }, { 46, 127 }, { 27, 27 }, { 13, 10 }, { 9, 9 }, { 112, 16777226 }, { 113, 16777227 }, { 114, 16777228 }, { 115, 16777229 }, { 116, 16777230 }, { 117, 16777231 }, { 118, 16777232 }, { 119, 16777233 }, { 120, 16777234 }, { 121, 16777235 }, { 122, 16777236 }, { 123, 16777237 }, { 124, 16777238 }, { 125, 16777239 }, { 126, 16777240 }, { 127, 16777241 }, { 128, 16777242 }, { 129, 16777243 }, { 130, 16777244 }, { 131, 16777245 }, { 106, 16777258 }, { 107, 16777259 }, { 13, 16777296 }, { 109, 16777261 }, { 110, 16777262 }, { 111, 16777263 }, { 96, 16777264 }, { 97, 16777265 }, { 98, 16777266 }, { 99, 16777267 }, { 100, 16777268 }, { 101, 16777269 }, { 102, 16777270 }, { 103, 16777271 }, { 104, 16777272 }, { 105, 16777273 }, { 20, 16777298 }, { 144, 16777299 }, { 145, 16777300 }, { 19, 16777301 }, { 3, 16777302 }, { 44, 16777303 } };
        Display.Displays = new Display[4];
        Display.TrimEnabled = false;
        Display.DeviceFinder = new Runnable() {
            public void run() {
                Display device = Display.getCurrent();
                if (device == null) {
                    device = Display.getDefault();
                }
                Display.setDevice(device);
            }
        };
    }
    
    static void setDevice(final Device currentDevice) {
        Display.CurrentDevice = currentDevice;
    }
    
    public Display() {
        this(null);
    }
    
    public Display(final DeviceData deviceData) {
        super(deviceData);
        this.msg = new MSG();
        this.hookMsg = new MSG();
        this.runDragDrop = true;
        this.dragCancelled = false;
        this.synchronizer = new Synchronizer(this);
        this.runMessages = true;
        this.runMessagesInIdle = false;
        this.runMessagesInMessageProc = true;
        this.nextTimerId = 101;
        this.keyboard = new byte[256];
        this.cursors = new Cursor[22];
        this.hdr = new NMHDR();
        this.plvfi = new NMLVDISPINFO();
        this.monitors = null;
        this.monitorCount = 0;
        this.skinList = new Widget[1024];
    }
    
    Control _getFocusControl() {
        return this.findControl(OS.GetFocus());
    }
    
    void addBar(final Menu menu) {
        if (this.bars == null) {
            this.bars = new Menu[4];
        }
        final int length = this.bars.length;
        for (int i = 0; i < length; ++i) {
            if (this.bars[i] == menu) {
                return;
            }
        }
        int n;
        for (n = 0; n < length && this.bars[n] != null; ++n) {}
        if (n == length) {
            final Menu[] bars = new Menu[length + 4];
            System.arraycopy(this.bars, 0, bars, 0, length);
            this.bars = bars;
        }
        this.bars[n] = menu;
    }
    
    void addControl(final int n, final Control control) {
        if (n == 0) {
            return;
        }
        if (this.freeSlot == -1) {
            final int length = this.indexTable.length;
            this.freeSlot = length;
            final int n2 = length + 1024;
            final int[] indexTable = new int[n2];
            final Control[] controlTable = new Control[n2];
            System.arraycopy(this.indexTable, 0, indexTable, 0, this.freeSlot);
            System.arraycopy(this.controlTable, 0, controlTable, 0, this.freeSlot);
            for (int i = this.freeSlot; i < n2 - 1; ++i) {
                indexTable[i] = i + 1;
            }
            indexTable[n2 - 1] = -1;
            this.indexTable = indexTable;
            this.controlTable = controlTable;
        }
        if (Display.USE_PROPERTY) {
            OS.SetProp(n, Display.SWT_OBJECT_INDEX, this.freeSlot + 1);
        }
        else {
            OS.SetWindowLongPtr(n, -21, this.freeSlot + 1);
        }
        final int freeSlot = this.freeSlot;
        this.freeSlot = this.indexTable[freeSlot];
        this.indexTable[freeSlot] = -2;
        this.controlTable[freeSlot] = control;
    }
    
    void addSkinnableWidget(final Widget widget) {
        if (this.skinCount >= this.skinList.length) {
            final Widget[] skinList = new Widget[this.skinList.length + 1024];
            System.arraycopy(this.skinList, 0, skinList, 0, this.skinList.length);
            this.skinList = skinList;
        }
        this.skinList[this.skinCount++] = widget;
    }
    
    public void addFilter(final int n, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.filterTable == null) {
            this.filterTable = new EventTable();
        }
        this.filterTable.hook(n, listener);
    }
    
    void addLayoutDeferred(final Composite composite) {
        if (this.layoutDeferred == null) {
            this.layoutDeferred = new Composite[64];
        }
        if (this.layoutDeferredCount == this.layoutDeferred.length) {
            final Composite[] layoutDeferred = new Composite[this.layoutDeferred.length + 64];
            System.arraycopy(this.layoutDeferred, 0, layoutDeferred, 0, this.layoutDeferred.length);
            this.layoutDeferred = layoutDeferred;
        }
        this.layoutDeferred[this.layoutDeferredCount++] = composite;
    }
    
    public void addListener(final int n, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            this.eventTable = new EventTable();
        }
        this.eventTable.hook(n, listener);
    }
    
    void addMenuItem(final MenuItem menuItem) {
        if (this.items == null) {
            this.items = new MenuItem[64];
        }
        for (int i = 0; i < this.items.length; ++i) {
            if (this.items[i] == null) {
                menuItem.id = i + 108;
                this.items[i] = menuItem;
                return;
            }
        }
        menuItem.id = this.items.length + 108;
        final MenuItem[] items = new MenuItem[this.items.length + 64];
        items[this.items.length] = menuItem;
        System.arraycopy(this.items, 0, items, 0, this.items.length);
        this.items = items;
    }
    
    void addPopup(final Menu menu) {
        if (this.popups == null) {
            this.popups = new Menu[4];
        }
        final int length = this.popups.length;
        for (int i = 0; i < length; ++i) {
            if (this.popups[i] == menu) {
                return;
            }
        }
        int n;
        for (n = 0; n < length && this.popups[n] != null; ++n) {}
        if (n == length) {
            final Menu[] popups = new Menu[length + 4];
            System.arraycopy(this.popups, 0, popups, 0, length);
            this.popups = popups;
        }
        this.popups[n] = menu;
    }
    
    int asciiKey(final int n) {
        if (OS.IsWinCE) {
            return 0;
        }
        for (int i = 0; i < this.keyboard.length; ++i) {
            this.keyboard[i] = 0;
        }
        if (!OS.GetKeyboardState(this.keyboard)) {
            return 0;
        }
        if (OS.IsUnicode) {
            final char[] array = { '\0' };
            if (OS.ToUnicode(n, n, this.keyboard, array, 1, 0) == 1) {
                return array[0];
            }
        }
        else {
            final short[] array2 = { 0 };
            if (OS.ToAscii(n, n, this.keyboard, array2, 0) == 1) {
                return array2[0];
            }
        }
        return 0;
    }
    
    public void asyncExec(final Runnable runnable) {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (this.isDisposed()) {
                this.error(45);
            }
            this.synchronizer.asyncExec(runnable);
        }
        // monitorexit(clazz3)
    }
    
    public void beep() {
        this.checkDevice();
        OS.MessageBeep(0);
    }
    
    protected void checkSubclass() {
        if (!isValidClass(this.getClass())) {
            this.error(43);
        }
    }
    
    protected void checkDevice() {
        if (this.thread == null) {
            this.error(24);
        }
        if (this.thread != Thread.currentThread() && this.threadId != OS.GetCurrentThreadId()) {
            this.error(22);
        }
        if (this.isDisposed()) {
            this.error(45);
        }
    }
    
    static void checkDisplay(final Thread thread, final boolean b) {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            for (int i = 0; i < Display.Displays.length; ++i) {
                if (Display.Displays[i] != null) {
                    if (!b) {
                        SWT.error(20, null, " [multiple displays]");
                    }
                    if (Display.Displays[i].thread == thread) {
                        SWT.error(22);
                    }
                }
            }
        }
        // monitorexit(clazz3)
    }
    
    void clearModal(final Shell shell) {
        if (this.modalShells == null) {
            return;
        }
        int n;
        int length;
        for (n = 0, length = this.modalShells.length; n < length && this.modalShells[n] != shell; ++n) {
            if (this.modalShells[n] == null) {
                return;
            }
        }
        if (n == length) {
            return;
        }
        System.arraycopy(this.modalShells, n + 1, this.modalShells, n, --length - n);
        this.modalShells[length] = null;
        if (n == 0 && this.modalShells[0] == null) {
            this.modalShells = null;
        }
        final Shell[] shells = this.getShells();
        for (int i = 0; i < shells.length; ++i) {
            shells[i].updateModal();
        }
    }
    
    int controlKey(final int n) {
        final int charUpper = OS.CharUpper((short)n);
        if (64 <= charUpper && charUpper <= 95) {
            return charUpper & 0xBF;
        }
        return n;
    }
    
    public void close() {
        this.checkDevice();
        final Event event = new Event();
        this.sendEvent(21, event);
        if (event.doit) {
            this.dispose();
        }
    }
    
    protected void create(final DeviceData deviceData) {
        this.checkSubclass();
        checkDisplay(this.thread = Thread.currentThread(), true);
        this.createDisplay(deviceData);
        register(this);
        if (Display.Default == null) {
            Display.Default = this;
        }
    }
    
    void createDisplay(final DeviceData deviceData) {
    }
    
    static int create32bitDIB(final Image image) {
        int transparentPixel = -1;
        int alpha = -1;
        int hbmMask = 0;
        int n = 0;
        byte[] alphaData = null;
        switch (image.type) {
            case 1: {
                final ICONINFO iconinfo = new ICONINFO();
                OS.GetIconInfo(image.handle, iconinfo);
                n = iconinfo.hbmColor;
                hbmMask = iconinfo.hbmMask;
                break;
            }
            case 0: {
                final ImageData imageData = image.getImageData();
                n = image.handle;
                alpha = imageData.alpha;
                alphaData = imageData.alphaData;
                transparentPixel = imageData.transparentPixel;
                break;
            }
        }
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(n, BITMAP.sizeof, bitmap);
        final int bmWidth = bitmap.bmWidth;
        final int bmHeight = bitmap.bmHeight;
        final int getDC = OS.GetDC(0);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
        final int selectObject = OS.SelectObject(createCompatibleDC, n);
        final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = bmWidth;
        bitmapinfoheader.biHeight = -bmHeight;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = 32;
        bitmapinfoheader.biCompression = 0;
        final byte[] array = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(array, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        final int[] array2 = { 0 };
        final int createDIBSection = OS.CreateDIBSection(0, array, 0, array2, 0, 0);
        if (createDIBSection == 0) {
            SWT.error(2);
        }
        final int selectObject2 = OS.SelectObject(createCompatibleDC2, createDIBSection);
        final BITMAP bitmap2 = new BITMAP();
        OS.GetObject(createDIBSection, BITMAP.sizeof, bitmap2);
        final int n2 = bitmap2.bmWidthBytes * bitmap2.bmHeight;
        OS.BitBlt(createCompatibleDC2, 0, 0, bmWidth, bmHeight, createCompatibleDC, 0, 0, 13369376);
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        if (transparentPixel != -1) {
            if (bitmap.bmBitsPixel <= 8) {
                final byte[] array3 = new byte[4];
                OS.GetDIBColorTable(createCompatibleDC, transparentPixel, 1, array3);
                b3 = array3[0];
                b2 = array3[1];
                b = array3[2];
            }
            else {
                switch (bitmap.bmBitsPixel) {
                    case 16: {
                        b3 = (byte)((transparentPixel & 0x1F) << 3);
                        b2 = (byte)((transparentPixel & 0x3E0) >> 2);
                        b = (byte)((transparentPixel & 0x7C00) >> 7);
                        break;
                    }
                    case 24: {
                        b3 = (byte)((transparentPixel & 0xFF0000) >> 16);
                        b2 = (byte)((transparentPixel & 0xFF00) >> 8);
                        b = (byte)(transparentPixel & 0xFF);
                        break;
                    }
                    case 32: {
                        b3 = (byte)((transparentPixel & 0xFF000000) >>> 24);
                        b2 = (byte)((transparentPixel & 0xFF0000) >> 16);
                        b = (byte)((transparentPixel & 0xFF00) >> 8);
                        break;
                    }
                }
            }
        }
        final byte[] array4 = new byte[n2];
        OS.MoveMemory(array4, array2[0], n2);
        if (hbmMask != 0) {
            OS.SelectObject(createCompatibleDC, hbmMask);
            int i = 0;
            int n3 = 0;
            while (i < bmHeight) {
                for (int j = 0; j < bmWidth; ++j) {
                    if (OS.GetPixel(createCompatibleDC, j, i) != 0) {
                        final byte[] array5 = array4;
                        final int n4 = n3 + 0;
                        final byte[] array6 = array4;
                        final int n5 = n3 + 1;
                        final byte[] array7 = array4;
                        final int n6 = n3 + 2;
                        final byte[] array8 = array4;
                        final int n7 = n3 + 3;
                        final boolean b4 = false;
                        array7[n6] = (array8[n7] = (byte)(b4 ? 1 : 0));
                        array5[n4] = (array6[n5] = (byte)(b4 ? 1 : 0));
                    }
                    else {
                        array4[n3 + 3] = -1;
                    }
                    n3 += 4;
                }
                ++i;
            }
        }
        else if (alpha != -1) {
            int k = 0;
            int n8 = 0;
            while (k < bmHeight) {
                for (int l = 0; l < bmWidth; ++l) {
                    final int n9 = (array4[n8 + 0] & 0xFF) * alpha + 128;
                    final int n10 = n9 + (n9 >> 8) >> 8;
                    final int n11 = (array4[n8 + 1] & 0xFF) * alpha + 128;
                    final int n12 = n11 + (n11 >> 8) >> 8;
                    final int n13 = (array4[n8 + 2] & 0xFF) * alpha + 128;
                    final int n14 = n13 + (n13 >> 8) >> 8;
                    array4[n8 + 0] = (byte)n10;
                    array4[n8 + 1] = (byte)n12;
                    array4[n8 + 2] = (byte)n14;
                    array4[n8 + 3] = (byte)alpha;
                    n8 += 4;
                }
                ++k;
            }
        }
        else if (alphaData != null) {
            int n15 = 0;
            int n16 = 0;
            int n17 = 0;
            while (n15 < bmHeight) {
                for (int n18 = 0; n18 < bmWidth; ++n18) {
                    final int n19 = alphaData[n17++] & 0xFF;
                    final int n20 = (array4[n16 + 0] & 0xFF) * n19 + 128;
                    final int n21 = n20 + (n20 >> 8) >> 8;
                    final int n22 = (array4[n16 + 1] & 0xFF) * n19 + 128;
                    final int n23 = n22 + (n22 >> 8) >> 8;
                    final int n24 = (array4[n16 + 2] & 0xFF) * n19 + 128;
                    final int n25 = n24 + (n24 >> 8) >> 8;
                    array4[n16 + 0] = (byte)n21;
                    array4[n16 + 1] = (byte)n23;
                    array4[n16 + 2] = (byte)n25;
                    array4[n16 + 3] = (byte)n19;
                    n16 += 4;
                }
                ++n15;
            }
        }
        else if (transparentPixel != -1) {
            int n26 = 0;
            int n27 = 0;
            while (n26 < bmHeight) {
                for (int n28 = 0; n28 < bmWidth; ++n28) {
                    if (array4[n27] == b3 && array4[n27 + 1] == b2 && array4[n27 + 2] == b) {
                        final byte[] array9 = array4;
                        final int n29 = n27 + 0;
                        final byte[] array10 = array4;
                        final int n30 = n27 + 1;
                        final byte[] array11 = array4;
                        final int n31 = n27 + 2;
                        final byte[] array12 = array4;
                        final int n32 = n27 + 3;
                        final boolean b5 = false;
                        array11[n31] = (array12[n32] = (byte)(b5 ? 1 : 0));
                        array9[n29] = (array10[n30] = (byte)(b5 ? 1 : 0));
                    }
                    else {
                        array4[n27 + 3] = -1;
                    }
                    n27 += 4;
                }
                ++n26;
            }
        }
        else {
            int n33 = 0;
            int n34 = 0;
            while (n33 < bmHeight) {
                for (int n35 = 0; n35 < bmWidth; ++n35) {
                    array4[n34 + 3] = -1;
                    n34 += 4;
                }
                ++n33;
            }
        }
        OS.MoveMemory(array2[0], array4, n2);
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.SelectObject(createCompatibleDC2, selectObject2);
        OS.DeleteObject(createCompatibleDC);
        OS.DeleteObject(createCompatibleDC2);
        OS.ReleaseDC(0, getDC);
        if (n != image.handle && n != 0) {
            OS.DeleteObject(n);
        }
        if (hbmMask != 0) {
            OS.DeleteObject(hbmMask);
        }
        return createDIBSection;
    }
    
    static int create32bitDIB(final int n, final int n2, final byte[] array, final int n3) {
        final BITMAP bitmap = new BITMAP();
        OS.GetObject(n, BITMAP.sizeof, bitmap);
        final int bmWidth = bitmap.bmWidth;
        final int bmHeight = bitmap.bmHeight;
        final int getDC = OS.GetDC(0);
        final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
        final int selectObject = OS.SelectObject(createCompatibleDC, n);
        final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
        final BITMAPINFOHEADER bitmapinfoheader = new BITMAPINFOHEADER();
        bitmapinfoheader.biSize = BITMAPINFOHEADER.sizeof;
        bitmapinfoheader.biWidth = bmWidth;
        bitmapinfoheader.biHeight = -bmHeight;
        bitmapinfoheader.biPlanes = 1;
        bitmapinfoheader.biBitCount = 32;
        bitmapinfoheader.biCompression = 0;
        final byte[] array2 = new byte[BITMAPINFOHEADER.sizeof];
        OS.MoveMemory(array2, bitmapinfoheader, BITMAPINFOHEADER.sizeof);
        final int[] array3 = { 0 };
        final int createDIBSection = OS.CreateDIBSection(0, array2, 0, array3, 0, 0);
        if (createDIBSection == 0) {
            SWT.error(2);
        }
        final int selectObject2 = OS.SelectObject(createCompatibleDC2, createDIBSection);
        final BITMAP bitmap2 = new BITMAP();
        OS.GetObject(createDIBSection, BITMAP.sizeof, bitmap2);
        final int n4 = bitmap2.bmWidthBytes * bitmap2.bmHeight;
        OS.BitBlt(createCompatibleDC2, 0, 0, bmWidth, bmHeight, createCompatibleDC, 0, 0, 13369376);
        byte b = 0;
        byte b2 = 0;
        byte b3 = 0;
        if (n3 != -1) {
            if (bitmap.bmBitsPixel <= 8) {
                final byte[] array4 = new byte[4];
                OS.GetDIBColorTable(createCompatibleDC, n3, 1, array4);
                b3 = array4[0];
                b2 = array4[1];
                b = array4[2];
            }
            else {
                switch (bitmap.bmBitsPixel) {
                    case 16: {
                        b3 = (byte)((n3 & 0x1F) << 3);
                        b2 = (byte)((n3 & 0x3E0) >> 2);
                        b = (byte)((n3 & 0x7C00) >> 7);
                        break;
                    }
                    case 24: {
                        b3 = (byte)((n3 & 0xFF0000) >> 16);
                        b2 = (byte)((n3 & 0xFF00) >> 8);
                        b = (byte)(n3 & 0xFF);
                        break;
                    }
                    case 32: {
                        b3 = (byte)((n3 & 0xFF000000) >>> 24);
                        b2 = (byte)((n3 & 0xFF0000) >> 16);
                        b = (byte)((n3 & 0xFF00) >> 8);
                        break;
                    }
                }
            }
        }
        OS.SelectObject(createCompatibleDC, selectObject);
        OS.SelectObject(createCompatibleDC2, selectObject2);
        OS.DeleteObject(createCompatibleDC);
        OS.DeleteObject(createCompatibleDC2);
        OS.ReleaseDC(0, getDC);
        final byte[] array5 = new byte[n4];
        OS.MoveMemory(array5, array3[0], n4);
        if (n2 != -1) {
            int i = 0;
            int n5 = 0;
            while (i < bmHeight) {
                for (int j = 0; j < bmWidth; ++j) {
                    array5[n5 + 3] = (byte)n2;
                    n5 += 4;
                }
                ++i;
            }
        }
        else if (array != null) {
            int k = 0;
            int n6 = 0;
            int n7 = 0;
            while (k < bmHeight) {
                for (int l = 0; l < bmWidth; ++l) {
                    array5[n6 + 3] = array[n7++];
                    n6 += 4;
                }
                ++k;
            }
        }
        else if (n3 != -1) {
            int n8 = 0;
            int n9 = 0;
            while (n8 < bmHeight) {
                for (int n10 = 0; n10 < bmWidth; ++n10) {
                    if (array5[n9] == b3 && array5[n9 + 1] == b2 && array5[n9 + 2] == b) {
                        array5[n9 + 3] = 0;
                    }
                    else {
                        array5[n9 + 3] = -1;
                    }
                    n9 += 4;
                }
                ++n8;
            }
        }
        OS.MoveMemory(array3[0], array5, n4);
        return createDIBSection;
    }
    
    static Image createIcon(final Image image) {
        final Device device = image.getDevice();
        final ImageData imageData = image.getImageData();
        if (imageData.alpha == -1 && imageData.alphaData == null) {
            return new Image(device, imageData, imageData.getTransparencyMask());
        }
        final int width = imageData.width;
        final int height = imageData.height;
        final int internal_new_GC = device.internal_new_GC(null);
        final int createCompatibleDC = OS.CreateCompatibleDC(internal_new_GC);
        int hbmColor;
        int hbmMask;
        int n;
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            hbmColor = create32bitDIB(image.handle, imageData.alpha, imageData.alphaData, imageData.transparentPixel);
            hbmMask = OS.CreateBitmap(width, height, 1, 1, null);
            n = OS.SelectObject(createCompatibleDC, hbmMask);
            OS.PatBlt(createCompatibleDC, 0, 0, width, height, 66);
        }
        else {
            hbmMask = createMaskFromAlpha(imageData, width, height);
            hbmColor = OS.CreateCompatibleBitmap(internal_new_GC, width, height);
            n = OS.SelectObject(createCompatibleDC, hbmColor);
            final int createCompatibleDC2 = OS.CreateCompatibleDC(internal_new_GC);
            final int selectObject = OS.SelectObject(createCompatibleDC2, image.handle);
            OS.PatBlt(createCompatibleDC, 0, 0, width, height, 66);
            OS.BitBlt(createCompatibleDC, 0, 0, width, height, createCompatibleDC2, 0, 0, 6684742);
            OS.SelectObject(createCompatibleDC2, hbmMask);
            OS.BitBlt(createCompatibleDC, 0, 0, width, height, createCompatibleDC2, 0, 0, 8913094);
            OS.SelectObject(createCompatibleDC2, image.handle);
            OS.BitBlt(createCompatibleDC, 0, 0, width, height, createCompatibleDC2, 0, 0, 6684742);
            OS.SelectObject(createCompatibleDC2, selectObject);
            OS.DeleteDC(createCompatibleDC2);
        }
        OS.SelectObject(createCompatibleDC, n);
        OS.DeleteDC(createCompatibleDC);
        device.internal_dispose_GC(internal_new_GC, null);
        final ICONINFO iconinfo = new ICONINFO();
        iconinfo.fIcon = true;
        iconinfo.hbmColor = hbmColor;
        iconinfo.hbmMask = hbmMask;
        final int createIconIndirect = OS.CreateIconIndirect(iconinfo);
        if (createIconIndirect == 0) {
            SWT.error(2);
        }
        OS.DeleteObject(hbmColor);
        OS.DeleteObject(hbmMask);
        return Image.win32_new(device, 1, createIconIndirect);
    }
    
    static int createMaskFromAlpha(final ImageData imageData, final int n, final int n2) {
        final int width = imageData.width;
        final int height = imageData.height;
        final ImageData internal_new = ImageData.internal_new(width, height, 1, new PaletteData(new RGB[] { new RGB(0, 0, 0), new RGB(255, 255, 255) }), 2, null, 1, null, null, -1, -1, -1, 0, 0, 0, 0);
        int n3 = 0;
        for (int i = 0; i < internal_new.height; ++i) {
            for (int j = 0; j < internal_new.width; ++j) {
                internal_new.setPixel(j, i, ((imageData.alphaData[n3++] & 0xFF) <= 127) ? 1 : 0);
            }
        }
        int createBitmap = OS.CreateBitmap(width, height, 1, 1, internal_new.data);
        if (width != n || height != n2) {
            final int getDC = OS.GetDC(0);
            final int createCompatibleDC = OS.CreateCompatibleDC(getDC);
            OS.SelectObject(createCompatibleDC, createBitmap);
            final int createCompatibleDC2 = OS.CreateCompatibleDC(getDC);
            final int createBitmap2 = OS.CreateBitmap(n, n2, 1, 1, null);
            OS.SelectObject(createCompatibleDC2, createBitmap2);
            if (!OS.IsWinCE) {
                OS.SetStretchBltMode(createCompatibleDC2, 3);
            }
            OS.StretchBlt(createCompatibleDC2, 0, 0, n, n2, createCompatibleDC, 0, 0, width, height, 13369376);
            OS.DeleteDC(createCompatibleDC);
            OS.DeleteDC(createCompatibleDC2);
            OS.ReleaseDC(0, getDC);
            OS.DeleteObject(createBitmap);
            createBitmap = createBitmap2;
        }
        return createBitmap;
    }
    
    static void deregister(final Display display) {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            for (int i = 0; i < Display.Displays.length; ++i) {
                if (display == Display.Displays[i]) {
                    Display.Displays[i] = null;
                }
            }
        }
        // monitorexit(clazz3)
    }
    
    protected void destroy() {
        if (this == Display.Default) {
            Display.Default = null;
        }
        deregister(this);
        this.destroyDisplay();
    }
    
    void destroyDisplay() {
    }
    
    public void disposeExec(final Runnable runnable) {
        this.checkDevice();
        if (this.disposeList == null) {
            this.disposeList = new Runnable[4];
        }
        for (int i = 0; i < this.disposeList.length; ++i) {
            if (this.disposeList[i] == null) {
                this.disposeList[i] = runnable;
                return;
            }
        }
        final Runnable[] disposeList = new Runnable[this.disposeList.length + 4];
        System.arraycopy(this.disposeList, 0, disposeList, 0, this.disposeList.length);
        disposeList[this.disposeList.length] = runnable;
        this.disposeList = disposeList;
    }
    
    void drawMenuBars() {
        if (this.bars == null) {
            return;
        }
        for (int i = 0; i < this.bars.length; ++i) {
            final Menu menu = this.bars[i];
            if (menu != null && !menu.isDisposed()) {
                menu.update();
            }
        }
        this.bars = null;
    }
    
    int embeddedProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 32770: {
                final MSG msg = new MSG();
                OS.MoveMemory(msg, n4, MSG.sizeof);
                OS.TranslateMessage(msg);
                OS.DispatchMessage(msg);
                OS.HeapFree(OS.GetProcessHeap(), 0, n4);
                break;
            }
            case 32771: {
                OS.DestroyWindow(n);
                if (this.embeddedCallback != null) {
                    this.embeddedCallback.dispose();
                }
                if (this.getMsgCallback != null) {
                    this.getMsgCallback.dispose();
                }
                final Callback callback = null;
                this.getMsgCallback = callback;
                this.embeddedCallback = callback;
                final boolean b = false;
                this.getMsgProc = (b ? 1 : 0);
                this.embeddedProc = (b ? 1 : 0);
                break;
            }
        }
        return OS.DefWindowProc(n, n2, n3, n4);
    }
    
    void error(final int n) {
        SWT.error(n);
    }
    
    boolean filterEvent(final Event event) {
        if (this.filterTable != null) {
            this.filterTable.sendEvent(event);
        }
        return false;
    }
    
    boolean filters(final int n) {
        return this.filterTable != null && this.filterTable.hooks(n);
    }
    
    boolean filterMessage(final MSG msg) {
        final int message = msg.message;
        if (256 <= message && message <= 264) {
            final Control control = this.findControl(msg.hwnd);
            if (control != null && (this.translateAccelerator(msg, control) || this.translateMnemonic(msg, control) || this.translateTraversal(msg, control))) {
                final boolean b = false;
                this.lastKey = (b ? 1 : 0);
                this.lastAscii = (b ? 1 : 0);
                final boolean lastVirtual = false;
                this.lastDead = lastVirtual;
                this.lastNull = lastVirtual;
                this.lastVirtual = lastVirtual;
                return true;
            }
        }
        return false;
    }
    
    Control findControl(int getParent) {
        if (getParent == 0) {
            return null;
        }
        int getWindow;
        do {
            final Control control = this.getControl(getParent);
            if (control != null) {
                return control;
            }
            getWindow = OS.GetWindow(getParent, 4);
            getParent = OS.GetParent(getParent);
        } while (getParent != 0 && getParent != getWindow);
        return null;
    }
    
    public Widget findWidget(final int n) {
        this.checkDevice();
        return this.getControl(n);
    }
    
    public Widget findWidget(final int n, final int n2) {
        this.checkDevice();
        final Control control = this.getControl(n);
        return (control != null) ? control.findItem(n2) : null;
    }
    
    public Widget findWidget(final Widget widget, final int n) {
        this.checkDevice();
        if (widget instanceof Control) {
            return this.findWidget(((Control)widget).handle, n);
        }
        return null;
    }
    
    int foregroundIdleProc(final int n, final int n2, final int n3) {
        if (n >= 0 && this.runMessages && this.getMessageCount() != 0) {
            if (this.runMessagesInIdle) {
                if (this.runMessagesInMessageProc) {
                    OS.PostMessage(this.hwndMessage, 32774, 0, 0);
                }
                else {
                    this.runAsyncMessages(false);
                }
            }
            if (!OS.PeekMessage(new MSG(), 0, 0, 0, 458754)) {
                this.wakeThread();
            }
        }
        return OS.CallNextHookEx(this.idleHook, n, n2, n3);
    }
    
    public static Display findDisplay(final Thread thread) {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            for (int i = 0; i < Display.Displays.length; ++i) {
                final Display display = Display.Displays[i];
                if (display != null && display.thread == thread) {
                    // monitorexit(clazz3)
                    return display;
                }
            }
            // monitorexit(clazz3)
            return null;
        }
    }
    
    TouchSource findTouchSource(final int n, final Monitor monitor) {
        if (this.touchSources == null) {
            this.touchSources = new TouchSource[4];
        }
        final int length = this.touchSources.length;
        for (int i = 0; i < length; ++i) {
            if (this.touchSources[i] != null && this.touchSources[i].handle == n) {
                return this.touchSources[i];
            }
        }
        int n2;
        for (n2 = 0; n2 < length && this.touchSources[n2] != null; ++n2) {}
        if (n2 == length) {
            final TouchSource[] touchSources = new TouchSource[length + 4];
            System.arraycopy(this.touchSources, 0, touchSources, 0, length);
            this.touchSources = touchSources;
        }
        return this.touchSources[n2] = new TouchSource(n, true, monitor.getBounds());
    }
    
    public Shell getActiveShell() {
        this.checkDevice();
        final Control control = this.findControl(OS.GetActiveWindow());
        return (control != null) ? control.getShell() : null;
    }
    
    public Menu getAppMenuBar() {
        this.checkDevice();
        return null;
    }
    
    public Rectangle getBounds() {
        this.checkDevice();
        if (OS.GetSystemMetrics(80) < 2) {
            return new Rectangle(0, 0, OS.GetSystemMetrics(0), OS.GetSystemMetrics(1));
        }
        return new Rectangle(OS.GetSystemMetrics(76), OS.GetSystemMetrics(77), OS.GetSystemMetrics(78), OS.GetSystemMetrics(79));
    }
    
    public static Display getCurrent() {
        return findDisplay(Thread.currentThread());
    }
    
    int getClickCount(final int n, final int lastButton, final int lastClickHwnd, final int n2) {
        Label_0146: {
            switch (n) {
                case 3: {
                    final int getDoubleClickTime = OS.GetDoubleClickTime();
                    if (this.clickRect == null) {
                        this.clickRect = new RECT();
                    }
                    final int abs = Math.abs(this.lastTime - this.getLastEventTime());
                    final POINT point = new POINT();
                    OS.POINTSTOPOINT(point, n2);
                    if (this.lastClickHwnd == lastClickHwnd && this.lastButton == lastButton && abs <= getDoubleClickTime && OS.PtInRect(this.clickRect, point)) {
                        ++this.clickCount;
                        break Label_0146;
                    }
                    this.clickCount = 1;
                    break Label_0146;
                }
                case 8: {
                    this.lastButton = lastButton;
                    this.lastClickHwnd = lastClickHwnd;
                    this.lastTime = this.getLastEventTime();
                    final int n3 = OS.GetSystemMetrics(36) / 2;
                    final int n4 = OS.GetSystemMetrics(37) / 2;
                    final int get_X_LPARAM = OS.GET_X_LPARAM(n2);
                    final int get_Y_LPARAM = OS.GET_Y_LPARAM(n2);
                    OS.SetRect(this.clickRect, get_X_LPARAM - n3, get_Y_LPARAM - n4, get_X_LPARAM + n3, get_Y_LPARAM + n4);
                }
                case 4: {
                    return this.clickCount;
                }
                default: {
                    return 0;
                }
            }
        }
    }
    
    public Rectangle getClientArea() {
        this.checkDevice();
        if (OS.GetSystemMetrics(80) < 2) {
            final RECT rect = new RECT();
            OS.SystemParametersInfo(48, 0, rect, 0);
            return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
        }
        return new Rectangle(OS.GetSystemMetrics(76), OS.GetSystemMetrics(77), OS.GetSystemMetrics(78), OS.GetSystemMetrics(79));
    }
    
    Control getControl(final int lastGetHwnd) {
        if (lastGetHwnd == 0) {
            return null;
        }
        if (this.lastControl != null && this.lastHwnd == lastGetHwnd) {
            return this.lastControl;
        }
        if (this.lastGetControl != null && this.lastGetHwnd == lastGetHwnd) {
            return this.lastGetControl;
        }
        int n;
        if (Display.USE_PROPERTY) {
            n = OS.GetProp(lastGetHwnd, Display.SWT_OBJECT_INDEX) - 1;
        }
        else {
            n = OS.GetWindowLongPtr(lastGetHwnd, -21) - 1;
        }
        if (n >= 0 && n < this.controlTable.length) {
            final Control lastGetControl = this.controlTable[n];
            if (lastGetControl != null && lastGetControl.checkHandle(lastGetHwnd)) {
                this.lastGetHwnd = lastGetHwnd;
                return this.lastGetControl = lastGetControl;
            }
        }
        return null;
    }
    
    public Control getCursorControl() {
        this.checkDevice();
        final POINT point = new POINT();
        if (!OS.GetCursorPos(point)) {
            return null;
        }
        return this.findControl(OS.WindowFromPoint(point));
    }
    
    public Point getCursorLocation() {
        this.checkDevice();
        final POINT point = new POINT();
        OS.GetCursorPos(point);
        return new Point(point.x, point.y);
    }
    
    public Point[] getCursorSizes() {
        this.checkDevice();
        return new Point[] { new Point(OS.GetSystemMetrics(13), OS.GetSystemMetrics(14)) };
    }
    
    public static Display getDefault() {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (Display.Default == null) {
                Display.Default = new Display();
            }
            // monitorexit(clazz3)
            return Display.Default;
        }
    }
    
    static boolean isValidClass(final Class clazz) {
        final String name = clazz.getName();
        return name.substring(0, name.lastIndexOf(46) + 1).equals("org.eclipse.swt.widgets.");
    }
    
    public Object getData(final String s) {
        this.checkDevice();
        if (s == null) {
            this.error(4);
        }
        if (s.equals("org.eclipse.swt.internal.win32.runMessagesInIdle")) {
            return new Boolean(this.runMessagesInIdle);
        }
        if (s.equals("org.eclipse.swt.internal.win32.runMessagesInMessageProc")) {
            return new Boolean(this.runMessagesInMessageProc);
        }
        if (s.equals("org.eclipse.swt.internal.win32.useOwnDC")) {
            return new Boolean(this.useOwnDC);
        }
        if (s.equals("org.eclipse.swt.internal.win32.accelKeyHit")) {
            return new Boolean(this.accelKeyHit);
        }
        if (this.keys == null) {
            return null;
        }
        for (int i = 0; i < this.keys.length; ++i) {
            if (this.keys[i].equals(s)) {
                return this.values[i];
            }
        }
        return null;
    }
    
    public Object getData() {
        this.checkDevice();
        return this.data;
    }
    
    public int getDismissalAlignment() {
        this.checkDevice();
        return 16384;
    }
    
    public int getDoubleClickTime() {
        this.checkDevice();
        return OS.GetDoubleClickTime();
    }
    
    public Control getFocusControl() {
        this.checkDevice();
        if (this.focusControl != null && !this.focusControl.isDisposed()) {
            return this.focusControl;
        }
        return this._getFocusControl();
    }
    
    String getFontName(final LOGFONT logfont) {
        char[] lfFaceName;
        if (OS.IsUnicode) {
            lfFaceName = ((LOGFONTW)logfont).lfFaceName;
        }
        else {
            lfFaceName = new char[32];
            final byte[] lfFaceName2 = ((LOGFONTA)logfont).lfFaceName;
            OS.MultiByteToWideChar(0, 1, lfFaceName2, lfFaceName2.length, lfFaceName, lfFaceName.length);
        }
        int n;
        for (n = 0; n < lfFaceName.length && lfFaceName[n] != '\0'; ++n) {}
        return new String(lfFaceName, 0, n);
    }
    
    public boolean getHighContrast() {
        this.checkDevice();
        if (OS.IsWinCE) {
            return false;
        }
        final HIGHCONTRAST highcontrast = new HIGHCONTRAST();
        highcontrast.cbSize = HIGHCONTRAST.sizeof;
        OS.SystemParametersInfo(66, 0, highcontrast, 0);
        return (highcontrast.dwFlags & 0x1) != 0x0;
    }
    
    public int getIconDepth() {
        this.checkDevice();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1) && this.getDepth() >= 24) {
            return 32;
        }
        final TCHAR tchar = new TCHAR(0, "Control Panel\\Desktop\\WindowMetrics", true);
        final int[] array = { 0 };
        if (OS.RegOpenKeyEx(-2147483647, tchar, 0, 131097, array) != 0) {
            return 4;
        }
        int int1 = 4;
        final int[] array2 = { 0 };
        final TCHAR tchar2 = new TCHAR(0, "Shell Icon BPP", true);
        if (OS.RegQueryValueEx(array[0], tchar2, 0, null, (TCHAR)null, array2) == 0) {
            final TCHAR tchar3 = new TCHAR(0, array2[0] / TCHAR.sizeof);
            if (OS.RegQueryValueEx(array[0], tchar2, 0, null, tchar3, array2) == 0) {
                try {
                    int1 = Integer.parseInt(tchar3.toString(0, tchar3.strlen()));
                }
                catch (NumberFormatException ex) {}
            }
        }
        OS.RegCloseKey(array[0]);
        return int1;
    }
    
    public Point[] getIconSizes() {
        this.checkDevice();
        return new Point[] { new Point(OS.GetSystemMetrics(49), OS.GetSystemMetrics(50)), new Point(OS.GetSystemMetrics(11), OS.GetSystemMetrics(12)) };
    }
    
    ImageList getImageList(final int n, final int n2, final int n3) {
        if (this.imageList == null) {
            this.imageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.imageList.length; i < length; ++i) {
            final ImageList list = this.imageList[i];
            if (list == null) {
                break;
            }
            final Point imageSize = list.getImageSize();
            if (imageSize.x == n2 && imageSize.y == n3 && list.getStyle() == n) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] imageList = new ImageList[length + 4];
            System.arraycopy(this.imageList, 0, imageList, 0, length);
            this.imageList = imageList;
        }
        final ImageList list2 = new ImageList(n);
        (this.imageList[i] = list2).addRef();
        return list2;
    }
    
    ImageList getImageListToolBar(final int n, final int n2, final int n3) {
        if (this.toolImageList == null) {
            this.toolImageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.toolImageList.length; i < length; ++i) {
            final ImageList list = this.toolImageList[i];
            if (list == null) {
                break;
            }
            final Point imageSize = list.getImageSize();
            if (imageSize.x == n2 && imageSize.y == n3 && list.getStyle() == n) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] toolImageList = new ImageList[length + 4];
            System.arraycopy(this.toolImageList, 0, toolImageList, 0, length);
            this.toolImageList = toolImageList;
        }
        final ImageList list2 = new ImageList(n);
        (this.toolImageList[i] = list2).addRef();
        return list2;
    }
    
    ImageList getImageListToolBarDisabled(final int n, final int n2, final int n3) {
        if (this.toolDisabledImageList == null) {
            this.toolDisabledImageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.toolDisabledImageList.length; i < length; ++i) {
            final ImageList list = this.toolDisabledImageList[i];
            if (list == null) {
                break;
            }
            final Point imageSize = list.getImageSize();
            if (imageSize.x == n2 && imageSize.y == n3 && list.getStyle() == n) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] toolDisabledImageList = new ImageList[length + 4];
            System.arraycopy(this.toolDisabledImageList, 0, toolDisabledImageList, 0, length);
            this.toolDisabledImageList = toolDisabledImageList;
        }
        final ImageList list2 = new ImageList(n);
        (this.toolDisabledImageList[i] = list2).addRef();
        return list2;
    }
    
    ImageList getImageListToolBarHot(final int n, final int n2, final int n3) {
        if (this.toolHotImageList == null) {
            this.toolHotImageList = new ImageList[4];
        }
        int i;
        int length;
        for (i = 0, length = this.toolHotImageList.length; i < length; ++i) {
            final ImageList list = this.toolHotImageList[i];
            if (list == null) {
                break;
            }
            final Point imageSize = list.getImageSize();
            if (imageSize.x == n2 && imageSize.y == n3 && list.getStyle() == n) {
                list.addRef();
                return list;
            }
        }
        if (i == length) {
            final ImageList[] toolHotImageList = new ImageList[length + 4];
            System.arraycopy(this.toolHotImageList, 0, toolHotImageList, 0, length);
            this.toolHotImageList = toolHotImageList;
        }
        final ImageList list2 = new ImageList(n);
        (this.toolHotImageList[i] = list2).addRef();
        return list2;
    }
    
    int getLastEventTime() {
        return OS.IsWinCE ? OS.GetTickCount() : OS.GetMessageTime();
    }
    
    MenuItem getMenuItem(int n) {
        if (this.items == null) {
            return null;
        }
        n -= 108;
        if (n >= 0 && n < this.items.length) {
            return this.items[n];
        }
        return null;
    }
    
    int getMessageCount() {
        return this.synchronizer.getMessageCount();
    }
    
    Shell getModalShell() {
        if (this.modalShells == null) {
            return null;
        }
        int length = this.modalShells.length;
        while (--length >= 0) {
            final Shell shell = this.modalShells[length];
            if (shell != null) {
                return shell;
            }
        }
        return null;
    }
    
    Dialog getModalDialog() {
        return this.modalDialog;
    }
    
    public Monitor[] getMonitors() {
        this.checkDevice();
        if (OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            return new Monitor[] { this.getPrimaryMonitor() };
        }
        this.monitors = new Monitor[4];
        final Callback callback = new Callback(this, "monitorEnumProc", 4);
        final int address = callback.getAddress();
        if (address == 0) {
            SWT.error(3);
        }
        OS.EnumDisplayMonitors(0, null, address, 0);
        callback.dispose();
        final Monitor[] array = new Monitor[this.monitorCount];
        System.arraycopy(this.monitors, 0, array, 0, this.monitorCount);
        this.monitors = null;
        this.monitorCount = 0;
        return array;
    }
    
    int getMsgProc(final int n, final int n2, final int n3) {
        if (this.embeddedHwnd == 0) {
            this.embeddedHwnd = OS.CreateWindowEx(0, this.windowClass, null, OS.WS_OVERLAPPED, 0, 0, 0, 0, 0, 0, OS.GetModuleHandle(null), null);
            this.embeddedCallback = new Callback(this, "embeddedProc", 4);
            this.embeddedProc = this.embeddedCallback.getAddress();
            if (this.embeddedProc == 0) {
                this.error(3);
            }
            OS.SetWindowLongPtr(this.embeddedHwnd, -4, this.embeddedProc);
        }
        Label_0302: {
            if (n >= 0 && (n2 & 0x1) != 0x0) {
                final MSG msg = new MSG();
                OS.MoveMemory(msg, n3, MSG.sizeof);
                switch (msg.message) {
                    case 256:
                    case 257:
                    case 260:
                    case 261: {
                        if (this.findControl(msg.hwnd) == null) {
                            break;
                        }
                        final int heapAlloc = OS.HeapAlloc(OS.GetProcessHeap(), 8, MSG.sizeof);
                        OS.MoveMemory(heapAlloc, msg, MSG.sizeof);
                        OS.PostMessage(this.hwndMessage, 32770, n2, heapAlloc);
                        switch (msg.wParam) {
                            case 16:
                            case 17:
                            case 18:
                            case 20:
                            case 144:
                            case 145: {
                                break Label_0302;
                            }
                            default: {
                                msg.message = 0;
                                OS.MoveMemory(n3, msg, MSG.sizeof);
                                break Label_0302;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return OS.CallNextHookEx(this.msgHook, n, n2, n3);
    }
    
    public Monitor getPrimaryMonitor() {
        this.checkDevice();
        if (OS.IsWinCE || OS.WIN32_VERSION < OS.VERSION(4, 10)) {
            final Monitor monitor = new Monitor();
            final int getSystemMetrics = OS.GetSystemMetrics(0);
            final int getSystemMetrics2 = OS.GetSystemMetrics(1);
            monitor.width = getSystemMetrics;
            monitor.height = getSystemMetrics2;
            final RECT rect = new RECT();
            OS.SystemParametersInfo(48, 0, rect, 0);
            monitor.clientX = rect.left;
            monitor.clientY = rect.top;
            monitor.clientWidth = rect.right - rect.left;
            monitor.clientHeight = rect.bottom - rect.top;
            return monitor;
        }
        this.monitors = new Monitor[4];
        final Callback callback = new Callback(this, "monitorEnumProc", 4);
        final int address = callback.getAddress();
        if (address == 0) {
            SWT.error(3);
        }
        OS.EnumDisplayMonitors(0, null, address, 0);
        callback.dispose();
        Monitor monitor2 = null;
        final MONITORINFO monitorinfo = new MONITORINFO();
        monitorinfo.cbSize = MONITORINFO.sizeof;
        for (int i = 0; i < this.monitorCount; ++i) {
            final Monitor monitor3 = this.monitors[i];
            OS.GetMonitorInfo(this.monitors[i].handle, monitorinfo);
            if ((monitorinfo.dwFlags & 0x1) != 0x0) {
                monitor2 = monitor3;
                break;
            }
        }
        this.monitors = null;
        this.monitorCount = 0;
        return monitor2;
    }
    
    public Shell[] getShells() {
        this.checkDevice();
        int n = 0;
        Shell[] array = new Shell[16];
        for (int i = 0; i < this.controlTable.length; ++i) {
            final Control control = this.controlTable[i];
            if (control != null && control instanceof Shell) {
                int n2;
                for (n2 = 0; n2 < n && array[n2] != control; ++n2) {}
                if (n2 == n) {
                    if (n == array.length) {
                        final Shell[] array2 = new Shell[n + 16];
                        System.arraycopy(array, 0, array2, 0, n);
                        array = array2;
                    }
                    array[n++] = (Shell)control;
                }
            }
        }
        if (n == array.length) {
            return array;
        }
        final Shell[] array3 = new Shell[n];
        System.arraycopy(array, 0, array3, 0, n);
        return array3;
    }
    
    Image getSortImage(final int n) {
        switch (n) {
            case 128: {
                if (this.upArrow != null) {
                    return this.upArrow;
                }
                final Color systemColor = this.getSystemColor(18);
                final Color systemColor2 = this.getSystemColor(20);
                final Color systemColor3 = this.getSystemColor(22);
                final ImageData imageData = new ImageData(8, 8, 4, new PaletteData(new RGB[] { systemColor.getRGB(), systemColor2.getRGB(), systemColor3.getRGB() }));
                imageData.transparentPixel = 2;
                this.upArrow = new Image(this, imageData);
                final GC gc = new GC(this.upArrow);
                gc.setBackground(systemColor3);
                gc.fillRectangle(0, 0, 8, 8);
                gc.setForeground(systemColor);
                gc.drawPolyline(new int[] { 0, 6, 1, 6, 1, 4, 2, 4, 2, 2, 3, 2, 3, 1 });
                gc.setForeground(systemColor2);
                gc.drawPolyline(new int[] { 0, 7, 7, 7, 7, 6, 6, 6, 6, 4, 5, 4, 5, 2, 4, 2, 4, 1 });
                gc.dispose();
                return this.upArrow;
            }
            case 1024: {
                if (this.downArrow != null) {
                    return this.downArrow;
                }
                final Color systemColor4 = this.getSystemColor(18);
                final Color systemColor5 = this.getSystemColor(20);
                final Color systemColor6 = this.getSystemColor(22);
                final ImageData imageData2 = new ImageData(8, 8, 4, new PaletteData(new RGB[] { systemColor4.getRGB(), systemColor5.getRGB(), systemColor6.getRGB() }));
                imageData2.transparentPixel = 2;
                this.downArrow = new Image(this, imageData2);
                final GC gc2 = new GC(this.downArrow);
                gc2.setBackground(systemColor6);
                gc2.fillRectangle(0, 0, 8, 8);
                gc2.setForeground(systemColor4);
                gc2.drawPolyline(new int[] { 7, 0, 0, 0, 0, 1, 1, 1, 1, 3, 2, 3, 2, 5, 3, 5, 3, 6 });
                gc2.setForeground(systemColor5);
                gc2.drawPolyline(new int[] { 4, 6, 4, 5, 5, 5, 5, 3, 6, 3, 6, 1, 7, 1 });
                gc2.dispose();
                return this.downArrow;
            }
            default: {
                return null;
            }
        }
    }
    
    public Synchronizer getSynchronizer() {
        this.checkDevice();
        return this.synchronizer;
    }
    
    public Thread getSyncThread() {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (this.isDisposed()) {
                this.error(45);
            }
            // monitorexit(clazz3)
            return this.synchronizer.syncThread;
        }
    }
    
    public Color getSystemColor(final int n) {
        this.checkDevice();
        int n2 = 0;
        switch (n) {
            case 17: {
                n2 = OS.GetSysColor(OS.COLOR_3DDKSHADOW);
                break;
            }
            case 18: {
                n2 = OS.GetSysColor(OS.COLOR_3DSHADOW);
                break;
            }
            case 19: {
                n2 = OS.GetSysColor(OS.COLOR_3DLIGHT);
                break;
            }
            case 20: {
                n2 = OS.GetSysColor(OS.COLOR_3DHIGHLIGHT);
                break;
            }
            case 22: {
                n2 = OS.GetSysColor(OS.COLOR_3DFACE);
                break;
            }
            case 23: {
                n2 = OS.GetSysColor(OS.COLOR_WINDOWFRAME);
                break;
            }
            case 21:
            case 24: {
                n2 = OS.GetSysColor(OS.COLOR_WINDOWTEXT);
                break;
            }
            case 25: {
                n2 = OS.GetSysColor(OS.COLOR_WINDOW);
                break;
            }
            case 26: {
                n2 = OS.GetSysColor(OS.COLOR_HIGHLIGHT);
                break;
            }
            case 27: {
                n2 = OS.GetSysColor(OS.COLOR_HIGHLIGHTTEXT);
                break;
            }
            case 28: {
                n2 = OS.GetSysColor(OS.COLOR_INFOTEXT);
                break;
            }
            case 29: {
                n2 = OS.GetSysColor(OS.COLOR_INFOBK);
                break;
            }
            case 30: {
                n2 = OS.GetSysColor(OS.COLOR_CAPTIONTEXT);
                break;
            }
            case 31: {
                n2 = OS.GetSysColor(OS.COLOR_ACTIVECAPTION);
                break;
            }
            case 32: {
                n2 = OS.GetSysColor(OS.COLOR_GRADIENTACTIVECAPTION);
                if (n2 == 0) {
                    n2 = OS.GetSysColor(OS.COLOR_ACTIVECAPTION);
                    break;
                }
                break;
            }
            case 33: {
                n2 = OS.GetSysColor(OS.COLOR_INACTIVECAPTIONTEXT);
                break;
            }
            case 34: {
                n2 = OS.GetSysColor(OS.COLOR_INACTIVECAPTION);
                break;
            }
            case 35: {
                n2 = OS.GetSysColor(OS.COLOR_GRADIENTINACTIVECAPTION);
                if (n2 == 0) {
                    n2 = OS.GetSysColor(OS.COLOR_INACTIVECAPTION);
                    break;
                }
                break;
            }
            default: {
                return super.getSystemColor(n);
            }
        }
        return Color.win32_new(this, n2);
    }
    
    public Cursor getSystemCursor(final int n) {
        this.checkDevice();
        if (n < 0 || n >= this.cursors.length) {
            return null;
        }
        if (this.cursors[n] == null) {
            this.cursors[n] = new Cursor(this, n);
        }
        return this.cursors[n];
    }
    
    public Font getSystemFont() {
        this.checkDevice();
        if (this.systemFont != null) {
            return this.systemFont;
        }
        int n = 0;
        if (!OS.IsWinCE) {
            final NONCLIENTMETRICS nonclientmetrics = OS.IsUnicode ? new NONCLIENTMETRICSW() : new NONCLIENTMETRICSA();
            nonclientmetrics.cbSize = NONCLIENTMETRICS.sizeof;
            if (OS.SystemParametersInfo(41, 0, nonclientmetrics, 0)) {
                final LOGFONT logfont = OS.IsUnicode ? ((NONCLIENTMETRICSW)nonclientmetrics).lfMessageFont : ((NONCLIENTMETRICSA)nonclientmetrics).lfMessageFont;
                n = OS.CreateFontIndirect(logfont);
                this.lfSystemFont = ((n != 0) ? logfont : null);
            }
        }
        if (n == 0) {
            n = OS.GetStockObject(17);
        }
        if (n == 0) {
            n = OS.GetStockObject(13);
        }
        return this.systemFont = Font.win32_new(this, n);
    }
    
    public Image getSystemImage(final int n) {
        this.checkDevice();
        switch (n) {
            case 1: {
                if (this.errorImage != null) {
                    return this.errorImage;
                }
                return this.errorImage = Image.win32_new(this, 1, OS.LoadImage(0, 32513, 1, 0, 0, 32768));
            }
            case 2:
            case 16: {
                if (this.infoImage != null) {
                    return this.infoImage;
                }
                return this.infoImage = Image.win32_new(this, 1, OS.LoadImage(0, 32516, 1, 0, 0, 32768));
            }
            case 4: {
                if (this.questionImage != null) {
                    return this.questionImage;
                }
                return this.questionImage = Image.win32_new(this, 1, OS.LoadImage(0, 32514, 1, 0, 0, 32768));
            }
            case 8: {
                if (this.warningIcon != null) {
                    return this.warningIcon;
                }
                return this.warningIcon = Image.win32_new(this, 1, OS.LoadImage(0, 32515, 1, 0, 0, 32768));
            }
            default: {
                return null;
            }
        }
    }
    
    public TaskBar getSystemTaskBar() {
        this.checkDevice();
        if (this.taskBar != null) {
            return this.taskBar;
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 1)) {
            this.taskBar = new TaskBar(this, 0);
        }
        return this.taskBar;
    }
    
    public Tray getSystemTray() {
        this.checkDevice();
        if (this.tray != null) {
            return this.tray;
        }
        if (!OS.IsWinCE) {
            this.tray = new Tray(this, 0);
        }
        return this.tray;
    }
    
    public Thread getThread() {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (this.isDisposed()) {
                this.error(45);
            }
            // monitorexit(clazz3)
            return this.thread;
        }
    }
    
    int hButtonTheme() {
        if (this.hButtonTheme != 0) {
            return this.hButtonTheme;
        }
        return this.hButtonTheme = OS.OpenThemeData(this.hwndMessage, Display.BUTTON);
    }
    
    int hEditTheme() {
        if (this.hEditTheme != 0) {
            return this.hEditTheme;
        }
        return this.hEditTheme = OS.OpenThemeData(this.hwndMessage, Display.EDIT);
    }
    
    int hExplorerBarTheme() {
        if (this.hExplorerBarTheme != 0) {
            return this.hExplorerBarTheme;
        }
        return this.hExplorerBarTheme = OS.OpenThemeData(this.hwndMessage, Display.EXPLORERBAR);
    }
    
    int hScrollBarTheme() {
        if (this.hScrollBarTheme != 0) {
            return this.hScrollBarTheme;
        }
        return this.hScrollBarTheme = OS.OpenThemeData(this.hwndMessage, Display.SCROLLBAR);
    }
    
    int hTabTheme() {
        if (this.hTabTheme != 0) {
            return this.hTabTheme;
        }
        return this.hTabTheme = OS.OpenThemeData(this.hwndMessage, Display.TAB);
    }
    
    public int internal_new_GC(final GCData gcData) {
        if (this.isDisposed()) {
            SWT.error(45);
        }
        final int getDC = OS.GetDC(0);
        if (getDC == 0) {
            SWT.error(2);
        }
        if (gcData != null) {
            if ((gcData.style & 0x6000000) != 0x0) {
                gcData.layout = (((gcData.style & 0x4000000) != 0x0) ? 1 : 0);
            }
            else {
                gcData.style |= 0x2000000;
            }
            gcData.device = this;
            gcData.font = this.getSystemFont();
        }
        return getDC;
    }
    
    protected void init() {
        super.init();
        if (Display.APP_NAME != null && !OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 1)) {
            final int length = Display.APP_NAME.length();
            final char[] array = new char[length + 1];
            Display.APP_NAME.getChars(0, length, array, 0);
            OS.SetCurrentProcessExplicitAppUserModelID(array);
        }
        this.windowCallback = new Callback(this, "windowProc", 4);
        this.windowProc = this.windowCallback.getAddress();
        if (this.windowProc == 0) {
            this.error(3);
        }
        this.threadId = OS.GetCurrentThreadId();
        this.windowClass = new TCHAR(0, "SWT_Window" + Display.WindowClassCount, true);
        this.windowShadowClass = new TCHAR(0, "SWT_WindowShadow" + Display.WindowClassCount, true);
        this.windowOwnDCClass = new TCHAR(0, "SWT_WindowOwnDC" + Display.WindowClassCount, true);
        ++Display.WindowClassCount;
        final int getProcessHeap = OS.GetProcessHeap();
        final int getModuleHandle = OS.GetModuleHandle(null);
        final WNDCLASS wndclass = new WNDCLASS();
        wndclass.hInstance = getModuleHandle;
        wndclass.lpfnWndProc = this.windowProc;
        wndclass.style = 8200;
        wndclass.hCursor = OS.LoadCursor(0, 32512);
        if (!OS.IsWinCE && Library.JAVA_VERSION >= Library.JAVA_VERSION(1, 6, 0)) {
            TCHAR tchar;
            for (tchar = new TCHAR(0, 260); OS.GetModuleFileName(0, tchar, tchar.length()) == tchar.length(); tchar = new TCHAR(0, tchar.length() + 260)) {}
            if (OS.ExtractIconEx(tchar, -1, null, null, 1) != 0) {
                final String string = tchar.toString(0, tchar.strlen());
                if (string.endsWith("java.exe") || string.endsWith("javaw.exe")) {
                    wndclass.hIcon = OS.LoadIcon(0, 32512);
                }
            }
        }
        final int n = this.windowClass.length() * TCHAR.sizeof;
        OS.MoveMemory(wndclass.lpszClassName = OS.HeapAlloc(getProcessHeap, 8, n), this.windowClass, n);
        OS.RegisterClass(wndclass);
        OS.HeapFree(getProcessHeap, 0, wndclass.lpszClassName);
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            final WNDCLASS wndclass2 = wndclass;
            wndclass2.style |= 0x20000;
        }
        final int n2 = this.windowShadowClass.length() * TCHAR.sizeof;
        OS.MoveMemory(wndclass.lpszClassName = OS.HeapAlloc(getProcessHeap, 8, n2), this.windowShadowClass, n2);
        OS.RegisterClass(wndclass);
        OS.HeapFree(getProcessHeap, 0, wndclass.lpszClassName);
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(5, 1)) {
            final WNDCLASS wndclass3 = wndclass;
            wndclass3.style |= 0x20;
        }
        final int n3 = this.windowOwnDCClass.length() * TCHAR.sizeof;
        OS.MoveMemory(wndclass.lpszClassName = OS.HeapAlloc(getProcessHeap, 8, n3), this.windowOwnDCClass, n3);
        OS.RegisterClass(wndclass);
        OS.HeapFree(getProcessHeap, 0, wndclass.lpszClassName);
        OS.SetWindowText(this.hwndMessage = OS.CreateWindowEx(0, this.windowClass, null, OS.WS_OVERLAPPED, 0, 0, 0, 0, 0, 0, getModuleHandle, null), new TCHAR(0, "SWT_Window_" + Display.APP_NAME, true));
        this.messageCallback = new Callback(this, "messageProc", 4);
        this.messageProc = this.messageCallback.getAddress();
        if (this.messageProc == 0) {
            this.error(3);
        }
        OS.SetWindowLongPtr(this.hwndMessage, -4, this.messageProc);
        if (!OS.IsWinCE) {
            this.msgFilterCallback = new Callback(this, "msgFilterProc", 3);
            this.msgFilterProc = this.msgFilterCallback.getAddress();
            if (this.msgFilterProc == 0) {
                this.error(3);
            }
            this.filterHook = OS.SetWindowsHookEx(-1, this.msgFilterProc, 0, this.threadId);
        }
        if (!OS.IsWinCE) {
            this.foregroundIdleCallback = new Callback(this, "foregroundIdleProc", 3);
            this.foregroundIdleProc = this.foregroundIdleCallback.getAddress();
            if (this.foregroundIdleProc == 0) {
                this.error(3);
            }
            this.idleHook = OS.SetWindowsHookEx(11, this.foregroundIdleProc, 0, this.threadId);
        }
        Display.TASKBARCREATED = OS.RegisterWindowMessage(new TCHAR(0, "TaskbarCreated", true));
        Display.TASKBARBUTTONCREATED = OS.RegisterWindowMessage(new TCHAR(0, "TaskbarButtonCreated", true));
        Display.SWT_RESTORECARET = OS.RegisterWindowMessage(new TCHAR(0, "SWT_RESTORECARET", true));
        Display.DI_GETDRAGIMAGE = OS.RegisterWindowMessage(new TCHAR(0, "ShellGetDragImage", true));
        Display.SWT_OPENDOC = OS.RegisterWindowMessage(new TCHAR(0, "SWT_OPENDOC", true));
        if (!OS.IsWinCE) {
            OS.OleInitialize(0);
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            OS.BufferedPaintInit();
        }
        this.indexTable = new int[1024];
        this.controlTable = new Control[1024];
        for (int i = 0; i < 1023; ++i) {
            this.indexTable[i] = i + 1;
        }
        this.indexTable[1023] = -1;
        this.lastHighContrast = this.getHighContrast();
    }
    
    public void internal_dispose_GC(final int n, final GCData gcData) {
        OS.ReleaseDC(0, n);
    }
    
    public boolean isTouchEnabled() {
        return (OS.GetSystemMetrics(94) & 0xC0) != 0x0;
    }
    
    boolean isXMouseActive() {
        boolean b = false;
        final TCHAR tchar = new TCHAR(0, "Control Panel\\Desktop", true);
        final int[] array = { 0 };
        if (OS.RegOpenKeyEx(-2147483647, tchar, 0, 131097, array) == 0) {
            final TCHAR tchar2 = new TCHAR(0, "UserPreferencesMask", true);
            final int[] array2 = { 4 };
            final int[] array3 = { 0 };
            if (OS.RegQueryValueEx(array[0], tchar2, 0, null, array3, array2) == 0) {
                b = ((array3[0] & 0x1) != 0x0);
            }
            OS.RegCloseKey(array[0]);
        }
        return b;
    }
    
    boolean isValidThread() {
        return this.thread == Thread.currentThread();
    }
    
    public Point map(final Control control, final Control control2, final Point point) {
        this.checkDevice();
        if (point == null) {
            this.error(4);
        }
        return this.map(control, control2, point.x, point.y);
    }
    
    public Point map(final Control control, final Control control2, final int x, final int y) {
        this.checkDevice();
        if (control != null && control.isDisposed()) {
            this.error(5);
        }
        if (control2 != null && control2.isDisposed()) {
            this.error(5);
        }
        if (control == control2) {
            return new Point(x, y);
        }
        final int n = (control != null) ? control.handle : 0;
        final int n2 = (control2 != null) ? control2.handle : 0;
        final POINT point = new POINT();
        point.x = x;
        point.y = y;
        OS.MapWindowPoints(n, n2, point, 1);
        return new Point(point.x, point.y);
    }
    
    public Rectangle map(final Control control, final Control control2, final Rectangle rectangle) {
        this.checkDevice();
        if (rectangle == null) {
            this.error(4);
        }
        return this.map(control, control2, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public Rectangle map(final Control control, final Control control2, final int left, final int top, final int n, final int n2) {
        this.checkDevice();
        if (control != null && control.isDisposed()) {
            this.error(5);
        }
        if (control2 != null && control2.isDisposed()) {
            this.error(5);
        }
        if (control == control2) {
            return new Rectangle(left, top, n, n2);
        }
        final int n3 = (control != null) ? control.handle : 0;
        final int n4 = (control2 != null) ? control2.handle : 0;
        final RECT rect = new RECT();
        rect.left = left;
        rect.top = top;
        rect.right = left + n;
        rect.bottom = top + n2;
        OS.MapWindowPoints(n3, n4, rect, 2);
        return new Rectangle(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
    }
    
    static char mbcsToWcs(final int n) {
        return mbcsToWcs(n, 0);
    }
    
    static char mbcsToWcs(final int n, final int n2) {
        if (OS.IsUnicode) {
            return (char)n;
        }
        final int n3 = n & 0xFFFF;
        if (n3 <= 127) {
            return (char)n;
        }
        byte[] array;
        if (n3 <= 255) {
            array = new byte[] { (byte)n3 };
        }
        else {
            array = new byte[] { (byte)(n3 >> 8 & 0xFF), (byte)(n3 & 0xFF) };
        }
        final char[] array2 = { '\0' };
        if (OS.MultiByteToWideChar((n2 != 0) ? n2 : 0, 1, array, array.length, array2, 1) == 0) {
            return '\0';
        }
        return array2[0];
    }
    
    int messageProc(final int n, final int n2, final int n3, final int n4) {
        switch (n2) {
            case 32774: {
                if (this.runMessagesInIdle) {
                    this.runAsyncMessages(false);
                    break;
                }
                break;
            }
            case 32770: {
                boolean b = false;
                final MSG msg = new MSG();
                OS.MoveMemory(msg, n4, MSG.sizeof);
                final Control control = this.findControl(msg.hwnd);
                Label_0661: {
                    if (control != null) {
                        boolean ignoreNextKey = false;
                        Label_0461: {
                            switch (msg.message) {
                                case 256:
                                case 260: {
                                    if (OS.IsWinCE) {
                                        break;
                                    }
                                    switch (msg.wParam) {
                                        case 16:
                                        case 17:
                                        case 18:
                                        case 20:
                                        case 144:
                                        case 145: {
                                            break Label_0461;
                                        }
                                        default: {
                                            final int mapVirtualKey = OS.MapVirtualKey(msg.wParam, 2);
                                            if (mapVirtualKey == 0) {
                                                break Label_0461;
                                            }
                                            ignoreNextKey = ((mapVirtualKey & (OS.IsWinNT ? Integer.MIN_VALUE : 32768)) != 0x0);
                                            if (!ignoreNextKey) {
                                                for (int i = 0; i < Display.ACCENTS.length; ++i) {
                                                    final short vkKeyScan = OS.VkKeyScan(Display.ACCENTS[i]);
                                                    if (vkKeyScan != -1 && (vkKeyScan & 0xFF) == msg.wParam) {
                                                        final int n5 = vkKeyScan >> 8;
                                                        if (OS.GetKeyState(16) < 0 == ((n5 & 0x1) != 0x0) && OS.GetKeyState(17) < 0 == ((n5 & 0x2) != 0x0) && OS.GetKeyState(18) < 0 == ((n5 & 0x4) != 0x0)) {
                                                            if ((n5 & 0x7) != 0x0) {
                                                                ignoreNextKey = true;
                                                                break;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                }
                                                break Label_0461;
                                            }
                                            break Label_0461;
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if (!ignoreNextKey && !this.ignoreNextKey) {
                            msg.hwnd = control.handle;
                            do {
                                if (!(b |= this.filterMessage(msg))) {
                                    OS.TranslateMessage(msg);
                                    b |= (OS.DispatchMessage(msg) == 1);
                                }
                            } while (OS.PeekMessage(msg, msg.hwnd, 256, 264, 10420227));
                        }
                        switch (msg.message) {
                            case 256:
                            case 260: {
                                switch (msg.wParam) {
                                    case 16:
                                    case 17:
                                    case 18:
                                    case 20:
                                    case 144:
                                    case 145: {
                                        break Label_0661;
                                    }
                                    default: {
                                        this.ignoreNextKey = ignoreNextKey;
                                        break Label_0661;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                switch (msg.wParam) {
                    case 16:
                    case 17:
                    case 18:
                    case 20:
                    case 144:
                    case 145: {
                        b = true;
                        break;
                    }
                }
                if (b) {
                    OS.HeapFree(OS.GetProcessHeap(), 0, n4);
                }
                else {
                    OS.PostMessage(this.embeddedHwnd, 32770, n3, n4);
                }
                return 0;
            }
            case 32772: {
                if (this.tray != null) {
                    final TrayItem[] items = this.tray.items;
                    for (int j = 0; j < items.length; ++j) {
                        final TrayItem trayItem = items[j];
                        if (trayItem != null && trayItem.id == n3) {
                            return trayItem.messageProc(n, n2, n3, n4);
                        }
                    }
                }
                return 0;
            }
            case 28: {
                if (n3 == 0 || this.isXMouseActive()) {
                    break;
                }
                final int getActiveWindow = OS.GetActiveWindow();
                if (getActiveWindow != 0 && OS.IsWindowEnabled(getActiveWindow)) {
                    break;
                }
                final Shell shell = (this.modalDialog != null) ? this.modalDialog.parent : this.getModalShell();
                if (shell == null || shell.isDisposed()) {
                    break;
                }
                final int handle = shell.handle;
                if (OS.IsWindowEnabled(handle)) {
                    shell.bringToTop();
                    if (shell.isDisposed()) {
                        break;
                    }
                }
                final int getLastActivePopup = OS.GetLastActivePopup(handle);
                if (getLastActivePopup != 0 && getLastActivePopup != shell.handle && this.getControl(getLastActivePopup) == null && OS.IsWindowEnabled(getLastActivePopup)) {
                    OS.SetActiveWindow(getLastActivePopup);
                    break;
                }
                break;
            }
            case 22: {
                if (n3 != 0) {
                    this.dispose();
                    break;
                }
                break;
            }
            case 17: {
                final Event event = new Event();
                this.sendEvent(21, event);
                if (!event.doit) {
                    return 0;
                }
                break;
            }
            case 800: {
                this.sendSettings = true;
            }
            case 26: {
                if (this.lastHighContrast != this.getHighContrast()) {
                    this.sendSettings = true;
                    this.lastHighContrast = this.getHighContrast();
                }
                if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
                    this.sendSettings = true;
                }
                switch (n3) {
                    case 0:
                    case 1:
                    case 67: {
                        this.sendSettings = true;
                        this.lastHighContrast = this.getHighContrast();
                        break;
                    }
                }
                if (this.sendSettings) {
                    OS.SetTimer(this.hwndMessage, 100, 2000, 0);
                    break;
                }
                break;
            }
            case 794: {
                if (OS.COMCTL32_MAJOR >= 6) {
                    if (this.hButtonTheme != 0) {
                        OS.CloseThemeData(this.hButtonTheme);
                    }
                    if (this.hEditTheme != 0) {
                        OS.CloseThemeData(this.hEditTheme);
                    }
                    if (this.hExplorerBarTheme != 0) {
                        OS.CloseThemeData(this.hExplorerBarTheme);
                    }
                    if (this.hScrollBarTheme != 0) {
                        OS.CloseThemeData(this.hScrollBarTheme);
                    }
                    if (this.hTabTheme != 0) {
                        OS.CloseThemeData(this.hTabTheme);
                    }
                    final boolean hButtonTheme = false;
                    this.hTabTheme = (hButtonTheme ? 1 : 0);
                    this.hScrollBarTheme = (hButtonTheme ? 1 : 0);
                    this.hExplorerBarTheme = (hButtonTheme ? 1 : 0);
                    this.hEditTheme = (hButtonTheme ? 1 : 0);
                    this.hButtonTheme = (hButtonTheme ? 1 : 0);
                    break;
                }
                break;
            }
            case 275: {
                if (n3 == 100) {
                    this.sendSettings = false;
                    OS.KillTimer(this.hwndMessage, 100);
                    this.runSettings();
                    break;
                }
                this.runTimer(n3);
                break;
            }
            default: {
                if (n2 == Display.TASKBARCREATED && this.tray != null) {
                    final TrayItem[] items2 = this.tray.items;
                    for (int k = 0; k < items2.length; ++k) {
                        final TrayItem trayItem2 = items2[k];
                        if (trayItem2 != null) {
                            trayItem2.recreate();
                        }
                    }
                }
                if (n2 != Display.SWT_OPENDOC) {
                    break;
                }
                final String sharedData = this.getSharedData(n3, n4);
                if (sharedData != null) {
                    if (sharedData.startsWith("/SWTINTERNAL_ID")) {
                        final MenuItem menuItem = this.getMenuItem(Integer.parseInt(sharedData.substring("/SWTINTERNAL_ID".length())));
                        if (menuItem != null) {
                            menuItem.sendSelectionEvent(13);
                        }
                    }
                    else {
                        final Event event2 = new Event();
                        event2.text = sharedData;
                        this.sendEvent(46, event2);
                    }
                    this.wakeThread();
                    break;
                }
                break;
            }
        }
        return OS.DefWindowProc(n, n2, n3, n4);
    }
    
    String getSharedData(final int n, final int n2) {
        if (OS.IsWinCE) {
            return null;
        }
        final int[] array = { 0 };
        if (n == OS.GetCurrentProcessId()) {
            array[0] = n2;
        }
        else {
            final int openProcess = OS.OpenProcess(80, false, n);
            if (openProcess == 0) {
                return null;
            }
            OS.DuplicateHandle(openProcess, n2, OS.GetCurrentProcess(), array, 2, false, 2);
            OS.CloseHandle(openProcess);
        }
        final int mapViewOfFile = OS.MapViewOfFile(array[0], 4, 0, 0, 0);
        if (mapViewOfFile == 0) {
            return null;
        }
        final int n3 = OS.IsUnicode ? OS.wcslen(mapViewOfFile) : C.strlen(mapViewOfFile);
        final TCHAR tchar = new TCHAR(0, n3);
        OS.MoveMemory(tchar, mapViewOfFile, tchar.length() * TCHAR.sizeof);
        final String string = tchar.toString(0, n3);
        OS.UnmapViewOfFile(mapViewOfFile);
        if (n2 != array[0]) {
            OS.CloseHandle(array[0]);
        }
        return string;
    }
    
    int monitorEnumProc(final int handle, final int n, final int n2, final int n3) {
        if (this.monitorCount >= this.monitors.length) {
            final Monitor[] monitors = new Monitor[this.monitors.length + 4];
            System.arraycopy(this.monitors, 0, monitors, 0, this.monitors.length);
            this.monitors = monitors;
        }
        final MONITORINFO monitorinfo = new MONITORINFO();
        monitorinfo.cbSize = MONITORINFO.sizeof;
        OS.GetMonitorInfo(handle, monitorinfo);
        final Monitor monitor = new Monitor();
        monitor.handle = handle;
        monitor.x = monitorinfo.rcMonitor_left;
        monitor.y = monitorinfo.rcMonitor_top;
        monitor.width = monitorinfo.rcMonitor_right - monitorinfo.rcMonitor_left;
        monitor.height = monitorinfo.rcMonitor_bottom - monitorinfo.rcMonitor_top;
        monitor.clientX = monitorinfo.rcWork_left;
        monitor.clientY = monitorinfo.rcWork_top;
        monitor.clientWidth = monitorinfo.rcWork_right - monitorinfo.rcWork_left;
        monitor.clientHeight = monitorinfo.rcWork_bottom - monitorinfo.rcWork_top;
        this.monitors[this.monitorCount++] = monitor;
        return 1;
    }
    
    int msgFilterProc(final int n, final int n2, final int n3) {
        switch (n) {
            case 16896: {
                if (this.runDragDrop || this.dragCancelled) {
                    break;
                }
                OS.MoveMemory(this.hookMsg, n3, MSG.sizeof);
                if (this.hookMsg.message == 512) {
                    this.dragCancelled = true;
                    OS.SendMessage(this.hookMsg.hwnd, 31, 0, 0);
                    break;
                }
                break;
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 8: {
                if (!this.runMessages) {
                    break;
                }
                OS.MoveMemory(this.hookMsg, n3, MSG.sizeof);
                if (this.hookMsg.message == 0 && !OS.PeekMessage(new MSG(), 0, 0, 0, 10420226) && this.runAsyncMessages(false)) {
                    this.wakeThread();
                    break;
                }
                break;
            }
        }
        return OS.CallNextHookEx(this.filterHook, n, n2, n3);
    }
    
    int numpadKey(final int n) {
        switch (n) {
            case 96: {
                return 48;
            }
            case 97: {
                return 49;
            }
            case 98: {
                return 50;
            }
            case 99: {
                return 51;
            }
            case 100: {
                return 52;
            }
            case 101: {
                return 53;
            }
            case 102: {
                return 54;
            }
            case 103: {
                return 55;
            }
            case 104: {
                return 56;
            }
            case 105: {
                return 57;
            }
            case 106: {
                return 42;
            }
            case 107: {
                return 43;
            }
            case 108: {
                return 0;
            }
            case 109: {
                return 45;
            }
            case 110: {
                return 46;
            }
            case 111: {
                return 47;
            }
            default: {
                return 0;
            }
        }
    }
    
    public boolean post(final Event event) {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (this.isDisposed()) {
                this.error(45);
            }
            if (event == null) {
                this.error(4);
            }
            final int type = event.type;
            switch (type) {
                case 1:
                case 2: {
                    final KEYBDINPUT keybdinput = new KEYBDINPUT();
                    keybdinput.wVk = (short)untranslateKey(event.keyCode);
                    if (keybdinput.wVk == 0) {
                        final char character = event.character;
                        switch (character) {
                            case 8: {
                                keybdinput.wVk = 8;
                                break;
                            }
                            case 13: {
                                keybdinput.wVk = 13;
                                break;
                            }
                            case 127: {
                                keybdinput.wVk = 46;
                                break;
                            }
                            case 27: {
                                keybdinput.wVk = 27;
                                break;
                            }
                            case 9: {
                                keybdinput.wVk = 9;
                                break;
                            }
                            case 10: {
                                // monitorexit(clazz3)
                                return false;
                            }
                            default: {
                                if (OS.IsWinCE) {
                                    keybdinput.wVk = (short)OS.CharUpper((short)character);
                                    break;
                                }
                                keybdinput.wVk = OS.VkKeyScan((short)wcsToMbcs(character, 0));
                                if (keybdinput.wVk == -1) {
                                    // monitorexit(clazz3)
                                    return false;
                                }
                                final KEYBDINPUT keybdinput2 = keybdinput;
                                keybdinput2.wVk &= 0xFF;
                                break;
                            }
                        }
                    }
                    keybdinput.dwFlags = ((type == 2) ? 2 : 0);
                    switch (keybdinput.wVk) {
                        case 3:
                        case 33:
                        case 34:
                        case 35:
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 44:
                        case 45:
                        case 46:
                        case 111:
                        case 144: {
                            final KEYBDINPUT keybdinput3 = keybdinput;
                            keybdinput3.dwFlags |= 0x1;
                            break;
                        }
                    }
                    final int getProcessHeap = OS.GetProcessHeap();
                    final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, INPUT.sizeof);
                    OS.MoveMemory(heapAlloc, new int[] { 1 }, 4);
                    OS.MoveMemory(heapAlloc + OS.PTR_SIZEOF, keybdinput, KEYBDINPUT.sizeof);
                    final boolean b = OS.SendInput(1, heapAlloc, INPUT.sizeof) != 0;
                    OS.HeapFree(getProcessHeap, 0, heapAlloc);
                    // monitorexit(clazz3)
                    return b;
                }
                case 3:
                case 4:
                case 5:
                case 37: {
                    final MOUSEINPUT mouseinput = new MOUSEINPUT();
                    if (type == 5) {
                        mouseinput.dwFlags = 32769;
                        int getSystemMetrics = 0;
                        int getSystemMetrics2 = 0;
                        int n;
                        int n2;
                        if (OS.WIN32_VERSION >= OS.VERSION(5, 0)) {
                            final MOUSEINPUT mouseinput2 = mouseinput;
                            mouseinput2.dwFlags |= 0x4000;
                            getSystemMetrics = OS.GetSystemMetrics(76);
                            getSystemMetrics2 = OS.GetSystemMetrics(77);
                            n = OS.GetSystemMetrics(78);
                            n2 = OS.GetSystemMetrics(79);
                        }
                        else {
                            n = OS.GetSystemMetrics(0);
                            n2 = OS.GetSystemMetrics(1);
                        }
                        mouseinput.dx = ((event.x - getSystemMetrics) * 65535 + n - 2) / (n - 1);
                        mouseinput.dy = ((event.y - getSystemMetrics2) * 65535 + n2 - 2) / (n2 - 1);
                    }
                    else if (type == 37) {
                        if (OS.WIN32_VERSION < OS.VERSION(5, 0)) {
                            // monitorexit(clazz3)
                            return false;
                        }
                        mouseinput.dwFlags = 2048;
                        switch (event.detail) {
                            case 2: {
                                mouseinput.mouseData = event.count * 120;
                                break;
                            }
                            case 1: {
                                final int[] array = { 0 };
                                OS.SystemParametersInfo(104, 0, array, 0);
                                mouseinput.mouseData = event.count * 120 / array[0];
                                break;
                            }
                            default: {
                                // monitorexit(clazz3)
                                return false;
                            }
                        }
                    }
                    else {
                        switch (event.button) {
                            case 1: {
                                mouseinput.dwFlags = ((type == 3) ? 2 : 4);
                                break;
                            }
                            case 2: {
                                mouseinput.dwFlags = ((type == 3) ? 32 : 64);
                                break;
                            }
                            case 3: {
                                mouseinput.dwFlags = ((type == 3) ? 8 : 16);
                                break;
                            }
                            case 4: {
                                if (OS.WIN32_VERSION < OS.VERSION(5, 0)) {
                                    // monitorexit(clazz3)
                                    return false;
                                }
                                mouseinput.dwFlags = ((type == 3) ? 128 : 256);
                                mouseinput.mouseData = 1;
                                break;
                            }
                            case 5: {
                                if (OS.WIN32_VERSION < OS.VERSION(5, 0)) {
                                    // monitorexit(clazz3)
                                    return false;
                                }
                                mouseinput.dwFlags = ((type == 3) ? 128 : 256);
                                mouseinput.mouseData = 2;
                                break;
                            }
                            default: {
                                // monitorexit(clazz3)
                                return false;
                            }
                        }
                    }
                    final int getProcessHeap2 = OS.GetProcessHeap();
                    final int heapAlloc2 = OS.HeapAlloc(getProcessHeap2, 8, INPUT.sizeof);
                    OS.MoveMemory(heapAlloc2, new int[1], 4);
                    OS.MoveMemory(heapAlloc2 + OS.PTR_SIZEOF, mouseinput, MOUSEINPUT.sizeof);
                    final boolean b3 = OS.SendInput(1, heapAlloc2, INPUT.sizeof) != 0;
                    OS.HeapFree(getProcessHeap2, 0, heapAlloc2);
                    // monitorexit(clazz3)
                    return b3;
                }
                default: {
                    // monitorexit(clazz3)
                    return false;
                }
            }
        }
    }
    
    void postEvent(final Event event) {
        if (this.eventQueue == null) {
            this.eventQueue = new Event[4];
        }
        int n;
        int length;
        for (n = 0, length = this.eventQueue.length; n < length && this.eventQueue[n] != null; ++n) {}
        if (n == length) {
            final Event[] eventQueue = new Event[length + 4];
            System.arraycopy(this.eventQueue, 0, eventQueue, 0, length);
            this.eventQueue = eventQueue;
        }
        this.eventQueue[n] = event;
    }
    
    public boolean readAndDispatch() {
        this.checkDevice();
        Display.lpStartupInfo = null;
        this.drawMenuBars();
        this.runSkin();
        this.runDeferredLayouts();
        this.runPopups();
        if (OS.PeekMessage(this.msg, 0, 0, 0, 1)) {
            if (!this.filterMessage(this.msg)) {
                OS.TranslateMessage(this.msg);
                OS.DispatchMessage(this.msg);
            }
            this.runDeferredEvents();
            return true;
        }
        return this.isDisposed() || (this.runMessages && this.runAsyncMessages(false));
    }
    
    static void register(final Display display) {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            for (int i = 0; i < Display.Displays.length; ++i) {
                if (Display.Displays[i] == null) {
                    Display.Displays[i] = display;
                    // monitorexit(clazz3)
                    return;
                }
            }
            final Display[] displays = new Display[Display.Displays.length + 4];
            System.arraycopy(Display.Displays, 0, displays, 0, Display.Displays.length);
            displays[Display.Displays.length] = display;
            Display.Displays = displays;
        }
        // monitorexit(clazz3)
    }
    
    protected void release() {
        this.sendEvent(12, new Event());
        final Shell[] shells = this.getShells();
        for (int i = 0; i < shells.length; ++i) {
            final Shell shell = shells[i];
            if (!shell.isDisposed()) {
                shell.dispose();
            }
        }
        if (this.tray != null) {
            this.tray.dispose();
        }
        this.tray = null;
        if (this.taskBar != null) {
            this.taskBar.dispose();
        }
        this.taskBar = null;
        while (this.readAndDispatch()) {}
        if (this.disposeList != null) {
            for (int j = 0; j < this.disposeList.length; ++j) {
                if (this.disposeList[j] != null) {
                    this.disposeList[j].run();
                }
            }
        }
        this.disposeList = null;
        this.synchronizer.releaseSynchronizer();
        this.synchronizer = null;
        this.releaseDisplay();
        super.release();
    }
    
    void releaseDisplay() {
        if (this.embeddedHwnd != 0) {
            OS.PostMessage(this.embeddedHwnd, 32771, 0, 0);
        }
        if (OS.COMCTL32_MAJOR >= 6) {
            if (this.hButtonTheme != 0) {
                OS.CloseThemeData(this.hButtonTheme);
            }
            if (this.hEditTheme != 0) {
                OS.CloseThemeData(this.hEditTheme);
            }
            if (this.hExplorerBarTheme != 0) {
                OS.CloseThemeData(this.hExplorerBarTheme);
            }
            if (this.hScrollBarTheme != 0) {
                OS.CloseThemeData(this.hScrollBarTheme);
            }
            if (this.hTabTheme != 0) {
                OS.CloseThemeData(this.hTabTheme);
            }
            final boolean hButtonTheme = false;
            this.hTabTheme = (hButtonTheme ? 1 : 0);
            this.hScrollBarTheme = (hButtonTheme ? 1 : 0);
            this.hExplorerBarTheme = (hButtonTheme ? 1 : 0);
            this.hEditTheme = (hButtonTheme ? 1 : 0);
            this.hButtonTheme = (hButtonTheme ? 1 : 0);
        }
        if (!OS.IsWinCE) {
            if (this.msgHook != 0) {
                OS.UnhookWindowsHookEx(this.msgHook);
            }
            this.msgHook = 0;
        }
        if (!OS.IsWinCE) {
            if (this.filterHook != 0) {
                OS.UnhookWindowsHookEx(this.filterHook);
            }
            this.filterHook = 0;
            this.msgFilterCallback.dispose();
            this.msgFilterCallback = null;
            this.msgFilterProc = 0;
        }
        if (!OS.IsWinCE) {
            if (this.idleHook != 0) {
                OS.UnhookWindowsHookEx(this.idleHook);
            }
            this.idleHook = 0;
            this.foregroundIdleCallback.dispose();
            this.foregroundIdleCallback = null;
            this.foregroundIdleProc = 0;
        }
        OS.KillTimer(this.hwndMessage, 100);
        if (this.hwndMessage != 0) {
            OS.DestroyWindow(this.hwndMessage);
        }
        this.hwndMessage = 0;
        this.messageCallback.dispose();
        this.messageCallback = null;
        this.messageProc = 0;
        final int getProcessHeap = OS.GetProcessHeap();
        final int getModuleHandle = OS.GetModuleHandle(null);
        OS.UnregisterClass(this.windowClass, getModuleHandle);
        OS.UnregisterClass(this.windowShadowClass, getModuleHandle);
        OS.UnregisterClass(this.windowOwnDCClass, getModuleHandle);
        final TCHAR windowClass = null;
        this.windowOwnDCClass = windowClass;
        this.windowShadowClass = windowClass;
        this.windowClass = windowClass;
        this.windowCallback.dispose();
        this.windowCallback = null;
        this.windowProc = 0;
        if (this.systemFont != null) {
            this.systemFont.dispose();
        }
        this.systemFont = null;
        this.lfSystemFont = null;
        if (this.errorImage != null) {
            this.errorImage.dispose();
        }
        if (this.infoImage != null) {
            this.infoImage.dispose();
        }
        if (this.questionImage != null) {
            this.questionImage.dispose();
        }
        if (this.warningIcon != null) {
            this.warningIcon.dispose();
        }
        final Image image = null;
        this.warningIcon = image;
        this.questionImage = image;
        this.infoImage = image;
        this.errorImage = image;
        if (this.upArrow != null) {
            this.upArrow.dispose();
        }
        if (this.downArrow != null) {
            this.downArrow.dispose();
        }
        final Image image2 = null;
        this.downArrow = image2;
        this.upArrow = image2;
        for (int i = 0; i < this.cursors.length; ++i) {
            if (this.cursors[i] != null) {
                this.cursors[i].dispose();
            }
        }
        this.cursors = null;
        if (this.resources != null) {
            for (int j = 0; j < this.resources.length; ++j) {
                if (this.resources[j] != null) {
                    this.resources[j].dispose();
                }
            }
            this.resources = null;
        }
        if (this.lpCustColors != 0) {
            OS.HeapFree(getProcessHeap, 0, this.lpCustColors);
        }
        this.lpCustColors = 0;
        if (!OS.IsWinCE) {
            OS.OleUninitialize();
        }
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(6, 0)) {
            OS.BufferedPaintUnInit();
        }
        this.thread = null;
        final MSG msg = null;
        this.hookMsg = msg;
        this.msg = msg;
        this.keyboard = null;
        this.modalDialog = null;
        this.modalShells = null;
        this.data = null;
        this.keys = null;
        this.values = null;
        final Menu[] array = null;
        this.popups = array;
        this.bars = array;
        this.indexTable = null;
        this.timerIds = null;
        this.controlTable = null;
        final Control lastControl = null;
        this.lastHittestControl = lastControl;
        this.lastGetControl = lastControl;
        this.lastControl = lastControl;
        final ImageList[] array2 = null;
        this.toolDisabledImageList = array2;
        this.toolHotImageList = array2;
        this.toolImageList = array2;
        this.imageList = array2;
        this.timerList = null;
        this.tableBuffer = null;
        this.columnVisible = null;
        final EventTable eventTable = null;
        this.filterTable = eventTable;
        this.eventTable = eventTable;
        this.items = null;
        this.clickRect = null;
        this.hdr = null;
        this.plvfi = null;
        this.monitors = null;
        this.touchSources = null;
        this.threadId = 0;
    }
    
    void releaseImageList(final ImageList list) {
        int i = 0;
        int length = this.imageList.length;
        while (i < length) {
            if (this.imageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.imageList, i + 1, this.imageList, i, --length - i);
                this.imageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.imageList[j] != null) {
                        return;
                    }
                }
                this.imageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    void releaseToolImageList(final ImageList list) {
        int i = 0;
        int length = this.toolImageList.length;
        while (i < length) {
            if (this.toolImageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.toolImageList, i + 1, this.toolImageList, i, --length - i);
                this.toolImageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.toolImageList[j] != null) {
                        return;
                    }
                }
                this.toolImageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    void releaseToolHotImageList(final ImageList list) {
        int i = 0;
        int length = this.toolHotImageList.length;
        while (i < length) {
            if (this.toolHotImageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.toolHotImageList, i + 1, this.toolHotImageList, i, --length - i);
                this.toolHotImageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.toolHotImageList[j] != null) {
                        return;
                    }
                }
                this.toolHotImageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    void releaseToolDisabledImageList(final ImageList list) {
        int i = 0;
        int length = this.toolDisabledImageList.length;
        while (i < length) {
            if (this.toolDisabledImageList[i] == list) {
                if (list.removeRef() > 0) {
                    return;
                }
                list.dispose();
                System.arraycopy(this.toolDisabledImageList, i + 1, this.toolDisabledImageList, i, --length - i);
                this.toolDisabledImageList[length] = null;
                for (int j = 0; j < length; ++j) {
                    if (this.toolDisabledImageList[j] != null) {
                        return;
                    }
                }
                this.toolDisabledImageList = null;
            }
            else {
                ++i;
            }
        }
    }
    
    public void removeFilter(final int n, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.filterTable == null) {
            return;
        }
        this.filterTable.unhook(n, listener);
        if (this.filterTable.size() == 0) {
            this.filterTable = null;
        }
    }
    
    public void removeListener(final int n, final Listener listener) {
        this.checkDevice();
        if (listener == null) {
            this.error(4);
        }
        if (this.eventTable == null) {
            return;
        }
        this.eventTable.unhook(n, listener);
    }
    
    void removeBar(final Menu menu) {
        if (this.bars == null) {
            return;
        }
        for (int i = 0; i < this.bars.length; ++i) {
            if (this.bars[i] == menu) {
                this.bars[i] = null;
                return;
            }
        }
    }
    
    Control removeControl(final int n) {
        if (n == 0) {
            return null;
        }
        final Control control = null;
        this.lastGetControl = control;
        this.lastControl = control;
        Control control2 = null;
        int freeSlot;
        if (Display.USE_PROPERTY) {
            freeSlot = OS.RemoveProp(n, Display.SWT_OBJECT_INDEX) - 1;
        }
        else {
            freeSlot = OS.GetWindowLongPtr(n, -21) - 1;
            OS.SetWindowLongPtr(n, -21, 0);
        }
        if (freeSlot >= 0 && freeSlot < this.controlTable.length) {
            control2 = this.controlTable[freeSlot];
            this.controlTable[freeSlot] = null;
            this.indexTable[freeSlot] = this.freeSlot;
            this.freeSlot = freeSlot;
        }
        return control2;
    }
    
    void removeMenuItem(final MenuItem menuItem) {
        if (this.items == null) {
            return;
        }
        this.items[menuItem.id - 108] = null;
    }
    
    void removePopup(final Menu menu) {
        if (this.popups == null) {
            return;
        }
        for (int i = 0; i < this.popups.length; ++i) {
            if (this.popups[i] == menu) {
                this.popups[i] = null;
                return;
            }
        }
    }
    
    boolean runAsyncMessages(final boolean b) {
        return this.synchronizer.runAsyncMessages(b);
    }
    
    boolean runDeferredEvents() {
        boolean b = false;
        while (this.eventQueue != null) {
            final Event event = this.eventQueue[0];
            if (event == null) {
                break;
            }
            int length = this.eventQueue.length;
            System.arraycopy(this.eventQueue, 1, this.eventQueue, 0, --length);
            this.eventQueue[length] = null;
            final Widget widget = event.widget;
            if (widget == null || widget.isDisposed()) {
                continue;
            }
            final Widget item = event.item;
            if (item != null && item.isDisposed()) {
                continue;
            }
            b = true;
            widget.sendEvent(event);
        }
        this.eventQueue = null;
        return b;
    }
    
    boolean runDeferredLayouts() {
        if (this.layoutDeferredCount != 0) {
            final Composite[] layoutDeferred = this.layoutDeferred;
            final int layoutDeferredCount = this.layoutDeferredCount;
            this.layoutDeferred = null;
            this.layoutDeferredCount = 0;
            for (final Composite composite : layoutDeferred) {
                if (!composite.isDisposed()) {
                    composite.setLayoutDeferred(false);
                }
            }
            return true;
        }
        return false;
    }
    
    boolean runPopups() {
        if (this.popups == null) {
            return false;
        }
        boolean b = false;
        while (this.popups != null) {
            final Menu menu = this.popups[0];
            if (menu == null) {
                break;
            }
            int length = this.popups.length;
            System.arraycopy(this.popups, 1, this.popups, 0, --length);
            this.popups[length] = null;
            this.runDeferredEvents();
            if (!menu.isDisposed()) {
                menu._setVisible(true);
            }
            b = true;
        }
        this.popups = null;
        return b;
    }
    
    void runSettings() {
        final Font systemFont = this.getSystemFont();
        this.saveResources();
        this.updateImages();
        this.sendEvent(39, null);
        final Font systemFont2 = this.getSystemFont();
        final boolean equals = systemFont.equals(systemFont2);
        final Shell[] shells = this.getShells();
        for (int i = 0; i < shells.length; ++i) {
            final Shell shell = shells[i];
            if (!shell.isDisposed()) {
                if (!equals) {
                    shell.updateFont(systemFont, systemFont2);
                }
                shell.layout(true, true);
            }
        }
    }
    
    boolean runSkin() {
        if (this.skinCount > 0) {
            final Widget[] skinList = this.skinList;
            final int skinCount = this.skinCount;
            this.skinList = new Widget[1024];
            this.skinCount = 0;
            if (this.eventTable != null && this.eventTable.hooks(45)) {
                for (int i = 0; i < skinCount; ++i) {
                    final Widget widget = skinList[i];
                    if (widget != null && !widget.isDisposed()) {
                        final Widget widget2 = widget;
                        widget2.state &= 0xFFDFFFFF;
                        skinList[i] = null;
                        final Event event = new Event();
                        event.widget = widget;
                        this.sendEvent(45, event);
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    boolean runTimer(final int n) {
        if (this.timerList != null && this.timerIds != null) {
            for (int i = 0; i < this.timerIds.length; ++i) {
                if (this.timerIds[i] == n) {
                    OS.KillTimer(this.hwndMessage, this.timerIds[i]);
                    this.timerIds[i] = 0;
                    final Runnable runnable = this.timerList[i];
                    this.timerList[i] = null;
                    if (runnable != null) {
                        runnable.run();
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    void saveResources() {
        int length = 0;
        if (this.resources == null) {
            this.resources = new Resource[27];
        }
        else {
            length = this.resources.length;
            final Resource[] resources = new Resource[length + 27];
            System.arraycopy(this.resources, 0, resources, 0, length);
            this.resources = resources;
        }
        if (this.systemFont != null && !OS.IsWinCE) {
            final NONCLIENTMETRICS nonclientmetrics = OS.IsUnicode ? new NONCLIENTMETRICSW() : new NONCLIENTMETRICSA();
            nonclientmetrics.cbSize = NONCLIENTMETRICS.sizeof;
            if (OS.SystemParametersInfo(41, 0, nonclientmetrics, 0)) {
                final LOGFONT lfSystemFont = OS.IsUnicode ? ((NONCLIENTMETRICSW)nonclientmetrics).lfMessageFont : ((NONCLIENTMETRICSA)nonclientmetrics).lfMessageFont;
                if (this.lfSystemFont == null || lfSystemFont.lfCharSet != this.lfSystemFont.lfCharSet || lfSystemFont.lfHeight != this.lfSystemFont.lfHeight || lfSystemFont.lfWidth != this.lfSystemFont.lfWidth || lfSystemFont.lfEscapement != this.lfSystemFont.lfEscapement || lfSystemFont.lfOrientation != this.lfSystemFont.lfOrientation || lfSystemFont.lfWeight != this.lfSystemFont.lfWeight || lfSystemFont.lfItalic != this.lfSystemFont.lfItalic || lfSystemFont.lfUnderline != this.lfSystemFont.lfUnderline || lfSystemFont.lfStrikeOut != this.lfSystemFont.lfStrikeOut || lfSystemFont.lfCharSet != this.lfSystemFont.lfCharSet || lfSystemFont.lfOutPrecision != this.lfSystemFont.lfOutPrecision || lfSystemFont.lfClipPrecision != this.lfSystemFont.lfClipPrecision || lfSystemFont.lfQuality != this.lfSystemFont.lfQuality || lfSystemFont.lfPitchAndFamily != this.lfSystemFont.lfPitchAndFamily || !this.getFontName(lfSystemFont).equals(this.getFontName(this.lfSystemFont))) {
                    this.resources[length++] = this.systemFont;
                    this.lfSystemFont = lfSystemFont;
                    this.systemFont = null;
                }
            }
        }
        if (this.errorImage != null) {
            this.resources[length++] = this.errorImage;
        }
        if (this.infoImage != null) {
            this.resources[length++] = this.infoImage;
        }
        if (this.questionImage != null) {
            this.resources[length++] = this.questionImage;
        }
        if (this.warningIcon != null) {
            this.resources[length++] = this.warningIcon;
        }
        final Image image = null;
        this.warningIcon = image;
        this.questionImage = image;
        this.infoImage = image;
        this.errorImage = image;
        for (int i = 0; i < this.cursors.length; ++i) {
            if (this.cursors[i] != null) {
                this.resources[length++] = this.cursors[i];
            }
            this.cursors[i] = null;
        }
        if (length < 27) {
            final Resource[] resources2 = new Resource[length];
            System.arraycopy(this.resources, 0, resources2, 0, length);
            this.resources = resources2;
        }
    }
    
    void sendEvent(final int type, Event event) {
        if (this.eventTable == null && this.filterTable == null) {
            return;
        }
        if (event == null) {
            event = new Event();
        }
        event.display = this;
        event.type = type;
        if (event.time == 0) {
            event.time = this.getLastEventTime();
        }
        if (!this.filterEvent(event) && this.eventTable != null) {
            this.eventTable.sendEvent(event);
        }
    }
    
    public void setCursorLocation(final int n, final int n2) {
        this.checkDevice();
        OS.SetCursorPos(n, n2);
    }
    
    public void setCursorLocation(final Point point) {
        this.checkDevice();
        if (point == null) {
            this.error(4);
        }
        this.setCursorLocation(point.x, point.y);
    }
    
    public void setData(final String s, final Object o) {
        this.checkDevice();
        if (s == null) {
            this.error(4);
        }
        if (s.equals("org.eclipse.swt.internal.win32.runMessagesInIdle")) {
            final Boolean b = (Boolean)o;
            this.runMessagesInIdle = (b != null && b);
            return;
        }
        if (s.equals("org.eclipse.swt.internal.win32.runMessagesInMessageProc")) {
            final Boolean b2 = (Boolean)o;
            this.runMessagesInMessageProc = (b2 != null && b2);
            return;
        }
        if (s.equals("org.eclipse.swt.internal.win32.useOwnDC")) {
            final Boolean b3 = (Boolean)o;
            this.useOwnDC = (b3 != null && b3);
            return;
        }
        if (s.equals("org.eclipse.swt.internal.win32.accelKeyHit")) {
            final Boolean b4 = (Boolean)o;
            this.accelKeyHit = (b4 != null && b4);
            return;
        }
        if (o == null) {
            if (this.keys == null) {
                return;
            }
            int n;
            for (n = 0; n < this.keys.length && !this.keys[n].equals(s); ++n) {}
            if (n == this.keys.length) {
                return;
            }
            if (this.keys.length == 1) {
                this.keys = null;
                this.values = null;
            }
            else {
                final String[] keys = new String[this.keys.length - 1];
                final Object[] values = new Object[this.values.length - 1];
                System.arraycopy(this.keys, 0, keys, 0, n);
                System.arraycopy(this.keys, n + 1, keys, n, keys.length - n);
                System.arraycopy(this.values, 0, values, 0, n);
                System.arraycopy(this.values, n + 1, values, n, values.length - n);
                this.keys = keys;
                this.values = values;
            }
        }
        else {
            if (this.keys == null) {
                this.keys = new String[] { s };
                this.values = new Object[] { o };
                return;
            }
            for (int i = 0; i < this.keys.length; ++i) {
                if (this.keys[i].equals(s)) {
                    this.values[i] = o;
                    return;
                }
            }
            final String[] keys2 = new String[this.keys.length + 1];
            final Object[] values2 = new Object[this.values.length + 1];
            System.arraycopy(this.keys, 0, keys2, 0, this.keys.length);
            System.arraycopy(this.values, 0, values2, 0, this.values.length);
            keys2[this.keys.length] = s;
            values2[this.values.length] = o;
            this.keys = keys2;
            this.values = values2;
        }
    }
    
    public void setData(final Object data) {
        this.checkDevice();
        this.data = data;
    }
    
    public static String getAppName() {
        return Display.APP_NAME;
    }
    
    public static String getAppVersion() {
        return Display.APP_VERSION;
    }
    
    public static void setAppName(final String app_NAME) {
        Display.APP_NAME = app_NAME;
    }
    
    public static void setAppVersion(final String app_VERSION) {
        Display.APP_VERSION = app_VERSION;
    }
    
    void setModalDialog(final Dialog modalDialog) {
        this.modalDialog = modalDialog;
        final Shell[] shells = this.getShells();
        for (int i = 0; i < shells.length; ++i) {
            shells[i].updateModal();
        }
    }
    
    void setModalShell(final Shell shell) {
        if (this.modalShells == null) {
            this.modalShells = new Shell[4];
        }
        int i;
        int length;
        for (i = 0, length = this.modalShells.length; i < length; ++i) {
            if (this.modalShells[i] == shell) {
                return;
            }
            if (this.modalShells[i] == null) {
                break;
            }
        }
        if (i == length) {
            final Shell[] modalShells = new Shell[length + 4];
            System.arraycopy(this.modalShells, 0, modalShells, 0, length);
            this.modalShells = modalShells;
        }
        this.modalShells[i] = shell;
        final Shell[] shells = this.getShells();
        for (int j = 0; j < shells.length; ++j) {
            shells[j].updateModal();
        }
    }
    
    public void setSynchronizer(final Synchronizer synchronizer) {
        this.checkDevice();
        if (synchronizer == null) {
            this.error(4);
        }
        if (synchronizer == this.synchronizer) {
            return;
        }
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        final Synchronizer synchronizer2;
        synchronized (clazz2) {
            synchronizer2 = this.synchronizer;
            this.synchronizer = synchronizer;
        }
        // monitorexit(clazz3)
        if (synchronizer2 != null) {
            synchronizer2.runAsyncMessages(true);
        }
    }
    
    int shiftedKey(final int n) {
        if (OS.IsWinCE) {
            return 0;
        }
        for (int i = 0; i < this.keyboard.length; ++i) {
            this.keyboard[i] = 0;
        }
        final byte[] keyboard = this.keyboard;
        final int n2 = 16;
        keyboard[n2] |= (byte)128;
        if (OS.IsUnicode) {
            final char[] array = { '\0' };
            if (OS.ToUnicode(n, n, this.keyboard, array, 1, 0) == 1) {
                return array[0];
            }
        }
        else {
            final short[] array2 = { 0 };
            if (OS.ToAscii(n, n, this.keyboard, array2, 0) == 1) {
                return array2[0];
            }
        }
        return 0;
    }
    
    public boolean sleep() {
        this.checkDevice();
        if (this.runMessages && this.getMessageCount() != 0) {
            return true;
        }
        if (OS.IsWinCE) {
            OS.MsgWaitForMultipleObjectsEx(0, 0, -1, 127, 4);
            return true;
        }
        return OS.WaitMessage();
    }
    
    public void syncExec(final Runnable runnable) {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        final Synchronizer synchronizer;
        synchronized (clazz2) {
            if (this.isDisposed()) {
                this.error(45);
            }
            synchronizer = this.synchronizer;
        }
        // monitorexit(clazz3)
        synchronizer.syncExec(runnable);
    }
    
    public void timerExec(final int n, final Runnable runnable) {
        this.checkDevice();
        if (runnable == null) {
            this.error(4);
        }
        if (this.timerList == null) {
            this.timerList = new Runnable[4];
        }
        if (this.timerIds == null) {
            this.timerIds = new int[4];
        }
        int n2;
        for (n2 = 0; n2 < this.timerList.length && this.timerList[n2] != runnable; ++n2) {}
        int n3;
        if (n2 != this.timerList.length) {
            n3 = this.timerIds[n2];
            if (n < 0) {
                OS.KillTimer(this.hwndMessage, n3);
                this.timerList[n2] = null;
                this.timerIds[n2] = 0;
                return;
            }
        }
        else {
            if (n < 0) {
                return;
            }
            for (n2 = 0; n2 < this.timerList.length && this.timerList[n2] != null; ++n2) {}
            n3 = this.nextTimerId++;
            if (n2 == this.timerList.length) {
                final Runnable[] timerList = new Runnable[this.timerList.length + 4];
                System.arraycopy(this.timerList, 0, timerList, 0, this.timerList.length);
                this.timerList = timerList;
                final int[] timerIds = new int[this.timerIds.length + 4];
                System.arraycopy(this.timerIds, 0, timerIds, 0, this.timerIds.length);
                this.timerIds = timerIds;
            }
        }
        final int setTimer = OS.SetTimer(this.hwndMessage, n3, n, 0);
        if (setTimer != 0) {
            this.timerList[n2] = runnable;
            this.timerIds[n2] = setTimer;
        }
    }
    
    boolean translateAccelerator(final MSG msg, final Control control) {
        this.accelKeyHit = true;
        final boolean translateAccelerator = control.translateAccelerator(msg);
        this.accelKeyHit = false;
        return translateAccelerator;
    }
    
    static int translateKey(final int n) {
        for (int i = 0; i < Display.KeyTable.length; ++i) {
            if (Display.KeyTable[i][0] == n) {
                return Display.KeyTable[i][1];
            }
        }
        return 0;
    }
    
    boolean translateMnemonic(final MSG msg, final Control control) {
        switch (msg.message) {
            case 258:
            case 262: {
                return control.translateMnemonic(msg);
            }
            default: {
                return false;
            }
        }
    }
    
    boolean translateTraversal(final MSG msg, final Control control) {
        Label_0166: {
            switch (msg.message) {
                case 256: {
                    switch (msg.wParam) {
                        case 9:
                        case 13:
                        case 27:
                        case 33:
                        case 34:
                        case 37:
                        case 38:
                        case 39:
                        case 40: {
                            return control.translateTraversal(msg);
                        }
                        default: {
                            break Label_0166;
                        }
                    }
                    break;
                }
                case 260: {
                    switch (msg.wParam) {
                        case 18: {
                            return control.translateTraversal(msg);
                        }
                        default: {
                            break Label_0166;
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    static int untranslateKey(final int n) {
        for (int i = 0; i < Display.KeyTable.length; ++i) {
            if (Display.KeyTable[i][1] == n) {
                return Display.KeyTable[i][0];
            }
        }
        return 0;
    }
    
    public void update() {
        this.checkDevice();
        if (!OS.IsWinCE && OS.WIN32_VERSION >= OS.VERSION(4, 10) && OS.IsHungAppWindow(this.hwndMessage)) {
            OS.PeekMessage(new MSG(), this.hwndMessage, 32773, 32773, 3);
        }
        final Shell[] shells = this.getShells();
        for (int i = 0; i < shells.length; ++i) {
            final Shell shell = shells[i];
            if (!shell.isDisposed()) {
                shell.update(true);
            }
        }
    }
    
    void updateImages() {
        if (this.upArrow != null) {
            this.upArrow.dispose();
        }
        if (this.downArrow != null) {
            this.downArrow.dispose();
        }
        final Image image = null;
        this.downArrow = image;
        this.upArrow = image;
        for (int i = 0; i < this.controlTable.length; ++i) {
            final Control control = this.controlTable[i];
            if (control != null) {
                control.updateImages();
            }
        }
    }
    
    public void wake() {
        Class<?> clazz2;
        Class clazz;
        if ((clazz = (clazz2 = (Class<?>)Display.class$0)) == null) {
            try {
                clazz = (Display.class$0 = (clazz2 = Class.forName("org.eclipse.swt.graphics.Device")));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Class clazz3 = clazz;
        synchronized (clazz2) {
            if (this.isDisposed()) {
                this.error(45);
            }
            if (this.thread == Thread.currentThread()) {
                // monitorexit(clazz3)
                return;
            }
            this.wakeThread();
        }
        // monitorexit(clazz3)
    }
    
    void wakeThread() {
        if (OS.IsWinCE) {
            OS.PostMessage(this.hwndMessage, 0, 0, 0);
        }
        else {
            OS.PostThreadMessage(this.threadId, 0, 0, 0);
        }
    }
    
    static int wcsToMbcs(final char c, final int n) {
        if (OS.IsUnicode) {
            return c;
        }
        if (c <= '\u007f') {
            return c;
        }
        return new TCHAR(n, c, false).tcharAt(0);
    }
    
    static int wcsToMbcs(final char c) {
        return wcsToMbcs(c, 0);
    }
    
    int windowProc(final int lastHwnd, final int n, final int n2, final int n3) {
        if (this.columnVisible != null && n == 78 && this.hwndParent == lastHwnd) {
            OS.MoveMemory(this.hdr, n3, NMHDR.sizeof);
            switch (this.hdr.code) {
                case -177:
                case -150: {
                    OS.MoveMemory(this.plvfi, n3, NMLVDISPINFO.sizeof);
                    if (this.plvfi.iSubItem >= 0 && this.plvfi.iSubItem < this.columnCount && !this.columnVisible[this.plvfi.iSubItem]) {
                        return 0;
                    }
                    break;
                }
            }
        }
        if (n == Display.TASKBARBUTTONCREATED && this.taskBar != null) {
            final TaskItem[] items = this.taskBar.items;
            for (int i = 0; i < items.length; ++i) {
                final TaskItem taskItem = items[i];
                if (taskItem != null && taskItem.shell != null && taskItem.shell.handle == lastHwnd) {
                    taskItem.recreate();
                    break;
                }
            }
        }
        if (n == 132) {
            if (this.hitCount++ >= 1024) {
                try {
                    Thread.sleep(1L);
                }
                catch (Throwable t) {}
            }
        }
        else {
            this.hitCount = 0;
        }
        if (this.lastControl != null && this.lastHwnd == lastHwnd) {
            return this.lastControl.windowProc(lastHwnd, n, n2, n3);
        }
        int n4;
        if (Display.USE_PROPERTY) {
            n4 = OS.GetProp(lastHwnd, Display.SWT_OBJECT_INDEX) - 1;
        }
        else {
            n4 = OS.GetWindowLongPtr(lastHwnd, -21) - 1;
        }
        if (n4 >= 0 && n4 < this.controlTable.length) {
            final Control lastControl = this.controlTable[n4];
            if (lastControl != null) {
                this.lastHwnd = lastHwnd;
                this.lastControl = lastControl;
                return lastControl.windowProc(lastHwnd, n, n2, n3);
            }
        }
        return OS.DefWindowProc(lastHwnd, n, n2, n3);
    }
    
    static String withCrLf(final String s) {
        final int length = s.length();
        if (length == 0) {
            return s;
        }
        int n = s.indexOf(10, 0);
        if (n == -1) {
            return s;
        }
        if (n > 0 && s.charAt(n - 1) == '\r') {
            return s;
        }
        ++n;
        int n2 = 1;
        while (n < length && (n = s.indexOf(10, n)) != -1) {
            ++n2;
            ++n;
        }
        final int n3 = n2 + length;
        int i = 0;
        final StringBuffer sb = new StringBuffer(n3);
        while (i < length) {
            int index = s.indexOf(10, i);
            if (index == -1) {
                index = length;
            }
            sb.append(s.substring(i, index));
            if ((i = index) < length) {
                sb.append("\r\n");
                ++i;
            }
        }
        return sb.toString();
    }
    
    static char[] withCrLf(final char[] array) {
        final int length = array.length;
        if (length == 0) {
            return array;
        }
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == '\n' && ++n == 1 && i > 0 && array[i - 1] == '\r') {
                return array;
            }
        }
        if (n == 0) {
            return array;
        }
        final int n2 = n + length;
        final char[] array2 = new char[n2];
        for (int n3 = 0, n4 = 0; n3 < length && n4 < n2; array2[n4++] = array[n3], ++n3) {
            if (array[n3] == '\n') {
                array2[n4++] = '\r';
            }
        }
        return array2;
    }
}
