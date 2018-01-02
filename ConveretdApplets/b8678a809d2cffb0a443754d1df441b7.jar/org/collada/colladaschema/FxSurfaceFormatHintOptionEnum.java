// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxSurfaceFormatHintOptionEnum
{
    SRGB_GAMMA("SRGB_GAMMA", 0, "SRGB_GAMMA"), 
    NORMALIZED_3("NORMALIZED_3", 1, "NORMALIZED3"), 
    NORMALIZED_4("NORMALIZED_4", 2, "NORMALIZED4"), 
    COMPRESSABLE("COMPRESSABLE", 3, "COMPRESSABLE");
    
    private final String value;
    
    private FxSurfaceFormatHintOptionEnum(final String s, final int n, final String v) {
        this.value = v;
    }
    
    public String value() {
        return this.value;
    }
    
    public static FxSurfaceFormatHintOptionEnum fromValue(final String v) {
        FxSurfaceFormatHintOptionEnum[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final FxSurfaceFormatHintOptionEnum c = values[i];
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
