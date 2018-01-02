// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxModifierEnumCommon
{
    CONST("CONST", 0), 
    UNIFORM("UNIFORM", 1), 
    VARYING("VARYING", 2), 
    STATIC("STATIC", 3), 
    VOLATILE("VOLATILE", 4), 
    EXTERN("EXTERN", 5), 
    SHARED("SHARED", 6);
    
    private FxModifierEnumCommon(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxModifierEnumCommon fromValue(final String v) {
        return valueOf(v);
    }
}
