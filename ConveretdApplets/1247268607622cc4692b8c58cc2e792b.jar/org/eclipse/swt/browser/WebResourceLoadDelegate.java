// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.webkit.IWebMutableURLRequest;
import org.eclipse.swt.internal.webkit.IWebURLRequest;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.internal.Compatibility;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.ole.win32.IUnknown;
import org.eclipse.swt.internal.ole.win32.GUID;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.webkit.IWebView;
import org.eclipse.swt.internal.webkit.IWebFrame;
import org.eclipse.swt.internal.webkit.IWebDataSource;
import org.eclipse.swt.internal.webkit.IWebURLProtectionSpace;
import org.eclipse.swt.internal.ole.win32.COM;
import org.eclipse.swt.internal.webkit.IWebURLCredential;
import org.eclipse.swt.internal.webkit.WebKit_win32;
import org.eclipse.swt.internal.webkit.IWebURLAuthenticationChallengeSender;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.webkit.IWebURLAuthenticationChallenge;
import org.eclipse.swt.internal.ole.win32.COMObject;

class WebResourceLoadDelegate
{
    COMObject iWebResourceLoadDelegate;
    int refCount;
    Browser browser;
    String postData;
    
    WebResourceLoadDelegate(final Browser browser) {
        this.refCount = 0;
        this.createCOMInterfaces();
        this.browser = browser;
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.iWebResourceLoadDelegate = new COMObject(new int[] { 2, 0, 0, 4, 6, 4, 4, 4, 4, 3, 4, 3 }) {
            public int method0(final int[] array) {
                return WebResourceLoadDelegate.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WebResourceLoadDelegate.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WebResourceLoadDelegate.this.Release();
            }
            
            public int method3(final int[] array) {
                return WebResourceLoadDelegate.this.identifierForInitialRequest(array[0], array[1], array[2], array[3]);
            }
            
            public int method4(final int[] array) {
                return WebResourceLoadDelegate.this.willSendRequest(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
            
            public int method5(final int[] array) {
                return WebResourceLoadDelegate.this.didReceiveAuthenticationChallenge(array[0], array[1], array[2], array[3]);
            }
            
            public int method6(final int[] array) {
                return -2147467263;
            }
            
            public int method7(final int[] array) {
                return 0;
            }
            
            public int method8(final int[] array) {
                return 0;
            }
            
            public int method9(final int[] array) {
                return 0;
            }
            
            public int method10(final int[] array) {
                return 0;
            }
            
            public int method11(final int[] array) {
                return -2147467263;
            }
        };
    }
    
    int didReceiveAuthenticationChallenge(final int n, final int n2, final int n3, final int n4) {
        final IWebURLAuthenticationChallenge webURLAuthenticationChallenge = new IWebURLAuthenticationChallenge(n3);
        final int[] array = { 0 };
        final int previousFailureCount = webURLAuthenticationChallenge.previousFailureCount(array);
        final int[] array2 = { 0 };
        if (previousFailureCount == 0 && array[0] < 3) {
            final AuthenticationListener[] authenticationListeners = this.browser.webBrowser.authenticationListeners;
            int i = 0;
            while (i < authenticationListeners.length) {
                final AuthenticationEvent authenticationEvent = new AuthenticationEvent(this.browser);
                authenticationEvent.location = ((WebKit)this.browser.webBrowser).lastNavigateURL;
                authenticationListeners[i].authenticate(authenticationEvent);
                if (!authenticationEvent.doit) {
                    if (webURLAuthenticationChallenge.sender(array2) != 0 || array2[0] == 0) {
                        return 0;
                    }
                    final IWebURLAuthenticationChallengeSender webURLAuthenticationChallengeSender = new IWebURLAuthenticationChallengeSender(array2[0]);
                    webURLAuthenticationChallengeSender.cancelAuthenticationChallenge(n3);
                    webURLAuthenticationChallengeSender.Release();
                    return 0;
                }
                else {
                    if (authenticationEvent.user != null && authenticationEvent.password != null && webURLAuthenticationChallenge.sender(array2) == 0 && array2[0] != 0) {
                        final IWebURLAuthenticationChallengeSender webURLAuthenticationChallengeSender2 = new IWebURLAuthenticationChallengeSender(array2[0]);
                        array2[0] = 0;
                        if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebURLCredential, 0, WebKit_win32.IID_IWebURLCredential, array2) == 0 && array2[0] != 0) {
                            final IWebURLCredential webURLCredential = new IWebURLCredential(array2[0]);
                            webURLCredential.initWithUser(WebKit.createBSTR(authenticationEvent.user), WebKit.createBSTR(authenticationEvent.password), 1);
                            webURLAuthenticationChallengeSender2.useCredential(webURLCredential.getAddress(), n3);
                            webURLCredential.Release();
                        }
                        webURLAuthenticationChallengeSender2.Release();
                        return 0;
                    }
                    ++i;
                }
            }
        }
        final String[] array3 = { null };
        final String[] array4 = { null };
        array2[0] = 0;
        if (webURLAuthenticationChallenge.proposedCredential(array2) == 0 && array2[0] != 0) {
            final IWebURLCredential webURLCredential2 = new IWebURLCredential(array2[0]);
            array2[0] = 0;
            if (webURLCredential2.user(array2) == 0 && array2[0] != 0) {
                array3[0] = WebKit.extractBSTR(array2[0]);
                COM.SysFreeString(array2[0]);
                final int[] array5 = { 0 };
                if (webURLCredential2.hasPassword(array5) == 0 && array5[0] != 0) {
                    array2[0] = 0;
                    if (webURLCredential2.password(array2) == 0 && array2[0] != 0) {
                        array4[0] = WebKit.extractBSTR(array2[0]);
                        COM.SysFreeString(array2[0]);
                    }
                }
            }
            webURLCredential2.Release();
        }
        array2[0] = 0;
        if (webURLAuthenticationChallenge.protectionSpace(array2) != 0 || array2[0] == 0) {
            return 0;
        }
        final IWebURLProtectionSpace webURLProtectionSpace = new IWebURLProtectionSpace(array2[0]);
        String s = null;
        String bstr = null;
        array2[0] = 0;
        if (webURLProtectionSpace.host(array2) == 0 && array2[0] != 0) {
            s = WebKit.extractBSTR(array2[0]);
            COM.SysFreeString(array2[0]);
            final int[] array6 = { 0 };
            if (webURLProtectionSpace.port(array6) == 0) {
                s = String.valueOf(s) + ":" + array6[0];
                array2[0] = 0;
                if (webURLProtectionSpace.realm(array2) == 0 && array2[0] != 0) {
                    bstr = WebKit.extractBSTR(array2[0]);
                    COM.SysFreeString(array2[0]);
                }
            }
        }
        webURLProtectionSpace.Release();
        final boolean showAuthenticationDialog = this.showAuthenticationDialog(array3, array4, s, bstr);
        array2[0] = 0;
        if (webURLAuthenticationChallenge.sender(array2) != 0 || array2[0] == 0) {
            return 0;
        }
        final IWebURLAuthenticationChallengeSender webURLAuthenticationChallengeSender3 = new IWebURLAuthenticationChallengeSender(array2[0]);
        if (!showAuthenticationDialog) {
            webURLAuthenticationChallengeSender3.cancelAuthenticationChallenge(n3);
            webURLAuthenticationChallengeSender3.Release();
            return 0;
        }
        array2[0] = 0;
        if (WebKit_win32.WebKitCreateInstance(WebKit_win32.CLSID_WebURLCredential, 0, WebKit_win32.IID_IWebURLCredential, array2) == 0 && array2[0] != 0) {
            final IWebURLCredential webURLCredential3 = new IWebURLCredential(array2[0]);
            webURLCredential3.initWithUser(WebKit.createBSTR(array3[0]), WebKit.createBSTR(array4[0]), 1);
            webURLAuthenticationChallengeSender3.useCredential(webURLCredential3.getAddress(), n3);
            webURLCredential3.Release();
        }
        webURLAuthenticationChallengeSender3.Release();
        return 0;
    }
    
