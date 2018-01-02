// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum IirImageTransformation
{
    UNDEFINED(0), 
    STANDARD(1);
    
    private int value;
    private static final Map<Integer, IirImageTransformation> lookup;
    
    private IirImageTransformation(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static IirImageTransformation get(final int value) {
        return IirImageTransformation.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, IirImageTransformation>();
        for (final IirImageTransformation s : EnumSet.allOf(IirImageTransformation.class)) {
            IirImageTransformation.lookup.put(s.getValue(), s);
        }
    }
}
