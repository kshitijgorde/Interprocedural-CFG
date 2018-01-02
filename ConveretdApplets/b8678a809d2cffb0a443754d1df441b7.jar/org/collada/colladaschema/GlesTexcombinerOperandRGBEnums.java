// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(GlBlendType.class)
public enum GlesTexcombinerOperandRGBEnums
{
    SRC_COLOR("SRC_COLOR", 0), 
    ONE_MINUS_SRC_COLOR("ONE_MINUS_SRC_COLOR", 1), 
    SRC_ALPHA("SRC_ALPHA", 2), 
    ONE_MINUS_SRC_ALPHA("ONE_MINUS_SRC_ALPHA", 3);
    
    private GlesTexcombinerOperandRGBEnums(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlesTexcombinerOperandRGBEnums fromValue(final String v) {
        return valueOf(v);
    }
}
