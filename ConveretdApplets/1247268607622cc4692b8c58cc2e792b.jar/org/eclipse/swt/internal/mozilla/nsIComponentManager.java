// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIComponentManager extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICOMPONENTMANAGER_IID_STR = "a88e5a60-205a-4bb1-94e1-2628daf51eae";
    public static final nsID NS_ICOMPONENTMANAGER_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_ICOMPONENTMANAGER_IID = new nsID("a88e5a60-205a-4bb1-94e1-2628daf51eae");
    }
    
    public nsIComponentManager(final int n) {
        super(n);
    }
    
    public int GetClassObject(final nsID nsID, final nsID nsID2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), nsID, nsID2, array);
    }
    
    public int GetClassObjectByContractID(final byte[] array, final nsID nsID, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), array, nsID, array2);
    }
    
    public int CreateInstance(final nsID nsID, final int n, final nsID nsID2, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), nsID, n, nsID2, array);
    }
    
    public int CreateInstanceByContractID(final byte[] array, final int n, final nsID nsID, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), array, n, nsID, array2);
    }
}
