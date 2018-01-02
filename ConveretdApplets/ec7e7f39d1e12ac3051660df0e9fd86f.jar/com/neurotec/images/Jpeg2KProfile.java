// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.images;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Jpeg2KProfile
{
    NONE(0), 
    FINGERPRINT_1000_PPI(1000), 
    FACE_LOSSY(2000), 
    FACE_LOSSLESS(2001);
    
    private int value;
    private static final Map<Integer, Jpeg2KProfile> lookup;
    
    private Jpeg2KProfile(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static Jpeg2KProfile get(final int value) {
        return Jpeg2KProfile.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, Jpeg2KProfile>();
        for (final Jpeg2KProfile s : EnumSet.allOf(Jpeg2KProfile.class)) {
            Jpeg2KProfile.lookup.put(s.getValue(), s);
        }
    }
}
