// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum ProtocolFamily implements Constant
{
    PF_UNSPEC, 
    PF_LOCAL, 
    PF_UNIX, 
    PF_INET, 
    PF_IMPLINK, 
    PF_PUP, 
    PF_CHAOS, 
    PF_NS, 
    PF_ISO, 
    PF_OSI, 
    PF_ECMA, 
    PF_DATAKIT, 
    PF_CCITT, 
    PF_SNA, 
    PF_DECnet, 
    PF_DLI, 
    PF_LAT, 
    PF_HYLINK, 
    PF_APPLETALK, 
    PF_ROUTE, 
    PF_LINK, 
    PF_XTP, 
    PF_COIP, 
    PF_CNT, 
    PF_SIP, 
    PF_IPX, 
    PF_RTIP, 
    PF_PIP, 
    PF_NDRV, 
    PF_ISDN, 
    PF_KEY, 
    PF_INET6, 
    PF_NATM, 
    PF_SYSTEM, 
    PF_NETBIOS, 
    PF_PPP, 
    PF_ATM, 
    PF_NETGRAPH, 
    PF_MAX, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<ProtocolFamily> resolver;
    
    public final int value() {
        return ProtocolFamily.resolver.intValue(this);
    }
    
    public final String description() {
        return ProtocolFamily.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final ProtocolFamily valueOf(final int value) {
        return ProtocolFamily.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(ProtocolFamily.class, 20000, 29999);
    }
}