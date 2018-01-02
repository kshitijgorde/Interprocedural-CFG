// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.biometrics.standards;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum BdifStandard
{
    ISO(0), 
    ANSI(1);
    
    private int value;
    private static final Map<Integer, BdifStandard> lookup;
    
    private BdifStandard(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static BdifStandard get(final int value) {
        return BdifStandard.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, BdifStandard>();
        for (final BdifStandard s : EnumSet.allOf(BdifStandard.class)) {
            BdifStandard.lookup.put(s.getValue(), s);
        }
    }
}
