// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlShadeModelType
{
    FLAT("FLAT", 0), 
    SMOOTH("SMOOTH", 1);
    
    private GlShadeModelType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlShadeModelType fromValue(final String v) {
        return valueOf(v);
    }
}
