// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum FcrImageColorSpace
{
    UNSPECIFIED(0), 
    RGB_24_BIT(1), 
    YUV_422(2), 
    GRAYSCALE_8_BIT(3), 
    OTHER(4), 
    VENDOR(128);
    
    private int value;
    private static final Map<Integer, FcrImageColorSpace> lookup;
    
    private FcrImageColorSpace(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static FcrImageColorSpace get(final int value) {
        return FcrImageColorSpace.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, FcrImageColorSpace>();
        for (final FcrImageColorSpace s : EnumSet.allOf(FcrImageColorSpace.class)) {
            FcrImageColorSpace.lookup.put(s.getValue(), s);
        }
    }
}
