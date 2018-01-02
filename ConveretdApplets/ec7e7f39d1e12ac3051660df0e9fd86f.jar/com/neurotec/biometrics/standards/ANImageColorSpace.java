// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANImageColorSpace
{
    UNSPECIFIED(0), 
    GRAY(1), 
    RGB(2), 
    SRGB(3), 
    YCC(4), 
    SYCC(5), 
    UNKNOWN(255);
    
    private int value;
    private static final Map<Integer, ANImageColorSpace> lookup;
    
    private ANImageColorSpace(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANImageColorSpace get(final int value) {
        return ANImageColorSpace.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANImageColorSpace>();
        for (final ANImageColorSpace s : EnumSet.allOf(ANImageColorSpace.class)) {
            ANImageColorSpace.lookup.put(s.getValue(), s);
        }
    }
}
