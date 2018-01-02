// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsICertOverrideService extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICERTOVERRIDESERVICE_IID_STR = "31738d2a-77d3-4359-84c9-4be2f38fb8c5";
    public static final nsID NS_ICERTOVERRIDESERVICE_IID;
    public static final int ERROR_UNTRUSTED = 1;
    public static final int ERROR_MISMATCH = 2;
    public static final int ERROR_TIME = 4;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 6;
        NS_ICERTOVERRIDESERVICE_IID = new nsID("31738d2a-77d3-4359-84c9-4be2f38fb8c5");
    }
    
    public nsICertOverrideService(final int n) {
        super(n);
    }
    
    public int RememberValidityOverride(final int n, final int n2, final int n3, final int n4, final int n5) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4, n5);
    }
    
    public int HasMatchingOverride(final int n, final int n2, final int n3, final int[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, n3, array, array2, array3);
    }
    
    public int GetValidityOverride(final int n, final int n2, final int n3, final int n4, final int[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, n2, n3, n4, array, array2, array3);
    }
    
    public int ClearValidityOverride(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2);
    }
    
    public int GetAllOverrideHostsWithPorts(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array, array2);
    }
    
    public int IsCertUsedForOverrides(final int n, final int n2, final int n3, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n, n2, n3, array);
    }
}
