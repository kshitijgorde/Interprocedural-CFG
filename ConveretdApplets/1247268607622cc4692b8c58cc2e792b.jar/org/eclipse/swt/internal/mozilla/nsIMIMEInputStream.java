// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIMIMEInputStream extends nsIInputStream
{
    static final int LAST_METHOD_ID;
    public static final String NS_IMIMEINPUTSTREAM_IID_STR = "dcbce63c-1dd1-11b2-b94d-91f6d49a3161";
    public static final nsID NS_IMIMEINPUTSTREAM_IID;
    
    static {
        LAST_METHOD_ID = nsIInputStream.LAST_METHOD_ID + 4;
        NS_IMIMEINPUTSTREAM_IID = new nsID("dcbce63c-1dd1-11b2-b94d-91f6d49a3161");
    }
    
    public nsIMIMEInputStream(final int n) {
        super(n);
    }
    
    public int GetAddContentLength(final int[] array) {
        return XPCOM.VtblCall(nsIInputStream.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int SetAddContentLength(final int n) {
        return XPCOM.VtblCall(nsIInputStream.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int AddHeader(final byte[] array, final byte[] array2) {
        return XPCOM.VtblCall(nsIInputStream.LAST_METHOD_ID + 3, this.getAddress(), array, array2);
    }
    
    public int SetData(final int n) {
        return XPCOM.VtblCall(nsIInputStream.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
}
