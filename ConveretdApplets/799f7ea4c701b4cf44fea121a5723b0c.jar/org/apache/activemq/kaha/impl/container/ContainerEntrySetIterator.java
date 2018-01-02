// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import java.util.Iterator;

public class ContainerEntrySetIterator implements Iterator
{
    private MapContainerImpl container;
    private Iterator iter;
    private ContainerMapEntry currentEntry;
    
    ContainerEntrySetIterator(final MapContainerImpl container, final Iterator iter) {
        this.container = container;
        this.iter = iter;
    }
    
    @Override
    public boolean hasNext() {
        return this.iter.hasNext();
    }
    
    @Override
    public Object next() {
        return this.currentEntry = this.iter.next();
    }
    
    @Override
    public void remove() {
        if (this.currentEntry != null) {
            this.container.remove(this.currentEntry.getKey());
        }
    }
}
