// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifFaceExpression
{
    UNSPECIFIED(0), 
    NEUTRAL(1), 
    SMILE(2), 
    SMILE_OPENED_JAW(3), 
    RAISED_BROWS(4), 
    EYES_AWAY(5), 
    SQUINTING(6), 
    FROWNING(7);
    
    private int value;
    private static final Map<Integer, BdifFaceExpression> lookup;
    
    private BdifFaceExpression(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifFaceExpression get(final int value) {
        return BdifFaceExpression.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifFaceExpression>();
        for (final BdifFaceExpression s : EnumSet.allOf(BdifFaceExpression.class)) {
            BdifFaceExpression.lookup.put(s.getValue(), s);
        }
    }
}
