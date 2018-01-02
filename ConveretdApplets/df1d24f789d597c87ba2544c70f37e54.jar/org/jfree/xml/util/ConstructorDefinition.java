// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

public class ConstructorDefinition
{
    private boolean isNull;
    private String propertyName;
    private Class type;
    
    public ConstructorDefinition(final String propertyName, final Class type) {
        this.isNull = (propertyName == null);
        this.propertyName = propertyName;
        this.type = type;
    }
    
    public Class getType() {
        return this.type;
    }
    
    public boolean isNull() {
        return this.isNull;
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
}
