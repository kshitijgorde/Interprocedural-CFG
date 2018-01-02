// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIX509CertValidity extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IX509CERTVALIDITY_IID_STR = "e701dfd8-1dd1-11b2-a172-ffa6cc6156ad";
    public static final nsID NS_IX509CERTVALIDITY_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 8;
        NS_IX509CERTVALIDITY_IID = new nsID("e701dfd8-1dd1-11b2-a172-ffa6cc6156ad");
    }
    
    public nsIX509CertValidity(final int n) {
        super(n);
    }
    
    public int GetNotBefore(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int GetNotBeforeLocalTime(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int GetNotBeforeLocalDay(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n);
    }
    
    public int GetNotBeforeGMT(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n);
    }
    
    public int GetNotAfter(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n);
    }
    
    public int GetNotAfterLocalTime(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n);
    }
    
    public int GetNotAfterLocalDay(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), n);
    }
    
    public int GetNotAfterGMT(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
}
