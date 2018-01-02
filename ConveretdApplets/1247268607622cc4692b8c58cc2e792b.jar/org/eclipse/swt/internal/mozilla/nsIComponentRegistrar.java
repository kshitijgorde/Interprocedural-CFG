// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIComponentRegistrar extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_ICOMPONENTREGISTRAR_IID_STR = "2417cbfe-65ad-48a6-b4b6-eb84db174392";
    public static final nsID NS_ICOMPONENTREGISTRAR_IID;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 12;
        NS_ICOMPONENTREGISTRAR_IID = new nsID("2417cbfe-65ad-48a6-b4b6-eb84db174392");
    }
    
    public nsIComponentRegistrar(final int n) {
        super(n);
    }
    
    public int AutoRegister(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n);
    }
    
    public int AutoUnregister(final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n);
    }
    
    public int RegisterFactory(final nsID nsID, final byte[] array, final byte[] array2, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), nsID, array, array2, n);
    }
    
    public int UnregisterFactory(final nsID nsID, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), nsID, n);
    }
    
    public int RegisterFactoryLocation(final nsID nsID, final byte[] array, final byte[] array2, final int n, final byte[] array3, final byte[] array4) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 5, this.getAddress(), nsID, array, array2, n, array3, array4);
    }
    
    public int UnregisterFactoryLocation(final nsID nsID, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 6, this.getAddress(), nsID, n);
    }
    
    public int IsCIDRegistered(final nsID nsID, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 7, this.getAddress(), nsID, array);
    }
    
    public int IsContractIDRegistered(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 8, this.getAddress(), array, array2);
    }
    
    public int EnumerateCIDs(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 9, this.getAddress(), array);
    }
    
    public int EnumerateContractIDs(final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int CIDToContractID(final nsID nsID, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 11, this.getAddress(), nsID, array);
    }
    
    public int ContractIDToCID(final byte[] array, final int n) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 12, this.getAddress(), array, n);
    }
}
