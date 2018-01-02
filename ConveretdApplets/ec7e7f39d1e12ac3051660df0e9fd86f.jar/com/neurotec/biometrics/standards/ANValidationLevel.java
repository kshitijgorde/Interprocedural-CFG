// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANValidationLevel
{
    MINIMAL(0), 
    STANDARD(1);
    
    private int value;
    private static final Map<Integer, ANValidationLevel> lookup;
    
    private ANValidationLevel(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANValidationLevel get(final int value) {
        return ANValidationLevel.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANValidationLevel>();
        for (final ANValidationLevel s : EnumSet.allOf(ANValidationLevel.class)) {
            ANValidationLevel.lookup.put(s.getValue(), s);
        }
    }
}
