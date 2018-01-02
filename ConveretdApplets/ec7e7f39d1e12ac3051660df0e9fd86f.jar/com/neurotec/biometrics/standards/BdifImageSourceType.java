// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifImageSourceType
{
    UNSPECIFIED(0), 
    UNKNOWN_PHOTO(1), 
    DIGITAL_PHOTO(2), 
    SCANNED_PHOTO(3), 
    UNKNOWN_VIDEO(4), 
    ANALOGUE_VIDEO(5), 
    DIGITAL_VIDEO(6), 
    UNKNOWN(7), 
    VENDOR(128);
    
    private int value;
    private static final Map<Integer, BdifImageSourceType> lookup;
    
    private BdifImageSourceType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifImageSourceType get(final int value) {
        return BdifImageSourceType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifImageSourceType>();
        for (final BdifImageSourceType s : EnumSet.allOf(BdifImageSourceType.class)) {
            BdifImageSourceType.lookup.put(s.getValue(), s);
        }
    }
}
