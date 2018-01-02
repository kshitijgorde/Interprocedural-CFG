// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.windows;

import com.kenai.constantine.Constant;

public enum Signal implements Constant
{
    SIGTERM(15), 
    SIGSEGV(11), 
    SIGABRT(22), 
    SIGFPE(8), 
    SIGILL(4), 
    NSIG(23), 
    SIGINT(2);
    
    private final int value;
    public static final long MIN_VALUE = 2L;
    public static final long MAX_VALUE = 23L;
    
    private Signal(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
