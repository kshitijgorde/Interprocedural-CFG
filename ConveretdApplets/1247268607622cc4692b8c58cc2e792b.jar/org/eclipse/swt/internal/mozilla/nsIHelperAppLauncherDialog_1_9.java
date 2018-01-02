// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIHelperAppLauncherDialog_1_9 extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IHELPERAPPLAUNCHERDIALOG_IID_STR = "f3704fdc-8ae6-4eba-a3c3-f02958ac0649";
    public static final nsID NS_IHELPERAPPLAUNCHERDIALOG_IID;
    public static final int REASON_CANTHANDLE = 0;
    public static final int REASON_SERVERREQUEST = 1;
    public static final int REASON_TYPESNIFFED = 2;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_IHELPERAPPLAUNCHERDIALOG_IID = new nsID("f3704fdc-8ae6-4eba-a3c3-f02958ac0649");
    }
    
    public nsIHelperAppLauncherDialog_1_9(final int n) {
        super(n);
    }
    
    public int Show(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3);
    }
    
    public int PromptForSaveToFile(final int n, final int n2, final char[] array, final char[] array2, final int n3, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, n2, array, array2, n3, array3);
    }
}
