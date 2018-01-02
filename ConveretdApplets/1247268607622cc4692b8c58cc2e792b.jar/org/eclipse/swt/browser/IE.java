// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.internal.win32.MSG;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.IPersistStreamInit;
import org.eclipse.swt.internal.win32.SAFEARRAYBOUND;
import org.eclipse.swt.internal.win32.SAFEARRAY;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.ole.win32.IDispatch;
import org.eclipse.swt.internal.ole.win32.COM;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Widget;
import java.util.Enumeration;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.ole.win32.OleEvent;
import org.eclipse.swt.ole.win32.OleClientSite;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import java.util.StringTokenizer;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.ole.win32.OleListener;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleFrame;

class IE extends WebBrowser
{
    OleFrame frame;
    WebSite site;
    OleAutomation auto;
    OleListener domListener;
    OleAutomation[] documents;
    boolean back;
    boolean forward;
    boolean delaySetText;
    boolean ignoreDispose;
    boolean ignoreTraverse;
    boolean performingInitialNavigate;
    boolean installFunctionsOnDocumentComplete;
    boolean untrustedText;
    boolean isRefresh;
    boolean isAboutBlank;
    Point location;
    Point size;
    boolean addressBar;
    boolean menuBar;
    boolean statusBar;
    boolean toolBar;
    int globalDispatch;
    String html;
    String lastNavigateURL;
    String uncRedirect;
    Object[] pendingText;
    Object[] pendingUrl;
    int style;
    int lastKeyCode;
    int lastCharCode;
    int lastMouseMoveX;
    int lastMouseMoveY;
    static int IEVersion;
    static int PDFCount;
    static String ProgId;
    static final int BeforeNavigate2 = 250;
    static final int CommandStateChange = 105;
    static final int DocumentComplete = 259;
    static final int DownloadComplete = 104;
    static final int NavigateComplete2 = 252;
    static final int NewWindow2 = 251;
    static final int OnMenuBar = 256;
    static final int OnStatusBar = 257;
    static final int OnToolBar = 255;
    static final int OnVisible = 254;
    static final int ProgressChange = 108;
    static final int RegisterAsBrowser = 552;
    static final int StatusTextChange = 102;
    static final int TitleChange = 113;
    static final int WindowClosing = 263;
    static final int WindowSetHeight = 267;
    static final int WindowSetLeft = 264;
    static final int WindowSetResizable = 262;
    static final int WindowSetTop = 265;
    static final int WindowSetWidth = 266;
    static final int NavigateError = 271;
    static final short CSC_NAVIGATEFORWARD = 1;
    static final short CSC_NAVIGATEBACK = 2;
    static final int INET_E_DEFAULT_ACTION = -2146697199;
    static final int INET_E_RESOURCE_NOT_FOUND = -2146697211;
    static final int READYSTATE_COMPLETE = 4;
    static final int URLPOLICY_ALLOW = 0;
    static final int URLPOLICY_DISALLOW = 3;
    static final int URLPOLICY_JAVA_PROHIBIT = 0;
    static final int URLPOLICY_JAVA_LOW = 196608;
    static final int URLZONE_LOCAL_MACHINE = 0;
    static final int URLZONE_INTRANET = 1;
    static final int URLACTION_ACTIVEX_MIN = 4608;
    static final int URLACTION_ACTIVEX_MAX = 5119;
    static final int URLACTION_ACTIVEX_RUN = 4608;
    static final int URLACTION_FEATURE_ZONE_ELEVATION = 8449;
    static final int URLACTION_JAVA_MIN = 7168;
    static final int URLACTION_JAVA_MAX = 7423;
    static final int URLACTION_SCRIPT_RUN = 5120;
    static final int DISPID_AMBIENT_DLCONTROL = -5512;
    static final int DLCTL_DLIMAGES = 16;
    static final int DLCTL_VIDEOS = 32;
    static final int DLCTL_BGSOUNDS = 64;
    static final int DLCTL_NO_SCRIPTS = 128;
    static final int DLCTL_NO_JAVA = 256;
    static final int DLCTL_NO_RUNACTIVEXCTLS = 512;
    static final int DLCTL_NO_DLACTIVEXCTLS = 1024;
    static final int DLCTL_DOWNLOADONLY = 2048;
    static final int DLCTL_NO_FRAMEDOWNLOAD = 4096;
    static final int DLCTL_RESYNCHRONIZE = 8192;
    static final int DLCTL_PRAGMA_NO_CACHE = 16384;
    static final int DLCTL_FORCEOFFLINE = 268435456;
    static final int DLCTL_NO_CLIENTPULL = 536870912;
    static final int DLCTL_SILENT = 1073741824;
    static final int DOCHOSTUIFLAG_THEME = 262144;
    static final int DOCHOSTUIFLAG_NO3DBORDER = 4;
    static final int DOCHOSTUIFLAG_NO3DOUTERBORDER = 2097152;
    static final String ABOUT_BLANK = "about:blank";
    static final String CLSID_SHELLEXPLORER1 = "{EAB22AC3-30C1-11CF-A7EB-0000C05BAE0B}";
    static final String EXTENSION_PDF = ".pdf";
    static final String HTML_DOCUMENT = "HTML Document";
    static final int MAX_PDF = 20;
    static final String EVENT_DOUBLECLICK = "dblclick";
    static final String EVENT_DRAGEND = "dragend";
    static final String EVENT_DRAGSTART = "dragstart";
    static final String EVENT_KEYDOWN = "keydown";
    static final String EVENT_KEYPRESS = "keypress";
    static final String EVENT_KEYUP = "keyup";
    static final String EVENT_MOUSEMOVE = "mousemove";
    static final String EVENT_MOUSEWHEEL = "mousewheel";
    static final String EVENT_MOUSEUP = "mouseup";
    static final String EVENT_MOUSEDOWN = "mousedown";
    static final String EVENT_MOUSEOUT = "mouseout";
    static final String EVENT_MOUSEOVER = "mouseover";
    static final String PROTOCOL_FILE = "file://";
    static final String PROPERTY_ALTKEY = "altKey";
    static final String PROPERTY_BUTTON = "button";
    static final String PROPERTY_CTRLKEY = "ctrlKey";
    static final String PROPERTY_DOCUMENT = "Document";
    static final String PROPERTY_FROMELEMENT = "fromElement";
    static final String PROPERTY_KEYCODE = "keyCode";
    static final String PROPERTY_REPEAT = "repeat";
    static final String PROPERTY_RETURNVALUE = "returnValue";
    static final String PROPERTY_SCREENX = "screenX";
    static final String PROPERTY_SCREENY = "screenY";
    static final String PROPERTY_SHIFTKEY = "shiftKey";
    static final String PROPERTY_TOELEMENT = "toElement";
    static final String PROPERTY_TYPE = "type";
    static final String PROPERTY_WHEELDELTA = "wheelDelta";
    
