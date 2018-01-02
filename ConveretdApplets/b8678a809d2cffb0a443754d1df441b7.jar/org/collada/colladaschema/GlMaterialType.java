// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlMaterialType
{
    EMISSION("EMISSION", 0), 
    AMBIENT("AMBIENT", 1), 
    DIFFUSE("DIFFUSE", 2), 
    SPECULAR("SPECULAR", 3), 
    AMBIENT_AND_DIFFUSE("AMBIENT_AND_DIFFUSE", 4);
    
    private GlMaterialType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlMaterialType fromValue(final String v) {
        return valueOf(v);
    }
}
