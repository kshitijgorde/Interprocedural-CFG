// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.freebsd;

import com.kenai.constantine.Constant;

public enum RLIM implements Constant
{
    RLIM_NLIMITS(11), 
    RLIM_INFINITY(-1);
    
    private final int value;
    public static final long MIN_VALUE = 11L;
    public static final long MAX_VALUE = -1L;
    
    private RLIM(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
