// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIProperties extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPROPERTIES_IID_STR = "78650582-4e93-4b60-8e85-26ebd3eb14ca";
    public static final nsID NS_IPROPERTIES_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 5;
        NS_IPROPERTIES_IID = new nsID("78650582-4e93-4b60-8e85-26ebd3eb14ca");
    }
    
    public nsIProperties(final int n) {
        super(n);
    }
    
    public int Get(final byte[] array, final nsID nsID, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array, nsID, array2);
    }
    
    public int Set(final byte[] array, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array, n);
    }
    
    public int Has(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array, array2);
    }
    
    public int Undefine(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetKeys(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array, array2);
    }
}
