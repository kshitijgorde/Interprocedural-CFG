// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum Sock implements Constant
{
    SOCK_STREAM, 
    SOCK_DGRAM, 
    SOCK_RAW, 
    SOCK_RDM, 
    SOCK_SEQPACKET, 
    SOCK_MAXADDRLEN, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<Sock> resolver;
    
    public final int value() {
        return Sock.resolver.intValue(this);
    }
    
    public final String description() {
        return Sock.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final Sock valueOf(final int value) {
        return Sock.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(Sock.class, 20000, 29999);
    }
}
