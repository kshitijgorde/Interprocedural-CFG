// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxSamplerWrapCommon
{
    NONE("NONE", 0), 
    WRAP("WRAP", 1), 
    MIRROR("MIRROR", 2), 
    CLAMP("CLAMP", 3), 
    BORDER("BORDER", 4);
    
    private FxSamplerWrapCommon(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxSamplerWrapCommon fromValue(final String v) {
        return valueOf(v);
    }
}
