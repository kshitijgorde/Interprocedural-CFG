// 
// Decompiled by Procyon v0.5.30
// 

package org.collada.colladaschema;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum FxPipelineStageCommon
{
    VERTEXPROGRAM("VERTEXPROGRAM", 0), 
    FRAGMENTPROGRAM("FRAGMENTPROGRAM", 1), 
    VERTEXSHADER("VERTEXSHADER", 2), 
    PIXELSHADER("PIXELSHADER", 3);
    
    private FxPipelineStageCommon(final String s, final int n) {
    }
    
    public String value() {
        return this.name();
    }
    
    public static FxPipelineStageCommon fromValue(final String v) {
        return valueOf(v);
    }
}