    void disposeCOMInterfaces() {
        if (this.iWebResourceLoadDelegate != null) {
            this.iWebResourceLoadDelegate.dispose();
            this.iWebResourceLoadDelegate = null;
        }
    }
    
    int getAddress() {
        return this.iWebResourceLoadDelegate.getAddress();
    }
    
    int identifierForInitialRequest(final int n, final int n2, final int n3, final int n4) {
        if (this.browser.isDisposed()) {
            return 0;
        }
        final IWebDataSource webDataSource = new IWebDataSource(n3);
        final int[] array = { 0 };
        if (webDataSource.webFrame(array) != 0 || array[0] == 0) {
            return 0;
        }
        new IWebFrame(array[0]).Release();
        final int[] array2 = { 0 };
        final IWebView webView = new IWebView(n);
        if (webView.mainFrame(array2) != 0 || array2[0] == 0) {
            return 0;
        }
        new IWebFrame(array2[0]).Release();
        if (array[0] == array2[0]) {
            final int malloc = C.malloc(8);
            webView.estimatedProgress(malloc);
            final double[] array3 = { 0.0 };
            OS.MoveMemory(array3, malloc, 8);
            C.free(malloc);
            final int current = (int)(array3[0] * 100.0);
            final ProgressEvent progressEvent = new ProgressEvent(this.browser);
            progressEvent.display = this.browser.getDisplay();
            progressEvent.widget = this.browser;
            progressEvent.current = current;
            progressEvent.total = Math.max(current, 100);
            final ProgressListener[] progressListeners = this.browser.webBrowser.progressListeners;
            for (int i = 0; i < progressListeners.length; ++i) {
                progressListeners[i].changed(progressEvent);
            }
        }
        return 0;
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147024809;
        }
        final GUID guid = new GUID();
        COM.MoveMemory(guid, n, GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIUnknown)) {
            OS.MoveMemory(n2, new int[] { this.iWebResourceLoadDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebResourceLoadDelegate.getAddress()).AddRef();
            return 0;
        }
        if (COM.IsEqualGUID(guid, WebKit_win32.IID_IWebResourceLoadDelegate)) {
            OS.MoveMemory(n2, new int[] { this.iWebResourceLoadDelegate.getAddress() }, OS.PTR_SIZEOF);
            new IUnknown(this.iWebResourceLoadDelegate.getAddress()).AddRef();
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
    
    boolean showAuthenticationDialog(final String[] array, final String[] array2, final String s, final String s2) {
        final Shell shell = this.browser.getShell();
        final Shell shell2 = new Shell(shell);
        shell2.setLayout(new GridLayout());
        shell2.setText(SWT.getMessage("SWT_Authentication_Required"));
        final Label label = new Label(shell2, 64);
        label.setText(Compatibility.getMessage("SWT_Enter_Username_and_Password", new String[] { s2, s }));
        final GridData layoutData = new GridData();
        layoutData.widthHint = Math.min(label.computeSize(-1, -1).x, this.browser.getMonitor().getBounds().width * 2 / 3);
        layoutData.horizontalAlignment = 4;
        layoutData.grabExcessHorizontalSpace = true;
        label.setLayoutData(layoutData);
        new Label(shell2, 0).setText(SWT.getMessage("SWT_Username"));
        final Text text = new Text(shell2, 2048);
        if (array[0] != null) {
            text.setText(array[0]);
        }
        final GridData layoutData2 = new GridData();
        layoutData2.horizontalAlignment = 4;
        layoutData2.grabExcessHorizontalSpace = true;
        text.setLayoutData(layoutData2);
        new Label(shell2, 0).setText(SWT.getMessage("SWT_Password"));
        final Text text2 = new Text(shell2, 4196352);
        if (array2[0] != null) {
            text2.setText(array2[0]);
        }
        final GridData layoutData3 = new GridData();
        layoutData3.horizontalAlignment = 4;
        layoutData3.grabExcessHorizontalSpace = true;
        text2.setLayoutData(layoutData3);
        final boolean[] array3 = { false };
        final Button[] array4 = new Button[2];
        final Listener listener = new Listener() {
            public void handleEvent(final Event event) {
                array[0] = text.getText();
                array2[0] = text2.getText();
                array3[0] = (event.widget == array4[1]);
                shell2.close();
            }
        };
        final Composite composite = new Composite(shell2, 0);
        final GridData layoutData4 = new GridData();
        layoutData4.horizontalAlignment = 3;
        composite.setLayoutData(layoutData4);
        composite.setLayout(new GridLayout(2, true));
        (array4[0] = new Button(composite, 8)).setText(SWT.getMessage("SWT_Cancel"));
        array4[0].setLayoutData(new GridData(768));
        array4[0].addListener(13, listener);
        (array4[1] = new Button(composite, 8)).setText(SWT.getMessage("SWT_OK"));
        array4[1].setLayoutData(new GridData(768));
        array4[1].addListener(13, listener);
        shell2.setDefaultButton(array4[1]);
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
        return array3[0];
    }
    
    int willSendRequest(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        final IWebURLRequest webURLRequest = new IWebURLRequest(n3);
        final int[] array = { 0 };
        if (webURLRequest.URL(array) == 0 && array[0] != 0) {
            final String bstr = WebKit.extractBSTR(array[0]);
            COM.SysFreeString(array[0]);
            if (bstr.startsWith("file://") && !bstr.startsWith("file:///")) {
                final String string = "file:///" + bstr.substring("file://".length());
                array[0] = 0;
                if (webURLRequest.mutableCopy(array) == 0 && array[0] != 0) {
                    final IWebMutableURLRequest webMutableURLRequest = new IWebMutableURLRequest(array[0]);
                    webMutableURLRequest.setURL(WebKit.createBSTR(string));
                    OS.MoveMemory(n6, new int[] { webMutableURLRequest.getAddress() }, C.PTR_SIZEOF);
                    return 0;
                }
            }
        }
        webURLRequest.AddRef();
        OS.MoveMemory(n6, new int[] { n3 }, C.PTR_SIZEOF);
        return 0;
    }
}
