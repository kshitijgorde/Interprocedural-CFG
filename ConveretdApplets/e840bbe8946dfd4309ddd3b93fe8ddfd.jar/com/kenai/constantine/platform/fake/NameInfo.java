// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum NameInfo implements Constant
{
    NI_MAXHOST(1), 
    NI_MAXSERV(2), 
    NI_NOFQDN(3), 
    NI_NUMERICHOST(4), 
    NI_NAMEREQD(5), 
    NI_NUMERICSERV(6), 
    NI_DGRAM(7), 
    NI_WITHSCOPEID(8);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 8L;
    
    private NameInfo(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
