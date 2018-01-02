// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifFPatternClass
{
    UNKNOWN(0), 
    PLAIN_ARCH(1), 
    TENTED_ARCH(2), 
    RADIAL_LOOP(3), 
    ULNAR_LOOP(4), 
    PLAIN_WHORL(5), 
    CENTRAL_POCKET_LOOP(6), 
    DOUBLE_LOOP(7), 
    ACCIDENTAL_WHORL(8), 
    WHORL(9), 
    RIGHT_SLANT_LOOP(10), 
    LEFT_SLANT_LOOP(11), 
    SCAR(12), 
    AMPUTATION(15);
    
    private int value;
    private static final Map<Integer, BdifFPatternClass> lookup;
    
    private BdifFPatternClass(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifFPatternClass get(final int value) {
        return BdifFPatternClass.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifFPatternClass>();
        for (final BdifFPatternClass s : EnumSet.allOf(BdifFPatternClass.class)) {
            BdifFPatternClass.lookup.put(s.getValue(), s);
        }
    }
}
