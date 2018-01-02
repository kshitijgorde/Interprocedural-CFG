// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDirectoryServiceProvider2 extends nsIDirectoryServiceProvider
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDIRECTORYSERVICEPROVIDER2_IID_STRING = "2f977d4b-5485-11d4-87e2-0010a4e75ef2";
    public static final nsID NS_IDIRECTORYSERVICEPROVIDER2_IID;
    
    static {
        LAST_METHOD_ID = nsIDirectoryServiceProvider.LAST_METHOD_ID + 1;
        NS_IDIRECTORYSERVICEPROVIDER2_IID = new nsID("2f977d4b-5485-11d4-87e2-0010a4e75ef2");
    }
    
    public nsIDirectoryServiceProvider2(final int n) {
        super(n);
    }
    
    public int GetFiles(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsIDirectoryServiceProvider.LAST_METHOD_ID + 1, this.getAddress(), array, array2);
    }
}
