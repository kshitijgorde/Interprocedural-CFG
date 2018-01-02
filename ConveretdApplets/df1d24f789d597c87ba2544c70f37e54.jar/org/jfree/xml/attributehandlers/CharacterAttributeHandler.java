// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.attributehandlers;

public class CharacterAttributeHandler implements AttributeHandler
{
    public String toAttributeValue(final Object o) {
        return ((Character)o).toString();
    }
    
    public Object toPropertyValue(final String s) {
        if (s.length() == 0) {
            throw new RuntimeException("Ugly, no char set!");
        }
        return new Character(s.charAt(0));
    }
}
