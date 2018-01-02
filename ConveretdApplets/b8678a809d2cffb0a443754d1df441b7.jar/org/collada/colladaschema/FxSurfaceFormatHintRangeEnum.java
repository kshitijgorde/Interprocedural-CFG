// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxSurfaceFormatHintRangeEnum
{
    SNORM("SNORM", 0), 
    UNORM("UNORM", 1), 
    SINT("SINT", 2), 
    UINT("UINT", 3), 
    FLOAT("FLOAT", 4);
    
    private FxSurfaceFormatHintRangeEnum(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxSurfaceFormatHintRangeEnum fromValue(final String v) {
        return valueOf(v);
    }
}
