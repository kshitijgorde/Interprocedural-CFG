// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlesSamplerWrap
{
    REPEAT("REPEAT", 0), 
    CLAMP("CLAMP", 1), 
    CLAMP_TO_EDGE("CLAMP_TO_EDGE", 2), 
    MIRRORED_REPEAT("MIRRORED_REPEAT", 3);
    
    private GlesSamplerWrap(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlesSamplerWrap fromValue(final String v) {
        return valueOf(v);
    }
}
