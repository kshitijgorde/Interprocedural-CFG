// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsISecurityCheckedComponent extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ISECURITYCHECKEDCOMPONENT_IID_STR = "0dad9e8c-a12d-4dcb-9a6f-7d09839356e1";
    public static final nsID NS_ISECURITYCHECKEDCOMPONENT_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_ISECURITYCHECKEDCOMPONENT_IID = new nsID("0dad9e8c-a12d-4dcb-9a6f-7d09839356e1");
    }
    
    public nsISecurityCheckedComponent(final int n) {
        super(n);
    }
    
    public int CanCreateWrapper(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array);
    }
    
    public int CanCallMethod(final int n, final char[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, array, array2);
    }
    
    public int CanGetProperty(final int n, final char[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, array, array2);
    }
    
    public int CanSetProperty(final int n, final char[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, array, array2);
    }
}
