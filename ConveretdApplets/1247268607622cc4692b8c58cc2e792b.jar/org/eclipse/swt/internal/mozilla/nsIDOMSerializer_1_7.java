// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMSerializer_1_7 extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMSERIALIZER_IID_STR = "9fd4ba15-e67c-4c98-b52c-7715f62c9196";
    public static final nsID NS_IDOMSERIALIZER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_IDOMSERIALIZER_IID = new nsID("9fd4ba15-e67c-4c98-b52c-7715f62c9196");
    }
    
    public nsIDOMSerializer_1_7(final int n) {
        super(n);
    }
    
    public int SerializeToString(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2);
    }
    
    public int SerializeToStream(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, n3);
    }
}
