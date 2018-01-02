// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum RLIMIT implements Constant
{
    RLIMIT_AS(1), 
    RLIMIT_CORE(2), 
    RLIMIT_CPU(3), 
    RLIMIT_DATA(4), 
    RLIMIT_FSIZE(5), 
    RLIMIT_LOCKS(6), 
    RLIMIT_MEMLOCK(7), 
    RLIMIT_MSGQUEUE(8), 
    RLIMIT_NICE(9), 
    RLIMIT_NLIMITS(10), 
    RLIMIT_NOFILE(11), 
    RLIMIT_NPROC(12), 
    RLIMIT_OFILE(13), 
    RLIMIT_RSS(14), 
    RLIMIT_RTPRIO(15), 
    RLIMIT_RTTIME(16), 
    RLIMIT_SIGPENDING(17), 
    RLIMIT_STACK(18);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 18L;
    
    private RLIMIT(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
