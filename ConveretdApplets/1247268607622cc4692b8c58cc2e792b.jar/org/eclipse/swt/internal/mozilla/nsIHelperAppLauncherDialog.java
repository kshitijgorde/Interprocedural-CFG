// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIHelperAppLauncherDialog extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IHELPERAPPLAUNCHERDIALOG_IID_STR = "d7ebddf0-4c84-11d4-807a-00600811a9c3";
    public static final nsID NS_IHELPERAPPLAUNCHERDIALOG_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 3;
        NS_IHELPERAPPLAUNCHERDIALOG_IID = new nsID("d7ebddf0-4c84-11d4-807a-00600811a9c3");
    }
    
    public nsIHelperAppLauncherDialog(final int n) {
        super(n);
    }
    
    public int Show(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2);
    }
    
    public int PromptForSaveToFile(final int n, final char[] array, final char[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, array, array2, array3);
    }
    
    public int ShowProgressDialog(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, n2);
    }
}
