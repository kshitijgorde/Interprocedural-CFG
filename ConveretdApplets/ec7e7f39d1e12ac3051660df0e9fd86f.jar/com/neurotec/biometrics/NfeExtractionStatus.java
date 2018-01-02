// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NfeExtractionStatus
{
    TEMPLATE_CREATED(1), 
    TOO_FEW_MINUTIAE(90), 
    QUALITY_CHECK_FAILED(100), 
    MATCHING_FAILED(200);
    
    private int value;
    private static final Map<Integer, NfeExtractionStatus> lookup;
    
    private NfeExtractionStatus(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NfeExtractionStatus get(final int value) {
        return NfeExtractionStatus.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NfeExtractionStatus>();
        for (final NfeExtractionStatus s : EnumSet.allOf(NfeExtractionStatus.class)) {
            NfeExtractionStatus.lookup.put(s.getValue(), s);
        }
    }
}
