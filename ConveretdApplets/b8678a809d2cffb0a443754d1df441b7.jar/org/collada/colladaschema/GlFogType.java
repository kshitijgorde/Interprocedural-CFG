// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlFogType
{
    LINEAR("LINEAR", 0, "LINEAR"), 
    EXP("EXP", 1, "EXP"), 
    EXP_2("EXP_2", 2, "EXP2");
    
    private final String value;
    
    private GlFogType(final String s, final int n, final String v) {
        this.value = v;
    }
    
    public String value() {
        return this.value;
    }
    
    public static GlFogType fromValue(final String v) {
        GlFogType[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final GlFogType c = values[i];
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v.toString());
    }
}
