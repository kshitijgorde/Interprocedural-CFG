// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifIrisScanType
{
    UNDEFINED(0), 
    PROGRESSIVE(1), 
    INTERLACE_FRAME(2), 
    INTERLACE_FIELD(2), 
    CORRECTED(2);
    
    private int value;
    private static final Map<Integer, BdifIrisScanType> lookup;
    
    private BdifIrisScanType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifIrisScanType get(final int value) {
        return BdifIrisScanType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifIrisScanType>();
        for (final BdifIrisScanType s : EnumSet.allOf(BdifIrisScanType.class)) {
            BdifIrisScanType.lookup.put(s.getValue(), s);
        }
    }
}