    static {
        IE.ProgId = "Shell.Explorer";
        IE.NativeClearSessions = new Runnable() {
            public void run() {
                if (OS.IsPPC) {
                    return;
                }
                OS.InternetSetOption(0, 42, 0, 0);
            }
        };
        IE.NativeGetCookie = new Runnable() {
            public void run() {
                if (OS.IsPPC) {
                    return;
                }
                final TCHAR tchar = new TCHAR(0, IE.CookieUrl, true);
                TCHAR tchar2 = new TCHAR(0, 8192);
                final int[] array = { tchar2.length() };
                if (!OS.InternetGetCookie(tchar, null, tchar2, array)) {
                    final int[] array2 = array;
                    final int n = 0;
                    array2[n] /= TCHAR.sizeof;
                    tchar2 = new TCHAR(0, array[0]);
                    if (!OS.InternetGetCookie(tchar, null, tchar2, array)) {
                        return;
                    }
                }
                final StringTokenizer stringTokenizer = new StringTokenizer(tchar2.toString(0, array[0]), ";");
                while (stringTokenizer.hasMoreTokens()) {
                    final String nextToken = stringTokenizer.nextToken();
                    final int index = nextToken.indexOf(61);
                    if (index != -1 && nextToken.substring(0, index).trim().equals(IE.CookieName)) {
                        IE.CookieValue = nextToken.substring(index + 1).trim();
                    }
                }
            }
        };
        IE.NativeSetCookie = new Runnable() {
            public void run() {
                if (OS.IsPPC) {
                    return;
                }
                IE.CookieResult = OS.InternetSetCookie(new TCHAR(0, IE.CookieUrl, true), null, new TCHAR(0, IE.CookieValue, true));
            }
        };
        final TCHAR tchar = new TCHAR(0, "Software\\Microsoft\\Internet Explorer", true);
        final int[] array = { 0 };
        if (OS.RegOpenKeyEx(-2147483646, tchar, 0, 131097, array) == 0) {
            final int[] array2 = { 0 };
            final TCHAR tchar2 = new TCHAR(0, "Version", true);
            if (OS.RegQueryValueEx(array[0], tchar2, 0, null, (TCHAR)null, array2) == 0) {
                final TCHAR tchar3 = new TCHAR(0, array2[0] / TCHAR.sizeof);
                if (OS.RegQueryValueEx(array[0], tchar2, 0, null, tchar3, array2) == 0) {
                    final String string = tchar3.toString(0, tchar3.strlen());
                    final int index = string.indexOf(".");
                    if (index != -1) {
                        final String substring = string.substring(0, index);
                        try {
                            IE.IEVersion = Integer.valueOf(substring);
                        }
                        catch (NumberFormatException ex) {}
                    }
                }
            }
            OS.RegCloseKey(array[0]);
        }
        final TCHAR tchar4 = new TCHAR(0, "Shell.Explorer\\CLSID", true);
        final int[] array3 = { 0 };
        if (OS.RegOpenKeyEx(Integer.MIN_VALUE, tchar4, 0, 131097, array3) == 0) {
            final int[] array4 = { 0 };
            if (OS.RegQueryValueEx(array3[0], null, 0, null, (TCHAR)null, array4) == 0) {
                final TCHAR tchar5 = new TCHAR(0, array4[0] / TCHAR.sizeof);
                if (OS.RegQueryValueEx(array3[0], null, 0, null, tchar5, array4) == 0 && tchar5.toString(0, tchar5.strlen()).equals("{EAB22AC3-30C1-11CF-A7EB-0000C05BAE0B}")) {
                    final TCHAR tchar6 = new TCHAR(0, "Shell.Explorer.2", true);
                    final int[] array5 = { 0 };
                    if (OS.RegOpenKeyEx(Integer.MIN_VALUE, tchar6, 0, 131097, array5) == 0) {
                        OS.RegCloseKey(array5[0]);
                        IE.ProgId = "Shell.Explorer.2";
                    }
                }
            }
            OS.RegCloseKey(array3[0]);
        }
        if (IE.NativePendingCookies != null) {
            WebBrowser.SetPendingCookies(IE.NativePendingCookies);
        }
        IE.NativePendingCookies = null;
    }
    
    IE() {
        this.documents = new OleAutomation[0];
        this.addressBar = true;
        this.menuBar = true;
        this.statusBar = true;
        this.toolBar = true;
    }
    
