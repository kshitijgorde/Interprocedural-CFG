// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlLightModelColorControlType
{
    SINGLE_COLOR("SINGLE_COLOR", 0), 
    SEPARATE_SPECULAR_COLOR("SEPARATE_SPECULAR_COLOR", 1);
    
    private GlLightModelColorControlType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlLightModelColorControlType fromValue(final String v) {
        return valueOf(v);
    }
}
