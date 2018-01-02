// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANColor
{
    BLACK(1), 
    BROWN(2), 
    GRAY(3), 
    BLUE(4), 
    GREEN(5), 
    ORANGE(6), 
    PURPLE(7), 
    RED(8), 
    YELLOW(9), 
    WHITE(10), 
    MULTICOLORED(11), 
    OUTLINED(12);
    
    private int value;
    private static final Map<Integer, ANColor> lookup;
    
    private ANColor(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANColor get(final int value) {
        return ANColor.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANColor>();
        for (final ANColor s : EnumSet.allOf(ANColor.class)) {
            ANColor.lookup.put(s.getValue(), s);
        }
    }
}
