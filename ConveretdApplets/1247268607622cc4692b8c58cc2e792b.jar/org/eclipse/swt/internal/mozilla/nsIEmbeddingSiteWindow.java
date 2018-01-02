// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIEmbeddingSiteWindow extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IEMBEDDINGSITEWINDOW_IID_STR = "3e5432cd-9568-4bd1-8cbe-d50aba110743";
    public static final nsID NS_IEMBEDDINGSITEWINDOW_IID;
    public static final int DIM_FLAGS_POSITION = 1;
    public static final int DIM_FLAGS_SIZE_INNER = 2;
    public static final int DIM_FLAGS_SIZE_OUTER = 4;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 8;
        NS_IEMBEDDINGSITEWINDOW_IID = new nsID("3e5432cd-9568-4bd1-8cbe-d50aba110743");
    }
    
    public nsIEmbeddingSiteWindow(final int n) {
        super(n);
    }
    
    public int SetDimensions(final int n, final int n2, final int n3, final int n4, final int n5) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4, n5);
    }
    
    public int GetDimensions(final int n, final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, array, array2, array3, array4);
    }
    
    public int SetFocus() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
    
    public int GetVisibility(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int SetVisibility(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetTitle(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int SetTitle(final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetSiteWindow(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
}
