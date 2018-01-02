// 
// Decompiled by Procyon v0.5.30
// 

package com.neurotec.plugins;

import java.util.Iterator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum NPluginState
{
    NONE(0), 
    LOAD_ERROR(1), 
    NOT_RECOGNIZED(2), 
    MODULE_OPTIONS_MISMATCH(3), 
    INVALID_MODULE(4), 
    INTERFACE_TYPE_MISMATCH(5), 
    INTERFACE_VERSION_MISMATCH(6), 
    INVALID_INTERFACE(7), 
    UNPLUGGED(64), 
    UNUSED(65), 
    DISABLED(66), 
    DUPLICATE(67), 
    INCOMPATIBLE_WITH_OTHER_PLUGINS(68), 
    PLUGGING_ERROR(69), 
    PLUGGED(128);
    
    private int value;
    private static final Map<Integer, NPluginState> lookup;
    
    private NPluginState(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static NPluginState get(final int value) {
        return NPluginState.lookup.get(value);
    }
    
    static {
        lookup = new HashMap<Integer, NPluginState>();
        for (final NPluginState s : EnumSet.allOf(NPluginState.class)) {
            NPluginState.lookup.put(s.getValue(), s);
        }
    }
}
