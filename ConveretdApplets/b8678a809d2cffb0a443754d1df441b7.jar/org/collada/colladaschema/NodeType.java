// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum NodeType
{
    JOINT("JOINT", 0), 
    NODE("NODE", 1);
    
    private NodeType(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static NodeType fromValue(final String v) {
        return valueOf(v);
    }
}
