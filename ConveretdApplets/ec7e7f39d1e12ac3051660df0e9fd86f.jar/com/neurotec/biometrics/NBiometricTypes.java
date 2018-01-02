// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import com.neurotec.jna.NLibrary;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public class NBiometricTypes
{
    private static final NBiometricTypesLibrary LIBRARY;
    public static final byte QUALITY_MIN = 0;
    public static final byte QUALITY_MAX = 100;
    public static final byte QUALITY_UNKNOWN = -2;
    public static final byte QUALITY_FAILED = -1;
    
    public static boolean isValid(final byte value) {
        return NBiometricTypes.LIBRARY.NBiometricQualityIsValid(value);
    }
    
    static {
        LIBRARY = (NBiometricTypesLibrary)Native.loadLibrary("NBiometrics", NBiometricTypesLibrary.class, W32APIOptions.UNICODE_OPTIONS);
    }
    
    interface NBiometricTypesLibrary extends NLibrary
    {
        boolean NBiometricQualityIsValid(final byte p0);
    }
}
