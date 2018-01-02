// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIBadCertListener2 extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IBADCERTLISTENER2_IID_STR = "2c3d268c-ad82-49f3-99aa-e9ffddd7a0dc";
    public static final nsID NS_IBADCERTLISTENER2_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_IBADCERTLISTENER2_IID = new nsID("2c3d268c-ad82-49f3-99aa-e9ffddd7a0dc");
    }
    
    public nsIBadCertListener2(final int n) {
        super(n);
    }
    
    public int NotifyCertProblem(final int n, final int n2, final int n3, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, array);
    }
}
