// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlStencilOpType
{
    KEEP("KEEP", 0), 
    ZERO("ZERO", 1), 
    REPLACE("REPLACE", 2), 
    INCR("INCR", 3), 
    DECR("DECR", 4), 
    INVERT("INVERT", 5), 
    INCR_WRAP("INCR_WRAP", 6), 
    DECR_WRAP("DECR_WRAP", 7);
    
    private GlStencilOpType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlStencilOpType fromValue(final String v) {
        return valueOf(v);
    }
}
