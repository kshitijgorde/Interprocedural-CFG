// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANSignatureRepresentationType
{
    SCANNED_UNCOMPRESSED(0), 
    SCANNED_COMPRESSED(1), 
    VECTOR_DATA(2);
    
    private int value;
    private static final Map<Integer, ANSignatureRepresentationType> lookup;
    
    private ANSignatureRepresentationType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANSignatureRepresentationType get(final int value) {
        return ANSignatureRepresentationType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANSignatureRepresentationType>();
        for (final ANSignatureRepresentationType s : EnumSet.allOf(ANSignatureRepresentationType.class)) {
            ANSignatureRepresentationType.lookup.put(s.getValue(), s);
        }
    }
}
