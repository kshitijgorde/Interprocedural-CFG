// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDownload_1_8 extends nsITransfer
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOWNLOAD_IID_STR = "9e1fd9f2-9727-4926-85cd-f16c375bba6d";
    public static final nsID NS_IDOWNLOAD_IID;
    
    static {
        LAST_METHOD_ID = nsITransfer.LAST_METHOD_ID + 10;
        NS_IDOWNLOAD_IID = new nsID("9e1fd9f2-9727-4926-85cd-f16c375bba6d");
    }
    
    public nsIDownload_1_8(final int n) {
        super(n);
    }
    
    public int GetTargetFile(final int[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetPercentComplete(final int[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetAmountTransferred(final int n) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetSize(final int n) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int GetSource(final int[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetTarget(final int[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int GetCancelable(final int[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetDisplayName(final int[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int GetStartTime(final long[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int GetMIMEInfo(final int[] array) {
        return XPCOM.VtblCall(nsITransfer.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
}
