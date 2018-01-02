// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebProgressListener extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBPROGRESSLISTENER_IID_STR = "570f39d1-efd0-11d3-b093-00a024ffc08c";
    public static final nsID NS_IWEBPROGRESSLISTENER_IID;
    public static final int STATE_START = 1;
    public static final int STATE_REDIRECTING = 2;
    public static final int STATE_TRANSFERRING = 4;
    public static final int STATE_NEGOTIATING = 8;
    public static final int STATE_STOP = 16;
    public static final int STATE_IS_REQUEST = 65536;
    public static final int STATE_IS_DOCUMENT = 131072;
    public static final int STATE_IS_NETWORK = 262144;
    public static final int STATE_IS_WINDOW = 524288;
    public static final int STATE_IS_INSECURE = 4;
    public static final int STATE_IS_BROKEN = 1;
    public static final int STATE_IS_SECURE = 2;
    public static final int STATE_SECURE_HIGH = 262144;
    public static final int STATE_SECURE_MED = 65536;
    public static final int STATE_SECURE_LOW = 131072;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 5;
        NS_IWEBPROGRESSLISTENER_IID = new nsID("570f39d1-efd0-11d3-b093-00a024ffc08c");
    }
    
    public nsIWebProgressListener(final int n) {
        super(n);
    }
    
    public int OnStateChange(final int n, final int n2, final int n3, final int n4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4);
    }
    
    public int OnProgressChange(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, n3, n4, n5, n6);
    }
    
    public int OnLocationChange(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, n2, n3);
    }
    
    public int OnStatusChange(final int n, final int n2, final int n3, final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2, n3, array);
    }
    
    public int OnSecurityChange(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n, n2, n3);
    }
}
