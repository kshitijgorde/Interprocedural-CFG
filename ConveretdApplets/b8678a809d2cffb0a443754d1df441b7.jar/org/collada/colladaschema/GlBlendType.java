// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlBlendType
{
    ZERO("ZERO", 0), 
    ONE("ONE", 1), 
    SRC_COLOR("SRC_COLOR", 2), 
    ONE_MINUS_SRC_COLOR("ONE_MINUS_SRC_COLOR", 3), 
    DEST_COLOR("DEST_COLOR", 4), 
    ONE_MINUS_DEST_COLOR("ONE_MINUS_DEST_COLOR", 5), 
    SRC_ALPHA("SRC_ALPHA", 6), 
    ONE_MINUS_SRC_ALPHA("ONE_MINUS_SRC_ALPHA", 7), 
    DST_ALPHA("DST_ALPHA", 8), 
    ONE_MINUS_DST_ALPHA("ONE_MINUS_DST_ALPHA", 9), 
    CONSTANT_COLOR("CONSTANT_COLOR", 10), 
    ONE_MINUS_CONSTANT_COLOR("ONE_MINUS_CONSTANT_COLOR", 11), 
    CONSTANT_ALPHA("CONSTANT_ALPHA", 12), 
    ONE_MINUS_CONSTANT_ALPHA("ONE_MINUS_CONSTANT_ALPHA", 13), 
    SRC_ALPHA_SATURATE("SRC_ALPHA_SATURATE", 14);
    
    private GlBlendType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlBlendType fromValue(final String v) {
        return valueOf(v);
    }
}
