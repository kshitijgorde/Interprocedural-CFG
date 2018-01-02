// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.openbsd;

import com.kenai.constantine.Constant;

public enum NameInfo implements Constant
{
    NI_MAXHOST(256), 
    NI_MAXSERV(32), 
    NI_NOFQDN(4), 
    NI_NUMERICHOST(1), 
    NI_NAMEREQD(8), 
    NI_NUMERICSERV(2), 
    NI_DGRAM(16);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 256L;
    
    private NameInfo(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
