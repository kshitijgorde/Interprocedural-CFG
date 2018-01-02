// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsICookie extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICOOKIE_IID_STR = "e9fcb9a4-d376-458f-b720-e65e7df593bc";
    public static final nsID NS_ICOOKIE_IID;
    public static final int STATUS_UNKNOWN = 0;
    public static final int STATUS_ACCEPTED = 1;
    public static final int STATUS_DOWNGRADED = 2;
    public static final int STATUS_FLAGGED = 3;
    public static final int STATUS_REJECTED = 4;
    public static final int POLICY_UNKNOWN = 0;
    public static final int POLICY_NONE = 1;
    public static final int POLICY_NO_CONSENT = 2;
    public static final int POLICY_IMPLICIT_CONSENT = 3;
    public static final int POLICY_EXPLICIT_CONSENT = 4;
    public static final int POLICY_NO_II = 5;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 9;
        NS_ICOOKIE_IID = new nsID("e9fcb9a4-d376-458f-b720-e65e7df593bc");
    }
    
    public nsICookie(final int n) {
        super(n);
    }
    
    public int GetName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int GetValue(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetIsDomain(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetHost(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int GetPath(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetIsSecure(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int GetExpires(final long[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int GetStatus(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int GetPolicy(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
}
