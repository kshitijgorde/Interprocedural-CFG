// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum INAddr implements Constant
{
    INADDR_ANY, 
    INADDR_BROADCAST, 
    INADDR_NONE, 
    INADDR_LOOPBACK, 
    INADDR_UNSPEC_GROUP, 
    INADDR_ALLHOSTS_GROUP, 
    INADDR_ALLRTRS_GROUP, 
    INADDR_MAX_LOCAL_GROUP, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<INAddr> resolver;
    
    public final int value() {
        return INAddr.resolver.intValue(this);
    }
    
    public final String description() {
        return INAddr.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final INAddr valueOf(final int value) {
        return INAddr.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(INAddr.class, 20000, 29999);
    }
}