    public void create(final Composite composite, final int style) {
        this.style = style;
        this.frame = new OleFrame(this.browser, 0);
        try {
            this.site = new WebSite(this.frame, 0, IE.ProgId);
        }
        catch (SWTException ex) {
            this.browser.dispose();
            SWT.error(2);
        }
        this.site.doVerb(-5);
        this.auto = new OleAutomation(this.site);
        this.domListener = new OleListener() {
            public void handleEvent(final OleEvent oleEvent) {
                IE.this.handleDOMEvent(oleEvent);
            }
        };
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        if (IE.this.ignoreDispose) {
                            IE.this.ignoreDispose = false;
                            break;
                        }
                        IE.this.ignoreDispose = true;
                        IE.this.browser.notifyListeners(event.type, event);
                        event.type = 0;
                        if (!IE.this.browser.isClosing) {
                            final LocationListener[] locationListeners = IE.this.locationListeners;
                            IE.this.locationListeners = new LocationListener[0];
                            IE.this.site.ignoreAllMessages = true;
                            IE.this.execute("window.location.href='about:blank'");
                            IE.this.site.ignoreAllMessages = false;
                            IE.this.locationListeners = locationListeners;
                        }
                        if (!IE.this.frame.isDisposed()) {
                            IE.this.unhookDOMListeners(IE.this.documents);
                        }
                        for (int i = 0; i < IE.this.documents.length; ++i) {
                            IE.this.documents[i].dispose();
                        }
                        IE.this.documents = null;
                        final Enumeration<BrowserFunction> elements = (Enumeration<BrowserFunction>)IE.this.functions.elements();
                        while (elements.hasMoreElements()) {
                            elements.nextElement().dispose(false);
                        }
                        IE.this.functions = null;
                        final IE this$0 = IE.this;
                        final IE this$2 = IE.this;
                        final String s = null;
                        this$2.uncRedirect = s;
                        this$0.lastNavigateURL = s;
                        IE.this.domListener = null;
                        if (IE.this.auto != null) {
                            IE.this.auto.dispose();
                        }
                        IE.this.auto = null;
                        break;
                    }
                    case 11: {
                        IE.this.frame.setBounds(IE.this.browser.getClientArea());
                        break;
                    }
                    case 37: {
                        event.doit = false;
                        break;
                    }
                    case 15: {
                        IE.this.site.setFocus();
                        break;
                    }
                    case 31: {
                        if (event.detail == 8 && event.widget instanceof WebSite) {
                            IE.this.browser.traverse(8, event);
                            event.doit = false;
                        }
                        if (event.detail == 16 && event.widget instanceof Browser) {
                            IE.this.site.traverse(16, event);
                            event.doit = false;
                        }
                        if (event.detail == 4 && event.doit && event.widget instanceof Browser) {
                            event.type = 0;
                            event.doit = false;
                            break;
                        }
                        break;
                    }
                }
            }
        };
        this.browser.addListener(12, listener);
        this.browser.addListener(15, listener);
        this.browser.addListener(11, listener);
        this.browser.addListener(31, listener);
        this.site.addListener(37, listener);
        this.site.addListener(31, listener);
        final OleListener oleListener = new OleListener() {
            final /* synthetic */ IE this$0;
            
            public void handleEvent(final OleEvent oleEvent) {
                if (IE.this.auto != null) {
                    switch (oleEvent.type) {
                        case 250: {
                            IE.this.isRefresh = false;
                            if (IE.this.performingInitialNavigate) {
                                break;
                            }
                            String s = oleEvent.arguments[1].getString();
                            if (IE.this.uncRedirect != null) {
                                if (IE.this.uncRedirect.equals(s) || (IE.this.uncRedirect.startsWith(s) && IE.this.uncRedirect.indexOf(92, 2) == s.length())) {
                                    final Variant variant = oleEvent.arguments[6];
                                    if (variant != null) {
                                        OS.MoveMemory(variant.getByRef(), new short[1], 2);
                                        break;
                                    }
                                    break;
                                }
                                else {
                                    IE.this.uncRedirect = null;
                                }
                            }
                            if (s.indexOf(":/") == -1 && s.indexOf(":\\") != -1) {
                                s = "file://" + s.replace('\\', '/');
                            }
                            if (s.startsWith("file://") && IE.this._getUrl().startsWith("about:blank") && IE.this.untrustedText) {
                                final Variant variant2 = oleEvent.arguments[6];
                                if (variant2 != null) {
                                    OS.MoveMemory(variant2.getByRef(), new short[] { -1 }, 2);
                                    break;
                                }
                                break;
                            }
                            else {
                                final LocationEvent locationEvent = new LocationEvent(IE.this.browser);
                                locationEvent.display = IE.this.browser.getDisplay();
                                locationEvent.widget = IE.this.browser;
                                locationEvent.location = s;
                                locationEvent.doit = true;
                                for (int i = 0; i < IE.this.locationListeners.length; ++i) {
                                    IE.this.locationListeners[i].changing(locationEvent);
                                }
                                final boolean b = locationEvent.doit && !IE.this.browser.isDisposed();
                                final Variant variant3 = oleEvent.arguments[6];
                                if (variant3 != null) {
                                    OS.MoveMemory(variant3.getByRef(), new short[] { b ? 0 : -1 }, 2);
                                }
                                if (b) {
                                    IE.this.lastNavigateURL = s;
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        case 105: {
                            final int int1 = oleEvent.arguments[0].getInt();
                            final boolean boolean1 = oleEvent.arguments[1].getBoolean();
                            switch (int1) {
                                case 2: {
                                    IE.this.back = boolean1;
                                    break;
                                }
                                case 1: {
                                    IE.this.forward = boolean1;
                                    break;
                                }
                            }
                            break;
                        }
                        case 259: {
                            if (IE.this.performingInitialNavigate) {
                                IE.this.performingInitialNavigate = false;
                                if (IE.this.pendingText != null) {
                                    IE.this.setText((String)IE.this.pendingText[0], (boolean)IE.this.pendingText[1]);
                                }
                                else if (IE.this.pendingUrl != null) {
                                    IE.this.setUrl((String)IE.this.pendingUrl[0], (String)IE.this.pendingUrl[1], (String[])IE.this.pendingUrl[2]);
                                }
                                final IE this$0 = IE.this;
                                final IE this$2 = IE.this;
                                final Object[] array = null;
                                this$2.pendingUrl = array;
                                this$0.pendingText = array;
                                break;
                            }
                            final IDispatch dispatch = oleEvent.arguments[0].getDispatch();
                            String location = oleEvent.arguments[1].getString();
                            if (location.indexOf(":/") == -1 && location.indexOf(":\\") != -1) {
                                location = "file://" + location.replace('\\', '/');
                            }
                            if (IE.this.html != null && location.equals("about:blank")) {
                                if (IE.this.delaySetText) {
                                    IE.this.delaySetText = false;
                                    IE.this.browser.getDisplay().asyncExec(new Runnable() {
                                        final /* synthetic */ IE$6 this$1 = this$1;
                                        
                                        public void run() {
                                            if (this.this$1.this$0.browser.isDisposed() || this.this$1.this$0.html == null) {
                                                return;
                                            }
                                            this.this$1.this$0.setHTML(this.this$1.this$0.html);
                                            this.this$1.this$0.html = null;
                                        }
                                    });
                                    break;
                                }
                                IE.this.setHTML(IE.this.html);
                                IE.this.html = null;
                                break;
                            }
                            else {
                                final IDispatch dispatch2 = new Variant(IE.this.auto).getDispatch();
                                final LocationEvent locationEvent2 = new LocationEvent(IE.this.browser);
                                locationEvent2.display = IE.this.browser.getDisplay();
                                locationEvent2.widget = IE.this.browser;
                                locationEvent2.location = location;
                                locationEvent2.top = (dispatch2.getAddress() == dispatch.getAddress());
                                for (int j = 0; j < IE.this.locationListeners.length; ++j) {
                                    IE.this.locationListeners[j].changed(locationEvent2);
                                }
                                if (IE.this.browser.isDisposed()) {
                                    return;
                                }
                                if (IE.this.globalDispatch != 0 && dispatch.getAddress() == IE.this.globalDispatch) {
                                    IE.this.globalDispatch = 0;
                                    final IE ie = (IE)IE.this.browser.webBrowser;
                                    if (ie.installFunctionsOnDocumentComplete) {
                                        ie.installFunctionsOnDocumentComplete = false;
                                        final Enumeration<BrowserFunction> elements = (Enumeration<BrowserFunction>)IE.this.functions.elements();
                                        while (elements.hasMoreElements()) {
                                            IE.this.execute(elements.nextElement().functionString);
                                        }
                                    }
                                    final ProgressEvent progressEvent = new ProgressEvent(IE.this.browser);
                                    progressEvent.display = IE.this.browser.getDisplay();
                                    progressEvent.widget = IE.this.browser;
                                    for (int k = 0; k < IE.this.progressListeners.length; ++k) {
                                        IE.this.progressListeners[k].completed(progressEvent);
                                    }
                                    break;
                                }
                                break;
                            }
                            break;
                        }
                        case 104: {
                            if (!IE.this.isRefresh) {
                                break;
                            }
                            IE.this.isRefresh = false;
                            final Enumeration<BrowserFunction> elements2 = (Enumeration<BrowserFunction>)IE.this.functions.elements();
                            while (elements2.hasMoreElements()) {
                                IE.this.execute(elements2.nextElement().functionString);
                            }
                            final ProgressEvent progressEvent2 = new ProgressEvent(IE.this.browser);
                            progressEvent2.display = IE.this.browser.getDisplay();
                            progressEvent2.widget = IE.this.browser;
                            for (int l = 0; l < IE.this.progressListeners.length; ++l) {
                                IE.this.progressListeners[l].completed(progressEvent2);
                            }
                            break;
                        }
                        case 252: {
                            final String string = oleEvent.arguments[1].getString();
                            IE.this.isAboutBlank = string.startsWith("about:blank");
                            boolean b2 = false;
                            String path = null;
                            try {
                                path = new URL(string).getPath();
                            }
                            catch (MalformedURLException ex) {}
                            if (path != null) {
                                final int lastIndex = path.lastIndexOf(46);
                                if (lastIndex != -1 && path.substring(lastIndex).equalsIgnoreCase(".pdf")) {
                                    b2 = true;
                                    ++IE.PDFCount;
                                    if (IE.PDFCount > 20) {
                                        COM.FreeUnusedLibraries = false;
                                    }
                                }
                            }
                            if (IE.this.uncRedirect != null) {
                                if (IE.this.uncRedirect.equals(string)) {
                                    IE.this.uncRedirect = null;
                                    break;
                                }
                                if (IE.this.uncRedirect.startsWith(string)) {
                                    IE.this.navigate(IE.this.uncRedirect, null, null, true);
                                    break;
                                }
                                IE.this.uncRedirect = null;
                            }
                            final Variant variant4 = oleEvent.arguments[0];
                            final IDispatch dispatch3 = variant4.getDispatch();
                            if (IE.this.globalDispatch == 0) {
                                IE.this.globalDispatch = dispatch3.getAddress();
                            }
                            final OleAutomation automation = variant4.getAutomation();
                            final boolean b3 = new Variant(IE.this.auto).getDispatch().getAddress() == dispatch3.getAddress();
                            if (b3) {
                                IE.this.unhookDOMListeners(IE.this.documents);
                                for (int n = 0; n < IE.this.documents.length; ++n) {
                                    IE.this.documents[n].dispose();
                                }
                                IE.this.documents = new OleAutomation[0];
                                final Enumeration<BrowserFunction> elements3 = (Enumeration<BrowserFunction>)IE.this.functions.elements();
                                while (elements3.hasMoreElements()) {
                                    IE.this.execute(elements3.nextElement().functionString);
                                }
                            }
                            if (!b2) {
                                IE.this.hookDOMListeners(automation, b3);
                            }
                            automation.dispose();
                            break;
                        }
                        case 271: {
                            if (IE.this.uncRedirect != null) {
                                IE.this.uncRedirect = null;
                                break;
                            }
                            final String string2 = oleEvent.arguments[1].getString();
                            if (!string2.startsWith("\\\\") || oleEvent.arguments[3].getInt() != -2146697211) {
                                break;
                            }
                            final int index = string2.indexOf(92, 2);
                            if (index != -1) {
                                final String substring = string2.substring(0, index);
                                final Variant variant5 = oleEvent.arguments[4];
                                if (variant5 != null) {
                                    OS.MoveMemory(variant5.getByRef(), new short[] { -1 }, 2);
                                }
                                IE.this.browser.getDisplay().asyncExec(new Runnable() {
                                    final /* synthetic */ IE$6 this$1 = this$1;
                                    
                                    public void run() {
                                        if (this.this$1.this$0.browser.isDisposed()) {
                                            return;
                                        }
                                        if (string2.endsWith("\\")) {
                                            this.this$1.this$0.uncRedirect = string2.substring(0, string2.length() - 1);
                                        }
                                        else {
                                            this.this$1.this$0.uncRedirect = string2;
                                        }
                                        this.this$1.this$0.navigate(substring, null, null, true);
                                    }
                                });
                                break;
                            }
                            break;
                        }
                        case 251: {
                            final int byRef = oleEvent.arguments[1].getByRef();
                            final WindowEvent windowEvent = new WindowEvent(IE.this.browser);
                            windowEvent.display = IE.this.browser.getDisplay();
                            windowEvent.widget = IE.this.browser;
                            windowEvent.required = false;
                            for (int n2 = 0; n2 < IE.this.openWindowListeners.length; ++n2) {
                                IE.this.openWindowListeners[n2].open(windowEvent);
                            }
                            IE ie2 = null;
                            if (windowEvent.browser != null && windowEvent.browser.webBrowser instanceof IE) {
                                ie2 = (IE)windowEvent.browser.webBrowser;
                            }
                            final boolean b4 = ie2 != null && !ie2.browser.isDisposed();
                            if (b4) {
                                ie2.installFunctionsOnDocumentComplete = true;
                                final IDispatch dispatch4 = new Variant(ie2.auto).getDispatch();
                                final int byRef2 = oleEvent.arguments[0].getByRef();
                                if (byRef2 != 0) {
                                    OS.MoveMemory(byRef2, new int[] { dispatch4.getAddress() }, OS.PTR_SIZEOF);
                                }
                            }
                            if (windowEvent.required) {
                                OS.MoveMemory(byRef, new short[] { b4 ? 0 : -1 }, 2);
                                break;
                            }
                            break;
                        }
                        case 256: {
                            IE.this.menuBar = oleEvent.arguments[0].getBoolean();
                            break;
                        }
                        case 257: {
                            IE.this.statusBar = oleEvent.arguments[0].getBoolean();
                            break;
                        }
                        case 255: {
                            if (!(IE.this.toolBar = oleEvent.arguments[0].getBoolean())) {
                                IE.this.addressBar = false;
                                IE.this.menuBar = false;
                                break;
                            }
                            break;
                        }
                        case 254: {
                            final boolean boolean2 = oleEvent.arguments[0].getBoolean();
                            final WindowEvent windowEvent2 = new WindowEvent(IE.this.browser);
                            windowEvent2.display = IE.this.browser.getDisplay();
                            windowEvent2.widget = IE.this.browser;
                            if (boolean2) {
                                if (IE.this.addressBar) {
                                    final Variant property = IE.this.auto.getProperty(IE.this.auto.getIDsOfNames(new String[] { "AddressBar" })[0]);
                                    if (property != null) {
                                        if (property.getType() == 11) {
                                            IE.this.addressBar = property.getBoolean();
                                        }
                                        property.dispose();
                                    }
                                }
                                windowEvent2.addressBar = IE.this.addressBar;
                                windowEvent2.menuBar = IE.this.menuBar;
                                windowEvent2.statusBar = IE.this.statusBar;
                                windowEvent2.toolBar = IE.this.toolBar;
                                windowEvent2.location = IE.this.location;
                                windowEvent2.size = IE.this.size;
                                for (int n3 = 0; n3 < IE.this.visibilityWindowListeners.length; ++n3) {
                                    IE.this.visibilityWindowListeners[n3].show(windowEvent2);
                                }
                                IE.this.location = null;
                                IE.this.size = null;
                                break;
                            }
                            for (int n4 = 0; n4 < IE.this.visibilityWindowListeners.length; ++n4) {
                                IE.this.visibilityWindowListeners[n4].hide(windowEvent2);
                            }
                            break;
                        }
                        case 108: {
                            if (IE.this.performingInitialNavigate) {
                                break;
                            }
                            final Variant variant6 = oleEvent.arguments[0];
                            final int current = (variant6.getType() != 3) ? 0 : variant6.getInt();
                            final Variant variant7 = oleEvent.arguments[1];
                            final int total = (variant7.getType() != 3) ? 0 : variant7.getInt();
                            final ProgressEvent progressEvent3 = new ProgressEvent(IE.this.browser);
                            progressEvent3.display = IE.this.browser.getDisplay();
                            progressEvent3.widget = IE.this.browser;
                            progressEvent3.current = current;
                            progressEvent3.total = total;
                            if (current != -1) {
                                for (int n5 = 0; n5 < IE.this.progressListeners.length; ++n5) {
                                    IE.this.progressListeners[n5].changed(progressEvent3);
                                }
                                break;
                            }
                            break;
                        }
                        case 102: {
                            if (IE.this.performingInitialNavigate) {
                                break;
                            }
                            final Variant variant8 = oleEvent.arguments[0];
                            if (variant8.getType() == 8) {
                                final String string3 = variant8.getString();
                                final StatusTextEvent statusTextEvent = new StatusTextEvent(IE.this.browser);
                                statusTextEvent.display = IE.this.browser.getDisplay();
                                statusTextEvent.widget = IE.this.browser;
                                statusTextEvent.text = string3;
                                for (int n6 = 0; n6 < IE.this.statusTextListeners.length; ++n6) {
                                    IE.this.statusTextListeners[n6].changed(statusTextEvent);
                                }
                                break;
                            }
                            break;
                        }
                        case 113: {
                            if (IE.this.performingInitialNavigate) {
                                break;
                            }
                            final Variant variant9 = oleEvent.arguments[0];
                            if (variant9.getType() == 8) {
                                final String string4 = variant9.getString();
                                final TitleEvent titleEvent = new TitleEvent(IE.this.browser);
                                titleEvent.display = IE.this.browser.getDisplay();
                                titleEvent.widget = IE.this.browser;
                                titleEvent.title = string4;
                                for (int n7 = 0; n7 < IE.this.titleListeners.length; ++n7) {
                                    IE.this.titleListeners[n7].changed(titleEvent);
                                }
                                break;
                            }
                            break;
                        }
                        case 263: {
                            IE.this.browser.getDisplay().asyncExec(new Runnable() {
                                final /* synthetic */ IE$6 this$1 = this$1;
                                
                                public void run() {
                                    if (this.this$1.this$0.browser.isDisposed()) {
                                        return;
                                    }
                                    final WindowEvent windowEvent = new WindowEvent(this.this$1.this$0.browser);
                                    windowEvent.display = this.this$1.this$0.browser.getDisplay();
                                    windowEvent.widget = this.this$1.this$0.browser;
                                    for (int i = 0; i < this.this$1.this$0.closeWindowListeners.length; ++i) {
                                        this.this$1.this$0.closeWindowListeners[i].close(windowEvent);
                                    }
                                    this.this$1.this$0.browser.dispose();
                                }
                            });
                            OS.MoveMemory(oleEvent.arguments[1].getByRef(), new short[] { oleEvent.arguments[0].getBoolean() ? 0 : -1 }, 2);
                            break;
                        }
                        case 267: {
                            if (IE.this.size == null) {
                                IE.this.size = new Point(0, 0);
                            }
                            IE.this.size.y = oleEvent.arguments[0].getInt();
                            break;
                        }
                        case 264: {
                            if (IE.this.location == null) {
                                IE.this.location = new Point(0, 0);
                            }
                            IE.this.location.x = oleEvent.arguments[0].getInt();
                            break;
                        }
                        case 265: {
                            if (IE.this.location == null) {
                                IE.this.location = new Point(0, 0);
                            }
                            IE.this.location.y = oleEvent.arguments[0].getInt();
                            break;
                        }
                        case 266: {
                            if (IE.this.size == null) {
                                IE.this.size = new Point(0, 0);
                            }
                            IE.this.size.x = oleEvent.arguments[0].getInt();
                            break;
                        }
                    }
                }
                final Variant[] arguments = oleEvent.arguments;
                for (int n8 = 0; n8 < arguments.length; ++n8) {
                    arguments[n8].dispose();
                }
            }
        };
        this.site.addEventListener(250, oleListener);
        this.site.addEventListener(105, oleListener);
        this.site.addEventListener(259, oleListener);
        this.site.addEventListener(104, oleListener);
        this.site.addEventListener(252, oleListener);
        this.site.addEventListener(271, oleListener);
        this.site.addEventListener(251, oleListener);
        this.site.addEventListener(256, oleListener);
        this.site.addEventListener(257, oleListener);
        this.site.addEventListener(255, oleListener);
        this.site.addEventListener(254, oleListener);
        this.site.addEventListener(108, oleListener);
        this.site.addEventListener(102, oleListener);
        this.site.addEventListener(113, oleListener);
        this.site.addEventListener(263, oleListener);
        this.site.addEventListener(267, oleListener);
        this.site.addEventListener(264, oleListener);
        this.site.addEventListener(265, oleListener);
        this.site.addEventListener(266, oleListener);
        final Variant variant = new Variant(true);
        this.auto.setProperty(552, variant);
        variant.dispose();
        final Variant variant2 = new Variant(false);
        final int[] iDsOfNames = this.auto.getIDsOfNames(new String[] { "RegisterAsDropTarget" });
        if (iDsOfNames != null) {
            this.auto.setProperty(iDsOfNames[0], variant2);
        }
        variant2.dispose();
    }
    
    public boolean back() {
        if (!this.back) {
            return false;
        }
        final Variant invoke = this.auto.invoke(this.auto.getIDsOfNames(new String[] { "GoBack" })[0]);
        return invoke != null && invoke.getType() == 0;
    }
    
    public boolean close() {
        boolean b = true;
        final Variant property = this.auto.getProperty(this.auto.getIDsOfNames(new String[] { "Document" })[0]);
        if (property == null || property.getType() == 0) {
            if (property != null) {
                property.dispose();
            }
        }
        else {
            final OleAutomation automation = property.getAutomation();
            property.dispose();
            final int[] iDsOfNames = automation.getIDsOfNames(new String[] { "parentWindow" });
            if (iDsOfNames != null) {
                final Variant property2 = automation.getProperty(iDsOfNames[0]);
                if (property2 == null || property2.getType() == 0) {
                    if (property2 != null) {
                        property2.dispose();
                    }
                }
                else {
                    final OleAutomation automation2 = property2.getAutomation();
                    property2.dispose();
                    final Variant property3 = automation2.getProperty(automation2.getIDsOfNames(new String[] { "location" })[0]);
                    if (property3 == null || property3.getType() == 0) {
                        if (property3 != null) {
                            property3.dispose();
                        }
                    }
                    else {
                        final OleAutomation automation3 = property3.getAutomation();
                        property3.dispose();
                        final LocationListener[] locationListeners = this.locationListeners;
                        this.locationListeners = new LocationListener[0];
                        final int n = automation3.getIDsOfNames(new String[] { "replace" })[0];
                        final Variant[] array = { new Variant("about:blank") };
                        final Variant invoke = automation3.invoke(n, array);
                        if (invoke == null) {
                            b = false;
                        }
                        else {
                            invoke.dispose();
                        }
                        array[0].dispose();
                        this.locationListeners = locationListeners;
                        automation3.dispose();
                    }
                    automation2.dispose();
                }
            }
            automation.dispose();
        }
        return b;
    }
    
    static Variant createSafeArray(final String s) {
        final byte[] bytes = s.getBytes();
        final int length = bytes.length;
        final int globalAlloc = OS.GlobalAlloc(64, length);
        C.memmove(globalAlloc, bytes, length);
        final int cElements = length;
        final int globalAlloc2 = OS.GlobalAlloc(64, SAFEARRAY.sizeof);
        final SAFEARRAY safearray = new SAFEARRAY();
        safearray.cDims = 1;
        safearray.fFeatures = 16;
        safearray.cbElements = 1;
        safearray.pvData = globalAlloc;
        final SAFEARRAYBOUND rgsabound = new SAFEARRAYBOUND();
        safearray.rgsabound = rgsabound;
        rgsabound.cElements = cElements;
        OS.MoveMemory(globalAlloc2, safearray, SAFEARRAY.sizeof);
        final int globalAlloc3 = OS.GlobalAlloc(64, Variant.sizeof);
        OS.MoveMemory(globalAlloc3, new short[] { 8209 }, 2);
        OS.MoveMemory(globalAlloc3 + 8, new int[] { globalAlloc2 }, C.PTR_SIZEOF);
        return new Variant(globalAlloc3, (short)16396);
    }
    
    public boolean execute(final String s) {
        final Variant property = this.auto.getProperty(this.auto.getIDsOfNames(new String[] { "Document" })[0]);
        if (property == null || property.getType() == 0) {
            if (property != null) {
                property.dispose();
            }
            return false;
        }
        final OleAutomation automation = property.getAutomation();
        property.dispose();
        final int[] iDsOfNames = automation.getIDsOfNames(new String[] { "parentWindow" });
        if (iDsOfNames == null) {
            automation.dispose();
            return false;
        }
        final Variant property2 = automation.getProperty(iDsOfNames[0]);
        final OleAutomation automation2 = property2.getAutomation();
        property2.dispose();
        automation.dispose();
        final int[] iDsOfNames2 = automation2.getIDsOfNames(new String[] { "execScript", "code" });
        final Variant[] array = { new Variant(s) };
        final Variant invoke = automation2.invoke(iDsOfNames2[0], array, new int[] { iDsOfNames2[1] });
        array[0].dispose();
        automation2.dispose();
        if (invoke == null) {
            return false;
        }
        invoke.dispose();
        return true;
    }
    
    public boolean forward() {
        if (!this.forward) {
            return false;
        }
        final Variant invoke = this.auto.invoke(this.auto.getIDsOfNames(new String[] { "GoForward" })[0]);
        return invoke != null && invoke.getType() == 0;
    }
    
    public String getBrowserType() {
        return "ie";
    }
    
    String getDeleteFunctionString(final String s) {
        return "window." + s + "=undefined";
    }
    
    public String getText() {
        final Variant property = this.auto.getProperty(this.auto.getIDsOfNames(new String[] { "Document" })[0]);
        if (property == null || property.getType() == 0) {
            if (property != null) {
                property.dispose();
            }
            return "";
        }
        final OleAutomation automation = property.getAutomation();
        property.dispose();
        final int[] iDsOfNames = automation.getIDsOfNames(new String[] { "documentElement" });
        if (iDsOfNames == null) {
            automation.dispose();
            return "";
        }
        final Variant property2 = automation.getProperty(iDsOfNames[0]);
        automation.dispose();
        if (property2 == null || property2.getType() == 0) {
            if (property2 != null) {
                property2.dispose();
            }
            return "";
        }
        final OleAutomation automation2 = property2.getAutomation();
        property2.dispose();
        final Variant property3 = automation2.getProperty(automation2.getIDsOfNames(new String[] { "outerHTML" })[0]);
        automation2.dispose();
        if (property3 == null || property3.getType() == 0) {
            if (property3 != null) {
                property3.dispose();
            }
            return "";
        }
        final String string = property3.getString();
        property3.dispose();
        return string;
    }
    
    public String getUrl() {
        final String getUrl = this._getUrl();
        return (getUrl.length() != 0) ? getUrl : "about:blank";
    }
    
    String _getUrl() {
        final Variant property = this.auto.getProperty(this.auto.getIDsOfNames(new String[] { "LocationURL" })[0]);
        if (property == null || property.getType() != 8) {
            return "";
        }
        final String string = property.getString();
        property.dispose();
        return string;
    }
    
    public boolean isBackEnabled() {
        return this.back;
    }
    
    public boolean isForwardEnabled() {
        return this.forward;
    }
    
    public boolean isFocusControl() {
        return this.site.isFocusControl() || this.frame.isFocusControl();
    }
    
    boolean navigate(final String s, final String s2, final String[] array, final boolean b) {
        int n = 1;
        if (s2 != null) {
            ++n;
        }
        if (array != null) {
            ++n;
        }
        final Variant[] array2 = new Variant[n];
        final int[] array3 = new int[n];
        final int[] iDsOfNames = this.auto.getIDsOfNames(new String[] { "Navigate", "URL", "PostData", "Headers" });
        int n2 = 0;
        array2[n2] = new Variant(s);
        array3[n2++] = iDsOfNames[1];
        if (s2 != null) {
            array2[n2] = createSafeArray(s2);
            array3[n2++] = iDsOfNames[2];
        }
        if (array != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                final String s3 = array[i];
                if (s3 != null) {
                    final int index = s3.indexOf(58);
                    if (index != -1) {
                        final String trim = s3.substring(0, index).trim();
                        final String trim2 = s3.substring(index + 1).trim();
                        if (trim.length() > 0 && trim2.length() > 0) {
                            sb.append(trim);
                            sb.append(':');
                            sb.append(trim2);
                            sb.append("\r\n");
                        }
                    }
                }
            }
            array2[n2] = new Variant(sb.toString());
            array3[n2++] = iDsOfNames[3];
        }
        boolean b2 = false;
        if (b && !OS.IsWinCE && IE.IEVersion >= 7) {
            b2 = (OS.CoInternetIsFeatureEnabled(21, 2) == 0);
            OS.CoInternetSetFeatureEnabled(21, 2, true);
        }
        final Variant invoke = this.auto.invoke(iDsOfNames[0], array2, array3);
        if (b && !OS.IsWinCE && IE.IEVersion >= 7) {
            OS.CoInternetSetFeatureEnabled(21, 2, b2);
        }
        for (int j = 0; j < n; ++j) {
            array2[j].dispose();
        }
        if (invoke == null) {
            return false;
        }
        final boolean b3 = invoke.getType() == 0;
        invoke.dispose();
        return b3;
    }
    
    public void refresh() {
        this.uncRedirect = null;
        final String getUrl = this._getUrl();
        final int lastIndex = getUrl.lastIndexOf(46);
        if (lastIndex != -1 && getUrl.substring(lastIndex).equalsIgnoreCase(".pdf")) {
            ++IE.PDFCount;
            if (IE.PDFCount > 20) {
                COM.FreeUnusedLibraries = false;
            }
        }
        this.isRefresh = true;
        this.auto.invoke(this.auto.getIDsOfNames(new String[] { "Refresh" })[0]);
    }
    
    void setHTML(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        s.getChars(0, length, array, 0);
        final int wideCharToMultiByte = OS.WideCharToMultiByte(65001, 0, array, length, null, 0, null, null);
        final byte[] array2 = { -17, -69, -65 };
        final int globalAlloc = OS.GlobalAlloc(64, array2.length + wideCharToMultiByte);
        if (globalAlloc != 0) {
            OS.MoveMemory(globalAlloc, array2, array2.length);
            OS.WideCharToMultiByte(65001, 0, array, length, globalAlloc + array2.length, wideCharToMultiByte, null, null);
            final int[] array3 = { 0 };
            if (OS.CreateStreamOnHGlobal(globalAlloc, true, array3) == 0) {
                final Variant property = this.auto.getProperty(this.auto.getIDsOfNames(new String[] { "Document" })[0]);
                final IDispatch dispatch = property.getDispatch();
                final int[] array4 = { 0 };
                if (dispatch.QueryInterface(COM.IIDIPersistStreamInit, array4) == 0) {
                    final IPersistStreamInit persistStreamInit = new IPersistStreamInit(array4[0]);
                    if (persistStreamInit.InitNew() == 0) {
                        persistStreamInit.Load(array3[0]);
                    }
                    persistStreamInit.Release();
                }
                property.dispose();
                new IUnknown(array3[0]).Release();
            }
            else {
                OS.GlobalFree(globalAlloc);
            }
        }
    }
    
    public boolean setText(final String html, final boolean b) {
        if (this.performingInitialNavigate) {
            this.pendingText = new Object[] { html, new Boolean(b) };
            this.pendingUrl = null;
            return true;
        }
        final boolean b2 = this.html != null;
        this.html = html;
        this.untrustedText = !b;
        if (b2) {
            return true;
        }
        if (this._getUrl().length() != 0) {
            final Variant property = this.auto.getProperty(this.auto.getIDsOfNames(new String[] { "ReadyState" })[0]);
            if (property == null) {
                return false;
            }
            this.delaySetText = (property.getInt() != 4);
            property.dispose();
            this.auto.invoke(this.auto.getIDsOfNames(new String[] { "Stop" })[0]);
        }
        final int[] iDsOfNames = this.auto.getIDsOfNames(new String[] { "Navigate", "URL" });
        final Variant[] array = { new Variant("about:blank") };
        final int[] array2 = { iDsOfNames[1] };
        boolean b3 = false;
        if (!OS.IsWinCE && IE.IEVersion >= 7) {
            b3 = (OS.CoInternetIsFeatureEnabled(21, 2) == 0);
            OS.CoInternetSetFeatureEnabled(21, 2, true);
        }
        final Variant invoke = this.auto.invoke(iDsOfNames[0], array, array2);
        if (!OS.IsWinCE && IE.IEVersion >= 7) {
            OS.CoInternetSetFeatureEnabled(21, 2, b3);
        }
        array[0].dispose();
        if (invoke == null) {
            return false;
        }
        final boolean b4 = invoke.getType() == 0;
        invoke.dispose();
        return b4;
    }
    
    public boolean setUrl(final String s, final String s2, final String[] array) {
        final String s3 = null;
        this.uncRedirect = s3;
        this.html = s3;
        if (this._getUrl().length() == 0 && !"about:blank".equalsIgnoreCase(s)) {
            this.pendingText = null;
            this.pendingUrl = new Object[] { s, s2, array };
            this.navigate("about:blank", null, null, this.performingInitialNavigate = true);
            return true;
        }
        if (s.endsWith(".xml")) {
            this.auto.invoke(this.auto.getIDsOfNames(new String[] { "Stop" })[0]);
        }
        return this.navigate(s, s2, array, false);
    }
    
    public void stop() {
        if (this.performingInitialNavigate) {
            final Object[] array = null;
            this.pendingUrl = array;
            this.pendingText = array;
            return;
        }
        if (this._getUrl().length() == 0) {
            return;
        }
        this.uncRedirect = null;
        this.auto.invoke(this.auto.getIDsOfNames(new String[] { "Stop" })[0]);
    }
    
    boolean translateMnemonics() {
        return false;
    }
    
    void handleDOMEvent(final OleEvent oleEvent) {
        if (oleEvent.arguments == null || oleEvent.arguments.length == 0) {
            return;
        }
        final OleAutomation automation = oleEvent.arguments[0].getAutomation();
        final Variant property = automation.getProperty(automation.getIDsOfNames(new String[] { "type" })[0]);
        final String string = property.getString();
        property.dispose();
        if (string.equals("keydown")) {
            final Variant property2 = automation.getProperty(automation.getIDsOfNames(new String[] { "keyCode" })[0]);
            this.lastKeyCode = this.translateKey(property2.getInt());
            property2.dispose();
            final Variant property3 = automation.getProperty(automation.getIDsOfNames(new String[] { "returnValue" })[0]);
            final boolean b = property3 != null && property3.getType() == 11 && !property3.getBoolean();
            property3.dispose();
            if (OS.PeekMessage(new MSG(), this.frame.handle, 258, 258, 0x2 | (b ? 1 : 0))) {
                automation.dispose();
                return;
            }
            if (b) {
                automation.dispose();
                return;
            }
            final Variant property4 = automation.getProperty(automation.getIDsOfNames(new String[] { "repeat" })[0]);
            final boolean boolean1 = property4.getBoolean();
            property4.dispose();
            if (boolean1) {
                automation.dispose();
                return;
            }
            int stateMask = 0;
            final Variant property5 = automation.getProperty(automation.getIDsOfNames(new String[] { "altKey" })[0]);
            if (property5.getBoolean()) {
                stateMask |= 0x10000;
            }
            property5.dispose();
            final Variant property6 = automation.getProperty(automation.getIDsOfNames(new String[] { "ctrlKey" })[0]);
            if (property6.getBoolean()) {
                stateMask |= 0x40000;
            }
            property6.dispose();
            final Variant property7 = automation.getProperty(automation.getIDsOfNames(new String[] { "shiftKey" })[0]);
            if (property7.getBoolean()) {
                stateMask |= 0x20000;
            }
            property7.dispose();
            final Event event = new Event();
            event.widget = this.browser;
            event.type = 1;
            event.keyCode = this.lastKeyCode;
            event.stateMask = stateMask;
            final Event event2 = event;
            event2.stateMask &= ~this.lastKeyCode;
            switch (this.lastKeyCode) {
                case 8: {
                    final Event event3 = event;
                    final char c = '\b';
                    event3.character = c;
                    this.lastCharCode = c;
                    break;
                }
                case 13: {
                    final Event event4 = event;
                    final char c2 = '\r';
                    event4.character = c2;
                    this.lastCharCode = c2;
                    break;
                }
                case 127: {
                    final Event event5 = event;
                    final char c3 = '\u007f';
                    event5.character = c3;
                    this.lastCharCode = c3;
                    break;
                }
                case 9: {
                    final Event event6 = event;
                    final char c4 = '\t';
                    event6.character = c4;
                    this.lastCharCode = c4;
                    break;
                }
            }
            if (!this.sendKeyEvent(event)) {
                final int n = automation.getIDsOfNames(new String[] { "returnValue" })[0];
                final Variant variant = new Variant(false);
                automation.setProperty(n, variant);
                variant.dispose();
            }
            if (this.lastKeyCode == 16777230) {
                this.isRefresh = true;
            }
            automation.dispose();
        }
        else if (string.equals("keypress")) {
            int stateMask2 = 0;
            final Variant property8 = automation.getProperty(automation.getIDsOfNames(new String[] { "ctrlKey" })[0]);
            if (property8.getBoolean()) {
                stateMask2 |= 0x40000;
            }
            property8.dispose();
            final Variant property9 = automation.getProperty(automation.getIDsOfNames(new String[] { "shiftKey" })[0]);
            if (property9.getBoolean()) {
                stateMask2 |= 0x20000;
            }
            property9.dispose();
            final Variant property10 = automation.getProperty(automation.getIDsOfNames(new String[] { "altKey" })[0]);
            if (property10.getBoolean()) {
                stateMask2 |= 0x10000;
            }
            property10.dispose();
            final Variant property11 = automation.getProperty(automation.getIDsOfNames(new String[] { "keyCode" })[0]);
            this.lastCharCode = property11.getInt();
            property11.dispose();
            if (this.lastCharCode == 13 || this.lastCharCode == 10) {
                automation.dispose();
                return;
            }
            final Event event7 = new Event();
            event7.widget = this.browser;
            event7.type = 1;
            event7.keyCode = this.lastKeyCode;
            event7.character = (char)this.lastCharCode;
            event7.stateMask = stateMask2;
            if (!this.sendKeyEvent(event7)) {
                final int n2 = automation.getIDsOfNames(new String[] { "returnValue" })[0];
                final Variant variant2 = new Variant(false);
                automation.setProperty(n2, variant2);
                variant2.dispose();
            }
            automation.dispose();
        }
        else {
            if (!string.equals("keyup")) {
                if (string.equals("mouseover")) {
                    final Variant property12 = automation.getProperty(automation.getIDsOfNames(new String[] { "fromElement" })[0]);
                    final boolean b2 = property12.getType() != 0;
                    property12.dispose();
                    if (b2) {
                        automation.dispose();
                        return;
                    }
                }
                if (string.equals("mouseout")) {
                    final Variant property13 = automation.getProperty(automation.getIDsOfNames(new String[] { "toElement" })[0]);
                    final boolean b3 = property13.getType() != 0;
                    property13.dispose();
                    if (b3) {
                        automation.dispose();
                        return;
                    }
                }
                int n3 = 0;
                final Event event8 = new Event();
                event8.widget = this.browser;
                final Variant property14 = automation.getProperty(automation.getIDsOfNames(new String[] { "screenX" })[0]);
                final int int1 = property14.getInt();
                property14.dispose();
                final Variant property15 = automation.getProperty(automation.getIDsOfNames(new String[] { "screenY" })[0]);
                final int int2 = property15.getInt();
                property15.dispose();
                final Point map = this.browser.getDisplay().map(null, this.browser, new Point(int1, int2));
                event8.x = map.x;
                event8.y = map.y;
                final Variant property16 = automation.getProperty(automation.getIDsOfNames(new String[] { "ctrlKey" })[0]);
                if (property16.getBoolean()) {
                    n3 |= 0x40000;
                }
                property16.dispose();
                final Variant property17 = automation.getProperty(automation.getIDsOfNames(new String[] { "altKey" })[0]);
                if (property17.getBoolean()) {
                    n3 |= 0x10000;
                }
                property17.dispose();
                final Variant property18 = automation.getProperty(automation.getIDsOfNames(new String[] { "shiftKey" })[0]);
                if (property18.getBoolean()) {
                    n3 |= 0x20000;
                }
                property18.dispose();
                event8.stateMask = n3;
                final Variant property19 = automation.getProperty(automation.getIDsOfNames(new String[] { "button" })[0]);
                int int3 = property19.getInt();
                property19.dispose();
                switch (int3) {
                    case 1: {
                        int3 = 1;
                        break;
                    }
                    case 2: {
                        int3 = 3;
                        break;
                    }
                    case 4: {
                        int3 = 2;
                        break;
                    }
                }
                if (string.equals("mousedown")) {
                    event8.type = 3;
                    event8.button = int3;
                    event8.count = 1;
                }
                else if (string.equals("mouseup") || string.equals("dragend")) {
                    event8.type = 4;
                    event8.button = ((int3 != 0) ? int3 : true);
                    event8.count = 1;
                    switch (event8.button) {
                        case 1: {
                            final Event event9 = event8;
                            event9.stateMask |= 0x80000;
                            break;
                        }
                        case 2: {
                            final Event event10 = event8;
                            event10.stateMask |= 0x100000;
                            break;
                        }
                        case 3: {
                            final Event event11 = event8;
                            event11.stateMask |= 0x200000;
                            break;
                        }
                        case 4: {
                            final Event event12 = event8;
                            event12.stateMask |= 0x800000;
                            break;
                        }
                        case 5: {
                            final Event event13 = event8;
                            event13.stateMask |= 0x2000000;
                            break;
                        }
                    }
                }
                else if (string.equals("mousewheel")) {
                    event8.type = 37;
                    final Variant property20 = automation.getProperty(automation.getIDsOfNames(new String[] { "wheelDelta" })[0]);
                    event8.count = property20.getInt() / 120 * 3;
                    property20.dispose();
                }
                else if (string.equals("mousemove")) {
                    if (event8.x == this.lastMouseMoveX && event8.y == this.lastMouseMoveY) {
                        return;
                    }
                    event8.type = 5;
                    this.lastMouseMoveX = event8.x;
                    this.lastMouseMoveY = event8.y;
                }
                else if (string.equals("mouseover")) {
                    event8.type = 6;
                }
                else if (string.equals("mouseout")) {
                    event8.type = 7;
                }
                else if (string.equals("dragstart")) {
                    event8.type = 29;
                    event8.button = 1;
                    final Event event14 = event8;
                    event14.stateMask |= 0x80000;
                }
                automation.dispose();
                this.browser.notifyListeners(event8.type, event8);
                if (string.equals("dblclick")) {
                    final Event event15 = new Event();
                    event15.widget = this.browser;
                    event15.type = 8;
                    event15.x = map.x;
                    event15.y = map.y;
                    event15.stateMask = n3;
                    event15.type = 8;
                    event15.button = 1;
                    event15.count = 2;
                    this.browser.notifyListeners(event15.type, event15);
                }
                return;
            }
            final Variant property21 = automation.getProperty(automation.getIDsOfNames(new String[] { "keyCode" })[0]);
            final int translateKey = this.translateKey(property21.getInt());
            property21.dispose();
            if (translateKey == 0) {
                final boolean b4 = false;
                this.lastCharCode = (b4 ? 1 : 0);
                this.lastKeyCode = (b4 ? 1 : 0);
                automation.dispose();
                return;
            }
            if (translateKey != this.lastKeyCode) {
                this.lastKeyCode = translateKey;
                this.lastCharCode = 0;
            }
            int stateMask3 = 0;
            final Variant property22 = automation.getProperty(automation.getIDsOfNames(new String[] { "ctrlKey" })[0]);
            if (property22.getBoolean()) {
                stateMask3 |= 0x40000;
            }
            property22.dispose();
            final Variant property23 = automation.getProperty(automation.getIDsOfNames(new String[] { "altKey" })[0]);
            if (property23.getBoolean()) {
                stateMask3 |= 0x10000;
            }
            property23.dispose();
            final Variant property24 = automation.getProperty(automation.getIDsOfNames(new String[] { "shiftKey" })[0]);
            if (property24.getBoolean()) {
                stateMask3 |= 0x20000;
            }
            property24.dispose();
            final Event event16 = new Event();
            event16.widget = this.browser;
            event16.type = 2;
            event16.keyCode = this.lastKeyCode;
            event16.character = (char)this.lastCharCode;
            event16.stateMask = stateMask3;
            switch (this.lastKeyCode) {
                case 65536:
                case 131072:
                case 262144:
                case 4194304: {
                    final Event event17 = event16;
                    event17.stateMask |= this.lastKeyCode;
                    break;
                }
            }
            this.browser.notifyListeners(event16.type, event16);
            if (!event16.doit) {
                final int n4 = automation.getIDsOfNames(new String[] { "returnValue" })[0];
                final Variant variant3 = new Variant(false);
                automation.setProperty(n4, variant3);
                variant3.dispose();
            }
            final boolean b5 = false;
            this.lastCharCode = (b5 ? 1 : 0);
            this.lastKeyCode = (b5 ? 1 : 0);
            automation.dispose();
        }
    }
    
    void hookDOMListeners(final OleAutomation oleAutomation, final boolean b) {
        final Variant property = oleAutomation.getProperty(oleAutomation.getIDsOfNames(new String[] { "Document" })[0]);
        if (property == null) {
            return;
        }
        if (property.getType() == 0) {
            property.dispose();
            return;
        }
        final OleAutomation automation = property.getAutomation();
        property.dispose();
        this.unhookDOMListeners(new OleAutomation[] { automation });
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -602, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -603, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -604, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -605, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -607, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", 1033, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -601, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -606, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418101, this.domListener);
        this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418091, this.domListener);
        if (b) {
            this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418104, this.domListener);
            this.site.addEventListener(automation, "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}", -2147418103, this.domListener);
        }
        final OleAutomation[] documents = new OleAutomation[this.documents.length + 1];
        System.arraycopy(this.documents, 0, documents, 0, this.documents.length);
        documents[this.documents.length] = automation;
        this.documents = documents;
    }
    
    void unhookDOMListeners(final OleAutomation[] array) {
        final char[] charArray = "{3050F613-98B5-11CF-BB82-00AA00BDCE0B}\u0000".toCharArray();
        final GUID guid = new GUID();
        if (COM.IIDFromString(charArray, guid) == 0) {
            for (int i = 0; i < array.length; ++i) {
                final OleAutomation oleAutomation = array[i];
                this.site.removeEventListener(oleAutomation, guid, -602, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -603, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -604, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -605, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -607, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, 1033, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -601, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -606, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -2147418101, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -2147418091, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -2147418104, this.domListener);
                this.site.removeEventListener(oleAutomation, guid, -2147418103, this.domListener);
            }
        }
    }
}
