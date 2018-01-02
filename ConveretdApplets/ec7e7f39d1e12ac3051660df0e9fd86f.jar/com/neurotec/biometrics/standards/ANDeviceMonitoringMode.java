// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANDeviceMonitoringMode
{
    UNSPECIFIED(0), 
    CONTROLLED(1), 
    ASSISTED(2), 
    OBSERVED(3), 
    UNATTENDED(4), 
    UNKNOWN(255);
    
    private int value;
    private static final Map<Integer, ANDeviceMonitoringMode> lookup;
    
    private ANDeviceMonitoringMode(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANDeviceMonitoringMode get(final int value) {
        return ANDeviceMonitoringMode.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANDeviceMonitoringMode>();
        for (final ANDeviceMonitoringMode s : EnumSet.allOf(ANDeviceMonitoringMode.class)) {
            ANDeviceMonitoringMode.lookup.put(s.getValue(), s);
        }
    }
}
