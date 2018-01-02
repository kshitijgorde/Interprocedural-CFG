// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class ShortAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        return ((Short)o).toString();
    }
    
    public Object toPropertyValue(final String s) {
        return new Short(s);
    }
}
