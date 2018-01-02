// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NMSpeed
{
    LOW(0), 
    MEDIUM(128), 
    HIGH(256);
    
    private int value;
    private static final Map<Integer, NMSpeed> lookup;
    
    private NMSpeed(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NMSpeed get(final int value) {
        return NMSpeed.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NMSpeed>();
        for (final NMSpeed s : EnumSet.allOf(NMSpeed.class)) {
            NMSpeed.lookup.put(s.getValue(), s);
        }
    }
}
