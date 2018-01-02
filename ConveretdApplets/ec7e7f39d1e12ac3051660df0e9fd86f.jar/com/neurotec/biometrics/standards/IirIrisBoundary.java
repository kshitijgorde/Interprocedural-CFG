// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IirIrisBoundary
{
    UNDEFINED(0), 
    PROCESSED(1);
    
    private int value;
    private static final Map<Integer, IirIrisBoundary> lookup;
    
    private IirIrisBoundary(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static IirIrisBoundary get(final int value) {
        return IirIrisBoundary.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, IirIrisBoundary>();
        for (final IirIrisBoundary s : EnumSet.allOf(IirIrisBoundary.class)) {
            IirIrisBoundary.lookup.put(s.getValue(), s);
        }
    }
}
