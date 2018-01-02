// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIInterfaceRequestor extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IINTERFACEREQUESTOR_IID_STR = "033a1470-8b2a-11d3-af88-00a024ffc08c";
    public static final nsID NS_IINTERFACEREQUESTOR_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_IINTERFACEREQUESTOR_IID = new nsID("033a1470-8b2a-11d3-af88-00a024ffc08c");
    }
    
    public nsIInterfaceRequestor(final int n) {
        super(n);
    }
    
    public int GetInterface(final nsID nsID, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), nsID, array);
    }
}
