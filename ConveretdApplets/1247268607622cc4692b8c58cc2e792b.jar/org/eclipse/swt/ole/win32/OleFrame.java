// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.ole.win32;

import java.util.Vector;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.MENUITEMINFO;
import org.eclipse.swt.internal.win32.RECT;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.win32.GUITHREADINFO;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.LONG;
import org.eclipse.swt.SWT;
import org.eclipse.swt.internal.Callback;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.internal.ole.win32.IOleInPlaceActiveObject;
import org.eclipse.swt.internal.ole.win32.COMObject;
import org.eclipse.swt.widgets.Composite;

public final class OleFrame extends Composite
{
    private COMObject iUnknown;
    private COMObject iOleInPlaceFrame;
    private IOleInPlaceActiveObject objIOleInPlaceActiveObject;
    private OleClientSite currentdoc;
    private int refCount;
    private MenuItem[] fileMenuItems;
    private MenuItem[] containerMenuItems;
    private MenuItem[] windowMenuItems;
    private Listener listener;
    private static String CHECK_FOCUS;
    private static String HHOOK;
    private static String HHOOKMSG;
    private static boolean ignoreNextKey;
    private static final short[] ACCENTS;
    private static final String CONSUME_KEY = "org.eclipse.swt.OleFrame.ConsumeKey";
    private static final String ACCEL_KEY_HIT = "org.eclipse.swt.internal.win32.accelKeyHit";
    static /* synthetic */ Class class$0;
    
    static {
        OleFrame.CHECK_FOCUS = "OLE_CHECK_FOCUS";
        OleFrame.HHOOK = "OLE_HHOOK";
        OleFrame.HHOOKMSG = "OLE_HHOOK_MSG";
        ACCENTS = new short[] { 126, 96, 39, 94, 34 };
    }
    
