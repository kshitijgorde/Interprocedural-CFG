// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum TCP implements Constant
{
    TCP_MAX_SACK, 
    TCP_MSS, 
    TCP_MINMSS, 
    TCP_MINMSSOVERLOAD, 
    TCP_MAXWIN, 
    TCP_MAX_WINSHIFT, 
    TCP_MAXBURST, 
    TCP_MAXHLEN, 
    TCP_MAXOLEN, 
    TCP_NODELAY, 
    TCP_MAXSEG, 
    TCP_NOPUSH, 
    TCP_NOOPT, 
    TCP_KEEPALIVE, 
    TCP_NSTATES, 
    TCP_RETRANSHZ, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<TCP> resolver;
    
    public final int value() {
        return TCP.resolver.intValue(this);
    }
    
    public final String description() {
        return TCP.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final TCP valueOf(final int value) {
        return TCP.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(TCP.class, 20000, 29999);
    }
}
