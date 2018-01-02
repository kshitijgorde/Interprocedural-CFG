// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifEyePosition
{
    UNKNOWN(0), 
    RIGHT(1), 
    LEFT(2);
    
    private int value;
    private static final Map<Integer, BdifEyePosition> lookup;
    
    private BdifEyePosition(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifEyePosition get(final int value) {
        return BdifEyePosition.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifEyePosition>();
        for (final BdifEyePosition s : EnumSet.allOf(BdifEyePosition.class)) {
            BdifEyePosition.lookup.put(s.getValue(), s);
        }
    }
}
