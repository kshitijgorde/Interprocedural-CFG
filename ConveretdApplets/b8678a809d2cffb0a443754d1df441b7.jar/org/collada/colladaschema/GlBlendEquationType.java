// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlBlendEquationType
{
    FUNC_ADD("FUNC_ADD", 0), 
    FUNC_SUBTRACT("FUNC_SUBTRACT", 1), 
    FUNC_REVERSE_SUBTRACT("FUNC_REVERSE_SUBTRACT", 2), 
    MIN("MIN", 3), 
    MAX("MAX", 4);
    
    private GlBlendEquationType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlBlendEquationType fromValue(final String v) {
        return valueOf(v);
    }
}
