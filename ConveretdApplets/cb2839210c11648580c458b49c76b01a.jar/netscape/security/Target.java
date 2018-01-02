// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

public class Target
{
    public Target() {
    }
    
    public Target(final String s, final Principal principal) {
    }
    
    public Target(final String s) {
    }
    
    public Target(final String s, final Principal principal, final Target[] array) {
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4) {
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final Target[] array) {
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final String s5) {
    }
    
    public Target(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final String s5, final Target[] array) {
    }
    
    public final Target registerTarget() {
        return this;
    }
    
    public static Target findTarget(final String s) {
        return new Target();
    }
    
    public static Target findTarget(final String s, final Principal principal) {
        return new Target();
    }
    
    public static Target findTarget(final Target target) {
        return new Target();
    }
    
    public Privilege checkPrivilegeEnabled(final Principal[] array, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Privilege checkPrivilegeEnabled(final Principal[] array) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Privilege checkPrivilegeEnabled(final Principal principal, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Privilege enablePrivilege(final Principal principal, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public static Target[] getAllRegisteredTargets() {
        return null;
    }
    
    public String getRisk() {
        return "";
    }
    
    public String getRiskColor() {
        return "";
    }
    
    public String getDescription() {
        return "";
    }
    
    public String getDetailDescription() {
        return "";
    }
    
    public static Target getTargetFromDescription(final String s) {
        return null;
    }
    
    public String getHelpUrl() {
        return "";
    }
    
    public String getDetailedInfo(final Object o) {
        return "";
    }
    
    public final boolean equals(final Object o) {
        return false;
    }
    
    public int hashCode() {
        return 0;
    }
    
    public String toString() {
        return "";
    }
    
    public String getName() {
        return "";
    }
}
