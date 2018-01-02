// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NeeImageKind
{
    UNCROPPED(1), 
    VGA(2), 
    CROPPED(3), 
    CROPPED_AND_MASKED(4);
    
    private int value;
    private static final Map<Integer, NeeImageKind> lookup;
    
    private NeeImageKind(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NeeImageKind get(final int value) {
        return NeeImageKind.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NeeImageKind>();
        for (final NeeImageKind s : EnumSet.allOf(NeeImageKind.class)) {
            NeeImageKind.lookup.put(s.getValue(), s);
        }
    }
}
