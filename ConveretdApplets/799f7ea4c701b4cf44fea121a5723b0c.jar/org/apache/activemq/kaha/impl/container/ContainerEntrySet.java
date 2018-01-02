// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.apache.activemq.kaha.MapContainer;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ContainerEntrySet extends ContainerCollectionSupport implements Set
{
    ContainerEntrySet(final MapContainerImpl container) {
        super(container);
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.container.entrySet().contains(o);
    }
    
    @Override
    public Iterator iterator() {
        return new ContainerEntrySetIterator(this.container, this.buildEntrySet().iterator());
    }
    
    @Override
    public Object[] toArray() {
        return this.buildEntrySet().toArray();
    }
    
    @Override
    public Object[] toArray(final Object[] a) {
        return this.buildEntrySet().toArray(a);
    }
    
    @Override
    public boolean add(final Object o) {
        throw new UnsupportedOperationException("Cannot add here");
    }
    
    @Override
    public boolean remove(final Object o) {
        final boolean result = false;
        if (this.buildEntrySet().remove(o)) {
            final ContainerMapEntry entry = (ContainerMapEntry)o;
            this.container.remove(entry.getKey());
        }
        return result;
    }
    
    @Override
    public boolean containsAll(final Collection c) {
        return this.buildEntrySet().containsAll(c);
    }
    
    @Override
    public boolean addAll(final Collection c) {
        throw new UnsupportedOperationException("Cannot add here");
    }
    
    @Override
    public boolean retainAll(final Collection c) {
        final List<Object> tmpList = new ArrayList<Object>();
        for (final Object o : c) {
            if (!this.contains(o)) {
                tmpList.add(o);
            }
        }
        boolean result = false;
        final Iterator<Object> j = tmpList.iterator();
        while (j.hasNext()) {
            result |= this.remove(j.next());
        }
        return result;
    }
    
    @Override
    public boolean removeAll(final Collection c) {
        boolean result = true;
        final Iterator i = c.iterator();
        while (i.hasNext()) {
            if (!this.remove(i.next())) {
                result = false;
            }
        }
        return result;
    }
    
    @Override
    public void clear() {
        this.container.clear();
    }
    
    protected Set<ContainerMapEntry> buildEntrySet() {
        final Set<ContainerMapEntry> set = new HashSet<ContainerMapEntry>();
        final Iterator i = this.container.keySet().iterator();
        while (i.hasNext()) {
            final ContainerMapEntry entry = new ContainerMapEntry(this.container, i.next());
            set.add(entry);
        }
        return set;
    }
}
