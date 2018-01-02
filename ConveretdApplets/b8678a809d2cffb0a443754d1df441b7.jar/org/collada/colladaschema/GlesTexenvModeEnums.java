// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlesTexenvModeEnums
{
    REPLACE("REPLACE", 0), 
    MODULATE("MODULATE", 1), 
    DECAL("DECAL", 2), 
    BLEND("BLEND", 3), 
    ADD("ADD", 4);
    
    private GlesTexenvModeEnums(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlesTexenvModeEnums fromValue(final String v) {
        return valueOf(v);
    }
}
