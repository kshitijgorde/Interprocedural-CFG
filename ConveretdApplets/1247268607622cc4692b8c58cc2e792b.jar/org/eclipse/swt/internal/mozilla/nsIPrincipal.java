// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIPrincipal extends nsISerializable
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPRINCIPAL_IID_STR = "b8268b9a-2403-44ed-81e3-614075c92034";
    public static final nsID NS_IPRINCIPAL_IID;
    public static final int ENABLE_DENIED = 1;
    public static final int ENABLE_UNKNOWN = 2;
    public static final int ENABLE_WITH_USER_PERMISSION = 3;
    public static final int ENABLE_GRANTED = 4;
    
    static {
        LAST_METHOD_ID = nsISerializable.LAST_METHOD_ID + 23;
        NS_IPRINCIPAL_IID = new nsID("b8268b9a-2403-44ed-81e3-614075c92034");
    }
    
    public nsIPrincipal(final int n) {
        super(n);
    }
    
    public int GetPreferences(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 1, this.getAddress(), array, array2, array3, array4, array5, array6);
    }
    
    public int Equals(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 2, this.getAddress(), n, array);
    }
    
    public int GetHashValue(final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 3, this.getAddress(), array);
    }
    
    public int GetJSPrincipals(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 4, this.getAddress(), n, array);
    }
    
    public int GetSecurityPolicy(final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 5, this.getAddress(), array);
    }
    
    public int SetSecurityPolicy(final int n) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int CanEnableCapability(final byte[] array, final int n) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 7, this.getAddress(), array, n);
    }
    
    public int SetCanEnableCapability(final byte[] array, final short n) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 8, this.getAddress(), array, n);
    }
    
    public int IsCapabilityEnabled(final byte[] array, final int n, final int[] array2) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 9, this.getAddress(), array, n, array2);
    }
    
    public int EnableCapability(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 10, this.getAddress(), array, array2);
    }
    
    public int RevertCapability(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 11, this.getAddress(), array, array2);
    }
    
    public int DisableCapability(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 12, this.getAddress(), array, array2);
    }
    
    public int GetURI(final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 13, this.getAddress(), array);
    }
    
    public int GetDomain(final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 14, this.getAddress(), array);
    }
    
    public int SetDomain(final int n) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
    
    public int GetOrigin(final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 16, this.getAddress(), array);
    }
    
    public int GetHasCertificate(final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 17, this.getAddress(), array);
    }
    
    public int GetFingerprint(final int n) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 18, this.getAddress(), n);
    }
    
    public int GetPrettyName(final int n) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 19, this.getAddress(), n);
    }
    
    public int Subsumes(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 20, this.getAddress(), n, array);
    }
    
    public int CheckMayLoad(final int n, final int n2) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 21, this.getAddress(), n, n2);
    }
    
    public int GetSubjectName(final int n) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 22, this.getAddress(), n);
    }
    
    public int GetCertificate(final int[] array) {
        return XPCOM.VtblCall(nsISerializable.LAST_METHOD_ID + 23, this.getAddress(), array);
    }
}
