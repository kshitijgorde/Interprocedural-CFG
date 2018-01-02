// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIProgressDialog extends nsIDownload
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPROGRESSDIALOG_IID_STR = "88a478b3-af65-440a-94dc-ed9b154d2990";
    public static final nsID NS_IPROGRESSDIALOG_IID;
    
    static {
        LAST_METHOD_ID = nsIDownload.LAST_METHOD_ID + 5;
        NS_IPROGRESSDIALOG_IID = new nsID("88a478b3-af65-440a-94dc-ed9b154d2990");
    }
    
    public nsIProgressDialog(final int n) {
        super(n);
    }
    
    public int Open(final int n) {
        return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int GetCancelDownloadOnClose(final int[] array) {
        return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int SetCancelDownloadOnClose(final int n) {
        return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetDialog(final int[] array) {
        return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int SetDialog(final int n) {
        return XPCOM.VtblCall(nsIDownload.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
}
