// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.lang;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NModuleOptions
{
    NONE(0), 
    DEBUG(1), 
    PROTECTED(2), 
    UNICODE(4), 
    NOANSIFUNC(8), 
    NOUNICODE(16), 
    LIB(32), 
    EXE(64);
    
    private int value;
    private static final Map<Integer, NModuleOptions> lookup;
    
    private NModuleOptions(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NModuleOptions get(final int value) {
        return NModuleOptions.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NModuleOptions>();
        for (final NModuleOptions s : EnumSet.allOf(NModuleOptions.class)) {
            NModuleOptions.lookup.put(s.getValue(), s);
        }
    }
}
