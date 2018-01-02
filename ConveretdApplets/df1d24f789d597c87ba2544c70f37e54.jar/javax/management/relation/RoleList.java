// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class RoleList extends ArrayList
{
    private static final long serialVersionUID = 5568344346499649313L;
    
    public RoleList() {
    }
    
    public RoleList(final int initialCapacity) {
        super(initialCapacity);
    }
    
    public RoleList(final List list) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("Null list");
        }
        final Iterator iterator = new ArrayList(list).iterator();
        while (iterator.hasNext()) {
            try {
                this.add(iterator.next());
                continue;
            }
            catch (ClassCastException cce) {
                throw new IllegalArgumentException("List element is not a role.");
            }
            break;
        }
    }
    
    public void add(final Role role) throws IllegalArgumentException {
        if (role == null) {
            throw new IllegalArgumentException("Null role");
        }
        super.add(role);
    }
    
    public void add(final int index, final Role role) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (role == null) {
            throw new IllegalArgumentException("Null role");
        }
        super.add(index, role);
    }
    
    public boolean addAll(final RoleList roleList) throws IndexOutOfBoundsException {
        return roleList != null && super.addAll(roleList);
    }
    
    public boolean addAll(final int index, final RoleList roleList) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (roleList == null) {
            throw new IllegalArgumentException("null roleList");
        }
        return super.addAll(index, roleList);
    }
    
    public void set(final int index, final Role role) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (role == null) {
            throw new IllegalArgumentException("Null role");
        }
        super.set(index, role);
    }
    
    public Object clone() {
        return super.clone();
    }
}
