// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlFuncType
{
    NEVER("NEVER", 0), 
    LESS("LESS", 1), 
    LEQUAL("LEQUAL", 2), 
    EQUAL("EQUAL", 3), 
    GREATER("GREATER", 4), 
    NOTEQUAL("NOTEQUAL", 5), 
    GEQUAL("GEQUAL", 6), 
    ALWAYS("ALWAYS", 7);
    
    private GlFuncType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlFuncType fromValue(final String v) {
        return valueOf(v);
    }
}
