// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum AddressFamily implements Constant
{
    AF_UNSPEC(0), 
    AF_UNIX(1), 
    AF_INET(2), 
    AF_IMPLINK(3), 
    AF_PUP(4), 
    AF_CHAOS(5), 
    AF_NS(6), 
    AF_OSI(19), 
    AF_ECMA(8), 
    AF_DATAKIT(9), 
    AF_CCITT(10), 
    AF_SNA(11), 
    AF_DECnet(12), 
    AF_DLI(13), 
    AF_LAT(14), 
    AF_HYLINK(15), 
    AF_APPLETALK(16), 
    AF_ROUTE(24), 
    AF_LINK(25), 
    AF_IPX(23), 
    AF_INET6(26), 
    AF_MAX(30);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 30L;
    
    private AddressFamily(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
