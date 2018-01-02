// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIProgressDialog_1_8 extends nsIDownload_1_8
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPROGRESSDIALOG_IID_STR = "20e790a2-76c6-462d-851a-22ab6cbbe48b";
    public static final nsID NS_IPROGRESSDIALOG_IID;
    
    static {
        LAST_METHOD_ID = nsIDownload_1_8.LAST_METHOD_ID + 7;
        NS_IPROGRESSDIALOG_IID = new nsID("20e790a2-76c6-462d-851a-22ab6cbbe48b");
    }
    
    public nsIProgressDialog_1_8(final int n) {
        super(n);
    }
    
    public int Open(final int n) {
        return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int GetCancelDownloadOnClose(final int[] array) {
        return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int SetCancelDownloadOnClose(final int n) {
        return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetObserver(final int[] array) {
        return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int SetObserver(final int n) {
        return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetDialog(final int[] array) {
        return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int SetDialog(final int n) {
        return XPCOM.VtblCall(nsIDownload_1_8.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
}
