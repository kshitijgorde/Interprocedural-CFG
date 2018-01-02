// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum GlslPipelineStage
{
    VERTEXPROGRAM("VERTEXPROGRAM", 0), 
    FRAGMENTPROGRAM("FRAGMENTPROGRAM", 1);
    
    private GlslPipelineStage(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static GlslPipelineStage fromValue(final String v) {
        return valueOf(v);
    }
}
