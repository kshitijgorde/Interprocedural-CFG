// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.browser;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.internal.mozilla.nsIURI;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.internal.mozilla.nsIBaseWindow;
import org.eclipse.swt.internal.mozilla.nsIWebBrowser;
import org.eclipse.swt.internal.mozilla.nsIWebBrowserChrome;
import org.eclipse.swt.internal.mozilla.nsIWindowCreator2;
import org.eclipse.swt.internal.mozilla.nsIWindowCreator;
import org.eclipse.swt.internal.C;
import org.eclipse.swt.internal.mozilla.nsISupports;
import org.eclipse.swt.internal.mozilla.XPCOM;
import org.eclipse.swt.internal.mozilla.nsID;
import org.eclipse.swt.internal.mozilla.XPCOMObject;

class WindowCreator2
{
    XPCOMObject supports;
    XPCOMObject windowCreator;
    XPCOMObject windowCreator2;
    int refCount;
    
    WindowCreator2() {
        this.refCount = 0;
        this.createCOMInterfaces();
    }
    
    int AddRef() {
        return ++this.refCount;
    }
    
    void createCOMInterfaces() {
        this.supports = new XPCOMObject(new int[] { 2, 0, 0 }) {
            public int method0(final int[] array) {
                return WindowCreator2.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WindowCreator2.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WindowCreator2.this.Release();
            }
        };
        this.windowCreator = new XPCOMObject(new int[] { 2, 0, 0, 3 }) {
            public int method0(final int[] array) {
                return WindowCreator2.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WindowCreator2.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WindowCreator2.this.Release();
            }
            
            public int method3(final int[] array) {
                return WindowCreator2.this.CreateChromeWindow(array[0], array[1], array[2]);
            }
        };
        this.windowCreator2 = new XPCOMObject(new int[] { 2, 0, 0, 3, 6 }) {
            public int method0(final int[] array) {
                return WindowCreator2.this.QueryInterface(array[0], array[1]);
            }
            
            public int method1(final int[] array) {
                return WindowCreator2.this.AddRef();
            }
            
            public int method2(final int[] array) {
                return WindowCreator2.this.Release();
            }
            
            public int method3(final int[] array) {
                return WindowCreator2.this.CreateChromeWindow(array[0], array[1], array[2]);
            }
            
            public int method4(final int[] array) {
                return WindowCreator2.this.CreateChromeWindow2(array[0], array[1], array[2], array[3], array[4], array[5]);
            }
        };
    }
    
    void disposeCOMInterfaces() {
        if (this.supports != null) {
            this.supports.dispose();
            this.supports = null;
        }
        if (this.windowCreator != null) {
            this.windowCreator.dispose();
            this.windowCreator = null;
        }
        if (this.windowCreator2 != null) {
            this.windowCreator2.dispose();
            this.windowCreator2 = null;
        }
    }
    
    int getAddress() {
        return this.windowCreator.getAddress();
    }
    
