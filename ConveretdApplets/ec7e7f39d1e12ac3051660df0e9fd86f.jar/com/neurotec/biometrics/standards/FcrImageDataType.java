// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum FcrImageDataType
{
    JPEG(0), 
    JPEG_2000(1);
    
    private int value;
    private static final Map<Integer, FcrImageDataType> lookup;
    
    private FcrImageDataType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static FcrImageDataType get(final int value) {
        return FcrImageDataType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, FcrImageDataType>();
        for (final FcrImageDataType s : EnumSet.allOf(FcrImageDataType.class)) {
            FcrImageDataType.lookup.put(s.getValue(), s);
        }
    }
}
