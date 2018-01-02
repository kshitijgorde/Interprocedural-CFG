// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum RLIMIT implements Constant
{
    RLIMIT_AS, 
    RLIMIT_CORE, 
    RLIMIT_CPU, 
    RLIMIT_DATA, 
    RLIMIT_FSIZE, 
    RLIMIT_LOCKS, 
    RLIMIT_MEMLOCK, 
    RLIMIT_MSGQUEUE, 
    RLIMIT_NICE, 
    RLIMIT_NLIMITS, 
    RLIMIT_NOFILE, 
    RLIMIT_NPROC, 
    RLIMIT_OFILE, 
    RLIMIT_RSS, 
    RLIMIT_RTPRIO, 
    RLIMIT_RTTIME, 
    RLIMIT_SIGPENDING, 
    RLIMIT_STACK, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<RLIMIT> resolver;
    
    public final int value() {
        return RLIMIT.resolver.intValue(this);
    }
    
    public final String description() {
        return RLIMIT.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final RLIMIT valueOf(final int value) {
        return RLIMIT.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(RLIMIT.class, 20000, 29999);
    }
}
