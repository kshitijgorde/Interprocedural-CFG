// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIXPCSecurityManager extends nsISupports
{
    static final int LAST_METHOD_ID;
    public static final String NS_IXPCSECURITYMANAGER_IID_STR = "31431440-f1ce-11d2-985a-006008962422";
    public static final nsID NS_IXPCSECURITYMANAGER_IID;
    public static final int HOOK_CREATE_WRAPPER = 1;
    public static final int HOOK_CREATE_INSTANCE = 2;
    public static final int HOOK_GET_SERVICE = 4;
    public static final int HOOK_CALL_METHOD = 8;
    public static final int HOOK_GET_PROPERTY = 16;
    public static final int HOOK_SET_PROPERTY = 32;
    public static final int HOOK_ALL = 63;
    public static final int ACCESS_CALL_METHOD = 0;
    public static final int ACCESS_GET_PROPERTY = 1;
    public static final int ACCESS_SET_PROPERTY = 2;
    
    static {
        LAST_METHOD_ID = nsISupports.LAST_METHOD_ID + 4;
        NS_IXPCSECURITYMANAGER_IID = new nsID("31431440-f1ce-11d2-985a-006008962422");
    }
    
    public nsIXPCSecurityManager(final int n) {
        super(n);
    }
    
    public int CanCreateWrapper(final int n, final nsID nsID, final int n2, final int n3, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 1, this.getAddress(), n, nsID, n2, n3, array);
    }
    
    public int CanCreateInstance(final int n, final nsID nsID) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 2, this.getAddress(), n, nsID);
    }
    
    public int CanGetService(final int n, final nsID nsID) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 3, this.getAddress(), n, nsID);
    }
    
    public int CanAccess(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int[] array) {
        return XPCOM.VtblCall(nsISupports.LAST_METHOD_ID + 4, this.getAddress(), n, n2, n3, n4, n5, n6, n7, array);
    }
}
