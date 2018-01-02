// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.tools;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NfiqQuality
{
    POOR(5), 
    FAIR(4), 
    GOOD(3), 
    VERY_GOOD(2), 
    EXCELLENT(1);
    
    private int value;
    private static final Map<Integer, NfiqQuality> lookup;
    
    private NfiqQuality(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NfiqQuality get(final int value) {
        return NfiqQuality.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NfiqQuality>();
        for (final NfiqQuality s : EnumSet.allOf(NfiqQuality.class)) {
            NfiqQuality.lookup.put(s.getValue(), s);
        }
    }
}
