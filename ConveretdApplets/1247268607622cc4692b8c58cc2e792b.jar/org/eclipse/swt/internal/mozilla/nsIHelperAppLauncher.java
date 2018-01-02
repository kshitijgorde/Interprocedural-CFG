// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIHelperAppLauncher extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IHELPERAPPLAUNCHER_IID_STR = "9503d0fe-4c9d-11d4-98d0-001083010e9b";
    public static final nsID NS_IHELPERAPPLAUNCHER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 9;
        NS_IHELPERAPPLAUNCHER_IID = new nsID("9503d0fe-4c9d-11d4-98d0-001083010e9b");
    }
    
    public nsIHelperAppLauncher(final int n) {
        super(n);
    }
    
    public int GetMIMEInfo(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetSource(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetSuggestedFileName(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int SaveToDisk(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2);
    }
    
    public int LaunchWithApplication(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n, n2);
    }
    
    public int Cancel() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress());
    }
    
    public int SetWebProgressListener(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int CloseProgressWindow() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress());
    }
    
    public int GetDownloadInfo(final int[] array, final long[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array, array2, array3);
    }
}
