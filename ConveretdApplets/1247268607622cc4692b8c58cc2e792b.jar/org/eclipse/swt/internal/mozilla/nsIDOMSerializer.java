// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMSerializer extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMSERIALIZER_IID_STR = "a6cf9123-15b3-11d2-932e-00805f8add32";
    public static final nsID NS_IDOMSERIALIZER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_IDOMSERIALIZER_IID = new nsID("a6cf9123-15b3-11d2-932e-00805f8add32");
    }
    
    public nsIDOMSerializer(final int n) {
        super(n);
    }
    
    public int SerializeToString(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array);
    }
    
    public int SerializeToStream(final int n, final int n2, final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, array);
    }
}
