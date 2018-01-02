// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum TCP implements Constant
{
    TCP_MSS(536), 
    TCP_NODELAY(1), 
    TCP_MAXSEG(2), 
    TCP_KEEPALIVE(8);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 536L;
    
    private TCP(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
