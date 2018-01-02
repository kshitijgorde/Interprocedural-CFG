// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlesTexcombinerOperatorRGBEnums
{
    REPLACE("REPLACE", 0, "REPLACE"), 
    MODULATE("MODULATE", 1, "MODULATE"), 
    ADD("ADD", 2, "ADD"), 
    ADD_SIGNED("ADD_SIGNED", 3, "ADD_SIGNED"), 
    INTERPOLATE("INTERPOLATE", 4, "INTERPOLATE"), 
    SUBTRACT("SUBTRACT", 5, "SUBTRACT"), 
    DOT_3_RGB("DOT_3_RGB", 6, "DOT3_RGB"), 
    DOT_3_RGBA("DOT_3_RGBA", 7, "DOT3_RGBA");
    
    private final String value;
    
    private GlesTexcombinerOperatorRGBEnums(final String s, final int n, final String v) {
        this.value = v;
    }
    
    public String value() {
        return this.value;
    }
    
    public static GlesTexcombinerOperatorRGBEnums fromValue(final String v) {
        GlesTexcombinerOperatorRGBEnums[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GlesTexcombinerOperatorRGBEnums c = values[i];
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
