// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

public class ParameterizedTarget extends UserTarget
{
    public Privilege checkPrivilegeEnabled(final Principal[] array, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public ParameterizedTarget() {
    }
    
    public Privilege enablePrivilege(final Principal principal, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public ParameterizedTarget(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final String s5) {
    }
    
    public ParameterizedTarget(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4) {
    }
    
    public String getDetailedInfo(final Object o) {
        return "";
    }
}
