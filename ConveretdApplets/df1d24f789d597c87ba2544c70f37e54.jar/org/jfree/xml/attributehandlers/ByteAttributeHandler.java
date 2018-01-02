// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class ByteAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        return ((Byte)o).toString();
    }
    
    public Object toPropertyValue(final String s) {
        return new Byte(s);
    }
}
