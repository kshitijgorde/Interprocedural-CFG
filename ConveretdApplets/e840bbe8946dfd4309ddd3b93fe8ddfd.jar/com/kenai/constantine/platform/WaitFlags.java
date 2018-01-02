// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum WaitFlags implements Constant
{
    WNOHANG, 
    WUNTRACED, 
    WSTOPPED, 
    WEXITED, 
    WCONTINUED, 
    WNOWAIT, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<WaitFlags> resolver;
    
    public final int value() {
        return WaitFlags.resolver.intValue(this);
    }
    
    public final String description() {
        return WaitFlags.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final WaitFlags valueOf(final int value) {
        return WaitFlags.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getBitmaskResolver(WaitFlags.class);
    }
}
