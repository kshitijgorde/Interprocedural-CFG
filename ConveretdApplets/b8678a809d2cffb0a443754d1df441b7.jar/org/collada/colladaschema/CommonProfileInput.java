// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum CommonProfileInput
{
    BINORMAL("BINORMAL", 0), 
    COLOR("COLOR", 1), 
    CONTINUITY("CONTINUITY", 2), 
    IMAGE("IMAGE", 3), 
    IN_TANGENT("IN_TANGENT", 4), 
    INPUT("INPUT", 5), 
    INTERPOLATION("INTERPOLATION", 6), 
    INV_BIND_MATRIX("INV_BIND_MATRIX", 7), 
    JOINT("JOINT", 8), 
    LINEAR_STEPS("LINEAR_STEPS", 9), 
    MORPH_TARGET("MORPH_TARGET", 10), 
    MORPH_WEIGHT("MORPH_WEIGHT", 11), 
    NORMAL("NORMAL", 12), 
    OUTPUT("OUTPUT", 13), 
    OUT_TANGENT("OUT_TANGENT", 14), 
    POSITION("POSITION", 15), 
    TANGENT("TANGENT", 16), 
    TEXBINORMAL("TEXBINORMAL", 17), 
    TEXCOORD("TEXCOORD", 18), 
    TEXTANGENT("TEXTANGENT", 19), 
    UV("UV", 20), 
    VERTEX("VERTEX", 21), 
    WEIGHT("WEIGHT", 22);
    
    private CommonProfileInput(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static CommonProfileInput fromValue(final String v) {
        return valueOf(v);
    }
}
