// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsICookieManager extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICOOKIEMANAGER_IID_STR = "aaab6710-0f2c-11d5-a53b-0010a401eb10";
    public static final nsID NS_ICOOKIEMANAGER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 3;
        NS_ICOOKIEMANAGER_IID = new nsID("aaab6710-0f2c-11d5-a53b-0010a401eb10");
    }
    
    public nsICookieManager(final int n) {
        super(n);
    }
    
    public int RemoveAll() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress());
    }
    
    public int GetEnumerator(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int Remove(final int n, final int n2, final int n3, final int n4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, n2, n3, n4);
    }
}
