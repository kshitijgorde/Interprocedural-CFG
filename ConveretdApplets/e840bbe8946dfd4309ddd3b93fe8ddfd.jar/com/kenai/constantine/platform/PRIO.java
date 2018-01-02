// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum PRIO implements Constant
{
    PRIO_MIN, 
    PRIO_PROCESS, 
    PRIO_PGRP, 
    PRIO_USER, 
    PRIO_MAX, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<PRIO> resolver;
    
    public final int value() {
        return PRIO.resolver.intValue(this);
    }
    
    public final String description() {
        return PRIO.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final PRIO valueOf(final int value) {
        return PRIO.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(PRIO.class, 20000, 29999);
    }
}
