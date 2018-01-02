// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxSurfaceFormatHintPrecisionEnum
{
    LOW("LOW", 0), 
    MID("MID", 1), 
    HIGH("HIGH", 2);
    
    private FxSurfaceFormatHintPrecisionEnum(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxSurfaceFormatHintPrecisionEnum fromValue(final String v) {
        return valueOf(v);
    }
}
