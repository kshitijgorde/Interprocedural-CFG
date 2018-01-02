// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWindowWatcher extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWINDOWWATCHER_IID_STR = "002286a8-494b-43b3-8ddd-49e3fc50622b";
    public static final nsID NS_IWINDOWWATCHER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 11;
        NS_IWINDOWWATCHER_IID = new nsID("002286a8-494b-43b3-8ddd-49e3fc50622b");
    }
    
    public nsIWindowWatcher(final int n) {
        super(n);
    }
    
    public int OpenWindow(final int n, final byte[] array, final byte[] array2, final byte[] array3, final int n2, final int[] array4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array, array2, array3, n2, array4);
    }
    
    public int RegisterNotification(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int UnregisterNotification(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetWindowEnumerator(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetNewPrompter(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n, array);
    }
    
    public int GetNewAuthPrompter(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n, array);
    }
    
    public int SetWindowCreator(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int GetChromeForWindow(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n, array);
    }
    
    public int GetWindowByName(final char[] array, final int n, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array, n, array2);
    }
    
    public int GetActiveWindow(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int SetActiveWindow(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
}
