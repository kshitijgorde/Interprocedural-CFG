// 
// Decompiled by Procyon v0.5.30
// 

package pclient.shd;

public class SessionProps
{
    public String role;
    public String permissions;
    public boolean member;
    
    public SessionProps() {
        this.role = "nobody";
        this.permissions = null;
        this.member = false;
    }
}
