// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

public enum NImageRotateType
{
    ROTATE_NONE(0), 
    ROTATE_90(1), 
    ROTATE_180(2), 
    ROTATE_270(3);
    
    private int value;
    
    private NImageRotateType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
