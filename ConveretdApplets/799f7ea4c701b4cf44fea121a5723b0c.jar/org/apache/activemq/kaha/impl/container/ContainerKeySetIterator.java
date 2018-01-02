// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.impl.index.IndexLinkedList;
import org.apache.activemq.kaha.impl.index.IndexItem;
import java.util.Iterator;

public class ContainerKeySetIterator implements Iterator
{
    protected IndexItem nextItem;
    protected IndexItem currentItem;
    private MapContainerImpl container;
    private IndexLinkedList list;
    
    ContainerKeySetIterator(final MapContainerImpl container) {
        this.container = container;
        this.list = container.getInternalList();
        this.currentItem = this.list.getRoot();
        this.nextItem = this.list.getNextEntry(this.currentItem);
    }
    
    @Override
    public boolean hasNext() {
        return this.nextItem != null;
    }
    
    @Override
    public Object next() {
        this.currentItem = this.nextItem;
        final Object result = this.container.getKey(this.nextItem);
        this.nextItem = this.list.getNextEntry(this.nextItem);
        return result;
    }
    
    @Override
    public void remove() {
        if (this.currentItem != null) {
            this.container.remove(this.currentItem);
            if (this.nextItem != null) {
                this.list.refreshEntry(this.nextItem);
            }
        }
    }
}
