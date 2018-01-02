// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifScaleUnits
{
    NONE(0), 
    PIXELS_PER_INCH(1), 
    PIXELS_PER_CENTIMETER(2);
    
    private int value;
    private static final Map<Integer, BdifScaleUnits> lookup;
    
    private BdifScaleUnits(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifScaleUnits get(final int value) {
        return BdifScaleUnits.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifScaleUnits>();
        for (final BdifScaleUnits s : EnumSet.allOf(BdifScaleUnits.class)) {
            BdifScaleUnits.lookup.put(s.getValue(), s);
        }
    }
}
