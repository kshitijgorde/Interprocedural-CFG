// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

import org.jfree.xml.attributehandlers.AttributeHandler;

public class AttributeDefinition
{
    private String attributeName;
    private AttributeHandler handler;
    private String propertyName;
    
    public AttributeDefinition(final String propertyName, final String attributeName, final AttributeHandler handler) {
        this.propertyName = propertyName;
        this.attributeName = attributeName;
        this.handler = handler;
    }
    
    public String getPropertyName() {
        return this.propertyName;
    }
    
    public String getAttributeName() {
        return this.attributeName;
    }
    
    public AttributeHandler getHandler() {
        return this.handler;
    }
}
