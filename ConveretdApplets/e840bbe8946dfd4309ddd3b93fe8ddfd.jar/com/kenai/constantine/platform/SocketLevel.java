// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum SocketLevel implements Constant
{
    SOL_SOCKET, 
    SOL_IP, 
    SOL_TCP, 
    SOL_UDP, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<SocketLevel> resolver;
    
    public final int value() {
        return SocketLevel.resolver.intValue(this);
    }
    
    public final String description() {
        return SocketLevel.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final SocketLevel valueOf(final int value) {
        return SocketLevel.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(SocketLevel.class, 20000, 29999);
    }
}
