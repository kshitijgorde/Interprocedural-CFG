// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;
import java.util.Map;

public enum NBiometricStatus
{
    NONE(0), 
    OK(1), 
    CANCELLED(2), 
    TIMEOUT(3), 
    CLEANING_NEEDED(10), 
    OBJECTS_NOT_REMOVED(20), 
    OBJECT_MISSING(21), 
    OBJECT_NOT_FOUND(22), 
    TOO_FEWO_BJECTS(23), 
    TOO_MANY_OBJECTS(24), 
    BAD_OBJECT_SEQUENCE(25), 
    SPOOF_DETECTED(30), 
    BAD_OBJECT(40), 
    TOO_SOFT(51), 
    TOO_HARD(52), 
    BAD_POSITION(60), 
    TOO_NORTH(61), 
    TOO_EAST(62), 
    TOO_SOUTH(63), 
    TOO_WEST(64), 
    TOO_CLOSE(65), 
    TOO_FAR(66), 
    BAD_SPEED(70), 
    TOO_SLOW(71), 
    TOO_FAST(72), 
    BAD_SIZE(80), 
    TOO_SHORT(81), 
    TOO_LONG(82), 
    TOO_NARROW(83), 
    TOO_WIDE(84), 
    TOO_SKEWED(91), 
    WRONG_DIRECTION(92);
    
    private int value;
    private static final NBiometricStatusLibrary LIBRARY;
    private static final Map<Integer, NBiometricStatus> lookup;
    
    private NBiometricStatus(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NBiometricStatus get(final int value) {
        return NBiometricStatus.lookup.get(value);
    }
    
    public static boolean isValid(final NBiometricStatus value) {
        return NBiometricStatus.LIBRARY.NBiometricStatusIsValid(value.getValue());
    }
    
    static {
        LIBRARY = (NBiometricStatusLibrary)Native.loadLibrary("NBiometrics", NBiometricStatusLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        lookup = new HashMap<Integer, NBiometricStatus>();
        for (final NBiometricStatus s : EnumSet.allOf(NBiometricStatus.class)) {
            NBiometricStatus.lookup.put(s.getValue(), s);
        }
    }
    
    interface NBiometricStatusLibrary extends NLibrary
    {
        boolean NBiometricStatusIsValid(final int p0);
    }
}
