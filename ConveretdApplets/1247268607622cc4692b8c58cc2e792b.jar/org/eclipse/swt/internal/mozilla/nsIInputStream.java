// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIInputStream extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IINPUTSTREAM_IID_STR = "fa9c7f6c-61b3-11d4-9877-00c04fa0cf4a";
    public static final nsID NS_IINPUTSTREAM_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 5;
        NS_IINPUTSTREAM_IID = new nsID("fa9c7f6c-61b3-11d4-9877-00c04fa0cf4a");
    }
    
    public nsIInputStream(final int n) {
        super(n);
    }
    
    public int Close() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress());
    }
    
    public int Available(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int Read(final byte[] array, final int n, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array, n, array2);
    }
    
    public int ReadSegments(final int n, final int n2, final int n3, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2, n3, array);
    }
    
    public int IsNonBlocking(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
}
