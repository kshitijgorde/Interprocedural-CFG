// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsISSLStatus extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ISSLSTATUS_IID_STR = "cfede939-def1-49be-81ed-d401b3a07d1c";
    public static final nsID NS_ISSLSTATUS_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 7;
        NS_ISSLSTATUS_IID = new nsID("cfede939-def1-49be-81ed-d401b3a07d1c");
    }
    
    public nsISSLStatus(final int n) {
        super(n);
    }
    
    public int GetServerCert(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetCipherName(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array);
    }
    
    public int GetKeyLength(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetSecretKeyLength(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int GetIsDomainMismatch(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int GetIsNotValidAtThisTime(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
    
    public int GetIsUntrusted(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
}
