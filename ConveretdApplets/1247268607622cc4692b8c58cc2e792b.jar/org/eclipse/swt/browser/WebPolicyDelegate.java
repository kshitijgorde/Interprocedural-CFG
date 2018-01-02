// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.internal.webkit.IWebError;
import org.eclipse.swt.internal.webkit.WebKit_win32;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.webkit.IWebPreferences;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.webkit.IWebURLRequest;
import org.eclipse.swt.internal.webkit.IWebPolicyDecisionListener;
import org.eclipse.swt.internal.webkit.IWebView;
import org.eclipse.swt.internal.ole.win32.COMObject;

class WebPolicyDelegate
{
    COMObject iWebPolicyDelegate;
    int refCount;
    Browser browser;
    
    WebPolicyDelegate(final Browser browser) {
        this.refCount = 0;
        this.createCOMInterfaces();
        this.browser = browser;
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.iWebPolicyDelegate = new COMObject(new int[] { 2, 0, 0, 5, 5, 5, 3 }) {
            public int method0(final int[] array) {
                return WebPolicyDelegate.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebPolicyDelegate.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebPolicyDelegate.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebPolicyDelegate.this.decidePolicyForNavigationAction(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method4(final int[] array) {
                return WebPolicyDelegate.this.decidePolicyForNewWindowAction(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method5(final int[] array) {
                return WebPolicyDelegate.this.decidePolicyForMIMEType(array[0], array[1], array[2], array[3], array[4]);
            }
            
            public int method6(final int[] array) {
                return WebPolicyDelegate.this.unableToImplementPolicyWithError(array[0], array[1], array[2]);
            }
        };
    }
    
    int decidePolicyForMIMEType(final int n, final int n2, final int n3, final int n4, final int n5) {
        final IWebView webView = new IWebView(n);
        final int[] array = { 0 };
        webView.canShowMIMEType(n2, array);
        final IWebPolicyDecisionListener webPolicyDecisionListener = new IWebPolicyDecisionListener(n5);
        if (array[0] != 0) {
            webPolicyDecisionListener.use();
        }
        else {
            webPolicyDecisionListener.download();
        }
        return 0;
    }
    
    int decidePolicyForNavigationAction(final int n, final int n2, final int n3, final int n4, final int n5) {
        final IWebURLRequest webURLRequest = new IWebURLRequest(n3);
        final int[] array = { 0 };
        if (webURLRequest.URL(array) != 0 || array[0] == 0) {
            return 0;
        }
        String s = WebKit.extractBSTR(array[0]);
        COM.SysFreeString(array[0]);
        final IWebPolicyDecisionListener webPolicyDecisionListener = new IWebPolicyDecisionListener(n5);
        final WebKit webKit = (WebKit)this.browser.webBrowser;
        if (webKit.loadingText) {
            webPolicyDecisionListener.use();
            return 0;
        }
        if (s.length() == 0) {
            webPolicyDecisionListener.ignore();
            return 0;
        }
        if (s.startsWith("file://") && webKit.getUrl().startsWith("about:blank") && webKit.untrustedText) {
            webPolicyDecisionListener.ignore();
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
        final LocationEvent locationEvent = new LocationEvent(this.browser);
        locationEvent.display = this.browser.getDisplay();
        locationEvent.widget = this.browser;
        locationEvent.location = s;
        locationEvent.doit = true;
        final LocationListener[] locationListeners = webKit.locationListeners;
        if (locationListeners != null) {
            for (int i = 0; i < locationListeners.length; ++i) {
                locationListeners[i].changing(locationEvent);
            }
        }
        if (locationEvent.doit) {
            if (webKit.jsEnabledChanged) {
                webKit.jsEnabledChanged = false;
                final IWebView webView = new IWebView(n);
                array[0] = 0;
                if (webView.preferences(array) == 0 && array[0] != 0) {
                    final IWebPreferences webPreferences = new IWebPreferences(array[0]);
                    webPreferences.setJavaScriptEnabled(webKit.jsEnabled ? 1 : 0);
                    webView.setPreferences(webPreferences.getAddress());
                    webPreferences.Release();
                }
            }
            webPolicyDecisionListener.use();
            webKit.lastNavigateURL = s;
        }
        else {
            webPolicyDecisionListener.ignore();
        }
        return 0;
    }
    
    int decidePolicyForNewWindowAction(final int n, final int n2, final int n3, final int n4, final int n5) {
        new IWebPolicyDecisionListener(n5).use();
        return 0;
    }
    
    protected void disposeCOMInterfaces() {
        if (this.iWebPolicyDelegate != null) {
            this.iWebPolicyDelegate.dispose();
            this.iWebPolicyDelegate = null;
        }
    }
    
    int getAddress() {
        return this.iWebPolicyDelegate.getAddress();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iWebPolicyDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebPolicyDelegate.getAddress()).AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, WebKit_win32.IID_IWebPolicyDelegate)) {
            OS.MoveMemory(n2, new int[] { this.iWebPolicyDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebPolicyDelegate.getAddress()).AddRef();
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
    
    int unableToImplementPolicyWithError(final int n, final int n2, final int n3) {
        if (this.browser.isDisposed()) {
            return 0;
        }
        final IWebError webError = new IWebError(n2);
        Object bstr = null;
        final int[] array = { 0 };
        if (webError.failingURL(array) == 0 && array[0] != 0) {
            bstr = WebKit.extractBSTR(array[0]);
            COM.SysFreeString(array[0]);
        }
        array[0] = 0;
        if (webError.localizedDescription(array) != 0 || array[0] == 0) {
            return 0;
        }
        final String bstr2 = WebKit.extractBSTR(array[0]);
        COM.SysFreeString(array[0]);
        final String string = String.valueOf((bstr != null) ? new StringBuffer(String.valueOf(bstr)).append("\n\n").toString() : "") + Compatibility.getMessage("SWT_Page_Load_Failed", new Object[] { bstr2 });
        final MessageBox messageBox = new MessageBox(this.browser.getShell(), 33);
        messageBox.setMessage(string);
        messageBox.open();
        return 0;
    }
}
