// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANImageType
{
    FACE(0), 
    SCAR(1), 
    MARK(2), 
    TATTOO(3), 
    OTHER(255);
    
    private int value;
    private static final Map<Integer, ANImageType> lookup;
    
    private ANImageType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANImageType get(final int value) {
        return ANImageType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANImageType>();
        for (final ANImageType s : EnumSet.allOf(ANImageType.class)) {
            ANImageType.lookup.put(s.getValue(), s);
        }
    }
}
