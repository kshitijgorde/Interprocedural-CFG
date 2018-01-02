// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANFMajorCase
{
    NA(0), 
    EJI(1), 
    TIP(2), 
    FV1(3), 
    FV2(4), 
    FV3(5), 
    FV4(6), 
    PRX(7), 
    DST(8), 
    MED(9);
    
    private int value;
    private static final Map<Integer, ANFMajorCase> lookup;
    
    private ANFMajorCase(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANFMajorCase get(final int value) {
        return ANFMajorCase.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANFMajorCase>();
        for (final ANFMajorCase s : EnumSet.allOf(ANFMajorCase.class)) {
            ANFMajorCase.lookup.put(s.getValue(), s);
        }
    }
}
