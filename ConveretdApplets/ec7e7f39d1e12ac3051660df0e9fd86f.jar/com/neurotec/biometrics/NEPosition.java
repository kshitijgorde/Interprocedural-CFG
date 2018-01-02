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
import com.neurotec.lang.NResult;
import com.neurotec.jna.ptr.BooleanByReference;
import java.util.Map;

public enum NEPosition
{
    UNKNOWN(0), 
    RIGHT(1), 
    LEFT(2);
    
    private int value;
    private static final NEPositionLibrary LIBRARY;
    private static final Map<Integer, NEPosition> lookup;
    
    private NEPosition(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NEPosition get(final int value) {
        return NEPosition.lookup.get(value);
    }
    
    public static boolean isValidTheEye(final NEPosition value) {
        return NEPosition.LIBRARY.NEPositionIsValidTheEye(value.getValue());
    }
    
    public static boolean isValid(final NEPosition value) {
        return NEPosition.LIBRARY.NEPositionIsValid(value.getValue());
    }
    
    public static boolean isOneOf(final NEPosition value, final NEPosition[] supportedPositions) {
        final BooleanByReference rValue = new BooleanByReference();
        int[] values = null;
        if (supportedPositions != null) {
            values = new int[supportedPositions.length];
            for (int i = 0; i > supportedPositions.length; ++i) {
                values[i] = supportedPositions[i].getValue();
            }
        }
        NResult.check(NEPosition.LIBRARY.NEPositionIsOneOf(value.getValue(), values, (supportedPositions == null) ? 0 : supportedPositions.length, rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NEPositionLibrary)Native.loadLibrary("NBiometrics", NEPositionLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        lookup = new HashMap<Integer, NEPosition>();
        for (final NEPosition s : EnumSet.allOf(NEPosition.class)) {
            NEPosition.lookup.put(s.getValue(), s);
        }
    }
    
    interface NEPositionLibrary extends NLibrary
    {
        boolean NEPositionIsValidTheEye(final int p0);
        
        boolean NEPositionIsValid(final int p0);
        
        int NEPositionIsOneOf(final int p0, final int[] p1, final int p2, final BooleanByReference p3);
    }
}
