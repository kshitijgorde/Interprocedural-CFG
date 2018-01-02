// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.devices;

import java.util.HashMap;
import java.util.Iterator;
import java.util.EnumSet;
import java.util.Map;

public enum NDeviceType
{
    CAMERA(1), 
    BIOMETRIC_DEVICE(2), 
    FSCANNER(4), 
    FINGER_SCANNER(8), 
    PALM_SCANNER(16), 
    IRIS_SCANNER(32), 
    ANY(Integer.MIN_VALUE);
    
    private int value;
    private static final Map<Integer, NDeviceType> lookup;
    
    private NDeviceType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NDeviceType get(final int value) {
        return NDeviceType.lookup.get(value);
    }
    
    public static int getValue(final EnumSet<NDeviceType> values) {
        int value = 0;
        for (final NDeviceType v : values) {
            value |= v.getValue();
        }
        return value;
    }
    
    public static EnumSet<NDeviceType> getSet(final int value) {
        final EnumSet<NDeviceType> values = EnumSet.noneOf(NDeviceType.class);
        for (final Map.Entry<Integer, NDeviceType> entry : NDeviceType.lookup.entrySet()) {
            if ((entry.getKey() & value) == entry.getKey()) {
                values.add(entry.getValue());
            }
        }
        if (value != getValue(values)) {
            throw new IllegalArgumentException("value contains unknown bit");
        }
        return values;
    }
    
    static {
        lookup = new HashMap<Integer, NDeviceType>();
        for (final NDeviceType s : EnumSet.allOf(NDeviceType.class)) {
            NDeviceType.lookup.put(s.getValue(), s);
        }
    }
}
