// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

public final class PrivilegeManager
{
    private static PrivilegeManager mgr;
    public static final int NO_SUBSET = 1;
    public static final int EQUAL = 0;
    public static final int PROPER_SUBSET = -1;
    
    public boolean checkMatchPrincipal(final Principal principal, final int n) {
        return true;
    }
    
    public void checkPrivilegeEnabled(final Target target) {
    }
    
    public static void revertPrivilege(final String s) {
    }
    
    public static Principal[] getMyPrincipals() {
        return createPrincipalArray();
    }
    
    public boolean isCalledByPrincipal(final Principal principal, final int n) {
        return true;
    }
    
    public void disablePrivilege(final Target target) {
    }
    
    static {
        PrivilegeManager.mgr = null;
    }
    
    public boolean checkMatchPrincipalAlways() {
        return true;
    }
    
    public void revertPrivilege(final Target target) {
    }
    
    public Principal[] getClassPrincipals(final Class clazz) {
        return createPrincipalArray();
    }
    
    public boolean checkMatchPrincipal(final Class clazz) {
        return true;
    }
    
    public void checkPrivilegeGranted(final Target target, final Principal principal, final Object o) {
    }
    
    public void checkPrivilegeGranted(final Target target, final Object o) {
    }
    
    public static void checkPrivilegeGranted(final String s) {
    }
    
    public static void checkPrivilegeEnabled(final String s) {
    }
    
    public void enablePrivilege(final Target target, final Principal principal, final Object o) {
    }
    
    public void enablePrivilege(final Target target) {
    }
    
    private static Principal[] createPrincipalArray() {
        return new Principal[] { new Principal() };
    }
    
    public Principal[] getClassPrincipalsFromStack(final int n) {
        return createPrincipalArray();
    }
    
    public void checkPrivilegeEnabled(final Target target, final Object o) {
    }
    
    public boolean hasPrincipal(final Class clazz, final Principal principal) {
        return true;
    }
    
    public int comparePrincipalArray(final Principal[] array, final Principal[] array2) {
        return -1;
    }
    
    public PrivilegeTable getPrivilegeTableFromStack() {
        return new PrivilegeTable();
    }
    
    public boolean isCalledByPrincipal(final Principal principal) {
        return true;
    }
    
    public static void enablePrivilege(final String s) {
    }
    
    public static PrivilegeManager getPrivilegeManager() {
        if (PrivilegeManager.mgr == null) {
            PrivilegeManager.mgr = new PrivilegeManager();
        }
        return PrivilegeManager.mgr;
    }
    
    public void checkPrivilegeGranted(final Target target) {
    }
    
    public void enablePrivilege(final Target target, final Principal principal) {
    }
    
    public static void disablePrivilege(final String s) {
    }
    
    public static Principal getSystemPrincipal() {
        return new Principal();
    }
    
    public boolean checkMatchPrincipal(final Class clazz, final int n) {
        return true;
    }
}
