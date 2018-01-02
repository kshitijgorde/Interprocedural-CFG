// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifGender
{
    UNSPECIFIED(0), 
    MALE(1), 
    FEMALE(2), 
    UNKNOWN(255);
    
    private int value;
    private static final Map<Integer, BdifGender> lookup;
    
    private BdifGender(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifGender get(final int value) {
        return BdifGender.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifGender>();
        for (final BdifGender s : EnumSet.allOf(BdifGender.class)) {
            BdifGender.lookup.put(s.getValue(), s);
        }
    }
}
