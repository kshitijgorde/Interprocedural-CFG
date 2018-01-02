// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum CgPipelineStage
{
    VERTEX("VERTEX", 0), 
    FRAGMENT("FRAGMENT", 1);
    
    private CgPipelineStage(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static CgPipelineStage fromValue(final String v) {
        return valueOf(v);
    }
}
