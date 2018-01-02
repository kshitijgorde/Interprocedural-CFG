// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum ProtocolFamily implements Constant
{
    PF_UNSPEC(0), 
    PF_UNIX(1), 
    PF_INET(2), 
    PF_IMPLINK(3), 
    PF_PUP(4), 
    PF_CHAOS(5), 
    PF_NS(6), 
    PF_OSI(19), 
    PF_ECMA(8), 
    PF_DATAKIT(9), 
    PF_CCITT(10), 
    PF_SNA(11), 
    PF_DECnet(12), 
    PF_DLI(13), 
    PF_LAT(14), 
    PF_HYLINK(15), 
    PF_APPLETALK(16), 
    PF_ROUTE(24), 
    PF_LINK(25), 
    PF_IPX(23), 
    PF_KEY(27), 
    PF_INET6(26), 
    PF_MAX(30);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 30L;
    
    private ProtocolFamily(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
