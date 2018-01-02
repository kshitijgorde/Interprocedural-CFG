// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.EnumSet;
import java.util.Map;

public enum NFMinutiaFormat
{
    HAS_QUALITY(1), 
    HAS_CURVATURE(2), 
    HAS_G(4);
    
    private int value;
    private static final Map<Integer, NFMinutiaFormat> lookup;
    
    private NFMinutiaFormat(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static int getValue(final EnumSet<NFMinutiaFormat> values) {
        int value = 0;
        for (final NFMinutiaFormat v : values) {
            value |= v.getValue();
        }
        return value;
    }
    
    public static EnumSet<NFMinutiaFormat> getSet(final int value) {
        final EnumSet<NFMinutiaFormat> values = EnumSet.noneOf(NFMinutiaFormat.class);
        for (final Map.Entry<Integer, NFMinutiaFormat> entry : NFMinutiaFormat.lookup.entrySet()) {
            if ((entry.getKey() & value) != 0x0) {
                values.add(entry.getValue());
            }
        }
        final EnumSet<NFMinutiaFormat> set = EnumSet.copyOf(values);
        if (value != getValue(set)) {
            throw new IllegalArgumentException("value contains unknown bit");
        }
        return set;
    }
    
    public static NFMinutiaFormat get(final int format) {
        return NFMinutiaFormat.lookup.get(format);
    }
    
    static {
        lookup = new HashMap<Integer, NFMinutiaFormat>();
        for (final NFMinutiaFormat s : EnumSet.allOf(NFMinutiaFormat.class)) {
            NFMinutiaFormat.lookup.put(s.getValue(), s);
        }
    }
}
