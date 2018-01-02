// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxOpaqueEnum
{
    A_ONE("A_ONE", 0), 
    RGB_ZERO("RGB_ZERO", 1);
    
    private FxOpaqueEnum(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxOpaqueEnum fromValue(final String v) {
        return valueOf(v);
    }
}
