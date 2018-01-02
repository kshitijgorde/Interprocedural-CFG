// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANTattooClass
{
    HUMAN(0), 
    ANIMAL(1), 
    PLANT(2), 
    FLAG(3), 
    OBJECT(4), 
    ABSTRACT(5), 
    SYMBOL(6), 
    OTHER(7);
    
    private int value;
    private static final Map<Integer, ANTattooClass> lookup;
    
    private ANTattooClass(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANTattooClass get(final int value) {
        return ANTattooClass.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANTattooClass>();
        for (final ANTattooClass s : EnumSet.allOf(ANTattooClass.class)) {
            ANTattooClass.lookup.put(s.getValue(), s);
        }
    }
}