    int QueryInterface(final int n, final int n2) {
        if (n == 0 || n2 == 0) {
            return -2147467262;
        }
        final nsID nsID = new nsID();
        XPCOM.memmove(nsID, n, 16);
        if (nsID.Equals(nsISupports.NS_ISUPPORTS_IID)) {
            C.memmove(n2, new int[] { this.supports.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIWindowCreator.NS_IWINDOWCREATOR_IID)) {
            C.memmove(n2, new int[] { this.windowCreator.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        if (nsID.Equals(nsIWindowCreator2.NS_IWINDOWCREATOR2_IID)) {
            C.memmove(n2, new int[] { this.windowCreator2.getAddress() }, C.PTR_SIZEOF);
            this.AddRef();
            return 0;
        }
        C.memmove(n2, new int[1], C.PTR_SIZEOF);
        return -2147467262;
    }
    
    int Release() {
        --this.refCount;
        if (this.refCount == 0) {
            this.disposeCOMInterfaces();
        }
        return this.refCount;
    }
    
    int CreateChromeWindow(final int n, final int n2, final int n3) {
        return this.CreateChromeWindow2(n, n2, 0, 0, 0, n3);
    }
    
    int CreateChromeWindow2(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        if (n == 0 && (n2 & Integer.MIN_VALUE) == 0x0) {
            return -2147467263;
        }
        Browser browser = null;
        if (n != 0) {
            final nsIWebBrowserChrome nsIWebBrowserChrome = new nsIWebBrowserChrome(n);
            final int[] array = { 0 };
            final int getWebBrowser = nsIWebBrowserChrome.GetWebBrowser(array);
            if (getWebBrowser != 0) {
                Mozilla.error(getWebBrowser);
            }
            if (array[0] == 0) {
                Mozilla.error(-2147467262);
            }
            final nsIWebBrowser nsIWebBrowser = new nsIWebBrowser(array[0]);
            final int[] array2 = { 0 };
            final int queryInterface = nsIWebBrowser.QueryInterface(nsIBaseWindow.NS_IBASEWINDOW_IID, array2);
            if (queryInterface != 0) {
                Mozilla.error(queryInterface);
            }
            if (array2[0] == 0) {
                Mozilla.error(-2147467262);
            }
            nsIWebBrowser.Release();
            final nsIBaseWindow nsIBaseWindow = new nsIBaseWindow(array2[0]);
            array2[0] = 0;
            final int[] array3 = { 0 };
            final int getParentNativeWindow = nsIBaseWindow.GetParentNativeWindow(array3);
            if (getParentNativeWindow != 0) {
                Mozilla.error(getParentNativeWindow);
            }
            if (array3[0] == 0) {
                Mozilla.error(-2147467262);
            }
            nsIBaseWindow.Release();
            browser = Mozilla.findBrowser(array3[0]);
        }
        boolean b = true;
        Browser browser2;
        if ((n2 & Integer.MIN_VALUE) != 0x0) {
            int n7 = 0;
            if ((n2 & 0x8000) == 0x0) {
                n7 |= 0x860;
            }
            if ((n2 & 0x20000000) != 0x0) {
                n7 |= 0x10000;
            }
            final Shell shell = (browser == null) ? new Shell(n7) : new Shell(browser.getShell(), n7);
            shell.setLayout(new FillLayout());
            browser2 = new Browser(shell, (browser == null) ? 32768 : (browser.getStyle() & 0x8000));
            browser2.addVisibilityWindowListener(new VisibilityWindowListener() {
                public void hide(final WindowEvent windowEvent) {
                }
                
                public void show(final WindowEvent windowEvent) {
                    if (windowEvent.location != null) {
                        shell.setLocation(windowEvent.location);
                    }
                    if (windowEvent.size != null) {
                        final Point size = windowEvent.size;
                        shell.setSize(shell.computeSize(size.x, size.y));
                    }
                    shell.open();
                }
            });
            browser2.addCloseWindowListener(new CloseWindowListener() {
                public void close(final WindowEvent windowEvent) {
                    shell.close();
                }
            });
            if (n4 != 0) {
                final nsIURI nsIURI = new nsIURI(n4);
                final int nsEmbedCString_new = XPCOM.nsEmbedCString_new();
                if (nsIURI.GetSpec(nsEmbedCString_new) == 0) {
                    final int nsEmbedCString_Length = XPCOM.nsEmbedCString_Length(nsEmbedCString_new);
                    if (nsEmbedCString_Length > 0) {
                        final int nsEmbedCString_get = XPCOM.nsEmbedCString_get(nsEmbedCString_new);
                        final byte[] array4 = new byte[nsEmbedCString_Length];
                        C.memmove(array4, nsEmbedCString_get, nsEmbedCString_Length);
                        browser2.setUrl(new String(array4));
                    }
                }
                XPCOM.nsEmbedCString_delete(nsEmbedCString_new);
            }
        }
        else {
            final WindowEvent windowEvent = new WindowEvent(browser);
            windowEvent.display = browser.getDisplay();
            windowEvent.widget = browser;
            windowEvent.required = true;
            for (int i = 0; i < browser.webBrowser.openWindowListeners.length; ++i) {
                browser.webBrowser.openWindowListeners[i].open(windowEvent);
            }
            browser2 = windowEvent.browser;
            b = (browser2 != null && !browser2.isDisposed());
            if (b) {
                final String s = "win32";
                b = (s.equals("gtk") || s.equals("motif") || (browser2.getStyle() & 0x8000) != 0x0);
            }
        }
        if (b) {
            final Mozilla mozilla = (Mozilla)browser2.webBrowser;
            mozilla.isChild = true;
            final int address = mozilla.webBrowserChrome.getAddress();
            final nsIWebBrowserChrome nsIWebBrowserChrome2 = new nsIWebBrowserChrome(address);
            nsIWebBrowserChrome2.SetChromeFlags(n2);
            nsIWebBrowserChrome2.AddRef();
            C.memmove(n6, new int[] { address }, C.PTR_SIZEOF);
        }
        else if (n5 != 0) {
            C.memmove(n5, new int[] { 1 }, 4);
        }
        return b ? 0 : -2147467263;
    }
}
