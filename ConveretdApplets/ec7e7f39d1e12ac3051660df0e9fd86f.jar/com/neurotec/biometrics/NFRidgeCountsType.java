// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NFRidgeCountsType
{
    NONE(0), 
    FOUR_NEIGHBORS(1), 
    EIGHT_NEIGHBORS(2), 
    FOUR_NEIGHBORS_WITH_INDEXEX(5), 
    EIGHT_NEIGHBORS_WITH_INDEXEX(6), 
    UNSPECIFIED(132);
    
    private int value;
    private static final Map<Integer, NFRidgeCountsType> lookup;
    
    private NFRidgeCountsType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NFRidgeCountsType get(final int value) {
        return NFRidgeCountsType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NFRidgeCountsType>();
        for (final NFRidgeCountsType s : EnumSet.allOf(NFRidgeCountsType.class)) {
            NFRidgeCountsType.lookup.put(s.getValue(), s);
        }
    }
}
