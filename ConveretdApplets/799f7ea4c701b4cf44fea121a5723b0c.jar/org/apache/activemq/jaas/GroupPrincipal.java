// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.jaas;

import java.security.Principal;

public class GroupPrincipal implements Principal
{
    private final String name;
    private transient int hash;
    
    public GroupPrincipal(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final GroupPrincipal that = (GroupPrincipal)o;
        return this.name.equals(that.name);
    }
    
    @Override
    public int hashCode() {
        if (this.hash == 0) {
            this.hash = this.name.hashCode();
        }
        return this.hash;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}
