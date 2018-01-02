// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIFactory extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IFACTORY_IID_STR = "00000001-0000-0000-c000-000000000046";
    public static final nsID NS_IFACTORY_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_IFACTORY_IID = new nsID("00000001-0000-0000-c000-000000000046");
    }
    
    public nsIFactory(final int n) {
        super(n);
    }
    
    public int CreateInstance(final int n, final nsID nsID, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, nsID, array);
    }
    
    public int LockFactory(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
}
