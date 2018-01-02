// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NFMinutiaType
{
    UNKNOWN(0), 
    END(1), 
    BIFURCATION(2), 
    OTHER(3);
    
    private int value;
    private static final Map<Integer, NFMinutiaType> lookup;
    
    private NFMinutiaType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NFMinutiaType get(final int value) {
        return NFMinutiaType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NFMinutiaType>();
        for (final NFMinutiaType s : EnumSet.allOf(NFMinutiaType.class)) {
            NFMinutiaType.lookup.put(s.getValue(), s);
        }
    }
}
