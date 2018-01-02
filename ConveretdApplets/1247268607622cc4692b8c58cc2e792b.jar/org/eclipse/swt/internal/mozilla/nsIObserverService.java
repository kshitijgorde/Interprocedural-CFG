// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIObserverService extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IOBSERVERSERVICE_IID_STR = "d07f5192-e3d1-11d2-8acd-00105a1b8860";
    public static final nsID NS_IOBSERVERSERVICE_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_IOBSERVERSERVICE_IID = new nsID("d07f5192-e3d1-11d2-8acd-00105a1b8860");
    }
    
    public nsIObserverService(final int n) {
        super(n);
    }
    
    public int AddObserver(final int n, final byte[] array, final int n2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, array, n2);
    }
    
    public int RemoveObserver(final int n, final byte[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, array);
    }
    
    public int NotifyObservers(final int n, final byte[] array, final char[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, array, array2);
    }
    
    public int EnumerateObservers(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array, array2);
    }
}
