// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANRecordDataType
{
    BINARY(0), 
    ASCII(1), 
    ASCII_BINARY(2);
    
    private int value;
    private static final Map<Integer, ANRecordDataType> lookup;
    
    private ANRecordDataType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANRecordDataType get(final int value) {
        return ANRecordDataType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANRecordDataType>();
        for (final ANRecordDataType s : EnumSet.allOf(ANRecordDataType.class)) {
            ANRecordDataType.lookup.put(s.getValue(), s);
        }
    }
}
