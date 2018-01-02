// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum FmrCaptureEquipmentCompliance
{
    NONE(0), 
    FBI(128), 
    ISO(1);
    
    private int value;
    private static final Map<Integer, FmrCaptureEquipmentCompliance> lookup;
    
    private FmrCaptureEquipmentCompliance(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static FmrCaptureEquipmentCompliance get(final int value) {
        return FmrCaptureEquipmentCompliance.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, FmrCaptureEquipmentCompliance>();
        for (final FmrCaptureEquipmentCompliance s : EnumSet.allOf(FmrCaptureEquipmentCompliance.class)) {
            FmrCaptureEquipmentCompliance.lookup.put(s.getValue(), s);
        }
    }
}
