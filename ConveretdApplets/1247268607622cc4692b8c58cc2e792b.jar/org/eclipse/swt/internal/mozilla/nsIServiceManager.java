// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIServiceManager extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ISERVICEMANAGER_IID_STR = "8bb35ed9-e332-462d-9155-4a002ab5c958";
    public static final nsID NS_ISERVICEMANAGER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_ISERVICEMANAGER_IID = new nsID("8bb35ed9-e332-462d-9155-4a002ab5c958");
    }
    
    public nsIServiceManager(final int n) {
        super(n);
    }
    
    public int GetService(final nsID nsID, final nsID nsID2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), nsID, nsID2, array);
    }
    
    public int GetServiceByContractID(final byte[] array, final nsID nsID, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array, nsID, array2);
    }
    
    public int IsServiceInstantiated(final nsID nsID, final nsID nsID2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), nsID, nsID2, array);
    }
    
    public int IsServiceInstantiatedByContractID(final byte[] array, final nsID nsID, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array, nsID, array2);
    }
}
