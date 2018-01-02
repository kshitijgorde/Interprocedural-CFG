// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsISimpleEnumerator extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ISIMPLEENUMERATOR_IID_STR = "d1899240-f9d2-11d2-bdd6-000064657374";
    public static final nsID NS_ISIMPLEENUMERATOR_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_ISIMPLEENUMERATOR_IID = new nsID("d1899240-f9d2-11d2-bdd6-000064657374");
    }
    
    public nsISimpleEnumerator(final int n) {
        super(n);
    }
    
    public int HasMoreElements(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetNext(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
}
