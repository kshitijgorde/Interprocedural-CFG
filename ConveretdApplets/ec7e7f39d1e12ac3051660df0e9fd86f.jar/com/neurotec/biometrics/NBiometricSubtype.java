// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.util.Iterator;
import java.util.EnumSet;
import java.util.Map;

public enum NBiometricSubtype
{
    LEFT(1), 
    RIGHT(2), 
    LEFT_THUMB(4), 
    LEFT_POINTER_FINGER(8), 
    LEFT_MIDDLE_FINGER(16), 
    LEFT_RING_FINGER(32), 
    LEFT_LITTLE_FINGER(64), 
    RIGHT_THUMB(128), 
    RIGHT_POINTER_FINGER(256), 
    RIGHT_MIDDLE_FINGER(512), 
    RIGHT_RING_FINGER(1024), 
    RIGHT_LITTLE_FINGER(2048), 
    LEFT_PALM(4096), 
    LEFT_BACK_OF_HAND(8192), 
    LEFT_WRIST(16384), 
    RIGHT_PALM(32768), 
    RIGHT_BACK_OF_HAND(65536), 
    RIGHT_WRIST(131072);
    
    private int value;
    private static final NBiometricSubtypeLibrary LIBRARY;
    private static final Map<Integer, NBiometricSubtype> lookup;
    
    private NBiometricSubtype(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NBiometricSubtype get(final int value) {
        return NBiometricSubtype.lookup.get(value);
    }
    
    public static int getValue(final EnumSet<NBiometricSubtype> values) {
        int value = 0;
        for (final NBiometricSubtype v : values) {
            value |= v.getValue();
        }
        return value;
    }
    
    public static EnumSet<NBiometricSubtype> getSet(final int value) {
        final EnumSet<NBiometricSubtype> values = EnumSet.noneOf(NBiometricSubtype.class);
        for (final Map.Entry<Integer, NBiometricSubtype> entry : NBiometricSubtype.lookup.entrySet()) {
            if ((entry.getKey() & value) == entry.getKey()) {
                values.add(entry.getValue());
            }
        }
        if (value != getValue(values)) {
            throw new IllegalArgumentException("value contains unknown bit");
        }
        return values;
    }
    
    public static boolean isValid(final EnumSet<NBiometricSubtype> value) {
        return NBiometricSubtype.LIBRARY.NBiometricSubtypeIsValid(getValue(value));
    }
    
    static {
        LIBRARY = (NBiometricSubtypeLibrary)Native.loadLibrary("NBiometrics", NBiometricSubtypeLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        lookup = new HashMap<Integer, NBiometricSubtype>();
        for (final NBiometricSubtype s : EnumSet.allOf(NBiometricSubtype.class)) {
            NBiometricSubtype.lookup.put(s.getValue(), s);
        }
    }
    
    interface NBiometricSubtypeLibrary extends NLibrary
    {
        boolean NBiometricSubtypeIsValid(final int p0);
    }
}
