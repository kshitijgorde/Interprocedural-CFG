// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum Shutdown implements Constant
{
    SHUT_RD(0), 
    SHUT_WR(1), 
    SHUT_RDWR(2);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 2L;
    
    private Shutdown(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
