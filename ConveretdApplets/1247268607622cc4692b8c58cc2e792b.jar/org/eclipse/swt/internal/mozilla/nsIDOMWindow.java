// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDOMWindow extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDOMWINDOW_IID_STR = "a6cf906b-15b3-11d2-932e-00805f8add32";
    public static final nsID NS_IDOMWINDOW_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 17;
        NS_IDOMWINDOW_IID = new nsID("a6cf906b-15b3-11d2-932e-00805f8add32");
    }
    
    public nsIDOMWindow(final int n) {
        super(n);
    }
    
    public int GetDocument(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetParent(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetTop(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetScrollbars(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetFrames(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int SetName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int GetTextZoom(final float[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array);
    }
    
    public int SetTextZoom(final float n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
    
    public int GetScrollX(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int GetScrollY(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), array);
    }
    
    public int ScrollTo(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), n, n2);
    }
    
    public int ScrollBy(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), n, n2);
    }
    
    public int GetSelection(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), array);
    }
    
    public int ScrollByLines(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
    
    public int ScrollByPages(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), n);
    }
    
    public int SizeToContent() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress());
    }
}
