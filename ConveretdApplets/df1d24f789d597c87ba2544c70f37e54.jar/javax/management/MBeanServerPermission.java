// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.Iterator;
import java.util.Enumeration;
import java.util.HashSet;
import java.security.PermissionCollection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.Permission;
import java.security.BasicPermission;

public class MBeanServerPermission extends BasicPermission
{
    private static final long serialVersionUID = -5661980843569388590L;
    private transient boolean allNames;
    
    public MBeanServerPermission(final String name) {
        this(name, null);
    }
    
    public MBeanServerPermission(final String name, final String actions) {
        super(name, actions);
        this.init(name, actions);
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(100);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" name=").append(this.getName());
        buffer.append(" actions=").append(this.getActions());
        return buffer.toString();
    }
    
    public boolean implies(final Permission p) {
        if (!(p instanceof MBeanServerPermission)) {
            return false;
        }
        boolean implies = this.allNames;
        if (!implies) {
            final String n0 = this.getName();
            final String n2 = p.getName();
            implies = n0.equals(n2);
            if (!implies) {
                implies = (n0.equals("createMBeanServer") && n2.equals("newMBeanServer"));
            }
        }
        return implies;
    }
    
    private void init(final String name, final String actions) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }
        if (actions != null && actions.length() > 0) {
            throw new IllegalArgumentException("actions must be null or empty");
        }
        if (!name.equals("*") && !name.equals("createMBeanServer") && !name.equals("findMBeanServer") && !name.equals("newMBeanServer") && !name.equals("releaseMBeanServer")) {
            throw new IllegalArgumentException("Unknown name: " + name);
        }
        this.allNames = name.equals("*");
    }
    
    private void readObject(final ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        this.init(this.getName(), this.getActions());
    }
    
    public PermissionCollection newPermissionCollection() {
        return new MBeanServerPermissionCollections();
    }
    
    class MBeanServerPermissionCollections extends PermissionCollection
    {
        private static final long serialVersionUID = -4111836792595161197L;
        private HashSet permissions;
        private boolean hasAll;
        
        MBeanServerPermissionCollections() {
            this.permissions = new HashSet();
        }
        
        public void add(final Permission p) {
            if (this.isReadOnly()) {
                throw new SecurityException("Collection is read-only");
            }
            if (p instanceof MBeanServerPermission) {
                this.permissions.add(p);
            }
            if (p.getName().equals("createMBeanServer")) {
                this.permissions.add(new MBeanServerPermission("newMBeanServer"));
            }
            else if (p.getName().equals("*")) {
                this.hasAll = true;
            }
        }
        
        public boolean implies(final Permission p) {
            boolean implies = false;
            if (p instanceof MBeanServerPermission) {
                implies = this.hasAll;
                if (!implies) {
                    implies = this.permissions.contains(p);
                }
            }
            return implies;
        }
        
        public Enumeration elements() {
            final Iterator iter = this.permissions.iterator();
            final Enumeration enumerator = new Enumeration() {
                public boolean hasMoreElements() {
                    return iter.hasNext();
                }
                
                public Object nextElement() {
                    return iter.next();
                }
            };
            return enumerator;
        }
    }
}
