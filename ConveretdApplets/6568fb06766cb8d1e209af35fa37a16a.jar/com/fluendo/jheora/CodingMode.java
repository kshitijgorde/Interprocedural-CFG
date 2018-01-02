// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class CodingMode
{
    private int value;
    public static final CodingMode CODE_INTER_NO_MV;
    public static final CodingMode CODE_INTRA;
    public static final CodingMode CODE_INTER_PLUS_MV;
    public static final CodingMode CODE_INTER_LAST_MV;
    public static final CodingMode CODE_INTER_PRIOR_LAST;
    public static final CodingMode CODE_USING_GOLDEN;
    public static final CodingMode CODE_GOLDEN_MV;
    public static final CodingMode CODE_INTER_FOURMV;
    public static final CodingMode[] MODES;
    
    private CodingMode(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    static {
        CODE_INTER_NO_MV = new CodingMode(0);
        CODE_INTRA = new CodingMode(1);
        CODE_INTER_PLUS_MV = new CodingMode(2);
        CODE_INTER_LAST_MV = new CodingMode(3);
        CODE_INTER_PRIOR_LAST = new CodingMode(4);
        CODE_USING_GOLDEN = new CodingMode(5);
        CODE_GOLDEN_MV = new CodingMode(6);
        CODE_INTER_FOURMV = new CodingMode(7);
        MODES = new CodingMode[] { CodingMode.CODE_INTER_NO_MV, CodingMode.CODE_INTRA, CodingMode.CODE_INTER_PLUS_MV, CodingMode.CODE_INTER_LAST_MV, CodingMode.CODE_INTER_PRIOR_LAST, CodingMode.CODE_USING_GOLDEN, CodingMode.CODE_GOLDEN_MV, CodingMode.CODE_INTER_FOURMV };
    }
}
