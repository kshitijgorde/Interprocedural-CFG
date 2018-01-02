// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum RLIM implements Constant
{
    RLIM_NLIMITS(1), 
    RLIM_INFINITY(2), 
    RLIM_SAVED_MAX(3), 
    RLIM_SAVED_CUR(4);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 4L;
    
    private RLIM(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
