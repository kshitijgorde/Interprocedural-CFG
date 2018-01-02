// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum Shutdown implements Constant
{
    SHUT_RD, 
    SHUT_WR, 
    SHUT_RDWR, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<Shutdown> resolver;
    
    public final int value() {
        return Shutdown.resolver.intValue(this);
    }
    
    public final String description() {
        return Shutdown.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final Shutdown valueOf(final int value) {
        return Shutdown.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getBitmaskResolver(Shutdown.class);
    }
}
