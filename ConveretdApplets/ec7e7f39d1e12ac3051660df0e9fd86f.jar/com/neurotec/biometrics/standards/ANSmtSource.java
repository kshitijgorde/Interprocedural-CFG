// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANSmtSource
{
    SCAR(0), 
    MARK(1), 
    TATTOO(2), 
    CHEMICAL(3), 
    BRANDED(4), 
    CUT(5);
    
    private int value;
    private static final Map<Integer, ANSmtSource> lookup;
    
    private ANSmtSource(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANSmtSource get(final int value) {
        return ANSmtSource.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANSmtSource>();
        for (final ANSmtSource s : EnumSet.allOf(ANSmtSource.class)) {
            ANSmtSource.lookup.put(s.getValue(), s);
        }
    }
}
