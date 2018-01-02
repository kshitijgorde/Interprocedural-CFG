// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class Colorspace
{
    private int colorSpace;
    public static final Colorspace UNSPECIFIED;
    public static final Colorspace ITU_REC_470M;
    public static final Colorspace ITU_REC_470BG;
    public static final Colorspace[] spaces;
    
    private Colorspace(final int colorSpace) {
        this.colorSpace = colorSpace;
    }
    
    static {
        UNSPECIFIED = new Colorspace(0);
        ITU_REC_470M = new Colorspace(1);
        ITU_REC_470BG = new Colorspace(2);
        spaces = new Colorspace[] { Colorspace.UNSPECIFIED, Colorspace.ITU_REC_470M, Colorspace.ITU_REC_470BG };
    }
}
