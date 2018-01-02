// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

public class Target
{
    public static Target getTargetFromDescription(final String s) {
        return null;
    }
    
    public String getRiskColor() {
        return "";
    }
    
    public static Target[] getAllRegisteredTargets() {
        return null;
    }
    
    public String getName() {
        return "";
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final String s5, final Target[] array) {
    }
    
    public final Target registerTarget() {
        return this;
    }
    
    public Target() {
    }
    
    public int hashCode() {
        return 0;
    }
    
    public static Target findTarget(final Target target) {
        return new Target();
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final Target[] array) {
    }
    
    public Privilege checkPrivilegeEnabled(final Principal[] array) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Target(final String s, final Principal principal) {
    }
    
    public Target(final String s) {
    }
    
    public Privilege checkPrivilegeEnabled(final Principal principal, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4) {
    }
    
    public String getDescription() {
        return "";
    }
    
    public String getRisk() {
        return "";
    }
    
    public String getDetailDescription() {
        return "";
    }
    
    public String getDetailedInfo(final Object o) {
        return "";
    }
    
    public String getHelpUrl() {
        return "";
    }
    
    public static Target findTarget(final String s) {
        return new Target();
    }
    
    public final boolean equals(final Object o) {
        return false;
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final String s5) {
    }
    
    public static Target findTarget(final String s, final Principal principal) {
        return new Target();
    }
    
    public Privilege checkPrivilegeEnabled(final Principal[] array, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Privilege enablePrivilege(final Principal principal, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public String toString() {
        return "";
    }
    
    public Target(final String s, final Principal principal, final Target[] array) {
    }
}
