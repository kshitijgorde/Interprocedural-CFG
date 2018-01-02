// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIDirectoryServiceProvider extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IDIRECTORYSERVICEPROVIDER_IID_STR = "bbf8cab0-d43a-11d3-8cc2-00609792278c";
    public static final nsID NS_IDIRECTORYSERVICEPROVIDER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 1;
        NS_IDIRECTORYSERVICEPROVIDER_IID = new nsID("bbf8cab0-d43a-11d3-8cc2-00609792278c");
    }
    
    public nsIDirectoryServiceProvider(final int n) {
        super(n);
    }
    
    public int GetFile(final byte[] array, final int[] array2, final int[] array3) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array, array2, array3);
    }
}
