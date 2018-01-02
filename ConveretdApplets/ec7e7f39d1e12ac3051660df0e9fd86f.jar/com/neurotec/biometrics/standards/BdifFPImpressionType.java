// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifFPImpressionType
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
    LIVE_SCAN_NON_OPTICAL_CONTACT_PLAIN(22), 
    LIVE_SCAN_NON_OPTICAL_CONTACT_ROLLED(23), 
    LIVE_SCAN_OPTICAL_CONTACTLESS_PLAIN(24), 
    LIVE_SCAN_OPTICAL_CONTACTLESS_ROLLED(25), 
    LIVE_SCAN_NON_OPTICAL_CONTACTLESS_PLAIN(26), 
    LIVE_SCAN_NON_OPTICAL_CONTACTLESS_ROLLED(27), 
    OTHER(28), 
    UNKNOWN(29);
    
    private int value;
    private static final Map<Integer, BdifFPImpressionType> lookup;
    
    private BdifFPImpressionType(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifFPImpressionType get(final int value) {
        return BdifFPImpressionType.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifFPImpressionType>();
        for (final BdifFPImpressionType s : EnumSet.allOf(BdifFPImpressionType.class)) {
            BdifFPImpressionType.lookup.put(s.getValue(), s);
        }
    }
}
