// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

import java.util.HashMap;

public class MultiplexMappingDefinition
{
    private Class baseClass;
    private String attributeName;
    private HashMap forwardMappings;
    private HashMap reverseMappings;
    
    public MultiplexMappingDefinition(final Class baseClass, final String attributeName, final MultiplexMappingEntry[] array) {
        this.attributeName = attributeName;
        this.baseClass = baseClass;
        this.forwardMappings = new HashMap();
        this.reverseMappings = new HashMap();
        for (int i = 0; i < array.length; ++i) {
            final MultiplexMappingEntry multiplexMappingEntry = array[i];
            this.forwardMappings.put(multiplexMappingEntry.getAttributeValue(), multiplexMappingEntry);
            this.reverseMappings.put(multiplexMappingEntry.getTargetClass(), multiplexMappingEntry);
        }
    }
    
    public String getAttributeName() {
        return this.attributeName;
    }
    
    public Class getBaseClass() {
        return this.baseClass;
    }
    
    public MultiplexMappingEntry getEntryForType(final String s) {
        return this.forwardMappings.get(s);
    }
    
    public MultiplexMappingEntry getEntryForClass(final String s) {
        return this.reverseMappings.get(s);
    }
}
