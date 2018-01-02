// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NleExtractionStatus
{
    NONE(0), 
    TEMPLATE_CREATED(1), 
    FACE_NOT_DETECTED(2), 
    EYES_NOT_DETECTED(3), 
    FACE_TOO_CLOSE_TO_IMAGE_BORDER(4), 
    QUALITY_CHECK_GRAYSCALE_DENSITY_FAILED(100), 
    QUALITY_CHECK_EXPLOSURE_FAILED(101), 
    QUALITY_CHECK_SHARPNESS_FAILED(102), 
    LIVENESS_CHECK_FAILED(200), 
    GENERALIZATION_FAILED(300);
    
    private int value;
    private static final Map<Integer, NleExtractionStatus> lookup;
    
    private NleExtractionStatus(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NleExtractionStatus get(final int value) {
        return NleExtractionStatus.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NleExtractionStatus>();
        for (final NleExtractionStatus s : EnumSet.allOf(NleExtractionStatus.class)) {
            NleExtractionStatus.lookup.put(s.getValue(), s);
        }
    }
}
