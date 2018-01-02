// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsITooltipListener extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ITOOLTIPLISTENER_IID_STR = "44b78386-1dd2-11b2-9ad2-e4eee2ca1916";
    public static final nsID NS_ITOOLTIPLISTENER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 2;
        NS_ITOOLTIPLISTENER_IID = new nsID("44b78386-1dd2-11b2-9ad2-e4eee2ca1916");
    }
    
    public nsITooltipListener(final int n) {
        super(n);
    }
    
    public int OnShowTooltip(final int n, final int n2, final char[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, array);
    }
    
    public int OnHideTooltip() {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress());
    }
}
