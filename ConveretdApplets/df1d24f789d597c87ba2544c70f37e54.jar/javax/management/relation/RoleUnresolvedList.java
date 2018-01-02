// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.relation;

import java.util.Iterator;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class RoleUnresolvedList extends ArrayList
{
    public RoleUnresolvedList() {
    }
    
    public RoleUnresolvedList(final int initialCapacity) {
        super(initialCapacity);
    }
    
    public RoleUnresolvedList(final List list) throws IllegalArgumentException {
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
                throw new IllegalArgumentException("List element is not an unresolved role.");
            }
            break;
        }
    }
    
    public void add(final RoleUnresolved roleUnresolved) throws IllegalArgumentException {
        if (roleUnresolved == null) {
            throw new IllegalArgumentException("Null unresolved role");
        }
        super.add(roleUnresolved);
    }
    
    public void add(final int index, final RoleUnresolved roleUnresolved) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (roleUnresolved == null) {
            throw new IllegalArgumentException("Null unresolved role");
        }
        super.add(index, roleUnresolved);
    }
    
    public boolean addAll(final RoleUnresolvedList roleUnresolvedList) throws IndexOutOfBoundsException {
        return roleUnresolvedList != null && super.addAll(roleUnresolvedList);
    }
    
    public boolean addAll(final int index, final RoleUnresolvedList roleUnresolvedList) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (roleUnresolvedList == null) {
            throw new IllegalArgumentException("null roleUnresolvedList");
        }
        return super.addAll(index, roleUnresolvedList);
    }
    
    public void set(final int index, final RoleUnresolved roleUnresolved) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (roleUnresolved == null) {
            throw new IllegalArgumentException("Null unresolved role");
        }
        super.set(index, roleUnresolved);
    }
    
    public Object clone() {
        return super.clone();
    }
}
