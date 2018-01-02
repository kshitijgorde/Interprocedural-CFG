// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.ole.win32.IDispatch;
import org.eclipse.swt.internal.ole.win32.EXCEPINFO;
import org.eclipse.swt.internal.ole.win32.VARIANT;
import org.eclipse.swt.internal.ole.win32.IDispatchEx;
import org.eclipse.swt.internal.ole.win32.TYPEATTR;
import org.eclipse.swt.SWT;
import java.util.Hashtable;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.internal.ole.win32.DISPPARAMS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.internal.win32.POINT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.internal.win32.DOCHOSTUIINFO;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.internal.ole.win32.COMObject;
import org.eclipse.swt.ole.win32.OleControlSite;

class WebSite extends OleControlSite
{
    COMObject iDocHostUIHandler;
    COMObject iDocHostShowUI;
    COMObject iServiceProvider;
    COMObject iInternetSecurityManager;
    COMObject iOleCommandTarget;
    COMObject iAuthenticate;
    COMObject iDispatch;
    boolean ignoreNextMessage;
    boolean ignoreAllMessages;
    Boolean canExecuteApplets;
    static final int OLECMDID_SHOWSCRIPTERROR = 40;
    static final short[] ACCENTS;
    static final String CONSUME_KEY = "org.eclipse.swt.OleFrame.ConsumeKey";
    
    static {
        ACCENTS = new short[] { 126, 96, 39, 94, 34 };
    }
    
    public WebSite(final Composite composite, final int n, final String s) {
        super(composite, n, s);
    }
    
