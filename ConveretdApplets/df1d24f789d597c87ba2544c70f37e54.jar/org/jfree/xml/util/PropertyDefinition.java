// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

public class PropertyDefinition
{
    private String propertyName;
    private String elementName;
    
    public PropertyDefinition(final String propertyName, final String elementName) {
        this.elementName = elementName;
        this.propertyName = propertyName;
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public String getElementName() {
        return this.elementName;
    }
}
