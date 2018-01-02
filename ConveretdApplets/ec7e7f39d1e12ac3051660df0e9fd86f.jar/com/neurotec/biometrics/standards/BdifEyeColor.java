// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifEyeColor
{
    UNSPECIFIED(0), 
    BLACK(1), 
    BLUE(2), 
    BROWN(3), 
    GRAY(4), 
    GREEN(5), 
    HAZEL(6), 
    MAROON(7), 
    MULTICOLORED(8), 
    PINK(9), 
    UNKNOWN(255);
    
    private int value;
    private static final Map<Integer, BdifEyeColor> lookup;
    
    private BdifEyeColor(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifEyeColor get(final int value) {
        return BdifEyeColor.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifEyeColor>();
        for (final BdifEyeColor s : EnumSet.allOf(BdifEyeColor.class)) {
            BdifEyeColor.lookup.put(s.getValue(), s);
        }
    }
}
