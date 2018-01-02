// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIBaseWindow extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IBASEWINDOW_IID_STR = "046bc8a0-8015-11d3-af70-00a024ffc08c";
    public static final nsID NS_IBASEWINDOW_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 24;
        NS_IBASEWINDOW_IID = new nsID("046bc8a0-8015-11d3-af70-00a024ffc08c");
    }
    
    public nsIBaseWindow(final int n) {
        super(n);
    }
    
    public int InitWindow(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, n3, n4, n5, n6);
    }
    
    public int Create() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress());
    }
    
    public int Destroy() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
    
    public int SetPosition(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2);
    }
    
    public int GetPosition(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array, array2);
    }
    
    public int SetSize(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n, n2, n3);
    }
    
    public int GetSize(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array, array2);
    }
    
    public int SetPositionAndSize(final int n, final int n2, final int n3, final int n4, final int n5) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n, n2, n3, n4, n5);
    }
    
    public int GetPositionAndSize(final int[] array, final int[] array2, final int[] array3, final int[] array4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array, array2, array3, array4);
    }
    
    public int Repaint(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n);
    }
    
    public int GetParentWidget(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), array);
    }
    
    public int SetParentWidget(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), n);
    }
    
    public int GetParentNativeWindow(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), array);
    }
    
    public int SetParentNativeWindow(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), n);
    }
    
    public int GetVisibility(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), array);
    }
    
    public int SetVisibility(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), n);
    }
    
    public int GetEnabled(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress(), array);
    }
    
    public int SetEnabled(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, this.getAddress(), n);
    }
    
    public int GetBlurSuppression(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, this.getAddress(), array);
    }
    
    public int SetBlurSuppression(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, this.getAddress(), n);
    }
    
    public int GetMainWidget(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 21, this.getAddress(), array);
    }
    
    public int SetFocus() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, this.getAddress());
    }
    
    public int GetTitle(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 23, this.getAddress(), array);
    }
    
    public int SetTitle(final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 24, this.getAddress(), array);
    }
}
