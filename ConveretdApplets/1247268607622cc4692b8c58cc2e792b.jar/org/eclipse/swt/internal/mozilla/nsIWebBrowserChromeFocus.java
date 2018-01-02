// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIWebBrowserChromeFocus extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IWEBBROWSERCHROMEFOCUS_IID_STR = "d2206418-1dd1-11b2-8e55-acddcd2bcfb8";
    public static final nsID NS_IWEBBROWSERCHROMEFOCUS_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_IWEBBROWSERCHROMEFOCUS_IID = new nsID("d2206418-1dd1-11b2-8e55-acddcd2bcfb8");
    }
    
    public nsIWebBrowserChromeFocus(final int n) {
        super(n);
    }
    
    public int FocusNextElement() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress());
    }
    
    public int FocusPrevElement() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress());
    }
}
