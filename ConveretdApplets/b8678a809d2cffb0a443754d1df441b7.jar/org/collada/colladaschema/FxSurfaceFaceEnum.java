// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxSurfaceFaceEnum
{
    POSITIVE_X("POSITIVE_X", 0), 
    NEGATIVE_X("NEGATIVE_X", 1), 
    POSITIVE_Y("POSITIVE_Y", 2), 
    NEGATIVE_Y("NEGATIVE_Y", 3), 
    POSITIVE_Z("POSITIVE_Z", 4), 
    NEGATIVE_Z("NEGATIVE_Z", 5);
    
    private FxSurfaceFaceEnum(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxSurfaceFaceEnum fromValue(final String v) {
        return valueOf(v);
    }
}
