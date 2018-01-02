// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

public class LookupDefinition
{
    private String propertyName;
    private String registryKey;
    
    public LookupDefinition(final String propertyName, final String registryKey) {
        this.propertyName = propertyName;
        this.registryKey = registryKey;
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public String getRegistryKey() {
        return this.registryKey;
    }
}
