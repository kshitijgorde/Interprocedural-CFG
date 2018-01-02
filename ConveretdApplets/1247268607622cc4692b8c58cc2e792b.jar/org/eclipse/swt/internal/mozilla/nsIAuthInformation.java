// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIAuthInformation extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IAUTHINFORMATION_IID_STR = "0d73639c-2a92-4518-9f92-28f71fea5f20";
    public static final nsID NS_IAUTHINFORMATION_IID;
    public static final int AUTH_HOST = 1;
    public static final int AUTH_PROXY = 2;
    public static final int NEED_DOMAIN = 4;
    public static final int ONLY_PASSWORD = 8;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 9;
        NS_IAUTHINFORMATION_IID = new nsID("0d73639c-2a92-4518-9f92-28f71fea5f20");
    }
    
    public nsIAuthInformation(final int n) {
        super(n);
    }
    
    public int GetFlags(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetRealm(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetAuthenticationScheme(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetUsername(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int SetUsername(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetPassword(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int SetPassword(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int GetDomain(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int SetDomain(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
}
