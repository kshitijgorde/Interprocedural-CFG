// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum CommonProfileParam
{
    A("A", 0), 
    ANGLE("ANGLE", 1), 
    B("B", 2), 
    DOUBLE_SIDED("DOUBLE_SIDED", 3), 
    G("G", 4), 
    P("P", 5), 
    Q("Q", 6), 
    R("R", 7), 
    S("S", 8), 
    T("T", 9), 
    TIME("TIME", 10), 
    U("U", 11), 
    V("V", 12), 
    W("W", 13), 
    X("X", 14), 
    Y("Y", 15), 
    Z("Z", 16);
    
    private CommonProfileParam(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static CommonProfileParam fromValue(final String v) {
        return valueOf(v);
    }
}
