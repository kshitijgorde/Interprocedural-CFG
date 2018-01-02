// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class Colorspace
{
    public static final Colorspace UNSPECIFIED;
    public static final Colorspace ITU_REC_470M;
    public static final Colorspace ITU_REC_470BG;
    public static final Colorspace[] spaces;
    
    static {
        UNSPECIFIED = new Colorspace();
        ITU_REC_470M = new Colorspace();
        ITU_REC_470BG = new Colorspace();
        spaces = new Colorspace[] { Colorspace.UNSPECIFIED, Colorspace.ITU_REC_470M, Colorspace.ITU_REC_470BG };
    }
}
