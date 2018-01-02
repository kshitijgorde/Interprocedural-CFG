// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlesStencilOpType
{
    KEEP("KEEP", 0), 
    ZERO("ZERO", 1), 
    REPLACE("REPLACE", 2), 
    INCR("INCR", 3), 
    DECR("DECR", 4), 
    INVERT("INVERT", 5);
    
    private GlesStencilOpType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlesStencilOpType fromValue(final String v) {
        return valueOf(v);
    }
}
