// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class StringAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        final String s = (String)o;
        if (s != null) {
            return s.toString();
        }
        return null;
    }
    
    public Object toPropertyValue(final String s) {
        return new String(s);
    }
}
