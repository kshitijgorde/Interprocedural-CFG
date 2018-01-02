// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIHelperAppLauncher_1_8 extends nsICancelable
{
    static final int LAST_METHOD_ID;
    public static final String NS_IHELPERAPPLAUNCHER_IID_STR = "99a0882d-2ff9-4659-9952-9ac531ba5592";
    public static final nsID NS_IHELPERAPPLAUNCHER_IID;
    
    static {
        LAST_METHOD_ID = nsICancelable.LAST_METHOD_ID + 9;
        NS_IHELPERAPPLAUNCHER_IID = new nsID("99a0882d-2ff9-4659-9952-9ac531ba5592");
    }
    
    public nsIHelperAppLauncher_1_8(final int n) {
        super(n);
    }
    
    public int GetMIMEInfo(final int[] array) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetSource(final int[] array) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetSuggestedFileName(final int n) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int SaveToDisk(final int n, final int n2) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 4, this.getAddress(), n, n2);
    }
    
    public int LaunchWithApplication(final int n, final int n2) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 5, this.getAddress(), n, n2);
    }
    
    public int SetWebProgressListener(final int n) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int CloseProgressWindow() {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 7, this.getAddress());
    }
    
    public int GetTargetFile(final int[] array) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetTimeDownloadStarted(final int n) {
        return XPCOM.VtblCall(nsICancelable.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
}
