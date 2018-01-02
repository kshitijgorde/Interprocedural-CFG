// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifFaceProperties
{
    NOT_SPECIFIED(0), 
    SPECIFIED(1), 
    GLASSES(2), 
    MOUSTACHES(4), 
    BEARD(8), 
    TEETH_VISIBLE(16), 
    BLINK(32), 
    MOUTH_OPEN(64), 
    LEFT_EYE_PATCH(128), 
    RIGHT_EYE_PATCH(256), 
    BOTH_EYE_PATCH(512), 
    DARK_GLASSES(1024), 
    DISTORTING_CONDITION(2048), 
    HAT(16777216), 
    SCARF(33554432), 
    NO_EAR(67108864);
    
    private int value;
    private static final Map<Integer, BdifFaceProperties> lookup;
    
    private BdifFaceProperties(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifFaceProperties get(final int value) {
        return BdifFaceProperties.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifFaceProperties>();
        for (final BdifFaceProperties s : EnumSet.allOf(BdifFaceProperties.class)) {
            BdifFaceProperties.lookup.put(s.getValue(), s);
        }
    }
}
