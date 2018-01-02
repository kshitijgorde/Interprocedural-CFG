// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlFaceType
{
    FRONT("FRONT", 0), 
    BACK("BACK", 1), 
    FRONT_AND_BACK("FRONT_AND_BACK", 2);
    
    private GlFaceType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlFaceType fromValue(final String v) {
        return valueOf(v);
    }
}
