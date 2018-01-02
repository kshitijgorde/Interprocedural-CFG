// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlesTexcombinerSourceEnums
{
    TEXTURE("TEXTURE", 0), 
    CONSTANT("CONSTANT", 1), 
    PRIMARY("PRIMARY", 2), 
    PREVIOUS("PREVIOUS", 3);
    
    private GlesTexcombinerSourceEnums(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlesTexcombinerSourceEnums fromValue(final String v) {
        return valueOf(v);
    }
}
