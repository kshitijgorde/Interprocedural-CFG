// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NFMinutiaOrder
{
    ASCENDING(1), 
    DESCENDING(2), 
    CARTESIAN_XY(4), 
    CARTESIAN_YX(8), 
    ANGLE(0), 
    POLAR(16);
    
    private int value;
    private static final Map<Integer, NFMinutiaOrder> lookup;
    
    private NFMinutiaOrder(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NFMinutiaOrder get(final int format) {
        return NFMinutiaOrder.lookup.get(format);
    }
    
    static {
        lookup = new HashMap<Integer, NFMinutiaOrder>();
        for (final NFMinutiaOrder s : EnumSet.allOf(NFMinutiaOrder.class)) {
            NFMinutiaOrder.lookup.put(s.getValue(), s);
        }
    }
}
