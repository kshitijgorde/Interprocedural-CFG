// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

import java.util.Enumeration;

public final class PrivilegeTable
{
    public int size() {
        return 0;
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public Enumeration keys() {
        return null;
    }
    
    public Enumeration elements() {
        return null;
    }
    
    public String toString() {
        return "";
    }
    
    public Privilege get(final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public Privilege get(final Target target) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public synchronized Privilege put(final Object o, final Privilege privilege) {
        return privilege;
    }
    
    public synchronized Privilege put(final Target target, final Privilege privilege) {
        return privilege;
    }
    
    public synchronized Privilege remove(final Object o) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public synchronized Privilege remove(final Target target) {
        return Privilege.findPrivilege(1, 1);
    }
    
    public synchronized void clear() {
    }
    
    public Object clone() {
        return new PrivilegeTable();
    }
}