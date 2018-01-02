// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum SpringType
{
    LINEAR("LINEAR", 0), 
    ANGULAR("ANGULAR", 1);
    
    private SpringType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static SpringType fromValue(final String v) {
        return valueOf(v);
    }
}
