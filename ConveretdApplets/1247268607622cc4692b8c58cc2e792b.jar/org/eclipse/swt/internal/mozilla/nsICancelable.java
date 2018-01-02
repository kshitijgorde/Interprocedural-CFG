// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsICancelable extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICANCELABLE_IID_STR = "d94ac0a0-bb18-46b8-844e-84159064b0bd";
    public static final nsID NS_ICANCELABLE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_ICANCELABLE_IID = new nsID("d94ac0a0-bb18-46b8-844e-84159064b0bd");
    }
    
    public nsICancelable(final int n) {
        super(n);
    }
    
    public int Cancel(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
}
