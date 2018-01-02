// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.container;

import org.apache.activemq.kaha.MapContainer;
import java.util.Map;

class ContainerMapEntry implements Map.Entry
{
    private MapContainer container;
    private Object key;
    
    ContainerMapEntry(final MapContainer container, final Object key) {
        this.container = container;
        this.key = key;
    }
    
    @Override
    public Object getKey() {
        return this.key;
    }
    
    @Override
    public Object getValue() {
        return this.container.get(this.key);
    }
    
    @Override
    public Object setValue(final Object value) {
        return this.container.put(this.key, value);
    }
}
