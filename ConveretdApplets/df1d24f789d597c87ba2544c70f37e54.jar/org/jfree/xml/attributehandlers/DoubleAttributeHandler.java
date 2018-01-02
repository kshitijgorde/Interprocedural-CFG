// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class DoubleAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        return ((Double)o).toString();
    }
    
    public Object toPropertyValue(final String s) {
        return new Double(s);
    }
}
