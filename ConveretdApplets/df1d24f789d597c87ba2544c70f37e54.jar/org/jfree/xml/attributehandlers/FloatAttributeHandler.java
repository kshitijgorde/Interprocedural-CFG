// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class FloatAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        return ((Float)o).toString();
    }
    
    public Object toPropertyValue(final String s) {
        return new Float(s);
    }
}
