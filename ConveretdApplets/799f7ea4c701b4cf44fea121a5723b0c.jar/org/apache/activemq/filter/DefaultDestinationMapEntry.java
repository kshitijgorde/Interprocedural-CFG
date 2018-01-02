// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.filter;

public class DefaultDestinationMapEntry extends DestinationMapEntry
{
    private Object value;
    
    @Override
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(final Object value) {
        this.value = value;
    }
}
