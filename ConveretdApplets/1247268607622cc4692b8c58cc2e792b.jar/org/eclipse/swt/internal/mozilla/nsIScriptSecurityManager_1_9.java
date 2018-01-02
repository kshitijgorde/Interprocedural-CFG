// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.mozilla;

public class nsIScriptSecurityManager_1_9 extends nsIXPCSecurityManager
{
    static final int LAST_METHOD_ID;
    public static final String NS_ISCRIPTSECURITYMANAGER_IID_STR = "3fffd8e8-3fea-442e-a0ed-2ba81ae197d5";
    public static final nsID NS_ISCRIPTSECURITYMANAGER_IID;
    public static final int STANDARD = 0;
    public static final int LOAD_IS_AUTOMATIC_DOCUMENT_REPLACEMENT = 1;
    public static final int ALLOW_CHROME = 2;
    public static final int DISALLOW_INHERIT_PRINCIPAL = 4;
    public static final int DISALLOW_SCRIPT_OR_DATA = 4;
    public static final int DISALLOW_SCRIPT = 8;
    
    static {
        LAST_METHOD_ID = nsIXPCSecurityManager.LAST_METHOD_ID + 26;
        NS_ISCRIPTSECURITYMANAGER_IID = new nsID("3fffd8e8-3fea-442e-a0ed-2ba81ae197d5");
    }
    
    public nsIScriptSecurityManager_1_9(final int n) {
        super(n);
    }
    
    public int CheckLoadURIFromScript(final int n, final int n2) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 3, this.getAddress(), n, n2);
    }
    
    public int CheckLoadURIWithPrincipal(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 4, this.getAddress(), n, n2, n3);
    }
    
    public int CheckLoadURI(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 5, this.getAddress(), n, n2, n3);
    }
    
    public int CheckLoadURIStrWithPrincipal(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 6, this.getAddress(), n, n2, n3);
    }
    
    public int CheckLoadURIStr(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 7, this.getAddress(), n, n2, n3);
    }
    
    public int CheckFunctionAccess(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 8, this.getAddress(), n, n2, n3);
    }
    
    public int CanExecuteScripts(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 9, this.getAddress(), n, n2, array);
    }
    
    public int GetSubjectPrincipal(final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 10, this.getAddress(), array);
    }
    
    public int GetSystemPrincipal(final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 11, this.getAddress(), array);
    }
    
    public int GetCodebasePrincipal(final int n, final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 13, this.getAddress(), n, array);
    }
    
    public int IsCapabilityEnabled(final byte[] array, final int[] array2) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 15, this.getAddress(), array, array2);
    }
    
    public int EnableCapability(final byte[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 16, this.getAddress(), array);
    }
    
    public int RevertCapability(final byte[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 17, this.getAddress(), array);
    }
    
    public int DisableCapability(final byte[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 18, this.getAddress(), array);
    }
    
    public int GetObjectPrincipal(final int n, final int n2, final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 20, this.getAddress(), n, n2, array);
    }
    
    public int SubjectPrincipalIsSystem(final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 21, this.getAddress(), array);
    }
    
    public int CheckSameOrigin(final int n, final int n2) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 22, this.getAddress(), n, n2);
    }
    
    public int CheckSameOriginURI(final int n, final int n2, final int n3) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 23, this.getAddress(), n, n2, n3);
    }
    
    public int GetPrincipalFromContext(final int n, final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 24, this.getAddress(), n, array);
    }
    
    public int GetChannelPrincipal(final int n, final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 25, this.getAddress(), n, array);
    }
    
    public int IsSystemPrincipal(final int n, final int[] array) {
        return XPCOM.VtblCall(nsIXPCSecurityManager.LAST_METHOD_ID + 26, this.getAddress(), n, array);
    }
}
