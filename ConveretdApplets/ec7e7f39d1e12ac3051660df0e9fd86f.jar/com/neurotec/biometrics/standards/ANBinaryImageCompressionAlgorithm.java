// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANBinaryImageCompressionAlgorithm
{
    NONE(0), 
    FACSIMILE(1), 
    VENDOR(255);
    
    private int value;
    private static final Map<Integer, ANBinaryImageCompressionAlgorithm> lookup;
    
    private ANBinaryImageCompressionAlgorithm(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANBinaryImageCompressionAlgorithm get(final int value) {
        return ANBinaryImageCompressionAlgorithm.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANBinaryImageCompressionAlgorithm>();
        for (final ANBinaryImageCompressionAlgorithm s : EnumSet.allOf(ANBinaryImageCompressionAlgorithm.class)) {
            ANBinaryImageCompressionAlgorithm.lookup.put(s.getValue(), s);
        }
    }
}
