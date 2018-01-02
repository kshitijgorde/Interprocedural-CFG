// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIPrefLocalizedString extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPREFLOCALIZEDSTRING_IID_STR = "ae419e24-1dd1-11b2-b39a-d3e5e7073802";
    public static final nsID NS_IPREFLOCALIZEDSTRING_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_IPREFLOCALIZEDSTRING_IID = new nsID("ae419e24-1dd1-11b2-b39a-d3e5e7073802");
    }
    
    public nsIPrefLocalizedString(final int n) {
        super(n);
    }
    
    public int GetData(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int SetData(final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int ToString(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int SetDataWithLength(final int n, final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, array);
    }
}
