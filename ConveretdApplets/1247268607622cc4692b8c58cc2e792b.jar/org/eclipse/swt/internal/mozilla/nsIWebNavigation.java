// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebNavigation extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBNAVIGATION_IID_STR = "f5d9e7b0-d930-11d3-b057-00a024ffc08c";
    public static final nsID NS_IWEBNAVIGATION_IID;
    public static final int LOAD_FLAGS_MASK = 65535;
    public static final int LOAD_FLAGS_NONE = 0;
    public static final int LOAD_FLAGS_IS_REFRESH = 16;
    public static final int LOAD_FLAGS_IS_LINK = 32;
    public static final int LOAD_FLAGS_BYPASS_HISTORY = 64;
    public static final int LOAD_FLAGS_REPLACE_HISTORY = 128;
    public static final int LOAD_FLAGS_BYPASS_CACHE = 256;
    public static final int LOAD_FLAGS_BYPASS_PROXY = 512;
    public static final int LOAD_FLAGS_CHARSET_CHANGE = 1024;
    public static final int STOP_NETWORK = 1;
    public static final int STOP_CONTENT = 2;
    public static final int STOP_ALL = 3;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 13;
        NS_IWEBNAVIGATION_IID = new nsID("f5d9e7b0-d930-11d3-b057-00a024ffc08c");
    }
    
    public nsIWebNavigation(final int n) {
        super(n);
    }
    
    public int GetCanGoBack(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetCanGoForward(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GoBack() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
    
    public int GoForward() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress());
    }
    
    public int GotoIndex(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int LoadURI(final char[] array, final int n, final int n2, final int n3, final int n4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array, n, n2, n3, n4);
    }
    
    public int Reload(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int Stop(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int GetDocument(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int GetCurrentURI(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int GetReferringURI(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), array);
    }
    
    public int GetSessionHistory(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), array);
    }
    
    public int SetSessionHistory(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), n);
    }
}
