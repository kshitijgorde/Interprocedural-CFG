// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.internal.win32.SYSTEMTIME;
import org.eclipse.swt.internal.win32.TCHAR;
import org.eclipse.swt.internal.win32.CERT_INFO;
import org.eclipse.swt.internal.win32.CERT_CONTEXT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridData;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.webkit.IWebErrorPrivate;
import org.eclipse.swt.internal.webkit.IWebError;
import org.eclipse.swt.internal.webkit.IWebMutableURLRequest;
import java.util.Enumeration;
import java.io.UnsupportedEncodingException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.webkit.IWebView;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.webkit.IWebURLRequest;
import org.eclipse.swt.internal.webkit.IWebDataSource;
import org.eclipse.swt.internal.webkit.IWebFrame;
import org.eclipse.swt.internal.webkit.WebKit_win32;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.COMObject;

class WebFrameLoadDelegate
{
    COMObject iWebFrameLoadDelegate;
    int refCount;
    Browser browser;
    String html;
    String url;
    static final String OBJECTNAME_EXTERNAL = "external";
    
    WebFrameLoadDelegate(final Browser browser) {
        this.refCount = 0;
        this.createCOMInterfaces();
        this.browser = browser;
    }
    
    void addEventHandlers(final boolean b) {
        if (b) {
            final StringBuffer sb = new StringBuffer("window.SWTkeyhandler = function SWTkeyhandler(e) {");
            sb.append("try {e.returnValue = HandleWebKitEvent(e.type, e.keyCode, e.charCode, e.altKey, e.ctrlKey, e.shiftKey, e.metaKey);} catch (e) {}};");
            sb.append("document.addEventListener('keydown', SWTkeyhandler, true);");
            sb.append("document.addEventListener('keypress', SWTkeyhandler, true);");
            sb.append("document.addEventListener('keyup', SWTkeyhandler, true);");
            this.browser.execute(sb.toString());
            final StringBuffer sb2 = new StringBuffer("window.SWTmousehandler = function SWTmousehandler(e) {");
            sb2.append("try {e.returnValue = HandleWebKitEvent(e.type, e.screenX, e.screenY, e.detail, e.button + 1, e.altKey, e.ctrlKey, e.shiftKey, e.metaKey, e.relatedTarget != null);} catch (e) {}};");
            sb2.append("document.addEventListener('mousedown', SWTmousehandler, true);");
            sb2.append("document.addEventListener('mouseup', SWTmousehandler, true);");
            sb2.append("document.addEventListener('mousemove', SWTmousehandler, true);");
            sb2.append("document.addEventListener('mousewheel', SWTmousehandler, true);");
            sb2.append("document.addEventListener('dragstart', SWTmousehandler, true);");
            sb2.append("document.addEventListener('mouseover', SWTmousehandler, true);");
            sb2.append("document.addEventListener('mouseout', SWTmousehandler, true);");
            this.browser.execute(sb2.toString());
        }
        else {
            final StringBuffer sb3 = new StringBuffer("for (var i = 0; i < frames.length; i++) {");
            sb3.append("frames[i].document.addEventListener('keydown', window.SWTkeyhandler, true);");
            sb3.append("frames[i].document.addEventListener('keypress', window.SWTkeyhandler, true);");
            sb3.append("frames[i].document.addEventListener('keyup', window.SWTkeyhandler, true);");
            sb3.append("frames[i].document.addEventListener('mousedown', window.SWTmousehandler, true);");
            sb3.append("frames[i].document.addEventListener('mouseup', window.SWTmousehandler, true);");
            sb3.append("frames[i].document.addEventListener('mousemove', window.SWTmousehandler, true);");
            sb3.append("frames[i].document.addEventListener('mouseover', window.SWTmousehandler, true);");
            sb3.append("frames[i].document.addEventListener('mouseout', window.SWTmousehandler, true);");
            sb3.append("frames[i].document.addEventListener('mousewheel', window.SWTmousehandler, true);");
            sb3.append("frames[i].document.addEventListener('dragstart', window.SWTmousehandler, true);");
            sb3.append('}');
            this.browser.execute(sb3.toString());
        }
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.iWebFrameLoadDelegate = new COMObject(new int[] { 2, 0, 0, 2, 2, 3, 2, 3, 3, 2, 3, 2, 5, 2, 2, 3, 4 }) {
            public int method0(final int[] array) {
                return WebFrameLoadDelegate.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebFrameLoadDelegate.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebFrameLoadDelegate.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebFrameLoadDelegate.this.didStartProvisionalLoadForFrame(array[0], array[1]);
            }
            
            public int method4(final int[] array) {
                return -2147467263;
            }
            
            public int method5(final int[] array) {
                return WebFrameLoadDelegate.this.didFailProvisionalLoadWithError(array[0], array[1], array[2]);
            }
            
            public int method6(final int[] array) {
                return WebFrameLoadDelegate.this.didCommitLoadForFrame(array[0], array[1]);
            }
            
            public int method7(final int[] array) {
                return WebFrameLoadDelegate.this.didReceiveTitle(array[0], array[1], array[2]);
            }
            
            public int method8(final int[] array) {
                return -2147467263;
            }
            
            public int method9(final int[] array) {
                return WebFrameLoadDelegate.this.didFinishLoadForFrame(array[0], array[1]);
            }
            
            public int method10(final int[] array) {
                return -2147467263;
            }
            
            public int method11(final int[] array) {
                return WebFrameLoadDelegate.this.didChangeLocationWithinPageForFrame(array[0], array[1]);
            }
            
            public int method12(final int[] array) {
                return 0;
            }
            
            public int method13(final int[] array) {
                return -2147467263;
            }
            
            public int method14(final int[] array) {
                return 0;
            }
            
            public int method15(final int[] array) {
                return 0;
            }
            
            public int method16(final int[] array) {
                return WebFrameLoadDelegate.this.didClearWindowObject(array[0], array[1], array[2], array[3]);
            }
        };
        final int ppVtable = this.iWebFrameLoadDelegate.ppVtable;
        final int[] array = { 0 };
        OS.MoveMemory(array, ppVtable, OS.PTR_SIZEOF);
        final int[] array2 = new int[17];
        OS.MoveMemory(array2, array[0], OS.PTR_SIZEOF * array2.length);
        array2[12] = WebKit_win32.willPerformClientRedirectToURL_CALLBACK(array2[12]);
        OS.MoveMemory(array[0], array2, OS.PTR_SIZEOF * array2.length);
    }
    
