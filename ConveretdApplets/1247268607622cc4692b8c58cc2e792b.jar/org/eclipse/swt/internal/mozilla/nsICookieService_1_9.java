// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsICookieService_1_9 extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICOOKIESERVICE_IID_STR = "2aaa897a-293c-4d2b-a657-8c9b7136996d";
    public static final nsID NS_ICOOKIESERVICE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_ICOOKIESERVICE_IID = new nsID("2aaa897a-293c-4d2b-a657-8c9b7136996d");
    }
    
    public nsICookieService_1_9(final int n) {
        super(n);
    }
    
    public int GetCookieString(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, array);
    }
    
    public int GetCookieStringFromHttp(final int n, final int n2, final int n3, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, n3, array);
    }
    
    public int SetCookieString(final int n, final int n2, final byte[] array, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, n2, array, n3);
    }
    
    public int SetCookieStringFromHttp(final int n, final int n2, final int n3, final byte[] array, final byte[] array2, final int n4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2, n3, array, array2, n4);
    }
}
