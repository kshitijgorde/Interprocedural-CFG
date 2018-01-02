// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsICertificateDialogs extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICERTIFICATEDIALOGS_IID_STR = "a03ca940-09be-11d5-ac5d-000064657374";
    public static final nsID NS_ICERTIFICATEDIALOGS_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 6;
        NS_ICERTIFICATEDIALOGS_IID = new nsID("a03ca940-09be-11d5-ac5d-000064657374");
    }
    
    public nsICertificateDialogs(final int n) {
        super(n);
    }
    
    public int ConfirmDownloadCACert(final int n, final int n2, final int[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, n2, array, array2);
    }
    
    public int NotifyCACertExists(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int SetPKCS12FilePassword(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, n2, array);
    }
    
    public int GetPKCS12FilePassword(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2, array);
    }
    
    public int ViewCert(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n, n2);
    }
    
    public int CrlImportStatusDialog(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n, n2);
    }
}
