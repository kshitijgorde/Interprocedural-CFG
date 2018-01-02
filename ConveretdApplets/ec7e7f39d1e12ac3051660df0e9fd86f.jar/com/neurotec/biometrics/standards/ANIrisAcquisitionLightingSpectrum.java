// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANIrisAcquisitionLightingSpectrum
{
    UNSPECIFIED(0), 
    NIR(1), 
    VIS(2), 
    OTHER(255);
    
    private int value;
    private static final Map<Integer, ANIrisAcquisitionLightingSpectrum> lookup;
    
    private ANIrisAcquisitionLightingSpectrum(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANIrisAcquisitionLightingSpectrum get(final int value) {
        return ANIrisAcquisitionLightingSpectrum.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANIrisAcquisitionLightingSpectrum>();
        for (final ANIrisAcquisitionLightingSpectrum s : EnumSet.allOf(ANIrisAcquisitionLightingSpectrum.class)) {
            ANIrisAcquisitionLightingSpectrum.lookup.put(s.getValue(), s);
        }
    }
}
