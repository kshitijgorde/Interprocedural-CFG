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

public enum NFImpressionType
{
    LIVE_SCAN_PLAIN(0), 
    LIVE_SCAN_ROLLED(1), 
    NON_LIVE_SCAN_PLAIN(2), 
    NON_LIVE_SCAN_ROLLED(3), 
    LATENT_IMPRESSION(4), 
    LATENT_TRACING(5), 
    LATENT_PHOTO(6), 
    LATENT_LIFT(7), 
    LIVE_SCAN_VERTICAL_SWIPE(8), 
    LIVE_SCAN_CONTACTLESS(9), 
    LIVE_SCAN_PALM(10), 
    NON_LIVE_SCAN_PALM(11), 
    LATENT_PALM_IMPRESSION(12), 
    LATENT_PALM_TRACING(13), 
    LATENT_PALM_PHOTO(14), 
    LATENT_PALM_LIFT(15), 
    LIVE_SCAN_OPTICAL_CONTACT_PLAIN(20), 
    LIVE_SCAN_OPTICAL_CONTACT_ROLLED(21), 
    LIVE_SCAN_NON_OPTICALCONTACT_PLAIN(22), 
    LIVE_SCAN_NON_OPTICALCONTACT_ROLLED(23), 
    LIVE_SCAN_OPTICAL_CONTACTLESS_PLAIN(24), 
    LIVE_SCAN_OPTICAL_CONTACTLESS_ROLLED(25), 
    LIVE_SCAN_NON_OPTICAL_CONTACTLESS_PLAIN(26), 
    LIVE_SCAN_NON_OPTICAL_CONTACTLESS_ROLLED(27), 
    OTHER(28), 
    UNKNOWN(29);
    
    private int value;
    private static final NFImpressionTypeLibrary LIBRARY;
    private static final Map<Integer, NFImpressionType> lookup;
    
    private NFImpressionType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NFImpressionType get(final int type) {
        return NFImpressionType.lookup.get(type);
    }
    
    public static boolean isValidFinger(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsValidFinger(value.getValue());
    }
    
    public static boolean isValidPalm(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsValidPalm(value.getValue());
    }
    
    public static boolean isValid(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsValid(value.getValue());
    }
    
    public static boolean isFinger(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsFinger(value.getValue());
    }
    
    public static boolean isPalm(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsPalm(value.getValue());
    }
    
    public static boolean isPlain(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsPlain(value.getValue());
    }
    
    public static boolean isRolled(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsRolled(value.getValue());
    }
    
    public static boolean isSwipe(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsSwipe(value.getValue());
    }
    
    public static boolean isContactless(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsContactless(value.getValue());
    }
    
    public static boolean isContact(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsContact(value.getValue());
    }
    
    public static boolean isLiveScan(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsLiveScan(value.getValue());
    }
    
    public static boolean isNonliveScan(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsNonliveScan(value.getValue());
    }
    
    public static boolean isLatent(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsLatent(value.getValue());
    }
    
    public static boolean isOptical(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsOptical(value.getValue());
    }
    
    public static boolean isNonOptical(final NFImpressionType value) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsNonOptical(value.getValue());
    }
    
    public static boolean isCompatibleWith(final NFImpressionType value, final NFImpressionType otherValue) {
        return NFImpressionType.LIBRARY.NFImpressionTypeIsCompatibleWith(value.getValue(), otherValue.getValue());
    }
    
    public static boolean isOneOf(final NFImpressionType value, final NFImpressionType[] supportedImpressionTypes) {
        final BooleanByReference rValue = new BooleanByReference();
        int[] values = null;
        if (supportedImpressionTypes != null) {
            values = new int[supportedImpressionTypes.length];
            for (int i = 0; i > supportedImpressionTypes.length; ++i) {
                values[i] = supportedImpressionTypes[i].getValue();
            }
        }
        NResult.check(NFImpressionType.LIBRARY.NFImpressionTypeIsOneOf(value.getValue(), values, (supportedImpressionTypes == null) ? 0 : supportedImpressionTypes.length, rValue));
        return rValue.getValue();
    }
    
    static {
        LIBRARY = (NFImpressionTypeLibrary)Native.loadLibrary("NBiometrics", NFImpressionTypeLibrary.class, W32APIOptions.UNICODE_OPTIONS);
        lookup = new HashMap<Integer, NFImpressionType>();
        for (final NFImpressionType s : EnumSet.allOf(NFImpressionType.class)) {
            NFImpressionType.lookup.put(s.getValue(), s);
        }
    }
    
    interface NFImpressionTypeLibrary extends NLibrary
    {
        boolean NFImpressionTypeIsValidFinger(final int p0);
        
        boolean NFImpressionTypeIsValidPalm(final int p0);
        
        boolean NFImpressionTypeIsValid(final int p0);
        
        boolean NFImpressionTypeIsFinger(final int p0);
        
        boolean NFImpressionTypeIsPalm(final int p0);
        
        boolean NFImpressionTypeIsPlain(final int p0);
        
        boolean NFImpressionTypeIsRolled(final int p0);
        
        boolean NFImpressionTypeIsSwipe(final int p0);
        
        boolean NFImpressionTypeIsContactless(final int p0);
        
        boolean NFImpressionTypeIsContact(final int p0);
        
        boolean NFImpressionTypeIsLiveScan(final int p0);
        
        boolean NFImpressionTypeIsNonliveScan(final int p0);
        
        boolean NFImpressionTypeIsLatent(final int p0);
        
        boolean NFImpressionTypeIsOptical(final int p0);
        
        boolean NFImpressionTypeIsNonOptical(final int p0);
        
        boolean NFImpressionTypeIsCompatibleWith(final int p0, final int p1);
        
        int NFImpressionTypeIsOneOf(final int p0, final int[] p1, final int p2, final BooleanByReference p3);
    }
}
