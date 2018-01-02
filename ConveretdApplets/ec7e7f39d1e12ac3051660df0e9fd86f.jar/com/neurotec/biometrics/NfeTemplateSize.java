// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NfeTemplateSize
{
    SMALL(0), 
    LARGE(256);
    
    private int value;
    private static final Map<Integer, NfeTemplateSize> lookup;
    
    private NfeTemplateSize(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NfeTemplateSize get(final int value) {
        return NfeTemplateSize.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NfeTemplateSize>();
        for (final NfeTemplateSize s : EnumSet.allOf(NfeTemplateSize.class)) {
            NfeTemplateSize.lookup.put(s.getValue(), s);
        }
    }
}
