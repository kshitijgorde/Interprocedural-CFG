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

public enum NBiometricType
{
    MULTIPLE_BIOMETRICS(1), 
    FACE(2), 
    VOICE(4), 
    FINGER(8), 
    IRIS(16), 
    RETINA(32), 
    HAND_GEOMETRY(64), 
    SIGNATURE_OR_SIGN(128), 
    KEYSTROKE(256), 
    LIP_MOVEMENT(512), 
    THERMAL_FACE(1024), 
    THERMAL_HAND(2048), 
    GAIT(4096), 
    SCENT(8192), 
    DNA(16384), 
    EAR(32768), 
    FINGER_GEOMETRY(65536), 
    PALM(131072), 
    VEIN(262144), 
    FOOT(524288), 
    PALM_GEOMETRY(1048576);
    
    private int value;
    private static final NBiometricTypeLibrary LIBRARY;
    private static final Map<Integer, NBiometricType> lookup;
    
    private NBiometricType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NBiometricType get(final int value) {
        return NBiometricType.lookup.get(value);
    }
    
    public static int getValue(final EnumSet<NBiometricType> values) {
        int value = 0;
        for (final NBiometricType v : values) {
            value |= v.getValue();
        }
        return value;
    }
    
    public static EnumSet<NBiometricType> getSet(final int value) {
        final EnumSet<NBiometricType> values = EnumSet.noneOf(NBiometricType.class);
        for (final Map.Entry<Integer, NBiometricType> entry : NBiometricType.lookup.entrySet()) {
            if ((entry.getKey() & value) == entry.getKey()) {
                values.add(entry.getValue());
            }
        }
        if (value != getValue(values)) {
            throw new IllegalArgumentException("value contains unknown bit");
        }
        return values;
    }
    
    public static boolean isValid(final EnumSet<NBiometricType> value) {
        return NBiometricType.LIBRARY.NBiometricTypeIsValid(getValue(value));
    }
    
    static {
        LIBRARY = (NBiometricTypeLibrary)Native.loadLibrary("NBiometrics", NBiometricTypeLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        lookup = new HashMap<Integer, NBiometricType>();
        for (final NBiometricType s : EnumSet.allOf(NBiometricType.class)) {
            NBiometricType.lookup.put(s.getValue(), s);
        }
    }
    
    interface NBiometricTypeLibrary extends NLibrary
    {
        boolean NBiometricTypeIsValid(final int p0);
    }
}
