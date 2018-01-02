// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifHairColor
{
    UNSPECIFIED(0), 
    BALD(1), 
    BLACK(2), 
    BLONDE(3), 
    BROWN(4), 
    GRAY(5), 
    RED(6), 
    BLUE(7), 
    GREEN(8), 
    ORANGE(9), 
    PINK(10), 
    PURPLE(11), 
    SANDY(12), 
    AUBURN(13), 
    WHITE(14), 
    STRAWBERRY(15), 
    UNKNOWN(255);
    
    private int value;
    private static final Map<Integer, BdifHairColor> lookup;
    
    private BdifHairColor(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifHairColor get(final int value) {
        return BdifHairColor.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifHairColor>();
        for (final BdifHairColor s : EnumSet.allOf(BdifHairColor.class)) {
            BdifHairColor.lookup.put(s.getValue(), s);
        }
    }
}
