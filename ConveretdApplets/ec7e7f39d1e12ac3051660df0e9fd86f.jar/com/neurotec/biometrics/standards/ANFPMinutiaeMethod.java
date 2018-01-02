// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANFPMinutiaeMethod
{
    UNSPECIFIED(0), 
    CONTROLLED(1), 
    ASSISTED(2), 
    OBSERVED(3), 
    UNATTENDED(4), 
    UNKNOWN(255);
    
    private int value;
    private static final Map<Integer, ANFPMinutiaeMethod> lookup;
    
    private ANFPMinutiaeMethod(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANFPMinutiaeMethod get(final int value) {
        return ANFPMinutiaeMethod.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANFPMinutiaeMethod>();
        for (final ANFPMinutiaeMethod s : EnumSet.allOf(ANFPMinutiaeMethod.class)) {
            ANFPMinutiaeMethod.lookup.put(s.getValue(), s);
        }
    }
}
