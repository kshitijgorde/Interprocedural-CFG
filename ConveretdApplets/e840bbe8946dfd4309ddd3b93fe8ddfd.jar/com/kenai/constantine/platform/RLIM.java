// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum RLIM implements Constant
{
    RLIM_NLIMITS, 
    RLIM_INFINITY, 
    RLIM_SAVED_MAX, 
    RLIM_SAVED_CUR, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<RLIM> resolver;
    
    public final int value() {
        return RLIM.resolver.intValue(this);
    }
    
    public final String description() {
        return RLIM.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final RLIM valueOf(final int value) {
        return RLIM.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(RLIM.class, 20000, 29999);
    }
}
