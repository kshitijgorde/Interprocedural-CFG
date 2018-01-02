// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NleTemplateSize
{
    SMALL(0), 
    COMPACT(1), 
    MEDIUM(64), 
    LARGE(128);
    
    private int value;
    private static final Map<Integer, NleTemplateSize> lookup;
    
    private NleTemplateSize(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NleTemplateSize get(final int value) {
        return NleTemplateSize.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NleTemplateSize>();
        for (final NleTemplateSize s : EnumSet.allOf(NleTemplateSize.class)) {
            NleTemplateSize.lookup.put(s.getValue(), s);
        }
    }
}
