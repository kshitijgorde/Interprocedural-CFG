// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class LongAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        return ((Long)o).toString();
    }
    
    public Object toPropertyValue(final String s) {
        return new Long(s);
    }
}
