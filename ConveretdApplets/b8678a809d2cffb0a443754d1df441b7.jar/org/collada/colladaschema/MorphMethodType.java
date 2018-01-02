// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum MorphMethodType
{
    NORMALIZED("NORMALIZED", 0), 
    RELATIVE("RELATIVE", 1);
    
    private MorphMethodType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static MorphMethodType fromValue(final String v) {
        return valueOf(v);
    }
}