    protected void createCOMInterfaces() {
        super.createCOMInterfaces();
        this.iDocHostUIHandler = new COMObject(new int[] { 2, 0, 0, 4, 1, 5, 0, 0, 1, 1, 1, 3, 3, 2, 2, 1, 3, 2 }) {
            public int method0(final int[] array) {
                return WebSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebSite.this.ShowContextMenu(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return WebSite.this.GetHostInfo(array[0]);
            }
            
            public int method5(final int[] array) {
                return WebSite.this.ShowUI(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method6(final int[] array) {
                return WebSite.this.HideUI();
            }
            
            public int method7(final int[] array) {
                return WebSite.this.UpdateUI();
            }
            
            public int method8(final int[] array) {
                return WebSite.this.EnableModeless(array[0]);
            }
            
            public int method9(final int[] array) {
                return WebSite.this.OnDocWindowActivate(array[0]);
            }
            
            public int method10(final int[] array) {
                return WebSite.this.OnFrameWindowActivate(array[0]);
            }
            
            public int method11(final int[] array) {
                return WebSite.this.ResizeBorder(array[0], array[1], array[2]);
            }
            
            public int method12(final int[] array) {
                return WebSite.this.TranslateAccelerator(array[0], array[1], array[2]);
            }
            
            public int method13(final int[] array) {
                return WebSite.this.GetOptionKeyPath(array[0], array[1]);
            }
            
            public int method14(final int[] array) {
                return WebSite.this.GetDropTarget(array[0], array[1]);
            }
            
            public int method15(final int[] array) {
                return WebSite.this.GetExternal(array[0]);
            }
            
            public int method16(final int[] array) {
                return WebSite.this.TranslateUrl(array[0], array[1], array[2]);
            }
            
            public int method17(final int[] array) {
                return WebSite.this.FilterDataObject(array[0], array[1]);
            }
        };
        this.iDocHostShowUI = new COMObject(new int[] { 2, 0, 0, 7, (C.PTR_SIZEOF == 4) ? 7 : 6 }) {
            public int method0(final int[] array) {
                return WebSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebSite.this.ShowMessage(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method4(final int[] array) {
                if (array.length == 7) {
                    return WebSite.this.ShowHelp(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
                }
                return WebSite.this.ShowHelp_64(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
        };
        this.iServiceProvider = new COMObject(new int[] { 2, 0, 0, 3 }) {
            public int method0(final int[] array) {
                return WebSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebSite.this.QueryService(array[0], array[1], array[2]);
            }
        };
        this.iInternetSecurityManager = new COMObject(new int[] { 2, 0, 0, 1, 1, 3, 4, 8, 7, 3, 3 }) {
            public int method0(final int[] array) {
                return WebSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebSite.this.SetSecuritySite(array[0]);
            }
            
            public int method4(final int[] array) {
                return WebSite.this.GetSecuritySite(array[0]);
            }
            
            public int method5(final int[] array) {
                return WebSite.this.MapUrlToZone(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return WebSite.this.GetSecurityId(array[0], array[1], array[2], array[3]);
            }
            
            public int method7(final int[] array) {
                return WebSite.this.ProcessUrlAction(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
            
            public int method8(final int[] array) {
                return WebSite.this.QueryCustomPolicy(array[0], array[1], array[2], array[3], array[4], array[5], array[6]);
            }
            
            public int method9(final int[] array) {
                return WebSite.this.SetZoneMapping(array[0], array[1], array[2]);
            }
            
            public int method10(final int[] array) {
                return WebSite.this.GetZoneMappings(array[0], array[1], array[2]);
            }
        };
        this.iOleCommandTarget = new COMObject(new int[] { 2, 0, 0, 4, 5 }) {
            public int method0(final int[] array) {
                return WebSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebSite.this.QueryStatus(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return WebSite.this.Exec(array[0], array[1], array[2], array[3], array[4]);
            }
        };
        this.iAuthenticate = new COMObject(new int[] { 2, 0, 0, 3 }) {
            public int method0(final int[] array) {
                return WebSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebSite.this.Authenticate(array[0], array[1], array[2]);
            }
        };
        this.iDispatch = new COMObject(new int[] { 2, 0, 0, 1, 3, 5, 8 }) {
            public int method0(final int[] array) {
                final GUID guid = new GUID();
                COM.MoveMemory(guid, array[0], GUID.sizeof);
                if (COM.IsEqualGUID(guid, COM.IIDIDispatch)) {
                    OS.MoveMemory(array[1], new int[] { WebSite.this.iDispatch.getAddress() }, OS.PTR_SIZEOF);
                    WebSite.this.AddRef();
                    return 0;
                }
                return WebSite.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebSite.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebSite.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebSite.this.GetTypeInfoCount(array[0]);
            }
            
            public int method4(final int[] array) {
                return WebSite.this.GetTypeInfo(array[0], array[1], array[2]);
            }
            
            public int method5(final int[] array) {
                return WebSite.this.GetIDsOfNames(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method6(final int[] array) {
                return WebSite.this.Invoke(array[0], array[1], array[2], array[3], array[4], array[5], array[6], array[7]);
            }
        };
    }
    
    protected void disposeCOMInterfaces() {
        super.disposeCOMInterfaces();
        if (this.iDocHostUIHandler != null) {
            this.iDocHostUIHandler.dispose();
            this.iDocHostUIHandler = null;
        }
        if (this.iDocHostShowUI != null) {
            this.iDocHostShowUI.dispose();
            this.iDocHostShowUI = null;
        }
        if (this.iServiceProvider != null) {
            this.iServiceProvider.dispose();
            this.iServiceProvider = null;
        }
        if (this.iInternetSecurityManager != null) {
            this.iInternetSecurityManager.dispose();
            this.iInternetSecurityManager = null;
        }
        if (this.iOleCommandTarget != null) {
            this.iOleCommandTarget.dispose();
            this.iOleCommandTarget = null;
        }
        if (this.iAuthenticate != null) {
            this.iAuthenticate.dispose();
            this.iAuthenticate = null;
        }
        if (this.iDispatch != null) {
            this.iDispatch.dispose();
            this.iDispatch = null;
        }
    }
    
    protected int AddRef() {
        return super.AddRef();
    }
    
    protected int QueryInterface(final int n, final int n2) {
        final int queryInterface = super.QueryInterface(n, n2);
        if (queryInterface == 0) {
            return queryInterface;
        }
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIDocHostUIHandler)) {
            OS.MoveMemory(n2, new int[] { this.iDocHostUIHandler.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIDocHostShowUI)) {
            OS.MoveMemory(n2, new int[] { this.iDocHostShowUI.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIServiceProvider)) {
            OS.MoveMemory(n2, new int[] { this.iServiceProvider.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIInternetSecurityManager)) {
            OS.MoveMemory(n2, new int[] { this.iInternetSecurityManager.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIOleCommandTarget)) {
            OS.MoveMemory(n2, new int[] { this.iOleCommandTarget.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    int EnableModeless(final int n) {
        return -2147467263;
    }
    
    int FilterDataObject(final int n, final int n2) {
        return -2147467263;
    }
    
    int GetDropTarget(final int n, final int n2) {
        return -2147467263;
    }
    
    int GetExternal(final int n) {
        OS.MoveMemory(n, new int[] { this.iDispatch.getAddress() }, C.PTR_SIZEOF);
        this.AddRef();
        return 0;
    }
    
    int GetHostInfo(final int n) {
        int dwFlags = 262144;
        if ((((IE)((Browser)this.getParent().getParent()).webBrowser).style & 0x800) == 0x0) {
            dwFlags |= 0x200000;
        }
        final DOCHOSTUIINFO dochostuiinfo = new DOCHOSTUIINFO();
        OS.MoveMemory(dochostuiinfo, n, DOCHOSTUIINFO.sizeof);
        dochostuiinfo.dwFlags = dwFlags;
        OS.MoveMemory(n, dochostuiinfo, DOCHOSTUIINFO.sizeof);
        return 0;
    }
    
    int GetOptionKeyPath(final int n, final int n2) {
        return -2147467263;
    }
    
    int HideUI() {
        return -2147467263;
    }
    
    int OnDocWindowActivate(final int n) {
        return -2147467263;
    }
    
    int OnFrameWindowActivate(final int n) {
        return -2147467263;
    }
    
    protected int Release() {
        return super.Release();
    }
    
    int ResizeBorder(final int n, final int n2, final int n3) {
        return -2147467263;
    }
    
    int ShowContextMenu(final int n, final int n2, final int n3, final int n4) {
        final Browser browser = (Browser)this.getParent().getParent();
        final Event event = new Event();
        final POINT point = new POINT();
        OS.MoveMemory(point, n2, POINT.sizeof);
        event.x = point.x;
        event.y = point.y;
        browser.notifyListeners(35, event);
        if (!event.doit) {
            return 0;
        }
        final Menu menu = browser.getMenu();
        if (menu != null && !menu.isDisposed()) {
            if (point.x != event.x || point.y != event.y) {
                menu.setLocation(event.x, event.y);
            }
            menu.setVisible(true);
            return 0;
        }
        return 1;
    }
    
    int ShowUI(final int n, final int n2, final int n3, final int n4, final int n5) {
        return 1;
    }
    
    int TranslateAccelerator(final int n, final int n2, final int n3) {
        final Menu menuBar = this.getShell().getMenuBar();
        if (menuBar != null && !menuBar.isDisposed() && menuBar.isEnabled()) {
            final int handle = menuBar.getShell().handle;
            final int sendMessage = OS.SendMessage(handle, 32769, 0, 0);
            if (sendMessage != 0) {
                final MSG msg = new MSG();
                OS.MoveMemory(msg, n, MSG.sizeof);
                if (OS.TranslateAccelerator(handle, sendMessage, msg) != 0) {
                    return 0;
                }
            }
        }
        int n4 = 1;
        final MSG msg2 = new MSG();
        OS.MoveMemory(msg2, n, MSG.sizeof);
        Label_0359: {
            if (msg2.message == 256) {
                switch (msg2.wParam) {
                    case 116: {
                        final OleAutomation oleAutomation = new OleAutomation(this);
                        final Variant property = oleAutomation.getProperty(oleAutomation.getIDsOfNames(new String[] { "LocationURL" })[0]);
                        oleAutomation.dispose();
                        if (property != null) {
                            if (property.getType() == 8 && property.getString().equals("about:blank")) {
                                n4 = 0;
                            }
                            property.dispose();
                        }
                        break Label_0359;
                    }
                    case 9: {}
                    case 8:
                    case 13: {
                        break Label_0359;
                    }
                    case 76:
                    case 78: {
                        if (OS.GetKeyState(17) < 0 && OS.GetKeyState(18) >= 0 && OS.GetKeyState(16) >= 0 && (msg2.wParam == 78 || IE.IEVersion >= 8)) {
                            this.frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", "false");
                            n4 = 0;
                            break Label_0359;
                        }
                        break;
                    }
                }
                OS.TranslateMessage(msg2);
                this.frame.setData("org.eclipse.swt.OleFrame.ConsumeKey", "true");
            }
        }
        switch (msg2.message) {
            case 256:
            case 257: {
                if (OS.IsWinCE) {
                    break;
                }
                int n5 = 0;
                switch (msg2.wParam) {
                    case 16:
                    case 17:
                    case 18:
                    case 20:
                    case 144:
                    case 145: {
                        break;
                    }
                    default: {
                        final int mapVirtualKey = OS.MapVirtualKey(msg2.wParam, 2);
                        if (mapVirtualKey == 0) {
                            break;
                        }
                        n5 = (((mapVirtualKey & (OS.IsWinNT ? Integer.MIN_VALUE : 32768)) != 0x0) ? 1 : 0);
                        if (n5 == 0) {
                            for (int i = 0; i < WebSite.ACCENTS.length; ++i) {
                                final short vkKeyScan = OS.VkKeyScan(WebSite.ACCENTS[i]);
                                if (vkKeyScan != -1 && (vkKeyScan & 0xFF) == msg2.wParam) {
                                    final int n6 = vkKeyScan >> 8;
                                    if (OS.GetKeyState(16) < 0 == ((n6 & 0x1) != 0x0) && OS.GetKeyState(17) < 0 == ((n6 & 0x2) != 0x0) && OS.GetKeyState(18) < 0 == ((n6 & 0x4) != 0x0)) {
                                        if ((n6 & 0x7) != 0x0) {
                                            n5 = 1;
                                            break;
                                        }
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                        break;
                    }
                }
                if (n5 != 0) {
                    n4 = 0;
                    break;
                }
                break;
            }
        }
        return n4;
    }
    
    int TranslateUrl(final int n, final int n2, final int n3) {
        return -2147467263;
    }
    
    int UpdateUI() {
        return -2147467263;
    }
    
    int ShowMessage(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final boolean b = this.ignoreNextMessage || this.ignoreAllMessages;
        this.ignoreNextMessage = false;
        return b ? 0 : 1;
    }
    
    int ShowHelp_64(final int n, final int n2, final int n3, final int n4, final long n5, final int n6) {
        final POINT point = new POINT();
        OS.MoveMemory(point, new long[] { n5 }, 8);
        return this.ShowHelp(n, n2, n3, n4, point.x, point.y, n6);
    }
    
    int ShowHelp(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        final Browser widget = (Browser)this.getParent().getParent();
        final Event event = new Event();
        event.type = 28;
        event.display = this.getDisplay();
        event.widget = widget;
        final Shell shell = widget.getShell();
        Composite parent;
        for (parent = widget; !parent.isListening(28); parent = parent.getParent()) {
            if (parent == shell) {
                return 0;
            }
        }
        parent.notifyListeners(28, event);
        return 0;
    }
    
    int QueryService(final int n, final int n2, final int n3) {
        if (n2 == 0 || n3 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n2, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIInternetSecurityManager)) {
            OS.MoveMemory(n3, new int[] { this.iInternetSecurityManager.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, COM.IIDIAuthenticate)) {
            OS.MoveMemory(n3, new int[] { this.iAuthenticate.getAddress() }, OS.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        OS.MoveMemory(n3, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    int SetSecuritySite(final int n) {
        return -2146697199;
    }
    
    int GetSecuritySite(final int n) {
        return -2146697199;
    }
    
    int MapUrlToZone(final int n, final int n2, final int n3) {
        final IE ie = (IE)((Browser)this.getParent().getParent()).webBrowser;
        if (ie.auto != null && ie.isAboutBlank && !ie.untrustedText) {
            OS.MoveMemory(n2, new int[] { 1 }, 4);
            return 0;
        }
        return -2146697199;
    }
    
    int GetSecurityId(final int n, final int n2, final int n3, final int n4) {
        return -2146697199;
    }
    
    int ProcessUrlAction(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.ignoreNextMessage = false;
        if (n2 == 8449) {
            final IE ie = (IE)((Browser)this.getParent().getParent()).webBrowser;
            if (ie.auto != null && ie._getUrl().startsWith("about:blank") && !ie.untrustedText) {
                if (n4 >= 4) {
                    OS.MoveMemory(n3, new int[1], 4);
                }
                return 0;
            }
        }
        int n9 = -2146697199;
        if (n2 >= 7168 && n2 <= 7423) {
            if (this.canExecuteApplets()) {
                n9 = 196608;
            }
            else {
                n9 = 0;
                this.ignoreNextMessage = true;
            }
        }
        if (n2 == 4608) {
            final GUID guid = new GUID();
            COM.MoveMemory(guid, n5, GUID.sizeof);
            if (COM.IsEqualGUID(guid, COM.IIDJavaBeansBridge) && !this.canExecuteApplets()) {
                n9 = 3;
                this.ignoreNextMessage = true;
            }
            if (COM.IsEqualGUID(guid, COM.IIDShockwaveActiveXControl)) {
                n9 = 3;
                this.ignoreNextMessage = true;
            }
        }
        if (n2 == 5120) {
            n9 = (((IE)((Browser)this.getParent().getParent()).webBrowser).jsEnabled ? 0 : 3);
        }
        if (n9 == -2146697199) {
            return -2146697199;
        }
        if (n4 >= 4) {
            OS.MoveMemory(n3, new int[] { n9 }, 4);
        }
        return (n9 != 0) ? 1 : 0;
    }
    
    boolean canExecuteApplets() {
        if (IE.IEVersion < 7) {
            return false;
        }
        if (this.canExecuteApplets == null) {
            this.canExecuteApplets = (Boolean)((Browser)this.getParent().getParent()).webBrowser.evaluate("try {var element = document.createElement('object');element.classid='clsid:CAFEEFAC-DEC7-0000-0000-ABCDEFFEDCBA';return element.object.isPlugin2();} catch (err) {};return false;");
            if (this.canExecuteApplets) {
                try {
                    Class.forName("sun.plugin2.main.server.IExplorerPlugin");
                    Class.forName("com.sun.deploy.services.Service");
                    Class.forName("com.sun.javaws.Globals");
                }
                catch (ClassNotFoundException ex) {
                    this.canExecuteApplets = Boolean.FALSE;
                }
            }
        }
        return this.canExecuteApplets;
    }
    
    int QueryCustomPolicy(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        return -2146697199;
    }
    
    int SetZoneMapping(final int n, final int n2, final int n3) {
        return -2146697199;
    }
    
    int GetZoneMappings(final int n, final int n2, final int n3) {
        return -2147467263;
    }
    
    int QueryStatus(final int n, final int n2, final int n3, final int n4) {
        return -2147221248;
    }
    
    int Exec(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n != 0) {
            final GUID guid = new GUID();
            COM.MoveMemory(guid, n, GUID.sizeof);
            if (COM.IsEqualGUID(guid, COM.CGID_DocHostCommandHandler) && n2 == 40) {
                return 0;
            }
            if (n2 == 1 && COM.IsEqualGUID(guid, COM.CGID_Explorer) && (n3 & 0xFFFF) == 0xA) {
                ((IE)((Browser)this.getParent().getParent()).webBrowser).toolBar = ((n3 & 0xFFFF0000) != 0x0);
            }
        }
        return -2147221248;
    }
    
    int Authenticate(final int n, final int n2, final int n3) {
        final IE ie = (IE)((Browser)this.getParent().getParent()).webBrowser;
        for (int i = 0; i < ie.authenticationListeners.length; ++i) {
            final AuthenticationEvent authenticationEvent = new AuthenticationEvent(ie.browser);
            authenticationEvent.location = ie.lastNavigateURL;
            ie.authenticationListeners[i].authenticate(authenticationEvent);
            if (!authenticationEvent.doit) {
                return -2147024891;
            }
            if (authenticationEvent.user != null && authenticationEvent.password != null) {
                final TCHAR tchar = new TCHAR(0, authenticationEvent.user, true);
                final int n4 = tchar.length() * TCHAR.sizeof;
                final int coTaskMemAlloc = OS.CoTaskMemAlloc(n4);
                OS.MoveMemory(coTaskMemAlloc, tchar, n4);
                final TCHAR tchar2 = new TCHAR(0, authenticationEvent.password, true);
                final int n5 = tchar2.length() * TCHAR.sizeof;
                final int coTaskMemAlloc2 = OS.CoTaskMemAlloc(n5);
                OS.MoveMemory(coTaskMemAlloc2, tchar2, n5);
                C.memmove(n, new int[1], C.PTR_SIZEOF);
                C.memmove(n2, new int[] { coTaskMemAlloc }, C.PTR_SIZEOF);
                C.memmove(n3, new int[] { coTaskMemAlloc2 }, C.PTR_SIZEOF);
                return 0;
            }
        }
        C.memmove(n, new int[] { this.getShell().handle }, C.PTR_SIZEOF);
        return 0;
    }
    
    int GetTypeInfoCount(final int n) {
        C.memmove(n, new int[1], 4);
        return 0;
    }
    
    int GetTypeInfo(final int n, final int n2, final int n3) {
        return 0;
    }
    
    int GetIDsOfNames(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int[] array = { 0 };
        OS.MoveMemory(array, n2, C.PTR_SIZEOF);
        final int wcslen = OS.wcslen(array[0]);
        final char[] array2 = new char[wcslen];
        OS.MoveMemory(array2, array[0], wcslen * 2);
        final String value = String.valueOf(array2);
        int n6 = 0;
        final int[] array3 = new int[n3];
        if (value.equals("callJava")) {
            for (int i = 0; i < n3; ++i) {
                array3[i] = i + 1;
            }
        }
        else {
            n6 = -2147352570;
            for (int j = 0; j < n3; ++j) {
                array3[j] = -1;
            }
        }
        OS.MoveMemory(n5, array3, n3 * 4);
        return n6;
    }
    
    int Invoke(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final Hashtable functions = ((IE)((Browser)this.getParent().getParent()).webBrowser).functions;
        if (functions == null) {
            if (n6 != 0) {
                OS.MoveMemory(n6, new int[1], C.PTR_SIZEOF);
            }
            return 0;
        }
        final DISPPARAMS dispparams = new DISPPARAMS();
        COM.MoveMemory(dispparams, n5, DISPPARAMS.sizeof);
        if (dispparams.cArgs != 2) {
            if (n6 != 0) {
                OS.MoveMemory(n6, new int[1], C.PTR_SIZEOF);
            }
            return 0;
        }
        final Variant win32_new = Variant.win32_new(dispparams.rgvarg + Variant.sizeof);
        if (win32_new.getType() != 3) {
            win32_new.dispose();
            if (n6 != 0) {
                OS.MoveMemory(n6, new int[1], C.PTR_SIZEOF);
            }
            return 0;
        }
        final int int1 = win32_new.getInt();
        win32_new.dispose();
        if (int1 <= 0) {
            if (n6 != 0) {
                OS.MoveMemory(n6, new int[1], C.PTR_SIZEOF);
            }
            return 0;
        }
        final Variant win32_new2 = Variant.win32_new(dispparams.rgvarg);
        final BrowserFunction browserFunction = functions.get(new Integer(int1));
        Object o = null;
        if (browserFunction != null) {
            try {
                final Object convertToJava = this.convertToJava(win32_new2);
                if (convertToJava instanceof Object[]) {
                    final Object[] array = (Object[])convertToJava;
                    try {
                        o = browserFunction.function(array);
                    }
                    catch (Exception ex) {
                        o = WebBrowser.CreateErrorString(ex.getLocalizedMessage());
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                if (browserFunction.isEvaluate) {
                    browserFunction.function(new String[] { WebBrowser.CreateErrorString(new SWTException(51).getLocalizedMessage()) });
                }
                o = WebBrowser.CreateErrorString(ex2.getLocalizedMessage());
            }
        }
        win32_new2.dispose();
        if (n6 != 0) {
            Variant variant;
            try {
                variant = this.convertToJS(o);
            }
            catch (SWTException ex3) {
                variant = this.convertToJS(WebBrowser.CreateErrorString(ex3.getLocalizedMessage()));
            }
            Variant.win32_copy(n6, variant);
            variant.dispose();
        }
        return 0;
    }
    
    Object convertToJava(final Variant variant) {
        switch (variant.getType()) {
            case 1: {
                return null;
            }
            case 8: {
                return variant.getString();
            }
            case 11: {
                return new Boolean(variant.getBoolean());
            }
            case 2:
            case 3:
            case 4:
            case 5:
            case 20: {
                return new Double(variant.getDouble());
            }
            case 9: {
                Object[] array = null;
                final OleAutomation automation = variant.getAutomation();
                final TYPEATTR typeInfoAttributes = automation.getTypeInfoAttributes();
                if (typeInfoAttributes != null) {
                    final GUID guid = new GUID();
                    guid.Data1 = typeInfoAttributes.guid_Data1;
                    guid.Data2 = typeInfoAttributes.guid_Data2;
                    guid.Data3 = typeInfoAttributes.guid_Data3;
                    guid.Data4 = typeInfoAttributes.guid_Data4;
                    if (COM.IsEqualGUID(guid, COM.IIDIJScriptTypeInfo)) {
                        final int[] iDsOfNames = automation.getIDsOfNames(new String[] { "length" });
                        if (iDsOfNames != null) {
                            final Variant property = automation.getProperty(iDsOfNames[0]);
                            final int int1 = property.getInt();
                            property.dispose();
                            array = new Object[int1];
                            for (int i = 0; i < int1; ++i) {
                                final int[] iDsOfNames2 = automation.getIDsOfNames(new String[] { String.valueOf(i) });
                                if (iDsOfNames2 != null) {
                                    final Variant property2 = automation.getProperty(iDsOfNames2[0]);
                                    try {
                                        array[i] = this.convertToJava(property2);
                                        property2.dispose();
                                    }
                                    catch (IllegalArgumentException ex) {
                                        property2.dispose();
                                        automation.dispose();
                                        throw ex;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        automation.dispose();
                        SWT.error(5);
                    }
                }
                automation.dispose();
                return array;
            }
            default: {
                SWT.error(5);
                return null;
            }
        }
    }
    
    Variant convertToJS(final Object o) {
        if (o == null) {
            return Variant.NULL;
        }
        if (o instanceof String) {
            return new Variant((String)o);
        }
        if (o instanceof Boolean) {
            return new Variant((boolean)o);
        }
        if (o instanceof Number) {
            return new Variant(((Number)o).doubleValue());
        }
        if (!(o instanceof Object[])) {
            SWT.error(51);
            return null;
        }
        final OleAutomation auto = ((IE)((Browser)this.getParent().getParent()).webBrowser).auto;
        final int[] iDsOfNames = auto.getIDsOfNames(new String[] { "Document" });
        if (iDsOfNames == null) {
            return new Variant();
        }
        final Variant property = auto.getProperty(iDsOfNames[0]);
        if (property == null) {
            return new Variant();
        }
        if (property.getType() == 0) {
            property.dispose();
            return new Variant();
        }
        final OleAutomation automation = property.getAutomation();
        property.dispose();
        final int[] iDsOfNames2 = automation.getIDsOfNames(new String[] { "parentWindow" });
        if (iDsOfNames2 == null) {
            automation.dispose();
            return new Variant();
        }
        final Variant property2 = automation.getProperty(iDsOfNames2[0]);
        if (property2 == null || property2.getType() == 0) {
            if (property2 != null) {
                property2.dispose();
            }
            automation.dispose();
            return new Variant();
        }
        final OleAutomation automation2 = property2.getAutomation();
        property2.dispose();
        automation.dispose();
        final int[] iDsOfNames3 = automation2.getIDsOfNames(new String[] { "Array" });
        if (iDsOfNames3 == null) {
            automation2.dispose();
            return new Variant();
        }
        final Variant property3 = automation2.getProperty(iDsOfNames3[0]);
        automation2.dispose();
        final IDispatch dispatch = property3.getDispatch();
        property3.dispose();
        final int[] array = { 0 };
        if (dispatch.QueryInterface(COM.IIDIDispatchEx, array) != 0) {
            return new Variant();
        }
        final IDispatchEx dispatchEx = new IDispatchEx(array[0]);
        array[0] = 0;
        final int globalAlloc = OS.GlobalAlloc(64, VARIANT.sizeof);
        if (dispatchEx.InvokeEx(0, 2048, 16384, new DISPPARAMS(), globalAlloc, null, 0) != 0) {
            OS.GlobalFree(globalAlloc);
            return new Variant();
        }
        final Variant win32_new = Variant.win32_new(globalAlloc);
        OS.GlobalFree(globalAlloc);
        final Object[] array2 = (Object[])o;
        final int length = array2.length;
        final OleAutomation automation3 = win32_new.getAutomation();
        final int[] iDsOfNames4 = automation3.getIDsOfNames(new String[] { "push" });
        if (iDsOfNames4 != null) {
            for (final Object o2 : array2) {
                try {
                    final Variant convertToJS = this.convertToJS(o2);
                    automation3.invoke(iDsOfNames4[0], new Variant[] { convertToJS });
                    convertToJS.dispose();
                }
                catch (SWTException ex) {
                    automation3.dispose();
                    win32_new.dispose();
                    throw ex;
                }
            }
        }
        automation3.dispose();
        return win32_new;
    }
}
