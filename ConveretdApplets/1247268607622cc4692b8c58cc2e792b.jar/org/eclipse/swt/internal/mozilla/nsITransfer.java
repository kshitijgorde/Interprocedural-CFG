// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsITransfer extends nsIWebProgressListener2
{
    static final int LAST_METHOD_ID;
    public static final String NS_ITRANSFER_IID_STR = "23c51569-e9a1-4a92-adeb-3723db82ef7c";
    public static final nsID NS_ITRANSFER_IID;
    
    static {
        LAST_METHOD_ID = nsIWebProgressListener2.LAST_METHOD_ID + 1;
        NS_ITRANSFER_IID = new nsID("23c51569-e9a1-4a92-adeb-3723db82ef7c");
    }
    
    public nsITransfer(final int n) {
        super(n);
    }
    
    public int Init(final int n, final int n2, final int n3, final int n4, final long n5, final int n6, final int n7) {
        return XPCOM.VtblCall(nsIWebProgressListener2.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4, n5, n6, n7);
    }
}
