// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum Shutdown implements Constant
{
    SHUT_RD(1), 
    SHUT_WR(2), 
    SHUT_RDWR(4);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 4L;
    
    private Shutdown(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
