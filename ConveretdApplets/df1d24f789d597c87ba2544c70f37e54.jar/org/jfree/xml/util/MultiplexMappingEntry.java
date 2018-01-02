// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

public class MultiplexMappingEntry
{
    private String attributeValue;
    private String targetClass;
    
    public MultiplexMappingEntry(final String attributeValue, final String targetClass) {
        this.attributeValue = attributeValue;
        this.targetClass = targetClass;
    }
    
    public String getAttributeValue() {
        return this.attributeValue;
    }
    
    public String getTargetClass() {
        return this.targetClass;
    }
}
