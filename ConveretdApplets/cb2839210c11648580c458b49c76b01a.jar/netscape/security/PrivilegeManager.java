// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

public final class PrivilegeManager
{
    public static final int PROPER_SUBSET = -1;
    public static final int EQUAL = 0;
    public static final int NO_SUBSET = 1;
    private static PrivilegeManager mgr;
    
    public void checkPrivilegeEnabled(final Target target) {
    }
    
    public void checkPrivilegeEnabled(final Target target, final Object o) {
    }
    
    public static void checkPrivilegeEnabled(final String s) {
    }
    
    public static void enablePrivilege(final String s) {
    }
    
    public void enablePrivilege(final Target target) {
    }
    
    public void enablePrivilege(final Target target, final Principal principal) {
    }
    
    public void enablePrivilege(final Target target, final Principal principal, final Object o) {
    }
    
    public void revertPrivilege(final Target target) {
    }
    
    public static void revertPrivilege(final String s) {
    }
    
    public void disablePrivilege(final Target target) {
    }
    
    public static void disablePrivilege(final String s) {
    }
    
    public static void checkPrivilegeGranted(final String s) {
    }
    
    public void checkPrivilegeGranted(final Target target) {
    }
    
    public void checkPrivilegeGranted(final Target target, final Object o) {
    }
    
    public void checkPrivilegeGranted(final Target target, final Principal principal, final Object o) {
    }
    
    public boolean isCalledByPrincipal(final Principal principal, final int n) {
        return true;
    }
    
    public boolean isCalledByPrincipal(final Principal principal) {
        return true;
    }
    
    public static Principal getSystemPrincipal() {
        return new Principal();
    }
    
    private static Principal[] createPrincipalArray() {
        return new Principal[] { new Principal() };
    }
    
    public static PrivilegeManager getPrivilegeManager() {
        if (PrivilegeManager.mgr == null) {
            PrivilegeManager.mgr = new PrivilegeManager();
        }
        return PrivilegeManager.mgr;
    }
    
    public static Principal[] getMyPrincipals() {
        return createPrincipalArray();
    }
    
    public Principal[] getClassPrincipals(final Class clazz) {
        return createPrincipalArray();
    }
    
    public boolean hasPrincipal(final Class clazz, final Principal principal) {
        return true;
    }
    
    public int comparePrincipalArray(final Principal[] array, final Principal[] array2) {
        return -1;
    }
    
    public boolean checkMatchPrincipal(final Class clazz, final int n) {
        return true;
    }
    
    public boolean checkMatchPrincipal(final Principal principal, final int n) {
        return true;
    }
    
    public boolean checkMatchPrincipal(final Class clazz) {
        return true;
    }
    
    public boolean checkMatchPrincipalAlways() {
        return true;
    }
    
    public Principal[] getClassPrincipalsFromStack(final int n) {
        return createPrincipalArray();
    }
    
    public PrivilegeTable getPrivilegeTableFromStack() {
        return new PrivilegeTable();
    }
    
    static {
        PrivilegeManager.mgr = null;
    }
}
