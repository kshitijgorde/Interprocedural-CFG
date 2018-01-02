// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.www;

import java.security.Principal;
import org.apache.catalina.realm.RealmBase;

public class BootstrapRealm extends RealmBase
{
    static final String Name = "BootstrapRealm";
    static final String Role = "BootstrapAdmin";
    
    public boolean hasRole(final Principal principal, final String role) {
        final BootstrapPrincipal p = (BootstrapPrincipal)principal;
        return p.getRole().equals("BootstrapAdmin");
    }
    
    protected String getName() {
        return "BootstrapRealm";
    }
    
    protected String getPassword(final String username) {
        if (username.equalsIgnoreCase(System.getProperty("cornerstone.www.username"))) {
            return System.getProperty("cornerstone.www.password");
        }
        return null;
    }
    
    protected Principal getPrincipal(final String username) {
        return new BootstrapPrincipal(username);
    }
    
    class BootstrapPrincipal implements Principal
    {
        private final String name;
        private final String role;
        
        public BootstrapPrincipal(final String name) {
            this.name = name;
            this.role = "BootstrapAdmin";
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
