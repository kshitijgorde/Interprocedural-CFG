// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANSignatureType
{
    SUBJECT(0), 
    OFFICIAL(1);
    
    private int value;
    private static final Map<Integer, ANSignatureType> lookup;
    
    private ANSignatureType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANSignatureType get(final int value) {
        return ANSignatureType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANSignatureType>();
        for (final ANSignatureType s : EnumSet.allOf(ANSignatureType.class)) {
            ANSignatureType.lookup.put(s.getValue(), s);
        }
    }
}
