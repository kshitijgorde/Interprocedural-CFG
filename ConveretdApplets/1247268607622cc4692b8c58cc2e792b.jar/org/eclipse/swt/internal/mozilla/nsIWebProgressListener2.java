// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebProgressListener2 extends nsIWebProgressListener
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBPROGRESSLISTENER2_IID_STR = "3f24610d-1e1f-4151-9d2e-239884742324";
    public static final nsID NS_IWEBPROGRESSLISTENER2_IID;
    
    static {
        LAST_METHOD_ID = nsIWebProgressListener.LAST_METHOD_ID + 1;
        NS_IWEBPROGRESSLISTENER2_IID = new nsID("3f24610d-1e1f-4151-9d2e-239884742324");
    }
    
    public nsIWebProgressListener2(final int n) {
        super(n);
    }
    
    public int OnProgressChange64(final int n, final int n2, final long n3, final long n4, final long n5, final long n6) {
        return XPCOM.VtblCall(nsIWebProgressListener.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4, n5, n6);
    }
}
