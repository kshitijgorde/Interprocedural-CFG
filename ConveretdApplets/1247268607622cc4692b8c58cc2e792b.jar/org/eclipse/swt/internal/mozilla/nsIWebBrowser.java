// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebBrowser extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBBROWSER_IID_STR = "69e5df00-7b8b-11d3-af61-00a024ffc08c";
    public static final nsID NS_IWEBBROWSER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 7;
        NS_IWEBBROWSER_IID = new nsID("69e5df00-7b8b-11d3-af61-00a024ffc08c");
    }
    
    public nsIWebBrowser(final int n) {
        super(n);
    }
    
    public int AddWebBrowserListener(final int n, final nsID nsID) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, nsID);
    }
    
    public int RemoveWebBrowserListener(final int n, final nsID nsID) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, nsID);
    }
    
    public int GetContainerWindow(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int SetContainerWindow(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int GetParentURIContentListener(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int SetParentURIContentListener(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int GetContentDOMWindow(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
}
