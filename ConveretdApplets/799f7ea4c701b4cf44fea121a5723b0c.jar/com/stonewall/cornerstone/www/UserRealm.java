// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.www;

import com.stonewall.cornerstone.entity.User;
import java.security.Principal;
import org.apache.catalina.realm.RealmBase;

public class UserRealm extends RealmBase
{
    static final String Name = "UserRealm";
    static final String Role = "User";
    
    public boolean hasRole(final Principal principal, final String role) {
        final UserPrincipal p = (UserPrincipal)principal;
        return p.getRole().equals("User");
    }
    
    protected String getName() {
        return "UserRealm";
    }
    
    protected String getPassword(final String userid) {
        String result = null;
        try {
            final RMIClient client = new RMIClient();
            client.setTarget("~.ps.nbi.core.UserNBI", new Object[0]);
            final User user = (User)client.invoke("lookup", userid);
            if (user != null) {
                user.decrypt();
                result = user.getPassword();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    protected Principal getPrincipal(final String username) {
        return new UserPrincipal(username);
    }
    
    class UserPrincipal implements Principal
    {
        final String name;
        final String role;
        
        public UserPrincipal(final String name) {
            this.name = name;
            this.role = "User";
        }
        
        @Override
        public String getName() {
            return this.name;
        }
        
        public String getRole() {
            return this.role;
        }
        
        @Override
        public String toString() {
            return this.getName();
        }
    }
}
