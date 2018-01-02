// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebBrowserChrome extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBBROWSERCHROME_IID_STR = "ba434c60-9d52-11d3-afb0-00a024ffc08c";
    public static final nsID NS_IWEBBROWSERCHROME_IID;
    public static final int STATUS_SCRIPT = 1;
    public static final int STATUS_SCRIPT_DEFAULT = 2;
    public static final int STATUS_LINK = 3;
    public static final int CHROME_DEFAULT = 1;
    public static final int CHROME_WINDOW_BORDERS = 2;
    public static final int CHROME_WINDOW_CLOSE = 4;
    public static final int CHROME_WINDOW_RESIZE = 8;
    public static final int CHROME_MENUBAR = 16;
    public static final int CHROME_TOOLBAR = 32;
    public static final int CHROME_LOCATIONBAR = 64;
    public static final int CHROME_STATUSBAR = 128;
    public static final int CHROME_PERSONAL_TOOLBAR = 256;
    public static final int CHROME_SCROLLBARS = 512;
    public static final int CHROME_TITLEBAR = 1024;
    public static final int CHROME_EXTRA = 2048;
    public static final int CHROME_WITH_SIZE = 4096;
    public static final int CHROME_WITH_POSITION = 8192;
    public static final int CHROME_WINDOW_MIN = 16384;
    public static final int CHROME_WINDOW_POPUP = 32768;
    public static final int CHROME_WINDOW_RAISED = 33554432;
    public static final int CHROME_WINDOW_LOWERED = 67108864;
    public static final int CHROME_CENTER_SCREEN = 134217728;
    public static final int CHROME_DEPENDENT = 268435456;
    public static final int CHROME_MODAL = 536870912;
    public static final int CHROME_OPENAS_DIALOG = 1073741824;
    public static final int CHROME_OPENAS_CHROME = Integer.MIN_VALUE;
    public static final int CHROME_ALL = 4094;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 10;
        NS_IWEBBROWSERCHROME_IID = new nsID("ba434c60-9d52-11d3-afb0-00a024ffc08c");
    }
    
    public nsIWebBrowserChrome(final int n) {
        super(n);
    }
    
    public int SetStatus(final int n, final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array);
    }
    
    public int GetWebBrowser(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int SetWebBrowser(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetChromeFlags(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int SetChromeFlags(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int DestroyBrowserWindow() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress());
    }
    
    public int SizeBrowserTo(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n, n2);
    }
    
    public int ShowAsModal() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress());
    }
    
    public int IsWindowModal(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int ExitModalEventLoop(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n);
    }
}