    int didChangeLocationWithinPageForFrame(final int n, final int n2) {
        final IWebFrame webFrame = new IWebFrame(n2);
        final int[] array = { 0 };
        if (webFrame.dataSource(array) != 0 || array[0] == 0) {
            return 0;
        }
        final IWebDataSource webDataSource = new IWebDataSource(array[0]);
        array[0] = 0;
        final int request = webDataSource.request(array);
        webDataSource.Release();
        if (request != 0 || array[0] == 0) {
            return 0;
        }
        final IWebURLRequest webURLRequest = new IWebURLRequest(array[0]);
        array[0] = 0;
        final int url = webURLRequest.URL(array);
        webURLRequest.Release();
        if (url != 0 || array[0] == 0) {
            return 0;
        }
        String s = WebKit.extractBSTR(array[0]);
        COM.SysFreeString(array[0]);
        if (s.length() == 0) {
            return 0;
        }
        if (s.equals("file:///")) {
            s = "about:blank";
        }
        else {
            final int length = "file:///".length();
            if (s.startsWith("file:///") && s.charAt(length) == '#') {
                s = "about:blank" + s.substring(length);
            }
        }
        final Display display = this.browser.getDisplay();
        array[0] = 0;
        final int mainFrame = new IWebView(n).mainFrame(array);
        boolean top = false;
        if (mainFrame == 0 && array[0] != 0) {
            top = (n2 == array[0]);
            new IWebFrame(array[0]).Release();
        }
        if (top) {
            final StatusTextEvent statusTextEvent = new StatusTextEvent(this.browser);
            statusTextEvent.display = display;
            statusTextEvent.widget = this.browser;
            statusTextEvent.text = s;
            final StatusTextListener[] statusTextListeners = this.browser.webBrowser.statusTextListeners;
            for (int i = 0; i < statusTextListeners.length; ++i) {
                statusTextListeners[i].changed(statusTextEvent);
            }
        }
        final LocationEvent locationEvent = new LocationEvent(this.browser);
        locationEvent.display = display;
        locationEvent.widget = this.browser;
        locationEvent.location = s;
        locationEvent.top = top;
        final LocationListener[] locationListeners = this.browser.webBrowser.locationListeners;
        for (int j = 0; j < locationListeners.length; ++j) {
            locationListeners[j].changed(locationEvent);
        }
        return 0;
    }
    
