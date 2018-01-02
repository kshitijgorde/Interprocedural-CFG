// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NMFusionType
{
    FUSE_ALWAYS(0), 
    SELECT_BY_FACE_THEN_FUSE(1), 
    SELECT_BY_IRIS_THEN_FUSE(2);
    
    private int value;
    private static final Map<Integer, NMFusionType> lookup;
    
    private NMFusionType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NMFusionType get(final int value) {
        return NMFusionType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NMFusionType>();
        for (final NMFusionType s : EnumSet.allOf(NMFusionType.class)) {
            NMFusionType.lookup.put(s.getValue(), s);
        }
    }
}
