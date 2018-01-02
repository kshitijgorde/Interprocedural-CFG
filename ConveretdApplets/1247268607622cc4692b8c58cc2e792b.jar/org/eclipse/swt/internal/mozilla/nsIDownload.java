// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDownload extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOWNLOAD_IID_STR = "06cb92f2-1dd2-11b2-95f2-96dfdfb804a1";
    public static final nsID NS_IDOWNLOAD_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 13;
        NS_IDOWNLOAD_IID = new nsID("06cb92f2-1dd2-11b2-95f2-96dfdfb804a1");
    }
    
    public nsIDownload(final int n) {
        super(n);
    }
    
    public int Init(final int n, final int n2, final char[] array, final int n3, final long n4, final int n5) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, array, n3, n4, n5);
    }
    
    public int GetSource(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetTarget(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetPersist(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetPercentComplete(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetDisplayName(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int SetDisplayName(final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetStartTime(final long[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetMIMEInfo(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int GetListener(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int SetListener(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int GetObserver(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), array);
    }
    
    public int SetObserver(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), n);
    }
}
