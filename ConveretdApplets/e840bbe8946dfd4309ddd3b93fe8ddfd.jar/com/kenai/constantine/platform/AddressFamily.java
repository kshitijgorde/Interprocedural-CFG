// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum AddressFamily implements Constant
{
    AF_UNSPEC, 
    AF_LOCAL, 
    AF_UNIX, 
    AF_INET, 
    AF_IMPLINK, 
    AF_PUP, 
    AF_CHAOS, 
    AF_NS, 
    AF_ISO, 
    AF_OSI, 
    AF_ECMA, 
    AF_DATAKIT, 
    AF_CCITT, 
    AF_SNA, 
    AF_DECnet, 
    AF_DLI, 
    AF_LAT, 
    AF_HYLINK, 
    AF_APPLETALK, 
    AF_ROUTE, 
    AF_LINK, 
    pseudo_AF_XTP, 
    AF_COIP, 
    AF_CNT, 
    pseudo_AF_RTIP, 
    AF_IPX, 
    AF_SIP, 
    pseudo_AF_PIP, 
    AF_NDRV, 
    AF_ISDN, 
    AF_E164, 
    pseudo_AF_KEY, 
    AF_INET6, 
    AF_NATM, 
    AF_SYSTEM, 
    AF_NETBIOS, 
    AF_PPP, 
    AF_ATM, 
    pseudo_AF_HDRCMPLT, 
    AF_NETGRAPH, 
    AF_AX25, 
    AF_MAX, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<AddressFamily> resolver;
    
    public final int value() {
        return AddressFamily.resolver.intValue(this);
    }
    
    public final String description() {
        return AddressFamily.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final AddressFamily valueOf(final int value) {
        return AddressFamily.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(AddressFamily.class, 20000, 29999);
    }
}
