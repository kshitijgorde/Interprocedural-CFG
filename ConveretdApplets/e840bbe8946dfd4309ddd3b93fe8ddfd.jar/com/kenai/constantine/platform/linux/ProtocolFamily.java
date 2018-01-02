// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.linux;

import com.kenai.constantine.Constant;

public enum ProtocolFamily implements Constant
{
    PF_UNSPEC(0), 
    PF_LOCAL(1), 
    PF_UNIX(1), 
    PF_INET(2), 
    PF_SNA(22), 
    PF_DECnet(12), 
    PF_APPLETALK(5), 
    PF_ROUTE(16), 
    PF_IPX(4), 
    PF_KEY(15), 
    PF_INET6(10), 
    PF_MAX(34);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 34L;
    
    private ProtocolFamily(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
