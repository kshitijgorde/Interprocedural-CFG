// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlPolygonModeType
{
    POINT("POINT", 0), 
    LINE("LINE", 1), 
    FILL("FILL", 2);
    
    private GlPolygonModeType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlPolygonModeType fromValue(final String v) {
        return valueOf(v);
    }
}
