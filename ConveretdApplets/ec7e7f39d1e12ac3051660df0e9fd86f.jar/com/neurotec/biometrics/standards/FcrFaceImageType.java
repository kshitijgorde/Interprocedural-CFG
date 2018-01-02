// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum FcrFaceImageType
{
    BASIC(0), 
    FULL_FRONTAL(1), 
    TOKEN_FRONTAL(2), 
    OTHER(3);
    
    private int value;
    private static final Map<Integer, FcrFaceImageType> lookup;
    
    private FcrFaceImageType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static FcrFaceImageType get(final int value) {
        return FcrFaceImageType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, FcrFaceImageType>();
        for (final FcrFaceImageType s : EnumSet.allOf(FcrFaceImageType.class)) {
            FcrFaceImageType.lookup.put(s.getValue(), s);
        }
    }
}
