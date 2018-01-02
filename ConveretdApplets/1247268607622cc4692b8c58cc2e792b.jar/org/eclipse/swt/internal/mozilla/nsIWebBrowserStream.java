// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebBrowserStream extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBBROWSERSTREAM_IID_STR = "86d02f0e-219b-4cfc-9c88-bd98d2cce0b8";
    public static final nsID NS_IWEBBROWSERSTREAM_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 3;
        NS_IWEBBROWSERSTREAM_IID = new nsID("86d02f0e-219b-4cfc-9c88-bd98d2cce0b8");
    }
    
    public nsIWebBrowserStream(final int n) {
        super(n);
    }
    
    public int OpenStream(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2);
    }
    
    public int AppendToStream(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2);
    }
    
    public int CloseStream() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
}
