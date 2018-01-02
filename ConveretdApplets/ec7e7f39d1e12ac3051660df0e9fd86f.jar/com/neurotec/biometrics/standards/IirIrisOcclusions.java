// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IirIrisOcclusions
{
    UNDEFINED(0), 
    BASE(1);
    
    private int value;
    private static final Map<Integer, IirIrisOcclusions> lookup;
    
    private IirIrisOcclusions(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static IirIrisOcclusions get(final int value) {
        return IirIrisOcclusions.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, IirIrisOcclusions>();
        for (final IirIrisOcclusions s : EnumSet.allOf(IirIrisOcclusions.class)) {
            IirIrisOcclusions.lookup.put(s.getValue(), s);
        }
    }
}
