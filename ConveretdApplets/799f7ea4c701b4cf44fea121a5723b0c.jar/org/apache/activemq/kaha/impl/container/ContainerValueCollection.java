// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import java.util.List;
import java.util.ArrayList;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.impl.index.IndexLinkedList;
import java.util.Iterator;
import java.util.Collection;

class ContainerValueCollection extends ContainerCollectionSupport implements Collection
{
    ContainerValueCollection(final MapContainerImpl container) {
        super(container);
    }
    
    @Override
    public boolean contains(final Object o) {
        return this.container.containsValue(o);
    }
    
    @Override
    public Iterator iterator() {
        final IndexLinkedList list = this.container.getItemList();
        return new ContainerValueCollectionIterator(this.container, list, list.getRoot());
    }
    
    @Override
    public Object[] toArray() {
        Object[] result = null;
        final IndexLinkedList list = this.container.getItemList();
        synchronized (list) {
            result = new Object[list.size()];
            IndexItem item = list.getFirst();
            int count = 0;
            while (item != null) {
                final Object value = this.container.getValue(item);
                result[count++] = value;
                item = list.getNextEntry(item);
            }
        }
        return result;
    }
    
    @Override
    public Object[] toArray(final Object[] result) {
        final IndexLinkedList list = this.container.getItemList();
        synchronized (list) {
            if (result.length <= list.size()) {
                IndexItem item = list.getFirst();
                int count = 0;
                while (item != null) {
                    final Object value = this.container.getValue(item);
                    result[count++] = value;
                    item = list.getNextEntry(item);
                }
            }
        }
        return result;
    }
    
    @Override
    public boolean add(final Object o) {
        throw new UnsupportedOperationException("Can't add an object here");
    }
    
    @Override
    public boolean remove(final Object o) {
        return this.container.removeValue(o);
    }
    
    @Override
    public boolean containsAll(final Collection c) {
        boolean result = !c.isEmpty();
        final Iterator i = c.iterator();
        while (i.hasNext()) {
            if (!this.contains(i.next())) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    @Override
    public boolean addAll(final Collection c) {
        throw new UnsupportedOperationException("Can't add everything here!");
    }
    
    @Override
    public boolean removeAll(final Collection c) {
        boolean result = true;
        for (final Object obj : c) {
            result &= this.remove(obj);
        }
        return result;
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
    public void clear() {
        this.container.clear();
    }
}
