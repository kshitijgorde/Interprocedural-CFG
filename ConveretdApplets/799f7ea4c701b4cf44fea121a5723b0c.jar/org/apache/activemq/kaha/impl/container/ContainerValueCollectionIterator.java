// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.apache.activemq.kaha.StoreEntry;
import org.apache.activemq.kaha.impl.index.IndexItem;
import org.apache.activemq.kaha.impl.index.IndexLinkedList;
import java.util.Iterator;

public class ContainerValueCollectionIterator implements Iterator
{
    protected BaseContainerImpl container;
    protected IndexLinkedList list;
    protected IndexItem nextItem;
    protected IndexItem currentItem;
    
    ContainerValueCollectionIterator(final BaseContainerImpl container, final IndexLinkedList list, final IndexItem start) {
        this.container = container;
        this.list = list;
        this.currentItem = start;
        this.nextItem = list.getNextEntry((IndexItem)list.refreshEntry(start));
    }
    
    @Override
    public boolean hasNext() {
        return this.nextItem != null;
    }
    
    @Override
    public Object next() {
        synchronized (this.container) {
            this.nextItem = (IndexItem)this.list.refreshEntry(this.nextItem);
            this.currentItem = this.nextItem;
            final Object result = this.container.getValue(this.nextItem);
            this.nextItem = this.list.getNextEntry(this.nextItem);
            return result;
        }
    }
    
    @Override
    public void remove() {
        synchronized (this.container) {
            if (this.currentItem != null) {
                this.currentItem = (IndexItem)this.list.refreshEntry(this.currentItem);
                this.container.remove(this.currentItem);
            }
        }
    }
}
