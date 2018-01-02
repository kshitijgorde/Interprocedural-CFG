// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlLogicOpType
{
    CLEAR("CLEAR", 0), 
    AND("AND", 1), 
    AND_REVERSE("AND_REVERSE", 2), 
    COPY("COPY", 3), 
    AND_INVERTED("AND_INVERTED", 4), 
    NOOP("NOOP", 5), 
    XOR("XOR", 6), 
    OR("OR", 7), 
    NOR("NOR", 8), 
    EQUIV("EQUIV", 9), 
    INVERT("INVERT", 10), 
    OR_REVERSE("OR_REVERSE", 11), 
    COPY_INVERTED("COPY_INVERTED", 12), 
    NAND("NAND", 13), 
    SET("SET", 14);
    
    private GlLogicOpType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlLogicOpType fromValue(final String v) {
        return valueOf(v);
    }
}
