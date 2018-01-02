// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIPrefBranch extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IPREFBRANCH_IID_STR = "56c35506-f14b-11d3-99d3-ddbfac2ccf65";
    public static final nsID NS_IPREFBRANCH_IID;
    public static final int PREF_INVALID = 0;
    public static final int PREF_STRING = 32;
    public static final int PREF_INT = 64;
    public static final int PREF_BOOL = 128;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 18;
        NS_IPREFBRANCH_IID = new nsID("56c35506-f14b-11d3-99d3-ddbfac2ccf65");
    }
    
    public nsIPrefBranch(final int n) {
        super(n);
    }
    
    public int GetRoot(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array);
    }
    
    public int GetPrefType(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array, array2);
    }
    
    public int GetBoolPref(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array, array2);
    }
    
    public int SetBoolPref(final byte[] array, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array, n);
    }
    
    public int GetCharPref(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array, array2);
    }
    
    public int SetCharPref(final byte[] array, final byte[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array, array2);
    }
    
    public int GetIntPref(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array, array2);
    }
    
    public int SetIntPref(final byte[] array, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array, n);
    }
    
    public int GetComplexValue(final byte[] array, final nsID nsID, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array, nsID, array2);
    }
    
    public int SetComplexValue(final byte[] array, final nsID nsID, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array, nsID, n);
    }
    
    public int ClearUserPref(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), array);
    }
    
    public int LockPref(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), array);
    }
    
    public int PrefHasUserValue(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 13, this.getAddress(), array, array2);
    }
    
    public int PrefIsLocked(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 14, this.getAddress(), array, array2);
    }
    
    public int UnlockPref(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 15, this.getAddress(), array);
    }
    
    public int DeleteBranch(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 16, this.getAddress(), array);
    }
    
    public int GetChildList(final byte[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 17, this.getAddress(), array, array2, array3);
    }
    
    public int ResetBranch(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 18, this.getAddress(), array);
    }
}
