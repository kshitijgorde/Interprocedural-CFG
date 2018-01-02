// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANImageCompressionAlgorithm
{
    NONE(0), 
    WSQ20(1), 
    JPEGB(2), 
    JPEGL(3), 
    JP2(4), 
    JP2L(5), 
    PNG(6), 
    VENDOR(255);
    
    private int value;
    private static final Map<Integer, ANImageCompressionAlgorithm> lookup;
    
    private ANImageCompressionAlgorithm(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANImageCompressionAlgorithm get(final int value) {
        return ANImageCompressionAlgorithm.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANImageCompressionAlgorithm>();
        for (final ANImageCompressionAlgorithm s : EnumSet.allOf(ANImageCompressionAlgorithm.class)) {
            ANImageCompressionAlgorithm.lookup.put(s.getValue(), s);
        }
    }
}
