// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsICategoryManager extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICATEGORYMANAGER_IID_STR = "3275b2cd-af6d-429a-80d7-f0c5120342ac";
    public static final nsID NS_ICATEGORYMANAGER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 6;
        NS_ICATEGORYMANAGER_IID = new nsID("3275b2cd-af6d-429a-80d7-f0c5120342ac");
    }
    
    public nsICategoryManager(final int n) {
        super(n);
    }
    
    public int GetCategoryEntry(final byte[] array, final byte[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array, array2, array3);
    }
    
    public int AddCategoryEntry(final byte[] array, final byte[] array2, final byte[] array3, final int n, final int n2, final int[] array4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array, array2, array3, n, n2, array4);
    }
    
    public int DeleteCategoryEntry(final byte[] array, final byte[] array2, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), array, array2, n);
    }
    
    public int DeleteCategory(final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array);
    }
    
    public int EnumerateCategory(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), array, array2);
    }
    
    public int EnumerateCategories(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), array);
    }
}
