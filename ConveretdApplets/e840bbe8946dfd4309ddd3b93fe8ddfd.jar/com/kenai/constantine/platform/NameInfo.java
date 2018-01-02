// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum NameInfo implements Constant
{
    NI_MAXHOST, 
    NI_MAXSERV, 
    NI_NOFQDN, 
    NI_NUMERICHOST, 
    NI_NAMEREQD, 
    NI_NUMERICSERV, 
    NI_DGRAM, 
    NI_WITHSCOPEID, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<NameInfo> resolver;
    
    public final int value() {
        return NameInfo.resolver.intValue(this);
    }
    
    public final String description() {
        return NameInfo.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final NameInfo valueOf(final int value) {
        return NameInfo.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(NameInfo.class, 20000, 29999);
    }
}
