// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.freebsd;

import com.kenai.constantine.Constant;

public enum NameInfo implements Constant
{
    NI_MAXHOST(1025), 
    NI_MAXSERV(32), 
    NI_NOFQDN(1), 
    NI_NUMERICHOST(2), 
    NI_NAMEREQD(4), 
    NI_NUMERICSERV(8), 
    NI_DGRAM(16);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 1025L;
    
    private NameInfo(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
