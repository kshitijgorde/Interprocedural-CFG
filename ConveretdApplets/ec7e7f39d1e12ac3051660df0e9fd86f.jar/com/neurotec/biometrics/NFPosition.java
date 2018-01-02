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

public enum NFPosition
{
    UNKNOWN(0), 
    RIGHT_THUMB(1), 
    RIGHT_INDEX_FINGER(2), 
    RIGHT_MIDDLE_FINGER(3), 
    RIGHT_RING_FINGER(4), 
    RIGHT_LITTLE_FINGER(5), 
    LEFT_THUMB(6), 
    LEFT_INDEX_FINGER(7), 
    LEFT_MIDDLE_FINGER(8), 
    LEFT_RING_FINGER(9), 
    LEFT_LITTLE_FINGER(10), 
    PLAIN_RIGHT_THUMB(11), 
    PLAIN_LEFT_THUMB(12), 
    PLAIN_RIGHT_FOUR_FINGERS(13), 
    PLAIN_LEFT_FOUR_FINGERS(14), 
    PLAIN_THUMBS(15), 
    UNKNOWN_PALM(20), 
    RIGHT_FULL_PALM(21), 
    RIGHT_WRITERS_PALM(22), 
    LEFT_FULL_PALM(23), 
    LEFT_WRITERS_PALM(24), 
    RIGHT_LOWER_PALM(25), 
    RIGHT_UPPER_PALM(26), 
    LEFT_LOWER_PALM(27), 
    LEFT_UPPER_PALM(28), 
    RIGHT_OTHER(29), 
    LEFT_OTHER(30), 
    RIGHT_INTERDIGITAL(31), 
    RIGHT_THENAR(32), 
    RIGHT_HYPOTHENAR(33), 
    LEFT_INTERDIGITAL(34), 
    LEFT_THENAR(35), 
    LEFT_HYPOTHENAR(36), 
    RIGHT_INDEX_MIDDLE_FINGERS(40), 
    RIGHT_MIDDLE_RING_FINGERS(41), 
    RIGHT_RING_LITTLE_FINGERS(42), 
    LEFT_INDEX_MIDDLE_FINGERS(43), 
    LEFT_MIDDLE_RING_FINGERS(44), 
    LEFT_RING_LITTLE_FINGERS(45), 
    RIGHT_INDEX_LEFT_INDEX_FINGERS(46), 
    RIGHT_INDEX_MIDDLE_RING_FINGERS(47), 
    RIGHT_MIDDLE_RING_LITTLE_FINGERS(48), 
    LEFT_INDEX_MIDDLE_RING_FINGERS(49), 
    LEFT_MIDDLE_RING_LITTLE_FINGERS(50), 
    UNKNOWN_TWO_FINGERS(-2), 
    UNKNOWN_THREE_FINGERS(-3), 
    UNKNOWN_FOUR_FINGERS(-4);
    
    private int value;
    private static final NFPositionLibrary LIBRARY;
    private static final Map<Integer, NFPosition> lookup;
    
    private NFPosition(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NFPosition get(final int value) {
        return NFPosition.lookup.get(value);
    }
    
    public static boolean isValidTheFinger(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsValidTheFinger(value.getValue());
    }
    
    public static boolean isValidFinger(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsValidFinger(value.getValue());
    }
    
    public static boolean isValidPalm(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsValidPalm(value.getValue());
    }
    
    public static boolean isValid(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsValid(value.getValue());
    }
    
    public static boolean isTheFinger(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsTheFinger(value.getValue());
    }
    
    public static boolean isFinger(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsFinger(value.getValue());
    }
    
    public static boolean isPalm(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsPalm(value.getValue());
    }
    
    public static boolean isSingleFinger(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsSingleFinger(value.getValue());
    }
    
    public static boolean isTwoFingers(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsTwoFingers(value.getValue());
    }
    
    public static boolean isThreeFingers(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsThreeFingers(value.getValue());
    }
    
    public static boolean isFourFingers(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsFourFingers(value.getValue());
    }
    
    public static boolean isKnown(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsKnown(value.getValue());
    }
    
    public static boolean isRight(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsRight(value.getValue());
    }
    
    public static boolean isLeft(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsLeft(value.getValue());
    }
    
    public static boolean isLeftAndRight(final NFPosition value) {
        return NFPosition.LIBRARY.NFPositionIsLeftAndRight(value.getValue());
    }
    
    public static boolean isCompatibleWith(final NFPosition value, final NFImpressionType imp) {
        return NFPosition.LIBRARY.NFPositionIsCompatibleWith(value.getValue(), imp.getValue());
    }
    
    public static boolean isOneOf(final NFPosition value, final NFPosition[] supportedPositions) {
        final BooleanByReference rValue = new BooleanByReference();
        int[] values = null;
        if (supportedPositions != null) {
            values = new int[supportedPositions.length];
            for (int i = 0; i > supportedPositions.length; ++i) {
                values[i] = supportedPositions[i].getValue();
            }
        }
        NResult.check(NFPosition.LIBRARY.NFPositionIsOneOf(value.getValue(), values, (supportedPositions == null) ? 0 : supportedPositions.length, rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NFPositionLibrary)Native.loadLibrary("NBiometrics", NFPositionLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        lookup = new HashMap<Integer, NFPosition>();
        for (final NFPosition s : EnumSet.allOf(NFPosition.class)) {
            NFPosition.lookup.put(s.getValue(), s);
        }
    }
    
    interface NFPositionLibrary extends NLibrary
    {
        boolean NFPositionIsValidTheFinger(final int p0);
        
        boolean NFPositionIsValidFinger(final int p0);
        
        boolean NFPositionIsValidPalm(final int p0);
        
        boolean NFPositionIsValid(final int p0);
        
        boolean NFPositionIsTheFinger(final int p0);
        
        boolean NFPositionIsFinger(final int p0);
        
        boolean NFPositionIsPalm(final int p0);
        
        boolean NFPositionIsSingleFinger(final int p0);
        
        boolean NFPositionIsTwoFingers(final int p0);
        
        boolean NFPositionIsThreeFingers(final int p0);
        
        boolean NFPositionIsFourFingers(final int p0);
        
        boolean NFPositionIsKnown(final int p0);
        
        boolean NFPositionIsRight(final int p0);
        
        boolean NFPositionIsLeft(final int p0);
        
        boolean NFPositionIsLeftAndRight(final int p0);
        
        boolean NFPositionIsCompatibleWith(final int p0, final int p1);
        
        int NFPositionIsOneOf(final int p0, final int[] p1, final int p2, final BooleanByReference p3);
    }
}
