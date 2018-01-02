// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

class ContainerCollectionSupport
{
    protected MapContainerImpl container;
    
    protected ContainerCollectionSupport(final MapContainerImpl container) {
        this.container = container;
    }
    
    public int size() {
        return this.container.size();
    }
    
    public boolean isEmpty() {
        return this.container.isEmpty();
    }
}
