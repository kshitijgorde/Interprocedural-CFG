// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public final class PropertyType
{
    public static final PropertyType ATTRIBUTE;
    public static final PropertyType ELEMENT;
    public static final PropertyType LOOKUP;
    private final String myName;
    
    private PropertyType(final String myName) {
        this.myName = myName;
    }
    
    public String toString() {
        return this.myName;
    }
    
    static {
        ATTRIBUTE = new PropertyType("ATTRIBUTE");
        ELEMENT = new PropertyType("ELEMENT");
        LOOKUP = new PropertyType("LOOKUP");
    }
}
