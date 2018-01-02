// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxSamplerFilterCommon
{
    NONE("NONE", 0), 
    NEAREST("NEAREST", 1), 
    LINEAR("LINEAR", 2), 
    NEAREST_MIPMAP_NEAREST("NEAREST_MIPMAP_NEAREST", 3), 
    LINEAR_MIPMAP_NEAREST("LINEAR_MIPMAP_NEAREST", 4), 
    NEAREST_MIPMAP_LINEAR("NEAREST_MIPMAP_LINEAR", 5), 
    LINEAR_MIPMAP_LINEAR("LINEAR_MIPMAP_LINEAR", 6);
    
    private FxSamplerFilterCommon(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxSamplerFilterCommon fromValue(final String v) {
        return valueOf(v);
    }
}
