// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebBrowserFocus extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBBROWSERFOCUS_IID_STR = "9c5d3c58-1dd1-11b2-a1c9-f3699284657a";
    public static final nsID NS_IWEBBROWSERFOCUS_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 8;
        NS_IWEBBROWSERFOCUS_IID = new nsID("9c5d3c58-1dd1-11b2-a1c9-f3699284657a");
    }
    
    public nsIWebBrowserFocus(final int n) {
        super(n);
    }
    
    public int Activate() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress());
    }
    
    public int Deactivate() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress());
    }
    
    public int SetFocusAtFirstElement() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress());
    }
    
    public int SetFocusAtLastElement() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress());
    }
    
    public int GetFocusedWindow(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int SetFocusedWindow(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int GetFocusedElement(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int SetFocusedElement(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
}
