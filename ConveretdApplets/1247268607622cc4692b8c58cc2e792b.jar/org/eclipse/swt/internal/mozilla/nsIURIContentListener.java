// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIURIContentListener extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IURICONTENTLISTENER_IID_STR = "94928ab3-8b63-11d3-989d-001083010e9b";
    public static final nsID NS_IURICONTENTLISTENER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 8;
        NS_IURICONTENTLISTENER_IID = new nsID("94928ab3-8b63-11d3-989d-001083010e9b");
    }
    
    public nsIURIContentListener(final int n) {
        super(n);
    }
    
    public int OnStartURIOpen(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array);
    }
    
    public int DoContent(final byte[] array, final int n, final int n2, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array, n, n2, array2, array3);
    }
    
    public int IsPreferred(final byte[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array, array2, array3);
    }
    
    public int CanHandleContent(final byte[] array, final int n, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array, n, array2, array3);
    }
    
    public int GetLoadCookie(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int SetLoadCookie(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int GetParentContentListener(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int SetParentContentListener(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
}
