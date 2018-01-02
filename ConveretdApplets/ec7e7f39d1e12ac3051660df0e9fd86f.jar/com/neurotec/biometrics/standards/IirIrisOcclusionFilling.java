// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IirIrisOcclusionFilling
{
    ZERO(0), 
    UNIT(1);
    
    private int value;
    private static final Map<Integer, IirIrisOcclusionFilling> lookup;
    
    private IirIrisOcclusionFilling(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static IirIrisOcclusionFilling get(final int value) {
        return IirIrisOcclusionFilling.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, IirIrisOcclusionFilling>();
        for (final IirIrisOcclusionFilling s : EnumSet.allOf(IirIrisOcclusionFilling.class)) {
            IirIrisOcclusionFilling.lookup.put(s.getValue(), s);
        }
    }
}
