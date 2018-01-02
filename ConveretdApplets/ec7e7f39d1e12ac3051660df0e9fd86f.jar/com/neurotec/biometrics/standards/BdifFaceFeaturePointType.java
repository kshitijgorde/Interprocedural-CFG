// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifFaceFeaturePointType
{
    POINT_2D(0);
    
    private int value;
    private static final Map<Integer, BdifFaceFeaturePointType> lookup;
    
    private BdifFaceFeaturePointType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifFaceFeaturePointType get(final int value) {
        return BdifFaceFeaturePointType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifFaceFeaturePointType>();
        for (final BdifFaceFeaturePointType s : EnumSet.allOf(BdifFaceFeaturePointType.class)) {
            BdifFaceFeaturePointType.lookup.put(s.getValue(), s);
        }
    }
}
