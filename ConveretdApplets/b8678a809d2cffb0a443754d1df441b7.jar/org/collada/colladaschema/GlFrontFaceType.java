// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlFrontFaceType
{
    CW("CW", 0), 
    CCW("CCW", 1);
    
    private GlFrontFaceType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlFrontFaceType fromValue(final String v) {
        return valueOf(v);
    }
}
