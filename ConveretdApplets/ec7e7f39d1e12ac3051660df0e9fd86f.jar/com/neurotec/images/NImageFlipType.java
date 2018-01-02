// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import java.util.Iterator;
import java.util.EnumSet;

public enum NImageFlipType
{
    FLIP_NONE(0), 
    FLIP_X(4), 
    FLIP_Y(8);
    
    public static final EnumSet<NImageFlipType> FLIP_XY;
    private int value;
    
    private NImageFlipType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static int getValue(final EnumSet<NImageFlipType> values) {
        int value = 0;
        for (final NImageFlipType v : values) {
            value |= v.getValue();
        }
        return value;
    }
    
    static {
        FLIP_XY = EnumSet.of(NImageFlipType.FLIP_X, NImageFlipType.FLIP_Y);
    }
}
