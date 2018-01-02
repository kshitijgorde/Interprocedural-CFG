// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMWindowCollection extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMWINDOWCOLLECTION_IID_STR = "a6cf906f-15b3-11d2-932e-00805f8add32";
    public static final nsID NS_IDOMWINDOWCOLLECTION_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 3;
        NS_IDOMWINDOWCOLLECTION_IID = new nsID("a6cf906f-15b3-11d2-932e-00805f8add32");
    }
    
    public nsIDOMWindowCollection(final int n) {
        super(n);
    }
    
    public int GetLength(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int Item(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, array);
    }
    
    public int NamedItem(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, array);
    }
}
