// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ANBiometricType
{
    NO_INFORMATION_GIVEN(0), 
    MULTIPLE_BIOMETRICS_USED(1), 
    FACIAL_FEATURES(2), 
    VOICE(4), 
    FINGERPRINT(8), 
    IRIS(16), 
    RETINA(32), 
    HAND_GEOMETRY(64), 
    SIGNATURE_DYNAMICS(128), 
    KEYSTROKE_DYNAMICS(256), 
    LIP_MOVEMENT(512), 
    THERMAL_FACE_IMAGE(1024), 
    THERMAL_HAND_IMAGE(2048), 
    GAIT(4096), 
    BODY_ODOR(8192), 
    DNA(16384), 
    EAR_SHAPE(32768), 
    FINGER_GEOMETRY(65536), 
    PALM_PRINT(131072), 
    VEIN_PATTERN(262144), 
    FOOT_PRINT(524288);
    
    private int value;
    private static final Map<Integer, ANBiometricType> lookup;
    
    private ANBiometricType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ANBiometricType get(final int value) {
        return ANBiometricType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, ANBiometricType>();
        for (final ANBiometricType s : EnumSet.allOf(ANBiometricType.class)) {
            ANBiometricType.lookup.put(s.getValue(), s);
        }
    }
}
