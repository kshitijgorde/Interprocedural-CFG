// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifIrisOrientation
{
    UNDEFINED(0), 
    BASE(1), 
    FLIPPED(2);
    
    private int value;
    private static final Map<Integer, BdifIrisOrientation> lookup;
    
    private BdifIrisOrientation(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifIrisOrientation get(final int value) {
        return BdifIrisOrientation.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifIrisOrientation>();
        for (final BdifIrisOrientation s : EnumSet.allOf(BdifIrisOrientation.class)) {
            BdifIrisOrientation.lookup.put(s.getValue(), s);
        }
    }
}