    public OleFrame(final Composite composite, final int n) {
        super(composite, n);
        this.refCount = 0;
        this.createCOMInterfaces();
        this.addListener(26, this.listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 26: {
                        OleFrame.this.onActivate(event);
                        break;
                    }
                    case 27: {
                        OleFrame.this.onDeactivate(event);
                        break;
                    }
                    case 12: {
                        OleFrame.this.onDispose(event);
                        break;
                    }
                    case 10:
                    case 11: {
                        OleFrame.this.onResize(event);
                        break;
                    }
                    default: {
                        OLE.error(20);
                        break;
                    }
                }
            }
        });
        this.addListener(27, this.listener);
        this.addListener(12, this.listener);
        this.addListener(11, this.listener);
        this.addListener(10, this.listener);
        this.AddRef();
        final Display display = this.getDisplay();
        initCheckFocus(display);
        initMsgHook(display);
    }
    
    private static void initCheckFocus(final Display display) {
        if (display.getData(OleFrame.CHECK_FOCUS) != null) {
            return;
        }
        display.setData(OleFrame.CHECK_FOCUS, OleFrame.CHECK_FOCUS);
        final Runnable[] array = { null };
        display.timerExec(50, array[0] = new Runnable() {
            private final /* synthetic */ Control[] val$lastFocus = val$lastFocus;
            
            public void run() {
                if (this.val$lastFocus[0] instanceof OleClientSite && !this.val$lastFocus[0].isDisposed()) {
                    for (int i = OS.GetFocus(); i != 0; i = OS.GetParent(i)) {
                        if (OS.GetWindow(i, 4) != 0) {
                            display.timerExec(50, array[0]);
                            return;
                        }
                    }
                }
                if (this.val$lastFocus[0] == null || this.val$lastFocus[0].isDisposed() || !this.val$lastFocus[0].isFocusControl()) {
                    Control control = display.getFocusControl();
                    if (control instanceof OleFrame) {
                        control = ((OleFrame)control).getCurrentDocument();
                    }
                    if (this.val$lastFocus[0] != control) {
                        final Event event = new Event();
                        if (this.val$lastFocus[0] instanceof OleClientSite && !this.val$lastFocus[0].isDisposed()) {
                            this.val$lastFocus[0].notifyListeners(16, event);
                        }
                        if (control instanceof OleClientSite && !control.isDisposed()) {
                            control.notifyListeners(15, event);
                        }
                    }
                    this.val$lastFocus[0] = control;
                }
                display.timerExec(50, array[0]);
            }
        });
    }
    
    private static void initMsgHook(final Display display) {
        if (display.getData(OleFrame.HHOOK) != null) {
            return;
        }
        Class class$0;
        if ((class$0 = OleFrame.class$0) == null) {
            try {
                class$0 = (OleFrame.class$0 = Class.forName("org.eclipse.swt.ole.win32.OleFrame"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        final Callback callback = new Callback(class$0, "getMsgProc", 3);
        final int address = callback.getAddress();
        if (address == 0) {
            SWT.error(3);
        }
        final int setWindowsHookEx = OS.SetWindowsHookEx(3, address, 0, OS.GetCurrentThreadId());
        if (setWindowsHookEx == 0) {
            callback.dispose();
            return;
        }
        display.setData(OleFrame.HHOOK, new LONG(setWindowsHookEx));
        display.setData(OleFrame.HHOOKMSG, new MSG());
        display.disposeExec(new Runnable() {
            private final /* synthetic */ int val$hHook = val$hHook;
            
            public void run() {
                if (this.val$hHook != 0) {
                    OS.UnhookWindowsHookEx(this.val$hHook);
                }
                callback.dispose();
            }
        });
    }
    
    static int getMsgProc(final int n, final int n2, final int n3) {
        final Display current = Display.getCurrent();
        if (current == null) {
            return 0;
        }
        final LONG long1 = (LONG)current.getData(OleFrame.HHOOK);
        if (long1 == null) {
            return 0;
        }
        if (n < 0 || (n2 & 0x1) == 0x0) {
            return OS.CallNextHookEx(long1.value, n, n2, n3);
        }
        final MSG msg = (MSG)current.getData(OleFrame.HHOOKMSG);
        OS.MoveMemory(msg, n3, MSG.sizeof);
        final int message = msg.message;
        if (256 <= message && message <= 264) {
            Widget widget = null;
            int i;
            for (i = msg.hwnd; i != 0; i = OS.GetParent(i)) {
                widget = current.findWidget(i);
                if (widget != null) {
                    break;
                }
            }
            if (widget != null && widget instanceof OleClientSite) {
                final OleClientSite oleClientSite = (OleClientSite)widget;
                if (oleClientSite.handle == i) {
                    boolean b = false;
                    final int getWindowThreadProcessId = OS.GetWindowThreadProcessId(msg.hwnd, null);
                    final GUITHREADINFO guithreadinfo = new GUITHREADINFO();
                    guithreadinfo.cbSize = GUITHREADINFO.sizeof;
                    final boolean getGUIThreadInfo = OS.GetGUIThreadInfo(getWindowThreadProcessId, guithreadinfo);
                    final int n4 = 30;
                    if (!getGUIThreadInfo || (guithreadinfo.flags & n4) == 0x0) {
                        final OleFrame frame = oleClientSite.frame;
                        frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", null);
                        current.setData("org.eclipse.swt.internal.win32.accelKeyHit", Boolean.TRUE);
                        b = frame.translateOleAccelerator(msg);
                        if (current.isDisposed()) {
                            return 0;
                        }
                        current.setData("org.eclipse.swt.internal.win32.accelKeyHit", Boolean.FALSE);
                        if (frame.isDisposed()) {
                            return 0;
                        }
                        final String s = (String)frame.getData("org.eclipse.swt.OleFrame.ConsumeKey");
                        if (s != null) {
                            b = s.equals("true");
                        }
                        frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", null);
                    }
                    boolean ignoreNextKey = false;
                    Label_0638: {
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
                                        break Label_0638;
                                    }
                                    default: {
                                        final int mapVirtualKey = OS.MapVirtualKey(msg.wParam, 2);
                                        if (mapVirtualKey == 0) {
                                            break Label_0638;
                                        }
                                        ignoreNextKey = ((mapVirtualKey & (OS.IsWinNT ? Integer.MIN_VALUE : 32768)) != 0x0);
                                        if (!ignoreNextKey) {
                                            for (int j = 0; j < OleFrame.ACCENTS.length; ++j) {
                                                final short vkKeyScan = OS.VkKeyScan(OleFrame.ACCENTS[j]);
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
                                            break Label_0638;
                                        }
                                        break Label_0638;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (!b && !ignoreNextKey && !OleFrame.ignoreNextKey) {
                        final int hwnd = msg.hwnd;
                        msg.hwnd = oleClientSite.handle;
                        b = (OS.DispatchMessage(msg) == 1);
                        msg.hwnd = hwnd;
                    }
                    Label_0804: {
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
                                        break Label_0804;
                                    }
                                    default: {
                                        OleFrame.ignoreNextKey = ignoreNextKey;
                                        break Label_0804;
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (b) {
                        msg.message = 0;
                        final MSG msg2 = msg;
                        final MSG msg3 = msg;
                        final boolean b2 = false;
                        msg3.lParam = (b2 ? 1 : 0);
                        msg2.wParam = (b2 ? 1 : 0);
                        OS.MoveMemory(n3, msg, MSG.sizeof);
                        return 0;
                    }
                }
            }
        }
        return OS.CallNextHookEx(long1.value, n, n2, n3);
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    private int ContextSensitiveHelp(final int n) {
        return 0;
    }
    
    private void createCOMInterfaces() {
        this.iUnknown = new COMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return OleFrame.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleFrame.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleFrame.this.Release();
            }
        };
        this.iOleInPlaceFrame = new COMObject(new int[] { 2, 0, 0, 1, 1, 1, 1, 1, 2, 2, 3, 1, 1, 1, 2 }) {
            public int method0(final int[] array) {
                return OleFrame.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return OleFrame.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return OleFrame.this.Release();
            }
            
            public int method3(final int[] array) {
                return OleFrame.this.GetWindow(array[0]);
            }
            
            public int method4(final int[] array) {
                return OleFrame.this.ContextSensitiveHelp(array[0]);
            }
            
            public int method5(final int[] array) {
                return OleFrame.this.GetBorder(array[0]);
            }
            
            public int method6(final int[] array) {
                return OleFrame.this.RequestBorderSpace(array[0]);
            }
            
            public int method7(final int[] array) {
                return OleFrame.this.SetBorderSpace(array[0]);
            }
            
            public int method8(final int[] array) {
                return OleFrame.this.SetActiveObject(array[0], array[1]);
            }
            
            public int method9(final int[] array) {
                return OleFrame.this.InsertMenus(array[0], array[1]);
            }
            
            public int method10(final int[] array) {
                return OleFrame.this.SetMenu(array[0], array[1], array[2]);
            }
            
            public int method11(final int[] array) {
                return OleFrame.this.RemoveMenus(array[0]);
            }
            
            public int method14(final int[] array) {
                return OleFrame.this.TranslateAccelerator(array[0], array[1]);
            }
        };
    }
    
    private void disposeCOMInterfaces() {
        if (this.iUnknown != null) {
            this.iUnknown.dispose();
        }
        this.iUnknown = null;
        if (this.iOleInPlaceFrame != null) {
            this.iOleInPlaceFrame.dispose();
        }
        this.iOleInPlaceFrame = null;
    }
    
    private int GetBorder(final int n) {
        if (n == 0) {
            return -2147024809;
        }
        final RECT rect = new RECT();
        OS.GetClientRect(this.handle, rect);
        OS.MoveMemory(n, rect, RECT.sizeof);
        return 0;
    }
    
    public MenuItem[] getContainerMenus() {
        return this.containerMenuItems;
    }
    
    public MenuItem[] getFileMenus() {
        return this.fileMenuItems;
    }
    
    int getIOleInPlaceFrame() {
        return this.iOleInPlaceFrame.getAddress();
    }
    
    private int getMenuItemID(final int n, final int n2) {
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 7;
        OS.GetMenuItemInfo(n, n2, true, menuiteminfo);
        int n3;
        if ((menuiteminfo.fState & 0x10) == 0x10) {
            n3 = menuiteminfo.hSubMenu;
        }
        else {
            n3 = menuiteminfo.wID;
        }
        return n3;
    }
    
    private int GetWindow(final int n) {
        if (n != 0) {
            OS.MoveMemory(n, new int[] { this.handle }, OS.PTR_SIZEOF);
        }
        return 0;
    }
    
    public MenuItem[] getWindowMenus() {
        return this.windowMenuItems;
    }
    
    private int InsertMenus(final int n, final int n2) {
        final Menu menuBar = this.getShell().getMenuBar();
        if (menuBar == null || menuBar.isDisposed()) {
            OS.MoveMemory(n2, new int[1], 4);
            return 0;
        }
        final int handle = menuBar.handle;
        final MENUITEMINFO menuiteminfo = new MENUITEMINFO();
        final int getProcessHeap = OS.GetProcessHeap();
        final int n3 = 128;
        final int heapAlloc = OS.HeapAlloc(getProcessHeap, 8, n3 * TCHAR.sizeof);
        menuiteminfo.cbSize = MENUITEMINFO.sizeof;
        menuiteminfo.fMask = 55;
        menuiteminfo.dwTypeData = heapAlloc;
        menuiteminfo.cch = n3;
        int n4 = 0;
        int n5 = 0;
        if (this.fileMenuItems != null) {
            for (int i = 0; i < this.fileMenuItems.length; ++i) {
                final MenuItem menuItem = this.fileMenuItems[i];
                if (menuItem != null) {
                    final int index = menuItem.getParent().indexOf(menuItem);
                    menuiteminfo.cch = n3;
                    if (OS.GetMenuItemInfo(handle, index, true, menuiteminfo) && OS.InsertMenuItem(n, n5, true, menuiteminfo)) {
                        ++n4;
                        ++n5;
                    }
                }
            }
        }
        OS.MoveMemory(n2, new int[] { n4 }, 4);
        int n6 = 0;
        if (this.containerMenuItems != null) {
            for (int j = 0; j < this.containerMenuItems.length; ++j) {
                final MenuItem menuItem2 = this.containerMenuItems[j];
                if (menuItem2 != null) {
                    final int index2 = menuItem2.getParent().indexOf(menuItem2);
                    menuiteminfo.cch = n3;
                    if (OS.GetMenuItemInfo(handle, index2, true, menuiteminfo) && OS.InsertMenuItem(n, n5, true, menuiteminfo)) {
                        ++n6;
                        ++n5;
                    }
                }
            }
        }
        OS.MoveMemory(n2 + 8, new int[] { n6 }, 4);
        int n7 = 0;
        if (this.windowMenuItems != null) {
            for (int k = 0; k < this.windowMenuItems.length; ++k) {
                final MenuItem menuItem3 = this.windowMenuItems[k];
                if (menuItem3 != null) {
                    final int index3 = menuItem3.getParent().indexOf(menuItem3);
                    menuiteminfo.cch = n3;
                    if (OS.GetMenuItemInfo(handle, index3, true, menuiteminfo) && OS.InsertMenuItem(n, n5, true, menuiteminfo)) {
                        ++n7;
                        ++n5;
                    }
                }
            }
        }
        OS.MoveMemory(n2 + 16, new int[] { n7 }, 4);
        if (heapAlloc != 0) {
            OS.HeapFree(getProcessHeap, 0, heapAlloc);
        }
        return 0;
    }
    
    void onActivate(final Event event) {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.OnFrameWindowActivate(true);
        }
    }
    
    void onDeactivate(final Event event) {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.OnFrameWindowActivate(false);
        }
    }
    
    private void onDispose(final Event event) {
        this.releaseObjectInterfaces();
        this.currentdoc = null;
        this.Release();
        this.removeListener(26, this.listener);
        this.removeListener(27, this.listener);
        this.removeListener(12, this.listener);
        this.removeListener(11, this.listener);
        this.removeListener(10, this.listener);
    }
    
    private void onResize(final Event event) {
        if (this.objIOleInPlaceActiveObject != null) {
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            this.objIOleInPlaceActiveObject.ResizeBorder(rect, this.iOleInPlaceFrame.getAddress(), true);
        }
    }
    
    private int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown) || COM.IsEqualGUID(guid, COM.IIDIOleInPlaceFrame)) {
            OS.MoveMemory(n2, new int[] { this.iOleInPlaceFrame.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
            if (COM.FreeUnusedLibraries) {
                COM.CoFreeUnusedLibraries();
            }
        }
        return this.refCount;
    }
    
    private void releaseObjectInterfaces() {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.Release();
        }
        this.objIOleInPlaceActiveObject = null;
    }
    
    private int RemoveMenus(final int n) {
        final Menu menuBar = this.getShell().getMenuBar();
        if (menuBar == null || menuBar.isDisposed()) {
            return 1;
        }
        final int handle = menuBar.handle;
        final Vector<LONG> vector = new Vector<LONG>();
        if (this.fileMenuItems != null) {
            for (int i = 0; i < this.fileMenuItems.length; ++i) {
                final MenuItem menuItem = this.fileMenuItems[i];
                if (menuItem != null && !menuItem.isDisposed()) {
                    vector.addElement(new LONG(this.getMenuItemID(handle, menuItem.getParent().indexOf(menuItem))));
                }
            }
        }
        if (this.containerMenuItems != null) {
            for (int j = 0; j < this.containerMenuItems.length; ++j) {
                final MenuItem menuItem2 = this.containerMenuItems[j];
                if (menuItem2 != null && !menuItem2.isDisposed()) {
                    vector.addElement(new LONG(this.getMenuItemID(handle, menuItem2.getParent().indexOf(menuItem2))));
                }
            }
        }
        if (this.windowMenuItems != null) {
            for (int k = 0; k < this.windowMenuItems.length; ++k) {
                final MenuItem menuItem3 = this.windowMenuItems[k];
                if (menuItem3 != null && !menuItem3.isDisposed()) {
                    vector.addElement(new LONG(this.getMenuItemID(handle, menuItem3.getParent().indexOf(menuItem3))));
                }
            }
        }
        for (int l = OS.GetMenuItemCount(n) - 1; l >= 0; --l) {
            if (vector.contains(new LONG(this.getMenuItemID(n, l)))) {
                OS.RemoveMenu(n, l, 1024);
            }
        }
        return 0;
    }
    
    private int RequestBorderSpace(final int n) {
        return 0;
    }
    
    int SetActiveObject(final int n, final int n2) {
        if (this.objIOleInPlaceActiveObject != null) {
            this.objIOleInPlaceActiveObject.Release();
            this.objIOleInPlaceActiveObject = null;
        }
        if (n != 0) {
            (this.objIOleInPlaceActiveObject = new IOleInPlaceActiveObject(n)).AddRef();
        }
        return 0;
    }
    
    private int SetBorderSpace(final int n) {
        if (this.objIOleInPlaceActiveObject == null) {
            return 0;
        }
        final RECT borderSpace = new RECT();
        if (n == 0 || this.currentdoc == null) {
            return 0;
        }
        COM.MoveMemory(borderSpace, n, RECT.sizeof);
        this.currentdoc.setBorderSpace(borderSpace);
        return 0;
    }
    
    public void setContainerMenus(final MenuItem[] containerMenuItems) {
        this.containerMenuItems = containerMenuItems;
    }
    
    OleClientSite getCurrentDocument() {
        return this.currentdoc;
    }
    
    void setCurrentDocument(final OleClientSite currentdoc) {
        this.currentdoc = currentdoc;
        if (this.currentdoc != null && this.objIOleInPlaceActiveObject != null) {
            final RECT rect = new RECT();
            OS.GetClientRect(this.handle, rect);
            this.objIOleInPlaceActiveObject.ResizeBorder(rect, this.iOleInPlaceFrame.getAddress(), true);
        }
    }
    
    public void setFileMenus(final MenuItem[] fileMenuItems) {
        this.fileMenuItems = fileMenuItems;
    }
    
    private int SetMenu(int handle, final int n, final int n2) {
        int address = 0;
        if (this.objIOleInPlaceActiveObject != null) {
            address = this.objIOleInPlaceActiveObject.getAddress();
        }
        final Menu menuBar = this.getShell().getMenuBar();
        if (menuBar == null || menuBar.isDisposed()) {
            return COM.OleSetMenuDescriptor(0, this.getShell().handle, n2, this.iOleInPlaceFrame.getAddress(), address);
        }
        final int handle2 = menuBar.getShell().handle;
        if (handle == 0 && n == 0) {
            handle = menuBar.handle;
        }
        if (handle == 0) {
            return -2147467259;
        }
        OS.SetMenu(handle2, handle);
        OS.DrawMenuBar(handle2);
        return COM.OleSetMenuDescriptor(n, handle2, n2, this.iOleInPlaceFrame.getAddress(), address);
    }
    
    public void setWindowMenus(final MenuItem[] windowMenuItems) {
        this.windowMenuItems = windowMenuItems;
    }
    
    private boolean translateOleAccelerator(final MSG msg) {
        if (this.objIOleInPlaceActiveObject == null) {
            return false;
        }
        final int translateAccelerator = this.objIOleInPlaceActiveObject.TranslateAccelerator(msg);
        return translateAccelerator != 1 && translateAccelerator != -2147467263;
    }
    
    private int TranslateAccelerator(final int n, final int n2) {
        final Menu menuBar = this.getShell().getMenuBar();
        if (menuBar == null || menuBar.isDisposed() || !menuBar.isEnabled()) {
            return 1;
        }
        if (n2 < 0) {
            return 1;
        }
        final int handle = menuBar.getShell().handle;
        final int sendMessage = OS.SendMessage(handle, 32769, 0, 0);
        if (sendMessage == 0) {
            return 1;
        }
        final MSG msg = new MSG();
        OS.MoveMemory(msg, n, MSG.sizeof);
        return (OS.TranslateAccelerator(handle, sendMessage, msg) == 0) ? 1 : 0;
    }
}
