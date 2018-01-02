// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlFogCoordSrcType
{
    FOG_COORDINATE("FOG_COORDINATE", 0), 
    FRAGMENT_DEPTH("FRAGMENT_DEPTH", 1);
    
    private GlFogCoordSrcType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlFogCoordSrcType fromValue(final String v) {
        return valueOf(v);
    }
}
