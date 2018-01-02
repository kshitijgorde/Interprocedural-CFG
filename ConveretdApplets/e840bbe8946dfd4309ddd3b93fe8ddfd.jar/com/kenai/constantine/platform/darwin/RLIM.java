// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum RLIM implements Constant
{
    RLIM_NLIMITS(9), 
    RLIM_INFINITY(-1), 
    RLIM_SAVED_MAX(-1), 
    RLIM_SAVED_CUR(-1);
    
    private final int value;
    public static final long MIN_VALUE = 9L;
    public static final long MAX_VALUE = -1L;
    
    private RLIM(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
