// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(GlBlendType.class)
public enum GlesTexcombinerOperandAlphaEnums
{
    SRC_ALPHA("SRC_ALPHA", 0), 
    ONE_MINUS_SRC_ALPHA("ONE_MINUS_SRC_ALPHA", 1);
    
    private GlesTexcombinerOperandAlphaEnums(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlesTexcombinerOperandAlphaEnums fromValue(final String v) {
        return valueOf(v);
    }
}
