// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

public class ParameterizedStringTarget extends ParameterizedTarget
{
    public ParameterizedStringTarget() {
    }
    
    public ParameterizedStringTarget(final String s, final Principal principal, final int n, final String s2, final String s3, final String s4, final String s5) {
    }
    
    public String getDetailedInfo(final Object o) {
        return "";
    }
    
    public Privilege enablePrivilege(final Principal principal, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Privilege checkPrivilegeEnabled(final Principal[] array, final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
}
