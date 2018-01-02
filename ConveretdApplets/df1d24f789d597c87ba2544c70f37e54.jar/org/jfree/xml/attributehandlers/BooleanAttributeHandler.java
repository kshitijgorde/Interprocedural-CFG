// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class BooleanAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        if (o instanceof Boolean) {
            return o.toString();
        }
        throw new ClassCastException("Give me a real type.");
    }
    
    public Object toPropertyValue(final String s) {
        return new Boolean(s);
    }
}
