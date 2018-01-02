// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIPrefService extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPREFSERVICE_IID_STR = "decb9cc7-c08f-4ea5-be91-a8fc637ce2d2";
    public static final nsID NS_IPREFSERVICE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 6;
        NS_IPREFSERVICE_IID = new nsID("decb9cc7-c08f-4ea5-be91-a8fc637ce2d2");
    }
    
    public nsIPrefService(final int n) {
        super(n);
    }
    
    public int ReadUserPrefs(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int ResetPrefs() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress());
    }
    
    public int ResetUserPrefs() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
    
    public int SavePrefFile(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int GetBranch(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array, array2);
    }
    
    public int GetDefaultBranch(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array, array2);
    }
}