    int didClearWindowObject(final int n, final int n2, final int n3, final int n4) {
        WebKit_win32.JSGlobalContextRetain(n2);
        final int jsContextGetGlobalObject = WebKit_win32.JSContextGetGlobalObject(n2);
        final int jsObjectMake = WebKit_win32.JSObjectMake(n2, WebKit.ExternalClass, ((WebKit)this.browser.webBrowser).webViewData);
        final byte[] array = null;
        byte[] array2;
        try {
            array2 = "external\u0000".getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException ex) {
            array2 = "external\u0000".getBytes();
        }
        final int jsStringCreateWithUTF8CString = WebKit_win32.JSStringCreateWithUTF8CString(array2);
        WebKit_win32.JSObjectSetProperty(n2, jsContextGetGlobalObject, jsStringCreateWithUTF8CString, jsObjectMake, 0, null);
        WebKit_win32.JSStringRelease(jsStringCreateWithUTF8CString);
        final Enumeration<BrowserFunction> elements = (Enumeration<BrowserFunction>)this.browser.webBrowser.functions.elements();
        while (elements.hasMoreElements()) {
            this.browser.execute(elements.nextElement().functionString);
        }
        final IWebView webView = new IWebView(n);
        final int[] array3 = { 0 };
        webView.mainFrame(array3);
        final boolean b = array3[0] == n4;
        new IWebFrame(array3[0]).Release();
        this.addEventHandlers(b);
        return 0;
    }
    
    int didCommitLoadForFrame(final int n, final int n2) {
        final IWebFrame webFrame = new IWebFrame(n2);
        final int[] array = { 0 };
        if (webFrame.dataSource(array) != 0 || array[0] == 0) {
            return 0;
        }
        final IWebDataSource webDataSource = new IWebDataSource(array[0]);
        array[0] = 0;
        final int request = webDataSource.request(array);
        webDataSource.Release();
        if (request != 0 || array[0] == 0) {
            return 0;
        }
        final IWebMutableURLRequest webMutableURLRequest = new IWebMutableURLRequest(array[0]);
        array[0] = 0;
        final int url = webMutableURLRequest.URL(array);
        webMutableURLRequest.Release();
        if (url != 0 || array[0] == 0) {
            return 0;
        }
        String location = WebKit.extractBSTR(array[0]);
        COM.SysFreeString(array[0]);
        if (location.length() == 0) {
            return 0;
        }
        if (location.equals("file:///")) {
            location = "about:blank";
        }
        else {
            final int length = "file:///".length();
            if (location.startsWith("file:///") && location.charAt(length) == '#') {
                location = "about:blank" + location.substring(length);
            }
        }
        final Display display = this.browser.getDisplay();
        array[0] = 0;
        final int mainFrame = new IWebView(n).mainFrame(array);
        boolean top = false;
        if (mainFrame == 0 && array[0] != 0) {
            top = (n2 == array[0]);
            new IWebFrame(array[0]).Release();
        }
        if (top) {
            this.url = location;
            if (location.startsWith("about:blank") && this.html != null) {
                return 0;
            }
            final Enumeration<BrowserFunction> elements = (Enumeration<BrowserFunction>)this.browser.webBrowser.functions.elements();
            while (elements.hasMoreElements()) {
                this.browser.webBrowser.execute(elements.nextElement().functionString);
            }
            final ProgressEvent progressEvent = new ProgressEvent(this.browser);
            progressEvent.display = display;
            progressEvent.widget = this.browser;
            progressEvent.current = 1;
            progressEvent.total = 100;
            final ProgressListener[] progressListeners = this.browser.webBrowser.progressListeners;
            for (int i = 0; i < progressListeners.length; ++i) {
                progressListeners[i].changed(progressEvent);
            }
            if (this.browser.isDisposed()) {
                return 0;
            }
            final StatusTextEvent statusTextEvent = new StatusTextEvent(this.browser);
            statusTextEvent.display = display;
            statusTextEvent.widget = this.browser;
            statusTextEvent.text = location;
            final StatusTextListener[] statusTextListeners = this.browser.webBrowser.statusTextListeners;
            for (int j = 0; j < statusTextListeners.length; ++j) {
                statusTextListeners[j].changed(statusTextEvent);
            }
            if (this.browser.isDisposed()) {
                return 0;
            }
        }
        final LocationEvent locationEvent = new LocationEvent(this.browser);
        locationEvent.display = display;
        locationEvent.widget = this.browser;
        locationEvent.location = location;
        locationEvent.top = top;
        final LocationListener[] locationListeners = this.browser.webBrowser.locationListeners;
        for (int k = 0; k < locationListeners.length; ++k) {
            locationListeners[k].changed(locationEvent);
        }
        return 0;
    }
    
