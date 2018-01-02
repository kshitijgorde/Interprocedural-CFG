// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIIOService extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IIOSERVICE_IID_STR = "bddeda3f-9020-4d12-8c70-984ee9f7935e";
    public static final nsID NS_IIOSERVICE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 10;
        NS_IIOSERVICE_IID = new nsID("bddeda3f-9020-4d12-8c70-984ee9f7935e");
    }
    
    public nsIIOService(final int n) {
        super(n);
    }
    
    public int GetProtocolHandler(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), array, array2);
    }
    
    public int GetProtocolFlags(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array, array2);
    }
    
    public int NewURI(final int n, final byte[] array, final int n2, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, array, n2, array2);
    }
    
    public int NewFileURI(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, array);
    }
    
    public int NewChannelFromURI(final int n, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), n, array);
    }
    
    public int NewChannel(final int n, final byte[] array, final int n2, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), n, array, n2, array2);
    }
    
    public int GetOffline(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), array);
    }
    
    public int SetOffline(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), n);
    }
    
    public int AllowPort(final int n, final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), n, array, array2);
    }
    
    public int ExtractScheme(final int n, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), n, n2);
    }
}
