// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlesTexcombinerOperatorAlphaEnums
{
    REPLACE("REPLACE", 0), 
    MODULATE("MODULATE", 1), 
    ADD("ADD", 2), 
    ADD_SIGNED("ADD_SIGNED", 3), 
    INTERPOLATE("INTERPOLATE", 4), 
    SUBTRACT("SUBTRACT", 5);
    
    private GlesTexcombinerOperatorAlphaEnums(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlesTexcombinerOperatorAlphaEnums fromValue(final String v) {
        return valueOf(v);
    }
}
