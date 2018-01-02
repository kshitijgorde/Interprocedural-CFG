// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.apache.activemq.kaha.impl.index.IndexLinkedList;
import java.util.ListIterator;

public class ContainerListIterator extends ContainerValueCollectionIterator implements ListIterator
{
    protected ContainerListIterator(final ListContainerImpl container, final IndexLinkedList list, final IndexItem start) {
        super(container, list, start);
    }
    
    @Override
    public boolean hasPrevious() {
        synchronized (this.container) {
            this.nextItem = (IndexItem)this.list.refreshEntry(this.nextItem);
            return this.list.getPrevEntry(this.nextItem) != null;
        }
    }
    
    @Override
    public Object previous() {
        synchronized (this.container) {
            this.nextItem = (IndexItem)this.list.refreshEntry(this.nextItem);
            this.nextItem = this.list.getPrevEntry(this.nextItem);
            return (this.nextItem != null) ? this.container.getValue(this.nextItem) : null;
        }
    }
    
    @Override
    public int nextIndex() {
        int result = -1;
        if (this.nextItem != null) {
            synchronized (this.container) {
                this.nextItem = (IndexItem)this.list.refreshEntry(this.nextItem);
                final StoreEntry next = this.list.getNextEntry(this.nextItem);
                if (next != null) {
                    result = this.container.getInternalList().indexOf(next);
                }
            }
        }
        return result;
    }
    
    @Override
    public int previousIndex() {
        int result = -1;
        if (this.nextItem != null) {
            synchronized (this.container) {
                this.nextItem = (IndexItem)this.list.refreshEntry(this.nextItem);
                final StoreEntry prev = this.list.getPrevEntry(this.nextItem);
                if (prev != null) {
                    result = this.container.getInternalList().indexOf(prev);
                }
            }
        }
        return result;
    }
    
    @Override
    public void set(final Object o) {
        final IndexItem item = ((ListContainerImpl)this.container).internalSet(this.previousIndex() + 1, o);
        this.nextItem = item;
    }
    
    @Override
    public void add(final Object o) {
        final IndexItem item = ((ListContainerImpl)this.container).internalAdd(this.previousIndex() + 1, o);
        this.nextItem = item;
    }
}
