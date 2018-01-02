// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NfeReturnedImage
{
    NONE(0), 
    BINARIZED(1), 
    SKELETONIZED(2);
    
    private int value;
    private static final Map<Integer, NfeReturnedImage> lookup;
    
    private NfeReturnedImage(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NfeReturnedImage get(final int value) {
        return NfeReturnedImage.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NfeReturnedImage>();
        for (final NfeReturnedImage s : EnumSet.allOf(NfeReturnedImage.class)) {
            NfeReturnedImage.lookup.put(s.getValue(), s);
        }
    }
}
