// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIX509Cert extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IX509CERT_IID_STR = "f0980f60-ee3d-11d4-998b-00b0d02354a0";
    public static final nsID NS_IX509CERT_IID;
    public static final int UNKNOWN_CERT = 0;
    public static final int CA_CERT = 1;
    public static final int USER_CERT = 2;
    public static final int EMAIL_CERT = 4;
    public static final int SERVER_CERT = 8;
    public static final int VERIFIED_OK = 0;
    public static final int NOT_VERIFIED_UNKNOWN = 1;
    public static final int CERT_REVOKED = 2;
    public static final int CERT_EXPIRED = 4;
    public static final int CERT_NOT_TRUSTED = 8;
    public static final int ISSUER_NOT_TRUSTED = 16;
    public static final int ISSUER_UNKNOWN = 32;
    public static final int INVALID_CA = 64;
    public static final int USAGE_NOT_ALLOWED = 128;
    public static final int CERT_USAGE_SSLClient = 0;
    public static final int CERT_USAGE_SSLServer = 1;
    public static final int CERT_USAGE_SSLServerWithStepUp = 2;
    public static final int CERT_USAGE_SSLCA = 3;
    public static final int CERT_USAGE_EmailSigner = 4;
    public static final int CERT_USAGE_EmailRecipient = 5;
    public static final int CERT_USAGE_ObjectSigner = 6;
    public static final int CERT_USAGE_UserCertImport = 7;
    public static final int CERT_USAGE_VerifyCA = 8;
    public static final int CERT_USAGE_ProtectedObjectSigner = 9;
    public static final int CERT_USAGE_StatusResponder = 10;
    public static final int CERT_USAGE_AnyCA = 11;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 27;
        NS_IX509CERT_IID = new nsID("f0980f60-ee3d-11d4-998b-00b0d02354a0");
    }
    
    public nsIX509Cert(final int n) {
        super(n);
    }
    
    public int GetNickname(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int GetEmailAddress(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetEmailAddresses(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array, array2);
    }
    
    public int ContainsEmailAddress(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, array);
    }
    
    public int GetSubjectName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetCommonName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int GetOrganization(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int GetOrganizationalUnit(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int GetSha1Fingerprint(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
    
    public int GetMd5Fingerprint(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n);
    }
    
    public int GetTokenName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int GetIssuerName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), n);
    }
    
    public int GetSerialNumber(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), n);
    }
    
    public int GetIssuerCommonName(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), n);
    }
    
    public int GetIssuerOrganization(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
    
    public int GetIssuerOrganizationUnit(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), n);
    }
    
    public int GetIssuer(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress(), array);
    }
    
    public int GetValidity(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, this.getAddress(), array);
    }
    
    public int GetDbKey(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, this.getAddress(), array);
    }
    
    public int GetWindowTitle(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, this.getAddress(), array);
    }
    
    public int GetChain(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 21, this.getAddress(), array);
    }
    
    public int GetUsagesArray(final int n, final int[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, this.getAddress(), n, array, array2, array3);
    }
    
    public int GetUsagesString(final int n, final int[] array, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 23, this.getAddress(), n, array, n2);
    }
    
    public int VerifyForUsage(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 24, this.getAddress(), n, array);
    }
    
    public int GetASN1Structure(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 25, this.getAddress(), array);
    }
    
    public int GetRawDER(final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, this.getAddress(), array, array2);
    }
    
    public int Equals(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 27, this.getAddress(), n, array);
    }
}
