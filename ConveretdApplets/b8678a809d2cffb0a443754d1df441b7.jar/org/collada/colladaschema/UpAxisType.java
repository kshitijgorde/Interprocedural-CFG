// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum UpAxisType
{
    X_UP("X_UP", 0), 
    Y_UP("Y_UP", 1), 
    Z_UP("Z_UP", 2);
    
    private UpAxisType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static UpAxisType fromValue(final String v) {
        return valueOf(v);
    }
}
