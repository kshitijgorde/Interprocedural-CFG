// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebProgress extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBPROGRESS_IID_STR = "570f39d0-efd0-11d3-b093-00a024ffc08c";
    public static final nsID NS_IWEBPROGRESS_IID;
    public static final int NOTIFY_STATE_REQUEST = 1;
    public static final int NOTIFY_STATE_DOCUMENT = 2;
    public static final int NOTIFY_STATE_NETWORK = 4;
    public static final int NOTIFY_STATE_WINDOW = 8;
    public static final int NOTIFY_STATE_ALL = 15;
    public static final int NOTIFY_PROGRESS = 16;
    public static final int NOTIFY_STATUS = 32;
    public static final int NOTIFY_SECURITY = 64;
    public static final int NOTIFY_LOCATION = 128;
    public static final int NOTIFY_ALL = 255;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_IWEBPROGRESS_IID = new nsID("570f39d0-efd0-11d3-b093-00a024ffc08c");
    }
    
    public nsIWebProgress(final int n) {
        super(n);
    }
    
    public int AddProgressListener(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2);
    }
    
    public int RemoveProgressListener(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetDOMWindow(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetIsLoadingDocument(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
}
