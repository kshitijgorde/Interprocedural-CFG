// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifFPMinutiaType
{
    UNSPECIFIED(-1), 
    UNKNOWN(0), 
    END(1), 
    BIFURCATION(2), 
    OTHER(3);
    
    private int value;
    private static final Map<Integer, BdifFPMinutiaType> lookup;
    
    private BdifFPMinutiaType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifFPMinutiaType get(final int value) {
        return BdifFPMinutiaType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifFPMinutiaType>();
        for (final BdifFPMinutiaType s : EnumSet.allOf(BdifFPMinutiaType.class)) {
            BdifFPMinutiaType.lookup.put(s.getValue(), s);
        }
    }
}
