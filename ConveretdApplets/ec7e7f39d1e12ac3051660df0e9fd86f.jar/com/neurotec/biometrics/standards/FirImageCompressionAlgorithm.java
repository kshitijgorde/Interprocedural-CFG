// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum FirImageCompressionAlgorithm
{
    NOT_BIT_PACKED(0), 
    BIT_PACKED(1), 
    WSG(2), 
    JPEG(3), 
    JPEG2000(4), 
    PNG(5);
    
    private int value;
    private static final Map<Integer, FirImageCompressionAlgorithm> lookup;
    
    private FirImageCompressionAlgorithm(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static FirImageCompressionAlgorithm get(final int value) {
        return FirImageCompressionAlgorithm.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, FirImageCompressionAlgorithm>();
        for (final FirImageCompressionAlgorithm s : EnumSet.allOf(FirImageCompressionAlgorithm.class)) {
            FirImageCompressionAlgorithm.lookup.put(s.getValue(), s);
        }
    }
}