    int didFailProvisionalLoadWithError(final int n, final int n2, final int n3) {
        final IWebError webError = new IWebError(n2);
        final int[] array = { 0 };
        webError.code(array);
        if (-1000 < array[0]) {
            return 0;
        }
        String bstr = null;
        final int[] array2 = { 0 };
        if (webError.failingURL(array2) == 0 && array2[0] != 0) {
            bstr = WebKit.extractBSTR(array2[0]);
            COM.SysFreeString(array2[0]);
        }
        if (bstr != null && -1204 <= array[0] && array[0] <= -1200) {
            final int[] array3 = { 0 };
            if (webError.localizedDescription(array3) != 0 || array3[0] == 0) {
                return 0;
            }
            final String bstr2 = WebKit.extractBSTR(array3[0]);
            COM.SysFreeString(array3[0]);
            array3[0] = 0;
            if (webError.QueryInterface(WebKit_win32.IID_IWebErrorPrivate, array3) != 0 || array3[0] == 0) {
                return 0;
            }
            final IWebErrorPrivate webErrorPrivate = new IWebErrorPrivate(array3[0]);
            array3[0] = 0;
            final int[] array4 = { 0 };
            final int sslPeerCertificate = webErrorPrivate.sslPeerCertificate(array4);
            webErrorPrivate.Release();
            if (sslPeerCertificate != 0 || array4[0] == 0) {
                return 0;
            }
            if (this.showCertificateDialog(n, bstr, bstr2, array4[0])) {
                final IWebFrame webFrame = new IWebFrame(n3);
                if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebMutableURLRequest, 0, WebKit_win32.IID_IWebMutableURLRequest, array3) != 0 || array3[0] == 0) {
                    return array4[0] = 0;
                }
                final IWebMutableURLRequest webMutableURLRequest = new IWebMutableURLRequest(array3[0]);
                webMutableURLRequest.setURL(array2[0]);
                webMutableURLRequest.setAllowsAnyHTTPSCertificate();
                webFrame.loadRequest(webMutableURLRequest.getAddress());
                webMutableURLRequest.Release();
            }
            return array4[0] = 0;
        }
        else {
            final int[] array5 = { 0 };
            if (webError.localizedDescription(array5) != 0 || array5[0] == 0) {
                return 0;
            }
            final String bstr3 = WebKit.extractBSTR(array5[0]);
            COM.SysFreeString(array5[0]);
            if (!this.browser.isDisposed()) {
                final String string = String.valueOf((bstr != null) ? new StringBuffer(String.valueOf(bstr)).append("\n\n").toString() : "") + Compatibility.getMessage("SWT_Page_Load_Failed", new Object[] { bstr3 });
                final MessageBox messageBox = new MessageBox(this.browser.getShell(), 33);
                messageBox.setMessage(string);
                messageBox.open();
            }
            return 0;
        }
    }
    
    int didFinishLoadForFrame(final int n, final int n2) {
        final IWebView webView = new IWebView(n);
        final int[] array = { 0 };
        if (webView.mainFrame(array) != 0 || array[0] == 0) {
            return 0;
        }
        final boolean b = n2 == array[0];
        new IWebFrame(array[0]).Release();
        if (!b) {
            return 0;
        }
        if (this.html != null && this.getUrl().startsWith("about:blank")) {
            ((WebKit)this.browser.webBrowser).loadingText = true;
            final int bstr = WebKit.createBSTR(this.html);
            int n3;
            if (((WebKit)this.browser.webBrowser).untrustedText) {
                n3 = WebKit.createBSTR("about:blank");
            }
            else {
                n3 = WebKit.createBSTR("file:///");
            }
            new IWebFrame(n2).loadHTMLString(bstr, n3);
            this.html = null;
        }
        if (!((WebKit)this.browser.webBrowser).loadingText) {
            if (this.browser.isDisposed()) {
                return 0;
            }
            final Display display = this.browser.getDisplay();
            final IWebFrame webFrame = new IWebFrame(n2);
            final int[] array2 = { 0 };
            if (webFrame.dataSource(array2) != 0 || array2[0] == 0) {
                return 0;
            }
            final IWebDataSource webDataSource = new IWebDataSource(array2[0]);
            array2[0] = 0;
            final int pageTitle = webDataSource.pageTitle(array2);
            webDataSource.Release();
            if (pageTitle != 0) {
                return 0;
            }
            String bstr2 = null;
            if (array2[0] != 0) {
                bstr2 = WebKit.extractBSTR(array2[0]);
                COM.SysFreeString(array2[0]);
            }
            if (bstr2 == null || bstr2.length() == 0) {
                final TitleEvent titleEvent = new TitleEvent(this.browser);
                titleEvent.display = display;
                titleEvent.widget = this.browser;
                titleEvent.title = this.getUrl();
                final TitleListener[] titleListeners = this.browser.webBrowser.titleListeners;
                for (int i = 0; i < titleListeners.length; ++i) {
                    titleListeners[i].changed(titleEvent);
                }
                if (this.browser.isDisposed()) {
                    return 0;
                }
            }
            final ProgressEvent progressEvent = new ProgressEvent(this.browser);
            progressEvent.display = display;
            progressEvent.widget = this.browser;
            progressEvent.current = 100;
            progressEvent.total = 100;
            final ProgressListener[] progressListeners = this.browser.webBrowser.progressListeners;
            for (int j = 0; j < progressListeners.length; ++j) {
                progressListeners[j].completed(progressEvent);
            }
            if (this.browser.isDisposed()) {
                return 0;
            }
        }
        ((WebKit)this.browser.webBrowser).loadingText = false;
        return 0;
    }
    
    int didReceiveTitle(final int n, final int n2, final int n3) {
        final int[] array = { 0 };
        if (new IWebView(n).mainFrame(array) != 0 || n3 == 0) {
            return 0;
        }
        if (n3 == array[0]) {
            final String bstr = WebKit.extractBSTR(n2);
            final TitleEvent titleEvent = new TitleEvent(this.browser);
            titleEvent.display = this.browser.getDisplay();
            titleEvent.widget = this.browser;
            titleEvent.title = bstr;
            final TitleListener[] titleListeners = this.browser.webBrowser.titleListeners;
            for (int i = 0; i < titleListeners.length; ++i) {
                titleListeners[i].changed(titleEvent);
            }
        }
        new IWebFrame(array[0]).Release();
        return 0;
    }
    
    int didStartProvisionalLoadForFrame(final int n, final int n2) {
        return 0;
    }
    
    void disposeCOMInterfaces() {
        if (this.iWebFrameLoadDelegate != null) {
            this.iWebFrameLoadDelegate.dispose();
            this.iWebFrameLoadDelegate = null;
        }
    }
    
    int getAddress() {
        return this.iWebFrameLoadDelegate.getAddress();
    }
    
    String getUrl() {
        if (this.url == null || this.url.length() == 0) {
            return "about:blank";
        }
        return this.url;
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iWebFrameLoadDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebFrameLoadDelegate.getAddress()).AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, WebKit_win32.IID_IWebFrameLoadDelegate)) {
            OS.MoveMemory(n2, new int[] { this.iWebFrameLoadDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebFrameLoadDelegate.getAddress()).AddRef();
            return 0;
        }
        OS.MoveMemory(n2, new int[1], OS.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    boolean showCertificateDialog(final int n, final String s, final String s2, final int n2) {
        final Shell shell = this.browser.getShell();
        final Shell shell2 = new Shell(shell, 67680);
        shell2.setText(Compatibility.getMessage("SWT_InvalidCert_Title"));
        shell2.setLayout(new GridLayout());
        final Label label = new Label(shell2, 64);
        String host;
        try {
            host = new URL(s).getHost();
        }
        catch (MalformedURLException ex) {
            host = s;
        }
        final StringBuffer sb = new StringBuffer("\n");
        sb.append(Compatibility.getMessage("SWT_InvalidCert_Message", new String[] { host }));
        sb.append("\n\n");
        sb.append(Compatibility.getMessage(s2));
        sb.append("\n");
        sb.append(Compatibility.getMessage("SWT_InvalidCert_Connect"));
        sb.append("\n");
        label.setText(sb.toString());
        final GridData layoutData = new GridData();
        layoutData.widthHint = Math.min(label.computeSize(-1, -1).x, this.browser.getMonitor().getBounds().width * 2 / 3);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        label.setLayoutData(layoutData);
        final boolean[] array = { false };
        final Button[] array2 = new Button[3];
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                if (event.widget == array2[2]) {
                    WebFrameLoadDelegate.this.showCertificate(shell2, n2);
                }
                else {
                    array[0] = (event.widget == array2[0]);
                    shell2.close();
                }
            }
        };
        final Composite composite = new Composite(shell2, 0);
        final GridData layoutData2 = new GridData();
        layoutData2.horizontalAlignment = 3;
        composite.setLayoutData(layoutData2);
        composite.setLayout(new GridLayout(3, true));
        (array2[0] = new Button(composite, 8)).setText(SWT.getMessage("SWT_Continue"));
        array2[0].setLayoutData(new GridData(768));
        array2[0].addListener(13, listener);
        (array2[1] = new Button(composite, 8)).setText(SWT.getMessage("SWT_Cancel"));
        array2[1].setLayoutData(new GridData(768));
        array2[1].addListener(13, listener);
        (array2[2] = new Button(composite, 8)).setText(SWT.getMessage("SWT_ViewCertificate"));
        array2[2].setLayoutData(new GridData(768));
        array2[2].addListener(13, listener);
        shell2.setDefaultButton(array2[0]);
        shell2.pack();
        final Rectangle bounds = shell.getBounds();
        final Rectangle bounds2 = shell2.getBounds();
        shell2.setLocation(shell.getLocation().x + (bounds.width - bounds2.width) / 2, shell.getLocation().y + (bounds.height - bounds2.height) / 2);
        shell2.open();
        final Display display = this.browser.getDisplay();
        while (!shell2.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        return array[0];
    }
    
    void showCertificate(final Shell shell, final int n) {
        final CERT_CONTEXT cert_CONTEXT = new CERT_CONTEXT();
        OS.MoveMemory(cert_CONTEXT, n, CERT_CONTEXT.sizeof);
        final CERT_INFO cert_INFO = new CERT_INFO();
        OS.MoveMemory(cert_INFO, cert_CONTEXT.pCertInfo, CERT_INFO.sizeof);
        final int certNameToStr = OS.CertNameToStr(1, cert_INFO.Issuer, 1, null, 0);
        final TCHAR tchar = new TCHAR(0, certNameToStr);
        OS.CertNameToStr(1, cert_INFO.Issuer, 1, tchar, certNameToStr);
        final String string = tchar.toString(0, tchar.strlen());
        final int certNameToStr2 = OS.CertNameToStr(1, cert_INFO.Subject, 1, null, 0);
        final TCHAR tchar2 = new TCHAR(0, certNameToStr2);
        OS.CertNameToStr(1, cert_INFO.Subject, 1, tchar2, certNameToStr2);
        final String string2 = tchar2.toString(0, tchar2.strlen());
        final SYSTEMTIME systemtime = new SYSTEMTIME();
        OS.FileTimeToSystemTime(cert_INFO.NotBefore, systemtime);
        final String string3 = String.valueOf(systemtime.wDay) + "/" + systemtime.wMonth + "/" + systemtime.wYear;
        final String string4 = String.valueOf(systemtime.wHour) + ":" + systemtime.wMinute + ":" + systemtime.wSecond;
        final SYSTEMTIME systemtime2 = new SYSTEMTIME();
        OS.FileTimeToSystemTime(cert_INFO.NotAfter, systemtime2);
        final String string5 = String.valueOf(systemtime2.wDay) + "/" + systemtime2.wMonth + "/" + systemtime2.wYear;
        final String string6 = String.valueOf(systemtime2.wHour) + ":" + systemtime2.wMinute + ":" + systemtime2.wSecond;
        final int cbData = cert_INFO.SerialNumber.cbData;
        final byte[] array = new byte[cbData];
        OS.MoveMemory(array, cert_INFO.SerialNumber.pbData, cbData);
        String s = new String();
        for (int i = cbData - 1; i >= 0; --i) {
            final String hexString = Integer.toHexString(0xFF & array[i]);
            if (hexString.length() == 1) {
                s = String.valueOf(s) + "0";
            }
            s = String.valueOf(s) + hexString + " ";
        }
        final Shell shell2 = new Shell(shell, 67680);
        shell2.setText(SWT.getMessage("SWT_Certificate"));
        shell2.setLayout(new GridLayout(1, false));
        final TabFolder tabFolder = new TabFolder(shell2, 0);
        tabFolder.setLayoutData(new GridData(4, 4, true, true, 1, 1));
        tabFolder.setLayout(new FillLayout());
        final TabItem tabItem = new TabItem(tabFolder, 0);
        tabItem.setText(SWT.getMessage("SWT_General"));
        final Composite control = new Composite(tabFolder, 2048);
        control.setLayout(new GridLayout(1, false));
        final Label label = new Label(control, 0);
        label.setLayoutData(new GridData(1, 16777216, false, false));
        label.setText(Compatibility.getMessage("SWT_IssuedTo", new Object[] { string2 }));
        final Label label2 = new Label(control, 0);
        label2.setLayoutData(new GridData(1, 16777216, false, false));
        label2.setText(Compatibility.getMessage("SWT_IssuedFrom", new Object[] { string }));
        final Label label3 = new Label(control, 0);
        label3.setLayoutData(new GridData(1, 16777216, false, false));
        label3.setText(Compatibility.getMessage("SWT_ValidFromTo", new Object[] { string3, string5 }));
        tabItem.setControl(control);
        final TabItem tabItem2 = new TabItem(tabFolder, 0);
        tabItem2.setText(SWT.getMessage("SWT_Details"));
        final Table control2 = new Table(tabFolder, 67588);
        control2.setHeaderVisible(true);
        new TableColumn(control2, 16384).setText(SWT.getMessage("SWT_Field"));
        new TableColumn(control2, 0).setText(SWT.getMessage("SWT_Value"));
        new TableItem(control2, 0).setText(new String[] { SWT.getMessage("SWT_Version"), "V" + String.valueOf(cert_INFO.dwVersion + 1) });
        new TableItem(control2, 0).setText(new String[] { SWT.getMessage("SWT_SerialNumber"), s });
        new TableItem(control2, 0).setText(new String[] { SWT.getMessage("SWT_Issuer"), string });
        final TableItem tableItem = new TableItem(control2, 0);
        final StringBuffer sb = new StringBuffer();
        sb.append(string3);
        sb.append(", ");
        sb.append(string4);
        sb.append(" GMT");
        tableItem.setText(new String[] { SWT.getMessage("SWT_ValidFrom"), sb.toString() });
        final TableItem tableItem2 = new TableItem(control2, 0);
        final StringBuffer sb2 = new StringBuffer();
        sb2.append(string5);
        sb2.append(", ");
        sb2.append(string6);
        sb2.append(" GMT");
        tableItem2.setText(new String[] { SWT.getMessage("SWT_ValidTo"), sb2.toString() });
        new TableItem(control2, 0).setText(new String[] { SWT.getMessage("SWT_Subject"), string2 });
        for (int j = 0; j < control2.getColumnCount(); ++j) {
            control2.getColumn(j).pack();
        }
        tabItem2.setControl(control2);
        final Button defaultButton = new Button(shell2, 8);
        final GridData layoutData = new GridData(16777224, 16777216, false, false);
        layoutData.widthHint = 75;
        defaultButton.setLayoutData(layoutData);
        defaultButton.setText(SWT.getMessage("SWT_OK"));
        defaultButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(final SelectionEvent selectionEvent) {
                shell2.dispose();
            }
        });
        shell2.setDefaultButton(defaultButton);
        shell2.pack();
        final Rectangle bounds = shell.getBounds();
        final Rectangle bounds2 = shell2.getBounds();
        shell2.setLocation(shell.getLocation().x + (bounds.width - bounds2.width) / 2, shell.getLocation().y + (bounds.height - bounds2.height) / 2);
        shell2.open();
        final Display display = this.browser.getDisplay();
        while (!shell2.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
    }
}
