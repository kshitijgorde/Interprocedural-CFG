// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxSurfaceFormatHintChannelsEnum
{
    RGB("RGB", 0), 
    RGBA("RGBA", 1), 
    L("L", 2), 
    LA("LA", 3), 
    D("D", 4), 
    XYZ("XYZ", 5), 
    XYZW("XYZW", 6);
    
    private FxSurfaceFormatHintChannelsEnum(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxSurfaceFormatHintChannelsEnum fromValue(final String v) {
        return valueOf(v);
    }
}
