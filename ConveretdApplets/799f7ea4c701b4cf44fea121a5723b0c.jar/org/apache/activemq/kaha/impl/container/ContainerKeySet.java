// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import java.util.Collection;
import org.apache.activemq.kaha.impl.index.IndexItem;
import java.util.List;
import org.apache.activemq.kaha.StoreEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class ContainerKeySet extends ContainerCollectionSupport implements Set
{
    ContainerKeySet(final MapContainerImpl container) {
        super(container);
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.container.containsKey(o);
    }
    
    @Override
    public Iterator iterator() {
        return new ContainerKeySetIterator(this.container);
    }
    
    @Override
    public Object[] toArray() {
        final List<Object> list = new ArrayList<Object>();
        IndexItem item = this.container.getInternalList().getRoot();
        while ((item = this.container.getInternalList().getNextEntry(item)) != null) {
            list.add(this.container.getKey(item));
        }
        return list.toArray();
    }
    
    @Override
    public Object[] toArray(final Object[] a) {
        final List<Object> list = new ArrayList<Object>();
        IndexItem item = this.container.getInternalList().getRoot();
        while ((item = this.container.getInternalList().getNextEntry(item)) != null) {
            list.add(this.container.getKey(item));
        }
        return list.toArray(a);
    }
    
    @Override
    public boolean add(final Object o) {
        throw new UnsupportedOperationException("Cannot add here");
    }
    
    @Override
    public boolean remove(final Object o) {
        return this.container.remove(o) != null;
    }
    
    @Override
    public boolean containsAll(final Collection c) {
        for (final Object key : c) {
            if (!this.container.containsKey(key)) {
                return false;
            }
        }
        return true;
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
        final Iterator<Object> j = tmpList.iterator();
        while (j.hasNext()) {
            this.remove(j.next());
        }
        return !tmpList.isEmpty();
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
    
    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer(32);
        result.append("ContainerKeySet[");
        IndexItem item = this.container.getInternalList().getRoot();
        while ((item = this.container.getInternalList().getNextEntry(item)) != null) {
            result.append(this.container.getKey(item));
            result.append(",");
        }
        result.append("]");
        return result.toString();
    }
}
