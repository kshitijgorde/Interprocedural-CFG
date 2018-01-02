// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NeeExtractionStatus
{
    SUCCEEDED(1), 
    TEMPLATE_CREATED(NeeExtractionStatus.SUCCEEDED.getValue()), 
    SEGMENTATION_FAILED(2), 
    QUALITY_CHECK_FAILED(100);
    
    private int value;
    private static final Map<Integer, NeeExtractionStatus> lookup;
    
    private NeeExtractionStatus(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NeeExtractionStatus get(final int value) {
        return NeeExtractionStatus.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NeeExtractionStatus>();
        for (final NeeExtractionStatus s : EnumSet.allOf(NeeExtractionStatus.class)) {
            NeeExtractionStatus.lookup.put(s.getValue(), s);
        }
    }
}
