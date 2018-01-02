// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.freebsd;

import com.kenai.constantine.Constant;

public enum RLIMIT implements Constant
{
    RLIMIT_AS(10), 
    RLIMIT_CORE(4), 
    RLIMIT_CPU(0), 
    RLIMIT_DATA(2), 
    RLIMIT_FSIZE(1), 
    RLIMIT_MEMLOCK(6), 
    RLIMIT_NOFILE(8), 
    RLIMIT_NPROC(7), 
    RLIMIT_RSS(5), 
    RLIMIT_STACK(3);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 10L;
    
    private RLIMIT(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
