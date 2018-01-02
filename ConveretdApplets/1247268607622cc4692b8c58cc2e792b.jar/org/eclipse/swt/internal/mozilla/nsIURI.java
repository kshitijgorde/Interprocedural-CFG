// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIURI extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IURI_IID_STR = "07a22cc0-0ce5-11d3-9331-00104ba0fd40";
    public static final nsID NS_IURI_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 26;
        NS_IURI_IID = new nsID("07a22cc0-0ce5-11d3-9331-00104ba0fd40");
    }
    
    public nsIURI(final int n) {
        super(n);
    }
    
    public int GetSpec(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int SetSpec(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetPrePath(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetScheme(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int SetScheme(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetUserPass(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int SetUserPass(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int GetUsername(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int SetUsername(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n);
    }
    
    public int GetPassword(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n);
    }
    
    public int SetPassword(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), n);
    }
    
    public int GetHostPort(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), n);
    }
    
    public int SetHostPort(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), n);
    }
    
    public int GetHost(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), n);
    }
    
    public int SetHost(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), n);
    }
    
    public int GetPort(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), array);
    }
    
    public int SetPort(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress(), n);
    }
    
    public int GetPath(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, this.getAddress(), n);
    }
    
    public int SetPath(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 19, this.getAddress(), n);
    }
    
    public int Equals(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 20, this.getAddress(), n, array);
    }
    
    public int SchemeIs(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 21, this.getAddress(), array, array2);
    }
    
    public int Clone(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 22, this.getAddress(), array);
    }
    
    public int Resolve(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 23, this.getAddress(), n, n2);
    }
    
    public int GetAsciiSpec(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 24, this.getAddress(), n);
    }
    
    public int GetAsciiHost(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 25, this.getAddress(), n);
    }
    
    public int GetOriginCharset(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 26, this.getAddress(), n);
    }
}
