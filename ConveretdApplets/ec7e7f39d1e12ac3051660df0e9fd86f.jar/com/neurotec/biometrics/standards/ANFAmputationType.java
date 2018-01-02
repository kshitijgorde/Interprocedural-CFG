// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANFAmputationType
{
    AMPUTATION(0), 
    UNABLE_TO_PRINT(1);
    
    private int value;
    private static final Map<Integer, ANFAmputationType> lookup;
    
    private ANFAmputationType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANFAmputationType get(final int value) {
        return ANFAmputationType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANFAmputationType>();
        for (final ANFAmputationType s : EnumSet.allOf(ANFAmputationType.class)) {
            ANFAmputationType.lookup.put(s.getValue(), s);
        }
    }
}
